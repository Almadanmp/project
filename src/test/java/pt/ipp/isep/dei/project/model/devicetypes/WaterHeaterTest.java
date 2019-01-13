package pt.ipp.isep.dei.project.model.devicetypes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WaterHeaterTest {

    @Test
    public void getTypeTest() {
        WaterHeater waterHeater = new WaterHeater();
        DeviceType expectedResult = DeviceType.WATER_HEATER;
        DeviceType result = waterHeater.getType();
        assertEquals(expectedResult, result);
    }

    @Test
    public void getConsumptionTest() {
        WaterHeater waterHeater = new WaterHeater();
        double expectedResult = 1;
        double result = waterHeater.getConsumption();
        assertEquals(expectedResult, result);
    }
}
