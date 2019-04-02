package pt.ipp.isep.dei.project.reader;

import pt.ipp.isep.dei.project.dto.GeographicAreaDTO;
import pt.ipp.isep.dei.project.model.GeographicAreaList;

import java.util.ArrayList;
import java.util.List;

public class GeographicAreaReaderJSON implements GeographicAreaReader {

    public List<GeographicAreaDTO> readFile(String filePath, GeographicAreaList geographicAreaList) {
        List<GeographicAreaDTO> geographicAreaDTOS = new ArrayList<>();
        return geographicAreaDTOS;
    }
}
