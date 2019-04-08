package pt.ipp.isep.dei.project.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project.model.AreaType;
import pt.ipp.isep.dei.project.model.GeographicArea;
import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.model.sensor.AreaSensor;
import pt.ipp.isep.dei.project.model.sensor.AreaSensorList;
import pt.ipp.isep.dei.project.model.sensor.SensorType;
import pt.ipp.isep.dei.project.repository.AreaSensorRepository;
import pt.ipp.isep.dei.project.repository.SensorTypeRepository;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.model.sensor.*;

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

    private SensorSettingsController controller = new SensorSettingsController();
    private Date validDate1;


    @Mock
    private SensorTypeRepository sensorTypeRepository;

    @Mock
    private AreaSensorRepository areaSensorRepository;

    @BeforeEach
    void arrangeArtifacts() {
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
        SensorTypeService typeSList = new SensorTypeService(sensorTypeRepository);
        SensorType typeA = new SensorType("Temperature", "Celsius");
        typeSList.add(typeA);
        String expectedResult = "---------------\n" +
                "0) Name: Temperature | Unit: Celsius\n" +
                "---------------\n";
        // Act
        String actualResult = controller.buildSensorTypesString(typeSList);
        // Assert
        assertEquals(expectedResult, actualResult);
    }

//    @Test
//    void seeIfAddTypeSensorToList() {
//        // Arrange
//        SensorTypeService tySList = new SensorTypeService(sensorTypeRepository);
//        SensorType tS = new SensorType();
//        SensorType expectedResult = tS;
//        tySList.add(tS);
//
//        // Act
//
//        SensorType actualResult = tySList.get(0);
//
//        // Assert
//
//        assertEquals(expectedResult, actualResult);
//    }

    //USER STORY 006 TESTS

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

        //Arrange

        String typeString = "Humidade";
        String units = "kg/m³";
        String expectedResult = "Humidade";
        SensorTypeService sensorTypeList = new SensorTypeService(sensorTypeRepository);

        //Act
        String actualResult = controller.createType(sensorTypeList, typeString, units).getName();

        //Assert
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
        controller.createSensor(idString, nameString, type1, loc1, date1);
        SensorType t1 = new SensorType(typeStr, "kg/m³");
        AreaSensor expectedResult = new AreaSensor("RF12345", "XV-56D", t1, loc1,
                validDate1);

        //Act
        AreaSensor actualResult = controller.createSensor(idString, nameString, type1, loc1, date1);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfAddSensorToSensorListTrue() {
        // Arrange

        GeographicArea geoArea = new GeographicArea("Portugal",
                new AreaType("Country"), 300, 200,
                new Local(30, 30, 50));
        AreaSensor firstAreaSensor = new AreaSensor("RF12345", "SensorOne", new SensorType
                ("Temperature", "Celsius"),
                new Local(1, 1, 1),
                validDate1);
        AreaSensor secondAreaSensor = new AreaSensor("RF12777", "SensorTwo", new SensorType("Temperature", "Celsius"),
                new Local(1, 1, 1),
                validDate1);
        AreaSensorList areaSensorList = new AreaSensorList(areaSensorRepository);
        areaSensorList.add(firstAreaSensor);
        geoArea.setSensorList(areaSensorList);

        // Act

        boolean actualResult = geoArea.addSensor(secondAreaSensor);

        // Assert

        assertTrue(actualResult);
    }

//    @Test
//    void seeIfTypeListIsPrinted() {
//
//        // Arrange
//
//        SensorTypeList list1 = new SensorTypeList(sensorTypeRepository);
//        SensorType t1 = new SensorType("rain", "mm");
//        SensorType t2 = new SensorType("wind", "km/h");
//        list1.add(t1);
//        list1.add(t2);
//
//        // Act
//
//        String result = "---------------\n" +
//                "0) Name: rain | Unit: mm\n" +
//                "1) Name: wind | Unit: km/h\n" +
//                "---------------\n";
//        String actualResult = controller.buildSensorTypesString(list1);
//
//        // Assert
//
//        assertEquals(result, actualResult);
//    }

    @Test
    void ensureThatWeCreateARoomSensor() {

        // Arrange

        String nameString = "XV-56D";
        String typeStr = "Temperatura";
        String unit = "Celsius";
        SensorTypeService sensorTypeList = new SensorTypeService(sensorTypeRepository);
        SensorType firstType = controller.createType(sensorTypeList, typeStr, unit);
        int year = 2018;
        int month = 8;
        int day = 9;
        Date date1 = controller.createDate(year, month, day);
        controller.createRoomSensor(nameString, firstType, date1);
        SensorType t1 = new SensorType(typeStr, "Celsius³");
        HouseSensor expectedResult = new HouseSensor("XV-56D", t1, validDate1);

        // Act

        HouseSensor actualResult = controller.createRoomSensor(nameString, firstType, date1);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void addTypeSensorToList() {

        // Arrange

        SensorType sensorType1 = new SensorType("temperature", "celsius");
        SensorType sensorType2 = new SensorType("temperature", "kelvin");
        SensorType sensorType3 = new SensorType("temperature", "celsius");
        SensorType sensorType4 = new SensorType("humidity", "percentage");
        SensorTypeService typeList = new SensorTypeService(sensorTypeRepository);

        // Act
        boolean actualResult1 = controller.addTypeSensorToList(sensorType1, typeList);
        boolean actualResult2 = controller.addTypeSensorToList(sensorType2, typeList);
        boolean actualResult3 = controller.addTypeSensorToList(sensorType3, typeList);
        boolean actualResult4 = controller.addTypeSensorToList(sensorType4, typeList);

        // Assert
        assertTrue(actualResult1);
        assertTrue(actualResult2);
        assertFalse(actualResult3);
        assertTrue(actualResult4);
    }
//
//    @Test
//    void addSensorToGeographicArea() {
//
//        // Arrange
//
//        GeographicArea ga1 = new GeographicArea("Porto", new AreaType("City"), 2, 3, new Local(4, 4, 100));
//        AreaSensor areaSensor1 = new AreaSensor("RF12345", "sensor1", new SensorType("temperature", "celsius"), new Local(1, 1, 1),
//                validDate1);
//        AreaSensor areaSensor2 = new AreaSensor("RF12345", "sensor1", new SensorType("temperature", "celsius"), new Local(1, 1, 1),
//                validDate1);
//        AreaSensor areaSensor3 = new AreaSensor("RF12345", "sensor3", new SensorType("temperature", "celsius"), new Local(1, 1, 1),
//                validDate1);
//
//        // Act
//        boolean actualResult1 = controller.addSensorToGeographicArea(areaSensor1, ga1);
//        boolean actualResult2 = controller.addSensorToGeographicArea(areaSensor2, ga1);
//        boolean actualResult3 = controller.addSensorToGeographicArea(areaSensor3, ga1);
//
//        // Assert
//        assertTrue(actualResult1);
//        assertFalse(actualResult2);
//        assertTrue(actualResult3);
//    }

    @Test
    void testBuildSensorString() {
        // Arrange

        AreaSensor areaSensor = new AreaSensor("RF12345", "Sensor", new SensorType("temperature", "celsius"), new Local(1, 1, 1),
                validDate1);
        String expectedResult = "Sensor, temperature, 1.0º lat, 1.0º long \n";


        // Act

        String actualResult = controller.buildSensorString(areaSensor);

        // Assert

        assertEquals(expectedResult, actualResult);
    }
}