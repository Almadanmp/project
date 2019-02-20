package pt.ipp.isep.dei.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.testng.Assert.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * EnergyGridList tests class.
 */

class EnergyGridListTest {
    // Common testing artifacts for testing class.

    private EnergyGridList validGridList;
    private EnergyGrid firstValidGrid;
    private EnergyGrid secondValidGrid;

    @BeforeEach
    void arrangeArtifacts() {
        validGridList = new EnergyGridList();
        firstValidGrid = new EnergyGrid("Primary Grid", 500);
        secondValidGrid = new EnergyGrid("Secondary Grid", 100);
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

        validGridList.addGrid(firstValidGrid);

        // Act

        boolean actualResult = validGridList.addGrid(firstValidGrid);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorks() {
        // Arrange

        EnergyGridList testList = new EnergyGridList();
        validGridList.addGrid(firstValidGrid);
        testList.addGrid(firstValidGrid);

        // Act

        boolean actualResult = testList.equals(validGridList);

        // Assert

        assertTrue(actualResult);
    }


    @Test
    void seeIfEqualsWorksDifferentContents() {
        // Arrange

        validGridList.addGrid(firstValidGrid);
        EnergyGridList testList = new EnergyGridList();
        testList.addGrid(secondValidGrid);

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
        EnergyGridList testListOneGrid = new EnergyGridList(); // Not empty: One Grid
        EnergyGridList testListTwoGrids = new EnergyGridList(); // Not empty: Two Grids

        EnergyGrid energyGrid1 = new EnergyGrid("grid1", 200);
        EnergyGrid energyGrid2 = new EnergyGrid("grid2", 200);

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


    @Test
    void seeIfEqualsDifferentObjectWorks() {
        // Act

        Boolean actualResult = validGridList.equals(firstValidGrid); // For sonarqube testing purposes.

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void hashCodeDummyTest() {
        // Arrange

        int expectedResult = 1;

        // Act

        int actualResult = validGridList.hashCode();

        // Assert
        Assertions.assertEquals(expectedResult, actualResult);
    }
}

