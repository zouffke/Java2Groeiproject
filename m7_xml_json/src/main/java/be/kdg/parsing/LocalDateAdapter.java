package be.kdg.parsing;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;

/**
 * This class extends the XmlAdapter class and provides custom serialization and deserialization for LocalDate objects.
 * It overrides the unmarshal and marshal methods from the XmlAdapter class.
 */
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
    /**
     * This method deserializes a LocalDate object from a string.
     * It parses the string to a LocalDate object.
     *
     * @param s the string to be deserialized
     * @return the deserialized LocalDate object
     * @throws Exception if an error occurs while parsing the string
     */
    @Override
    public LocalDate unmarshal (String s) throws Exception {
        return LocalDate.parse(s);
    }

    /**
     * This method serializes a LocalDate object to a string.
     * It converts the LocalDate object to a string.
     *
     * @param date the LocalDate object to be serialized
     * @return the serialized LocalDate object as a string
     * @throws Exception if an error occurs while converting the LocalDate object to a string
     */
    @Override
    public String marshal (LocalDate date) throws Exception{
        return date.toString();
    }
}