package pt.ipp.isep.dei.project.model;

import org.junit.jupiter.api.Assertions;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PowerSourceListTest {

    @Test
    public void seeIfContainsPowerSource() {
    }

    @Test
    public void seeIfGetPowerSources() {

    }

    @Test
    public void seeIfGetPowerSourceList() {
    }

    @Test
    public void seeIfEquals() {
    }

    @Test
    public void hashCodeDummyTest() {
        PowerSourceList powerSourceList = new PowerSourceList();
        PowerSource power = new PowerSource("power", 19, 289);
        powerSourceList.addPowerSource(power);
        int expectedResult = 1;
        int actualResult = powerSourceList.hashCode();
        Assertions.assertEquals(expectedResult, actualResult);
    }
}