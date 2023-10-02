package be.kdg.model;

import java.time.LocalDate;
import java.util.Objects;

public class Sailboat extends Boat implements Comparable<Sailboat> {
    private Classification classification;

    public Sailboat(String name, String harbour, double depth, int length, Classification classification, LocalDate buildYear) {
        super.setName(name);
        super.setHarbour(harbour);
        super.setDepth(depth);
        super.setLength(length);
        this.setClassification(classification);
        super.setBuildYear(buildYear);
    }

    public Sailboat(String name, double depth, int length, Classification classification, LocalDate buildYear) {
        this(name, "Unknown", depth, length, classification, buildYear);
    }

    public Sailboat(String name, Classification classification, LocalDate buildYear) {
        this(name, "Unknown", -1, -1, classification, buildYear);
    }

    public Sailboat() {
        this("Unknown", "Unknown", -1, -1, null, null);
    }



    public void setClassification(Classification classification) {
        this.classification = classification;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sailboat sailboat = (Sailboat) o;
        return super.equals(o) && classification == sailboat.classification;
    }

    @Override
    public int hashCode() {
        return Objects.hash(classification) + super.hashCode();
    }

    @Override
    public int compareTo(Sailboat o) {
        return this.getName().compareTo(o.getName()) + this.getBuildYear().compareTo(o.getBuildYear()) + this.classification.compareTo(o.classification);
    }

    @Override
    public String toString() {
        return String.format(
                        """
                        %s
                        Classification: %-20s Build year: %s
                        """,
                super.toString(), classification, getBuildYear());
    }



    public Classification getClassification() {
        return classification;
    }

}
