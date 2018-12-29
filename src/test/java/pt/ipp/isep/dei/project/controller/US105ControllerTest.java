package pt.ipp.isep.dei.project.controller;

import pt.ipp.isep.dei.project.model.*;
import org.junit.jupiter.api.Test;

import static org.testng.Assert.assertEquals;


public class US105ControllerTest {

    @Test
    public void testCreateNewRoom() {
        //ARRANGE
        US105Controller us105Controller = new US105Controller();
        String roomDesignation = "cozinha";
        int roomFloor = 1;
        double roomDimensions = 52.3;
        Room expectedResult = new Room("cozinha", 1, 52.3);
        Room actualResult;
        //ACT
        us105Controller.createNewRoom(roomDesignation, roomFloor, roomDimensions);
        actualResult = us105Controller.getRoom();
        //ASSERT
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testGetRoomFromRoomList() {
        //ARRANGE
        US105Controller us105Controller = new US105Controller();
        String roomDesignation = "sala";
        int roomFloor = 5;
        double roomDimensions = 52.3;
        Room expectedResult = new Room("sala", 5, 23.7);
        Room actualResult;
        //ACT
        us105Controller.createNewRoom(roomDesignation, roomFloor, roomDimensions);
        actualResult = us105Controller.getRoom();
        //ASSERT
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testCheckIfHouseExistsInListWithFirstHouseName() {
        //ARRANGE
        US105Controller us105Controller = new US105Controller();
        String houseDesignation = "casa";
        House house = new House("casa","Rua",new Local(1,2),"4100");
        House house1 = new House("casa1","Rua1",new Local(11,22),"4110");
        House house2 = new House("casa2","Rua1",new Local(11,22),"4110");
        HouseList houseList = new HouseList();
        houseList.addHouseToHouseList(house);
        houseList.addHouseToHouseList(house1);
        houseList.addHouseToHouseList(house2);

        boolean expectedResult = true;
        boolean actualResult;
        //ACT
        actualResult = us105Controller.checkIfHouseExistsInList(houseDesignation, houseList);
        //ASSERT
        assertEquals(expectedResult, actualResult);
    }
    @Test
    public void testCheckIfHouseExistsInListWithMiddleHouseName() {
        //ARRANGE
        US105Controller us105Controller = new US105Controller();
        String houseDesignation = "casa1";
        House house = new House("casa","Rua",new Local(1,2),"4100");
        House house1 = new House("casa1","Rua1",new Local(11,22),"4110");
        House house2 = new House("casa2","Rua1",new Local(11,22),"4110");
        HouseList houseList = new HouseList();
        houseList.addHouseToHouseList(house);
        houseList.addHouseToHouseList(house1);
        houseList.addHouseToHouseList(house2);

        boolean expectedResult = true;
        boolean actualResult;
        //ACT
        actualResult = us105Controller.checkIfHouseExistsInList(houseDesignation, houseList);
        //ASSERT
        assertEquals(expectedResult, actualResult);
    }
    @Test
    public void testCheckIfHouseExistsInListWithLastHouseName() {
        //ARRANGE
        US105Controller us105Controller = new US105Controller();
        String houseDesignation = "casa2";
        House house = new House("casa","Rua",new Local(1,2),"4100");
        House house1 = new House("casa1","Rua1",new Local(11,22),"4110");
        House house2 = new House("casa2","Rua2",new Local(111,22),"4210");
        HouseList houseList = new HouseList();
        houseList.addHouseToHouseList(house);
        houseList.addHouseToHouseList(house1);
        houseList.addHouseToHouseList(house2);

        boolean expectedResult = true;
        boolean actualResult;
        //ACT
        actualResult = us105Controller.checkIfHouseExistsInList(houseDesignation, houseList);
        //ASSERT
        assertEquals(expectedResult, actualResult);
    }
    @Test
    public void testCheckIfHouseNotInList() {
        //ARRANGE
        US105Controller us105Controller = new US105Controller();
        String houseDesignation = "casa5";
        House house = new House("casa","Rua",new Local(1,2),"4100");
        House house1 = new House("casa1","Rua1",new Local(11,22),"4110");
        House house2 = new House("casa2","Rua2",new Local(111,22),"4210");
        HouseList houseList = new HouseList();
        houseList.addHouseToHouseList(house);
        houseList.addHouseToHouseList(house1);
        houseList.addHouseToHouseList(house2);

        boolean expectedResult = false;
        boolean actualResult;
        //ACT
        actualResult = us105Controller.checkIfHouseExistsInList(houseDesignation, houseList);
        //ASSERT
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testAddRoomToFirstHouseInList() {
        //ARRANGE
        US105Controller us105Controller = new US105Controller();

        Room room = new Room("cozinha",2,23);
        RoomList roomList = new RoomList();
        roomList.addRoom(room);

        House house = new House("casa","Rua",new Local(1,2),"4100",roomList);
        House house1 = new House("casa1","Rua1",new Local(11,22),"4110",roomList);
        House house2 = new House("casa2","Rua2",new Local(111,22),"4210",roomList);
        HouseList houseList = new HouseList();
        houseList.addHouseToHouseList(house);
        houseList.addHouseToHouseList(house1);
        houseList.addHouseToHouseList(house2);

        Room roomToAdd = new Room("sala",5,30.1);
        String houseDesignationTest = "casa";

        boolean expectedResult = true;
        boolean actualResult;
        //ACT
        actualResult = us105Controller.addRoomToHouse(houseDesignationTest, houseList, roomToAdd);
        //ASSERT
        assertEquals(expectedResult, actualResult);
    }
    @Test
    public void testAddRoomToSecondHouseInList() {
        //ARRANGE
        US105Controller us105Controller = new US105Controller();

        Room room = new Room("cozinha",2,23);
        RoomList roomList = new RoomList();
        roomList.addRoom(room);

        House house = new House("casa","Rua",new Local(1,2),"4100",roomList);
        House house1 = new House("casa1","Rua1",new Local(11,22),"4110",roomList);
        House house2 = new House("casa2","Rua2",new Local(111,22),"4210",roomList);
        HouseList houseList = new HouseList();
        houseList.addHouseToHouseList(house);
        houseList.addHouseToHouseList(house1);
        houseList.addHouseToHouseList(house2);

        Room roomToAdd = new Room("sala",5,30.1);
        String houseDesignationTest = "casa1";

        boolean expectedResult = true;
        boolean actualResult;
        //ACT
        actualResult = us105Controller.addRoomToHouse(houseDesignationTest, houseList, roomToAdd);
        //ASSERT
        assertEquals(expectedResult, actualResult);
    }
    @Test
    public void testAddRoomToLastHouseInList() {
        //ARRANGE
        US105Controller us105Controller = new US105Controller();

        Room room = new Room("cozinha",2,23);
        RoomList roomList = new RoomList();
        roomList.addRoom(room);

        House house = new House("casa","Rua",new Local(1,2),"4100",roomList);
        House house1 = new House("casa1","Rua1",new Local(11,22),"4110",roomList);
        House house2 = new House("casa2","Rua2",new Local(111,22),"4210",roomList);
        HouseList houseList = new HouseList();
        houseList.addHouseToHouseList(house);
        houseList.addHouseToHouseList(house1);
        houseList.addHouseToHouseList(house2);

        Room roomToAdd = new Room("sala",5,30.1);
        String houseDesignationTest = "casa1";

        boolean expectedResult = true;
        boolean actualResult;
        //ACT
        actualResult = us105Controller.addRoomToHouse(houseDesignationTest, houseList, roomToAdd);
        //ASSERT
        assertEquals(expectedResult, actualResult);
    }
    @Test
    public void testAddRoomWithSameNameWithRoomThatAlreadyExistsInHouse() {
        //ARRANGE
        US105Controller us105Controller = new US105Controller();

        Room room = new Room("cozinha",2,23);
        RoomList roomList = new RoomList();
        roomList.addRoom(room);

        House house = new House("casa","Rua",new Local(1,2),"4100",roomList);
        House house1 = new House("casa1","Rua1",new Local(11,22),"4110",roomList);
        House house2 = new House("casa2","Rua2",new Local(111,22),"4210",roomList);
        HouseList houseList = new HouseList();
        houseList.addHouseToHouseList(house);
        houseList.addHouseToHouseList(house1);
        houseList.addHouseToHouseList(house2);

        Room roomToAdd = new Room("cozinha",1,12);
        String houseDesignationTest = "casa1";

        boolean expectedResult = false;
        boolean actualResult;
        //ACT
        actualResult = us105Controller.addRoomToHouse(houseDesignationTest, houseList, roomToAdd);
        //ASSERT
        assertEquals(expectedResult, actualResult);
    }
}