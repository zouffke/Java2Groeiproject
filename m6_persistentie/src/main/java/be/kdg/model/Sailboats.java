package be.kdg.model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

/**
 * This class represents a collection of Sailboat objects.
 * It provides methods to add, remove, search, and sort sailboats.
 * It also provides a method to get the size of the collection and a toString method.
 * This class implements Serializable interface for object serialization.
 */
public class Sailboats implements Serializable {

    //region vars

    /**
     * serialVersionUID is used for ensuring that same class (that was serialized) is loaded during deserialization.
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ArrayList to store the Sailboat objects.
     */
    private ArrayList<Sailboat> sailboats;

    //endregion

    //region constructors

    /**
     * Constructor that initializes the ArrayList.
     */
    public Sailboats() {
        this.sailboats = new ArrayList<>();
    }

    //endregion

    /**
     * Adds a Sailboat object to the ArrayList.
     *
     * @param sailboat the Sailboat object to be added
     * @return true if the Sailboat object was added successfully, false otherwise
     */
    public boolean add(Sailboat sailboat) {
        return sailboats.add(sailboat);
    }

    /**
     * Removes a sailboat from the ArrayList.
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
     * Searches for a sailboat in the ArrayList.
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
     * Sorts the sailboats in the ArrayList based on their names.
     *
     * @return a list of sorted Sailboat objects
     */
    public List<Sailboat> sortedOnName() {
        List<Sailboat> sorted = new ArrayList<>(sailboats);
        sorted.sort((Comparator.comparing(Sailboat::getName)));
        return sorted;
    }

    /**
     * Sorts the sailboats in the ArrayList based on their classifications.
     *
     * @return a list of sorted Sailboat objects
     */
    public List<Sailboat> sortedOnClassification(){
        List<Sailboat> sorted = new ArrayList<>(sailboats);
        sorted.sort((Comparator.comparing(Sailboat::getClassification)));
        return sorted;
    }

    /**
     * Sorts the sailboats in the ArrayList based on their build years.
     *
     * @return a list of sorted Sailboat objects
     */
    public List<Sailboat> sortedOnBuildYear(){
        List<Sailboat> sorted = new ArrayList<>(sailboats);
        sorted.sort((Comparator.comparing(Sailboat::getBuildYear)));
        return sorted;
    }

    /**
     * Returns the size of the ArrayList.
     *
     * @return the size of the ArrayList
     */
    public int getSize(){
        return sailboats.size();
    }

    /**
     * Returns a string representation of the sailboats in the ArrayList.
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

    /**
     * Checks if the current object is equal to the specified object.
     *
     * @param o the object to be compared
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sailboats sailboats1 = (Sailboats) o;

        List<Sailboat> sailboatList1 = this.sortedOnBuildYear();
        List<Sailboat> sailboatList2 = sailboats1.sortedOnBuildYear();
        boolean equals = true;

        for (int i = 0; i < sailboatList1.size(); i++){
            Sailboat sailboat = sailboatList1.get(i);
            Sailboat sailboat1 = sailboatList2.get(i);

            if (!sailboat.equals(sailboat1)) {
                equals = false;
                break;
            }
        }

        return equals;
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for the object
     */
    @Override
    public int hashCode() {
        return Objects.hash(sailboats);
    }
}