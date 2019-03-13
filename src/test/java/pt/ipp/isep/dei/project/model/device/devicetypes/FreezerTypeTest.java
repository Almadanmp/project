package pt.ipp.isep.dei.project.model.device.devicetypes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.Freezer;
import pt.ipp.isep.dei.project.model.device.devicespecs.FreezerSpec;

import static org.testng.Assert.*;

class FreezerTypeTest {
    // Common testing artifacts for tests in this class.
    private FreezerType freezerType;

    @BeforeEach
    void arrangeArtifacts(){
        freezerType = new FreezerType();
    }

    @Test
    void seeIfCreateDeviceWorks() {
        // Arrange

        Device expectedResult = new Freezer(new FreezerSpec());

        // Act

        Device result = freezerType.createDevice();

        // Assert

        assertEquals(result, expectedResult);
    }

    @Test
    void seeIfGetDeviceTypeWorks() {
        // Arrange

        String expectedResult = "Freezer";

        // Act

        String result = freezerType.getDeviceType();

        // Assert

        assertEquals(result, expectedResult);
    }

}