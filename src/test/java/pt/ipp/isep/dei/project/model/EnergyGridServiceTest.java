package pt.ipp.isep.dei.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project.repository.EnergyGridRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * EnergyGridList tests class.
 */
@ExtendWith(MockitoExtension.class)
class EnergyGridServiceTest {
    // Common testing artifacts for testing class.

    private EnergyGrid firstValidGrid;
    private EnergyGrid secondValidGrid;

    @Mock
    private EnergyGridRepository energyGridRepository;

    private EnergyGridService validGridList;

    @BeforeEach
    void arrangeArtifacts() {
        MockitoAnnotations.initMocks(this);
        validGridList = new EnergyGridService(this.energyGridRepository);
        firstValidGrid = new EnergyGrid("Primary Grid", 500, "CasaUm");
        secondValidGrid = new EnergyGrid("Secondary Grid", 100,"CasaUm");
    }

    @Test
    void seeIfCreateEnergyGrid() {
        EnergyGrid expectedResult = new EnergyGrid("Primary Grid", 500, "CasaUm");

        EnergyGrid result = validGridList.createEnergyGrid("Primary Grid", 500, "CasaUm");

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfAddEnergyGrid() {
        EnergyGrid energyGrid = new EnergyGrid("Third Grid", 56, "CasaUm");

        Mockito.when(energyGridRepository.findByName(energyGrid.getName())).thenReturn(energyGrid);

        assertTrue(validGridList.addPersistenceGrid(energyGrid));
    }

    @Test
    void seeIfGetEnergyGridByIdRepository() {
        long mockId = 1234;

        EnergyGrid energyGrid = new EnergyGrid("Third Grid", 56, "CasaUm");
        energyGrid.setId(1234);
        validGridList.addPersistenceGrid(energyGrid);

        Mockito.when(energyGridRepository.findById(mockId)).thenReturn(Optional.of(energyGrid));

        EnergyGrid result = validGridList.getById(mockId);

        assertEquals(result.getId(), energyGrid.getId());
        assertEquals(result.getName(), energyGrid.getName());
    }

    @Test
    void seeIfGetEnergyGridByIdRepositoryNull() {
        long mockId = 1234;

        Mockito.when(energyGridRepository.findById(mockId)).thenReturn(Optional.empty());

        Throwable exception = assertThrows(NoSuchElementException.class, () -> validGridList.getById(mockId));

        assertEquals("ERROR: There is no Energy Grid with the selected ID.", exception.getMessage());
    }

    @Test
    void seeIfSizeRepository() {

        EnergyGrid energyGrid = new EnergyGrid("Third Grid", 56, "CasaUm");

        List<EnergyGrid> energyGrids = new ArrayList<>();
        energyGrids.add(energyGrid);

        Mockito.when(energyGridRepository.findAll()).thenReturn(energyGrids);

        int result = validGridList.size();

        assertEquals(result, 1);
    }

    @Test
    void seeIfSizeRepositoryDoNotChange() {

        List<EnergyGrid> energyGrids = new ArrayList<>();
        energyGrids.add(null);

        Mockito.when(energyGridRepository.findAll()).thenReturn(energyGrids);

        int result = validGridList.size();

        assertEquals(result, 1);
    }

    @Test
    void seeIfIsEmptyFalse() {

        EnergyGrid energyGrid = new EnergyGrid("Third Grid", 56, "CasaUm");

        validGridList.addGrid(energyGrid);


        assertFalse(validGridList.isEmpty());

    }

    @Test
    void seeIfIsEmptyTrue() {

        assertTrue(validGridList.isEmpty());
    }

    @Test
    void getAllAsString() {

        EnergyGrid energyGrid = new EnergyGrid("Third Grid", 56, "CasaUm");
        energyGrid.setId(20);
        validGridList.addGrid(energyGrid);

        String expectedResult = "---------------\n" +
                "20) Designation: Third Grid | Max Power: 56.0\n" +
                "---------------\n";

        String result = validGridList.buildString();

        assertEquals(expectedResult, result);
    }

    @Test
    void getAllAsStringEmpty() {

        String expectedResult = "Invalid List - List is Empty\n";

        String result = validGridList.buildString();

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfAddEnergyGridToEnergyGridListTrue() {
        // Act
        boolean actualResult = validGridList.addGrid(firstValidGrid);


        // Assert
        assertTrue(actualResult);
    }

    @Test
    void seeIfAddEnergyGridToEnergyGridListFalseAlreadyInList() {
        // Arrange
        EnergyGrid energyGrid = new EnergyGrid("Primary Grid", 500,"CasaUm");
        validGridList.addGrid(energyGrid);


        // Act
        boolean actualResult = validGridList.addGrid(energyGrid);

        // Assert
        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorks() {
        // Arrange
        EnergyGrid energyGrid = new EnergyGrid("Primary Grid", 500, "CasaUm");
        List<EnergyGrid> testList = new ArrayList<>();
        List<EnergyGrid> validList = new ArrayList<>();
        validList.add(energyGrid);
        testList.add(energyGrid);

        // Act

        boolean actualResult = testList.equals(validList);

        // Assert

        assertTrue(actualResult);
    }


    @Test
    void seeIfEqualsWorksDifferentContents() {
        // Arrange

        EnergyGrid energyGrid = new EnergyGrid("Primary Grid", 500, "CasaUm");
        EnergyGrid energyGrid2 = new EnergyGrid("Primary Grid", 500, "CasaUm");
        List<EnergyGrid> testList = new ArrayList<>();
        List<EnergyGrid> validList = new ArrayList<>();
        validList.add(energyGrid);
        testList.add(energyGrid2);

        // Act

        boolean actualResult = validGridList.equals(testList);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsItselfWorks() {
        // Act

        boolean actualResult = validGridList.equals(validGridList); // For sonarqube coverage purposes.

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfIsEmpty() {
        //Arrange
        EnergyGridService testListOneGrid = new EnergyGridService(); // Not empty: One Grid
        EnergyGridService testListTwoGrids = new EnergyGridService(); // Not empty: Two Grids

        EnergyGrid energyGrid1 = new EnergyGrid("grid1", 200, "CasaUm");
        EnergyGrid energyGrid2 = new EnergyGrid("grid2", 200, "CasaUm");

        testListOneGrid.addGrid(energyGrid1);
        testListTwoGrids.addGrid(energyGrid1);
        testListTwoGrids.addGrid(energyGrid2);

        // Act

        boolean actualResult1 = validGridList.isEmpty();
        boolean actualResult2 = testListOneGrid.isEmpty();
        boolean actualResult3 = testListTwoGrids.isEmpty();

        // Assert

        assertTrue(actualResult1);
        assertFalse(actualResult2);
        assertFalse(actualResult3);
    }

//    @Test
//    void getElementsAsArray() {
//        //Arrange
//
//        EnergyGrid energyGrid = new EnergyGrid("Primary Grid", 500);
//
//
//        EnergyGrid[] expectedResult1 = new EnergyGrid[0];
//        EnergyGrid[] expectedResult2 = new EnergyGrid[1];
//        EnergyGrid[] expectedResult3 = new EnergyGrid[2];
//
//        EnergyGridService emptyList = new EnergyGridService();
//        validGridList.addGrid(energyGrid);
//        EnergyGridService validEnergyGridService2 = new EnergyGridService();
//        validEnergyGridService2.addGrid(energyGrid);
//        validEnergyGridService2.addGrid(secondValidGrid);
//
//        expectedResult2[0] = energyGrid;
//        expectedResult3[0] = energyGrid;
//        expectedResult3[1] = secondValidGrid;
//
//        //Act
//
//        EnergyGrid[] actualResult1 = emptyList.getElementsAsArray();
//        EnergyGrid[] actualResult2 = validGridList.getElementsAsArray();
//        EnergyGrid[] actualResult3 = validEnergyGridService2.getElementsAsArray();
//
//        //Assert
//
//        assertArrayEquals(expectedResult1, actualResult1);
//        assertArrayEquals(expectedResult2, actualResult2);
//        assertArrayEquals(expectedResult3, actualResult3);
//    }


    @Test
    void seeIfEqualsDifferentObjectWorks() {
        // Act

        boolean actualResult = validGridList.equals(firstValidGrid); // For sonarqube testing purposes.

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void getGridByIndex() {
        //Arrange

        validGridList.addGrid(firstValidGrid);
        validGridList.addGrid(secondValidGrid);

        // Act

        EnergyGrid actualResult1 = validGridList.get(0);
        EnergyGrid actualResult2 = validGridList.get(1);

        // Assert

        assertEquals(firstValidGrid, actualResult1);
        assertEquals(secondValidGrid, actualResult2);
    }

    @Test
    void getByIndexEmptyGridList() {

        //Act

        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> validGridList.get(0));

        //Assert

        assertEquals("The energy grid list is empty.", exception.getMessage());
    }

//    @Test
//    void gridListSize() {
//        //Act
//
//        int actualResult1 = validGridList.size();
//
//        //Assert Empty List
//
//        assertEquals(0, actualResult1);
//
//        //Arrange
//
//        validGridList.addGrid(new EnergyGrid("grid", 200));
//
//        //Act
//
//        int actualResult2 = validGridList.size();
//
//        //Assert One Grid
//
//        assertEquals(1, actualResult2);
//    }

    @Test
    void seeIfCreateEnergyWorks() {
        //Act

        EnergyGrid actualResult1 = validGridList.createEnergyGrid("Primary Grid", 500, "CasaUm");

        //Assert Empty List

        assertEquals(firstValidGrid, actualResult1);

        //Arrange

        validGridList.addGrid(firstValidGrid);

        //Act When Grid Exists

        EnergyGrid actualResult2 = validGridList.createEnergyGrid("Primary Grid", 500, "CasaUm");

        //Assert

        assertEquals(firstValidGrid, actualResult2);

        //Act When Grid Doesn't Exist

        EnergyGrid actualResult3 = validGridList.createEnergyGrid("Secondary Grid", 100,"CasaUm");

        //Assert

        assertEquals(secondValidGrid, actualResult3);
    }

    @Test
    void hashCodeDummyTest() {
        // Arrange

        int expectedResult = 1;

        // Act

        int actualResult = validGridList.hashCode();

        // Assert

        assertEquals(expectedResult, actualResult);
    }
}

