package pt.ipp.isep.dei.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.sensor.Sensor;
import pt.ipp.isep.dei.project.model.sensor.SensorList;
import pt.ipp.isep.dei.project.model.sensor.TypeSensor;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * GeographicArea tests class.
 */

class GeographicAreaTest {
    private GeographicArea validArea;

    @BeforeEach
    void arrangeArtifacts() {
        validArea = new GeographicArea("Portugal", new TypeArea("Country"), 300, 200,
                new Local(50, 50, 10));
    }

    @Test
    void seeIfGetTypeAreaWorks() {
        // Arrange

        TypeArea expectedResult = new TypeArea("Country");

        // Act

        TypeArea actualResult = validArea.getTypeArea();

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

        GeographicArea testArea = new GeographicArea("Portugal", new TypeArea("Country"), 300, 200,
                new Local(50, 50, 10));

        // Act

        boolean actualResult = validArea.equals(testArea);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfEqualsWorksFalseDiffLocalDiffTypeDiffLocal() {
        // Arrange

        GeographicArea testArea = new GeographicArea("Porto", new TypeArea("City"), 300, 200,
                new Local(21, 31, 1));

        // Act

        boolean actualResult = validArea.equals(testArea);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksFalseSameLocalSameNameDiffType(){
        // Arrange

        GeographicArea testArea = new GeographicArea("Portugal", new TypeArea("City"), 300, 200,
                new Local(50, 50, 1));

        // Act

        boolean actualResult = validArea.equals(testArea);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksFalseSameLocalDiffNameSameType(){
        // Arrange

        GeographicArea testArea = new GeographicArea("Porto", new TypeArea("Country"), 300, 200,
                new Local(50, 50, 1));

        // Act

        boolean actualResult = validArea.equals(testArea);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksFalseSameLocalDiffNameDiffType(){
        // Arrange

        GeographicArea testArea = new GeographicArea("Porto", new TypeArea("City"), 300, 200,
                new Local(50, 50, 1));

        // Act

        boolean actualResult = validArea.equals(testArea);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksFalseDiffLocalSameNameSameType(){
        // Arrange

        GeographicArea testArea = new GeographicArea("Portugal", new TypeArea("Country"), 300, 200,
                new Local(50, 30, 1));

        // Act

        boolean actualResult = validArea.equals(testArea);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksFalseDiffLocalDiffNameSameType(){
        // Arrange

        GeographicArea testArea = new GeographicArea("Porto", new TypeArea("Country"), 300, 200,
                new Local(50, 21, 1));

        // Act

        boolean actualResult = validArea.equals(testArea);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksFalseDiffLocalSameNameDiffType(){
        // Arrange

        GeographicArea testArea = new GeographicArea("Portugal", new TypeArea("City"), 300, 200,
                new Local(21, 50, 1));

        // Act

        boolean actualResult = validArea.equals(testArea);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksDifferentObject() {
        // Arrange

        RoomList testList = new RoomList();

        // Act

        boolean actualResult = validArea.equals(testList); // Necessary for Sonarqube coverage purposes.

        // Assert

        assertFalse(actualResult);
    }


    @Test
    void seeIfGetSetSensorListWork() {
        // Arrange

        Sensor testSensor = new Sensor("RF12345", "Vento", new TypeSensor("Atmosférico", "km/h"),
                new Local(12, 31, 21), new Date());
        validArea.addSensor(testSensor);
        SensorList expectedResult = new SensorList();
        expectedResult.add(testSensor);

        // Act

        SensorList actualResult = validArea.getSensorList();

        // Assert

        assertEquals(expectedResult, actualResult);
    }


    @Test
    void seeIfGetSetMotherAreaWorks() {
        // Arrange

        GeographicArea testArea = new GeographicArea("Porto", new TypeArea("City"), 2, 5,
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

        GeographicArea testArea = new GeographicArea("Porto", new TypeArea("City"), 2, 5,
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

        GeographicArea testArea = new GeographicArea("Porto", new TypeArea("City"), 2, 5,
                new Local(22, 23, 100));


        // Act

        boolean actualResult = validArea.isContainedInArea(testArea);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfCheckIfAreaIsContainedWorksTransitive() {
        // Arrange

        GeographicArea firstTestArea = new GeographicArea("Porto", new TypeArea("City"),
                2, 4, new Local(22, 22, 100));
        GeographicArea secondTestArea = new GeographicArea("Europe", new TypeArea("Continent"),
                200, 400, new Local(22, 22, 100));
        firstTestArea.setMotherArea(validArea);
        validArea.setMotherArea(secondTestArea);

        // Act

        boolean actualResult = firstTestArea.isContainedInArea(secondTestArea);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeAddSensorToGA() {
        // Arrange

        Sensor firstTestSensor = new Sensor("Sensor 1", new TypeSensor("Temperature", "Celsius"), new Date());
        Sensor secondTestSensor = new Sensor("Sensor 1", new TypeSensor("Temperature", "Celsius"), new Date());
        Sensor thirdTestSensor = new Sensor("Sensor 3", new TypeSensor("Temperature", "Celsius"), new Date());

        // Act

        boolean result1 = validArea.addSensor(firstTestSensor);
        boolean result2 = validArea.addSensor(secondTestSensor);
        boolean result3 = validArea.addSensor(thirdTestSensor);

        // Assert

        assertTrue(result1);
        assertFalse(result2);
        assertTrue(result3);
    }

    @Test
    void seeIfIsSensorListEmptyWorks() {
        // Act With No Sensors

        boolean actualResult1 = validArea.isSensorListEmpty();

        // Assert With No Sensors

        assertTrue(actualResult1);

        // Arrange

        Sensor sensor = new Sensor("Sensor 1", new TypeSensor("Temperature", "Celsius"), new Date());
        validArea.addSensor(sensor);

        // Act

        boolean actualResult2 = validArea.isSensorListEmpty();

        // Assert

        assertFalse(actualResult2);
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
    void seeIfGetTypeArea() {
        // Arrange

        TypeArea type = new TypeArea("Island");
        validArea.setTypeArea(type);

        // Act

        TypeArea actualType = validArea.getTypeArea();

        // Assert

        assertEquals(type, actualType);
    }

    @Test
    void seeIfEqualsTypeAreaWorks() {
        // Arrange

        TypeArea typeArea1 = new TypeArea("City");
        validArea.setTypeArea(typeArea1);

        // Act

        boolean actualResult = validArea.equalsTypeArea(typeArea1);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfEqualsTypeAreaWorksFalse() {
        // Arrange

        TypeArea typeArea1 = new TypeArea("City");
        validArea.setTypeArea(typeArea1);
        TypeArea typeArea2 = new TypeArea("Street");

        // Act

        boolean actualResult = validArea.equalsTypeArea(typeArea2);

        // Assert

        assertFalse(actualResult);

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
    void seeIfRemovesSensorWorks() {
        // Arrange

        Sensor sensor = new Sensor();
        validArea.addSensor(sensor);

        // Act

        boolean actualResult = validArea.removeSensor(sensor);

        // Assert

        assertTrue(actualResult);

    }

    @Test
    void seeIfRemovesSensorWorksFalse() {
        // Arrange

        Sensor sensor = new Sensor();

        // Act

        boolean actualResult = validArea.removeSensor(sensor);

        // Assert

        assertFalse(actualResult);

    }

    @Test
    void seeIfGetSensorList() {
        // Arrange

        SensorList sensorList = new SensorList();
        validArea.setSensorList(sensorList);

        // Act

        SensorList actualSensorList = validArea.getSensorList();

        // Assert

        assertEquals(sensorList, actualSensorList);
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
    void seeIfEqualsWorksNotAnInstance (){
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
        boolean actualResult1 = validArea.equalsParameters("Portugal", new TypeArea("Country"), new Local(50, 50, 10));
        boolean actualResult2 = validArea.equalsParameters("Porto", new TypeArea("City"), new Local(20, 20, 20));
        boolean actualResult3 = validArea.equalsParameters("Porto", new TypeArea("Country"), new Local(50, 50, 10));
        boolean actualResult4 = validArea.equalsParameters("Portugal", new TypeArea("City"), new Local(50, 50, 10));
        boolean actualResult5 = validArea.equalsParameters("Portugal", new TypeArea("Country"), new Local(20, 50, 10));

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
        geographicArea.setId(95L);
        Long expectedResult = 95L;
        //Actual
        Long actualResult = geographicArea.getId();
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetSensorsOfGivenTypeWorks() {
        //Arrange
        GeographicArea geographicArea = new GeographicArea();
        SensorList sensorList = new SensorList();
        Sensor sensor1 = new Sensor("Sensor 1", new TypeSensor("temperature", "C"), new GregorianCalendar(2018, Calendar.JANUARY, 1).getTime());
        Sensor sensor2 = new Sensor("Sensor 2", new TypeSensor("rainfall", "mm"), new GregorianCalendar(2018, Calendar.JANUARY, 2).getTime());
        Sensor sensor3 = new Sensor("Sensor 3", new TypeSensor("temperature", "C"), new GregorianCalendar(2018, Calendar.JANUARY, 3).getTime());
        sensorList.add(sensor1);
        sensorList.add(sensor2);
        sensorList.add(sensor3);
        geographicArea.setSensorList(sensorList);
        SensorList expectedResult = new SensorList();
        expectedResult.add(sensor1);
        expectedResult.add(sensor3);
        //Act
        SensorList actualResult = geographicArea.getSensorsOfGivenType("temperature");
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
    void seeIfToStringOverrideWorks() {
        // Arrange

        GeographicArea motherArea = new GeographicArea();
        GeographicArea geographicArea = new GeographicArea();
        geographicArea.setTypeArea(new TypeArea("city"));
        geographicArea.setLength(2);
        geographicArea.setWidth(3);
        geographicArea.setMotherArea(motherArea);
        geographicArea.setLocation(new Local(2, 3, 5));
        geographicArea.setDescription("city of porto");
        String expectedResult = "GeographicArea[id=null, typeArea='pt.ipp.isep.dei.project.model.TypeArea@1', length='2.0, width='3.0', motherArea='GeographicArea[id=null, typeArea='null', length='0.0, width='0.0', motherArea='null, location='null', description='null'], location='Local[id=0, latitude='2.0', longitude='3.0', altitude='5.0']', description='city of porto']";

        // Act

        String actualResult = geographicArea.toString();

        // Assert

        assertEquals(expectedResult, actualResult);
    }
}


