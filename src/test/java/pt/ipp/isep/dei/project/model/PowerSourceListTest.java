package pt.ipp.isep.dei.project.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.testng.Assert.*;

/**
 * PowerSourceList class tests.
 */

 class PowerSourceListTest {
     private PowerSourceList validList;
     private PowerSource validPowerSource;
     private PowerSource validSecondaryPowerSource;

     @BeforeEach
     void arrangeArtifacts(){
         validList = new PowerSourceList();
         validPowerSource = new PowerSource("Generator", 50, 50);
         validSecondaryPowerSource = new PowerSource("Generator 101", 50, 50);
         validList.add(validPowerSource);

     }

    @Test
    void seeIfContainsPowerWorksTrue() {
        // Act

        validList.add(validSecondaryPowerSource);
        boolean actualResult = validList.contains(validSecondaryPowerSource);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfContainsPowerWorksFalse() {
         //Act

         boolean actualResult = validList.contains(validSecondaryPowerSource);

         //Arrange

         assertFalse(actualResult);
    }

    @Test
    void seeIfAddPowerSourceWorks() {
        // Act

        boolean actualResult = validList.add(validSecondaryPowerSource);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfAddPowerSourceWorksForFalse() {
        // Act

        boolean actualResult = validList.add(validPowerSource);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeHashCodeDummyTest() {
         // Arrange

        int expectedResult = 1;

        // Act

        int actualResult = validList.hashCode();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfEqualsPowerSourceWithDifferentObject() {
         // Arrange

         int test = 3;

         // Act

         boolean actualResult = validList.equals(test);  // Needed for Sonarqube testing purposes.

         // Assert

         assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsPowerSourceWithDifferentContent() {
         // Arrange

         PowerSourceList pSList2 = new PowerSourceList();
         pSList2.add(validSecondaryPowerSource);

         // Act

         boolean actualResult = validList.equals(pSList2);

         // Assert

         assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsPowerSourceWithSameContent() {
         // Arrange

         PowerSourceList pSList2 = new PowerSourceList();
         pSList2.add(validPowerSource);

         // Act

         boolean actualResult = validList.equals(pSList2);

         // Assert

         assertTrue(actualResult);
    }

    @Test
    void seeIfEqualsSameObject() {
         // Act

         boolean actualResult = validList.equals(validList); // Needed for Sonarqube testing purposes.

         // Assert

         assertTrue(actualResult);
    }
}