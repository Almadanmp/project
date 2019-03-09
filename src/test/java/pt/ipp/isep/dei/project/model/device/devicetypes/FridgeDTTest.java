package pt.ipp.isep.dei.project.model.device.devicetypes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.Fridge;
import pt.ipp.isep.dei.project.model.device.devicespecs.FridgeSpec;

import static org.testng.Assert.assertEquals;

/**
 * device tests class.
 */

class FridgeDTTest {

    // Common testing artifacts for tests in this class.
    private FridgeDT fridgeType;

    @BeforeEach
    void arrangeArtifacts(){
        fridgeType = new FridgeDT();
    }

    @Test
    void seeIfCreateDeviceWorks() {
        // Arrange

        Device expectedResult = new Fridge(new FridgeSpec());

        // Act

        Device result = fridgeType.createDevice();

        // Assert

        assertEquals(result, expectedResult);
    }

    @Test
    void seeIfGetDeviceTypeWorks() {
        // Arrange

        String expectedResult = "Fridge";

        // Act

        String result = fridgeType.getDeviceType();

        // Assert

        assertEquals(result, expectedResult);
    }

}
