package pt.ipp.isep.dei.project.io.ui.utils;

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
    public void roomListsAreInvalid() {
        Room room1 = new Room("room1", 19, 23456789, 5, 3);
        UtilsUI utilsUI = new UtilsUI();
        Mapper mapper = new Mapper();
        House validHouse = new House("ISEP", new Address("Rua Dr. António Bernardino de Almeida",
                "4455-125", "Porto"),
                new Local(20, 20, 20), 60,
                180, new ArrayList<>());
        validHouse.setMotherArea(new GeographicArea("Porto", new TypeArea("Cidade"),
                2, 3, new Local(4, 4, 100)));
        validHouse.addRoom(room1);
        RoomDTO roomDTO = mapper.roomToDTO(room1);

        boolean result1 = utilsUI.roomDTOSensorListIsValid(roomDTO, validHouse);
        boolean result2 = utilsUI.roomDTODeviceListIsValid(roomDTO, validHouse);

        //ASSERT
        assertFalse(result1);
        assertFalse(result2);
    }

    @Test
    public void roomListsAreValid() {
        Room room1 = new Room("room1", 19, 23456789, 5, 3);
        GregorianCalendar date = new GregorianCalendar(2010, 11, 2, 12, 12);
        Sensor sensor1 = new Sensor("RF12345", "sensor", new TypeSensor("sensor", "celsius"), new Local(2, 2, 2), date.getTime());
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
        boolean result1 = utilsUI.roomDTOSensorListIsValid(roomDTO, validHouse);
        boolean result2 = utilsUI.roomDTODeviceListIsValid(roomDTO, validHouse);

        //ASSERT
        assertTrue(result1);
        assertTrue(result2);
    }

    @Test
    void seeIfPrintMessageWorks() {

        //Arrange

        String expectedResult = "test";

        // Act

        String actualResult = UtilsUI.printMessage("test");

        //Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfPrintMessageFails() {

        //Arrange

        String expectedResult = "test";

        // Act

        String actualResult = UtilsUI.printMessage("test2");

        //Assert

        assertNotEquals(expectedResult, actualResult);
    }
}