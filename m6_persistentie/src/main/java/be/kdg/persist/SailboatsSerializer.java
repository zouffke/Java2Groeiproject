package be.kdg.persist;

import be.kdg.model.Sailboats;

import java.io.*;

public class SailboatsSerializer {
    private final String FILENAME;

    public SailboatsSerializer() {
        this.FILENAME = "db/sailboats.ser";
    }

    public void serialize(Sailboats sailboats) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME));
        oos.writeObject(sailboats);
    }

    public Sailboats deserialize() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME));
        return (Sailboats) ois.readObject();
    }
}
