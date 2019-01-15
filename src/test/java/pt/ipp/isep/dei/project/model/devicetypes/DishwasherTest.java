package pt.ipp.isep.dei.project.model.devicetypes;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DishwasherTest {

    @Test
    public void seeIfGetTypeTest() {
        Dishwasher dishwasher = new Dishwasher();
        DeviceType expectedResult = DeviceType.DISHWASHER;
        DeviceType result = dishwasher.getType();
        assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfGetConsumptionTest() {
        Dishwasher dishwasher = new Dishwasher();
        double expectedResult = 0;
        double result = dishwasher.getConsumption();
        assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfGetNominalPower() {
        Dishwasher dishwasher = new Dishwasher(4);
        double expectedResult = 6;
        dishwasher.getNominalPower();
        dishwasher.setNominalPower(6);
        double result = dishwasher.getNominalPower();
        assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfGetCapacity() {
        Dishwasher dishwasher = new Dishwasher(4);
        double expectedResult = 6;
        dishwasher.getCapacity();
        dishwasher.setCapacity(6);
        double result = dishwasher.getCapacity();
        assertEquals(expectedResult, result);
    }

    @Test
    public void getAttributeNamesTest() {
        Dishwasher dishwasher = new Dishwasher(1);
        List<String> expectedResult = new ArrayList<>();
        List<String> result = dishwasher.getAttributeNames();
        assertEquals(expectedResult, result);
    }

    @Test
    public void getAttributeValuesTest() {
        Dishwasher dishwasher = new Dishwasher(1);
        double expectedResult = 0;
        double result = dishwasher.getAttributeValue("lisboa");
        assertEquals(expectedResult, result);
    }

    @Test
    public void setAttributeValueTest() {
        Dishwasher dishwasher = new Dishwasher(1);
        boolean result = dishwasher.setAttributeValue("lisboa", 12);
        assertEquals(false, result);
    }


}
