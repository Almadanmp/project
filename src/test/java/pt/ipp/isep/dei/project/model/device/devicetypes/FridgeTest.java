package pt.ipp.isep.dei.project.model.device.devicetypes;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Fridge tests class.
 */

public class FridgeTest {

    @Test
    public void getTypeTest() {
        Fridge fridge = new Fridge();
        DeviceType expectedResult = DeviceType.FRIDGE;
        DeviceType result = fridge.getType();
        assertEquals(expectedResult, result);
    }

    @Test
    public void getConsumptionTest() {
        Fridge fridge = new Fridge();
        double expectedResult = 0;
        double result = fridge.getConsumption();
        assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfGetFreezerCapacity() {
        Fridge fridge = new Fridge(4, 5, 1);
        double expectedResult = 3;
        fridge.setFreezerCapacity(3);
        double result = fridge.getFreezerCapacity();
        assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfGetRefrigeratorCapacity() {
        Fridge fridge = new Fridge(4, 5, 1);
        double expectedResult = 3;
        fridge.setRefrigeratorCapacity(3);
        double result = fridge.getRefrigeratorCapacity();
        assertEquals(expectedResult, result);
    }


    @Test
    public void getAttributeValuesTest() {
        Fridge fridge = new Fridge(4, 5, 1);
        double expectedResult = 4.0;
        Object result = fridge.getAttributeValue("freezerCapacity");
        assertEquals(expectedResult, result);
    }

    @Test
    public void setAttributeValueTest() {
        Fridge fridge = new Fridge(4, 5, 1);
        boolean result = fridge.setAttributeValue("lisboa", 12);
        assertEquals(false, result);
    }

    @Test
    public void seeIfGetAttributeNamesFreezer() {
        Fridge fridge = new Fridge(4, 5, 6);
        double expectedResult = 4;
        assertEquals(expectedResult, fridge.getAttributeValue("freezerCapacity"));
    }

    @Test
    public void seeIfGetAttributeNamesRefrigerator() {
        Fridge fridge = new Fridge(4, 5, 6);
        double expectedResult = 5;
        assertEquals(expectedResult, fridge.getAttributeValue("refrigeratorCapacity"));
    }
    @Test
    public void seeIfGetAttributeNamesAnnual() {
        Fridge fridge = new Fridge(4, 5, 6);
        double expectedResult = 6;
        assertEquals(expectedResult, fridge.getAttributeValue("annualEnergyConsumption"));
    }

    @Test
    public void seeIfSetAttributeValuesTest() {
        Fridge fridge = new Fridge(4, 5, 1);
        Object result = fridge.setAttributeValue("freezerCapacity", fridge);
        assertEquals(false, result);
    }

    @Test
    public void seeIfGetAttributeNamesTest() {
        Fridge fridge = new Fridge(4, 5, 1);
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("freezerCapacity");
        expectedResult.add("refrigeratorCapacity");
        expectedResult.add("annualEnergyConsumption");
        List<String> result = fridge.getAttributeNames();
        assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfGetAttributeValuesTest() {
        Fridge fridge = new Fridge(4, 5, 1);
        double expectedResult = 4;
        Object result = fridge.getAttributeValue("freezerCapacity");
        assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfGetAttributeValuesTest2() {
        Fridge fridge = new Fridge(4, 5, 1);
        int expectedResult = 0;
        Object result = fridge.getAttributeValue("no");
        assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfGetAndSetAttributeValue() {
        Fridge fridge = new Fridge(4, 5, 1);
        String attribute = "freezerCapacity";
        Double expectedResult = 6.0;
        boolean setResult = fridge.setAttributeValue(attribute, 6.0);
        Object getResult = fridge.getAttributeValue(attribute);
        assertEquals(expectedResult, getResult);
        assertTrue(setResult);
    }
    @Test
    public void seeIfGetAndSetAttributeValue2() {
        Fridge fridge = new Fridge(4, 5, 1);
        String attribute = "refrigeratorCapacity";
        Double expectedResult = 6.0;
        boolean setResult = fridge.setAttributeValue(attribute, 6.0);
        Object getResult = fridge.getAttributeValue(attribute);
        assertEquals(expectedResult, getResult);
        assertTrue(setResult);
    }

    @Test
    public void seeIfGetAndSetAttributeValue3() {
        Fridge fridge = new Fridge(4, 5, 1);
        String attribute = "annualEnergyConsumption";
        Double expectedResult = 6.0;
        boolean setResult = fridge.setAttributeValue(attribute, 6.0);
        Object getResult = fridge.getAttributeValue(attribute);
        assertEquals(expectedResult, getResult);
        assertTrue(setResult);
    }
    @Test
    public void seeIfSetAttributeValueInvalid() {
        Fridge fridge = new Fridge(4, 5, 1);
        String attribute = "invalid";
        boolean result = fridge.setAttributeValue(attribute, 6);
        assertFalse(result);
    }

    @Test
    public void seeIfSetAttributemValueInvalid2() {
        Fridge fridge = new Fridge(4, 5, 1);
        String attribute = "freezerCapacity";
        boolean result = fridge.setAttributeValue(attribute, 6);
        assertFalse(result);
    }
    @Test
    public void seeIfSetAttributemValueInvalid3() {
        Fridge fridge = new Fridge(4, 5, 1);
        String attribute = "refrigeratorCapacity";
        boolean result = fridge.setAttributeValue(attribute, 6);
        assertFalse(result);
    }
    @Test
    public void seeIfSetAttributemValueInvalid4() {
        Fridge fridge = new Fridge(4, 5, 1);
        String attribute = "annualEnergyConsumption";
        boolean result = fridge.setAttributeValue(attribute, 6);
        assertFalse(result);
    } @Test
    public void seeIfSetAttributemValueInvalid5() {
        Fridge fridge = new Fridge(4, 5, 1);
        String attribute = "freezerCapacity";
        boolean result = fridge.setAttributeValue(attribute, "ljlkhg");
        assertFalse(result);
    }
    @Test
    public void seeIfSetAttributemValueInvalid6() {
        Fridge fridge = new Fridge(4, 5, 1);
        String attribute = "refrigeratorCapacity";
        boolean result = fridge.setAttributeValue(attribute, "ljlkhg");
        assertFalse(result);
    }
    @Test
    public void seeIfSetAttributemValueInvalid7() {
        Fridge fridge = new Fridge(4, 5, 1);
        String attribute = "annualEnergyConsumption";
        boolean result = fridge.setAttributeValue(attribute, "ljlkhg");
        assertFalse(result);
    }

}
