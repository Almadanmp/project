package pt.ipp.isep.dei.project.model.device.devicetypes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.MicrowaveOven;
import pt.ipp.isep.dei.project.model.device.devicespecs.MicrowaveOvenSpec;

import static org.testng.Assert.*;

class MicrowaveOvenTypeTest {
    // Common testing artifacts for tests in this class.
    private MicrowaveOvenType ovenType;

    @BeforeEach
    void arrangeArtifacts(){
        ovenType = new MicrowaveOvenType();
    }

    @Test
    void seeIfCreateDeviceWorks() {
        // Arrange

        Device expectedResult = new MicrowaveOven(new MicrowaveOvenSpec());

        // Act

        Device result = ovenType.createDevice();

        // Assert

        assertEquals(result, expectedResult);
    }

    @Test
    void seeIfGetDeviceTypeWorks() {
        // Arrange

        String expectedResult = "Microwave Oven";

        // Act

        String result = ovenType.getDeviceType();

        // Assert

        assertEquals(result, expectedResult);
    }

}