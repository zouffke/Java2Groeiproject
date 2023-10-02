package be.kdg.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public class Sailboats {
    private TreeSet<Sailboat> sailboats;

    public Sailboats() {
        this.sailboats = new TreeSet<>();
    }

    public boolean add(Sailboat sailboat) {
        return sailboats.add(sailboat);
    }

    public boolean remove(String name, Classification classification, LocalDate buildYear) {
        for (Iterator<Sailboat> it = sailboats.iterator(); it.hasNext(); ) {
            if (it.next().equals(new Sailboat(name, classification, buildYear))) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    public Sailboat search(String name, Classification classification, LocalDate buildYear) {
        for (Sailboat sailboat : sailboats) {
            if (sailboat.equals(new Sailboat(name, classification, buildYear))) {
                return sailboat;
            }
        }
        return null;
    }

    public List<Sailboat> sortedOnName() {
        List<Sailboat> sorted = new ArrayList<>(sailboats);
        sorted.sort(((o1, o2) -> {
            return o1.getName().compareTo(o2.getName());
        }));
        return sorted;
    }

    public List<Sailboat> sortedOnClassification(){
        List<Sailboat> sorted = new ArrayList<>(sailboats);
        sorted.sort(((o1, o2) -> {
            return o1.getClassification().compareTo(o2.getClassification());
        }));
        return sorted;
    }

    public List<Sailboat> sortedOnBuildYear(){
        List<Sailboat> sorted = new ArrayList<>(sailboats);
        sorted.sort(((o1, o2) -> {
            return o1.getBuildYear().compareTo(o2.getBuildYear());
        }));
        return sorted;
    }

    public int getSize(){
        return sailboats.size();
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (Sailboat sailboat : sailboats){
            string.append(sailboat.toString()).append("\n");
        }
        return string.toString();
    }
}
