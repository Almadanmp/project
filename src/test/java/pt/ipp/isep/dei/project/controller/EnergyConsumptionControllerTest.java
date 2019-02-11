package pt.ipp.isep.dei.project.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.TestUtils;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.DeviceList;
import pt.ipp.isep.dei.project.model.device.Log;
import pt.ipp.isep.dei.project.model.device.LogList;
import pt.ipp.isep.dei.project.model.device.devicespecs.FridgeSpec;
import pt.ipp.isep.dei.project.model.device.devicespecs.WaterHeaterSpec;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * EnergyConsumptionController tests class.
 */

class EnergyConsumptionControllerTest {

    // Common artifacts for testing in this class.

    private EnergyGrid validGrid = new EnergyGrid("validGrid", 300);
    private Room validRoom1 = new Room("Kitchen", 0, 35, 40, 20);
    private Room validRoom2 = new Room("Upstairs Bathroom", 2, 15, 20, 10);
    private Device validDevice1 = new Device(new WaterHeaterSpec());
    private Device validDevice2 = new Device(new WaterHeaterSpec());
    private Device validDevice3 = new Device(new FridgeSpec());
    private EnergyConsumptionController controller = new EnergyConsumptionController();
    private Log validLog1 = new Log(56, new GregorianCalendar(2018, Calendar.NOVEMBER, 20, 10, 10).getTime(),
            new GregorianCalendar(2018, Calendar.NOVEMBER, 20, 10, 50).getTime());


