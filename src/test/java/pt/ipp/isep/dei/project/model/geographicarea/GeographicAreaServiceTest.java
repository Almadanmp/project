package pt.ipp.isep.dei.project.model.geographicarea;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.model.areatype.AreaType;
import pt.ipp.isep.dei.project.model.room.RoomService;
import pt.ipp.isep.dei.project.model.sensortype.SensorType;
import pt.ipp.isep.dei.project.repository.AreaSensorRepository;
import pt.ipp.isep.dei.project.repository.AreaTypeRepository;
import pt.ipp.isep.dei.project.repository.GeographicAreaRepository;
import pt.ipp.isep.dei.project.repository.SensorTypeRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * GeographicAreaList tests class.
 */
@ExtendWith(MockitoExtension.class)
class GeographicAreaServiceTest {
    // Common testing artifacts for this class.

    private GeographicArea firstValidArea;
    private List<GeographicArea> validList;

    @Mock
    GeographicAreaRepository geographicAreaRepository;

    @Mock
    AreaSensorRepository areaSensorRepository;

    @Mock
    AreaTypeRepository areaTypeRepository;
    private AreaSensor firstValidAreaSensor;
    private AreaSensor secondValidAreaSensor;
    private AreaSensor thirdValidAreaSensor;
    private Date validDate1; // Date 21/11/2018
    private Date validDate2; // Date 03/09/2018

    private GeographicAreaService geographicAreaService;
    private SensorTypeRepository sensorTypeRepository;


    @BeforeEach
    void arrangeArtifacts() {
        firstValidArea = new GeographicArea("Portugal", new AreaType("Country"), 300, 200,
                new Local(50, 50, 10));
        validList = new ArrayList<>();
        validList.add(firstValidArea);
        MockitoAnnotations.initMocks(this);
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
        this.geographicAreaService = new GeographicAreaService(geographicAreaRepository, areaTypeRepository, areaSensorRepository, sensorTypeRepository);
    }


