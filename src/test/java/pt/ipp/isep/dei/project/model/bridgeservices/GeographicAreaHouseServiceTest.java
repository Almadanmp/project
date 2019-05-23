package pt.ipp.isep.dei.project.model.bridgeservices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project.dto.DateDTO;
import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.geographicarea.AreaSensor;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicAreaRepository;
import pt.ipp.isep.dei.project.model.house.Address;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.house.HouseRepository;
import pt.ipp.isep.dei.project.model.sensortype.SensorType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GeographicAreaHouseServiceTest {
    @Mock
    private GeographicAreaRepository geographicAreaRepository;
    @Mock
    private HouseRepository houseRepository;

    @InjectMocks
    GeographicAreaHouseService geographicAreaHouseService;

    private Date validDate1; // Date 21/11/2018
    private Date validDate2; // Date 03/09/2018
    private GeographicArea firstValidArea;
    private List<GeographicArea> validList;
    private static final String PATH_TO_FRIDGE = "pt.ipp.isep.dei.project.model.device.devicetypes.FridgeType";
    private AreaSensor firstValidAreaSensor;
    private AreaSensor secondValidAreaSensor;
    private AreaSensor validAreaSensor;

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
    private SensorType validSensortypeTemp;
    private SensorType validSensortypeTemp2;

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
        firstValidArea.setId(12L);
        validList = new ArrayList<>();
        validList.add(firstValidArea);
        validSensortypeTemp = new SensorType("Temperature", "Celsius");
        validSensortypeTemp2 = new SensorType("Rainfall", "Celsius");

        firstValidAreaSensor = new AreaSensor("SensorOne", "SensorOne", validSensortypeTemp.getName(), new Local(2, 2, 2), validDate1);
        firstValidAreaSensor.setActive(true);
        secondValidAreaSensor = new AreaSensor("SensorTwo", "SensorTwo", validSensortypeTemp.getName(), new Local(10, 10, 10),
                validDate1);
        secondValidAreaSensor.setActive(true);
        validAreaSensor = new AreaSensor("SensorThree", "SensorThree", validSensortypeTemp.getName(), new Local(10, 10, 10),
                sensorCreationTime);
        validAreaSensor.setActive(true);

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
    void seeIfcategoryIIICalculusUS445Works() {

        // Act
        boolean actualResult = geographicAreaHouseService.categoryIIICalculusAboveAverage(validReading, 0);
        // Assert
        assertTrue(actualResult);
    }

    @Test
    void seeIfcategoryIIICalculusUS445FailsBoundaryValue() {

        // Arrange
        double result = (validReading.getValue() / 0.33) - 18.8 - 4;

        // Act
        boolean actualResult = geographicAreaHouseService.categoryIIICalculusAboveAverage(validReading, result);

        // Assert
        assertFalse(actualResult);
    }

    @Test
    void seeIfcategoryIIICalculusUS445Fails() {
        // Arrange
        // Act
        boolean actualResult = geographicAreaHouseService.categoryIIICalculusAboveAverage(validReading, 20);
        // Assert
        assertFalse(actualResult);
    }

    @Test
    void seeIfcategoryIICalculusUS445Works() {
        // Arrange
        // Act
        boolean actualResult = geographicAreaHouseService.categoryIICalculusAboveAverage(validReading, 0);
        // Assert
        assertTrue(actualResult);
    }

    @Test
    void seeIfcategoryIICalculusUS445Fails() {
        // Arrange
        // Act
        boolean actualResult = geographicAreaHouseService.categoryIICalculusAboveAverage(validReading, 20);
        // Assert
        assertFalse(actualResult);
    }

    @Test
    void seeIfcategoryICalculusUS445Works() {
        // Arrange
        // Act
        boolean actualResult = geographicAreaHouseService.categoryICalculusAboveAverage(validReading, 0);
        // Assert
        assertTrue(actualResult);
    }

    @Test
    void seeIfcategoryICalculusUS445Fails() {
        // Arrange
        // Act
        boolean actualResult = geographicAreaHouseService.categoryICalculusAboveAverage(validReading, 20);
        // Assert
        assertFalse(actualResult);
    }

    @Test
    void seeIfcategoryICalculusUS440Works() {
        // Arrange
        // Act
        boolean actualResult = geographicAreaHouseService.categoryICalculusTemperaturesLowerThanAverage(validReading, 0);
        // Assert
        assertFalse(actualResult);
    }

    @Test
    void seeIfcategoryICalculusUS440Fails() {
        // Arrange
        // Act
        boolean actualResult = geographicAreaHouseService.categoryICalculusTemperaturesLowerThanAverage(validReading, 20);
        // Assert
        assertTrue(actualResult);
    }

    @Test
    void seeIfcategoryIICalculusUS440Works() {
        // Arrange
        // Act
        boolean actualResult = geographicAreaHouseService.categoryIICalculusTemperaturesLowerThanAverage(validReading, 0);
        // Assert
        assertFalse(actualResult);
    }

    @Test
    void seeIfcategoryIICalculusUS440Fails() {
        // Arrange
        // Act
        boolean actualResult = geographicAreaHouseService.categoryIICalculusTemperaturesLowerThanAverage(validReading, 30);
        // Assert
        assertTrue(actualResult);
    }

    @Test
    void seeIfcategoryIIICalculusUS440Works() {
        // Arrange
        // Act
        boolean actualResult = geographicAreaHouseService.categoryIIICalculusTemperaturesLowerThanAverage(validReading, 0);
        // Assert
        assertFalse(actualResult);
    }

    @Test
    void seeIfcategoryIIICalculusUS440Fails() {
        // Arrange
        // Act
        boolean actualResult = geographicAreaHouseService.categoryIIICalculusTemperaturesLowerThanAverage(validReading, 40);
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
        expectedResult.add(validReading);

        // Act
        List<Reading> actualResult = geographicAreaHouseService.getReadingListBetweenFormattedDates(initialTime, endingTime, validAreaSensor);
        // Assert

        assertNotEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetReadingsAboveCategoryIIILimitFailsEmptyList() {

        // Arrange
        List<Reading> expectedResult = new ArrayList<>();

        // Act
        Mockito.when(geographicAreaRepository.getByID(firstValidArea.getId())).thenReturn(firstValidArea);
        List<Reading> actualResult = geographicAreaHouseService.getReadingsAboveCategoryIIILimit(validReadingList, validHouse);
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
        AreaSensor validAreaSensor = new AreaSensor("SensOne", "SensOne", validSensortypeTemp.getName(), new Local(2, 2, 2), new Date());
        validAreaSensor.setActive(true);
        firstValidArea.addSensor(validAreaSensor);
        //Act
        AreaSensor actualResult = geographicAreaHouseService.getClosestAreaSensorOfGivenType("Temperature", house, firstValidArea);

        //Assert
        assertEquals(validAreaSensor, actualResult);
    }

    //ver se funciona minDistSensor.size() > 1
    @Test
    void seeIfGetClosestSensorOfGivenTypeSizeActiveSensor() {

        //Arrange
        House house = new House("12", new Local(2, 2, 2), 2, 2, deviceTypeString);
        AreaSensor validAreaSensorTest1 = new AreaSensor("SensOne", "SensOne", validSensortypeTemp.getName(), new Local(50, 50, 50), new Date());
        AreaSensor validAreaSensorTest2 = new AreaSensor("SensTwo", "SensOne", validSensortypeTemp.getName(), new Local(50, 50, 54), new Date());
        AreaSensor validAreaSensorTest3 = new AreaSensor("SensThree", "SensOne", validSensortypeTemp.getName(), new Local(50, 50, 55), new Date());
        validAreaSensorTest1.setActive(true);
        validAreaSensorTest2.setActive(true);
        validAreaSensorTest3.setActive(true);
        firstValidArea.addSensor(validAreaSensorTest1);
        firstValidArea.addSensor(validAreaSensorTest2);
        firstValidArea.addSensor(validAreaSensorTest3);

        AreaSensor actualResult = geographicAreaHouseService.getClosestAreaSensorOfGivenType("Temperature", house, firstValidArea);

        //Assert
        assertEquals(validAreaSensor, actualResult);
    }

    @Test
    void seeIfGetClosestSensorOfGivenTypeSizeActiveSensorSameLocal() {

        //Arrange
        House house = new House("12", new Local(2, 2, 2), 2, 2, deviceTypeString);
        AreaSensor validAreaSensorTest1 = new AreaSensor("SensOne", "SensOne", validSensortypeTemp.getName(), new Local(50, 50, 50), new Date());
        AreaSensor validAreaSensorTest2 = new AreaSensor("SensTwo", "SensOne", validSensortypeTemp.getName(), new Local(50, 50, 54), new Date());
        AreaSensor validAreaSensorTest3 = new AreaSensor("SensThree", "SensOne", validSensortypeTemp.getName(), new Local(50, 50, 55), new Date());
        AreaSensor validAreaSensorTest4 = new AreaSensor("test", "Stest", validSensortypeTemp.getName(), new Local(10, 10, 10),
                sensorCreationTime);
        validAreaSensorTest4.addReading(validReading);
        validAreaSensorTest1.setActive(true);
        validAreaSensorTest2.setActive(true);
        validAreaSensorTest3.setActive(true);
        validAreaSensorTest4.setActive(true);
        firstValidArea.addSensor(validAreaSensorTest1);
        firstValidArea.addSensor(validAreaSensorTest2);
        firstValidArea.addSensor(validAreaSensorTest3);
        firstValidArea.addSensor(validAreaSensorTest4);

        AreaSensor actualResult = geographicAreaHouseService.getClosestAreaSensorOfGivenType("Temperature", house, firstValidArea);

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
    void idDateDTOValidSuccess() {
        DateDTO dateDTO = new DateDTO();
        dateDTO.setInitialDate(validDate2);
        dateDTO.setEndDate(validDate1);
        assertTrue(geographicAreaHouseService.isDateDTOValid(dateDTO));
    }

    @Test
    void idDateDTOValidInvalidNoEndDate() {
        DateDTO dateDTO = new DateDTO();
        dateDTO.setInitialDate(validDate1);
        assertFalse(geographicAreaHouseService.isDateDTOValid(dateDTO));
    }

    @Test
    void idDateDTOValidInvalidNoDates() {
        DateDTO dateDTO = new DateDTO();
        assertFalse(geographicAreaHouseService.isDateDTOValid(dateDTO));
    }

    @Test
    void idDateDTOValidInvalidNoInitialDate() {
        DateDTO dateDTO = new DateDTO();
        dateDTO.setEndDate(validDate1);
        assertFalse(geographicAreaHouseService.isDateDTOValid(dateDTO));
    }

    @Test
    void idDateDTOValidInvalidInvertedDates() {
        DateDTO dateDTO = new DateDTO();
        dateDTO.setInitialDate(validDate1);
        dateDTO.setEndDate(validDate1);
        assertFalse(geographicAreaHouseService.isDateDTOValid(dateDTO));
    }

    @Test
    void idDateDTOValidValidSameDate() {
        DateDTO dateDTO = new DateDTO();
        dateDTO.setInitialDate(validDate1);
        dateDTO.setEndDate(validDate1);
        assertFalse(geographicAreaHouseService.isDateDTOValid(dateDTO));
    }

    @Test
    void getHighestAmplitudeSuccessMockito() {

        DateDTO dateDTO = new DateDTO();
        dateDTO.setInitialDate(validDate2);
        dateDTO.setEndDate(validDate1);
        List<House> houses = new ArrayList<>();
        houses.add(validHouse);
        Mockito.when(houseRepository.getHouses()).thenReturn(houses);
        Mockito.when(geographicAreaRepository.getByID(firstValidArea.getId())).thenReturn(firstValidArea);
        String expectedResult = "03/10/2018, with 0.0ºC";

        String actualResult = geographicAreaHouseService.getHighestTemperatureAmplitude(dateDTO);

        assertEquals(expectedResult, actualResult);
    }


    @Test
    void getHighestAmplitudeInvertedDates() {

        DateDTO dateDTO = new DateDTO();
        dateDTO.setInitialDate(validDate1);
        dateDTO.setEndDate(validDate2);
        assertThrows(IllegalArgumentException.class,
                () -> geographicAreaHouseService.getHighestTemperatureAmplitude(dateDTO));
    }

    @Test
    void getHighestAmplitudeNoGeographicAreaInDBl() {

        DateDTO dateDTO = new DateDTO();
        dateDTO.setInitialDate(validDate2);
        dateDTO.setEndDate(validDate1);
        List<House> houses = new ArrayList<>();
        houses.add(validHouse);
        Mockito.when(houseRepository.getHouses()).thenReturn(houses);
        Mockito.when(geographicAreaRepository.getByID(validHouse.getMotherAreaID())).thenReturn(null);

        assertThrows(NoSuchElementException.class,
                () -> geographicAreaHouseService.getHighestTemperatureAmplitude(dateDTO));
    }

    @Test
    void getHighestAmplitudeEmptySensor() {
        GeographicArea areaNoSensors = new GeographicArea("Portugal", "Country", 300, 200,
                new Local(50, 50, 10));
        areaNoSensors.setId(12L);
        validHouse.setMotherAreaID(areaNoSensors.getId());
        DateDTO dateDTO = new DateDTO();
        dateDTO.setInitialDate(validDate2);
        dateDTO.setEndDate(validDate1);
        List<House> houses = new ArrayList<>();
        houses.add(validHouse);
        Mockito.when(houseRepository.getHouses()).thenReturn(houses);
        Mockito.when(geographicAreaRepository.getByID(validHouse.getMotherAreaID())).thenReturn(areaNoSensors);
        areaNoSensors.addSensor(new AreaSensor("test", "test", validSensortypeTemp2.getName(), new Local(2, 2, 2), validDate1));
        assertThrows(NoSuchElementException.class,
                () -> geographicAreaHouseService.getHighestTemperatureAmplitude(dateDTO));
    }


    @Test
    void getHighestAmplitudeIncompleteDatesMockito() throws IllegalArgumentException {

        DateDTO dateDTO = new DateDTO();
        dateDTO.setInitialDate(validDate2);
        assertThrows(IllegalArgumentException.class,
                () -> geographicAreaHouseService.getHighestTemperatureAmplitude(dateDTO));
    }
}

