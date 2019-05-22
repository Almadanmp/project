package pt.ipp.isep.dei.project.controller.controllercli;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project.dto.RoomDTO;
import pt.ipp.isep.dei.project.dto.mappers.RoomMapper;
import pt.ipp.isep.dei.project.model.device.*;
import pt.ipp.isep.dei.project.model.device.devicespecs.*;
import pt.ipp.isep.dei.project.model.device.devicetypes.FridgeType;
import pt.ipp.isep.dei.project.model.device.program.FixedTimeProgram;
import pt.ipp.isep.dei.project.model.device.program.ProgramList;
import pt.ipp.isep.dei.project.model.room.Room;
import pt.ipp.isep.dei.project.model.room.RoomRepository;
import pt.ipp.isep.dei.project.model.room.RoomSensor;
import pt.ipp.isep.dei.project.model.sensortype.SensorType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * RoomConfigurationController tests class.
 */
@ExtendWith(MockitoExtension.class)
class RoomConfigurationControllerTest {

    // Common artifacts for testing in this class.

    private Room validRoomWithDevices;
    private Room validRoomNoDevices;
    private Device validDeviceFridge = new Fridge(new FridgeSpec());
    @Mock
    private RoomRepository roomRepository;
    private Date validDate1;
    @InjectMocks
    private RoomConfigurationController controller;

