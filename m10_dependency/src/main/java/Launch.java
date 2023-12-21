import be.kdg.sailboatproject.Main;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;

public class Launch {
    public static void main(String[] args) {
        try {
            InputStream inputStream = Launch.class.getResourceAsStream("logging.properties");
            LogManager.getLogManager().readConfiguration(inputStream);
        } catch (IOException e){
            System.out.println(e.getMessage());
        }

        Main.main(args);
    }
}
