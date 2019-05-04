package pt.ipp.isep.dei.project.io.ui.reader;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import pt.ipp.isep.dei.project.dto.ReadingDTO;
import pt.ipp.isep.dei.project.io.ui.reader.deserializer.ReadingDTOWrapperCustomDeserializer;
import pt.ipp.isep.dei.project.io.ui.reader.wrapper.ReadingDTOLWrapperList;
import pt.ipp.isep.dei.project.io.ui.reader.wrapper.ReadingDTOWrapper;
import pt.ipp.isep.dei.project.services.units.Adapter;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ReadingsReaderXML implements ReadingsReader {

    public List<ReadingDTO> readFile(String filePath) {
        List<ReadingDTO> finalList;
        List<ReadingDTOWrapper> readingDTOWrapper;
        ObjectMapper objectMapper = new XmlMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_CREATOR_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);

        SimpleModule module = new SimpleModule();
        module.addDeserializer(ReadingDTOWrapper.class, new ReadingDTOWrapperCustomDeserializer());
        objectMapper.registerModule(module);

        try {
            File file = new File(filePath);
            ReadingDTOLWrapperList readingDTOLWrapperList = objectMapper.readValue(file, ReadingDTOLWrapperList.class);
            readingDTOWrapper = readingDTOLWrapperList.getReadingDTOWrapperList();
            finalList = Adapter.readingDTOWrapperConversion(readingDTOWrapper);
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        return finalList;
    }
}
