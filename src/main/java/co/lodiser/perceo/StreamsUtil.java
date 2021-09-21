package co.lodiser.perceo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

public class StreamsUtil {

    public static <T> Predicate<T> distinctByKey(
            Function<? super T, ?> keyExtractor) {

        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return  t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}