package pt.ipp.isep.dei.project.model.device.devicetypes;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.PortableElectricConvectionHeater;

import static org.testng.Assert.assertEquals;

/**
 * device tests class.
 */

class PortableElectricConvectionHeaterDTTest {

    @Test
    void getDeviceType() {
        PortableElectricConvectionHeaterDT dt = new PortableElectricConvectionHeaterDT();
        String result = dt.getDeviceType();
        String expectedResult = "PortableElectricConvectionHeater";
        assertEquals(result, expectedResult);
    }
}
