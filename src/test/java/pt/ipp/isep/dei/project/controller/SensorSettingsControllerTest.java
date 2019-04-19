package pt.ipp.isep.dei.project.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.model.geographicArea.AreaSensor;
import pt.ipp.isep.dei.project.model.room.HouseSensor;
import pt.ipp.isep.dei.project.model.sensorType.SensorType;
import pt.ipp.isep.dei.project.model.sensorType.SensorTypeService;
import pt.ipp.isep.dei.project.repository.AreaSensorRepository;
import pt.ipp.isep.dei.project.repository.SensorTypeRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * SensorSettingsController tests class.
 */
@ExtendWith(MockitoExtension.class)
class SensorSettingsControllerTest {

    // Common testing artifacts for class.

    private SensorSettingsController controller = new SensorSettingsController();
    private Date validDate1;


    @Mock
    private SensorTypeRepository sensorTypeRepository;

    @Mock
    private AreaSensorRepository areaSensorRepository;

    @BeforeEach
    void arrangeArtifacts() {
        MockitoAnnotations.initMocks(this);
        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            validDate1 = validSdf.parse("01/04/2018 00:00:00");

        } catch (
                ParseException c) {
            c.printStackTrace();
        }
    }

    @Test
    void seeIfBuildSensorTypesStrings() {
        // Arrange

        List<SensorType> sensorTypes = new ArrayList<>();
        SensorTypeService service = new SensorTypeService(sensorTypeRepository);
        SensorType typeA = new SensorType("Temperature", "Celsius");
        sensorTypes.add(typeA);
        Mockito.when(sensorTypeRepository.findAll()).thenReturn(sensorTypes);
        String expectedResult = "---------------\n" +
                "0) Name: Temperature | Unit: Celsius \n" +
                "---------------\n";

        // Act

        String actualResult = controller.buildSensorTypesString(service);

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
        SensorTypeService sensorTypeList = new SensorTypeService(sensorTypeRepository);

        // Act

        String actualResult = controller.createType(sensorTypeList, typeString, units).getName();

        // Assert

        assertEquals(expectedResult, actualResult);
    }


    @Test
    void seeIfDateIsCreated() {
        // Arrange

        int year = 2018;
        int month = 3;
        int day = 01;
        Date expectedResult = validDate1;

        // Act

        Date actualResult = controller.createDate(year, month, day);

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
        SensorTypeService sensorTypeList = new SensorTypeService(sensorTypeRepository);
        String typeStr = "Humidity";
        String unit = "kg/m³";
        SensorType type1 = controller.createType(sensorTypeList, typeStr, unit);
        int year = 2018;
        int month = 8;
        int day = 9;
        Date date1 = controller.createDate(year, month, day);
        controller.createSensor(idString, nameString, type1, loc1, date1, 6008L);
        SensorType t1 = new SensorType(typeStr, "kg/m³");
        AreaSensor expectedResult = new AreaSensor("RF12345", "XV-56D", t1, loc1,
                validDate1, 6008L);

        // Act

        AreaSensor actualResult = controller.createSensor(idString, nameString, type1, loc1, date1, 6008L);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void ensureThatWeCreateARoomSensor() {

        // Arrange

        String idString = "T289738";
        String roomID = "RoomDA";
        String nameString = "XV-56D";
        String typeStr = "Temperatura";
        String unit = "Celsius";
        SensorTypeService sensorTypeList = new SensorTypeService(sensorTypeRepository);
        SensorType firstType = controller.createType(sensorTypeList, typeStr, unit);
        int year = 2018;
        int month = 8;
        int day = 9;
        Date date1 = controller.createDate(year, month, day);
        controller.createRoomSensor(idString, nameString, firstType, date1, roomID);
        SensorType t1 = new SensorType(typeStr, "Celsius³");
        HouseSensor expectedResult = new HouseSensor("T289738", "XV-56D", t1, validDate1, "RoomDA");

        // Act

        HouseSensor actualResult = controller.createRoomSensor(idString, nameString, firstType, date1, roomID);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void addTypeSensorToList() {

        // Arrange

        SensorType sensorType1 = new SensorType("temperature", "celsius");
        SensorType sensorType2 = new SensorType("temperature", "kelvin");
        SensorType sensorType4 = new SensorType("humidity", "percentage");
        SensorTypeService typeList = new SensorTypeService(sensorTypeRepository);

        // Act

        boolean actualResult1 = controller.addTypeSensorToList(sensorType1, typeList);
        boolean actualResult2 = controller.addTypeSensorToList(sensorType2, typeList);
        boolean actualResult4 = controller.addTypeSensorToList(sensorType4, typeList);

        // Assert

        assertTrue(actualResult1);
        assertTrue(actualResult2);
        assertTrue(actualResult4);
    }

    @Test
    void testBuildSensorString() {
        // Arrange

        AreaSensor areaSensor = new AreaSensor("RF12345", "Sensor", new SensorType("temperature", "celsius"), new Local(1, 1, 1),
                validDate1, 6008L);
        String expectedResult = "Sensor, temperature, 1.0º lat, 1.0º long \n";


        // Act

        String actualResult = controller.buildSensorString(areaSensor);

        // Assert

        assertEquals(expectedResult, actualResult);
    }
}