package pt.ipp.isep.dei.project.controller;

import pt.ipp.isep.dei.project.model.*;
import org.junit.jupiter.api.Test;

import static org.testng.Assert.*;

public class US145ControllerTest {
    @Test
    public void ensureThatWeAddRoomToEnergyGrid() {
        US145Controller ctrl145 = new US145Controller();
        Room room1 = new Room("quarto1", 19, 23456789);
        EnergyGrid eg1 = new EnergyGrid("rede", 56789);
        RoomList rl = new RoomList();
        eg1.setListOfRooms(rl);

        boolean expectedResult = true;

        boolean actualResult = ctrl145.addRoomToEnergyGrid(room1,eg1);

        assertEquals(expectedResult,actualResult);
    }
}