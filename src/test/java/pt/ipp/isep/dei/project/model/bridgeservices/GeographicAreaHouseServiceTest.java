package pt.ipp.isep.dei.project.model.bridgeservices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project.dto.DateValueDTO;
import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.geographicarea.AreaSensor;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicAreaRepository;
import pt.ipp.isep.dei.project.model.house.Address;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.house.HouseRepository;
import pt.ipp.isep.dei.project.model.room.Room;
import pt.ipp.isep.dei.project.model.room.RoomSensor;
import pt.ipp.isep.dei.project.model.sensortype.SensorType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GeographicAreaHouseServiceTest {
    private static final String PATH_TO_FRIDGE = "pt.ipp.isep.dei.project.model.device.devicetypes.FridgeType";
    @InjectMocks
    GeographicAreaHouseService geographicAreaHouseService;
    @Mock
    private GeographicAreaRepository geographicAreaRepository;
    @Mock
    private HouseRepository houseRepository;
    private Date validDate1; // Date 21/11/2018
    private Date validDate2; // Date 03/09/2018
    private Date validDate3; // Date 12/10/2018
    private Date validDate4; // Date 01/10/2018
    private Date validDate5; // Date 04/10/2018
    private GeographicArea firstValidArea;
    private AreaSensor firstValidAreaSensor;
    private AreaSensor secondValidAreaSensor;
    private AreaSensor firstRainfallAreaSensor;
    private AreaSensor validAreaSensor;
    private Date initialTime;
    private Date endingTime;
    private Date sensorCreationTime;
    private Reading validReading1;
    private Reading validReading2;
    private Reading validRainfallReading1;
    private Reading validRainfallReading2;
    private Date validReadingDate1;
    private Date validReadingDate2; // 04-10-2018
    private Date validReadingDate3;
    private Date validReadingDate4;
    private Date validReadingDate5;
    private Date validReadingDate6;
    private Date validReadingDate7;
    private Date invalidReadingDate;
    private List<Reading> validReadingList;
    private House validHouse;
    private List<String> deviceTypeString;
    private SensorType validSensorTypeTemp;
    private SensorType validSensorTypeTemp2;
    private RoomSensor firstValidRoomSensor;
    private Date validRoomDate1;
    private Room validRoom1;
    private Date roomReadingDate1;
    private Date roomReadingDate2;
    private Date roomReadingDate3;

    @BeforeEach
    void arrangeArtifacts() {
        MockitoAnnotations.initMocks(this);
        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        SimpleDateFormat readingSD = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat validSdf2 = new SimpleDateFormat("dd/MM/yyyy");
        validSdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        try {
            validDate1 = validSdf.parse("21/11/2018 00:00:00");
            validDate2 = validSdf.parse("03/09/2018 00:00:00");
            validDate3 = validSdf.parse("12/10/2018 00:00:00");
            validDate4 = validSdf.parse("01/10/2018 00:00:00");
            validDate5 = validSdf.parse("04/10/2018 00:00:00");
            validReadingDate1 = readingSD.parse("2018-10-03");
            validReadingDate2 = readingSD.parse("2018-10-04");
            validReadingDate3 = readingSD.parse("2018-10-05");
            validReadingDate4 = readingSD.parse("2018-10-03");
            validReadingDate5 = readingSD.parse("2018-11-05");
            validReadingDate6 = readingSD.parse("2018-11-06");
            validReadingDate7 = readingSD.parse("2018-11-07");
            invalidReadingDate = readingSD.parse("2050-11-07");
            initialTime = readingSD.parse("2017-10-03");
            endingTime = readingSD.parse("2019-10-03");
            sensorCreationTime = readingSD.parse("2016-10-03");
            validRoomDate1 = validSdf2.parse("01/02/2018");
            roomReadingDate1 = validSdf2.parse("01/12/2018");
            roomReadingDate2 = validSdf2.parse("10/12/2018");
            roomReadingDate3 = validSdf2.parse("20/12/2018");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        firstValidArea = new GeographicArea("Portugal", "Country", 300, 200,
                new Local(50, 50, 10));
        firstValidArea.setId(12L);
        validSensorTypeTemp = new SensorType("temperature", "Celsius");
        validSensorTypeTemp2 = new SensorType("rainfall", "%");

        firstValidAreaSensor = new AreaSensor("SensorOne", "SensorOne", validSensorTypeTemp.getName(), new Local(2, 2, 2), validDate1);
        firstValidAreaSensor.setActive(true);
        secondValidAreaSensor = new AreaSensor("SensorTwo", "SensorTwo", validSensorTypeTemp.getName(), new Local(10, 10, 10),
                validDate1);
        secondValidAreaSensor.setActive(true);
        validAreaSensor = new AreaSensor("SensorThree", "SensorThree", validSensorTypeTemp.getName(), new Local(10, 10, 10),
                sensorCreationTime);
        validAreaSensor.setActive(true);
        firstRainfallAreaSensor = new AreaSensor("SensorRainfall", "SensorRainfall", validSensorTypeTemp2.getName(), new Local(10, 10, 10),
                sensorCreationTime);
        firstRainfallAreaSensor.setActive(true);

        validReading1 = new Reading(23, validDate2, "C", "sensorID");
        validReading2 = new Reading(23, validReadingDate1, "C", "SensorThree");

        validRainfallReading1 = new Reading(12, validDate2, "rainfall", "SensorThree");
        validRainfallReading2 = new Reading(23, validReadingDate1, "rainfall", "SensorThree");

        validAreaSensor.addReading(validReading2);
        validReadingList = new ArrayList<>();
        validReadingList.add(validReading2);

        firstRainfallAreaSensor.addReading(validRainfallReading1);
        firstRainfallAreaSensor.addReading(validRainfallReading2);

        deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        validHouse = new House("ISEP", new Address("Rua Dr. António Bernardino de Almeida", "431",
                "4455-125", "Porto", "Portugal"),
                new Local(20, 20, 20), 60,
                180, deviceTypeString);
        validHouse.setMotherAreaID(firstValidArea.getId());
        firstValidArea.addSensor(validAreaSensor);
        firstValidArea.addSensor(firstRainfallAreaSensor);
        firstValidRoomSensor = new RoomSensor("T32875", "SensorOne", "temperature", validRoomDate1);
        firstValidRoomSensor.setActive(true);
        validRoom1 = new Room("Bedroom", "Double Bedroom", 2, 15, 15, 10, "Room1");
    }

    @Test
    void seeIfGetHouseAreaTemperatureWorks() {
        //Arrange

        List<House> houses = new ArrayList<>();
        houses.add(validHouse);

        Reading mostRecent = new Reading(45D, validDate5, "C", "SensorThree");

        validAreaSensor.addReading(mostRecent);
        validAreaSensor.addReading(validReading1);

        Mockito.when(houseRepository.getHouses()).thenReturn(houses);
        Mockito.when(geographicAreaRepository.getByID(firstValidArea.getId())).thenReturn(firstValidArea);

        // Act

        double actualResult = geographicAreaHouseService.getHouseAreaTemperature();

        // Assert

        assertEquals(45D, actualResult, 0.01);
    }


    @Test
    void seeIfcategoryIIICalculusUS445Works() {

        // Act
        boolean actualResult = geographicAreaHouseService.categoryIIICalculusAboveAverage(validReading1, 0);
        // Assert
        assertTrue(actualResult);
    }

    @Test
    void seeIfcategoryIIICalculusUS445Fails() {
        // Arrange
        // Act
        boolean actualResult = geographicAreaHouseService.categoryIIICalculusAboveAverage(validReading1, 20);
        // Assert
        assertFalse(actualResult);
    }

    @Test
    void seeIfcategoryIICalculusUS445Works() {
        // Arrange
        // Act
        boolean actualResult = geographicAreaHouseService.categoryIICalculusAboveAverage(validReading1, 0);
        // Assert
        assertTrue(actualResult);
    }

    @Test
    void seeIfcategoryIICalculusUS445Fails() {
        // Arrange
        // Act
        boolean actualResult = geographicAreaHouseService.categoryIICalculusAboveAverage(validReading1, 20);
        // Assert
        assertFalse(actualResult);
    }

    @Test
    void seeIfcategoryICalculusUS445Works() {
        // Arrange
        // Act
        boolean actualResult = geographicAreaHouseService.categoryICalculusAboveAverage(validReading1, 0);
        // Assert
        assertTrue(actualResult);
    }

    @Test
    void seeIfcategoryICalculusUS445Fails() {
        // Arrange
        // Act
        boolean actualResult = geographicAreaHouseService.categoryICalculusAboveAverage(validReading1, 20);
        // Assert
        assertFalse(actualResult);
    }

    @Test
    void seeIfcategoryICalculusUS440Works() {
        // Arrange
        // Act
        boolean actualResult = geographicAreaHouseService.categoryICalculusTemperaturesLowerThanAverage(validReading1, 0);
        // Assert
        assertFalse(actualResult);
    }

    @Test
    void seeIfcategoryICalculusUS440Fails() {
        // Arrange
        // Act
        boolean actualResult = geographicAreaHouseService.categoryICalculusTemperaturesLowerThanAverage(validReading1, 20);
        // Assert
        assertTrue(actualResult);
    }

    @Test
    void seeIfcategoryIICalculusUS440Works() {
        // Arrange
        // Act
        boolean actualResult = geographicAreaHouseService.categoryIICalculusTemperaturesLowerThanAverage(validReading1, 0);
        // Assert
        assertFalse(actualResult);
    }

    @Test
    void seeIfcategoryIICalculusUS440Fails() {
        // Arrange
        // Act
        boolean actualResult = geographicAreaHouseService.categoryIICalculusTemperaturesLowerThanAverage(validReading1, 30);
        // Assert
        assertTrue(actualResult);
    }

    @Test
    void seeIfcategoryIIICalculusUS440Works() {
        // Arrange
        // Act
        boolean actualResult = geographicAreaHouseService.categoryIIICalculusTemperaturesLowerThanAverage(validReading1, 0);
        // Assert
        assertFalse(actualResult);
    }

    @Test
    void seeIfcategoryIIICalculusUS440Fails() {
        // Arrange
        // Act
        boolean actualResult = geographicAreaHouseService.categoryIIICalculusTemperaturesLowerThanAverage(validReading1, 40);
        // Assert
        assertTrue(actualResult);
    }

    @Test
    void seeIfGetAverageReadingsBetweenDatesFailsEmptyReadingList() {
        // Arrange
        Double expectedResult = Double.NaN;
        // Act
        Double actualResult = geographicAreaHouseService.getAverageReadingsBetweenFormattedDates(validDate2, validDate1, firstValidAreaSensor);
        // Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetAverageReadingsBetweenDatesWorks() {

        // Arrange
        double expectedResult = validReading2.getValue();

        // Act
        double actualResult = geographicAreaHouseService.getAverageReadingsBetweenFormattedDates(initialTime, endingTime, validAreaSensor);
        // Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetReadingsBetweenDatesWorks() {

        // Arrange
        List<Reading> expectedResult = new ArrayList<>();
        expectedResult.add(validReading2);

        // Act
        List<Reading> actualResult = geographicAreaHouseService.getReadingListBetweenFormattedDates(initialTime, endingTime, validAreaSensor);
        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetReadingsBetweenDatesFAilsWrongList() {

        // Arrange
        List<Reading> expectedResult = new ArrayList<>();
        expectedResult.add(validReading1);

        // Act
        List<Reading> actualResult = geographicAreaHouseService.getReadingListBetweenFormattedDates(initialTime, endingTime, validAreaSensor);
        // Assert

        assertNotEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetReadingsAboveCategoryIIILimitFailsEmptyList() {

        // Arrange
        House house = new House("12", new Local(2, 2, 2), 2, 2, deviceTypeString);
        house.setMotherAreaID(firstValidArea.getId());
        List<House> houses = new ArrayList<>();
        houses.add(house);
        List<Reading> expectedResult = new ArrayList<>();

        // Act
        Mockito.when(houseRepository.getHouses()).thenReturn(houses);
        Mockito.when(geographicAreaRepository.getByID(firstValidArea.getId())).thenReturn(firstValidArea);
        List<Reading> actualResult = geographicAreaHouseService.getReadingsAboveCategoryIIILimit(validReadingList);
        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetAreaSensorsByDistanceToHouse() {

        //Arrange
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add("pt.ipp.isep.dei.project.model.device.devicetypes.FridgeType");
        House house = new House("12", new Local(2, 2, 2), 2, 2, deviceTypeString);

        List<AreaSensor> listAreaSensor = new ArrayList<>();
        listAreaSensor.add(secondValidAreaSensor);
        listAreaSensor.add(firstValidAreaSensor);

        List<AreaSensor> expectedResult = new ArrayList<>();
        expectedResult.add(firstValidAreaSensor);

        //Act

        List<AreaSensor> actualResult = geographicAreaHouseService.getAreaSensorsByDistanceToHouse(listAreaSensor, house, 0);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetClosestSensorOfGivenType() {

        //Arrange
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add("pt.ipp.isep.dei.project.model.device.devicetypes.FridgeType");
        House house = new House("12", new Local(2, 2, 2), 2, 2, deviceTypeString);
        house.setMotherAreaID(firstValidArea.getId());
        List<House> houses = new ArrayList<>();
        houses.add(house);
        AreaSensor validAreaSensor = new AreaSensor("SensOne", "SensOne", validSensorTypeTemp.getName(), new Local(2, 2, 2), new Date());
        validAreaSensor.setActive(true);
        firstValidArea.addSensor(validAreaSensor);
        //Act
        Mockito.when(houseRepository.getHouses()).thenReturn(houses);
        Mockito.when(geographicAreaRepository.getByID(firstValidArea.getId())).thenReturn(firstValidArea);
        AreaSensor actualResult = geographicAreaHouseService.getClosestAreaSensorOfGivenType("temperature");

        //Assert
        assertEquals(validAreaSensor, actualResult);
    }

    //ver se funciona minDistSensor.size() > 1
    @Test
    void seeIfGetClosestSensorOfGivenTypeSizeActiveSensor() {

        //Arrange
        House house = new House("12", new Local(2, 2, 2), 2, 2, deviceTypeString);
        house.setMotherAreaID(firstValidArea.getId());
        List<House> houses = new ArrayList<>();
        houses.add(house);
        AreaSensor validAreaSensorTest1 = new AreaSensor("SensOne", "SensOne", validSensorTypeTemp.getName(), new Local(50, 50, 50), new Date());
        AreaSensor validAreaSensorTest2 = new AreaSensor("SensTwo", "SensOne", validSensorTypeTemp.getName(), new Local(50, 50, 54), new Date());
        AreaSensor validAreaSensorTest3 = new AreaSensor("SensThree", "SensOne", validSensorTypeTemp.getName(), new Local(50, 50, 55), new Date());
        validAreaSensorTest1.setActive(true);
        validAreaSensorTest2.setActive(true);
        validAreaSensorTest3.setActive(true);
        firstValidArea.addSensor(validAreaSensorTest1);
        firstValidArea.addSensor(validAreaSensorTest2);
        firstValidArea.addSensor(validAreaSensorTest3);
        Mockito.when(houseRepository.getHouses()).thenReturn(houses);
        Mockito.when(geographicAreaRepository.getByID(firstValidArea.getId())).thenReturn(firstValidArea);
        AreaSensor actualResult = geographicAreaHouseService.getClosestAreaSensorOfGivenType("temperature");

        //Assert
        assertEquals(validAreaSensor, actualResult);
    }

    @Test
    void seeIfGetClosestSensorOfGivenTypeSizeActiveSensorSameLocal() {

        //Arrange
        House house = new House("12", new Local(2, 2, 2), 2, 2, deviceTypeString);
        house.setMotherAreaID(firstValidArea.getId());
        List<House> houses = new ArrayList<>();
        houses.add(house);
        AreaSensor validAreaSensorTest1 = new AreaSensor("SensOne", "SensOne", validSensorTypeTemp.getName(), new Local(50, 50, 50), new Date());
        AreaSensor validAreaSensorTest2 = new AreaSensor("SensTwo", "SensOne", validSensorTypeTemp.getName(), new Local(50, 50, 54), new Date());
        AreaSensor validAreaSensorTest3 = new AreaSensor("SensThree", "SensOne", validSensorTypeTemp.getName(), new Local(50, 50, 55), new Date());
        AreaSensor validAreaSensorTest4 = new AreaSensor("test", "Stest", validSensorTypeTemp.getName(), new Local(10, 10, 10),
                sensorCreationTime);
        validAreaSensorTest4.addReading(validReading1);
        validAreaSensorTest1.setActive(true);
        validAreaSensorTest2.setActive(true);
        validAreaSensorTest3.setActive(true);
        validAreaSensorTest4.setActive(true);
        firstValidArea.addSensor(validAreaSensorTest1);
        firstValidArea.addSensor(validAreaSensorTest2);
        firstValidArea.addSensor(validAreaSensorTest3);
        firstValidArea.addSensor(validAreaSensorTest4);
        Mockito.when(houseRepository.getHouses()).thenReturn(houses);
        Mockito.when(geographicAreaRepository.getByID(firstValidArea.getId())).thenReturn(firstValidArea);
        AreaSensor actualResult = geographicAreaHouseService.getClosestAreaSensorOfGivenType("temperature");

        //Assert
        assertEquals(validAreaSensor, actualResult);
    }

    @Test
    void seeIfGetDistanceToHouseWorks() {
        // Arrange

        House house = new House("House", new Address("Rua das Flores", "431", "4512", "Porto", "Portugal"), new Local(
                4, 6, 6), 60, 180,
                new ArrayList<>());
        GeographicArea geographicArea = new GeographicArea("Porto", "City",
                2, 3, new Local(4, 4, 100));
        house.setMotherAreaID(geographicArea.getId());
        double expectedResult = 799.8866399214708;

        //Act
        double actualResult = geographicAreaHouseService.getDistanceToHouse(validAreaSensor, house);

        //Assert
        assertEquals(expectedResult, actualResult, 0.01);
    }

    @Test
    void seeDistanceToSensor() {
        // Act
        House house = new House("House", new Address("Rua das Flores", "431", "4512", "Porto", "Portugal"), new Local(
                4, 6, 6), 60, 180,
                new ArrayList<>());
        double actualResult = geographicAreaHouseService.calculateDistanceToSensor(firstValidAreaSensor, house);

        // Assert

        assertEquals(496.71314778391405, actualResult, 0.01);
    }

    @Test
    void getHighestAmplitudeSuccessMockito() {
        // Arrange

        List<House> houses = new ArrayList<>();
        houses.add(validHouse);
        Mockito.when(houseRepository.getHouses()).thenReturn(houses);
        Mockito.when(geographicAreaRepository.getByID(firstValidArea.getId())).thenReturn(firstValidArea);
        DateValueDTO expectedResult = new DateValueDTO(validReadingDate4, 23.0);

        // Act

        DateValueDTO actualResult = geographicAreaHouseService.getHighestTemperatureAmplitude(validDate2, validDate1);

        // Assert

        assertEquals(expectedResult.getDate(), actualResult.getDate());
        assertEquals(expectedResult.getValue(), actualResult.getValue());

    }


    @Test
    void getHighestAmplitudeInvertedDates() {
        // Assert

        assertThrows(IllegalArgumentException.class,
                () -> geographicAreaHouseService.getHighestTemperatureAmplitude(validDate1, validDate2));

    }

    @Test
    void getHighestAmplitudeNoGeographicAreaInDBl() {
        // Arrange

        List<House> houses = new ArrayList<>();
        houses.add(validHouse);
        Mockito.when(houseRepository.getHouses()).thenReturn(houses);
        Mockito.when(geographicAreaRepository.getByID(validHouse.getMotherAreaID())).thenReturn(null);

        // Assert

        assertThrows(NoSuchElementException.class,
                () -> geographicAreaHouseService.getHighestTemperatureAmplitude(validDate2, validDate1));

    }

    @Test
    void getHighestAmplitudeEmptySensor() {
        // Arrange

        GeographicArea areaNoSensors = new GeographicArea("Portugal", "Country", 300, 200,
                new Local(50, 50, 10));
        areaNoSensors.setId(12L);
        validHouse.setMotherAreaID(areaNoSensors.getId());
        List<House> houses = new ArrayList<>();
        houses.add(validHouse);
        Mockito.when(houseRepository.getHouses()).thenReturn(houses);
        Mockito.when(geographicAreaRepository.getByID(validHouse.getMotherAreaID())).thenReturn(areaNoSensors);
        areaNoSensors.addSensor(new AreaSensor("test", "test", validSensorTypeTemp2.getName(), new Local(2, 2, 2), validDate1));

        // Assert

        assertThrows(NoSuchElementException.class,
                () -> geographicAreaHouseService.getHighestTemperatureAmplitude(validDate2, validDate1));

    }


    @Test
    void getHighestAmplitudeIncompleteDatesMockito() throws IllegalArgumentException {
        // Assert

        assertThrows(IllegalArgumentException.class,
                () -> geographicAreaHouseService.getHighestTemperatureAmplitude(validDate2, null));

    }

    @Test
    void seeIfGetReadingsAboveCategoryLimit() {
        // Arrange

        List<Reading> expectedResult1 = new ArrayList<>();
        List<Reading> expectedResult2 = new ArrayList<>();
        List<Reading> expectedResult3 = new ArrayList<>();

        AreaSensor sensor = new AreaSensor("SensorTen", "SensorTen", "temperature", new Local(2, 2, 2), validDate2);
        sensor.setActive(true);
        sensor.addReading(new Reading(19, validReadingDate1, "Temperature", "SensorTen"));
        sensor.addReading(new Reading(19, validReadingDate2, "Temperature", "SensorTen"));
        sensor.addReading(new Reading(19, validReadingDate3, "Temperature", "SensorTen"));
        sensor.addReading(new Reading(19, validReadingDate4, "Temperature", "SensorTen"));
        sensor.addReading(new Reading(19, validReadingDate5, "Temperature", "SensorTen"));
        sensor.addReading(new Reading(19, validReadingDate6, "Temperature", "SensorTen"));
        sensor.addReading(new Reading(19, validReadingDate7, "Temperature", "SensorTen"));


        firstValidArea.addSensor(sensor);
        firstValidArea.removeSensor(validAreaSensor);
        validHouse.setMotherAreaID(firstValidArea.getId());
        List<House> houses = new ArrayList<>();
        houses.add(validHouse);

        Reading reading1 = new Reading(5, validReadingDate1, "temperature", "SensorTen");
        Reading reading2 = new Reading(15, validReadingDate2, "temperature", "SensorTen");
        Reading reading3 = new Reading(25, validReadingDate3, "temperature", "SensorTen");
        Reading reading4 = new Reading(27, validReadingDate4, "temperature", "SensorTen");
        Reading reading5 = new Reading(28, validReadingDate5, "temperature", "SensorTen");
        Reading reading6 = new Reading(29, validReadingDate6, "temperature", "SensorTen");
        Reading reading7 = new Reading(33, validReadingDate7, "temperature", "SensorTen");
        Reading reading8 = new Reading(0, validReadingDate7, "temperature", "SensorTen");
        Reading reading9 = new Reading(100, validReadingDate7, "temperature", "SensorTen");


        expectedResult1.add(reading5);
        expectedResult1.add(reading6);
        expectedResult1.add(reading7);
        expectedResult1.add(reading9);

        expectedResult2.add(reading6);
        expectedResult2.add(reading7);
        expectedResult2.add(reading9);

        expectedResult3.add(reading7);
        expectedResult3.add(reading9);

        List<Reading> list = new ArrayList<>();
        list.add(reading1);
        list.add(reading2);
        list.add(reading3);
        list.add(reading4);
        list.add(reading5);
        list.add(reading6);
        list.add(reading7);
        list.add(reading8);
        list.add(reading9);
        Mockito.when(houseRepository.getHouses()).thenReturn(houses);
        Mockito.when(geographicAreaRepository.getByID(firstValidArea.getId())).thenReturn(firstValidArea);

        // Act

        List<Reading> actualResult1 = geographicAreaHouseService.getReadingsAboveCategoryILimit(list);
        List<Reading> actualResult2 = geographicAreaHouseService.getReadingsAboveCategoryIILimit(list);
        List<Reading> actualResult3 = geographicAreaHouseService.getReadingsAboveCategoryIIILimit(list);

        // Assert

        assertEquals(expectedResult1, actualResult1);
        assertEquals(expectedResult2, actualResult2);
        assertEquals(expectedResult3, actualResult3);

    }

    @Test
    void seeIfGetReadingsBelowCategoryILimitWorksNoReadings() {
        // Arrange

        List<Reading> expectedResult = new ArrayList<>();
        List<Reading> readings = new ArrayList<>();
        firstValidRoomSensor.setReadings(readings);
        List<RoomSensor> roomSensors = new ArrayList<>();
        roomSensors.add(firstValidRoomSensor);
        validRoom1.setRoomSensors(roomSensors);

        // Act

        List<Reading> actualResult = geographicAreaHouseService.getReadingsBelowCategoryILimit(readings);

        // Assert

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfGetReadingsBelowCategoryLimit() {
        // Arrange

        List<Reading> expectedResult1 = new ArrayList<>();
        List<Reading> expectedResult2 = new ArrayList<>();
        List<Reading> expectedResult3 = new ArrayList<>();

        AreaSensor sensor = new AreaSensor("SensorTen", "SensorTen", "temperature", new Local(2, 2, 2), validDate2);
        sensor.setActive(true);
        sensor.addReading(new Reading(19, validReadingDate1, "Temperature", "SensorTen"));
        sensor.addReading(new Reading(19, validReadingDate2, "Temperature", "SensorTen"));
        sensor.addReading(new Reading(19, validReadingDate3, "Temperature", "SensorTen"));
        sensor.addReading(new Reading(19, validReadingDate4, "Temperature", "SensorTen"));
        sensor.addReading(new Reading(19, validReadingDate5, "Temperature", "SensorTen"));
        sensor.addReading(new Reading(19, validReadingDate6, "Temperature", "SensorTen"));
        sensor.addReading(new Reading(19, validReadingDate7, "Temperature", "SensorTen"));


        firstValidArea.addSensor(sensor);
        firstValidArea.removeSensor(validAreaSensor);
        validHouse.setMotherAreaID(firstValidArea.getId());
        List<House> houses = new ArrayList<>();
        houses.add(validHouse);
        Reading reading1 = new Reading(5, validReadingDate1, "temperature", "SensorTen");
        Reading reading2 = new Reading(15, validReadingDate2, "temperature", "SensorTen");
        Reading reading3 = new Reading(25, validReadingDate3, "temperature", "SensorTen");
        Reading reading4 = new Reading(27, validReadingDate4, "temperature", "SensorTen");
        Reading reading5 = new Reading(28, validReadingDate5, "temperature", "SensorTen");
        Reading reading6 = new Reading(29, validReadingDate6, "temperature", "SensorTen");
        Reading reading7 = new Reading(33, validReadingDate7, "temperature", "SensorTen");
        Reading reading8 = new Reading(0, validReadingDate1, "temperature", "SensorTen");
        Reading reading9 = new Reading(100, validReadingDate7, "temperature", "SensorTen");

        expectedResult1.add(reading1);
        expectedResult1.add(reading2);
        expectedResult1.add(reading8);

        expectedResult2.add(reading1);
        expectedResult2.add(reading2);
        expectedResult2.add(reading8);

        expectedResult3.add(reading1);
        expectedResult3.add(reading2);
        expectedResult3.add(reading8);

        List<Reading> list = new ArrayList<>();
        list.add(reading1);
        list.add(reading2);
        list.add(reading3);
        list.add(reading4);
        list.add(reading5);
        list.add(reading6);
        list.add(reading7);
        list.add(reading8);
        list.add(reading9);

        Mockito.when(houseRepository.getHouses()).thenReturn(houses);
        Mockito.when(geographicAreaRepository.getByID(firstValidArea.getId())).thenReturn(firstValidArea);

        // Act

        List<Reading> actualResult1 = geographicAreaHouseService.getReadingsBelowCategoryILimit(list);
        List<Reading> actualResult2 = geographicAreaHouseService.getReadingsBelowCategoryIILimit(list);
        List<Reading> actualResult3 = geographicAreaHouseService.getReadingsBelowCategoryIIILimit(list);

        // Assert

        assertEquals(expectedResult1, actualResult1);
        assertEquals(expectedResult2, actualResult2);
        assertEquals(expectedResult3, actualResult3);

    }

    @Test
    void seeIfGetReadingsAboveCategoryLimitNaNCondition() {
        // Arrange

        List<Reading> expectedResult = new ArrayList<>();

        AreaSensor sensor = new AreaSensor("SensorTen", "SensorTen", "temperature", new Local(2, 2, 2), validDate2);
        sensor.setActive(true);
        sensor.addReading(new Reading(19, validReadingDate1, "Temperature", "SensorTen"));
        sensor.addReading(new Reading(19, validReadingDate2, "Temperature", "SensorTen"));
        sensor.addReading(new Reading(19, validReadingDate3, "Temperature", "SensorTen"));
        sensor.addReading(new Reading(19, validReadingDate4, "Temperature", "SensorTen"));
        sensor.addReading(new Reading(19, validReadingDate5, "Temperature", "SensorTen"));
        sensor.addReading(new Reading(19, validReadingDate6, "Temperature", "SensorTen"));
        sensor.addReading(new Reading(19, validReadingDate7, "Temperature", "SensorTen"));


        firstValidArea.addSensor(sensor);
        firstValidArea.removeSensor(validAreaSensor);
        validHouse.setMotherAreaID(firstValidArea.getId());

        List<Reading> list = new ArrayList<>();

        // Act

        List<Reading> actualResult1 = geographicAreaHouseService.getReadingsAboveCategoryILimit(list);
        List<Reading> actualResult2 = geographicAreaHouseService.getReadingsAboveCategoryIILimit(list);
        List<Reading> actualResult3 = geographicAreaHouseService.getReadingsAboveCategoryIIILimit(list);

        List<Reading> actualResult4 = geographicAreaHouseService.getReadingsBelowCategoryILimit(list);
        List<Reading> actualResult5 = geographicAreaHouseService.getReadingsBelowCategoryIILimit(list);
        List<Reading> actualResult6 = geographicAreaHouseService.getReadingsBelowCategoryIIILimit(list);

        // Assert

        assertEquals(expectedResult, actualResult1);
        assertEquals(expectedResult, actualResult2);
        assertEquals(expectedResult, actualResult3);

        assertEquals(expectedResult, actualResult4);
        assertEquals(expectedResult, actualResult5);
        assertEquals(expectedResult, actualResult6);
    }

    @Test
    void seeIfcategoryIIICalculusUS445FailsBoundaryValue() {

        // Arrange
        double result = (validReading1.getValue() - 18.8 - 4) / 0.33;

        // Act
        boolean actualResult = geographicAreaHouseService.categoryIIICalculusAboveAverage(validReading1, result);

        // Assert
        assertFalse(actualResult);
    }

    @Test
    void seeIfcategoryIICalculusUS445FailsBoundaryValue() {

        // Arrange
        double result = (validReading1.getValue() - 18.8 - 3) / 0.33;

        // Act
        boolean actualResult = geographicAreaHouseService.categoryIICalculusAboveAverage(validReading1, result);

        // Assert
        assertFalse(actualResult);
    }

    @Test
    void seeIfcategoryICalculusUS445FailsBoundaryValue() {

        // Arrange
        double result = (validReading1.getValue() - 18.8 - 2) / 0.33;

        // Act
        boolean actualResult = geographicAreaHouseService.categoryICalculusAboveAverage(validReading1, result);

        // Assert
        assertFalse(actualResult);
    }

    @Test
    void seeIfcategoryICalculusUS440FailsBoundaryValue() {

        // Arrange
        double result = (validReading1.getValue() - 18.8 + 2) / 0.33;

        // Act
        boolean actualResult = geographicAreaHouseService.categoryICalculusTemperaturesLowerThanAverage(validReading1, result);

        // Assert
        assertFalse(actualResult);
    }

    @Test
    void seeIfcategoryIICalculusUS440FailsBoundaryValue() {

        // Arrange
        double result = (validReading1.getValue() - 18.8 + 3) / 0.33;

        // Act
        boolean actualResult = geographicAreaHouseService.categoryIICalculusTemperaturesLowerThanAverage(validReading1, result);

        // Assert
        assertFalse(actualResult);
    }

    @Test
    void seeIfcategoryIIICalculusUS440FailsBoundaryValue() {

        // Arrange
        double result = (validReading1.getValue() - 18.8 + 4) / 0.33;

        // Act
        boolean actualResult = geographicAreaHouseService.categoryIIICalculusTemperaturesLowerThanAverage(validReading1, result);

        // Assert
        assertFalse(actualResult);
    }

    @Test
    void seeIfGetReadingsAboveCategoryLimitNaNTemperatureList() {

        // Arrange

        List<Reading> expectedResult = new ArrayList<>();

        AreaSensor sensor = new AreaSensor("SensorTen", "SensorTen", "temperature", new Local(2, 2, 2), validDate2);
        sensor.setActive(true);
        sensor.addReading(new Reading(19, validReadingDate1, "Temperature", "SensorTen"));
        sensor.addReading(new Reading(19, validReadingDate2, "Temperature", "SensorTen"));
        sensor.addReading(new Reading(19, validReadingDate3, "Temperature", "SensorTen"));
        sensor.addReading(new Reading(19, validReadingDate4, "Temperature", "SensorTen"));
        sensor.addReading(new Reading(19, validReadingDate5, "Temperature", "SensorTen"));
        sensor.addReading(new Reading(19, validReadingDate6, "Temperature", "SensorTen"));
        sensor.addReading(new Reading(19, validReadingDate7, "Temperature", "SensorTen"));


        firstValidArea.addSensor(sensor);
        firstValidArea.removeSensor(validAreaSensor);
        validHouse.setMotherAreaID(firstValidArea.getId());
        List<House> houses = new ArrayList<>();
        houses.add(validHouse);
        Reading reading1 = new Reading(5, invalidReadingDate, "temperature", "SensorTen");
        Reading reading2 = new Reading(15, invalidReadingDate, "temperature", "SensorTen");
        Reading reading3 = new Reading(25, invalidReadingDate, "temperature", "SensorTen");
        Reading reading4 = new Reading(27, invalidReadingDate, "temperature", "SensorTen");
        Reading reading5 = new Reading(28, invalidReadingDate, "temperature", "SensorTen");
        Reading reading6 = new Reading(29, invalidReadingDate, "temperature", "SensorTen");
        Reading reading7 = new Reading(33, invalidReadingDate, "temperature", "SensorTen");
        Reading reading8 = new Reading(0, invalidReadingDate, "temperature", "SensorTen");
        Reading reading9 = new Reading(100, invalidReadingDate, "temperature", "SensorTen");

        List<Reading> list = new ArrayList<>();
        list.add(reading1);
        list.add(reading2);
        list.add(reading3);
        list.add(reading4);
        list.add(reading5);
        list.add(reading6);
        list.add(reading7);
        list.add(reading8);
        list.add(reading9);
        Mockito.when(houseRepository.getHouses()).thenReturn(houses);
        Mockito.when(geographicAreaRepository.getByID(firstValidArea.getId())).thenReturn(firstValidArea);

        // Act

        List<Reading> actualResult1 = geographicAreaHouseService.getReadingsBelowCategoryILimit(list);
        List<Reading> actualResult2 = geographicAreaHouseService.getReadingsBelowCategoryIILimit(list);
        List<Reading> actualResult3 = geographicAreaHouseService.getReadingsBelowCategoryIIILimit(list);

        List<Reading> actualResult4 = geographicAreaHouseService.getReadingsAboveCategoryILimit(list);
        List<Reading> actualResult5 = geographicAreaHouseService.getReadingsAboveCategoryIILimit(list);
        List<Reading> actualResult6 = geographicAreaHouseService.getReadingsAboveCategoryIIILimit(list);

        // Assert

        assertEquals(expectedResult, actualResult1);
        assertEquals(expectedResult, actualResult2);
        assertEquals(expectedResult, actualResult3);

        assertEquals(expectedResult, actualResult4);
        assertEquals(expectedResult, actualResult5);
        assertEquals(expectedResult, actualResult6);
    }

    @Test
    void getHottestDaySuccessMockito() {
        // Arrange

        List<House> houses = new ArrayList<>();
        houses.add(validHouse);
        Mockito.when(houseRepository.getHouses()).thenReturn(houses);
        Mockito.when(geographicAreaRepository.getByID(firstValidArea.getId())).thenReturn(firstValidArea);
        DateValueDTO expectedResult = new DateValueDTO(validReadingDate4, 23.0);

        // Act

        DateValueDTO actualResult = geographicAreaHouseService.getHottestDay(validDate2, validDate1);

        // Assert

        assertEquals(expectedResult.getDate(), actualResult.getDate());
        assertEquals(expectedResult.getValue(), actualResult.getValue());

    }


    @Test
    void getHottestDayInvertedDates() {
        // Assert

        assertThrows(IllegalArgumentException.class,
                () -> geographicAreaHouseService.getHottestDay(validDate1, validDate2));

    }

    @Test
    void getHottestDayNoGeographicAreaInDBl() {
        // Arrange

        List<House> houses = new ArrayList<>();
        houses.add(validHouse);
        Mockito.when(houseRepository.getHouses()).thenReturn(houses);
        Mockito.when(geographicAreaRepository.getByID(validHouse.getMotherAreaID())).thenReturn(null);

        // Assert

        assertThrows(NoSuchElementException.class,
                () -> geographicAreaHouseService.getHottestDay(validDate2, validDate1));

    }

    @Test
    void getHottestDayEmptySensor() {
        // Arrange

        GeographicArea areaNoSensors = new GeographicArea("Portugal", "Country", 300, 200,
                new Local(50, 50, 10));
        areaNoSensors.setId(12L);
        validHouse.setMotherAreaID(areaNoSensors.getId());
        List<House> houses = new ArrayList<>();
        houses.add(validHouse);
        Mockito.when(houseRepository.getHouses()).thenReturn(houses);
        Mockito.when(geographicAreaRepository.getByID(validHouse.getMotherAreaID())).thenReturn(areaNoSensors);
        areaNoSensors.addSensor(new AreaSensor("test", "test", validSensorTypeTemp2.getName(), new Local(2, 2, 2), validDate1));

        // Assert

        assertThrows(NoSuchElementException.class,
                () -> geographicAreaHouseService.getHottestDay(validDate2, validDate1));

    }


    @Test
    void getHottestDayIncompleteDatesMockito() throws IllegalArgumentException {
        // Assert

        assertThrows(IllegalArgumentException.class,
                () -> geographicAreaHouseService.getHottestDay(validDate2, null));

    }

    @Test
    void getLastColdestDaySuccessMockito() {
        // Arrange

        List<House> houses = new ArrayList<>();
        houses.add(validHouse);
        Mockito.when(houseRepository.getHouses()).thenReturn(houses);
        Mockito.when(geographicAreaRepository.getByID(firstValidArea.getId())).thenReturn(firstValidArea);
        DateValueDTO expectedResult = new DateValueDTO(validReadingDate4, 0.0);

        // Act

        DateValueDTO actualResult = geographicAreaHouseService.getLastColdestDay(validDate2, validDate1);

        // Assert

        assertEquals(expectedResult.getDate(), actualResult.getDate());
        assertEquals(expectedResult.getValue(), actualResult.getValue());

    }


    @Test
    void getLastColdestDayInvertedDates() {
        // Assert

        assertThrows(IllegalArgumentException.class,
                () -> geographicAreaHouseService.getLastColdestDay(validDate1, validDate2));

    }

    @Test
    void getLastColdestDayNoGeographicAreaInDBl() {
        // Arrange

        List<House> houses = new ArrayList<>();
        houses.add(validHouse);
        Mockito.when(houseRepository.getHouses()).thenReturn(houses);
        Mockito.when(geographicAreaRepository.getByID(validHouse.getMotherAreaID())).thenReturn(null);

        // Assert

        assertThrows(NoSuchElementException.class,
                () -> geographicAreaHouseService.getLastColdestDay(validDate2, validDate1));

    }

    @Test
    void getLastColdestDayEmptySensor() {
        // Arrange

        GeographicArea areaNoSensors = new GeographicArea("Portugal", "Country", 300, 200,
                new Local(50, 50, 10));
        areaNoSensors.setId(12L);
        validHouse.setMotherAreaID(areaNoSensors.getId());
        List<House> houses = new ArrayList<>();
        houses.add(validHouse);
        Mockito.when(houseRepository.getHouses()).thenReturn(houses);
        Mockito.when(geographicAreaRepository.getByID(validHouse.getMotherAreaID())).thenReturn(areaNoSensors);
        areaNoSensors.addSensor(new AreaSensor("test", "test", validSensorTypeTemp2.getName(), new Local(2, 2, 2), validDate1));

        // Assert

        assertThrows(NoSuchElementException.class,
                () -> geographicAreaHouseService.getLastColdestDay(validDate2, validDate1));

    }


    @Test
    void getLastColdestDayIncompleteDatesMockito() throws IllegalArgumentException {
        // Assert

        assertThrows(IllegalArgumentException.class,
                () -> geographicAreaHouseService.getLastColdestDay(validDate2, null));

    }

    @Test
    void seeIfGetTotalRainfallOnGivenDay() {
        // Arrange

        List<House> houses = new ArrayList<>();
        houses.add(validHouse);
        long areaId = firstValidArea.getId();

        double expectedResult = 23.0;

        // Act

        Mockito.when(houseRepository.getHouses()).thenReturn(houses);
        Mockito.when(geographicAreaRepository.getByID(areaId)).thenReturn(firstValidArea);

        double actualResult = geographicAreaHouseService.getTotalRainfallOnGivenDay(validReadingDate1);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getAverageRainfallSuccessMockito() {
        // Arrange

        List<House> houses = new ArrayList<>();
        houses.add(validHouse);
        Mockito.when(houseRepository.getHouses()).thenReturn(houses);
        Mockito.when(geographicAreaRepository.getByID(firstValidArea.getId())).thenReturn(firstValidArea);
        double expectedResult = 17.5;

        // Act

        double actualResult = geographicAreaHouseService.getAverageDailyRainfall(validDate2, validDate1);

        // Assert

        assertEquals(expectedResult, actualResult);

    }


    @Test
    void getAverageRainfallInvertedDates() {
        // Assert

        assertThrows(IllegalArgumentException.class,
                () -> geographicAreaHouseService.getAverageDailyRainfall(validDate1, validDate2));

    }

    @Test
    void getAverageRainfallNoGeographicAreaInDBl() {
        // Arrange

        List<House> houses = new ArrayList<>();
        houses.add(validHouse);
        Mockito.when(houseRepository.getHouses()).thenReturn(houses);
        Mockito.when(geographicAreaRepository.getByID(validHouse.getMotherAreaID())).thenReturn(null);

        // Assert

        assertThrows(NoSuchElementException.class,
                () -> geographicAreaHouseService.getAverageDailyRainfall(validDate2, validDate1));

    }

    @Test
    void getAverageRainfallEmptySensor() {
        // Arrange

        GeographicArea areaNoSensors = new GeographicArea("Portugal", "Country", 300, 200,
                new Local(50, 50, 10));
        areaNoSensors.setId(12L);
        validHouse.setMotherAreaID(areaNoSensors.getId());
        areaNoSensors.addSensor(new AreaSensor("test", "test", "rainfall", new Local(2, 2, 2), validDate1));
        List<House> houses = new ArrayList<>();
        houses.add(validHouse);
        Mockito.when(houseRepository.getHouses()).thenReturn(houses);
        Mockito.when(geographicAreaRepository.getByID(validHouse.getMotherAreaID())).thenReturn(areaNoSensors);

        // Assert

        assertThrows(IllegalArgumentException.class,
                () -> geographicAreaHouseService.getAverageDailyRainfall(validDate2, validDate1));

    }


    @Test
    void getAverageRainfallIncompleteDatesMockito() throws IllegalArgumentException {
        // Assert

        assertThrows(IllegalArgumentException.class,
                () -> geographicAreaHouseService.getAverageDailyRainfall(validDate2, null));

    }

}

