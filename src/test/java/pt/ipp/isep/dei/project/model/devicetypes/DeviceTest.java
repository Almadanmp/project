package pt.ipp.isep.dei.project.model.devicetypes;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Device;
import pt.ipp.isep.dei.project.model.Room;

import static org.junit.jupiter.api.Assertions.*;

public class DeviceTest {

    @Test
    public void getDeviceTypeTest() {
        Device d = new Device("WMOne", 12, new WashingMachine());
        DeviceType dT = DeviceType.WASHING_MACHINE;

        DeviceType expectedResult = dT;
        DeviceType result = d.getDeviceType();
        assertEquals(expectedResult, result);
    }

    @Test
    void seeEqualToSameObject() {
        Device d = new Device("WMOne", 12, new WashingMachine());
        boolean actualResult = d.equals(d);
        assertTrue(actualResult);
    }

    @Test
    void seeEqualsToDifObject() {
        Device d = new Device("WMOne", 12, new WashingMachine());
        Device d2 = new Device("WMTwo", 12, new WashingMachine());

        boolean actualResult = d.equals(d2);
        assertFalse(actualResult);
    }


    @Test
    void seeEqualsToDifTypeObject() {
        Device d = new Device("WMOne", 12, new WashingMachine());
        Room room = new Room("quarto", 1, 80, 2, 2);

        boolean actualResult = d.equals(room);
        assertFalse(actualResult);
    }

    @Test
    void seeEqualsToNullObject() {
        Device d = new Device("WMOne", 12, new WashingMachine());
        boolean actualResult = d.equals(null);

        assertFalse(actualResult);
    }

    @Test
    void seeIfPrintDeviceWorks() {
        Device d1 = new Device("frigo", 150, new Fridge());
        Room room = new Room("kitchen", 1, 1, 1, 1);
        d1.setmParentRoom(room);
        String result = d1.printDevice();
        String expectedResult = "The Device Name is frigo, which is in the Room kitchen, and its NominalPower is 150.0 kW.\n";
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetParentRoomWorks() {
        Device d1 = new Device("frigo", 150, new Fridge());
        Room room = new Room("kitchen", 1, 1, 1, 1);
        d1.setmParentRoom(room);
        Room result = d1.getmParentRoom();
        assertEquals(room, result);
    }

    @Test
    void seeIfSetNameWorks() {
        Device d1 = new Device("frigo", 150, new Fridge());
        d1.setmName("frigorifico");
        String result = d1.getName();
        String expectedResult = "frigorifico";
        assertEquals(expectedResult, result);
    }

    @Test
    public void hashCodeDummyTest() {
        Device d1 = new Device();
        int expectedResult = 1;
        int actualResult = d1.hashCode();
        assertEquals(expectedResult, actualResult);
    }



}
