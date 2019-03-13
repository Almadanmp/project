package pt.ipp.isep.dei.project.model.device.devicetypes;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.WallElectricHeater;
import pt.ipp.isep.dei.project.model.device.devicespecs.WallElectricHeaterSpec;

import static org.testng.Assert.assertEquals;

/**
 * device tests class.
 */

class WallElectricHeaterTypeTest {

    @Test
    void seeCreateDevice(){
        // Arrange

        WallElectricHeaterType dt = new WallElectricHeaterType();
        Device expectedResult = new WallElectricHeater(new WallElectricHeaterSpec());

        // Act

        Device actualResult = dt.createDevice();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetDeviceTypeWorks() {
        // Arrange

        String expectedResult = "WallElectricHeater";
        WallElectricHeaterType dt = new WallElectricHeaterType();

        // Act

        String result = dt.getDeviceType();

        // Assert

        assertEquals(result, expectedResult);
    }
}
