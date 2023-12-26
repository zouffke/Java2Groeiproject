package be.kdg.parsing;

import be.kdg.model.Sailboats;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

/**
 * This class provides methods for parsing JSON files into Sailboats objects using the Gson library.
 * It includes a method to write a Sailboats object to a JSON file and a method to read a Sailboats object from a JSON file.
 */
public class SailboatsGsonParser {

    /**
     * Writes a Sailboats object to a JSON file.
     * It creates a Gson object with a custom serializer for LocalDate objects and pretty printing enabled.
     * It then converts the Sailboats object to a JSON string and writes it to the file.
     *
     * @param sailboats the Sailboats object to be written to the file
     * @param fileName the name of the file to write to
     * @throws IOException if an I/O error occurs while writing to the file
     */
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

    /**
     * Reads a Sailboats object from a JSON file.
     * It creates a Gson object with a custom deserializer for LocalDate objects.
     * It then reads the JSON string from the file and converts it to a Sailboats object.
     *
     * @param fileName the name of the file to read from
     * @return the Sailboats object read from the file
     * @throws IOException if an I/O error occurs while reading from the file
     */
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