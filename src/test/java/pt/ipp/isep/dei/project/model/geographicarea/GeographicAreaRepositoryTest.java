package pt.ipp.isep.dei.project.model.geographicarea;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project.dto.*;
import pt.ipp.isep.dei.project.dto.mappers.AreaSensorMapper;
import pt.ipp.isep.dei.project.dto.mappers.GeographicAreaMapper;
import pt.ipp.isep.dei.project.dto.mappers.ReadingMapper;
import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.areatype.AreaType;
import pt.ipp.isep.dei.project.model.device.WaterHeater;
import pt.ipp.isep.dei.project.model.device.devicespecs.WaterHeaterSpec;
import pt.ipp.isep.dei.project.model.house.Address;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.sensortype.SensorType;
import pt.ipp.isep.dei.project.model.repository.GeographicAreaCrudRepo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * GeographicAreaService tests class.
 */
@ExtendWith(MockitoExtension.class)
class GeographicAreaRepositoryTest {

    // Common testing artifacts for this class.

    private static final String PATH_TO_FRIDGE = "pt.ipp.isep.dei.project.model.device.devicetypes.FridgeType";
    @Mock
    GeographicAreaCrudRepo geographicAreaCrudRepo;
    private GeographicArea firstValidArea;
    private List<GeographicArea> validList;
    private List<GeographicAreaDTO> validDTOList;
    private GeographicAreaDTO validDTO;
    private GeographicAreaWebDTO validWebDTO;
    private AreaSensor firstValidAreaSensor;
    private AreaSensor secondValidAreaSensor;
    private AreaSensor validAreaSensor;
    private AreaSensorDTO validAreaSensorDTO;
    private Date validDate1; // Date 21/11/2018
    private Date validDate2; // Date 03/09/2018
    private Date validDate3;
    private Date validDate4;
    private Date initialTime;
    private Date endingTime;
    private Date sensorCreationTime;
    private Date validReadingDate;
    private Date validReadingDate2;
    private Date validReadingDate3;
    private Reading validReading;
    private Reading validReading2;
    private Reading validReadingHotDay;
    private Reading validReadingColdDay;
    private List<Reading> validReadingList;
    private House validHouse;
    private List<String> deviceTypeString;
    @InjectMocks
    private GeographicAreaRepository geographicAreaRepository;

