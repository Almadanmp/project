package pt.ipp.isep.dei.project.controller.controllercli;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project.dto.RoomDTO;
import pt.ipp.isep.dei.project.dto.mappers.RoomMapper;
import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.model.bridgeservices.EnergyGridRoomService;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.DeviceList;
import pt.ipp.isep.dei.project.model.device.Fridge;
import pt.ipp.isep.dei.project.model.device.WaterHeater;
import pt.ipp.isep.dei.project.model.device.devicespecs.FridgeSpec;
import pt.ipp.isep.dei.project.model.device.devicespecs.WaterHeaterSpec;
import pt.ipp.isep.dei.project.model.device.log.Log;
import pt.ipp.isep.dei.project.model.device.log.LogList;
import pt.ipp.isep.dei.project.model.energy.EnergyGrid;
import pt.ipp.isep.dei.project.model.energy.EnergyGridRepository;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project.model.house.Address;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.room.Room;
import pt.ipp.isep.dei.project.model.room.RoomRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * EnergyConsumptionController tests class.
 */
@ExtendWith(MockitoExtension.class)
class EnergyConsumptionControllerTest {

    // Common artifacts for testing in this class.

    private EnergyGrid validGrid = new EnergyGrid("validGrid", 300D, "34576");
    private Room validRoom1; // Is a room with 3 valid devices.
    private Room validRoom2; // Is a room without devices.
    private Device validDevice1 = new WaterHeater(new WaterHeaterSpec());
    private Device validDevice2 = new WaterHeater(new WaterHeaterSpec());
    private Device validDevice3 = new Fridge(new FridgeSpec());
    private SimpleDateFormat validSdf; // SimpleDateFormat dd/MM/yyyy HH:mm:ss
    private Date validDate1; // Date 09/08/2018
    private Date validDate2; // Date 11/02/2014
    private Log validLog1;
    private GeographicArea validArea;
    private static final String PATH_TO_FRIDGE = "pt.ipp.isep.dei.project.model.device.devicetypes.FridgeType";
    @Mock
    private RoomRepository roomRepository;
    private List<Room> roomList;
    @Mock
    EnergyGridRepository energyGridRepository;

    @Mock
    EnergyGridRoomService energyGridRoomService;

    @InjectMocks
    private EnergyConsumptionController controller;

    @BeforeEach
    void arrangeArtifacts() {
        this.roomList = new ArrayList<>();
        validRoom1 = new Room("Kitchen", "Ground Floor Kitchen", 0, 35, 40, 20, "Room1");
        validRoom2 = new Room("Bathroom", "2nd Floor Bathroom", 2, 15, 20, 10, "Room1");
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
        validLog1 = new Log(56, validDate1, validDate2);
        validArea = new GeographicArea("Porto", "Cidade", 2, 3, new Local(4, 4, 100));
    }

    @Test
    void seeIfGetGridConsumptionInIntervalWorks() {

        validGrid.addRoomId(validRoom1.getId());

        Mockito.when(energyGridRoomService.getGridConsumptionInInterval(validGrid, validDate1, validDate2)).thenReturn(200D);

        //Act

        double actualResult = controller.getGridConsumptionInInterval(validGrid, validDate1, validDate2);

        //Assert

        assertEquals(200D, actualResult, 0.01);
    }

