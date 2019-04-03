package pt.ipp.isep.dei.project.reader;

import pt.ipp.isep.dei.project.dto.GeographicAreaDTO;

import java.util.List;

public interface GeographicAreaReader {

    /** This method receives a file path that contains information to create Geographic Areas
     * and their respective Sensors and returns a List of Geographic Area DTOs.
     * @param filePath  the file location
     * @return a List with GeographicAreaDTOs
     * **/
    List<GeographicAreaDTO> readFile(String filePath);
}
