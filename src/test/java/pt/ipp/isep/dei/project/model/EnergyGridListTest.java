package pt.ipp.isep.dei.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

        Assertions.assertTrue(actualResult);
    }

    @Test
    void seeIfAddEnergyGridToEnergyGridListFalseAlreadyInList() {
        // Arrange

        validGridList.addGrid(firstValidGrid);

        // Act

        boolean actualResult = validGridList.addGrid(firstValidGrid);

        // Assert

        Assertions.assertTrue(actualResult);
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

        Assertions.assertTrue(actualResult);
    }


    @Test
    void seeIfEqualsWorksDifferentContents() {
        // Arrange

        validGridList.addGrid(firstValidGrid);
        EnergyGridList testList = new EnergyGridList();
        testList.addGrid(secondValidGrid);

        // Act

        boolean actualResult = validGridList.equals(testList);

        Assertions.assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsItselfWorks() {
        // Act

        boolean actualResult = validGridList.equals(validGridList); // For sonarqube coverage purposes.

        // Assert

        Assertions.assertTrue(actualResult);
    }

    @Test
    void seeIfEqualsDifferentObjectWorks() {
        // Act

        Boolean actualResult = validGridList.equals(firstValidGrid); // For sonarqube testing purposes.

        // Assert

        Assertions.assertEquals(false, actualResult);
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

