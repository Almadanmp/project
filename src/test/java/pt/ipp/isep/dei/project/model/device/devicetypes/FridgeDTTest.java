package pt.ipp.isep.dei.project.model.device.devicetypes;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.device.DeviceTemporary;
import pt.ipp.isep.dei.project.model.device.devicespecs.FridgeSpec;

import static org.testng.Assert.assertEquals;

/**
 * device tests class.
 */

class FridgeDTTest {

    @Test
    void createFridgeType() {
        FridgeDT dt = new FridgeDT();
        DeviceTemporary result = dt.createDeviceType();
        DeviceTemporary expectedResult = new DeviceTemporary(new FridgeSpec());
        assertEquals(result, expectedResult);
    }

    @Test
    void getDeviceType() {
        FridgeDT dt = new FridgeDT();
        String result = dt.getDeviceType();
        String expectedResult = "Fridge";
        assertEquals(result, expectedResult);
    }
}
