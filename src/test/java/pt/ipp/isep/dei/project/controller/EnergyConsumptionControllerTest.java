package pt.ipp.isep.dei.project.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.DeviceList;
import pt.ipp.isep.dei.project.model.device.Fridge;
import pt.ipp.isep.dei.project.model.device.WaterHeater;
import pt.ipp.isep.dei.project.model.device.devicespecs.FridgeSpec;
import pt.ipp.isep.dei.project.model.device.devicespecs.WaterHeaterSpec;
import pt.ipp.isep.dei.project.model.device.log.Log;
import pt.ipp.isep.dei.project.model.device.log.LogList;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * EnergyConsumptionController tests class.
 */

class EnergyConsumptionControllerTest {

    // Common artifacts for testing in this class.

    private EnergyGrid validGrid = new EnergyGrid("validGrid", 300);
    private Room validRoom1; // Is a room with 3 valid devices.
    private Room validRoom2; // Is a room without devices.
    private Device validDevice1 = new WaterHeater(new WaterHeaterSpec());
    private Device validDevice2 = new WaterHeater(new WaterHeaterSpec());
    private Device validDevice3 = new Fridge(new FridgeSpec());
    private EnergyConsumptionController controller = new EnergyConsumptionController();
    private Log validLog1 = new Log(56, new GregorianCalendar(2018, Calendar.NOVEMBER, 20, 10, 10).getTime(),
            new GregorianCalendar(2018, Calendar.NOVEMBER, 20, 10, 50).getTime());
    public static final String PATH_TO_FRIDGE = "pt.ipp.isep.dei.project.model.device.devicetypes.FridgeDT";


