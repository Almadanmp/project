package pt.ipp.isep.dei.project.model.devicetypes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DishwasherTest {

    @Test
    public void getTypeTest() {
        Dishwasher dishwasher = new Dishwasher();
        DeviceType expectedResult = DeviceType.DISHWASHER;
        DeviceType result = dishwasher.getType();
        assertEquals(expectedResult, result);
    }

    @Test
    public void getConsumptionTest() {
        Dishwasher dishwasher = new Dishwasher();
        double expectedResult = 0;
        double result = dishwasher.getConsumption();
        assertEquals(expectedResult, result);
    }
}
