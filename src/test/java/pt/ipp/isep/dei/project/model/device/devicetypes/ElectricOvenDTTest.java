package pt.ipp.isep.dei.project.model.device.devicetypes;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.ElectricOven;
import pt.ipp.isep.dei.project.model.device.devicespecs.ElectricOvenSpec;

import static org.testng.Assert.assertEquals;

 class ElectricOvenDTTest {

    @Test
    void ensureWeCreateElectricOvenType() {
        ElectricOvenDT dt = new ElectricOvenDT();
        Device result = dt.createDevice();
        Device expectedResult = new ElectricOven(new ElectricOvenSpec());
        assertEquals(result, expectedResult);
    }

    @Test
    void ensureWeGetDeviceType() {
        ElectricOvenDT dt = new ElectricOvenDT();
        String result = dt.getDeviceType();
        String expectedResult = "Electric Oven";
        assertEquals(result, expectedResult);
    }

}