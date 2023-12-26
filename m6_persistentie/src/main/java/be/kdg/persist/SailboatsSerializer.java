package be.kdg.persist;

import be.kdg.model.Sailboats;

import java.io.*;

/**
 * This class provides methods for serializing and deserializing Sailboats objects.
 * It includes a method to serialize a Sailboats object to a file and a method to deserialize a Sailboats object from a file.
 */
public class SailboatsSerializer {
    /**
     * The name of the file where the Sailboats object will be serialized to and deserialized from.
     */
    private final String FILENAME;

    /**
     * Constructor that initializes the filename.
     */
    public SailboatsSerializer() {
        this.FILENAME = "db/sailboats.ser";
    }

    /**
     * Serializes a Sailboats object to a file.
     *
     * @param sailboats the Sailboats object to be serialized
     * @throws IOException if an I/O error occurs while writing to the file
     */
    public void serialize(Sailboats sailboats) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME));
        oos.writeObject(sailboats);
    }

    /**
     * Deserializes a Sailboats object from a file.
     *
     * @return the deserialized Sailboats object
     * @throws IOException if an I/O error occurs while reading from the file
     * @throws ClassNotFoundException if the class of a serialized object cannot be found
     */
    public Sailboats deserialize() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME));
        return (Sailboats) ois.readObject();
    }
}