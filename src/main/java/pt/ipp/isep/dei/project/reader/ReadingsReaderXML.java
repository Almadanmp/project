package pt.ipp.isep.dei.project.reader;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import pt.ipp.isep.dei.project.dto.ReadingDTOWithUnitAndSensorID;
import pt.ipp.isep.dei.project.reader.wrapper.ReadingDTOLWrapperList;
import pt.ipp.isep.dei.project.reader.wrapper.ReadingDTOWrapper;
import pt.ipp.isep.dei.project.services.units.Adapter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadingsReaderXML implements ReadingsReader {

    public List<ReadingDTOWithUnitAndSensorID> readFile(String filePath) {
        List<ReadingDTOWithUnitAndSensorID> finalList = new ArrayList<>();
        List<ReadingDTOWrapper> readingDTOWrapper;
        ObjectMapper objectMapper = new XmlMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            File file = new File(filePath);
            ReadingDTOLWrapperList readingDTOLWrapperList = objectMapper.readValue(file, ReadingDTOLWrapperList.class);
            readingDTOWrapper = readingDTOLWrapperList.getReadingDTOWrapperList();
            finalList = Adapter.readingDTOWrapperConversion(readingDTOWrapper);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return finalList;
    }
}
