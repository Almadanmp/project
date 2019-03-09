package pt.ipp.isep.dei.project.model.device.devicetypes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.Stove;
import pt.ipp.isep.dei.project.model.device.devicespecs.StoveSpec;

import static org.testng.Assert.*;

class StoveDTTest {
    // Common testing artifacts for tests in this class.
    private StoveDT stoveType;

    @BeforeEach
    void arrangeArtifacts(){
        stoveType = new StoveDT();
    }

    @Test
    void seeIfCreateDeviceWorks() {
        // Arrange

        Device expectedResult = new Stove(new StoveSpec());

        // Act

        Device result = stoveType.createDevice();

        // Assert

        assertEquals(result, expectedResult);
    }

    @Test
    void seeIfGetDeviceTypeWorks() {
        // Arrange

        String expectedResult = "Stove";

        // Act

        String result = stoveType.getDeviceType();

        // Assert

        assertEquals(result, expectedResult);
    }

}