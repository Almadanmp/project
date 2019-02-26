package pt.ipp.isep.dei.project.model.device.devicetypes;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.devicespecs.WineCoolerSpec;
import pt.ipp.isep.dei.project.model.device.program.WineCooler;

import static org.testng.Assert.*;

public class WineCoolerDTTest {

    @Test
    void createWineCoolerType() {
        WineCoolerDT dt = new WineCoolerDT();
        Device result = dt.createDevice();
        Device expectedResult = new WineCooler(new WineCoolerSpec());
        assertEquals(result, expectedResult);
    }

    @Test
    void getDeviceType() {
        WineCoolerDT dt = new WineCoolerDT();
        String result = dt.getDeviceType();
        String expectedResult = "WineCooler";
        assertEquals(result, expectedResult);
    }
}