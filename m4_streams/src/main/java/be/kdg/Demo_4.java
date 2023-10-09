package be.kdg;

import be.kdg.data.Data;
import be.kdg.model.Sailboat;
import be.kdg.model.Sailboats;
import be.kdg.util.SailboatFunctions;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;


public class Demo_4 {
    public static void main(String[] args) {
        Sailboats sailboats = new Sailboats();

        List<Sailboat> dataList = Data.getData();
        for (Sailboat sailboat : dataList) {
            sailboats.add(sailboat);
        }

        System.out.println("Sailboats sorted on name:");
        System.out.println(sailboats.sortedBy(Sailboat::getName));

        System.out.println("\nSailboats sorted on length");
        System.out.println(sailboats.sortedBy(Sailboat::getLength));

        System.out.println("\nSailboats sorted on depth:");
        System.out.println(sailboats.sortedBy(Sailboat::getDepth));

        System.out.println("\n\nSailboats filtered on length, > 30ft");
        System.out.println(SailboatFunctions.filteredList(dataList, s -> s.getLength() > 30));

        System.out.println("\nSailboats filtered on depth, > 2m");
        System.out.println(SailboatFunctions.filteredList(dataList, s -> s.getDepth() > 2));

        System.out.println("\nSailboats filtered on buildyear, > 2000");
        System.out.println(SailboatFunctions.filteredList(dataList, s -> s.getBuildYear().isAfter(LocalDate.of(2000, 1, 1))));

        System.out.printf("\n\nGemiddelde lengte: %.1fft\n", SailboatFunctions.average(dataList, Sailboat::getLength));
        System.out.printf("Gemiddelde diepte: %.1fm\n", SailboatFunctions.average(dataList, Sailboat::getDepth));

        System.out.printf("\n\nAantal boten met een lengte > 30ft: %d\n", SailboatFunctions.countIf(dataList, s -> s.getLength() > 30));
        System.out.printf("Aantal boten met een diepte groter dan 2m: %d\n", SailboatFunctions.countIf(dataList, s -> s.getDepth() > 2));

        System.out.printf("\n\nAantal boten gebouwd na 2000: %d\n", dataList.stream()
                .filter(s -> s.getBuildYear().isAfter(LocalDate.of(2000, 1, 1)))
                .count());

        System.out.printf("\n\n");
    }
}
