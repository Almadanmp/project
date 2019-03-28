package pt.ipp.isep.dei.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import pt.ipp.isep.dei.project.io.ui.MainUI;
import pt.ipp.isep.dei.project.repository.GeographicAreaRepository;

import static org.junit.jupiter.api.Assertions.*;

/**
 * GeographicAreaList tests class.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@ContextConfiguration(classes = {MainUI.class},
        loader = AnnotationConfigContextLoader.class)
class GeographicAreaListTest {
    // Common testing artifacts for this class.

    @Autowired
    GeographicAreaRepository geographicAreaRepository;

    @Autowired
    GeographicAreaList geographicAreaList;

    private GeographicAreaList validList;
    private GeographicArea firstValidArea;
    private GeographicArea secondValidArea;

    @BeforeEach
    void arrangeArtifacts() {
        validList = geographicAreaList;
        firstValidArea = new GeographicArea("Portugal", new TypeArea("Country"), 300, 200,
                new Local(50, 50, 10));
        secondValidArea = new GeographicArea("Europe", new TypeArea("Continent"), 3000, 2000,
                new Local(90, 100, 10));
        validList.addGeographicArea(firstValidArea);
    }


    @Test
    void seeIfAddGAToListWorksAlreadyContained() {
        //Act

        boolean actualResult = validList.addGeographicArea(firstValidArea);
        //Assert

        assertFalse(actualResult);
    }

//    @Test
//    void seeIfAddGAToListWorks() {
//        //Act
//
//        boolean actualResult = validList.addGeographicArea(secondValidArea);
//
//        //Assert
//
//        assertTrue(actualResult);
//    }

    @Test
    void seeIfContainsWorksTrue() {
        // Act

        boolean actualResult = validList.contains(firstValidArea);

        // Assert

        assertTrue(actualResult);
    }

//    @Test
//    void seeIfFalseWhenGivenGeoAreaIsNotContainedInGeographicAreaList() {
//        // Act
//
//        boolean actualResult = validList.contains(secondValidArea);
//
//        // Assert
//
//        assertFalse(actualResult);
//    }


//    @Test
//    void seeIfGetGeoAreaByTypeWorks() {
//        // Arrange
//
//        GeographicAreaList expectedResult = new GeographicAreaList(firstValidArea);
//
//        // Act
//
//        GeographicAreaList actualResult = validList.getGeoAreasByType("Country");
//
//        // Assert
//
//        assertEquals(expectedResult, actualResult);
//    }


//    @Test
//    void seeifEqualsWorks() {
//        // Arrange
//
//        GeographicAreaList testList = new GeographicAreaList(firstValidArea);
//
//        //Act
//
//        boolean actualResult = validList.equals(testList);
//
//        // Assert
//
//        assertTrue(actualResult);
//    }

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

//    @Test
//    void seeIfPrintsGeoAList() {
//        // Arrange
//
//        String expectedResult = "---------------\n" +
//                "0) Name: Portugal | Type: Country | Latitude: 50.0 | Longitude: 50.0\n" +
//                "---------------\n";
//
//        // Act
//
//        String result = validList.buildString();
//
//        // Assert
//
//        assertEquals(expectedResult, result);
//    }

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

        boolean actualResult = validList.containsObjectMatchesParameters("Lisboa", new TypeArea("City"), new Local(21,
                30, 40));


        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfContainsGAByParametersTrue() {
        // Act

        boolean actualResult = validList.containsObjectMatchesParameters("Portugal", new TypeArea("Country"), new Local(50,
                50, 10));


        // Assert

        assertTrue(actualResult);
    }

    @Test
    void checkIfGAExistsIfEmptyList() {
        // Arrange

        GeographicAreaList testList = new GeographicAreaList();

        // Act

        boolean actualResult = testList.containsObjectMatchesParameters("Lisboa", new TypeArea("City"), new Local(21,
                30, 40));


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

//    @Test
//    void seeIfGetByIndexWorks() {
//        //Arrange
//
//        validList.addGeographicArea(secondValidArea);
//
//        //Act
//
//        GeographicArea actualResult1 = validList.get(0);
//        GeographicArea actualResult2 = validList.get(1);
//
//        //Assert
//
//        assertEquals(firstValidArea, actualResult1);
//        assertEquals(secondValidArea, actualResult2);
//    }

    @Test
    void getByIndexEmptyGAList() {
        //Arrange

        GeographicAreaList emptyList = new GeographicAreaList();

        //Act

        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> emptyList.get(0));

        //Assert

        assertEquals("The geographic area list is empty.", exception.getMessage());
    }


    @Test
    void createGA() {
        String iD = "Coimbra";
        TypeArea typeArea = new TypeArea("Distrito");
        Local local = new Local(12, 12, 12);
        GeographicArea expectedResult = new GeographicArea(iD, typeArea, 12, 12, local);

        GeographicArea actualResult = validList.createGA(iD, typeArea, 12, 12, local);

        assertEquals(expectedResult, actualResult);
    }

//    @Test
//    void seeIfGetsAll(){
//        // Arrange
//
//        GeographicAreaList expectedResult = new GeographicAreaList();
//        expectedResult.addWithoutPersisting(firstValidArea);
//
//        // Act
//
//        GeographicAreaList actualResult = validList.getAll();
//
//        // Assert
//
//        assertEquals(expectedResult, actualResult);
//    }

    @Test
    void seeIfGetsGeoAreasByType(){
        // Arrange

        geographicAreaRepository.deleteAll();
        validList.addGeographicArea(firstValidArea);

        GeographicAreaList expectedResult = new GeographicAreaList();
        expectedResult.addWithoutPersisting(firstValidArea);


        // Act

        GeographicAreaList actualResult = validList.getGeoAreasByType("Country");

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfRemovesGeographicAreaTrue() {
        // Act

        boolean actualResult = validList.removeGeographicArea(firstValidArea);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfRemovesGeographicAreaFalse() {
        // Act

        validList.removeGeographicArea(firstValidArea);
        boolean actualResult = validList.removeGeographicArea(firstValidArea);

        // Assert

        assertFalse(actualResult);
    }
}