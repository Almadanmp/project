package pt.ipp.isep.dei.project.controller.controllercli;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project.io.ui.utils.DateUtils;
import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.model.geographicarea.AreaSensor;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project.model.sensortype.SensorType;
import pt.ipp.isep.dei.project.model.sensortype.SensorTypeRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * SensorSettingsController tests class.
 */
@ExtendWith(MockitoExtension.class)
class SensorSettingsControllerTest {

    // Common testing artifacts for class.

    private Date validDate1;
    private GeographicArea validGeographicArea;
    private AreaSensor validAreaSensor;
    private SensorType validSensorTypeTemperature;
    @Mock
    SensorTypeRepository sensorTypeRepository;
    @InjectMocks
    private SensorSettingsController controller;

    @BeforeEach
    void arrangeArtifacts() {
        MockitoAnnotations.initMocks(this);
        validSensorTypeTemperature = new SensorType("Temperature", "Celsius");
        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            validDate1 = validSdf.parse("01/04/2018 00:00:00");

        } catch (
                ParseException c) {
            c.printStackTrace();
        }
        validGeographicArea = new GeographicArea("GA", "City", 100, 90, new Local(0, 0, 0));
        validAreaSensor = new AreaSensor("12", "SensorDTO1", validSensorTypeTemperature.getName(), new Local(2, 4, 5), validDate1);
    }

    @Test
    void seeIfAddSensorToGeographicAreaWorks() {
        //Arrange

        validGeographicArea.addSensor(validAreaSensor);

        // Act

        boolean actualResult = controller.addSensorToGeographicArea(validAreaSensor, validGeographicArea);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfAddSensorToGeographicAreaWorksWhenSensorExists() {

        // Act

        boolean actualResult = controller.addSensorToGeographicArea(validAreaSensor, validGeographicArea);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfBuildSensorTypesStrings() {
        // Arrange
        String expectedResult = "---------------\n" +
                "Name: Temperature | Unit: Celsius \n" +
                "---------------\n";

        // Act
        Mockito.when(sensorTypeRepository.buildString()).thenReturn(expectedResult);
        String actualResult = controller.buildSensorTypesString();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfLocalIsCreated() {
        // Arrange

        double lat = 50.0;
        double lon = 50.0;
        double alt = 50.0;
        Local expectedResult = new Local(50, 50, 50);

        // Act

        Local actualResult = controller.createLocal(lat, lon, alt);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfTypeIsCreated() {
        // Arrange

        String typeString = "Humidade";
        String units = "kg/m³";
        String expectedResult = "Humidade";

        // Act
        Mockito.when(sensorTypeRepository.createTypeSensor(typeString, units)).thenReturn(new SensorType(typeString, units));
        String actualResult = controller.createType(typeString, units).getName();

        // Assert

        assertEquals(expectedResult, actualResult);
    }


    @Test
    void seeIfSensorIsCreated() {
        // Arrange

        String idString = "RF12345";
        String nameString = "XV-56D";
        double lat = 50.0;
        double lon = 50.0;
        double alt = 50.0;
        Local loc1 = controller.createLocal(lat, lon, alt);
        String typeStr = "Humidity";
        String unit = "kg/m³";
        SensorType type1 = controller.createType(typeStr, unit);
        int year = 2018;
        int month = 8;
        int day = 9;
        Date date1 = DateUtils.createDate(year, month, day);
        controller.createSensor(idString, nameString, typeStr, loc1, date1);
        SensorType t1 = new SensorType(typeStr, "kg/m³");
        AreaSensor expectedResult = new AreaSensor("RF12345", "XV-56D", typeStr, loc1,
                validDate1);

        // Act
        AreaSensor actualResult = controller.createSensor(idString, nameString, typeStr, loc1, date1);

        // Assert

        assertEquals(expectedResult, actualResult);
    }


    @Test
    void addTypeSensorToList() {

        // Arrange

        SensorType sensorType1 = new SensorType("temperature", "celsius");
        SensorType sensorType2 = new SensorType("temperature", "kelvin");
        SensorType sensorType4 = new SensorType("humidity", "percentage");

        // Act
        Mockito.when(sensorTypeRepository.add(sensorType1)).thenReturn(true);
        boolean actualResult1 = controller.addTypeSensorToList(sensorType1);
        Mockito.when(sensorTypeRepository.add(sensorType2)).thenReturn(true);
        boolean actualResult2 = controller.addTypeSensorToList(sensorType2);
        Mockito.when(sensorTypeRepository.add(sensorType4)).thenReturn(true);
        boolean actualResult4 = controller.addTypeSensorToList(sensorType4);

        // Assert

        assertTrue(actualResult1);
        assertTrue(actualResult2);
        assertTrue(actualResult4);
    }

    @Test
    void testBuildSensorString() {
        // Arrange

        AreaSensor areaSensor = new AreaSensor("RF12345", "Sensor", validSensorTypeTemperature.getName(), new Local(1, 1, 1),
                validDate1);
        String expectedResult = "Sensor, Temperature, 1.0º lat, 1.0º long \n";

        // Act

        String actualResult = controller.buildSensorString(areaSensor);

        // Assert

        assertEquals(expectedResult, actualResult);
    }
}