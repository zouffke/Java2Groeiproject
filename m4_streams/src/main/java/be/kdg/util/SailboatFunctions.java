package be.kdg.util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;

public class SailboatFunctions {

    public static <T> List<T> filteredList(List<T> list, Predicate<T> predicate) {
        return new ArrayList<>(list.stream()
                .filter(predicate)
                .toList());
    }

    public static <T> Double average(List<T> list, ToDoubleFunction<T> mapper) {
        return list.stream()
                .mapToDouble(mapper)
                .average()
                .getAsDouble();
    }

    public static <T> long countIf(List<T> list, Predicate<T> predicate) {
        return list.stream()
                .filter(predicate)
                .count();
    }
}
