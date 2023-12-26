package be.kdg.parsing;

import be.kdg.model.Sailboat;
import be.kdg.model.Sailboats;
import com.sun.xml.txw2.output.IndentingXMLStreamWriter;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.TransformerConfigurationException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * This class provides methods for parsing Sailboats objects into XML files using the StAX parser.
 * It includes a method to write a Sailboats object to an XML file.
 */
public class SailboatStaxParser {
    /**
     * The Sailboats object to be written to the XML file.
     */
    private final Sailboats sailboats;

    /**
     * The XMLStreamWriter used to write the XML file.
     */
    private XMLStreamWriter xmlStreamWriter;

    /**
     * Constructor that initializes the Sailboats object and the XMLStreamWriter.
     * It creates a new XMLStreamWriter with an IndentingXMLStreamWriter for pretty printing.
     *
     * @param sailboats the Sailboats object to be written to the XML file
     * @param path the path to the XML file
     * @throws IOException if an I/O error occurs while creating the FileWriter
     * @throws XMLStreamException if an error occurs while creating the XMLStreamWriter
     * @throws TransformerConfigurationException if an error occurs while creating the IndentingXMLStreamWriter
     */
    public SailboatStaxParser(Sailboats sailboats, String path) throws IOException, XMLStreamException {
        this.sailboats = sailboats;
        this.xmlStreamWriter = XMLOutputFactory.newInstance().createXMLStreamWriter(new FileWriter(path, StandardCharsets.UTF_8));
        this.xmlStreamWriter = new IndentingXMLStreamWriter(this.xmlStreamWriter);
    }

    /**
     * Writes the Sailboats object to an XML file.
     * It writes the start of the document, the opening tag of the XML, and the Sailboat elements.
     * It then writes the ending tag of the XML and the end of the document.
     *
     * @throws XMLStreamException if an error occurs while writing to the XMLStreamWriter
     */
    public void staxWriteXML() throws XMLStreamException {
        //Start the document
        xmlStreamWriter.writeStartDocument();
        //Write the opening tag of the XML
        xmlStreamWriter.writeStartElement("sailboats");                                                   //<Sailboats>
        for (Sailboat sailboat : sailboats.getSailboats()) {
            writeElement(sailboat);
        }
        //Write ending tag of XML
        xmlStreamWriter.writeEndElement();                                                                         //</Sailboats>

        xmlStreamWriter.writeEndDocument();

        xmlStreamWriter.close();
    }

    /**
     * Writes a Sailboat element to the XML file.
     * It writes the opening tag of the Sailboat element with the name attribute and the child elements.
     * It then writes the ending tag of the Sailboat element.
     *
     * @param sailboat the Sailboat object to be written to the XML file
     * @throws XMLStreamException if an error occurs while writing to the XMLStreamWriter
     */
    private void writeElement(Sailboat sailboat) throws XMLStreamException {
        //Write opening tag
        xmlStreamWriter.writeStartElement("sailboat"); //<Sailboat>
        //add data to opening tag
        xmlStreamWriter.writeAttribute("name", sailboat.getName());                                         //<Sailboat Name="x">

        writeSimpleElement(xmlStreamWriter, "harbour", sailboat.getHarbour());                                    //<Harbour>...</Harbour>
        writeSimpleElement(xmlStreamWriter, "depth", String.valueOf(sailboat.getDepth()));                        //<Depth>...</Depth>
        writeSimpleElement(xmlStreamWriter, "length", String.valueOf(sailboat.getLength()));                      //<Length>...</Length>
        writeSimpleElement(xmlStreamWriter, "classification", sailboat.getClassification().toString());           //<Classification>...</Classification>
        writeSimpleElement(xmlStreamWriter, "build-year", sailboat.getBuildYear().toString());                     //<Buildyear>...</Buildyear>

        xmlStreamWriter.writeEndElement();                                                                            //</Sailboat>
    }

    /**
     * Writes a simple element to the XML file.
     * It writes the opening tag of the element, the content, and the ending tag.
     *
     * @param xmlStreamWriter the XMLStreamWriter to write to
     * @param name the name of the element
     * @param content the content of the element
     * @throws XMLStreamException if an error occurs while writing to the XMLStreamWriter
     */
    private static void writeSimpleElement(XMLStreamWriter xmlStreamWriter, String name, String content) throws XMLStreamException {
        xmlStreamWriter.writeStartElement(name);
        xmlStreamWriter.writeCharacters(content);
        xmlStreamWriter.writeEndElement();
    }
}