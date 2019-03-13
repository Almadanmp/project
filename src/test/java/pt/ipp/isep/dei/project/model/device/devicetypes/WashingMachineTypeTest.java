package pt.ipp.isep.dei.project.model.device.devicetypes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.WashingMachine;
import pt.ipp.isep.dei.project.model.device.devicespecs.WashingMachineSpec;

import static org.testng.Assert.assertEquals;

/**
 * device tests class.
 */

class WashingMachineTypeTest {

    // Common testing artifacts for tests in this class.
    private WashingMachineType washingMachineType;

    @BeforeEach
    void arrangeArtifacts() {
        washingMachineType = new WashingMachineType();
    }

    @Test
    void seeIfCreateDeviceWorks() {
        // Arrange

        Device expectedResult = new WashingMachine(new WashingMachineSpec());

        // Act

        Device result = washingMachineType.createDevice();

        // Assert

        assertEquals(result, expectedResult);
    }

    @Test
    void seeIfGetDeviceTypeWorks() {
        // Arrange

        String expectedResult = "Washing Machine";

        // Act

        String result = washingMachineType.getDeviceType();

        // Assert

        assertEquals(result, expectedResult);
    }

}
