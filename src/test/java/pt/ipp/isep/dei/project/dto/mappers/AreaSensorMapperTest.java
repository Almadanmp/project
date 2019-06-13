package pt.ipp.isep.dei.project.dto.mappers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.dto.AreaSensorDTO;
import pt.ipp.isep.dei.project.dto.LocalDTO;
import pt.ipp.isep.dei.project.dto.ReadingDTO;
import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.model.geographicarea.AreaSensor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AreaSensorMapperTest {

    private AreaSensor areaSensor;
    private AreaSensorDTO validAreaSensorDTO;

    @BeforeEach
    void arrangeArtifacts() {

        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();

        try {
            date = validSdf.parse("11/02/2018 10:00:00");

        } catch (ParseException e) {
            e.printStackTrace();
        }

        areaSensor = new AreaSensor("12", "SensorDTO1", "Temperature", new Local(2, 4, 5), date);
        validAreaSensorDTO = new AreaSensorDTO();
        validAreaSensorDTO.setActive(true);
        validAreaSensorDTO.setId("12");
        validAreaSensorDTO.setName("SensorDTO1");
        validAreaSensorDTO.setTypeSensor("Temperature");
        validAreaSensorDTO.setUnits("Celsius");
        validAreaSensorDTO.setLatitude(2);
        validAreaSensorDTO.setLongitude(4);
        validAreaSensorDTO.setAltitude(5);
        validAreaSensorDTO.setDateStartedFunctioning("11/02/2018 10:00:00");

        ReadingDTO readingDTO = new ReadingDTO();
        readingDTO.setSensorId("SensorID");
        readingDTO.setDate(date);
        List<ReadingDTO> readingDTOList = new ArrayList<>();
        readingDTOList.add(readingDTO);

        validAreaSensorDTO.setReadingDTOS(readingDTOList);

    }

    @Test
    void dtoToObject() {
        //Act

        AreaSensor actualResult = AreaSensorMapper.dtoToObject(validAreaSensorDTO);

        //Assert

        assertEquals(areaSensor, actualResult);
    }

    @Test
    void objectToDTO() {
        //Act

        AreaSensorDTO actualResult = AreaSensorMapper.objectToDTO(areaSensor);

        //Assert

        assertEquals(validAreaSensorDTO, actualResult);
    }
}