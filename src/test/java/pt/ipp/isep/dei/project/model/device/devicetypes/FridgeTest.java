package pt.ipp.isep.dei.project.model.device.devicetypes;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.TestUtils;
import pt.ipp.isep.dei.project.model.device.deviceSpecs.Fridge;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Fridge tests class.
 */

class FridgeTest {

    @Test
    void getTypeTest() {
        Fridge fridge = new Fridge();
        String expectedResult = "Fridge";
        String result = fridge.getType();
        assertEquals(expectedResult, result);
    }

    @Test
    void getConsumptionTest() {
        Fridge fridge = new Fridge();
        double expectedResult = 0;
        double result = fridge.getConsumption();
        assertEquals(expectedResult, result);
    }

    @Test
    void getAttributeValuesTest() {
        Fridge fridge = new Fridge();
        fridge.setAttributeValue(TestUtils.F_FREEZER_CAPACITY, 4D);
        double expectedResult = 4.0;
        Object result = fridge.getAttributeValue("Freezer Capacity");
        assertEquals(expectedResult, result);
    }

    @Test
    void setAttributeValueTest() {
        Fridge fridge = new Fridge();
        boolean result = fridge.setAttributeValue("lisboa", 12D);
        assertEquals(false, result);
    }

    @Test
    void seeIfGetAttributeNamesFreezer() {
        Fridge fridge = new Fridge();
        fridge.setAttributeValue(TestUtils.F_FREEZER_CAPACITY, 4D);
        double expectedResult = 4;
        assertEquals(expectedResult, fridge.getAttributeValue("Freezer Capacity"));
    }

    @Test
    void seeIfGetAttributeNamesRefrigerator() {
        Fridge fridge = new Fridge();
        fridge.setAttributeValue(TestUtils.F_REFRIGERATOR_CAPACITY, 5D);
        double expectedResult = 5;
        assertEquals(expectedResult, fridge.getAttributeValue(TestUtils.F_REFRIGERATOR_CAPACITY));
    }

    @Test
    void seeIfGetAttributeNamesAnnual() {
        Fridge fridge = new Fridge();
        fridge.setAttributeValue(TestUtils.F_ANNUAL_CONSUMPTION, 6D);
        double expectedResult = 6;
        assertEquals(expectedResult, fridge.getAttributeValue("Annual Energy Consumption"));
    }

    @Test
    void seeIfSetAttributeValuesTest() {
        Fridge fridge = new Fridge();
        Object result = fridge.setAttributeValue("Freezer Capacity", fridge);
        assertEquals(false, result);
    }