    @BeforeEach
    void arrangeArtifacts() {
        validDevice1.setName("WaterHeater");
        validDevice1.setNominalPower(21.0);
        validDevice1.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 12D);
        validDevice1.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 40D);
        validDevice1.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 234D);
        validDevice2.setName("WaterHeaterTwo");
        validDevice2.setNominalPower(55.0);
        validDevice2.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 12D);
        validDevice2.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 40D);
        validDevice2.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 234D);
        validDevice3.setName("Fridge");
        validDevice3.setNominalPower(10.0);
        validDevice3.setAttributeValue(TestUtils.F_FREEZER_CAPACITY, 5D);
        validDevice3.setAttributeValue(TestUtils.F_REFRIGERATOR_CAPACITY, 3D);
        validDevice3.setAttributeValue(TestUtils.F_ANNUAL_CONSUMPTION, 456D);
        validRoom1.addDevice(validDevice1);
        validRoom1.addDevice(validDevice2);
        validRoom1.addDevice(validDevice3);
    }
    //US705 TESTS

    @Test
    void seeIfRoomsAndDevicesGetPrinted() {

        //Arrange

        RoomList roomList = new RoomList();
        roomList.addRoom(validRoom1);
        validGrid.setRoomList(roomList);

        String expectedResult = "0) Kitchen.\n" +
                "1) WaterHeater, Type: WaterHeater, Power: 21.0.\n" +
                "2) WaterHeaterTwo, Type: WaterHeater, Power: 55.0.\n" +
                "3) Fridge, Type: Fridge, Power: 10.0.\n";

        //Act
        String actualResult = controller.buildRoomsAndDevicesString(validGrid);

        //Assert
        assertEquals(expectedResult, actualResult);

    }

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

        Room r1 = new Room("Kitchen", 0, 30, 50, 10);
        RoomList roomList = new RoomList();
        EnergyConsumptionController controller = new EnergyConsumptionController();

        //Act
        boolean actualResult = controller.removeRoomFromList(r1, roomList);

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
        deviceTypeString.add(TestUtils.PATH_TO_FRIDGE);
        House house = new House("ISEP", "Rua Dr. António Bernardino de Almeida", "4200-072", "Porto", new Local(20, 20, 20), new GeographicArea("Porto", new TypeArea("Cidade"), 2, 3, new Local(4, 4, 100)), 60, 180, deviceTypeString);
        RoomList roomList = new RoomList();
        roomList.addRoom(validRoom1);
        roomList.addRoom(validRoom2);
        house.setRoomList(roomList);
        EnergyConsumptionController controller = new EnergyConsumptionController();
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
        deviceTypeString.add(TestUtils.PATH_TO_FRIDGE);
        House house = new House("ISEP", "Rua Dr. António Bernardino de Almeida", "4200-072", "Porto", new Local(20, 20, 20), new GeographicArea("Porto", new TypeArea("Cidade"), 2, 3, new Local(4, 4, 100)), 60, 180, deviceTypeString);
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
        deviceTypeString.add(TestUtils.PATH_TO_FRIDGE);
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
        deviceTypeString.add(TestUtils.PATH_TO_FRIDGE);
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
        deviceTypeString.add(TestUtils.PATH_TO_FRIDGE);
        House house = new House("ISEP", "Rua Dr. António Bernardino de Almeida", "4200-072", "Porto", new Local(20, 20, 20), new GeographicArea("Porto", new TypeArea("Cidade"), 2, 3, new Local(4, 4, 100)), 60, 180, deviceTypeString);
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
        deviceTypeString.add(TestUtils.PATH_TO_FRIDGE);
        House house = new House("ISEP", "Rua Dr. António Bernardino de Almeida", "4200-072", "Porto", new Local(20, 20, 20), new GeographicArea("Porto", new TypeArea("Cidade"), 2, 3, new Local(4, 4, 100)), 60, 180, deviceTypeString);
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
        EnergyConsumptionController controller = new EnergyConsumptionController();
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(TestUtils.PATH_TO_FRIDGE);
        House h1 = new House("house1", "Rua Carlos Peixoto", "4535", "Santa Maria de Lamas", new Local(20, 20, 20), new GeographicArea("porto", new TypeArea("cidade"), 2, 3, new Local(4, 4, 100)), 60, 180, deviceTypeString);
        Room r1 = new Room("quarto", 1, 12, 12, 12);
        Device d1 = new Device(new FridgeSpec());
        d1.setNominalPower(12.0);
        d1.setAttributeValue(TestUtils.F_FREEZER_CAPACITY, 4D);
        d1.setAttributeValue(TestUtils.F_REFRIGERATOR_CAPACITY, 7D);
        d1.setAttributeValue(TestUtils.F_ANNUAL_CONSUMPTION, 45D);
        r1.addDevice(d1);
        Room r2 = new Room("kitchen", 2, 12, 12, 12);
        Device d4 = new Device(new FridgeSpec());
        d4.setNominalPower(12.0);
        d4.setAttributeValue(TestUtils.F_FREEZER_CAPACITY, 3D);
        d4.setAttributeValue(TestUtils.F_REFRIGERATOR_CAPACITY, 7D);
        d4.setAttributeValue(TestUtils.F_ANNUAL_CONSUMPTION, 435D);
        RoomList rlist1 = new RoomList();
        validGrid.setRoomList(rlist1);
        rlist1.addRoom(r2);
        rlist1.addRoom(r1);
        r2.addDevice(d4);
        h1.addRoomToRoomList(r1);
        h1.addRoomToRoomList(r2);
        double expectedResult = 24;
        double result = controller.getTotalPowerFromGrid(validGrid);
        assertEquals(expectedResult, result);
    }

    @Test
    void getTotalNominalPowerFromGridNoDevicesTest() {
        EnergyConsumptionController controller = new EnergyConsumptionController();
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(TestUtils.PATH_TO_FRIDGE);
        House h1 = new House("house1", "Rua Carlos Peixoto", "4535", "Santa Maria de Lamas", new Local(20, 20, 20), new GeographicArea("porto", new TypeArea("cidade"), 2, 3, new Local(4, 4, 100)), 60, 180, deviceTypeString);
        Room r1 = new Room("quarto", 1, 12, 12, 12);
        Room r2 = new Room("kitchen", 2, 12, 12, 12);
        RoomList rlist1 = new RoomList();
        validGrid.setRoomList(rlist1);
        rlist1.addRoom(r2);
        rlist1.addRoom(r1);
        h1.addRoomToRoomList(r1);
        h1.addRoomToRoomList(r2);
        double expectedResult = 0;
        double result = controller.getTotalPowerFromGrid(validGrid);
        assertEquals(expectedResult, result);
    }

    @Test
    void getWaterHeaterDeviceListTest() {
        EnergyConsumptionController controller = new EnergyConsumptionController();
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(TestUtils.PATH_TO_FRIDGE);
        House h1 = new House("house1", "Rua Carlos Peixoto", "4535", "Santa Maria de Lamas", new Local(20, 20, 20), new GeographicArea("porto", new TypeArea("cidade"), 2, 3, new Local(4, 4, 100)), 60, 180, deviceTypeString);
        Room r1 = new Room("quarto", 1, 12, 12, 12);
        h1.addRoomToRoomList(r1);
        Device d6 = new Device(new WaterHeaterSpec());
        d6.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 400D);
        d6.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 20D);
        d6.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 0.9D);
        DeviceList d1 = new DeviceList();
        d1.addDevice(d6);
        r1.setDeviceList(d1);
        List<Device> expecteResult = new ArrayList<>();
        expecteResult.add(d6);
        List<Device> result = controller.getWaterHeaterDeviceList(h1);
        Assertions.assertEquals(expecteResult, result);
    }

    @Test
    void getWaterHeaterDeviceListTest2() {
        EnergyConsumptionController controller = new EnergyConsumptionController();
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(TestUtils.PATH_TO_FRIDGE);
        House house = new House("house1", "Rua Carlos Peixoto", "4535", "Santa Maria de Lamas", new Local(20, 20, 20), new GeographicArea("porto", new TypeArea("cidade"), 2, 3, new Local(4, 4, 100)), 60, 180, deviceTypeString);
        Room r1 = new Room("quarto", 1, 12, 12, 12);
        Device d2 = new Device(new WaterHeaterSpec());
        d2.setName("asdfgdsa");
        d2.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 200D);
        d2.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 20D);
        d2.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 10D);
        Device d3 = new Device(new WaterHeaterSpec());
        d3.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 500D);
        d3.setName("dsfgrws");
        d3.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 30D);
        d3.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 1D);
        r1.addDevice(d2);
        r1.addDevice(d3);
        house.addRoomToRoomList(r1);
        List<Device> expectedResult = new ArrayList<>();
        expectedResult.add(d2);
        expectedResult.add(d3);
        List<Device> result = controller.getWaterHeaterDeviceList(house);
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void getHouseDevicesOfGivenType() {
        EnergyConsumptionController controller = new EnergyConsumptionController();
        Device d = new Device(new WaterHeaterSpec());
        d.setName("wHeater2");
        d.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 500D);
        d.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 30D);
        d.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 1D);
        String result = controller.getWHName(d);
        Assertions.assertEquals("wHeater2", result);
    }

    @Test
    void configureOneHeaterTestFalse() {
        Device d6 = new Device(new WaterHeaterSpec());
        d6.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 400D);
        d6.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 20D);
        d6.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 0.9D);
        d6.setAttributeValue("energy", 12);
        d6.setAttributeValue("water", 60);
        Double attributeValue = null;
        Double attributeValue2 = 30.0;
        EnergyConsumptionController controller = new EnergyConsumptionController();
        controller.configureWH(d6, attributeValue, attributeValue2);
        boolean result = controller.configureWH(d6, attributeValue, attributeValue2);
        boolean expectedResult = false;
        assertEquals(expectedResult, result);
    }

    @Test
    void configureOneHeaterTestFalseSecondElement() {
        Device d6 = new Device(new WaterHeaterSpec());
        d6.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 400D);
        d6.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 20D);
        d6.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 0.9D);
        d6.setAttributeValue("blowUp", 12);
        d6.setAttributeValue("blabla", 60);
        Double attributeValue = 2.0;
        Double attributeValue2 = null;
        EnergyConsumptionController controller = new EnergyConsumptionController();
        controller.configureWH(d6, attributeValue, attributeValue2);
        boolean result = controller.configureWH(d6, attributeValue, attributeValue2);
        boolean expectedResult = false;
        assertEquals(expectedResult, result);
    }

    @Test
    void configureOneHeaterTestFalseBothElement() {
        Device d6 = new Device(new WaterHeaterSpec());
        d6.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 400D);
        d6.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 20D);
        d6.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 0.9D);
        d6.setAttributeValue("blowUp", 12);
        d6.setAttributeValue("blabla", 60);
        Double attributeValue = null;
        Double attributeValue2 = 30.0;
        EnergyConsumptionController controller = new EnergyConsumptionController();
        controller.configureWH(d6, attributeValue, attributeValue2);
        boolean result = controller.configureWH(d6, attributeValue, attributeValue2);
        boolean expectedResult = false;
        assertEquals(expectedResult, result);
    }

    @Test
    void configureOneHeaterTestNegative() {
        Device d6 = new Device(new WaterHeaterSpec());
        d6.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 400D);
        d6.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 20D);
        d6.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 0.9D);
        d6.setAttributeValue("blowUp", 12);
        d6.setAttributeValue("blabla", 60);
        Double attributeValue = -2.0;
        Double attributeValue2 = -2.5;
        EnergyConsumptionController controller = new EnergyConsumptionController();
        controller.configureWH(d6, attributeValue, attributeValue2);
        boolean result = controller.configureWH(d6, attributeValue, attributeValue2);
        boolean expectedResult = true;
        assertEquals(expectedResult, result);
    }

    @Test
    void configureOneHeaterTestTrue() {
        Device d6 = new Device(new WaterHeaterSpec());
        d6.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 400D);
        d6.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 20D);
        d6.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 0.9D);
        d6.setAttributeValue("Porto", 12);
        d6.setAttributeValue("Lisboa", 60);
        Double attributeValue = 2.0;
        Double attributeValue2 = 30.0;
        EnergyConsumptionController controller = new EnergyConsumptionController();
        boolean result = controller.configureWH(d6, attributeValue, attributeValue2);
        boolean expectedResult = true;
        assertEquals(expectedResult, result);
    }

    @Test
    void configureOneHeaterTestTrue2() {
        Device d6 = new Device(new WaterHeaterSpec());
        d6.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 400D);
        d6.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 20D);
        d6.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 0.9D);
        d6.setAttributeValue("Porto", 12);
        d6.setAttributeValue("Lisboa", 60);
        Double attributeValue = 2.0;
        Double attributeValue2 = 30.0;
        EnergyConsumptionController controller = new EnergyConsumptionController();
        controller.configureWH(d6, attributeValue, attributeValue2);
        boolean result = controller.configureWH(d6, attributeValue, attributeValue2);
        boolean expectedResult = true;
        assertEquals(expectedResult, result);
    }

    @Test
    void getWaterHeaterNameTest() {
        Device d6 = new Device(new WaterHeaterSpec());
        d6.setName("wHeater4");
        d6.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 20D);
        d6.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 400D);
        d6.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 0.9D);
        String expectedResult = "wHeater4";
        String result = d6.getName();
        assertEquals(expectedResult, result);
    }

    @Test
    void getTotalMeteredEnergyConsumptionInDeviceWithinGivenTimeIntervalTest1() {
        EnergyConsumptionController ctrl = new EnergyConsumptionController();
        Date initialTime = new GregorianCalendar(2018, Calendar.NOVEMBER, 20, 10, 2).getTime();
        Date finalTime = new GregorianCalendar(2018, Calendar.NOVEMBER, 20, 10, 60).getTime();
        Date periodBeginning = new GregorianCalendar(2018, Calendar.NOVEMBER, 20, 10, 10).getTime();
        Date periodEnding = new GregorianCalendar(2018, Calendar.NOVEMBER, 20, 10, 50).getTime();
        Device device = new Device(new WaterHeaterSpec());
        device.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 400.0D);
        device.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 400.0D);
        device.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 0.9D);
        Log log = new Log(56, periodBeginning, periodEnding);
        device.addLog(log);
        String result = ctrl.getDeviceConsumptionInInterval(device, initialTime, finalTime);
        String expectedResult = "The total Energy Consumption for the given device is: 56.0 kW/h.";
        assertEquals(result, expectedResult);
    }

    @Test
    void getTotalMeteredEnergyConsumptionInDeviceWithinGivenTimeIntervalTestForEmptyList() {
        EnergyConsumptionController ctrl = new EnergyConsumptionController();
        GregorianCalendar cal1 = new GregorianCalendar(2018, 10, 20, 9, 2);
        cal1.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date initialTime = cal1.getTime();
        GregorianCalendar cal2 = new GregorianCalendar(2018, 10, 20, 9, 60);
        cal2.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date finalTime = cal2.getTime();
        GregorianCalendar cal3 = new GregorianCalendar(2018, 10, 20, 9, 0);
        cal3.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date periodBeginning = cal3.getTime();
        GregorianCalendar cal4 = new GregorianCalendar(2018, 10, 20, 10, 10);
        cal4.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date periodEnding = cal4.getTime();
        Device device = new Device(new WaterHeaterSpec());
        device.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 400D);
        device.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 400D);
        device.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 0.9D);
        Log log = new Log(56, periodBeginning, periodEnding);
        device.addLog(log);
        String result = ctrl.getDeviceConsumptionInInterval(device, initialTime, finalTime);
        String expectedResult = "This device has no energy consumption logs in the given interval.";
        assertEquals(result, expectedResult);
    }

    @Test
    void getTotalMeteredEnergyConsumptionInDeviceWithinGivenTimeIntervalTestForFalseOutOfBoundsCondition1() {
        EnergyConsumptionController ctrl = new EnergyConsumptionController();
        GregorianCalendar cal1 = new GregorianCalendar(2018, 10, 20, 10, 0);
        cal1.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date initialTime = cal1.getTime();
        GregorianCalendar cal2 = new GregorianCalendar(2018, 10, 20, 11, 0);
        cal2.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date finalTime = cal2.getTime();
        GregorianCalendar cal3 = new GregorianCalendar(2018, 10, 20, 9, 55);
        cal3.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date periodBeginning = cal3.getTime();
        GregorianCalendar cal4 = new GregorianCalendar(2018, 10, 20, 11, 5);
        cal4.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date periodEnding = cal4.getTime();
        Device device = new Device(new WaterHeaterSpec());
        device.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 400D);
        device.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 400D);
        device.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 0.9D);
        Log log = new Log(56, periodBeginning, periodEnding);
        device.addLog(log);
        String result = ctrl.getDeviceConsumptionInInterval(device, initialTime, finalTime);
        String expectedResult = "This device has no energy consumption logs in the given interval.";
        assertEquals(result, expectedResult);
    }

    @Test
    void getTotalMeteredEnergyConsumptionInDeviceWithinGivenTimeIntervalTestForFalseOutOfBoundsCondition2() {
        EnergyConsumptionController ctrl = new EnergyConsumptionController();
        GregorianCalendar cal1 = new GregorianCalendar(2018, 10, 20, 10, 0);
        cal1.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date initialTime = cal1.getTime();
        GregorianCalendar cal2 = new GregorianCalendar(2018, 10, 20, 11, 0);
        cal2.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date finalTime = cal2.getTime();
        GregorianCalendar cal3 = new GregorianCalendar(2018, 10, 20, 9, 5);
        cal3.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date periodBeginning = cal3.getTime();
        GregorianCalendar cal4 = new GregorianCalendar(2018, 10, 20, 10, 55);
        cal4.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date periodEnding = cal4.getTime();
        Device device = new Device(new WaterHeaterSpec());
        device.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 400D);
        device.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 400D);
        device.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 0.9D);
        Log log = new Log(56, periodBeginning, periodEnding);
        device.addLog(log);
        String result = ctrl.getDeviceConsumptionInInterval(device, initialTime, finalTime);
        String expectedResult = "This device has no energy consumption logs in the given interval.";
        assertEquals(result, expectedResult);
    }

    @Test
    void getTotalMeteredEnergyConsumptionInDeviceWithinGivenTimeIntervalTestForFalseOutOfBoundsCondition3() {
        EnergyConsumptionController ctrl = new EnergyConsumptionController();
        GregorianCalendar cal1 = new GregorianCalendar(2018, 10, 20, 10, 0);
        cal1.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date initialTime = cal1.getTime();
        GregorianCalendar cal2 = new GregorianCalendar(2018, 10, 20, 11, 0);
        cal2.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date finalTime = cal2.getTime();
        GregorianCalendar cal3 = new GregorianCalendar(2018, 10, 20, 10, 5);
        cal3.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date periodBeginning = cal3.getTime();
        GregorianCalendar cal4 = new GregorianCalendar(2018, 10, 20, 11, 55);
        cal4.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date periodEnding = cal4.getTime();
        Device device = new Device(new WaterHeaterSpec());
        device.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 400D);
        device.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 400D);
        device.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 0.9D);
        Log log = new Log(56, periodBeginning, periodEnding);
        device.addLog(log);
        String result = ctrl.getDeviceConsumptionInInterval(device, initialTime, finalTime);
        String expectedResult = "This device has no energy consumption logs in the given interval.";
        assertEquals(result, expectedResult);
    }

    @Test
    void getTotalMeteredEnergyConsumptionInDeviceWithinGivenTimeIntervalTestForFalseOutOfBoundsCondition4() {
        EnergyConsumptionController ctrl = new EnergyConsumptionController();
        GregorianCalendar cal1 = new GregorianCalendar(2018, 10, 20, 10, 0);
        cal1.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date initialTime = cal1.getTime();
        GregorianCalendar cal2 = new GregorianCalendar(2018, 10, 20, 11, 0);
        cal2.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date finalTime = cal2.getTime();
        GregorianCalendar cal3 = new GregorianCalendar(2018, 10, 20, 10, 5);
        cal3.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date periodBeginning = cal3.getTime();
        GregorianCalendar cal4 = new GregorianCalendar(2018, 10, 20, 10, 55);
        cal4.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date periodEnding = cal4.getTime();
        Device device = new Device(new WaterHeaterSpec());
        device.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 400D);
        device.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 400D);
        device.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 0.9D);
        Log log = new Log(56, periodBeginning, periodEnding);
        device.addLog(log);
        String result = ctrl.getDeviceConsumptionInInterval(device, initialTime, finalTime);
        String expectedResult = "The total Energy Consumption for the given device is: 56.0 kW/h.";
        assertEquals(result, expectedResult);
    }

    @Test
    void getTotalMeteredEnergyConsumptionInDeviceWithinGivenTimeIntervalTestForFalseOutOfBoundsCondition5() {
        EnergyConsumptionController ctrl = new EnergyConsumptionController();
        GregorianCalendar cal1 = new GregorianCalendar(2018, 10, 20, 10, 0);
        cal1.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date initialTime = cal1.getTime();
        GregorianCalendar cal2 = new GregorianCalendar(2018, 10, 20, 11, 0);
        cal2.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date finalTime = cal2.getTime();
        GregorianCalendar cal3 = new GregorianCalendar(2018, 10, 20, 10, 0);
        cal3.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date periodBeginning = cal3.getTime();
        GregorianCalendar cal4 = new GregorianCalendar(2018, 10, 20, 11, 5);
        cal4.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date periodEnding = cal4.getTime();
        Device device = new Device(new WaterHeaterSpec());
        device.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 400D);
        device.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 400D);
        device.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 0.9D);
        Log log = new Log(56, periodBeginning, periodEnding);
        device.addLog(log);
        String result = ctrl.getDeviceConsumptionInInterval(device, initialTime, finalTime);
        String expectedResult = "This device has no energy consumption logs in the given interval.";
        assertEquals(result, expectedResult);
    }

    @Test
    void getTotalMeteredEnergyConsumptionInDeviceWithinGivenTimeIntervalTestForFalseOutOfBoundsCondition6() {
        EnergyConsumptionController ctrl = new EnergyConsumptionController();
        GregorianCalendar cal1 = new GregorianCalendar(2018, 10, 20, 10, 0);
        cal1.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date initialTime = cal1.getTime();
        GregorianCalendar cal2 = new GregorianCalendar(2018, 10, 20, 11, 0);
        cal2.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date finalTime = cal2.getTime();
        GregorianCalendar cal3 = new GregorianCalendar(2018, 10, 20, 9, 55);
        cal3.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date periodBeginning = cal3.getTime();
        GregorianCalendar cal4 = new GregorianCalendar(2018, 10, 20, 11, 0);
        cal4.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date periodEnding = cal4.getTime();
        Device device = new Device(new WaterHeaterSpec());
        device.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 400D);
        device.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 400D);
        device.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 0.9D);
        Log log = new Log(56, periodBeginning, periodEnding);
        device.addLog(log);
        String result = ctrl.getDeviceConsumptionInInterval(device, initialTime, finalTime);
        String expectedResult = "This device has no energy consumption logs in the given interval.";
        assertEquals(result, expectedResult);
    }

    @Test
    void getTotalMeteredEnergyConsumptionInDeviceWithinGivenTimeIntervalTestForFalseOutOfBoundsCondition7() {
        EnergyConsumptionController ctrl = new EnergyConsumptionController();
        GregorianCalendar cal1 = new GregorianCalendar(2018, 10, 20, 10, 0);
        cal1.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date initialTime = cal1.getTime();
        GregorianCalendar cal2 = new GregorianCalendar(2018, 10, 20, 11, 0);
        cal2.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date finalTime = cal2.getTime();
        GregorianCalendar cal3 = new GregorianCalendar(2018, 10, 20, 10, 0);
        cal3.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date periodBeginning = cal3.getTime();
        GregorianCalendar cal4 = new GregorianCalendar(2018, 10, 20, 11, 0);
        cal4.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date periodEnding = cal4.getTime();
        Device device = new Device(new WaterHeaterSpec());
        device.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 400D);
        device.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 400D);
        device.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 0.9D);
        Log log = new Log(56, periodBeginning, periodEnding);
        device.addLog(log);
        String result = ctrl.getDeviceConsumptionInInterval(device, initialTime, finalTime);
        String expectedResult = "The total Energy Consumption for the given device is: 56.0 kW/h.";
        assertEquals(result, expectedResult);
    }

    @Test
    void getTotalMeteredEnergyConsumptionInDeviceWithinGivenTimeIntervalTestForFalseOutOfBoundsCondition8() {
        EnergyConsumptionController ctrl = new EnergyConsumptionController();
        GregorianCalendar cal1 = new GregorianCalendar(2018, 10, 20, 10, 0);
        cal1.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date initialTime = cal1.getTime();
        GregorianCalendar cal2 = new GregorianCalendar(2018, 10, 20, 11, 0);
        cal2.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date finalTime = cal2.getTime();
        GregorianCalendar cal3 = new GregorianCalendar(2018, 10, 20, 10, 0);
        cal3.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date periodBeginning = cal3.getTime();
        GregorianCalendar cal4 = new GregorianCalendar(2018, 10, 20, 10, 50);
        cal4.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date periodEnding = cal4.getTime();
        Device device = new Device(new WaterHeaterSpec());
        device.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 400D);
        device.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 400D);
        device.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 0.9D);
        Log log = new Log(56, periodBeginning, periodEnding);
        device.addLog(log);
        String result = ctrl.getDeviceConsumptionInInterval(device, initialTime, finalTime);
        String expectedResult = "The total Energy Consumption for the given device is: 56.0 kW/h.";
        assertEquals(result, expectedResult);
    }

    @Test
    void getTotalMeteredEnergyConsumptionInDeviceWithinGivenTimeIntervalTestForFalseOutOfBoundsCondition10() {
        EnergyConsumptionController ctrl = new EnergyConsumptionController();
        GregorianCalendar cal1 = new GregorianCalendar(2018, 10, 20, 10, 0);
        cal1.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date initialTime = cal1.getTime();
        GregorianCalendar cal2 = new GregorianCalendar(2018, 10, 20, 11, 0);
        cal2.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date finalTime = cal2.getTime();
        GregorianCalendar cal3 = new GregorianCalendar(2018, 10, 20, 10, 5);
        cal3.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date periodBeginning = cal3.getTime();
        GregorianCalendar cal4 = new GregorianCalendar(2018, 10, 20, 11, 0);
        cal4.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date periodEnding = cal4.getTime();
        Device device = new Device(new WaterHeaterSpec());
        device.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 400D);
        device.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 400D);
        device.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 0.9D);
        Log log = new Log(56, periodBeginning, periodEnding);
        device.addLog(log);
        String result = ctrl.getDeviceConsumptionInInterval(device, initialTime, finalTime);
        String expectedResult = "The total Energy Consumption for the given device is: 56.0 kW/h.";
        assertEquals(result, expectedResult);
    }

    @Test
    void getTotalMeteredEnergyConsumptionInDeviceWithinGivenTimeIntervalTestForFalseOutOfBoundsCondition11() {
        EnergyConsumptionController ctrl = new EnergyConsumptionController();
        GregorianCalendar cal1 = new GregorianCalendar(2018, 10, 20, 10, 0);
        cal1.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date initialTime = cal1.getTime();
        GregorianCalendar cal2 = new GregorianCalendar(2018, 10, 20, 11, 0);
        cal2.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date finalTime = cal2.getTime();
        GregorianCalendar cal3 = new GregorianCalendar(2018, 10, 20, 10, 0);
        cal3.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date periodBeginning1 = cal3.getTime();
        GregorianCalendar cal4 = new GregorianCalendar(2018, 10, 20, 10, 55);
        cal4.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date periodEnding1 = cal4.getTime();
        GregorianCalendar cal5 = new GregorianCalendar(2018, 10, 20, 10, 0);
        cal5.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date periodBeginning2 = cal5.getTime();
        GregorianCalendar cal6 = new GregorianCalendar(2018, 10, 20, 10, 30);
        cal6.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date periodEnding2 = cal6.getTime();
        Device device = new Device(new WaterHeaterSpec());
        device.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 400D);
        device.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 400D);
        device.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 0.9D);
        Log log1 = new Log(56, periodBeginning1, periodEnding1);
        Log log2 = new Log(55, periodBeginning2, periodEnding2);
        device.addLog(log1);
        device.addLog(log2);
        String result = ctrl.getDeviceConsumptionInInterval(device, initialTime, finalTime);
        String expectedResult = "The total Energy Consumption for the given device is: 111.0 kW/h.";
        assertEquals(result, expectedResult);
    }

    @Test
    void getTotalMeteredEnergyConsumptionInDeviceWithinGivenTimeIntervalTestSameTime() {
        EnergyConsumptionController ctrl = new EnergyConsumptionController();
        GregorianCalendar cal1 = new GregorianCalendar(2018, 10, 20, 10, 0);
        cal1.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date initialTime = cal1.getTime();
        GregorianCalendar cal2 = new GregorianCalendar(2018, 10, 20, 10, 0);
        cal2.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date finalTime = cal2.getTime();
        GregorianCalendar cal3 = new GregorianCalendar(2018, 10, 20, 10, 0);
        cal3.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date periodBeginning1 = cal3.getTime();
        GregorianCalendar cal4 = new GregorianCalendar(2018, 10, 20, 10, 0);
        cal4.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date periodEnding1 = cal4.getTime();
        GregorianCalendar cal5 = new GregorianCalendar(2018, 10, 20, 10, 0);
        cal5.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date periodBeginning2 = cal5.getTime();
        GregorianCalendar cal6 = new GregorianCalendar(2018, 10, 20, 10, 0);
        cal6.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date periodEnding2 = cal6.getTime();
        Device device = new Device(new WaterHeaterSpec());
        device.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 400D);
        device.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 400D);
        device.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 0.9D);
        Log log1 = new Log(56, periodBeginning1, periodEnding1);
        Log log2 = new Log(55, periodBeginning2, periodEnding2);
        device.addLog(log1);
        device.addLog(log2);
        String result = ctrl.getDeviceConsumptionInInterval(device, initialTime, finalTime);
        String expectedResult = "The total Energy Consumption for the given device is: 111.0 kW/h.";
        assertEquals(result, expectedResult);
    }

    @Test
    void getTotalMeteredEnergyConsumptionInDeviceWithinGivenTimeIntervalTestSameTime2() {
        EnergyConsumptionController ctrl = new EnergyConsumptionController();
        GregorianCalendar cal1 = new GregorianCalendar(2018, 10, 20, 10, 0);
        cal1.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date initialTime = cal1.getTime();
        GregorianCalendar cal2 = new GregorianCalendar(2018, 10, 20, 10, 0);
        cal2.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date finalTime = cal2.getTime();
        Device device = new Device(new WaterHeaterSpec());
        device.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 400D);
        device.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 400D);
        device.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 0.9D);
        String result = ctrl.getDeviceConsumptionInInterval(device, initialTime, finalTime);
        String expectedResult = "This device has no energy consumption logs in the given interval.";
        assertEquals(result, expectedResult);
    }

    @Test
    void getTotalMeteredEnergyConsumptionInDeviceWithinGivenTimeIntervalTesDifferentTime() {
        EnergyConsumptionController ctrl = new EnergyConsumptionController();
        GregorianCalendar cal1 = new GregorianCalendar(2018, Calendar.NOVEMBER, 10, 10, 10);
        cal1.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date initialTime = cal1.getTime();
        GregorianCalendar cal2 = new GregorianCalendar(2018, Calendar.NOVEMBER, 10, 10, 10);
        cal2.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date finalTime = cal2.getTime();
        GregorianCalendar cal3 = new GregorianCalendar(2018, Calendar.NOVEMBER, 10, 10, 10);
        cal3.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date periodBeginning1 = cal3.getTime();
        GregorianCalendar cal4 = new GregorianCalendar(2018, Calendar.NOVEMBER, 10, 10, 10);
        cal4.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date periodEnding1 = cal4.getTime();
        GregorianCalendar cal5 = new GregorianCalendar(2018, Calendar.NOVEMBER, 10, 10, 10);
        cal5.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date periodBeginning2 = cal5.getTime();
        GregorianCalendar cal6 = new GregorianCalendar(2018, Calendar.NOVEMBER, 10, 10, 10);
        cal6.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date periodEnding2 = cal6.getTime();
        Device device = new Device(new WaterHeaterSpec());
        device.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 400D);
        device.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 400D);
        device.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 0.9D);
        Log log1 = new Log(56, periodBeginning1, periodEnding1);
        Log log2 = new Log(55, periodBeginning2, periodEnding2);
        device.addLog(log1);
        device.addLog(log2);
        String result = ctrl.getDeviceConsumptionInInterval(device, initialTime, finalTime);
        String expectedResult = "The total Energy Consumption for the given device is: 111.0 kW/h.";
        assertEquals(result, expectedResult);
    }

    @Test
    void seeIfGetTotalMeteredEnergyConsumptionInGridInTimeIntervalWorks() {
        //Arrange
        EnergyConsumptionController ctrl = new EnergyConsumptionController();
        Room room = new Room("Kitchen", 0, 30, 50, 10);
        Device device = new Device(new WaterHeaterSpec());
        device.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 400D);
        device.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 400D);
        device.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 0.9D);
        room.addDevice(device);
        validGrid.addRoomToAnEnergyGrid(room);
        //Act
        GregorianCalendar cal1 = new GregorianCalendar(2018, 10, 20, 10, 2);
        cal1.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date initialTime = cal1.getTime();
        GregorianCalendar cal2 = new GregorianCalendar(2018, 10, 20, 10, 60);
        cal2.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date finalTime = cal2.getTime();
        GregorianCalendar cal3 = new GregorianCalendar(2018, 10, 20, 10, 10);
        cal3.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date periodBeginning = cal3.getTime();
        GregorianCalendar cal4 = new GregorianCalendar(2018, 10, 20, 10, 50);
        cal4.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date periodEnding = cal4.getTime();
        Log log = new Log(56, periodBeginning, periodEnding);
        device.addLog(log);
        double actualResult = ctrl.getGridConsumptionInInterval(validGrid, initialTime, finalTime);
        double expectedResult = 56.0;
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetGridLogsInInterval() {
        //Arrange
        EnergyConsumptionController ctrl = new EnergyConsumptionController();
        Room room = new Room("Kitchen", 0, 30, 50, 10);
        Device device = new Device(new WaterHeaterSpec());
        device.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 400D);
        device.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 400D);
        device.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 0.9D);
        room.addDevice(device);
        validGrid.addRoomToAnEnergyGrid(room);
        //Act
        GregorianCalendar cal1 = new GregorianCalendar(2018, 10, 20, 10, 2);
        cal1.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date initialTime = cal1.getTime();
        GregorianCalendar cal2 = new GregorianCalendar(2018, 10, 20, 10, 60);
        cal2.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date finalTime = cal2.getTime();
        GregorianCalendar cal3 = new GregorianCalendar(2018, 10, 20, 10, 10);
        cal3.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date periodBeginning = cal3.getTime();
        GregorianCalendar cal4 = new GregorianCalendar(2018, 10, 20, 10, 50);
        cal4.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date periodEnding = cal4.getTime();
        Log log = new Log(56, periodBeginning, periodEnding);
        device.addLog(log);
        LogList actualResult = ctrl.getGridLogsInInterval(validGrid, initialTime, finalTime);
        LogList expectedResult = validGrid.getLogsInInterval(initialTime, finalTime);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    void seeIfGetRoomLogsInInterval() {
        //Arrange
        EnergyConsumptionController ctrl = new EnergyConsumptionController();
        Room room = new Room("Kitchen", 0, 30, 50, 10);
        Device device = new Device(new WaterHeaterSpec());
        device.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 400D);
        device.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 400D);
        device.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 0.9D);
        room.addDevice(device);
        validGrid.addRoomToAnEnergyGrid(room);
        //Act
        GregorianCalendar cal1 = new GregorianCalendar(2018, 10, 20, 10, 2);
        cal1.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date initialTime = cal1.getTime();
        GregorianCalendar cal2 = new GregorianCalendar(2018, 10, 20, 10, 60);
        cal2.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date finalTime = cal2.getTime();
        GregorianCalendar cal3 = new GregorianCalendar(2018, 10, 20, 10, 10);
        cal3.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date periodBeginning = cal3.getTime();
        GregorianCalendar cal4 = new GregorianCalendar(2018, 10, 20, 10, 50);
        cal4.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date periodEnding = cal4.getTime();
        Log log = new Log(56, periodBeginning, periodEnding);
        device.addLog(log);
        LogList actualResult = ctrl.getRoomLogsInInterval(room, initialTime, finalTime);
        LogList expectedResult = room.getLogsInInterval(initialTime, finalTime);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    void seeIfGetDeviceLogsInInterval() {
        //Arrange
        EnergyConsumptionController ctrl = new EnergyConsumptionController();
        Room room = new Room("Kitchen", 0, 30, 50, 10);
        Device device = new Device(new WaterHeaterSpec());
        device.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 400D);
        device.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 400D);
        device.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 0.9D);
        room.addDevice(device);
        validGrid.addRoomToAnEnergyGrid(room);
        //Act
        GregorianCalendar cal1 = new GregorianCalendar(2018, 10, 20, 10, 2);
        cal1.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date initialTime = cal1.getTime();
        GregorianCalendar cal2 = new GregorianCalendar(2018, 10, 20, 10, 60);
        cal2.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date finalTime = cal2.getTime();
        GregorianCalendar cal3 = new GregorianCalendar(2018, 10, 20, 10, 10);
        cal3.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date periodBeginning = cal3.getTime();
        GregorianCalendar cal4 = new GregorianCalendar(2018, 10, 20, 10, 50);
        cal4.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date periodEnding = cal4.getTime();
        Log log = new Log(56, periodBeginning, periodEnding);
        device.addLog(log);
        LogList actualResult = ctrl.getDeviceLogsInInterval(device, initialTime, finalTime);
        LogList expectedResult = room.getLogsInInterval(initialTime, finalTime);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    void seeIfBuildLogListString() {
        EnergyConsumptionController ctrl = new EnergyConsumptionController();
        LogList list = new LogList();
        Log log = new Log(10, new GregorianCalendar(2018, Calendar.NOVEMBER, 20, 10, 2).getTime(), new GregorianCalendar(2018, Calendar.NOVEMBER, 20, 10, 20).getTime());
        list.addLog(log);
        String result = ctrl.buildLogListString(list);
        String expectedResult = "\n0) Start Date: 20/11/2018 10:02:00 | End Date: 20/11/2018 10:20:00 | Value: 10.0";
        assertEquals(result, expectedResult);
    }
}