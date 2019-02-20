package pt.ipp.isep.dei.project.io.ui;

import org.testng.annotations.Test;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.Dishwasher;
import pt.ipp.isep.dei.project.model.device.devicespecs.DishwasherSpec;
import pt.ipp.isep.dei.project.model.device.log.Log;
import pt.ipp.isep.dei.project.model.device.program.Program;
import pt.ipp.isep.dei.project.model.device.program.ProgramList;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class UtilsUITest {

    // Common artifacts for testing in this class.
    private static final String PATH_TO_FRIDGE = "pt.ipp.isep.dei.project.model.device.devicetypes.FridgeDT";


    @Test
    public void geographicAreaListIsInvalid() {
        GeographicAreaList geographicAreaList = new GeographicAreaList();
        GeographicAreaList geographicAreaList1 = null;
        UtilsUI utilsUI = new UtilsUI();
        //ACT
        boolean result1 = utilsUI.geographicAreaListIsValid(geographicAreaList);
        boolean result2 = utilsUI.geographicAreaListIsValid(geographicAreaList1);
        //ASSERT
        assertFalse(result1);
        assertFalse(result2);
    }

    @Test
    public void geographicAreaListIsValid() {
        GeographicAreaList geographicAreaList = new GeographicAreaList();
        GeographicAreaList geographicAreaList1 = new GeographicAreaList();
        GeographicArea geographicArea = new GeographicArea("Porto", new TypeArea("Cidade"), 20, 20, new Local(20, 20, 20));
        GeographicArea geographicArea1 = new GeographicArea("Lisboa", new TypeArea("Cidade"), 20, 20, new Local(20, 20, 20));

        geographicAreaList.addGeographicArea(geographicArea);
        geographicAreaList1.addGeographicArea(geographicArea);
        geographicAreaList1.addGeographicArea(geographicArea1);

        UtilsUI utilsUI = new UtilsUI();
        //ACT
        boolean result1 = utilsUI.geographicAreaListIsValid(geographicAreaList);
        boolean result2 = utilsUI.geographicAreaListIsValid(geographicAreaList1);
        //ASSERT
        assertTrue(result1);
        assertTrue(result2);
    }

    @Test
    public void houseListsAreInvalid() {
        GeographicArea ga = new GeographicArea("Portugal", new TypeArea("País"), 10, 20, new Local(16, 17, 18));
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        Address address = new Address("Rua das Flores", "4512", "Porto");
        House house = new House("Casa de praia", address, new Local(4, 6, 5), ga, 60, 180, deviceTypeString);
        UtilsUI utilsUI = new UtilsUI();
        //ACT
        boolean result1 = utilsUI.houseRoomListIsValid(house);
        boolean result2 = utilsUI.houseGridListIsValid(house);
        //ASSERT
        assertFalse(result1);
        assertFalse(result2);
    }

    @Test
    public void houseListsAreValid() {
        GeographicArea ga = new GeographicArea("Portugal", new TypeArea("País"), 10, 20, new Local(16, 17, 18));
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        Address address = new Address("Rua das Flores", "4512", "Porto");
        House house = new House("Casa de praia", address, new Local(4, 6, 5), ga, 60, 180, deviceTypeString);
        Room room1 = new Room("room1", 19, 23456789, 5, 3);
        Device device = new Dishwasher(new DishwasherSpec());
        room1.addDevice(device);
        EnergyGrid energyGrid1 = new EnergyGrid("mainGrid", 200);
        house.addRoomToRoomList(room1);
        house.addGrid(energyGrid1);
        UtilsUI utilsUI = new UtilsUI();
        //ACT
        boolean result1 = utilsUI.houseRoomListIsValid(house);
        boolean result2 = utilsUI.houseGridListIsValid(house);

        //ASSERT
        assertTrue(result1);
        assertTrue(result2);
    }

    @Test
    public void roomListsAreInvalid() {
        Room room1 = new Room("room1", 19, 23456789, 5, 3);
        UtilsUI utilsUI = new UtilsUI();
        //ACT
        boolean result1 = utilsUI.roomSensorListIsValid(room1);
        boolean result2 = utilsUI.roomDeviceListIsValid(room1);

        //ASSERT
        assertFalse(result1);
        assertFalse(result2);
    }

    @Test
    public void roomListsAreValid() {
        Room room1 = new Room("room1", 19, 23456789, 5, 3);
        GregorianCalendar date = new GregorianCalendar(2010, 11, 2, 12, 12);
        Sensor sensor1 = new Sensor("sensor", new TypeSensor("sensor", "celsius"), new Local(2, 2, 2), date.getTime());
        room1.addSensor(sensor1);
        Device device = new Dishwasher(new DishwasherSpec());
        room1.addDevice(device);
        UtilsUI utilsUI = new UtilsUI();
        //ACT
        boolean result1 = utilsUI.roomSensorListIsValid(room1);
        boolean result2 = utilsUI.roomDeviceListIsValid(room1);

        //ASSERT
        assertTrue(result1);
        assertTrue(result2);
    }


    @Test
    public void gridListsAreValid() {
        EnergyGrid energyGrid1 = new EnergyGrid("mainGrid", 200);
        Room room1 = new Room("room1", 19, 23456789, 5, 3);
        Device device = new Dishwasher(new DishwasherSpec());
        room1.addDevice(device);
        energyGrid1.addRoom(room1);
        UtilsUI utilsUI = new UtilsUI();
        //ACT
        boolean result1 = utilsUI.gridRoomListIsValid(energyGrid1);
        boolean result2 = utilsUI.gridDeviceListIsValid(energyGrid1);

        //ASSERT
        assertTrue(result1);
        assertTrue(result2);
    }

    @Test
    public void gridListsAreInvalid() {
        EnergyGrid energyGrid1 = new EnergyGrid("mainGrid", 200);
        UtilsUI utilsUI = new UtilsUI();
        //ACT
        boolean result1 = utilsUI.gridRoomListIsValid(energyGrid1);
        boolean result2 = utilsUI.gridDeviceListIsValid(energyGrid1);

        //ASSERT
        assertFalse(result1);
        assertFalse(result2);
    }

    @Test
    public void typeAreaListIsInvalid() {
        TypeAreaList typeAreaList = new TypeAreaList();
        TypeAreaList typeAreaList1 = null;
        UtilsUI utilsUI = new UtilsUI();
        //ACT
        boolean result1 = utilsUI.typeAreaListIsValid(typeAreaList);
        boolean result2 = utilsUI.typeAreaListIsValid(typeAreaList1);

        //ASSERT
        assertFalse(result1);
        assertFalse(result2);
    }

    @Test
    public void typeAreaListIsValid() {
        TypeAreaList typeAreaList = new TypeAreaList();
        TypeArea typeArea = new TypeArea("Cidade");
        typeAreaList.addTypeArea(typeArea);
        UtilsUI utilsUI = new UtilsUI();
        //ACT
        boolean result1 = utilsUI.typeAreaListIsValid(typeAreaList);
        //ASSERT
        assertTrue(result1);
    }

    @Test
    public void testProgramListIsInvalid() {
        ProgramList programList = new ProgramList();
        ProgramList programList1 = null;
        UtilsUI utilsUI = new UtilsUI();
        //ACT
        boolean result1 = utilsUI.programListIsValid(programList);
        boolean result2 = utilsUI.programListIsValid(programList1);

        //ASSERT
        assertFalse(result1);
        assertFalse(result2);
    }

    @Test
    public void testProgramListIsValid() {
        ProgramList programList = new ProgramList();
        Program program = new Program("eco", 25, 50);
        programList.addProgram(program);
        UtilsUI utilsUI = new UtilsUI();
        //ACT
        boolean result1 = utilsUI.programListIsValid(programList);

        //ASSERT
        assertTrue(result1);
    }

    @Test
    public void testDeviceLogListIsInvalid() {
        Device device = new Dishwasher(new DishwasherSpec());
        UtilsUI utilsUI = new UtilsUI();
        //ACT
        boolean result1 = utilsUI.deviceLogListIsValid(device);
        //ASSERT
        assertFalse(result1);
    }

    @Test
    public void testDeviceLogListIsValid() {
        Device device = new Dishwasher(new DishwasherSpec());
        GregorianCalendar date1 = new GregorianCalendar(2010, 11, 2, 12, 12);
        GregorianCalendar date2 = new GregorianCalendar(2010, 11, 2, 12, 42);
        Log log = new Log(20, date1.getTime(), date2.getTime());
        device.addLog(log);
        UtilsUI utilsUI = new UtilsUI();
        //ACT
        boolean result1 = utilsUI.deviceLogListIsValid(device);
        //ASSERT
        assertTrue(result1);
    }

    @Test
    public void typeSensorListIsInvalid() {
        TypeSensorList typeSensors = new TypeSensorList();
        UtilsUI utilsUI = new UtilsUI();
        //ACT
        boolean result1 = utilsUI.typeSensorListIsValid(typeSensors);
        //ASSERT
        assertFalse(result1);
    }

    @Test
    public void typeSensorListIsValid() {
        TypeSensorList typeSensors = new TypeSensorList();
        TypeSensor typeSensor = new TypeSensor("typeSensor", "celsius");
        typeSensors.add(typeSensor);
        UtilsUI utilsUI = new UtilsUI();
        //ACT
        boolean result1 = utilsUI.typeSensorListIsValid(typeSensors);
        //ASSERT
        assertTrue(result1);
    }

    @Test
    public void geographicAreaSensorListIsValid() {
        GeographicArea geographicArea = new GeographicArea("Porto", new TypeArea("Cidade"), 20, 20, new Local(20, 20, 20));
        GregorianCalendar date = new GregorianCalendar(2010, 11, 2, 12, 12);
        Sensor sensor1 = new Sensor("sensor", new TypeSensor("sensor", "celsius"), new Local(2, 2, 2), date.getTime());
        geographicArea.addSensorToSensorList(sensor1);
        UtilsUI utilsUI = new UtilsUI();
        //ACT
        boolean result1 = utilsUI.geographicAreaSensorListIsValid(geographicArea);
        //ASSERT
        assertTrue(result1);
    }

    @Test
    public void geographicAreaSensorListIsInvalid() {
        GeographicArea geographicArea = new GeographicArea("Porto", new TypeArea("Cidade"), 20, 20, new Local(20, 20, 20));
        UtilsUI utilsUI = new UtilsUI();
        //ACT
        boolean result1 = utilsUI.geographicAreaSensorListIsValid(geographicArea);
        //ASSERT
        assertFalse(result1);
    }

    @Test
    public void houseMotherAreaIsValid() {
        GeographicArea ga = new GeographicArea("Portugal", new TypeArea("País"), 10, 20, new Local(16, 17, 18));
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        Address address = new Address("Rua das Flores", "4512", "Porto");
        House house = new House("Casa de praia", address, new Local(4, 6, 5), ga, 60, 180, deviceTypeString);
        UtilsUI utilsUI = new UtilsUI();
        //ACT
        boolean result1 = utilsUI.houseMotherAreaIsValid(house);
        //ASSERT
        assertTrue(result1);
    }

    @Test
    public void houseMotherAreaIsInvalid() {
        GeographicArea ga = new GeographicArea("Portugal", new TypeArea("País"), 10, 20, new Local(16, 17, 18));
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        Address address = new Address("Rua das Flores", "4512", "Porto");
        House house = new House("Casa de praia", address, new Local(4, 6, 5), ga, 60, 180, deviceTypeString);
        GeographicArea ga1 = null;
        house.setMotherArea(ga1);
        UtilsUI utilsUI = new UtilsUI();
        //ACT
        boolean result1 = utilsUI.houseMotherAreaIsValid(house);
        //ASSERT
        assertFalse(result1);
    }
}