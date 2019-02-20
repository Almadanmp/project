package pt.ipp.isep.dei.project.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testng.Assert;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.model.device.*;
import pt.ipp.isep.dei.project.model.device.devicespecs.*;
import pt.ipp.isep.dei.project.model.device.devicetypes.WaterHeaterDT;
import pt.ipp.isep.dei.project.model.device.program.Program;
import pt.ipp.isep.dei.project.model.device.program.ProgramList;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.testng.Assert.assertEquals;

/**
 * RoomConfigurationController tests class.
 */

class RoomConfigurationControllerTest {

    // Common artifacts for testing in this class.
    public static final String PATH_TO_FRIDGE = "pt.ipp.isep.dei.project.model.device.devicetypes.FridgeDT";


    /*USER STORY 230 - As a Room Owner [or Power User, or Administrator], I want to know the total
    nominal power of a room, i.e. the sum of the nominal power of all devices in the
    room. - TERESA VARELA */

    @Test
    void seeRoomWithoutDevicesNominalPower() {
        //ARRANGE
        Room room1 = new Room("room1", 19, 5, 3, 3);
        double expectedResult = 0;
        RoomConfigurationController ctrl = new RoomConfigurationController();
        //ACT
        double actualResult = ctrl.getRoomNominalPower(room1);
        //ASSERT
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeGetRoomNominalPower() {
        //ARRANGE
        RoomConfigurationController ctrl = new RoomConfigurationController();

        Device d1 = new Fridge(new FridgeSpec());
        d1.setNominalPower(12.0);
        ctrl.setAttributeValue(d1, FridgeSpec.FREEZER_CAPACITY, 4D);
        ctrl.setAttributeValue(d1, FridgeSpec.REFRIGERATOR_CAPACITY, 4D);
        ctrl.setAttributeValue(d1, FridgeSpec.ANNUAL_CONSUMPTION, 56D);
        d1.setName("hgsdsg");
        Device d2 = new Fridge(new FridgeSpec());
        d2.setName("fdshht");
        d2.setNominalPower(10.0);
        ctrl.setAttributeValue(d2, FridgeSpec.FREEZER_CAPACITY, 4D);
        ctrl.setAttributeValue(d2, FridgeSpec.REFRIGERATOR_CAPACITY, 4D);
        ctrl.setAttributeValue(d2, FridgeSpec.ANNUAL_CONSUMPTION, 56D);
        Device d3 = new Fridge(new FridgeSpec());
        d3.setName("hfsh");
        d3.setNominalPower(1.0);
        ctrl.setAttributeValue(d3, FridgeSpec.FREEZER_CAPACITY, 4D);
        ctrl.setAttributeValue(d3, FridgeSpec.REFRIGERATOR_CAPACITY, 4D);
        ctrl.setAttributeValue(d3, FridgeSpec.ANNUAL_CONSUMPTION, 56D);
        DeviceList deviceList = new DeviceList();
        deviceList.addDevice(d1);
        deviceList.addDevice(d2);
        deviceList.addDevice(d3);
        Room room1 = new Room("room1", 19, 5, 3, 3);
        room1.setDeviceList(deviceList);
        double expectedResult = 23;
        //ACT
        double actualResult = ctrl.getRoomNominalPower(room1);
        //ASSERT
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeSetAttributeValues() {
        //ARRANGE
        RoomConfigurationController ctrl = new RoomConfigurationController();

        Device d1 = new Fridge(new FridgeSpec());
        d1.setNominalPower(12.0);
        assertTrue(ctrl.setAttributeValue(d1, FridgeSpec.FREEZER_CAPACITY, 4D));
        assertTrue(ctrl.setAttributeValue(d1, FridgeSpec.REFRIGERATOR_CAPACITY, 4D));
        assertTrue(ctrl.setAttributeValue(d1, FridgeSpec.ANNUAL_CONSUMPTION, 56D));
        d1.setName("hgsdsg");
        Device d2 = new Fridge(new FridgeSpec());
        d2.setName("fdshht");
        d2.setNominalPower(10.0);
        assertTrue(ctrl.setAttributeValue(d2, FridgeSpec.FREEZER_CAPACITY, 4D));
        assertTrue(ctrl.setAttributeValue(d2, FridgeSpec.REFRIGERATOR_CAPACITY, 4D));
        assertTrue(ctrl.setAttributeValue(d2, FridgeSpec.ANNUAL_CONSUMPTION, 56D));
        Device d3 = new Fridge(new FridgeSpec());
        d3.setName("hfsh");
        d3.setNominalPower(1.0);
        assertTrue(ctrl.setAttributeValue(d3, FridgeSpec.FREEZER_CAPACITY, 4D));
        assertTrue(ctrl.setAttributeValue(d3, FridgeSpec.REFRIGERATOR_CAPACITY, 4D));
        assertTrue(ctrl.setAttributeValue(d3, FridgeSpec.ANNUAL_CONSUMPTION, 56D));
        DeviceList deviceList = new DeviceList();
        deviceList.addDevice(d1);
        deviceList.addDevice(d2);
        deviceList.addDevice(d3);
        Room room1 = new Room("room1", 19, 5, 3, 3);
    }

    @Test
    void seeSetAttributeValuesFalse() {
        //ARRANGE
        RoomConfigurationController ctrl = new RoomConfigurationController();

        Device d1 = new Fridge(new FridgeSpec());
        d1.setNominalPower(12.0);
        assertFalse(ctrl.setAttributeValue(d1, FridgeSpec.FREEZER_CAPACITY, "Fail"));
        assertFalse(ctrl.setAttributeValue(d1, FridgeSpec.REFRIGERATOR_CAPACITY, "Fail"));
        assertFalse(ctrl.setAttributeValue(d1, FridgeSpec.ANNUAL_CONSUMPTION, "Fail"));
        d1.setName("hgsdsg");
        Device d2 = new Fridge(new FridgeSpec());
        d2.setName("fdshht");
        d2.setNominalPower(10.0);
        assertFalse(ctrl.setAttributeValue(d2, FridgeSpec.FREEZER_CAPACITY, "Fail"));
        assertFalse(ctrl.setAttributeValue(d2, FridgeSpec.REFRIGERATOR_CAPACITY, "Fail"));
        assertFalse(ctrl.setAttributeValue(d2, FridgeSpec.ANNUAL_CONSUMPTION, "Fail"));
        Device d3 = new Fridge(new FridgeSpec());
        d3.setName("hfsh");
        d3.setNominalPower(1.0);
        assertFalse(ctrl.setAttributeValue(d3, FridgeSpec.FREEZER_CAPACITY, "Fail"));
        assertFalse(ctrl.setAttributeValue(d3, FridgeSpec.REFRIGERATOR_CAPACITY, "Fail"));
        assertFalse(ctrl.setAttributeValue(d3, FridgeSpec.ANNUAL_CONSUMPTION, "Fail"));
    }

    /* USER STORY 253 - As an Administrator, I want to add a new sensor to a room from the list of available
    sensor types, in order to configure it. - ANDRÉ RUA */


    @Test
    void seeIfPrintSensorListWorks() {
        //Arrange
        Sensor s1 = new Sensor("Vento1", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 10, 4).getTime());
        Sensor s2 = new Sensor("Pluviosidade1", new TypeSensor("Pluviosidade", "l/m2"),
                new Local(10, 30, 20),
                new GregorianCalendar(118, 12, 4).getTime());
        SensorList sList = new SensorList();
        sList.addSensor(s1);
        sList.addSensor(s2);
        //Act
        RoomConfigurationController ctrl = new RoomConfigurationController();
        String actualResult = ctrl.buildSensorListString(sList);
        String expectedResult = "---------------\n" +
                "0) Name: Vento1 | Type: Atmosphere\n" +
                "1) Name: Pluviosidade1 | Type: Pluviosidade\n" +
                "---------------\n";
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfPrintDeviceList() {
        RoomConfigurationController ctrl = new RoomConfigurationController();
        Device d1 = new Fridge(new FridgeSpec());
        d1.setName("frigorifico");
        d1.setNominalPower(200.0);
        d1.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 5D);
        d1.setAttributeValue(FridgeSpec.REFRIGERATOR_CAPACITY, 7D);
        d1.setAttributeValue(FridgeSpec.ANNUAL_CONSUMPTION, 56D);
        Room room = new Room("kitchen", 1, 1, 2, 2);
        DeviceList dlist = new DeviceList();
        dlist.addDevice(d1);
        room.setDeviceList(dlist);
        String result = ctrl.buildDeviceListString(room);
        String expectedResult = "---------------\n" +
                "\n" +
                "0) device Name: frigorifico, device Type: Fridge, device Nominal Power: 200.0\n" +
                "---------------\n";
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfSensorIsAddedToRoom() {
        //Arrange
        Room room1 = new Room("Quarto", 1, 5, 1, 21);
        Sensor sensor1 = new Sensor("coiso", new TypeSensor("rain", "mm"), new GregorianCalendar(2, 3, 4).getTime());
        //Act
        RoomConfigurationController ctrl = new RoomConfigurationController();
        boolean actualResult = ctrl.addSensorToRoom(room1, sensor1);
        //Assert
        assertTrue(actualResult);
    }

    @Test
    void setNominalPowerDevice() {
        RoomConfigurationController ctrl = new RoomConfigurationController();
        Device device = new Fridge(new FridgeSpec());
        device.setNominalPower(3.0);
        ctrl.setNominalPowerDevice(device, 5);
        double result = device.getNominalPower();
        assertEquals(5.0, result);
    }

    @Test
    void removeDeviceSuccess() {
        RoomConfigurationController ctrl = new RoomConfigurationController();
        Room r1 = new Room("quarto", 1, 12, 12, 12);
        Device d2 = new WaterHeater(new WaterHeaterSpec());
        d2.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 200D);
        d2.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 20);
        d2.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 10D);
        Device d3 = new WaterHeater(new WaterHeaterSpec());
        d3.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 500D);
        d3.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 30);
        d3.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 1D);
        r1.addDevice(d2);
        r1.addDevice(d3);
        boolean result = ctrl.removeDevice(r1, d2);
        assertEquals(true, result);
    }

    @Test
    void removeDeviceFails() {
        RoomConfigurationController ctrl = new RoomConfigurationController();
        Room r1 = new Room("quarto", 1, 12, 12, 12);
        Device d2 = new WaterHeater(new WaterHeaterSpec());
        d2.setName("fdsgdg");
        d2.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 200D);
        d2.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 2);
        d2.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 1D);
        Device d3 = new WaterHeater(new WaterHeaterSpec());
        d3.setName("hfhfdh");
        d3.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 500D);
        d3.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 3D);
        d3.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 1D);
        Device d4 = new WaterHeater(new WaterHeaterSpec());
        d4.setName("jhgjhgj");
        d4.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 50D);
        d4.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 3D);
        d4.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 1D);
        r1.addDevice(d2);
        r1.addDevice(d3);
        boolean result = ctrl.removeDevice(r1, d4);
        assertEquals(false, result);

    }

    @Test
    void ensureThatWeDeactivateDevice() {
        RoomConfigurationController roomConfigurationController = new RoomConfigurationController();
        Device d2 = new WaterHeater(new WaterHeaterSpec());
        d2.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 200D);
        d2.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 2);
        d2.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 1D);
        boolean expectedResult = true;
        boolean actualResult = roomConfigurationController.deactivateDevice(d2);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void ensureThatWeDoNotDeactivateDevice() {
        RoomConfigurationController roomConfigurationController = new RoomConfigurationController();
        Device d2 = new WaterHeater(new WaterHeaterSpec());
        d2.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 200D);
        d2.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 2);
        d2.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 1D);
        roomConfigurationController.deactivateDevice(d2);
        boolean expectedResult = false;
        boolean actualResult = roomConfigurationController.deactivateDevice(d2);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfConfigureOneWashingMachineProgram() {
        RoomConfigurationController ctrl = new RoomConfigurationController();
        Device d1 = new WashingMachine(new WashingMachineSpec());
        Program program = new Program("rep", 22, 23);
        ProgramList programList = new ProgramList();
        programList.addProgram(program);
        ctrl.configureProgramListFromAProgrammableDevice(d1, programList);
        Object result = d1.getAttributeValue("programList");
        assertEquals(d1.getAttributeValue("programList"), result);
    }

    @Test
    public void getAttributeNamesTest() {
        RoomConfigurationController ctrl = new RoomConfigurationController();
        Device d1 = new Lamp(new LampSpec());
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("Luminous Flux");
        List<String> result = ctrl.getAttributeNames(d1);
        assertEquals(expectedResult, result);
    }

    @Test
    public void getAttributeNamesTest1() {
        RoomConfigurationController ctrl = new RoomConfigurationController();
        Device d1 = new Lamp(new LampSpec());
        String expectedResult = "Lamp";
        String result = ctrl.getType(d1);
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfAddDeviceToRoom() {
        RoomConfigurationController ctrl = new RoomConfigurationController();
        Device device = new WaterHeater(new WaterHeaterSpec());
        device.setName("waterheater");
        device.setNominalPower(150.0);
        Room room = new Room("cozinha", 1, 1, 1, 1);
        ctrl.addDevice(room, device);
        String result = ctrl.buildDeviceListString(room);
        String expectedResult = "---------------\n" +
                "\n" +
                "0) device Name: waterheater, device Type: WaterHeater, device Nominal Power: 150.0\n" +
                "---------------\n";
        assertEquals(expectedResult, result);
    }

    @Test
    void addDeviceFails() {
        RoomConfigurationController ctrl = new RoomConfigurationController();
        Room r1 = new Room("quarto", 1, 12, 12, 12);
        Device d2 = new WaterHeater(new WaterHeaterSpec());
        d2.setName("wHeater1");
        d2.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 200D);
        d2.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 30D);
        d2.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 10D);
        Device d3 = new WaterHeater(new WaterHeaterSpec());
        d3.setName("wHeater1");
        d3.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 500D);
        d3.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 30D);
        d3.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 1D);
        r1.addDevice(d2);
        boolean expectedResult = false;
        boolean result = ctrl.addDevice(r1, d3);
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void addDeviceTrue() {
        RoomConfigurationController ctrl = new RoomConfigurationController();
        Room r1 = new Room("quarto", 1, 12, 12, 12);
        Device d2 = new WaterHeater(new WaterHeaterSpec());
        d2.setName("sadffdg");
        d2.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 200D);
        d2.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 30D);
        d2.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 10D);
        r1.addDevice(d2);
        boolean expectedResult = false;
        boolean result = ctrl.addDevice(r1, d2);
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void createDevice() {
        RoomConfigurationController ctrl = new RoomConfigurationController();
        Room r1 = new Room("quarto", 1, 12, 12, 12);
        Device d2 = new WaterHeater(new WaterHeaterSpec());
        d2.setName("wHeater1");
        d2.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 200D);
        d2.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 30D);
        d2.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 10D);
        r1.addDevice(d2);
        Device expectedResult = d2;
        Device result = ctrl.createDevice(new WaterHeaterDT());
        result.setName("wHeater1");
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void seeIfAddProgramToProgramList() {
        RoomConfigurationController ctrl = new RoomConfigurationController();
        Program program = new Program("program", 2, 3);
        ProgramList plist = new ProgramList();
        ctrl.addProgramToProgramList(plist, program);
        String expectedResult = "---------------\n" + "\n0) Program Name: program, Duration: 2.0, Energy Consumption: 3.0"
                + "\n---------------\n";
        String result = plist.buildProgramListString();
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetProgramAttributeNamesTest() {
        RoomConfigurationController ctrl = new RoomConfigurationController();
        Program program = new Program("program", 2, 2);
        ctrl.setProgramAttributeValue(program, 0, 34);
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add(Program.DURATION);
        expectedResult.add(Program.ENERGY_CONSUMPTION);
        List<String> result = ctrl.getProgramAttributeNames(program);
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetProgramList() {
        RoomConfigurationController ctrl = new RoomConfigurationController();
        Dishwasher d1 = new Dishwasher(new DishwasherSpec());
        ctrl.setAttributeValue(d1, "Duration", 34);
        Program program = new Program("programa", 2, 2);
        ProgramList listProgram = ctrl.getProgramList(d1);
        ctrl.addProgramToProgramList(listProgram, program);
        ProgramList result = ctrl.getProgramList(d1);
        Assertions.assertEquals(listProgram, result);
    }

    @Test
    void seeIfGetProgramAttributeUnit() {
        RoomConfigurationController ctrl = new RoomConfigurationController();
        Program program = new Program("program", 2, 2);
        ctrl.setProgramAttributeValue(program, 0, 12);
        String expectedResult = "min";
        Object result = ctrl.getProgramAttributeUnit(program, 0);
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetProgramAttributeValue() {
        RoomConfigurationController ctrl = new RoomConfigurationController();
        Program program = new Program("program", 2, 2);
        double expectedResult = 2.0;
        Object result = ctrl.getProgramAttributeValue(program, 1);
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAttributeValue() {
        RoomConfigurationController ctrl = new RoomConfigurationController();
        WaterHeaterSpec whspec = new WaterHeaterSpec();
        whspec.setAttributeValue("Volume Of Water", 5D);
        WaterHeater device = new WaterHeater(whspec);
        double expectedResult = 5.0;
        Object result = ctrl.getAttributeValue(device, 0);
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfSetProgramName() {
        RoomConfigurationController ctrl = new RoomConfigurationController();
        Program program = new Program();
        ctrl.setProgramName(program, "program");
        String result = program.getProgramName();
        String expectedResult = "program";
        assertEquals(result, expectedResult);
    }

    @Test
    void getProgramListFromAProgrammableDevice() {
        RoomConfigurationController ctrl = new RoomConfigurationController();
        WashingMachineSpec wmSpec = new WashingMachineSpec();
        WashingMachine washMach = new WashingMachine(wmSpec);
        ProgramList programList = washMach.getProgramList();
        Program program = new Program();
        programList.addProgram(program);
        ProgramList result = ctrl.getProgramList(washMach);
        assertEquals(result, programList);
    }

    @Test
    void seeIfIsProgrammableTrue() {
        RoomConfigurationController ctrl = new RoomConfigurationController();
        WashingMachineSpec wmSpec = new WashingMachineSpec();
        WashingMachine washMach = new WashingMachine(wmSpec);
        boolean result = ctrl.isProgrammable(washMach);
        Assert.assertTrue(result);
    }

    @Test
    void seeIfIsProgrammableFalse() {
        RoomConfigurationController ctrl = new RoomConfigurationController();
        WaterHeaterSpec wmSpec = new WaterHeaterSpec();
        WaterHeater washMach = new WaterHeater(wmSpec);
        ctrl.setDeviceName("not a washing machine", washMach);
        boolean result = ctrl.isProgrammable(washMach);
        Assert.assertFalse(result);
    }

    @Test
    void seeIfSetDeviceName() {
        RoomConfigurationController ctrl = new RoomConfigurationController();
        WaterHeaterSpec wmSpec = new WaterHeaterSpec();
        WaterHeater washMach = new WaterHeater(wmSpec);
        ctrl.setDeviceName("not a washing machine", washMach);
        String result = washMach.getName();
        assertEquals(result, "not a washing machine");
    }


}