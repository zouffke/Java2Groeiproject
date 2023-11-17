package be.kdg.parsing;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;

public class SailboatsJaxbParser {
    public static void JaxbWriteXml(String file, Object root) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(root.getClass());
        Marshaller m = jc.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        m.marshal(root, new File(file));
    }

    @SuppressWarnings("unchecked")
    public static <T> T JaxbReadXml(String file, Class<T> typeParameterClass) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(typeParameterClass);
        Unmarshaller u = jc.createUnmarshaller();

        File f = new File(file);

        return (T) u.unmarshal(f);
    }
}
