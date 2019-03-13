package pt.ipp.isep.dei.project.model.device.devicetypes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.Lamp;
import pt.ipp.isep.dei.project.model.device.devicespecs.LampSpec;

import static org.testng.Assert.assertEquals;

/**
 * device tests class.
 */

class LampTypeTest {
    // Common testing artifacts for tests in this class.
    private LampType lampType;

    @BeforeEach
    void arrangeArtifacts(){
        lampType = new LampType();
    }

    @Test
    void seeIfCreateDeviceWorks() {
        // Arrange

        Device expectedResult = new Lamp(new LampSpec());

        // Act

        Device result = lampType.createDevice();

        // Assert

        assertEquals(result, expectedResult);
    }

    @Test
    void seeIfGetDeviceTypeWorks() {
        // Arrange

        String expectedResult = "Lamp";

        // Act

        String result = lampType.getDeviceType();

        // Assert

        assertEquals(result, expectedResult);
    }

}
