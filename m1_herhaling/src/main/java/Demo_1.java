import be.kdg.data.Data;
import be.kdg.model.Classification;
import be.kdg.model.Sailboat;
import be.kdg.model.Sailboats;

import java.time.LocalDate;
import java.util.List;

public class Demo_1 {
    public static void main(String[] args) {
        Sailboats sailboats = new Sailboats();

        List<Sailboat> dataList = Data.getData();
        for(Sailboat sailboat : dataList){
            sailboats.add(sailboat);
        }

        System.out.println(sailboats);

        System.out.print(sailboats.add(dataList.get(4)));

        System.out.println("\nSearching for goodspeed:\n");
        System.out.println(sailboats.search("goodspeed", Classification.CRUISE_RACER, LocalDate.of(2004, 1, 1)));

        System.out.println("Removing goodspeed");
        System.out.println(sailboats.remove("goodspeed", Classification.CRUISE_RACER, LocalDate.of(2004, 1, 1)));
        System.out.println(sailboats);

        System.out.println(sailboats.getSize());

        System.out.println(sailboats.sortedOnName());

        System.out.println(sailboats.sortedOnBuildYear());

        System.out.println(sailboats.sortedOnClassification());

        try {
            Sailboat sailboat = new Sailboat("", 0, 0, null, LocalDate.of(2024, 1, 1));
        } catch (IllegalArgumentException e){
            System.out.printf("Illegal argument exception caught: %s", e.getMessage());
        }
    }
}
