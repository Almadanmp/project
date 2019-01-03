package pt.ipp.isep.dei.project.controller;

import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.HouseList;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Local;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.testng.Assert.assertTrue;

public class US101ControllerTest {

//    @Test
//    public void seeIfCreatesHouseAndAddsItToList() {
//        String address = "rua da rua 345";
//        String zipCode = "4450";
//        String nome = "casa";
//        double latitude = 38;
//        double longitude = 7;
//        Local local = new Local(latitude, longitude);
//        US101Controller us3 = new US101Controller();
//        House house = new House(nome, address, local, zipCode);
//        House house2 = new House(nome, address, local, zipCode);
//        HouseList houseList = new HouseList(house);
//        houseList.addHouseToHouseList(house2);
//        int index = us3.printGeoGraphicAreaElementsByIndex();
//        boolean result = us3.setHouseAddress(address,);
//
//        assertTrue(result);
//        assertEquals(1, houseList.getHouseList().size());
//    }
//
//    @Test
//    public void seeIfFailsCreatingSecondEqualHouse() {
//        HouseList houseList = new HouseList();
//        String address = "rua da rua 345";
//        String zipCode = "4450";
//        String nome = "casa";
//        double latitude = 38;
//        double longitude = 7;
//        US101Controller us3 = new US101Controller();
//        boolean result1 = us3.addHouseToHouseList(houseList,nome,  address, zipCode, latitude, longitude);
//        boolean result2 = us3.addHouseToHouseList(houseList,nome,  address, zipCode, latitude, longitude);
//
//        assertTrue(result1); //safety check (already covered on previous test)
//        assertFalse(result2);
//        assertEquals(1, houseList.getHouseList().size());
//    }
//
//    @Test
//    public void seeIfCreatesTwoDifferentHouses() {
//        HouseList houseList = new HouseList();
//        String address1 = "rua da rua 345";
//        String zipCode = "4450";
//        String nome = "casa";
//        double latitude = 38;
//        double longitude = 7;
//        String address2 = "rua da outra rua 56";
//
//        US101Controller us3 = new US101Controller();
//        boolean result1 = us3.addHouseToHouseList(houseList,nome,  address1, zipCode, latitude, longitude);
//        boolean result2 = us3.addHouseToHouseList(houseList,nome, address2, zipCode, latitude, longitude);
//
//        assertTrue(result1); //safety check (already covered on previous test)
//        assertTrue(result2);
//        assertEquals(2, houseList.getHouseList().size());
//    }
//
//    @Test
//    public void seeIfFailsWithNullInputHouseList() {
//        String address1 = "rua da rua 345";
//        String zipCode = "4450";
//        String nome = "casa";
//        double latitude = 38;
//        double longitude = 7;
//
//        US101Controller us3 = new US101Controller();
//        boolean result = us3.addHouseToHouseList(null, nome, address1, zipCode, latitude, longitude);
//
//       assertFalse(result);
//    }

}
