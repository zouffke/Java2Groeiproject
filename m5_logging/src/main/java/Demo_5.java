import be.kdg.model.Classification;
import be.kdg.model.Sailboat;
import be.kdg.model.Sailboats;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.logging.LogManager;

public class Demo_5 {
    public static void main(String[] args) {
        try {
            InputStream inputStream = Demo_5.class.getResourceAsStream("logging.properties");
            LogManager.getLogManager().readConfiguration(inputStream);
        } catch (IOException e){
            System.out.println(e);
        }

        //Sailboat sailboat = new Sailboat("", "", -2, -2, Classification.RACEBOAT, LocalDate.of(2024, 1, 1));

        Sailboats sailboats = new Sailboats();
        sailboats.add(new Sailboat("Teraju", "Argentina", -1, 75, Classification.Y, LocalDate.of(1995, 1, 1)));
    }
}
