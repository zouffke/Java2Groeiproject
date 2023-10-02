package be.kdg.model;

import java.time.LocalDate;
import java.util.Objects;

public class Boat {
    private String name;
    private String harbour;
    private double depth; //m
    private int length; //ft
    private LocalDate buildYear;

    public void setName(String name) {
        if (!name.isBlank()) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Name can not be les than 1 character long");
        }
    }

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

    public void setBuildYear(LocalDate buildYear) {
        if (LocalDate.now().isAfter(buildYear)) {
            this.buildYear = buildYear;
        } else {
            throw new IllegalArgumentException("Build year can not be in the future");
        }
    }

    public String getName() {
        return name;
    }

    public String getHarbour() {
        return harbour;
    }

    public double getDepth() {
        return depth;
    }

    public int getLength() {
        return length;
    }

    public LocalDate getBuildYear() {
        return buildYear;
    }

    @Override
    public String toString() {
        return String.format("""
                        Name: %-30s Harbour: %s
                        Depth: %-29s Length: %dft
                         """, name, harbour, String.format("%.2fm", depth), length);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Boat boat = (Boat) o;
        return Objects.equals(name, boat.name) && Objects.equals(buildYear, boat.buildYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, buildYear);
    }
}
