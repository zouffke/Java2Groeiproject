package be.kdg;

import be.kdg.kollections.Kollections;
import be.kdg.kollections.lists.ArrayList;
import be.kdg.kollections.lists.LinkedList;
import be.kdg.kollections.lists.List;
import be.kdg.kollections.maps.HashMap;
import be.kdg.kollections.maps.ListMap;
import be.kdg.kollections.maps.Map;
import be.kdg.kollections.sets.ArraySet;
import be.kdg.kollections.sets.TreeSet;
import be.kdg.model.Classification;
import be.kdg.model.Sailboat;
import be.kdg.model.SailboatFactory;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
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
        System.out.println("Selection sort:");
        for (int n = 1000; n < 20000; n += 1000) {
            List<Sailboat> sailboats = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                sailboats.add(SailboatFactory.newRandomSailboat());
            }
            Kollections.selectionSort(sailboats);
            System.out.printf("%d;%d\n", n, Sailboat.compareCounter);
        }
    }

    public static void testMergeSort() {
        System.out.println("Merge sort:");
        for (int n = 1000; n < 20000; n += 1000) {
            List<Sailboat> sailboats = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                sailboats.add(SailboatFactory.newRandomSailboat());
            }
            Kollections.mergeSort(sailboats);
            System.out.printf("%d;%d\n", n, Sailboat.compareCounter);
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> Map<Sailboat, String> fillMap(Class<T> mapType, int size) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Map<Sailboat, String> map = (Map<Sailboat, String>) mapType.getDeclaredConstructor().newInstance();

        for (int i = 0; i < size; i++) {
            Sailboat sailboat = SailboatFactory.newFilledSailboat("boat" + i
                    , "test"
                    , 1,
                    1,
                    Classification.Y,
                    LocalDate.now());
            map.put(sailboat, sailboat.getName());
        }
        return map;
    }

    public static void compareListMapToHashMap(int size) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        ListMap<Sailboat, String> listMap = (ListMap<Sailboat, String>) fillMap(ListMap.class, size);
        HashMap<Sailboat, String> hashMap = (HashMap<Sailboat, String>) fillMap(HashMap.class, size);

        long startTime = System.nanoTime();
        Sailboat.equalsCounter = 0;
        for (int i = 0; i < size; i++) {
            listMap.get(SailboatFactory.newFilledSailboat("boat" + i
                    , "test"
                    , 1,
                    1,
                    Classification.Y,
                    LocalDate.now()));
        }
        long endTime = System.nanoTime();
        System.out.printf("Listmap: n = %d, equalsCount = %10d, nanosec = %10d\n", size, Sailboat.equalsCounter, endTime - startTime);

        startTime = System.nanoTime();
        Sailboat.equalsCounter = 0;
        for (int i = 0; i < size; i++) {
            hashMap.get(SailboatFactory.newFilledSailboat("boat" + i
                    , "test"
                    , 1,
                    1,
                    Classification.Y,
                    LocalDate.now()));
        }
        endTime = System.nanoTime();
        System.out.printf("Hashmap: n = %d, equalsCount = %10d, nanosec = %10d\n", size, Sailboat.equalsCounter, endTime - startTime);
    }

    public static void compareArraySetToTreeSet(int size) {
        ArraySet<Sailboat> arraySet = new ArraySet<>();
        TreeSet<Sailboat> treeSet = new TreeSet<>();

        long startTime = System.nanoTime();
        Sailboat.equalsCounter = 0;
        Sailboat.compareCounter = 0;
        for (int i = 0; i < size; i++) {
            arraySet.add(SailboatFactory.newRandomSailboat());
        }
        long endTime = System.nanoTime();

        System.out.printf("ArraySet, n = %d: equalscount: %d\n", size, Sailboat.equalsCounter);
        System.out.printf("ArraySet, n = %d: comparecount: %d\n", size, Sailboat.compareCounter);
        System.out.printf("ArraySet, n = %d: nanosec: %d\n", size, endTime - startTime);

        startTime = System.nanoTime();
        Sailboat.equalsCounter = 0;
        Sailboat.compareCounter = 0;
        for (int i = 0; i < size; i++) {
            treeSet.add(SailboatFactory.newRandomSailboat());
        }
        endTime = System.nanoTime();

        System.out.printf("TreeSet,  n = %d: equalscount: %d\n", size, Sailboat.equalsCounter);
        System.out.printf("TreeSet,  n = %d: comparecount: %d\n", size, Sailboat.compareCounter);
        System.out.printf("TreeSet,  n = %d: nanosec: %d\n", size, endTime - startTime);
    }
}