    @BeforeEach
    void arrangeArtifacts() {
        validRoom1 = new Room("Kitchen", 0, 35, 40, 20);
        validRoom2 = new Room("Upstairs Bathroom", 2, 15, 20, 10);
        validDevice1.setName("WaterHeater");
        validDevice1.setNominalPower(21.0);
        validDevice1.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 12D);
        validDevice1.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 40D);
        validDevice1.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 234D);
        validDevice2.setName("WaterHeaterTwo");
        validDevice2.setNominalPower(55.0);
        validDevice2.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 12D);
        validDevice2.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 40D);
        validDevice2.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 234D);
        validDevice3.setName("Fridge");
        validDevice3.setNominalPower(10.0);
        validDevice3.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 5D);
        validDevice3.setAttributeValue(FridgeSpec.REFRIGERATOR_CAPACITY, 3D);
        validDevice3.setAttributeValue(FridgeSpec.ANNUAL_CONSUMPTION, 456D);
        validRoom1.addDevice(validDevice1);
        validRoom1.addDevice(validDevice2);
        validRoom1.addDevice(validDevice3);
    }

    //US705 TESTS

    @Test
    void seeIfRoomDevicesGetAddedToDeviceList() {

        //Arrange

        DeviceList deviceList1 = new DeviceList();

        //Act
        boolean actualResult = controller.addRoomDevicesToDeviceList(validRoom1, deviceList1);

        //Assert
        assertTrue(actualResult);

    }

    @Test
    void seeIfRoomDevicesGetRemovedFromDeviceList() {

        //Arrange

        DeviceList deviceList1 = new DeviceList();
        deviceList1.addDevice(validDevice1);
        deviceList1.addDevice(validDevice2);
        deviceList1.addDevice(validDevice3);

        //Act
        boolean actualResult = controller.removeRoomDevicesFromDeviceList(validRoom1, deviceList1);

        //Assert
        assertTrue(actualResult);

    }

    @Test
    void seeIfRemoveRoomDevicesFromDeviceListIsFalseNullList() {

        //Act
        boolean actualResult = controller.removeRoomDevicesFromDeviceList(validRoom1, null);

        //Assert
        assertFalse(actualResult);

    }

    @Test
    void seeIfAddRoomDevicesToDeviceListIsFalseNullList() {

        //Act
        boolean actualResult = controller.removeRoomDevicesFromDeviceList(validRoom1, null);

        //Assert
        assertFalse(actualResult);

    }

    @Test
    void seeIfAddRoomToListWorks() {

        //Arrange
        RoomList roomList = new RoomList();

        //Act
        boolean actualResult = controller.addRoomToList(validRoom1, roomList);

        //Assert
        assertTrue(actualResult);

    }

    @Test
    void seeIfRemoveRoomFromListWorks() {

        //Arrange
        RoomList roomList = new RoomList();
        roomList.addRoom(validRoom1);

        //Act
        boolean actualResult = controller.removeRoomFromList(validRoom1, roomList);

        //Assert
        assertTrue(actualResult);

    }

    @Test
    void seeIfRemoveRoomFromListWorksFalse() {

        //Arrange

        RoomList roomList = new RoomList();
        EnergyConsumptionController controller = new EnergyConsumptionController();

        //Act
        boolean actualResult = controller.removeRoomFromList(validRoom1, roomList);

        //Assert
        assertFalse(actualResult);

    }

    @Test
    void seeIfAddRoomToListWorksFalse() {

        //Arrange

        RoomList roomList = new RoomList();
        roomList.addRoom(validRoom1);

        //Act
        boolean actualResult = controller.addRoomToList(validRoom1, roomList);

        //Assert
        assertFalse(actualResult);

    }

    @Test
    void seeIfAddDeviceToListWorks() {

        //Arrange
        DeviceList deviceList = new DeviceList();

        //Act
        boolean actualResult = controller.addDeviceToList(validDevice1, deviceList);

        //Assert
        assertTrue(actualResult);

    }

    @Test
    void seeIfAddDeviceToListWorksFalse() {

        //Arrange

        DeviceList deviceList = new DeviceList();
        deviceList.addDevice(validDevice1);

        //Act
        boolean actualResult = controller.addDeviceToList(validDevice1, deviceList);

        //Assert
        assertFalse(actualResult);

    }

    @Test
    void seeIfRemoveDeviceFromListWorksFalse() {

        //Arrange

        DeviceList deviceList = new DeviceList();

        //Act
        boolean actualResult = controller.removeDeviceFromList(validDevice1, deviceList);

        //Assert
        assertFalse(actualResult);

    }

    @Test
    void seeIfRemoveDeviceFromListWorks() {

        //Arrange

        DeviceList deviceList = new DeviceList();
        deviceList.addDevice(validDevice1);

        //Act
        boolean actualResult = controller.removeDeviceFromList(validDevice1, deviceList);

        //Assert
        assertTrue(actualResult);

    }


    @Test
    void seeIfGetSumNominalPowerFromListWorks() {

        //Arrange

        DeviceList deviceList = new DeviceList();
        deviceList.addDevice(validDevice1);
        deviceList.addDevice(validDevice2);
        deviceList.addDevice(validDevice3);
        double expectedResult = 86;

        //Act
        double actualResult = controller.getSelectionNominalPower(deviceList);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetSumNominalPowerFromListWorksZero() {

        //Arrange

        DeviceList deviceList = new DeviceList();
        double expectedResult = 0;

        //Act
        double actualResult = controller.getSelectionNominalPower(deviceList);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    //US721 TESTS

    @Test
    void seeIfGetHouseRoomListWorks() {

        //Arrange
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        House house = new House("ISEP", "Rua Dr. António Bernardino de Almeida", "4200-072", "Porto", new Local(20, 20, 20), new GeographicArea("Porto", new TypeArea("Cidade"), 2, 3, new Local(4, 4, 100)), 60, 180, deviceTypeString);
        RoomList roomList = new RoomList();
        roomList.addRoom(validRoom1);
        roomList.addRoom(validRoom2);
        house.setRoomList(roomList);
        List<Room> expectedResult = new ArrayList<>();
        expectedResult.add(validRoom1);
        expectedResult.add(validRoom2);

        //Act

        List<Room> actualResult = controller.getHouseRoomList(house);

        //Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetHouseGridListWorks() {

        //Arrange
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        House house = new House("ISEP", "Rua Dr. António Bernardino de Almeida", "4200-072", "Porto",
                new Local(20, 20, 20), new GeographicArea("Porto", new TypeArea("Cidade"),
                2, 3, new Local(4, 4, 100)), 60, 180,
                deviceTypeString);
        EnergyGrid testGrid = new EnergyGrid("GridOne", 300);
        EnergyGridList houseGrid = new EnergyGridList();
        houseGrid.addGrid(testGrid);
        house.setEGList(houseGrid);
        List<EnergyGrid> expectedResult = new ArrayList<>();
        expectedResult.add(testGrid);

        //Act

        List<EnergyGrid> actualResult = controller.getHouseGridList(house);

        //Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetRoomConsumptionInIntervalWorks() {
        //Arrange

        Date initialTime = new GregorianCalendar(2018, Calendar.NOVEMBER, 20, 10, 2).getTime();
        Date finalTime = new GregorianCalendar(2018, Calendar.NOVEMBER, 20, 10, 60).getTime();
        validDevice1.addLog(validLog1);
        DeviceList deviceList = new DeviceList();
        deviceList.addDevice(validDevice1);
        validRoom1.setDeviceList(deviceList);
        double expectedResult = 56;

        //Act
        double actualResult = controller.getRoomConsumptionInInterval(validRoom1, initialTime, finalTime);


        //Assert
        assertEquals(expectedResult, actualResult);
    }


    //US752 TESTS

    @Test
    void getDailyHouseConsumptionTestZero() {

        //Arrange

        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        House house = new House("ISEP", "Rua Dr. António Bernardino de Almeida", "4200-072", "Porto",
                new Local(20, 20, 20), new GeographicArea("Porto", new TypeArea("Cidade"),
                2, 3, new Local(4, 4, 100)), 60, 180,
                deviceTypeString);
        validRoom1.addDevice(validDevice1);
        validRoom1.addDevice(validDevice2);
        validRoom2.addDevice(validDevice3);
        house.addRoomToRoomList(validRoom1);
        house.addRoomToRoomList(validRoom2);
        double expectedResult = 0.0;

        //Act

        double result = controller.getDailyWaterHeaterConsumption(house);

        //Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void getDailyHouseConsumptionNoRoomsTest() {

        //Arrange

        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        House house = new House("ISEP", "Rua Dr. António Bernardino de Almeida", "4200-072", "Porto",
                new Local(20, 20, 20), new GeographicArea("Porto", new TypeArea("Cidade"),
                2, 3, new Local(4, 4, 100)), 60, 180,
                deviceTypeString);
        double expectedResult = 0;

        //Act

        double result = controller.getDailyWaterHeaterConsumption(house);

        //Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void getDailyHouseConsumptionNoDevicesTest() {

        //Arrange

        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        House house = new House("ISEP", "Rua Dr. António Bernardino de Almeida", "4200-072",
                "Porto", new Local(20, 20, 20), new GeographicArea("Porto", new TypeArea
                ("Cidade"), 2, 3, new Local(4, 4, 100)), 60,
                180, deviceTypeString);
        house.addRoomToRoomList(validRoom1);
        house.addRoomToRoomList(validRoom2);
        double expectedResult = 0;

        //Act

        double result = controller.getDailyWaterHeaterConsumption(house);

        //Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void getDailyHouseConsumptionNoHeaterDevicesTest() {

        //Arrange

        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        House house = new House("ISEP", "Rua Dr. António Bernardino de Almeida", "4200-072",
                "Porto", new Local(20, 20, 20), new GeographicArea("Porto",
                new TypeArea("Cidade"), 2, 3, new Local(4, 4, 100)),
                60, 180, deviceTypeString);
        validRoom1.addDevice(validDevice1);
        validRoom2.addDevice(validDevice2);
        house.addRoomToRoomList(validRoom1);
        house.addRoomToRoomList(validRoom2);
        double expectedResult = 0;

        //Act

        double result = controller.getDailyWaterHeaterConsumption(house);

        //Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void getTotalNominalPowerFromGridTest() {

        //Arrange

        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        House house = new House("ISEP", "Rua Dr. António Bernardino de Almeida", "4200-072",
                "Porto", new Local(20, 20, 20), new GeographicArea("Porto",
                new TypeArea("Cidade"), 2, 3, new Local(4, 4, 100)),
                60, 180, deviceTypeString);
        RoomList roomList = new RoomList();
        validGrid.setRoomList(roomList);
        roomList.addRoom(validRoom2);
        roomList.addRoom(validRoom1);
        house.addRoomToRoomList(validRoom1);
        house.addRoomToRoomList(validRoom2);
        double expectedResult = 86;

        //Act

        double result = controller.getTotalPowerFromGrid(validGrid);

        //Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void getTotalNominalPowerFromGridNoDevicesTest() {

        //Arrange

        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        House house = new House("ISEP", "Rua Dr. António Bernardino de Almeida", "4200-072",
                "Porto", new Local(20, 20, 20), new GeographicArea("Porto",
                new TypeArea("Cidade"), 2, 3, new Local(4, 4, 100)),
                60, 180, deviceTypeString);
        RoomList roomList = new RoomList();
        validGrid.setRoomList(roomList);
        house.addRoomToRoomList(validRoom2);
        double expectedResult = 0;

        //Act

        double result = controller.getTotalPowerFromGrid(validGrid);

        //Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void getWaterHeaterDeviceListTest() {

        //Arrange

        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        House house = new House("ISEP", "Rua Dr. António Bernardino de Almeida", "4200-072",
                "Porto", new Local(20, 20, 20), new GeographicArea("Porto",
                new TypeArea("Cidade"), 2, 3, new Local(4, 4, 100)),
                60, 180, deviceTypeString);
        house.addRoomToRoomList(validRoom1);
        DeviceList deviceList = new DeviceList();
        deviceList.addDevice(validDevice1);
        validRoom1.setDeviceList(deviceList);
        List<Device> expecteResult = new ArrayList<>();
        expecteResult.add(validDevice1);

        //Act

        List<Device> result = controller.getWaterHeaterDeviceList(house);

        //Assert

        Assertions.assertEquals(expecteResult, result);
    }

    @Test
    void getWaterHeaterDeviceListTestTwoDevices() {

        //Arrange

        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        House house = new House("ISEP", "Rua Dr. António Bernardino de Almeida", "4200-072",
                "Porto", new Local(20, 20, 20), new GeographicArea("Porto",
                new TypeArea("Cidade"), 2, 3, new Local(4, 4, 100)),
                60, 180, deviceTypeString);
        validRoom1.addDevice(validDevice1);
        validRoom1.addDevice(validDevice2);
        house.addRoomToRoomList(validRoom1);
        List<Device> expectedResult = new ArrayList<>();
        expectedResult.add(validDevice1);
        expectedResult.add(validDevice2);

        //Act

        List<Device> result = controller.getWaterHeaterDeviceList(house);

        //Assert

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetWHNameWorks() {

        //Act

        String result = controller.getWHName(validDevice1);

        //Assert

        Assertions.assertEquals("WaterHeater", result);
    }

    @Test
    void configureOneHeaterTestFalse() {

        //Arrange

        Double attributeValue2 = 30.0;

        //Act

        boolean result = controller.configureWH(validDevice1, null, attributeValue2);

        //Assert

        assertFalse(result);
    }

    @Test
    void configureOneHeaterTestFalseSecondElement() {

        //Arrange

        Double attributeValue2 = 30.0;

        //Act

        boolean result = controller.configureWH(validDevice1, attributeValue2, null);

        //Assert

        assertFalse(result);
    }

    @Test
    void configureOneHeaterTestFalseBothElement() {

        //Act

        boolean result = controller.configureWH(validDevice1, null, null);

        //Assert

        assertFalse(result);
    }

    @Test
    void configureOneHeaterTestNegative() {

        //Act

        boolean result = controller.configureWH(validDevice1, -2D, -2.5);

        //Assert

        assertTrue(result);
    }

    @Test
    void configureOneHeaterTestTrue() {

        //Act

        boolean result = controller.configureWH(validDevice1, 2D, 30D);

        //Assert

        assertTrue(result);
    }

    @Test
    void getDeviceConsumptionInIntervalTest() {

        //Arrange

        String expectedResult = "The total Energy Consumption for the given device is: 56.0 kW/h.";
        Date initialTime = new GregorianCalendar(2018, Calendar.NOVEMBER, 20, 10, 2).getTime();
        Date finalTime = new GregorianCalendar(2018, Calendar.NOVEMBER, 20, 10, 60).getTime();
        validDevice1.addLog(validLog1);

        //Act

        String result = controller.getDeviceConsumptionInInterval(validDevice1, initialTime, finalTime);


        //Assert

        assertEquals(result, expectedResult);
    }

    @Test
    void getDeviceConsumptionInIntervalEmptyLogListTest() {

        //Arrange
        Date initialTime = new GregorianCalendar(2018, Calendar.NOVEMBER, 20, 10, 2).getTime();
        Date finalTime = new GregorianCalendar(2018, Calendar.NOVEMBER, 20, 10, 60).getTime();
        String expectedResult = "This device has no energy consumption logs in the given interval.";

        //Act

        String result = controller.getDeviceConsumptionInInterval(validDevice1, initialTime, finalTime);


        //Assert
        assertEquals(result, expectedResult);
    }

    @Test
    void getDeviceConsumptionInIntervalOutsideIntervalBeforeTest() {

        //Arrange
        Date initialTime = new GregorianCalendar(2013, Calendar.NOVEMBER, 20, 10, 2).getTime();
        Date finalTime = new GregorianCalendar(2014, Calendar.NOVEMBER, 20, 10, 60).getTime();
        validDevice1.addLog(validLog1);
        String expectedResult = "This device has no energy consumption logs in the given interval.";

        //Act

        String result = controller.getDeviceConsumptionInInterval(validDevice1, initialTime, finalTime);


        //Assert
        assertEquals(result, expectedResult);

    }

    @Test
    void getDeviceConsumptionInIntervalOutsideIntervalAfterTest() {

        //Arrange
        Date initialTime = new GregorianCalendar(2020, Calendar.NOVEMBER, 20, 10, 2).getTime();
        Date finalTime = new GregorianCalendar(2020, Calendar.NOVEMBER, 20, 10, 60).getTime();
        validDevice1.addLog(validLog1);
        String expectedResult = "This device has no energy consumption logs in the given interval.";

        //Act

        String result = controller.getDeviceConsumptionInInterval(validDevice1, initialTime, finalTime);


        //Assert
        assertEquals(result, expectedResult);
    }

    @Test
    void getDeviceConsumptionInIntervalSameTime() {

        //Arrange

        Date initialTime = new GregorianCalendar(2018, Calendar.NOVEMBER, 20, 10, 10).getTime();
        Date finalTime = new GregorianCalendar(2018, Calendar.NOVEMBER, 20, 10, 50).getTime();
        validDevice1.addLog(validLog1);
        String expectedResult = "The total Energy Consumption for the given device is: 56.0 kW/h.";

        //Act

        String result = controller.getDeviceConsumptionInInterval(validDevice1, initialTime, finalTime);


        //Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void getDeviceConsumptionInIntervalSameTimeNoLogs() {

        //Arrange

        Date time = new GregorianCalendar(2018, Calendar.NOVEMBER, 20, 10, 10).getTime();
        validDevice1.addLog(validLog1);
        String expectedResult = "This device has no energy consumption logs in the given interval.";

        //Act

        String result = controller.getDeviceConsumptionInInterval(validDevice1, time, time);


        //Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetGridConsumptionInIntervalWorks() {

        //Arrange

        Date initialTime = new GregorianCalendar(2018, Calendar.NOVEMBER, 20, 10, 10).getTime();
        Date finalTime = new GregorianCalendar(2018, Calendar.NOVEMBER, 20, 10, 50).getTime();
        validGrid.addRoomToAnEnergyGrid(validRoom1);
        double expectedResult = 56.0;

        //Act

        validDevice1.addLog(validLog1);
        double actualResult = controller.getGridConsumptionInInterval(validGrid, initialTime, finalTime);


        //Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetGridLogsInInterval() {

        //Arrange

        Date initialTime = new GregorianCalendar(2018, Calendar.NOVEMBER, 20, 10, 10).getTime();
        Date finalTime = new GregorianCalendar(2018, Calendar.NOVEMBER, 20, 10, 50).getTime();
        validGrid.addRoomToAnEnergyGrid(validRoom1);
        LogList expectedResult = new LogList();
        expectedResult.addLog(validLog1);

        //Act

        validDevice1.addLog(validLog1);
        LogList actualResult = controller.getGridLogsInInterval(validGrid, initialTime, finalTime);

        //Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetRoomLogsInInterval() {
        //Arrange

        Date initialTime = new GregorianCalendar(2018, Calendar.NOVEMBER, 20, 10, 10).getTime();
        Date finalTime = new GregorianCalendar(2018, Calendar.NOVEMBER, 20, 10, 50).getTime();
        LogList expectedResult = new LogList();
        expectedResult.addLog(validLog1);

        //Act

        validDevice1.addLog(validLog1);
        LogList actualResult = controller.getRoomLogsInInterval(validRoom1, initialTime, finalTime);

        //Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetDeviceLogsInInterval() {

        //Arrange

        Date initialTime = new GregorianCalendar(2018, Calendar.NOVEMBER, 20, 10, 10).getTime();
        Date finalTime = new GregorianCalendar(2018, Calendar.NOVEMBER, 20, 10, 50).getTime();
        LogList expectedResult = new LogList();
        expectedResult.addLog(validLog1);

        //Act

        validDevice1.addLog(validLog1);
        LogList actualResult = controller.getDeviceLogsInInterval(validDevice1, initialTime, finalTime);

        //Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfBuildLogListStringWorks() {

        //Arrange

        LogList list = new LogList();
        list.addLog(validLog1);
        String expectedResult = "\n0) Start Date: 20/11/2018 10:10:00 | End Date: 20/11/2018 10:50:00 | Value: 56.0";

        //Act

        String result = controller.buildLogListString(list);

        //Assert

        assertEquals(expectedResult, result);
    }
}