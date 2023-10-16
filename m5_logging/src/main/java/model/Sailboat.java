package model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * Class containing all information about sailboat.
 *
 * @author Luca Dandois
 * @version 1.0
 * @see <a href="https://en.wikipedia.org/wiki/Sailboat">Sailboat</a>
 */
public class Sailboat implements Comparable<Sailboat> {
    //region vars
    private static final Logger logger = Logger.getLogger("be.kdg.model.Sailboat");
    private String name;
    private String harbour;
    private double depth; //m
    private int length; //ft
    private Classification classification;
    private LocalDate buildYear;
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
        setName(name);
        setHarbour(harbour);
        setDepth(depth);
        setLength(length);
        setClassification(classification);
        setBuildYear(buildYear);
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
     */
    public void setName(String name) {
        if (!name.isBlank()) {
            this.name = name;
        } else {
            logger.severe(String.format("Name, %s can not be less than 1 character long", name));
        }
    }

    /**
     * Sets the harbour where the boat is located
     *
     * @param harbour Harbour where the boat is located
     */
    public void setHarbour(String harbour) {
        if (!harbour.isBlank()) {
            this.harbour = harbour;
        } else {
            logger.severe(String.format("Harbour, %s can not be less than 1 character long", harbour));
        }
    }

    /**
     * Sets the depth of the boat in Meters
     *
     * @param depth Depth of the boat in Meters
     */
    public void setDepth(double depth) {
        if (depth >= 0 || depth == -1) {
            this.depth = depth;
        } else {
            logger.severe(String.format("Depth, %f can not be less than 0", depth));
        }
    }

    /**
     * Sets the length of the boat in Feet
     *
     * @param length Length of the boat in Feet
     */
    public void setLength(int length) {
        if (length > 0 || length == -1) {
            this.length = length;
        } else {
            logger.severe(String.format("Length, %d can not be less than 0", length));
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
     */
    public void setBuildYear(LocalDate buildYear) {
        if (LocalDate.now().isAfter(buildYear) || LocalDate.now().equals(buildYear)) {
            this.buildYear = buildYear;
        } else {
            logger.severe(String.format("Build year, %s can not be in the future", buildYear));
        }
    }
    //endregion

    //region Override methods
    /**
     * Overrides the equals method
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

    //region Getters

    /**
     * Gets the name of the boat
     * @return Name of the boat
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the harbour where the boat is located
     * @return Harbour where the boat is located
     */
    public String getHarbour() {
        return harbour;
    }

    /**
     * Gets the depth of the boat in Meters
     * @return Depth of the boat in Meters
     */
    public double getDepth() {
        return depth;
    }

    /**
     * Gets the length of the boat in Feet
     * @return Length of the boat in Feet
     */
    public int getLength() {
        return length;
    }

    /**
     * Gets the classification of the boat
     * @return Classification of the boat
     */
    public Classification getClassification() {
        return classification;
    }

    /**
     * Gets the build year of the boat
     * @return Build year of the boat
     */
    public LocalDate getBuildYear() {
        return buildYear;
    }
    //endregion
}
