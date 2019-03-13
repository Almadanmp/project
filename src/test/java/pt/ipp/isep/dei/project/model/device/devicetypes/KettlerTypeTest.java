package pt.ipp.isep.dei.project.model.device.devicetypes;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.Kettler;
import pt.ipp.isep.dei.project.model.device.devicespecs.KettlerSpec;

import static org.testng.Assert.*;

class KettlerTypeTest {

    @Test
    void seeIfCreateDeviceWorks() {
        //Arrange

        KettlerType kettlerType = new KettlerType();
        Device expectedResult = new Kettler(new KettlerSpec());

        //Act

        Device actualResult = kettlerType.createDevice();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetDeviceTypeWorks() {
        //Arrange

        KettlerType kettlerType = new KettlerType();

        //Act

        String actualResult = kettlerType.getDeviceType();

        // Assert

        assertEquals("Kettler", actualResult);
    }
}