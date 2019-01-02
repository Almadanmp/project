package pt.ipp.isep.dei.project.controller;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

class US130ControllerTest {


    @Test
    void seeIfHouExistsInListWorks() {
        HouseList list = new HouseList();
        House house = new House("Casa Oliveira", "Santa Maria de Lamas", new Local(42, 50), "4535");
        list.addHouseToHouseList(house);
        US130Controller ctrl130 = new US130Controller(list);
        String houseName = "Casa Oliveira";
        boolean result = ctrl130.seeIfHouseExistsInHouseList(houseName);
        assertTrue(result);
       }
}