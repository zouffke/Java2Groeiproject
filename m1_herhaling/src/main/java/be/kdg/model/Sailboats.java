package be.kdg.model;

import java.time.LocalDate;
import java.util.*;

/**
 * The Sailboats class represents a collection of Sailboat objects.
 * It provides methods to add, remove, search, and sort sailboats.
 * The sailboats are stored in a TreeSet, ensuring that there are no duplicates and they are automatically sorted.
 */
public class Sailboats {

    //region vars

    private final TreeSet<Sailboat> sailboats;

    //endregion

    //region constructors

    /**
     * Constructor for the Sailboats class.
     * Initializes a new Sailboats object with an empty TreeSet of sailboats.
     */
    public Sailboats() {
        this.sailboats = new TreeSet<>();
    }

    //endregion

    /**
     * Adds a sailboat to the collection.
     * @param sailboat The sailboat to add.
     * @return true if the sailboat was added, false otherwise (if the sailboat was already in the collection).
     */
    public boolean add(Sailboat sailboat) {
        return sailboats.add(sailboat);
    }

    /**
     * Removes a sailboat from the collection.
     * @param name The name of the sailboat to remove.
     * @param classification The classification of the sailboat to remove.
     * @param buildYear The build year of the sailboat to remove.
     * @return true if the sailboat was removed, false otherwise (if no such sailboat was found).
     */
    public boolean remove(String name, Classification classification, LocalDate buildYear) {
        for (Iterator<Sailboat> it = sailboats.iterator(); it.hasNext(); ) {
            if (it.next().equals(new Sailboat(name, classification, buildYear))) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    /**
     * Searches for a sailboat in the collection.
     * @param name The name of the sailboat to search for.
     * @param classification The classification of the sailboat to search for.
     * @param buildYear The build year of the sailboat to search for.
     * @return The sailboat if found, null otherwise.
     */
    public Sailboat search(String name, Classification classification, LocalDate buildYear) {
        for (Sailboat sailboat : sailboats) {
            if (sailboat.equals(new Sailboat(name, classification, buildYear))) {
                return sailboat;
            }
        }
        return null;
    }

    /**
     * Returns a list of sailboats sorted by name.
     * @return A List of Sailboat objects sorted by name.
     */
    public List<Sailboat> sortedOnName() {
        List<Sailboat> sorted = new ArrayList<>(sailboats);
        sorted.sort(((o1, o2) -> {
            return o1.getName().compareTo(o2.getName());
        }));
        return sorted;
    }

    /**
     * Returns a list of sailboats sorted by classification.
     * @return A List of Sailboat objects sorted by classification.
     */
    public List<Sailboat> sortedOnClassification(){
        List<Sailboat> sorted = new ArrayList<>(sailboats);
        sorted.sort(((o1, o2) -> {
            return o1.getClassification().compareTo(o2.getClassification());
        }));
        return sorted;
    }

    /**
     * Returns a list of sailboats sorted by build year.
     * @return A List of Sailboat objects sorted by build year.
     */
    public List<Sailboat> sortedOnBuildYear(){
        List<Sailboat> sorted = new ArrayList<>(sailboats);
        sorted.sort(((o1, o2) -> {
            return o1.getBuildYear().compareTo(o2.getBuildYear());
        }));
        return sorted;
    }

    /**
     * Returns the number of sailboats in the collection.
     * @return The number of sailboats in the collection.
     */
    public int getSize(){
        return sailboats.size();
    }

    /**
     * Returns a string representation of the collection of sailboats.
     * @return A string representation of the collection of sailboats.
     */
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (Sailboat sailboat : sailboats){
            string.append(sailboat.toString()).append("\n");
        }
        return string.toString();
    }
}