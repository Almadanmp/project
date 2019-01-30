package pt.ipp.isep.dei.project.model.device.devicetypes;

import org.junit.jupiter.api.Test;

/**
 * device tests class.
 */

public class FridgeDTTest {

    @Test
    public void createFridgeType() {
        FridgeDT dt = new FridgeDT();
        dt.createDeviceType();
    }
}
