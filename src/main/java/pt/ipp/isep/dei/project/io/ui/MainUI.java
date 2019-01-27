package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.DeviceList;
import pt.ipp.isep.dei.project.model.device.Program;
import pt.ipp.isep.dei.project.model.device.ProgramList;

import java.util.*;

public class MainUI {

    public static void main(String[] args) {
        String temperature = "temperature";
        String portoString = "Porto";

        //Program Variables

        // *************************
        // ******* < MOCK DATA FOR TESTING PURPOSES >*******
        // *************************

        // *** MAIN MOCK *******************

        // Geo List (1)

        GeographicAreaList geographicalAreaList = new GeographicAreaList();

        // Geo Areas (4)

        GeographicArea isep = new GeographicArea("ISEP", new TypeArea("urban area"), 52, 5, new Local(41.178553, -8.608035, 111));
        isep.setWidth(0.261);
        isep.setLength(0.249);
        isep.setDescription("Campus do ISEP");


        GeographicArea porto = new GeographicArea(portoString, new TypeArea("city"), 31, 6, new Local(41.164077, -8.620802, 118));
        porto.setWidth(10.09);
        porto.setLength(10.09);
        porto.setDescription("City of Porto");

        GeographicArea portugal = new GeographicArea("Portugal", new TypeArea("country"), 22, 9, new Local(60, 0, 110));
        portugal.setWidth(100);
        portugal.setLength(800);
        portugal.setDescription("Country of Portugal");

        GeographicArea portoSanto = new GeographicArea("Porto Santo", new TypeArea("city"), 42, 12, new Local(-45, 67, 67));
        portoSanto.setWidth(156);
        portoSanto.setLength(235);
        portoSanto.setDescription("City of Porto Santo");

        geographicalAreaList.addGeographicAreaToGeographicAreaList(isep);
        geographicalAreaList.addGeographicAreaToGeographicAreaList(porto);
        geographicalAreaList.addGeographicAreaToGeographicAreaList(portugal);
        geographicalAreaList.addGeographicAreaToGeographicAreaList(portoSanto);

        //Rooms

        //Edificio B Rooms
        Room roomISEP1 = new Room("B107", 1, 7, 11, 3.5);
        Room roomISEP2 = new Room("B109", 1, 7, 11, 3.5);
        Room roomISEP3 = new Room("B106", 1, 7, 13, 3.5);

        RoomList gridRoomList = new RoomList();
        gridRoomList.addRoom(roomISEP2);
        gridRoomList.addRoom(roomISEP3);

        // Houses (1 per Geographical Area!)

        House edificioB = new House("Edificio B", "Rua Dr António Bernardino de Almeida, 431", "4200-072", portoString, new Local(41.177748, -8.607745, 112), isep);
        edificioB.setMotherArea(isep);
        edificioB.addRoomToRoomList(roomISEP1);
        edificioB.addRoomToRoomList(roomISEP2);
        edificioB.addRoomToRoomList(roomISEP3);

        // Sensor Readings

        ReadingList readingListSensorRoom109 = new ReadingList();
        Reading reading10 = new Reading(14, new GregorianCalendar(2018, GregorianCalendar.DECEMBER, 30).getTime());
        Reading reading11 = new Reading(13.7, new GregorianCalendar(2018, GregorianCalendar.DECEMBER, 30).getTime());
        Reading reading12 = new Reading(16.5, new GregorianCalendar(2018, GregorianCalendar.DECEMBER, 30).getTime());
        Reading reading13 = new Reading(15.1, new GregorianCalendar(2018, GregorianCalendar.DECEMBER, 30).getTime());
        Reading reading14 = new Reading(13.8, new GregorianCalendar(2018, GregorianCalendar.DECEMBER, 31).getTime());
        Reading reading15 = new Reading(13.3, new GregorianCalendar(2018, GregorianCalendar.DECEMBER, 31).getTime());
        Reading reading16 = new Reading(15.5, new GregorianCalendar(2018, GregorianCalendar.DECEMBER, 31).getTime());
        Reading reading17 = new Reading(14.2, new GregorianCalendar(2018, GregorianCalendar.DECEMBER, 31).getTime());
        Reading reading18 = new Reading(12.5, new GregorianCalendar(2019, GregorianCalendar.JANUARY, 1).getTime());
        Reading reading19 = new Reading(12.4, new GregorianCalendar(2019, GregorianCalendar.JANUARY, 1).getTime());
        Reading reading20 = new Reading(13.8, new GregorianCalendar(2019, GregorianCalendar.JANUARY, 1).getTime());
        Reading reading21 = new Reading(12.9, new GregorianCalendar(2019, GregorianCalendar.JANUARY, 1).getTime());
        Reading reading22 = new Reading(11.5, new GregorianCalendar(2019, GregorianCalendar.JANUARY, 2).getTime());
        Reading reading23 = new Reading(11.2, new GregorianCalendar(2019, GregorianCalendar.JANUARY, 2).getTime());
        Reading reading24 = new Reading(13.5, new GregorianCalendar(2019, GregorianCalendar.JANUARY, 2).getTime());
        Reading reading25 = new Reading(12.8, new GregorianCalendar(2019, GregorianCalendar.JANUARY, 2).getTime());
        readingListSensorRoom109.addReading(reading10);
        readingListSensorRoom109.addReading(reading11);
        readingListSensorRoom109.addReading(reading12);
        readingListSensorRoom109.addReading(reading13);
        readingListSensorRoom109.addReading(reading14);
        readingListSensorRoom109.addReading(reading15);
        readingListSensorRoom109.addReading(reading16);
        readingListSensorRoom109.addReading(reading17);
        readingListSensorRoom109.addReading(reading18);
        readingListSensorRoom109.addReading(reading19);
        readingListSensorRoom109.addReading(reading20);
        readingListSensorRoom109.addReading(reading21);
        readingListSensorRoom109.addReading(reading22);
        readingListSensorRoom109.addReading(reading23);
        readingListSensorRoom109.addReading(reading24);
        readingListSensorRoom109.addReading(reading25);

        ReadingList readingListISEPRainfall = new ReadingList();
        Reading reading26 = new Reading(0.5, new GregorianCalendar(2018, GregorianCalendar.DECEMBER, 29).getTime());
        Reading reading27 = new Reading(1.2, new GregorianCalendar(2018, GregorianCalendar.DECEMBER, 30).getTime());
        Reading reading28 = new Reading(1.5, new GregorianCalendar(2018, GregorianCalendar.DECEMBER, 31).getTime());
        Reading reading29 = new Reading(0.3, new GregorianCalendar(2019, GregorianCalendar.JANUARY, 1).getTime());
        Reading reading30 = new Reading(0.0, new GregorianCalendar(2019, GregorianCalendar.JANUARY, 2).getTime());
        Reading reading31 = new Reading(0.0, new GregorianCalendar(2019, GregorianCalendar.JANUARY, 2).getTime());
        Reading reading32 = new Reading(0.0, new GregorianCalendar(2019, GregorianCalendar.JANUARY, 3).getTime());
        readingListISEPRainfall.addReading(reading26);
        readingListISEPRainfall.addReading(reading27);
        readingListISEPRainfall.addReading(reading28);
        readingListISEPRainfall.addReading(reading29);
        readingListISEPRainfall.addReading(reading30);
        readingListISEPRainfall.addReading(reading31);
        readingListISEPRainfall.addReading(reading32);

        ReadingList readingListISEPtemperature = new ReadingList();
        Reading reading33 = new Reading(8, new GregorianCalendar(2018, GregorianCalendar.DECEMBER, 30).getTime());
        Reading reading34 = new Reading(6.9, new GregorianCalendar(2018, GregorianCalendar.DECEMBER, 30).getTime());
        Reading reading35 = new Reading(16.5, new GregorianCalendar(2018, GregorianCalendar.DECEMBER, 30).getTime());
        Reading reading36 = new Reading(11.2, new GregorianCalendar(2018, GregorianCalendar.DECEMBER, 30).getTime());
        Reading reading37 = new Reading(7.2, new GregorianCalendar(2018, GregorianCalendar.DECEMBER, 31).getTime());
        Reading reading38 = new Reading(5.3, new GregorianCalendar(2018, GregorianCalendar.DECEMBER, 31).getTime());
        Reading reading39 = new Reading(15.1, new GregorianCalendar(2018, GregorianCalendar.DECEMBER, 31).getTime());
        Reading reading40 = new Reading(9.2, new GregorianCalendar(2018, GregorianCalendar.JANUARY, 31).getTime());
        Reading reading41 = new Reading(6.5, new GregorianCalendar(2019, GregorianCalendar.JANUARY, 1).getTime());
        Reading reading42 = new Reading(4.3, new GregorianCalendar(2019, GregorianCalendar.JANUARY, 1).getTime());
        Reading reading43 = new Reading(14.8, new GregorianCalendar(2019, GregorianCalendar.JANUARY, 1).getTime());
        Reading reading44 = new Reading(8.9, new GregorianCalendar(2019, GregorianCalendar.JANUARY, 1).getTime());
        Reading reading45 = new Reading(6.1, new GregorianCalendar(2019, GregorianCalendar.JANUARY, 2, 2, 0).getTime());
        Reading reading46 = new Reading(3.2, new GregorianCalendar(2019, GregorianCalendar.JANUARY, 2, 8, 0).getTime());
        Reading reading47 = new Reading(14.1, new GregorianCalendar(2019, GregorianCalendar.JANUARY, 2, 14, 0).getTime());
        Reading reading48 = new Reading(8.3, new GregorianCalendar(2019, GregorianCalendar.JANUARY, 2, 20, 0).getTime());
        readingListISEPtemperature.addReading(reading33);
        readingListISEPtemperature.addReading(reading34);
        readingListISEPtemperature.addReading(reading35);
        readingListISEPtemperature.addReading(reading36);
        readingListISEPtemperature.addReading(reading38);
        readingListISEPtemperature.addReading(reading39);
        readingListISEPtemperature.addReading(reading40);
        readingListISEPtemperature.addReading(reading41);
        readingListISEPtemperature.addReading(reading42);
        readingListISEPtemperature.addReading(reading43);
        readingListISEPtemperature.addReading(reading44);
        readingListISEPtemperature.addReading(reading45);
        readingListISEPtemperature.addReading(reading37);
        readingListISEPtemperature.addReading(reading46);
        readingListISEPtemperature.addReading(reading47);
        readingListISEPtemperature.addReading(reading48);

        // Sensors

        Sensor sensorRoom109 = new Sensor("Temperature B109", new TypeSensor(temperature, "ºC"), new Local(0, 0, 0), new GregorianCalendar(2018, 10, 16).getTime());
        sensorRoom109.setReadingList(readingListSensorRoom109);
        Sensor sensorRainfallISEP = new Sensor("Meteo station ISEP - rainfall", new TypeSensor("rainfall", "mm"), new Local(41.179230, -8.606409, 125), new GregorianCalendar(2016, 11, 15).getTime());
        sensorRainfallISEP.setReadingList(readingListISEPRainfall);
        Sensor sensorTemperatureISEP = new Sensor("Meteo station ISEP - temperature", new TypeSensor(temperature, "ºC"), new Local(41.179230, -8.606409, 125), new GregorianCalendar(2016, 11, 15).getTime());
        sensorTemperatureISEP.setReadingList(readingListISEPtemperature);

        // Sensor Lists
        SensorList room109SensorList = new SensorList();
        room109SensorList.addSensor(sensorRoom109);
        roomISEP2.setRoomSensorList(room109SensorList);

        SensorList isepSensorList = new SensorList();
        isepSensorList.addSensor(sensorRainfallISEP);
        isepSensorList.addSensor(sensorTemperatureISEP);
        isep.setSensorList(isepSensorList);
        SensorList test = new SensorList();
        roomISEP1.setRoomSensorList(test);
        roomISEP3.setRoomSensorList(test);
        porto.setSensorList(test);

        // Energy Grid

        EnergyGrid mainGrid = new EnergyGrid();
        mainGrid.setNominalPower(0);
        mainGrid.setName("main grid");
        EnergyGridList energyGridListIsep = new EnergyGridList();
        energyGridListIsep.addGrid(mainGrid);
        mainGrid.setRoomList(gridRoomList);
        edificioB.setEGList(energyGridListIsep);


        // Type  Area List

        TypeAreaList mTypeAreaList = new TypeAreaList();
        TypeArea typeAreaA = new TypeArea("city");
        TypeArea typeAreaB = new TypeArea("country");
        mTypeAreaList.addTypeArea(typeAreaA);
        mTypeAreaList.addTypeArea(typeAreaB);

        // Sensor Type List


        //Devices
        String pathToFridge = edificioB.getDeviceTypePathToClassById("Fridge");
        String pathToWaterHeater = edificioB.getDeviceTypePathToClassById("WaterHeater");
        String pathToDishwasher = edificioB.getDeviceTypePathToClassById("Dishwasher");
        String pathToWashingMachine = edificioB.getDeviceTypePathToClassById("WashingMachine");
        String pathToLamp = edificioB.getDeviceTypePathToClassById("Lamp");

        //Device WashingMachine


        Program program1 = new Program("programa", 2, 2);
        ProgramList listProgram = new ProgramList();
        Program program2 = new Program("programa", 2, 2);
        listProgram.addProgram(program1);
        listProgram.addProgram(program2);
        Device device1 = new Device("washingMachine", 23.0, pathToWashingMachine);
        device1.setAttributeValue("capacity", 100D);
        Device device2 = new Device("FridgeOne", 4, pathToFridge);
        device2.setAttributeValue("freezerCapacity", 2D);
        device2.setAttributeValue("refrigeratorCapacity", 2D);
        device2.setAttributeValue("annualEnergyConsumption", 45D);
        Device device3 = new Device("WH Main", 3, pathToWaterHeater);
        device3.setAttributeValue("volumeOfWater", 500D);
        device3.setAttributeValue("hotWaterTemperature", 25D);
        device3.setAttributeValue("performanceRatio", 10D);
        Device device4 = new Device("Lamp", 4, pathToLamp);
        device4.setAttributeValue("luminousFlux", 23D);
        DeviceList listDevices = new DeviceList();
        listDevices.addDevice(device1);
        listDevices.addDevice(device2);
        listDevices.addDevice(device3);
        listDevices.addDevice(device4);
        roomISEP2.setDeviceList(listDevices);
        Device device5 = new Device("FridgeOne1", 4, pathToFridge);
        device5.setAttributeValue("freezerCapacity", 1D);
        device5.setAttributeValue("refrigeratorCapacity", 1D);
        device5.setAttributeValue("annualEnergyConsumption", 100D);
        Device device6 = new Device("WH Secondary", 3, pathToWaterHeater);
        device6.setAttributeValue("volumeOfWater", 500D);
        device6.setAttributeValue("hotWaterTemperature", 25D);
        device6.setAttributeValue("performanceRatio", 10D);
        DeviceList listDevices1 = new DeviceList();
        listDevices1.addDevice(device5);
        listDevices1.addDevice(device6);
        roomISEP3.setDeviceList(listDevices1);

        // *** MOCKS EXTRA ****************

        // House - With RoomList Different From EnergyGrid (In order to check attach and detach from an energy grid)
        Room room4 = new Room("room1", 1, 33, 13, 23);
        Room room5 = new Room("room2", 2, 13, 93, 23);
        Room room6 = new Room("room3", 2, 73, 43, 23);
        Room room7 = new Room("room4", 5, 63, 23, 23);
        House house4 = new House("houseRoomDifEG", "Street", "4230", portoString, new Local(23, 23, 21), isep);
        house4.addRoomToRoomList(room4);
        house4.addRoomToRoomList(room5);


        EnergyGrid energyGrid1 = new EnergyGrid();
        energyGrid1.setName("energyGrid1");
        energyGrid1.setNominalPower(1233);
        energyGrid1.addRoomToAnEnergyGrid(room6);
        energyGrid1.addRoomToAnEnergyGrid(room7);

        EnergyGridList energyGridList1 = new EnergyGridList();
        energyGridList1.addGrid(energyGrid1);
        house4.setEGList(energyGridList1);


        //DATASET_SPRINT02

        GeographicAreaList geographicAreaListSP2 = new GeographicAreaList();
        TypeArea urbanArea = new TypeArea("urban area");
        GeographicArea geographicAreaSP2 = new GeographicArea("ISEP", urbanArea, 0.249, 0.261, new Local(41.178553, -8.608035, 111));
        geographicAreaListSP2.addGeographicAreaToGeographicAreaList(geographicAreaSP2);
        TypeAreaList typeAreaListSP2 = new TypeAreaList();
        typeAreaListSP2.addTypeArea(urbanArea);
        geographicAreaSP2.setDescription("Campus do ISEP");
        Room b107SP2 = new Room("B107", 1, 7, 11, 3.5);
        Room b109SP2 = new Room("B109", 1, 7, 11, 3.5);


        List<TypeSensor> typeSensorListSP2 = new ArrayList<>();
        TypeSensor temperatureB109SP2 = new TypeSensor(temperature, "celsius");
        SensorList sensorListRoomB109SP2 = new SensorList();
        b109SP2.setRoomSensorList(sensorListRoomB109SP2);
        Sensor sensorTemperatureB109SP2 = new Sensor("Temperature B109", temperatureB109SP2, new GregorianCalendar(2018, 10, 15).getTime());
        sensorListRoomB109SP2.addSensor(sensorTemperatureB109SP2);
        ReadingList readingListSensorTemperatureB109SP2 = new ReadingList();
        sensorTemperatureB109SP2.setReadingList(readingListSensorTemperatureB109SP2);
        Reading reading1SensorTemperatureB109SP2 = new Reading(14, new GregorianCalendar(2018, 11, 30, 2, 0).getTime());
        Reading reading2SensorTemperatureB109SP2 = new Reading(13.7, new GregorianCalendar(2018, 11, 30, 8, 0).getTime());
        Reading reading3SensorTemperatureB109SP2 = new Reading(16.5, new GregorianCalendar(2018, 11, 30, 14, 0).getTime());
        Reading reading4SensorTemperatureB109SP2 = new Reading(15.1, new GregorianCalendar(2018, 11, 30, 20, 0).getTime());
        Reading reading5SensorTemperatureB109SP2 = new Reading(13.8, new GregorianCalendar(2018, 11, 31, 2, 0).getTime());
        Reading reading6SensorTemperatureB109SP2 = new Reading(13.3, new GregorianCalendar(2018, 11, 31, 8, 0).getTime());
        Reading reading7SensorTemperatureB109SP2 = new Reading(15.5, new GregorianCalendar(2018, 11, 31, 14, 0).getTime());
        Reading reading8SensorTemperatureB109SP2 = new Reading(14.2, new GregorianCalendar(2018, 11, 31, 20, 0).getTime());
        Reading reading9SensorTemperatureB109SP2 = new Reading(12.5, new GregorianCalendar(2019, 0, 1, 2, 0).getTime());
        Reading reading10SensorTemperatureB109SP2 = new Reading(12.4, new GregorianCalendar(2019, 0, 1, 8, 0).getTime());
        Reading reading11SensorTemperatureB109SP2 = new Reading(13.8, new GregorianCalendar(2019, 0, 1, 14, 0).getTime());
        Reading reading12SensorTemperatureB109SP2 = new Reading(12.9, new GregorianCalendar(2019, 0, 1, 20, 0).getTime());
        Reading reading13SensorTemperatureB109SP2 = new Reading(11.5, new GregorianCalendar(2019, 0, 2, 2, 0).getTime());
        Reading reading14SensorTemperatureB109SP2 = new Reading(11.2, new GregorianCalendar(2019, 0, 2, 8, 0).getTime());
        Reading reading15SensorTemperatureB109SP2 = new Reading(13.5, new GregorianCalendar(2019, 0, 2, 14, 0).getTime());
        Reading reading16SensorTemperatureB109SP2 = new Reading(12.8, new GregorianCalendar(2019, 0, 2, 20, 0).getTime());
        readingListSensorTemperatureB109SP2.addReading(reading1SensorTemperatureB109SP2);
        readingListSensorTemperatureB109SP2.addReading(reading2SensorTemperatureB109SP2);
        readingListSensorTemperatureB109SP2.addReading(reading3SensorTemperatureB109SP2);
        readingListSensorTemperatureB109SP2.addReading(reading4SensorTemperatureB109SP2);
        readingListSensorTemperatureB109SP2.addReading(reading5SensorTemperatureB109SP2);
        readingListSensorTemperatureB109SP2.addReading(reading6SensorTemperatureB109SP2);
        readingListSensorTemperatureB109SP2.addReading(reading7SensorTemperatureB109SP2);
        readingListSensorTemperatureB109SP2.addReading(reading8SensorTemperatureB109SP2);
        readingListSensorTemperatureB109SP2.addReading(reading9SensorTemperatureB109SP2);
        readingListSensorTemperatureB109SP2.addReading(reading10SensorTemperatureB109SP2);
        readingListSensorTemperatureB109SP2.addReading(reading11SensorTemperatureB109SP2);
        readingListSensorTemperatureB109SP2.addReading(reading12SensorTemperatureB109SP2);
        readingListSensorTemperatureB109SP2.addReading(reading13SensorTemperatureB109SP2);
        readingListSensorTemperatureB109SP2.addReading(reading14SensorTemperatureB109SP2);
        readingListSensorTemperatureB109SP2.addReading(reading15SensorTemperatureB109SP2);
        readingListSensorTemperatureB109SP2.addReading(reading16SensorTemperatureB109SP2);


        TypeSensor humidityB109 = new TypeSensor("humidity", "g/m3");
        typeSensorListSP2.add(humidityB109);
        typeSensorListSP2.add(temperatureB109SP2);
        Sensor sensorHumidityB109SP2 = new Sensor("Humidity B109", humidityB109, new GregorianCalendar(2018, 11, 22).getTime());
        sensorListRoomB109SP2.addSensor(sensorHumidityB109SP2);
        ReadingList readingListSensorHumidityB109SP2 = new ReadingList();
        sensorHumidityB109SP2.setReadingList(readingListSensorHumidityB109SP2);
        Reading reading1SensorHumidityB109SP2 = new Reading(84, new GregorianCalendar(2018, 11, 30, 2, 0).getTime());
        Reading reading2SensorHumidityB109SP2 = new Reading(85.7, new GregorianCalendar(2018, 11, 30, 8, 0).getTime());
        Reading reading3SensorHumidityB109SP2 = new Reading(76.5, new GregorianCalendar(2018, 11, 30, 14, 0).getTime());
        Reading reading4SensorHumidityB109SP2 = new Reading(78.1, new GregorianCalendar(2018, 11, 30, 20, 0).getTime());
        Reading reading5SensorHumidityB109SP2 = new Reading(83.8, new GregorianCalendar(2018, 11, 31, 2, 0).getTime());
        Reading reading6SensorHumidityB109SP2 = new Reading(83.9, new GregorianCalendar(2018, 11, 31, 8, 0).getTime());
        Reading reading7SensorHumidityB109SP2 = new Reading(75.5, new GregorianCalendar(2018, 11, 31, 14, 0).getTime());
        Reading reading8SensorHumidityB109SP2 = new Reading(77.2, new GregorianCalendar(2018, 11, 31, 20, 0).getTime());
        Reading reading9SensorHumidityB109SP2 = new Reading(82.5, new GregorianCalendar(2019, 0, 1, 2, 0).getTime());
        Reading reading10SensorHumidityB109SP2 = new Reading(82.4, new GregorianCalendar(2019, 0, 1, 8, 0).getTime());
        Reading reading11SensorHumidityB109SP2 = new Reading(73.8, new GregorianCalendar(2019, 0, 1, 14, 0).getTime());
        Reading reading12SensorHumidityB109SP2 = new Reading(72.9, new GregorianCalendar(2019, 0, 1, 20, 0).getTime());
        Reading reading13SensorHumidityB109SP2 = new Reading(80.5, new GregorianCalendar(2019, 0, 2, 2, 0).getTime());
        Reading reading14SensorHumidityB109SP2 = new Reading(79.2, new GregorianCalendar(2019, 0, 2, 8, 0).getTime());
        Reading reading15SensorHumidityB109SP2 = new Reading(71.5, new GregorianCalendar(2019, 0, 2, 14, 0).getTime());
        Reading reading16SensorHumidityB109SP2 = new Reading(72.8, new GregorianCalendar(2019, 0, 2, 20, 0).getTime());
        readingListSensorHumidityB109SP2.addReading(reading1SensorHumidityB109SP2);
        readingListSensorHumidityB109SP2.addReading(reading2SensorHumidityB109SP2);
        readingListSensorHumidityB109SP2.addReading(reading3SensorHumidityB109SP2);
        readingListSensorHumidityB109SP2.addReading(reading4SensorHumidityB109SP2);
        readingListSensorHumidityB109SP2.addReading(reading5SensorHumidityB109SP2);
        readingListSensorHumidityB109SP2.addReading(reading6SensorHumidityB109SP2);
        readingListSensorHumidityB109SP2.addReading(reading7SensorHumidityB109SP2);
        readingListSensorHumidityB109SP2.addReading(reading8SensorHumidityB109SP2);
        readingListSensorHumidityB109SP2.addReading(reading9SensorHumidityB109SP2);
        readingListSensorHumidityB109SP2.addReading(reading10SensorHumidityB109SP2);
        readingListSensorHumidityB109SP2.addReading(reading11SensorHumidityB109SP2);
        readingListSensorHumidityB109SP2.addReading(reading12SensorHumidityB109SP2);
        readingListSensorHumidityB109SP2.addReading(reading13SensorHumidityB109SP2);
        readingListSensorHumidityB109SP2.addReading(reading14SensorHumidityB109SP2);
        readingListSensorHumidityB109SP2.addReading(reading15SensorHumidityB109SP2);
        readingListSensorHumidityB109SP2.addReading(reading16SensorHumidityB109SP2);

        DeviceList deviceListRoomB109SP2 = new DeviceList();
        Device ehwB109SP2 = new Device("EHW B109", 1.5, pathToWaterHeater);
        ehwB109SP2.setAttributeValue("volumeOfWater", 100D);
        ehwB109SP2.setAttributeValue("hotWaterTemperature", 55D);
        ehwB109SP2.setAttributeValue("performanceRatio", 0.91D);

        Device dishWasherB109SP2 = new Device("Dishwasher B109", 1.5, pathToDishwasher);
        dishWasherB109SP2.setAttributeValue("capacity", 2D);
        ProgramList programListDishWasherB109SP2 = dishWasherB109SP2.getProgramList();
        Program glassesB109 = new Program("Glasses", 0, 0.9);
        Program ecoB109 = new Program("Eco", 0, 1.3);
        Program ecoTurboB109 = new Program("Eco Turbo", 0, 1.7);
        Program dishesB109 = new Program("Dishes", 0, 2.1);
        programListDishWasherB109SP2.addProgram(glassesB109);
        programListDishWasherB109SP2.addProgram(ecoB109);
        programListDishWasherB109SP2.addProgram(ecoTurboB109);
        programListDishWasherB109SP2.addProgram(dishesB109);

        Device washingMachineB109SP2 = new Device("Washing Machine B109", 2.5, pathToWashingMachine);
        washingMachineB109SP2.setAttributeValue("capacity", 0D);
        ProgramList programListWashingMachineB109SP2 = washingMachineB109SP2.getProgramList();
        Program wool = new Program("Wool", 0, 0.9);
        Program fast = new Program("Fast", 0, 1.3);
        Program fastPlus = new Program("Fast Plus", 0, 1.7);
        Program synthetic30 = new Program("Synthetic 30º", 0, 2.1);
        programListWashingMachineB109SP2.addProgram(wool);
        programListWashingMachineB109SP2.addProgram(fast);
        programListWashingMachineB109SP2.addProgram(fastPlus);
        programListWashingMachineB109SP2.addProgram(synthetic30);
        deviceListRoomB109SP2.addDevice(ehwB109SP2);
        deviceListRoomB109SP2.addDevice(dishWasherB109SP2);
        deviceListRoomB109SP2.addDevice(washingMachineB109SP2);
        b109SP2.setDeviceList(deviceListRoomB109SP2);

        Room roomB106SP2 = new Room("B106", 1, 7, 13, 3.5);
        DeviceList deviceListRoomB106SP2 = new DeviceList();
        Device ehwB106SP2 = new Device("EHW B106", 2.2, pathToWaterHeater);
        ehwB106SP2.setAttributeValue("volumeOfWater", 150D);
        ehwB106SP2.setAttributeValue("hotWaterTemperature", 55D);
        ehwB106SP2.setAttributeValue("performanceRatio", 0.92D);

        Device dishWasherB106SP2 = new Device("Dishwasher B106", 1.4, pathToDishwasher);
        dishWasherB106SP2.setAttributeValue("capacity", 0D);
        ProgramList programDishWasherB106SP2 = dishWasherB106SP2.getProgramList();
        Program glassesB106 = new Program("Glasses", 0, 0.8);
        Program lightB106 = new Program("Light", 0, 1.3);
        Program lightTurboB106 = new Program("Light Turbo", 0, 1.9);
        Program dishesB106 = new Program("Dishes", 0, 2.3);
        programDishWasherB106SP2.addProgram(glassesB106);
        programDishWasherB106SP2.addProgram(lightB106);
        programDishWasherB106SP2.addProgram(lightTurboB106);
        programDishWasherB106SP2.addProgram(dishesB106);
        deviceListRoomB106SP2.addDevice(ehwB106SP2);
        deviceListRoomB106SP2.addDevice(dishWasherB106SP2);
        roomB106SP2.setDeviceList(deviceListRoomB106SP2);

        TypeSensor typeSensorRainFall = new TypeSensor("rainfall", "l/m2");
        SensorList areaSensorSP2 = new SensorList();
        Sensor meteoStationHumiditySP2 = new Sensor("Meteo station ISEP - rainfall", typeSensorRainFall, new Local(41.179230, -8.606409, 125), new GregorianCalendar(2016, 11, 15).getTime());
        ReadingList readingListMeteoStationSP2 = new ReadingList();
        Reading reading1MeteoSationSP2 = new Reading(0.5, new GregorianCalendar(2018, 11, 29).getTime());
        Reading reading2MeteoSationSP2 = new Reading(1.2, new GregorianCalendar(2018, 11, 30).getTime());
        Reading reading3MeteoSationSP2 = new Reading(1.5, new GregorianCalendar(2018, 11, 31).getTime());
        Reading reading4MeteoSationSP2 = new Reading(0.3, new GregorianCalendar(2019, 0, 1).getTime());
        Reading reading5MeteoSationSP2 = new Reading(0.0, new GregorianCalendar(2019, 0, 2).getTime());
        Reading reading6MeteoSationSP2 = new Reading(0.0, new GregorianCalendar(2019, 0, 2).getTime());
        Reading reading7MeteoSationSP2 = new Reading(0.0, new GregorianCalendar(2019, 0, 3).getTime());
        readingListMeteoStationSP2.addReading(reading1MeteoSationSP2);
        readingListMeteoStationSP2.addReading(reading2MeteoSationSP2);
        readingListMeteoStationSP2.addReading(reading3MeteoSationSP2);
        readingListMeteoStationSP2.addReading(reading4MeteoSationSP2);
        readingListMeteoStationSP2.addReading(reading5MeteoSationSP2);
        readingListMeteoStationSP2.addReading(reading6MeteoSationSP2);
        readingListMeteoStationSP2.addReading(reading7MeteoSationSP2);
        meteoStationHumiditySP2.setReadingList(readingListMeteoStationSP2);
        areaSensorSP2.addSensor(meteoStationHumiditySP2);

        TypeSensor typeSensorMeteoStation = new TypeSensor(temperature, "celsius");
        Sensor meteoStationTemperatureSP2 = new Sensor("Meteo station ISEP - temperature", typeSensorMeteoStation, new Local(41.179230, -8.606409, 125), new GregorianCalendar(2016, 11, 15).getTime());
        ReadingList readingListMeteoStationTemperatureSP2 = new ReadingList();
        Reading reading1MeteoSationTSP2 = new Reading(8, new GregorianCalendar(2018, 11, 30, 2, 0).getTime());
        Reading reading2MeteoSationTSP2 = new Reading(6.9, new GregorianCalendar(2018, 11, 30, 8, 0).getTime());
        Reading reading3MeteoSationTSP2 = new Reading(16.5, new GregorianCalendar(2018, 11, 30, 14, 0).getTime());
        Reading reading4MeteoSationTSP2 = new Reading(11.2, new GregorianCalendar(2018, 11, 30, 20, 0).getTime());
        Reading reading5MeteoSationTSP2 = new Reading(7.2, new GregorianCalendar(2018, 11, 31, 2, 0).getTime());
        Reading reading6MeteoSationTSP2 = new Reading(5.3, new GregorianCalendar(2018, 11, 31, 8, 0).getTime());
        Reading reading7MeteoSationTSP2 = new Reading(15.1, new GregorianCalendar(2018, 11, 31, 14, 0).getTime());
        Reading reading8MeteoSationTSP2 = new Reading(9.2, new GregorianCalendar(2018, 11, 31, 20, 0).getTime());
        Reading reading9MeteoSationTSP2 = new Reading(6.5, new GregorianCalendar(2019, 0, 1, 2, 0).getTime());
        Reading reading10MeteoSationTSP2 = new Reading(4.3, new GregorianCalendar(2019, 0, 1, 8, 0).getTime());
        Reading reading11MeteoSationTSP2 = new Reading(14.8, new GregorianCalendar(2019, 0, 1, 14, 0).getTime());
        Reading reading12MeteoSationTSP2 = new Reading(8.9, new GregorianCalendar(2019, 0, 1, 20, 0).getTime());
        Reading reading13MeteoSationTSP2 = new Reading(6.1, new GregorianCalendar(2019, 0, 2, 2, 0).getTime());
        Reading reading14MeteoSationTSP2 = new Reading(3.2, new GregorianCalendar(2019, 0, 2, 8, 0).getTime());
        Reading reading15MeteoSationTSP2 = new Reading(14.1, new GregorianCalendar(2019, 0, 2, 14, 0).getTime());
        Reading reading16MeteoSationTSP2 = new Reading(8.3, new GregorianCalendar(2019, 0, 2, 20, 0).getTime());
        readingListMeteoStationTemperatureSP2.addReading(reading1MeteoSationTSP2);
        readingListMeteoStationTemperatureSP2.addReading(reading2MeteoSationTSP2);
        readingListMeteoStationTemperatureSP2.addReading(reading3MeteoSationTSP2);
        readingListMeteoStationTemperatureSP2.addReading(reading4MeteoSationTSP2);
        readingListMeteoStationTemperatureSP2.addReading(reading5MeteoSationTSP2);
        readingListMeteoStationTemperatureSP2.addReading(reading6MeteoSationTSP2);
        readingListMeteoStationTemperatureSP2.addReading(reading7MeteoSationTSP2);
        readingListMeteoStationTemperatureSP2.addReading(reading8MeteoSationTSP2);
        readingListMeteoStationTemperatureSP2.addReading(reading9MeteoSationTSP2);
        readingListMeteoStationTemperatureSP2.addReading(reading10MeteoSationTSP2);
        readingListMeteoStationTemperatureSP2.addReading(reading11MeteoSationTSP2);
        readingListMeteoStationTemperatureSP2.addReading(reading12MeteoSationTSP2);
        readingListMeteoStationTemperatureSP2.addReading(reading13MeteoSationTSP2);
        readingListMeteoStationTemperatureSP2.addReading(reading14MeteoSationTSP2);
        readingListMeteoStationTemperatureSP2.addReading(reading15MeteoSationTSP2);
        readingListMeteoStationTemperatureSP2.addReading(reading16MeteoSationTSP2);
        meteoStationTemperatureSP2.setReadingList(readingListMeteoStationTemperatureSP2);
        areaSensorSP2.addSensor(meteoStationTemperatureSP2);


        TypeArea citySP2 = new TypeArea("city");
        GeographicArea portoSP2 = new GeographicArea(portoString, citySP2, 3.30, 10.09, new Local(41.164077, -8.620802, 118));
        typeAreaListSP2.addTypeArea(citySP2);
        portoSP2.setDescription("City of Porto");
        geographicAreaListSP2.addGeographicAreaToGeographicAreaList(portoSP2);
        EnergyGrid mainGridSP2 = new EnergyGrid();
        mainGridSP2.setName("main grid");
        mainGridSP2.setNominalPower(0);
        House houseSP2 = new House("Edificio B", "Rua Dr António Bernardino de Almeida, 431", "4200-072", portoString, new Local(41.177748, -8.607745, 112), geographicAreaSP2);
        houseSP2.setMotherArea(geographicAreaSP2);
        geographicAreaSP2.setSensorList(areaSensorSP2);
        EnergyGridList mainGridList = new EnergyGridList();
        mainGridList.addGrid(mainGridSP2);
        houseSP2.setEGList(mainGridList);
        houseSP2.addRoomToRoomList(b107SP2);
        houseSP2.addRoomToRoomList(b109SP2);
        houseSP2.addRoomToRoomList(roomB106SP2);
        mainGridSP2.addRoomToAnEnergyGrid(roomB106SP2);
        mainGridSP2.addRoomToAnEnergyGrid(b107SP2);
        mainGridSP2.addRoomToAnEnergyGrid(b109SP2);

        // House - With RoomList Different From EnergyGrid (In order to check attach and detach from an energy grid)
        Room room1 = new Room("room1", 1, 33, 13, 23);
        Room room2 = new Room("room2", 2, 13, 93, 23);

        House houseTest = new House("houseRoomDifEG", "Street", "4230", "Porto", new Local(23, 23, 21), isep);
        houseTest.addRoomToRoomList(room1);
        houseTest.addRoomToRoomList(room2);


/**
 // House - Empty RoomList - Without EnergyGrid
 GeographicArea geographicArea4 = new GeographicArea();
 RoomList roomList = new RoomList();
 House house = new House("houseNoRooms", "Street", "4230", msPorto, new Local(23, 23, 21), geographicArea4, roomList);


 //Empty TypeAreaList

 TypeAreaList emptyTypeAreaList = new TypeAreaList();

 // Empty Geo Area List

 GeographicAreaList emptyGeographicalAreaList = new GeographicAreaList();

 // Geo Area List -  Geo Area without Sensor List

 GeographicAreaList geographicalAreaList1 = new GeographicAreaList();
 GeographicArea geographicArea1 = new GeographicArea("GeoAreaNoSensor", new TypeArea("City"), 23, 34, new Local(23, 23, 12));

 geographicalAreaList1.addGeographicAreaToGeographicAreaList(geographicArea1);

 // Geo Area List -  Geo Area with Empty Sensor List

 GeographicAreaList geographicalAreaList2 = new GeographicAreaList();
 GeographicArea geographicArea2 = new GeographicArea("GeoAreaEmptySensor", new TypeArea("City"), 23, 34, new Local(23, 23, 12));
 SensorList sensorList2 = new SensorList();

 geographicArea2.setSensorList(sensorList2);
 geographicalAreaList2.addGeographicAreaToGeographicAreaList(geographicArea2);

 // Geo Area List -  Geo Area with Sensor List

 GeographicAreaList geographicalAreaList3 = new GeographicAreaList();
 GeographicArea geographicArea3 = new GeographicArea("GeoAreaWithSensor", new TypeArea("City"), 23, 34, new Local(23, 23, 12));
 Sensor s3 = new Sensor("sensor", new TypeSensor("Temperatura", "Celsius"), new Local(2, 3, 4), new GregorianCalendar(2016, 11, 15).getTime());
 SensorList sensorList3 = new SensorList();
 sensorList3.addSensor(s3);

 geographicArea3.setSensorList(sensorList3);
 geographicalAreaList3.addGeographicAreaToGeographicAreaList(geographicArea3);

 // House - Empty RoomList - Without EnergyGrid
 GeographicArea geographicArea4 = new GeographicArea();
 RoomList roomList = new RoomList();
 House house = new House("houseNoRooms", "Street", "4230", msPorto, new Local(23, 23, 21), geographicArea4, roomList);

 // House - Empty RoomList - Empty EnergyGrid
 RoomList roomList1 = new RoomList();
 House house1 = new House("houseNoRoomsNoEG", "Street", "4230", msPorto, new Local(23, 23, 21), geographicArea4, roomList1);

 // House - With RoomList - Without EnergyGrid
 Room room2 = new Room("houseNoEG", 1, 23, 23, 23);
 RoomList roomList2 = new RoomList();
 roomList2.addRoom(room2);
 House house2 = new House("h", "Street", "4230", msPorto, new Local(23, 23, 21), geographicArea4, roomList2);

 // House - With RoomList - Empty EnergyGrid
 Room room3 = new Room("room3", 1, 23, 23, 23);
 RoomList roomList3 = new RoomList();
 roomList3.addRoom(room3);
 House house3 = new House("houseEmptyEG", "Street", "4230", msPorto, new Local(23, 23, 21), geographicArea4, roomList3);

 // House - With RoomList Different From EnergyGrid (In order to check attach and detach from an energy grid)
 Room room4 = new Room("room1", 1, 33, 13, 23);
 Room room5 = new Room("room2", 2, 13, 93, 23);
 Room room6 = new Room("room3", 2, 73, 43, 23);
 Room room7 = new Room("room4", 5, 63, 23, 23);
 RoomList roomList4 = new RoomList();
 roomList4.addRoom(room4);
 roomList4.addRoom(room5);
 House house4 = new House("houseRoomDifEG", "Street", "4230", msPorto, new Local(23, 23, 21), geographicArea4, roomList4);

 EnergyGrid energyGrid1 = new EnergyGrid("energyGrid1", 1233);
 energyGrid1.addRoomToAnEnergyGrid(room6);
 energyGrid1.addRoomToAnEnergyGrid(room7);

 EnergyGridList energyGridList1 = new EnergyGridList();
 energyGridList1.addGrid(energyGrid1);
 house4.setEGList(energyGridList1);


 // **************************
 // ******* < / MOCK DATA FOR TESTING PURPOSES > ******
 // **************************
 **/

        //MAIN CODE

        Scanner enterToReturnToConsole = new Scanner(System.in);
        int option;
        while (true) {

            System.out.println("\n************\n" +
                    "*** Smart Grid Menu ****\n" +
                    "**** sWitCh 2018 *****\n" +
                    "************\n");

            // Submenus Input selection

            String[] menu = {
                    " 1. Geographic Area Settings\n",
                    "2. House Settings.\n",
                    "3. Room Settings.\n",
                    "4. Sensor Settings.\n",
                    "5. Energy Grid Settings.\n",
                    "6. House Monitoring.\n",
                    "7. Energy Consumption Management.\n" +
                            " 0. Exit Application\n"};

            System.out.println("Select the task you want to do:");

            String formattedString = Arrays.toString(menu)
                    .replace(",", "")  //remove the commas
                    .replace("[", "")  //remove the right bracket
                    .replace("]", "");  //remove the left bracket

            System.out.print(formattedString);
            System.out.print("\nEnter option number:\n");
            boolean activeInput = true;

            while (activeInput) {
                InputUtils inputUtils = new InputUtils();
                UtilsUI utilsUI = new UtilsUI();
                option = inputUtils.readInputNumberAsInt();
                switch (option) {
                    case 0:
                        return;
                    case 1:
                        GASettingsUI view1 = new GASettingsUI();
                        view1.runGASettings(geographicAreaListSP2, typeAreaListSP2);
                        returnToMenu(enterToReturnToConsole);
                        activeInput = false;
                        break;
                    case 2:
                        HouseConfigurationUI houseC = new HouseConfigurationUI();
                        houseC.run(houseSP2);
                        returnToMenu(enterToReturnToConsole);
                        activeInput = false;
                        break;
                    case 3:
                        RoomConfigurationUI roomConfiguration = new RoomConfigurationUI();
                        roomConfiguration.run(houseSP2, typeSensorListSP2);
                        returnToMenu(enterToReturnToConsole);
                        activeInput = false;
                        break;
                    case 4:
                        SensorSettingsUI sensorSettings = new SensorSettingsUI();
                        sensorSettings.run(geographicAreaListSP2, typeSensorListSP2);
                        returnToMenu(enterToReturnToConsole);
                        activeInput = false;
                        break;
                    case 5:
                        EnergyGridSettingsUI energyGridSettings = new EnergyGridSettingsUI();
                        energyGridSettings.run(houseSP2);
                        returnToMenu(enterToReturnToConsole);
                        activeInput = false;
                        break;
                    case 6:
                        HouseMonitoringUI houseM = new HouseMonitoringUI();
                        houseM.run(edificioB);
                        returnToMenu(enterToReturnToConsole);
                        activeInput = false;
                        break;
                    case 7:
                        EnergyConsumptionUI energyConsumptionUI = new EnergyConsumptionUI();
                        energyConsumptionUI.run(houseSP2);
                        returnToMenu(enterToReturnToConsole);
                        activeInput = false;
                        break;
                    default:
                        System.out.println(utilsUI.invalidOption);
                        break;
                }
            }
        }
    }

    private static void returnToMenu(Scanner scanner) {
        String pressEnter = "\nPress ENTER to return.";
        System.out.println(pressEnter);
        scanner.nextLine();
    }
}