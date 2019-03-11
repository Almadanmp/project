package pt.ipp.isep.dei.project.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;
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

    @Test
    void getElementsAsArray() {
        //Arrange

        PowerSource[] expectedResult1 = new PowerSource[0];
        PowerSource[] expectedResult2 = new PowerSource[1];
        PowerSource[] expectedResult3 = new PowerSource[2];

        PowerSourceList emptyList = new PowerSourceList();
        PowerSourceList onePowerSource = new PowerSourceList();
        PowerSourceList twoPowerSource = new PowerSourceList();

        onePowerSource.add(new PowerSource("powerSource1", 200, 500));
        twoPowerSource.add(new PowerSource("powerSource1", 200, 500));
        twoPowerSource.add(new PowerSource("powerSource2", 200, 500));

        expectedResult2[0] = new PowerSource("powerSource1", 200, 500);
        expectedResult3[0] = new PowerSource("powerSource1", 200, 500);
        expectedResult3[1] = new PowerSource("powerSource2", 200, 500);

        //Act

        PowerSource[] actualResult1 = emptyList.getElementsAsArray();
        PowerSource[] actualResult2 = onePowerSource.getElementsAsArray();
        PowerSource[] actualResult3 = twoPowerSource.getElementsAsArray();

        //Assert

        assertArrayEquals(expectedResult1, actualResult1);
        assertArrayEquals(expectedResult2, actualResult2);
        assertArrayEquals(expectedResult3, actualResult3);
    }

    @Test
    void seeIfCreatePowerSourceWorks() {
         //Arrange

        PowerSource powerSource = new PowerSource("powerSource", 200, 100);
        // Act

        PowerSource actualResult1 = validList.createPowerSource("Generator", 50, 50);
        PowerSource actualResult2 = validList.createPowerSource("powerSource", 200, 100);

        // Assert

        assertEquals(validPowerSource, actualResult1);
        assertEquals(powerSource, actualResult2);
    }

}