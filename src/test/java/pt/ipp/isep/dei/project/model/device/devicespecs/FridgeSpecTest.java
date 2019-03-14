package pt.ipp.isep.dei.project.model.device.devicespecs;

import org.junit.jupiter.api.BeforeEach;
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
    private FridgeSpec validFridgeSpec;

    @BeforeEach
    void arrangeArtifacts(){
        validFridgeSpec = new FridgeSpec();
    }

    @Test
    void seeIfGetAttributeNamesWorks() {
        // Arrange

        List<String> expectedResult1 = new ArrayList<>();
        expectedResult1.add("Freezer Capacity");
        expectedResult1.add("Refrigerator Capacity");
        expectedResult1.add("Annual Energy Consumption");

        // Act

        List<String> actualResult = validFridgeSpec.getAttributeNames();

        // Assert

        assertEquals(expectedResult1, actualResult);
    }

    @Test
    void seeIfGetAttributeValuesWorksCapacity() {
        // Arrange

        validFridgeSpec.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 1D);
        validFridgeSpec.setAttributeValue(FridgeSpec.REFRIGERATOR_CAPACITY, 1D);
        validFridgeSpec.setAttributeValue(FridgeSpec.ANNUAL_CONSUMPTION, 1D);
        Double expectedResult = 1.0;

        // Act

        Object result = validFridgeSpec.getAttributeValue(FridgeSpec.FREEZER_CAPACITY);
        Object result1 = validFridgeSpec.getAttributeValue(FridgeSpec.REFRIGERATOR_CAPACITY);
        Object result2 = validFridgeSpec.getAttributeValue(FridgeSpec.ANNUAL_CONSUMPTION);

        // Assert

        assertEquals(expectedResult, result);
        assertEquals(expectedResult, result1);
        assertEquals(expectedResult, result2);
    }

    @Test
    void seeIfGetAttributeValuesWorksWrongName() {
        // Arrange

        validFridgeSpec.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 1D);

        int expectedResult = 0;

        // Act

        Object actualResult = validFridgeSpec.getAttributeValue("Pressure");
        boolean actualResult2 = validFridgeSpec.setAttributeValue(FridgeSpec.REFRIGERATOR_CAPACITY, 1D);
        boolean actualResult3 = validFridgeSpec.setAttributeValue(FridgeSpec.ANNUAL_CONSUMPTION, 1D);

        // Assert

        assertEquals(expectedResult, actualResult);
        assertTrue(actualResult2);
        assertTrue(actualResult3);
    }

    @Test
    void seeIfSetAttributeValueWorksWrongAttributeName() {
        // Act

        boolean result = validFridgeSpec.setAttributeValue("Lisbon", 12);
        boolean actualResult1 = this.validFridgeSpec.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, "invalid type");
        boolean actualResult2 = this.validFridgeSpec.setAttributeValue(FridgeSpec.REFRIGERATOR_CAPACITY, "invalid type");
        boolean actualResult3 = this.validFridgeSpec.setAttributeValue(FridgeSpec.ANNUAL_CONSUMPTION, "invalid type");

        // Assert

        assertFalse(result);
        assertFalse(actualResult1);
        assertFalse(actualResult2);
        assertFalse(actualResult3);
    }

    @Test
    void seeIfSetAttributeValueWorks() {
        // Act

        boolean actualResult = validFridgeSpec.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 12.0);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfGetAttributeUnitWorks() {
        // Arrange

        String expectedResult = "Kg";

        // Act

        Object result = validFridgeSpec.getAttributeUnit(FridgeSpec.FREEZER_CAPACITY);

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAttributeValueWorksWrongName() {
        // Act

        Object result = validFridgeSpec.getAttributeValue("lisbon");

        // Assert

        assertEquals(0, result);
    }

    @Test
    void seeIfSetAttributeValueWorksFalseNotDouble() {
        // Act

        Object result = validFridgeSpec.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 5);
        Object result1 = validFridgeSpec.setAttributeValue(FridgeSpec.REFRIGERATOR_CAPACITY, 5);
        Object result2 = validFridgeSpec.setAttributeValue(FridgeSpec.ANNUAL_CONSUMPTION, 5);

        // Assert

        assertEquals(false, result);
        assertEquals(false, result1);
        assertEquals(false, result2);
    }

    @Test
    void seeIfGetAttributeUnitWorksInAllCases() {
        //Arrange

        String expectedResult = "Kg";
        String expectedResult1 = "Kg";
        String expectedResult2 = "kWh";

        // Happy case.

        assertEquals(expectedResult, validFridgeSpec.getAttributeUnit(FridgeSpec.FREEZER_CAPACITY));
        assertEquals(expectedResult1, validFridgeSpec.getAttributeUnit(FridgeSpec.REFRIGERATOR_CAPACITY));
        assertEquals(expectedResult2, validFridgeSpec.getAttributeUnit(FridgeSpec.ANNUAL_CONSUMPTION));

        // Wrong Attribute name.

        assertEquals(0, validFridgeSpec.getAttributeUnit("notCAPACITY"));

        // Empty attribute name.

        assertEquals(0, validFridgeSpec.getAttributeUnit(""));
    }

    @Test
    void seeIfGetAttributeValueUnitWorks1() {
        // Happy Cases

        validFridgeSpec.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 5D);
        validFridgeSpec.setAttributeValue(FridgeSpec.REFRIGERATOR_CAPACITY, 5D);
        validFridgeSpec.setAttributeValue(FridgeSpec.ANNUAL_CONSUMPTION, 5D);

        assertEquals(5.0, validFridgeSpec.getAttributeValue(FridgeSpec.FREEZER_CAPACITY));
        assertEquals(5.0, validFridgeSpec.getAttributeValue(FridgeSpec.REFRIGERATOR_CAPACITY));
        assertEquals(5.0, validFridgeSpec.getAttributeValue(FridgeSpec.ANNUAL_CONSUMPTION));

        assertEquals("Kg", validFridgeSpec.getAttributeUnit("Freezer Capacity"));
        assertEquals("Kg", validFridgeSpec.getAttributeUnit("Refrigerator Capacity"));
        assertEquals("kWh", validFridgeSpec.getAttributeUnit("Annual Energy Consumption"));

        // Wrong attribute Names.

        assertEquals(0, validFridgeSpec.getAttributeValue("\0Freezer Capacity"));
        assertEquals(0, validFridgeSpec.getAttributeValue("\0Refrigerator Capacity"));
        assertEquals(0, validFridgeSpec.getAttributeValue("\0Annual Energy Consumptionn"));

        assertEquals(0, validFridgeSpec.getAttributeUnit("\0Freezer Capacity"));
        assertEquals(0, validFridgeSpec.getAttributeUnit("\0Refrigerator Capacity"));
        assertEquals(0, validFridgeSpec.getAttributeUnit("\0Annual Energy Consumption"));

        // Empty attribute name.

        assertEquals(0, validFridgeSpec.getAttributeValue(" "));
        assertEquals(0, validFridgeSpec.getAttributeUnit(" "));
        assertEquals(0, validFridgeSpec.getAttributeValue(""));
        assertEquals(0, validFridgeSpec.getAttributeUnit(""));


        // Wrong sets

        assertFalse(validFridgeSpec.setAttributeValue("Freezer Capacity", ""));
        assertFalse(validFridgeSpec.setAttributeValue("Refrigerator Capacity", ""));
        assertFalse(validFridgeSpec.setAttributeValue("Annual Energy Consumption", ""));

        assertFalse(validFridgeSpec.setAttributeValue(" ", ""));
        assertFalse(validFridgeSpec.setAttributeValue(" ", ""));
        assertFalse(validFridgeSpec.setAttributeValue(" ", ""));

        assertFalse(validFridgeSpec.setAttributeValue(" ", 5D));
        assertFalse(validFridgeSpec.setAttributeValue(" ", 5D));
        assertFalse(validFridgeSpec.setAttributeValue(" ", 5D));

        assertFalse(validFridgeSpec.setAttributeValue("\0" + FridgeSpec.FREEZER_CAPACITY, 5D));
        assertFalse(validFridgeSpec.setAttributeValue("\0" + FridgeSpec.REFRIGERATOR_CAPACITY, 5D));
        assertFalse(validFridgeSpec.setAttributeValue("\0" + FridgeSpec.ANNUAL_CONSUMPTION, 5D));

    }

    @Test
    void seeIfGetAttributeValueUnitWorks() {
        //Arrange

        validFridgeSpec.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 10D);
        validFridgeSpec.setAttributeValue(FridgeSpec.REFRIGERATOR_CAPACITY, 10D);
        validFridgeSpec.setAttributeValue(FridgeSpec.ANNUAL_CONSUMPTION, 10D);

        // Assert

        assertEquals(10D, validFridgeSpec.getAttributeValue(FridgeSpec.FREEZER_CAPACITY));
        assertEquals(10D, validFridgeSpec.getAttributeValue(FridgeSpec.REFRIGERATOR_CAPACITY));
        assertEquals(10D, validFridgeSpec.getAttributeValue(FridgeSpec.ANNUAL_CONSUMPTION));
    }

    @Test
    void seeIfGetAttributeValueUnitWorksWithWrongStrings() {
        // Assert

        assertEquals(0, validFridgeSpec.getAttributeValue(""));
        assertEquals(0, validFridgeSpec.getAttributeValue("\0" + FridgeSpec.FREEZER_CAPACITY));
        assertEquals(0, validFridgeSpec.getAttributeValue("\0" + FridgeSpec.REFRIGERATOR_CAPACITY));
        assertEquals(0, validFridgeSpec.getAttributeValue("\0" + FridgeSpec.ANNUAL_CONSUMPTION));
    }

}