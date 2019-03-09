package pt.ipp.isep.dei.project.model.device.devicetypes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.WaterHeater;
import pt.ipp.isep.dei.project.model.device.devicespecs.WaterHeaterSpec;

import static org.testng.Assert.assertEquals;

/**
 * device tests class.
 */

class WaterHeaterDTTest {
    // Common testing artifacts for tests in this class.
    private WaterHeaterDT heaterType;

    @BeforeEach
    void arrangeArtifacts(){
        heaterType = new WaterHeaterDT();
    }

    @Test
    void seeIfCreateDeviceWorks() {
        // Arrange

        Device expectedResult = new WaterHeater(new WaterHeaterSpec());

        // Act

        Device result = heaterType.createDevice();

        // Assert

        assertEquals(result, expectedResult);
    }

    @Test
    void seeIfGetDeviceTypeWorks() {
        // Arrange

        String expectedResult = "WaterHeater";

        // Act

        String result = heaterType.getDeviceType();

        // Assert

        assertEquals(result, expectedResult);
    }

}