package pt.ipp.isep.dei.project.model.device.devicetypes;

import org.junit.jupiter.api.Test;

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
        Lamp lamp = new Lamp(23);
        DeviceType expectedResult = DeviceType.LAMP;
        DeviceType result = lamp.getType();
        assertEquals(expectedResult, result);
    }

    @Test
    public void getConsumptionTest() {
        Lamp lamp = new Lamp(5);
        double expectedResult = 0;
        double result = lamp.getConsumption();
        assertEquals(expectedResult, result);
    }

    @Test
    public void getAttributeNamesTest() {
        Lamp lamp = new Lamp(23);
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("LuminousFlux");
        List<String> result = lamp.getAttributeNames();
        assertEquals(expectedResult, result);
    }

    @Test
    public void setAttributeValueTest() {
        Lamp lamp = new Lamp(23);
        boolean result = lamp.setAttributeValue("lisboa", 12);
        assertEquals(false, result);
    }

    @Test
    public void setAttributeValueTestTrue2() {
        Lamp lamp = new Lamp(3);
        boolean actualResult = lamp.setAttributeValue("LuminousFlux", 12.0);
        assertTrue(actualResult);
    }

    @Test
    public void getObjectAttributeValueTest() {
        Lamp lamp = new Lamp(4);
       double expectedResult = 4;
        Object result = lamp.getAttributeValue("LuminousFlux");
        assertEquals(expectedResult, result);
    }

    @Test
    public void setAttributeValueTestFalse() {
        Lamp lamp = new Lamp(4);
        Object result = lamp.setAttributeValue("LuminousFlux",5);
        assertEquals(false, result);
    }
    @Test
    public void setAttributeValueTestDefault() {
        Lamp lamp = new Lamp(1);
        lamp.setAttributeValue("LuminousFlux", 5.0);
        Object result = lamp.getAttributeValue("lisbon");
        assertEquals(0, result);
    }

    @Test
    public void setAttributeValueTestTrue() {
        Lamp lamp = new Lamp(1);
        lamp.setAttributeValue("LuminousFlux", 5.0);
        Object result = lamp.getAttributeValue("LuminousFlux");
        assertEquals(5.0, result);
    }

}
