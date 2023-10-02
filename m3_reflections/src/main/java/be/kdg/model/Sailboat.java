package be.kdg.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Class containing all information about sailboat.
 *
 * @author Luca Dandois
 * @version 1.0
 * @see <a href="https://en.wikipedia.org/wiki/Sailboat">Sailboat</a>
 */
public class Sailboat extends Boat implements Comparable<Sailboat> {
    //region vars
    private Classification classification;
    //endregion

    //region Constructors

    /**
     * Constructor for Sailboat
     *
     * @param name           Name of the boat
     * @param harbour        Harbour where the boat is located
     * @param depth          Depth of the boat in Meters
     * @param length         Length of the boat in Feet
     * @param classification Classification of the boat, Enum
     * @param buildYear      Build year of the boat
     * @see Classification
     */
    public Sailboat(String name, String harbour, double depth, int length, Classification classification, LocalDate buildYear) {
        super.setName(name);
        super.setHarbour(harbour);
        super.setDepth(depth);
        super.setLength(length);
        this.setClassification(classification);
        super.setBuildYear(buildYear);
    }

    /**
     * Constructor for Sailboat, overloaded
     *
     * @param name           Name of the boat
     * @param depth          Depth of the boat in Meters
     * @param length         Length of the boat in Feet
     * @param classification Classification of the boat, Enum
     * @param buildYear      Build year of the boat
     * @see Sailboat#Sailboat(String, String, double, int, Classification, LocalDate)
     * @see Classification
     */
    public Sailboat(String name, double depth, int length, Classification classification, LocalDate buildYear) {
        this(name, "Unknown", depth, length, classification, buildYear);
    }

    /**
     * Constructor for Sailboat, overloaded
     *
     * @param name           Name of the boat
     * @param classification Classification of the boat, Enum
     * @param buildYear      Build year of the boat
     * @see Sailboat#Sailboat(String, String, double, int, Classification, LocalDate)
     * @see Classification
     */
    public Sailboat(String name, Classification classification, LocalDate buildYear) {
        this(name, "Unknown", -1, -1, classification, buildYear);
    }

    /**
     * Default constructor for Sailboat
     *
     * @see Sailboat#Sailboat(String, String, double, int, Classification, LocalDate)
     */
    public Sailboat() {
        this("Unknown", "Unknown", -1, -1, null, LocalDate.EPOCH);
    }
    //endregion

    //region Setters

    /**
     * Sets the classification of the boat
     *
     * @param classification Classification of the boat, Enum
     * @see Classification
     */
    public void setClassification(Classification classification) {
        this.classification = classification;
    }
    //endregion

    //region Override methods

    /**
     * Checks if the boat object is equal to the o object
     *
     * @return true if object o is equal to this
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sailboat sailboat = (Sailboat) o;
        return super.equals(o) && classification == sailboat.classification;
    }

    /**
     * Hashcode for Sailboat
     *
     * @return Hashcode for Sailboat
     */
    @Override
    public int hashCode() {
        return Objects.hash(classification) + super.hashCode();
    }

    /**
     * Compares two Sailboats
     *
     * @param o Sailboat to compare to
     * @return 0 if equal, 1 if not equal
     */
    @Override
    public int compareTo(Sailboat o) {
        return this.getName().compareTo(o.getName()) + this.getBuildYear().compareTo(o.getBuildYear()) + this.classification.compareTo(o.classification);
    }

    /**
     * String representation of Sailboat
     *
     * @return String representation of Sailboat
     */
    @Override
    public String toString() {
        return String.format(
                """
                        %s
                        Classification: %-20s Build year: %s
                        """,
                super.toString(), classification, getBuildYear());
    }
    //endregion

    //region Getters
    /**
     * Gets the classification of the boat
     * @return Classification of the boat
     */
    public Classification getClassification() {
        return classification;
    }
    //endregion
}
