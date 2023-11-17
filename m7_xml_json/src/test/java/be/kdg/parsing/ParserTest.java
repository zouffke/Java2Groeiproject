package be.kdg.parsing;

import be.kdg.data.Data;
import be.kdg.model.Sailboat;
import be.kdg.model.Sailboats;
import jakarta.xml.bind.JAXBException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerConfigurationException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    private static Sailboats sailboats;

    @BeforeEach
    void setUp() {
        sailboats = new Sailboats();
        for (Sailboat sailboat : Data.getData()) {
            sailboats.add(sailboat);
        }
    }

    @Test
    void testStaxDom() throws IOException, XMLStreamException, TransformerConfigurationException {
        SailboatStaxParser sailboatStaxParser = new SailboatStaxParser(sailboats, "datafiles/staxSailboats.xml");
        sailboatStaxParser.staxWriteXML();

        Sailboats newSailboats = SailboatsDomParser.domReadXML("datafiles/staxSailboats.xml");

        assertEquals(sailboats, newSailboats);
    }

    @Test
    void testJaxb() throws JAXBException {
        SailboatsJaxbParser.JaxbWriteXml("datafiles/jaxbSailboats.xml", sailboats);

        Sailboats sailboats1 = SailboatsJaxbParser.JaxbReadXml("datafiles/jaxbSailboats.xml", Sailboats.class);

        assertEquals(sailboats, sailboats1);
    }

    //@Test
    void testStaxVsJaxb() throws IOException, XMLStreamException, JAXBException, TransformerConfigurationException {
        SailboatStaxParser sailboatStaxParser = new SailboatStaxParser(sailboats, "datafiles/staxSailboats.xml");
        sailboatStaxParser.staxWriteXML();
        SailboatsJaxbParser.JaxbWriteXml("datafiles/jaxbSailboats.xml", sailboats);
        assertEquals(new String(Files.readAllBytes(Paths.get("datafiles/staxSailboats.xml"))),
                new String(Files.readAllBytes(Paths.get("datafiles/jaxbSailboats.xml"))));
    }

    @Test
    void testGson() throws IOException {
        SailboatsGsonParser.writeJson(sailboats, "datafiles/gsonSailboats.json");

        Sailboats sailboats1 = SailboatsGsonParser.readJson("datafiles/gsonSailboats.json");

        assertEquals(sailboats, sailboats1);
    }
}
