package com.example.football.config;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

    @Override
    public LocalDate unmarshal(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    @Override
    public String marshal(LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
