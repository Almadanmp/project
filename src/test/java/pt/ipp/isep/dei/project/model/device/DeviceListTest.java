package pt.ipp.isep.dei.project.model.device;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.RoomList;
import pt.ipp.isep.dei.project.model.device.devicespecs.FridgeSpec;
import pt.ipp.isep.dei.project.model.device.devicespecs.WashingMachineSpec;
import pt.ipp.isep.dei.project.model.device.log.Log;
import pt.ipp.isep.dei.project.model.device.log.LogList;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * DeviceList tests class.
 */

class DeviceListTest {
    // Common testing artifacts for testing in this class.

    private DeviceList validList;
    private Device firstValidDevice;

    @BeforeEach
    void arrangeArtifacts() {
        validList = new DeviceList();
        firstValidDevice = new Fridge(new FridgeSpec());
        firstValidDevice.setName("FridgeOne");
        firstValidDevice.setNominalPower(30);
    }

    @Test
    void seeIfAddDeviceWorksDuplicate() {
        // Arrange

        validList.add(firstValidDevice);

        // Act

        boolean result = validList.add(firstValidDevice);

        // Assert

        assertFalse(result);
    }

    @Test
    void seeIfAddDeviceWorks() {
        // Act

        boolean result = validList.add(firstValidDevice);

        // Assert

        assertTrue(result);
    }

    @Test
    void seeIfContainsDeviceWorks() {
        // Arrange

        validList.add(firstValidDevice);

        // Act

        boolean result = validList.containsDevice(firstValidDevice);

        // Assert

        assertTrue(result);
    }

    @Test
    void seeIfContainsDeviceWorksFalse() {
        // Act

        boolean result = validList.containsDevice(firstValidDevice);

        // Assert

        assertFalse(result);
    }

    @Test
    void seeIfRemoveDeviceWorks() {
        // Arrange

        validList.add(firstValidDevice);

        // Act

        boolean result = validList.removeDevice(firstValidDevice);

        // Assert

        assertTrue(result);
    }

    @Test
    void seeIfRemoveDeviceWorksForFalse() {
        // Act

        boolean result = validList.removeDevice(firstValidDevice);

        // Assert

        assertFalse(result);
    }

    @Test
    void seeIfIsEmptyWorksFalse() {
        // Arrange

        validList.add(firstValidDevice);

        // Act

        boolean result = validList.isEmpty();

        // Assert

        assertFalse(result);
    }

    @Test
    void seeIfIsEmptyWorks() {
        // Act

        boolean result = validList.isEmpty();

        // Assert

        assertTrue(result);
    }

    @Test
    void seeIfEqualsWorksOnItself() {
        assertEquals(validList, validList);
    }


