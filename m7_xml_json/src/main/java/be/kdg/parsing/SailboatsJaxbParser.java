package be.kdg.parsing;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;

/**
 * This class provides methods for parsing XML files into Java objects using the JAXB library.
 * It includes a method to write a Java object to an XML file and a method to read a Java object from an XML file.
 */
public class SailboatsJaxbParser {
    /**
     * Writes a Java object to an XML file.
     * It creates a JAXBContext and a Marshaller for the class of the object.
     * It then marshals the object to the file.
     *
     * @param file the name of the file to write to
     * @param root the object to be written to the file
     * @throws JAXBException if an error occurs while marshalling the object
     */
    public static void JaxbWriteXml(String file, Object root) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(root.getClass());
        Marshaller m = jc.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        m.marshal(root, new File(file));
    }

    /**
     * Reads a Java object from an XML file.
     * It creates a JAXBContext and an Unmarshaller for the specified class.
     * It then unmarshals the object from the file.
     *
     * @param file the name of the file to read from
     * @param typeParameterClass the class of the object to be read from the file
     * @return the object read from the file
     * @throws JAXBException if an error occurs while unmarshalling the object
     */
    @SuppressWarnings("unchecked")
    public static <T> T JaxbReadXml(String file, Class<T> typeParameterClass) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(typeParameterClass);
        Unmarshaller u = jc.createUnmarshaller();

        File f = new File(file);
        return (T) u.unmarshal(f);
    }
}