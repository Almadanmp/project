package pt.ipp.isep.dei.project.model.geographicarea;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.areatype.AreaType;
import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.model.areatype.AreaType;
import pt.ipp.isep.dei.project.model.device.devicetypes.DeviceType;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.room.RoomService;
import pt.ipp.isep.dei.project.model.sensortype.SensorType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * GeographicArea tests class.
 */

class GeographicAreaTest {
    private GeographicArea validArea;
    private AreaSensor firstValidAreaSensor;
    private AreaSensor secondValidAreaSensor;
    private Date validDate1; // Date 21/11/2018

    @BeforeEach
    void arrangeArtifacts() {
        validArea = new GeographicArea("Portugal", new AreaType("Country"), 300, 200,
                new Local(50, 50, 10));

        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            validDate1 = validSdf.parse("21/11/2018 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        firstValidAreaSensor = new AreaSensor("SensorOne", "SensorOne", new SensorType("Temperature", "Celsius"), new Local(2, 2, 2), validDate1, 6008L);
        firstValidAreaSensor.setActive(true);
        secondValidAreaSensor = new AreaSensor("SensorTwo", "SensorTwo", new SensorType("Temperature", "Celsius"), new Local(10, 10, 10),
                validDate1, 6008L);
        secondValidAreaSensor.setActive(true);
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

        AreaSensor validAreaSensor = new AreaSensor("SensOne", "SensOne", new SensorType("Temperature", "Celsius"), new Local(10, 10, 10), new Date(), 6008L);
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

        AreaSensor validAreaSensor = new AreaSensor("SensOne", "SensOne", new SensorType("Temperature", "Celsius"), new Local(10, 10, 10), new Date(), 6008L);
        AreaSensor areaSensor = new AreaSensor("SensOne", "SensOne", new SensorType("Temperature", "Celsius"), new Local(10, 10, 10), new Date(), 6008L);

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

        AreaSensor validAreaSensor = new AreaSensor("SensOne", "SensOne", new SensorType("Temperature", "Celsius"), new Local(10, 10, 10), new Date(), 6008L);
        AreaSensor areaSensor = new AreaSensor("SensOne", "SensOne", new SensorType("Temperature", "Celsius"), new Local(10, 10, 10), new Date(), 6008L);

        validAreaSensor.setActive(true);
        List<AreaSensor> listAreaSensor = new ArrayList<>();

        //Act

        validArea.setAreaSensors(listAreaSensor);
        validArea.addSensor(validAreaSensor);

        //Assert

        assertFalse(validArea.addSensor(areaSensor));

    }

    @Test
    void seeIfGetTypeAreaWorks() {
        // Arrange

        AreaType expectedResult = new AreaType("Country");

        // Act

        AreaType actualResult = validArea.getAreaType();

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

        GeographicArea testArea = new GeographicArea("Portugal", new AreaType("Country"), 300, 200,
                new Local(50, 50, 10));

        // Act

        boolean actualResult = validArea.equals(testArea);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfEqualsWorksFalseDiffLocalDiffTypeDiffLocal() {
        // Arrange

        GeographicArea testArea = new GeographicArea("Porto", new AreaType("City"), 300, 200,
                new Local(21, 31, 1));

        // Act

        boolean actualResult = validArea.equals(testArea);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksFalseSameLocalSameNameDiffType() {
        // Arrange

        GeographicArea testArea = new GeographicArea("Portugal", new AreaType("City"), 300, 200,
                new Local(50, 50, 1));

        // Act

        boolean actualResult = validArea.equals(testArea);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksFalseSameLocalDiffNameSameType() {
        // Arrange

        GeographicArea testArea = new GeographicArea("Porto", new AreaType("Country"), 300, 200,
                new Local(50, 50, 1));

        // Act

        boolean actualResult = validArea.equals(testArea);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksFalseSameLocalDiffNameDiffType() {
        // Arrange

        GeographicArea testArea = new GeographicArea("Porto", new AreaType("City"), 300, 200,
                new Local(50, 50, 1));

        // Act

        boolean actualResult = validArea.equals(testArea);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksFalseDiffLocalSameNameSameType() {
        // Arrange

        GeographicArea testArea = new GeographicArea("Portugal", new AreaType("Country"), 300, 200,
                new Local(50, 30, 1));

        // Act

        boolean actualResult = validArea.equals(testArea);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksFalseDiffLocalDiffNameSameType() {
        // Arrange

        GeographicArea testArea = new GeographicArea("Porto", new AreaType("Country"), 300, 200,
                new Local(50, 21, 1));

        // Act

        boolean actualResult = validArea.equals(testArea);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksFalseDiffLocalSameNameDiffType() {
        // Arrange

        GeographicArea testArea = new GeographicArea("Portugal", new AreaType("City"), 300, 200,
                new Local(21, 50, 1));

        // Act

        boolean actualResult = validArea.equals(testArea);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksDifferentObject() {
        // Arrange

        RoomService testList = new RoomService();

        // Act

        boolean actualResult = validArea.equals(testList); // Necessary for Sonarqube coverage purposes.

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfGetSetMotherAreaWorks() {
        // Arrange

        GeographicArea testArea = new GeographicArea("Porto", new AreaType("City"), 2, 5,
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
    void seeIfCheckIfAreaIsContainedWorksTrue() {
        // Arrange

        GeographicArea testArea = new GeographicArea("Porto", new AreaType("City"), 2, 5,
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

        GeographicArea testArea = new GeographicArea("Porto", new AreaType("City"), 2, 5,
                new Local(22, 23, 100));


        // Act

        boolean actualResult = validArea.isContainedInArea(testArea);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfCheckIfAreaIsContainedWorksTransitive() {
        // Arrange

        GeographicArea firstTestArea = new GeographicArea("Porto", new AreaType("City"),
                2, 4, new Local(22, 22, 100));
        GeographicArea secondTestArea = new GeographicArea("Europe", new AreaType("Continent"),
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
        boolean actualResult1 = validArea.equalsParameters("Portugal", new AreaType("Country"), new Local(50, 50, 10));
        boolean actualResult2 = validArea.equalsParameters("Porto", new AreaType("City"), new Local(20, 20, 20));
        boolean actualResult3 = validArea.equalsParameters("Porto", new AreaType("Country"), new Local(50, 50, 10));
        boolean actualResult4 = validArea.equalsParameters("Portugal", new AreaType("City"), new Local(50, 50, 10));
        boolean actualResult5 = validArea.equalsParameters("Portugal", new AreaType("Country"), new Local(20, 50, 10));

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

//    @Test
//    void seeIfGetAreaSensorsByDistanceToHouse(){
//
//        //Arrange
//        List<String> deviceTypeString = new ArrayList<>();
//        deviceTypeString.add("pt.ipp.isep.dei.project.model.device.devicetypes.FridgeType");
//        House house = new House("12", new Local(2, 2, 2), 2, 2, deviceTypeString);
//        AreaSensor validAreaSensor = new AreaSensor("SensOne", "SensOne", new SensorType("Temperature", "Celsius"), new Local(2, 2, 2), new Date(), 6008L);
//        validAreaSensor.setActive(true);
//        List<AreaSensor> listAreaSensor = new ArrayList<>();
//        listAreaSensor.add(validAreaSensor);
//
//        //Act
//        validArea.setAreaSensors(listAreaSensor);
//        List<AreaSensor> actualResult = validArea.getAreaSensorsByDistanceToHouse(listAreaSensor,house,1000.2);
//
//        //Assert
//        assertEquals(listAreaSensor, actualResult);
//    }

    @Test
    void seeIfGetClosestSensorOfGivenType(){

        //Arrange
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add("pt.ipp.isep.dei.project.model.device.devicetypes.FridgeType");
        House house = new House("12", new Local(2, 2, 2), 2, 2, deviceTypeString);
        AreaSensor validAreaSensor = new AreaSensor("SensOne", "SensOne", new SensorType("Temperature", "Celsius"), new Local(2, 2, 2), new Date(), 6008L);
        validAreaSensor.setActive(true);
        List<AreaSensor> listAreaSensor = new ArrayList<>();
        listAreaSensor.add(validAreaSensor);

        //Act
        validArea.setAreaSensors(listAreaSensor);
        AreaSensor actualResult = validArea.getClosestAreaSensorOfGivenType("Temperature",house);

        //Assert
        assertEquals(validAreaSensor, actualResult);
    }

    @Test
    void seeIfGetClosestSensorOfNoExistType(){

        //Arrange
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add("pt.ipp.isep.dei.project.model.device.devicetypes.FridgeType");
        House house = new House("12", new Local(2, 2, 2), 2, 2, deviceTypeString);
        AreaSensor areaSensorError = new AreaSensor("RF12345", "EmptyList", new SensorType("temperature", " " +
                ""), new Local(0, 0, 0), new GregorianCalendar(1900, Calendar.FEBRUARY,
                1).getTime(), 2356L);

        //Act

        AreaSensor actualResult = validArea.getClosestAreaSensorOfGivenType("Humidity",house);

        //Assert
        assertEquals(areaSensorError, actualResult);
    }

    @Test
    void seeIfGetClosestSensorOfGivenTypeSize(){

        //Arrange
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add("pt.ipp.isep.dei.project.model.device.devicetypes.FridgeType");
        House house = new House("12", new Local(2, 2, 2), 2, 2, deviceTypeString);
        AreaSensor validAreaSensor = new AreaSensor("SensOne", "SensOne", new SensorType("Temperature", "Celsius"), new Local(2000, 2000, 2000), new Date(), 6008L);
        validAreaSensor.setActive(true);
        List<AreaSensor> listAreaSensor = new ArrayList<>();
        listAreaSensor.add(validAreaSensor);

        //Act
        validArea.setAreaSensors(listAreaSensor);
        AreaSensor actualResult = validArea.getClosestAreaSensorOfGivenType("Temperature",house);

        //Assert
        assertEquals(validAreaSensor, actualResult);
    }

    @Test
    void seeIfGetMostRecentlyUsedAreaSensorNoReadings (){
        //Arrange
        AreaSensor validAreaSensor = new AreaSensor("SensOne", "SensOne", new SensorType("Temperature", "Celsius"), new Local(2000, 2000, 2000), new Date(), 6008L);
        validAreaSensor.setActive(true);
        List<AreaSensor> listAreaSensor = new ArrayList<>();
        listAreaSensor.add(validAreaSensor);

        //Act
        validArea.setAreaSensors(listAreaSensor);
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> validArea.getMostRecentlyUsedAreaSensor(listAreaSensor));


        //Assert
        assertEquals("The sensor list has no readings available.", exception.getMessage());
    }

    @Test
    void seeIfGetMostRecentlyUsedAreaSensorNoSensors (){
        //Arrange

        List<AreaSensor> listAreaSensor = new ArrayList<>();

        //Act
        validArea.setAreaSensors(listAreaSensor);
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> validArea.getMostRecentlyUsedAreaSensor(listAreaSensor));

        //Assert
        assertEquals("The sensor list is empty.", exception.getMessage());
    }

    @Test
    void seeIfGetMostRecentlyUsedAreaSensor (){
        //Arrange
        Date date = new GregorianCalendar(2018, Calendar.FEBRUARY, 13).getTime();
        Reading firstValidReading = new Reading(31, date, "C", "Test");
        AreaSensor validAreaSensor = new AreaSensor("SensOne", "SensOne", new SensorType("Temperature", "Celsius"), new Local(2000, 2000, 2000), new Date(), 6008L);
        validAreaSensor.setActive(true);
        List<AreaSensor> listAreaSensor = new ArrayList<>();
        List<Reading> readingList = new ArrayList<>();


        //Act
        readingList.add(firstValidReading);
        listAreaSensor.add(validAreaSensor);
        validAreaSensor.setAreaReadings(readingList);
        validArea.setAreaSensors(listAreaSensor);
        AreaSensor actualResult = validArea.getMostRecentlyUsedAreaSensor(listAreaSensor);

        //Assert
        assertEquals(validAreaSensor, actualResult);
    }

}


