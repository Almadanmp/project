package pt.ipp.isep.dei.project.model.device.devicetypes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.ElectricOven;
import pt.ipp.isep.dei.project.model.device.devicespecs.ElectricOvenSpec;

import static org.testng.Assert.assertEquals;

class ElectricOvenTypeTest {
    // Common testing artifacts for tests in this class.
    private ElectricOvenType electricOvenType;

    @BeforeEach
    void arrangeArtifacts(){
        electricOvenType = new ElectricOvenType();
    }

    @Test
    void seeIfCreateDeviceWorks() {
        // Arrange

        Device expectedResult = new ElectricOven(new ElectricOvenSpec());

        // Act

        Device result = electricOvenType.createDevice();

        // Assert

        assertEquals(result, expectedResult);
    }

    @Test
    void seeIfGetDeviceTypeWorks() {
        // Arrange

        String expectedResult = "Electric Oven";

        // Act

        String result = electricOvenType.getDeviceType();

        // Assert

        assertEquals(result, expectedResult);
    }

}