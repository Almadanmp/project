package pt.ipp.isep.dei.project.model.devicetypes;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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


    @Test
    void seeIfPrintDeviceTypeByList(){
        DeviceType fridge = DeviceType.FRIDGE;
        List<Integer> list = new ArrayList<>();
        Integer i = 1;
        list.add(i);
        String result = fridge.printDeviceTypeByIndex();
        String expectedResult =
                "0) Device Type: WATER_HEATER;\n" +
                        "1) Device Type: WASHING_MACHINE;\n" +
                        "2) Device Type: DISHWASHER;\n" +
                        "3) Device Type: FRIDGE;\n";
        assertEquals(expectedResult,result);
    }
}
