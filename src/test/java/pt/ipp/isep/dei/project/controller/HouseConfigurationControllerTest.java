package pt.ipp.isep.dei.project.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testng.Assert;
import pt.ipp.isep.dei.project.model.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

class HouseConfigurationControllerTest {

    //USER STORY 001 TESTS

    @Test
    void seeIfnewTAGWorks() {
        TypeAreaList newList = new TypeAreaList();
        HouseConfigurationController ctrl = new HouseConfigurationController(newList);
        boolean result = ctrl.createAndAddTypeAreaToList("cidade");
        assertTrue(result);
    }

    @Test
    void seeIfnewTAGWorksWithAnother() {
        TypeArea tipo = new TypeArea("rua");
        TypeAreaList newList = new TypeAreaList();
        newList.addTypeArea(tipo);
        HouseConfigurationController ctrl = new HouseConfigurationController(newList);
        boolean result = ctrl.createAndAddTypeAreaToList("cidade");
        assertTrue(result);
    }

    @Test
    void seeIfnewTAGDoesntWorkWhenDuplicatedISAdded() {
        TypeArea tipo = new TypeArea("cidade");
        TypeAreaList expectedResult = new TypeAreaList();
        expectedResult.addTypeArea(tipo);
        HouseConfigurationController ctrl = new HouseConfigurationController(expectedResult);
        boolean result = ctrl.createAndAddTypeAreaToList("cidade");
        assertFalse(result);
    }

    @Test
    void seeIfNewTAGDoesntWorkWhenNullIsAdded() {
        TypeArea tipo = new TypeArea("cidade");
        TypeAreaList list = new TypeAreaList();
        list.addTypeArea(tipo);
        HouseConfigurationController ctrl = new HouseConfigurationController(list);
        boolean result = ctrl.createAndAddTypeAreaToList(null);
        assertFalse(result);
    }

    @Test
    void seeIfNewTAGDoesntWorkWhenNameIsEmpty() {
        TypeArea tipo = new TypeArea("cidade");
        TypeAreaList list = new TypeAreaList();
        list.addTypeArea(tipo);
        HouseConfigurationController ctrl = new HouseConfigurationController(list);
        boolean result = ctrl.createAndAddTypeAreaToList("");
        assertFalse(result);
    }

    @Test
    void seeIfNewTAGDoesntWorkWhenNumbersAreAdded() {
        TypeArea tipo = new TypeArea("cidade");
        TypeAreaList list = new TypeAreaList();
        list.addTypeArea(tipo);
        HouseConfigurationController ctrl = new HouseConfigurationController(list);
        boolean result = ctrl.createAndAddTypeAreaToList("cidade1");
        assertFalse(result);
    }

    //USER STORY 002 TESTS

