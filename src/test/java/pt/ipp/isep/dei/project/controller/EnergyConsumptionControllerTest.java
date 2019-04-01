package pt.ipp.isep.dei.project.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.dto.RoomDTO;
import pt.ipp.isep.dei.project.dto.mappers.RoomMapper;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.DeviceList;
import pt.ipp.isep.dei.project.model.device.Fridge;
import pt.ipp.isep.dei.project.model.device.WaterHeater;
import pt.ipp.isep.dei.project.model.device.devicespecs.FridgeSpec;
import pt.ipp.isep.dei.project.model.device.devicespecs.WaterHeaterSpec;
import pt.ipp.isep.dei.project.model.device.log.Log;
import pt.ipp.isep.dei.project.model.device.log.LogList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private SimpleDateFormat validSdf; // SimpleDateFormat dd/MM/yyyy HH:mm:ss
    private Date validDate1; // Date 09/08/2018
    private Date validDate2; // Date 11/02/2014
    private Log validLog1;
    private GeographicArea validArea;
    private static final String PATH_TO_FRIDGE = "pt.ipp.isep.dei.project.model.device.devicetypes.FridgeType";


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
        validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            validDate2 = validSdf.parse("21/11/2014 10:20:00");
            validDate1 = validSdf.parse("20/11/2018 10:10:00");
        } catch (ParseException c) {
            c.printStackTrace();
        }
        validLog1 = new Log(56, validDate1,validDate2);
        validArea =  new GeographicArea("Porto",
                new TypeArea("Cidade"), 2, 3, new Local(4, 4, 100));
    }

    //US705 TESTS

    @Test
    void seeIfRoomDevicesGetRemovedFromDeviceList() {

        //Arrange

        DeviceList deviceList1 = new DeviceList();
        deviceList1.add(validDevice1);
        deviceList1.add(validDevice2);
        deviceList1.add(validDevice3);

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
    void seeIfAddRoomDevicesToDeviceList(){
        // Arrange

        Room room = new Room("quarto", 10, 2, 5, 4);
        room.addDevice(validDevice1);
        room.addDevice(validDevice2);
        DeviceList actualResult = new DeviceList();
        DeviceList expectedResult = new DeviceList();
        expectedResult.add(validDevice1);
        expectedResult.add(validDevice2);

        // Act

        controller.addRoomDevicesToDeviceList(room, actualResult);

        // Assert

        assertEquals(expectedResult, actualResult);

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
        roomList.add(validRoom1);

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
        roomList.add(validRoom1);

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
        deviceList.add(validDevice1);

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
        deviceList.add(validDevice1);

        //Act
        boolean actualResult = controller.removeDeviceFromList(validDevice1, deviceList);

        //Assert
        assertTrue(actualResult);

    }


    @Test
    void seeIfGetSumNominalPowerFromListWorks() {

        //Arrange

        DeviceList deviceList = new DeviceList();
        deviceList.add(validDevice1);
        deviceList.add(validDevice2);
        deviceList.add(validDevice3);
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
    void seeIfGetHouseGridListWorks() {

        //Arrange
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        Address address = new Address("Rua Dr. António Bernardino de Almeida", "4200-072", "Porto");

        House house = new House("ISEP", address,
                new Local(20, 20, 20), 60, 180,
                deviceTypeString);
        house.setMotherArea(validArea);
        EnergyGrid testGrid = new EnergyGrid("GridOne", 300);
        EnergyGridList houseGrid = new EnergyGridList();
        houseGrid.addGrid(testGrid);
        house.setGridList(houseGrid);
        EnergyGridList expectedResult = new EnergyGridList();
        expectedResult.addGrid(testGrid);

        //Act

        EnergyGridList actualResult = controller.getHouseGridList(house);

        //Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetRoomConsumptionInIntervalWorks() {
        //Arrange
        Date initialTime = new Date();
        try {
            initialTime = validSdf.parse("20/11/2018 10:02:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date finalTime = new Date();
        try {
            finalTime = validSdf.parse("20/11/2018 10:50:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        validDevice1.addLog(validLog1);
        validRoom1.addDevice(validDevice1);
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
        Address address = new Address("Rua Dr. António Bernardino de Almeida", "4200-072", "Porto");

        House house = new House("ISEP", address,
                new Local(20, 20, 20),  60, 180,
                deviceTypeString);
        house.setMotherArea(validArea);
        validRoom1.addDevice(validDevice1);
        validRoom1.addDevice(validDevice2);
        validRoom2.addDevice(validDevice3);
        house.addRoom(validRoom1);
        house.addRoom(validRoom2);
        double expectedResult = 0.0;

        //Act

        double actualResult = controller.getDailyWaterHeaterConsumption(house);

        //Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getDailyHouseConsumptionNoRoomsTest() {

        //Arrange

        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        Address address = new Address("Rua Dr. António Bernardino de Almeida", "4200-072", "Porto");
        House house = new House("ISEP", address,
                new Local(20, 20, 20), 60, 180,
                deviceTypeString);
        house.setMotherArea(validArea);
        double expectedResult = 0;

        //Act

        double actualResult = controller.getDailyWaterHeaterConsumption(house);

        //Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getDailyHouseConsumptionNoDevicesTest() {

        //Arrange

        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        Address address = new Address("Rua Dr. António Bernardino de Almeida", "4200-072", "Porto");

        House house = new House("ISEP", address , new Local(20, 20, 20), 60,
                180, deviceTypeString);
        house.setMotherArea(validArea);
        house.addRoom(validRoom1);
        house.addRoom(validRoom2);
        double expectedResult = 0;

        //Act

        double actualResult = controller.getDailyWaterHeaterConsumption(house);

        //Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getDailyHouseConsumptionNoHeaterDevicesTest() {

        //Arrange

        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        Address address = new Address("Rua Dr. António Bernardino de Almeida", "4200-072", "Porto");
        House house = new House("ISEP", address, new Local(20, 20, 20),
                60, 180, deviceTypeString);
        house.setMotherArea(validArea);
        validRoom1.addDevice(validDevice1);
        validRoom2.addDevice(validDevice2);
        house.addRoom(validRoom1);
        house.addRoom(validRoom2);
        double expectedResult = 0;

        //Act

        double actualResult = controller.getDailyWaterHeaterConsumption(house);

        //Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getTotalNominalPowerFromGridTest() {

        //Arrange

        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        Address address = new Address("Rua Dr. António Bernardino de Almeida", "4200-072", "Porto");
        House house = new House("ISEP", address, new Local(20, 20, 20),
                60, 180, deviceTypeString);
        house.setMotherArea(validArea);
        RoomList roomList = new RoomList();
        validGrid.setRoomList(roomList);
        roomList.add(validRoom2);
        roomList.add(validRoom1);
        house.addRoom(validRoom1);
        house.addRoom(validRoom2);
        double expectedResult = 86;

        //Act

        double actualResult = controller.getTotalPowerFromGrid(validGrid);

        //Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getTotalNominalPowerFromGridNoDevicesTest() {

        //Arrange

        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        Address address = new Address("Rua Dr. António Bernardino de Almeida", "4200-072", "Porto");
        House house = new House("ISEP", address, new Local(20, 20, 20),
                60, 180, deviceTypeString);
        house.setMotherArea(validArea);

        RoomList roomList = new RoomList();
        validGrid.setRoomList(roomList);
        house.addRoom(validRoom2);
        double expectedResult = 0;

        //Act

        double actualResult = controller.getTotalPowerFromGrid(validGrid);

        //Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getWaterHeaterDeviceListTestTwoDevices() {

        //Arrange

        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        Address address = new Address("Rua Dr. António Bernardino de Almeida", "4200-072", "Porto");
        House house = new House("ISEP", address, new Local(20, 20, 20),
                60, 180, deviceTypeString);
        house.setMotherArea(validArea);
        validRoom1.addDevice(validDevice1);
        validRoom1.addDevice(validDevice2);
        house.addRoom(validRoom1);
        DeviceList expectedResult = new DeviceList();
        expectedResult.add(validDevice1);
        expectedResult.add(validDevice2);

        //Act

        DeviceList actualResult = controller.getWaterHeaterDeviceList(house);

        //Assert

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetWHNameWorks() {

        //Act

        String actualResult = controller.getWHName(validDevice1);

        //Assert

        Assertions.assertEquals("WaterHeater", actualResult);
    }

    @Test
    void configureOneHeaterTestFalse() {

        //Arrange

        Double attributeValue2 = 30.0;

        //Act

        boolean actualResult = controller.configureWH(validDevice1, null, attributeValue2);

        //Assert

        assertFalse(actualResult);
    }

    @Test
    void configureOneHeaterTestFalseSecondElement() {

        //Arrange

        Double attributeValue2 = 30.0;

        //Act

        boolean actualResult = controller.configureWH(validDevice1, attributeValue2, null);

        //Assert

        assertFalse(actualResult);
    }

    @Test
    void configureOneHeaterTestFalseBothElement() {

        //Act

        boolean actualResult = controller.configureWH(validDevice1, null, null);

        //Assert

        assertFalse(actualResult);
    }

    @Test
    void configureOneHeaterTestNegative() {

        //Act

        boolean actualResult = controller.configureWH(validDevice1, -2D, -2.5);

        //Assert

        assertTrue(actualResult);
    }

    @Test
    void configureOneHeaterTestTrue() {

        //Act

        boolean actualResult = controller.configureWH(validDevice1, 2D, 30D);

        //Assert

        assertTrue(actualResult);
    }

    @Test
    void getDeviceConsumptionInIntervalTest() {

        //Arrange

        String expectedResult = "The total Energy Consumption for the given device is: 56.0 kW/h.";
        validDevice1.addLog(validLog1);

        //Act

        String actualResult = controller.getDeviceConsumptionInInterval(validDevice1, validDate1, validDate2);


        //Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getDeviceConsumptionInIntervalEmptyLogListTest() {

        //Arrange
        String expectedResult = "This device has no energy consumption logs in the given interval.";

        //Act

        String actualResult = controller.getDeviceConsumptionInInterval(validDevice1, validDate1,validDate2);


        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getDeviceConsumptionInIntervalOutsideIntervalBeforeTest() {

        Date initialTime = new Date();
        try {
            initialTime = validSdf.parse("20/10/2014 10:02:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date finalTime = new Date();
        try {
            finalTime = validSdf.parse("20/10/2014 10:59:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        validDevice1.addLog(validLog1);
        String expectedResult = "This device has no energy consumption logs in the given interval.";

        //Act

        String actualResult = controller.getDeviceConsumptionInInterval(validDevice1, initialTime,finalTime);


        //Assert
        assertEquals(expectedResult, actualResult);

    }

    @Test
    void getDeviceConsumptionInIntervalOutsideIntervalAfterTest() {

        //Arrange
        Date initialTime = new Date();
        try {
            initialTime = validSdf.parse("20/10/2020 10:02:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date finalTime = new Date();
        try {
            finalTime = validSdf.parse("20/10/2020 10:59:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        validDevice1.addLog(validLog1);
        String expectedResult = "This device has no energy consumption logs in the given interval.";

        //Act

        String actualResult = controller.getDeviceConsumptionInInterval(validDevice1, initialTime,finalTime);


        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getDeviceConsumptionInIntervalSameTime() {

        //Arrange
        validDevice1.addLog(validLog1);
        String expectedResult = "The total Energy Consumption for the given device is: 56.0 kW/h.";

        //Act

        String actualResult = controller.getDeviceConsumptionInInterval(validDevice1, validDate1,validDate2);


        //Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getDeviceConsumptionInIntervalSameTimeNoLogs() {

        //Arrange
        Date time = new Date();
        try {
           time = validSdf.parse("20/10/2014 10:02:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        validDevice1.addLog(validLog1);
        String expectedResult = "This device has no energy consumption logs in the given interval.";

        //Act

        String actualResult = controller.getDeviceConsumptionInInterval(validDevice1, time, time);


        //Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetGridConsumptionInIntervalWorks() {

        //Arrange
        validGrid.addRoom(validRoom1);
        double expectedResult = 56.0;

        //Act

        validDevice1.addLog(validLog1);
        double actualResult = controller.getGridConsumptionInInterval(validGrid, validDate1,validDate2);


        //Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetGridLogsInIntervalWorks() {

        //Arrange
        validGrid.addRoom(validRoom1);
        LogList expectedResult = new LogList();
        expectedResult.addLog(validLog1);

        //Act

        validDevice1.addLog(validLog1);
        LogList actualResult = controller.getGridLogsInInterval(validGrid, validDate1, validDate2);

        //Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetRoomLogsInIntervalWorks() {
        //Arrange

        LogList expectedResult = new LogList();
        expectedResult.addLog(validLog1);
        House validHouse = new House("ISEP", new Address("Rua Dr. António Bernardino de Almeida",
                "4455-125", "Porto"),
                new Local(20, 20, 20),  60,
                180, new ArrayList<>());
        validHouse.setMotherArea(validArea);

        validHouse.addRoom(validRoom1);
        RoomDTO roomDTO = RoomMapper.objectToDTO(validRoom1);

        //Act

        validDevice1.addLog(validLog1);
        LogList actualResult = controller.getRoomLogsInInterval(roomDTO, validDate1,validDate2,validHouse);

        //Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetDeviceLogsInInterval() {

        //Arrange

        LogList expectedResult = new LogList();
        expectedResult.addLog(validLog1);

        //Act

        validDevice1.addLog(validLog1);
        LogList actualResult = controller.getDeviceLogsInInterval(validDevice1, validDate1,validDate2);

        //Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfBuildLogListStringWorks() {

        //Arrange

        LogList list = new LogList();
        list.addLog(validLog1);
        String expectedResult = "\n0) Start Date: 20/11/2018 10:10:00 | End Date: 21/11/2014 10:20:00 | Value: 56.0";

        //Act

        String actualResult = controller.buildLogListString(list);

        //Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfAddRoomDevicesToDeviceListWorks() {
        // Arrange

        DeviceList expectedResult = new DeviceList();
        expectedResult.add(validDevice1);
        validRoom1.setDeviceList(expectedResult);
        DeviceList actualResult = new DeviceList();
        actualResult.add(validDevice1);

        // Act

        controller.addRoomDevicesToDeviceList(validRoom1,actualResult);

        // Assert

        assertEquals(expectedResult,actualResult);
    }

}