package pt.ipp.isep.dei.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testng.Assert;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.DeviceList;
import pt.ipp.isep.dei.project.model.device.Log;
import pt.ipp.isep.dei.project.model.device.LogList;
import pt.ipp.isep.dei.project.model.device.devicespecs.FridgeSpec;
import pt.ipp.isep.dei.project.model.device.devicespecs.LampSpec;
import pt.ipp.isep.dei.project.model.device.devicespecs.WashingMachineSpec;
import pt.ipp.isep.dei.project.model.device.devicespecs.WaterHeaterSpec;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.testng.Assert.*;

/**
 * EnergyGrid tests class.
 */

class EnergyGridTest {

    // Common artifacts for testing in this class.
    public static final String PATH_TO_FRIDGE = "pt.ipp.isep.dei.project.model.device.devicetypes.FridgeDT";


    @Test
    void seeIfPrintGridWorks() {
        Reading r1 = new Reading(20, new GregorianCalendar(2018, GregorianCalendar.DECEMBER, 30).getTime());
        ReadingList readingList = new ReadingList();
        readingList.addReading(r1);
        Device device = new Device(new FridgeSpec());
        device.setNominalPower(200.0);
        device.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 200D);
        device.setAttributeValue(FridgeSpec.REFRIGERATOR_CAPACITY, 200D);
        device.setAttributeValue(FridgeSpec.ANNUAL_CONSUMPTION, 200D);
        DeviceList deviceList = new DeviceList();
        deviceList.addDevice(device);
        EnergyGrid energyGrid = new EnergyGrid("grid", 0);
        String result = energyGrid.buildGridString();
        assertEquals("Energy Grid: grid, Max Power: 0.0", result);
    }

    @Test
    void seeIfGetListOfRoomsWorks() {
        Room room = new Room("room1", 1, 1, 2, 2);
        RoomList roomList = new RoomList();
        roomList.addRoom(room);
        Reading r1 = new Reading(20, new GregorianCalendar(2018, GregorianCalendar.DECEMBER, 30).getTime());
        ReadingList readingList = new ReadingList();
        readingList.addReading(r1);
        Device device = new Device(new FridgeSpec());
        device.setNominalPower(200.0);
        device.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 200D);
        device.setAttributeValue(FridgeSpec.REFRIGERATOR_CAPACITY, 200D);
        device.setAttributeValue(FridgeSpec.ANNUAL_CONSUMPTION, 200D);
        DeviceList deviceList = new DeviceList();
        deviceList.addDevice(device);
        EnergyGrid energyGrid = new EnergyGrid("grid", 0);
        energyGrid.setRoomList(roomList);
        String result = energyGrid.getListOfRooms().buildRoomsString();
        assertEquals("---------------\n" +
                "0) Designation: room1 | House Floor: 1 | Width: 1.0 | Length: 2.0 | Height: 2.0\n" +
                "---------------\n", result);
    }

    @Test
    void seeIfAddPowerToListSourceWorks() {
        PowerSourceList pWL1 = new PowerSourceList();
        PowerSource pS1 = new PowerSource("topfloor", 22, 15);
        PowerSource pS2 = new PowerSource("downfloor", 22, 15);
        EnergyGrid energyGrid = new EnergyGrid("grid", 0);
        energyGrid.setListPowerSources(pWL1);
        pWL1.addPowerSource(pS1);
        boolean expectedResult = true;
        boolean result = energyGrid.addPowerSource(pS2);
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfAddPowerToListSourceFails() {
        PowerSourceList pWL1 = new PowerSourceList();
        PowerSource pS1 = new PowerSource("downfloor", 22, 15);
        PowerSource pS2 = new PowerSource("downfloor", 22, 15);
        EnergyGrid energyGrid = new EnergyGrid("grid", 0);
        energyGrid.setListPowerSources(pWL1);
        pWL1.addPowerSource(pS1);
        boolean expectedResult = false;
        boolean result = energyGrid.addPowerSource(pS2);
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfRemovesRoom() {
        Room room = new Room("room1", 1, 1, 2, 2);
        RoomList roomList = new RoomList();
        roomList.addRoom(room);
        PowerSourceList pWL1 = new PowerSourceList();
        PowerSource pS1 = new PowerSource("downfloor", 22, 15);
        EnergyGrid energyGrid = new EnergyGrid("grid", 0);
        energyGrid.setRoomList(roomList);
        energyGrid.setListPowerSources(pWL1);
        pWL1.addPowerSource(pS1);
        boolean expectedResult = true;
        boolean result = energyGrid.removeRoom(room);
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfRemovesRoomFails() {
        Room room = new Room("room1", 1, 1, 2, 2);
        Room room2 = new Room("room2", 1, 1, 2, 2);
        RoomList roomList = new RoomList();
        roomList.addRoom(room);
        PowerSourceList pWL1 = new PowerSourceList();
        PowerSource pS1 = new PowerSource("downfloor", 22, 15);
        EnergyGrid energyGrid = new EnergyGrid("grid", 0);
        energyGrid.setRoomList(roomList);
        energyGrid.setListPowerSources(pWL1);
        pWL1.addPowerSource(pS1);
        boolean expectedResult = false;
        boolean result = energyGrid.removeRoom(room2);
        assertEquals(expectedResult, result);
    }

    @Test
    void ensureThatAObjectIsAInstanceOf() {
        EnergyGrid energyGrid = new EnergyGrid("topFloor", 0);
        EnergyGrid energyGrid2 = new EnergyGrid("topFloor", 0);
        Boolean expectedResult = true;
        Boolean actualResult = energyGrid.equals(energyGrid2);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void ensureThatAnObjectIsNotAInstanceOf() {
        EnergyGrid energyGrid = new EnergyGrid("topFloor", 0);
        EnergyGrid energyGrid2 = new EnergyGrid("downFloor", 0);
        Boolean expectedResult = false;
        Boolean actualResult = energyGrid.equals(energyGrid2);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetListPowerSourcesIsSuccessful() {
        EnergyGrid energyGrid = new EnergyGrid("topFloor", 0);
        PowerSource powerSource = new PowerSource("PS1", 400, 400);
        PowerSourceList powerSourceList = new PowerSourceList();
        energyGrid.setListPowerSources(powerSourceList);
        energyGrid.addPowerSource(powerSource);
        PowerSourceList expectedResult = new PowerSourceList();
        expectedResult.addPowerSource(powerSource);
        PowerSourceList result = energyGrid.getListPowerSources();
        assertEquals(expectedResult, result);
    }

    @Test
    void testAddRoomToAnEnergyGrid() {
        EnergyGrid energyGrid = new EnergyGrid("topFloor", 0);
        PowerSource powerSource = new PowerSource("PS1", 400, 400);
        PowerSourceList powerSourceList = new PowerSourceList();
        RoomList roomList = new RoomList();
        Room room = new Room("Quarto", 2, 10, 20, 3);
        energyGrid.setListPowerSources(powerSourceList);
        energyGrid.addPowerSource(powerSource);
        energyGrid.setRoomList(roomList);
        boolean expectedResult = true;
        boolean result = energyGrid.addRoomToAnEnergyGrid(room);
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfEqualsEnergyGridToSameObject() {
        EnergyGrid energyGrid1 = new EnergyGrid("EG1", 400);
        EnergyGrid energyGrid2 = new EnergyGrid("EG1", 400);
        boolean actualResult = energyGrid1.equals(energyGrid2);
        assertTrue(actualResult);
    }

    @Test
    void seeIfEqualsEnergyGridToObject() {
        EnergyGrid energyGrid1 = new EnergyGrid("EG1", 400);
        boolean actualResult = energyGrid1.equals(energyGrid1);
        assertTrue(actualResult);
    }

    @Test
    void seeIfFalseWhenObjectsAreDifferentWithDifferentContent() {
        Room room = new Room("Quarto", 2, 10, 20, 2);
        EnergyGrid energyGrid1 = new EnergyGrid("EG1", 400);
        boolean actualResult = energyGrid1.equals(room);
        assertFalse(actualResult);
    }

    @Test
    void seeIfGetNominalPowerWorksMultipleRooms() {

        //Arrange
        Room r1 = new Room("Kitchen", 0, 12, 30, 10);
        Room r2 = new Room("Sótão", 3, 30, 40, 12);
        Device d1 = new Device(new WaterHeaterSpec());
        d1.setNominalPower(30.0);
        d1.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 12D);
        d1.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 40D);
        d1.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 234D);
        Device d2 = new Device(new FridgeSpec());
        d2.setNominalPower(200.0);
        d2.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 3D);
        d2.setAttributeValue(FridgeSpec.REFRIGERATOR_CAPACITY, 3D);
        d2.setAttributeValue(FridgeSpec.ANNUAL_CONSUMPTION, 45D);
        DeviceList deviceList1 = new DeviceList();
        deviceList1.addDevice(d1);
        r1.setDeviceList(deviceList1);
        DeviceList deviceList2 = new DeviceList();
        deviceList2.addDevice(d2);
        r2.setDeviceList(deviceList2);
        RoomList roomList = new RoomList();
        roomList.addRoom(r1);
        roomList.addRoom(r2);
        EnergyGrid grid = new EnergyGrid("EG1", 400);
        grid.setRoomList(roomList);
        double expectedResult = 230;

        //Act
        double actualResult = grid.getNominalPower();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfPrintDevicesWorks() {
        Device d1 = new Device(new FridgeSpec());
        d1.setNominalPower(21.0);
        d1.setName("Fridge");
        d1.setNominalPower(21.0);
        d1.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 2D);
        d1.setAttributeValue(FridgeSpec.REFRIGERATOR_CAPACITY, 2D);
        d1.setAttributeValue(FridgeSpec.ANNUAL_CONSUMPTION, 34D);
        Device d2 = new Device(new WashingMachineSpec());
        d2.setNominalPower(30.0);
        d2.setAttributeValue("capacity", 24D);
        d2.setName("WashingMachine");
        d2.setNominalPower(30.0);
        DeviceList deviceList = new DeviceList();
        deviceList.addDevice(d1);
        deviceList.addDevice(d2);
        EnergyGrid energyGrid = new EnergyGrid("EG1", 400);
        Room r1 = new Room("Kitchen", 0, 21, 31, 10);
        r1.setDeviceList(deviceList);
        RoomList roomList = new RoomList();
        roomList.addRoom(r1);
        energyGrid.setRoomList(roomList);
        String expectedResult = "0) The device Name is Fridge, and its NominalPower is 21.0 kW.\n" +
                "1) The device Name is WashingMachine, and its NominalPower is 30.0 kW.\n";

        String actualResult = energyGrid.buildDeviceListString();

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfPrintRoomListWorks() {
        EnergyGrid grid = new EnergyGrid("Main Energy Grid Edificio C", 333);
        String result = grid.buildRoomListString();
        Assert.assertEquals("Invalid List - List is Empty\n", result);
    }

    @Test
    void seeIfFalseWhenObjectsAreNull() {
        EnergyGrid energyGrid1 = new EnergyGrid("EG1", 400);
        boolean actualResult = energyGrid1.equals(null);
        assertFalse(actualResult);
    }

    @Test
    void hashCodeDummyTest() {
        EnergyGrid energyGrid = new EnergyGrid("EG1", 400);
        int expectedResult = 1;
        int result = energyGrid.hashCode();
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfDeviceListPrintsByTypeWithNullList() throws IOException {
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 4), new GeographicArea("porto", new TypeArea("cidade"), 2, 3, new Local(4, 4, 100)), 60, 180, deviceTypeString);
        Room room1EdC = new Room("B107", 1, 7, 11, 3.5);
        EnergyGrid eg = new EnergyGrid("Main Energy Grid Edificio C", 333);
        RoomList rl = new RoomList();
        DeviceList deviceList = new DeviceList();
        room1EdC.setDeviceList(deviceList);
        eg.setRoomList(rl);
        rl.addRoom(room1EdC);
        String expectedResult = "---------------\n" + "---------------\n";
        String result = eg.buildListOfDeviceByTypeString(eg, house);
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void seeIfDeviceListPrintsByTypeWithNullList2() throws IOException {
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 4), new GeographicArea("porto", new TypeArea("cidade"), 2, 3, new Local(4, 4, 100)), 60, 180, deviceTypeString);
        Room m = null;
        EnergyGrid eg = new EnergyGrid("Main Energy Grid Edificio C", 333);
        RoomList rl = new RoomList();
        rl.addRoom(m);
        eg.setRoomList(rl);
        String expectedResult = "---------------\n" + "---------------\n";
        String result = eg.buildListOfDeviceByTypeString(eg, house);
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void energyConsumptionDummyTest() {
        EnergyGrid eg = new EnergyGrid("Main Energy Grid Edificio C", 333);
        double result = eg.getEnergyConsumption(10);
        assertEquals(result, 0);
    }

    @Test
    void seeIfDeviceListPrintsByTypeWithNullList3() {
        Room m = new Room("room", 2, 2, 2, 2);
        EnergyGrid eg = new EnergyGrid("Main Energy Grid Edificio C", 333);
        RoomList rl = new RoomList();
        rl.addRoom(m);
        eg.setRoomList(rl);
        List<Device> dlist = eg.getDeviceList();
        Device d1 = new Device(new FridgeSpec());
        dlist.add(d1);
        List<Device> expectedResult = eg.getDeviceList();
        List<Device> result = eg.getDeviceList();
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetsLogInInterval() {
        EnergyGrid eg = new EnergyGrid("Main Energy Grid Edificio C", 333);
        Date date1 = new GregorianCalendar(2018, Calendar.JANUARY, 11, 15, 30, 26).getTime();
        Date date2 = new GregorianCalendar(2018, Calendar.MARCH, 11, 15, 30, 26).getTime();
        Log log = new Log(300, new GregorianCalendar(2018, Calendar.FEBRUARY, 20, 10, 0).getTime(), new GregorianCalendar(2018, Calendar.FEBRUARY, 20, 10, 30).getTime());

        Device device4 = new Device(new LampSpec());
        device4.setNominalPower(4.0);
        device4.setName("Lamp");
        device4.setAttributeValue("luminousFlux", 23D);
        device4.addLog(log);
        Room room1 = new Room("wc", 2, 2, 2, 2);
        eg.addRoomToAnEnergyGrid(room1);
        room1.addDevice(device4);
        LogList logList = new LogList();
        logList.addLog(log);

        LogList result = eg.getLogsInInterval(date1, date2);
        LogList actualResult = logList;
        assertEquals(result, actualResult);
    }

    @Test
    void seeIfDoesNotGetLogInInterval() {
        EnergyGrid eg = new EnergyGrid("Main Energy Grid Edificio C", 333);
        Date date1 = new GregorianCalendar(2018, Calendar.JANUARY, 11, 15, 30, 26).getTime();
        Date date2 = new GregorianCalendar(2018, Calendar.MARCH, 11, 15, 30, 26).getTime();
        Log log = new Log(300, new GregorianCalendar(2018, Calendar.FEBRUARY, 20, 10, 0).getTime(), new GregorianCalendar(2018, Calendar.FEBRUARY, 20, 10, 30).getTime());

        Device device4 = new Device(new LampSpec());
        device4.setNominalPower(4.0);
        device4.setName("Lamp");
        device4.addLog(log);
        device4.setAttributeValue("luminousFlux", 23D);
        Room room1 = new Room("wc", 2, 2, 2, 2);
        eg.addRoomToAnEnergyGrid(room1);
        room1.addDevice(device4);
        LogList listTest = new LogList();
        Log logTest = new Log(300, new GregorianCalendar(2018, Calendar.FEBRUARY, 20, 10, 0).getTime(), new GregorianCalendar(2018, Calendar.FEBRUARY, 20, 10, 30).getTime());
        listTest.addLog(logTest);


        LogList result = eg.getLogsInInterval(date1, date2);
        LogList actualResult = listTest;
        assertNotEquals(result, actualResult);
    }

    @Test
    void seeIfGetsLogInInterval2() {
        EnergyGrid eg = new EnergyGrid("Main Energy Grid Edificio C", 333);
        Date date1 = new GregorianCalendar(2019, Calendar.JANUARY, 11, 15, 30, 26).getTime();
        Date date2 = new GregorianCalendar(2018, Calendar.MARCH, 11, 15, 30, 26).getTime();
        Log log = new Log(300, new GregorianCalendar(2018, Calendar.FEBRUARY, 20, 10, 0).getTime(), new GregorianCalendar(2018, Calendar.FEBRUARY, 20, 10, 30).getTime());

        Device device4 = new Device(new LampSpec());
        device4.setNominalPower(4.0);
        device4.setName("Lamp");
        device4.addLog(log);
        device4.setAttributeValue("luminousFlux", 23D);
        Room room1 = new Room("wc", 2, 2, 2, 2);
        eg.addRoomToAnEnergyGrid(room1);
        room1.addDevice(device4);
        LogList listTest = new LogList();
        Log logTest = new Log(300, new GregorianCalendar(2018, Calendar.FEBRUARY, 20, 10, 0).getTime(), new GregorianCalendar(2018, Calendar.FEBRUARY, 20, 10, 30).getTime());
        listTest.addLog(logTest);

        LogList result = eg.getLogsInInterval(date1, date2);
        LogList actualResult = listTest;
        assertNotEquals(result, actualResult);
    }

    @Test
    void seeIfSetsNegativeMaxContractedPower() {
        EnergyGrid eg = new EnergyGrid("Main Energy Grid Edificio C", 0);
        boolean result = eg.setMaxContractedPower(-1);
        assertFalse(result);
    }

    @Test
    void seeIfSetsMaxContractedPowerZero() {
        EnergyGrid eg = new EnergyGrid("Main Energy Grid Edificio C", 5);
        boolean result = eg.setMaxContractedPower(0);
        assertTrue(result);
    }

    @Test
    void seeIfGetEnergyConsumption() {
        EnergyGrid eg = new EnergyGrid("Main Energy Grid Edificio C", 333);
        double expectedResult = 0;
        double value = eg.getEnergyConsumption(21);
        assertEquals(expectedResult, value);
    }
}