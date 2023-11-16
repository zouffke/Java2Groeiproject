package be.kdg.parsing;

import be.kdg.model.Classification;
import be.kdg.model.Sailboat;
import be.kdg.model.Sailboats;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

public class SailboatsDomParser {
    public static Sailboats domReadXML(String fileName) throws IOException, XMLStreamException {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader eventReader = factory.createXMLEventReader(new FileReader(fileName, StandardCharsets.UTF_8));

        Sailboats sailboats = new Sailboats();
        Sailboat sailboat = new Sailboat();

        while (eventReader.hasNext()) {
            XMLEvent event = eventReader.nextEvent();

            switch (event.getEventType()) {
                case XMLEvent.START_ELEMENT:
                    if (!event.asStartElement().getName().getLocalPart().equalsIgnoreCase("sailboats")) {
                        StartElement startElement = event.asStartElement();
                        String tagName = startElement.getName().getLocalPart();

                        String data;
                        switch (tagName.toLowerCase()) {
                            case "sailboat":
                                sailboat.setName(startElement.getAttributeByName(new QName("Name")).getValue());
                                break;
                            case "harbour":
                                data = getCharContent(eventReader);
                                if (data != null) sailboat.setHarbour(data);
                                break;
                            case "depth":
                                data = getCharContent(eventReader);
                                if (data != null) sailboat.setDepth(Double.parseDouble(data));
                                break;
                            case "length":
                                data = getCharContent(eventReader);
                                if (data != null) sailboat.setLength(Integer.parseInt(data));
                                break;
                            case "classification":
                                data = getCharContent(eventReader);
                                if (data != null) sailboat.setClassification(Classification.valueOf(data));
                                break;
                            case "buildyear":
                                data = getCharContent(eventReader);
                                if (data != null) sailboat.setBuildYear(LocalDate.parse(data));
                                break;
                        }
                    }
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    if (event.asEndElement().getName().getLocalPart().equalsIgnoreCase("sailboat")) {
                        sailboats.add(sailboat);
                        sailboat = new Sailboat();
                    }
                    break;
            }
        }
        return sailboats;
    }

    private static String getCharContent(XMLEventReader eventReader) throws XMLStreamException {
        if (!eventReader.hasNext()) return null;

        XMLEvent event = eventReader.nextEvent();

        if (event.getEventType() != XMLStreamConstants.CHARACTERS) return null;

        return event.asCharacters().getData();
    }
}
