package co.lodiser.perceo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DestinosUtil {

    public static List<Double> encontrarRutas(
            List<Double> destinos, Double autonomia){
        Double restante = autonomia;
        List<Double> rutas = new ArrayList<>();
        Double encontrado;

        Collections.sort(destinos);
        Collections.reverse(destinos);

        while (restante!=0) {
            Double finalRestante = restante;
            encontrado = destinos.stream()
                    .filter(d -> d <= finalRestante)
                    .findFirst()
                    .orElse(null);
            if (encontrado==null){
                break;
            }

            rutas.add(encontrado);
            restante = restante - encontrado;
        }

        return rutas;
    }
}
