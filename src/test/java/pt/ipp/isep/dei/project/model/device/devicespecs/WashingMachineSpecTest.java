
package pt.ipp.isep.dei.project.model.device.devicespecs;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * WashingMachineSpec tests class.
 */


class WashingMachineSpecTest {

    @Test
    void getAttributeNamesTest() {
        WashingMachineSpec washingMachineSpec = new WashingMachineSpec();
        washingMachineSpec.setAttributeValue(WashingMachineSpec.CAPACITY, 5D);
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add(WashingMachineSpec.CAPACITY);
        List<String> result = washingMachineSpec.getAttributeNames();
        assertEquals(expectedResult, result);
    }

    @Test
    void getAttributeValuesTest() {
        WashingMachineSpec washingMachineSpec = new WashingMachineSpec();
        washingMachineSpec.setAttributeValue(WashingMachineSpec.CAPACITY, 5D);
        double expectedResult = 5.0;
        Object result = washingMachineSpec.getAttributeValue("Capacity");
        assertEquals(expectedResult, result);
    }

    @Test
    void getAttributeUnitTest() {
        WashingMachineSpec washingMachineSpec = new WashingMachineSpec();
        washingMachineSpec.setAttributeValue(WashingMachineSpec.CAPACITY, 5D);
        String expectedResult = "Kg";
        Object result = washingMachineSpec.getAttributeUnit("Capacity");
        assertEquals(expectedResult, result);
    }


    @Test
    void getAttributeValuesTest1() {
        WashingMachineSpec washingMachineSpec = new WashingMachineSpec();
        washingMachineSpec.setAttributeValue(WashingMachineSpec.CAPACITY, 5D);
        int expectedResult = 0;
        Object result = washingMachineSpec.getAttributeValue("Capacity" + "programList");
        assertEquals(expectedResult, result);
    }

    @Test
    void getAttributeValuesWithCapacityEmptyTest() {
        WashingMachineSpec washingMachineSpec = new WashingMachineSpec();
        washingMachineSpec.setAttributeValue(WashingMachineSpec.CAPACITY, 34D);
        double expectedResult = 34.0;
        Object result = washingMachineSpec.getAttributeValue("Capacity");
        assertEquals(expectedResult, result);
    }

    @Test
    void setAttributeValueTestCapacity() {
        WashingMachineSpec washingMachineSpec = new WashingMachineSpec();
        washingMachineSpec.setAttributeValue("Capacity", 5.0D);
        Object result = washingMachineSpec.getAttributeValue("Capacity");
        assertEquals(5.0, result);
    }

    @Test
    void setAttributeValueTestCapacity2() {
        WashingMachineSpec washingMachineSpec = new WashingMachineSpec();
        washingMachineSpec.setAttributeValue(WashingMachineSpec.CAPACITY, 23D);
        Object result = washingMachineSpec.getAttributeValue("Capacity");
        assertEquals(23.0, result);
    }

    @Test
    void seeIfSetAttributeValueTestTrueWorks() {
        //Arrange
        WashingMachineSpec washingMachineSpec = new WashingMachineSpec();
        washingMachineSpec.setAttributeValue(WashingMachineSpec.CAPACITY, 5D);
        //Act
        boolean actualResult = washingMachineSpec.setAttributeValue("Capacity", 12.0);
        //Assert
        assertEquals(true, actualResult);
    }

    @Test
    void setAttributeValueTestDefault() {
        WashingMachineSpec washingMachineSpec = new WashingMachineSpec();
        washingMachineSpec.setAttributeValue("capacity", 5.0);
        Object result = washingMachineSpec.getAttributeValue("lisbon");
        assertEquals(0, result);
    }

    @Test
    void setAttributeValueTestDefault3() {
        WashingMachineSpec washingMachineSpec = new WashingMachineSpec();
        washingMachineSpec.setAttributeValue("Capacity", 6.0);
        Object result = washingMachineSpec.getAttributeValue("Capacity");
        Object expectedResult = 6.0;
        assertEquals(expectedResult, result);
    }


    @Test
    void setAttributeValueTestFalseAgain() {
        WashingMachineSpec washingMachineSpec = new WashingMachineSpec();
        Object result = washingMachineSpec.setAttributeValue(WashingMachineSpec.CAPACITY, 5);
        assertEquals(false, result);
    }


    @Test
    void setAttributeValueTestFalseCap() {
        WashingMachineSpec washingMachineSpec = new WashingMachineSpec();
        Object result = washingMachineSpec.setAttributeValue(WashingMachineSpec.CAPACITY, "sjfhbfhfh");
        assertEquals(false, result);
    }

    @Test
    void setAttributeValueTest() {
        WashingMachineSpec washingMachineSpec = new WashingMachineSpec();
        boolean result = washingMachineSpec.setAttributeValue("lisboa", 12);
        assertEquals(false, result);
    }

    @Test
    void testGetAttributeCoveringAllCases() {
        //Arrange
        WashingMachineSpec washingMachineSpec = new WashingMachineSpec();
        washingMachineSpec.setAttributeValue(WashingMachineSpec.CAPACITY, 5D);
        // original strings:
        assertEquals(5.0, washingMachineSpec.getAttributeValue(WashingMachineSpec.CAPACITY));

        // same hash codes, but different strings:
        assertEquals(0, washingMachineSpec.getAttributeValue("\0Capacity"));

        // distinct hash code to cover default cases of switches
        assertEquals(0, washingMachineSpec.getAttributeValue(""));
    }

    @Test
    void testSetAttributeValueCoveringAllCases() {
        //Arrange
        WashingMachineSpec wMachineSpec = new WashingMachineSpec();
        Double attribute = 6.0;
        // original strings:
        assertTrue(wMachineSpec.setAttributeValue(WashingMachineSpec.CAPACITY, attribute));
        // same hash codes, but different strings:
        assertFalse(wMachineSpec.setAttributeValue("notCapacity", attribute));
        // distinct hash code to cover default cases of switches
        assertFalse(wMachineSpec.setAttributeValue("", attribute));
    }

    @Test
    void seeIfGetAttributeUnitWorksInAllCases() {
        //Arrange
        WashingMachineSpec wMachineSpec = new WashingMachineSpec();
        String attributeKg = "Kg";
        // original strings:
        assertEquals(attributeKg, wMachineSpec.getAttributeUnit(WashingMachineSpec.CAPACITY));
        // same hash codes, but different strings:
        assertEquals(false, wMachineSpec.getAttributeUnit("notCapacity"));
        // distinct hash code to cover default cases of switches
        assertEquals(false, wMachineSpec.getAttributeUnit(""));
    }
}
