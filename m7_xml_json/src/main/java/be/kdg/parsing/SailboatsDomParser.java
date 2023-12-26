package be.kdg.parsing;

import be.kdg.model.Classification;
import be.kdg.model.Sailboat;
import be.kdg.model.Sailboats;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

/**
 * This class provides methods for parsing XML files into Sailboats objects using the DOM parser.
 */
public class SailboatsDomParser {

    /**
     * Parses an XML file into a Sailboats object.
     * It reads the XML file using a DocumentBuilder and parses it into a Document object.
     * It then gets the root element of the XML and retrieves a NodeList of 'sailboat' elements.
     * For each 'sailboat' element, it creates a Sailboat object and adds it to a Sailboats object.
     * The Sailboats object is then returned.
     *
     * @param fileName the name of the XML file to be parsed
     * @return a Sailboats object containing the Sailboat objects parsed from the XML file
     * @throws IOException if an I/O error occurs while reading the file
     * @throws XMLStreamException if an error occurs while parsing the XML
     * @throws ParserConfigurationException if a DocumentBuilder cannot be created
     * @throws SAXException if any parse errors occur
     */
    public static Sailboats domReadXML(String fileName) throws IOException, XMLStreamException, ParserConfigurationException, SAXException {
        Document doc = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder().parse(new File(fileName));
        Element rootElement = doc.getDocumentElement();

        Sailboats sailboats = new Sailboats();
        Sailboat sailboat = new Sailboat();

        NodeList sailboatNodes = rootElement.getChildNodes();

        for (int i = 0; i < sailboatNodes.getLength(); i++) {
            if (sailboatNodes.item(i).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            Element e = (Element) sailboatNodes.item(i);
            sailboat.setName(e.getAttribute("name"));
            sailboat.setHarbour(e.getElementsByTagName("harbour").item(0).getTextContent());
            sailboat.setDepth(Double.parseDouble(e.getElementsByTagName("depth").item(0).getTextContent()));
            sailboat.setLength(Integer.parseInt(e.getElementsByTagName("length").item(0).getTextContent()));
            sailboat.setClassification(Classification.valueOf(e.getElementsByTagName("classification").item(0).getTextContent()));
            sailboat.setBuildYear(LocalDate.parse(e.getElementsByTagName("build-year").item(0).getTextContent()));
            sailboats.add(sailboat);
        }

        return sailboats;
    }
}