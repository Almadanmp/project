package pt.ipp.isep.dei.project.model.devicetypes;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        expectedResult.add("LuminousFlux");
        List<String> result = lamp.getAttributeNames();
        assertEquals(expectedResult, result);
    }

    @Test
    public void getAttributeValuesTest() {
        Lamp lamp = new Lamp();
        double expectedResult = 0;
        double result = lamp.getAttributeValue("lisboa");
        assertEquals(expectedResult, result);
    }

    @Test
    public void setAttributeValueTest() {
        Lamp lamp = new Lamp();
        boolean result = lamp.setAttributeValue("lisboa", 12);
        assertEquals(false, result);
    }
}
