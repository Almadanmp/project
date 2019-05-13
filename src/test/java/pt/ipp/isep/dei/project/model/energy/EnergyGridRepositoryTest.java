package pt.ipp.isep.dei.project.model.energy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project.model.room.Room;
import pt.ipp.isep.dei.project.repository.EnergyGridRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

/**
 * EnergyGridList tests class.
 */
@ExtendWith(MockitoExtension.class)
class EnergyGridRepositoryTest {
    // Common testing artifacts for testing class.

    private EnergyGrid firstValidGrid;
    private EnergyGrid secondValidGrid;

    @Mock
    private EnergyGridRepo energyGridRepository;

    private EnergyGridRepository validGridList;

    @BeforeEach
    void arrangeArtifacts() {
        MockitoAnnotations.initMocks(this);
        validGridList = new EnergyGridRepository(this.energyGridRepository);
        firstValidGrid = new EnergyGrid("Primary Grid", 500, "CasaUm");
        secondValidGrid = new EnergyGrid("Secondary Grid", 100, "CasaUm");
    }

    @Test
    void seeIfGetAllGridsWorks() {
        // Arrange
        Mockito.when(energyGridRepository.findAll()).thenReturn(null);

        List<EnergyGrid> expectedResult = new ArrayList<>();

        // Act
        List<EnergyGrid> actualResult = validGridList.getAllGrids();

        // Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfAddGridWorks() {
        // Arrange

        Mockito.when(energyGridRepository.save(firstValidGrid)).thenReturn(firstValidGrid);

        EnergyGrid expectedResult = firstValidGrid;

        // Act

        EnergyGrid actualResult = validGridList.addGrid(firstValidGrid);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfAddGridWorksNull() {
        // Arrange

        Mockito.when(energyGridRepository.save(firstValidGrid)).thenReturn(firstValidGrid);

        EnergyGrid expectedResult = firstValidGrid;

        // Act

        EnergyGrid actualResult = validGridList.addGrid(firstValidGrid);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    /**
     * This test tries to have a Grid with powersources and one equal but without them.
     * Then it saves the grid with powersources in the mockito repository.
     * Then it saves the grid without PS, to see if by substituting the grid it still keeps the powersources.
     * The test is positive, meaning it keeps the powersources. We are still not sure if this is
     * the wanted behaviour, but if it is, it works.
     */

    @Test
    void seeIfAddGridWorksAndKeepsPowerSourceList() {

        // Arrange
        PowerSource firstPowerSource = new PowerSource("Top Floor", 25,
                15);

        List<PowerSource> expectedResult = new ArrayList<>();
        expectedResult.add(firstPowerSource);

        EnergyGrid firstValidGridWithNoPSList = firstValidGrid;

        firstValidGrid.addPowerSource(firstPowerSource);

        String mockId = "Primary Grid";

        Mockito.when(energyGridRepository.findByName(mockId)).thenReturn(firstValidGrid);

        Mockito.when(energyGridRepository.save(firstValidGrid)).thenReturn(firstValidGrid);

        // Act
        validGridList.addGrid(firstValidGrid);
        validGridList.addGrid(firstValidGridWithNoPSList);
        EnergyGrid copiedGridWithoutPS = energyGridRepository.findByName("Primary Grid");

        List<PowerSource> actualResult = copiedGridWithoutPS.getPowerSourceList();

        // Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfCreateEnergyGrid() {
        EnergyGrid expectedResult = new EnergyGrid("Primary Grid", 500, "CasaUm");

        EnergyGrid result = validGridList.createEnergyGrid("Primary Grid", 500, "CasaUm");

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetEnergyGridByIdRepository() {

        EnergyGrid energyGrid = new EnergyGrid("Third Grid", 56, "CasaUm");
        validGridList.addGrid(energyGrid);

        Mockito.when(energyGridRepository.findById(energyGrid.getName())).thenReturn(Optional.of(energyGrid));

        EnergyGrid result = validGridList.getById(energyGrid.getName());

        assertEquals(result.getName(), energyGrid.getName());
    }

    @Test
    void seeIfGetEnergyGridByIdRepositoryNull() {
        String mockId = "1234";

        Mockito.when(energyGridRepository.findById(mockId)).thenReturn(Optional.empty());

        Throwable exception = assertThrows(NoSuchElementException.class, () -> validGridList.getById(mockId));

        assertEquals("ERROR: There is no Energy Grid with the selected ID.", exception.getMessage());
    }

    @Test
    void seeIfSizeRepository() {

        EnergyGrid energyGrid = new EnergyGrid("Third Grid", 56, "CasaUm");

        List<EnergyGrid> energyGrids = new ArrayList<>();
        energyGrids.add(energyGrid);
        int expectedResult =1;

        Mockito.when(energyGridRepository.findAll()).thenReturn(energyGrids);

        int result = validGridList.size();

        assertEquals(expectedResult,result);
    }

    @Test
    void seeIfSizeRepositoryDoNotChange() {

        List<EnergyGrid> energyGrids = new ArrayList<>();
        energyGrids.add(null);
        int expectedResult =1;

        Mockito.when(energyGridRepository.findAll()).thenReturn(energyGrids);

        int result = validGridList.size();

        assertEquals(expectedResult,result);
    }

//    @Test
//    void seeIfIsEmptyFalse() {
//
//        EnergyGrid energyGrid = new EnergyGrid("Third Grid", 56, "CasaUm");
//
//        validGridList.addGrid(energyGrid);
//
//
//        assertFalse(validGridList.isEmpty());
//
//    }

    @Test
    void seeIfIsEmptyTrue() {

        assertTrue(validGridList.isEmpty());
    }

    @Test
    void getAllAsString() {

        String expectedResult = "Invalid List - List is Empty\n";

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

        // Act

        boolean actualResult1 = validGridList.isEmpty();

        // Assert

        assertTrue(actualResult1);
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


}

