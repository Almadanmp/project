package pt.ipp.isep.dei.project.reader;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import pt.ipp.isep.dei.project.dto.AreaSensorDTO;
import pt.ipp.isep.dei.project.dto.GeographicAreaDTO;

import pt.ipp.isep.dei.project.reader.wrapper.AreaSensorDTOWrapper;
import pt.ipp.isep.dei.project.reader.wrapper.AreaSensorDTOWrapperList;
import pt.ipp.isep.dei.project.reader.wrapper.GeographicAreaDTOWrapper;
import pt.ipp.isep.dei.project.reader.wrapper.GeographicAreaDTOWrapperList;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class GeographicAreaReaderJSON implements GeographicAreaReader {

    public List<GeographicAreaDTO> readFile(String filePath) {
        List<GeographicAreaDTO> geographicAreaDTOS;
        List<GeographicAreaDTOWrapper> geoAreaDTOWrapperList;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            File file = new File(filePath);
            GeographicAreaDTOWrapperList geographicAreaDTOWrapperList = objectMapper.readValue(file, GeographicAreaDTOWrapperList.class);
            geoAreaDTOWrapperList = geographicAreaDTOWrapperList.getGeoAreaDTOWrapperList();
            geographicAreaDTOS = GeographicAreaDTOWrapper.geographicAreaDTOWrapperConversion(geoAreaDTOWrapperList);
            for (GeographicAreaDTO dto : geographicAreaDTOS) {
                dto.setAreaSensorDTOList(readAreaSensorDTOS(filePath));
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        return geographicAreaDTOS;
    }

    public List<AreaSensorDTO> readAreaSensorDTOS(String filePath) {
        List<AreaSensorDTOWrapper> areaSensorDTOWrappers;
        List<AreaSensorDTO> sensorDTOS;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            File file = new File(filePath);
            AreaSensorDTOWrapperList areaSensorDTOWrapperList = objectMapper.readValue(file, AreaSensorDTOWrapperList.class);
            areaSensorDTOWrappers = areaSensorDTOWrapperList.getGeoAreaDTOWrapperList();
            sensorDTOS = GeographicAreaDTOWrapper.areaSensorDTOWrapperConversion(areaSensorDTOWrappers);
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        return sensorDTOS;
    }


}