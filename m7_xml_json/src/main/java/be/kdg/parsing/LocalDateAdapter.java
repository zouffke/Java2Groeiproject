package be.kdg.parsing;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.time.LocalDate;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
    @Override
    public LocalDate unmarshal (String s) throws Exception {
        return LocalDate.parse(s);
    }

    @Override
    public String marshal (LocalDate date) throws Exception{
        return date.toString();
    }
}