    @Test
    void seeIfEqualsWorksTrue() {
        // Arrange

        DeviceList testList = new DeviceList();
        validList.add(firstValidDevice);
        testList.add(firstValidDevice);

        // Act

        boolean actualResult = validList.equals(testList);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfEqualsWorksNotAnInstance() {
        assertNotEquals(validList, new RoomList());
    }

    @Test
    void seeIfBuildStringWorks() {
        // Arrange

        validList.add(firstValidDevice);
        String expectedResult = "---------------\n" +
                "0) device Name: FridgeOne, device Type: Fridge, device Nominal Power: 30.0\n" +
                "---------------\n";

        // Act

        String actualResult = validList.buildString();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testGetterElementsAsArray() {
        //Arrange

        // Device

        Device testDevice = new Fridge(new FridgeSpec());
        testDevice.setName("Fridge2");
        testDevice.setNominalPower(21.0);
        testDevice.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 4D);
        testDevice.setAttributeValue(FridgeSpec.REFRIGERATOR_CAPACITY, 7D);
        testDevice.setAttributeValue(FridgeSpec.ANNUAL_CONSUMPTION, 435D);

        // Lists

        DeviceList emptyList = new DeviceList();
        DeviceList oneDeviceList = new DeviceList();
        oneDeviceList.add(firstValidDevice);
        DeviceList twoDevicesList = new DeviceList();
        twoDevicesList.add(firstValidDevice);
        twoDevicesList.add(testDevice);

        // Expected Results

        Device[] expectedResult1 = new Device[0];
        Device[] expectedResult2 = new Device[1];
        Device[] expectedResult3 = new Device[2];
        expectedResult2[0] = firstValidDevice;
        expectedResult3[0] = firstValidDevice;
        expectedResult3[1] = testDevice;

        // Act

        Device[] actualResult1 = emptyList.getElementsAsArray();
        Device[] actualResult2 = oneDeviceList.getElementsAsArray();
        Device[] actualResult3 = twoDevicesList.getElementsAsArray();

        // Assert

        assertArrayEquals(expectedResult1, actualResult1);
        assertArrayEquals(expectedResult2, actualResult2);
        assertArrayEquals(expectedResult3, actualResult3);
    }

    @Test
    void seeIfAppendListNoDuplicatesWorks() {
        // Arrange

        // Device

        Device testDevice = new Fridge(new FridgeSpec());
        testDevice.setName("Fridge2");
        testDevice.setNominalPower(21.0);

        // List

        validList.add(firstValidDevice);
        DeviceList listToAppend = new DeviceList();
        listToAppend.add(testDevice);

        // Expected result.

        DeviceList expectedResult = new DeviceList();
        expectedResult.add(firstValidDevice);
        expectedResult.add(testDevice);

        //Act

        DeviceList actualResult = validList.appendListNoDuplicates(listToAppend);

        //Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfAppendListNoDuplicatesWorksWithDuplicates() {
        // Arrange

        // List

        validList.add(firstValidDevice);
        DeviceList listToAppend = new DeviceList();
        listToAppend.add(firstValidDevice);

        // Expected result.

        DeviceList expectedResult = new DeviceList();
        expectedResult.add(firstValidDevice);

        //Act

        DeviceList actualResult = validList.appendListNoDuplicates(listToAppend);

        //Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfAppendListNoDuplicatesWorksEmpty() {
        // Arrange

        // List

        validList.add(firstValidDevice);
        DeviceList listToAppend = new DeviceList();

        // Expected result.

        DeviceList expectedResult = new DeviceList();
        expectedResult.add(firstValidDevice);

        //Act

        DeviceList actualResult = validList.appendListNoDuplicates(listToAppend);

        //Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetWorks() {
        // Arrange

        Device testDevice = new Fridge(new FridgeSpec());
        testDevice.setName("Fridge2");
        testDevice.setNominalPower(21.0);
        testDevice.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 4D);
        testDevice.setAttributeValue(FridgeSpec.REFRIGERATOR_CAPACITY, 7D);
        testDevice.setAttributeValue(FridgeSpec.ANNUAL_CONSUMPTION, 435D);

        Device secondTestDevice = new Fridge(new FridgeSpec());
        testDevice.setName("Fridge3");
        testDevice.setNominalPower(21.0);
        testDevice.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 10D);
        testDevice.setAttributeValue(FridgeSpec.REFRIGERATOR_CAPACITY, 70D);
        testDevice.setAttributeValue(FridgeSpec.ANNUAL_CONSUMPTION, 45D);

        validList.add(firstValidDevice);
        validList.add(testDevice);
        validList.add(secondTestDevice);

        // Act

        Device actualResult1 = validList.get(0);
        Device actualResult2 = validList.get(1);
        Device actualResult3 = validList.get(2);

        // Assert

