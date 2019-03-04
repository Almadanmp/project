package pt.ipp.isep.dei.project.model.device.devicespecs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.device.WineCooler;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.testng.Assert.*;

public class WineCoolerSpecTest {
    private WineCooler wineCoolerValid = new WineCooler(new WineCoolerSpec());

    @BeforeEach
    void arrangeArtifacts() {
        wineCoolerValid.setName("Wine Cooler");
        wineCoolerValid.setNominalPower(15);
        wineCoolerValid.setAnnualConsumption(3650);
        wineCoolerValid.setAttributeValue("Number of Bottles", 15.0);}


    @Test
    void seeIfGetAttributeNamesTest() {
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("Number of Bottles");
        List<String> actualResult = wineCoolerValid.getAttributeNames();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSetAttributeValueTestFalse() {
        boolean result = wineCoolerValid.setAttributeValue("Flux", 12);
        assertFalse(result);
    }

    @Test
    void seeIfSetAttributeValueTestTrueFalse() {
        Double attribute = 6.0;
        boolean actualResult = wineCoolerValid.setAttributeValue("Number of Bottles", 12.0);
        boolean actualResultDouble = wineCoolerValid.setAttributeValue(WineCoolerSpec.NUMBER_BOTTLES, 5);
        assertTrue(wineCoolerValid.setAttributeValue(WineCoolerSpec.NUMBER_BOTTLES, attribute));
        assertFalse(wineCoolerValid.setAttributeValue("notFLUX", attribute));
        assertFalse(wineCoolerValid.setAttributeValue("", attribute));
        assertFalse(wineCoolerValid.setAttributeValue(null, attribute));
        assertFalse(actualResultDouble);
        assertTrue(actualResult);
    }

    @Test
    void seeIfGetObjectAttributeValueTest() {
       Double expectedResult = 15.0;
        Object actualResult = wineCoolerValid.getAttributeValue(WineCoolerSpec.NUMBER_BOTTLES);
        Object actualResultFalse = wineCoolerValid.getAttributeValue("flux");
        Object actualResultEmpty = wineCoolerValid.getAttributeValue("");
        assertEquals(expectedResult, actualResult);
        assertEquals(false, actualResultFalse);
        assertEquals(false, actualResultEmpty);
    }

    @Test
    void seeIfGetObjectAttributeUnitTest() {
        String expectedResult = "bottles";
        Object actualResult = wineCoolerValid.getAttributeUnit(WineCoolerSpec.NUMBER_BOTTLES);
        Object actualResultFalse = wineCoolerValid.getAttributeUnit("flux");
        Object actualResultEmpty = wineCoolerValid.getAttributeUnit("");
        assertEquals(expectedResult, actualResult);
        assertEquals(false, actualResultFalse);
        assertEquals(false, actualResultEmpty);

    }

    @Test
    void setAttributeValueTestTrueBottle() {
        WineCoolerSpec spec = new WineCoolerSpec();
        spec.setAttributeValue(WineCoolerSpec.NUMBER_BOTTLES, 5.0);
        Object result = spec.getAttributeValue("Number of Bottles");
        assertEquals(5.0, result);
    }


    @Test
    void testSetAttributeValueForNotInteger() {
       Integer attributeD = 6;
        wineCoolerValid.setAttributeValue(WineCoolerSpec.NUMBER_BOTTLES, attributeD);
        assertFalse(wineCoolerValid.setAttributeValue(WineCoolerSpec.NUMBER_BOTTLES, attributeD));
        assertFalse(wineCoolerValid.setAttributeValue("notFLUX", attributeD));
        assertFalse(wineCoolerValid.setAttributeValue("notNOMINAL_POWER", attributeD));
        assertFalse(wineCoolerValid.setAttributeValue("", attributeD));
    }
}