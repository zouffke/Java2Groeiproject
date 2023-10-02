package be.kdg.model;

import be.kdg.reflection.CanRun;

import java.time.LocalDate;
import java.util.Objects;

public class Boat {
    //region vars
    private String name;
    private String harbour;
    private double depth; //m
    private int length; //ft
    private LocalDate buildYear;
    //endregion

    //region Constructors
    /**
     * Constructor for Boat
     * @param name Name of the boat
     * @param harbour Harbour where the boat is located
     * @param depth Depth of the boat in Meters
     * @param length Length of the boat in Feet
     * @param buildYear Build year of the boat
     */
    public Boat(String name, String harbour, double depth, int length, LocalDate buildYear) {
        this.name = name;
        this.harbour = harbour;
        this.depth = depth;
        this.length = length;
        this.buildYear = buildYear;
    }

    public Boat(){
        this("Unknown", "Unknown", -1, -1, LocalDate.EPOCH);
    }
    //endregion

    //region Setters
    /**
     * Sets the name of the boat
     *
     * @param name Name of the boat
     * @throws IllegalArgumentException if name is less than 1 character long
     */
    @CanRun()
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
    @CanRun("Antwerpen")
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
     * Sets the build year of the boat
     *
     * @param buildYear Build year of the boat
     * @throws IllegalArgumentException if build year is in the future
     */
    public void setBuildYear(LocalDate buildYear) {
        if (LocalDate.now().isAfter(buildYear)) {
            this.buildYear = buildYear;
        } else {
            throw new IllegalArgumentException("Build year can not be in the future");
        }
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
     * Gets the build year of the boat
     * @return Build year of the boat
     */
    public LocalDate getBuildYear() {
        return buildYear;
    }
    //endregion

    //region Overrides
    /**
     * Overrides the toString method
     * @return String representation of the boat
     */
    @Override
    public String toString() {
        return String.format("""
                        Name: %-30s Harbour: %s
                        Depth: %-29s Length: %dft
                         """, name, harbour, String.format("%.2fm", depth), length);
    }

    /**
     * Overrides the equals method
     * @param o Object to compare to
     * @return True if equal, false if not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Boat boat = (Boat) o;
        return Objects.equals(name, boat.name) && Objects.equals(buildYear, boat.buildYear);
    }

    /**
     * Overrides the hashCode method
     * @return Hash of the object
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, buildYear);
    }
    //endregion
}
