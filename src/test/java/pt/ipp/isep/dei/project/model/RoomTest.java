package pt.ipp.isep.dei.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testng.Assert;
import pt.ipp.isep.dei.project.TestUtils;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.DeviceList;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Room tests class.
 */

class RoomTest {

    @Test
    void seeIfRemoveDeviceFromRoomWorks() {
        Device device = new Device("waterheater", 150, TestUtils.PATH_TO_WATERHEATER);
        device.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 12D);
        device.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 40D);
        device.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 234D);
        Device device1 = new Device("skjsjk", 123, TestUtils.PATH_TO_WATERHEATER);
        device1.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 12D);
        device1.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 40D);
        device1.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 234D);
        Room room = new Room("cozinha", 1, 1, 1, 1);
        room.addDevice(device);
        room.addDevice(device1);
        room.removeDevice(device1);
        String result = room.buildDeviceListString();
        String expectedResult = "---------------\n" +
                "\n" +
                "0) device Name: waterheater, device Type: WaterHeater, device Nominal Power: 150.0\n" +
                "---------------\n";
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetMaxTemperatureInARoomOnAGivenDayWorks() {
        SensorList list = new SensorList();
        TypeSensor tipo = new TypeSensor("temperature", "Celsius");
        ReadingList listR = new ReadingList();
        Date d2 = new GregorianCalendar(2018, 2, 2).getTime();
        Reading r1;
        Reading r2;
        r1 = new Reading(30, d2);
        r2 = new Reading(20, d2);
        listR.addReading(r1);
        listR.addReading(r2);
        Sensor s1 = new Sensor("sensor1", tipo, new Local(1, 1, 50), new Date());
        s1.setReadingList(listR);
        list.addSensor(s1);
        RoomList roomList1 = new RoomList();
        Room room1 = new Room("room1", 19, 23456789, 5, 3);
        roomList1.addRoom(room1);
        room1.setRoomSensorList(list);
        ReadingList rL1 = new ReadingList();
        r1 = new Reading(30, d2);
        rL1.addReading(r1);
        Room room = new Room("quarto", 1, 80, 5, 3);
        room.setRoomSensorList(list);
        House house = new House("casa", "ss", "ss", "sss", new Local(1, 1, 50), new GeographicArea("porto", new TypeArea("sksks"), 1, 1, new Local(1, 1, 50)),60,180);
        house.addRoomToRoomList(room);
        double result = room.getMaxTemperatureInARoomOnAGivenDay(house, d2);
        double expectedResult = 30.0;
        assertEquals(expectedResult, result, 0.01);
    }

    @Test
    void seeIfgetMaxTemperatureInARoomOnAGivenDayWorksNegatives() {
        SensorList list = new SensorList();
        TypeSensor tipo = new TypeSensor("temperature", "Celsius");
        ReadingList listR = new ReadingList();
        Date d2 = new GregorianCalendar(2018, 2, 2).getTime();
        Reading r1;
        Reading r2;
        r1 = new Reading(-30, d2);
        r2 = new Reading(20, d2);
        listR.addReading(r1);
        listR.addReading(r2);
        Sensor s1 = new Sensor("sensor1", tipo, new Local(1, 1, 50), new Date());
        s1.setReadingList(listR);
        list.addSensor(s1);
        Room room = new Room("quarto", 1, 80, 5, 3);
        room.setRoomSensorList(list);
        House house = new House("casa", "ss", "ss", "sss", new Local(1, 1, 50), new GeographicArea("porto", new TypeArea("sksks"), 1, 1, new Local(1, 1, 50)),60,180);
        house.addRoomToRoomList(room);
        double result = room.getMaxTemperatureInARoomOnAGivenDay(house, d2);
        double expectedResult = 20.0;
        assertEquals(expectedResult, result, 0.01);
    }

    @Test
    void seeIfGetMaxTemperatureInARoomOnAGivenDayWorksWithTwoDates() {
        SensorList list = new SensorList();
        TypeSensor tipo = new TypeSensor("temperature", "Celsius");
        ReadingList listR = new ReadingList();
        Date d2 = new GregorianCalendar(2018, 2, 2).getTime();
        Date d3 = new GregorianCalendar(2018, 2, 3).getTime();
        Reading r1 = new Reading(-30, d2);
        Reading r2 = new Reading(20, d2);
        Reading r3 = new Reading(25, d3);
        listR.addReading(r1);
        listR.addReading(r2);
        listR.addReading(r3);
        Sensor s1 = new Sensor("sensor1", tipo, new Local(1, 1, 50), new Date());
        s1.setReadingList(listR);
        list.addSensor(s1);
        Room room = new Room("quarto", 1, 80, 5, 3);
        room.setRoomSensorList(list);
        House house = new House("casa", "ss", "ss", "sss", new Local(1, 1, 50), new GeographicArea("porto", new TypeArea("sksks"), 1, 1, new Local(1, 1, 50)),60,180);
        house.addRoomToRoomList(room);
        double result = room.getMaxTemperatureInARoomOnAGivenDay(house, d3);
        double expectedResult = 25.0;
        assertEquals(expectedResult, result, 0.01);
    }

    @Test
    void seeIfGetMaxTemperatureInARoomOnAGivenDayWorksWithTwoDatesAndNeg() {
        SensorList list = new SensorList();
        TypeSensor tipo = new TypeSensor("temperature", "Celsius");
        ReadingList listR = new ReadingList();
        Date d2 = new GregorianCalendar(2018, 2, 2).getTime();
        Date d3 = new GregorianCalendar(2018, 2, 3).getTime();
        Reading r1 = new Reading(-30, d2);
        Reading r2 = new Reading(-20, d2);
        Reading r3 = new Reading(-25, d3);
        listR.addReading(r1);
        listR.addReading(r2);
        listR.addReading(r3);
        Sensor s1 = new Sensor("sensor1", tipo, new Local(1, 1, 50), new Date());
        s1.setReadingList(listR);
        list.addSensor(s1);
        Room room = new Room("quarto", 1, 80, 5, 3);
        room.setRoomSensorList(list);
        House house = new House("casa", "ss", "ss", "sss", new Local(1, 1, 50), new GeographicArea("porto", new TypeArea("sksks"), 1, 1, new Local(1, 1, 50)),60,180);
        house.addRoomToRoomList(room);
        double result = room.getMaxTemperatureInARoomOnAGivenDay(house, d3);
        double expectedResult = -25.0;
        assertEquals(expectedResult, result, 0.01);
    }

    @Test
    void SeeIfPrintListOfDevicesFromRoomWorks() {
        DeviceList deviceList = new DeviceList();
        Device d1 = new Device("frigorifico", 230, TestUtils.PATH_TO_FRIDGE);
        d1.setAttributeValue(TestUtils.F_FREEZER_CAPACITY, 1D);
        d1.setAttributeValue(TestUtils.F_REFRIGERATOR_CAPACITY, 1D);
        d1.setAttributeValue(TestUtils.F_ANNUAL_CONSUMPTION, 34D);
        deviceList.addDevice(d1);
        Room room = new Room("cozinha", 0, 1, 1, 1);
        room.setDeviceList(deviceList);
        String result = room.buildDeviceListString();
        String expectedResult = "---------------\n" +
                "\n" +
                "0) device Name: frigorifico, device Type: Fridge, device Nominal Power: 230.0\n" +
                "---------------\n";
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetCurrentRoomTemperatureWorks() {
        SensorList list = new SensorList();
        TypeSensor type = new TypeSensor("temperature", "Celsius");
        ReadingList listR = new ReadingList();
        Date d1 = new GregorianCalendar(2018, 2, 1, 16, 30).getTime();
        Date d2 = new GregorianCalendar(2018, 2, 1, 20, 30).getTime();
        Date d3 = new GregorianCalendar(2018, 1, 1, 20, 30).getTime();
        Date d4 = new GregorianCalendar(2017, 12, 1, 20, 30).getTime();
        Reading r1;
        Reading r2;
        Reading r3;
        Reading r4;
        r1 = new Reading(30, d1);
        r2 = new Reading(20, d2);
        r3 = new Reading(25, d3);
        r4 = new Reading(10, d4);
        listR.addReading(r1);
        listR.addReading(r2);
        listR.addReading(r3);
        listR.addReading(r4);
        Sensor s1 = new Sensor("sensor1", type, new Local(1, 1, 50), new Date());
        s1.setReadingList(listR);
        list.addSensor(s1);
        Room room = new Room("quarto", 1, 80, 5, 3);
        room.setRoomSensorList(list);
        double result = room.getCurrentRoomTemperature();
        double expectedResult = 20.0;
        assertEquals(expectedResult, result, 0.01);
    }

    @Test
    void seeIfGetCurrentRoomTemperatureWorksNegative() {
        SensorList list = new SensorList();
        TypeSensor type = new TypeSensor("temperature", "Celsius");
        ReadingList listR = new ReadingList();
        Date d1 = new GregorianCalendar(2018, 2, 1, 16, 30).getTime();
        Date d2 = new GregorianCalendar(2018, 2, 1, 20, 30).getTime();
        Date d3 = new GregorianCalendar(2018, 1, 1, 20, 30).getTime();
        Date d4 = new GregorianCalendar(2017, 12, 1, 20, 30).getTime();
        Reading r1;
        Reading r2;
        Reading r3;
        Reading r4;
        r1 = new Reading(30, d1);
        r2 = new Reading(-20, d2);
        r3 = new Reading(25, d3);
        r4 = new Reading(10, d4);
        listR.addReading(r1);
        listR.addReading(r2);
        listR.addReading(r3);
        listR.addReading(r4);
        Sensor s1 = new Sensor("sensor1", type, new Local(1, 1, 50), new Date());
        s1.setReadingList(listR);
        list.addSensor(s1);
        Room room = new Room("quarto", 1, 80, 5, 3);
        room.setRoomSensorList(list);
        double result = room.getCurrentRoomTemperature();
        double expectedResult = -20.0;
        assertEquals(expectedResult, result, 0.01);
    }

    @Test
    void seeIfGetCurrentRoomTemperatureWorksMinute() {
        SensorList list = new SensorList();
        TypeSensor type = new TypeSensor("temperature", "Celsius");
        ReadingList listR = new ReadingList();
        Date d1 = new GregorianCalendar(2018, 2, 1, 20, 31).getTime();
        Date d2 = new GregorianCalendar(2018, 2, 1, 20, 30).getTime();
        Date d3;
        d3 = new GregorianCalendar(2018, 1, 1, 20, 30).getTime();
        Date d4 = new GregorianCalendar(2017, 12, 1, 20, 30).getTime();
        Reading r1;
        Reading r2;
        Reading r3;
        Reading r4;
        r1 = new Reading(30, d1);
        r2 = new Reading(-20, d2);
        r3 = new Reading(25, d3);
        r4 = new Reading(10, d4);
        listR.addReading(r1);
        listR.addReading(r2);
        listR.addReading(r3);
        listR.addReading(r4);
        Sensor s1 = new Sensor("sensor1", type, new Local(1, 1, 50), new Date());
        s1.setReadingList(listR);
        list.addSensor(s1);
        Room room = new Room("quarto", 1, 80, 5, 3);
        room.setRoomSensorList(list);
        double result = room.getCurrentRoomTemperature();
        double expectedResult = 30.0;
        assertEquals(expectedResult, result, 0.01);
    }

    @Test
    void seeIfGetCurrentRoomTemperatureWorksMinute2() {
        SensorList list = new SensorList();
        Room room = new Room("quarto", 1, 80, 5, 3);
        room.setRoomSensorList(list);
        double result = room.getCurrentRoomTemperature();
        double expectedResult = 0.0;
        assertEquals(expectedResult, result, 0.01);
    }

    @Test
    void seeIfDoesSensorListInARoomContainASensorByNameWorks() {
        SensorList list = new SensorList();
        TypeSensor type = new TypeSensor("temperature", "Celsius");
        ReadingList listR = new ReadingList();
        Sensor s1 = new Sensor("sensor1", type, new Local(1, 1, 50), new Date());
        s1.setReadingList(listR);
        list.addSensor(s1);
        Room room = new Room("quarto", 1, 80, 5, 3);
        room.setRoomSensorList(list);
        boolean result = room.doesSensorListInARoomContainASensorByName("sensor1");
        assertTrue(result);
    }

    @Test
    void seeIfDoesSensorListInARoomContainASensorByNameWorksFalse() {
        SensorList list = new SensorList();
        TypeSensor type = new TypeSensor("temperature", "Celsius");
        ReadingList listR = new ReadingList();
        Sensor s1 = new Sensor("sensor1", type, new Local(1, 1, 50), new Date());
        s1.setReadingList(listR);
        list.addSensor(s1);
        Room room = new Room("quarto", 1, 8, 5, 2);
        room.setRoomSensorList(list);
        boolean result = room.doesSensorListInARoomContainASensorByName("sensor89");
        assertEquals(false, result);
    }

    @Test
    void seeIfAddSensorWorks() {
        SensorList list = new SensorList();
        TypeSensor type = new TypeSensor("temperature", "Celsius");
        ReadingList listR = new ReadingList();
        Sensor s1 = new Sensor("sensor1", type, new Local(1, 1, 50), new Date());
        s1.setReadingList(listR);
        list.addSensor(s1);
        Room room = new Room("quarto", 1, 8, 5, 3);
        room.setRoomSensorList(list);
        Sensor s2 = new Sensor("sensor2", type, new Local(1, 1, 50), new Date());
        s2.setReadingList(listR);
        boolean result = room.addSensor(s2);
        assertTrue(result);
    }

    @Test
    void seeIfAddSensorWorksFalse() {
        SensorList list = new SensorList();
        TypeSensor type = new TypeSensor("temperature", "Celsius");
        ReadingList listR = new ReadingList();
        Sensor s1 = new Sensor("sensor1", type, new Local(1, 1, 50), new Date());
        s1.setReadingList(listR);
        list.addSensor(s1);
        Room room = new Room("quarto", 1, 80, 5, 3);
        room.setRoomSensorList(list);
        boolean result = room.addSensor(s1);
        assertEquals(false, result);
    }

    @Test
    void seeIfEqualsWork() {
        SensorList list = new SensorList();
        TypeSensor type = new TypeSensor("temperature", "Celsius");
        ReadingList listR = new ReadingList();
        Sensor s1 = new Sensor("sensor1", type, new Local(1, 1, 50), new Date());
        s1.setReadingList(listR);
        list.addSensor(s1);
        Room room = new Room("quarto", 1, 80, 5, 3);
        boolean result = room.equals(null);
        assertEquals(false, result);
    }

    @Test
    void seeIfEqualsWorkDifClass() {
        TypeSensor type = new TypeSensor("temperature", "Celsius");
        Room room = new Room("quarto", 1, 80, 5, 3);
        boolean result = room.equals(type);
        assertEquals(false, result);
    }

    @Test
    void hashCodeDummyTest() {
        Room room1 = new Room("room1", 19, 5, 3, 3);
        int expectedResult = 1;
        int actualResult = room1.hashCode();
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfPrintRoomWorks() {
        Room room = new Room("room1", 1, 1, 2, 2);
        String result = room.buildRoomString();
        assertEquals("room1, 1, 1.0, 2.0, 2.0.\n", result);
    }


    @Test
    void seeNominalPowerOfRoomWithoutDevices() {
        //ARRANGE
        Room room1 = new Room("room1", 19, 5, 3, 3);
        double expectedResult = 0;
        //ACT
        double actualResult = room1.getNominalPower();
        //ASSERT
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeNominalPowerOfRoom() {
        //ARRANGE
        Device d1 = new Device("d1", 12, TestUtils.PATH_TO_FRIDGE);
        d1.setAttributeValue(TestUtils.F_FREEZER_CAPACITY, 2D);
        d1.setAttributeValue(TestUtils.F_REFRIGERATOR_CAPACITY, 2D);
        d1.setAttributeValue(TestUtils.F_ANNUAL_CONSUMPTION, 45D);
        Device d2 = new Device("d2", 10, TestUtils.PATH_TO_FRIDGE);
        d2.setAttributeValue(TestUtils.F_FREEZER_CAPACITY, 2D);
        d2.setAttributeValue(TestUtils.F_REFRIGERATOR_CAPACITY, 2D);
        d2.setAttributeValue(TestUtils.F_ANNUAL_CONSUMPTION, 45D);
        Device d3 = new Device("d3", 1, TestUtils.PATH_TO_FRIDGE);
        d3.setAttributeValue(TestUtils.F_FREEZER_CAPACITY, 2D);
        d3.setAttributeValue(TestUtils.F_REFRIGERATOR_CAPACITY, 2D);
        d3.setAttributeValue(TestUtils.F_ANNUAL_CONSUMPTION, 45D);
        DeviceList deviceList = new DeviceList();
        deviceList.addDevice(d1);
        deviceList.addDevice(d2);
        deviceList.addDevice(d3);
        Room room1 = new Room("room1", 19, 5, 3, 3);
        room1.setDeviceList(deviceList);
        double expectedResult = 23;
        //ACT
        double actualResult = room1.getNominalPower();
        //ASSERT
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void getDailyRoomConsumptionPerTypeTest() {
        Room r1 = new Room("quarto", 1, 12, 12, 12);
        Device d1 = new Device("fridgeOne", 12, TestUtils.PATH_TO_FRIDGE);
        d1.setAttributeValue(TestUtils.F_FREEZER_CAPACITY, 2D);
        d1.setAttributeValue(TestUtils.F_REFRIGERATOR_CAPACITY, 2D);
        d1.setAttributeValue(TestUtils.F_ANNUAL_CONSUMPTION, 45D);
        Device d2 = new Device("wHeater1", 12, TestUtils.PATH_TO_WATERHEATER);
        d2.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 200D);
        d2.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 30D);
        d2.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 0.9D);
        Device d3 = new Device("wHeater2", 11, TestUtils.PATH_TO_WATERHEATER);
        d3.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 500D);
        d3.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 20D);
        d3.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 0.9D);
        r1.addDevice(d1);
        r1.addDevice(d2);
        r1.addDevice(d3);
        Double expectedResult = 0.0;
        Double result = r1.getDailyConsumptionByDeviceType("WaterHeater");
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfRemoveRoomDevicesFromDeviceListAssertTrue() {
        Room r1 = new Room("quarto", 1, 12, 12, 12);
        Device d2 = new Device("wHeater1", 12, TestUtils.PATH_TO_WATERHEATER);
        d2.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 200D);
        d2.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 30D);
        d2.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 0.9D);
        Device d3 = new Device("wHeater2", 11, TestUtils.PATH_TO_WATERHEATER);
        d3.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 500D);
        d3.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 20D);
        d3.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 0.9D);
        r1.addDevice(d2);
        r1.addDevice(d3);
        d2.setAttributeValue("coldWaterTemperature", 5.0);
        d2.setAttributeValue("volumeOfWaterToHeat", 100.0);
        d3.setAttributeValue("volumeOfWaterToHeat", 100.0);
        d3.setAttributeValue("coldWaterTemperature", 1.0);

        assertTrue(r1.removeDevice(d2));
    }

    @Test
    void seeIfRemoveRoomDevicesFromDeviceListAssertTrueList() {
        Room r1 = new Room("quarto", 1, 12, 12, 12);
        Device d2 = new Device("wHeater1", 12, TestUtils.PATH_TO_WATERHEATER);
        d2.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 200D);
        d2.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 30D);
        d2.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 0.9D);
        Device d3 = new Device("wHeater2", 11, TestUtils.PATH_TO_WATERHEATER);
        d3.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 500D);
        d3.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 20D);
        d3.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 0.9D);
        DeviceList dlist = new DeviceList();
        dlist.addDevice(d2);
        dlist.addDevice(d3);
        r1.setDeviceList(dlist);
        d2.setAttributeValue("coldWaterTemperature", 5.0);
        d2.setAttributeValue("volumeOfWaterToHeat", 100.0);
        d3.setAttributeValue("volumeOfWaterToHeat", 100.0);
        d3.setAttributeValue("coldWaterTemperature", 1.0);

        assertTrue(r1.removeDevice(d2));
    }

    @Test
    void seeIfRemoveRoomDevicesFromDeviceListAssertFalse() {
        Room r1 = new Room("quarto", 1, 12, 12, 12);
        Device d2 = new Device("wHeater1", 12, TestUtils.PATH_TO_WATERHEATER);
        d2.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 200D);
        d2.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 30D);
        d2.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 0.9D);
        Device d1 = new Device("wHeater4", 12, TestUtils.PATH_TO_WATERHEATER);
        d1.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 200D);
        d1.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 30D);
        d1.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 0.9D);
        Device d3 = new Device("wHeater2", 11, TestUtils.PATH_TO_WATERHEATER);
        d3.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 500D);
        d3.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 20D);
        d3.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 0.9D);
        r1.addDevice(d2);
        r1.addDevice(d3);
        d2.setAttributeValue("coldWaterTemperature", 5.0);
        d2.setAttributeValue("volumeOfWaterToHeat", 100.0);
        d3.setAttributeValue("volumeOfWaterToHeat", 100.0);
        d3.setAttributeValue("coldWaterTemperature", 1.0);

        assertFalse(r1.removeDevice(d1));
    }

    @Test
    void getDailyRoomConsumptionPerTypeTest2() {
        Room r1 = new Room("quarto", 1, 12, 12, 12);
        Device d2 = new Device("wHeater1", 12, TestUtils.PATH_TO_WATERHEATER);
        d2.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 200D);
        d2.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 30D);
        d2.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 0.9D);
        Device d3 = new Device("wHeater2", 11, TestUtils.PATH_TO_WATERHEATER);
        d3.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 500D);
        d3.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 20D);
        d3.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 0.9D);
        r1.addDevice(d2);
        r1.addDevice(d3);
        d2.setAttributeValue("Cold Water Temperature", 5.0);
        d2.setAttributeValue("Volume Of Water To Heat", 100.0);
        d3.setAttributeValue("Volume Of Water To Heat", 100.0);
        d3.setAttributeValue("Cold Water Temperature", 1.0);
        double expectedResult = 4.60548;
        double result = r1.getDailyConsumptionByDeviceType(d2.getType());
        assertEquals(expectedResult, result);
    }

    @Test
    void addDeviceFails() {
        Room r1 = new Room("quarto", 1, 12, 12, 12);
        Device d2 = new Device("wHeater1", 12, TestUtils.PATH_TO_WATERHEATER);
        d2.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 200D);
        d2.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 30D);
        d2.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 10D);
        Device d3 = new Device("wHeater1", 11, TestUtils.PATH_TO_WATERHEATER);
        d3.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 500D);
        d3.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 30D);
        d3.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 1D);
        r1.addDevice(d2);
        boolean expectedResult = false;
        boolean result = r1.addDevice(d3);
        assertEquals(expectedResult, result);
    }

    @Test
    void addDeviceSucceeds() {
        Room r1 = new Room("quarto", 1, 12, 12, 12);
        Device d2 = new Device("wHeater1", 12, TestUtils.PATH_TO_WATERHEATER);
        d2.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 200D);
        d2.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 20D);
        d2.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 10D);
        Device d3 = new Device("wHeater2", 11, TestUtils.PATH_TO_WATERHEATER);
        d3.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 500D);
        d3.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 30D);
        d3.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 1D);
        r1.addDevice(d2);
        boolean expectedResult = true;
        boolean result = r1.addDevice(d3);
        assertEquals(expectedResult, result);
    }

    @Test
    void addDeviceSucceeds2() {
        Room r1 = new Room("quarto", 1, 12, 12, 12);
        Device d2 = new Device("wHeater1", 12, TestUtils.PATH_TO_WATERHEATER);
        d2.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 200D);
        d2.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 20D);
        d2.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 10D);
        Device d3 = new Device("wHeater2", 11, TestUtils.PATH_TO_WATERHEATER);
        d3.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 500D);
        d3.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 30D);
        d3.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 1D);
        r1.addDevice(d2);
        r1.addDevice(d3);
        boolean result = r1.addDevice(d3);
        assertFalse(result);
    }

    @Test
    void addDeviceSucceeds3() {
        Room r1 = new Room("quarto", 1, 12, 12, 12);
        Device d3 = new Device("wHeater2", 11, TestUtils.PATH_TO_WATERHEATER);
        d3.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 500D);
        d3.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 30D);
        d3.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 1D);
        boolean expectedResult = true;
        boolean result = r1.addDevice(d3);
        assertEquals(expectedResult, result);
    }

    @Test
    void getRoomDevicesOfGivenTypeSuccess() {
        Room r1 = new Room("quarto", 1, 12, 12, 12);
        Device d2 = new Device("wHeater1", 12, TestUtils.PATH_TO_WATERHEATER);
        d2.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 200D);
        d2.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 20D);
        d2.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 10D);
        Device d3 = new Device("wHeater2", 11, TestUtils.PATH_TO_WATERHEATER);
        d3.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 500D);
        d3.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 30D);
        d3.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 1D);
        r1.addDevice(d2);
        r1.addDevice(d3);
        List<Device> expectedResult = new ArrayList<>();
        expectedResult.add(d2);
        expectedResult.add(d3);
        List<Device> result = r1.getDevicesOfGivenType("WaterHeater");
        assertEquals(expectedResult, result);
    }

    @Test
    void getRoomDevicesOfGivenTypeFails() {
        Room r1 = new Room("quarto", 1, 12, 12, 12);
        Device d2 = new Device("wHeater1", 12, TestUtils.PATH_TO_WATERHEATER);
        d2.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 200D);
        d2.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 20D);
        d2.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 10D);
        Device d3 = new Device("wHeater2", 11, TestUtils.PATH_TO_WATERHEATER);
        d3.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 500D);
        d3.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 30D);
        d3.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 1D);
        r1.addDevice(d2);
        r1.addDevice(d3);
        List<Device> expectedResult = new ArrayList<>();
        List<Device> result = r1.getDevicesOfGivenType("Fridge");
        assertEquals(expectedResult, result);
    }

    @Test
    void removeDeviceSucess() {
        Room r1 = new Room("quarto", 1, 12, 12, 12);
        Device d2 = new Device("wHeater1", 12, TestUtils.PATH_TO_WATERHEATER);
        d2.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 200D);
        d2.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 20D);
        d2.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 10D);
        Device d3 = new Device("wHeater2", 11, TestUtils.PATH_TO_WATERHEATER);
        d3.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 500D);
        d3.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 30D);
        d3.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 1D);
        r1.addDevice(d2);
        r1.addDevice(d3);
        boolean result = r1.removeDevice(d2);
        assertEquals(true, result);
    }

    @Test
    void removeDeviceFails() {
        Room r1 = new Room("quarto", 1, 12, 12, 12);
        Device d2 = new Device("wHeater1", 12, TestUtils.PATH_TO_WATERHEATER);
        d2.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 200D);
        d2.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 20D);
        d2.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 10D);
        Device d3 = new Device("wHeater2", 11, TestUtils.PATH_TO_WATERHEATER);
        d3.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 500D);
        d3.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 30D);
        d3.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 1D);
        Device d4 = new Device("wHeater4", 11, TestUtils.PATH_TO_WATERHEATER);
        d4.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 50D);
        d4.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 3D);
        d4.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 1D);
        r1.addDevice(d2);
        r1.addDevice(d3);
        boolean result = r1.removeDevice(d4);
        assertEquals(false, result);

    }

    @Test
    void seeIfAddRoomDevicesToDeviceListWorksWhenDeviceListAlreadyAddedToRoom() {
        //Arrange
        Device device1 = new Device("waterheater", 150, TestUtils.PATH_TO_WATERHEATER);
        device1.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 12D);
        device1.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 40D);
        device1.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 234D);
        Device device2 = new Device("skjsjk", 123, TestUtils.PATH_TO_WATERHEATER);
        device2.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 12D);
        device2.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 40D);
        device2.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 234D);
        Room room = new Room("cozinha", 1, 1, 1, 1);
        DeviceList dList = new DeviceList();
        dList.addDevice(device1);
        dList.addDevice(device2);
        room.setDeviceList(dList);
        //Act
        boolean actualResult = room.addRoomDevicesToDeviceList(dList);
        boolean expectedResult = false;
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfAddRoomDevicesToDeviceListWorksAlreadyContained() {
        //Arrange
        Device device1 = new Device("waterheater", 150, TestUtils.PATH_TO_WATERHEATER);
        device1.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 12D);
        device1.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 40D);
        device1.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 234D);
        Device device2 = new Device("skjsjk", 123, TestUtils.PATH_TO_WATERHEATER);
        device2.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 12D);
        device2.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 40D);
        device2.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 234D);
        Room room = new Room("cozinha", 1, 1, 1, 1);
        DeviceList dList = new DeviceList();
        dList.addDevice(device1);
        dList.addDevice(device2);
        DeviceList deviceList2 = new DeviceList();
        deviceList2.addDevice(device1);
        deviceList2.addDevice(device2);
        room.setDeviceList(dList);
        //Act
        boolean actualResult = room.addRoomDevicesToDeviceList(deviceList2);
        boolean expectedResult = false;
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfAddRoomDevicesToDeviceListWorksWhenNotYetAddedToRoom() {
        //Arrange
        Device device1 = new Device("waterheater", 150, TestUtils.PATH_TO_WATERHEATER);
        device1.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 12D);
        device1.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 40D);
        device1.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 234D);
        Device device2 = new Device("skjsjk", 123, TestUtils.PATH_TO_WATERHEATER);
        device2.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 12D);
        device2.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 40D);
        device2.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 234D);
        Room room = new Room("cozinha", 1, 1, 1, 1);
        DeviceList dList = new DeviceList();
        dList.addDevice(device1);
        dList.addDevice(device2);
        //Act
        boolean actualResult = room.addRoomDevicesToDeviceList(dList);
        boolean expectedResult = false;
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetAllHouseDevices() {
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 4), new GeographicArea("porto", new TypeArea("cidade"), 2, 3, new Local(4, 4, 100)),60,180);
        Room r1 = new Room("quarto", 1, 12, 12, 12);
        Device d2 = new Device("wHeater1", 12, TestUtils.PATH_TO_WATERHEATER);
        d2.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 200D);
        d2.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 20D);
        d2.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 10D);
        Device d3 = new Device("wHeater2", 11, TestUtils.PATH_TO_WATERHEATER);
        d3.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 500D);
        d3.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 30D);
        d3.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 1D);
        r1.addDevice(d2);
        r1.addDevice(d3);
        house.addRoomToRoomList(r1);
        List<Device> expectedResult = new ArrayList<>();
        expectedResult.add(d2);
        expectedResult.add(d3);
        List<Device> result = house.getAllHouseDevices();
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    void ensureThatWeDoNotAddADeviceToADeviceList() {
        DeviceList deviceList = new DeviceList();
        Device device = new Device("WHeater", 23, TestUtils.PATH_TO_WATERHEATER);
        device.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 12D);
        device.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 40D);
        device.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 234D);
        Room room = new Room("Room", 1, 2, 3, 4);
        deviceList.addDevice(device);
        room.setDeviceList(deviceList);
        boolean expectedResult = false;
        boolean actualResult = room.addRoomDevicesToDeviceList(deviceList);
        assertEquals(expectedResult, actualResult);
    }


    @Test
    void seeThatWeAddADeviceToADeviceList() {
        DeviceList deviceList = new DeviceList();
        Device device = new Device("WHeater", 23, TestUtils.PATH_TO_WATERHEATER);
        device.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 12D);
        device.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 40D);
        device.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 234D);
        DeviceList deviceList2 = new DeviceList();
        deviceList.addDevice(device);
        Room room = new Room("Room", 1, 2, 3, 4);
        room.setDeviceList(deviceList);
        boolean expectedResult = true;
        boolean actualResult = room.addRoomDevicesToDeviceList(deviceList2);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void ensureThatWeDontAddADeviceToADeviceList() {
        DeviceList deviceList = new DeviceList();
        Device device = new Device("WHeater", 23, TestUtils.PATH_TO_WATERHEATER);
        device.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 12D);
        device.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 40D);
        device.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 234D);
        Room room = new Room("Room", 1, 2, 3, 4);
        deviceList.addDevice(device);
        boolean expectedResult = false;
        boolean actualResult = room.addRoomDevicesToDeviceList(deviceList);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void ensureThatWeAddADeviceToADeviceList() {
        DeviceList deviceList = new DeviceList();
        Device device = new Device("WHeater", 23, TestUtils.PATH_TO_WATERHEATER);
        device.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 12D);
        device.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 40D);
        device.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 234D);
        Room room = new Room("Room", 1, 2, 3, 4);
        deviceList.addDevice(device);
        room.setDeviceList(deviceList);
        DeviceList deviceList1 = new DeviceList();
        Device device1 = new Device("WHeater2", 23, TestUtils.PATH_TO_WATERHEATER);
        device1.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 12D);
        device1.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 40D);
        device1.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 234D);
        deviceList1.addDevice(device1);
        boolean expectedResult = true;
        boolean actualResult = room.addRoomDevicesToDeviceList(deviceList1);
        assertEquals(expectedResult, actualResult);
    }
}