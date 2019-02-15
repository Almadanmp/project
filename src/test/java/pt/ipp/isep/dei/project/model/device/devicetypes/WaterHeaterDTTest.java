package pt.ipp.isep.dei.project.model.device.devicetypes;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.device.DeviceTemporary;
import pt.ipp.isep.dei.project.model.device.devicespecs.WaterHeaterSpec;

import static org.testng.Assert.assertEquals;

/**
 * device tests class.
 */

class WaterHeaterDTTest {

    @Test
    void createWaterHeaterType() {
        WaterHeaterDT dt = new WaterHeaterDT();
        DeviceTemporary result = dt.createDeviceType();
        DeviceTemporary expectedResult = new DeviceTemporary(new WaterHeaterSpec());
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