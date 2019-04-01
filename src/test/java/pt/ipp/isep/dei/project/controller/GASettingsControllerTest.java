package pt.ipp.isep.dei.project.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import pt.ipp.isep.dei.project.dto.GeographicAreaDTO;
import pt.ipp.isep.dei.project.dto.LocalDTO;
import pt.ipp.isep.dei.project.dto.SensorDTO;
import pt.ipp.isep.dei.project.dto.mappers.GeographicAreaMapper;
import pt.ipp.isep.dei.project.dto.mappers.LocalMapper;
import pt.ipp.isep.dei.project.dto.mappers.SensorMapper;
import pt.ipp.isep.dei.project.dto.mappers.TypeAreaMapper;
import pt.ipp.isep.dei.project.io.ui.MainUI;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.model.sensor.Sensor;
import pt.ipp.isep.dei.project.model.sensor.TypeSensor;
import pt.ipp.isep.dei.project.repository.GeographicAreaRepository;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.testng.Assert.*;

/**
 * GASettingsController tests class.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@ContextConfiguration(classes = {MainUI.class},
        loader = AnnotationConfigContextLoader.class)
class GASettingsControllerTest {
    private GASettingsController controller = new GASettingsController();
    private GeographicArea firstValidArea;
    private GeographicArea secondValidArea;
    private TypeArea typeCountry;
    private TypeArea typeCity;
    private GeographicAreaDTO validGeographicAreaDTO;
    private SensorDTO validSensorDTO1;
    private SensorDTO validSensorDTO2;
    private Sensor validSensor1;
    private Sensor validSensor2;
    private GeographicAreaList validGeographicAreaList;
    private TypeAreaList validTypeAreaList;
    private Date date; // Wed Nov 21 05:12:00 WET 2018

    @Autowired
    GeographicAreaRepository geographicAreaRepository;

    @BeforeEach
    void arrangeArtifacts() {

        SimpleDateFormat day = new SimpleDateFormat("dd-MM-yyyy");
        try {
            date = day.parse("12-12-2018");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        typeCountry = new TypeArea("Country");
        typeCity = new TypeArea("City");
        firstValidArea = new GeographicArea("Portugal", typeCountry,
                2, 5, new Local(21, 33, 5));
        secondValidArea = new GeographicArea("Portugal", typeCity,
                2, 5, new Local(21, 33, 5));
        validSensor1 = new Sensor("RF12345", "SensOne", new TypeSensor("Temperature", "Celsius"),
                new Local(31, 15, 3), date);
        validSensor2 = new Sensor("TT12345", "SensTwo", new TypeSensor("Temperature", "Celsius"),
                new Local(21, 65, 3), date);
        firstValidArea.addSensor(validSensor1);
        validGeographicAreaDTO = GeographicAreaMapper.objectToDTO(firstValidArea);
        validSensorDTO1 = SensorMapper.objectToDTO(validSensor1);
        validSensorDTO2 = SensorMapper.objectToDTO(validSensor2);

        validGeographicAreaList = new GeographicAreaList();
        validGeographicAreaList.addGeographicArea(firstValidArea);
        validGeographicAreaList.addGeographicArea(secondValidArea);

        validTypeAreaList = new TypeAreaList();
    }

    @Test
    void seeIfPrintGATypeListWorks() {
        // Arrange

        validTypeAreaList.addTypeArea(typeCountry);
        validTypeAreaList.addTypeArea(typeCity);
        String expectedResult = "---------------\n" +
                "0) Description: Country \n" +
                "1) Description: City \n" +
                "---------------\n";

        // Act

        String actualResult = controller.buildGATypeListString(validTypeAreaList);


        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfMatchGAByTypeAreaWorks() {
        // Arrange

        validGeographicAreaList.addAndPersistGA(secondValidArea);
        GeographicAreaList expectedResult = new GeographicAreaList();
        expectedResult.setGeographicAreaRepository(geographicAreaRepository);
        expectedResult.addAndPersistGA(secondValidArea);

        // Act

        GeographicAreaList actualResult = controller.matchGAByTypeArea(validGeographicAreaList, TypeAreaMapper.objectToDTO(typeCity));

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetTypeAreaName() {

        // Arrange

        String expectedResult = "City";

        // Act
        String actualResult = controller.getTypeAreaName(TypeAreaMapper.objectToDTO(typeCity));

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    //USER STORY 001 TESTS

    @Test
    void seeIfCreateTypeAreaWorksEmptyList() {

        // Act

        boolean result = controller.createAndAddTypeAreaToList(validTypeAreaList, "City");

        // Assert

        assertTrue(result);
    }

    @Test
    void seeIfCreateTypeAreaWorksListWithElements() {

        // Arrange

        validTypeAreaList.addTypeArea(typeCountry);

        // Act

        boolean result = controller.createAndAddTypeAreaToList(validTypeAreaList, "City");

        // Assert

        assertTrue(result);
    }

    @Test
    void seeIfNewTAGDoesntWorkWhenDuplicatedISAdded() {

        // Arrange

        validTypeAreaList.addTypeArea(typeCountry);

        // Act

        boolean result = controller.createAndAddTypeAreaToList(validTypeAreaList, "Country");

        // Assert

        assertFalse(result);
    }

    //USER STORY 002 TESTS

    @Test
    void seeIfPrintTypeAreaListWorks() {

        // Arrange

        validTypeAreaList.addTypeArea(typeCountry);
        String expectedResult = "---------------\n" +
                "0) Description: Country \n" +
                "---------------\n";

        // Act

        String actualResult = controller.getTypeAreaList(validTypeAreaList);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfPrintTypeAreaListWorksWithTwoTypes() {

        // Arrange

        validTypeAreaList.addTypeArea(typeCountry);
        validTypeAreaList.addTypeArea(typeCity);
        String expectedResult = "---------------\n" +
                "0) Description: Country \n" +
                "1) Description: City \n" +
                "---------------\n";

        // Act

        String actualResult = controller.getTypeAreaList(validTypeAreaList);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfPrintTypeAreaListWorksWithThreeTypes() {

        // Arrange

        validTypeAreaList.addTypeArea(typeCity);
        validTypeAreaList.addTypeArea(typeCountry);
        String expectedResult = "---------------\n" +
                "0) Description: City \n" +
                "1) Description: Country \n" +
                "---------------\n";

        // Act

        String actualResult = controller.getTypeAreaList(validTypeAreaList);

        // Assert

        assertEquals(expectedResult, actualResult);
    }


    //USER STORY 003 TESTS

    @Test
    void seeIfCreatesGeographicAreaAndAddsItToList() {
        // Arrange

        GeographicAreaList geographicAreaList = new GeographicAreaList();

        // Act

        boolean result = controller.addNewGeoAreaToList(geographicAreaList, validGeographicAreaDTO,
                LocalMapper.objectToDTO(firstValidArea.getLocal()));

        // Assert

        assertTrue(result);
        assertEquals(1, geographicAreaList.size());
    }

    //USER STORY 004 TESTS

    @Test
    void seeIfMatchGAByTypeCountry() {

        //Arrange

        GeographicAreaList gaL1 = new GeographicAreaList();
        gaL1.addGeographicArea(firstValidArea);
        gaL1.addGeographicArea(secondValidArea);
        GeographicAreaList expectedResult = new GeographicAreaList();
        expectedResult.addGeographicArea(firstValidArea);

        //Act

        GeographicAreaList actualResult = controller.matchGAByTypeArea(gaL1, TypeAreaMapper.objectToDTO(typeCountry));

        //Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfMatchGAByTypeCity() {

        //Arrange

        GeographicAreaList gaL1 = new GeographicAreaList();
        gaL1.addGeographicArea(firstValidArea);
        gaL1.addGeographicArea(secondValidArea);
        GeographicAreaList expectedResult = new GeographicAreaList();
        expectedResult.addGeographicArea(secondValidArea);

        //Act

        GeographicAreaList actualResult = controller.matchGAByTypeArea(gaL1, TypeAreaMapper.objectToDTO(typeCity));

        //Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeMatchGAByTypeNotInList() {

        //Arrange

        GeographicAreaList gaL1 = new GeographicAreaList();
        gaL1.addGeographicArea(firstValidArea);
        GeographicAreaList expectedResult = new GeographicAreaList();

        //Act

        GeographicAreaList actualResult = controller.matchGAByTypeArea(gaL1, TypeAreaMapper.objectToDTO(typeCity));

        //Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getTypeAreaName() {

        //Arrange

        String expectedResult = "City";
        String actualResult;

        //Act
        actualResult = controller.getTypeAreaName(TypeAreaMapper.objectToDTO(typeCity));

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfDeactivateSensor() {

        //Act
        boolean actualResult = controller.deactivateSensor(validGeographicAreaList, validSensorDTO1, validGeographicAreaDTO);

        //Assert
        assertTrue(actualResult);
    }

    @Test
    void seeIfDeactivateSensorWhenSecondInList() {

        //Act
        boolean actualResult = controller.deactivateSensor(validGeographicAreaList, validSensorDTO2, validGeographicAreaDTO);

        //Assert
        assertTrue(actualResult);
    }

    @Test
    void seeIfDeactivatedSensorDoesntChange() {
        //Arrange

        validSensor1.deactivateSensor();
        SensorDTO sensorDTO = SensorMapper.objectToDTO(validSensor1);

        //Act

        boolean actualResult = controller.deactivateSensor(validGeographicAreaList, sensorDTO, validGeographicAreaDTO);

        //Assert

        assertFalse(actualResult);
    }


    @Test
    void seeIfPrintGAList() {

        //Arrange

        validGeographicAreaList.addGeographicArea(secondValidArea);
        String expectedResult = "---------------\n" +
                "0) Name: Portugal | Type: Country | Latitude: 21.0 | Longitude: 33.0\n" +
                "1) Name: Portugal | Type: City | Latitude: 21.0 | Longitude: 33.0\n" +
                "---------------\n";

        //Act

        String result = controller.buildGAListString(validGeographicAreaList);

        //Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfSetMotherAreaWorks() {
        // Act

        boolean actualResult = controller.setMotherArea(firstValidArea, secondValidArea);

        // Assert

        Assertions.assertTrue(actualResult);
    }

    @Test
    void seeIfSetMotherAreaBreaks() {
        // Act

        boolean actualResult = controller.setMotherArea(firstValidArea, null);

        // Assert

        Assertions.assertFalse(actualResult);
    }

//USER STORY 008 Tests

    @Test
    void seeIfItsContained() {

        //Arrange

        firstValidArea.setMotherArea(secondValidArea);

        //Act

        boolean actualResult = controller.isAreaContained(secondValidArea, firstValidArea);

        //Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfIndirectlyContained() {

        // Arrange

        GeographicArea grandDaughterGA = new GeographicArea("Porto", new TypeArea("Country"),
                2, 4, new Local(21, 33, 5));
        grandDaughterGA.setMotherArea(secondValidArea);
        secondValidArea.setMotherArea(firstValidArea);

        // Act

        boolean actualResult = controller.isAreaContained(firstValidArea, grandDaughterGA);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfNotContained() {

        // Arrange

        GeographicArea grandDaughterGA = new GeographicArea("Oporto", new TypeArea("Country"), 2, 4, new Local(21, 33, 5));
        grandDaughterGA.setMotherArea(secondValidArea);
        secondValidArea.setMotherArea(firstValidArea);

        // Act

        boolean actualResult = controller.isAreaContained(grandDaughterGA, firstValidArea);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeGAId() {

        //Act

        String actualResult = controller.getGeographicAreaId(firstValidArea);

        //Assert

        assertEquals(actualResult, "Portugal");
    }

    @Test
    void seeIfCreateGeoAreaDTO() {
        GeographicAreaDTO expectedResult = new GeographicAreaDTO();
        expectedResult.setName("Joana");
        expectedResult.setLatitude(12);
        expectedResult.setLongitude(13);
        expectedResult.setAltitude(13);
        expectedResult.setTypeArea(typeCity.getName());

        GeographicAreaDTO result = controller.createGeoAreaDTO("Joana", TypeAreaMapper.objectToDTO(typeCity),
                controller.createLocalDTO(12, 13, 13), 12, 13);

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfCreateLocalDTO() {

        LocalDTO result = controller.createLocalDTO(12, 13, 14);

        assertTrue(result instanceof LocalDTO);
    }

    //USER STORY 011 Tests

    @Test
    void seeIfInputAreaWorks() {

        //Arrange

        InputStream in = new ByteArrayInputStream("0".getBytes());
        System.setIn(in);
        GeographicAreaDTO expectedResult = validGeographicAreaDTO;

        //Act

        GeographicAreaDTO actualResult = controller.inputArea(validGeographicAreaList);

        //Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfInputSensorWorks() {

        //Arrange

        InputStream in = new ByteArrayInputStream("0".getBytes());
        System.setIn(in);
        SensorDTO expectedResult = validSensorDTO1;

        //Act

        SensorDTO actualResult = controller.inputSensor(validGeographicAreaDTO);

        //Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfRemoveSensorWorks() {

        //Arrange

        GeographicAreaList expectedResult = validGeographicAreaList;
        expectedResult.get(0).removeSensor(validSensor1);

        //Act

        controller.removeSensor(validGeographicAreaList, validSensorDTO1, validGeographicAreaDTO);
        GeographicAreaList actualResult = validGeographicAreaList;

        //Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfRemoveSensorWorksWhenSecondInList() {

        //Arrange

        GeographicAreaList expectedResult = validGeographicAreaList;
        expectedResult.get(0).removeSensor(validSensor2);

        //Act

        controller.removeSensor(validGeographicAreaList, validSensorDTO2, validGeographicAreaDTO);
        GeographicAreaList actualResult = validGeographicAreaList;

        //Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfAddNewGeoAreaToListWorksAlreadyThere() {
        // Act

        boolean result = controller.addNewGeoAreaToList(validGeographicAreaList, validGeographicAreaDTO, LocalMapper.objectToDTO
                (new Local(21, 33, 5)));

        // Assert

        assertTrue(result);
    }

    @Test
    void seeIfRemoveSensorFails() {

        //Arrange

        GeographicAreaList expectedResult = new GeographicAreaList();

        //Act

        controller.removeSensor(validGeographicAreaList, validSensorDTO1, validGeographicAreaDTO);
        GeographicAreaList actualResult = validGeographicAreaList;

        //Assert

        assertNotEquals(expectedResult, actualResult);
    }
}