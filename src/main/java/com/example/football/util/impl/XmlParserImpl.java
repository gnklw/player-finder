package com.example.football.util.impl;

import com.example.football.util.XmlParser;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.FileReader;

@Component
public class XmlParserImpl implements XmlParser {

    @Override
    @SuppressWarnings("unchecked")
    public <T> T fromFile(String filePath, Class<T> tClass) throws JAXBException, FileNotFoundException {
        var jaxbContext = JAXBContext.newInstance(tClass);
        var unmarshaller = jaxbContext.createUnmarshaller();
        return (T) unmarshaller.unmarshal(new FileReader(filePath));
    }
}
