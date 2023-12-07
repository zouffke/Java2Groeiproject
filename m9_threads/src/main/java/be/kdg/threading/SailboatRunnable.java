package be.kdg.threading;

import be.kdg.model.Sailboat;
import be.kdg.model.SailboatFactory;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SailboatRunnable implements Runnable {

    private List<Sailboat> sailboats;
    private final Predicate<Sailboat> predicate;

    public SailboatRunnable(Predicate<Sailboat> predicate) {
        this.predicate = predicate;
    }

    @Override
    public void run() {
        sailboats = Stream.generate(SailboatFactory::newRandomSailboat)
                .filter(predicate)
                .limit(1000)
                .collect(Collectors.toList());
    }

    public List<Sailboat> getSailboats() {
        return sailboats;
    }
}