    @Test
    void seeIfGetAttributeNamesTest() {
        Fridge fridge = new Fridge();
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add(Fridge.FREEZER_CAPACITY);
        expectedResult.add(Fridge.REFRIGERATOR_CAPACITY);
        expectedResult.add(Fridge.ANNUAL_CONSUMPTION);
        expectedResult.add(Fridge.NOMINAL_POWER);
        List<String> result = fridge.getAttributeNames();
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAttributeValuesTest() {
        Fridge fridge = new Fridge();
        double expectedResult = 4;
        fridge.setAttributeValue(TestUtils.F_FREEZER_CAPACITY, 4D);
        Object result = fridge.getAttributeValue("Freezer Capacity");
        assertEquals(expectedResult, result);
    }
    @Test
    void seeIfGetAttributeUnitTest() {
        Fridge fridge = new Fridge();
        String expectedResult = "Kg";
        Object result = fridge.getAttributeUnit("Freezer Capacity");
        assertEquals(expectedResult, result);
    }
    @Test
    void seeIfGetAttributeUnitTest2() {
        Fridge fridge = new Fridge();
        String expectedResult = "Kg";
        Object result = fridge.getAttributeUnit("Refrigerator Capacity");
        assertEquals(expectedResult, result);
        assertEquals(0, fridge.getAttributeUnit(""));

    }

    @Test
    void seeIfGetAttributeValuesTest2() {
        Fridge fridge = new Fridge();
        int expectedResult = 0;
        Object result = fridge.getAttributeValue("no");
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAndSetAttributeValue() {
        Fridge fridge = new Fridge();
        String attribute = "Freezer Capacity";
        fridge.setAttributeValue(attribute, 6);
        Double expectedResult = 6.0;
        boolean setResult = fridge.setAttributeValue(attribute, 6.0);
        Object getResult = fridge.getAttributeValue(attribute);
        assertEquals(expectedResult, getResult);
        assertTrue(setResult);
    }

    @Test
    void seeIfGetAndSetAttributeValue2() {
        Fridge fridge = new Fridge();
        String attribute = "Refrigerator Capacity";
        Double expectedResult = 6.0;
        fridge.setAttributeValue(TestUtils.F_REFRIGERATOR_CAPACITY, 6);
        boolean setResult = fridge.setAttributeValue(attribute, 6.0);
        Object getResult = fridge.getAttributeValue(attribute);
        assertEquals(expectedResult, getResult);
        assertTrue(setResult);
    }

    @Test
    void seeIfGetAndSetAttributeValue3() {
        Fridge fridge = new Fridge();
        String attribute = "Annual Energy Consumption";
        fridge.setAttributeValue(TestUtils.F_ANNUAL_CONSUMPTION, 6);
        Double expectedResult = 6.0;
        boolean setResult = fridge.setAttributeValue(attribute, 6.0);
        Object getResult = fridge.getAttributeValue(attribute);
        assertEquals(expectedResult, getResult);
        assertTrue(setResult);
    }

    @Test
    void seeIfSetAttributeValueInvalid() {
        Fridge fridge = new Fridge();
        String attribute = "invalid";
        boolean result = fridge.setAttributeValue(attribute, 6);
        assertFalse(result);
    }

    @Test
    void seeIfSetAttributeValueInvalid2() {
        Fridge fridge = new Fridge();
        String attribute = "freezerCapacity";
        boolean result = fridge.setAttributeValue(attribute, 6);
        assertFalse(result);
    }

    @Test
    void seeIfSetAttributeValueInvalid3() {
        Fridge fridge = new Fridge();
        String attribute = "refrigeratorCapacity";
        boolean result = fridge.setAttributeValue(attribute, 6);
        assertFalse(result);
    }

    @Test
    void seeIfSetAttributeValueInvalid4() {
        Fridge fridge = new Fridge();
        String attribute = "annualEnergyConsumption";
        boolean result = fridge.setAttributeValue(attribute, 6);
        assertFalse(result);
    }

    @Test
    void seeIfSetAttributeValueInvalid5() {
        Fridge fridge = new Fridge();
        String attribute = "freezerCapacity";
        boolean result = fridge.setAttributeValue(attribute, "ljlkhg");
        assertFalse(result);
    }

    @Test
    void seeIfSetAttributeValueInvalid6() {
        Fridge fridge = new Fridge();
        String attribute = "refrigeratorCapacity";
        boolean result = fridge.setAttributeValue(attribute, "ljlkhg");
        assertFalse(result);
    }

    @Test
    void seeIfSetAttributeValueInvalid7() {
        Fridge fridge = new Fridge();
        String attribute = "annualEnergyConsumption";
        boolean result = fridge.setAttributeValue(attribute, "ljlkhg");
        assertFalse(result);
    }

    @Test
    void seeIfGetObjectAttributeValueTestWorks() {
        //Arrange
        Fridge fridge = new Fridge();
        fridge.setAttributeValue(TestUtils.F_FREEZER_CAPACITY, 4D);
        fridge.setAttributeValue(TestUtils.F_REFRIGERATOR_CAPACITY, 5D);
        fridge.setAttributeValue(TestUtils.F_ANNUAL_CONSUMPTION, 6D);
        //Act
        double expectedResult1 = 4;
        double expectedResult2 = 5;
        double expectedResult3 = 6;
        Object actualResult1 = fridge.getAttributeValue("Freezer Capacity");
        Object actualResult2 = fridge.getAttributeValue("Refrigerator Capacity");
        Object actualResult3 = fridge.getAttributeValue("Annual Energy Consumption");
        //Assert
        assertEquals(expectedResult1, actualResult1);
        assertEquals(expectedResult2, actualResult2);
        assertEquals(expectedResult3, actualResult3);
    }

    @Test
    void testGetAttributeCoveringAllCases() {
        //Arrange
        Fridge fridge = new Fridge();
        fridge.setAttributeValue(TestUtils.F_FREEZER_CAPACITY, 5D);
        fridge.setAttributeValue(TestUtils.F_REFRIGERATOR_CAPACITY, 5D);
        fridge.setAttributeValue(TestUtils.F_ANNUAL_CONSUMPTION, 5D);

        // original strings:
        assertEquals(5.0, fridge.getAttributeValue("Freezer Capacity"));
        assertEquals(5.0, fridge.getAttributeValue("Refrigerator Capacity"));
        assertEquals(5.0, fridge.getAttributeValue("Annual Energy Consumption"));

        // same hash codes, but different strings:
        assertEquals(0, fridge.getAttributeValue("\0Freezer Capacity"));
        assertEquals(0, fridge.getAttributeValue("\0Refrigerator Capacity"));
        assertEquals(0, fridge.getAttributeValue("\0Annual Energy Consumption"));

        // distinct hash code to cover default cases of switches
        assertEquals(0, fridge.getAttributeValue(""));
    }

    @Test
    void testSetAttributeCoveringAllCases() {
        //Arrange
        Fridge fridge = new Fridge();
        Double attribute = 6.0;

        // original strings:
        assertTrue(fridge.setAttributeValue("Freezer Capacity", attribute));
        assertTrue(fridge.setAttributeValue("Refrigerator Capacity", attribute));
        assertTrue(fridge.setAttributeValue("Annual Energy Consumption", attribute));

        // same hash codes, but different strings:
        assertFalse(fridge.setAttributeValue("\0Freezer Capacity", attribute));
        assertFalse(fridge.setAttributeValue("\0Refrigerator Capacity", attribute));
        assertFalse(fridge.setAttributeValue("\0Annual Energy Consumption", attribute));

        // distinct hash code to cover default cases of switches
        assertFalse(fridge.setAttributeValue("", attribute));
    }

}
