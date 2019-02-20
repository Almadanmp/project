package pt.ipp.isep.dei.project.model;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * SensorList tests class.
 */

class SensorListTest {

    @Test
    void seeIfAddSensorsWorks_true() {
        Date d1 = new Date();
        Date d2 = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        try {
            d1 = sd.parse("04/09/118");
            d2 = sd.parse("04/10/118");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21), d1);
        Sensor s2 = new Sensor("Chuva", new TypeSensor("Atmosphere", "l/m2"),
                new Local(10, 30, 20), d2);
        SensorList sl = new SensorList(s1);
        sl.addSensor(s2);
        boolean expectedResult = true;

        boolean actualResult = sl.containsSensor(s2);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfAddSensorWorks_false() {
        Date d1 = new Date();
        Date d2 = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        try {
            d1 = sd.parse("04/10/118");
            d2 = sd.parse("04/09/118");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21), d1);
        Sensor s2 = new Sensor("Chuva", new TypeSensor("Atmosphere", "l/m2"),
                new Local(10, 30, 20), d2);
        SensorList sl = new SensorList(s1);
        boolean actualResult = sl.containsSensor(s2);
        boolean expectedResult = false;
        assertEquals(expectedResult, actualResult);
        System.out.println(actualResult);
    }

    @Test
    void seeIfAddSensorThatExistsWorks_false() {
        Date d1 = new Date();
        Date d2 = new Date();
        Date d3 = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        try {
            d1 = sd.parse("04/10/118");
            d2 = sd.parse("04/12/118");
            d3 = sd.parse("04/08/118");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21), d1);
        Sensor s2 = new Sensor("Chuva", new TypeSensor("Atmosphere", "l/m2"),
                new Local(10, 30, 20), d2);
        Sensor s3 = new Sensor("Chuva", new TypeSensor("Atmosphere", "l/m2"),
                new Local(10, 30, 20), d3);
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
        Date d1 = new Date();
        Date d2 = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        try {
            d1 = sd.parse("04/12/118");
            d2 = sd.parse("04/10/118");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21), d1);
        Sensor s2 = new Sensor("Chuva", new TypeSensor("Atmosphere", "l/m2"),
                new Local(10, 30, 20), d2);
        SensorList lc = new SensorList(s1);
        lc.addSensor(s2);
        lc.removeSensor(s1);
        boolean expectedResult = true;
        boolean result = lc.containsSensor(s2);
        assertEquals(result, expectedResult);
    }

    @Test
    void seeIfRemoveSensor_false() {
        Date d1 = new Date();
        Date d2 = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        try {
            d1 = sd.parse("04/12/118");
            d2 = sd.parse("04/10/118");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21), d1);
        Sensor s2 = new Sensor("Chuva", new TypeSensor("Atmosphere", "l/m2"),
                new Local(10, 30, 20), d2);
        SensorList lc = new SensorList(s1);
        lc.addSensor(s2);
        lc.removeSensor(s1);
        boolean expectedResult = false;
        boolean result = lc.containsSensor(s1);
        assertEquals(result, expectedResult);
    }

    @Test
    void seeIfArrayGetSensors() {
        Date d1 = new Date();
        Date d2 = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        try {
            d1 = sd.parse("04/12/118");
            d2 = sd.parse("04/10/118");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21), d1);
        Sensor s2 = new Sensor("Chuva", new TypeSensor("Atmosphere", "l/m2"),
                new Local(10, 30, 20), d2);
        Sensor[] expectedResult = new Sensor[]{s1, s2};
        Sensor[] result;
        SensorList lc = new SensorList(s1);
        lc.addSensor(s2);
        result = lc.getSensors();
        assertArrayEquals(result, expectedResult);
    }

    @Test
    void seeIfGetSensorsList() {
        Date d1 = new Date();
        Date d2 = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        try {
            d1 = sd.parse("04/12/118");
            d2 = sd.parse("04/10/118");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21), d1);
        Sensor s2 = new Sensor("Chuva", new TypeSensor("Atmosphere", "l/m2"),
                new Local(10, 30, 20), d2);
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
    void seeIfEqualsSameObject() {
        Date d1 = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        try {
            d1 = sd.parse("04/12/118");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21), d1);
        SensorList sl = new SensorList(s1);
        boolean actualResult = sl.equals(sl);
        assertTrue(actualResult);

    }

    @Test
    void seeIfequalsSensorListWithSameContent() {
        Date d1 = new Date();
        Date d2 = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        try {
            d1 = sd.parse("04/12/118");
            d2 = sd.parse("04/12/118");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21), d1);
        Sensor s2 = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
                new Local(10, 30, 20), d2);
        SensorList sl1 = new SensorList(s1);
        SensorList sl2 = new SensorList(s2);
        boolean actualResult = sl1.equals(sl2);
        boolean expectedResult = true;
        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfequalsSensorListWithDifferentContent() {
        Date d1 = new Date();
        Date d2 = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        try {
            d1 = sd.parse("04/12/118");
            d2 = sd.parse("04/10/118");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21), d1);
        Sensor s2 = new Sensor("Chuva", new TypeSensor("Atmosphere", "l/m2"),
                new Local(10, 30, 20), d2);
        SensorList sl1 = new SensorList(s1);
        SensorList sl2 = new SensorList(s2);
        boolean actualResult = sl1.equals(sl2);
        boolean expectedResult = false;
        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfEqualsSensorListWithDifferentObject() {
        Date d1 = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        try {
            d1 = sd.parse("04/12/118");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21), d1);
        int teste = 3;
        SensorList sl1 = new SensorList(s1);
        boolean actualResult = sl1.equals(teste);
        assertFalse(actualResult);

    }

    @Test
    void seeHashCodeDummyTest() {
        Date d1 = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        try {
            d1 = sd.parse("04/12/118");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21), d1);
        SensorList l1 = new SensorList(s1);
        int expectedResult = 1;
        int actualResult = l1.hashCode();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getSensorsInGAInACertainTimePeriod() {
        Date d1 = new Date();
        Date d2 = new Date();
        Date d3 = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        try {
            d1 = sd.parse("01/05/2000");
            d2 = sd.parse("01/07/2000");
            d3 = sd.parse("01/11/2000");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Sensor s1 = new Sensor("s1", new TypeSensor("temperatura", "Celsius"), new Local(20, 14, 15), d1);
        Sensor s2 = new Sensor("s2", new TypeSensor("temperatura", "Celsius"), new Local(15, 19, 15), d2);
        Sensor s3 = new Sensor("s2", new TypeSensor("temperatura", "Celsius"), new Local(15, 19, 15), d3);
        SensorList sensorList = new SensorList(s1);
        sensorList.addSensor(s1);
        sensorList.addSensor(s2);
        sensorList.addSensor(s3);
        GeographicArea ga1 = new GeographicArea("Portugal", new TypeArea("cidade"), 10, 20, new Local(16, 17, 18));
        ga1.setSensorList(sensorList);
        Date d4 = new Date();
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
        try {
            d4 = s.parse("05/10/2000");
        } catch (ParseException c) {
            c.printStackTrace();
        }
        List<Sensor> result = sensorList.getSensorsInGAAtACertainTimePeriod(d4, ga1);
        List<Sensor> expectedResult = new ArrayList<>();
        expectedResult.add(s1);
        expectedResult.add(s2);
        assertEquals(expectedResult, result);
    }

    @Test
    void getSensorsInGAInACertainTimePeriodFalse() {
        Date d1 = new Date();
        Date d2 = new Date();
        Date d3 = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        try {
            d1 = sd.parse("08/10/2000");
            d2 = sd.parse("02/11/2000");
            d3 = sd.parse("01/11/2000");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Sensor s1 = new Sensor("s1", new TypeSensor("temperatura", "Celsius"), new Local(20, 14, 15), d1);
        Sensor s2 = new Sensor("s2", new TypeSensor("temperatura", "Celsius"), new Local(15, 19, 15), d2);
        Sensor s3 = new Sensor("s2", new TypeSensor("temperatura", "Celsius"), new Local(15, 19, 15), d3);
        SensorList sensorList = new SensorList(s1);
        sensorList.addSensor(s1);
        sensorList.addSensor(s2);
        sensorList.addSensor(s3);
        GeographicArea ga1 = new GeographicArea("Portugal", new TypeArea("cidade"), 10, 20, new Local(16, 17, 18));
        ga1.setSensorList(sensorList);
        Date date1 = new Date();
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
        try {
            date1 = s.parse("05/10/2000");
        } catch (ParseException c) {
            c.printStackTrace();
        }
        List<Sensor> result = sensorList.getSensorsInGAAtACertainTimePeriod(date1, ga1);
        List<Sensor> expectedResult = new ArrayList<>();
        assertEquals(expectedResult, result);
    }

    @Test
    void getSensorsInGAInACertainTimePeriodWrongArea() {
        Date d1 = new Date();
        Date d2 = new Date();
        Date d3 = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        try {
            d1 = sd.parse("01/05/2000");
            d2 = sd.parse("01/07/2000");
            d3 = sd.parse("01/11/2000");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Sensor s1 = new Sensor("s1", new TypeSensor("temperatura", "Celsius"), new Local(20, 14, 15), d1);
        Sensor s2 = new Sensor("s2", new TypeSensor("temperatura", "Celsius"), new Local(50, 19, 15), d2);
        Sensor s3 = new Sensor("s2", new TypeSensor("temperatura", "Celsius"), new Local(15, 19, 15), d3);
        SensorList sensorList = new SensorList(s1);
        sensorList.addSensor(s1);
        sensorList.addSensor(s2);
        sensorList.addSensor(s3);
        GeographicArea ga1 = new GeographicArea("Portugal", new TypeArea("cidade"), 10, 20, new Local(16, 17, 18));
        ga1.setSensorList(sensorList);
        Date date1 = new Date();
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
        try {
            date1 = s.parse("05/10/2000");
        } catch (ParseException c) {
            c.printStackTrace();
        }
        List<Sensor> result = sensorList.getSensorsInGAAtACertainTimePeriod(date1, ga1);
        List<Sensor> expectedResult = new ArrayList<>();
        expectedResult.add(s1);

        assertEquals(expectedResult, result);
    }

    @Test
    void seeItGetSensorListByTypeEquals() {
        //Arrange
        TypeSensor t1 = new TypeSensor("Rain", "l/m2");
        TypeSensor t2 = new TypeSensor("Vento", "km/h");
        Date d1 = new Date();
        Date d2 = new Date();
        Date d3 = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        try {
            d1 = sd.parse("08/10/2000");
            d2 = sd.parse("02/11/2000");
            d3 = sd.parse("01/11/2000");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Sensor s1 = new Sensor("s1", t1, new Local(15, 16, 50), d1);
        Sensor s2 = new Sensor("s2", t2, new Local(16, 17, 50), d2);
        Sensor s3 = new Sensor("s3", t1, new Local(0, 0, 50), d3);
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
        Date d1 = new Date();
        Date d2 = new Date();
        Date d3 = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        try {
            d1 = sd.parse("08/10/2000");
            d2 = sd.parse("02/11/2000");
            d3 = sd.parse("01/11/2000");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Sensor s1 = new Sensor("s1", t1, new Local(15, 16, 50), d1);
        Sensor s2 = new Sensor("s2", t2, new Local(16, 17, 50), d2);
        Sensor s3 = new Sensor("s3", t1, new Local(0, 0, 50), d3);
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
        Date d1 = new Date();
        Date d2 = new Date();
        Date d3 = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        try {
            d1 = sd.parse("08/10/2000");
            d2 = sd.parse("02/11/2000");
            d3 = sd.parse("01/11/2000");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Sensor s1 = new Sensor("s1", t1, new Local(15, 16, 50), d1);
        Sensor s2 = new Sensor("s2", t2, new Local(16, 17, 50), d2);
        Sensor s3 = new Sensor("s3", t1, new Local(0, 0, 50), d3);
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
        Date d1 = new Date();
        Date d2 = new Date();
        Date d3 = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        try {
            d1 = sd.parse("08/10/2000");
            d2 = sd.parse("02/11/2000");
            d3 = sd.parse("01/11/2000");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Sensor s1 = new Sensor("s1", t1, new Local(15, 16, 50), d1);
        Sensor s2 = new Sensor("s2", t2, new Local(16, 17, 50), d2);
        Sensor s3 = new Sensor("s3", t1, new Local(0, 0, 50), d3);
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
    void ensureThatSensorIsInSensorListByString() {
        Room room = new Room("Quarto Miki", 1, 3, 3, 3);
        TypeSensor t1 = new TypeSensor("Rain", "l/m2");
        TypeSensor t2 = new TypeSensor("Vento", "km/h");
        Date d1 = new Date();
        Date d2 = new Date();
        Date d3 = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        try {
            d1 = sd.parse("08/10/2000");
            d2 = sd.parse("02/11/2000");
            d3 = sd.parse("01/11/2000");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Sensor s1 = new Sensor("s1", t1, new Local(15, 16, 50), d1);
        Sensor s2 = new Sensor("s2", t2, new Local(16, 17, 50), d2);
        Sensor s3 = new Sensor("s3", t1, new Local(0, 0, 50), d3);
        SensorList sensorList1 = new SensorList(s1);
        sensorList1.addSensor(s1);
        sensorList1.addSensor(s2);
        sensorList1.addSensor(s3);
        room.setSensorList(sensorList1);
        boolean expectedResult = true;
        boolean actualResult = sensorList1.doesSensorListContainSensorByName("s1");
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void ensureThatSensorIsNotInSensorListByString() {
        Room room = new Room("Quarto Miki", 1, 3, 3, 3);
        TypeSensor t1 = new TypeSensor("Rain", "l/m2");
        TypeSensor t2 = new TypeSensor("Vento", "km/h");
        Date d1 = new Date();
        Date d2 = new Date();
        Date d3 = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        try {
            d1 = sd.parse("08/10/2000");
            d2 = sd.parse("02/11/2000");
            d3 = sd.parse("01/11/2000");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Sensor s1 = new Sensor("s1", t1, new Local(15, 16, 50), d1);
        Sensor s2 = new Sensor("s2", t2, new Local(16, 17, 50), d2);
        Sensor s3 = new Sensor("s3", t1, new Local(0, 0, 50), d3);
        SensorList sensorList1 = new SensorList(s1);
        sensorList1.addSensor(s1);
        sensorList1.addSensor(s2);
        sensorList1.addSensor(s3);
        room.setSensorList(sensorList1);
        boolean expectedResult = false;
        boolean actualResult = sensorList1.doesSensorListContainSensorByName("s4");
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfPrintsSensorWholeList() {
        Room room = new Room("Quarto Miki", 1, 3, 3, 3);
        TypeSensor t1 = new TypeSensor("Rain", "l/m2");
        TypeSensor t2 = new TypeSensor("Vento", "km/h");
        Date d1 = new Date();
        Date d2 = new Date();
        Date d3 = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        try {
            d1 = sd.parse("08/10/2000");
            d2 = sd.parse("02/11/2000");
            d3 = sd.parse("01/11/2000");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Sensor s1 = new Sensor("s1", t1, new Local(15, 16, 50), d1);
        Sensor s2 = new Sensor("s2", t2, new Local(16, 17, 50), d2);
        Sensor s3 = new Sensor("s3", t1, new Local(0, 0, 50), d3);
        SensorList sensorList1 = new SensorList(s1);
        sensorList1.addSensor(s1);
        sensorList1.addSensor(s2);
        sensorList1.addSensor(s3);
        room.setSensorList(sensorList1);
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
        room.setSensorList(sensorList1);
        String expectedResult = "Invalid List - List is Empty\n";
        String result = sensorList1.buildSensorWholeListString(sensorList1);
        assertEquals(expectedResult, result);
    }

    @Test
    void seeReadings() {
        Date date1 = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        try {
            date1 = sd.parse("05/10/2000");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SensorList sensorList1 = new SensorList(); //EMPTY LIST
        SensorList sensorList2 = new SensorList(); //ONE SENSOR WITH NO READINGS
        SensorList sensorList3 = new SensorList(); //TWO SENSORS: FIRST WITH READINGS
        SensorList sensorList4 = new SensorList(); //TWO SENSORS: SECOND WITH READINGS
        SensorList sensorList5 = new SensorList(); //TWO SENSORS: BOTH WITH READINGS

        Sensor sensor1 = new Sensor("sensor1", new TypeSensor("temperature", "ºC"), date1);
        sensorList2.addSensor(sensor1);

        Sensor sensor2 = new Sensor("sensor2", new TypeSensor("temperature", "ºC"), date1);
        Sensor sensor3 = new Sensor("sensor3", new TypeSensor("temperature", "ºC"), date1);
        Reading reading1 = new Reading(20, date1);
        Reading reading2 = new Reading(21, date1);
        sensor2.addReading(reading1);
        sensor2.addReading(reading2);
        sensorList3.addSensor(sensor2);
        sensorList3.addSensor(sensor3);

        Sensor sensor4 = new Sensor("sensor4", new TypeSensor("temperature", "ºC"), date1);
        Sensor sensor5 = new Sensor("sensor5", new TypeSensor("temperature", "ºC"), date1);
        sensor5.addReading(reading1);
        sensor5.addReading(reading2);
        sensorList4.addSensor(sensor4);
        sensorList4.addSensor(sensor5);

        Sensor sensor6 = new Sensor("sensor6", new TypeSensor("temperature", "ºC"), date1);
        Sensor sensor7 = new Sensor("sensor7", new TypeSensor("temperature", "ºC"), date1);
        Reading reading3 = new Reading(32, date1);
        Reading reading4 = new Reading(12, date1);
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
        Date date1 = new Date();
        Date date2 = new Date();
        Date date3 = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        try {
            date1 = sd.parse("05/10/2000 23:57");
            date2 = sd.parse("05/10/2000 23:58");
            date3 = sd.parse("05/10/2000 23:59");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SensorList sensorList1 = new SensorList(); //EMPTY LIST
        SensorList sensorList2 = new SensorList(); //ONE SENSOR WITH NO READINGS
        SensorList sensorList3 = new SensorList(); //THREE SENSORS: FIRST MOST RECENT
        SensorList sensorList4 = new SensorList(); //THREE SENSORS: SECOND MOST RECENT
        SensorList sensorList5 = new SensorList(); //THREE SENSORS: THIRD MOST RECENT

        Sensor sensor1 = new Sensor("sensor1", new TypeSensor("temperature", "ºC"), date1);
        sensorList2.addSensor(sensor1);

        Sensor sensor2 = new Sensor("sensor2", new TypeSensor("temperature", "ºC"), date1);
        Sensor sensor3 = new Sensor("sensor3", new TypeSensor("temperature", "ºC"), date1);
        Sensor sensor4 = new Sensor("sensor4", new TypeSensor("temperature", "ºC"), date1);
        Reading reading1 = new Reading(20, date3);
        Reading reading2 = new Reading(21, date1);
        Reading reading3 = new Reading(32, date2);

        sensor2.addReading(reading1);
        sensor3.addReading(reading2);
        sensor4.addReading(reading3);
        sensorList3.addSensor(sensor2);
        sensorList3.addSensor(sensor3);
        sensorList3.addSensor(sensor4);

        Sensor sensor5 = new Sensor("sensor5", new TypeSensor("temperature", "ºC"), date1);
        Sensor sensor6 = new Sensor("sensor6", new TypeSensor("temperature", "ºC"), date1);
        Sensor sensor7 = new Sensor("sensor7", new TypeSensor("temperature", "ºC"), date1);
        Reading reading4 = new Reading(20, date1);
        Reading reading5 = new Reading(21, date3);
        Reading reading6 = new Reading(32, date2);

        sensor5.addReading(reading4);
        sensor6.addReading(reading5);
        sensor7.addReading(reading6);
        sensorList4.addSensor(sensor5);
        sensorList4.addSensor(sensor6);
        sensorList4.addSensor(sensor7);

        Sensor sensor8 = new Sensor("sensor8", new TypeSensor("temperature", "ºC"), date1);
        Sensor sensor9 = new Sensor("sensor9", new TypeSensor("temperature", "ºC"), date1);
        Sensor sensor10 = new Sensor("sensor10", new TypeSensor("temperature", "ºC"), date1);
        Reading reading7 = new Reading(20, date2);
        Reading reading8 = new Reading(21, date1);
        Reading reading9 = new Reading(32, date3);

        sensor8.addReading(reading7);
        sensor9.addReading(reading8);
        sensor10.addReading(reading9);
        sensorList5.addSensor(sensor8);
        sensorList5.addSensor(sensor9);
        sensorList5.addSensor(sensor10);

        Date date4 = new Date();
        Date date5 = new Date();
        SimpleDateFormat sd2 = new SimpleDateFormat("dd/MM/yyyy");
        try {
            date4 = sd2.parse("01/00/1900");
            date5 = sd2.parse("01/00/1900");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Sensor expectedResult1 = new Sensor("emptySensor", new TypeSensor("type", " "), date4);
        Sensor expectedResult2 = new Sensor("emptySensor", new TypeSensor("type", " "), date5);
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
        Date date1 = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        try {
            date1 = sd.parse("05/10/2018 23:57");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SensorList sensorList1 = new SensorList(); //EMPTY LIST
        SensorList sensorList2 = new SensorList(); //ONE SENSOR WITHOUT READINGS
        SensorList sensorList3 = new SensorList(); //TWO SENSORS WITHOUT READINGS
        SensorList sensorList4 = new SensorList(); //THREE SENSORS: FIRST HAS READINGS
        SensorList sensorList5 = new SensorList(); //THREE SENSORS: LAST HAS READINGS

        Sensor sensor1 = new Sensor("sensor1", new TypeSensor("temperature", "ºC"), date1);
        sensorList2.addSensor(sensor1);

        Sensor sensor2 = new Sensor("sensor2", new TypeSensor("temperature", "ºC"), date1);
        Sensor sensor3 = new Sensor("sensor3", new TypeSensor("temperature", "ºC"), date1);
        sensorList3.addSensor(sensor2);
        sensorList3.addSensor(sensor3);

        Sensor sensor5 = new Sensor("sensor5", new TypeSensor("temperature", "ºC"), date1);
        Sensor sensor6 = new Sensor("sensor6", new TypeSensor("temperature", "ºC"), date1);
        Sensor sensor7 = new Sensor("sensor7", new TypeSensor("temperature", "ºC"), date1);
        Reading reading4 = new Reading(20, date1);
        sensor5.addReading(reading4);
        sensorList4.addSensor(sensor5);
        sensorList4.addSensor(sensor6);
        sensorList4.addSensor(sensor7);

        Sensor sensor8 = new Sensor("sensor8", new TypeSensor("temperature", "ºC"), date1);
        Sensor sensor9 = new Sensor("sensor9", new TypeSensor("temperature", "ºC"), date1);
        Sensor sensor10 = new Sensor("sensor10", new TypeSensor("temperature", "ºC"), date1);
        Reading reading7 = new Reading(26, date1);
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

    @Test
    void isEmpty() {
        Date date1 = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        try {
            date1 = sd.parse("05/10/2018 23:57");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //Arrange
        SensorList sensorList1 = new SensorList(); //EMPTY LIST
        SensorList sensorList2 = new SensorList(); //ONE SENSOR
        SensorList sensorList3 = new SensorList(); //TWO SENSORS

        Sensor sensor1 = new Sensor("sensor1", new TypeSensor("temperature", "ºC"), date1);
        sensorList2.addSensor(sensor1);

        Sensor sensor2 = new Sensor("sensor2", new TypeSensor("temperature", "ºC"), date1);
        sensorList3.addSensor(sensor1);
        sensorList3.addSensor(sensor2);

        //Act
        boolean actualResult1 = sensorList1.isEmpty();
        boolean actualResult2 = sensorList2.isEmpty();
        boolean actualResult3 = sensorList3.isEmpty();

        //Assert
        assertTrue(actualResult1);
        assertFalse(actualResult2);
        assertFalse(actualResult3);
    }
}
