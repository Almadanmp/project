package pt.ipp.isep.dei.project.model.devicetypes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeviceTypeTest {

    @Test
    public void waterHeaterValueTest() {
        DeviceType waterHeater = DeviceType.WATER_HEATER;
        assertEquals(DeviceType.valueOf("WATER_HEATER"), waterHeater);
    }

    @Test
    public void washingMachineValueTest() {
        DeviceType washingMachine = DeviceType.WASHING_MACHINE;
        assertEquals(DeviceType.valueOf("WASHING_MACHINE"), washingMachine);
    }

    @Test
    public void dishwasherValueTest() {
        DeviceType dishwasher = DeviceType.DISHWASHER;
        assertEquals(DeviceType.valueOf("DISHWASHER"), dishwasher);
    }

    @Test
    public void fridgeValueTest() {
        DeviceType fridge = DeviceType.FRIDGE;
        assertEquals(DeviceType.valueOf("FRIDGE"), fridge);
    }

}
