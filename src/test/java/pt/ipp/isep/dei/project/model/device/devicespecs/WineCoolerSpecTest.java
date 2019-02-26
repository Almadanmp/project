package pt.ipp.isep.dei.project.model.device.devicespecs;

import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.testng.Assert.*;

public class WineCoolerSpecTest {

    @Test
    void seeIfGetAttributeNamesTest() {
        WineCoolerSpec spec = new WineCoolerSpec();
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("Number Bottles");
        List<String> result = spec.getAttributeNames();
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfSetAttributeValueTestFalse() {
        WineCoolerSpec spec = new WineCoolerSpec();
        boolean result = spec.setAttributeValue("Flux", 12);
        assertFalse(result);
    }

    @Test
    void seeIfSetAttributeValueTestTrue() {
        WineCoolerSpec spec = new WineCoolerSpec();
        boolean actualResult = spec.setAttributeValue("Number Bottles", 12);
        assertTrue(actualResult);
    }

    @Test
    void seeIfGetObjectAttributeValueTest() {
        WineCoolerSpec spec = new WineCoolerSpec();
        spec.setAttributeValue(WineCoolerSpec.NUMBER_BOTTLES, 4);
       Integer expectedResult = 4;
        Object result = spec.getAttributeValue(WineCoolerSpec.NUMBER_BOTTLES);
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetObjectAttributeUnitTest() {
        WineCoolerSpec spec = new WineCoolerSpec();
        String expectedResult = "bottles";
        Object result = spec.getAttributeUnit(WineCoolerSpec.NUMBER_BOTTLES);
        assertEquals(expectedResult, result);
        assertEquals(false, spec.getAttributeUnit(""));

    }


    @Test
    void seeIfGetAttributeValueTestDefault() {
        WineCoolerSpec spec = new WineCoolerSpec();
        spec.setAttributeValue("Number Bottles", 5);
        Object result = spec.getAttributeValue("flux");
        assertEquals(false, result);
    }



    @Test
    void SeeIfSetAttributeValueTestFalseBottle() {
        WineCoolerSpec spec = new WineCoolerSpec();
        Object result = spec.setAttributeValue(WineCoolerSpec.NUMBER_BOTTLES, 5.0);
        assertEquals(false, result);
    }

    @Test
    void setAttributeValueTestTrueBottle() {
        WineCoolerSpec spec = new WineCoolerSpec();
        spec.setAttributeValue(WineCoolerSpec.NUMBER_BOTTLES, 5);
        Object result = spec.getAttributeValue("Number Bottles");
        assertEquals(5, result);
    }

    @Test
    void seeIfGetAttributeValueForAllCases() {
        //Arrange
        WineCoolerSpec spec = new WineCoolerSpec();
        Integer attribute = 6;
        spec.setAttributeValue(WineCoolerSpec.NUMBER_BOTTLES, attribute);
        // original strings:
        assertEquals(attribute, spec.getAttributeValue(WineCoolerSpec.NUMBER_BOTTLES));
        // same hash codes, but different strings:
        assertEquals(false, spec.getAttributeValue("notFLUX"));
        // distinct hash code to cover default cases of switches
        assertEquals(false, spec.getAttributeValue(""));
    }

    @Test
    void seeIfGetAttributeUnitForAllCases() {
        //Arrange
        WineCoolerSpec spec = new WineCoolerSpec();
        String attributeBo = "bottles";
        // original strings:
        assertEquals(attributeBo, spec.getAttributeUnit(WineCoolerSpec.NUMBER_BOTTLES));
        // same hash codes, but different strings:
        assertEquals(false, spec.getAttributeUnit("notFLUX"));
        // distinct hash code to cover default cases of switches
        assertEquals(false, spec.getAttributeUnit(""));
    }

    @Test
    void testSetAttributeValueForAllCases() {
        //Arrange
        WineCoolerSpec spec = new WineCoolerSpec();
        Integer attribute = 6;
        // original strings + int:
        assertTrue(spec.setAttributeValue(WineCoolerSpec.NUMBER_BOTTLES, attribute));
        // same hash codes, but different strings + int:
        assertFalse(spec.setAttributeValue("notFLUX", attribute));
        // distinct hash code to cover default cases of switches + int
        assertFalse(spec.setAttributeValue("", attribute));
    }

    @Test
    void testSetAttributeValueForNotInteger() {
        //Arrange
        WineCoolerSpec spec = new WineCoolerSpec();
       Integer attributeD = 6;
        Integer attribute = 6;
        spec.setAttributeValue(LampSpec.FLUX, attributeD);
        // original strings + not int:
        assertFalse(spec.setAttributeValue(LampSpec.FLUX, attribute));
        // same hash codes, but different strings + not int:
        assertFalse(spec.setAttributeValue("notFLUX", attribute));
        assertFalse(spec.setAttributeValue("notNOMINAL_POWER", attribute));
        // distinct hash code to cover default cases of switches + not int
        assertFalse(spec.setAttributeValue("", attribute));
    }
}