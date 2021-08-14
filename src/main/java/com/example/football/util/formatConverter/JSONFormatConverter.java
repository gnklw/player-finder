package com.example.football.util.formatConverter;

import com.example.football.exeptions.UnableToConvertException;
import com.example.football.util.formatConverter.FormatConverter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

@Component("json_format_converter")
public class JSONFormatConverter implements FormatConverter {

    private final GsonBuilder gsonBuilder;

    private Gson gson;

    public JSONFormatConverter(GsonBuilder gsonBuilder) {
        this.gsonBuilder = gsonBuilder;
    }

    @Override
    public String serialize(Object o) throws UnableToConvertException {
        return this.getGson().toJson(o);
    }

    @Override
    public void serialize(Object o, String fileName) {
        try (FileWriter fw =  new FileWriter(fileName)) {
            this.getGson().toJson(o,fw);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public <T> T deserialize(String input, Class<T> toType) {
        return this.getGson().fromJson(input, toType);
    }

    @Override
    public <T> T deserializeFromFile(String fileName, Class<T> toType) throws IOException {
        try (FileReader fr = new FileReader(fileName)) {
            return this.getGson().fromJson(fr, toType);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Gson getGson() {
        if (this.gson == null) {
            this.gson = this.gsonBuilder.create();
        }

        return this.gson;
    }
}
