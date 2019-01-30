package pt.ipp.isep.dei.project.model.device.devicetypes;

import org.junit.jupiter.api.Test;

/**
 * device tests class.
 */

public class WashingMachineDTTest {

    @Test
    public void createWashingMachineType() {
        WashingMachineDT dt = new WashingMachineDT();
        dt.createDevice();
    }
}