    @BeforeEach
    void arrangeArtifacts() {
        MockitoAnnotations.initMocks(this);
        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        SimpleDateFormat readingSD = new SimpleDateFormat("yyyy-MM-dd");
        try {
            validDate1 = validSdf.parse("21/11/2018 00:00:00");
            validDate2 = validSdf.parse("03/09/2018 00:00:00");
            validDate3 = validSdf.parse("12/10/2018 00:00:00");
            validDate4 = validSdf.parse("01/10/2018 00:00:00");
            validReadingDate = readingSD.parse("2018-10-03");
            validReadingDate2 = readingSD.parse("2018-10-04");
            validReadingDate3 = readingSD.parse("2018-10-05");
            initialTime = readingSD.parse("2017-10-03");
            endingTime = readingSD.parse("2019-10-03");
            sensorCreationTime = readingSD.parse("2016-10-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        firstValidArea = new GeographicArea("Portugal", "Country", 300, 200,
                new Local(50, 50, 10));
        validList = new ArrayList<>();
        validWebDTO = new GeographicAreaWebDTO();
        validWebDTO.setTypeArea("country");
        validWebDTO.setName("Oporto");
        validDTO = new GeographicAreaDTO();
        validDTO.setTypeArea("country");
        validDTO.setName("Oporto");
        validDTO.setWidth(45);
        validDTO.setLength(44);
        LocalDTO localDTO = new LocalDTO();
        localDTO.setLatitude(3);
        localDTO.setAltitude(55);
        localDTO.setLongitude(3);
        validDTO.setLocal(localDTO);
        validDTO.setId(256L);
        validDTOList = new ArrayList<>();
        validDTOList.add(validDTO);
        validList.add(firstValidArea);

        firstValidAreaSensor = new AreaSensor("SensorOne", "SensorOne", "Temperature", new Local(2, 2, 2), validDate1);
        firstValidAreaSensor.setActive(true);
        secondValidAreaSensor = new AreaSensor("SensorTwo", "SensorTwo", "Temperature", new Local(10, 10, 10),
                validDate1);
        secondValidAreaSensor.setActive(true);
        validAreaSensor = new AreaSensor("SensorThree", "SensorThree", "temperature", new Local(10, 10, 10),
                sensorCreationTime);
        validAreaSensor.setActive(true);
        validAreaSensorDTO = AreaSensorMapper.objectToDTO(validAreaSensor);

        validReading = new Reading(23, validDate2, "C", "sensorID");
        validReading2 = new Reading(23, validReadingDate, "C", "SensorThree");
        validReadingHotDay = new Reading(50, validReadingDate2, "C", "SensorThree");
        validReadingColdDay = new Reading(0, validReadingDate3, "C", "SensorThree");

        validAreaSensor.addReading(validReading2);
        //validAreaSensor.addReading(validReadingColdDay);
        //validAreaSensor.addReading(validReadingHotDay);
        validReadingList = new ArrayList<>();
        validReadingList.add(validReading2);
        validReadingList.add(validReadingColdDay);
        validReadingList.add(validReadingHotDay);

        deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        validHouse = new House("ISEP", new Address("Rua Dr. António Bernardino de Almeida", "431",
                "4455-125", "Porto", "Portugal"),
                new Local(20, 20, 20), 60,
                180, deviceTypeString);
        validHouse.setMotherAreaID(firstValidArea.getId());
        firstValidArea.addSensor(validAreaSensor);
    }

    @Test
    void seeIfUpdateGeoAreaWorks() {
        // Arrange

        GeographicArea expectedResult = new GeographicArea("Portugal", "Country", 300, 200,
                new Local(50, 50, 10));

        // Assert

        assertEquals(expectedResult, firstValidArea);
    }

    @Test
    void seeIfAddAreaReadingsWorksWhenSensorIDIsInvalid() {
        // Arrange

        List<Reading> readings = new ArrayList<>();
        Reading reading = new Reading(21D, validDate1, "C", "sensorID");
        readings.add(reading);

        List<GeographicArea> geographicAreas = new ArrayList<>();
        geographicAreas.add(firstValidArea);

        Mockito.when(geographicAreaCrudRepo.findAll()).thenReturn(geographicAreas);

        int expectedResult = 0;

        //Act

        int actualResult = geographicAreaRepository.addAreaReadings("invalidSensor", readings);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfAddAreaReadingsWorks() {
        // Arrange

        List<Reading> readings = new ArrayList<>();
        Reading reading = new Reading(21D, validDate1, "C", "sensorID");
        readings.add(reading);

        List<GeographicArea> geographicAreas = new ArrayList<>();
        geographicAreas.add(firstValidArea);
        firstValidArea.addSensor(firstValidAreaSensor);

        Mockito.when(geographicAreaCrudRepo.findAll()).thenReturn(geographicAreas);

        int expectedResult = 1;

        //Act

        int actualResult = geographicAreaRepository.addAreaReadings("SensorOne", readings);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfAddReadingsToAreaSensorWorks() {
        // Arrange

        List<Reading> readings = new ArrayList<>();
        Reading reading = new Reading(21D, validDate1, "C", "sensorID");
        readings.add(reading);

        int expectedResult = 1;

        //Act

        int actualResult = geographicAreaRepository.addReadingsToAreaSensor(firstValidAreaSensor, readings);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfAddReadingsToAreaSensorWorksWhenReadingIsFromBeforeSensorActivatingDate() {
        // Arrange

        List<Reading> readings = new ArrayList<>();
        Reading reading = new Reading(21D, validDate2, "C", "sensorID");
        readings.add(reading);

        int expectedResult = 0;

        //Act

        int actualResult = geographicAreaRepository.addReadingsToAreaSensor(firstValidAreaSensor, readings);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfAddReadingsToAreaSensorWorksWhenReadingAlreadyExists() {
        // Arrange

        List<Reading> readings = new ArrayList<>();
        Reading reading = new Reading(21D, validDate1, "C", "sensorID");
        readings.add(reading);

        firstValidAreaSensor.addReading(reading);

        int expectedResult = 0;

        //Act

        int actualResult = geographicAreaRepository.addReadingsToAreaSensor(firstValidAreaSensor, readings);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfAddReadingsToAreaSensorWorksWhenListIsEmpty() {
        // Arrange

        List<Reading> readings = new ArrayList<>();

        int expectedResult = 0;

        //Act

        int actualResult = geographicAreaRepository.addReadingsToAreaSensor(firstValidAreaSensor, readings);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetGeographicAreaContainingSensorWithGivenIdWorks() {
        // Arrange

        firstValidArea.addSensor(firstValidAreaSensor);
        firstValidArea.addSensor(secondValidAreaSensor);

        Mockito.when(geographicAreaCrudRepo.findAll()).thenReturn(validList);

        //Act

        GeographicArea actualResult = geographicAreaRepository.getGeographicAreaContainingSensorWithGivenId("SensorTwo");

        // Assert

        assertEquals(firstValidArea, actualResult);
    }

    @Test
    void seeIfGetGeographicAreaContainingSensorWithGivenIdWorksWhenSensorIdIsNull() {
        // Arrange

        firstValidArea.addSensor(firstValidAreaSensor);
        firstValidArea.addSensor(secondValidAreaSensor);

        Mockito.when(geographicAreaCrudRepo.findAll()).thenReturn(validList);

        // Assert

        assertThrows(IllegalArgumentException.class,
                () -> geographicAreaRepository.getGeographicAreaContainingSensorWithGivenId(null));
    }

    @Test
    void seeIfGetGeographicAreaContainingSensorWithGivenIdWorksWhenSensorIdDoesNotExist() {
        // Arrange

        firstValidArea.addSensor(firstValidAreaSensor);
        firstValidArea.addSensor(secondValidAreaSensor);

        Mockito.when(geographicAreaCrudRepo.findAll()).thenReturn(validList);

        // Assert

        assertThrows(IllegalArgumentException.class,
                () -> geographicAreaRepository.getGeographicAreaContainingSensorWithGivenId("invalidSensorID"));
    }

    @Test
    void seeIfGetGeographicAreaContainingSensorWithGivenIdWorksWhenSensorIdIsEmpty() {
        // Arrange

        firstValidArea.addSensor(firstValidAreaSensor);
        firstValidArea.addSensor(secondValidAreaSensor);

        Mockito.when(geographicAreaCrudRepo.findAll()).thenReturn(validList);

        // Assert

        assertThrows(IllegalArgumentException.class,
                () -> geographicAreaRepository.getGeographicAreaContainingSensorWithGivenId(""));
    }

    @Test
    void seeIfGetGeographicAreaContainingSensorWithGivenIdWorksWhenSensorIdDoesNotExistEmptyList() {
        // Arrange

        List<GeographicArea> emptyList = new ArrayList<>();

        Mockito.when(geographicAreaCrudRepo.findAll()).thenReturn(emptyList);

        // Assert

        assertThrows(IllegalArgumentException.class,
                () -> geographicAreaRepository.getGeographicAreaContainingSensorWithGivenId("invalidSensorID"));
    }


    @Test
    void seeIfEqualsWorksFalseDifferentObject() {
        // Arrange

        //Act

        boolean actualResult = geographicAreaRepository.equals(new WaterHeater(new WaterHeaterSpec())); // Needed for SonarQube testing purposes.

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfPrintsGeoAList() {
        // Arrange
        String expectedResult = "---------------\n" +
                "null) Name: Portugal | Type: Country | Latitude: 50.0 | Longitude: 50.0\n" +
                "---------------\n";

        // Act

        String result = geographicAreaRepository.buildStringRepository(validList);

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfPrintsGeoAListIfEmpty() {
        // Arrange

        List<GeographicArea> geoAreas = new ArrayList<>();
        String expectedResult = "Invalid List - List is Empty\n";

        // Act

        String actualResult = geographicAreaRepository.buildStringRepository(geoAreas);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfIsEmptyFalse() {
        List<GeographicArea> geographicAreas = new ArrayList<>();
        geographicAreas.add(firstValidArea);

        Mockito.when(geographicAreaCrudRepo.findAll()).thenReturn(geographicAreas);

        assertFalse(geographicAreaRepository.isEmpty());

    }

    @Test
    void seeIfIsEmptyTrue() {

        List<GeographicArea> geographicAreas = new ArrayList<>();

        Mockito.when(geographicAreaCrudRepo.findAll()).thenReturn(geographicAreas);

        assertTrue(geographicAreaRepository.isEmpty());
    }

    @Test
    void seeIfGetTypeAreaByIdRepository() {
        long mockId = 1234;
        firstValidArea.setId(mockId);

        Mockito.when(geographicAreaCrudRepo.findById(mockId)).thenReturn(Optional.of(firstValidArea));

        GeographicArea result = geographicAreaRepository.getByID(mockId);

        assertEquals(result.getId(), firstValidArea.getId());

    }

    @Test
    void seeIfGetTypeAreaByIdRepositoryNull() {
        long mockId = 1234;

        Mockito.when(geographicAreaCrudRepo.findById(mockId)).thenReturn(Optional.empty());

        Throwable exception = assertThrows(NoSuchElementException.class, () -> geographicAreaRepository.getByID(mockId));

        assertEquals("ERROR: There is no Geographic Area with the selected ID.", exception.getMessage());
    }

    @Test
    void seeIfCreateGAWorks() {
        String iD = "Coimbra";
        Local local = new Local(12, 12, 12);

        AreaType city = new AreaType("city");

        GeographicArea expectedResult = new GeographicArea(iD, city.getName(), 12, 12, local);

        GeographicArea actualResult = geographicAreaRepository.createGA(iD, city.getName(), 12, 12, local);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfCreateAreaSensorWorks() {
        SensorType rainfall = new SensorType("rainfall", "mm");

        AreaSensor expectedResult = new AreaSensor("Sensor123", "Temperature Sensor 2",
                rainfall.getName(), new Local(41, -8, 100), validDate1);

        AreaSensor actualResult = firstValidArea.createAreaSensor("Sensor123", "Temperature Sensor 2",
                "rainfall", new Local(41, -8, 100), validDate1);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfCreateAreaSensorWorksWithSensorTypeNull() {
        assertThrows(IllegalArgumentException.class,
                () -> firstValidArea.createAreaSensor("Sensor123", "Temperature Sensor 2",
                        null, new Local(41, -8, 100), validDate1));
    }

    @Test
    void seeIfAddAndPersistReturnsFalse() {
        // Arrange

        // First Area

        GeographicAreaDTO firstArea = new GeographicAreaDTO();
        firstArea.setName("ISEP");
        firstArea.setDescription("Campus do ISEP");
        firstArea.setTypeArea("urban area");
        firstArea.setWidth(0.261);
        firstArea.setLength(0.249);
        firstArea.setLocal(new LocalDTO(41.178553, -8.608035, 111));

        // Populate expectedResult array

        GeographicArea areaOne = GeographicAreaMapper.dtoToObject(firstArea);

        validList.add(areaOne);

        Mockito.when(geographicAreaCrudRepo.findAll()).thenReturn(validList);

        boolean result = geographicAreaRepository.addAndPersistGA(areaOne);

        assertFalse(result);
    }

    @Test
    void seeIfGetsGeoAreasByType() {

        // Act
        List<GeographicArea> geographicAreas = new ArrayList<>();
        geographicAreas.add(firstValidArea);
        Mockito.when(geographicAreaRepository.getGeoAreasByType("Country")).thenReturn(geographicAreas);
        List<GeographicArea> actualResult = geographicAreaRepository.getGeoAreasByType("Country");
        int expectedResult = 1;
        // Assert

        assertEquals(expectedResult, actualResult.size());
    }

    @Test
    void seeIfGetsGeoAreasByTypeNotARealType() {

        // Act

        List<GeographicArea> actualResult = geographicAreaRepository.getGeoAreasByType("Not a valid type");
        int expectedResult = 0;
        // Assert

        assertEquals(expectedResult, actualResult.size());
    }


    @Test
    void seeIfEqualsWorksOnSameObject() {
        //Act

        boolean actualResult = geographicAreaRepository.equals(geographicAreaRepository); // Required for Sonarqube testing purposes.

        //Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfEqualsWorksOnDiffObject() {
        //Act

        boolean actualResult = geographicAreaRepository.equals(20D); // Required for Sonarqube testing purposes.

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
//        validHouse.setMotherAreaID(new GeographicArea("Porto", new AreaType("Cidade"),
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
    void seeIfGetAllGeoAreaDTOWorksEmpty() {
        // Arrange
        Mockito.when(geographicAreaRepository.getAllDTO()).thenReturn(new ArrayList<>());
        List<GeographicAreaDTO> expectedResult = new ArrayList<>();

        // Act
        List<GeographicAreaDTO> actualResult = geographicAreaRepository.getAllDTO();

        // Assert
        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfGetAllGeoAreaDTOWorks() {
        // Arrange
        List<GeographicArea> listGA = new ArrayList<>();
        GeographicArea area = GeographicAreaMapper.dtoToObject(validDTO);
        listGA.add(area);
        Mockito.when(geographicAreaCrudRepo.findAll()).thenReturn(listGA);

        List<GeographicAreaDTO> expectedResult = new ArrayList<>();
        expectedResult.add(validDTO);

        // Act
        List<GeographicAreaDTO> actualResult = geographicAreaRepository.getAllDTO();

        // Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetAllDTOWebInformationWorksEmpty() {
        // Arrange
        Mockito.when(geographicAreaRepository.getAllDTOWebInformation()).thenReturn(new ArrayList<>());
        List<GeographicAreaWebDTO> expectedResult = new ArrayList<>();

        // Act
        List<GeographicAreaWebDTO> actualResult = geographicAreaRepository.getAllDTOWebInformation();

        // Assert
        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfGetAllDTOWebInformationWorks() {
        // Arrange
        Mockito.when(geographicAreaCrudRepo.findAll()).thenReturn(validList);
        GeographicAreaWebDTO geographicAreaWebDTO = GeographicAreaMapper.objectToWebDTO(firstValidArea);
        List<GeographicAreaWebDTO> geographicAreaWebDTOS = new ArrayList<>();
        geographicAreaWebDTOS.add(geographicAreaWebDTO);

        // Act
        List<GeographicAreaWebDTO> actualResult = geographicAreaRepository.getAllDTOWebInformation();

        // Assert
        assertEquals(geographicAreaWebDTOS, actualResult);
    }

    @Test
    void seeIfGetDTOByIdWithMotherWorks() {
        //Arrange
        Mockito.when(geographicAreaCrudRepo.findById(4L)).thenReturn(Optional.of(firstValidArea));
        GeographicAreaDTO geographicAreaDTO = GeographicAreaMapper.objectToDTO(firstValidArea);
        //Act
        GeographicAreaDTO actualResult = geographicAreaRepository.getDTOByIdWithParent(4L);
        //Assert
        assertEquals(geographicAreaDTO, actualResult);
    }

    @Test
    void seeIfGetDTOByIdWithMotherDoesNotWork() {
        //Arrange
        Mockito.when(geographicAreaCrudRepo.findById(4L)).thenReturn(Optional.empty());
        //Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> geographicAreaRepository.getDTOByIdWithParent(4L));
        //Assert
        assertEquals("Geographic Area not found - 404", exception.getMessage());

    }

    @Test
    void seeIfGetAreaDTObyIdWorks() {

        // Arrange

        List<GeographicArea> listGA = new ArrayList<>();
        GeographicArea area = GeographicAreaMapper.dtoToObject(validDTO);
        area.setId(25L);
        listGA.add(area);
        Optional<GeographicArea> opt = Optional.of(area);
        Mockito.when(geographicAreaCrudRepo.findById(25L)).thenReturn(opt);

        GeographicAreaDTO expectedResult = validDTO;

        // Act

        GeographicAreaDTO actualResult = geographicAreaRepository.getDTOById(25L);

        // Assert

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfGetAreaDTObyIdFailsNotPresent() {
        // Arrange
        List<GeographicArea> listGA = new ArrayList<>();
        GeographicArea area = GeographicAreaMapper.dtoToObject(validDTO);
        listGA.add(area);

        // Act

        assertThrows(IllegalArgumentException.class,
                () -> geographicAreaRepository.getDTOById(area.getId()));

        // Assert

    }

    @Test
    void seeIfAddSensorDTOWorks() {
        // Arrange

        // Act

        boolean actualResult = geographicAreaRepository.addSensorDTO(validDTO, validAreaSensorDTO);

        // Assert
        assertTrue(actualResult);

    }

    @Test
    void seeIfAddSensorDTOFailsSameSensor() {
        // Arrange

        geographicAreaRepository.addSensorDTO(validDTO, validAreaSensorDTO);

        // Act

        boolean actualResult = geographicAreaRepository.addSensorDTO(validDTO, validAreaSensorDTO);

        // Assert
        assertFalse(actualResult);

    }

    @Test
    void seeIfRemoveSensorDTOWorks() {
        // Arrange

        geographicAreaRepository.addSensorDTO(validDTO, validAreaSensorDTO);

        // Act

        boolean actualResult = geographicAreaRepository.removeSensorDTO(validDTO, validAreaSensorDTO.getSensorId());

        // Assert
        assertTrue(actualResult);

    }

    @Test
    void seeIfRemoveSensorDTOFailsWrongId() {
        // Arrange

        geographicAreaRepository.addSensorDTO(validDTO, validAreaSensorDTO);

        // Act

        boolean actualResult = geographicAreaRepository.removeSensorDTO(validDTO, "Sensor test");

        // Assert
        assertFalse(actualResult);

    }

//    @Test
//    void seeIfGetMotherDTOWorks() {
//        // Arrange
//
//        geographicAreaRepository.setMotherDTO(validDTO, validDTO);
//
//        // Act
//        Long expectedResult = 256L;
//        Long actualResult = geographicAreaRepository.getMotherDTO(validDTO);
//
//        // Assert
//        assertEquals(expectedResult, actualResult);
//
//    }


    @Test
    void seeIfUpdateAreaDTOWorks() {

        // Arrange

        GeographicArea area = GeographicAreaMapper.dtoToObject(validDTO);
        area.setId(25L);
        Optional<GeographicArea> opt = Optional.of(area);
        Mockito.when(geographicAreaCrudRepo.findById(25L)).thenReturn(opt);

        GeographicAreaDTO expectedResult = validDTO;

        // Act

        geographicAreaRepository.updateAreaDTO(validDTO);
        GeographicAreaDTO actualResult = geographicAreaRepository.getDTOById(25L);

        // Assert

        assertEquals(expectedResult, actualResult);

    }

//    @Test
//    void seeIfUpdateAreaDTOWithMotherWorks() {
//
//        // Arrange
//
//        GeographicArea area = GeographicAreaMapper.dtoToObject(validDTO);
//        area.setSensorId(25L);
//        area.setChildAreas(firstValidArea.getSensorId());
//        firstValidArea.setSensorId(23L);
//        GeographicAreaDTO geographicAreaDTO =GeographicAreaMapper.objectToDTO(firstValidArea);
//        Optional<GeographicArea> opt = Optional.of(area);
//        Mockito.when(geographicAreaCrudRepo.findById(25L)).thenReturn(opt);
//
//        GeographicAreaDTO expectedResult = validDTO;
//
//        // Act
//
//        geographicAreaRepository.addChildArea(validDTO, geographicAreaDTO);
//        GeographicAreaDTO actualResult = geographicAreaRepository.getDTOById(25L);
//
//        // Assert
//
//        assertEquals(expectedResult, actualResult);
//
//    }

    @Test
    void seeIfDeleteAreaDTOWorks() {

        // Arrange

        GeographicArea area = GeographicAreaMapper.dtoToObject(validDTO);
        area.setId(25L);
        GeographicAreaDTO expectedResult = validDTO;

        // Act

        geographicAreaRepository.deleteFromDatabase(validDTO);

        // Assert

        assertThrows(IllegalArgumentException.class,
                () -> geographicAreaRepository.getDTOById(25L));

    }

    @Test
    void seeIfDeleteAreaDTOFails() {

        // Arrange

        GeographicArea area = GeographicAreaMapper.dtoToObject(validDTO);
        area.setId(25L);
        Optional<GeographicArea> opt = Optional.of(area);
        Mockito.when(geographicAreaCrudRepo.findById(25L)).thenReturn(opt);
        GeographicAreaDTO expectedResult = validDTO;

        // Act

        geographicAreaRepository.deleteFromDatabase(validDTO);
        GeographicAreaDTO actualResult = geographicAreaRepository.getDTOById(25L);

        // Assert

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfAddAndPersistGeoAreaDTOFailsDuplicateGA() {
        // Arrange
        List<GeographicArea> listGA = new ArrayList<>();
        GeographicArea area = GeographicAreaMapper.dtoToObject(validDTO);
        listGA.add(area);
        Mockito.when(geographicAreaCrudRepo.findAll()).thenReturn(listGA);

        // Act
        boolean actualResult = geographicAreaRepository.addAndPersistDTO(validDTO);

        // Assert
        assertFalse(actualResult);

    }

    @Test
    void seeIfAddAndPersistGeoAreaDTOWorks() {
        // Arrange
        List<GeographicArea> listGA = new ArrayList<>();
        Mockito.when(geographicAreaCrudRepo.findAll()).thenReturn(listGA);

        // Act
        boolean actualResult = geographicAreaRepository.addAndPersistDTO(validDTO);

        // Assert
        assertTrue(actualResult);

    }

    @Test
    void seeIfAddAndPersistGeoAreaFailsDuplicateGA() {
        // Arrange
        List<GeographicArea> listGA = new ArrayList<>();
        GeographicArea area = GeographicAreaMapper.dtoToObject(validDTO);
        listGA.add(area);
        Mockito.when(geographicAreaCrudRepo.findAll()).thenReturn(listGA);

        // Act
        boolean actualResult = geographicAreaRepository.addAndPersistGA(area);

        // Assert
        assertFalse(actualResult);

    }

    @Test
    void seeIfAddAndPersistGeoAreaWorks() {
        // Arrange
        List<GeographicArea> listGA = new ArrayList<>();
        GeographicArea area = GeographicAreaMapper.dtoToObject(validDTO);
        Mockito.when(geographicAreaCrudRepo.findAll()).thenReturn(listGA);

        // Act
        boolean actualResult = geographicAreaRepository.addAndPersistGA(area);

        // Assert
        assertTrue(actualResult);

    }

    @Test
    void seeIfUpdateGeoAreaWork() {
        geographicAreaRepository.updateGeoArea(firstValidArea);
    }

    @Test
    void seeIfDeactivateSensorWorks() {
        //Arrange
        Mockito.when(geographicAreaCrudRepo.findById(4L)).thenReturn(Optional.of(firstValidArea));
        //Act
        boolean actualResult = geographicAreaRepository.deactivateAreaSensor(4L, "SensorThree");
        //Assert
        assertTrue(actualResult);
    }

    @Test
    void seeIfDeactivateSensorDoesntWork() {
        //Arrange
        Mockito.when(geographicAreaCrudRepo.findById(4L)).thenReturn(Optional.of(firstValidArea));
        firstValidArea.getSensors().get(0).deactivateSensor();
        //Act
        boolean actualResult = geographicAreaRepository.deactivateAreaSensor(4L, "SensorThree");
        //Assert
        assertFalse(actualResult);
    }

    @Test
    void seeIfAddDaughterAreaWorks() {
        //Arrange
        GeographicArea geographicArea = new GeographicArea();
        Mockito.doReturn(Optional.of(firstValidArea)).when(geographicAreaCrudRepo).findById(4L);
        Mockito.doReturn(Optional.of(geographicArea)).when(geographicAreaCrudRepo).findById(3L);
        //Act
        boolean actualResult = geographicAreaRepository.addChildArea(3L, 4L);
        //Assert
        assertTrue(actualResult);
    }

    @Test
    void seeIfAddDaughterAreaThrowsException1() {
        //Arrange
        GeographicArea geographicArea = new GeographicArea();
        Mockito.doReturn(Optional.empty()).when(geographicAreaCrudRepo).findById(4L);
        Mockito.doReturn(Optional.of(geographicArea)).when(geographicAreaCrudRepo).findById(3L);
        //Assert
        assertThrows(NoSuchElementException.class,
                () -> geographicAreaRepository.addChildArea(3L, 4L));
    }

    @Test
    void seeIfAddDaughterAreaThrowsException2() {
        //Arrange
        Mockito.doReturn(Optional.of(firstValidArea)).when(geographicAreaCrudRepo).findById(4L);
        Mockito.doReturn(Optional.empty()).when(geographicAreaCrudRepo).findById(3L);
        //Assert
        assertThrows(NoSuchElementException.class,
                () -> geographicAreaRepository.addChildArea(3L, 4L));
    }

    @Test
    void seeIfAddDaughterAreaDoesntWork() {
        //Arrange
        GeographicArea geographicArea = new GeographicArea();
        Mockito.doReturn(Optional.of(firstValidArea)).when(geographicAreaCrudRepo).findById(4L);
        Mockito.doReturn(Optional.of(geographicArea)).when(geographicAreaCrudRepo).findById(3L);
        geographicAreaRepository.addChildArea(3L, 4L);
        //Act
        boolean actualResult = geographicAreaRepository.addChildArea(3L, 4L);
        //Assert
        assertFalse(actualResult);
    }

    @Test
    void seeAddReadingsToGeographicAreaSensorsWorks() {
        //Arrange
        List<ReadingDTO> readingDTOS = new ArrayList<>();
        for (Reading r : validReadingList) {
            readingDTOS.add(ReadingMapper.objectToDTO(r));
        }
        //Act
        int actualResult = geographicAreaRepository.addReadingsToGeographicAreaSensors(readingDTOS);
        //Assert
        assertEquals(0, actualResult);
    }

    @Test
    void seeIfRemoveDaughterAreaWorks() {
        //Arrange
        GeographicArea geographicArea = new GeographicArea();
        Mockito.doReturn(Optional.of(firstValidArea)).when(geographicAreaCrudRepo).findById(4L);
        Mockito.doReturn(Optional.of(geographicArea)).when(geographicAreaCrudRepo).findById(3L);
        //Act
        boolean actualResult = geographicAreaRepository.removeChildArea(3L, 4L);
        //Assert
        assertFalse(actualResult);
    }

    @Test
    void seeIfRemoveDaughterAreaThrowsException1() {
        //Arrange
        GeographicArea geographicArea = new GeographicArea();
        Mockito.doReturn(Optional.empty()).when(geographicAreaCrudRepo).findById(4L);
        Mockito.doReturn(Optional.of(geographicArea)).when(geographicAreaCrudRepo).findById(3L);
        //Assert
        assertThrows(NoSuchElementException.class,
                () -> geographicAreaRepository.removeChildArea(3L, 4L));
    }

    @Test
    void seeIfRemoveDaughterAreaThrowsException2() {
        //Arrange
        Mockito.doReturn(Optional.of(firstValidArea)).when(geographicAreaCrudRepo).findById(4L);
        Mockito.doReturn(Optional.empty()).when(geographicAreaCrudRepo).findById(3L);
        //Assert
        assertThrows(NoSuchElementException.class,
                () -> geographicAreaRepository.removeChildArea(3L, 4L));
    }

    @Test
    void seeIfRemoveDaughterAreaDoesntWork() {
        //Arrange
        GeographicArea geographicArea = new GeographicArea();
        Mockito.doReturn(Optional.of(firstValidArea)).when(geographicAreaCrudRepo).findById(4L);
        Mockito.doReturn(Optional.of(geographicArea)).when(geographicAreaCrudRepo).findById(3L);
        geographicAreaRepository.addChildArea(3L, 4L);
        //Act
        boolean actualResult = geographicAreaRepository.removeChildArea(3L, 4L);
        //Assert
        assertTrue(actualResult);
    }
}