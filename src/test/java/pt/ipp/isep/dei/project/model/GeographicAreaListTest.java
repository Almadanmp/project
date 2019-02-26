package pt.ipp.isep.dei.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * GeographicAreaList tests class.
 */

class GeographicAreaListTest {
    // Common testing artifacts for this class.

    private GeographicAreaList validList;
    private GeographicArea firstValidArea;
    private GeographicArea secondValidArea;

    @BeforeEach
    void arrangeArtifacts() {
        validList = new GeographicAreaList();
        firstValidArea = new GeographicArea("Portugal", new TypeArea("Country"), 300, 200,
                new Local(50, 50, 10));
        secondValidArea = new GeographicArea("Europe", new TypeArea("Continent"), 3000, 2000,
                new Local(90, 100, 10));
        validList.addGeographicArea(firstValidArea);
    }

    @Test
    void seeIfConstructorWorks() {
        // Arrange

        GeographicAreaList expectedResult = validList;

        //Act

        GeographicAreaList actualResult = new GeographicAreaList(firstValidArea);

        //Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfAddGAToListWorksAlreadyContained() {
        //Act

        boolean actualResult = validList.addGeographicArea(firstValidArea);

        //Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfAddGAToListWorks() {
        //Act

        boolean actualResult = validList.addGeographicArea(secondValidArea);

        //Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfContainsWorksTrue() {
        // Act

        boolean actualResult = validList.contains(firstValidArea);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfFalseWhenGivenGeoAreaIsNotContainedInGeographicAreaList() {
        // Act

        boolean actualResult = validList.contains(secondValidArea);

        // Assert

        assertFalse(actualResult);
    }


    @Test
    void seeIfGetGeoAreaByTypeWorks() {
        // Arrange

        GeographicAreaList expectedResult = new GeographicAreaList(firstValidArea);

        // Act

        GeographicAreaList actualResult = validList.getGeoAreasByType("Country");

        // Assert

        assertEquals(expectedResult, actualResult);
    }


    @Test
    void seeIfGetGeoArea() {
        // Arrange

        GeographicAreaList expectedResult = new GeographicAreaList();

        // Act

        GeographicAreaList actualResult = validList.getGeoAreasByType("Village");

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeifEqualsWorks() {
        // Arrange

        GeographicAreaList testList = new GeographicAreaList(firstValidArea);

        //Act

        boolean actualResult = validList.equals(testList);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfEqualsToSameObject() {
        //Act

        boolean actualResult = validList.equals(validList); // Needed for SonarQube testing purposes.

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfEqualsWorksFalse() {
        // Arrange

        GeographicAreaList testList = new GeographicAreaList(secondValidArea);

        //Act

        boolean actualResult = validList.equals(testList);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksFalseDifferentObject() {
        // Arrange

        RoomList testList = new RoomList();

        //Act

        boolean actualResult = validList.equals(testList); // Needed for SonarQube testing purposes.

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfPrintsGeoAList() {
        // Arrange

        String expectedResult = "---------------\n" +
                "0) Name: Portugal | Type: Country | Latitude: 50.0 | Longitude: 50.0\n" +
                "---------------\n";

        // Act

        String result = validList.buildString();

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfPrintsGeoAListIfEmpty() {
        // Arrange

        GeographicAreaList testList = new GeographicAreaList();
        String expectedResult = "Invalid List - List is Empty\n";

        // Act

        String actualResult = testList.buildString();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void hashCodeDummyTest() {
        // Arrange

        GeographicAreaList testList = new GeographicAreaList();
        int expectedResult = 1;

        // Act

        int actualResult = testList.hashCode();

        // Assert

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfContainsGAByParametersFalse() {
        // Act

        boolean actualResult = validList.containsObjectMatchesParameters("Lisboa", new TypeArea("City"), 21,
                30, 40);


        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfContainsGAByParametersTrue() {
        // Act

        boolean actualResult = validList.containsObjectMatchesParameters("Portugal", new TypeArea("Country"), 50,
                50, 10);


        // Assert

        assertTrue(actualResult);
    }

    @Test
    void checkIfGAExistsIfEmptyList() {
        // Arrange

        GeographicAreaList testList = new GeographicAreaList();

        // Act

        boolean actualResult = testList.containsObjectMatchesParameters("Lisboa", new TypeArea("City"), 21,
                30, 40);


        // Assert

        assertFalse(actualResult);
    }

    @Test
    void isEmpty() {
        //Arrange

        GeographicAreaList emptyTestList = new GeographicAreaList();


        // Act

        boolean actualResult1 = emptyTestList.isEmpty();
        boolean actualResult2 = validList.isEmpty();

        // Assert

        assertTrue(actualResult1);
        assertFalse(actualResult2);
    }
}