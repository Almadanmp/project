package pt.ipp.isep.dei.project.io.ui.reader.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import pt.ipp.isep.dei.project.io.ui.utils.DateUtils;
import pt.ipp.isep.dei.project.io.ui.reader.wrapper.ReadingDTOWrapper;

import java.io.IOException;
import java.util.Date;

public class ReadingDTOWrapperCustomDeserializer extends StdDeserializer<ReadingDTOWrapper> {

    private static final long serialVersionUID = 2L;

    public ReadingDTOWrapperCustomDeserializer() {
        this(null);
    }

    private ReadingDTOWrapperCustomDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public ReadingDTOWrapper deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        ObjectCodec oc = jp.getCodec();
        JsonNode node = oc.readTree(jp);
        ReadingDTOWrapper readingDTOWrapper = new ReadingDTOWrapper();
        final String parsingDate;
        String tempDate;
        try {
            tempDate = node.get("timestamp/date").asText();
        } catch (Exception e) {
            e.getMessage();
            tempDate = node.get("timestamp_date").asText();
        }

        parsingDate = tempDate;
        try {
            Date finalDate = DateUtils.dateParser(parsingDate);
            readingDTOWrapper.setDate(finalDate);
        } catch (IllegalArgumentException illegal) {
            illegal.getMessage();
            return null;
        }
        String value = node.get("value").asText();
        try {
            final double readingValue = Double.parseDouble(value);
            readingDTOWrapper.setValue(readingValue);
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
        final String unit = node.get("unit").asText();
        final String id = node.get("id").asText();
        readingDTOWrapper.setSensorId(id);
        readingDTOWrapper.setUnit(unit);
        return readingDTOWrapper;
    }
}