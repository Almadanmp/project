package pt.ipp.isep.dei.project.controller;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Device;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.Room;
import pt.ipp.isep.dei.project.model.devicetypes.Fridge;
import pt.ipp.isep.dei.project.model.devicetypes.WaterHeater;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EGConsumptionControllerTest {


    //US752 TESTS

    @Test
    public void getDailyHouseConsumptionTest() {
        EGConsumptionController controller = new EGConsumptionController();
        House h1 = new House();
        Room r1 = new Room("quarto", 1, 12, 12, 12);
        Device d1 = new Device("fridgeOne", 12, new Fridge());
        Device d2 = new Device("wHeater1", 12, new WaterHeater(200, 30, 1));
        Device d3 = new Device("wHeater2", 11, new WaterHeater(500, 20, 10));
        r1.addDevice(d1);
        r1.addDevice(d2);
        r1.addDevice(d3);
        Room r2 = new Room("kitchen", 2, 12, 12, 12);
        Device d4 = new Device("fridgeTwo", 12, new Fridge());
        Device d5 = new Device("wHeater3", 12, new WaterHeater(300, 15, 1));
        Device d6 = new Device("wHeater4", 11, new WaterHeater(400, 20, 12));
        r2.addDevice(d4);
        r2.addDevice(d5);
        r2.addDevice(d6);
        h1.addRoomToRoomList(r1);
        h1.addRoomToRoomList(r2);
        double expectedResult = 457198.56000000006;
        double result = controller.getDailyHouseConsumptionWaterHeater(h1);
        assertEquals(expectedResult, result);
    }

    @Test
    public void getDailyHouseConsumptionNoRoomsTest() {
        EGConsumptionController controller = new EGConsumptionController();
        House h1 = new House();
        double expectedResult = 0;
        double result = controller.getDailyHouseConsumptionWaterHeater(h1);
        assertEquals(expectedResult, result);
    }

    @Test
    public void getDailyHouseConsumptionNoDevicesTest() {
        EGConsumptionController controller = new EGConsumptionController();
        House h1 = new House();
        Room r1 = new Room("quarto", 1, 12, 12, 12);
        Room r2 = new Room("kitchen", 2, 12, 12, 12);
        h1.addRoomToRoomList(r1);
        h1.addRoomToRoomList(r2);
        double expectedResult = 0;
        double result = controller.getDailyHouseConsumptionWaterHeater(h1);
        assertEquals(expectedResult, result);
    }

    @Test
    public void getDailyHouseConsumptionNoHeaterDevicesTest() {
        EGConsumptionController controller = new EGConsumptionController();
        House h1 = new House();
        Room r1 = new Room("quarto", 1, 12, 12, 12);
        Device d1 = new Device("fridgeOne", 12, new Fridge());
        r1.addDevice(d1);
        Room r2 = new Room("kitchen", 2, 12, 12, 12);
        Device d4 = new Device("fridgeTwo", 12, new Fridge());
        r2.addDevice(d4);
        h1.addRoomToRoomList(r1);
        h1.addRoomToRoomList(r2);
        double expectedResult = 0;
        double result = controller.getDailyHouseConsumptionWaterHeater(h1);
        assertEquals(expectedResult, result);
    }
}
