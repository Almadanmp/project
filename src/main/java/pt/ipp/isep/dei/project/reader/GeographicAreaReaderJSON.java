package pt.ipp.isep.dei.project.reader;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import pt.ipp.isep.dei.project.dto.GeographicAreaDTO;
import pt.ipp.isep.dei.project.reader.wrapper.GeographicAreaDTOWrapper;
import pt.ipp.isep.dei.project.reader.wrapper.GeographicAreaDTOWrapperList;
import pt.ipp.isep.dei.project.reader.wrapper.ListOfGeographicAreaDTOWrapperList;
import pt.ipp.isep.dei.project.services.units.Adapter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GeographicAreaReaderJSON implements GeographicAreaReader {

    public List<GeographicAreaDTO> readFile(String filePath) {
        List<GeographicAreaDTO> geographicAreaDTOS = new ArrayList<>();

        List<GeographicAreaDTOWrapperList> listOfGeoAreaDTOWrapperList;
        List<GeographicAreaDTOWrapper> geoAreaDTOWrapperList;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            File file = new File(filePath);
          //  GeographicAreaDTOWrapperList geographicAreaDTOWrapperList = objectMapper.readValue(file, GeographicAreaDTOWrapperList.class);
            ListOfGeographicAreaDTOWrapperList listGeographicAreaDTOWrapperList = objectMapper.readValue(file, ListOfGeographicAreaDTOWrapperList.class);
            listOfGeoAreaDTOWrapperList = listGeographicAreaDTOWrapperList.getGeoAreaDTOWrapperList();
            for (GeographicAreaDTOWrapperList list : listOfGeoAreaDTOWrapperList){
                geoAreaDTOWrapperList = list.getGeoAreaDTOWrapperList();
                geographicAreaDTOS = Adapter.geographicAreaDTOWrapperConversion(geoAreaDTOWrapperList);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        return geographicAreaDTOS;
    }
}
