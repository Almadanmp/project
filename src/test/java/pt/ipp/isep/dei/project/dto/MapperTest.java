package pt.ipp.isep.dei.project.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.testng.Assert.*;

public class MapperTest {

    private GeographicArea geoArea;
    private Mapper mapper;
    private Date date; // Wed Nov 21 05:12:00 WET 2018

    @BeforeEach
    void arrangeArtifacts() {
        geoArea = new GeographicArea("Portugal", new TypeArea("Country"), 300, 200,
                new Local(50, 50, 10));
        mapper = new Mapper();
        SimpleDateFormat day = new SimpleDateFormat("dd-MM-yyyy");
        try {
            date = day.parse("12/12/2018");
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void seeIfCreateDTOFromGA() {
        // Act
        GeographicAreaDTO resultDTO = mapper.geographicAreaToDTO(geoArea);
        GeographicArea result = mapper.createGeographicAreaFromDTO(resultDTO);

        // Assert
        assertTrue(resultDTO instanceof GeographicAreaDTO);
        assertTrue(result instanceof GeographicArea);


    }

//    @Test
//    public void seeIfCreateDTOFromSensor() {
//        //Arrange
//        Sensor sensor = new Sensor("RF12345", "SensOne", new TypeSensor("Temperature", "Celsius"),
//                new Local(31, 15, 3), date);
//
//        //Act
//        SensorDTO resultDTO = mapper.sensorToDTO(sensor);
//        Sensor result = mapper.sensorDTOToObject(resultDTO);
//
//        //Assert
//        assertTrue(resultDTO instanceof SensorDTO);
//        assertTrue(result instanceof Sensor);
//
//    }


}