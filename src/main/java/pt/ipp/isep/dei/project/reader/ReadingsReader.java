package pt.ipp.isep.dei.project.reader;


import pt.ipp.isep.dei.project.reader.wrapper.ReadingDTOWrapper;

import java.util.List;

public interface ReadingsReader {
    /** This method receives a file path that contains information to create readings
     * and returns a mapper with String containing the sensor Id and the corresponding Reading DTO.
     * @param filePath  the file location
     * @return mapper with sensor Id and corresponding ReadingDTO
     * **/
    List<ReadingDTOWrapper> readFile(String filePath);
}
