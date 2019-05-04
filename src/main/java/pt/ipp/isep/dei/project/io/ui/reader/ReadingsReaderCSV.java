package pt.ipp.isep.dei.project.io.ui.reader;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import pt.ipp.isep.dei.project.dto.ReadingDTO;
import pt.ipp.isep.dei.project.io.ui.reader.deserializer.ReadingDTOWrapperCustomDeserializer;
import pt.ipp.isep.dei.project.io.ui.reader.wrapper.ReadingDTOWrapper;
import pt.ipp.isep.dei.project.services.units.Adapter;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ReadingsReaderCSV implements ReadingsReader {

    public List<ReadingDTO> readFile(String filePath) {
        List<ReadingDTO> finalList;
        List<ReadingDTOWrapper> readingDTOWrappers;
        CsvSchema csvSchema = CsvSchema.emptySchema().withHeader();
        ObjectMapper csvMapper = new CsvMapper();
        MappingIterator<ReadingDTOWrapper> matchOutcomeIterator;
        csvMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        csvMapper.configure(DeserializationFeature.FAIL_ON_NULL_CREATOR_PROPERTIES, false);
        csvMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);

        SimpleModule module = new SimpleModule();
        module.addDeserializer(ReadingDTOWrapper.class, new ReadingDTOWrapperCustomDeserializer());
        csvMapper.registerModule(module);

        File csvFile = new File(filePath);

        try {
            matchOutcomeIterator = csvMapper.readerFor(ReadingDTOWrapper.class).with(csvSchema)
                    .readValues(csvFile);
            readingDTOWrappers = matchOutcomeIterator.readAll();
            finalList = Adapter.readingDTOWrapperConversion(readingDTOWrappers);
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        return finalList;
    }
}
