package pt.ipp.isep.dei.project.model.device.devicetypes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.WineCooler;
import pt.ipp.isep.dei.project.model.device.devicespecs.WineCoolerSpec;

import static org.testng.Assert.*;

class WineCoolerTypeTest {
    // Common testing artifacts for tests in this class.
    private WineCoolerType coolerType;

    @BeforeEach
    void arrangeArtifacts(){
        coolerType = new WineCoolerType();
    }

    @Test
    void seeIfCreateDeviceWorks() {
        // Arrange

        Device expectedResult = new WineCooler(new WineCoolerSpec());

        // Act

        Device result = coolerType.createDevice();

        // Assert

        assertEquals(result, expectedResult);
    }

    @Test
    void seeIfGetDeviceTypeWorks() {
        // Arrange

        String expectedResult = "WineCooler";

        // Act

        String result = coolerType.getDeviceType();

        // Assert

        assertEquals(result, expectedResult);
    }

}