    @Test
    void seeIfPrintTypeAreaListWorks() {
        TypeAreaList list = new TypeAreaList();
        TypeArea t1 = new TypeArea("Rua");
        list.addTypeArea(t1);
        HouseConfigurationController ctrl = new HouseConfigurationController(list);
        String actualResult = ctrl.getTypeAreaList();
        String expectedResult = "\n" +
                "Area Types List:\n" +
                "\n" +
                "-Rua;";
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfPrintTypeAreaListWorksWithTwoTypes() {
        TypeAreaList list = new TypeAreaList();
        TypeArea t1 = new TypeArea("Rua");
        TypeArea t2 = new TypeArea("Cidade");
        list.addTypeArea(t1);
        list.addTypeArea(t2);
        HouseConfigurationController ctrl = new HouseConfigurationController(list);
        String actualResult = ctrl.getTypeAreaList();
        String expectedResult = "\n" +
                "Area Types List:\n" +
                "\n" +
                "-Rua;\n" +
                "-Cidade;";
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfPrintTypeAreaListWorksWithThreeTypes() {
        TypeAreaList list = new TypeAreaList();
        TypeArea t1 = new TypeArea("Rua");
        TypeArea t2 = new TypeArea("Cidade");
        TypeArea t3 = new TypeArea("Viela");
        list.addTypeArea(t1);
        list.addTypeArea(t2);
        list.addTypeArea(t3);
        HouseConfigurationController ctrl = new HouseConfigurationController(list);
        String actualResult = ctrl.getTypeAreaList();
        String expectedResult = "\n" +
                "Area Types List:\n" +
                "\n" +
                "-Rua;\n" +
                "-Cidade;\n" +
                "-Viela;";
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfPrintTypeAreaListWorksWithEmptyList() {
        TypeAreaList list = new TypeAreaList();
        HouseConfigurationController ctrl = new HouseConfigurationController(list);
        String actualResult = ctrl.getTypeAreaList();
        String expectedResult = "\n" +
                "Area Types List:\n" +
                "\n" +
                "|||| List is Empty ||||\n" +
                "Add types to list first";
        assertEquals(expectedResult, actualResult);
    }

    //USER STORY 003 TESTS

    @Test
    void seeIfCreatesGeographicAreaAndAddsItToList() {
        GeographicAreaList geoList = new GeographicAreaList();
        String name = "Porto";
        String typeArea = "Distrito";
        double latitude = 38;
        double longitude = 7;
        HouseConfigurationController us3 = new HouseConfigurationController();
        boolean result = us3.addNewGeoAreaToList(geoList, name, typeArea, latitude, longitude);

        assertTrue(result);
        assertEquals(1, geoList.getGeographicAreaList().size());
    }

    @Test
    void seeIfFailsCreatingSecondEqualGeographicArea() {
        GeographicAreaList geoList = new GeographicAreaList();
        String name = "Porto";
        String typeArea = "Distrito";
        double latitude = 38;
        double longitude = 7;

        HouseConfigurationController us3 = new HouseConfigurationController();
        boolean result1 = us3.addNewGeoAreaToList(geoList, name, typeArea, latitude, longitude);
        boolean result2 = us3.addNewGeoAreaToList(geoList, name, typeArea, latitude, longitude);

        assertTrue(result1); //safety check (already covered on previous test)
        Assertions.assertFalse(result2);
        assertEquals(1, geoList.getGeographicAreaList().size());
    }

    @Test
    void seeIfCreatesTwoDifferentGeographicAreas() {
        GeographicAreaList geoList = new GeographicAreaList();
        String name1 = "Porto";
        String typeArea = "Distrito";
        double latitude = 38;
        double longitude = 7;
        String name2 = "Lisboa";

        HouseConfigurationController us3 = new HouseConfigurationController();
        boolean result1 = us3.addNewGeoAreaToList(geoList, name1, typeArea, latitude, longitude);
        boolean result2 = us3.addNewGeoAreaToList(geoList, name2, typeArea, latitude, longitude);

        assertTrue(result1); //safety check (already covered on previous test)
        assertTrue(result2);
        assertEquals(2, geoList.getGeographicAreaList().size());
    }

    @Test
    void seeIfFailsWithNullInputGeoList() {
        String name1 = "Porto";
        String typeArea = "Distrito";
        double latitude = 38;
        double longitude = 7;

        HouseConfigurationController us3 = new HouseConfigurationController();
        boolean result = us3.addNewGeoAreaToList(null, name1, typeArea, latitude, longitude);

        Assertions.assertFalse(result);
    }

    //USER STORY 004 TESTS
/**
    @Test
    void seeIfConstructorAddsGeographicAreas() {

        //Arrange
        GeographicAreaList geographicAreaList = new GeographicAreaList();
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7);
        GeographicArea ga1 = new GeographicArea(t1, l1);
        TypeArea t2 = new TypeArea("Cidade");
        Local l2 = new Local(40, 7);
        GeographicArea ga2 = new GeographicArea(t2, l2);
        TypeArea t3 = new TypeArea("Rua");
        Local l3 = new Local(38, 59);
        GeographicArea ga3 = new GeographicArea(t3, l3);
        TypeArea t4 = new TypeArea("Montanha");
        Local l4 = new Local(38, 32);
        GeographicArea ga4 = new GeographicArea(t4, l4);

        geographicAreaList.addGeographicAreaToGeographicAreaList(ga1);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga2);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga3);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga4);
        HouseConfigurationController ctrl = new HouseConfigurationController(geographicAreaList);
        GeographicAreaList expectedResult = new GeographicAreaList();
        expectedResult.addGeographicAreaToGeographicAreaList(ga1);
        expectedResult.addGeographicAreaToGeographicAreaList(ga2);
        expectedResult.addGeographicAreaToGeographicAreaList(ga3);
        expectedResult.addGeographicAreaToGeographicAreaList(ga4);

        //Act
        GeographicAreaList actualResult = ctrl.getGeographicAreaList();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfConstructorDoesNotAddGeographicArea() {

        //Arrange
        GeographicAreaList geographicAreaList = new GeographicAreaList();
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7);
        GeographicArea ga1 = new GeographicArea(t1, l1);
        TypeArea t2 = new TypeArea("Cidade");
        Local l2 = new Local(40, 7);
        GeographicArea ga2 = new GeographicArea(t2, l2);
        TypeArea t3 = new TypeArea("Rua");
        Local l3 = new Local(38, 59);
        GeographicArea ga3 = new GeographicArea(t3, l3);
        TypeArea t4 = new TypeArea("Montanha");
        Local l4 = new Local(38, 32);
        GeographicArea ga4 = new GeographicArea(t4, l4);

        geographicAreaList.addGeographicAreaToGeographicAreaList(ga1);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga2);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga4);
        HouseConfigurationController ctrl = new HouseConfigurationController(geographicAreaList);
        GeographicAreaList expectedResult = new GeographicAreaList();
        expectedResult.addGeographicAreaToGeographicAreaList(ga1);
        expectedResult.addGeographicAreaToGeographicAreaList(ga2);
        expectedResult.addGeographicAreaToGeographicAreaList(ga4);

        //Act
        GeographicAreaList actualResult = ctrl.getGeographicAreaList();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeThatWeMatchGeographicAreaListWithGeographicAreasFromTypeGivenInTheBeginning() {

        //Arrange
        GeographicAreaList geographicAreaList = new GeographicAreaList();
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7);
        GeographicArea ga1 = new GeographicArea(t1, l1);
        TypeArea t2 = new TypeArea("Cidade");
        Local l2 = new Local(40, 7);
        GeographicArea ga2 = new GeographicArea(t2, l2);
        TypeArea t4 = new TypeArea("Montanha");
        Local l4 = new Local(38, 32);
        GeographicArea ga4 = new GeographicArea(t4, l4);

        geographicAreaList.addGeographicAreaToGeographicAreaList(ga1);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga4);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga2);
        HouseConfigurationController ctrl = new HouseConfigurationController(geographicAreaList);
        GeographicAreaList expectedResult = new GeographicAreaList();
        expectedResult.addGeographicAreaToGeographicAreaList(ga1);


        //Act
        GeographicAreaList actualResult;
        ctrl.matchGAByTypeArea("Rua");
        actualResult = ctrl.getGeographicAreaList();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeThatWeMatchGeographicAreaListWithGeographicAreasFromTypeGivenInTheMiddle() {

        //Arrange
        GeographicAreaList geographicAreaList = new GeographicAreaList();
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7);
        GeographicArea ga1 = new GeographicArea(t1, l1);
        TypeArea t2 = new TypeArea("Cidade");
        Local l2 = new Local(40, 7);
        GeographicArea ga2 = new GeographicArea(t2, l2);
        TypeArea t4 = new TypeArea("Montanha");
        Local l4 = new Local(38, 32);
        GeographicArea ga4 = new GeographicArea(t4, l4);

        geographicAreaList.addGeographicAreaToGeographicAreaList(ga1);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga4);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga2);
        HouseConfigurationController ctrl = new HouseConfigurationController(geographicAreaList);
        GeographicAreaList expectedResult = new GeographicAreaList();
        expectedResult.addGeographicAreaToGeographicAreaList(ga2);


        //Act
        GeographicAreaList actualResult;
        ctrl.matchGAByTypeArea("Cidade");
        actualResult = ctrl.getGeographicAreaList();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeThatWeMatchGeographicAreaListWithGeographicAreasFromTypeGivenInTheEnd() {

        //Arrange
        GeographicAreaList geographicAreaList = new GeographicAreaList();
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7);
        GeographicArea ga1 = new GeographicArea(t1, l1);
        TypeArea t2 = new TypeArea("Cidade");
        Local l2 = new Local(40, 7);
        GeographicArea ga2 = new GeographicArea(t2, l2);
        TypeArea t4 = new TypeArea("Montanha");
        Local l4 = new Local(38, 32);
        GeographicArea ga4 = new GeographicArea(t4, l4);

        geographicAreaList.addGeographicAreaToGeographicAreaList(ga1);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga4);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga2);
        HouseConfigurationController ctrl = new HouseConfigurationController(geographicAreaList);
        GeographicAreaList expectedResult = new GeographicAreaList();
        expectedResult.addGeographicAreaToGeographicAreaList(ga4);


        //Act
        GeographicAreaList actualResult;
        ctrl.matchGAByTypeArea("Montanha");
        actualResult = ctrl.getGeographicAreaList();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeThatWeDontMatchGeographicAreaListWithGeographicAreasFromTypeGivenBecauseOfUpperCaseDifference() {

        //Arrange
        GeographicAreaList geographicAreaList = new GeographicAreaList();
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7);
        GeographicArea ga1 = new GeographicArea(t1, l1);
        TypeArea t2 = new TypeArea("Cidade");
        Local l2 = new Local(40, 7);
        GeographicArea ga2 = new GeographicArea(t2, l2);
        TypeArea t3 = new TypeArea("Rua");
        Local l3 = new Local(38, 59);
        GeographicArea ga3 = new GeographicArea(t3, l3);
        TypeArea t4 = new TypeArea("Montanha");
        Local l4 = new Local(38, 32);
        GeographicArea ga4 = new GeographicArea(t4, l4);

        geographicAreaList.addGeographicAreaToGeographicAreaList(ga1);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga4);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga2);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga3);
        HouseConfigurationController ctrl = new HouseConfigurationController(geographicAreaList);
        GeographicAreaList expectedResult = new GeographicAreaList();
        expectedResult.addGeographicAreaToGeographicAreaList(ga1);
        expectedResult.addGeographicAreaToGeographicAreaList(ga3);

        //Act
        GeographicAreaList actualResult;
        ctrl.matchGAByTypeArea("rua");
        actualResult = ctrl.getGeographicAreaList();

        //Assert
        assertNotEquals(expectedResult, actualResult);
    }

    @Test
    void seeThatWeDontMatchGeographicAreaListWithGeographicAreasFromTypeGiven() {

        //Arrange
        GeographicAreaList geographicAreaList = new GeographicAreaList();
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7);
        GeographicArea ga1 = new GeographicArea(t1, l1);
        TypeArea t2 = new TypeArea("Cidade");
        Local l2 = new Local(40, 7);
        GeographicArea ga2 = new GeographicArea(t2, l2);
        TypeArea t3 = new TypeArea("Rua");
        Local l3 = new Local(38, 59);
        GeographicArea ga3 = new GeographicArea(t3, l3);
        TypeArea t4 = new TypeArea("Montanha");
        Local l4 = new Local(38, 32);
        GeographicArea ga4 = new GeographicArea(t4, l4);

        geographicAreaList.addGeographicAreaToGeographicAreaList(ga1);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga4);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga2);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga3);
        HouseConfigurationController ctrl = new HouseConfigurationController(geographicAreaList);
        GeographicAreaList expectedResult = new GeographicAreaList();

        //Act
        GeographicAreaList actualResult;
        ctrl.matchGAByTypeArea("Pais");
        actualResult = ctrl.getGeographicAreaList();

        //Assert
        assertEquals(expectedResult, actualResult);
    }
**/
    //USER STORY 005 TESTS

    @Test
    void seeIfConstructorWorks() {
        //Arrange
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 10, 4).getTime());
        Sensor s2 = new Sensor("Chuva", new TypeSensor("Atmosphere"),
                new Local(10, 30, 20),
                new GregorianCalendar(118, 1, 4).getTime());
        SensorList lc = new SensorList(new Sensor[]{s1, s2});
        SensorList expectedResult = new SensorList();


        //Act
        expectedResult.addSensor(s1);
        expectedResult.addSensor(s2);
        HouseConfigurationController constructedList = new HouseConfigurationController(lc);
        SensorList actualResult = constructedList.getSensorList();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSetTypeWorksFalse() {
        //Arrange
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 10, 4).getTime());
        Sensor s2 = new Sensor("Chuva", new TypeSensor("Atmosphere"),
                new Local(10, 30, 20),
                new GregorianCalendar(118, 1, 4).getTime());
        SensorList lc = new SensorList();
        lc.addSensor(s1);
        lc.addSensor(s2);
        boolean expectedResult = false;
        HouseConfigurationController ctrl = new HouseConfigurationController(lc);

        //Act
        boolean actualResult = ctrl.setTypeSensor("Portugal", "Movement");

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSetTypeWorks() {
        //Arrange
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 10, 4).getTime());
        Sensor s2 = new Sensor("Chuva", new TypeSensor("Atmosphere"),
                new Local(10, 30, 20),
                new GregorianCalendar(118, 1, 4).getTime());
        SensorList lc = new SensorList();
        lc.addSensor(s1);
        lc.addSensor(s2);
        boolean expectedResult = true;
        HouseConfigurationController ctrl = new HouseConfigurationController(lc);

