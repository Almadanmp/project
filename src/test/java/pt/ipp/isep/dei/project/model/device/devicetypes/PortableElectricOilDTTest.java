package pt.ipp.isep.dei.project.model.device.devicetypes;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.WallElectricHeater;

import static org.testng.Assert.assertEquals;

/**
 * device tests class.
 */

class PortableElectricOilDTTest {

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
