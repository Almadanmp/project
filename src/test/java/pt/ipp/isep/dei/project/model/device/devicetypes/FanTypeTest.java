package pt.ipp.isep.dei.project.model.device.devicetypes;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.Fan;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FanTypeTest {
    // Common testing artifacts for testing in this class.

    private FanType validType = new FanType();

    @Test
    void seeIfCreateDeviceWorks(){
        // Act

        Device actualResult = validType.createDevice();

        // Assert

        assertThat(actualResult, instanceOf(Fan.class));
    }

    @Test
    void seeIfGetDeviceTypeWorks(){
        // Arrange

        String expectedResult = "Fan";

        // Act

        String actualResult = validType.getDeviceType();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

}
