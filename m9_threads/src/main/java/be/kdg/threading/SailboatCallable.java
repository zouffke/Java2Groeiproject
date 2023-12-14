package be.kdg.threading;

import be.kdg.model.Sailboat;
import be.kdg.model.SailboatFactory;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class SailboatCallable implements Callable<List<Sailboat>> {

    private final Predicate<Sailboat> predicate;

    public SailboatCallable(Predicate<Sailboat> predicate){
        this.predicate = predicate;
    }

    @Override
    public List<Sailboat> call() throws Exception {
        return Stream.generate(SailboatFactory::newRandomSailboat)
                .filter(predicate)
                .limit(1000)
                .toList();
    }
}
