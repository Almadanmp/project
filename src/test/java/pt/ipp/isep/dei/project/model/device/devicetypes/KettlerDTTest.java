package pt.ipp.isep.dei.project.model.device.devicetypes;

import org.testng.annotations.Test;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.Kettler;
import pt.ipp.isep.dei.project.model.device.devicespecs.KettlerSpec;

import static org.testng.Assert.*;

public class KettlerDTTest {

    @Test
    public void seeIfCreateDeviceWorks() {
        //Arrange

        KettlerDT kettlerDT = new KettlerDT();
        Device expectedResult = new Kettler(new KettlerSpec());

        //Act

        Device actualResult = kettlerDT.createDevice();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfGetDeviceTypeWorks() {
        //Arrange

        KettlerDT kettlerDT = new KettlerDT();

        //Act

        String actualResult = kettlerDT.getDeviceType();

        // Assert

        assertEquals("Kettler", actualResult);
    }
}