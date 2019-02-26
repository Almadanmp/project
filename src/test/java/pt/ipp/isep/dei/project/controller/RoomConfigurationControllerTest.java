package pt.ipp.isep.dei.project.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testng.Assert;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.model.device.*;
import pt.ipp.isep.dei.project.model.device.devicespecs.*;
import pt.ipp.isep.dei.project.model.device.devicetypes.FridgeDT;
import pt.ipp.isep.dei.project.model.device.program.Program;
import pt.ipp.isep.dei.project.model.device.program.ProgramList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.testng.Assert.assertEquals;

/**
 * RoomConfigurationController tests class.
 */

class RoomConfigurationControllerTest {

    // Common artifacts for testing in this class.

    private Room validRoomWithDevices;
    private Room validRoomNoDevices;
    private Device validDeviceFridge = new Fridge(new FridgeSpec());
    private RoomConfigurationController controller = new RoomConfigurationController();

    @BeforeEach
    void arrangeArtifacts(){
        validRoomWithDevices = new Room("Office", 2, 15, 15, 10);
        validRoomNoDevices = new Room("Kitchen", 1, 20, 20, 10);
        controller.setAttributeValue(validDeviceFridge, FridgeSpec.FREEZER_CAPACITY, 4D);
        controller.setAttributeValue(validDeviceFridge, FridgeSpec.REFRIGERATOR_CAPACITY, 4D);
        controller.setAttributeValue(validDeviceFridge, FridgeSpec.ANNUAL_CONSUMPTION, 56D);
        validDeviceFridge.setNominalPower(25);
        validRoomWithDevices.addDevice(validDeviceFridge);
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

    /* USER STORY 253 - As an Administrator, I want to add a new sensor to a room from the list of available
    sensor types, in order to configure it. */

    @Test
    void seeIfPrintSensorListWorks() {
        //Arrange
        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");;
        Date date = new Date();
        try {
            date = validSdf.parse("03/12/2017 10:02:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Sensor s1 = new Sensor("SensorOne", new TypeSensor("Wind", "km/h"),
                new Local(12, 31, 21),
                date);
        Sensor s2 = new Sensor("SensorTwo", new TypeSensor("Rain", "l/m2"),
                new Local(10, 30, 20),
               date);
        SensorList sensorList = new SensorList();
        sensorList.addSensor(s1);
        sensorList.addSensor(s2);
        String expectedResult = "---------------\n" +
                "0) Name: SensorOne | Type: Wind\n" +
                "1) Name: SensorTwo | Type: Rain\n" +
                "---------------\n";

        //Act

        String actualResult = controller.buildSensorListString(sensorList);

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
        Sensor testSensor = new Sensor("SensorOne", new TypeSensor("Rain", "mm"), date);
        // Act

        boolean actualResult = controller.addSensorToRoom(validRoomWithDevices, testSensor);

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

        Assert.assertTrue(actualResult);
    }

    @Test
    void removeDeviceFails() {
        // Act

        boolean actualResult = controller.removeDevice(validRoomNoDevices, validDeviceFridge);

        Assert.assertFalse(actualResult);
    }

    @Test
    void ensureThatWeDeactivateDevice() {
        // Act

        boolean actualResult = controller.deactivateDevice(validDeviceFridge);

        // Assert

        Assert.assertTrue(actualResult);
    }

    @Test
    void ensureThatWeDoNotDeactivateDevice() {
        // Act

        controller.deactivateDevice(validDeviceFridge);
        boolean actualResult = controller.deactivateDevice(validDeviceFridge);

        // Assert

        Assert.assertFalse(actualResult);
    }

    @Test
    void seeIfConfigureOneWashingMachineProgramWorks() {
        // Arrange

        Device washingMachine = new WashingMachine(new WashingMachineSpec());
        Program program = new Program("LowHeat", 60, 100);
        ProgramList programList = new ProgramList();
        programList.addProgram(program);

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

        Device actualResult = controller.createDevice(new FridgeDT());

        // Assert

        Assertions.assertEquals(validDeviceFridge, actualResult);
    }

    @Test
    void seeIfAddProgramToProgramList() {
        // Arrange

        Program program = new Program("Low Heat", 60, 100);
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

        Program program = new Program("Low Heat", 60, 120);
        controller.setProgramAttributeValue(program, 0, 100);
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add(Program.DURATION);
        expectedResult.add(Program.ENERGY_CONSUMPTION);

        // Act

        List<String> actualResult = controller.getProgramAttributeNames(program);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetProgramAttributeCharacteristics() {

        // Arrange

        Program program = new Program("Low Heat", 60, 200);
        String expectedResultUnit = "min";
        double expectedResultValue = 60;

        // Act

        Object actualResultUnit = controller.getProgramAttributeUnit(program, 0);
        Object actualResultValue = controller.getProgramAttributeValue(program,0);

        // Assert

        Assertions.assertEquals(expectedResultUnit, actualResultUnit);
        assertEquals(expectedResultValue, actualResultValue);
    }

    @Test
    void seeIfSetProgramName() {
        // Arrange

        Program program = new Program();
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

        assertEquals(actualResult, "Not a Washing Machine");
    }


}