package be.kdg;

import be.kdg.model.Classification;
import be.kdg.model.Sailboat;
import be.kdg.model.SailboatFactory;
import be.kdg.model.Sailboats;
import be.kdg.threading.SailboatRunnable;

public class Demo_12 {
    public static void main(String[] args) {
        final int OBJECTS = 5000;
        Sailboats sailboats = new Sailboats(OBJECTS * 2);

        Runnable runnable = () -> {
            for (int i = 0; i < OBJECTS; i++){
                sailboats.add(SailboatFactory.newRandomSailboat());
            }
        };

        Thread thread1 = new Thread(runnable, "thread 1");
        Thread thread2 = new Thread(runnable, "thread 2");

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        System.out.printf("Na toevoegen door 2 threads met elk %d objecten: sailboats = %d", OBJECTS, sailboats.getSize());
    }
}
