package pt.ipp.isep.dei.project.io.ui;

import org.testng.annotations.Test;
import pt.ipp.isep.dei.project.dto.Mapper;
import pt.ipp.isep.dei.project.dto.RoomDTO;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.Dishwasher;
import pt.ipp.isep.dei.project.model.device.devicespecs.DishwasherSpec;
import pt.ipp.isep.dei.project.model.device.log.Log;
import pt.ipp.isep.dei.project.model.device.program.FixedTimeProgram;
import pt.ipp.isep.dei.project.model.device.program.ProgramList;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import static org.testng.Assert.*;

public class UtilsUITest {

    // Common artifacts for testing in this class.
    private static final String PATH_TO_FRIDGE = "pt.ipp.isep.dei.project.model.device.devicetypes.FridgeType";


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
        House house = new House("Casa de praia", address, new Local(4, 6, 5), 60, 180, deviceTypeString);
        house.setMotherArea(ga);
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
        House house = new House("Casa de praia", address, new Local(4, 6, 5), 60, 180, deviceTypeString);
        house.setMotherArea(ga);
        Room room1 = new Room("room1", 19, 23456789, 5, 3);
        Device device = new Dishwasher(new DishwasherSpec());
        room1.addDevice(device);
        EnergyGrid energyGrid1 = new EnergyGrid("mainGrid", 200);
        house.addRoom(room1);
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
        Mapper mapper = new Mapper();
        House validHouse = new House("ISEP", new Address("Rua Dr. António Bernardino de Almeida",
                "4455-125", "Porto"),
                new Local(20, 20, 20),  60,
                180, new ArrayList<>());
        validHouse.setMotherArea(new GeographicArea("Porto", new TypeArea("Cidade"),
                2, 3, new Local(4, 4, 100)));
        validHouse.addRoom(room1);
        RoomDTO roomDTO = mapper.roomToDTO(room1);

        boolean result1 = utilsUI.roomDTOSensorListIsValid(roomDTO,validHouse);
        boolean result2 = utilsUI.roomDTODeviceListIsValid(roomDTO,validHouse);

        //ASSERT
        assertFalse(result1);
        assertFalse(result2);
    }

    @Test
    public void roomListsAreValid() {
        Room room1 = new Room("room1", 19, 23456789, 5, 3);
        GregorianCalendar date = new GregorianCalendar(2010, 11, 2, 12, 12);
        Sensor sensor1 = new Sensor("RF12345","sensor", new TypeSensor("sensor", "celsius"), new Local(2, 2, 2), date.getTime());
        room1.addSensor(sensor1);
        Device device = new Dishwasher(new DishwasherSpec());
        room1.addDevice(device);
        Mapper mapper = new Mapper();
        House validHouse = new House("ISEP", new Address("Rua Dr. António Bernardino de Almeida",
                "4455-125", "Porto"),
                new Local(20, 20, 20), 60,
                180, new ArrayList<>());
        validHouse.setMotherArea(new GeographicArea("Porto", new TypeArea("Cidade"),
                2, 3, new Local(4, 4, 100)));
        validHouse.addRoom(room1);
        RoomDTO roomDTO = mapper.roomToDTO(room1);
        UtilsUI utilsUI = new UtilsUI();
        //ACT
        boolean result1 = utilsUI.roomDTOSensorListIsValid(roomDTO,validHouse);
        boolean result2 = utilsUI.roomDTODeviceListIsValid(roomDTO,validHouse);

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
        FixedTimeProgram program = new FixedTimeProgram("eco", 25, 50);
        programList.add(program);
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
        Sensor sensor1 = new Sensor("RF12345","sensor", new TypeSensor("sensor", "celsius"), new Local(2, 2, 2), date.getTime());
        geographicArea.addSensor(sensor1);
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
    void seeIfPrintMessageWorks() {

        //Arrange

        String expectedResult = "test";

        // Act

        String actualResult = UtilsUI.printMessage("test");

        //Assert

        assertEquals(expectedResult,actualResult);
    }

    @Test
    void seeIfPrintMessageFails() {

        //Arrange

        String expectedResult = "test";

        // Act

        String actualResult = UtilsUI.printMessage("test2");

        //Assert

        assertNotEquals(expectedResult,actualResult);
    }
}