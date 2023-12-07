package be.kdg.threading;

import be.kdg.model.Sailboat;

import java.util.List;
import java.util.function.Predicate;

public class SailboatAttacker implements Runnable {

    private final List<Sailboat> sailboats;
    private final Predicate<Sailboat> predicate;

    public SailboatAttacker(List<Sailboat> sailboats, Predicate<Sailboat> predicate) {
        this.sailboats = sailboats;
        this.predicate = predicate;
    }

    @Override
    public void run() {
        synchronized (sailboats) {
            sailboats.removeIf(predicate);
        }
    }
}
