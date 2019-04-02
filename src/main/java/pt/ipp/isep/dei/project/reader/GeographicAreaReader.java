package pt.ipp.isep.dei.project.reader;

import pt.ipp.isep.dei.project.dto.GeographicAreaDTO;
import pt.ipp.isep.dei.project.model.GeographicAreaList;

import java.util.List;

public interface GeographicAreaReader {

    List<GeographicAreaDTO> readFile(String filePath, GeographicAreaList geographicAreaList);

}
