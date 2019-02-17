package pt.ipp.isep.dei.project.model.device.devicetypes;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.device.devices.Device;
import pt.ipp.isep.dei.project.model.device.devices.Lamp;
import pt.ipp.isep.dei.project.model.device.devicespecs.LampSpec;

import static org.testng.Assert.assertEquals;

/**
 * device tests class.
 */

class LampDTTest {

    @Test
    void createLampType() {
        LampDT dt = new LampDT();
        Device result = dt.createDeviceType();
        Device expectedResult = new Lamp(new LampSpec());
        assertEquals(result, expectedResult);
    }

    @Test
    void getDeviceType() {
        LampDT dt = new LampDT();
        String result = dt.getDeviceType();
        String expectedResult = "Lamp";
        assertEquals(result, expectedResult);
    }
}
