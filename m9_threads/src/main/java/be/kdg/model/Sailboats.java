package be.kdg.model;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * This class represents a collection of Sailboat objects.
 * It provides methods to add, remove, search, and sort sailboats.
 * It also provides a method to get the size of the collection and a toString method.
 * The Sailboats are stored in an ArrayBlockingQueue, which is a bounded blocking queue backed by an array.
 */
public class Sailboats {
    /**
     * ArrayBlockingQueue to store the Sailboat objects.
     */
    private final ArrayBlockingQueue<Sailboat> sailboats;

    /**
     * Constructor that initializes the ArrayBlockingQueue with a given capacity.
     *
     * @param sizeCap the capacity of the ArrayBlockingQueue
     */
    public Sailboats(int sizeCap) {
        this.sailboats = new ArrayBlockingQueue<>(sizeCap);
    }

    /**
     * Adds a Sailboat object to the ArrayBlockingQueue.
     *
     * @param sailboat the Sailboat object to be added
     * @return true if the Sailboat object was added successfully, false otherwise
     */
    public boolean add(Sailboat sailboat) {
        return sailboats.add(sailboat);
    }

    /**
     * Removes a sailboat from the ArrayBlockingQueue.
     *
     * @param name           the name of the sailboat to be removed
     * @param classification the classification of the sailboat to be removed
     * @param buildYear      the build year of the sailboat to be removed
     * @return true if the sailboat was removed successfully, false otherwise
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
     * Searches for a sailboat in the ArrayBlockingQueue.
     *
     * @param name           the name of the sailboat to be searched
     * @param classification the classification of the sailboat to be searched
     * @param buildYear      the build year of the sailboat to be searched
     * @return the Sailboat object if found, null otherwise
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
     * Sorts the sailboats in the ArrayBlockingQueue based on their names.
     *
     * @return a list of sorted Sailboat objects
     */
    public List<Sailboat> sortedOnName() {
        List<Sailboat> sorted = new ArrayList<>(sailboats);
        sorted.sort((Comparator.comparing(Sailboat::getName)));
        return sorted;
    }

    /**
     * Sorts the sailboats in the ArrayBlockingQueue based on their classifications.
     *
     * @return a list of sorted Sailboat objects
     */
    public List<Sailboat> sortedOnClassification(){
        List<Sailboat> sorted = new ArrayList<>(sailboats);
        sorted.sort((Comparator.comparing(Sailboat::getClassification)));
        return sorted;
    }

    /**
     * Sorts the sailboats in the ArrayBlockingQueue based on their build years.
     *
     * @return a list of sorted Sailboat objects
     */
    public List<Sailboat> sortedOnBuildYear(){
        List<Sailboat> sorted = new ArrayList<>(sailboats);
        sorted.sort((Comparator.comparing(Sailboat::getBuildYear)));
        return sorted;
    }

    /**
     * Returns the size of the ArrayBlockingQueue.
     *
     * @return the size of the ArrayBlockingQueue
     */
    public int getSize(){
        return sailboats.size();
    }

    /**
     * Returns a string representation of the sailboats in the ArrayBlockingQueue.
     *
     * @return a string representation of the sailboats
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