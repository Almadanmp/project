package pt.ipp.isep.dei.project.dto.mappers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.dto.AreaSensorDTO;
import pt.ipp.isep.dei.project.dto.GeographicAreaDTO;
import pt.ipp.isep.dei.project.dto.GeographicAreaWebDTO;
import pt.ipp.isep.dei.project.dto.LocalDTO;
import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.model.geographicarea.AreaSensor;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project.model.sensortype.SensorType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GeographicAreaMapperTest {
    // Common testing artifacts for testing in this class.

    private GeographicArea validAreaObject;
    private GeographicArea validAreaObjectWithDaughters;
    private LocalDTO localDTO;
    private AreaSensorDTO validAreaSensorDTO;
    private AreaSensor firstValidAreaSensor;
    private AreaSensor secondValidAreaSensor;
    private Date validDate1;


    @BeforeEach
    void arrangeArtifacts() {
        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();

        try {
            date = validSdf.parse("21/03/2018 10:02:00");
            validDate1 = validSdf.parse("01/04/2018 00:00:00");

        } catch (ParseException e) {
            e.printStackTrace();
        }

        validAreaObject = new GeographicArea("Portugal", "Country", 300, 200,
                new Local(50, 50, 10));
        validAreaObject.setId(6008L);
        localDTO = new LocalDTO();
        localDTO.setAltitude(100);
        localDTO.setLongitude(100);
        localDTO.setLatitude(100);
        GeographicArea geographicArea = new GeographicArea();
        geographicArea.setLocation(new Local(100,100,100));
        List<AreaSensor> areaSensors = new ArrayList<>();
        geographicArea.setAreaSensors(areaSensors);
        List<GeographicArea> daughterList = new ArrayList<>();
        daughterList.add(geographicArea);

        validAreaObjectWithDaughters = new GeographicArea("Porto", "City", 300, 200,
                new Local(50, 50, 10));
        validAreaObjectWithDaughters.setId(10L);
        validAreaObjectWithDaughters.setChildAreas(daughterList);

        validAreaSensorDTO = new AreaSensorDTO();
        validAreaSensorDTO.setActive(true);
        validAreaSensorDTO.setId("12");
        validAreaSensorDTO.setName("SensorDTO1");
        validAreaSensorDTO.setTypeSensor("Temperature");
        validAreaSensorDTO.setUnits("Celsius");
        validAreaSensorDTO.setLatitude(2);
        validAreaSensorDTO.setLongitude(4);
        validAreaSensorDTO.setAltitude(5);
        validAreaSensorDTO.setDateStartedFunctioning("21/03/2018 10:02:00");

        SensorType validSensorTypeTemperature = new SensorType("Temperature", "Cº");
        firstValidAreaSensor = new AreaSensor("SensorOne", "SensorOne", validSensorTypeTemperature.getName(), new Local(2, 2, 2), validDate1);
        firstValidAreaSensor.setActive(true);
        secondValidAreaSensor = new AreaSensor("SensorTwo", "SensorTwo", validSensorTypeTemperature.getName(), new Local(10, 10, 10),
                validDate1);
        secondValidAreaSensor.setActive(true);
    }


    @Test
    void seeIfObjectToDTOWorks() {
        // Arrange

        GeographicAreaDTO expectedResult = new GeographicAreaDTO();
        LocalDTO localDTO = new LocalDTO();
        localDTO.setLatitude(50D);
        localDTO.setLongitude(50D);
        localDTO.setAltitude(10D);
        expectedResult.setName("Portugal");
        expectedResult.setTypeArea("Country");
        expectedResult.setLength(300);
        expectedResult.setWidth(200);
        expectedResult.setId(6008L);
        expectedResult.setLocal(localDTO);

        // Act

        GeographicAreaDTO actualResult = GeographicAreaMapper.objectToDTO(validAreaObject);

        // Assert

        assertEquals(expectedResult, actualResult);
        assertEquals(actualResult.getGeographicAreaId(), validAreaObject.getId());
    }

    @Test
    void seeIfObjectToWebDTOWorks() {
        // Arrange

        GeographicAreaWebDTO expectedResult = new GeographicAreaWebDTO();
        LocalDTO localDTO = new LocalDTO();
        localDTO.setLatitude(50D);
        localDTO.setLongitude(50D);
        localDTO.setAltitude(10D);
        expectedResult.setName("Portugal");
        expectedResult.setTypeArea("Country");
        expectedResult.setId(6008L);

        // Act

        GeographicAreaWebDTO actualResult = GeographicAreaMapper.objectToWebDTO(validAreaObject);

        // Assert

        assertEquals(expectedResult, actualResult);
        assertEquals(actualResult.getId(), validAreaObject.getId());
    }

    @Test
    void seeIfObjectToDTOWithMotherWorks() {
        // Arrange

        GeographicAreaDTO expectedResult = new GeographicAreaDTO();
        List<GeographicAreaDTO> daughterAreaList = new ArrayList<>();
        GeographicAreaDTO dto1 = new GeographicAreaDTO();
        dto1.setLocal(localDTO);
        daughterAreaList.add(dto1);
        LocalDTO localDTO = new LocalDTO();
        localDTO.setLatitude(50D);
        localDTO.setLongitude(50D);
        localDTO.setAltitude(10D);
        expectedResult.setName("Porto");
        expectedResult.setTypeArea("City");
        expectedResult.setLength(300);
        expectedResult.setWidth(200);
        expectedResult.setDaughterAreaList(daughterAreaList);
        expectedResult.setId(10L);
        expectedResult.setLocal(localDTO);

        // Act

        GeographicAreaDTO actualResult = GeographicAreaMapper.objectToDTOWithMother(validAreaObjectWithDaughters);

        // Assert

        assertEquals(expectedResult, actualResult);
        assertEquals(actualResult.getGeographicAreaId(), validAreaObjectWithDaughters.getId());
    }

    @Test
    void seeIfDTOToObjectWorks() {
        // Arrange

        GeographicAreaDTO dtoToConvert = new GeographicAreaDTO();
        LocalDTO localDTO = new LocalDTO();
        localDTO.setLatitude(50);
        localDTO.setAltitude(10);
        localDTO.setLongitude(50);
        dtoToConvert.setName("Portugal");
        dtoToConvert.setTypeArea("Country");
        dtoToConvert.setLength(300);
        dtoToConvert.setWidth(200);
        dtoToConvert.setId(6008L);
        dtoToConvert.setLocal(localDTO);

        // Act

        GeographicArea actualResult = GeographicAreaMapper.dtoToObject(dtoToConvert);

        // Assert

        assertEquals(validAreaObject, actualResult);
        Long.compare(actualResult.getId(), 6008L);
    }

    @Test
    void seeIfDTOToObjectWorksWhenIDNull() {
        // Arrange

        GeographicAreaDTO dtoToConvert = new GeographicAreaDTO();
        LocalDTO localDTO = new LocalDTO();
        localDTO.setLatitude(50);
        localDTO.setAltitude(10);
        localDTO.setLongitude(50);
        dtoToConvert.setName("Portugal");
        dtoToConvert.setTypeArea("Country");
        dtoToConvert.setLength(300);
        dtoToConvert.setWidth(200);
        dtoToConvert.setId(null);
        dtoToConvert.setLocal(localDTO);

        List<AreaSensorDTO> areaSensorDTOList = new ArrayList<>();
        areaSensorDTOList.add(validAreaSensorDTO);
        dtoToConvert.setSensorList(areaSensorDTOList);


        // Act

        GeographicArea actualResult = GeographicAreaMapper.dtoToObject(dtoToConvert);

        // Assert

        assertEquals(validAreaObject, actualResult);
    }

    @Test
    void seeIfDtoToObjectThrowsException() {
        GeographicAreaDTO geographicAreaDTO = new GeographicAreaDTO();
        assertThrows(NullPointerException.class,
                () -> GeographicAreaMapper.dtoToObject(geographicAreaDTO));
    }

    @Test
    void seeIfObjectToDtoThrowsException() {
        GeographicArea geographicArea = new GeographicArea();
        assertThrows(NullPointerException.class,
                () -> GeographicAreaMapper.objectToDTO(geographicArea));
    }

//    @Test
//    void seeIfDTOToObjectWorksMother() {
//        // Arrange
//
//        GeographicAreaDTO daughterDTO = GeographicAreaMapper.objectToDTO(validAreaObject);
//        validAreaObject.addSensor(firstValidAreaSensor);
//        validAreaObject.addSensor(secondValidAreaSensor);
//
//        GeographicAreaDTO dtoToConvert = new GeographicAreaDTO();
//        LocalDTO localDTO = new LocalDTO();
//        localDTO.setLatitude(50);
//        localDTO.setAltitude(10);
//        localDTO.setLongitude(50);
//        dtoToConvert.setName("Portugal");
//        dtoToConvert.setTypeArea("Country");
//        dtoToConvert.setLength(300);
//        dtoToConvert.setWidth(200);
//        dtoToConvert.setSensorId(6008L);
//        dtoToConvert.setLocal(localDTO);
//        dtoToConvert.addDaughter(daughterDTO);
//
//        // Act
//
//        GeographicArea actualResult = GeographicAreaMapper.dtoToObjectWithMother(dtoToConvert,validAreaObject);
//
//        // Assert
//
//        assertEquals(validAreaObject, actualResult);
//        Long.compare(actualResult.getSensorId(), 6008L);
//    }

}