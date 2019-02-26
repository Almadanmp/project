package pt.ipp.isep.dei.project.model.device.devicetypes;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.Stove;
import pt.ipp.isep.dei.project.model.device.devicespecs.StoveSpec;

import static org.testng.Assert.*;

public class StoveDTTest {

    @Test
    void ensureWeCreateStoveType() {
        StoveDT dt = new StoveDT();
        Device result = dt.createDevice();
        Device expectedResult = new Stove(new StoveSpec());
        assertEquals(result, expectedResult);
    }

    @Test
    void ensureWeGetDeviceType() {
        StoveDT dt = new StoveDT();
        String result = dt.getDeviceType();
        String expectedResult = "Stove";
        assertEquals(result, expectedResult);
    }

}