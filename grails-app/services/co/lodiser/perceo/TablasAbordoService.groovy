package co.lodiser.perceo

import grails.gorm.transactions.Transactional

import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.TimeUnit
import java.util.function.Function
import java.util.function.Predicate

@Transactional
class TablasAbordoService {

    def buscarClientes(){
        def hoy = new Date()
        def clientes = Cliente.findAll()
        println "Calculando rutas para "+clientes.size+" clientes"
        for (cli in clientes){
            def destinos = Destino.findAllByCliente(cli)
            println "\tEncontrados "+destinos.size+" destinos para el cliente "+cli.unidadNombre
            if (!destinos){
                System.err.println("\t Terminando calculos para el cliente")
                return
            }
            def consumosPendientes = Consumo.findAllByVehiculoInListAndRutasIsEmpty(cli.vehiculos)
            buscarConsumos(consumosPendientes, destinos)
        }
    }

    def buscarConsumos(def consumosPendientes, def destinos){
        println "\t Encontrados "+consumosPendientes.size+" consumos pendientes"
        for (con in consumosPendientes) {
            println "\t\tBuscando consumo anterior de vehiculo "+con.vehiculo
            def consSig = Consumo.findByVehiculoAndFechaGreaterThanOrderByFechaAscTop1(con.vehiculo, con.fecha)
            println "\t\tEncontrado consumo siguiente: "+consSig
            if (consSig){
                println "\t\tRegistrando rutas entre consumos"
                registrarRuta(con,consSig,destinos)
            }
        }
    }

    @Transactional
    def registrarRuta(Consumo inicio, Consumo fin, List<Destino> destinos) {
        def autonomia = inicio.cantidad * inicio.vehiculo.tasaFalla.tasa
        def candidatos = destinos.stream()
                .filter(StreamsUtil.distinctByKey({ p -> p.distancia }))
                .filter({ d -> d.getDistancia() <= autonomia })
                .map({ d -> d.distancia })
                .findAll()

        def dias = TimeUnit.DAYS.convert(fin.fecha.time - inicio.fecha.time, TimeUnit.MILLISECONDS)+1

        println "\t\tBuscando rutas para autonomia de "+autonomia+" en "+dias+" dias"
        def combinacion = DestinosUtil.encontrarRutas(candidatos, autonomia);
        Collections.shuffle(combinacion);

        //Tomando combinaciones al azar con fechas aleatorias
        Destino destino
        List<Ruta> rutas = new ArrayList<>()
        Ruta ruta
        for (r in combinacion){
            destino = destinos.stream()
                    .filter({ x -> r.equals(x.distancia) })
                    .find()

            ruta = new Ruta(
                    destino:destino,
                    consumo:inicio,
                    fecha:DateUtil.addHour(inicio.fecha, new Random().nextInt((int)dias*24))
            )

            rutas.add(ruta)
        }

        //Ordenando rutas por fecha
        Collections.sort(rutas, new Comparator<Ruta>() {
            @Override
            int compare(Ruta o1,Ruta o2) {
                return o1.fecha.compareTo(o2.fecha)
            }
        })

        //Buscando la ruta registrada anterior
        Double kmInicial = inicio.vehiculo.kmInicial
        def anterior = Ruta.where{
            fecha == max(fecha).of {consumo.vehiculo == inicio.vehiculo}
        }.find()

        if (anterior){
            kmInicial = Math.max(anterior.kmFinal + 1, kmInicial)
        }

        //Insertando rutas finales
        for (r in rutas){
            r.anterior = anterior
            r.kmInicial = kmInicial
            r.kmFinal = kmInicial + r.destino.distancia
            r.save()

            kmInicial = r.kmFinal + 1
            anterior = r
        }

        println "\t\tRegistradas "+rutas.size+" rutas"
    }
}
