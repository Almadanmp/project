package pt.ipp.isep.dei.project.model;

import org.junit.jupiter.api.BeforeEach;
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

    // Common artifacts for testing in this class.
    private TypeSensor validTypeSensor1; // Sensor Type temperature with unit Celsius;
    private TypeSensor validTypeSensor2; // Sensor Type rain with unit millimeters;
    private Local validLocal1; // Localization with Latitude:38, Longitude:7, Altitude: 5
    private Local validLocal2; // Localization with Latitude:100, Longitude:76, Altitude: 59
    private SimpleDateFormat validSdf1; // SimpleDateFormat dd/MM/yyyy
    private SimpleDateFormat validSdf2; // SimpleDateFormat dd/MM/yyyy HH:mm:ss
    private Date validDate1; // Date 04/09/2018
    private Date validDate2; // Date 04/10/2018
    private Date validDate3; // Date 05/10/2018
    private Date validDate4; // Date 20/12/2018
    private Date validDate5; // Date 05/10/2018 23:57
    private GeographicArea validGeographicArea; // Id:Portugal, Type:Country, Length:2000, Width:2000
    private GeographicArea validGeographicArea2; // Id:Spain, Type:Country, Length:2, Width:2
    private SensorList validSensorList1; // Empty Sensor List

    @BeforeEach
    void arrangeArtifacts() {
        validTypeSensor1 = new TypeSensor("Temperature", "Celsius");
        validTypeSensor2 = new TypeSensor("Rain", "mm");
        validLocal1 = new Local(38, 7, 5);
        validLocal2 = new Local(100, 76, 59);
        validSdf1 = new SimpleDateFormat("dd/MM/yyyy");
        validSdf2 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        try {
            validDate1 = validSdf1.parse("04/09/2018");
            validDate2 = validSdf1.parse("04/10/2018");
            validDate3 = validSdf1.parse("05/10/2018");
            validDate4 = validSdf1.parse("20/12/2019");
            validDate5 = validSdf2.parse("05/10/2018 23:57");
        } catch (ParseException c) {
            c.printStackTrace();
        }
        validSensorList1 = new SensorList();
        validGeographicArea = new GeographicArea("Portugal", new TypeArea("Country"), 2000, 2000, validLocal2);
        validGeographicArea2 = new GeographicArea("Spain", new TypeArea("Country"), 2, 2, validLocal2);
    }

    @Test
    void seeIfAddingSensorAlreadyAddedFails() {
        //Arrange
        Sensor s1 = new Sensor("Wind Sensor 1", validTypeSensor1, validLocal1, validDate1);
        Sensor s2 = new Sensor("Rain Sensor 1", validTypeSensor1, validLocal1, validDate2);
        Sensor s3 = new Sensor("Pressure Sensor 1", validTypeSensor1, validLocal1, validDate1);
        validSensorList1.addSensor(s1);
        validSensorList1.addSensor(s2);
        validSensorList1.addSensor(s3);

        //Act
        boolean actualResult = validSensorList1.addSensor(s3);

        //Assert
        assertFalse(actualResult);
    }

    @Test
    void seeIfAddingSameSensorTwiceFails() {
        //Arrange
        Sensor s1 = new Sensor("Wind Sensor 1", validTypeSensor1, validLocal1, validDate1);
        validSensorList1.addSensor(s1);

        //Act
        boolean actualResult = validSensorList1.addSensor(s1);

        //Assert
        assertFalse(actualResult);
    }

    @Test
    void seeIfGetSensorsAsListWorks() {
        //Arrange
        Sensor s1 = new Sensor("Wind Sensor 1", validTypeSensor1, validLocal1, validDate1);
        Sensor s2 = new Sensor("Rain Sensor 1", validTypeSensor1, validLocal1, validDate2);
        List<Sensor> expectedResult = new ArrayList<>();
        expectedResult.add(s1);
        expectedResult.add(s2);
        validSensorList1.addSensor(s1);
        validSensorList1.addSensor(s2);

        //Act
        List<Sensor> result = validSensorList1.getListOfSensors();

        //Assert
        assertEquals(result, expectedResult);
    }

    @Test
    void seeIfEqualsWorksOnSameObject() {
        //Arrange
        Sensor s1 = new Sensor("Wind Sensor 1", validTypeSensor1, validLocal1, validDate1);
        validSensorList1.addSensor(s1);

        //Act
        boolean actualResult = validSensorList1.equals(validSensorList1);

        //Assert
        assertTrue(actualResult);
    }

    @Test
    void seeIfEqualsWorksOnSensorListWithSameContent() {
        //Arrange
        Sensor s1 = new Sensor("Wind Sensor 1", validTypeSensor1, validLocal1, validDate1);
        SensorList sList1 = new SensorList(s1);
        SensorList sList2 = new SensorList(s1);

        //Act
        boolean actualResult = sList1.equals(sList2);

        //Assert
        assertTrue(actualResult);
    }

    @Test
    void seeIfEqualsWorksOnSensorListWithDifferentContent() {
        //Arrange
        Sensor s1 = new Sensor("Wind Sensor 1", validTypeSensor1, validLocal1, validDate1);
        Sensor s2 = new Sensor("Rain Sensor 1", validTypeSensor1, validLocal1, validDate2);
        SensorList slist1 = new SensorList(s1);
        SensorList slist2 = new SensorList(s2);

        //Act
        boolean actualResult = slist1.equals(slist2);

        //Assert
        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsSensorListWithDifferentObject() {
        //Arrange
        Sensor s1 = new Sensor("Wind Sensor 1", validTypeSensor1, validLocal1, validDate1);
        Sensor s2 = new Sensor("Rain Sensor 1", validTypeSensor1, validLocal1, validDate2);
        validSensorList1.addSensor(s1);

        //Act
        boolean actualResult = validSensorList1.equals(s2);

        //Assert
        assertFalse(actualResult);
    }

    @Test
    void seeHashCodeDummyTest() {
        //Arrange
        Sensor s1 = new Sensor("Wind Sensor 1", validTypeSensor1, validLocal1, validDate1);
        validSensorList1.addSensor(s1);
        int expectedResult = 1;

        //Act
        int actualResult = validSensorList1.hashCode();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeItGetSensorListByTypeEquals() {
        //Arrange
        Sensor s1 = new Sensor("Wind Sensor 1", validTypeSensor2, validLocal1, validDate1);
        Sensor s2 = new Sensor("Rain Sensor 1", validTypeSensor1, validLocal1, validDate2);
        Sensor s3 = new Sensor("Pressure Sensor 1", validTypeSensor2, validLocal1, validDate3);
        validSensorList1.addSensor(s1);
        validSensorList1.addSensor(s2);
        validSensorList1.addSensor(s3);

        //Act
        SensorList actualResult = validSensorList1.getSensorListByType("Rain");
        SensorList expectedResult = new SensorList();
        expectedResult.addSensor(s1);
        expectedResult.addSensor(s3);

        //Assert
        expectedResult.equals(actualResult);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeItGetSensorListByTypeContains() {
        //Arrange
        Sensor s1 = new Sensor("Wind Sensor 1", validTypeSensor2, validLocal1, validDate1);
        Sensor s2 = new Sensor("Rain Sensor 1", validTypeSensor1, validLocal1, validDate2);
        Sensor s3 = new Sensor("Pressure Sensor 1", validTypeSensor2, validLocal1, validDate3);
        validSensorList1.addSensor(s1);
        validSensorList1.addSensor(s2);
        validSensorList1.addSensor(s3);
        SensorList expectedResult = new SensorList();
        expectedResult.addSensor(s1);
        expectedResult.addSensor(s3);

        //Act
        SensorList actualResult = validSensorList1.getSensorListByType("Rain");

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeItGetSensorListByTypeDoesNotContain() {
        //Arrange
        Sensor s1 = new Sensor("Wind Sensor 1", validTypeSensor2, validLocal1, validDate1);
        Sensor s2 = new Sensor("Rain Sensor 1", validTypeSensor1, validLocal1, validDate2);
        Sensor s3 = new Sensor("Pressure Sensor 1", validTypeSensor2, validLocal1, validDate3);
        validSensorList1.addSensor(s1);
        validSensorList1.addSensor(s2);
        validSensorList1.addSensor(s3);
        SensorList expectedResult = new SensorList();

        //Act
        SensorList actualResult = validSensorList1.getSensorListByType("Pressure");

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfPrintsSensorWholeList() {
        //Arrange
        Sensor s1 = new Sensor("Temperature Sensor 1", validTypeSensor2, validLocal1, validDate1);
        Sensor s2 = new Sensor("Rain Sensor 1", validTypeSensor1, validLocal1, validDate2);
        Sensor s3 = new Sensor("Pressure Sensor 1", validTypeSensor2, validLocal1, validDate3);
        validSensorList1.addSensor(s1);
        validSensorList1.addSensor(s2);
        validSensorList1.addSensor(s3);

        String expectedResult = "---------------\n" +
                "0) Name: Temperature Sensor 1 | Type: Rain\n" +
                "1) Name: Rain Sensor 1 | Type: Temperature\n" +
                "2) Name: Pressure Sensor 1 | Type: Rain\n" +
                "---------------\n";

        //Act
        String actualResult = validSensorList1.buildSensorWholeListString(validSensorList1);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfPrintsEmptySensorWholeList() {
        //Arrange
        String expectedResult = "Invalid List - List is Empty\n";

        //Act
        String actualResult = validSensorList1.buildSensorWholeListString(validSensorList1);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeReadings() {
        //Arrange
        SensorList sensorList1 = new SensorList(); //EMPTY LIST
        SensorList sensorList2 = new SensorList(); //ONE SENSOR WITH NO READINGS
        SensorList sensorList3 = new SensorList(); //TWO SENSORS: FIRST WITH READINGS
        SensorList sensorList4 = new SensorList(); //TWO SENSORS: SECOND WITH READINGS
        SensorList sensorList5 = new SensorList(); //TWO SENSORS: BOTH WITH READINGS

        //Arrange 2
        Sensor sensor1 = new Sensor("Temperature Sensor 1", validTypeSensor1, validLocal1, validDate1);
        sensorList2.addSensor(sensor1);

        //Arrange 3
        Sensor sensor2 = new Sensor("Temperature Sensor 2", validTypeSensor1, validLocal1, validDate1);
        Sensor sensor3 = new Sensor("Temperature Sensor 3", validTypeSensor1, validLocal1, validDate1);
        Reading reading1 = new Reading(20, validDate1);
        Reading reading2 = new Reading(21, validDate1);
        sensor2.addReading(reading1);
        sensor2.addReading(reading2);
        sensorList3.addSensor(sensor2);
        sensorList3.addSensor(sensor3);

        //Arrange 4
        Sensor sensor4 = new Sensor("Temperature Sensor 4", validTypeSensor1, validLocal1, validDate1);
        Sensor sensor5 = new Sensor("Temperature Sensor 5", validTypeSensor1, validLocal1, validDate1);
        sensor5.addReading(reading1);
        sensor5.addReading(reading2);
        sensorList4.addSensor(sensor4);
        sensorList4.addSensor(sensor5);

        //Arrange 5
        Sensor sensor6 = new Sensor("sensor6", validTypeSensor1, validLocal1, validDate1);
        Sensor sensor7 = new Sensor("sensor7", validTypeSensor1, validLocal1, validDate1);
        Reading reading3 = new Reading(32, validDate1);
        Reading reading4 = new Reading(12, validDate1);
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

        //Act
        ReadingList actualResult1 = sensorList1.getReadings();
        ReadingList actualResult2 = sensorList2.getReadings();
        ReadingList actualResult3 = sensorList3.getReadings();
        ReadingList actualResult4 = sensorList4.getReadings();
        ReadingList actualResult5 = sensorList5.getReadings();

        //Assert
        assertEquals(actualResult1, expectedResult1);
        assertEquals(actualResult2, expectedResult2);
        assertEquals(actualResult3, expectedResult3);
        assertEquals(actualResult4, expectedResult4);
        assertEquals(actualResult5, expectedResult5);
    }

    @Test
    void mostRecentlyUsedSensor() {
        //Arrange
        Date date1 = new Date();
        Date date2 = new Date();
        Date date3 = new Date();
        Date date4 = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        SimpleDateFormat sd2 = new SimpleDateFormat("dd/MM/yyyy");
        try {
            date1 = sd.parse("05/10/2000 23:57");
            date2 = sd.parse("05/10/2000 23:58");
            date3 = sd.parse("05/10/2000 23:59");
            date4 = sd2.parse("01/00/1900");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SensorList sensorList1 = new SensorList(); //EMPTY LIST
        SensorList sensorList2 = new SensorList(); //ONE SENSOR WITH NO READINGS
        SensorList sensorList3 = new SensorList(); //THREE SENSORS: FIRST MOST RECENT
        SensorList sensorList4 = new SensorList(); //THREE SENSORS: SECOND MOST RECENT
        SensorList sensorList5 = new SensorList(); //THREE SENSORS: THIRD MOST RECENT

        Sensor sensor1 = new Sensor("sensor1", validTypeSensor1, date1);
        sensorList2.addSensor(sensor1);

        Sensor sensor2 = new Sensor("sensor2", validTypeSensor1, date1);
        Sensor sensor3 = new Sensor("sensor3", validTypeSensor1, date1);
        Sensor sensor4 = new Sensor("sensor4", validTypeSensor1, date1);
        Reading reading1 = new Reading(20, date3);
        Reading reading2 = new Reading(21, date1);
        Reading reading3 = new Reading(32, date2);

        sensor2.addReading(reading1);
        sensor3.addReading(reading2);
        sensor4.addReading(reading3);
        sensorList3.addSensor(sensor2);
        sensorList3.addSensor(sensor3);
        sensorList3.addSensor(sensor4);

        Sensor sensor5 = new Sensor("sensor5", validTypeSensor1, date1);
        Sensor sensor6 = new Sensor("sensor6", validTypeSensor1, date1);
        Sensor sensor7 = new Sensor("sensor7", validTypeSensor1, date1);
        Reading reading4 = new Reading(20, date1);
        Reading reading5 = new Reading(21, date3);
        Reading reading6 = new Reading(32, date2);

        sensor5.addReading(reading4);
        sensor6.addReading(reading5);
        sensor7.addReading(reading6);
        sensorList4.addSensor(sensor5);
        sensorList4.addSensor(sensor6);
        sensorList4.addSensor(sensor7);

        Sensor sensor8 = new Sensor("sensor8", validTypeSensor1, date1);
        Sensor sensor9 = new Sensor("sensor9", validTypeSensor1, date1);
        Sensor sensor10 = new Sensor("sensor10", validTypeSensor1, date1);
        Reading reading7 = new Reading(20, date2);
        Reading reading8 = new Reading(21, date1);
        Reading reading9 = new Reading(32, date3);

        sensor8.addReading(reading7);
        sensor9.addReading(reading8);
        sensor10.addReading(reading9);
        sensorList5.addSensor(sensor8);
        sensorList5.addSensor(sensor9);
        sensorList5.addSensor(sensor10);

        Sensor expectedResult1 = new Sensor("emptySensor", new TypeSensor("type", " "), date4);
        Sensor expectedResult2 = sensor1;
        Sensor expectedResult3 = sensor2;
        Sensor expectedResult4 = sensor6;
        Sensor expectedResult5 = sensor10;

        //Act
        Sensor actualResult1 = sensorList1.getMostRecentlyUsedSensor();
        Sensor actualResult2 = sensorList2.getMostRecentlyUsedSensor();
        Sensor actualResult3 = sensorList3.getMostRecentlyUsedSensor();
        Sensor actualResult4 = sensorList4.getMostRecentlyUsedSensor();
        Sensor actualResult5 = sensorList5.getMostRecentlyUsedSensor();

        //Assert
        assertEquals(actualResult1, expectedResult1);
        assertEquals(actualResult2, expectedResult2);
        assertEquals(actualResult3, expectedResult3);
        assertEquals(actualResult4, expectedResult4);
        assertEquals(actualResult5, expectedResult5);
    }

    @Test
    void isEmpty() {
        //Arrange
        SensorList sensorList1 = new SensorList(); //EMPTY LIST
        SensorList sensorList2 = new SensorList(); //ONE SENSOR
        SensorList sensorList3 = new SensorList(); //TWO SENSORS

        Sensor sensor1 = new Sensor("sensor1", new TypeSensor("temperature", "ºC"), validDate5);
        sensorList2.addSensor(sensor1);

        Sensor sensor2 = new Sensor("sensor2", new TypeSensor("temperature", "ºC"), validDate5);
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