        assertEquals(firstValidDevice, actualResult1);
        assertEquals(testDevice, actualResult2);
        assertEquals(secondTestDevice, actualResult3);
    }

    @Test
    void seeIfGetWorksEmptyList() {
        // Act

        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> validList.get(0));

        // Assert

        assertEquals("The device list is empty.", exception.getMessage());
    }

    @Test
    void seeIfSizeWorks() {
        // Act

        int actualResult1 = validList.size();

        // Arrange to addWithoutPersisting one device.

        validList.add(firstValidDevice);

        // Act

        int actualResult2 = validList.size();

        // Arrange to addWithoutPersisting second device.

        Device testDevice = new Fridge(new FridgeSpec());
        testDevice.setName("Fridge2");
        testDevice.setNominalPower(21.0);
        validList.add(testDevice);

        // Act

        int actualResult3 = validList.size();

        //Assert

        assertEquals(0, actualResult1);
        assertEquals(1, actualResult2);
        assertEquals(2, actualResult3);
    }

    @Test
    void seeIfGetTypeByIndexWorks() {
        // Arrange

        Device testDevice = new WashingMachine(new WashingMachineSpec());
        validList.add(firstValidDevice);
        validList.add(testDevice);

        //Act

        String actualResult1 = validList.getTypeByIndex(0);
        String actualResult2 = validList.getTypeByIndex(1);

        //Assert

        assertEquals("Fridge", actualResult1);
        assertEquals("Washing Machine", actualResult2);
    }

    @Test
    void seeIfGetTypeByIndexWorksEmpty() {
        //Act

        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> validList.getTypeByIndex(0));

        //Assert

        assertEquals("The device list is empty.", exception.getMessage());
    }

    @Test
    void seeIfGetConsumptionInIntervalWorks() {
        // Arrange

        double expectedResult = 21;

        // Logs

        Log firstLog = new Log(21, new GregorianCalendar(2019, Calendar.JANUARY, 21).getTime(),
                new GregorianCalendar(2018, Calendar.SEPTEMBER, 21).getTime());
        firstValidDevice.addLog(firstLog);
        validList.add(firstValidDevice);

        // Interval

        Date initialTime = new GregorianCalendar(2019, Calendar.JANUARY, 20, 10, 0,
                0).getTime();
        Date finalTime = new GregorianCalendar(2019, Calendar.FEBRUARY, 20, 11, 0,
                0).getTime();

        // Act

        double actualResult = validList.getConsumptionInInterval(initialTime, finalTime);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetConsumptionInIntervalWorksOutOfBounds() {
        // Arrange

        firstValidDevice = new Fridge(new FridgeSpec());
        validList.add(firstValidDevice);
        double expectedResult = 0.0;

        // Interval

        Date initialTime = new GregorianCalendar(2018, Calendar.SEPTEMBER, 20, 10, 0,
                0).getTime();
        Date finalTime = new GregorianCalendar(2018, Calendar.SEPTEMBER, 20, 11, 0,
                0).getTime();

        // Logs

        Log firstLog = new Log(21, new GregorianCalendar(2018, Calendar.SEPTEMBER, 19).getTime(),
                new GregorianCalendar(2018, Calendar.SEPTEMBER, 21).getTime());
        firstValidDevice.addLog(firstLog);

        // Act

        double actualResult = firstValidDevice.getConsumptionInInterval(initialTime, finalTime);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetLogsInIntervalWorks() {
        // Arrange

        LogList expectedResultList = new LogList();
        Log validLog = new Log(1, new GregorianCalendar(2019, Calendar.FEBRUARY, 1).getTime(),
                new GregorianCalendar(2019, Calendar.FEBRUARY, 1).getTime());
        expectedResultList.addLog(validLog);
        firstValidDevice.addLog(validLog);
        validList.add(firstValidDevice);

        // Interval

        Date initialTime = new GregorianCalendar(2019, Calendar.JANUARY, 20, 10, 0,
                0).getTime();
        Date finalTime = new GregorianCalendar(2019, Calendar.FEBRUARY, 20, 11, 0,
                0).getTime();

        // Act

        LogList actualResultList = validList.getLogsInInterval(initialTime, finalTime);

        // Assert

        assertEquals(expectedResultList, actualResultList);
    }

    @Test
    void seeIfGetLogsInIntervalWorksOutOfBounds() {
        // Arrange

        firstValidDevice = new Fridge(new FridgeSpec());
        validList.add(firstValidDevice);
        LogList expectedResult = new LogList();

        // Interval

        Date initialTime = new GregorianCalendar(2018, Calendar.SEPTEMBER, 20, 10, 0,
                0).getTime();
        Date finalTime = new GregorianCalendar(2018, Calendar.SEPTEMBER, 20, 11, 0,
                0).getTime();

        // Logs

        Log firstLog = new Log(21, new GregorianCalendar(2018, Calendar.SEPTEMBER, 19).getTime(),
                new GregorianCalendar(2018, Calendar.SEPTEMBER, 21).getTime());
        firstValidDevice.addLog(firstLog);

        // Act

        LogList actualResult = validList.getLogsInInterval(initialTime, finalTime);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfRemoveDevicesFromGivenListWorks() {
        // Arrange

        // Device.

        Device testDevice = new WashingMachine(new WashingMachineSpec());
        testDevice.setName("WashingMachine");
        testDevice.setNominalPower(21.0);
        testDevice.setAttributeValue(WashingMachineSpec.WM_CAPACITY, 4D);

        // Create the Lists.

        DeviceList emptyList = new DeviceList();      // empty list.
        DeviceList oneDeviceList = new DeviceList();  // list with one of the devices already contained in ValidList.
        DeviceList twoDevicesList = new DeviceList(); // list with both of the devices already contained in ValidList.

        // Populate the lists.

        validList.add(firstValidDevice);
        validList.add(testDevice);
        oneDeviceList.add(firstValidDevice);
        twoDevicesList.add(firstValidDevice);
        twoDevicesList.add(testDevice);

        // Act

        boolean actualResult1 = validList.removeDevicesFromGivenList(emptyList);
        boolean actualResult2 = validList.removeDevicesFromGivenList(oneDeviceList);
        boolean actualResult3 = validList.removeDevicesFromGivenList(twoDevicesList);

        // Assert

        assertTrue(actualResult1);
        assertTrue(actualResult2);
        assertTrue(actualResult3);
        assertEquals(emptyList, oneDeviceList); //See if devices get removed
        assertEquals(emptyList, twoDevicesList); //See if devices get removed
    }

    @Test
    void hashCodeDummyTest() {
        // Arrange

        int expectedResult = 1;

        // Act

        int actualResult = validList.hashCode();

        // Assert
        assertEquals(expectedResult, actualResult);
    }
}

