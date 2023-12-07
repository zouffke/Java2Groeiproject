package be.kdg;

import be.kdg.model.Classification;
import be.kdg.threading.SailboatRunnable;

public class Demo_9 {
    public static void main(String[] args) {
        SailboatRunnable sR1 = new SailboatRunnable(sailboat -> sailboat.getLength() > 30);
        SailboatRunnable sR2 = new SailboatRunnable(sailboat -> sailboat.getClassification().equals(Classification.CRUISER));
        SailboatRunnable sR3 = new SailboatRunnable(sailboat -> sailboat.getDepth() < 1);

        final int TESTCOUNT = 100;

        long totalTime = 0;

        for (int i = 0; i < TESTCOUNT; i++) {

            Thread thread1 = new Thread(sR1, "length");
            Thread thread2 = new Thread(sR2, "class");
            Thread thread3 = new Thread(sR3, "depth");

            long start = System.currentTimeMillis();
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

            long end = System.currentTimeMillis();

            totalTime += end - start;

            System.out.println(thread1.getName() + "\n==========================");
            sR1.getSailboats().stream().limit(5).forEach(System.out::println);
            System.out.println(thread2.getName() + "\n==========================");
            sR2.getSailboats().stream().limit(5).forEach(System.out::println);
            System.out.println(thread3.getName() + "\n==========================");
            sR3.getSailboats().stream().limit(5).forEach(System.out::println);
        }

        System.out.printf("3 threads verzamelen elk 1000 sailboats (gemiddelde uit 100 runs): %.1fms\n", (double)(totalTime/TESTCOUNT));
    }
}
