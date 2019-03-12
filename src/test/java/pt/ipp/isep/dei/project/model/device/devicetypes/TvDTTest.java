package pt.ipp.isep.dei.project.model.device.devicetypes;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.TV;
import pt.ipp.isep.dei.project.model.device.devicespecs.TvSpec;

import static org.testng.Assert.assertEquals;

class TvDTTest {

    @Test
    void seeIfCreateDeviceWorks() {
        TvDT tVDT = new TvDT();
        Device expectedResult = new TV(new TvSpec());

        Device actualResult = tVDT.createDevice();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetDeviceTypeWorks() {
        TvDT tVDT = new TvDT();

        String actualResult = tVDT.getDeviceType();

        assertEquals("TV", actualResult);
    }
}