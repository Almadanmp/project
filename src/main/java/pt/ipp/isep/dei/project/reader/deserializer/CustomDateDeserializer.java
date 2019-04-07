package pt.ipp.isep.dei.project.reader.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomDateDeserializer extends StdDeserializer<Date> {
    private static final long serialVersionUID = 1L;

    public CustomDateDeserializer() {
        this(null);
    }

    public CustomDateDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Date deserialize(JsonParser jsonparser, DeserializationContext context)
            throws IOException, JsonProcessingException {
        List<SimpleDateFormat> simpleDateFormats = getSimpleDateFormats();
        String date = jsonparser.getText();
        for (SimpleDateFormat sdf : simpleDateFormats) {
            try {
                return sdf.parse(date);
            } catch (ParseException e) {
                e.getMessage();
            }
        }
        throw new RuntimeException();
    }

    private List<SimpleDateFormat> getSimpleDateFormats() {
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'+00:00'");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat dateFormat3 = new SimpleDateFormat("yyyy-MM-dd");

        List<SimpleDateFormat> simpleDateFormats = new ArrayList<>();
        simpleDateFormats.add(dateFormat1);
        simpleDateFormats.add(dateFormat2);
        simpleDateFormats.add(dateFormat3);
        return simpleDateFormats;
    }
}