package pt.ipp.isep.dei.project.model.device.devicetypes;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.WallTowelHeater;
import pt.ipp.isep.dei.project.model.device.devicespecs.WallTowelHeaterSpec;

import static org.testng.Assert.*;

class WallTowelHeaterTypeTest {

    // Common artifacts for testing in this class.
    private WallTowelHeaterType validWTHDT = new WallTowelHeaterType();

    @Test
    void seeIfCreateDeviceWorks() {
        // Arrange

        Device expectedResult = new WallTowelHeater(new WallTowelHeaterSpec());

        // Act

        Device actualResult = validWTHDT.createDevice();

        // Assert

        assertEquals(actualResult, expectedResult);
    }

    @Test
    void seeIfGetDeviceTypeWorks() {
        // Arrange

        String expectedResult = "WallTowelHeater";

        // Act

        String actualResult = validWTHDT.getDeviceType();

        // Assert

        assertEquals(actualResult, expectedResult);
    }
}
