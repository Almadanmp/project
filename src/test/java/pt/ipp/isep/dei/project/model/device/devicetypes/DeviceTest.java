package pt.ipp.isep.dei.project.model.device.devicetypes;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.TestUtils;
import pt.ipp.isep.dei.project.model.Room;
import pt.ipp.isep.dei.project.model.device.Device;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * device tests class.
 */

public class DeviceTest {

    @Test
    public void getDeviceTypeTest() {
        Device d = new Device("WMOne", 12, TestUtils.PATH_TO_WASHINGMACHINE);
        d.setAttributeValue("capacity", 12D);
        String dT = "WashingMachine";
        String expectedResult = dT;
        String result = d.getType();
        assertEquals(expectedResult, result);
    }

    @Test
    void seeEqualToSameObject() {
        Device d = new Device("WMOne", 12, TestUtils.PATH_TO_WASHINGMACHINE);
        d.setAttributeValue("capacity", 12D);
        boolean actualResult = d.equals(d);
        assertTrue(actualResult);
    }

    @Test
    void seeEqualsToDifObject() {
        Device d = new Device("WMOne", 12, TestUtils.PATH_TO_WASHINGMACHINE);
        d.setAttributeValue(TestUtils.WM_CAPACITY, 34);
        Device d2 = new Device("WMTwo", 12, TestUtils.PATH_TO_WASHINGMACHINE);
        d.setAttributeValue(TestUtils.WM_CAPACITY, 45);

        boolean actualResult = d.equals(d2);
        assertFalse(actualResult);
    }


    @Test
    void seeEqualsToDifTypeObject() {
        Device d = new Device("WMOne", 12, TestUtils.PATH_TO_WASHINGMACHINE);
        d.setAttributeValue(TestUtils.WM_CAPACITY, 56);
        Room room = new Room("quarto", 1, 80, 2, 2);

        boolean actualResult = d.equals(room);
        assertFalse(actualResult);
    }

    @Test
    void seeEqualsToNullObject() {
        Device d = new Device("WMOne", 12, TestUtils.PATH_TO_WASHINGMACHINE);
        d.setAttributeValue(TestUtils.WM_CAPACITY, 34);
        boolean actualResult = d.equals(null);

        assertFalse(actualResult);
    }

    @Test
    void seeIfPrintDeviceWorks() {
        Device d1 = new Device("frigo", 150, TestUtils.PATH_TO_FRIDGE);
        d1.setAttributeValue(TestUtils.F_FREEZER_CAPACITY, 2D);
        d1.setAttributeValue(TestUtils.F_REFRIGERATOR_CAPACITY, 2D);
        d1.setAttributeValue(TestUtils.F_ANNUAL_CONSUMPTION, 45D);
        Room room = new Room("kitchen", 1, 1, 1, 1);
        String result = d1.buildDeviceString();
        String expectedResult = "The device Name is frigo, and its NominalPower is 150.0 kW.\n";
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfSetNameWorks() {
        Device d1 = new Device("frigo", 150, TestUtils.PATH_TO_FRIDGE);
        d1.setAttributeValue(TestUtils.F_FREEZER_CAPACITY, 2D);
        d1.setAttributeValue(TestUtils.F_REFRIGERATOR_CAPACITY, 2D);
        d1.setAttributeValue(TestUtils.F_ANNUAL_CONSUMPTION, 23D);
        d1.setmName("frigorifico");
        String result = d1.getName();
        String expectedResult = "frigorifico";
        assertEquals(expectedResult, result);
    }

    @Test
    public void hashCodeDummyTest() {
        Device d1 = new Device("FridgeTwo", 12, TestUtils.PATH_TO_FRIDGE);
        d1.setAttributeValue(TestUtils.F_FREEZER_CAPACITY, 4D);
        d1.setAttributeValue(TestUtils.F_REFRIGERATOR_CAPACITY, 56D);
        d1.setAttributeValue(TestUtils.F_ANNUAL_CONSUMPTION, 345D);
        int expectedResult = 1;
        int actualResult = d1.hashCode();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetAndSetAttributeValue() {
        Device d1 = new Device("heater", 150, TestUtils.PATH_TO_WATERHEATER);
        d1.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 12D);
        d1.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 40D);
        d1.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 234D);
        Double expectedResult = 33.3;
        d1.setAttributeValue("volumeOfWater", 33.3);
        Object result = d1.getAttributeValue("volumeOfWater");
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAttributeNames() {
        Device d1 = new Device("heater", 150, TestUtils.PATH_TO_WATERHEATER);
        d1.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 12D);
        d1.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 40D);
        d1.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 234D);
        List<String> result = d1.getAttributeNames();
        assertTrue(result.contains("volumeOfWater"));
        assertTrue(result.contains("hotWaterTemperature"));
        assertTrue(result.contains("performanceRatio"));
        assertEquals(result.size(), 3);
    }
    
    @Test
    void ensureThatWeDeactivateADevice(){
        Device d1 = new Device("heater", 150, TestUtils.PATH_TO_WATERHEATER);
        d1.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 12D);
        d1.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 40D);
        d1.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 234D);
        boolean expectedResult = true;
        boolean actualResult = d1.deactivate();
        assertEquals(expectedResult,actualResult);
    }

    @Test
    void ensureThatWeDoNotDeactivate(){
        Device d1 = new Device("heater", 150, TestUtils.PATH_TO_WATERHEATER);
        d1.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 12D);
        d1.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 40D);
        d1.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 234D);
        d1.deactivate();
        boolean expectedResult = false;
        boolean actualResult = d1.deactivate();
        assertEquals(expectedResult,actualResult);
    }
}
