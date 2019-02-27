package pt.ipp.isep.dei.project.model.device.devicespecs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.device.Freezer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.testng.Assert.*;

public class FreezerSpecTest {
    private Freezer freezerValid = new Freezer(new FreezerSpec());

    @BeforeEach
    void arrangeArtifacts() {
        freezerValid.setName("Freezer");
        freezerValid.setNominalPower(30);
        freezerValid.setAnnualConsumption(3650);
        freezerValid.setAttributeValue("Capacity", 15.0);}


    @Test
    void seeIfGetAttributeNamesTest() {
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("Capacity");
        List<String> actualResult = freezerValid.getAttributeNames();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSetAttributeValueTestFalse() {
        boolean result = freezerValid.setAttributeValue("Bottle", 12.0);
        assertFalse(result);
    }

    @Test
    void seeIfSetAttributeValueTestTrueFalse() {
        Double attribute = 16.0;
        boolean actualResult = freezerValid.setAttributeValue("Capacity", 16.0);
        boolean actualResultDouble = freezerValid.setAttributeValue(FreezerSpec.CAPACITY, 16);
        assertTrue(freezerValid.setAttributeValue(FreezerSpec.CAPACITY, attribute));
        assertFalse(freezerValid.setAttributeValue("notBottle", attribute));
        assertFalse(freezerValid.setAttributeValue("", attribute));
        assertFalse(freezerValid.setAttributeValue(null, attribute));
        assertFalse(actualResultDouble);
        assertTrue(actualResult);
    }

    @Test
    void seeIfGetObjectAttributeValueTest() {
        Double expectedResult = 15.0;
        Object actualResult = freezerValid.getAttributeValue(FreezerSpec.CAPACITY);
        Object actualResultFalse = freezerValid.getAttributeValue("bottle");
        Object actualResultEmpty = freezerValid.getAttributeValue("");
        assertEquals(expectedResult, actualResult);
        assertEquals(false, actualResultFalse);
        assertEquals(false, actualResultEmpty);
    }

    @Test
    void seeIfGetObjectAttributeUnitTest() {
        String expectedResult = "l";
        Object actualResult = freezerValid.getAttributeUnit(FreezerSpec.CAPACITY);
        Object actualResultFalse = freezerValid.getAttributeUnit("bottle");
        Object actualResultEmpty = freezerValid.getAttributeUnit("");
        assertEquals(expectedResult, actualResult);
        assertEquals(false, actualResultFalse);
        assertEquals(false, actualResultEmpty);

    }

    @Test
    void setAttributeValueTestTrueBottle() {
        FreezerSpec spec = new FreezerSpec();
        spec.setAttributeValue(FreezerSpec.CAPACITY, 5.0);
        Object result = spec.getAttributeValue("Capacity");
        assertEquals(5.0, result);
    }


    @Test
    void testSetAttributeValueForNotInteger() {
        Integer attributeD = 6;
        freezerValid.setAttributeValue(FreezerSpec.CAPACITY, attributeD);
        assertFalse(freezerValid.setAttributeValue(FreezerSpec.CAPACITY, attributeD));
        assertFalse(freezerValid.setAttributeValue("notBottle", attributeD));
        assertFalse(freezerValid.setAttributeValue("notNOMINAL_POWER", attributeD));
        assertFalse(freezerValid.setAttributeValue("", attributeD));
    }
}