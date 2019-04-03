package pt.ipp.isep.dei.project.reader;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.*;
import pt.ipp.isep.dei.project.dto.ReadingDTOWithSensorID;
import pt.ipp.isep.dei.project.reader.wrapper.ReadingDTOWrapper;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ReadingsReaderJSON implements ReadingsReader {

    public List<ReadingDTOWithSensorID> readFile(String filePath) {
        List<ReadingDTOWithSensorID> readingDTOWithSensorID = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            File file = new File(filePath);
            ReadingDTOWrapper readingDTOWrapper = objectMapper.readValue(file, ReadingDTOWrapper.class);
            readingDTOWithSensorID = readingDTOWrapper.getReadingDTOList();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return readingDTOWithSensorID;
    }
}
