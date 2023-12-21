package be.kdg.sailboatproject.model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Class containing all information about sailboat.
 *
 * @author Luca Dandois
 * @version 1.0
 * @see <a href="https://en.wikipedia.org/wiki/Sailboat">Sailboat</a>
 */
public class Sailboat implements Comparable<Sailboat>, Serializable {
    //region vars
    private int id;
    private String name;
    private String harbour;
    private transient double depth; //m
    private transient int length; //ft
    private transient Classification classification;
    private LocalDate buildYear;
    @Serial
    private static final long serialVersionUID = 1L;
    //endregion

    //region Constructors

    public Sailboat(int id, String name, String harbour, double depth, int length, Classification classification, LocalDate buildYear) {
        setName(name);
        setHarbour(harbour);
        setDepth(depth);
        setLength(length);
        setClassification(classification);
        setBuildYear(buildYear);
        setId(id);
    }

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
        this(-1, name, harbour, depth, length, classification, buildYear);
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
        this("Unknown", "Unknown", -1, -1, null, null);
    }
    //endregion

    //region Setters

    /**
     * Sets the name of the boat
     *
     * @param name Name of the boat
     * @throws IllegalArgumentException if name is less than 1 character long
     */
    public void setName(String name) {
        if (!name.isBlank()) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Name can not be les than 1 character long");
        }
    }

    /**
     * Sets the harbour where the boat is located
     *
     * @param harbour Harbour where the boat is located
     * @throws IllegalArgumentException if harbour is less than 1 character long
     */
    public void setHarbour(String harbour) {
        if (!harbour.isBlank()) {
            this.harbour = harbour;
        } else {
            throw new IllegalArgumentException("Harbour can not be les than 1 character long");
        }
    }

    /**
     * Sets the depth of the boat in Meters
     *
     * @param depth Depth of the boat in Meters
     * @throws IllegalArgumentException if depth is less than 0
     */
    public void setDepth(double depth) {
        if (depth >= 0 || depth == -1) {
            this.depth = depth;
        } else {
            throw new IllegalArgumentException("Depth can not be less than 0");
        }
    }

    /**
     * Sets the length of the boat in Feet
     *
     * @param length Length of the boat in Feet
     * @throws IllegalArgumentException if length is less than 0
     */
    public void setLength(int length) {
        if (length > 0 || length == -1) {
            this.length = length;
        } else {
            throw new IllegalArgumentException("Length can not be less than 0");
        }
    }

    /**
     * Sets the classification of the boat
     *
     * @param classification Classification of the boat, Enum
     * @see Classification
     */
    public void setClassification(Classification classification) {
        this.classification = classification;
    }

    /**
     * Sets the build year of the boat
     *
     * @param buildYear Build year of the boat
     * @throws IllegalArgumentException if build year is in the future
     */
    public void setBuildYear(LocalDate buildYear) {
        if (LocalDate.now().isAfter(buildYear) || LocalDate.now().equals(buildYear)) {
            this.buildYear = buildYear;
        } else {
            throw new IllegalArgumentException("Build year can not be in the future");
        }
    }
    //endregion

    //region Override methods

    /**
     * Overrides the equals method
     *
     * @param o Object to compare to
     * @return True if equal, false if not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sailboat sailboat = (Sailboat) o;
        return Objects.equals(name, sailboat.name) && classification == sailboat.classification && Objects.equals(buildYear, sailboat.buildYear);
    }

    /**
     * Overrides the hashCode method
     *
     * @return Hash of the object
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, classification, buildYear);
    }

    /**
     * Compares two Sailboats
     *
     * @param o Sailboat to compare to
     * @return 0 if equal, 1 if not equal
     */
    @Override
    public int compareTo(Sailboat o) {
        return this.name.compareTo(o.name)
                + this.classification.compareTo(o.classification)
                + this.buildYear.compareTo(o.buildYear);
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
                        Name: %-30s Harbour: %s
                        Depth: %-29s Length: %dft
                        Classification: %-20s Build year: %s
                        """,
                name, harbour, String.format("%.2fm", depth), length, classification, buildYear);
    }
    //endregion

    public void setId(int id){
        this.id = id;
    }

    //region Getters

    /**
     * Gets the name of the boat
     *
     * @return Name of the boat
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the harbour where the boat is located
     *
     * @return Harbour where the boat is located
     */
    public String getHarbour() {
        return harbour;
    }

    /**
     * Gets the depth of the boat in Meters
     *
     * @return Depth of the boat in Meters
     */
    public double getDepth() {
        return depth;
    }

    /**
     * Gets the length of the boat in Feet
     *
     * @return Length of the boat in Feet
     */
    public int getLength() {
        return length;
    }

    /**
     * Gets the classification of the boat
     *
     * @return Classification of the boat
     */
    public Classification getClassification() {
        return classification;
    }

    /**
     * Gets the build year of the boat
     *
     * @return Build year of the boat
     */
    public LocalDate getBuildYear() {
        return buildYear;
    }

    public int getId(){return this.id;}
    //endregion
}
