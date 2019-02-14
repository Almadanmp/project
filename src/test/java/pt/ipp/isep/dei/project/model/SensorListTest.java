package pt.ipp.isep.dei.project.model;

import org.junit.jupiter.api.Test;
import org.testng.Assert;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * SensorList tests class.
 */

class SensorListTest {

    @Test
    void seeIfConstructorWorks() {
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
    void seeIfConstructorWorksNoSensor() {
        Sensor[] expectedResult = new Sensor[]{};
        Sensor[] result;
        SensorList lc = new SensorList(new Sensor[]{});
        result = lc.getSensors();
        assertArrayEquals(result, expectedResult);
    }

    @Test
    void seeIfAddSensorsWorks_true() {
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
    void seeIfAddSensorWorks_false() {
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
    void seeIfAddSensorThatExistsWorks_false() {
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
    void seeIfRemoveSensor_true() {
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
    void seeIfRemoveSensor_false() {
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
    void seeIfArrayGetSensors() {
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
    void seeIfGetSensorsList() {
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
    void seeIfequalsSameObject() {
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 12, 4).getTime());
        SensorList sl = new SensorList(s1);
        boolean actualResult = sl.equals(sl);
        assertTrue(actualResult);

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
    void seeIfequalsSensorListWithDifferentContent() {
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
    void seeIfequalsSensorListWithDifferentObject() {
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 12, 4).getTime());
        int teste = 3;
        SensorList sl1 = new SensorList(s1);
        boolean actualResult = sl1.equals(teste);
        assertFalse(actualResult);

    }

    @Test
    void seeHashCodeDummyTest() {
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
    void seeIfSetTypeWorksAssertTrue() {
        //Arrange
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 10, 4).getTime());
        Sensor s2 = new Sensor("Chuva", new TypeSensor("Atmosphere", "l/m2"),
                new Local(10, 30, 20),
                new GregorianCalendar(118, 1, 4).getTime());
        SensorList lc = new SensorList(new Sensor[]{s1, s2});

        //Act
        boolean expectedResult = lc.setTypeSensorByString("Chuva", "Movement");


        //Assert
        assertTrue(expectedResult);
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
        Sensor sensor = new Sensor("Termometro", new TypeSensor("Temperature", "Cº"), new Local(1, 1, 50), new Date());
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
        sensorList1.addSensor(s1);
        List<Sensor> actualResult = sensorList1.getSensorListByType("Rain");
        List<Sensor> expectedResult = new ArrayList<>();
        expectedResult.add(s1);
        expectedResult.add(s3);


        //Assert
        expectedResult.equals(actualResult);
        assertEquals(expectedResult, actualResult);
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
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeItGetSensorListByTypeDoesNotContain() {
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
        List<Sensor> actualResult = sensorList1.getSensorListByType("Temperature");
        List<Sensor> expectedResult = new ArrayList<>();

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
        String actualResult = sensorList1.buildSensorListString();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void ensureThatEmptySensorListIsPrintedWithWarningMessage() {
        Room room = new Room("Quarto Miki", 1, 3, 3, 3);
        SensorList sensorList1 = new SensorList();
        room.setRoomSensorList(sensorList1);
        String expectedResult = "Invalid List - List is Empty\n";
        String actualResult = sensorList1.buildSensorListString();
        assertEquals(expectedResult, actualResult);
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
        List<Integer> result = sensorList1.matchSensorIndexByString("s3");
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
        String result = sensorList1.buildElementsByIndexString(list);
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
    void seeIfPrintsSensorWholeList() {
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
        String result = sensorList1.buildSensorWholeListString(sensorList1);
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfPrintsEmptySensorWholeList() {
        Room room = new Room("Quarto Miki", 1, 3, 3, 3);
        SensorList sensorList1 = new SensorList();
        room.setRoomSensorList(sensorList1);
        String expectedResult = "Invalid List - List is Empty\n";
        String result = sensorList1.buildSensorWholeListString(sensorList1);
        assertEquals(expectedResult, result);
    }

    @Test
    void seeReadings() {
        GregorianCalendar date1 = new GregorianCalendar(2000, 10, 5);

        SensorList sensorList1 = new SensorList(); //EMPTY LIST
        SensorList sensorList2 = new SensorList(); //ONE SENSOR WITH NO READINGS
        SensorList sensorList3 = new SensorList(); //TWO SENSORS: FIRST WITH READINGS
        SensorList sensorList4 = new SensorList(); //TWO SENSORS: SECOND WITH READINGS
        SensorList sensorList5 = new SensorList(); //TWO SENSORS: BOTH WITH READINGS

        Sensor sensor1 = new Sensor("sensor1", new TypeSensor("temperature","ºC"),date1.getTime());
        sensorList2.addSensor(sensor1);

        Sensor sensor2 = new Sensor("sensor2", new TypeSensor("temperature","ºC"),date1.getTime());
        Sensor sensor3 = new Sensor("sensor3", new TypeSensor("temperature","ºC"),date1.getTime());
        Reading reading1 = new Reading(20,date1.getTime());
        Reading reading2 = new Reading(21,date1.getTime());
        sensor2.addReading(reading1);
        sensor2.addReading(reading2);
        sensorList3.addSensor(sensor2);
        sensorList3.addSensor(sensor3);

        Sensor sensor4 = new Sensor("sensor4", new TypeSensor("temperature","ºC"),date1.getTime());
        Sensor sensor5 = new Sensor("sensor5", new TypeSensor("temperature","ºC"),date1.getTime());
        sensor5.addReading(reading1);
        sensor5.addReading(reading2);
        sensorList4.addSensor(sensor4);
        sensorList4.addSensor(sensor5);

        Sensor sensor6 = new Sensor("sensor6", new TypeSensor("temperature","ºC"),date1.getTime());
        Sensor sensor7 = new Sensor("sensor7", new TypeSensor("temperature","ºC"),date1.getTime());
        Reading reading3 = new Reading(32,date1.getTime());
        Reading reading4 = new Reading(12,date1.getTime());
        sensor6.addReading(reading1);
        sensor6.addReading(reading2);
        sensor7.addReading(reading3);
        sensor7.addReading(reading4);
        sensorList5.addSensor(sensor6);
        sensorList5.addSensor(sensor7);


        ReadingList expectedResult1 = new ReadingList();
        ReadingList expectedResult2 = new ReadingList();
        ReadingList expectedResult3 = new ReadingList();
        ReadingList expectedResult4 = new ReadingList();
        ReadingList expectedResult5 = new ReadingList();
        expectedResult3.addReading(reading1);
        expectedResult3.addReading(reading2);
        expectedResult4.addReading(reading1);
        expectedResult4.addReading(reading2);
        expectedResult5.addReading(reading1);
        expectedResult5.addReading(reading2);
        expectedResult5.addReading(reading3);
        expectedResult5.addReading(reading4);
        //ACT
        ReadingList actualResult1 = sensorList1.getReadings();
        ReadingList actualResult2 = sensorList2.getReadings();
        ReadingList actualResult3 = sensorList3.getReadings();
        ReadingList actualResult4 = sensorList4.getReadings();
        ReadingList actualResult5 = sensorList5.getReadings();
        //ASSERT
        assertEquals(actualResult1, expectedResult1);
        assertEquals(actualResult2, expectedResult2);
        assertEquals(actualResult3, expectedResult3);
        assertEquals(actualResult4, expectedResult4);
        assertEquals(actualResult5, expectedResult5);
    }

    @Test
    void mostRecentlyUsedSensor() {
        GregorianCalendar date1 = new GregorianCalendar(2018, 10, 5,23,57);
        GregorianCalendar date2 = new GregorianCalendar(2018, 10, 5,23,58);
        GregorianCalendar date3 = new GregorianCalendar(2018, 10, 5,23,59);

        SensorList sensorList1 = new SensorList(); //EMPTY LIST
        SensorList sensorList2 = new SensorList(); //ONE SENSOR WITH NO READINGS
        SensorList sensorList3 = new SensorList(); //THREE SENSORS: FIRST MOST RECENT
        SensorList sensorList4 = new SensorList(); //THREE SENSORS: SECOND MOST RECENT
        SensorList sensorList5 = new SensorList(); //THREE SENSORS: THIRD MOST RECENT

        Sensor sensor1 = new Sensor("sensor1", new TypeSensor("temperature","ºC"),date1.getTime());
        sensorList2.addSensor(sensor1);

        Sensor sensor2 = new Sensor("sensor2", new TypeSensor("temperature","ºC"),date1.getTime());
        Sensor sensor3 = new Sensor("sensor3", new TypeSensor("temperature","ºC"),date1.getTime());
        Sensor sensor4 = new Sensor("sensor4", new TypeSensor("temperature","ºC"),date1.getTime());
        Reading reading1 = new Reading(20,date3.getTime());
        Reading reading2 = new Reading(21,date1.getTime());
        Reading reading3 = new Reading(32,date2.getTime());

        sensor2.addReading(reading1);
        sensor3.addReading(reading2);
        sensor4.addReading(reading3);
        sensorList3.addSensor(sensor2);
        sensorList3.addSensor(sensor3);
        sensorList3.addSensor(sensor4);


        Sensor sensor5 = new Sensor("sensor5", new TypeSensor("temperature","ºC"),date1.getTime());
        Sensor sensor6 = new Sensor("sensor6", new TypeSensor("temperature","ºC"),date1.getTime());
        Sensor sensor7 = new Sensor("sensor7", new TypeSensor("temperature","ºC"),date1.getTime());
        Reading reading4 = new Reading(20,date1.getTime());
        Reading reading5 = new Reading(21,date3.getTime());
        Reading reading6 = new Reading(32,date2.getTime());

        sensor5.addReading(reading4);
        sensor6.addReading(reading5);
        sensor7.addReading(reading6);
        sensorList4.addSensor(sensor5);
        sensorList4.addSensor(sensor6);
        sensorList4.addSensor(sensor7);

        Sensor sensor8 = new Sensor("sensor8", new TypeSensor("temperature","ºC"),date1.getTime());
        Sensor sensor9 = new Sensor("sensor9", new TypeSensor("temperature","ºC"),date1.getTime());
        Sensor sensor10 = new Sensor("sensor10", new TypeSensor("temperature","ºC"),date1.getTime());
        Reading reading7 = new Reading(20,date2.getTime());
        Reading reading8 = new Reading(21,date1.getTime());
        Reading reading9 = new Reading(32,date3.getTime());

        sensor8.addReading(reading7);
        sensor9.addReading(reading8);
        sensor10.addReading(reading9);
        sensorList5.addSensor(sensor8);
        sensorList5.addSensor(sensor9);
        sensorList5.addSensor(sensor10);


        Sensor expectedResult1 = new Sensor("emptySensor",new TypeSensor("type"," "),new GregorianCalendar(1900,0,1).getTime());
        Sensor expectedResult2 = new Sensor("emptySensor",new TypeSensor("type"," "),new GregorianCalendar(1900,0,1).getTime());
        Sensor expectedResult3 = sensor2;
        Sensor expectedResult4 = sensor6;
        Sensor expectedResult5 = sensor10;

        //ACT
        Sensor actualResult1 = sensorList1.getMostRecentlyUsedSensor();
        Sensor actualResult2 = sensorList2.getMostRecentlyUsedSensor();
        Sensor actualResult3 = sensorList3.getMostRecentlyUsedSensor();
        Sensor actualResult4 = sensorList4.getMostRecentlyUsedSensor();
        Sensor actualResult5 = sensorList5.getMostRecentlyUsedSensor();
        //ASSERT
        assertEquals(actualResult1, expectedResult1);
        assertEquals(actualResult2, expectedResult2);
        assertEquals(actualResult3, expectedResult3);
        assertEquals(actualResult4, expectedResult4);
        assertEquals(actualResult5, expectedResult5);
    }

    @Test
    void hasReadings() {
        GregorianCalendar date1 = new GregorianCalendar(2018, 10, 5,23,57);

        SensorList sensorList1 = new SensorList(); //EMPTY LIST
        SensorList sensorList2 = new SensorList(); //ONE SENSOR WITHOUT READINGS
        SensorList sensorList3 = new SensorList(); //TWO SENSORS WITHOUT READINGS
        SensorList sensorList4 = new SensorList(); //THREE SENSORS: FIRST HAS READINGS
        SensorList sensorList5 = new SensorList(); //THREE SENSORS: LAST HAS READINGS

        Sensor sensor1 = new Sensor("sensor1", new TypeSensor("temperature","ºC"),date1.getTime());
        sensorList2.addSensor(sensor1);

        Sensor sensor2 = new Sensor("sensor2", new TypeSensor("temperature","ºC"),date1.getTime());
        Sensor sensor3 = new Sensor("sensor3", new TypeSensor("temperature","ºC"),date1.getTime());
        sensorList3.addSensor(sensor2);
        sensorList3.addSensor(sensor3);

        Sensor sensor5 = new Sensor("sensor5", new TypeSensor("temperature","ºC"),date1.getTime());
        Sensor sensor6 = new Sensor("sensor6", new TypeSensor("temperature","ºC"),date1.getTime());
        Sensor sensor7 = new Sensor("sensor7", new TypeSensor("temperature","ºC"),date1.getTime());
        Reading reading4 = new Reading(20,date1.getTime());
        sensor5.addReading(reading4);
        sensorList4.addSensor(sensor5);
        sensorList4.addSensor(sensor6);
        sensorList4.addSensor(sensor7);

        Sensor sensor8 = new Sensor("sensor8", new TypeSensor("temperature","ºC"),date1.getTime());
        Sensor sensor9 = new Sensor("sensor9", new TypeSensor("temperature","ºC"),date1.getTime());
        Sensor sensor10 = new Sensor("sensor10", new TypeSensor("temperature","ºC"),date1.getTime());
        Reading reading7 = new Reading(26,date1.getTime());
        sensor10.addReading(reading7);
        sensorList5.addSensor(sensor8);
        sensorList5.addSensor(sensor9);
        sensorList5.addSensor(sensor10);
        //ACT
        boolean actualResult1 = sensorList1.hasReadings();
        boolean actualResult2 = sensorList2.hasReadings();
        boolean actualResult3 = sensorList3.hasReadings();
        boolean actualResult4 = sensorList4.hasReadings();
        boolean actualResult5 = sensorList4.hasReadings();
        //ASSERT
        assertFalse(actualResult1);
        assertFalse(actualResult2);
        assertFalse(actualResult3);
        assertTrue(actualResult4);
        assertTrue(actualResult5);
    }
}
