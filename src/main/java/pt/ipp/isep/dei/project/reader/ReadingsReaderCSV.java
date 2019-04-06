package pt.ipp.isep.dei.project.reader;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import pt.ipp.isep.dei.project.dto.ReadingDTOWithUnit;
import pt.ipp.isep.dei.project.reader.wrapper.ReadingDTOWrapper;
import pt.ipp.isep.dei.project.services.units.Adapter;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadingsReaderCSV implements ReadingsReader {

    public Map<ReadingDTOWithUnit, String> readFile(String filePath) {
        Map<ReadingDTOWithUnit, String> finalList = new HashMap<>();
        List<ReadingDTOWrapper> readingDTOWrappers;
        CsvSchema csvSchema = CsvSchema.emptySchema().withHeader();
        ObjectMapper csvMapper = new CsvMapper();

        File csvFile = new File(filePath);

        MappingIterator<ReadingDTOWrapper> matchOutcomeIterator;
        try {
            matchOutcomeIterator = csvMapper.readerFor(ReadingDTOWrapper.class).with(csvSchema)
                    .readValues(csvFile);
            readingDTOWrappers = matchOutcomeIterator.readAll();
            finalList = Adapter.readingDTOWrapperConversion(readingDTOWrappers);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return finalList;
    }
}