        //Act
        boolean actualResult = ctrl.setTypeSensor("Vento", "Movement");

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    //USER STORY 006 TESTS

    @Test
     void seeIfLocalIsCreated() {

        //Arrange
        HouseConfigurationController ctrl = new HouseConfigurationController();
        double lat = 50.0;
        double lon = 50.0;
        double alt = 50.0;
        ctrl.createLocal(lat, lon, alt);
        Local expectedResult = new Local(50, 50, 50);

        //Act
        Local actualResult = ctrl.getLocal();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfLocalIsCreated2() {

        //Arrange
        HouseConfigurationController ctrl = new HouseConfigurationController();
        double lat = 500.0;
        double lon = 500.0;
        double alt = 500.0;
        ctrl.createLocal(lat, lon, alt);
        Local expectedResult = new Local(500, 500, 500);

        //Act
        Local actualResult = ctrl.getLocal();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfLocalIsCreated3() {

        //Arrange
        HouseConfigurationController ctrl = new HouseConfigurationController();
        double lat = -50.0;
        double lon = -50.0;
        double alt = -50.0;
        ctrl.createLocal(lat, lon, alt);
        Local expectedResult = new Local(-50, -50, -50);

        //Act
        Local actualResult = ctrl.getLocal();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfTypeIsCreated() {

        //Arrange
        HouseConfigurationController ctrl = new HouseConfigurationController();
        String typeString = "Humedade";
        ctrl.createType(typeString);
        TypeSensor expectedResult = new TypeSensor("Humedade");

        //Act
        TypeSensor actualResult = ctrl.getType();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfTypeIsCreated2() {

        //Arrange
        HouseConfigurationController ctrl = new HouseConfigurationController();
        String typeString = "Humedade";
        ctrl.createType(typeString);
        TypeSensor expectedResult = new TypeSensor(typeString);

        //Act
        TypeSensor actualResult = ctrl.getType();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfDateIsCreated() {

        //Arrange
        HouseConfigurationController ctrl = new HouseConfigurationController();
        int year = 1989;
        int month = 7;
        int day = 12;
        ctrl.createDate(year, month, day);
        Date expectedResult = new GregorianCalendar(1989, 7, 12).getTime();

        //Act
        Date actualResult = ctrl.getDate();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSensorIsCreated() {

        //Arrange
        HouseConfigurationController ctrl = new HouseConfigurationController();
        String nameString = "XV-56D";
        double lat = 50.0;
        double lon = 50.0;
        double alt = 50.0;
        Local loc1 = ctrl.createLocal(lat, lon, alt);
        String typeStr = "Humedade";
        TypeSensor type1 = ctrl.createType(typeStr);
        int year = 2018;
        int month = 8;
        int day = 9;
        Date date1 = ctrl.createDate(year, month, day);
        ctrl.createSensor(nameString, type1, loc1, date1);
        TypeSensor t1 = new TypeSensor("Humedade");
        Sensor expectedResult = new Sensor("XV-56D", t1, loc1, new GregorianCalendar(2018, 8, 9).getTime());

        //Act
        Sensor actualResult = ctrl.createSensor(nameString, type1, loc1, date1);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSensorIsCreatedAndGetSensor() {

        //Arrange
        HouseConfigurationController ctrl = new HouseConfigurationController();
        String nameString = "XV-56D";
        double lat = 50.0;
        double lon = 50.0;
        double alt = 50.0;
        Local loc1 = ctrl.createLocal(lat, lon, alt);
        String typeStr = "Humedade";
        TypeSensor type1 = ctrl.createType(typeStr);
        int year = 2018;
        int month = 8;
        int day = 9;
        Date date1 = ctrl.createDate(year, month, day);
        ctrl.createSensor(nameString, type1, loc1, date1);
        TypeSensor t1 = new TypeSensor("Humedade");
        Sensor expectedResult = new Sensor("XV-56D", t1, loc1, new GregorianCalendar(2018, 8, 9).getTime());

        //Act
        Sensor actualResult = ctrl.getSensor();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSensorIsAddedToSensorList() {

        //Arrange
        HouseConfigurationController ctrl = new HouseConfigurationController();
        String nameString = "XV-56D";
        double lat = 50.0;
        double lon = 50.0;
        double alt = 50.0;
        Local loc1 = ctrl.createLocal(lat, lon, alt);
        String typeStr = "Humedade";
        TypeSensor type1 = ctrl.createType(typeStr);
        int year = 2018;
        int month = 8;
        int day = 9;
        Date date1 = ctrl.createDate(year, month, day);
        Sensor sens1 = ctrl.createSensor(nameString, type1, loc1, date1);
        SensorList xSensorList = new SensorList();

        //Act
        boolean actualResult = ctrl.addSensor(sens1, xSensorList);

        //Assert
        assertTrue(actualResult);
    }

    @Test
    void seeIfSensorIsNotAddedToSensorList() {

        //Arrange
        HouseConfigurationController ctrl = new HouseConfigurationController();
        String nameString = "XV-56D";
        double lat = 50.0;
        double lon = 50.0;
        double alt = 50.0;
        Local loc1 = ctrl.createLocal(lat, lon, alt);
        String typeStr = "Humedade";
        TypeSensor type1 = ctrl.createType(typeStr);
        int year = 2018;
        int month = 8;
        int day = 9;
        Date date1 = ctrl.createDate(year, month, day);
        Sensor sens1 = ctrl.createSensor(nameString, type1, loc1, date1);
        SensorList xSensorList = new SensorList();
        xSensorList.addSensor(sens1);

        //Act
        boolean actualResult = ctrl.addSensor(sens1, xSensorList);

        //Assert
        assertFalse(actualResult);
    }

    @Test
    void seeIfSensorListIsAddedToGeographicArea() {

        //Arrange
        HouseConfigurationController ctrl = new HouseConfigurationController();
        String nameString = "XV-56D";
        double lat = 50.0;
        double lon = 50.0;
        double alt = 50.0;
        Local loc1 = ctrl.createLocal(lat, lon, alt);
        String typeStr = "Humedade";
        TypeSensor type1 = ctrl.createType(typeStr);
        int year = 2018;
        int month = 8;
        int day = 9;
        Date date1 = ctrl.createDate(year, month, day);
        Sensor sens1 = ctrl.createSensor(nameString, type1, loc1, date1);
        SensorList xSensorList = new SensorList();
        xSensorList.addSensor(sens1);
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7);
        GeographicArea areaG = new GeographicArea(t1, l1);
        areaG.setName("Alegria");

        String areaNameInput = "Alegria";
        GeographicAreaList xgaList = new GeographicAreaList();
        xgaList.addGeographicAreaToGeographicAreaList(areaG);

        //Act
        boolean actualResult = ctrl.addSensorToGeographicArea(areaNameInput, xgaList, xSensorList);

        //Assert
        assertTrue(actualResult);
    }

    @Test
    void seeIfSensorListIsAddedToGeographicAreaFalse() {

        //Arrange
        HouseConfigurationController ctrl = new HouseConfigurationController();
        String nameString = "XV-56D";
        double lat = 50.0;
        double lon = 50.0;
        double alt = 50.0;
        Local loc1 = ctrl.createLocal(lat, lon, alt);
        String typeStr = "Humedade";
        TypeSensor type1 = ctrl.createType(typeStr);
        int year = 2018;
        int month = 8;
        int day = 9;
        Date date1 = ctrl.createDate(year, month, day);
        Sensor sens1 = ctrl.createSensor(nameString, type1, loc1, date1);
        SensorList xSensorList = new SensorList();
        xSensorList.addSensor(sens1);
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7);
        GeographicArea areaG = new GeographicArea(t1, l1);
        areaG.setName("Alegria");

        String areaNameInput = "Direita";
        GeographicAreaList xgaList = new GeographicAreaList();
        xgaList.addGeographicAreaToGeographicAreaList(areaG);

        //Act
        boolean actualResult = ctrl.addSensorToGeographicArea(areaNameInput, xgaList, xSensorList);

        //Assert
        assertFalse(actualResult);
    }

    @Test
    void seeIfSensorListIsAddedToGeographicAreaEmptyList() {

        //Arrange
        HouseConfigurationController ctrl = new HouseConfigurationController();
        String nameString = "XV-56D";
        double lat = 50.0;
        double lon = 50.0;
        double alt = 50.0;
        Local loc1 = ctrl.createLocal(lat, lon, alt);
        String typeStr = "Humedade";
        TypeSensor type1 = ctrl.createType(typeStr);
        int year = 2018;
        int month = 8;
        int day = 9;
        Date date1 = ctrl.createDate(year, month, day);
        Sensor sens1 = ctrl.createSensor(nameString, type1, loc1, date1);
        SensorList xSensorList = new SensorList();
        xSensorList.addSensor(sens1);
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7);
        GeographicArea areaG = new GeographicArea(t1, l1);
        areaG.setName("Alegria");

        String areaNameInput = "Alegria";
        GeographicAreaList xgaList = new GeographicAreaList();

        //Act
        boolean actualResult = ctrl.addSensorToGeographicArea(areaNameInput, xgaList, xSensorList);

        //Assert
        assertFalse(actualResult);
    }

    //USER STORY 007 TESTS

    @Test
    void seeIfMatchGeoArea() {
        GeographicAreaList geographicAreaList = new GeographicAreaList();
        GeographicArea ga1 = new GeographicArea("Porto");
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga1);
        HouseConfigurationController ctrl = new HouseConfigurationController(geographicAreaList);

        GeographicArea actualResult = ctrl.matchGeoArea("Porto");

        assertEquals(ga1, actualResult);
    }

    @Test
    void seeIfMatchGeoAreaNull() {
        GeographicAreaList geographicAreaList = new GeographicAreaList();
        GeographicArea ga1 = new GeographicArea("Gaia");
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga1);
        HouseConfigurationController ctrl = new HouseConfigurationController(geographicAreaList);

        GeographicArea actualResult = ctrl.matchGeoArea("Porto");

        assertNull(actualResult);
    }

    @Test
    void seeIfSetMotherArea() {
        GeographicAreaList geographicAreaList = new GeographicAreaList();
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7);
        GeographicArea ga1 = new GeographicArea("Cedofeita", t1, l1);
        GeographicArea ga2 = new GeographicArea("Porto", t1, l1);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga1);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga2);
        HouseConfigurationController ctrl = new HouseConfigurationController(geographicAreaList);

        ctrl.setMotherArea(ga1, ga2);

        GeographicArea result = ctrl.getMotherArea();

        assertEquals(ga2, result);

    }

