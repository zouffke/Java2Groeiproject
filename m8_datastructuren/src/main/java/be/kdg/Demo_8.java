package be.kdg;

import java.lang.reflect.InvocationTargetException;

public class Demo_8 {
    public static void main(String[] args) {
        /*
        System.out.println(SailboatFactory.newEmptySailboat());
        System.out.println(SailboatFactory.newFilledSailboat("Sailboat", "Antwerp", 2.5, 10, Classification.Y, LocalDate.of(2010, 1, 1)));
        System.out.println(SailboatFactory.newRandomSailboat());

        System.out.println("\n\n PART 2 \n\n");

        List<Sailboat> sbl = PerformanceTester.randomList(10);

        for (int i = 0; i < sbl.size(); i++) {
            System.out.println(sbl.get(i));
        }

        System.out.println("\n\n PART 3 \n\n");

        PerformanceTester.compareArrayListAndLinkedList(20000);

        System.out.println("\n\n PART 4 \n\n");

        List<Sailboat> test = new ArrayList<>();

        System.out.println("Selection sort:");
        System.out.println("Before:\n============================");
        for (int i = 0; i < 30; i++){
            test.add(SailboatFactory.newRandomSailboat());
            System.out.println(test.get(i));
        }

        Kollections.selectionSort(test);

        System.out.println("After:\n============================");
        for (int i = 0; i < test.size(); i++){
            System.out.println(test.get(i));
        }

        System.out.println("Merge sort:");
        System.out.println("Before:\n============================");
        for (int i = 0; i < 30; i++){
            test.add(SailboatFactory.newRandomSailboat());
            System.out.println(test.get(i));
        }

        Kollections.mergeSort(test);

        System.out.println("After:\n============================");
        for (int i = 0; i < test.size(); i++){
            System.out.println(test.get(i));
        }

        PerformanceTester.testSelectionSort();
        PerformanceTester.testMergeSort();

        System.out.println("quick sort:");
        for (int i = 0; i < 30; i++){
            test.add(SailboatFactory.newRandomSailboat());
            System.out.println(test.get(i));
        }

        Kollections.quickSort(test);
        for (int i = 0; i < test.size(); i++){
            System.out.println(test.get(i));
        }

        System.out.println(Kollections.binarySearch(test, test.get(4)));
        System.out.println(Kollections.lineairSearch(test, test.get(4)));
        System.out.println(Kollections.binarySearch(test, SailboatFactory.newRandomSailboat()));
        System.out.println(Kollections.lineairSearch(test, SailboatFactory.newRandomSailboat()));
        */

        try {
            PerformanceTester.compareListMapToHashMap(1000);
            System.out.println();
            PerformanceTester.compareArraySetToTreeSet(1000);
        } catch (InvocationTargetException | NoSuchMethodException | InstantiationException |
                 IllegalAccessException e) {
            System.out.println(e);
        }
    }
}
