package com.example.football.util.formatConverter;

import com.example.football.exeptions.UnableToConvertException;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

@Component("xml_format_converter")
public class XMLFormatConverter implements FormatConverter {

    @Override
    public String serialize(Object o) throws UnableToConvertException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(o.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            StringWriter sw = new StringWriter();

            marshaller.marshal(o, sw);

            return sw.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new UnableToConvertException();
        }
    }

    @Override
    public void serialize(Object o, String fileName) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(o.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            FileWriter fw = new FileWriter(fileName);

            marshaller.marshal(o, fw);
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T deserialize(String input, Class<T> toType) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(toType);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());

            return (T) unmarshaller.unmarshal(inputStream);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T deserializeFromFile(String fileName, Class<T> toType) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(toType);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            return (T) unmarshaller.unmarshal(new FileInputStream(fileName));
        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
