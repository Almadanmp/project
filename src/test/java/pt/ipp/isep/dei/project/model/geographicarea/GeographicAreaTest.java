package pt.ipp.isep.dei.project.model.geographicarea;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.model.areatype.AreaType;
import pt.ipp.isep.dei.project.model.device.WaterHeater;
import pt.ipp.isep.dei.project.model.device.devicespecs.WaterHeaterSpec;
import pt.ipp.isep.dei.project.model.sensortype.SensorType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * GeographicArea tests class.
 */

class GeographicAreaTest {
    private GeographicArea validArea;
    private AreaSensor firstValidAreaSensor;
    private AreaSensor secondValidAreaSensor;
    private Date validDate1; // Date 21/11/2018 00h00m00s
    private Date validDate2; // Date 25/11/2018 00h00m00s
    private Date validDate3; //  Date 28/12/2018 12h30m00s
    private SensorType validSensorTypeTemperature;

    @BeforeEach
    void arrangeArtifacts() {
        validArea = new GeographicArea("Portugal", "Country", 300, 200,
                new Local(50, 50, 10));

        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            validDate1 = validSdf.parse("21/11/2018 00:00:00");
            validDate2 = validSdf.parse("25/11/2018 00:00:00");
            validDate3 = validSdf.parse("28/12/2018 12:30:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        validSensorTypeTemperature = new SensorType("Temperature", "Cº");
        firstValidAreaSensor = new AreaSensor("SensorOne", "SensorOne", validSensorTypeTemperature.getName(), new Local(2, 2, 2), validDate1);
        firstValidAreaSensor.setActive(true);
        secondValidAreaSensor = new AreaSensor("SensorTwo", "SensorTwo", validSensorTypeTemperature.getName(), new Local(10, 10, 10),
                validDate1);
        secondValidAreaSensor.setActive(true);
    }


    @Test
    void seeIfGetAreaSensorByIDWorks() {
        //Arrange

        validArea.addSensor(firstValidAreaSensor);
        validArea.addSensor(secondValidAreaSensor);

        //Act

        AreaSensor actualResult = validArea.getAreaSensorByID("SensorTwo");

        //Assert

        assertEquals(secondValidAreaSensor, actualResult);
    }

    @Test
    void seeIfGetAreaSensorByIDWorksWhenSensorDoesNotExist() {
        //Arrange

        validArea.addSensor(firstValidAreaSensor);

        // Assert

        assertThrows(IllegalArgumentException.class,
                () -> validArea.getAreaSensorByID("invalidSensorID"));
    }

    @Test
    void seeIfGetAreaSensorsWorks() {
        //Arrange

        List<AreaSensor> areaSensors = new ArrayList<>();
        areaSensors.add(firstValidAreaSensor);
        List<AreaSensor> expectedResult = new ArrayList<>();
        expectedResult.add(firstValidAreaSensor);

        validArea.setAreaSensors(areaSensors);

        //Act

        List<AreaSensor> actualResult = validArea.getAreaSensors();

        //Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetAreaSensorsWorksWhenEmpty() {
        //Arrange

        List<AreaSensor> areaSensors = new ArrayList<>();
        List<AreaSensor> expectedResult = new ArrayList<>();

        validArea.setAreaSensors(areaSensors);

        //Act

        List<AreaSensor> actualResult = validArea.getAreaSensors();

        //Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfRemoveSensor() {
        //Arrange

        validArea.addSensor(firstValidAreaSensor);

        //Act

        boolean actualResult1 = validArea.removeSensor(firstValidAreaSensor);

        //Assert

        assertTrue(actualResult1);
    }

    @Test
    void seeIfRemoveSensorIfSensorDontExist() {
        //Arrange

        validArea.addSensor(firstValidAreaSensor);

        //Act

        boolean actualResult1 = validArea.removeSensor(secondValidAreaSensor);

        //Assert

        assertFalse(actualResult1);
    }


    @Test
    void seeIfSetAreaSensors() {

        //Arrange

        AreaSensor validAreaSensor = new AreaSensor("SensOne", "SensOne", validSensorTypeTemperature.getName(), new Local(10, 10, 10), new Date());
        validAreaSensor.setActive(true);
        List<AreaSensor> listAreaSensor = new ArrayList<>();

        //Act

        validArea.setAreaSensors(listAreaSensor);

        //Assert

        assertEquals(listAreaSensor, validArea.getAreaSensors());
    }

    @Test
    void seeIfGetAreaSensor() {

        //Arrange

        AreaSensor validAreaSensor = new AreaSensor("SensOne", "SensOne", validSensorTypeTemperature.getName(), new Local(10, 10, 10), new Date());
        AreaSensor areaSensor = new AreaSensor("SensOne", "SensOne", validSensorTypeTemperature.getName(), new Local(10, 10, 10), new Date());

        validAreaSensor.setActive(true);
        List<AreaSensor> listAreaSensor = new ArrayList<>();

        //Act
        validArea.setAreaSensors(listAreaSensor);
        validArea.addSensor(validAreaSensor);
        validArea.addSensor(areaSensor);

        //Assert

        assertEquals(validAreaSensor, validArea.getSensor(0));
    }

    @Test
    void seeIfAddSensorFalse() {

        //Arrange

        AreaSensor validAreaSensor = new AreaSensor("SensOne", "SensOne", validSensorTypeTemperature.getName(), new Local(10, 10, 10), new Date());
        AreaSensor areaSensor = new AreaSensor("SensOne", "SensOne", validSensorTypeTemperature.getName(), new Local(10, 10, 10), new Date());

        validAreaSensor.setActive(true);
        List<AreaSensor> listAreaSensor = new ArrayList<>();

        //Act

        validArea.setAreaSensors(listAreaSensor);
        validArea.addSensor(validAreaSensor);

        //Assert

        assertFalse(validArea.addSensor(areaSensor));

    }

    @Test
    void seeIfAddSensorTrue() {

        //Arrange

        AreaSensor validAreaSensor = new AreaSensor("SensOne", "SensOne", validSensorTypeTemperature.getName(), new Local(10, 10, 10), new Date());
        AreaSensor areaSensor = new AreaSensor("SensOne", "SensOne", validSensorTypeTemperature.getName(), new Local(10, 10, 10), new Date());

        validAreaSensor.setActive(true);
        areaSensor.setActive(true);
        List<AreaSensor> listAreaSensor = new ArrayList<>();

        //Act

        validArea.setAreaSensors(listAreaSensor);


        //Assert

        assertTrue(validArea.addSensor(areaSensor));

    }

    @Test
    void seeIfAddSensorFalseNotSensor() {

        //Arrange

        AreaSensor validAreaSensor = new AreaSensor("SensOne", "SensOne", validSensorTypeTemperature.getName(), new Local(10, 10, 10), new Date());
        validAreaSensor.setActive(true);
        List<AreaSensor> listAreaSensor = new ArrayList<>();

        //Act

        validArea.setAreaSensors(listAreaSensor);


        //Assert

        assertTrue(validArea.addSensor(validAreaSensor));

    }

    @Test
    void seeIfGetTypeAreaWorks() {
        // Arrange

        String expectedResult = "Country";

        // Act

        String actualResult = validArea.getAreaTypeID();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfEqualsWorksSameObject() {
        // Act

        boolean actualResult = validArea.equals(validArea); // Needed for SonarQube coverage purposes.

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfEqualsWorks() {
        // Arrange

        GeographicArea testArea = new GeographicArea("Portugal", "Country", 300, 200,
                new Local(50, 50, 10));

        // Act

        boolean actualResult = validArea.equals(testArea);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfEqualsWorksFalseDiffLocalDiffTypeDiffLocal() {
        // Arrange

        GeographicArea testArea = new GeographicArea("Porto", "City", 300, 200,
                new Local(21, 31, 1));

        // Act

        boolean actualResult = validArea.equals(testArea);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksFalseSameLocalSameNameDiffType() {
        // Arrange

        GeographicArea testArea = new GeographicArea("Portugal", "City", 300, 200,
                new Local(50, 50, 1));

        // Act

        boolean actualResult = validArea.equals(testArea);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksFalseSameLocalDiffNameSameType() {
        // Arrange

        GeographicArea testArea = new GeographicArea("Porto", "Country", 300, 200,
                new Local(50, 50, 1));

        // Act

        boolean actualResult = validArea.equals(testArea);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksFalseSameLocalDiffNameDiffType() {
        // Arrange

        GeographicArea testArea = new GeographicArea("Porto", "City", 300, 200,
                new Local(50, 50, 1));

        // Act

        boolean actualResult = validArea.equals(testArea);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksFalseDiffLocalSameNameSameType() {
        // Arrange

        GeographicArea testArea = new GeographicArea("Portugal", "Country", 300, 200,
                new Local(50, 30, 1));

        // Act

        boolean actualResult = validArea.equals(testArea);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksFalseDiffLocalDiffNameSameType() {
        // Arrange

        GeographicArea testArea = new GeographicArea("Porto", "Country", 300, 200,
                new Local(50, 21, 1));

        // Act

        boolean actualResult = validArea.equals(testArea);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksFalseDiffLocalSameNameDiffType() {
        // Arrange

        GeographicArea testArea = new GeographicArea("Portugal", "City", 300, 200,
                new Local(21, 50, 1));

        // Act

        boolean actualResult = validArea.equals(testArea);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksDifferentObject() {
        // Arrange

        // Act

        boolean actualResult = validArea.equals(new WaterHeater(new WaterHeaterSpec())); // Necessary for Sonarqube coverage purposes.

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfGetSetMotherAreaWorks() {
        // Arrange

        GeographicArea testArea = new GeographicArea("Porto", "City", 2, 5,
                new Local(22, 23, 100));
        validArea.setMotherArea(testArea);

        // Act

        GeographicArea actualResult = validArea.getMotherArea();

        // Assert

        assertEquals(testArea, actualResult);
    }

    @Test
    void seeIfGetSetMotherAreaWorksFalse() {
        // Act

        boolean actualResult = validArea.setMotherArea(null);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfGetSetDescription() {
        // Arrange

        validArea.setDescription("Country of Portugal.");

        // Act

        String actualResult = validArea.getDescription();

        // Assert

        assertEquals("Country of Portugal.", actualResult);
    }

    @Test
    void seeIfGetSetAreaType() {
        // Arrange

        AreaType areaType = new AreaType("cidade");
        validArea.setAreaTypeID(areaType.getName());

        // Act

        String actualResult = validArea.getAreaTypeID();

        // Assert

        assertEquals(areaType.getName(), actualResult);
    }


    @Test
    void seeIfCheckIfAreaIsContainedWorksTrue() {
        // Arrange

        GeographicArea testArea = new GeographicArea("Porto", "City", 2, 5,
                new Local(22, 23, 100));
        validArea.setMotherArea(testArea);

        // Act

        boolean actualResult = validArea.isContainedInArea(testArea);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfCheckIfAreaIsContainedWorksFalse() {
        // Arrange

        GeographicArea testArea = new GeographicArea("Porto", "City", 2, 5,
                new Local(22, 23, 100));


        // Act

        boolean actualResult = validArea.isContainedInArea(testArea);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfCheckIfAreaIsContainedWorksTransitive() {
        // Arrange

        GeographicArea firstTestArea = new GeographicArea("Porto", "City",
                2, 4, new Local(22, 22, 100));
        GeographicArea secondTestArea = new GeographicArea("Europe", "Continent",
                200, 400, new Local(22, 22, 100));
        firstTestArea.setMotherArea(validArea);
        validArea.setMotherArea(secondTestArea);

        // Act

        boolean actualResult = firstTestArea.isContainedInArea(secondTestArea);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfToStringWorks() {
        // Arrange

        String expectedResult = "Portugal, Country, 50.0º lat, 50.0º long\n";

        // Act

        String actualResult = validArea.buildString();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void hashCodeDummyTest() {
        // Arrange

        int expectedResult = 1;

        // Act

        int actualResult = validArea.hashCode();

        // Assert

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetId() {
        // Arrange

        validArea.setName("Malta");

        // Act

        String id = validArea.getName();

        // Assert

        assertEquals("Malta", id);
    }

    @Test
    void seeIfGetLocation() {
        // Arrange

        Local local = new Local(51, 24, 36);
        validArea.setLocation(local);

        // Act

        Local actualLocal = validArea.getLocal();

        // Assert

        assertEquals(local, actualLocal);

    }


    @Test
    void seeIfGetLengthWidth() {
        // Arrange

        validArea.setWidth(5);
        validArea.setLength(10);

        // Act

        double actualWidth = validArea.getWidth();
        double actualLength = validArea.getLength();

        // Assert

        assertEquals(10, actualLength);
        assertEquals(5, actualWidth);
    }

    @Test
    void seeIfEqualsWorksNotAnInstance() {
        // Arrange
        Local local = new Local(21, 1, 12);

        // Act
        boolean actualResult = validArea.equals(local);

        // Assert

        assertFalse(actualResult);


    }

    @Test
    void seeIfEqualsParametersWorks() {
        // Act
        boolean actualResult1 = validArea.equalsParameters("Portugal", "Country", new Local(50, 50, 10));
        boolean actualResult2 = validArea.equalsParameters("Porto", "City", new Local(20, 20, 20));
        boolean actualResult3 = validArea.equalsParameters("Porto", "Country", new Local(50, 50, 10));
        boolean actualResult4 = validArea.equalsParameters("Portugal", "City", new Local(50, 50, 10));
        boolean actualResult5 = validArea.equalsParameters("Portugal", "Country", new Local(20, 50, 10));

        // Assert

        assertTrue(actualResult1);
        assertFalse(actualResult2);
        assertFalse(actualResult3);
        assertFalse(actualResult4);
        assertFalse(actualResult5);
    }

    @Test
    void seeIfGetIdWorks() {
        //Arrange
        GeographicArea geographicArea = new GeographicArea();
        geographicArea.setId(6008L);
        Long expectedResult = 6008L;
        //Actual
        Long actualResult = geographicArea.getId();
        //Assert
        assertEquals(expectedResult, actualResult);
    }


    @Test
    void seeIfGetLocationWorks() {
        //Arrange
        GeographicArea geographicArea = new GeographicArea();
        geographicArea.setLocation(new Local(2, 1, 4));
        Local expectedResult = new Local(2, 1, 4);
        //Act
        Local actualResult = geographicArea.getLocation();
        //Assert
        assertEquals(expectedResult, actualResult);
    }


    @Test
    void seeIfSensorToStringWorks() {
        // Arrange

        List<AreaSensor> areaSensors = new ArrayList<>();
        areaSensors.add(secondValidAreaSensor);
        areaSensors.add(firstValidAreaSensor);
        String expectedResult =
                "---------------\n" +
                        "SensorTwo) Name: SensorTwo | Type: Temperature | Active\n" +
                        "SensorOne) Name: SensorOne | Type: Temperature | Active\n" +
                        "---------------\n";

        // Act

        String actualResult = validArea.buildString(areaSensors);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSensorToStringWorksEmpty() {
        // Arrange
        List<AreaSensor> areaSensors = new ArrayList<>();
        String expectedResult = "Invalid List - List is Empty\n";

        // Act

        String actualResult = validArea.buildString(areaSensors);

        // Assert

        assertEquals(expectedResult, actualResult);
    }
}


