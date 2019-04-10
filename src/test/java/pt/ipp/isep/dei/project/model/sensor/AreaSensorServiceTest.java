package pt.ipp.isep.dei.project.model.sensor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.repository.AreaSensorRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * AreaSensorList tests class.
 */
@ExtendWith(MockitoExtension.class)
class AreaSensorServiceTest {

    // Common artifacts for testing in this class.


    private AreaSensor firstValidAreaSensor;
    private AreaSensor secondValidAreaSensor;
    private AreaSensor thirdValidAreaSensor;
    private Date validDate1; // Date 21/11/2018
    private Date validDate2; // Date 03/09/2018

    @Mock
    AreaSensorRepository areaSensorRepository;

    private AreaSensorService validAreaSensorService; // Contains the first valid sensor by default.


    @BeforeEach
    void arrangeArtifacts() {
        MockitoAnnotations.initMocks(this);
        validAreaSensorService = new AreaSensorService(areaSensorRepository);
        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            validDate1 = validSdf.parse("21/11/2018 00:00:00");
            validDate2 = validSdf.parse("03/09/2018 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        firstValidAreaSensor = new AreaSensor("SensorOne", "SensorOne", new SensorType("Temperature", "Celsius"), new Local(2, 2, 2), validDate1, 6008L);
        firstValidAreaSensor.setActive(true);
        secondValidAreaSensor = new AreaSensor("SensorTwo", "SensorTwo", new SensorType("Temperature", "Celsius"), new Local(10, 10, 10),
                validDate1, 6008L);
        secondValidAreaSensor.setActive(true);
        thirdValidAreaSensor = new AreaSensor("SensorThree", "SensorThree", new SensorType("Rainfall", "l/m2"), new Local(10, 10, 10),
                validDate1, 6008L);
        validAreaSensorService.add(firstValidAreaSensor);
    }


    @Test
    void seeIfEqualsWorksOnSameObject() {
        //Act

        boolean actualResult = validAreaSensorService.equals(validAreaSensorService); // Required for Sonarqube testing purposes.

        //Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfEqualsWorksOnDiffObject() {
        //Act

        boolean actualResult = validAreaSensorService.equals(20D); // Required for Sonarqube testing purposes.

        //Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksOnSensorListWithSameContent() {
        // Arrange

        AreaSensorService expectedResult = new AreaSensorService();
        expectedResult.add(firstValidAreaSensor);

        // Act

        boolean actualResult = validAreaSensorService.equals(expectedResult);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfSensorExistsInRepository() {
        //Arrange
        AreaSensor areaSensor = new AreaSensor("SensorOne", "SensorOne", new SensorType("Temperature", "Celsius"), new Local(
                31, 1, 2), new Date(), 6008L);
        areaSensor.setActive(true);

        validAreaSensorService.addWithPersist(areaSensor);


        //Assert
        assertFalse(validAreaSensorService.sensorExistsInRepository("SensorOne"));
    }

    @Test
    void seeIfEqualsWorksOnSensorListWithDifferentContent() {
        // Arrange

        AreaSensorService expectedResult = new AreaSensorService();
        expectedResult.add(secondValidAreaSensor);

        // Act

        boolean actualResult = validAreaSensorService.equals(expectedResult);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void hashCodeDummyTest() {
        // Arrange

        int expectedResult = 1;

        // Act

        int actualResult = validAreaSensorService.hashCode();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfAddPersist() {
        AreaSensor areaSensor = new AreaSensor("Sensor", "Sensor", new SensorType("Temperature", "Celsius"), new Local(
                31, 1, 2), new Date(), 6008L);
        areaSensor.setActive(true);

        assertTrue(validAreaSensorService.addWithPersist(areaSensor));
        assertFalse(validAreaSensorService.addWithPersist(areaSensor));
    }

    @Test
    void seeItGetSensorListByTypeWorks() {
        // Arrange

        AreaSensorService expectedResult = new AreaSensorService();
        expectedResult.add(firstValidAreaSensor);
        expectedResult.add(secondValidAreaSensor);
        validAreaSensorService.add(secondValidAreaSensor);
        validAreaSensorService.add(thirdValidAreaSensor);

        // Act

        AreaSensorService actualResult = validAreaSensorService.getSensorListByType("Temperature");

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeItGetSensorListByTypeWorksFalse() {
        // Arrange

        AreaSensorService expectedResult = new AreaSensorService();

        // Act

        AreaSensorService actualResult = validAreaSensorService.getSensorListByType("Pressure");

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfToStringWorks() {
        // Arrange

        List<AreaSensor> areaSensors = new ArrayList<>();
        areaSensors.add(secondValidAreaSensor);
        areaSensors.add(thirdValidAreaSensor);
        String expectedResult =
                "---------------\n" +
                        "SensorTwo) Name: SensorTwo | Type: Temperature | Active\n" +
                        "SensorThree) Name: SensorThree | Type: Rainfall | Active\n" +
                        "---------------\n";

        // Act

        String actualResult = validAreaSensorService.buildString(areaSensors);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfToStringWorksEmpty() {
        // Arrange
        List<AreaSensor> areaSensors = new ArrayList<>();
        String expectedResult = "Invalid List - List is Empty\n";

        // Act

        String actualResult = validAreaSensorService.buildString(areaSensors);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetMostRecentlyUsedSensorWorksNoSensors() {
        // Arrange

        validAreaSensorService = new AreaSensorService();

        // Assert

        assertThrows(IllegalArgumentException.class, validAreaSensorService::getMostRecentlyUsedSensor);
    }

    @Test
    void seeIfGetMostRecentlyUsedSensorWorksThreeSensors() {
        // Assign readings to sensors.

        Reading mostRecentReading = new Reading(3, new GregorianCalendar(2019, Calendar.JANUARY, 1)
                .getTime(), "C", firstValidAreaSensor.getId());
        firstValidAreaSensor.addReading(mostRecentReading);
        Reading secondReading = new Reading(3, new GregorianCalendar(2018, Calendar.JANUARY, 2)
                .getTime(), "C", secondValidAreaSensor.getId());
        secondValidAreaSensor.addReading(secondReading);
        Reading thirdReading = new Reading(3, new GregorianCalendar(2017, Calendar.JANUARY, 1)
                .getTime(), "C", thirdValidAreaSensor.getId());
        thirdValidAreaSensor.addReading(thirdReading);

        // Test for when most recent reading is in the first sensor.

        // Arrange

        validAreaSensorService.add(secondValidAreaSensor);
        validAreaSensorService.add(thirdValidAreaSensor);

        // Act

        AreaSensor actualResult1 = validAreaSensorService.getMostRecentlyUsedSensor();

        // Assert

        assertEquals(firstValidAreaSensor, actualResult1);

        // Test for when most recent reading is in the middle sensor.

        // Arrange

        validAreaSensorService = new AreaSensorService();
        validAreaSensorService.add(secondValidAreaSensor);
        validAreaSensorService.add(firstValidAreaSensor);
        validAreaSensorService.add(thirdValidAreaSensor);

        // Act

        AreaSensor actualResult2 = validAreaSensorService.getMostRecentlyUsedSensor();

        // Assert

        assertEquals(firstValidAreaSensor, actualResult2);

        // Test for when most recent reading is in the last sensor.

        // Arrange

        validAreaSensorService = new AreaSensorService();
        validAreaSensorService.add(secondValidAreaSensor);
        validAreaSensorService.add(thirdValidAreaSensor);
        validAreaSensorService.add(firstValidAreaSensor);

        // Act

        AreaSensor actualResult3 = validAreaSensorService.getMostRecentlyUsedSensor();

        // Assert

        assertEquals(firstValidAreaSensor, actualResult3);
    }

    @Test
    void seeIfGetMostRecentlyUsedSensorWorksOneSensorNoReadings() {
        // Assert

        assertThrows(IllegalArgumentException.class, validAreaSensorService::getMostRecentlyUsedSensor);
    }

    @Test
    void seeIfIsEmptyWorks() {
        // Arrange

        AreaSensorService emptyList = new AreaSensorService();
        AreaSensorService twoSensorsList = new AreaSensorService();
        twoSensorsList.add(firstValidAreaSensor);
        twoSensorsList.add(secondValidAreaSensor);
        twoSensorsList.add(secondValidAreaSensor);

        // Act

        boolean actualResult1 = emptyList.isEmpty();
        boolean actualResult2 = validAreaSensorService.isEmpty();
        boolean actualResult3 = twoSensorsList.isEmpty();

        // Assert

        assertTrue(actualResult1);
        assertFalse(actualResult2);
        assertFalse(actualResult3);
    }

    @Test
    void getSensorsWithReadings() {
        // Arrange

        AreaSensorService emptyList = new AreaSensorService();
        AreaSensorService twoSensorsList = new AreaSensorService();

        Reading readingOne = new Reading(31, new GregorianCalendar(2018, Calendar.MARCH, 1).getTime(), "C", secondValidAreaSensor.getId());
        secondValidAreaSensor.addReading(readingOne);

        twoSensorsList.add(firstValidAreaSensor);
        twoSensorsList.add(secondValidAreaSensor);

        AreaSensorService expectedResult1 = new AreaSensorService();
        expectedResult1.add(secondValidAreaSensor);

        // Act

        AreaSensorService actualResult1 = twoSensorsList.getSensorsWithReadings();

        // Assert

        assertThrows(IllegalArgumentException.class, emptyList::getSensorsWithReadings);
        assertEquals(expectedResult1, actualResult1);
    }

    @Test
    void seeIfGetByIndexWorks() {
        //Arrange

        validAreaSensorService.add(secondValidAreaSensor);

        //Act

        AreaSensor actualResult1 = validAreaSensorService.get(0);
        AreaSensor actualResult2 = validAreaSensorService.get(1);

        //Assert

        assertEquals(firstValidAreaSensor, actualResult1);
        assertEquals(secondValidAreaSensor, actualResult2);
    }

    @Test
    void getByIndexEmptySensorList() {
        //Arrange

        AreaSensorService emptyList = new AreaSensorService();

        //Act

        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> emptyList.get(0));

        //Assert

        assertEquals("The sensor list is empty.", exception.getMessage());
    }

    @Test
    void getElementsAsArray() {
        //Arrange

        AreaSensor[] expectedResult1 = new AreaSensor[0];
        AreaSensor[] expectedResult2 = new AreaSensor[1];
        AreaSensor[] expectedResult3 = new AreaSensor[2];

        AreaSensorService emptyAreaSensorService = new AreaSensorService();
        AreaSensorService validAreaSensorService2 = new AreaSensorService();
        validAreaSensorService2.add(firstValidAreaSensor);
        validAreaSensorService2.add(secondValidAreaSensor);

        expectedResult2[0] = firstValidAreaSensor;
        expectedResult3[0] = firstValidAreaSensor;
        expectedResult3[1] = secondValidAreaSensor;

        //Act

        AreaSensor[] actualResult1 = emptyAreaSensorService.getElementsAsArray();
        AreaSensor[] actualResult2 = validAreaSensorService.getElementsAsArray();
        AreaSensor[] actualResult3 = validAreaSensorService2.getElementsAsArray();

        //Assert

        assertArrayEquals(expectedResult1, actualResult1);
        assertArrayEquals(expectedResult2, actualResult2);
        assertArrayEquals(expectedResult3, actualResult3);
    }

    @Test
    void seeIfGetSensorsByDistanceToHouseWorks() {
        //Arrange

        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();

        try {
            date = validSdf.parse("10/02/2017 10:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //Sensors

        AreaSensor areaSensorSameLocalHouse = new AreaSensor("123", "sameLocalAsHouse", new SensorType("Temperature", "K"), new Local(20, 20, 20), date, 6008L);
        AreaSensor areaSensorDiffLocalHouse = new AreaSensor("125", "DiffLocalAsHouse", new SensorType("Temperature", "K"), new Local(20, 25, 20), date, 6008L);

        AreaSensorService validAreaSensorService = new AreaSensorService();
        validAreaSensorService.add(areaSensorDiffLocalHouse);
        validAreaSensorService.add(areaSensorSameLocalHouse);

        //House

        List<String> deviceTypeString = new ArrayList<>();
        Address address = new Address("Rua Dr. António Bernardino de Almeida", "431", "4200-072", "Porto", "Portugal");
        House house = new House("ISEP", address, new Local(20, 20, 20), 60,
                180, deviceTypeString);
        house.setMotherArea(new GeographicArea("Porto", new AreaType
                ("Cidade"), 2, 3, new Local(4, 4, 100)));


        AreaSensorService expectedResult = new AreaSensorService();
        expectedResult.add(areaSensorSameLocalHouse);

        //Act

        AreaSensorService actualResult = validAreaSensorService.getSensorsByDistanceToHouse(house, 0);

        //Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSizeWorks() {

        //Arrange

        AreaSensorService emptyList = new AreaSensorService();
        AreaSensorService twoSensors = new AreaSensorService();
        twoSensors.add(firstValidAreaSensor);
        twoSensors.add(secondValidAreaSensor);

        //Act

        int actualResult1 = emptyList.size();
        int actualResult2 = validAreaSensorService.size();
        int actualResult3 = twoSensors.size();

        //Assert

        assertEquals(0, actualResult1);
        assertEquals(1, actualResult2);
        assertEquals(2, actualResult3);
    }

    @Test
    void seeIfContainsWorks() {

        //Arrange

        AreaSensorService emptyList = new AreaSensorService();

        //Act

        boolean actualResult1 = emptyList.contains(firstValidAreaSensor);
        boolean actualResult2 = validAreaSensorService.contains(firstValidAreaSensor);
        boolean actualResult3 = validAreaSensorService.contains(secondValidAreaSensor);

        //Assert

        assertFalse(actualResult1);
        assertTrue(actualResult2);
        assertFalse(actualResult3);
    }

//    @Test
//    void seeIfGetSensorsDistanceToHouse() {
//        //Arrange
//        List<String> deviceTypeString = new ArrayList<>();
//        deviceTypeString.add("pt.ipp.isep.dei.project.model.device.devicetypes.FridgeType");
//        GeographicArea validArea = new GeographicArea("Europe", new AreaType("Continent"), 3500, 3000,
//                new Local(20, 12, 33));
//        House validHouse = new House("ISEP", new Address("Rua Dr. António Bernardino de Almeida", "431",
//                "4455-125", "Porto", "Portugal"),
//                new Local(20, 20, 20), 60,
//                180, deviceTypeString);
//        validHouse.setMotherArea(new GeographicArea("Porto", new AreaType("Cidade"),
//                2, 3, new Local(4, 4, 100)));
//        List<Double> expectedResult = new ArrayList<>();
//        expectedResult.add(2259.92026088549);
//
//        //Act
//        List<Double> actualResult = validAreaSensorService.getSensorsDistanceToHouse(validHouse);
//
//        //Assert
//        assertEquals(expectedResult, actualResult);
//
//    }


    @Test
    void seeIfGetSensors() {
        List<AreaSensor> expectedResult = new ArrayList<>();
        expectedResult.add(firstValidAreaSensor);
        assertEquals(expectedResult, validAreaSensorService.getAreaSensors());
    }


//    @Test
//    void seeIfAddReadingToMatchingSensorWorks() {
//        // Arrange
//
//        Date dateSensorStartedFunctioning = new GregorianCalendar(2017, Calendar.FEBRUARY, 3).getTime();
//        firstValidSensor.setDateStartedFunctioning(dateSensorStartedFunctioning);
//
//        // Act for reading within bounds.
//
//        boolean result = validAreaSensorList.addReadingToMatchingSensor("SensorOne", 31D, new GregorianCalendar(
//                2017, Calendar.FEBRUARY, 5).getTime());
//
//        // Act for reading outside bounds.
//
//        boolean failedResult = validAreaSensorList.addReadingToMatchingSensor("SensorOne", 31D, new GregorianCalendar(
//                2015, Calendar.FEBRUARY, 1).getTime());
//
//        // Act for not existing sensor
//
//        boolean failedResult2 = validAreaSensorList.addReadingToMatchingSensor("xxxxxxx", 32D, new GregorianCalendar(
//                2018, Calendar.FEBRUARY, 1).getTime());
//
//
//        // Assert
//
//        assertTrue(result);
//        assertFalse(failedResult);
//        assertFalse(failedResult2);
//    }

    @Test
    void seeIfSensorExistsInRepositoryWorks() {
        //Arrange

        String sensorId = "SensorOne";
        Mockito.when(areaSensorRepository.findById(sensorId)).thenReturn(Optional.of(firstValidAreaSensor));

        //Act

        boolean actualResult1 = validAreaSensorService.sensorExistsInRepository(sensorId);

        //Assert

        assertTrue(actualResult1);
    }

    @Test
    void seeIfSensorExistsInRepositoryWorksWhenSensorIsNotInRepository() {
        //Arrange

        String sensorId = "SensorOne";
        Mockito.when(areaSensorRepository.findById(sensorId)).thenReturn(Optional.empty());

        //Act

        boolean actualResult1 = validAreaSensorService.sensorExistsInRepository(sensorId);

        //Assert

        assertFalse(actualResult1);
    }

    @Test
    void seeIfSensorFromRepositoryIsActiveWorks() {
        //Arrange

        String sensorId = "SensorOne";
        Mockito.when(areaSensorRepository.findById(sensorId)).thenReturn(Optional.of(firstValidAreaSensor));

        //Act

        boolean actualResult1 = validAreaSensorService.sensorFromRepositoryIsActive(sensorId, validDate1);

        //Assert

        assertTrue(actualResult1);
    }

    @Test
    void seeIfSensorFromRepositoryIsActiveWorksWhenSensorStartsAfterReading() {
        //Arrange

        String sensorId = "SensorOne";
        Mockito.when(areaSensorRepository.findById(sensorId)).thenReturn(Optional.of(firstValidAreaSensor));

        //Act

        boolean actualResult1 = validAreaSensorService.sensorFromRepositoryIsActive(sensorId, validDate2);

        //Assert

        assertFalse(actualResult1);
    }

    @Test
    void seeIfSensorFromRepositoryIsActiveWorksWhenSensorDoesNotExist() {
        //Arrange

        String sensorId = "SensorOne";
        Mockito.when(areaSensorRepository.findById(sensorId)).thenReturn((Optional.empty()));

        //Act

        boolean actualResult1 = validAreaSensorService.sensorFromRepositoryIsActive(sensorId, validDate1);

        //Assert

        assertFalse(actualResult1);
    }


}
