package be.kdg.model;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.logging.Logger;

/**
 * This class represents a collection of Sailboat objects.
 * It provides methods to add, remove, search, and sort sailboats.
 * It also provides a method to get the size of the collection and a toString method.
 */
public class Sailboats {
    /**
     * Logger instance used for logging messages.
     */
    private static final Logger logger = Logger.getLogger("be.kdg.model.Sailboats");

    /**
     * TreeSet to store the Sailboat objects.
     */
    private final TreeSet<Sailboat> sailboats;

    /**
     * Constructor that initializes the TreeSet.
     */
    public Sailboats() {
        this.sailboats = new TreeSet<>();
    }

    /**
     * Adds a Sailboat object to the TreeSet.
     *
     * @param sailboat the Sailboat object to be added
     * @return true if the Sailboat object was added successfully, false otherwise
     */
    public boolean add(Sailboat sailboat) {
        logger.finer(String.format("Sailboat%n%sAdded to the list", sailboat));
        return sailboats.add(sailboat);
    }

    /**
     * Removes a sailboat from the TreeSet.
     *
     * @param name           the name of the sailboat to be removed
     * @param classification the classification of the sailboat to be removed
     * @param buildYear      the build year of the sailboat to be removed
     * @return true if the sailboat was removed successfully, false otherwise
     */
    public boolean remove(String name, Classification classification, LocalDate buildYear) {
        Sailboat tester = new Sailboat(name, classification, buildYear);
        logger.finer(String.format("Sailboat%n%sremoved from the list", tester));
        return sailboats.removeIf(sailboat -> sailboat.equals(tester));
    }

    /**
     * Searches for a sailboat in the TreeSet.
     *
     * @param name           the name of the sailboat to be searched
     * @param classification the classification of the sailboat to be searched
     * @param buildYear      the build year of the sailboat to be searched
     * @return the Sailboat object if found, null otherwise
     */
    public Sailboat search(String name, Classification classification, LocalDate buildYear) {
        Sailboat tester = new Sailboat(name, classification, buildYear);
        return sailboats.stream().filter(sailboat -> sailboat.equals(tester)).findFirst().orElse(null);
    }

    /**
     * Sorts the sailboats in the TreeSet based on a given function.
     *
     * @param function the function to be used for sorting
     * @return a list of sorted Sailboat objects
     */
    public List<Sailboat> sortedBy(Function<Sailboat, Comparable> function) {
        List<Sailboat> sorted = new ArrayList<>(sailboats);
        Collections.sort(sorted, Comparator.comparing(function));
        return sorted;
    }

    /**
     * Returns the size of the TreeSet.
     *
     * @return the size of the TreeSet
     */
    public int getSize() {
        return sailboats.size();
    }

    /**
     * Returns a string representation of the sailboats in the TreeSet.
     *
     * @return a string representation of the sailboats
     */
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        sailboats.forEach(sailboat -> string.append(sailboat.toString()).append("\n"));
        return string.toString();
    }
}