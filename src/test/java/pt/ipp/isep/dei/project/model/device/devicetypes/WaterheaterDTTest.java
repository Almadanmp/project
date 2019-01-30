package pt.ipp.isep.dei.project.model.device.devicetypes;

import org.junit.jupiter.api.Test;

/**
 * device tests class.
 */

public class WaterheaterDTTest {

    @Test
    public void createWaterheaterType() {
        WaterHeaterDT dt = new WaterHeaterDT();
        dt.createDeviceType();
    }
}
