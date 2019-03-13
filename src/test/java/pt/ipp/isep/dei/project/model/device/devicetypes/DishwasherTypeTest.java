package pt.ipp.isep.dei.project.model.device.devicetypes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.Dishwasher;
import pt.ipp.isep.dei.project.model.device.devicespecs.DishwasherSpec;

import static org.testng.Assert.assertEquals;

/**
 * device tests class.
 */

class DishwasherTypeTest {
    // Common testing artifacts for tests in this class.
    private DishwasherType dishwasherType;

    @BeforeEach
    void arrangeArtifacts(){
        dishwasherType = new DishwasherType();
    }

    @Test
    void seeIfCreateDeviceWorks() {
        // Arrange

        Device expectedResult = new Dishwasher(new DishwasherSpec());

        // Act

        Device result = dishwasherType.createDevice();

        // Assert

        assertEquals(result, expectedResult);
    }

    @Test
    void seeIfGetDeviceTypeWorks() {
        // Arrange

        String expectedResult = "Dishwasher";

        // Act

        String result = dishwasherType.getDeviceType();

        // Assert

        assertEquals(result, expectedResult);
    }
}
