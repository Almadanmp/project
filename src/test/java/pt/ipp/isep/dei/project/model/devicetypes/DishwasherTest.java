package pt.ipp.isep.dei.project.model.devicetypes;

import org.junit.jupiter.api.Test;

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

}
