package be.kdg.parsing;

import be.kdg.data.Data;
import be.kdg.model.Sailboat;
import be.kdg.model.Sailboats;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerConfigurationException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParseTest {
    private static Sailboats sailboats;

    @BeforeEach
    void setUp() {
        sailboats = new Sailboats();
        for (Sailboat sailboat : Data.getData()) {
            sailboats.add(sailboat);
        }
    }

    @Test
    void testStaxDom() {
        try {
            SailboatStaxParser sailboatStaxParser = new SailboatStaxParser(sailboats, "datafiles/staxSailboats.xml");
            sailboatStaxParser.staxWriteXML();

            Sailboats newSailboats = SailboatsDomParser.domReadXML("datafiles/staxSailboats.xml");

            System.out.println(sailboats.equals(newSailboats));

            assertEquals(sailboats, newSailboats);
        } catch (IOException | TransformerConfigurationException | XMLStreamException e) {
            System.out.println(e);
        }
    }
}
