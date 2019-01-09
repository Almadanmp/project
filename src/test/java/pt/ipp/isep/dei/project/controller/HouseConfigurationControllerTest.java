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
     * @Test void seeIfConstructorAddsGeographicAreas() {
     * <p>
     * //Arrange
     * GeographicAreaList geographicAreaList = new GeographicAreaList();
     * TypeArea t1 = new TypeArea("Rua");
     * Local l1 = new Local(38, 7);
     * GeographicArea ga1 = new GeographicArea(t1, l1);
     * TypeArea t2 = new TypeArea("Cidade");
     * Local l2 = new Local(40, 7);
     * GeographicArea ga2 = new GeographicArea(t2, l2);
     * TypeArea t3 = new TypeArea("Rua");
     * Local l3 = new Local(38, 59);
     * GeographicArea ga3 = new GeographicArea(t3, l3);
     * TypeArea t4 = new TypeArea("Montanha");
     * Local l4 = new Local(38, 32);
     * GeographicArea ga4 = new GeographicArea(t4, l4);
     * <p>
     * geographicAreaList.addGeographicAreaToGeographicAreaList(ga1);
     * geographicAreaList.addGeographicAreaToGeographicAreaList(ga2);
     * geographicAreaList.addGeographicAreaToGeographicAreaList(ga3);
     * geographicAreaList.addGeographicAreaToGeographicAreaList(ga4);
     * HouseConfigurationController ctrl = new HouseConfigurationController(geographicAreaList);
     * GeographicAreaList expectedResult = new GeographicAreaList();
     * expectedResult.addGeographicAreaToGeographicAreaList(ga1);
     * expectedResult.addGeographicAreaToGeographicAreaList(ga2);
     * expectedResult.addGeographicAreaToGeographicAreaList(ga3);
     * expectedResult.addGeographicAreaToGeographicAreaList(ga4);
     * <p>
     * //Act
     * GeographicAreaList actualResult = ctrl.getGeographicAreaList();
     * <p>
     * //Assert
     * assertEquals(expectedResult, actualResult);
     * }
     * @Test void seeIfConstructorDoesNotAddGeographicArea() {
     * <p>
     * //Arrange
     * GeographicAreaList geographicAreaList = new GeographicAreaList();
     * TypeArea t1 = new TypeArea("Rua");
     * Local l1 = new Local(38, 7);
     * GeographicArea ga1 = new GeographicArea(t1, l1);
     * TypeArea t2 = new TypeArea("Cidade");
     * Local l2 = new Local(40, 7);
     * GeographicArea ga2 = new GeographicArea(t2, l2);
     * TypeArea t3 = new TypeArea("Rua");
     * Local l3 = new Local(38, 59);
     * GeographicArea ga3 = new GeographicArea(t3, l3);
     * TypeArea t4 = new TypeArea("Montanha");
     * Local l4 = new Local(38, 32);
     * GeographicArea ga4 = new GeographicArea(t4, l4);
     * <p>
     * geographicAreaList.addGeographicAreaToGeographicAreaList(ga1);
     * geographicAreaList.addGeographicAreaToGeographicAreaList(ga2);
     * geographicAreaList.addGeographicAreaToGeographicAreaList(ga4);
     * HouseConfigurationController ctrl = new HouseConfigurationController(geographicAreaList);
     * GeographicAreaList expectedResult = new GeographicAreaList();
     * expectedResult.addGeographicAreaToGeographicAreaList(ga1);
     * expectedResult.addGeographicAreaToGeographicAreaList(ga2);
     * expectedResult.addGeographicAreaToGeographicAreaList(ga4);
     * <p>
     * //Act
     * GeographicAreaList actualResult = ctrl.getGeographicAreaList();
     * <p>
     * //Assert
     * assertEquals(expectedResult, actualResult);
     * }
     * @Test void seeThatWeMatchGeographicAreaListWithGeographicAreasFromTypeGivenInTheBeginning() {
     * <p>
     * //Arrange
     * GeographicAreaList geographicAreaList = new GeographicAreaList();
     * TypeArea t1 = new TypeArea("Rua");
     * Local l1 = new Local(38, 7);
     * GeographicArea ga1 = new GeographicArea(t1, l1);
     * TypeArea t2 = new TypeArea("Cidade");
     * Local l2 = new Local(40, 7);
     * GeographicArea ga2 = new GeographicArea(t2, l2);
     * TypeArea t4 = new TypeArea("Montanha");
     * Local l4 = new Local(38, 32);
     * GeographicArea ga4 = new GeographicArea(t4, l4);
     * <p>
     * geographicAreaList.addGeographicAreaToGeographicAreaList(ga1);
     * geographicAreaList.addGeographicAreaToGeographicAreaList(ga4);
     * geographicAreaList.addGeographicAreaToGeographicAreaList(ga2);
     * HouseConfigurationController ctrl = new HouseConfigurationController(geographicAreaList);
     * GeographicAreaList expectedResult = new GeographicAreaList();
     * expectedResult.addGeographicAreaToGeographicAreaList(ga1);
     * <p>
     * <p>
     * //Act
     * GeographicAreaList actualResult;
     * ctrl.matchGAByTypeArea("Rua");
     * actualResult = ctrl.getGeographicAreaList();
     * <p>
     * //Assert
     * assertEquals(expectedResult, actualResult);
     * }
     * @Test void seeThatWeMatchGeographicAreaListWithGeographicAreasFromTypeGivenInTheMiddle() {
     * <p>
     * //Arrange
     * GeographicAreaList geographicAreaList = new GeographicAreaList();
     * TypeArea t1 = new TypeArea("Rua");
     * Local l1 = new Local(38, 7);
     * GeographicArea ga1 = new GeographicArea(t1, l1);
     * TypeArea t2 = new TypeArea("Cidade");
     * Local l2 = new Local(40, 7);
     * GeographicArea ga2 = new GeographicArea(t2, l2);
     * TypeArea t4 = new TypeArea("Montanha");
     * Local l4 = new Local(38, 32);
     * GeographicArea ga4 = new GeographicArea(t4, l4);
     * <p>
     * geographicAreaList.addGeographicAreaToGeographicAreaList(ga1);
     * geographicAreaList.addGeographicAreaToGeographicAreaList(ga4);
     * geographicAreaList.addGeographicAreaToGeographicAreaList(ga2);
     * HouseConfigurationController ctrl = new HouseConfigurationController(geographicAreaList);
     * GeographicAreaList expectedResult = new GeographicAreaList();
     * expectedResult.addGeographicAreaToGeographicAreaList(ga2);
     * <p>
     * <p>
     * //Act
     * GeographicAreaList actualResult;
     * ctrl.matchGAByTypeArea("Cidade");
     * actualResult = ctrl.getGeographicAreaList();
     * <p>
     * //Assert
     * assertEquals(expectedResult, actualResult);
     * }
     * @Test void seeThatWeMatchGeographicAreaListWithGeographicAreasFromTypeGivenInTheEnd() {
     * <p>
     * //Arrange
     * GeographicAreaList geographicAreaList = new GeographicAreaList();
     * TypeArea t1 = new TypeArea("Rua");
     * Local l1 = new Local(38, 7);
     * GeographicArea ga1 = new GeographicArea(t1, l1);
     * TypeArea t2 = new TypeArea("Cidade");
     * Local l2 = new Local(40, 7);
     * GeographicArea ga2 = new GeographicArea(t2, l2);
     * TypeArea t4 = new TypeArea("Montanha");
     * Local l4 = new Local(38, 32);
     * GeographicArea ga4 = new GeographicArea(t4, l4);
     * <p>
     * geographicAreaList.addGeographicAreaToGeographicAreaList(ga1);
     * geographicAreaList.addGeographicAreaToGeographicAreaList(ga4);
     * geographicAreaList.addGeographicAreaToGeographicAreaList(ga2);
     * HouseConfigurationController ctrl = new HouseConfigurationController(geographicAreaList);
     * GeographicAreaList expectedResult = new GeographicAreaList();
     * expectedResult.addGeographicAreaToGeographicAreaList(ga4);
     * <p>
     * <p>
     * //Act
     * GeographicAreaList actualResult;
     * ctrl.matchGAByTypeArea("Montanha");
     * actualResult = ctrl.getGeographicAreaList();
     * <p>
     * //Assert
     * assertEquals(expectedResult, actualResult);
     * }
     * @Test void seeThatWeDontMatchGeographicAreaListWithGeographicAreasFromTypeGivenBecauseOfUpperCaseDifference() {
     * <p>
     * //Arrange
     * GeographicAreaList geographicAreaList = new GeographicAreaList();
     * TypeArea t1 = new TypeArea("Rua");
     * Local l1 = new Local(38, 7);
     * GeographicArea ga1 = new GeographicArea(t1, l1);
     * TypeArea t2 = new TypeArea("Cidade");
     * Local l2 = new Local(40, 7);
     * GeographicArea ga2 = new GeographicArea(t2, l2);
     * TypeArea t3 = new TypeArea("Rua");
     * Local l3 = new Local(38, 59);
     * GeographicArea ga3 = new GeographicArea(t3, l3);
     * TypeArea t4 = new TypeArea("Montanha");
     * Local l4 = new Local(38, 32);
     * GeographicArea ga4 = new GeographicArea(t4, l4);
     * <p>
     * geographicAreaList.addGeographicAreaToGeographicAreaList(ga1);
     * geographicAreaList.addGeographicAreaToGeographicAreaList(ga4);
     * geographicAreaList.addGeographicAreaToGeographicAreaList(ga2);
     * geographicAreaList.addGeographicAreaToGeographicAreaList(ga3);
     * HouseConfigurationController ctrl = new HouseConfigurationController(geographicAreaList);
     * GeographicAreaList expectedResult = new GeographicAreaList();
     * expectedResult.addGeographicAreaToGeographicAreaList(ga1);
     * expectedResult.addGeographicAreaToGeographicAreaList(ga3);
     * <p>
     * //Act
     * GeographicAreaList actualResult;
     * ctrl.matchGAByTypeArea("rua");
     * actualResult = ctrl.getGeographicAreaList();
     * <p>
     * //Assert
     * assertNotEquals(expectedResult, actualResult);
     * }
     * @Test void seeThatWeDontMatchGeographicAreaListWithGeographicAreasFromTypeGiven() {
     * <p>
     * //Arrange
     * GeographicAreaList geographicAreaList = new GeographicAreaList();
     * TypeArea t1 = new TypeArea("Rua");
     * Local l1 = new Local(38, 7);
     * GeographicArea ga1 = new GeographicArea(t1, l1);
     * TypeArea t2 = new TypeArea("Cidade");
     * Local l2 = new Local(40, 7);
     * GeographicArea ga2 = new GeographicArea(t2, l2);
     * TypeArea t3 = new TypeArea("Rua");
     * Local l3 = new Local(38, 59);
     * GeographicArea ga3 = new GeographicArea(t3, l3);
     * TypeArea t4 = new TypeArea("Montanha");
     * Local l4 = new Local(38, 32);
     * GeographicArea ga4 = new GeographicArea(t4, l4);
     * <p>
     * geographicAreaList.addGeographicAreaToGeographicAreaList(ga1);
     * geographicAreaList.addGeographicAreaToGeographicAreaList(ga4);
     * geographicAreaList.addGeographicAreaToGeographicAreaList(ga2);
     * geographicAreaList.addGeographicAreaToGeographicAreaList(ga3);
     * HouseConfigurationController ctrl = new HouseConfigurationController(geographicAreaList);
     * GeographicAreaList expectedResult = new GeographicAreaList();
     * <p>
     * //Act
     * GeographicAreaList actualResult;
     * ctrl.matchGAByTypeArea("Pais");
     * actualResult = ctrl.getGeographicAreaList();
     * <p>
     * //Assert
     * assertEquals(expectedResult, actualResult);
     * }
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
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5),ga, roomList);
        Room room = new Room("Quarto", 1, 20,2,2);
        EnergyGrid energyGrid1 = new EnergyGrid("EG1", 400);
        EnergyGrid energyGrid2 = new EnergyGrid("EG2", 200);
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
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5),ga, roomList);
        Room room = new Room("Quarto", 1, 20,2,2);
        EnergyGrid energyGrid1 = new EnergyGrid("EG1", 400);
        EnergyGrid energyGrid2 = new EnergyGrid("EG2", 200);
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
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5),ga, roomList);
        Room room = new Room("Quarto", 1, 20,2,2);
        EnergyGrid energyGrid1 = new EnergyGrid("EG1", 400);
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
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5),ga, roomList);
        Room room = new Room("Quarto", 1, 20,2,2);
        EnergyGrid energyGrid1 = new EnergyGrid("EG1", 400);
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
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5),ga, roomList);
        Room room = new Room("Quarto", 1, 20,2,2);
        EnergyGrid energyGrid1 = new EnergyGrid("EG1", 400);
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
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5),ga, roomList);
        Room room = new Room("Quarto", 1, 20,2,2);
        EnergyGrid energyGrid1 = new EnergyGrid("EG1", 400);
        EnergyGrid energyGrid2 = new EnergyGrid("EG2", 200);
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
