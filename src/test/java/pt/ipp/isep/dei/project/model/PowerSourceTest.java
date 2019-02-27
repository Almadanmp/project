package pt.ipp.isep.dei.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * PowerSource tests class.
 */

class PowerSourceTest {
    private PowerSource validPowerSource;


    @BeforeEach
    void arrangeArtifacts(){
        validPowerSource = new PowerSource("Energy", 50, 50);
    }

    @Test
    void seeIfEqualsPowerSourceWithDifferentObject() {
        // Arrange

        int test = 3;

        // Act

        boolean actualResult = validPowerSource.equals(test); // Necessary for Sonarqube testing purposes.

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsPowerSourceWithDifferentContent() {
        // Arrange

        PowerSource pS2 = new PowerSource("Voltage", 50, 50);

        // Act

        boolean actualResult = validPowerSource.equals(pS2);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsPowerSourceWithSameContent() {
        // Arrange

        PowerSource pS2 = new PowerSource("Energy", 50, 50);

        // Act

        boolean actualResult = validPowerSource.equals(pS2);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfEqualsSameObject() {
        // Act

        boolean actualResult = validPowerSource.equals(validPowerSource); // Necessary for Sonarqube testing purposes.

        // Assert

        assertTrue(actualResult);

    }

    @Test
    void seeIfEqualsNotAInstanceOfNull() {
        // Arrange

        PowerSource testPowerSource = null;
        // Act

        boolean actualResult = validPowerSource.equals(testPowerSource); // Necessary for Soarqube testing purposes.

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsNotAInstanceOfGetClass() {
        //Act

        boolean actualResult = validPowerSource.getClass().equals(getClass());

        //Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfGetMaxPowerOutputWorks(){
        //Arrange

        double expectedResult = 50;

        //Act

        double actualResult = validPowerSource.getMaxPowerOutput();

        //Assert

        assertEquals(expectedResult,actualResult);
    }

    @Test
    void seeIfMaxEnergyStorageWorks(){
        //Arrange

        double expectedResult = 50;

        //Act

        double actualResult = validPowerSource.getMaxEnergyStorage();

        //Assert

        assertEquals(expectedResult,actualResult);
    }

    @Test
    void hashCodeDummyTest() {
        // Arrange

        int expectedResult = 1;

        // Act

        int actualResult = validPowerSource.hashCode();

        // Assert

        assertEquals(expectedResult, actualResult);
    }
}
