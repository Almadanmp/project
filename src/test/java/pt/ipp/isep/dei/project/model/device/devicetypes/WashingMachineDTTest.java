package pt.ipp.isep.dei.project.model.device.devicetypes;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.device.DeviceTemporary;
import pt.ipp.isep.dei.project.model.device.devicespecs.WashingMachineSpec;

import static org.testng.Assert.assertEquals;

/**
 * device tests class.
 */

class WashingMachineDTTest {

    @Test
    void createWashingMachineType() {
        WashingMachineDT dt = new WashingMachineDT();
        DeviceTemporary result = dt.createDeviceType();
        DeviceTemporary expectedResult = new DeviceTemporary(new WashingMachineSpec());
        assertEquals(result, expectedResult);
    }

    @Test
    void getDeviceType() {
        WashingMachineDT dt = new WashingMachineDT();
        String result = dt.getDeviceType();
        String expectedResult = "Washing Machine";
        assertEquals(result, expectedResult);
    }
}
