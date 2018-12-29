package Project_Test.ModelTest;

import PT.IPP.ISEP.DEI.Project.Model.GeographicArea;
import PT.IPP.ISEP.DEI.Project.Model.House;
import PT.IPP.ISEP.DEI.Project.Model.HouseList;
import PT.IPP.ISEP.DEI.Project.Model.Local;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class HouseListTest {

    @Test
    public void seeIfConstructorHomeListWorks() {
        //Arrange
        String address = "rua da rua 345";
        String zipCode = "4450";
        double latitude = 38;
        double longitude = 7;
        Local local = new Local(latitude, longitude);
        House house = new House(address, local, zipCode);
        HouseList houseList = new HouseList(house);
        List<House> expectedResult = new ArrayList<>();
        List<House> actualResult;
        //Act
        expectedResult.add(house);
        actualResult = houseList.getHouseList();
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfAddsHouseToHouseListIfSameAsConstructor() {
        //Arrange
        String address = "rua da rua 345";
        String zipCode = "4450";
        double latitude = 38;
        double longitude = 7;
        Local local = new Local(latitude, longitude);
        House house1 = new House(address, local, zipCode);
        House house2 = new House(address, local, zipCode);
        HouseList houseList = new HouseList(house1);
        houseList.addHouseToHouseList(house2);
        boolean expectedResult = false;
        boolean actualResult;
        //Act
        actualResult = houseList.addHouseToHouseList(house2);
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfAddsHouseToHomeListIfDifferentFromConstructor() {
        //Arrange
        String address1 = "rua da rua 345";
        String address2 = "rua da rua 455";
        String zipCode = "4450";
        double latitude = 38;
        double longitude = 7;
        Local local = new Local(latitude, longitude);
        House house1 = new House(address1, local, zipCode);
        House house2 = new House(address2, local, zipCode);
        HouseList houseList = new HouseList(house1);
        boolean expectedResult = true;
        boolean actualResult;
        //Act
        actualResult = houseList.addHouseToHouseList(house2);
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfAnInvalidListIsAdded() {
        HouseList list = new HouseList();
        boolean expectedResult = false;
        boolean actualResult = list.checkIfHouseListIsValid();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfEqualsToSameObject() {
        //Arrange
        String address1 = "rua da rua 345";
        String address2 = "rua da rua 455";
        String zipCode = "4450";
        double latitude = 38;
        double longitude = 7;
        Local local = new Local(latitude, longitude);
        House house1 = new House(address1, local, zipCode);
        House house2 = new House(address2, local, zipCode);
        HouseList houseList1 = new HouseList(house1);
        houseList1.addHouseToHouseList(house2);
        HouseList houseList2 = new HouseList(house1);
        houseList2.addHouseToHouseList(house2);

        boolean expectedResult = true;
        boolean actualResult;
        //Act
        actualResult = houseList1.equals(houseList2);
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfEqualsToDifferentObject() {
        //Arrange
        String address1 = "rua da rua 345";
        String address2 = "rua da rua 455";
        String zipCode = "4450";
        double latitude = 38;
        double longitude = 7;
        Local local = new Local(latitude, longitude);
        House house1 = new House(address1, local, zipCode);
        House house2 = new House(address2, local, zipCode);
        House house3 = new House("rua da estrada", local, "3450");
        HouseList houseList1 = new HouseList(house1);
        houseList1.addHouseToHouseList(house2);
        HouseList houseList2 = new HouseList(house2);
        houseList2.addHouseToHouseList(house3);

        boolean expectedResult = false;
        boolean actualResult;
        //Act
        actualResult = houseList1.equals(houseList2);
        //Assert
        assertEquals(expectedResult, actualResult);
    }
    @Test
    public void seeIfFalseWhenObjectsAreFromDifferentClass() {
        //Arrange

        String address1 = "rua da rua 345";
        String address2 = "rua da rua 455";
        String zipCode = "4450";
        double latitude = 38;
        double longitude = 7;
        Local local = new Local(latitude, longitude);
        House house1 = new House(address1, local, zipCode);
        House house2 = new House(address2, local, zipCode);
        HouseList houseList1 = new HouseList(house1);
        houseList1.addHouseToHouseList(house2);

        boolean expectedResult = false;
        boolean actualResult;
        //Act
        actualResult = houseList1.equals(latitude);
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfPrintsHouseList(){
        House house1 = new House("vacationHouse","Flower Street", new Local (11,13), "4230-111");
        House house2 = new House ("workHouse","Torrinha", new Local (12,12), "4345-000");
        House house3 = new House ("dreamHouse","New York", new Local (122,122), "6666-000");
        HouseList hL1 = new HouseList(house1);
        hL1.addHouseToHouseList(house2);
        hL1.addHouseToHouseList(house3);
        GeographicArea gA1 = new GeographicArea();
        gA1.setHouseList(hL1);
        String expectedResult = "---------------\n" +
                "0) Designation: vacationHouse | Address: Flower Street | ZipCode: 4230-111\n" +
                "1) Designation: workHouse | Address: Torrinha | ZipCode: 4345-000\n" +
                "2) Designation: dreamHouse | Address: New York | ZipCode: 6666-000\n" +
                "---------------\n";
        String result = hL1.printHouseList(gA1);
        assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfPrintsHouseListIfEmpty(){
        HouseList hL1 = new HouseList();
       GeographicArea gA1 = new GeographicArea();
        gA1.setHouseList(hL1);
        String expectedResult = "Invalid List - List is Empty\n";
        String result = hL1.printHouseList(gA1);
        assertEquals(expectedResult, result);
    }
}
