package pt.ipp.isep.dei.project.model.device.devicespecs;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * fridgeSpec tests class.
 */

class FridgeSpec2Test {

    @Test
    void seeIfGetAttributeNamesTestWorks() {
        FridgeSpec2 fridgespec = new FridgeSpec2();
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add(FridgeSpec2.FREEZER_CAPACITY);
        expectedResult.add(FridgeSpec2.REFRIGERATOR_CAPACITY);
        expectedResult.add(FridgeSpec2.ANNUAL_CONSUMPTION);
        List<String> result = fridgespec.getAttributeNames();
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAttributeValuesTestCapacityWorks() {
        FridgeSpec2 fridgespec = new FridgeSpec2();
        fridgespec.setAttributeValue(FridgeSpec2.FREEZER_CAPACITY, 1D);
        Double expectedResult = 1.0;
        Object result = fridgespec.getAttributeValue(FridgeSpec2.FREEZER_CAPACITY);
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAttributeValuesTestCapacityWorksFalse() {
        FridgeSpec2 fridgespec = new FridgeSpec2();
        fridgespec.setAttributeValue(FridgeSpec2.FREEZER_CAPACITY, 1D);
        Double expectedResult = 1.0;
        Object result = fridgespec.getAttributeValue(FridgeSpec2.FREEZER_CAPACITY);
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAttributeValuesTestReturn0Works() {
        //Arrange
        FridgeSpec2 fridgespec = new FridgeSpec2();
        fridgespec.setAttributeValue(FridgeSpec2.FREEZER_CAPACITY, 1D);
        //Act
        int expectedResult = 0;
        Object actualResult = fridgespec.getAttributeValue("cenas");
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void setAttributeValueTest() {
        FridgeSpec2 fridgespec = new FridgeSpec2();
        fridgespec.setAttributeValue(FridgeSpec2.FREEZER_CAPACITY, 1D);
        boolean result = fridgespec.setAttributeValue("lisboa", 12);
        assertEquals(false, result);
    }

    @Test
    void seeIfSetAttributeValueTestTrueWorks() {
        //Arrange
        FridgeSpec2 fridgespec = new FridgeSpec2();
        fridgespec.setAttributeValue(FridgeSpec2.FREEZER_CAPACITY, 1D);
        //Act
        boolean actualResult = fridgespec.setAttributeValue(FridgeSpec2.FREEZER_CAPACITY, 12.0);
        //Assert
        assertTrue(actualResult);
    }

    @Test
    void getAttributeUnitTest() {
        FridgeSpec2 fridgeSpec = new FridgeSpec2();
        fridgeSpec.setAttributeValue(FridgeSpec2.FREEZER_CAPACITY, 5D);
        String expectedResult = "Kg";
        Object result = fridgeSpec.getAttributeUnit(FridgeSpec2.FREEZER_CAPACITY);
        assertEquals(expectedResult, result);
    }

    @Test
    void setAttributeValueTestCapacity() {
        FridgeSpec2 fridgeSpec = new FridgeSpec2();
        fridgeSpec.setAttributeValue(FridgeSpec2.FREEZER_CAPACITY, 1D);
        fridgeSpec.setAttributeValue(FridgeSpec2.FREEZER_CAPACITY, 5.0);
        Object result = fridgeSpec.getAttributeValue(FridgeSpec2.FREEZER_CAPACITY);
        assertEquals(5.0, result);
    }

    @Test
    void setAttributeValueTestDefault() {
        FridgeSpec2 fridgeSpec = new FridgeSpec2();
        fridgeSpec.setAttributeValue(FridgeSpec2.FREEZER_CAPACITY, 1D);
        fridgeSpec.setAttributeValue(FridgeSpec2.FREEZER_CAPACITY, 5.0);
        Object result = fridgeSpec.getAttributeValue("lisbon");
        assertEquals(0, result);
    }

    @Test
    void setAttributeValueTestFalseAgain() {
        FridgeSpec2 fridgeSpec = new FridgeSpec2();
        fridgeSpec.setAttributeValue(FridgeSpec2.FREEZER_CAPACITY, 1D);
        Object result = fridgeSpec.setAttributeValue(FridgeSpec2.FREEZER_CAPACITY, 5);
        assertEquals(false, result);
    }


    @Test
    void testSetAttributeValueCoveringAllCases() {
        //Arrange
        FridgeSpec2 fridgeSpec = new FridgeSpec2();
        Double attribute = 6.0;
        // original strings:
        assertTrue(fridgeSpec.setAttributeValue(FridgeSpec2.FREEZER_CAPACITY, attribute));
        // same hash codes, but different strings:
        assertFalse(fridgeSpec.setAttributeValue("notCAPACITY", attribute));
        // distinct hash code to cover default cases of switches
        assertFalse(fridgeSpec.setAttributeValue("", attribute));
    }

    @Test
    void seeIfGetAttributeUnitWorksInAllCases() {
        //Arrange
        FridgeSpec2 fridgeSpec = new FridgeSpec2();
        String attributeKg = "Kg";
        // original strings:
        assertEquals(attributeKg, fridgeSpec.getAttributeUnit(FridgeSpec2.FREEZER_CAPACITY));
        // same hash codes, but different strings:
        assertEquals(0, fridgeSpec.getAttributeUnit("notCAPACITY"));
        // distinct hash code to cover default cases of switches
        assertEquals(0, fridgeSpec.getAttributeUnit(""));
    }

}