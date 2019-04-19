package pt.ipp.isep.dei.project.model.sensor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project.model.GeographicAreaService;
import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.repository.AreaSensorRepository;
import pt.ipp.isep.dei.project.repository.AreaTypeRepository;
import pt.ipp.isep.dei.project.repository.GeographicAreaRepository;
import pt.ipp.isep.dei.project.repository.SensorTypeRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
    @Mock
    GeographicAreaRepository geographicAreaRepository;
    @Mock
    AreaTypeRepository areaTypeRepository;
    @Mock
    SensorTypeRepository sensorTypeRepository;

    private AreaSensorService validAreaSensorService; // Contains the first valid sensor by default.

    private GeographicAreaService geographicAreaService;

    @BeforeEach
    void arrangeArtifacts() {
        MockitoAnnotations.initMocks(this);
        validAreaSensorService = new AreaSensorService(areaSensorRepository, sensorTypeRepository);
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
        this.geographicAreaService = new GeographicAreaService(geographicAreaRepository, areaTypeRepository, areaSensorRepository);
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


//    @Test
//    void seeIfUpdateSensor() {
//        // Arrange
//        AreaSensor sensor = new AreaSensor("SensorOne", "SensorOne", new SensorType("Temperature", "Celsius"), new Local(2, 2, 2), validDate1, 6008L);
//        sensor.setActive(true);
//        AreaSensorService areaSensors = new AreaSensorService();
//        areaSensors.add(sensor);
//
//
//        // Act
//        Mockito.when(areaSensorRepository.findByName("SensorOne")).thenReturn(sensor);
//
//
//        // Assert
//
//        assertEquals(sensor, validAreaSensorService.updateSensor(sensor));
//    }

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
    void seeIfAddPersist() {
        AreaSensor areaSensor = new AreaSensor("Sensor", "Sensor", new SensorType("Temperature", "Celsius"), new Local(
                31, 1, 2), new Date(), 6008L);
        areaSensor.setActive(true);

        assertTrue(validAreaSensorService.addWithPersist(areaSensor));
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

        boolean actualResult1 = geographicAreaService.sensorFromRepositoryIsActive(sensorId, validDate1);

        //Assert

        assertTrue(actualResult1);
    }

    @Test
    void seeIfSensorFromRepositoryIsActiveWorksWhenSensorStartsAfterReading() {
        //Arrange

        String sensorId = "SensorOne";
        Mockito.when(areaSensorRepository.findById(sensorId)).thenReturn(Optional.of(firstValidAreaSensor));

        //Act

        boolean actualResult1 = geographicAreaService.sensorFromRepositoryIsActive(sensorId, validDate2);

        //Assert

        assertFalse(actualResult1);
    }

    @Test
    void seeIfSensorFromRepositoryIsActiveWorksWhenSensorDoesNotExist() {
        //Arrange

        String sensorId = "SensorOne";
        Mockito.when(areaSensorRepository.findById(sensorId)).thenReturn((Optional.empty()));

        //Act

        boolean actualResult1 = geographicAreaService.sensorFromRepositoryIsActive(sensorId, validDate1);

        //Assert

        assertFalse(actualResult1);
    }

    @Test
    void seeIfRemoveSensor() {
        //Arrange

        String sensorId = "SensorOne";
        Mockito.when(areaSensorRepository.findById(sensorId)).thenReturn((Optional.empty()));

        //Act

        boolean actualResult1 = validAreaSensorService.remove(firstValidAreaSensor);

        //Assert

        assertFalse(actualResult1);
    }

    @Test
    void seeIfRemoveSensorTrue() {
        //Arrange
        String sensorId = "SensorOne";
        Mockito.when(areaSensorRepository.findById(sensorId)).thenReturn((Optional.of(firstValidAreaSensor)));

        //Act

        boolean actualResult1 = validAreaSensorService.remove(firstValidAreaSensor);

        //Assert

        assertTrue(actualResult1);
    }

    @Test
    void seeIfGetById() {
        String mockId = "SensorOne";

        AreaSensor areaSensor = new AreaSensor("SensorOne", "SensorOne", new SensorType("Temperature", "Celsius"), new Local(2, 2, 2), validDate1, 6008L);
        areaSensor.setId("SensorOne");

        Mockito.when(areaSensorRepository.findById(mockId)).thenReturn(Optional.of(areaSensor));

        AreaSensor result = validAreaSensorService.getById(mockId);

        assertEquals(result.getId(), areaSensor.getId());
        assertEquals(result.getName(), areaSensor.getName());

    }

}
