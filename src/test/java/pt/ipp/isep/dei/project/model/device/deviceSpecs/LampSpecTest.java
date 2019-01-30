package pt.ipp.isep.dei.project.model.device.deviceSpecs;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.TestUtils;
import pt.ipp.isep.dei.project.model.device.deviceSpecs.LampSpec;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * LampSpec tests class.
 */

public class LampSpecTest {
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

}
