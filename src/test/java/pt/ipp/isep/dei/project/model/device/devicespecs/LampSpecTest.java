package pt.ipp.isep.dei.project.model.device.devicespecs;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.TestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * LampSpec tests class.
 */

public class LampSpecTest {

    public static final String FLUX = "Luminous Flux";
    public static final String NOMINAL_POWER = "nominal power";
    public static final String notFLUX = "\0Luminous Flux";
    public static final String notNOMINAL_POWER = "\0nominal power";

    @Test
    public void getTypeTest() {
        LampSpec lampSpec = new LampSpec();
        String expectedResult = "Lamp";
        String result = lampSpec.getType();
        assertEquals(expectedResult, result);
    }

    @Test
    public void getConsumptionTest() {
        LampSpec lampSpec = new LampSpec();
        double expectedResult = 0;
        double result = lampSpec.getConsumption();
        assertEquals(expectedResult, result);
    }

    @Test
    public void getAttributeNamesTest() {
        LampSpec lampSpec = new LampSpec();
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("Luminous Flux");
        List<String> result = lampSpec.getAttributeNames();
        assertEquals(expectedResult, result);
    }

    @Test
    public void setAttributeValueTest() {
        LampSpec lampSpec = new LampSpec();
        boolean result = lampSpec.setAttributeValue("lisboa", 12);
        assertEquals(false, result);
    }

    @Test
    public void setAttributeValueTestTrue2() {
        LampSpec lampSpec = new LampSpec();
        boolean actualResult = lampSpec.setAttributeValue("Luminous Flux", 12.0);
        assertTrue(actualResult);
    }

    @Test
    public void setAttributeValueTestTrue3() {
        LampSpec lampSpec = new LampSpec();
        boolean actualResult = lampSpec.setAttributeValue("nominal power", 12.0);
        assertTrue(actualResult);
    }

    @Test
    public void getObjectAttributeValueTest() {
        LampSpec lampSpec = new LampSpec();
        lampSpec.setAttributeValue(TestUtils.L_FLUX, 4D);
        Double expectedResult = 4.0;
        Object result = lampSpec.getAttributeValue(TestUtils.L_FLUX);
        assertEquals(expectedResult, result);
    }
    @Test
    public void getObjectAttributeUnitTest() {
        LampSpec lampSpec = new LampSpec();
        String expectedResult = "lm";
        Object result = lampSpec.getAttributeUnit(TestUtils.L_FLUX);
        assertEquals(expectedResult, result);
        assertEquals(false, lampSpec.getAttributeUnit(""));

    }

    @Test
    public void setAttributeValueTestFalse() {
        LampSpec lampSpec = new LampSpec();
        Object result = lampSpec.setAttributeValue("luminousFlux", 5);
        assertEquals(false, result);
    }

    @Test
    public void setAttributeValueTestDefault() {
        LampSpec lampSpec = new LampSpec();
        lampSpec.setAttributeValue("luminousFlux", 5.0);
        Object result = lampSpec.getAttributeValue("lisbon");
        assertEquals(false, result);
    }

    @Test
    public void setAttributeValueTestTrue() {
        LampSpec lampSpec = new LampSpec();
        lampSpec.setAttributeValue("Luminous Flux", 5.0);
        Object result = lampSpec.getAttributeValue("Luminous Flux");
        assertEquals(5.0, result);
    }@Test
    public void getObjectAttributeValueTestNominalPower() {
        LampSpec lampSpec = new LampSpec();
        lampSpec.setAttributeValue(TestUtils.NOMINAL_POWER, 4D);
        Double expectedResult = 4.0;
        Object result = lampSpec.getAttributeValue(TestUtils.NOMINAL_POWER);
        assertEquals(expectedResult, result);
    }
    @Test
    public void getObjectAttributeUnitTestNominalPower() {
        LampSpec lampSpec = new LampSpec();
        String expectedResult = "kW";
        Object result = lampSpec.getAttributeUnit(TestUtils.NOMINAL_POWER);
        assertEquals(expectedResult, result);
        assertEquals(false, lampSpec.getAttributeUnit(""));

    }

