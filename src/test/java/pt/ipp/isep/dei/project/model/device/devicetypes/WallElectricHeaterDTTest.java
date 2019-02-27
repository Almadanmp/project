package pt.ipp.isep.dei.project.model.device.devicetypes;

import org.junit.jupiter.api.Test;


import static org.testng.Assert.assertEquals;

/**
 * device tests class.
 */

class WallElectricHeaterDTTest {

    @Test
    void getDeviceType() {
        WallElectricHeaterDT dt = new WallElectricHeaterDT();
        String result = dt.getDeviceType();
        String expectedResult = "WallElectricHeater";
        assertEquals(result, expectedResult);
    }
}
