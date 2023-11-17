package be.kdg.parsing;

import be.kdg.model.Sailboat;
import be.kdg.model.Sailboats;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class SailboatsGsonParser {

    public static void writeJson(Sailboats sailboats, String fileName) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder
                .registerTypeAdapter(LocalDate.class,
                        new LocalDataGsonAdapter().nullSafe())
                .setPrettyPrinting().create();
        String json = gson.toJson(sailboats);
        try (FileWriter jsonWriter = new FileWriter(fileName)) {
            jsonWriter.write(json);
        }
    }

    public static Sailboats readJson(String fileName) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder
                .registerTypeAdapter(LocalDate.class,
                        new LocalDataGsonAdapter().nullSafe())
                .create();

        try (BufferedReader data = new BufferedReader(new FileReader(fileName))) {
            return gson.fromJson(data, Sailboats.class);
        }
    }
}
