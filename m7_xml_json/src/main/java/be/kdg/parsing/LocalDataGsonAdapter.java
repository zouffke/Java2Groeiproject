package be.kdg.parsing;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDate;

/**
 * This class extends the TypeAdapter class and provides custom serialization and deserialization for LocalDate objects.
 * It overrides the write and read methods from the TypeAdapter class.
 */
public class LocalDataGsonAdapter extends TypeAdapter<LocalDate> {

    /**
     * This method serializes a LocalDate object to JSON.
     * It writes the LocalDate object to the JsonWriter as a string.
     *
     * @param out the JsonWriter to write the LocalDate object to
     * @param value the LocalDate object to be serialized
     * @throws IOException if an I/O error occurs while writing to the JsonWriter
     */
    @Override
    public void write(final JsonWriter out, final LocalDate value) throws IOException {
        out.value(value.toString());
    }

    /**
     * This method deserializes a LocalDate object from JSON.
     * It reads a string from the JsonReader and parses it to a LocalDate object.
     *
     * @param in the JsonReader to read the LocalDate object from
     * @return the deserialized LocalDate object
     * @throws IOException if an I/O error occurs while reading from the JsonReader
     */
    @Override
    public LocalDate read(final JsonReader in) throws IOException {
        return LocalDate.parse(in.nextString());
    }
}