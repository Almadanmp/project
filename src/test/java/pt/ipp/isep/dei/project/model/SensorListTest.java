package pt.ipp.isep.dei.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

/**
 * SensorList tests class.
 */

class SensorListTest {

    // Common artifacts for testing in this class.

    private SensorList validSensorList; // Contains the first valid sensor by default.
    private Sensor firstValidSensor;
    private Sensor secondValidSensor;
    private Sensor thirdValidSensor;

    @BeforeEach
    void arrangeArtifacts() {
        validSensorList = new SensorList();
        firstValidSensor = new Sensor("SensorOne", new TypeSensor("Temperature", "Celsius"),
                new Date());
        secondValidSensor = new Sensor("SensorTwo", new TypeSensor("Temperature", "Celsius"),
                new Date());
        thirdValidSensor = new Sensor("SensorThree", new TypeSensor("Rainfall", "l/m2"),
                new Date());
        validSensorList.add(firstValidSensor);
    }


    @Test
    void seeIfEqualsWorksOnSameObject() {
        //Act

        boolean actualResult = validSensorList.equals(validSensorList); // Required for Sonarqube testing purposes.

        //Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfEqualsWorksOnSensorListWithSameContent() {
        // Arrange

        SensorList expectedResult = new SensorList();
        expectedResult.add(firstValidSensor);

        // Act

        boolean actualResult = validSensorList.equals(expectedResult);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfEqualsWorksOnSensorListWithDifferentContent() {
        // Arrange

        SensorList expectedResult = new SensorList();
        expectedResult.add(secondValidSensor);

        // Act

        boolean actualResult = validSensorList.equals(expectedResult);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void hashCodeDummyTest() {
        // Arrange

        int expectedResult = 1;

        // Act

        int actualResult = validSensorList.hashCode();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeItGetSensorListByTypeWorks() {
        // Arrange

        SensorList expectedResult = new SensorList();
        expectedResult.add(firstValidSensor);
        expectedResult.add(secondValidSensor);
        validSensorList.add(secondValidSensor);
        validSensorList.add(thirdValidSensor);

        // Act

        SensorList actualResult = validSensorList.getSensorListByType("Temperature");

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeItGetSensorListByTypeWorksFalse() {
        // Arrange

        SensorList expectedResult = new SensorList();

        // Act

        SensorList actualResult = validSensorList.getSensorListByType("Pressure");

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfToStringWorks() {
        // Arrange

        validSensorList.add(secondValidSensor);
        validSensorList.add(thirdValidSensor);
        String expectedResult = "---------------\n" +
                "0) Name: SensorOne | Type: Temperature\n" +
                "1) Name: SensorTwo | Type: Temperature\n" +
                "2) Name: SensorThree | Type: Rainfall\n" +
                "---------------\n";

        // Act

        String actualResult = validSensorList.toString();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfToStringWorksEmpty() {
        // Arrange

        validSensorList = new SensorList();
        String expectedResult = "Invalid List - List is Empty\n";

        // Act

        String actualResult = validSensorList.toString();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetReadingsWorksNoReadings() {
        // Arrange

        ReadingList expectedResult = new ReadingList();

        // Act

        ReadingList actualResult = validSensorList.getReadings();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetReadingsWorksReadingsAtBeginning() {
        // Arrange

        Reading readingOne = new Reading(31, new Date());
        validSensorList.add(secondValidSensor);
        firstValidSensor.addReading(readingOne);
        ReadingList expectedResult = new ReadingList();
        expectedResult.addReading(readingOne);

        // Act

        ReadingList actualResult = validSensorList.getReadings();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetReadingsWorksReadingsAtEnd() {
        // Arrange

        Reading readingOne = new Reading(31, new Date());
        validSensorList.add(secondValidSensor);
        secondValidSensor.addReading(readingOne);
        ReadingList expectedResult = new ReadingList();
        expectedResult.addReading(readingOne);

        // Act

        ReadingList actualResult = validSensorList.getReadings();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetReadingsWorksAllSensorsHaveReadings() {
        // Arrange

        Reading readingOne = new Reading(31, new Date());
        Reading readingTwo = new Reading(20, new Date());
        validSensorList.add(secondValidSensor);
        secondValidSensor.addReading(readingOne);
        firstValidSensor.addReading(readingTwo);
        ReadingList expectedResult = new ReadingList();
        expectedResult.addReading(readingOne);
        expectedResult.addReading(readingTwo);

        // Act

        ReadingList actualResult = validSensorList.getReadings();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void mostRecentlyUsedSensor() {
        SensorList sensorList1 = new SensorList(); //EMPTY LIST
        SensorList sensorList2 = new SensorList(); //ONE SENSOR WITH NO READINGS
        SensorList sensorList3 = new SensorList(); //THREE SENSORS: FIRST MOST RECENT
        SensorList sensorList4 = new SensorList(); //THREE SENSORS: SECOND MOST RECENT
        SensorList sensorList5 = new SensorList(); //THREE SENSORS: THIRD MOST RECENT
    }

    @Test
    void seeIfGetMostRecentlyUsedSensorWorksNoSensors() {
        // Arrange

        validSensorList = new SensorList();

        // Assert

        assertThrows(IllegalArgumentException.class, validSensorList::getMostRecentlyUsedSensor);
    }

    @Test
    void seeIfGetMostRecentlyUsedSensorWorksThreeSensors() {
        // Assign readings to sensors.

        Reading mostRecentReading = new Reading(3, new GregorianCalendar(2019, Calendar.JANUARY, 1)
                .getTime());
        firstValidSensor.addReading(mostRecentReading);
        Reading secondReading = new Reading(3, new GregorianCalendar(2018, Calendar.JANUARY, 2)
                .getTime());
        secondValidSensor.addReading(secondReading);
        Reading thirdReading = new Reading(3, new GregorianCalendar(2017, Calendar.JANUARY, 1)
                .getTime());
        thirdValidSensor.addReading(thirdReading);

        // Test for when most recent reading is in the first sensor.

        // Arrange

        validSensorList.add(secondValidSensor);
        validSensorList.add(thirdValidSensor);

        // Act

        Sensor actualResult1 = validSensorList.getMostRecentlyUsedSensor();

        // Assert

        assertEquals(firstValidSensor, actualResult1);

        // Test for when most recent reading is in the middle sensor.

        // Arrange

        validSensorList = new SensorList();
        validSensorList.add(secondValidSensor);
        validSensorList.add(firstValidSensor);
        validSensorList.add(thirdValidSensor);

        // Act

        Sensor actualResult2 = validSensorList.getMostRecentlyUsedSensor();

        // Assert

        assertEquals(firstValidSensor, actualResult2);

        // Test for when most recent reading is in the last sensor.

        // Arrange

        validSensorList = new SensorList();
        validSensorList.add(secondValidSensor);
        validSensorList.add(thirdValidSensor);
        validSensorList.add(firstValidSensor);

        // Act

        Sensor actualResult3 = validSensorList.getMostRecentlyUsedSensor();

        // Assert

        assertEquals(firstValidSensor, actualResult3);
    }

    @Test
    void seeIfGetMostRecentlyUsedSensorWorksOneSensorNoReadings() {
        // Assert

        assertThrows(IllegalArgumentException.class, validSensorList::getMostRecentlyUsedSensor);
    }

    @Test
    void seeIfIsEmptyWorks() {
        // Arrange

        SensorList emptyList = new SensorList();
        SensorList twoSensorsList = new SensorList();
        twoSensorsList.add(firstValidSensor);
        twoSensorsList.add(secondValidSensor);

        // Act

        boolean actualResult1 = emptyList.isEmpty();
        boolean actualResult2 = validSensorList.isEmpty();
        boolean actualResult3 = twoSensorsList.isEmpty();

        // Assert

        assertTrue(actualResult1);
        assertFalse(actualResult2);
        assertFalse(actualResult3);
    }

    @Test
    void getSensorsWithReadings() {
        // Arrange

        SensorList emptyList = new SensorList();
        SensorList twoSensorsList = new SensorList();

        Reading readingOne = new Reading(31, new Date());
        secondValidSensor.addReading(readingOne);

        twoSensorsList.add(firstValidSensor);
        twoSensorsList.add(secondValidSensor);

        SensorList expectedResult1 = new SensorList();
        expectedResult1.add(secondValidSensor);

        // Act

        SensorList actualResult1 = twoSensorsList.getSensorsWithReadings();

        // Assert

        assertThrows(IllegalArgumentException.class, emptyList::getSensorsWithReadings);
        assertEquals(expectedResult1, actualResult1);
    }

    @Test
    void getByIndexEmptySensorList() {
        //Arrange

        SensorList emptyList = new SensorList();

        //Act

        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> emptyList.get(0));

        //Assert

        assertEquals("The sensor list is empty.", exception.getMessage());
    }

    @Test
    void getElementsAsArray() {
        //Arrange

        Sensor[] expectedResult1 = new Sensor[0];
        Sensor[] expectedResult2 = new Sensor[1];
        Sensor[] expectedResult3 = new Sensor[2];

        SensorList emptySensorList = new SensorList();
        SensorList validSensorList2 = new SensorList();
        validSensorList2.add(firstValidSensor);
        validSensorList2.add(secondValidSensor);

        expectedResult2[0] = firstValidSensor;
        expectedResult3[0] = firstValidSensor;
        expectedResult3[1] = secondValidSensor;

        //Act

        Sensor[] actualResult1 = emptySensorList.getElementsAsArray();
        Sensor[] actualResult2 = validSensorList.getElementsAsArray();
        Sensor[] actualResult3 = validSensorList2.getElementsAsArray();

        //Assert

        assertArrayEquals(expectedResult1, actualResult1);
        assertArrayEquals(expectedResult2, actualResult2);
        assertArrayEquals(expectedResult3, actualResult3);
    }
}
