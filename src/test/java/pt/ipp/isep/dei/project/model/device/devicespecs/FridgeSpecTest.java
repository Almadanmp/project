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

class FridgeSpecTest {

    @Test
    void seeIfGetAttributeNamesTestWorks() {
        FridgeSpec fridgespec = new FridgeSpec();
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add(FridgeSpec.FREEZER_CAPACITY);
        expectedResult.add(FridgeSpec.REFRIGERATOR_CAPACITY);
        expectedResult.add(FridgeSpec.ANNUAL_CONSUMPTION);
        List<String> result = fridgespec.getAttributeNames();
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAttributeValuesTestCapacityWorks() {
        FridgeSpec fridgespec = new FridgeSpec();
        fridgespec.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 1D);
        Double expectedResult = 1.0;
        Object result = fridgespec.getAttributeValue(FridgeSpec.FREEZER_CAPACITY);
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAttributeValuesTestCapacityWorksFalse() {
        FridgeSpec fridgespec = new FridgeSpec();
        fridgespec.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 1D);
        Double expectedResult = 1.0;
        Object result = fridgespec.getAttributeValue(FridgeSpec.FREEZER_CAPACITY);
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAttributeValuesTestReturn0Works() {
        //Arrange
        FridgeSpec fridgespec = new FridgeSpec();
        fridgespec.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 1D);
        //Act
        int expectedResult = 0;
        Object actualResult = fridgespec.getAttributeValue("cenas");
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void setAttributeValueTest() {
        FridgeSpec fridgespec = new FridgeSpec();
        fridgespec.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 1D);
        boolean result = fridgespec.setAttributeValue("lisboa", 12);
        assertEquals(false, result);
    }

    @Test
    void seeIfSetAttributeValueTestTrueWorks() {
        //Arrange
        FridgeSpec fridgespec = new FridgeSpec();
        fridgespec.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 1D);
        //Act
        boolean actualResult = fridgespec.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 12.0);
        //Assert
        assertTrue(actualResult);
    }

    @Test
    void getAttributeUnitTest() {
        FridgeSpec fridgeSpec = new FridgeSpec();
        fridgeSpec.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 5D);
        String expectedResult = "Kg";
        Object result = fridgeSpec.getAttributeUnit(FridgeSpec.FREEZER_CAPACITY);
        assertEquals(expectedResult, result);
    }

    @Test
    void setAttributeValueTestCapacity() {
        FridgeSpec fridgeSpec = new FridgeSpec();
        fridgeSpec.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 1D);
        fridgeSpec.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 5.0);
        Object result = fridgeSpec.getAttributeValue(FridgeSpec.FREEZER_CAPACITY);
        assertEquals(5.0, result);
    }

    @Test
    void setAttributeValueTestDefault() {
        FridgeSpec fridgeSpec = new FridgeSpec();
        fridgeSpec.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 1D);
        fridgeSpec.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 5.0);
        Object result = fridgeSpec.getAttributeValue("lisbon");
        assertEquals(0, result);
    }

    @Test
    void setAttributeValueTestFalseAgain() {
        FridgeSpec fridgeSpec = new FridgeSpec();
        fridgeSpec.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 1D);
        Object result = fridgeSpec.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 5);
        assertEquals(false, result);
    }


    @Test
    void testSetAttributeValueCoveringAllCases() {
        //Arrange
        FridgeSpec fridgeSpec = new FridgeSpec();
        Double attribute = 6.0;
        // original strings:
        assertTrue(fridgeSpec.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, attribute));
        // same hash codes, but different strings:
        assertFalse(fridgeSpec.setAttributeValue("notCAPACITY", attribute));
        // distinct hash code to cover default cases of switches
        assertFalse(fridgeSpec.setAttributeValue("", attribute));
    }

    @Test
    void seeIfGetAttributeUnitWorksInAllCases() {
        //Arrange
        FridgeSpec fridgeSpec = new FridgeSpec();
        String attributeKg = "Kg";
        // original strings:
        assertEquals(attributeKg, fridgeSpec.getAttributeUnit(FridgeSpec.FREEZER_CAPACITY));
        // same hash codes, but different strings:
        assertEquals(0, fridgeSpec.getAttributeUnit("notCAPACITY"));
        // distinct hash code to cover default cases of switches
        assertEquals(0, fridgeSpec.getAttributeUnit(""));
    }

}