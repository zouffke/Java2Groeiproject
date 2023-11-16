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

public class SailboatStaxParser {
    private final Sailboats sailboats;
    private XMLStreamWriter xmlStreamWriter;

    public SailboatStaxParser(Sailboats sailboats, String path) throws IOException, XMLStreamException, TransformerConfigurationException {
        this.sailboats = sailboats;
        this.xmlStreamWriter = XMLOutputFactory.newInstance().createXMLStreamWriter(new FileWriter(path, StandardCharsets.UTF_8));
        this.xmlStreamWriter = new IndentingXMLStreamWriter(this.xmlStreamWriter);
    }

    public void staxWriteXML() throws XMLStreamException {
        //Start the document
        xmlStreamWriter.writeStartDocument();
        //Write the opening tag of the XML
        xmlStreamWriter.writeStartElement("Sailboats");                                                   //<Sailboats>
        for (Sailboat sailboat : sailboats.sortedOnBuildYear()) {
            writeElement(sailboat);
        }
        //Write ending tag of XML
        xmlStreamWriter.writeEndElement();                                                                         //</Sailboats>

        xmlStreamWriter.close();

    }

    private void writeElement(Sailboat sailboat) throws XMLStreamException {
        //Write opening tag
        xmlStreamWriter.writeStartElement("Sailboat"); //<Sailboat>
        //add data to opening tag
        xmlStreamWriter.writeAttribute("Name", sailboat.getName());                                         //<Sailboat Name="x">

        writeSimpleElement(xmlStreamWriter, "Harbour", sailboat.getHarbour());                                    //<Harbour>...</Harbour>
        writeSimpleElement(xmlStreamWriter, "Depth", String.valueOf(sailboat.getDepth()));                        //<Depth>...</Depth>
        writeSimpleElement(xmlStreamWriter, "Length", String.valueOf(sailboat.getLength()));                      //<Length>...</Length>
        writeSimpleElement(xmlStreamWriter, "Classification", sailboat.getClassification().toString());           //<Classification>...</Classification>
        writeSimpleElement(xmlStreamWriter, "Buildyear", sailboat.getBuildYear().toString());                     //<Buildyear>...</Buildyear>

        xmlStreamWriter.writeEndElement();                                                                            //</Sailboat>
    }

    private static void writeSimpleElement(XMLStreamWriter xmlStreamWriter, String name, String content) throws XMLStreamException {
        xmlStreamWriter.writeStartElement(name);
        xmlStreamWriter.writeCharacters(content);
        xmlStreamWriter.writeEndElement();
    }
}
