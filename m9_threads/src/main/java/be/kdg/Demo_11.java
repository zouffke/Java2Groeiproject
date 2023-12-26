package be.kdg;

import be.kdg.model.Classification;
import be.kdg.model.Sailboat;
import be.kdg.model.Sailboats;
import be.kdg.threading.SailboatCallable;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Predicate;

public class Demo_11 {
    public static void main(String[] args) {
        Predicate<Sailboat> classification = s -> s.getClassification().equals(Classification.CRUISER);
        Predicate<Sailboat> depth = s -> s.getDepth() > 0.5;
        Predicate<Sailboat> length = s -> s.getLength() < 30;

        SailboatCallable callable1 = new SailboatCallable(classification);
        SailboatCallable callable2 = new SailboatCallable(depth);
        SailboatCallable callable3 = new SailboatCallable(length);

        final int TEST_COUNT = 100;
        long totalTime = 0;
        final int FUTURES = 3;

        Sailboats sailboats = new Sailboats(3000);

        ExecutorService pool = Executors.newFixedThreadPool(3);
        try {
            for (int i = 0; i < TEST_COUNT; i++) {

                long startTime = System.currentTimeMillis();
                Future<List<Sailboat>> future1 = pool.submit(callable1);
                Future<List<Sailboat>> future2 = pool.submit(callable2);
                Future<List<Sailboat>> future3 = pool.submit(callable3);

                for (Sailboat sailboat : future1.get()) {
                    sailboats.add(sailboat);
                }
                for (Sailboat sailboat : future2.get()) {
                    sailboats.add(sailboat);
                }
                for (Sailboat sailboat : future3.get()) {
                    sailboats.add(sailboat);
                }

                totalTime += System.currentTimeMillis() - startTime;
            }


        } catch (Exception ignore) {
        } finally {
            pool.shutdown();
        }

        double avgTime = (double) totalTime / TEST_COUNT;

        System.out.printf("%d Futures verzamelen elk %d sailboats (gemiddelde uit %d runs): %f ms", FUTURES, sailboats.getSize() / TEST_COUNT, TEST_COUNT, avgTime);
    }
}