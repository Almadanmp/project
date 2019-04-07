package pt.ipp.isep.dei.project.reader;

import com.fasterxml.jackson.databind.*;
import pt.ipp.isep.dei.project.dto.ReadingDTOWithUnitAndSensorID;
import pt.ipp.isep.dei.project.reader.wrapper.ReadingDTOLWrapperList;
import pt.ipp.isep.dei.project.reader.wrapper.ReadingDTOWrapper;
import pt.ipp.isep.dei.project.services.units.Adapter;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ReadingsReaderJSON implements ReadingsReader {

    public List<ReadingDTOWithUnitAndSensorID> readFile(String filePath) {
        List<ReadingDTOWithUnitAndSensorID> finalList = new ArrayList<>();
        List<ReadingDTOWrapper> readingDTOWrapperList;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            File file = new File(filePath);
            ReadingDTOLWrapperList readingDTOLWrapperList = objectMapper.readValue(file, ReadingDTOLWrapperList.class);
            readingDTOWrapperList = readingDTOLWrapperList.getReadingDTOWrapperList();
            finalList = Adapter.readingDTOWrapperConversion(readingDTOWrapperList);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return finalList;
    }


}
