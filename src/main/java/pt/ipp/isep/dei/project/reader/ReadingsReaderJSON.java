package pt.ipp.isep.dei.project.reader;

import com.fasterxml.jackson.databind.*;
import pt.ipp.isep.dei.project.reader.wrapper.ReadingDTOLWrapperList;
import pt.ipp.isep.dei.project.reader.wrapper.ReadingDTOWrapper;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ReadingsReaderJSON implements ReadingsReader {

    public List<ReadingDTOWrapper> readFile(String filePath) {
        List<ReadingDTOWrapper> readingDTOWrapperList = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            File file = new File(filePath);
            ReadingDTOLWrapperList readingDTOLWrapperList = objectMapper.readValue(file, ReadingDTOLWrapperList.class);
            readingDTOWrapperList = readingDTOLWrapperList.getReadingDTOWrapperList();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return readingDTOWrapperList;
    }
}
