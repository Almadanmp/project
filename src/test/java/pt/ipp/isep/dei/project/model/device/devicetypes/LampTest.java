package pt.ipp.isep.dei.project.model.device.devicetypes;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.TestUtils;

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
        DeviceType expectedResult = DeviceType.LAMP;
        DeviceType result = lamp.getType();
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
        expectedResult.add("luminousFlux");
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
        boolean actualResult = lamp.setAttributeValue("luminousFlux", 12.0);
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
        lamp.setAttributeValue("luminousFlux", 5.0);
        Object result = lamp.getAttributeValue("luminousFlux");
        assertEquals(5.0, result);
    }

}
