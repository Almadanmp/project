package pt.ipp.isep.dei.project.model.device.devicetypes;

import org.junit.jupiter.api.Test;


import static org.testng.Assert.assertEquals;

/**
 * device tests class.
 */

class WallElectricHeaterDTTest {

    @Test
    void seeIfGetDeviceTypeWorks() {
        // Arrange

        String expectedResult = "WallElectricHeater";
        WallElectricHeaterDT dt = new WallElectricHeaterDT();

        // Act

        String result = dt.getDeviceType();

        // Assert

        assertEquals(result, expectedResult);
    }
}
