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

        List<String> expectedResult = new ArrayList<>();
        expectedResult.add(FridgeSpec.FREEZER_CAPACITY);
        expectedResult.add(FridgeSpec.REFRIGERATOR_CAPACITY);
        expectedResult.add(FridgeSpec.ANNUAL_CONSUMPTION);

        // Act

        List<String> result = validFridgeSpec.getAttributeNames();

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAttributeValuesWorksCapacity() {
        // Arrange

        validFridgeSpec.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 1D);
        Double expectedResult = 1.0;

        // Act

        Object result = validFridgeSpec.getAttributeValue(FridgeSpec.FREEZER_CAPACITY);

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAttributeValuesWorksWrongName() {
        // Arrange

        validFridgeSpec.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 1D);
        int expectedResult = 0;


        // Act

        Object actualResult = validFridgeSpec.getAttributeValue("Pressure");

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSetAttributeValueWorksWrongAttributeName() {
        // Act

        boolean result = validFridgeSpec.setAttributeValue("Lisbon", 12);

        // Assert

        assertFalse(result);
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

        // Assert

        assertEquals(false, result);
    }

    @Test
    void seeIfGetAttributeUnitWorksInAllCases() {
        //Arrange

        String expectedResult = "Kg";

        // Happy case.

        assertEquals(expectedResult, validFridgeSpec.getAttributeUnit(FridgeSpec.FREEZER_CAPACITY));

        // Wrong Attribute name.

        assertEquals(0, validFridgeSpec.getAttributeUnit("notCAPACITY"));

        // Empty attribute name.

        assertEquals(0, validFridgeSpec.getAttributeUnit(""));
    }
}