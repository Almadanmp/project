package pt.ipp.isep.dei.project.model.devicetypes;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Device;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeviceTest {

    @Test
    public void getDeviceTypeTest() {
        Device d = new Device("WMOne", 12, new WashingMachine());
        DeviceType dT = DeviceType.WASHING_MACHINE;

        DeviceType expectedResult = dT;
        DeviceType result = d.getDeviceType();
        assertEquals(expectedResult, result);
    }
}