    @BeforeEach
    void arrangeArtifacts() {
        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            validDate1 = validSdf.parse("11/01/2018 10:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        validRoomWithDevices = new Room("Office", "2nd Floor Office", 2, 15, 15, 10, "House");
        validRoomNoDevices = new Room("Kitchen", "Fully Equipped Kitchen", 1, 20, 20, 10, "House");
        controller.setAttributeValue(validDeviceFridge, FridgeSpec.FREEZER_CAPACITY, 4D);
        controller.setAttributeValue(validDeviceFridge, FridgeSpec.REFRIGERATOR_CAPACITY, 4D);
        controller.setAttributeValue(validDeviceFridge, FridgeSpec.ANNUAL_CONSUMPTION, 56D);
        validDeviceFridge.setNominalPower(25);
        validRoomWithDevices.addDevice(validDeviceFridge);
    }


    @Test
    void seeIfGetDeviceByIndexWorks() {
        // Arrange

        List<Room> roomList = new ArrayList<>();
        roomList.add(validRoomWithDevices);

        DeviceList deviceList = new DeviceList();
        deviceList.add(validDeviceFridge);

        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setName("Office");
        roomDTO.setDescription("2nd Floor Office");
        roomDTO.setFloor(2);
        roomDTO.setHeight(10);
        roomDTO.setLength(15);
        roomDTO.setWidth(15);
        roomDTO.setHouseId("House");
        roomDTO.setDeviceList(deviceList);

        Mockito.when(roomRepository.updateHouseRoom(roomDTO)).thenReturn(RoomMapper.dtoToObject(roomDTO));

        // Act

        Device actualResult = controller.getDeviceByIndex(roomDTO, 0);

        // Assert

        assertEquals(validDeviceFridge, actualResult);
    }

    @Test
    void seeIfGetDeviceListSizeWorks() {
        // Arrange

        List<Room> roomList = new ArrayList<>();
        roomList.add(validRoomWithDevices);

        DeviceList deviceList = new DeviceList();
        deviceList.add(validDeviceFridge);

        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setName("Office");
        roomDTO.setDescription("2nd Floor Office");
        roomDTO.setFloor(2);
        roomDTO.setHeight(10);
        roomDTO.setLength(15);
        roomDTO.setWidth(15);
        roomDTO.setHouseId("House");
        roomDTO.setDeviceList(deviceList);
        // Act
        Mockito.when(roomRepository.updateHouseRoom(roomDTO)).thenReturn(RoomMapper.dtoToObject(roomDTO));
        int actualResult = controller.getDeviceListSize(roomDTO);

        // Assert

        assertEquals(1, actualResult);
    }


    @Test
    void seeIfCreateRoomSensorWorks() {
        // Arrange

        SensorType sensorType = new SensorType("temperature", "Celsius");
        RoomSensor expectedResult = new RoomSensor("Sensor1", "Sensor1", sensorType.getName(), validDate1);

        // Act

        RoomSensor actualResult = controller.createRoomSensor(validRoomNoDevices, "Sensor1", "Sensor1", sensorType, validDate1);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetProgramList() {
        // Arrange

        ProgramList pList = new ProgramList();
        FixedTimeProgram fTProgram = new FixedTimeProgram();
        pList.add(fTProgram);
        Dishwasher dish = new Dishwasher(new DishwasherSpec());
        dish.setProgramList(pList);
        // Act

        ProgramList actualResult = controller.getProgramList(dish);
        // Assert

        assertEquals(pList, actualResult);
    }

    @Test
    void seeIfGetAttributeUnit() {
        // Arrange
        Object expectedResult = "Kg";
        // Act
        Object actualResult = controller.getAttributeUnit(validDeviceFridge, 1);
        // Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetAttributeValue() {
        // Arrange
        Object expectedResult = 4.0;
        // Act
        Object actualResult = controller.getAttributeValue(validDeviceFridge, 1);
        // Assert
        assertEquals(expectedResult, actualResult);
    }

    /*USER STORY 230 - As a Room Owner [or Power User, or Administrator], I want to know the total
    nominal power of a room, i.e. the sum of the nominal power of all devices in the
    room. */

    @Test
    void seeIfGetRoomNominalPowerWorksNoDevices() {
        // Arrange

        double expectedResult = 0;

        // Act

        double actualResult = controller.getRoomNominalPower(validRoomNoDevices);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetRoomNominalPowerWorks() {
        // Arrange

        double expectedResult = 25;

        // Act

        double actualResult = controller.getRoomNominalPower(validRoomWithDevices);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSetAttributeValuesWorks() {
        // Assert

        assertTrue(controller.setAttributeValue(validDeviceFridge, FridgeSpec.FREEZER_CAPACITY, 4D));
        assertTrue(controller.setAttributeValue(validDeviceFridge, FridgeSpec.REFRIGERATOR_CAPACITY, 4D));
        assertTrue(controller.setAttributeValue(validDeviceFridge, FridgeSpec.ANNUAL_CONSUMPTION, 56D));
    }


    @Test
    void seeIfSetAttributeValuesWorksFalse() {
        // Assert

        assertFalse(controller.setAttributeValue(validDeviceFridge, FridgeSpec.FREEZER_CAPACITY, "Fail"));
        assertFalse(controller.setAttributeValue(validDeviceFridge, FridgeSpec.REFRIGERATOR_CAPACITY, "Fail"));
        assertFalse(controller.setAttributeValue(validDeviceFridge, FridgeSpec.ANNUAL_CONSUMPTION, "Fail"));
    }

    /* USER STORY 253 - As an Administrator, I want to addWithoutPersisting a new sensor to a room from the list of available
    sensor types, in order to configure it. */

    @Test
    void seeIfPrintSensorListWorks() {
        //Arrange
        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        Date date = new Date();
        try {
            date = validSdf.parse("03/12/2017 10:02:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        RoomSensor s1 = new RoomSensor("T1292u37", "SensorOne", "Wind",
                date);
        RoomSensor s2 = new RoomSensor("T1292u37", "SensorTwo", "Rain",
                date);
        List<RoomSensor> roomSensorList = new ArrayList<>();
        roomSensorList.add(s1);
        roomSensorList.add(s2);
        validRoomWithDevices.setRoomSensors(roomSensorList);
        String expectedResult = "---------------\n" +
                "ID: T1292u37 | SensorOne | Type: Wind | Active\n" +
                "ID: T1292u37 | SensorTwo | Type: Rain | Active\n" +
                "---------------\n";

        //Act

        String actualResult = controller.buildSensorListString(validRoomWithDevices);

        //Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfPrintDeviceList() {
        // Arrange

        String expectedResult = "---------------\n" +
                "0) device Name: null, device Type: Fridge, device Nominal Power: 25.0\n" +
                "---------------\n";

        // Act

        String actualResult = controller.buildDeviceListString(validRoomWithDevices);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSensorIsAddedToRoom() {
        // Arrange
        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        try {
            date = validSdf.parse("04/14/2017 10:02:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        RoomSensor testAreaSensor = new RoomSensor("T4328745", "SensorOne", "Rain", date);
        Mockito.when(roomRepository.saveRoom(validRoomNoDevices)).thenReturn(true);
        boolean actualResult = controller.addSensorToRoom(testAreaSensor, validRoomNoDevices);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void setNominalPowerDevice() {
        // Act

        controller.setNominalPowerDevice(validDeviceFridge, 5);
        double actualResult = validDeviceFridge.getNominalPower();

        // Assert

        assertEquals(5.0, actualResult);
    }

    @Test
    void removeDeviceSuccess() {
        // Act

        boolean actualResult = controller.removeDevice(validRoomWithDevices, validDeviceFridge);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void removeDeviceFails() {
        // Act

        boolean actualResult = controller.removeDevice(validRoomNoDevices, validDeviceFridge);

        assertFalse(actualResult);
    }

    @Test
    void ensureThatWeDeactivateDevice() {
        // Act

        boolean actualResult = controller.deactivateDevice(validDeviceFridge);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void ensureThatWeDoNotDeactivateDevice() {
        // Act

        controller.deactivateDevice(validDeviceFridge);
        boolean actualResult = controller.deactivateDevice(validDeviceFridge);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfConfigureOneWashingMachineProgramWorks() {
        // Arrange

        Device washingMachine = new WashingMachine(new WashingMachineSpec());
        FixedTimeProgram program = new FixedTimeProgram("LowHeat", 60, 100);
        ProgramList programList = new ProgramList();
        programList.add(program);

        // Act

        controller.configureDeviceProgramList(washingMachine, programList);
        Object actualResult = washingMachine.getAttributeValue("programList");

        // Assert

        assertEquals(washingMachine.getAttributeValue("programList"), actualResult);
    }

    @Test
    void getAttributeNamesTest() {
        // Arrange

        Device lamp = new Lamp(new LampSpec());
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("Luminous Flux");
        String expectedResultType = "Lamp";

        // Act

        List<String> actualResult = controller.getAttributeNames(lamp);
        String actualResultType = controller.getType(lamp);

        // Assert

        assertEquals(expectedResult, actualResult);
        assertEquals(expectedResultType, actualResultType);
    }

    @Test
    void seeIfAddDeviceToRoom() {
        // Arrange

        controller.addDevice(validRoomNoDevices, validDeviceFridge);
        String expectedResult = "---------------\n" +
                "0) device Name: null, device Type: Fridge, device Nominal Power: 25.0\n" +
                "---------------\n";

        // Act

        String result = controller.buildDeviceListString(validRoomNoDevices);

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void addDeviceFails() {
        // Act

        boolean result = controller.addDevice(validRoomWithDevices, validDeviceFridge);

        // Assert

        assertFalse(result);
    }

    @Test
    void addDeviceTrue() {
        // Act

        boolean result = controller.addDevice(validRoomNoDevices, validDeviceFridge);

        // Assert

        assertTrue(result);
    }

    @Test
    void createDevice() {
        // Act

        Device actualResult = controller.createDevice(new FridgeType());

        // Assert

        assertEquals(validDeviceFridge, actualResult);
    }

    @Test
    void seeIfAddProgramToProgramList() {
        // Arrange

        FixedTimeProgram program = new FixedTimeProgram("Low Heat", 60, 100);
        ProgramList programList = new ProgramList();
        String expectedResult = "---------------\n" +
                "\n" +
                "0) Program Name: Low Heat, Duration: 60.0, Energy Consumption: 100.0\n" +
                "---------------\n";

        // Act

        controller.addProgramToProgramList(programList, program);
        String actualResult = programList.buildString();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetProgramAttributeNamesTest() {
        // Arrange

        FixedTimeProgram program = new FixedTimeProgram("Low Heat", 60, 120);
        controller.setProgramAttributeValue(program, 0, 100);
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add(FixedTimeProgram.DURATION);
        expectedResult.add(FixedTimeProgram.ENERGY_CONSUMPTION);

        // Act

        List<String> actualResult = controller.getProgramAttributeNames(program);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetProgramAttributeCharacteristics() {

        // Arrange

        FixedTimeProgram program = new FixedTimeProgram("Low Heat", 60, 200);
        String expectedResultUnit = "min";
        double expectedResultValue = 60;

        // Act

        Object actualResultUnit = controller.getProgramAttributeUnit(program, 0);
        Object actualResultValue = controller.getProgramAttributeValue(program, 0);

        // Assert

        assertEquals(expectedResultUnit, actualResultUnit);
        assertEquals(expectedResultValue, actualResultValue);
    }

    @Test
    void seeIfSetProgramName() {
        // Arrange

        FixedTimeProgram program = new FixedTimeProgram();
        controller.setProgramName(program, "Low Heat");
        String expectedResult = "Low Heat";

        // Act

        String actualResult = program.getProgramName();

        // Assert

        assertEquals(expectedResult, actualResult);
    }


    @Test
    void seeIfSetDeviceName() {
        // Arrange

        WaterHeater washMach = new WaterHeater(new WaterHeaterSpec());

        // Act

        controller.setDeviceName("Not a Washing Machine", washMach);
        String actualResult = washMach.getName();

        // Assert

        assertEquals("Not a Washing Machine", actualResult);
    }


//    @Test
//    void deviceListSize() {
//        //Arrange
//        String PATH_TO_FRIDGE = "pt.ipp.isep.dei.project.model.device.devicetypes.FridgeType";
//        List<String> deviceTypeString = new ArrayList<>();
//        deviceTypeString.add(PATH_TO_FRIDGE);
//        House validHouse = new House("ISEP", new Address("Rua Dr. António Bernardino de Almeida", "431",
//                "4455-125", "Porto", "Portugal"),
//                new Local(20, 20, 20), 60,
//                180, deviceTypeString);
//        validHouse.setMotherAreaID(new GeographicArea("Porto", new AreaType("Cidade"),
//                2, 3, new Local(4, 4, 100)));
//        Room emptyDeviceList = new Room("emptyDeviceList", "emptyDeviceList", 2, 20, 20, 3, "Room1", "Grid1");
//        validHouse.saveSensor(emptyDeviceList);
//
//        //Act
//
//        int actualResult1 = controllercli.getDeviceListSize(RoomMapper.objectToDTO(emptyDeviceList), validHouse);
//
//        //Assert Empty List
//
//        assertEquals(0, actualResult1);
//    }
//
//    @Test
//    void see() {
//        String PATH_TO_FRIDGE = "pt.ipp.isep.dei.project.model.device.devicetypes.FridgeType";
//        List<String> deviceTypeString = new ArrayList<>();
//        deviceTypeString.add(PATH_TO_FRIDGE);
//        House validHouse = new House("ISEP", new Address("Rua Dr. António Bernardino de Almeida", "431",
//                "4455-125", "Porto", "Portugal"),
//                new Local(20, 20, 20), 60,
//                180, deviceTypeString);
//        validHouse.setMotherAreaID(new GeographicArea("Porto", new AreaType("Cidade"),
//                2, 3, new Local(4, 4, 100)));
//        Room validRoom = new Room("Bedroom", "Single Bedroom", 2, 30, 40, 10, "Room1", "Grid1");
//        Device validDevice = new WaterHeater(new WaterHeaterSpec());
//        validRoom.addDevice(validDevice);
//        validHouse.saveSensor(validRoom);
//
//        //Act
//
//        Device actualResult = controllercli.getDeviceByIndex(RoomMapper.objectToDTO(validRoom), validHouse, 0);
//
//        //Assert
//
//        assertEquals(validDevice, actualResult);
//    }
}