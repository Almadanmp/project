package pt.ipp.isep.dei.project.model.device.devicespecs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * LampSpec tests class.
 */

class LampSpecTest {
    private LampSpec validLampSpec;

    @BeforeEach
    void arrangeArtifacts() {
        validLampSpec = new LampSpec();
    }

    @Test
    void seeIfGetAttributeNamesWorks() {
        // Arrange

        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("Luminous Flux");

        // Act

        List<String> result = validLampSpec.getAttributeNames();

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfSetAttributeValueWorksFalseWrongAttributeName() {
        // Act

        boolean result = validLampSpec.setAttributeValue("Lisbon", 12);
        boolean result1 = validLampSpec.setAttributeValue(null, 12);

        // Assert

        assertFalse(result);
        assertFalse(result1);
    }

    @Test
    void seeIfSetAttributeValueWorks() {
        // Act

        boolean actualResult = validLampSpec.setAttributeValue("Luminous Flux", 12.0);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfGetSetAttributeValueWorks() {
        // Arrange

        Double expectedResult = 4.0;
        validLampSpec.setAttributeValue(LampSpec.FLUX, 4D);

        // Act

        Object result = validLampSpec.getAttributeValue(LampSpec.FLUX);

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAttributeUnitWorks() {
        // Arrange

        String expectedResult = "lm";

        // Act

        Object result = validLampSpec.getAttributeUnit(LampSpec.FLUX);

        // Assert

        assertEquals(expectedResult, result);
        assertEquals(false, validLampSpec.getAttributeUnit(""));
    }

    @Test
    void seeIfSetAttributeValueWorksWrongNameNotDouble() {
        // Act

        Object result = validLampSpec.setAttributeValue("luminousFlux", 5);

        // Assert

        assertEquals(false, result);
    }

    @Test
    void seeIfGetAttributeValueWorksWrongName() {
        // Act

        Object result = validLampSpec.getAttributeValue("Lisbon");

        // Assert

        assertEquals(false, result);
    }

    @Test
    void seeIfSetAttributeValueWorksFalseNotDouble() {
        // Act

        Object result = validLampSpec.setAttributeValue(LampSpec.FLUX, 5);

        // Assert

        assertEquals(false, result);
    }

    @Test
    void seeIfGetAttributeValueWorks() {
        // Arrange


        validLampSpec.setAttributeValue(LampSpec.FLUX, 6D);

        // Happy Case

        assertEquals(6D, validLampSpec.getAttributeValue(LampSpec.FLUX));

        // Wrong attribute name.

        assertEquals(false, validLampSpec.getAttributeValue("notFLUX"));

        // Empty attribute name.

        assertEquals(false, validLampSpec.getAttributeValue(""));
    }

    @Test
    void seeIfGetAttributeUnitWorksAllCases() {
        // Arrange

        String attributeLm = "lm";

        // Happy case.

        assertEquals(attributeLm, validLampSpec.getAttributeUnit(LampSpec.FLUX));

        // Wrong attribute name.

        assertEquals(false, validLampSpec.getAttributeUnit("notFLUX"));

        // Empty attribute name.

        assertEquals(false, validLampSpec.getAttributeUnit(""));
    }

    @Test
    void seeIfSetAttributeValueWorksAllCases() {
        // Happy case.

        assertTrue(validLampSpec.setAttributeValue(LampSpec.FLUX, 6D));

        // Wrong attribute name.

        assertFalse(validLampSpec.setAttributeValue("notFLUX", 6D));

        // Empty attribute name.

        assertFalse(validLampSpec.setAttributeValue("", 6D));
    }

    @Test
    void seeIfSetAttributeValueWorksNotDoubles() {
        // Correct name, not a double.

        assertFalse(validLampSpec.setAttributeValue(LampSpec.FLUX, 5));

        // Incorrect name, not a double.

        assertFalse(validLampSpec.setAttributeValue("notFLUX", 5));
        assertFalse(validLampSpec.setAttributeValue("notNOMINAL_POWER", 5));

        // Empty name, not a double.

        assertFalse(validLampSpec.setAttributeValue("", 5));
    }
}
