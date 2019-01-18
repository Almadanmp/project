package pt.ipp.isep.dei.project.model.device.devicetypes;

import org.junit.jupiter.api.Test;

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
        DeviceType expectedResult = DeviceType.FRIDGE;
        DeviceType result = fridge.getType();
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
    void seeIfGetFreezerCapacity() {
        Fridge fridge = new Fridge(4, 5, 1);
        double expectedResult = 3;
        fridge.setFreezerCapacity(3);
        double result = fridge.getFreezerCapacity();
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetRefrigeratorCapacity() {
        Fridge fridge = new Fridge(4, 5, 1);
        double expectedResult = 3;
        fridge.setRefrigeratorCapacity(3);
        double result = fridge.getRefrigeratorCapacity();
        assertEquals(expectedResult, result);
    }


    @Test
    void getAttributeValuesTest() {
        Fridge fridge = new Fridge(4, 5, 1);
        double expectedResult = 4.0;
        Object result = fridge.getAttributeValue("freezerCapacity");
        assertEquals(expectedResult, result);
    }

    @Test
    void setAttributeValueTest() {
        Fridge fridge = new Fridge(4, 5, 1);
        boolean result = fridge.setAttributeValue("lisboa", 12);
        assertEquals(false, result);
    }

    @Test
    void seeIfGetAttributeNamesFreezer() {
        Fridge fridge = new Fridge(4, 5, 6);
        double expectedResult = 4;
        assertEquals(expectedResult, fridge.getAttributeValue("freezerCapacity"));
    }

    @Test
    void seeIfGetAttributeNamesRefrigerator() {
        Fridge fridge = new Fridge(4, 5, 6);
        double expectedResult = 5;
        assertEquals(expectedResult, fridge.getAttributeValue("refrigeratorCapacity"));
    }

    @Test
    void seeIfGetAttributeNamesAnnual() {
        Fridge fridge = new Fridge(4, 5, 6);
        double expectedResult = 6;
        assertEquals(expectedResult, fridge.getAttributeValue("annualEnergyConsumption"));
    }

    @Test
    void seeIfSetAttributeValuesTest() {
        Fridge fridge = new Fridge(4, 5, 1);
        Object result = fridge.setAttributeValue("freezerCapacity", fridge);
        assertEquals(false, result);
    }

    @Test
    void seeIfGetAttributeNamesTest() {
        Fridge fridge = new Fridge(4, 5, 1);
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("freezerCapacity");
        expectedResult.add("refrigeratorCapacity");
        expectedResult.add("annualEnergyConsumption");
        List<String> result = fridge.getAttributeNames();
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAttributeValuesTest() {
        Fridge fridge = new Fridge(4, 5, 1);
        double expectedResult = 4;
        Object result = fridge.getAttributeValue("freezerCapacity");
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAttributeValuesTest2() {
        Fridge fridge = new Fridge(4, 5, 1);
        int expectedResult = 0;
        Object result = fridge.getAttributeValue("no");
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAndSetAttributeValue() {
        Fridge fridge = new Fridge(4, 5, 1);
        String attribute = "freezerCapacity";
        Double expectedResult = 6.0;
        boolean setResult = fridge.setAttributeValue(attribute, 6.0);
        Object getResult = fridge.getAttributeValue(attribute);
        assertEquals(expectedResult, getResult);
        assertTrue(setResult);
    }

    @Test
    void seeIfGetAndSetAttributeValue2() {
        Fridge fridge = new Fridge(4, 5, 1);
        String attribute = "refrigeratorCapacity";
        Double expectedResult = 6.0;
        boolean setResult = fridge.setAttributeValue(attribute, 6.0);
        Object getResult = fridge.getAttributeValue(attribute);
        assertEquals(expectedResult, getResult);
        assertTrue(setResult);
    }

    @Test
    void seeIfGetAndSetAttributeValue3() {
        Fridge fridge = new Fridge(4, 5, 1);
        String attribute = "annualEnergyConsumption";
        Double expectedResult = 6.0;
        boolean setResult = fridge.setAttributeValue(attribute, 6.0);
        Object getResult = fridge.getAttributeValue(attribute);
        assertEquals(expectedResult, getResult);
        assertTrue(setResult);
    }

    @Test
    void seeIfSetAttributeValueInvalid() {
        Fridge fridge = new Fridge(4, 5, 1);
        String attribute = "invalid";
        boolean result = fridge.setAttributeValue(attribute, 6);
        assertFalse(result);
    }

    @Test
    void seeIfSetAttributeValueInvalid2() {
        Fridge fridge = new Fridge(4, 5, 1);
        String attribute = "freezerCapacity";
        boolean result = fridge.setAttributeValue(attribute, 6);
        assertFalse(result);
    }

    @Test
    void seeIfSetAttributeValueInvalid3() {
        Fridge fridge = new Fridge(4, 5, 1);
        String attribute = "refrigeratorCapacity";
        boolean result = fridge.setAttributeValue(attribute, 6);
        assertFalse(result);
    }

    @Test
    void seeIfSetAttributeValueInvalid4() {
        Fridge fridge = new Fridge(4, 5, 1);
        String attribute = "annualEnergyConsumption";
        boolean result = fridge.setAttributeValue(attribute, 6);
        assertFalse(result);
    }

    @Test
    void seeIfSetAttributeValueInvalid5() {
        Fridge fridge = new Fridge(4, 5, 1);
        String attribute = "freezerCapacity";
        boolean result = fridge.setAttributeValue(attribute, "ljlkhg");
        assertFalse(result);
    }

    @Test
    void seeIfSetAttributeValueInvalid6() {
        Fridge fridge = new Fridge(4, 5, 1);
        String attribute = "refrigeratorCapacity";
        boolean result = fridge.setAttributeValue(attribute, "ljlkhg");
        assertFalse(result);
    }

    @Test
    void seeIfSetAttributeValueInvalid7() {
        Fridge fridge = new Fridge(4, 5, 1);
        String attribute = "annualEnergyConsumption";
        boolean result = fridge.setAttributeValue(attribute, "ljlkhg");
        assertFalse(result);
    }

    @Test
    void seeIfGetObjectAttributeValueTestWorks() {
        //Arrange
        Fridge fridge = new Fridge(4, 5, 6);
        //Act
        double expectedResult1 = 4;
        double expectedResult2 = 5;
        double expectedResult3 = 6;
        Object actualResult1 = fridge.getAttributeValue("freezerCapacity");
        Object actualResult2 = fridge.getAttributeValue("refrigeratorCapacity");
        Object actualResult3 = fridge.getAttributeValue("annualEnergyConsumption");
        //Assert
        assertEquals(expectedResult1, actualResult1);
        assertEquals(expectedResult2, actualResult2);
        assertEquals(expectedResult3, actualResult3);
    }


    @Test
    public void itShouldThrowNullPointerExceptionWhenSetAttribute() {
        assertThrows(NullPointerException.class,
                () -> {
                    //Arrange
                    Fridge fridge = new Fridge(Double.NaN, Double.NaN,Double.NaN);
                    //Act
                   Object expectedResult1 = null;
                    Object expectedResult2 = null;
                    Object expectedResult3 = null;
                    Object actualResult1 = fridge.getAttributeValue(null);
                    Object actualResult2 = fridge.getAttributeValue(null);
                    Object actualResult3 = fridge.getAttributeValue(null);
                    //Assert
                    assertEquals(expectedResult1, actualResult1);
                    assertEquals(expectedResult2, actualResult2);
                    assertEquals(expectedResult3, actualResult3);
                    //do whatever you want to do here
                    //ex : objectName.thisMethodShoulThrowNullPointerExceptionForNullParameter(null);
                });
    }
}
