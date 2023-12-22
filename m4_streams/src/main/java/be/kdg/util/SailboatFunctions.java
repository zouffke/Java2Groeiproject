package be.kdg.util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;

/**
 * The SailboatFunctions class provides static utility methods for working with lists of sailboats.
 * It provides methods to filter a list based on a predicate, calculate the average of a numeric property of the sailboats, and count the number of sailboats that satisfy a predicate.
 */
public class SailboatFunctions {

    /**
     * Returns a new list containing all elements of the original list that satisfy the given predicate.
     *
     * @param list the original list
     * @param predicate a predicate to apply to each element to determine if it should be included
     * @param <T> the type of elements in the list
     * @return a new list containing all elements of the original list that satisfy the given predicate
     */
    public static <T> List<T> filteredList(List<T> list, Predicate<T> predicate) {
        return new ArrayList<>(list.stream()
                .filter(predicate)
                .toList());
    }

    /**
     * Returns the average of the double values produced by applying the given function to the elements of the list.
     *
     * @param list the list of elements
     * @param mapper a function to apply to each element to produce a double value
     * @param <T> the type of elements in the list
     * @return the average of the double values produced by applying the given function to the elements of the list
     */
    public static <T> Double average(List<T> list, ToDoubleFunction<T> mapper) {
        return list.stream()
                .mapToDouble(mapper)
                .average()
                .orElse(0);
    }

    /**
     * Returns the count of elements in the list that satisfy the given predicate.
     *
     * @param list the list of elements
     * @param predicate a predicate to apply to each element to determine if it should be counted
     * @param <T> the type of elements in the list
     * @return the count of elements in the list that satisfy the given predicate
     */
    public static <T> long countIf(List<T> list, Predicate<T> predicate) {
        return list.stream()
                .filter(predicate)
                .count();
    }
}