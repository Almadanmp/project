package pt.ipp.isep.dei.project.model.device.devicetypes;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.devicespecs.WashingMachineSpec;
import pt.ipp.isep.dei.project.model.device.devicespecs.WaterHeaterSpec;

import static org.testng.Assert.assertEquals;

/**
 * device tests class.
 */

class WaterHeaterDTTest {

    @Test
    void createWaterHeaterType() {
        WaterHeaterDT dt = new WaterHeaterDT();
        Device result = dt.createDeviceType();
        Device expectedResult = new Device(new WaterHeaterSpec());
        assertEquals(result, expectedResult);
    }

    @Test
    void getDeviceType() {
        WaterHeaterDT dt = new WaterHeaterDT();
        String result = dt.getDeviceType();
        String expectedResult = "Water Heater";
        assertEquals(result, expectedResult);
    }
}
