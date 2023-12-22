package be.kdg.model;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;

public class Sailboats {

    //region vars
    private TreeSet<Sailboat> sailboats;

    //endregion

    //region

    public Sailboats() {
        this.sailboats = new TreeSet<>();
    }

    public boolean add(Sailboat sailboat) {
        return sailboats.add(sailboat);
    }

    /**
     * Removes a sailboat from the list
     *
     * @param name           Name of the sailboat
     * @param classification Classification of the sailboat
     * @param buildYear      Build year of the sailboat
     * @return true if the sailboat was removed, false if it was not found
     */
    public boolean remove(String name, Classification classification, LocalDate buildYear) {
        Sailboat tester = new Sailboat(name, classification, buildYear);
        return sailboats.removeIf(sailboat -> sailboat.equals(tester));
    }

    /**
     * Searches for a sailboat in the list
     *
     * @param name           Name of the sailboat
     * @param classification Classification of the sailboat
     * @param buildYear      Build year of the sailboat
     * @return Sailboat if found, null if not found
     */
    public Sailboat search(String name, Classification classification, LocalDate buildYear) {
        Sailboat tester = new Sailboat(name, classification, buildYear);
        return sailboats.stream().filter(sailboat -> sailboat.equals(tester)).findFirst().orElse(null);
    }

    /**
     * Sorts the sailboats on a given function
     *
     * @param function Function to sort on
     * @return Sorted list of sailboats
     */
    public List<Sailboat> sortedBy(Function<Sailboat, Comparable> function) {
        List<Sailboat> sorted = new ArrayList<>(sailboats);
        Collections.sort(sorted, Comparator.comparing(function));
        return sorted;
    }

    /**
     * Gets the size of the list
     *
     * @return Size of the list
     */
    public int getSize() {
        return sailboats.size();
    }

    /**
     * ToString of the sailboats
     *
     * @return String of the sailboats
     */
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        sailboats.forEach(sailboat -> string.append(sailboat.toString()).append("\n"));
        return string.toString();
    }
}
