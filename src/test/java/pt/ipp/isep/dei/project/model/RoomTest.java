package pt.ipp.isep.dei.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testng.Assert;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.DeviceList;
import pt.ipp.isep.dei.project.model.device.Fridge;
import pt.ipp.isep.dei.project.model.device.WaterHeater;
import pt.ipp.isep.dei.project.model.device.devicespecs.FridgeSpec;
import pt.ipp.isep.dei.project.model.device.devicespecs.WaterHeaterSpec;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static java.lang.Double.NaN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.testng.Assert.assertTrue;

/**
 * Room tests class.
 */

class RoomTest {

    @Test
    void seeIfRemoveDeviceFromRoomWorks() {
        Device device = new WaterHeater(new WaterHeaterSpec());
        device.setName("waterheater");
        device.setNominalPower(150.0);
        device.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 12D);
        device.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 40D);
        device.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 234D);
        Device device1 = new WaterHeater(new WaterHeaterSpec());
        device1.setName("sdgfsgsd");
        device1.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 12D);
        device1.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 40D);
        device1.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 234D);
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
    void SeeIfPrintListOfDevicesFromRoomWorks() {
        DeviceList deviceList = new DeviceList();
        Device d1 = new Fridge(new FridgeSpec());
        d1.setName("frigorifico");
        d1.setNominalPower(230.0);
        d1.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 1D);
        d1.setAttributeValue(FridgeSpec.REFRIGERATOR_CAPACITY, 1D);
        d1.setAttributeValue(FridgeSpec.ANNUAL_CONSUMPTION, 34D);
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
        room.setSensorList(list);
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
        room.setSensorList(list);
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
        room.setSensorList(list);
        double result = room.getCurrentRoomTemperature();
        double expectedResult = 30.0;
        assertEquals(expectedResult, result, 0.01);
    }

    @Test
    void seeGetCurrentRoomTemperatureWithoutReadings() {
        SensorList list = new SensorList();
        Room room = new Room("quarto", 1, 80, 5, 3);
        room.setSensorList(list);

        assertThrows(IllegalArgumentException.class, room::getCurrentRoomTemperature);
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
        room.setSensorList(list);
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
        room.setSensorList(list);
        boolean result = room.addSensor(s1);
        assertFalse(result);
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
        assertFalse(result);
    }

    @Test
    void seeIfEqualsWorkDifClass() {
        TypeSensor type = new TypeSensor("temperature", "Celsius");
        Room room = new Room("quarto", 1, 80, 5, 3);
        boolean result = room.equals(type);
        assertFalse(result);
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
        Device d1 = new Fridge(new FridgeSpec());
        d1.setNominalPower(12.0);
        d1.setName("dsgdgdfg");
        d1.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 2D);
        d1.setAttributeValue(FridgeSpec.REFRIGERATOR_CAPACITY, 2D);
        d1.setAttributeValue(FridgeSpec.ANNUAL_CONSUMPTION, 45D);
        Device d2 = new Fridge(new FridgeSpec());
        d2.setNominalPower(10.0);
        d2.setName("fdgdhsd");
        d2.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 2D);
        d2.setAttributeValue(FridgeSpec.REFRIGERATOR_CAPACITY, 2D);
        d2.setAttributeValue(FridgeSpec.ANNUAL_CONSUMPTION, 45D);
        Device d3 = new Fridge(new FridgeSpec());
        d3.setNominalPower(1.0);
        d3.setName("sdgfddsa");
        d3.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 2D);
        d3.setAttributeValue(FridgeSpec.REFRIGERATOR_CAPACITY, 2D);
        d3.setAttributeValue(FridgeSpec.ANNUAL_CONSUMPTION, 45D);
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
        Device d1 = new Fridge(new FridgeSpec());
        d1.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 2D);
        d1.setAttributeValue(FridgeSpec.REFRIGERATOR_CAPACITY, 2D);
        d1.setAttributeValue(FridgeSpec.ANNUAL_CONSUMPTION, 45D);
        Device d2 = new WaterHeater(new WaterHeaterSpec());
        d2.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 200D);
        d2.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 30D);
        d2.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 0.9D);
        Device d3 = new WaterHeater(new WaterHeaterSpec());
        d3.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 500D);
        d3.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 20D);
        d3.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 0.9D);
        r1.addDevice(d1);
        r1.addDevice(d2);
        r1.addDevice(d3);
        Double expectedResult = 0.0;
        Double result = r1.getDailyConsumptionByDeviceType("WaterHeater", 1440);
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfRemoveRoomDevicesFromDeviceListAssertTrue() {
        Room r1 = new Room("quarto", 1, 12, 12, 12);
        Device d2 = new WaterHeater(new WaterHeaterSpec());
        d2.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 200D);
        d2.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 30D);
        d2.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 0.9D);
        Device d3 = new WaterHeater(new WaterHeaterSpec());
        d3.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 500D);
        d3.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 20D);
        d3.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 0.9D);
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
        Device d2 = new WaterHeater(new WaterHeaterSpec());
        d2.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 200D);
        d2.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 30D);
        d2.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 0.9D);
        Device d3 = new WaterHeater(new WaterHeaterSpec());
        d3.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 500D);
        d3.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 20D);
        d3.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 0.9D);
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
        Device d2 = new WaterHeater(new WaterHeaterSpec());
        d2.setName("sdgdfg");
        d2.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 200D);
        d2.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 30D);
        d2.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 0.9D);
        Device d1 = new WaterHeater(new WaterHeaterSpec());
        d1.setName("hgfhs");
        d1.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 200D);
        d1.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 30D);
        d1.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 0.9D);
        Device d3 = new WaterHeater(new WaterHeaterSpec());
        d3.setName("hfhgfh");
        d3.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 500D);
        d3.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 20D);
        d3.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 0.9D);
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
        Device d2 = new WaterHeater(new WaterHeaterSpec());
        d2.setName("sdgfdg");
        d2.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 200D);
        d2.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 30D);
        d2.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 0.9D);
        Device d3 = new WaterHeater(new WaterHeaterSpec());
        d3.setName("fdsgfds");
        d3.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 500D);
        d3.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 20D);
        d3.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 0.9D);
        r1.addDevice(d2);
        r1.addDevice(d3);
        d2.setAttributeValue("Cold Water Temperature", 5.0);
        d2.setAttributeValue("Volume Of Water To Heat", 100.0);
        d3.setAttributeValue("Volume Of Water To Heat", 100.0);
        d3.setAttributeValue("Cold Water Temperature", 1.0);
        double expectedResult = 4.60548;
        double result = r1.getDailyConsumptionByDeviceType(d2.getType(), 1440);
        assertEquals(expectedResult, result);
    }

    @Test
    void addDeviceFails() {
        Room r1 = new Room("quarto", 1, 12, 12, 12);
        Device d2 = new WaterHeater(new WaterHeaterSpec());
        d2.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 200D);
        d2.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 30D);
        d2.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 10D);
        Device d3 = new WaterHeater(new WaterHeaterSpec());
        d3.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 500D);
        d3.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 30D);
        d3.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 1D);
        r1.addDevice(d2);
        boolean result = r1.addDevice(d3);
        assertFalse(result);
    }

    @Test
    void addDeviceSucceeds() {
        Room r1 = new Room("quarto", 1, 12, 12, 12);
        Device d2 = new WaterHeater(new WaterHeaterSpec());
        d2.setName("dsgsg");
        d2.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 200D);
        d2.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 20D);
        d2.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 10D);
        Device d3 = new WaterHeater(new WaterHeaterSpec());
        d3.setName("dssg");
        d3.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 500D);
        d3.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 30D);
        d3.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 1D);
        r1.addDevice(d2);
        boolean result = r1.addDevice(d3);
        assertTrue(result);
    }

    @Test
    void addDeviceSucceeds2() {
        Room r1 = new Room("quarto", 1, 12, 12, 12);
        Device d2 = new WaterHeater(new WaterHeaterSpec());
        d2.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 200D);
        d2.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 20D);
        d2.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 10D);
        Device d3 = new WaterHeater(new WaterHeaterSpec());
        d3.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 500D);
        d3.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 30D);
        d3.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 1D);
        r1.addDevice(d2);
        r1.addDevice(d3);
        boolean result = r1.addDevice(d3);
        assertFalse(result);
    }

    @Test
    void addDeviceSucceeds3() {
        Room r1 = new Room("quarto", 1, 12, 12, 12);
        Device d3 = new WaterHeater(new WaterHeaterSpec());
        d3.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 500D);
        d3.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 30D);
        d3.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 1D);
        boolean expectedResult = true;
        boolean result = r1.addDevice(d3);
        assertEquals(expectedResult, result);
    }

    @Test
    void getRoomDevicesOfGivenTypeSuccess() {
        Room r1 = new Room("quarto", 1, 12, 12, 12);
        Device d2 = new WaterHeater(new WaterHeaterSpec());
        d2.setName("sdgdh");
        d2.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 200D);
        d2.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 20D);
        d2.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 10D);
        Device d3 = new WaterHeater(new WaterHeaterSpec());
        d3.setName("fgdffds");
        d3.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 500D);
        d3.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 30D);
        d3.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 1D);
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
        Device d2 = new WaterHeater(new WaterHeaterSpec());
        d2.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 200D);
        d2.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 20D);
        d2.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 10D);
        Device d3 = new WaterHeater(new WaterHeaterSpec());
        d3.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 500D);
        d3.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 30D);
        d3.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 1D);
        r1.addDevice(d2);
        r1.addDevice(d3);
        List<Device> expectedResult = new ArrayList<>();
        List<Device> result = r1.getDevicesOfGivenType("Fridge");
        assertEquals(expectedResult, result);
    }

    @Test
    void removeDeviceSucess() {
        Room r1 = new Room("quarto", 1, 12, 12, 12);
        Device d2 = new WaterHeater(new WaterHeaterSpec());
        d2.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 200D);
        d2.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 20D);
        d2.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 10D);
        Device d3 = new WaterHeater(new WaterHeaterSpec());
        d3.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 500D);
        d3.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 30D);
        d3.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 1D);
        r1.addDevice(d2);
        r1.addDevice(d3);
        boolean result = r1.removeDevice(d2);
        Assertions.assertTrue(result);
    }

    @Test
    void removeDeviceFails() {
        Room r1 = new Room("quarto", 1, 12, 12, 12);
        Device d2 = new WaterHeater(new WaterHeaterSpec());
        d2.setName("sdfdsfg");
        d2.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 200D);
        d2.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 20D);
        d2.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 10D);
        Device d3 = new WaterHeater(new WaterHeaterSpec());
        d3.setName("fdhgh");
        d3.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 500D);
        d3.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 30D);
        d3.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 1D);
        Device d4 = new WaterHeater(new WaterHeaterSpec());
        d4.setName("fdgfdsh");
        d4.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 50D);
        d4.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 3D);
        d4.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 1D);
        r1.addDevice(d2);
        r1.addDevice(d3);
        boolean result = r1.removeDevice(d4);
        assertFalse(result);

    }

    @Test
    void seeIfAddRoomDevicesToDeviceListWorksWhenDeviceListAlreadyAddedToRoom() {
        //Arrange
        Device device1 = new WaterHeater(new WaterHeaterSpec());
        device1.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 12D);
        device1.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 40D);
        device1.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 234D);
        Device device2 = new WaterHeater(new WaterHeaterSpec());
        device2.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 12D);
        device2.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 40D);
        device2.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 234D);
        Room room = new Room("cozinha", 1, 1, 1, 1);
        DeviceList dList = new DeviceList();
        dList.addDevice(device1);
        dList.addDevice(device2);
        room.setDeviceList(dList);
        //Act
        room.addRoomDevicesToDeviceList(dList);
        List<Device> actualResult = room.getDeviceList();
        List<Device> expectedResult = dList.getList();
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSetDeviceListWorksAlreadyContained() {
        //Arrange
        Device device1 = new WaterHeater(new WaterHeaterSpec());
        device1.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 12D);
        device1.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 40D);
        device1.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 234D);
        Device device2 = new WaterHeater(new WaterHeaterSpec());
        device2.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 12D);
        device2.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 40D);
        device2.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 234D);
        Room room = new Room("cozinha", 1, 1, 1, 1);
        DeviceList dList = new DeviceList();
        dList.addDevice(device1);
        dList.addDevice(device2);
        DeviceList deviceList2 = new DeviceList();
        deviceList2.addDevice(device1);
        deviceList2.addDevice(device2);
        room.setDeviceList(dList);
        //Act
        List<Device> actualResult = room.getDeviceList();
        List<Device> expectedResult = dList.getList();
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfAddRoomDevicesToDeviceListWorksAlreadyContained() {
        //Arrange
        Device device1 = new WaterHeater(new WaterHeaterSpec());
        device1.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 12D);
        device1.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 40D);
        device1.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 234D);
        Device device2 = new WaterHeater(new WaterHeaterSpec());
        device2.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 12D);
        device2.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 40D);
        device2.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 234D);
        Room room = new Room("cozinha", 1, 1, 1, 1);
        DeviceList dList = new DeviceList();
        dList.addDevice(device1);
        dList.addDevice(device2);
        DeviceList deviceList2 = new DeviceList();
        room.addRoomDevicesToDeviceList(dList);
        //Act
        List<Device> actualResult = room.getDeviceList();
        List<Device> expectedResult = deviceList2.getList();
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfAddRoomDevicesToDeviceListWorksWhenNotYetAddedToRoom() {
        //Arrange
        Device device1 = new WaterHeater(new WaterHeaterSpec());
        device1.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 12D);
        device1.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 40D);
        device1.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 234D);
        Device device2 = new WaterHeater(new WaterHeaterSpec());
        device2.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 12D);
        device2.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 40D);
        device2.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 234D);
        Room room = new Room("cozinha", 1, 1, 1, 1);
        DeviceList dList = new DeviceList();
        DeviceList dList2 = new DeviceList();
        dList.addDevice(device1);
        dList.addDevice(device2);
        room.addDevice(device1);
        room.addDevice(device2);
        room.addRoomDevicesToDeviceList(dList2);
        //Act
        List<Device> actualResult = room.getDeviceList();
        List<Device> expectedResult = dList.getList();
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void ensureThatWeDoNotAddADeviceToADeviceList() {
        DeviceList deviceList = new DeviceList();
        Device device = new WaterHeater(new WaterHeaterSpec());
        device.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 12D);
        device.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 40D);
        device.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 234D);
        Room room = new Room("Room", 1, 2, 3, 4);
        deviceList.addDevice(device);
        room.setDeviceList(deviceList);
        boolean expectedResult = false;
        boolean actualResult = room.addDevice(device);
        assertEquals(expectedResult, actualResult);
    }


    @Test
    void seeThatWeAddADeviceToADeviceList() {
        DeviceList deviceList = new DeviceList();
        Device device = new WaterHeater(new WaterHeaterSpec());
        device.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 12D);
        device.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 40D);
        device.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 234D);
        Room room = new Room("Room", 1, 2, 3, 4);
        room.setDeviceList(deviceList);
        boolean expectedResult = true;
        boolean actualResult = room.addDevice(device);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetEnergyConsumption() {
        Room room = new Room("Room", 1, 2, 3, 4);
        double expectedResult = 0;
        double value = room.getEnergyConsumption(21);
        Assert.assertEquals(expectedResult, value);
    }

    @Test
    void getTemperatureSensors() {
        Room room1 = new Room("room1", 1, 2, 3, 4); //NO SENSORS
        Room room2 = new Room("room2", 1, 2, 3, 4); //TWO TEMPERATURE SENSORS
        Room room3 = new Room("room3", 1, 2, 3, 4); //ONE HUMIDITY SENSOR
        Room room4 = new Room("room4", 1, 2, 3, 4); //TWO TEMP + ONE HUMIDITY

        GregorianCalendar gregorianCalendar = new GregorianCalendar(2018, 1, 1);
        Sensor sensor1 = new Sensor("sensor1", new TypeSensor("temperature", "ºC"), gregorianCalendar.getTime());
        Sensor sensor2 = new Sensor("sensor2", new TypeSensor("humidity", "%"), gregorianCalendar.getTime());
        Sensor sensor3 = new Sensor("sensor3", new TypeSensor("temperature", "ºC"), gregorianCalendar.getTime());

        room2.addSensor(sensor1);
        room2.addSensor(sensor3);
        room3.addSensor(sensor2);
        room4.addSensor(sensor1);
        room4.addSensor(sensor2);
        room4.addSensor(sensor3);

        SensorList expectedResult1 = new SensorList();
        SensorList expectedResult2 = new SensorList();
        SensorList expectedResult3 = new SensorList();
        SensorList expectedResult4 = new SensorList();
        expectedResult2.addSensor(sensor1);
        expectedResult2.addSensor(sensor3);
        expectedResult4.addSensor(sensor1);
        expectedResult4.addSensor(sensor3);

        //ACT
        SensorList actualResult1 = room1.getSensorsOfGivenType("temperature");
        SensorList actualResult2 = room2.getSensorsOfGivenType("temperature");
        SensorList actualResult3 = room3.getSensorsOfGivenType("temperature");
        SensorList actualResult4 = room4.getSensorsOfGivenType("temperature");
        //ASSERT
        assertEquals(actualResult1, expectedResult1);
        assertEquals(actualResult2, expectedResult2);
        assertEquals(actualResult3, expectedResult3);
        assertEquals(actualResult4, expectedResult4);
    }

    @Test
    void getMaxTemperatureOnGivenDay() {
        Room room1 = new Room("room1", 1, 2, 3, 4); //TWO TEMPERATURE SENSORS WITH READINGS SAME VALUE ON DAY TESTED + ONE HUMIDITY
        Room room2 = new Room("room4", 1, 2, 3, 4); //TWO TEMPERATURE SENSORS WITH MAX IN FIRST SENSOR + ONE HUMIDITY
        Room room3 = new Room("room4", 1, 2, 3, 4); //TWO TEMPERATURE SENSORS WITH MAX IN SECOND SENSOR + ONE HUMIDITY
        Room room4 = new Room("room4", 1, 2, 3, 4); //TWO TEMPERATURE SENSORS WITH 3 READINGS ON SAME SENSOR (MAX IN FIRST READING) + ONE HUMIDITY
        Room room5 = new Room("room4", 1, 2, 3, 4); //TWO TEMPERATURE SENSORS WITH 3 READINGS ON SAME SENSOR (MAX IN SECOND READING) + ONE HUMIDITY
        Room room6 = new Room("room4", 1, 2, 3, 4); //TWO TEMPERATURE SENSORS WITH 3 READINGS ON SAME SENSOR (MAX IN THIRD READING) + ONE HUMIDITY

        GregorianCalendar gregorianCalendar1 = new GregorianCalendar(2018, 1, 1, 23, 59);
        GregorianCalendar gregorianCalendar2 = new GregorianCalendar(2018, 1, 2, 0, 0);
        GregorianCalendar gregorianCalendar3 = new GregorianCalendar(2018, 1, 2, 1, 1);
        GregorianCalendar gregorianCalendar4 = new GregorianCalendar(2018, 1, 2, 12, 12);

        Sensor sensor1 = new Sensor("sensor1", new TypeSensor("temperature", "ºC"), gregorianCalendar1.getTime());
        Sensor sensor2 = new Sensor("sensor2", new TypeSensor("humidity", "%"), gregorianCalendar1.getTime());
        Sensor sensor3 = new Sensor("sensor3", new TypeSensor("temperature", "ºC"), gregorianCalendar1.getTime());
        Reading reading1 = new Reading(20, gregorianCalendar2.getTime());
        sensor1.addReading(reading1);
        sensor2.addReading(reading1);
        sensor3.addReading(reading1);

        room1.addSensor(sensor1);
        room1.addSensor(sensor2);
        room1.addSensor(sensor3);

        Sensor sensor4 = new Sensor("sensor4", new TypeSensor("temperature", "ºC"), gregorianCalendar1.getTime());
        Sensor sensor5 = new Sensor("sensor5", new TypeSensor("temperature", "ºC"), gregorianCalendar1.getTime());
        Reading reading2 = new Reading(24, gregorianCalendar2.getTime());
        Reading reading3 = new Reading(20, gregorianCalendar3.getTime());
        sensor4.addReading(reading2);
        sensor5.addReading(reading3);

        room2.addSensor(sensor2);
        room2.addSensor(sensor4);
        room2.addSensor(sensor5);

        Sensor sensor6 = new Sensor("sensor6", new TypeSensor("temperature", "ºC"), gregorianCalendar1.getTime());
        Sensor sensor7 = new Sensor("sensor7", new TypeSensor("temperature", "ºC"), gregorianCalendar1.getTime());
        Reading reading4 = new Reading(20, gregorianCalendar2.getTime());
        Reading reading5 = new Reading(25, gregorianCalendar3.getTime());
        sensor6.addReading(reading4);
        sensor7.addReading(reading5);

        room3.addSensor(sensor2);
        room3.addSensor(sensor6);
        room3.addSensor(sensor7);

        Sensor sensor8 = new Sensor("sensor8", new TypeSensor("temperature", "ºC"), gregorianCalendar1.getTime());
        Sensor sensor9 = new Sensor("sensor9", new TypeSensor("temperature", "ºC"), gregorianCalendar1.getTime());
        Reading reading6 = new Reading(26, gregorianCalendar2.getTime());
        Reading reading7 = new Reading(21, gregorianCalendar3.getTime());
        Reading reading8 = new Reading(20, gregorianCalendar4.getTime());
        sensor8.addReading(reading6);
        sensor8.addReading(reading7);
        sensor8.addReading(reading8);
        sensor9.addReading(reading8);

        room4.addSensor(sensor2);
        room4.addSensor(sensor8);
        room4.addSensor(sensor9);

        Sensor sensor10 = new Sensor("sensor10", new TypeSensor("temperature", "ºC"), gregorianCalendar1.getTime());
        Sensor sensor11 = new Sensor("sensor11", new TypeSensor("temperature", "ºC"), gregorianCalendar1.getTime());
        Reading reading9 = new Reading(21, gregorianCalendar2.getTime());
        Reading reading10 = new Reading(27, gregorianCalendar3.getTime());
        Reading reading11 = new Reading(20, gregorianCalendar4.getTime());
        sensor10.addReading(reading9);
        sensor10.addReading(reading10);
        sensor11.addReading(reading11);
        sensor11.addReading(reading11);

        room5.addSensor(sensor2);
        room5.addSensor(sensor10);
        room5.addSensor(sensor11);


        Sensor sensor12 = new Sensor("sensor12", new TypeSensor("temperature", "ºC"), gregorianCalendar1.getTime());
        Sensor sensor13 = new Sensor("sensor13", new TypeSensor("temperature", "ºC"), gregorianCalendar1.getTime());
        Reading reading12 = new Reading(21, gregorianCalendar2.getTime());
        Reading reading13 = new Reading(20, gregorianCalendar3.getTime());
        Reading reading14 = new Reading(28, gregorianCalendar4.getTime());
        sensor12.addReading(reading12);
        sensor12.addReading(reading13);
        sensor12.addReading(reading14);
        sensor13.addReading(reading12);

        room6.addSensor(sensor2);
        room6.addSensor(sensor12);
        room6.addSensor(sensor13);

        //ACT
        double actualResult1 = room1.getMaxTemperatureOnGivenDay(gregorianCalendar2.getTime());
        double actualResult2 = room2.getMaxTemperatureOnGivenDay(gregorianCalendar2.getTime());
        double actualResult3 = room3.getMaxTemperatureOnGivenDay(gregorianCalendar2.getTime());
        double actualResult4 = room4.getMaxTemperatureOnGivenDay(gregorianCalendar2.getTime());
        double actualResult5 = room5.getMaxTemperatureOnGivenDay(gregorianCalendar2.getTime());
        double actualResult6 = room6.getMaxTemperatureOnGivenDay(gregorianCalendar2.getTime());


        //ASSERT
        assertEquals(actualResult1, 20, 0.01);
        assertEquals(actualResult2, 24, 0.01);
        assertEquals(actualResult3, 25, 0.01);
        assertEquals(actualResult4, 26, 0.01);
        assertEquals(actualResult5, 27, 0.01);
        assertEquals(actualResult6, 28, 0.01);
    }
    @Test
    void getMaxTemperatureOnGivenDayIllegalArguments() {
        //Arrange

        Room room1 = new Room("room1", 1, 2, 3, 4); //NO SENSORS
        Room room2 = new Room("room2", 1, 2, 3, 4); //TWO TEMPERATURE SENSORS WITHOUT READINGS + ONE HUMIDITY

        GregorianCalendar gregorianCalendar1 = new GregorianCalendar(2018, 1, 1, 23, 59);

        Sensor sensor1 = new Sensor("sensor1", new TypeSensor("temperature", "ºC"), gregorianCalendar1.getTime());
        Sensor sensor2 = new Sensor("sensor2", new TypeSensor("humidity", "%"), gregorianCalendar1.getTime());
        Sensor sensor3 = new Sensor("sensor3", new TypeSensor("temperature", "ºC"), gregorianCalendar1.getTime());

        room2.addSensor(sensor1);
        room2.addSensor(sensor2);
        room2.addSensor(sensor3);

        //Act and Assert

        assertThrows(IllegalArgumentException.class, () -> room1.getMaxTemperatureOnGivenDay(gregorianCalendar1.getTime()));
        assertThrows(IllegalArgumentException.class, () -> room2.getMaxTemperatureOnGivenDay(gregorianCalendar1.getTime()));
    }

    @Test
    void seeGetCurrentRoomTemperature() {
        Room room1 = new Room("room1", 1, 2, 3, 4); //NO SENSORS
        Room room2 = new Room("room2", 1, 2, 3, 4); //TWO TEMPERATURE SENSORS WITHOUT READINGS + ONE HUMIDITY
        Room room3 = new Room("room3", 1, 2, 3, 4); //TWO TEMPERATURE SENSORS WITH MOST RECENT READING ON SAME MINUTE + ONE HUMIDITY
        Room room4 = new Room("room4", 1, 2, 3, 4); //TWO TEMPERATURE SENSORS WITH MOST RECENT IN FIRST SENSOR + ONE HUMIDITY
        Room room5 = new Room("room4", 1, 2, 3, 4); //TWO TEMPERATURE SENSORS WITH MOST RECENT IN SECOND SENSOR + ONE HUMIDITY
        Room room6 = new Room("room4", 1, 2, 3, 4); //TWO TEMPERATURE SENSORS WITH 3 READINGS ON SAME SENSOR (MOST RECENT IN FIRST READING) + ONE HUMIDITY
        Room room7 = new Room("room4", 1, 2, 3, 4); //TWO TEMPERATURE SENSORS WITH 3 READINGS ON SAME SENSOR (MOST RECENT IN SECOND READING) + ONE HUMIDITY
        Room room8 = new Room("room4", 1, 2, 3, 4); //TWO TEMPERATURE SENSORS WITH 3 READINGS ON SAME SENSOR (MOST RECENT IN THIRD READING) + ONE HUMIDITY

        GregorianCalendar gregorianCalendar1 = new GregorianCalendar(2018, 1, 1, 23, 59);
        GregorianCalendar gregorianCalendar2 = new GregorianCalendar(2018, 1, 2, 0, 0);
        GregorianCalendar gregorianCalendar3 = new GregorianCalendar(2018, 1, 2, 0, 1);

        Sensor sensor1 = new Sensor("sensor1", new TypeSensor("temperature", "ºC"), gregorianCalendar1.getTime());
        Sensor sensor2 = new Sensor("sensor2", new TypeSensor("humidity", "%"), gregorianCalendar1.getTime());
        Sensor sensor3 = new Sensor("sensor3", new TypeSensor("temperature", "ºC"), gregorianCalendar1.getTime());

        room2.addSensor(sensor1);
        room2.addSensor(sensor2);
        room2.addSensor(sensor3);

        Sensor sensor4 = new Sensor("sensor4", new TypeSensor("temperature", "ºC"), gregorianCalendar1.getTime());
        Sensor sensor5 = new Sensor("sensor5", new TypeSensor("humidity", "%"), gregorianCalendar1.getTime());
        Sensor sensor6 = new Sensor("sensor6", new TypeSensor("temperature", "ºC"), gregorianCalendar1.getTime());
        Reading reading1 = new Reading(20, gregorianCalendar2.getTime());
        sensor4.addReading(reading1);
        sensor5.addReading(reading1);
        sensor6.addReading(reading1);

        room3.addSensor(sensor4);
        room3.addSensor(sensor5);
        room3.addSensor(sensor6);

        Sensor sensor7 = new Sensor("sensor7", new TypeSensor("temperature", "ºC"), gregorianCalendar1.getTime());
        Sensor sensor8 = new Sensor("sensor8", new TypeSensor("temperature", "ºC"), gregorianCalendar1.getTime());
        Reading reading2 = new Reading(24, gregorianCalendar2.getTime());
        Reading reading3 = new Reading(20, gregorianCalendar1.getTime());
        sensor7.addReading(reading2);
        sensor8.addReading(reading3);

        room4.addSensor(sensor5);
        room4.addSensor(sensor7);
        room4.addSensor(sensor8);

        Sensor sensor9 = new Sensor("sensor9", new TypeSensor("temperature", "ºC"), gregorianCalendar1.getTime());
        Sensor sensor10 = new Sensor("sensor10", new TypeSensor("temperature", "ºC"), gregorianCalendar1.getTime());
        Reading reading4 = new Reading(20, gregorianCalendar1.getTime());
        Reading reading5 = new Reading(25, gregorianCalendar2.getTime());
        sensor9.addReading(reading4);
        sensor10.addReading(reading5);

        room5.addSensor(sensor5);
        room5.addSensor(sensor9);
        room5.addSensor(sensor10);

        Sensor sensor11 = new Sensor("sensor11", new TypeSensor("temperature", "ºC"), gregorianCalendar1.getTime());
        Sensor sensor12 = new Sensor("sensor12", new TypeSensor("temperature", "ºC"), gregorianCalendar1.getTime());
        Reading reading6 = new Reading(26, gregorianCalendar3.getTime());
        Reading reading7 = new Reading(21, gregorianCalendar1.getTime());
        Reading reading8 = new Reading(20, gregorianCalendar2.getTime());
        sensor11.addReading(reading6);
        sensor11.addReading(reading7);
        sensor11.addReading(reading8);
        sensor12.addReading(reading8);

        room6.addSensor(sensor5);
        room6.addSensor(sensor11);
        room6.addSensor(sensor12);

        Sensor sensor13 = new Sensor("sensor13", new TypeSensor("temperature", "ºC"), gregorianCalendar1.getTime());
        Sensor sensor14 = new Sensor("sensor14", new TypeSensor("temperature", "ºC"), gregorianCalendar1.getTime());
        Reading reading9 = new Reading(21, gregorianCalendar2.getTime());
        Reading reading10 = new Reading(27, gregorianCalendar3.getTime());
        Reading reading11 = new Reading(20, gregorianCalendar1.getTime());
        sensor13.addReading(reading9);
        sensor13.addReading(reading10);
        sensor13.addReading(reading11);
        sensor14.addReading(reading11);

        room7.addSensor(sensor5);
        room7.addSensor(sensor13);
        room7.addSensor(sensor14);


        Sensor sensor15 = new Sensor("sensor15", new TypeSensor("Temperature", "ºC"), gregorianCalendar1.getTime());
        Sensor sensor16 = new Sensor("sensor16", new TypeSensor("Temperature", "ºC"), gregorianCalendar1.getTime());
        Reading reading12 = new Reading(21, gregorianCalendar2.getTime());
        Reading reading13 = new Reading(20, gregorianCalendar1.getTime());
        Reading reading14 = new Reading(28, gregorianCalendar3.getTime());
        sensor15.addReading(reading12);
        sensor15.addReading(reading13);
        sensor15.addReading(reading14);
        sensor16.addReading(reading12);

        room8.addSensor(sensor5);
        room8.addSensor(sensor15);
        room8.addSensor(sensor16);

        //ACT
        double actualResult3 = room3.getCurrentRoomTemperature();
        double actualResult4 = room4.getCurrentRoomTemperature();
        double actualResult5 = room5.getCurrentRoomTemperature();
        double actualResult6 = room6.getCurrentRoomTemperature();
        double actualResult7 = room7.getCurrentRoomTemperature();
        double actualResult8 = room8.getCurrentRoomTemperature();


        //ASSERT
        assertThrows(IllegalArgumentException.class, room1::getCurrentRoomTemperature);
        assertThrows(IllegalArgumentException.class, room2::getCurrentRoomTemperature);
        assertEquals(actualResult3, 20, 0.01);
        assertEquals(actualResult4, 24, 0.01);
        assertEquals(actualResult5, 25, 0.01);
        assertEquals(actualResult6, 26, 0.01);
        assertEquals(actualResult7, 27, 0.01);
        assertEquals(actualResult8, 28, 0.01);
    }

    @Test
    void getCurrentRoomTemperatureIllegalArguments() {
        //Arrange

        Room room1 = new Room("room1", 1, 2, 3, 4); //NO SENSORS
        Room room2 = new Room("room2", 1, 2, 3, 4); //TWO TEMPERATURE SENSORS WITHOUT READINGS + ONE HUMIDITY

        GregorianCalendar gregorianCalendar1 = new GregorianCalendar(2018, 1, 1, 23, 59);

        Sensor sensor1 = new Sensor("sensor1", new TypeSensor("temperature", "ºC"), gregorianCalendar1.getTime());
        Sensor sensor2 = new Sensor("sensor2", new TypeSensor("humidity", "%"), gregorianCalendar1.getTime());
        Sensor sensor3 = new Sensor("sensor3", new TypeSensor("temperature", "ºC"), gregorianCalendar1.getTime());

        room2.addSensor(sensor1);
        room2.addSensor(sensor2);
        room2.addSensor(sensor3);

        //Act and Assert

        assertThrows(IllegalArgumentException.class, room1::getCurrentRoomTemperature);
        assertThrows(IllegalArgumentException.class, room2::getCurrentRoomTemperature);
    }


    @Test
    void getSensorList() {
        Room room1 = new Room("room1", 0, 1, 1, 1); //NO SENSORS
        Room room2 = new Room("room2", 0, 1, 1, 1); //ONE SENSOR
        Room room3 = new Room("room3", 0, 1, 1, 1); //TWO SENSORS

        GregorianCalendar gregorianCalendar1 = new GregorianCalendar(2018, 1, 1, 23, 59);
        Sensor sensor1 = new Sensor("sensor1", new TypeSensor("temperature", "ºC"), gregorianCalendar1.getTime());
        Sensor sensor2 = new Sensor("sensor2", new TypeSensor("humidity", "%"), gregorianCalendar1.getTime());

        room2.addSensor(sensor1);
        room3.addSensor(sensor1);
        room3.addSensor(sensor2);

        SensorList expectedResult1 = new SensorList();
        SensorList expectedResult2 = new SensorList();
        SensorList expectedResult3 = new SensorList();
        expectedResult2.addSensor(sensor1);
        expectedResult3.addSensor(sensor1);
        expectedResult3.addSensor(sensor2);
        //ACT
        SensorList actualResult1 = room1.getSensorList();
        SensorList actualResult2 = room2.getSensorList();
        SensorList actualResult3 = room3.getSensorList();
        //ASSERT
        assertEquals(actualResult1, expectedResult1);
        assertEquals(actualResult2, expectedResult2);
        assertEquals(actualResult3, expectedResult3);
    }
}