package Sprint_0;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class SensorList_Test {

    @Test
    public void seeIfConstructorWorks() {
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere"),
                new Local(12, 31, 21),
                new GregorianCalendar(118,10,4).getTime());
        Sensor s2 = new Sensor("Chuva", new TypeSensor("Atmosphere"),
                new Local(10, 30, 20),
                new GregorianCalendar(118,1,4).getTime());
        Sensor[] expectedResult = new Sensor[]{s1, s2};
        Sensor[] result;
        SensorList lc = new SensorList(new Sensor[]{s1, s2});
        result = lc.getSensors();
        assertArrayEquals(result, expectedResult);
    }

    @Test
    public void seeIfAddSensorsWorks_true() {
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere"),
                new Local(12, 31, 21),
                new GregorianCalendar(118,9,4).getTime());
        Sensor s2 = new Sensor("Chuva", new TypeSensor("Atmosphere"),
                new Local(10, 30, 20),
                new GregorianCalendar(118,10,4).getTime());
        SensorList sl = new SensorList(s1);
        sl.addSensor(s2);
        boolean expectedResult = true;

        boolean actualResult = sl.containsSensor(s1);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfAddSensorWorks_false() {
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere"),
                new Local(12, 31, 21),
                new GregorianCalendar(118,10,4).getTime());
        Sensor s2 = new Sensor("Chuva", new TypeSensor("Atmosphere"),
                new Local(10, 30, 20),
                new GregorianCalendar(118,9,4).getTime());
        SensorList sl = new SensorList(s1);
        boolean actualResult = sl.containsSensor(s2);
        boolean expectedResult = false;
        assertEquals(expectedResult, actualResult);
        System.out.println(actualResult);
    }

    @Test
    public void seeIfAddSensorThatExistsWorks_false() {
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere"),
                new Local(12, 31, 21),
                new GregorianCalendar(118,10,4).getTime());
        Sensor s2 = new Sensor("Chuva", new TypeSensor("Atmosphere"),
                new Local(10, 30, 20),
                new GregorianCalendar(118,12,4).getTime());
        Sensor s3 = new Sensor("Chuva", new TypeSensor("Atmosphere"),
                new Local(10, 30, 20),
                new GregorianCalendar(118,8,4).getTime());
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
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere"),
                new Local(12, 31, 21),
                new GregorianCalendar(118,12,4).getTime());
        Sensor s2 = new Sensor("Chuva", new TypeSensor("Atmosphere"),
                new Local(10, 30, 20),
                new GregorianCalendar(118,10,4).getTime());
        SensorList lc = new SensorList(s1);
        lc.addSensor(s2);
        lc.removeSensor(s1);
        boolean expectedResult = true;
        boolean result = lc.containsSensor(s2);
        assertEquals(result, expectedResult);
    }

    @Test
    public void seeIfRemoveSensor_false() {
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere"),
                new Local(12, 31, 21),
                new GregorianCalendar(118,12,4).getTime());
        Sensor s2 = new Sensor("Chuva", new TypeSensor("Atmosphere"),
                new Local(10, 30, 20),
                new GregorianCalendar(118,10,4).getTime());
        SensorList lc = new SensorList(s1);
        lc.addSensor(s2);
        lc.removeSensor(s1);
        boolean expectedResult = false;
        boolean result = lc.containsSensor(s1);
        assertEquals(result, expectedResult);
    }

    @Test
    public void seeIfArrayGetSensors() {
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere"),
                new Local(12, 31, 21),
                new GregorianCalendar(118,12,4).getTime());
        Sensor s2 = new Sensor("Chuva", new TypeSensor("Atmosphere"),
                new Local(10, 30, 20),
                new GregorianCalendar(118,10,4).getTime());
        Sensor[] expectedResult = new Sensor[]{s1, s2};
        Sensor[] result;
        SensorList lc = new SensorList(s1);
        lc.addSensor(s2);
        result = lc.getSensors();
        assertArrayEquals(result, expectedResult);
    }

    @Test
    public void seeIfGetSensorsList() {
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere"),
                new Local(12, 31, 21),
                new GregorianCalendar(118,12,4).getTime());
        Sensor s2 = new Sensor("Chuva", new TypeSensor("Atmosphere"),
                new Local(10, 30, 20),
                new GregorianCalendar(118,10,4).getTime());
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
    public void equalsSameObject() {
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere"),
                new Local(12, 31, 21),
                new GregorianCalendar(118,12,4).getTime());
        SensorList sl = new SensorList(s1);
        boolean actualResult = sl.equals(sl);
        boolean expectedResult = true;
        assertEquals(expectedResult, actualResult);

    }

    @Test
    public void equalsSensorListWithSameContent() {
        Sensor s1 = new Sensor("Chuva", new TypeSensor("Atmosphere"),
                new Local(10, 30, 20),
                new GregorianCalendar(118,12,4).getTime());
        Sensor s2 = new Sensor("Chuva", new TypeSensor("Atmosphere"),
                new Local(10, 30, 20),
                new GregorianCalendar(118,12,4).getTime());
        SensorList sl1 = new SensorList(s1);
        SensorList sl2 = new SensorList(s2);
        boolean actualResult = sl1.equals(sl2);
        boolean expectedResult = true;
        assertEquals(expectedResult, actualResult);

    }

    @Test
    public void equalsSensorListWithDifferentContent() {
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere"),
                new Local(12, 31, 21),
                new GregorianCalendar(118,12,4).getTime());
        Sensor s2 = new Sensor("Chuva", new TypeSensor("Atmosphere"),
                new Local(10, 30, 20),
                new GregorianCalendar(118,12,4).getTime());
        SensorList sl1 = new SensorList(s1);
        SensorList sl2 = new SensorList(s2);
        boolean actualResult = sl1.equals(sl2);
        boolean expectedResult = false;
        assertEquals(expectedResult, actualResult);

    }

    @Test
    public void seeIfGetMostRecentlyUsedSensorWorks() {

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
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere"),
                new Local(12, 31, 21),
                new GregorianCalendar(118,12,4).getTime(), l1);
        Sensor s2 = new Sensor("Chuva", new TypeSensor("Atmosphere"),
                new Local(10, 30, 20),
                new GregorianCalendar(118,10,4).getTime(), l2);
        SensorList list1 = new SensorList(new Sensor[]{s1, s2});
        Sensor expectedResult = new Sensor("Vento", new TypeSensor("Atmosphere"),
                new Local(12, 31, 21),
                new GregorianCalendar(118,12,4).getTime(), l1);
        Sensor actualResult;

        //Act
        actualResult = list1.getMostRecentlyUsedSensor();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfGetMostRecentlyUsedSensorWorksSwitchedSensors() {

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
        Sensor s2 = new Sensor("Vento", new TypeSensor("Atmosphere"),
                new Local(12, 31, 21),
                new GregorianCalendar(118,12,4).getTime(), l1);
        Sensor s1 = new Sensor("Movimento", new TypeSensor("Atmosphere"),
                new Local(10, 30, 20),
                new GregorianCalendar(118,12,4).getTime(), l2);
        SensorList list1 = new SensorList(new Sensor[]{s1, s2});
        Sensor expectedResult = new Sensor("Vento", new TypeSensor("Atmosphere"),
                new Local(12, 31, 21),
                new GregorianCalendar(118,12,4).getTime(), l1);
        Sensor actualResult;

        //Act
        actualResult = list1.getMostRecentlyUsedSensor();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void equalsSensorListWithDifferentObject() {
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere"),
                new Local(12, 31, 21),
                new GregorianCalendar(118,12,4).getTime());
        int teste = 3;
        SensorList sl1 = new SensorList(s1);
        boolean actualResult = sl1.equals(teste);
        boolean expectedResult = false;
        assertEquals(expectedResult, actualResult);

    }

    @Test
    public void hashCodeDummyTest() {
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere"),
                new Local(12, 31, 21),
                new GregorianCalendar(118,12,4).getTime());
        SensorList l1 = new SensorList(s1);
        int expectedResult = 1;
        int actualResult = l1.hashCode();
        assertEquals(expectedResult, actualResult);
    }
}


