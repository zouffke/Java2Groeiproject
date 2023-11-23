package be.kdg;

import be.kdg.kollections.ArrayList;
import be.kdg.kollections.List;
import be.kdg.model.Classification;
import be.kdg.model.Sailboat;
import be.kdg.model.SailboatFactory;

import java.time.LocalDate;

public class Demo_8 {
    public static void main(String[] args) {
        System.out.println(SailboatFactory.newEmptySailboat());
        System.out.println(SailboatFactory.newFilledSailboat("Sailboat", "Antwerp", 2.5, 10, Classification.Y, LocalDate.of(2010, 1, 1)));
        System.out.println(SailboatFactory.newRandomSailboat());

        System.out.println("\n\n PART 2 \n\n");

        List<Sailboat> sbl = PerformanceTester.randomList(10);

        for (int i = 0; i < sbl.size(); i++){
            System.out.println(sbl.get(i));
        }

        System.out.println("\n\n PART 3 \n\n");

        PerformanceTester.compareArrayListAndLinkedList(20000);
    }
}