    @Test
    void seeIfEqualsWorksFalseDifferentObject() {
        // Arrange

        RoomService testList = new RoomService();

        //Act

        boolean actualResult = geographicAreaService.equals(testList); // Needed for SonarQube testing purposes.

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfPrintsGeoAList() {
        // Arrange
        List<GeographicArea> geographicAreas = new ArrayList<>();
        String expectedResult = "Invalid List - List is Empty\n";

        // Act

        String result = geographicAreaService.buildStringRepository(geographicAreas);

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfPrintsGeoAListIfEmpty() {
        // Arrange

        List<GeographicArea> geoAreas = new ArrayList<>();
        String expectedResult = "Invalid List - List is Empty\n";

        // Act

        String actualResult = geographicAreaService.buildStringRepository(geoAreas);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfIsEmptyFalse() {
        List<GeographicArea> geographicAreas = new ArrayList<>();
        geographicAreas.add(firstValidArea);

        Mockito.when(geographicAreaRepository.findAll()).thenReturn(geographicAreas);

        assertFalse(geographicAreaService.isEmpty());

    }

    @Test
    void seeIfIsEmptyTrue() {

        List<GeographicArea> geographicAreas = new ArrayList<>();

        Mockito.when(geographicAreaRepository.findAll()).thenReturn(geographicAreas);

        assertTrue(geographicAreaService.isEmpty());
    }

    @Test
    void seeIfGetTypeAreaByIdRepository() {
        long mockId = 1234;
        firstValidArea.setId(mockId);

        Mockito.when(geographicAreaRepository.findById(mockId)).thenReturn(Optional.of(firstValidArea));

        GeographicArea result = geographicAreaService.get(mockId);

        assertEquals(result.getId(), firstValidArea.getId());

    }

    @Test
    void seeIfGetTypeAreaByIdRepositoryNull() {
        long mockId = 1234;

        Mockito.when(geographicAreaRepository.findById(mockId)).thenReturn(Optional.empty());

        Throwable exception = assertThrows(NoSuchElementException.class, () -> geographicAreaService.get(mockId));

        assertEquals("ERROR: There is no Geographic Area with the selected ID.", exception.getMessage());
    }

    @Test
    void createGA() {
        String iD = "Coimbra";
        AreaType areaType = new AreaType("Distrito");
        Local local = new Local(12, 12, 12);
        GeographicArea expectedResult = new GeographicArea(iD, areaType, 12, 12, local);

//        GeographicArea actualResult = geographicAreaService.createGA(iD, areaType.getName(), 12, 12, local);

//        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetsGeoAreasByType() {

        // Act

        List<GeographicArea> actualResult = geographicAreaService.getGeoAreasByType(validList, "Country");

        // Assert

        assertEquals(actualResult.size(), 1);
    }

    @Test
    void seeIfEqualsWorksOnSameObject() {
        //Act

        boolean actualResult = geographicAreaService.equals(geographicAreaService); // Required for Sonarqube testing purposes.

        //Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfEqualsWorksOnDiffObject() {
        //Act

        boolean actualResult = geographicAreaService.equals(20D); // Required for Sonarqube testing purposes.

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

        geographicAreaService.addAreaSensorToDb(areaSensor);


        //Assert
        assertFalse(geographicAreaService.areaSensorExistsInRepository("SensorOne"));
    }

    @Test
    void seeIfAddPersist() {
        AreaSensor areaSensor = new AreaSensor("Sensor", "Sensor", new SensorType("Temperature", "Celsius"), new Local(
                31, 1, 2), new Date(), 6008L);
        areaSensor.setActive(true);

        assertTrue(geographicAreaService.addAreaSensorToDb(areaSensor));
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

        String actualResult = geographicAreaService.buildString(areaSensors);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfToStringWorksEmpty() {
        // Arrange
        List<AreaSensor> areaSensors = new ArrayList<>();
        String expectedResult = "Invalid List - List is Empty\n";

        // Act

        String actualResult = geographicAreaService.buildString(areaSensors);

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

        boolean actualResult1 = geographicAreaService.areaSensorExistsInRepository(sensorId);

        //Assert

        assertTrue(actualResult1);
    }

    @Test
    void seeIfSensorExistsInRepositoryWorksWhenSensorIsNotInRepository() {
        //Arrange

        String sensorId = "SensorOne";
        Mockito.when(areaSensorRepository.findById(sensorId)).thenReturn(Optional.empty());

        //Act

        boolean actualResult1 = geographicAreaService.areaSensorExistsInRepository(sensorId);

        //Assert

        assertFalse(actualResult1);
    }

    @Test
    void seeIfRemoveSensor() {
        //Arrange

        String sensorId = "SensorOne";
        Mockito.when(areaSensorRepository.findById(sensorId)).thenReturn((Optional.empty()));

        //Act

        boolean actualResult1 = geographicAreaService.remove(firstValidAreaSensor);

        //Assert

        assertFalse(actualResult1);
    }

    @Test
    void seeIfRemoveSensorTrue() {
        //Arrange
        String sensorId = "SensorOne";
        Mockito.when(areaSensorRepository.findById(sensorId)).thenReturn((Optional.of(firstValidAreaSensor)));

        //Act

        boolean actualResult1 = geographicAreaService.remove(firstValidAreaSensor);

        //Assert

        assertTrue(actualResult1);
    }

    @Test
    void seeIfGetById() {
        String mockId = "SensorOne";

        AreaSensor areaSensor = new AreaSensor("SensorOne", "SensorOne", new SensorType("Temperature", "Celsius"), new Local(2, 2, 2), validDate1, 6008L);
        areaSensor.setId("SensorOne");

        Mockito.when(areaSensorRepository.findById(mockId)).thenReturn(Optional.of(areaSensor));

        AreaSensor result = geographicAreaService.getById(mockId);

        assertEquals(result.getId(), areaSensor.getId());
        assertEquals(result.getName(), areaSensor.getName());

    }

}