package pt.ipp.isep.dei.project.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.dto.Mapper;
import pt.ipp.isep.dei.project.dto.SensorDTO;
import pt.ipp.isep.dei.project.model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.testng.Assert.*;

/**
 * GASettingsController tests class.
 */

class GASettingsControllerTest {
    private GASettingsController controller = new GASettingsController();
    private GeographicArea firstValidArea;
    private GeographicArea secondValidArea;
    private TypeArea typeCountry;
    private TypeArea typeCity;

    @BeforeEach
    void arrangeArtifacts() {
        typeCountry = new TypeArea("Country");
        typeCity = new TypeArea("City");
        firstValidArea = new GeographicArea("Portugal", typeCountry,
                2, 5, new Local(21, 33, 5));
        secondValidArea = new GeographicArea("Portugal", typeCity,
                2, 5, new Local(21, 33, 5));
    }

    //SHARED METHODS

    @Test
    void seeIfPrintGATypeListWorks() {
        // Arrange

        TypeAreaList list = new TypeAreaList();
        list.addTypeArea(typeCountry);
        list.addTypeArea(typeCity);
        String expectedResult = "---------------\n" +
                "0) Name: Country \n" +
                "1) Name: City \n" +
                "---------------\n";

        // Act

        String actualResult = controller.buildGATypeListString(list);


        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfMatchGAByTypeAreaWorks() {
        // Arrange

        GeographicAreaList geographicAreaList = new GeographicAreaList();
        geographicAreaList.addGeographicArea(firstValidArea);
        geographicAreaList.addGeographicArea(secondValidArea);
        GeographicAreaList expectedResult = new GeographicAreaList();
        expectedResult.addGeographicArea(secondValidArea);

        // Act

        GeographicAreaList actualResult = controller.matchGAByTypeArea(geographicAreaList, typeCity);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetTypeAreaName() {

        // Arrange

        String expectedResult = "City";

        // Act
        String actualResult = controller.getTypeAreaName(typeCity);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    //USER STORY 001 TESTS

    @Test
    void seeIfCreateTypeAreaWorksEmptyList() {

        // Arrange

        TypeAreaList newList = new TypeAreaList();

        // Act

        boolean result = controller.createAndAddTypeAreaToList("City", newList);

        // Assert

        assertTrue(result);
    }

    @Test
    void seeIfCreateTypeAreaWorksListWithElements() {

        // Arrange

        TypeAreaList typeAreaList = new TypeAreaList();
        typeAreaList.addTypeArea(typeCountry);

        // Act

        boolean result = controller.createAndAddTypeAreaToList("City", typeAreaList);

        // Assert

        assertTrue(result);
    }

    @Test
    void seeIfNewTAGDoesntWorkWhenDuplicatedISAdded() {

        // Arrange

        TypeAreaList expectedResult = new TypeAreaList();
        expectedResult.addTypeArea(typeCountry);

        // Act

        boolean result = controller.createAndAddTypeAreaToList("Country", expectedResult);

        // Assert

        assertFalse(result);
    }

    @Test
    void seeIfCreateTypeAreaDoesntWorkWhenNullIsAdded() {

        // Arrange

        TypeAreaList list = new TypeAreaList();
        list.addTypeArea(typeCountry);

        // Act

        boolean result = controller.createAndAddTypeAreaToList(null, list);

        // Assert

        assertFalse(result);
    }

    @Test
    void seeIfCreateTypeAreaDoesntWorkWhenNameIsEmpty() {

        // Arrange


        TypeAreaList list = new TypeAreaList();
        list.addTypeArea(typeCountry);

        // Act

        boolean result = controller.createAndAddTypeAreaToList("", list);

        // Assert

        assertFalse(result);
    }

    @Test
    void seeIfCreateTypeAreaDoesntWorkWhenNumbersAreAdded() {

        // Arrange

        TypeAreaList list = new TypeAreaList();
        list.addTypeArea(typeCountry);

        // Act

        boolean result = controller.createAndAddTypeAreaToList("Country21", list);

        // Assert

        assertFalse(result);
    }


    //USER STORY 002 TESTS

    @Test
    void seeIfPrintTypeAreaListWorks() {

        // Arrange

        TypeAreaList list = new TypeAreaList();
        list.addTypeArea(typeCountry);
        String expectedResult = "---------------\n" +
                "0) Name: Country \n" +
                "---------------\n";

        // Act

        String actualResult = controller.getTypeAreaList(list);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfPrintTypeAreaListWorksWithTwoTypes() {

        // Arrange

        TypeAreaList list = new TypeAreaList();
        list.addTypeArea(typeCountry);
        list.addTypeArea(typeCity);
        String expectedResult = "---------------\n" +
                "0) Name: Country \n" +
                "1) Name: City \n" +
                "---------------\n";

        // Act

        String actualResult = controller.getTypeAreaList(list);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfPrintTypeAreaListWorksWithThreeTypes() {

        // Arrange

        TypeAreaList list = new TypeAreaList();
        list.addTypeArea(typeCity);
        list.addTypeArea(typeCountry);
        String expectedResult = "---------------\n" +
                "0) Name: City \n" +
                "1) Name: Country \n" +
                "---------------\n";

        // Act

        String actualResult = controller.getTypeAreaList(list);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    //USER STORY 003 TESTS

    @Test
    void seeIfCreatesGeographicAreaAndAddsItToList() {

        // Arrange

        GeographicAreaList geoList = new GeographicAreaList();
        double latitude = 38;
        double longitude = 7;
        double altitude = 5;
        double length = 2;
        double width = 4;

        // Act

        boolean result = controller.addNewGeoAreaToList(geoList, "Porto", typeCity, controller.createLocal(latitude, longitude, altitude), length, width);

        // Assert

        assertTrue(result);
        assertEquals(1, geoList.size());
    }

    @Test
    void seeIfCreatingExistingGeographicAreaFails() {

        // Arrange

        String name = "Porto";
        GeographicAreaList geoList = new GeographicAreaList();
        double latitude = 38;
        double longitude = 7;
        double altitude = 5;
        double length = 2;
        double width = 4;
        GeographicArea geoFail = new GeographicArea(name, typeCity, length, width, new Local(latitude, longitude, altitude));

        // Act

        boolean result1 = controller.addNewGeoAreaToList(geoList, name, typeCity, controller.createLocal(latitude, longitude, altitude), length, width);
        boolean result2 = controller.addNewGeoAreaToList(geoList, name, typeCity, controller.createLocal(latitude, longitude, altitude), length, width);
        geoList.addGeographicArea(geoFail);
        boolean result3 = controller.addNewGeoAreaToList(geoList, name, typeCity, controller.createLocal(latitude, longitude, altitude), length, width);

        // Assert

        assertTrue(result1); //safety check (already covered on previous test)
        Assertions.assertFalse(result2);
        assertFalse(result3);
        assertEquals(1, geoList.size());
    }

    @Test
    void seeIfCreatesTwoDifferentGeographicAreas() {

        // Arrange

        GeographicAreaList geoList = new GeographicAreaList();
        String name1 = "Porto";
        double latitude = 38;
        double longitude = 7;
        double altitude = 5;
        double length = 2;
        double width = 4;
        String name2 = "Lisboa";

        // Act

        boolean result1 = controller.addNewGeoAreaToList(geoList, name1, typeCity, controller.createLocal(latitude, longitude, altitude), length, width);
        boolean result2 = controller.addNewGeoAreaToList(geoList, name2, typeCity, controller.createLocal(latitude, longitude, altitude), length, width);

        // Assert

        assertTrue(result1); //safety check (already covered on previous test)
        assertTrue(result2);
        assertEquals(2, geoList.size());
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

        GeographicAreaList actualResult = controller.matchGAByTypeArea(gaL1, typeCountry);

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

        GeographicAreaList actualResult = controller.matchGAByTypeArea(gaL1, typeCity);

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

        GeographicAreaList actualResult = controller.matchGAByTypeArea(gaL1, typeCity);

        //Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getTypeAreaName() {

        //Arrange

        String expectedResult = "City";
        String actualResult;

        //Act
        actualResult = controller.getTypeAreaName(typeCity);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfActivateSensor() {

        //Arrange
        Sensor sensor = new Sensor("SensOne", new TypeSensor("Temperature", "Celsius"), new Date());

        //Act
        boolean actualResult = sensor.activateOrDeactivate();

        //Assert
        assertFalse( actualResult);
    }
    @Test
    void seeIfDeactivateSensor() {

        //Arrange
        Sensor sensor = new Sensor("SensOne", new TypeSensor("Temperature", "Celsius"), new Date());

        //Act
        sensor.setActive();
       boolean actualResult = sensor.activateOrDeactivate();


        //Assert
        assertFalse(actualResult);
    }



    //
    @Test
    void seeIfPrintGAList() {

        //Arrange

        GeographicAreaList gAL1 = new GeographicAreaList();
        gAL1.addGeographicArea(firstValidArea);
        gAL1.addGeographicArea(secondValidArea);
        String expectedResult = "---------------\n" +
                "0) Name: Portugal | Type: Country | Latitude: 21.0 | Longitude: 33.0\n" +
                "1) Name: Portugal | Type: City | Latitude: 21.0 | Longitude: 33.0\n" +
                "---------------\n";

        //Act

        String result = controller.buildGAListString(gAL1);

        //Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfSetMotherAreaWorks() {

        // Arrange

        GeographicAreaList geographicAreaList = new GeographicAreaList();
        geographicAreaList.addGeographicArea(firstValidArea);
        geographicAreaList.addGeographicArea(secondValidArea);

        // Act

        boolean actualResult = controller.setMotherArea(firstValidArea, secondValidArea);

        // Assert

        Assertions.assertTrue(actualResult);
    }

    @Test
    void seeIfSetMotherAreaBreaks() {
        // Arrange

        GeographicAreaList geographicAreaList = new GeographicAreaList();

        // Act

        geographicAreaList.addGeographicArea(firstValidArea);
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

//    @Test
//    void seeIfActivate() {
//        // Arrange
//        Mapper mapper = new Mapper();
//        Date startDate = new Date();
//        Sensor sensor = new Sensor("SensOne", new TypeSensor("Temperature", "Celsius"),startDate );
//        sensor.setLocal(new Local(12,23,23));
//        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//        try {
//            startDate = validSdf.parse("11/01/2018 10:00:00");
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        SensorDTO sensorDTO = mapper.sensorToDTO(sensor);
//
//        // Act
//
//        boolean actualResult = controller.activateOrDeactivateSensor(sensorDTO);
//
//        // Assert
//
//        Assertions.assertTrue(actualResult);
//    }
//
//    @Test
//    void seeIfDeactivate() {
//        // Arrange
//        Mapper mapper = new Mapper();
//        Date startDate = new Date();
//        Sensor sensor = new Sensor("SensOne", new TypeSensor("Temperature", "Celsius"),startDate );
//        sensor.setActive();
//        sensor.setLocal(new Local(12,23,23));
//        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//        try {
//            startDate = validSdf.parse("11/01/2018 10:00:00");
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        SensorDTO sensorDTO = mapper.sensorToDTO(sensor);
//
//
//        // Act
//
//        boolean actualResult = controller.activateOrDeactivateSensor(sensorDTO);
//
//        // Assert
//
//        Assertions.assertFalse(actualResult);
//    }


}