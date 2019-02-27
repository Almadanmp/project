package pt.ipp.isep.dei.project.model.device.devicetypes;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.Freezer;
import pt.ipp.isep.dei.project.model.device.devicespecs.FreezerSpec;

import static org.testng.Assert.*;

public class FreezerDTTest {
    @Test
    void createFreezerType() {
       FreezerDT dt = new FreezerDT();
        Device result = dt.createDevice();
        Device expectedResult = new Freezer(new FreezerSpec());
        assertEquals(result, expectedResult);
    }

    @Test
    void getDeviceType() {
        FreezerDT dt = new FreezerDT();
        String result = dt.getDeviceType();
        String expectedResult = "Freezer";
        assertEquals(result, expectedResult);
    }
}