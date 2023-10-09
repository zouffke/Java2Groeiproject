package be.kdg;

import be.kdg.data.Data;
import be.kdg.model.Classification;
import be.kdg.model.Sailboat;
import be.kdg.model.Sailboats;
import be.kdg.util.SailboatFunctions;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Demo_4 {
    public static void main(String[] args) {
        Sailboats sailboats = new Sailboats();

        List<Sailboat> dataList = Data.getData();
        dataList.forEach(sailboats::add);

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

        System.out.println("\nboten gesorteerd op yachthaven en naam: ");
        dataList.stream()
                .sorted(Comparator.comparing(Sailboat::getHarbour).thenComparing(Sailboat::getName))
                .forEach(System.out::println);

        System.out.println("\nNamen van alle boten in hoofdletters, omgekeerd gesorteerd en zonder dubbels:");
        System.out.println(dataList.stream()
                .map(Sailboat::getName)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .map(String::toUpperCase)
                .collect(Collectors.joining(", ")));

        System.out.println("\nEen willekeurige boot met een lengte van meer dan 30ft:");
        System.out.println(dataList.stream()
                .filter(s -> s.getLength() > 30)
                .findAny());

        System.out.printf("\n\nDe langste boot: %s\n", dataList.stream()
                .max(Comparator.comparing(Sailboat::getLength)));
        System.out.printf("De oudste boot: %s\n", dataList.stream()
                .min(Comparator.comparing(Sailboat::getBuildYear)));

        System.out.println("\nList met gesorteerde bootnamen die beginnen met een S");
        System.out.println(dataList.stream()
                .map(Sailboat::getName)
                .filter(s -> s.toLowerCase().startsWith("s"))
                .collect(Collectors.joining(", ")));

        System.out.println("\nSublist met zeilboten van voor 2000:");
        dataList.stream()
                .filter(s -> s.getBuildYear().isBefore(LocalDate.of(2000, 1, 1)))
                .forEach(System.out::println);
        System.out.println("\nSublist met zeilboten van na 2000:");
        dataList.stream()
                .filter(s -> s.getBuildYear().isAfter(LocalDate.of(2000, 1, 1)))
                .forEach(System.out::println);

        Map<Classification, List<Sailboat>> myList = dataList.stream()
                .collect(Collectors.groupingBy(Sailboat::getClassification));

        System.out.println("\nMap met alle boten per classificatie:");
        myList.keySet()
                .forEach(c -> System.out.printf("%s: %s\n", c, myList.get(c).stream()
                        .map(Sailboat::getName)
                        .collect(Collectors.joining(", "))));
    }
}
