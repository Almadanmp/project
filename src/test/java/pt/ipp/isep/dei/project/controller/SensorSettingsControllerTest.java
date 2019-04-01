package pt.ipp.isep.dei.project.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.model.sensor.Sensor;
import pt.ipp.isep.dei.project.model.sensor.SensorList;
import pt.ipp.isep.dei.project.model.sensor.TypeSensor;
import pt.ipp.isep.dei.project.model.sensor.TypeSensorList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * SensorSettingsController tests class.
 */

class SensorSettingsControllerTest {

    // Common testing artifacts for class.

    private SensorSettingsController controller = new SensorSettingsController();
    private Date validDate1;

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
        TypeSensorList typeSList = new TypeSensorList();
        TypeSensor typeA = new TypeSensor("Temperature", "Celsius");
        typeSList.add(typeA);
        String expectedResult = "---------------\n" +
                "0) Name: Temperature | Unit: Celsius\n" +
                "---------------\n";
        // Act
        String actualResult = controller.buildSensorTypesString(typeSList);
        // Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfAddTypeSensorToList() {
        // Arrange
        TypeSensorList tySList = new TypeSensorList();
        TypeSensor tS = new TypeSensor();
        TypeSensor expectedResult = tS;
        tySList.add(tS);

        // Act

        TypeSensor actualResult = tySList.get(0);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

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
        TypeSensorList typeSensorList = new TypeSensorList();

        //Act
        String actualResult = controller.createType(typeSensorList, typeString, units).getName();

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
        TypeSensorList typeSensorList = new TypeSensorList();
        String typeStr = "Humidity";
        String unit = "kg/m³";
        TypeSensor type1 = controller.createType(typeSensorList, typeStr, unit);
        int year = 2018;
        int month = 8;
        int day = 9;
        Date date1 = controller.createDate(year, month, day);
        controller.createSensor(idString, nameString, type1, loc1, date1);
        TypeSensor t1 = new TypeSensor(typeStr, "kg/m³");
        Sensor expectedResult = new Sensor("RF12345", "XV-56D", t1, loc1,
                validDate1);

        //Act
        Sensor actualResult = controller.createSensor(idString, nameString, type1, loc1, date1);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfAddSensorToSensorListTrue() {
        // Arrange

        GeographicArea geoArea = new GeographicArea("Portugal",
                new TypeArea("Country"), 300, 200,
                new Local(30, 30, 50));
        Sensor firstSensor = new Sensor("RF12345", "SensorOne", new TypeSensor
                ("Temperature", "Celsius"),
                new Local(1, 1, 1),
                validDate1);
        Sensor secondSensor = new Sensor("RF12777", "SensorTwo", new TypeSensor("Temperature", "Celsius"),
                new Local(1, 1, 1),
                validDate1);
        SensorList sensorList = new SensorList();
        sensorList.add(firstSensor);
        geoArea.setSensorList(sensorList);

        // Act

        boolean actualResult = geoArea.addSensor(secondSensor);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfTypeListIsPrinted() {

        // Arrange

        TypeSensorList list1 = new TypeSensorList();
        TypeSensor t1 = new TypeSensor("rain", "mm");
        TypeSensor t2 = new TypeSensor("wind", "km/h");
        list1.add(t1);
        list1.add(t2);

        // Act

        String result = "---------------\n" +
                "0) Name: rain | Unit: mm\n" +
                "1) Name: wind | Unit: km/h\n" +
                "---------------\n";
        String actualResult = controller.buildSensorTypesString(list1);

        // Assert

        assertEquals(result, actualResult);
    }

    @Test
    void ensureThatWeCreateARoomSensor() {

        // Arrange

        String nameString = "XV-56D";
        String typeStr = "Temperatura";
        String unit = "Celsius";
        TypeSensorList typeSensorList = new TypeSensorList();
        TypeSensor firstType = controller.createType(typeSensorList, typeStr, unit);
        int year = 2018;
        int month = 8;
        int day = 9;
        Date date1 = controller.createDate(year, month, day);
        controller.createRoomSensor(nameString, firstType, date1);
        TypeSensor t1 = new TypeSensor(typeStr, "Celsius³");
        Sensor expectedResult = new Sensor("XV-56D", t1, validDate1);

        // Act

        Sensor actualResult = controller.createRoomSensor(nameString, firstType, date1);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void addTypeSensorToList() {

        // Arrange

        TypeSensor typeSensor1 = new TypeSensor("temperature", "celsius");
        TypeSensor typeSensor2 = new TypeSensor("temperature", "kelvin");
        TypeSensor typeSensor3 = new TypeSensor("temperature", "celsius");
        TypeSensor typeSensor4 = new TypeSensor("humidity", "percentage");
        TypeSensorList typeList = new TypeSensorList();

        // Act
        boolean actualResult1 = controller.addTypeSensorToList(typeSensor1, typeList);
        boolean actualResult2 = controller.addTypeSensorToList(typeSensor2, typeList);
        boolean actualResult3 = controller.addTypeSensorToList(typeSensor3, typeList);
        boolean actualResult4 = controller.addTypeSensorToList(typeSensor4, typeList);

        // Assert
        assertTrue(actualResult1);
        assertTrue(actualResult2);
        assertFalse(actualResult3);
        assertTrue(actualResult4);
    }

    @Test
    void addSensorToGeographicArea() {

        // Arrange

        GeographicArea ga1 = new GeographicArea("Porto", new TypeArea("City"), 2, 3, new Local(4, 4, 100));
        Sensor sensor1 = new Sensor("RF12345", "sensor1", new TypeSensor("temperature", "celsius"), new Local(1, 1, 1),
                validDate1);
        Sensor sensor2 = new Sensor("RF12345", "sensor1", new TypeSensor("temperature", "celsius"), new Local(1, 1, 1),
                validDate1);
        Sensor sensor3 = new Sensor("RF12345", "sensor3", new TypeSensor("temperature", "celsius"), new Local(1, 1, 1),
                validDate1);

        // Act
        boolean actualResult1 = controller.addSensorToGeographicArea(sensor1, ga1);
        boolean actualResult2 = controller.addSensorToGeographicArea(sensor2, ga1);
        boolean actualResult3 = controller.addSensorToGeographicArea(sensor3, ga1);

        // Assert
        assertTrue(actualResult1);
        assertFalse(actualResult2);
        assertTrue(actualResult3);
    }

    @Test
    void testBuildSensorString() {
        // Arrange

        Sensor sensor = new Sensor("RF12345", "Sensor", new TypeSensor("temperature", "celsius"), new Local(1, 1, 1),
                validDate1);
        String expectedResult = "Sensor, temperature, 1.0º lat, 1.0º long \n";


        // Act

        String actualResult = controller.buildSensorString(sensor);

        // Assert

        assertEquals(expectedResult, actualResult);
    }
}