package pt.ipp.isep.dei.project.model.device.devicetypes;

import org.junit.jupiter.api.Test;

/**
 * device tests class.
 */

public class LampDTTest {

    @Test
    public void createLampType() {
        LampDT dt = new LampDT();
        dt.createDevice();
    }
}
