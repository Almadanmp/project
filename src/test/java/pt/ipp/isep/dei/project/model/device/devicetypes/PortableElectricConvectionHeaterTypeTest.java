package pt.ipp.isep.dei.project.model.device.devicetypes;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.PortableElectricConvectionHeater;
import pt.ipp.isep.dei.project.model.device.devicespecs.PortableElectricConvectionHeaterSpec;

import static org.testng.Assert.assertEquals;

/**
 * device tests class.
 */

class PortableElectricConvectionHeaterTypeTest {

    @Test
    void seeCreateDevice(){
        // Arrange

        PortableElectricConvectionHeaterType dt = new PortableElectricConvectionHeaterType();
        Device expectedResult = new PortableElectricConvectionHeater(new PortableElectricConvectionHeaterSpec());

        // Act

        Device actualResult = dt.createDevice();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetDeviceTypeWorks() {
        // Arrange

        String expectedResult = "PortableElectricConvectionHeater";
        PortableElectricConvectionHeaterType dt = new PortableElectricConvectionHeaterType();

        // Act

        String result = dt.getDeviceType();

        // Assert

        assertEquals(result, expectedResult);
    }
}
