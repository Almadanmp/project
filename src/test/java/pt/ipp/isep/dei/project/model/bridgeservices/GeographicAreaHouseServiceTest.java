package pt.ipp.isep.dei.project.model.bridgeservices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import pt.ipp.isep.dei.project.controllerCLI.ReaderController;
import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.geographicarea.AreaSensor;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicAreaRepository;
import pt.ipp.isep.dei.project.model.house.Address;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.sensortype.SensorType;
import pt.ipp.isep.dei.project.repository.AreaTypeCrudeRepo;
import pt.ipp.isep.dei.project.repository.GeographicAreaCrudeRepo;
import pt.ipp.isep.dei.project.repository.SensorTypeCrudeRepo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class GeographicAreaHouseServiceTest {

    @Mock
    private SensorTypeCrudeRepo sensorTypeCrudeRepo;
    @Mock
    GeographicAreaCrudeRepo geographicAreaCrudeRepo;
    @Mock
    AreaTypeCrudeRepo areaTypeCrudeRepo;

    private GeographicAreaHouseService geographicAreaHouseService;
    private Date validDate1; // Date 21/11/2018
    private Date validDate2; // Date 03/09/2018
    private GeographicArea firstValidArea;
    private List<GeographicArea> validList;
    private static final Logger logger = Logger.getLogger(ReaderController.class.getName());
    private static final String PATH_TO_FRIDGE = "pt.ipp.isep.dei.project.model.device.devicetypes.FridgeType";
    private AreaSensor firstValidAreaSensor;
    private AreaSensor secondValidAreaSensor;
    private AreaSensor validAreaSensor;
    private GeographicAreaRepository geographicAreaRepository;
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
        geographicAreaHouseService = new GeographicAreaHouseService(geographicAreaCrudeRepo, areaTypeCrudeRepo, sensorTypeCrudeRepo);
        firstValidArea = new GeographicArea("Portugal", "Country", 300, 200,
                new Local(50, 50, 10));
        validList = new ArrayList<>();
        validList.add(firstValidArea);

        firstValidAreaSensor = new AreaSensor("SensorOne", "SensorOne", new SensorType("Temperature", "Celsius"), new Local(2, 2, 2), validDate1, 6008L);
        firstValidAreaSensor.setActive(true);
        secondValidAreaSensor = new AreaSensor("SensorTwo", "SensorTwo", new SensorType("Temperature", "Celsius"), new Local(10, 10, 10),
                validDate1, 6008L);
        secondValidAreaSensor.setActive(true);
        validAreaSensor = new AreaSensor("SensorThree", "SensorThree", new SensorType("temperature", "C"), new Local(10, 10, 10),
                sensorCreationTime, 6008L);
        validAreaSensor.setActive(true);

        this.geographicAreaRepository = new GeographicAreaRepository(geographicAreaCrudeRepo, areaTypeCrudeRepo, sensorTypeCrudeRepo);

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
        validHouse.setMotherArea(firstValidArea);
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
        List<Reading> actualResult = geographicAreaHouseService.getReadingsAboveCategoryIIILimit(validReadingList, validHouse);
        // Assert

        assertEquals(expectedResult, actualResult);
    }

}