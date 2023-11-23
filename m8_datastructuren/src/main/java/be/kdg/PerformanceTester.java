package be.kdg;

import be.kdg.kollections.ArrayList;
import be.kdg.kollections.LinkedList;
import be.kdg.kollections.List;
import be.kdg.model.Sailboat;
import be.kdg.model.SailboatFactory;

import java.util.Random;

public class PerformanceTester {

    public static List<Sailboat> randomList(int n) {
        List<Sailboat> myList = new LinkedList<>();
        new Random().ints(n).forEach(i -> myList.add(SailboatFactory.newRandomSailboat()));
        return myList;
    }

    public static void compareArrayListAndLinkedList(int n) {
        List<Sailboat> arrList = new ArrayList<>();
        List<Sailboat> lnkList = new LinkedList<>();

        long t1 = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            arrList.add(0, SailboatFactory.newRandomSailboat());
        }
        long t2 = System.currentTimeMillis();

        System.out.printf("Adding %d to ArrayList: %d ms.\n", n, t2 - t1);

        t1 = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            lnkList.add(0, SailboatFactory.newRandomSailboat());
        }
        t2 = System.currentTimeMillis();

        System.out.printf("Adding %d to LinkedList: %d ms.\n", n, t2 - t1);

        //get at end
        t1 = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            arrList.get(arrList.size() - 1);
        }
        t2 = System.currentTimeMillis();
        System.out.printf("Getting %d from ArrayList: %d ms.\n", n, t2 - t1);

        t1 = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            lnkList.get(lnkList.size() - 1);
        }
        t2 = System.currentTimeMillis();

        System.out.printf("Getting %d from LinkedList: %d ms.\n", n, t2 - t1);
    }

    public static void testSelectionSort() {
        //TODO: test selectionsort for (int n = 1000; n < 20000; n += 1000)
    }

    public static void testMergeSort() {
        //TODO: test mergesort for (int n = 1000; n < 200000; n += 1000)
    }
}
