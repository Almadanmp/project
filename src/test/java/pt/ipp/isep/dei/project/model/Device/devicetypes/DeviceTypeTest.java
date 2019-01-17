package pt.ipp.isep.dei.project.model.Device.devicetypes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * DeviceType tests class.
 */

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
    @Test
    public void lampValueTest() {
        DeviceType lamp = DeviceType.LAMP;
        assertEquals(DeviceType.valueOf("LAMP"), lamp);
    }

    @Test
    void seeIfPrintDeviceTypeByList(){
        DeviceType fridge = DeviceType.FRIDGE;
        String result = fridge.buildDeviceTypesByIndexString();
        String expectedResult =
                "0) Device Type: WATER_HEATER;\n" +
                        "1) Device Type: WASHING_MACHINE;\n" +
                        "2) Device Type: DISHWASHER;\n" +
                        "3) Device Type: FRIDGE;\n" +
                        "4) Device Type: LAMP;\n";
        assertEquals(expectedResult,result);
    }

    @Test
    void seeIfPrintDeviceType(){
        DeviceType fridge = DeviceType.FRIDGE;
        String result = fridge.buildDeviceTypeString(fridge);
        String expectedResult =
                        "FRIDGE";
        assertEquals(expectedResult,result);
    }
}