    @Test
    public void setAttributeValueTestFalseNominalPower() {
        LampSpec lampSpec = new LampSpec();
        Object result = lampSpec.setAttributeValue(TestUtils.NOMINAL_POWER, 5);
        assertEquals(false, result);
    }

    @Test
    public void setAttributeValueTestFalseFlux() {
        LampSpec lampSpec = new LampSpec();
        Object result = lampSpec.setAttributeValue(TestUtils.L_FLUX, 5);
        assertEquals(false, result);
    }

    @Test
    public void setAttributeValueTestDefaultNominalPower() {
        LampSpec lampSpec = new LampSpec();
        lampSpec.setAttributeValue(TestUtils.NOMINAL_POWER, 5.0);
        Object result = lampSpec.getAttributeValue("lisbon");
        assertEquals(false, result);
    }

    @Test
    public void setAttributeValueTestTrueNominalPower() {
        LampSpec lampSpec = new LampSpec();
        lampSpec.setAttributeValue(TestUtils.NOMINAL_POWER, 5.0);
        Object result = lampSpec.getAttributeValue("nominal power");
        assertEquals(5.0, result);
    }
    @Test
    public void setAttributeValueTestTrueFlux() {
        LampSpec lampSpec = new LampSpec();
        lampSpec.setAttributeValue(TestUtils.L_FLUX, 5.0);
        Object result = lampSpec.getAttributeValue("Luminous Flux");
        assertEquals(5.0, result);
    }

    @Test
    void testGetAttributeValueForAllCases() {
        //Arrange
        LampSpec lSpec = new LampSpec();
        Double attribute = 6.0;
        lSpec.setAttributeValue(FLUX, attribute);
        lSpec.setAttributeValue(NOMINAL_POWER, attribute);
        // original strings:
        assertEquals(attribute, lSpec.getAttributeValue(FLUX));
        assertEquals(attribute, lSpec.getAttributeValue(NOMINAL_POWER));
        // same hash codes, but different strings:
        assertEquals(false, lSpec.getAttributeValue(notFLUX));
        assertEquals(false, lSpec.getAttributeValue(notNOMINAL_POWER));
        // distinct hash code to cover default cases of switches
        assertEquals(false, lSpec.getAttributeValue(""));
    }

    @Test
    void testGetAttributeUnitForAllCases() {
        //Arrange
        LampSpec lSpec = new LampSpec();
        String attributeLm = "lm";
        String attributeKW = "kW";
        // original strings:
        assertEquals(attributeLm, lSpec.getAttributeUnit(FLUX));
        assertEquals(attributeKW, lSpec.getAttributeUnit(NOMINAL_POWER));
        // same hash codes, but different strings:
        assertEquals(false, lSpec.getAttributeUnit(notFLUX));
        assertEquals(false, lSpec.getAttributeUnit(notNOMINAL_POWER));
        // distinct hash code to cover default cases of switches
        assertEquals(false, lSpec.getAttributeUnit(""));
    }

    @Test
    public void testSetAttributeValueForAllCases() {
        //Arrange
        LampSpec lSpec = new LampSpec();
        Double attribute = 6.0;
        lSpec.setAttributeValue(FLUX, attribute);
        lSpec.setAttributeValue(NOMINAL_POWER, attribute);
        // original strings:
        assertTrue(lSpec.setAttributeValue(FLUX, attribute));
        assertTrue(lSpec.setAttributeValue(NOMINAL_POWER, attribute));
        // same hash codes, but different strings:
        assertFalse(lSpec.setAttributeValue(notFLUX, attribute));
        assertFalse(lSpec.setAttributeValue(notNOMINAL_POWER, attribute));
        // distinct hash code to cover default cases of switches
        assertFalse(lSpec.setAttributeValue("", attribute));
    }
}