    @Test
    void seeIfGetsLogInIntervalWorks() {
        // Arrange
        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date initialTime = new Date();
        try {
            initialTime = validSdf.parse("11/01/2018 10:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date finalTime = new Date();
        try {
            finalTime = validSdf.parse("11/03/2018 10:30:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date logDate = new Date();
        try {
            logDate = validSdf.parse("20/02/2018 10:30:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log validLog = new Log(300, logDate, new GregorianCalendar
                (2018, Calendar.FEBRUARY, 20, 10, 30).getTime());
        LogList logList = new LogList();
        logList.addLog(validLog);
        LogList expectedResult = new LogList();
        expectedResult.addLog(validLog);

        Mockito.when(energyGridRoomService.getLogsInInterval(validGrid, initialTime, finalTime)).thenReturn(logList);

        // Act

        LogList actualResult = controller.getGridLogsInInterval(validGrid, initialTime, finalTime);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetNominalPowerWorksMultipleRooms() {
        //Arrange

        double expectedResult = 40;

        Mockito.when(energyGridRoomService.getNominalPower(validGrid)).thenReturn(40.0);
        //Act

        double actualResult = controller.getTotalPowerFromGrid(validGrid);

        //Assert

        assertEquals(expectedResult, actualResult);
    }


    @Test
    void seeIfRoomDevicesGetRemovedFromDeviceList() {
        // Arrange

        DeviceList deviceList1 = new DeviceList();
        deviceList1.add(validDevice1);
        deviceList1.add(validDevice2);
        deviceList1.add(validDevice3);

        // Act

        boolean actualResult = controller.removeRoomDevicesFromDeviceList(validRoom1, deviceList1);

        // Assert

        assertTrue(actualResult);

    }

    @Test
    void seeIfRemoveRoomDevicesFromDeviceListIsFalseNullList() {
        // Act

        boolean actualResult = controller.removeRoomDevicesFromDeviceList(validRoom1, null);

        // Assert

        assertFalse(actualResult);

    }

    @Test
    void seeIfAddRoomDevicesToDeviceListIsFalseNullList() {
        // Act

        boolean actualResult = controller.removeRoomDevicesFromDeviceList(validRoom1, null);

        // Assert

        assertFalse(actualResult);

    }

    @Test
    void seeIfAddRoomDevicesToDeviceList() {
        // Arrange

        Room room = new Room("Room", "Single Bedroom", 10, 2, 5, 4, "Room1");
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
        // Arrange

        List<Room> roomService = new ArrayList<>();

        // Act

        boolean actualResult = controller.addRoomToList(validRoom1, roomService);

        // Assert

        assertTrue(actualResult);

    }

    @Test
    void seeIfRemoveRoomFromListWorks() {
        // Arrange

        roomList.add(validRoom1);
        // Act
        Mockito.when(roomRepository.removeRoom(validRoom1)).thenReturn(true);
        boolean actualResult = controller.removeRoomFromList(validRoom1);

        // Assert

        assertTrue(actualResult);

    }

    @Test
    void seeIfRemoveRoomFromListWorksFalse() {
        // Act

        boolean actualResult = controller.removeRoomFromList(validRoom1);

        // Assert

        assertFalse(actualResult);

    }

    @Test
    void seeIfAddRoomToListWorksFalse() {
        // Arrange

        roomList.add(validRoom1);

        // Act

        boolean actualResult = controller.addRoomToList(validRoom1, roomList);

        // Assert

        assertFalse(actualResult);

    }

    @Test
    void seeIfAddDeviceToListWorks() {
        // Arrange

        DeviceList deviceList = new DeviceList();

        // Act

        boolean actualResult = controller.addDeviceToList(validDevice1, deviceList);

        // Assert

        assertTrue(actualResult);

    }


    @Test
    void seeIfAddDeviceToListWorksFalse() {
        // Arrange

        DeviceList deviceList = new DeviceList();
        deviceList.add(validDevice1);

        // Act

        boolean actualResult = controller.addDeviceToList(validDevice1, deviceList);

        // Assert

        assertFalse(actualResult);

    }

    @Test
    void seeIfRemoveDeviceFromListWorksFalse() {
        // Arrange

        DeviceList deviceList = new DeviceList();

        // Act

        boolean actualResult = controller.removeDeviceFromList(validDevice1, deviceList);

        // Assert

        assertFalse(actualResult);

    }

    @Test
    void seeIfRemoveDeviceFromListWorks() {
        // Arrange

        DeviceList deviceList = new DeviceList();
        deviceList.add(validDevice1);

        // Act

        boolean actualResult = controller.removeDeviceFromList(validDevice1, deviceList);

        // Assert

        assertTrue(actualResult);

    }


    @Test
    void seeIfGetSumNominalPowerFromListWorks() {
        // Arrange

        DeviceList deviceList = new DeviceList();
        deviceList.add(validDevice1);
        deviceList.add(validDevice2);
        deviceList.add(validDevice3);
        double expectedResult = 86;

        // Act

        double actualResult = controller.getSelectionNominalPower(deviceList);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetSumNominalPowerFromListWorksZero() {
        // Arrange

        DeviceList deviceList = new DeviceList();
        double expectedResult = 0;

        // Act

        double actualResult = controller.getSelectionNominalPower(deviceList);

        // Assert

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfGetHouseGridListWorksEmptyList() {
        // Arrange

        List<EnergyGrid> expectedResult = new ArrayList<>();

        // Act

        List<EnergyGrid> actualResult = controller.getHouseGridList();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetHouseGridListWorks() {
        // Arrange

        List<EnergyGrid> expectedResult = new ArrayList<>();
        expectedResult.add(validGrid);

        // Act
        Mockito.when(energyGridRepository.getAllGrids()).thenReturn(expectedResult);
        List<EnergyGrid> actualResult = controller.getHouseGridList();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetRoomConsumptionInIntervalWorks() {
        // Arrange

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

        // Act

        double actualResult = controller.getRoomConsumptionInInterval(validRoom1, initialTime, finalTime);


        // Assert

        assertEquals(expectedResult, actualResult);
    }

//    @Test
//    void getTotalNominalPowerFromGridTest() {
//        // Arrange
//
//        List<String> deviceTypeString = new ArrayList<>();
//        deviceTypeString.add(PATH_TO_FRIDGE);
//        Address address = new Address("Rua Dr. António Bernardino de Almeida", "431", "4200-072", "Porto", "Portugal");
//        House house = new House("ISEP", address, new Local(20, 20, 20),
//                60, 180, deviceTypeString);
//        house.setMotherAreaID(validArea.getSensorID());
//        roomList.add(validRoom2);
//        roomList.add(validRoom1);
//        List<String> rlist = new ArrayList<>();
//        rlist.add(validRoom1.getSensorID());
//        rlist.add(validRoom2.getSensorID());
//        validGrid.setRooms(rlist);
//        double expectedResult = 86;
//
//        // Act
//
//        double actualResult = controller.getTotalPowerFromGrid(validGrid);
//
//        // Assert
//
//        assertEquals(expectedResult, actualResult);
//
//    }

//    @Test
//    void getTotalNominalPowerFromGridNoDevicesTest() {
//        // Arrange
//
//        List<String> deviceTypeString = new ArrayList<>();
//        deviceTypeString.add(PATH_TO_FRIDGE);
//        Address address = new Address("Rua Dr. António Bernardino de Almeida", "431", "4200-072", "Porto", "Portugal");
//        House house = new House("ISEP", address, new Local(20, 20, 20),
//                60, 180, deviceTypeString);
//        house.setMotherAreaID(validArea.getSensorID());
//
//        List<Room> roomService = new ArrayList<>();
//        validGrid.setRooms(roomService);
//        double expectedResult = 0;
//
//        // Act
//
//        double actualResult = controller.getTotalPowerFromGrid(validGrid);
//
//        // Assert
//
//        assertEquals(expectedResult, actualResult);
//
//    }

    @Test
    void seeIfGetWHNameWorks() {
        // Act

        String actualResult = controller.getWHName(validDevice1);

        // Assert

        Assertions.assertEquals("WaterHeater", actualResult);

    }

    @Test
    void configureOneHeaterTestFalse() {
        // Arrange

        Double attributeValue2 = 30.0;

        // Act

        boolean actualResult = controller.configureWH(validDevice1, null, attributeValue2);

        // Assert

        assertFalse(actualResult);

    }

    @Test
    void configureOneHeaterTestFalseSecondElement() {
        // Arrange

        Double attributeValue2 = 30.0;

        // Act

        boolean actualResult = controller.configureWH(validDevice1, attributeValue2, null);

        // Assert

        assertFalse(actualResult);

    }

    @Test
    void configureOneHeaterTestFalseBothElement() {
        // Act

        boolean actualResult = controller.configureWH(validDevice1, null, null);

        // Assert

        assertFalse(actualResult);

    }

    @Test
    void configureOneHeaterTestNegative() {
        // Act

        boolean actualResult = controller.configureWH(validDevice1, -2D, -2.5);

        // Assert

        assertTrue(actualResult);

    }

    @Test
    void configureOneHeaterTestTrue() {
        // Act

        boolean actualResult = controller.configureWH(validDevice1, 2D, 30D);

        // Assert

        assertTrue(actualResult);

    }

    @Test
    void getDeviceConsumptionInIntervalTest() {
        // Arrange

        String expectedResult = "The total Energy Consumption for the given device is: 56.0 kW/h.";
        validDevice1.addLog(validLog1);

        // Act

        String actualResult = controller.getDeviceConsumptionInInterval(validDevice1, validDate1, validDate2);


        // Assert

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void getDeviceConsumptionInIntervalEmptyLogListTest() {
        // Arrange

        String expectedResult = "This device has no energy consumption logs in the given interval.";

        // Act

        String actualResult = controller.getDeviceConsumptionInInterval(validDevice1, validDate1, validDate2);


        // Assert
        assertEquals(expectedResult, actualResult);

    }

    @Test
    void getDeviceConsumptionInIntervalOutsideIntervalBeforeTest() {
        // Arrange

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

        // Act

        String actualResult = controller.getDeviceConsumptionInInterval(validDevice1, initialTime, finalTime);


        // Assert
        assertEquals(expectedResult, actualResult);

    }

    @Test
    void getDeviceConsumptionInIntervalOutsideIntervalAfterTest() {
        // Arrange

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

        // Act

        String actualResult = controller.getDeviceConsumptionInInterval(validDevice1, initialTime, finalTime);

        // Assert

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void getDeviceConsumptionInIntervalSameTime() {
        // Arrange

        validDevice1.addLog(validLog1);
        String expectedResult = "The total Energy Consumption for the given device is: 56.0 kW/h.";

        // Act

        String actualResult = controller.getDeviceConsumptionInInterval(validDevice1, validDate1, validDate2);


        // Assert

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void getDeviceConsumptionInIntervalSameTimeNoLogs() {
        // Arrange

        Date time = new Date();
        try {
            time = validSdf.parse("20/10/2014 10:02:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        validDevice1.addLog(validLog1);
        String expectedResult = "This device has no energy consumption logs in the given interval.";

        // Act

        String actualResult = controller.getDeviceConsumptionInInterval(validDevice1, time, time);


        // Assert

        assertEquals(expectedResult, actualResult);

    }

//    @Test
//    void seeIfGetGridConsumptionInIntervalWorks() {
//        // Arrange
//
//        validGrid.addRoom(validRoom1);
//        double expectedResult = 56.0;
//
//        // Act
//
//        validDevice1.addLog(validLog1);
//        double actualResult = controller.getGridConsumptionInInterval(validGrid, validDate1, validDate2);
//
//
//        // Assert
//
//        assertEquals(expectedResult, actualResult);
//
//    }
//
//    @Test
//    void seeIfGetGridLogsInIntervalWorks() {
//        // Arrange
//
//        validGrid.addRoom(validRoom1);
//        LogList expectedResult = new LogList();
//        expectedResult.addLog(validLog1);
//
//        // Act
//
//        validDevice1.addLog(validLog1);
//        LogList actualResult = controller.getGridLogsInInterval(validGrid, validDate1, validDate2);
//
//        // Assert
//
//        assertEquals(expectedResult, actualResult);
//
//    }

    @Test
    void seeIfGetDeviceLogsInInterval() {
        // Arrange

        LogList expectedResult = new LogList();
        expectedResult.addLog(validLog1);

        // Act

        validDevice1.addLog(validLog1);
        LogList actualResult = controller.getDeviceLogsInInterval(validDevice1, validDate1, validDate2);

        // Assert

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfBuildLogListStringWorks() {
        // Arrange

        LogList list = new LogList();
        list.addLog(validLog1);
        String expectedResult = "\n0) Start Date: 20/11/2018 10:10:00 | End Date: 21/11/2014 10:20:00 | Value: 56.0";

        // Act

        String actualResult = controller.buildLogListString(list);

        // Assert

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

        controller.addRoomDevicesToDeviceList(validRoom1, actualResult);

        // Assert

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfGetRoomLogsInIntervalWorks() {
        // Arrange

        Date insideInterval = new GregorianCalendar(2016, Calendar.JULY, 1).getTime();
        LogList expectedResult = new LogList();
        Log log1 = new Log(11, insideInterval, insideInterval);
        Log log2 = new Log(13, insideInterval, insideInterval);
        Log log3 = new Log(15, insideInterval, insideInterval);
        expectedResult.addLog(log1);
        expectedResult.addLog(log2);
        expectedResult.addLog(log3);
        validDevice1.addLog(log1);
        validDevice2.addLog(log2);
        validDevice3.addLog(log3);

        // Act

        RoomDTO testDTO = RoomMapper.objectToDTO(validRoom1);
        Mockito.when(roomRepository.updateHouseRoom(testDTO)).thenReturn(validRoom1);
        LogList actualResult = controller.getRoomLogsInInterval(testDTO, validDate2, validDate1);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetRoomLogsInIntervalWorksNoRooms() {
        // Arrange

        RoomDTO testDTO = RoomMapper.objectToDTO(validRoom1);

        // Assert

        assertThrows((RuntimeException.class), () -> controller.getRoomLogsInInterval(testDTO, validDate2, validDate1));
    }

    @Test
    void seeIfGetRoomLogsInIntervalWorksNoLogs() {
        // Arrange
        LogList expectedResult = new LogList();

        // Act

        RoomDTO testDTO = RoomMapper.objectToDTO(validRoom1);
        Mockito.when(roomRepository.updateHouseRoom(testDTO)).thenReturn(validRoom1);
        LogList actualResult = controller.getRoomLogsInInterval(testDTO, validDate2, validDate1);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetWaterHeaterDeviceListWorks() {
        // Arrange
        DeviceList expectedResult = new DeviceList();
        expectedResult.add(validDevice1);
        expectedResult.add(validDevice2);
        validRoom1.addDevice(validDevice1);
        validRoom2.addDevice(validDevice2);
        roomList.add(validRoom1);
        roomList.add(validRoom2);
        // Act
        Mockito.when(roomRepository.getAllRooms()).thenReturn(roomList);
        DeviceList actualResult = controller.getWaterHeaterDeviceList();

        // Assert

        assertEquals(expectedResult.size(), actualResult.size());
    }

    @Test
    void seeIfGetDailyWaterHeaterConsumptionWorksNoRooms() {
        // Arrange

        double expectedResult = 0;

        // Act

        double actualResult = controller.getDailyWaterHeaterConsumption();

        // Assert

        assertEquals(expectedResult, actualResult, 0.01);
    }

    @Test
    void seeIfGetDailyWaterHeaterConsumptionWorks() {
        // Arrange
        roomList.add(validRoom1);
        double expectedResult = 97.9;

        validDevice1.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER_HEAT, 30D);
        // Act
        Mockito.when(roomRepository.getDailyConsumptionByDeviceType("WaterHeater", 1440)).thenReturn(expectedResult);
        double actualResult = controller.getDailyWaterHeaterConsumption();

        // Assert

        assertEquals(expectedResult, actualResult, 0.01);
    }

    @Test
    void seeIfGetDailyWaterHeaterConsumptionWorksNoWaterToHeat() {
        // Arrange

        double expectedResult = 0;
        Mockito.when(roomRepository.getDailyConsumptionByDeviceType("WaterHeater", 1440)).thenReturn(expectedResult);
        // Act

        double actualResult = controller.getDailyWaterHeaterConsumption();

        // Assert

        assertEquals(expectedResult, actualResult, 0.01);
    }
}