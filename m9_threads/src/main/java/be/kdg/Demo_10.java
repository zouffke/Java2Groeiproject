package be.kdg;

import be.kdg.model.Classification;
import be.kdg.model.Sailboat;
import be.kdg.model.SailboatFactory;
import be.kdg.threading.SailboatAttacker;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Demo_10 {
    public static void main(String[] args) {
        List<Sailboat> sailboats = Stream.generate(SailboatFactory::newRandomSailboat).limit(1000).collect(Collectors.toList());

        Predicate<Sailboat> classification = s -> s.getClassification().equals(Classification.CRUISER);
        Predicate<Sailboat> depth = s -> s.getDepth() > 0.5;
        Predicate<Sailboat> length = s -> s.getLength() < 30;

        SailboatAttacker attacker1 = new SailboatAttacker(sailboats, classification);
        SailboatAttacker attacker2 = new SailboatAttacker(sailboats, depth);
        SailboatAttacker attacker3 = new SailboatAttacker(sailboats, length);

        Thread thread1 = new Thread(attacker1, "classification");
        Thread thread2 = new Thread(attacker2, "depth");
        Thread thread3 = new Thread(attacker3, "length");

        System.out.println("Voor zuivering:");
        System.out.printf("Aantal boten met classificatie CRUISER: %d\n", sailboats.stream().filter(classification).count());
        System.out.printf("Aantal boten met depth groter dan 0.5: %d\n", sailboats.stream().filter(depth).count());
        System.out.printf("Aantal boten met length kleiner dan 30: %d\n", sailboats.stream().filter(length).count());

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        System.out.println("\nNa zuivering:");
        System.out.printf("Aantal boten met classificatie CRUISER: %d\n", sailboats.stream().filter(classification).count());
        System.out.printf("Aantal boten met depth groter dan 0.5: %d\n", sailboats.stream().filter(depth).count());
        System.out.printf("Aantal boten met length kleiner dan 30: %d\n", sailboats.stream().filter(length).count());
    }
}
