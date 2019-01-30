package pt.ipp.isep.dei.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * PowerSource tests class.
 */

class PowerSourceTest {

    @Test
    void seeHashCodeDummyTest() {
        PowerSource pS1 = new PowerSource("Energia", 1, 1);
        int expectedResult = 1;
        int actualResult = pS1.hashCode();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfEqualsPowerSourceWithDifferentObject() {
        PowerSource pS1 = new PowerSource("Energia", 50, 50);
        int teste = 3;
        boolean actualResult = pS1.equals(teste);
        boolean expectedResult = false;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfEqualsPowerSourceWithDifferentContent() {
        PowerSource pS1 = new PowerSource("Energia", 50, 50);
        PowerSource pS2 = new PowerSource("Muita Energia", 50, 50);
        boolean actualResult = pS1.equals(pS2);
        boolean expectedResult = false;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfEqualsPowerSourceWithSameContent() {
        PowerSource pS1 = new PowerSource("Energia", 50, 50);
        PowerSource pS2 = new PowerSource("Energia", 50, 50);
        boolean actualResult = pS1.equals(pS2);
        boolean expectedResult = true;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfEqualsSameObject() {
        PowerSource pS1 = new PowerSource("Energia", 50, 50);
        boolean actualResult = pS1.equals(pS1);
        boolean expectedResult = true;
        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfEqualsNotAInstanceOfNull() {
        PowerSource pS1 = new PowerSource("Energia", 50, 50);

        Boolean expectedResult = false;

        Boolean actualResult = pS1.equals(null);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfEqualsNotAInstanceOfGetClass() {
        PowerSource pS1 = new PowerSource("Energia", 50, 50);

        Boolean expectedResult = false;

        Boolean actualResult = pS1.getClass().equals(getClass());

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetMaxPowerOutputWorks(){
        PowerSource ps1 = new PowerSource("Energia", 50, 50);
        double expectedResult = 50;
        double actualResult = ps1.getMaxPowerOutput();
        assertEquals(expectedResult,actualResult);
    }

    @Test
    void seeIfMaxEnergyStorageWorks(){
        PowerSource ps1 = new PowerSource("Energia", 50, 50);
        double expectedResult = 50;
        double actualResult = ps1.getMaxEnergyStorage();
        assertEquals(expectedResult,actualResult);
    }
}