    @Test
    void seeIfPrintGeographicAreaList() {
        GeographicAreaList geographicAreaList = new GeographicAreaList();
        GeographicArea ga1 = new GeographicArea("Porto");
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga1);
        HouseConfigurationController ctrl = new HouseConfigurationController(geographicAreaList);

        String actualResult = ctrl.printGeographicAreaListNames();

        assertEquals("Geographic Area List: \n" +
                "-Porto;", actualResult);
    }

    @Test
    void seeIfValidateGeoAreaTrue() {
        GeographicAreaList geographicAreaList = new GeographicAreaList();
        GeographicArea ga1 = new GeographicArea("Porto");
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga1);
        HouseConfigurationController ctrl = new HouseConfigurationController(geographicAreaList);
        boolean actualResult = ctrl.validateGeoArea("Porto");

        assertTrue(actualResult);


    }

    @Test
    void seeIfValidateGeoAreaFalse() {
        GeographicAreaList geographicAreaList = new GeographicAreaList();
        GeographicArea ga1 = new GeographicArea("Porto");
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga1);
        HouseConfigurationController ctrl = new HouseConfigurationController(geographicAreaList);
        boolean actualResult = ctrl.validateGeoArea("Gaia");

        assertFalse(actualResult);


    }

    @Test
    void seeIfGetGeographicAreaList() {
        GeographicAreaList geographicAreaList = new GeographicAreaList();

        TypeArea t1 = new TypeArea("Cidade");
        Local l1 = new Local(38, 7);
        String n1 = "Porto";
        GeographicArea ga1 = new GeographicArea(t1, l1);
        ga1.setName(n1);

        Local l2 = new Local(39, 67);
        String n2 = "Braga";
        GeographicArea ga2 = new GeographicArea(t1, l2);
        ga2.setName(n2);

        geographicAreaList.addGeographicAreaToGeographicAreaList(ga1);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga2);
        HouseConfigurationController ctrl = new HouseConfigurationController(geographicAreaList);
        GeographicAreaList actualResult = ctrl.getGeographicAreaList();
        assertEquals(geographicAreaList, actualResult);
    }

    //USER STORY 008 TESTS

    @Test
    void seeIfAreaContainedMatchToAreasOfList() {
        GeographicAreaList list = new GeographicAreaList();
        GeographicArea area1 = new GeographicArea("Porto", new TypeArea("Cidade"), new Local(45, 45));
        GeographicArea area2 = new GeographicArea("Portugal", new TypeArea("Pais"), new Local(45, 45));
        GeographicArea area3 = new GeographicArea("Europa", new TypeArea("Continente"), new Local(45, 45));
        list.addGeographicAreaToGeographicAreaList(area1);
        list.addGeographicAreaToGeographicAreaList(area2);
        list.addGeographicAreaToGeographicAreaList(area3);
        HouseConfigurationController ctrl = new HouseConfigurationController(list);

        ctrl.matchGeographicAreas("Porto", "Europa");
        GeographicArea expectedResult = area1;
        GeographicArea actualResult = ctrl.getmGeographicAreaContained();

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfAreaContainerMatchToAreasOfList() {
        GeographicAreaList list = new GeographicAreaList();
        GeographicArea area1 = new GeographicArea("Porto", new TypeArea("Cidade"), new Local(45, 45));
        GeographicArea area2 = new GeographicArea("Portugal", new TypeArea("Pais"), new Local(45, 45));
        GeographicArea area3 = new GeographicArea("Europa", new TypeArea("Continente"), new Local(45, 45));
        list.addGeographicAreaToGeographicAreaList(area1);
        list.addGeographicAreaToGeographicAreaList(area2);
        list.addGeographicAreaToGeographicAreaList(area3);
        HouseConfigurationController ctrl = new HouseConfigurationController(list);

        ctrl.matchGeographicAreas("Porto", "Europa");
        GeographicArea expectedResult = area3;
        GeographicArea actualResult = ctrl.getmGeographicAreaContainer();

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfAreaContainedMatchToAreasOfListFails() {
        GeographicAreaList list = new GeographicAreaList();
        GeographicArea area1 = new GeographicArea("Porto", new TypeArea("Cidade"), new Local(45, 45));
        GeographicArea area2 = new GeographicArea("Portugal", new TypeArea("Pais"), new Local(45, 45));
        GeographicArea area3 = new GeographicArea("Europa", new TypeArea("Continente"), new Local(45, 45));
        list.addGeographicAreaToGeographicAreaList(area1);
        list.addGeographicAreaToGeographicAreaList(area2);
        list.addGeographicAreaToGeographicAreaList(area3);
        HouseConfigurationController ctrl = new HouseConfigurationController(list);

        boolean expectedResult = false;
        boolean actualResult = ctrl.matchGeographicAreas("Lisboa", "Europa");

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfAreaContainedInAnotherAreaWorks() {
        GeographicAreaList list = new GeographicAreaList();
        GeographicArea area1 = new GeographicArea("Porto", new TypeArea("Cidade"), new Local(45, 45));
        GeographicArea area2 = new GeographicArea("Portugal", new TypeArea("Pais"), new Local(45, 45));
        GeographicArea area3 = new GeographicArea("Europa", new TypeArea("Continente"), new Local(45, 45));
        list.addGeographicAreaToGeographicAreaList(area1);
        list.addGeographicAreaToGeographicAreaList(area2);
        list.addGeographicAreaToGeographicAreaList(area3);
        HouseConfigurationController ctrl = new HouseConfigurationController(list);

        area1.setMotherArea(area2);
        area2.setMotherArea(area3);
        ctrl.matchGeographicAreas("Porto", "Europa");
        boolean actualResult = ctrl.seeIfItsContained();
        boolean expectedResult = true;

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfAreaContainedInAnotherAreaFailsWithContainerNotFilled() {
        GeographicAreaList list = new GeographicAreaList();
        GeographicArea area1 = new GeographicArea("Porto", new TypeArea("Cidade"), new Local(45, 45));
        GeographicArea area2 = new GeographicArea("Portugal", new TypeArea("Pais"), new Local(45, 45));
        GeographicArea area3 = new GeographicArea("Europa", new TypeArea("Continente"), new Local(45, 45));
        list.addGeographicAreaToGeographicAreaList(area1);
        list.addGeographicAreaToGeographicAreaList(area2);
        list.addGeographicAreaToGeographicAreaList(area3);
        HouseConfigurationController ctrl = new HouseConfigurationController(list);

        area1.setMotherArea(area2);
        area2.setMotherArea(area3);
        ctrl.matchGeographicAreas("Porto", "Polonia");
        boolean actualResult = ctrl.seeIfItsContained();
        boolean expectedResult = false;

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfAreaContainedInAnotherAreaFails() {
        GeographicAreaList list = new GeographicAreaList();
        GeographicArea area1 = new GeographicArea("Porto", new TypeArea("Cidade"), new Local(45, 45));
        GeographicArea area2 = new GeographicArea("Portugal", new TypeArea("Pais"), new Local(45, 45));
        GeographicArea area3 = new GeographicArea("Europa", new TypeArea("Continente"), new Local(45, 45));
        list.addGeographicAreaToGeographicAreaList(area1);
        list.addGeographicAreaToGeographicAreaList(area2);
        list.addGeographicAreaToGeographicAreaList(area3);
        HouseConfigurationController ctrl = new HouseConfigurationController(list);

        area1.setMotherArea(area2);
        ctrl.matchGeographicAreas("Porto", "Europa");
        boolean actualResult = ctrl.seeIfItsContained();
        boolean expectedResult = false;

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfListIsInvalid() {
        GeographicAreaList list = new GeographicAreaList();
        HouseConfigurationController ctrl = new HouseConfigurationController(list);
        boolean actualResult = ctrl.matchGeographicAreas("Porto", "Europa");
        boolean expectedResult = false;
        Assertions.assertEquals(expectedResult, actualResult);
    }

    //USER STORY 101 TESTS

    @Test
    void seeIfConstructorWorksUS101() {
        //Arrange

        GeographicAreaList geographicAreaList = new GeographicAreaList();
        GeographicArea ga1 = new GeographicArea();
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga1);
        GeographicAreaList expectedResult = new GeographicAreaList(ga1);

        //Act
        HouseConfigurationController controller = new HouseConfigurationController(geographicAreaList);
        GeographicAreaList actualResult = controller.getGeoList();

        //Assert
        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfSetHouseAddress() {

        HouseConfigurationController ctrl = new HouseConfigurationController();
        String address = "rua da rua 345";
        String zipCode = "4450";
        double latitude = 38;
        double longitude = 7;
        Local local = new Local(latitude, longitude);
        House house = new House(address, local, zipCode);
        HouseList houseList = new HouseList(house);
        houseList.addHouseToHouseList(house);
        ctrl.setHouseList(houseList);
        ctrl.setHouseAddress("rua da rua", house);
        String result = houseList.getHouseList().get(0).getmAddress();
        assertEquals("rua da rua", result);
    }


    @Test
    void seeIfSetHouseZipCode() {
        HouseConfigurationController ctrl = new HouseConfigurationController();
        String address = "rua da rua 345";
        String zipCode = "4450";
        double latitude = 38;
        double longitude = 7;
        Local local = new Local(latitude, longitude);
        House house = new House(address, local, zipCode);
        HouseList houseList = new HouseList(house);
        houseList.addHouseToHouseList(house);
        ctrl.setHouseList(houseList);
        ctrl.setHouseZIPCode("6789", house);
        String result = houseList.getHouseList().get(0).getmZipCode();
        assertEquals("6789", result);


    }

    @Test
    void seeIfSetHouseGPS() {
        HouseConfigurationController ctrl = new HouseConfigurationController();
        String address = "rua da rua 345";
        String zipCode = "4450";
        double latitude = 38;
        double longitude = 7;
        Local local = new Local(latitude, longitude);
        House house = new House(address, local, zipCode);
        HouseList houseList = new HouseList(house);
        houseList.addHouseToHouseList(house);
        ctrl.setHouseList(houseList);
        ctrl.setHouseLocal(39, 8, house);
        Local result = houseList.getHouseList().get(0).getmGPS();
        Local localExpected = new Local(39, 8);
        assertEquals(localExpected, result);
    }

    @Test
    void seeIfPrintGAList() {
        //Arrange
        GeographicArea gA1 = new GeographicArea("Portugal", new TypeArea("Country"), new Local(21, 33));
        GeographicArea gA2 = new GeographicArea("Oporto", new TypeArea("City"), new Local(14, 14));
        GeographicArea gA3 = new GeographicArea("Lisbon", new TypeArea("Village"), new Local(3, 3));
        GeographicAreaList gAL1 = new GeographicAreaList();
        gAL1.addGeographicAreaToGeographicAreaList(gA1);
        gAL1.addGeographicAreaToGeographicAreaList(gA2);
        gAL1.addGeographicAreaToGeographicAreaList(gA3);

        //Act
        String expectedResult = "---------------\n" +
                "0) Name: Portugal | Type: Country | Latitude: 21.0 | Longitude: 33.0\n" +
                "1) Name: Oporto | Type: City | Latitude: 14.0 | Longitude: 14.0\n" +
                "2) Name: Lisbon | Type: Village | Latitude: 3.0 | Longitude: 3.0\n" +
                "---------------\n";
        HouseConfigurationController ctrl = new HouseConfigurationController(gAL1);
        String result = ctrl.printGAList(gAL1);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGeographicAreaIndexMatchByString() {
        //Arrange
        HouseConfigurationController ctrl = new HouseConfigurationController();
        GeographicAreaList mGeographicAreaList = new GeographicAreaList();
        GeographicArea gA1 = new GeographicArea("porto", new TypeArea("cidade"), new Local(4, 4));
        GeographicArea gA2 = new GeographicArea("lisboa", new TypeArea("aldeia"), new Local(4, 4));
        mGeographicAreaList.addGeographicAreaToGeographicAreaList(gA1);
        mGeographicAreaList.addGeographicAreaToGeographicAreaList(gA2);
        //Act
        List<Integer> result = ctrl.matchGeographicAreaIndexByString("lisboa", mGeographicAreaList);
        List<Integer> expectedResult = Collections.singletonList(mGeographicAreaList.getGeographicAreaList().indexOf(gA2));
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfPrintsHouseList() {
        //Assert
        HouseConfigurationController ctrl = new HouseConfigurationController();
        House house1 = new House("vacationHouse", "Flower Street", new Local(11, 13), "4230-111");
        House house2 = new House("workHouse", "Torrinha", new Local(12, 12), "4345-000");
        House house3 = new House("dreamHouse", "New York", new Local(122, 122), "6666-000");
        HouseList hL1 = new HouseList(house1);
        hL1.addHouseToHouseList(house2);
        hL1.addHouseToHouseList(house3);
        GeographicArea gA1 = new GeographicArea();
        gA1.setHouseList(hL1);

        //Act
        String expectedResult = "---------------\n" +
                "0) Designation: vacationHouse | Address: Flower Street | ZipCode: 4230-111\n" +
                "1) Designation: workHouse | Address: Torrinha | ZipCode: 4345-000\n" +
                "2) Designation: dreamHouse | Address: New York | ZipCode: 6666-000\n" +
                "---------------\n";
        String result = ctrl.printHouseList(gA1);

        //Assert
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    void seeIfPrintsHouseListIfEmpty() {
        //Arrange
        HouseConfigurationController ctrl = new HouseConfigurationController();
        HouseList hL1 = new HouseList();
        GeographicArea gA1 = new GeographicArea();
        gA1.setHouseList(hL1);

        //Act
        String expectedResult = "Invalid List - List is Empty\n";
        String result = ctrl.printHouseList(gA1);

        //Assert
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    void seeIfPrintGeoGraphicAreaElementsByIndex() {
        //Arrange
        HouseConfigurationController ctrl = new HouseConfigurationController();
        List<Integer> list = new ArrayList<>();
        Integer i = 1;
        list.add(i);
        GeographicAreaList mGeographicAreaList = new GeographicAreaList();
        GeographicArea geoa1 = new GeographicArea("porto", new TypeArea("cidade"), new Local(4, 4));
        GeographicArea geoa2 = new GeographicArea("lisboa", new TypeArea("aldeia"), new Local(4, 4));
        mGeographicAreaList.addGeographicAreaToGeographicAreaList(geoa1);
        mGeographicAreaList.addGeographicAreaToGeographicAreaList(geoa2);

        //Act
        String result = ctrl.printGeoGraphicAreaElementsByIndex(list, mGeographicAreaList);
        String expectedResult = "---------------\n" +
                "1) lisboa, aldeia, 4.0º lat, 4.0º long\n" +
                "---------------\n";

        //Assert
        assertEquals(expectedResult, result);
    }

//USER STORY 105


    // USER STORY 108
    @Test
    void seeIfMatchGeographicAreaIndexByStringWorks() {
        //Arrange -------------------------------------
        HouseConfigurationController ctrl = new HouseConfigurationController();
        //Geo Area List
        GeographicAreaList mGeographicAreaList = new GeographicAreaList();
        GeographicArea geoa1 = new GeographicArea("porto", new TypeArea("cidade"), new Local(4, 4));
        GeographicArea geoa2 = new GeographicArea("lisboa", new TypeArea("aldeia"), new Local(4, 4));
        mGeographicAreaList.addGeographicAreaToGeographicAreaList(geoa1);
        mGeographicAreaList.addGeographicAreaToGeographicAreaList(geoa2);
        //Act -----------------------------------------
        List<Integer> result = ctrl.matchGeographicAreaIndexByString("lisboa", mGeographicAreaList);
        List<Integer> expectedResult = Collections.singletonList(mGeographicAreaList.getGeographicAreaList().indexOf(geoa2));
        //Assert --------------------------------------
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfPrintGeoGraphicAreaElementsByIndex2() {
        //Arrange -----------------------------------------
        HouseConfigurationController ctrl = new HouseConfigurationController();
        //Geo Area List
        GeographicAreaList mGeographicAreaList = new GeographicAreaList();
        GeographicArea geoa1 = new GeographicArea("porto", new TypeArea("cidade"), new Local(4, 4));
        GeographicArea geoa2 = new GeographicArea("lisboa", new TypeArea("aldeia"), new Local(4, 4));
        mGeographicAreaList.addGeographicAreaToGeographicAreaList(geoa1);
        mGeographicAreaList.addGeographicAreaToGeographicAreaList(geoa2);
        //Act ---------------------------------------------
        List<Integer> list = new ArrayList<>();
        Integer i = 1;
        list.add(i);
        String result = ctrl.printGeoGraphicAreaElementsByIndex(list, mGeographicAreaList);
        String expectedResult = "---------------\n" +
                "1) lisboa, aldeia, 4.0º lat, 4.0º long\n" +
                "---------------\n";
        //Assert ------------------------------------------
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void seeIfmatchHouseIndexByString() {
        //Arrange -------------------------------
        HouseConfigurationController ctrl = new HouseConfigurationController();
        //Geo Area List
        GeographicAreaList mGeographicAreaList = new GeographicAreaList();
        GeographicArea geoa1 = new GeographicArea("porto", new TypeArea("cidade"), new Local(4, 4));
        mGeographicAreaList.addGeographicAreaToGeographicAreaList(geoa1);
        //House List
        HouseList houseList1 = new HouseList();
        House house2 = new House("houseTwo", "Address2", new Local(4, 4), "3456-123");
        House house3 = new House("house3", "Address3", new Local(18, 10), "3555-555");
        geoa1.setHouseList(houseList1);
        houseList1.addHouseToHouseList(house2);
        houseList1.addHouseToHouseList(house3);
        //Act -----------------------------------
        List<Integer> result = ctrl.matchHouseIndexByString("house3", geoa1);
        List<Integer> expectedResult = new ArrayList<>();
        Integer i = 1;
        expectedResult.add(i);
        //Assert --------------------------------
        Assertions.assertEquals(expectedResult, result);

    }

    @Test
    public void seeIfPrintHouseWorks() {
        //Arrange ----------------------
        HouseConfigurationController ctrl = new HouseConfigurationController();
        //Geo Area List
        GeographicAreaList mGeographicAreaList = new GeographicAreaList();
        GeographicArea geoa1 = new GeographicArea("porto", new TypeArea("cidade"), new Local(4, 4));
        mGeographicAreaList.addGeographicAreaToGeographicAreaList(geoa1);
        //House List
        HouseList houseList1 = new HouseList();
        House house1 = new House("a minha rica casinha", "Address2", new Local(4, 4), "3456-123");
        geoa1.setHouseList(houseList1);
        houseList1.addHouseToHouseList(house1);
        //Act --------------------------
        String result = ctrl.printHouse(geoa1.getHouseList().getHouseList().get(0));
        String expectedResult = "a minha rica casinha, Address2, 3456-123.\n";
        //Assert -----------------------
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfMatchRoomIndexByString() {
        //Arrange -----------------------------
        HouseConfigurationController ctrl = new HouseConfigurationController();
        //Geo Area List
        GeographicAreaList mGeographicAreaList = new GeographicAreaList();
        GeographicArea geoa1 = new GeographicArea("porto", new TypeArea("cidade"), new Local(4, 4));
        mGeographicAreaList.addGeographicAreaToGeographicAreaList(geoa1);
        //House List
        HouseList houseList1 = new HouseList();
        House house1 = new House("house2", "Address2", new Local(4, 4), "3456-123");
        geoa1.setHouseList(houseList1);
        houseList1.addHouseToHouseList(house1);
        //Room List
        RoomList roomList1 = new RoomList();
        house1.setmRoomList(roomList1);
        Room room1 = new Room("room1", 19, 23456789,2,2);
        Room room2 = new Room("kitchen", 8, 2,2,2);
        roomList1.addRoom(room1);
        roomList1.addRoom(room2);
        //Act ---------------------------------
        List<Integer> result = ctrl.matchRoomIndexByString("kitchen", house1);
        List<Integer> expectedResult = new ArrayList<>();
        Integer i = 1;
        expectedResult.add(i);
        //Assert ------------------------------
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfPrintRoomElementsByIndex() {
        //Arrange --------------------------------
        HouseConfigurationController ctrl = new HouseConfigurationController();
        //Geo Area List
        GeographicAreaList mGeographicAreaList = new GeographicAreaList();
        GeographicArea geoa1 = new GeographicArea("porto", new TypeArea("cidade"), new Local(4, 4));
        mGeographicAreaList.addGeographicAreaToGeographicAreaList(geoa1);
        //House List
        HouseList houseList1 = new HouseList();
        House house1 = new House("house2", "Address2", new Local(4, 4), "3456-123");
        geoa1.setHouseList(houseList1);
        houseList1.addHouseToHouseList(house1);
        //Room List
        RoomList roomList1;
        roomList1 = new RoomList();
        house1.setmRoomList(roomList1);
        Room room1 = new Room("room1", 19, 23456789,2,2);
        Room room2 = new Room("kitchen", 8, 2,2,2);
        roomList1.addRoom(room1);
        roomList1.addRoom(room2);
        //Act ------------------------------------
        List<Integer> list = new ArrayList<>();
        Integer i = 1;
        list.add(i);
        String result = ctrl.printRoomElementsByIndex(list, house1);
        String expectedResult = "1) kitchen, 8, 2.0, 2.0, 2.0.\n";
        //Assert ---------------------------------
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfPrintRoomListWork() {
        //Arrange ------------------------
        HouseConfigurationController ctrl = new HouseConfigurationController();
        //Geo Area List
        GeographicAreaList mGeographicAreaList = new GeographicAreaList();
        GeographicArea geoa1 = new GeographicArea("porto", new TypeArea("cidade"), new Local(4, 4));
        mGeographicAreaList.addGeographicAreaToGeographicAreaList(geoa1);
        //House List
        HouseList houseList1 = new HouseList();
        House house1 = new House("a minha rica casinha", "Address2", new Local(4, 4), "3456-123");
        geoa1.setHouseList(houseList1);
        houseList1.addHouseToHouseList(house1);
        //Room List
        RoomList roomList1 = new RoomList();
        house1.setmRoomList(roomList1);
        Room room1 = new Room("room1", 19, 23456789,2,2);
        Room room2 = new Room("kitchen", 8, 2,2,2);
        roomList1.addRoom(room1);
        roomList1.addRoom(room2);
        //Act ----------------------------
        String result = ctrl.printRoomList(house1);
        String expectedResult = "---------------\n" +
                "0) Designation: room1 | House Floor: 19 | Width: 2.3456789E7 | Length: 2.0 | Height: 2.0\n" +
                "1) Designation: kitchen | House Floor: 8 | Width: 2.0 | Length: 2.0 | Height: 2.0\n" +
                "---------------\n";
        //Assert -------------------------
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfPrintRoomWorks() {
        //Arrange ---------------------
        HouseConfigurationController ctrl = new HouseConfigurationController();
        //Geo Area List
        GeographicAreaList mGeographicAreaList = new GeographicAreaList();
        GeographicArea geoa1 = new GeographicArea("porto", new TypeArea("cidade"), new Local(4, 4));
        mGeographicAreaList.addGeographicAreaToGeographicAreaList(geoa1);
        //House List
        HouseList houseList1 = new HouseList();
        House house1 = new House("a minha rica casinha", "Address2", new Local(4, 4), "3456-123");
        geoa1.setHouseList(houseList1);
        houseList1.addHouseToHouseList(house1);
        //Room List
        RoomList roomList1 = new RoomList();
        house1.setmRoomList(roomList1);
        Room room1 = new Room("room1", 19, 23456789,2,2);
        Room room2 = new Room("kitchen", 8, 2,2,2);
        roomList1.addRoom(room1);
        roomList1.addRoom(room2);
        //Act -------------------------
        String result = ctrl.printRoom(room2);
        String expectedResult = "kitchen, 8, 2.0, 2.0, 2.0.\n";
        //Assert ----------------------
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void seeIfSetRoomName() {

        HouseConfigurationController ctrl = new HouseConfigurationController();
        String name = "rua da rua 345";
        int roomFloor = 3;
        Room room = new Room(name, roomFloor, 8,8,2);
        RoomList roomList = new RoomList();
        roomList.addRoom(room);
        roomList.getListOfRooms().get(0);
        ctrl.setRoomList(roomList);
        ctrl.editRoom(room, "nome", 2, 34,2,2);
        String result = roomList.getListOfRooms().get(0).getRoomName();

        assertEquals("nome", result);
    }

    @Test
    void seeIfSetRoomHouseFloor() {

        HouseConfigurationController ctrl = new HouseConfigurationController();
        String name = "rua da rua 345";
        int roomFloor = 3;
        Room room = new Room(name, roomFloor, 8,8,2);
        RoomList roomList = new RoomList();
        roomList.addRoom(room);
        roomList.getListOfRooms().get(0);
        ctrl.setRoomList(roomList);
        ctrl.editRoom(room, "nome", 2, 34,2,2);
        int result = roomList.getListOfRooms().get(0).getHouseFloor();

        assertEquals(2, result);
    }

    @Test
    void seeIfSetRoomWidth() {

        HouseConfigurationController ctrl = new HouseConfigurationController();
        String name = "rua da rua 345";
        int roomFloor = 3;
        Room room = new Room(name, roomFloor, 8,8,2);
        RoomList roomList = new RoomList();
        roomList.addRoom(room);
        roomList.getListOfRooms().get(0);
        ctrl.setRoomList(roomList);
        ctrl.editRoom(room, "nome", 2, 34,2,2);
        double result = roomList.getListOfRooms().get(0).getRoomWidth();

        assertEquals(34, result);
    }

    //USER STORY 130 TESTS

    @Test
    void seeIfHouExistsInListWorks() {
        HouseList list = new HouseList();
        House house = new House("Casa Oliveira", "Santa Maria de Lamas", new Local(42, 50), "4535");
        list.addHouseToHouseList(house);
        HouseConfigurationController ctrl130 = new HouseConfigurationController(list);
        String houseName = "Casa Oliveira";
        boolean result = ctrl130.seeIfHouseExistsInHouseList(houseName);
        assertTrue(result);
    }

    @Test
    void seeIfHouExistsInListFails() {
        HouseList list = new HouseList();
        House house = new House("Casa Oliveira", "Santa Maria de Lamas", new Local(42, 50), "4535");
        list.addHouseToHouseList(house);
        HouseConfigurationController ctrl130 = new HouseConfigurationController(list);
        String houseName = "Casa Marques";
        boolean result = ctrl130.seeIfHouseExistsInHouseList(houseName);
        assertTrue(!result);
    }

    @Test
    void seeIfEnergyGridIsAddedToAnHouse() {
        HouseList list = new HouseList();
        EnergyGridList listEG = new EnergyGridList();
        EnergyGrid energyGrid = new EnergyGrid("grid", 400);
        House house = new House("Casa Oliveira", "Santa Maria de Lamas", new Local(42, 50), "4535");
        listEG.addEnergyGridToEnergyGridList(energyGrid);
        house.setmEGList(listEG);
        list.addHouseToHouseList(house);
        HouseConfigurationController ctrl130 = new HouseConfigurationController(list);
        ctrl130.seeIfHouseExistsInHouseList("Casa Oliveira");
        boolean result = ctrl130.addEnergyGridToHouse();
        assertTrue(result);
    }

    @Test
    void seeIfEnergyGridIsCreated() {
        HouseList list = new HouseList();
        HouseConfigurationController ctrl130 = new HouseConfigurationController(list);
        ctrl130.createEnergyGrid("EG", 400);
        EnergyGrid expectedResult = new EnergyGrid("EG", 400);
        EnergyGrid result = ctrl130.getEnergyGrid();
        assertEquals(expectedResult, result);
    }

    //USER STORY 135

    @Test
    void seeIfPowerSourceIsCreated() {
        HouseList list = new HouseList();
        House house = new House("Casa Oliveira", "Santa Maria de Lamas", new Local(42, 50), "4535");
        list.addHouseToHouseList(house);
        HouseConfigurationController ctrl135 = new HouseConfigurationController(list);
        ctrl135.createPowerSource("Gerador", 400, 200);
        PowerSource result = ctrl135.getPowerSource();
        PowerSource expectedResult = new PowerSource("Gerador", 400, 200);
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfEnergyGridListIsSelected() {
        HouseList list = new HouseList();
        House house = new House("Casa Oliveira", "Santa Maria de Lamas", new Local(42, 50), "4535");
        EnergyGridList listEG = new EnergyGridList();
        EnergyGrid energyGrid = new EnergyGrid("EG1", 400);
        EnergyGrid energyGrid2 = new EnergyGrid("EG2", 300);
        listEG.addEnergyGridToEnergyGridList(energyGrid);
        listEG.addEnergyGridToEnergyGridList(energyGrid2);
        house.setmEGList(listEG);
        list.addHouseToHouseList(house);
        HouseConfigurationController ctrl135 = new HouseConfigurationController(list);
        ctrl135.seeIfHouseExistsInHouseList("Casa Oliveira");
        ctrl135.selectEnergyGrid("EG1");
        EnergyGrid result = ctrl135.getEnergyGrid();
        EnergyGrid expectedResult = new EnergyGrid("EG1", 400);
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfEnergyGridListIsSelectedFails() {
        HouseList list = new HouseList();
        House house = new House("Casa Oliveira", "Santa Maria de Lamas", new Local(42, 50), "4535");
        EnergyGridList listEG = new EnergyGridList();
        EnergyGrid energyGrid = new EnergyGrid("EG1", 400);
        EnergyGrid energyGrid2 = new EnergyGrid("EG2", 300);
        listEG.addEnergyGridToEnergyGridList(energyGrid);
        listEG.addEnergyGridToEnergyGridList(energyGrid2);
        house.setmEGList(listEG);
        list.addHouseToHouseList(house);
        HouseConfigurationController ctrl135 = new HouseConfigurationController(list);
        ctrl135.seeIfHouseExistsInHouseList("Casa Oliveira");
        ctrl135.selectEnergyGrid("EG3");
        EnergyGrid result = ctrl135.getEnergyGrid();
        assertNull(result);
    }

    @Test
    void seeIfPowerSourceIsAddedToEnergyGrid() {
        HouseList list = new HouseList();
        House house = new House("Casa Oliveira", "Santa Maria de Lamas", new Local(42, 50), "4535");
        EnergyGridList listEG = new EnergyGridList();
        EnergyGrid energyGrid = new EnergyGrid("EG1", 400);
        PowerSourceList powerSourceList = new PowerSourceList();
        energyGrid.setListPowerSources(powerSourceList);
        listEG.addEnergyGridToEnergyGridList(energyGrid);
        house.setmEGList(listEG);
        list.addHouseToHouseList(house);
        HouseConfigurationController ctrl135 = new HouseConfigurationController(list);
        PowerSource powerSource = new PowerSource("Gerador", 400, 200);
        ctrl135.seeIfHouseExistsInHouseList("Casa Oliveira");
        ctrl135.selectEnergyGrid("EG1");
        ctrl135.createPowerSource("Gerador", 400, 200);
        ctrl135.addPowerSourceToEnergyGrid();
        boolean result = ctrl135.getEnergyGrid().getmListPowerSources().containsPowerSource(powerSource);
        assertTrue(result);
    }

    @Test
    void seeIfPowerSourceIsAddedToEnergyGridFails() {
        HouseList list = new HouseList();
        House house = new House("Casa Oliveira", "Santa Maria de Lamas", new Local(42, 50), "4535");
        EnergyGridList listEG = new EnergyGridList();
        EnergyGrid energyGrid = new EnergyGrid("EG1", 400);
        PowerSourceList powerSourceList = new PowerSourceList();
        energyGrid.setListPowerSources(powerSourceList);
        listEG.addEnergyGridToEnergyGridList(energyGrid);
        house.setmEGList(listEG);
        list.addHouseToHouseList(house);
        HouseConfigurationController ctrl135 = new HouseConfigurationController(list);
        PowerSource powerSource = new PowerSource("Painel Solar", 200, 200);
        ctrl135.seeIfHouseExistsInHouseList("Casa Oliveira");
        ctrl135.selectEnergyGrid("EG1");
        ctrl135.createPowerSource("Gerador", 400, 200);
        ctrl135.addPowerSourceToEnergyGrid();
        boolean result = ctrl135.getEnergyGrid().getmListPowerSources().containsPowerSource(powerSource);
        assertTrue(!result);
    }

    @Test
    void seeIfEnergyGridListIsDisplayed() {
        HouseList list = new HouseList();
        House house = new House("Casa Oliveira", "Santa Maria de Lamas", new Local(42, 50), "4535");
        EnergyGridList listEG = new EnergyGridList();
        EnergyGrid energyGrid = new EnergyGrid("EG1", 400);
        PowerSourceList powerSourceList = new PowerSourceList();
        energyGrid.setListPowerSources(powerSourceList);
        listEG.addEnergyGridToEnergyGridList(energyGrid);
        house.setmEGList(listEG);
        list.addHouseToHouseList(house);
        HouseConfigurationController ctrl135 = new HouseConfigurationController(list);
        ctrl135.seeIfHouseExistsInHouseList("Casa Oliveira");
        String result = ctrl135.seeIfEnergyGridListIsEmptyAndShowItsContent();
        String expectedResult = "Energy grid list: \n" + "-EG1;";
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfEnergyGridListIsEmpty() {
        HouseList list = new HouseList();
        House house = new House("Casa Oliveira", "Santa Maria de Lamas", new Local(42, 50), "4535");
        EnergyGridList listEG = new EnergyGridList();
        house.setmEGList(listEG);
        list.addHouseToHouseList(house);
        HouseConfigurationController ctrl135 = new HouseConfigurationController(list);
        ctrl135.seeIfHouseExistsInHouseList("Casa Oliveira");
        String result = ctrl135.seeIfEnergyGridListIsEmptyAndShowItsContent();
        String expectedResult = "The list is empty.";
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfPrintsGeoA() {
        HouseConfigurationController US101 = new HouseConfigurationController();
        GeographicArea gA1 = new GeographicArea("Portugal", new TypeArea("Country"), new Local(21, 33));
        GeographicArea gA2 = new GeographicArea("Oporto", new TypeArea("City"), new Local(14, 14));
        GeographicArea gA3 = new GeographicArea("Lisbon", new TypeArea("Village"), new Local(3, 3));
        GeographicAreaList gAL1 = new GeographicAreaList();
        gAL1.addGeographicAreaToGeographicAreaList(gA1);
        gAL1.addGeographicAreaToGeographicAreaList(gA2);
        gAL1.addGeographicAreaToGeographicAreaList(gA3);
        String expectedResult =
                "Portugal, Country, 21.0º lat, 33.0º long\n";
        String result = US101.printGA(gA1);
        assertEquals(expectedResult, result);
    }

    //USER STORY 145

    @Test
    void seeIfIndexIsMatchedByString() {
        House house = new House("Casa", new Local(45, 50), "4535");
        Room room = new Room("Quarto", 1, 20,2,2);
        EnergyGrid energyGrid1 = new EnergyGrid("EG1", 400);
        EnergyGrid energyGrid2 = new EnergyGrid("EG2", 200);
        RoomList roomList = new RoomList();
        EnergyGridList energyGridList = new EnergyGridList();
        energyGridList.addEnergyGridToEnergyGridList(energyGrid1);
        energyGridList.addEnergyGridToEnergyGridList(energyGrid2);
        roomList.addRoom(room);
        house.setmRoomList(roomList);
        house.setmEGList(energyGridList);
        HouseConfigurationController ctrlUS145 = new HouseConfigurationController(room);
        List<Integer> result = ctrlUS145.matchGridIndexByString("EG2", house);
        List<Integer> expectedResult = new ArrayList<>();
        expectedResult.add(1);
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfEnergyGridIsPrintedByIndex() {
        House house = new House("Casa", new Local(45, 50), "4535");
        Room room = new Room("Quarto", 1, 20,2,2);
        EnergyGrid energyGrid1 = new EnergyGrid("EG1", 400);
        EnergyGrid energyGrid2 = new EnergyGrid("EG2", 200);
        RoomList roomList = new RoomList();
        EnergyGridList energyGridList = new EnergyGridList();
        energyGridList.addEnergyGridToEnergyGridList(energyGrid1);
        energyGridList.addEnergyGridToEnergyGridList(energyGrid2);
        roomList.addRoom(room);
        house.setmRoomList(roomList);
        house.setmEGList(energyGridList);
        HouseConfigurationController ctrlUS145 = new HouseConfigurationController(energyGridList);
        List<Integer> list = ctrlUS145.matchGridIndexByString("EG1", house);
        String actualResult = ctrlUS145.printEnergyGridByIndex(list);
        String expectedResult = "0) EG1, 400.0, null.\n";
        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfEnergyGridPrints() {
        House house = new House("Casa", new Local(45, 50), "4535");
        Room room = new Room("Quarto", 1, 20,2,2);
        EnergyGrid energyGrid1 = new EnergyGrid("EG1", 400);
        RoomList roomList = new RoomList();
        EnergyGridList energyGridList = new EnergyGridList();
        energyGridList.addEnergyGridToEnergyGridList(energyGrid1);
        roomList.addRoom(room);
        house.setmRoomList(roomList);
        house.setmEGList(energyGridList);
        HouseConfigurationController ctrlUS145 = new HouseConfigurationController(room);
        String result = ctrlUS145.printEnergyGrid(energyGrid1);
        String expectedResult = "Energy Grid: EG1, Max Power: 400.0";
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGridListPrints() {
        House house = new House("Casa", new Local(45, 50), "4535");
        Room room = new Room("Quarto", 1, 20,2,2);
        EnergyGrid energyGrid1 = new EnergyGrid("EG1", 400);
        RoomList roomList = new RoomList();
        EnergyGridList energyGridList = new EnergyGridList();
        energyGridList.addEnergyGridToEnergyGridList(energyGrid1);
        roomList.addRoom(room);
        house.setmRoomList(roomList);
        house.setmEGList(energyGridList);
        HouseConfigurationController ctrlUS145 = new HouseConfigurationController(room);
        String result = ctrlUS145.printGridList(house);
        String expectedResult = "---------------\n" +
                "0) Designation: EG1 | Max Power: 400.0\n" +
                "---------------\n";
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfRoomsPrint() {
        House house = new House("Casa", new Local(45, 50), "4535");
        Room room = new Room("Quarto", 1, 20,2,2);
        EnergyGrid energyGrid1 = new EnergyGrid("EG1", 400);
        RoomList roomList = new RoomList();
        EnergyGridList energyGridList = new EnergyGridList();
        energyGridList.addEnergyGridToEnergyGridList(energyGrid1);
        roomList.addRoom(room);
        house.setmRoomList(roomList);
        house.setmEGList(energyGridList);
        HouseConfigurationController ctrlUS145 = new HouseConfigurationController(room);
        String result = ctrlUS145.printRooms(roomList);
        String expectedResult = "---------------\n" +
                "0) Designation: Quarto | House Floor: 1 | Width: 20.0 | Length: 2.0 | Height: 2.0\n" +
                "---------------\n";
        assertEquals(expectedResult, result);
    }

    //USER STORY 149

    @Test
    void seeIfRoomIsRemovedFromGrid() {
        House house = new House("Casa", new Local(45, 50), "4535");
        Room room = new Room("Quarto", 1, 20,2,2);
        EnergyGrid energyGrid1 = new EnergyGrid("EG1", 400);
        EnergyGrid energyGrid2 = new EnergyGrid("EG2", 200);
        RoomList roomList = new RoomList();
        EnergyGridList energyGridList = new EnergyGridList();
        energyGridList.addEnergyGridToEnergyGridList(energyGrid1);
        energyGridList.addEnergyGridToEnergyGridList(energyGrid2);
        roomList.addRoom(room);
        energyGrid1.setListOfRooms(roomList);
        house.setmEGList(energyGridList);
        HouseConfigurationController ctrlUS145 = new HouseConfigurationController(room);
        boolean result = ctrlUS145.removeRoomFromGrid(energyGrid1, room);
        assertTrue(result);
    }
}
