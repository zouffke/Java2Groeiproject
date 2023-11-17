package be.kdg.parsing;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDate;

public class LocalDataGsonAdapter extends TypeAdapter<LocalDate> {

    @Override
    public void write(final JsonWriter out, final LocalDate value) throws IOException {
        out.value(value.toString());
    }

    @Override
    public LocalDate read(final JsonReader in) throws IOException {
        return LocalDate.parse(in.nextString());
    }
}
