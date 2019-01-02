package pt.ipp.isep.dei.project.controller;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class US135ControllerTest {


    @Test
    void seeIfHouExistsInListWorks() {
        HouseList list = new HouseList();
        House house = new House("Casa Oliveira", "Santa Maria de Lamas", new Local(42, 50), "4535");
        list.addHouseToHouseList(house);
        US135Controller ctrl135 = new US135Controller(list);
        String houseName = "Casa Oliveira";
        boolean result = ctrl135.seeIfHouseExistsInHouseList(houseName);
        assertTrue(result);
    }

    @Test
    void seeIfHouExistsInListFails() {
        HouseList list = new HouseList();
        House house = new House("Casa Oliveira", "Santa Maria de Lamas", new Local(42, 50), "4535");
        list.addHouseToHouseList(house);
        US135Controller ctrl135 = new US135Controller(list);
        String houseName = "Casa Marques";
        boolean result = ctrl135.seeIfHouseExistsInHouseList(houseName);
        assertTrue(!result);
    }

    @Test
    void seeIfPowerSourceIsCreated(){
        HouseList list = new HouseList();
        House house = new House("Casa Oliveira", "Santa Maria de Lamas", new Local(42, 50), "4535");
        list.addHouseToHouseList(house);
        US135Controller ctrl135 = new US135Controller(list);
        ctrl135.createPowerSource("Gerador", 400, 200);
        PowerSource result = ctrl135.getPowerSource();
        PowerSource expectedResult = new PowerSource("Gerador", 400, 200);
        assertEquals(expectedResult, result);
    }
}