package pt.ipp.isep.dei.project.reader;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import pt.ipp.isep.dei.project.dto.ReadingDTOWithUnitAndSensorID;
import pt.ipp.isep.dei.project.reader.wrapper.ReadingDTOWrapper;
import pt.ipp.isep.dei.project.services.units.Adapter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadingsReaderCSV implements ReadingsReader {

    public List<ReadingDTOWithUnitAndSensorID> readFile(String filePath) {
        List<ReadingDTOWithUnitAndSensorID> finalList = new ArrayList<>();
        List<ReadingDTOWrapper> readingDTOWrappers;
        CsvSchema csvSchema = CsvSchema.emptySchema().withHeader();
        ObjectMapper csvMapper = new CsvMapper();
        MappingIterator<ReadingDTOWrapper> matchOutcomeIterator;

        File csvFile = new File(filePath);

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