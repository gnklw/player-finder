package com.example.football.util.formatConverter;

import com.example.football.exeptions.UnableToConvertException;

import java.io.IOException;

public interface FormatConverter {

    String serialize(Object o) throws UnableToConvertException;

    void serialize(Object o, String fileName);

    <T> T deserialize(String input, Class<T> toType);

    <T> T deserializeFromFile(String fileName, Class<T> toType) throws IOException;
}
