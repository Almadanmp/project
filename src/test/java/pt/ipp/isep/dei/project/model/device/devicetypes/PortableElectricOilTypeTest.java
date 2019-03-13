package pt.ipp.isep.dei.project.model.device.devicetypes;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.PortableElectricOilHeater;
import pt.ipp.isep.dei.project.model.device.devicespecs.PortableElectricOilHeaterSpec;

import static org.testng.Assert.assertEquals;

/**
 * device tests class.
 */

class PortableElectricOilTypeTest {

    @Test
    void seeCreateDevice(){
        // Arrange

        PortableElectricOilHeaterType dt = new PortableElectricOilHeaterType();
        Device expectedResult = new PortableElectricOilHeater(new PortableElectricOilHeaterSpec());

        // Act

        Device actualResult = dt.createDevice();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetDeviceTypeWorks() {
        // Arrange

        String expectedResult = "PortableElectricOilHeater";
        PortableElectricOilHeaterType dt = new PortableElectricOilHeaterType();

        // Act

        String result = dt.getDeviceType();

        // Assert

        assertEquals(result, expectedResult);
    }
}
