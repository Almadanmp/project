package pt.ipp.isep.dei.project.model.device.devicetypes;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.TestUtils;
import pt.ipp.isep.dei.project.model.device.deviceSpecs.Lamp;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Lamp tests class.
 */

public class LampTest {
    @Test
    public void getTypeTest() {
        Lamp lamp = new Lamp();
        String expectedResult = "Lamp";
        String result = lamp.getType();
        assertEquals(expectedResult, result);
    }

    @Test
    public void getConsumptionTest() {
        Lamp lamp = new Lamp();
        double expectedResult = 0;
        double result = lamp.getConsumption();
        assertEquals(expectedResult, result);
    }

    @Test
    public void getAttributeNamesTest() {
        Lamp lamp = new Lamp();
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("Luminous Flux");
        List<String> result = lamp.getAttributeNames();
        assertEquals(expectedResult, result);
    }

    @Test
    public void setAttributeValueTest() {
        Lamp lamp = new Lamp();
        boolean result = lamp.setAttributeValue("lisboa", 12);
        assertEquals(false, result);
    }

    @Test
    public void setAttributeValueTestTrue2() {
        Lamp lamp = new Lamp();
        boolean actualResult = lamp.setAttributeValue("Luminous Flux", 12.0);
        assertTrue(actualResult);
    }

    @Test
    public void getObjectAttributeValueTest() {
        Lamp lamp = new Lamp();
        lamp.setAttributeValue(TestUtils.L_FLUX, 4D);
        Double expectedResult = 4.0;
        Object result = lamp.getAttributeValue(TestUtils.L_FLUX);
        assertEquals(expectedResult, result);
    }
    @Test
    public void getObjectAttributeUnitTest() {
        Lamp lamp = new Lamp();
        String expectedResult = "lm";
        Object result = lamp.getAttributeUnit(TestUtils.L_FLUX);
        assertEquals(expectedResult, result);
        assertEquals(0, lamp.getAttributeUnit(""));

    }

    @Test
    public void setAttributeValueTestFalse() {
        Lamp lamp = new Lamp();
        Object result = lamp.setAttributeValue("luminousFlux", 5);
        assertEquals(false, result);
    }

    @Test
    public void setAttributeValueTestDefault() {
        Lamp lamp = new Lamp();
        lamp.setAttributeValue("luminousFlux", 5.0);
        Object result = lamp.getAttributeValue("lisbon");
        assertEquals(0, result);
    }

    @Test
    public void setAttributeValueTestTrue() {
        Lamp lamp = new Lamp();
        lamp.setAttributeValue("Luminous Flux", 5.0);
        Object result = lamp.getAttributeValue("Luminous Flux");
        assertEquals(5.0, result);
    }

}
