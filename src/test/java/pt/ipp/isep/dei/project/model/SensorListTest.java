package pt.ipp.isep.dei.project.model;

import org.junit.jupiter.api.Test;
import org.testng.Assert;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


public class SensorListTest {

    @Test
    public void seeIfConstructorWorks() {
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 10, 4).getTime());
        Sensor s2 = new Sensor("Chuva", new TypeSensor("Atmosphere", "l/m2"),
                new Local(10, 30, 20),
                new GregorianCalendar(118, 1, 4).getTime());
        Sensor[] expectedResult = new Sensor[]{s1, s2};
        Sensor[] result;
        SensorList lc = new SensorList(new Sensor[]{s1, s2});
        result = lc.getSensors();
        assertArrayEquals(result, expectedResult);
    }

    @Test
    public void seeIfConstructorWorksNoSensor() {
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 10, 4).getTime());
        Sensor s2 = new Sensor("Chuva", new TypeSensor("Atmosphere", "l/m2"),
                new Local(10, 30, 20),
                new GregorianCalendar(118, 1, 4).getTime());
        Sensor[] expectedResult = new Sensor[]{};
        Sensor[] result;
        SensorList lc = new SensorList(new Sensor[]{});
        result = lc.getSensors();
        assertArrayEquals(result, expectedResult);
    }

    @Test
    public void seeIfAddSensorsWorks_true() {
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 9, 4).getTime());
        Sensor s2 = new Sensor("Chuva", new TypeSensor("Atmosphere", "l/m2"),
                new Local(10, 30, 20),
                new GregorianCalendar(118, 10, 4).getTime());
        SensorList sl = new SensorList(s1);
        sl.addSensor(s2);
        boolean expectedResult = true;

        boolean actualResult = sl.containsSensor(s2);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfAddSensorWorks_false() {
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 10, 4).getTime());
        Sensor s2 = new Sensor("Chuva", new TypeSensor("Atmosphere", "l/m2"),
                new Local(10, 30, 20),
                new GregorianCalendar(118, 9, 4).getTime());
        SensorList sl = new SensorList(s1);
        boolean actualResult = sl.containsSensor(s2);
        boolean expectedResult = false;
        assertEquals(expectedResult, actualResult);
        System.out.println(actualResult);
    }

    @Test
    public void seeIfAddSensorThatExistsWorks_false() {
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 10, 4).getTime());
        Sensor s2 = new Sensor("Chuva", new TypeSensor("Atmosphere", "l/m2"),
                new Local(10, 30, 20),
                new GregorianCalendar(118, 12, 4).getTime());
        Sensor s3 = new Sensor("Chuva", new TypeSensor("Atmosphere", "l/m2"),
                new Local(10, 30, 20),
                new GregorianCalendar(118, 8, 4).getTime());
        SensorList sl = new SensorList(s1);
        sl.addSensor(s2);
        sl.addSensor(s3);
        boolean actualResult = sl.addSensor(s3);
        boolean expectedResult = false;
        assertEquals(expectedResult, actualResult);
        System.out.println(actualResult);
    }

    @Test
    public void seeIfRemoveSensor_true() {
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 12, 4).getTime());
        Sensor s2 = new Sensor("Chuva", new TypeSensor("Atmosphere", "l/m2"),
                new Local(10, 30, 20),
                new GregorianCalendar(118, 10, 4).getTime());
        SensorList lc = new SensorList(s1);
        lc.addSensor(s2);
        lc.removeSensor(s1);
        boolean expectedResult = true;
        boolean result = lc.containsSensor(s2);
        assertEquals(result, expectedResult);
    }

    @Test
    public void seeIfRemoveSensor_false() {
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 12, 4).getTime());
        Sensor s2 = new Sensor("Chuva", new TypeSensor("Atmosphere", "l/m2"),
                new Local(10, 30, 20),
                new GregorianCalendar(118, 10, 4).getTime());
        SensorList lc = new SensorList(s1);
        lc.addSensor(s2);
        lc.removeSensor(s1);
        boolean expectedResult = false;
        boolean result = lc.containsSensor(s1);
        assertEquals(result, expectedResult);
    }

    @Test
    public void seeIfArrayGetSensors() {
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 12, 4).getTime());
        Sensor s2 = new Sensor("Chuva", new TypeSensor("Atmosphere", "l/m2"),
                new Local(10, 30, 20),
                new GregorianCalendar(118, 10, 4).getTime());
        Sensor[] expectedResult = new Sensor[]{s1, s2};
        Sensor[] result;
        SensorList lc = new SensorList(s1);
        lc.addSensor(s2);
        result = lc.getSensors();
        assertArrayEquals(result, expectedResult);
    }

    @Test
    public void seeIfGetSensorsList() {
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 12, 4).getTime());
        Sensor s2 = new Sensor("Chuva", new TypeSensor("Atmosphere", "l/m2"),
                new Local(10, 30, 20),
                new GregorianCalendar(118, 10, 4).getTime());
        List<Sensor> expectedResult = new ArrayList<>();
        List<Sensor> result;
        SensorList lc = new SensorList(s1);
        lc.addSensor(s2);
        result = lc.getSensorList();
        expectedResult.add(s1);
        expectedResult.add(s2);
        assertEquals(result, expectedResult);
    }

    @Test
    public void seeIfequalsSameObject() {
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 12, 4).getTime());
        SensorList sl = new SensorList(s1);
        boolean actualResult = sl.equals(sl);
        boolean expectedResult = true;
        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfequalsSensorListWithSameContent() {
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 12, 4).getTime());
        Sensor s2 = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
                new Local(10, 30, 20),
                new GregorianCalendar(118, 12, 4).getTime());
        SensorList sl1 = new SensorList(s1);
        SensorList sl2 = new SensorList(s2);
        boolean actualResult = sl1.equals(sl2);
        boolean expectedResult = true;
        assertEquals(expectedResult, actualResult);

    }

    @Test
    public void seeIfequalsSensorListWithDifferentContent() {
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 12, 4).getTime());
        Sensor s2 = new Sensor("Chuva", new TypeSensor("Atmosphere", "l/m2"),
                new Local(10, 30, 20),
                new GregorianCalendar(118, 12, 4).getTime());
        SensorList sl1 = new SensorList(s1);
        SensorList sl2 = new SensorList(s2);
        boolean actualResult = sl1.equals(sl2);
        boolean expectedResult = false;
        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfGetMostRecentlyUsedSensorWorks() {

        //Arrange

        Reading reading1 = new Reading(15, new GregorianCalendar(118, 11, 25).getTime());
        Reading reading2 = new Reading(29, new GregorianCalendar(118, 9, 3).getTime());
        Reading reading3 = new Reading(15, new GregorianCalendar(113, 11, 25).getTime());
        Reading reading4 = new Reading(29, new GregorianCalendar(111, 9, 3).getTime());
        ReadingList l1 = new ReadingList();
        ReadingList l2 = new ReadingList();
        l1.addReading(reading1);
        l1.addReading(reading2);
        l2.addReading(reading3);
        l2.addReading(reading4);
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 12, 4).getTime());
        s1.setReadingList(l1);
        Sensor s2 = new Sensor("Chuva", new TypeSensor("Atmosphere", "l/m2"),
                new Local(10, 30, 20),
                new GregorianCalendar(118, 10, 4).getTime());
        s2.setReadingList(l2);
        SensorList list1 = new SensorList(new Sensor[]{s1, s2});
        Sensor expectedResult = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 12, 4).getTime());
        expectedResult.setReadingList(l1);
        Sensor actualResult;

        //Act
        actualResult = list1.getMostRecentlyUsedSensor();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetMostRecentlyUsedSensorWorksSwitchedSensors() {

        //Arrange
        Reading reading1 = new Reading(15, new GregorianCalendar(118, 11, 25).getTime());
        Reading reading2 = new Reading(29, new GregorianCalendar(118, 9, 3).getTime());
        Reading reading3 = new Reading(15, new GregorianCalendar(113, 11, 25).getTime());
        Reading reading4 = new Reading(29, new GregorianCalendar(111, 9, 3).getTime());
        ReadingList l1 = new ReadingList();
        ReadingList l2 = new ReadingList();
        l1.addReading(reading1);
        l1.addReading(reading2);
        l2.addReading(reading3);
        l2.addReading(reading4);
        Sensor s2 = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 12, 4).getTime());
        s2.setReadingList(l1);
        Sensor s1 = new Sensor("Movimento", new TypeSensor("Atmosphere", "m/s"),
                new Local(10, 30, 20),
                new GregorianCalendar(118, 12, 4).getTime());
        s1.setReadingList(l2);
        SensorList list1 = new SensorList(new Sensor[]{s1, s2});
        Sensor expectedResult = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 12, 4).getTime());
        expectedResult.setReadingList(l1);
        Sensor actualResult;

        //Act
        actualResult = list1.getMostRecentlyUsedSensor();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfequalsSensorListWithDifferentObject() {
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 12, 4).getTime());
        int teste = 3;
        SensorList sl1 = new SensorList(s1);
        boolean actualResult = sl1.equals(teste);
        boolean expectedResult = false;
        assertEquals(expectedResult, actualResult);

    }

    @Test
    public void seeHashCodeDummyTest() {
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 12, 4).getTime());
        SensorList l1 = new SensorList(s1);
        int expectedResult = 1;
        int actualResult = l1.hashCode();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getSensorsInGAInACertainTimePeriod() {
        Sensor s1 = new Sensor("s1", new TypeSensor("temperatura", "Celsius"), new Local(20, 14, 15), new GregorianCalendar(2000, 5, 1).getTime());
        Sensor s2 = new Sensor("s2", new TypeSensor("temperatura", "Celsius"), new Local(15, 19, 15), new GregorianCalendar(2000, 7, 1).getTime());
        Sensor s3 = new Sensor("s2", new TypeSensor("temperatura", "Celsius"), new Local(15, 19, 15), new GregorianCalendar(2000, 11, 1).getTime());
        SensorList sensorList = new SensorList(s1);
        sensorList.addSensor(s1);
        sensorList.addSensor(s2);
        sensorList.addSensor(s3);
        GeographicArea ga1 = new GeographicArea("Portugal", new TypeArea("cidade"), 10, 20, new Local(16, 17, 18));
        ga1.setSensorList(sensorList);
        GregorianCalendar date1 = new GregorianCalendar(2000, 10, 5);
        List<Sensor> result = sensorList.getSensorsInGAAtACertainTimePeriod(date1, ga1);
        List<Sensor> expectedResult = new ArrayList<>();
        expectedResult.add(s1);
        expectedResult.add(s2);
        assertEquals(expectedResult, result);
    }

    @Test
    void getSensorsInGAInACertainTimePeriodFalse() {
        Sensor s1 = new Sensor("s1", new TypeSensor("temperatura", "Celsius"), new Local(20, 14, 15), new GregorianCalendar(2000, 10, 8).getTime());
        Sensor s2 = new Sensor("s2", new TypeSensor("temperatura", "Celsius"), new Local(15, 19, 15), new GregorianCalendar(2000, 11, 2).getTime());
        Sensor s3 = new Sensor("s2", new TypeSensor("temperatura", "Celsius"), new Local(15, 19, 15), new GregorianCalendar(2000, 11, 1).getTime());
        SensorList sensorList = new SensorList(s1);
        sensorList.addSensor(s1);
        sensorList.addSensor(s2);
        sensorList.addSensor(s3);
        GeographicArea ga1 = new GeographicArea("Portugal", new TypeArea("cidade"), 10, 20, new Local(16, 17, 18));
        ga1.setSensorList(sensorList);
        GregorianCalendar date1 = new GregorianCalendar(2000, 10, 5);
        List<Sensor> result = sensorList.getSensorsInGAAtACertainTimePeriod(date1, ga1);
        List<Sensor> expectedResult = new ArrayList<>();
        assertEquals(expectedResult, result);
    }

    @Test
    void getSensorsInGAInACertainTimePeriodWrongArea() {
        Sensor s1 = new Sensor("s1", new TypeSensor("temperatura", "Celsius"), new Local(20, 14, 15), new GregorianCalendar(2000, 5, 1).getTime());
        Sensor s2 = new Sensor("s2", new TypeSensor("temperatura", "Celsius"), new Local(50, 19, 15), new GregorianCalendar(2000, 7, 1).getTime());
        Sensor s3 = new Sensor("s2", new TypeSensor("temperatura", "Celsius"), new Local(15, 19, 15), new GregorianCalendar(2000, 11, 1).getTime());
        SensorList sensorList = new SensorList(s1);
        sensorList.addSensor(s1);
        sensorList.addSensor(s2);
        sensorList.addSensor(s3);
        GeographicArea ga1 = new GeographicArea("Portugal", new TypeArea("cidade"), 10, 20, new Local(16, 17, 18));
        ga1.setSensorList(sensorList);
        GregorianCalendar date1 = new GregorianCalendar(2000, 10, 5);
        List<Sensor> result = sensorList.getSensorsInGAAtACertainTimePeriod(date1, ga1);
        List<Sensor> expectedResult = new ArrayList<>();
        expectedResult.add(s1);

        assertEquals(expectedResult, result);
    }

    @Test
    void getListOfSensorsContainedInGA() {
        //Arrange
        TypeSensor t1 = new TypeSensor("Humidade", "kg/m³");
        Sensor s1 = new Sensor("s1", t1, new Local(15, 16, 50), new GregorianCalendar(2000, 10, 8).getTime());
        Sensor s2 = new Sensor("s2", t1, new Local(16, 17, 50), new GregorianCalendar(2000, 11, 2).getTime());
        Sensor s3 = new Sensor("s3", t1, new Local(0, 0, 50), new GregorianCalendar(2000, 11, 1).getTime());
        SensorList sensorList1 = new SensorList(s1);
        Local l1 = new Local(20, 20, 50);
        TypeArea ta1 = new TypeArea("Pantano");
        GeographicArea ga1 = new GeographicArea("Portugal", ta1, 10, 20, l1);

        //Act
        ga1.setWidth(15);
        ga1.setLength(15);
        sensorList1.addSensor(s1);
        sensorList1.addSensor(s2);
        sensorList1.addSensor(s3);
        List<Sensor> actualResult = sensorList1.getListOfSensorsContainedInGeographicArea(ga1, t1);
        List<Sensor> expectedResult = new ArrayList<>();
        expectedResult.add(s1);
        expectedResult.add(s2);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getListOfSensorsContainedInGAAllThreeContained() {
        //Arrange
        TypeSensor t1 = new TypeSensor("Humidade", "kg/m³");
        Sensor s1 = new Sensor("s1", t1, new Local(15, 16, 50), new GregorianCalendar(2000, 10, 8).getTime());
        Sensor s2 = new Sensor("s2", t1, new Local(16, 17, 50), new GregorianCalendar(2000, 11, 2).getTime());
        Sensor s3 = new Sensor("s3", t1, new Local(15, 15, 50), new GregorianCalendar(2000, 11, 1).getTime());
        SensorList sensorList1 = new SensorList(s1);
        Local l1 = new Local(20, 20, 50);
        TypeArea ta1 = new TypeArea("Pantano");
        GeographicArea ga1 = new GeographicArea("Portugal", ta1, 10, 20, l1);

        //Act
        ga1.setWidth(21);
        ga1.setLength(22);
        sensorList1.addSensor(s1);
        sensorList1.addSensor(s2);
        sensorList1.addSensor(s3);
        List<Sensor> actualResult = sensorList1.getListOfSensorsContainedInGeographicArea(ga1, t1);
        List<Sensor> expectedResult = new ArrayList<>();
        expectedResult.add(s1);
        expectedResult.add(s2);
        expectedResult.add(s3);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getListOfSensorsContainedInGADifferentTypeSensor() {
        //Arrange
        TypeSensor t1 = new TypeSensor("Humidade", "kg/m³");
        TypeSensor t2 = new TypeSensor("Vento", "km/h");
        Sensor s1 = new Sensor("s1", t1, new Local(15, 16, 50), new GregorianCalendar(2000, 10, 8).getTime());
        Sensor s2 = new Sensor("s2", t2, new Local(16, 17, 50), new GregorianCalendar(2000, 11, 2).getTime());
        Sensor s3 = new Sensor("s3", t1, new Local(0, 0, 50), new GregorianCalendar(2000, 11, 1).getTime());
        SensorList sensorList1 = new SensorList(s1);
        Local l1 = new Local(20, 20, 50);
        TypeArea ta1 = new TypeArea("Pantano");
        GeographicArea ga1 = new GeographicArea("Portugal", ta1, 10, 20, l1);
        Local l2 = new Local(10, 30, 50);
        Local l3 = new Local(30, 10, 50);

        //Act
        ga1.setWidth(20);
        ga1.setLength(30);
        sensorList1.addSensor(s1);
        sensorList1.addSensor(s2);
        sensorList1.addSensor(s3);
        List<Sensor> actualResult = sensorList1.getListOfSensorsContainedInGeographicArea(ga1, t1);
        List<Sensor> expectedResult = new ArrayList<>();
        expectedResult.add(s1);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeItGetSensorByName() {
        //Arrange

        TypeSensor t1 = new TypeSensor("Humidade", "kg/m³");
        TypeSensor t2 = new TypeSensor("Vento", "km/h");
        Sensor s1 = new Sensor("s1", t1, new Local(15, 16, 50), new GregorianCalendar(2000, 10, 8).getTime());
        Sensor s2 = new Sensor("s2", t2, new Local(16, 17, 50), new GregorianCalendar(2000, 11, 2).getTime());
        Sensor s3 = new Sensor("s3", t1, new Local(0, 0, 50), new GregorianCalendar(2000, 11, 1).getTime());
        SensorList sensorList1 = new SensorList(s1);

        //Act
        sensorList1.addSensor(s1);
        sensorList1.addSensor(s2);
        sensorList1.addSensor(s3);
        Sensor actualResult = sensorList1.getSensorByName("s1");

        //Assert
        assertEquals(s1, actualResult);
    }

    @Test
    void seeItGetSensorByNameIfNameDoestExist() {
        //Arrange

        TypeSensor t1 = new TypeSensor("Humidade", "kg/m³");
        TypeSensor t2 = new TypeSensor("Vento", "km/h");
        Sensor s1 = new Sensor("s1", t1, new Local(15, 16, 50), new GregorianCalendar(2000, 10, 8).getTime());
        Sensor s2 = new Sensor("s2", t2, new Local(16, 17, 50), new GregorianCalendar(2000, 11, 2).getTime());
        Sensor s3 = new Sensor("s3", t1, new Local(0, 0, 50), new GregorianCalendar(2000, 11, 1).getTime());
        SensorList sensorList1 = new SensorList(s1);

        //Act
        sensorList1.addSensor(s1);
        sensorList1.addSensor(s2);
        sensorList1.addSensor(s3);
        Sensor actualResult = sensorList1.getSensorByName("t1");

        //Assert
        assertNull(actualResult);
    }

    @Test
    void ensureThatWeTestEmptyConstructor() {
        SensorList sl = new SensorList();
        TypeSensor t1 = new TypeSensor("Humidade", "kg/m³");
        Sensor s1 = new Sensor("s1", t1, new Local(15, 16, 50), new GregorianCalendar(2000, 10, 8).getTime());
        sl.addSensor(s1);
        Sensor expectedResult = s1;

        Sensor actualResult = sl.getMostRecentlyUsedSensor();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSetTypeWorks() {
        //Arrange
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 10, 4).getTime());
        Sensor s2 = new Sensor("Chuva", new TypeSensor("Atmosphere", "l/m2"),
                new Local(10, 30, 20),
                new GregorianCalendar(118, 1, 4).getTime());
        SensorList lc = new SensorList(new Sensor[]{s1, s2});
        String expectedResult = "Movement";

        //Act
        lc.setTypeSensorByString("Chuva", "Movement");
        String actualResult = lc.getSensorByName("Chuva").getTypeSensor().getName();

        //Assert
        assertEquals(expectedResult, actualResult);
    }


    @Test
    void seeIfSetTypeWorksFalse() {
        //Arrange
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 10, 4).getTime());
        Sensor s2 = new Sensor("Chuva", new TypeSensor("Atmosphere", "l/m2"),
                new Local(10, 30, 20),
                new GregorianCalendar(118, 1, 4).getTime());
        SensorList lc = new SensorList(new Sensor[]{s1, s2});
        boolean expectedResult = false;

        //Act

        boolean actualResult = lc.setTypeSensorByString("Portugal", "Movement");

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSetTypeWorksEmptyList() {
        //Arrange
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 10, 4).getTime());
        Sensor s2 = new Sensor("Chuva", new TypeSensor("Atmosphere", "l/m2"),
                new Local(10, 30, 20),
                new GregorianCalendar(118, 1, 4).getTime());
        SensorList lc = new SensorList();
        boolean expectedResult = false;

        //Act
        boolean actualResult = lc.setTypeSensorByString("Portugal", "Movement");

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfAnInvalidListIsAdded() {
        SensorList list = new SensorList();
        boolean expectedResult = false;
        boolean actualResult = list.checkIfListIsValid();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfAnValidListIsAdded() {
        SensorList list = new SensorList();
        Sensor sensor = new Sensor("Termometro", new TypeSensor(), new Local(1, 1, 50), new Date());
        list.addSensor(sensor);
        boolean expectedResult = true;
        boolean actualResult = list.checkIfListIsValid();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeItGetSensorByType() {
        //Arrange

        TypeSensor t1 = new TypeSensor("Humidade", "kg/m³");
        TypeSensor t2 = new TypeSensor("Vento", "km/h");
        Sensor s1 = new Sensor("s1", t1, new Local(15, 16, 50), new GregorianCalendar(2000, 10, 8).getTime());
        Sensor s2 = new Sensor("s2", t2, new Local(16, 17, 50), new GregorianCalendar(2000, 11, 2).getTime());
        Sensor s3 = new Sensor("s3", t1, new Local(0, 0, 50), new GregorianCalendar(2000, 11, 1).getTime());
        SensorList sensorList1 = new SensorList(s1);

        //Act
        sensorList1.addSensor(s1);
        sensorList1.addSensor(s2);
        sensorList1.addSensor(s3);
        Sensor actualResult = sensorList1.getSensorByType("Humidade");

        //Assert
        assertEquals(s1, actualResult);
    }

    @Test
    void seeItGetSensorByTypeNull() {
        //Arrange

        TypeSensor t1 = new TypeSensor("Vento", "km/h");
        TypeSensor t2 = new TypeSensor("Vento", "km/h");
        Sensor s1 = new Sensor("s1", t1, new Local(15, 16, 50), new GregorianCalendar(2000, 10, 8).getTime());
        Sensor s2 = new Sensor("s2", t2, new Local(16, 17, 50), new GregorianCalendar(2000, 11, 2).getTime());
        Sensor s3 = new Sensor("s3", t1, new Local(0, 0, 50), new GregorianCalendar(2000, 11, 1).getTime());
        SensorList sensorList1 = new SensorList(s1);

        //Act
        sensorList1.addSensor(s1);
        sensorList1.addSensor(s2);
        sensorList1.addSensor(s3);
        Sensor actualResult = sensorList1.getSensorByType("Humidade");
        Sensor expectedResult = null;

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeItGetSensorByTypeList() {
        //Arrange

        TypeSensor t1 = new TypeSensor("Vento", "km/h");
        Sensor s1 = new Sensor("s1", t1, new Local(15, 16, 50), new GregorianCalendar(2000, 10, 8).getTime());
        SensorList sensorList1 = new SensorList(s1);

        //Act
        sensorList1.addSensor(s1);
        Sensor actualResult = sensorList1.getSensorByType("Vento");

        //Assert
        assertEquals(s1, actualResult);
    }

    @Test
    void seeItGetSensorListByTypeEquals() {
        //Arrange

        TypeSensor t1 = new TypeSensor("Rain", "l/m2");
        TypeSensor t2 = new TypeSensor("Vento", "km/h");
        Sensor s1 = new Sensor("s1", t1, new Local(15, 16, 50), new GregorianCalendar(2000, 10, 8).getTime());
        Sensor s2 = new Sensor("s2", t2, new Local(16, 17, 50), new GregorianCalendar(2000, 11, 2).getTime());
        Sensor s3 = new Sensor("s3", t1, new Local(0, 0, 50), new GregorianCalendar(2000, 11, 1).getTime());
        SensorList sensorList1 = new SensorList(s1);

        //Act
        sensorList1.addSensor(s1);
        sensorList1.addSensor(s2);
        sensorList1.addSensor(s3);
        List<Sensor> actualResult = sensorList1.getSensorListByType("Rain");
        List<Sensor> expectedResult = new ArrayList<>();
        expectedResult.add(s1);
        expectedResult.add(s3);

        //Assert
        expectedResult.equals(actualResult);
    }

    @Test
    void seeItGetSensorListByTypeContains() {
        //Arrange

        TypeSensor t1 = new TypeSensor("Rain", "l/m2");
        TypeSensor t2 = new TypeSensor("Vento", "km/h");
        Sensor s1 = new Sensor("s1", t1, new Local(15, 16, 50), new GregorianCalendar(2000, 10, 8).getTime());
        Sensor s2 = new Sensor("s2", t2, new Local(16, 17, 50), new GregorianCalendar(2000, 11, 2).getTime());
        Sensor s3 = new Sensor("s3", t1, new Local(0, 0, 50), new GregorianCalendar(2000, 11, 1).getTime());
        SensorList sensorList1 = new SensorList(s1);

        //Act
        sensorList1.addSensor(s1);
        sensorList1.addSensor(s2);
        sensorList1.addSensor(s3);
        List<Sensor> actualResult = sensorList1.getSensorListByType("Rain");
        List<Sensor> expectedResult = new ArrayList<>();
        expectedResult.add(s1);
        expectedResult.add(s3);

        //Assert
        expectedResult.contains(actualResult);
    }

    @Test
    void seeItGetSensorListByTypeContainsSameName() {
        //Arrange

        TypeSensor t1 = new TypeSensor("Rain", "l/m2");
        TypeSensor t2 = new TypeSensor("Rain", "l/m2");
        Sensor s1 = new Sensor("s1", t1, new Local(15, 16, 50), new GregorianCalendar(2000, 10, 8).getTime());
        Sensor s2 = new Sensor("s2", t2, new Local(16, 17, 50), new GregorianCalendar(2000, 11, 2).getTime());
        Sensor s3 = new Sensor("s3", t1, new Local(0, 0, 50), new GregorianCalendar(2000, 11, 1).getTime());
        SensorList sensorList1 = new SensorList(s1);

        //Act
        sensorList1.addSensor(s1);
        sensorList1.addSensor(s2);
        sensorList1.addSensor(s3);
        List<Sensor> actualResult = sensorList1.getSensorListByType("Rain");
        List<Sensor> expectedResult = new ArrayList<>();
        expectedResult.add(s1);
        expectedResult.add(s2);
        expectedResult.add(s3);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void ensureThatSensorListIsPrintCorrectly() {
        Room room = new Room("Quarto Miki", 1, 3, 3, 3);
        TypeSensor t1 = new TypeSensor("Rain", "l/m2");
        TypeSensor t2 = new TypeSensor("Vento", "km/h");
        Sensor s1 = new Sensor("s1", t1, new Local(15, 16, 50), new GregorianCalendar(2000, 10, 8).getTime());
        Sensor s2 = new Sensor("s2", t2, new Local(16, 17, 50), new GregorianCalendar(2000, 11, 2).getTime());
        Sensor s3 = new Sensor("s3", t1, new Local(0, 0, 50), new GregorianCalendar(2000, 11, 1).getTime());
        SensorList sensorList1 = new SensorList(s1);
        sensorList1.addSensor(s1);
        sensorList1.addSensor(s2);
        sensorList1.addSensor(s3);
        room.setRoomSensorList(sensorList1);
        String expectedResult = "---------------\n" +
                "0) Designation: s1 | Sensor Type: Rain\n" +
                "1) Designation: s2 | Sensor Type: Vento\n" +
                "2) Designation: s3 | Sensor Type: Rain\n" +
                "---------------\n";
        String actualResult = sensorList1.printSensorList(room);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void ensureThatEmptySensorListIsPrintedWithWarningMessage() {
        Room room = new Room("Quarto Miki", 1, 3, 3, 3);
        TypeSensor t1 = new TypeSensor("Rain", "l/m2");
        TypeSensor t2 = new TypeSensor("Vento", "km/h");
        SensorList sensorList1 = new SensorList();
        room.setRoomSensorList(sensorList1);
        String expectedResult = "Invalid List - List is Empty\n";
        String actualResult = sensorList1.printSensorList(room);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfMatchSensorIndexByString() {
        //Arrange
        List<Integer> list = new ArrayList<>();
        Integer i = 2;
        list.add(i);
        Room room = new Room("Quarto Miki", 1, 3, 3, 3);
        TypeSensor t1 = new TypeSensor("Rain", "l/m2");
        TypeSensor t2 = new TypeSensor("Vento", "km/h");
        Sensor s1 = new Sensor("s1", t1, new Local(15, 16, 50), new GregorianCalendar(2000, 10, 8).getTime());
        Sensor s2 = new Sensor("s2", t2, new Local(16, 17, 50), new GregorianCalendar(2000, 11, 2).getTime());
        Sensor s3 = new Sensor("s3", t1, new Local(0, 0, 50), new GregorianCalendar(2000, 11, 1).getTime());
        SensorList sensorList1 = new SensorList(s1);
        sensorList1.addSensor(s1);
        sensorList1.addSensor(s2);
        sensorList1.addSensor(s3);
        room.setRoomSensorList(sensorList1);
        List<Integer> result = sensorList1.matchSensorIndexByString("s3");
        List<Integer> expectedResult = Collections.singletonList(2);
        assertEquals(expectedResult, result);

    }

    @Test
    void seeIfMatchSensorListIndexByString() {
        //Arrange
        List<Integer> list = new ArrayList<>();
        Integer i = 2;
        list.add(i);
        Room room = new Room("Quarto Miki", 1, 3, 3, 3);
        TypeSensor t1 = new TypeSensor("Rain", "l/m2");
        TypeSensor t2 = new TypeSensor("Vento", "km/h");
        Sensor s1 = new Sensor("s1", t1, new Local(15, 16, 50), new GregorianCalendar(2000, 10, 8).getTime());
        Sensor s2 = new Sensor("s2", t2, new Local(16, 17, 50), new GregorianCalendar(2000, 11, 2).getTime());
        Sensor s3 = new Sensor("s3", t1, new Local(0, 0, 50), new GregorianCalendar(2000, 11, 1).getTime());
        SensorList sensorList1 = new SensorList(s1);
        sensorList1.addSensor(s1);
        sensorList1.addSensor(s2);
        sensorList1.addSensor(s3);
        room.setRoomSensorList(sensorList1);
        List<Integer> result = sensorList1.matchSensorListIndexByString("s3");
        List<Integer> expectedResult = Collections.singletonList(2);
        assertEquals(expectedResult, result);

    }

    @Test
    void seeIfPrintElementsByIndex() {
        //Arrange
        List<Integer> list = new ArrayList<>();
        Integer i = 2;
        list.add(i);
        Room room = new Room("Quarto Miki", 1, 3, 3, 3);
        TypeSensor t1 = new TypeSensor("Rain", "l/m2");
        TypeSensor t2 = new TypeSensor("Vento", "km/h");
        Sensor s1 = new Sensor("s1", t1, new Local(15, 16, 50), new GregorianCalendar(2000, 10, 8).getTime());
        Sensor s2 = new Sensor("s2", t2, new Local(16, 17, 50), new GregorianCalendar(2000, 11, 2).getTime());
        Sensor s3 = new Sensor("s3", t1, new Local(0, 0, 50), new GregorianCalendar(2000, 11, 1).getTime());
        SensorList sensorList1 = new SensorList(s1);
        sensorList1.addSensor(s1);
        sensorList1.addSensor(s2);
        sensorList1.addSensor(s3);
        room.setRoomSensorList(sensorList1);

        //Act
        String result = sensorList1.printElementsByIndex(list);
        String expectedResult = "2) s3 which is a Rain sensor.\n";

        //Assert
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    void ensureThatSensorIsInSensorListByString() {
        Room room = new Room("Quarto Miki", 1, 3, 3, 3);
        TypeSensor t1 = new TypeSensor("Rain", "l/m2");
        TypeSensor t2 = new TypeSensor("Vento", "km/h");
        Sensor s1 = new Sensor("s1", t1, new Local(15, 16, 50), new GregorianCalendar(2000, 10, 8).getTime());
        Sensor s2 = new Sensor("s2", t2, new Local(16, 17, 50), new GregorianCalendar(2000, 11, 2).getTime());
        Sensor s3 = new Sensor("s3", t1, new Local(0, 0, 50), new GregorianCalendar(2000, 11, 1).getTime());
        SensorList sensorList1 = new SensorList(s1);
        sensorList1.addSensor(s1);
        sensorList1.addSensor(s2);
        sensorList1.addSensor(s3);
        room.setRoomSensorList(sensorList1);
        boolean expectedResult = true;
        boolean actualResult = sensorList1.doesSensorListContainSensorByName("s1");
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void ensureThatSensorIsNotInSensorListByString() {
        Room room = new Room("Quarto Miki", 1, 3, 3, 3);
        TypeSensor t1 = new TypeSensor("Rain", "l/m2");
        TypeSensor t2 = new TypeSensor("Vento", "km/h");
        Sensor s1 = new Sensor("s1", t1, new Local(15, 16, 50), new GregorianCalendar(2000, 10, 8).getTime());
        Sensor s2 = new Sensor("s2", t2, new Local(16, 17, 50), new GregorianCalendar(2000, 11, 2).getTime());
        Sensor s3 = new Sensor("s3", t1, new Local(0, 0, 50), new GregorianCalendar(2000, 11, 1).getTime());
        SensorList sensorList1 = new SensorList(s1);
        sensorList1.addSensor(s1);
        sensorList1.addSensor(s2);
        sensorList1.addSensor(s3);
        room.setRoomSensorList(sensorList1);
        boolean expectedResult = false;
        boolean actualResult = sensorList1.doesSensorListContainSensorByName("s4");
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfPrintsSensorWholeList() {
        Room room = new Room("Quarto Miki", 1, 3, 3, 3);
        TypeSensor t1 = new TypeSensor("Rain", "l/m2");
        TypeSensor t2 = new TypeSensor("Vento", "km/h");
        Sensor s1 = new Sensor("s1", t1, new Local(15, 16, 50), new GregorianCalendar(2000, 10, 8).getTime());
        Sensor s2 = new Sensor("s2", t2, new Local(16, 17, 50), new GregorianCalendar(2000, 11, 2).getTime());
        Sensor s3 = new Sensor("s3", t1, new Local(0, 0, 50), new GregorianCalendar(2000, 11, 1).getTime());
        SensorList sensorList1 = new SensorList(s1);
        sensorList1.addSensor(s1);
        sensorList1.addSensor(s2);
        sensorList1.addSensor(s3);
        room.setRoomSensorList(sensorList1);
        String expectedResult = "---------------\n" +
                "0) Name: s1 | Type: Rain\n" +
                "1) Name: s2 | Type: Vento\n" +
                "2) Name: s3 | Type: Rain\n" +
                "---------------\n";
        String result = sensorList1.printSensorWholeList(sensorList1);
        assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfPrintsEmptySensorWholeList() {
        Room room = new Room("Quarto Miki", 1, 3, 3, 3);
        TypeSensor t1 = new TypeSensor("Rain", "l/m2");
        TypeSensor t2 = new TypeSensor("Vento", "km/h");
        SensorList sensorList1 = new SensorList();
        room.setRoomSensorList(sensorList1);
        String expectedResult = "Invalid List - List is Empty\n";
        String result = sensorList1.printSensorWholeList(sensorList1);
        assertEquals(expectedResult, result);
    }


}
