package pt.ipp.isep.dei.project.model.geographicArea;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.areaType.AreaType;
import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.model.room.RoomService;

import static org.junit.jupiter.api.Assertions.*;

/**
 * GeographicArea tests class.
 */

class GeographicAreaTest {
    private GeographicArea validArea;

    @BeforeEach
    void arrangeArtifacts() {
        validArea = new GeographicArea("Portugal", new AreaType("Country"), 300, 200,
                new Local(50, 50, 10));
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
}


