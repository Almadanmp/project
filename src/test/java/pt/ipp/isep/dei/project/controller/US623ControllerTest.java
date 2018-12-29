package pt.ipp.isep.dei.project.controller;

import pt.ipp.isep.dei.project.model.*;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * User Story 623 - controller Tests
 */

public class US623ControllerTest {

    @Test
    public void seeIfGetsAverageRainfallOfGA() {
        GeographicArea gA1 = new GeographicArea("Portugal", new TypeArea("Pais"), new Local(21, 33));
        House house = new House("1742", "Costa Cabral", new Local(21, 33), "4450-145");
        house.setmMotherArea(gA1); //TODO Use Constructor instead of set (once constructors are fixed)
        HouseList hL1 = new HouseList();
        hL1.addHouseToHouseList(house);
        gA1.setHouseList(hL1);
        //Arrange
        ReadingList readingList = new ReadingList();
        Reading r1 = new Reading(15, new GregorianCalendar(2018, 11, 3).getTime());
        Reading r2 = new Reading(19, new GregorianCalendar(2018, 11, 4).getTime());
        Reading r3 = new Reading(17, new GregorianCalendar(2018, 11, 1).getTime());
        ReadingList readingList2 = new ReadingList();
        readingList.addReading(r1);
        readingList.addReading(r2);
        readingList.addReading(r3);
        Reading r4 = new Reading(20, new GregorianCalendar(2018, 11, 20).getTime());
        Reading r5 = new Reading(25, new GregorianCalendar(2018, 11, 2).getTime());
        Reading r6 = new Reading(45, new GregorianCalendar(2018, 11, 1).getTime());
        readingList2.addReading(r4);
        readingList2.addReading(r5);
        readingList2.addReading(r6);
        Sensor s1 = new Sensor("Sensor 1", new TypeSensor("Rain"), new Local(16, 17, 18), new GregorianCalendar(2010, 8, 9).getTime(), readingList);
        Sensor s2 = new Sensor("Sensor 2", new TypeSensor("Temperature"), new Local(16, 17, 18), new GregorianCalendar(2010, 8, 9).getTime(), readingList2);
        Sensor s3 = new Sensor("Sensor 3", new TypeSensor("Rain"), new Local(16, 17, 18), new GregorianCalendar(2010, 8, 9).getTime(), readingList2);
        SensorList sensorList = new SensorList();
        sensorList.addSensor(s2);
        sensorList.addSensor(s1);
        sensorList.addSensor(s3);
        gA1.setSensorList(sensorList);

        US623Controller US623 = new US623Controller();
        GregorianCalendar dateMin = new GregorianCalendar(2018, 11, 1);
        GregorianCalendar dateMax = new GregorianCalendar(2018, 11, 20);
        Date dateToTest1 = dateMin.getTime();
        Date dateToTest2 = dateMax.getTime();
        double expectedResult = 23.5;
        double actualResult = US623.getAVGDailyRainfallOnGivenPeriod(house, dateToTest1, dateToTest2);
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfPrintsGeoAList() {
        US623Controller US623 = new US623Controller();
        GeographicArea gA1 = new GeographicArea("Portugal", new TypeArea("Country"), new Local(21, 33));
        GeographicArea gA2 = new GeographicArea("Oporto", new TypeArea("City"), new Local(14, 14));
        GeographicArea gA3 = new GeographicArea("Lisbon", new TypeArea("Village"), new Local(3, 3));
        GeographicAreaList gAL1 = new GeographicAreaList();
        gAL1.addGeographicAreaToGeographicAreaList(gA1);
        gAL1.addGeographicAreaToGeographicAreaList(gA2);
        gAL1.addGeographicAreaToGeographicAreaList(gA3);
        String expectedResult = "---------------\n" +
                "0) Name: Portugal | Type: Country | Latitude: 21.0 | Longitude: 33.0\n" +
                "1) Name: Oporto | Type: City | Latitude: 14.0 | Longitude: 14.0\n" +
                "2) Name: Lisbon | Type: Village | Latitude: 3.0 | Longitude: 3.0\n" +
                "---------------\n";
        String result = US623.printGAList(gAL1);
        assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfPrintsGeoAListIfEmpty() {
        US623Controller US623 = new US623Controller();
        GeographicAreaList gAL1 = new GeographicAreaList();
        String expectedResult = "Invalid List - List is Empty\n";
        String result = US623.printGAList(gAL1);
        assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfPrintsHouseList(){
        US623Controller US623 = new US623Controller();
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
        String result = US623.printHouseList(gA1);
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfPrintsHouseListIfEmpty(){
        US623Controller US623 = new US623Controller();
        HouseList hL1 = new HouseList();
        GeographicArea gA1 = new GeographicArea();
        gA1.setHouseList(hL1);
        String expectedResult = "Invalid List - List is Empty\n";
        String result = US623.printHouseList(gA1);
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void seeCreateDate() {
        US623Controller US623 = new US623Controller();
        int year = 2018;
        int month = 1;
        int day = 13;
        Date expectedResult = new GregorianCalendar(year, month, day).getTime();
        Date result = US623.createDate(year, month, day);
        assertEquals(expectedResult, result);
    }
}
