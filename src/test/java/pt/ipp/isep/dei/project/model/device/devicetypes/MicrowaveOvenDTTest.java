package pt.ipp.isep.dei.project.model.device.devicetypes;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.MicrowaveOven;
import pt.ipp.isep.dei.project.model.device.devicespecs.MicrowaveOvenSpec;

import static org.testng.Assert.*;

public class MicrowaveOvenDTTest {

    @Test
    void createMicrowaveOvenType() {
        MicrowaveOvenDT dt = new MicrowaveOvenDT();
        Device result = dt.createDevice();
        Device expectedResult = new MicrowaveOven(new MicrowaveOvenSpec());
        assertEquals(result, expectedResult);
    }

    @Test
    void getDeviceType() {
        MicrowaveOvenDT dt = new MicrowaveOvenDT();
        String result = dt.getDeviceType();
        String expectedResult = "Microwave Oven";
        assertEquals(result, expectedResult);
    }

}