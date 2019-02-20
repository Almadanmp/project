package pt.ipp.isep.dei.project.model;


import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.testng.Assert.*;

/**
 * PowerSourceList tests class.
 */

 class PowerSourceListTest {

    @Test
    void seeIfContainsPowerWorksTrue() {
        PowerSource pS1 = new PowerSource("Energia", 50, 50);
        PowerSource pS2 = new PowerSource("Muita Energia", 50, 50);
        PowerSourceList pSList1 = new PowerSourceList();
        pSList1.addPowerSource(pS1);
        pSList1.addPowerSource(pS2);
        boolean actualResult = pSList1.containsPowerSource(pS2);
        boolean expectedResult = true;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfContainsPowerWorksFalse() {
        PowerSource pS1 = new PowerSource("Energia", 50, 50);
        PowerSource pS2 = new PowerSource("Muita Energia", 50, 50);
        PowerSourceList pSList1 = new PowerSourceList();
        pSList1.addPowerSource(pS1);
        boolean actualResult = pSList1.containsPowerSource(pS2);
        boolean expectedResult = false;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfAddPowerSourceWorks() {
        //Arrange
        PowerSource pS1 = new PowerSource("Energia", 50, 50);
        PowerSourceList pSList1 = new PowerSourceList();
        //Act
        boolean actualResult = pSList1.addPowerSource(pS1);
        //Assert
        assertTrue(actualResult);
    }

    @Test
    void seeIfAddPowerSourceWorks2() {
        //Arrange
        PowerSource pS1 = new PowerSource("Energia", 50, 50);
        PowerSource pS2 = new PowerSource("Muita Energia", 50, 50);
        PowerSourceList pSList1 = new PowerSourceList();
        pSList1.addPowerSource(pS1);
        //Act
        boolean actualResult = pSList1.addPowerSource(pS2);
        //Assert
        assertTrue(actualResult);
    }

    @Test
    void seeIfAddPowerSourceWorksForFalse() {
        //Arrange
        PowerSource pS1 = new PowerSource("Energia", 50, 50);
        PowerSource pS2 = new PowerSource("Energia", 50, 50);
        PowerSourceList pSList1 = new PowerSourceList();
        pSList1.addPowerSource(pS1);
        //Act
        boolean actualResult = pSList1.addPowerSource(pS2);
        //Assert
        assertFalse(actualResult);
    }


    @Test
    void seeIfAddPowerSourceWorksForFalse2() {
        //Arrange
        PowerSource pS1 = new PowerSource("Energia", 50, 50);
        PowerSourceList pSList1 = new PowerSourceList();
        pSList1.addPowerSource(pS1);
        //Act
        boolean actualResult = pSList1.addPowerSource(pS1);
        //Assert
        assertFalse(actualResult);
    }


    @Test
    void seeHashCodeDummyTest() {
        PowerSource pS1 = new PowerSource("Energia", 50, 50);
        PowerSourceList pSList1 = new PowerSourceList();
        pSList1.addPowerSource(pS1);
        int expectedResult = 1;
        int actualResult = pSList1.hashCode();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfEqualsPowerSourceWithDifferentObject() {
        PowerSource pS1 = new PowerSource("Energia", 50, 50);
        int teste = 3;
        PowerSourceList pSList1 = new PowerSourceList();
        pSList1.addPowerSource(pS1);
        boolean actualResult = pSList1.equals(teste);
        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsPowerSourceWithDifferentContent() {
        PowerSource pS1 = new PowerSource("Energia", 50, 50);
        PowerSource pS2 = new PowerSource("Muita Energia", 50, 50);
        PowerSourceList pSList1 = new PowerSourceList();
        PowerSourceList pSList2 = new PowerSourceList();
        pSList1.addPowerSource(pS1);
        pSList2.addPowerSource(pS2);
        boolean actualResult = pSList1.equals(pSList2);
        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsPowerSourceWithDifferentContent2() {
        PowerSource pS1 = new PowerSource("Energia", 50, 50);
        PowerSource pS2 = new PowerSource("Muita Energia", 50, 50);
        PowerSourceList pSList1 = new PowerSourceList();
        pSList1.addPowerSource(pS1);
        boolean actualResult = pSList1.equals(pS2);
        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsPowerSourceWithSameContent() {
        PowerSource pS1 = new PowerSource("Energia", 50, 50);
        PowerSource pS2 = new PowerSource("Energia", 50, 50);
        PowerSourceList pSList1 = new PowerSourceList();
        PowerSourceList pSList2 = new PowerSourceList();
        pSList1.addPowerSource(pS1);
        pSList2.addPowerSource(pS2);
        boolean actualResult = pSList1.equals(pSList2);
        assertEquals(true, actualResult);
    }

    @Test
    void seeIfEqualsSameObject() {
        PowerSource pS1 = new PowerSource("Energia", 50, 50);
        PowerSourceList pSList1 = new PowerSourceList();
        pSList1.addPowerSource(pS1);
        boolean actualResult = pSList1.equals(pSList1);
        boolean expectedResult = true;
        assertEquals(true, actualResult);
    }

}