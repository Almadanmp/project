package pt.ipp.isep.dei.project.model;

import org.junit.jupiter.api.Test;

import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PowerSourceTest {

    @Test
    public void seeHashCodeDummyTest() {
        PowerSource pS1 = new PowerSource("Energia", 50, 50);
        PowerSourceList pSList1 = new PowerSourceList();
        pSList1.addPowerSource(pS1);
        int expectedResult = 1;
        int actualResult = pSList1.hashCode();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfEqualsPowerSourceWithDifferentObject() {
        PowerSource pS1 = new PowerSource("Energia", 50, 50);
        int teste = 3;
        PowerSourceList pSList1 = new PowerSourceList();
        pSList1.addPowerSource(pS1);
        boolean actualResult = pSList1.equals(teste);
        boolean expectedResult = false;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfEqualsPowerSourceWithDifferentContent() {
        PowerSource pS1 = new PowerSource("Energia", 50, 50);
        PowerSource pS2 = new PowerSource("Muita Energia", 50, 50);
        boolean actualResult = pS1.equals(pS2);
        boolean expectedResult = false;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfEqualsPowerSourceWithSameContent() {
        PowerSource pS1 = new PowerSource("Energia", 50, 50);
        PowerSource pS2 = new PowerSource("Energia", 50, 50);
        boolean actualResult = pS1.equals(pS2);
        boolean expectedResult = true;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfEqualsSameObject() {
        PowerSource pS1 = new PowerSource("Energia", 50, 50);
        boolean actualResult = pS1.equals(pS1);
        boolean expectedResult = true;
        assertEquals(expectedResult, actualResult);

    }

    @Test
    public void seeIfEqualsNotAInstanceOfNull() {
        PowerSource pS1 = new PowerSource("Energia", 50, 50);

        Boolean expectedResult = false;

        Boolean actualResult = pS1.equals(null);

        assertEquals(expectedResult, actualResult);
    }
}
