package pt.ipp.isep.dei.project.reader.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import pt.ipp.isep.dei.project.io.ui.utils.DateUtils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CustomDateDeserializer extends StdDeserializer<Date> {
    private static final long serialVersionUID = 1L;

    public CustomDateDeserializer() {
        this(null);
    }

    private CustomDateDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Date deserialize(JsonParser jsonparser, DeserializationContext context)
            throws IOException, JsonProcessingException {
        List<SimpleDateFormat> simpleDateFormats = DateUtils.getSimpleDateFormats();
        String date = jsonparser.getText();
        for (SimpleDateFormat sdf : simpleDateFormats) {
            try {
                return sdf.parse(date);
            } catch (ParseException e) {
                e.getMessage();
            }
        }
        return Date.from(null);
    }
}