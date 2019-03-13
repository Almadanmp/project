package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.model.device.Dishwasher;
import pt.ipp.isep.dei.project.model.device.WashingMachine;
import pt.ipp.isep.dei.project.model.device.WaterHeater;
import pt.ipp.isep.dei.project.model.device.devicespecs.DishwasherSpec;
import pt.ipp.isep.dei.project.model.device.devicespecs.WashingMachineSpec;
import pt.ipp.isep.dei.project.model.device.devicespecs.WaterHeaterSpec;
import pt.ipp.isep.dei.project.model.device.log.Log;
import pt.ipp.isep.dei.project.model.device.program.FixedTimeProgram;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * MOCK DATA FOR TESTING PURPOSES
 */

class MockUI {


    private static final String GLASSES = "Glasses";
    private static final String DISHES = "Dishes";

    private TypeAreaList typeAreaList;
    private TypeSensorList typeSensorList;
    private GeographicAreaList geoAreaList;

    //GLOBAL VARIABLES TO BE USED BY CLASS METHODS
    private GeographicArea geoAreaIsep;

    private TypeSensor temperatureST;
    private TypeSensor humidityST;

//Getter Methods to use on MAINUI

    TypeAreaList getTypeAreaList() {
        return typeAreaList;
    }

    TypeSensorList getTypeSensorList() {
        return typeSensorList;
    }

    GeographicAreaList getGeoAreaList() {
        return geoAreaList;
    }

    void initializeMockUI() {
        this.typeAreaList = mockTypeAreaList();
        this.typeSensorList = mockTypeSensorList();
        this.geoAreaList = mockGeographicAreaList();
    }


    private TypeAreaList mockTypeAreaList() {
        TypeAreaList mockTypeAreaList = new TypeAreaList();
        TypeArea urbanArea = new TypeArea("Urban Area");
        TypeArea city = new TypeArea("City");
        mockTypeAreaList.addTypeArea(urbanArea);
        mockTypeAreaList.addTypeArea(city);

        return mockTypeAreaList;
    }

    private TypeSensorList mockTypeSensorList() {
        TypeSensorList mockTypeSensorList = new TypeSensorList();

        TypeSensor rainfallST = new TypeSensor("rainfall", "l/m2");
        mockTypeSensorList.add(rainfallST);

        this.temperatureST = new TypeSensor("Temperature", "º");
        mockTypeSensorList.add(this.temperatureST);

        this.humidityST = new TypeSensor("Humidity", "g/m3");
        mockTypeSensorList.add(this.humidityST);

        return mockTypeSensorList;

    }

    private GeographicAreaList mockGeographicAreaList() {
        GeographicAreaList mockGeographicAreaList = new GeographicAreaList();

        /* Now created with JSON import

        //ISEP GEOGRAPHIC AREA//
        this.geoAreaIsep = new GeographicArea("ISEP", this.urbanArea, 0.249, 0.261, new Local(41.178553, -8.608035, 111));
        mockGeographicAreaList.addGeographicArea(this.geoAreaIsep);
        Sensor rainfallIsepSensor = new Sensor("Meteo Station Isep - Rainfall", this.rainfallST, new GregorianCalendar(2016, Calendar.NOVEMBER, 15).getTime());
        rainfallIsepSensor.setLocal(new Local(41.179230, -8.606409, 125));
        createRainfallIsepSensorReadings(rainfallIsepSensor);
        geoAreaIsep.addSensor(rainfallIsepSensor);

        Sensor temperatureIsepSensor = new Sensor("Meteo Station Isep - Temperature", this.temperatureST, new GregorianCalendar(2016, Calendar.NOVEMBER, 15).getTime());
        temperatureIsepSensor.setLocal(new Local(41.179230, -8.606409, 125));
        createTemperatureIsepSensorReadings(temperatureIsepSensor);
        geoAreaIsep.addSensor(temperatureIsepSensor);

        //PORTO GEOGRAPHIC AREA//
        GeographicArea geoAreaPorto = new GeographicArea("City of Porto", this.city, 3.30, 10.09, new Local(41.164077, -8.620802, 118));
        mockGeographicAreaList.addGeographicArea(geoAreaPorto);

        */
        return mockGeographicAreaList;
    }

    House mockHouse(int gridMeteringPeriod, int deviceMeteringPeriod, List<String> deviceTypeConfig) {
        House mockHouse = new House("Edificio B", new Address("Rua Dr António Bernardino de Almeida, 431", "4200-072", "Porto"), new Local(41.177748, -8.607745, 112), this.geoAreaIsep, gridMeteringPeriod, deviceMeteringPeriod, deviceTypeConfig);
        EnergyGrid mainGrid = new EnergyGrid("Main Grid", 0);
        EnergyGridList mockEGList = new EnergyGridList();
        mockEGList.addGrid(mainGrid);
        mockHouse.setGridList(mockEGList);

        //ROOM B107//
        Room roomB107 = createRoomB107();
        mainGrid.addRoom(roomB107);
        mockHouse.addRoom(roomB107);

        //ROOM B109//
        Room roomB109 = createRoomB109();
        mainGrid.addRoom(roomB109);
        mockHouse.addRoom(roomB109);


        //ROOM B106//
        Room roomB106 = createRoomB106();
        mockHouse.addRoom(roomB106);
        mainGrid.addRoom(roomB106);

        return mockHouse;
    }


    //METHODS TO CREATE ROOMS
    private Room createRoomB107() {
        Room roomB107 = new Room("B107", 1, 7, 11, 3.5);
        WaterHeater wH107 = new WaterHeater(new WaterHeaterSpec());
        wH107.setName("EHW B107");
        wH107.setNominalPower(1.5);
        wH107.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 100D);
        wH107.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 55D);
        wH107.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 0.91);

        createWh107Logs(wH107);

        roomB107.addDevice(wH107);

        Dishwasher dW107 = new Dishwasher(new DishwasherSpec());
        dW107.setName("Dishwasher B107");
        dW107.setNominalPower(1.5);
        dW107.setAttributeValue(DishwasherSpec.DW_CAPACITY, 0D); // NOT SPECIFIED ON GIVEN MOCK DATA
        dW107.getProgramList().add(new FixedTimeProgram(GLASSES, 0.0, 0.9)); //duration not specified on given mock data
        dW107.getProgramList().add(new FixedTimeProgram("Eco", 0.0, 1.3));//duration not specified on given mock data
        dW107.getProgramList().add(new FixedTimeProgram("Eco Turbo", 0.0, 1.7));//duration not specified on given mock data
        dW107.getProgramList().add(new FixedTimeProgram(DISHES, 0.0, 2.1));//duration not specified on given mock data

        createDw107Logs(dW107);
        roomB107.addDevice(dW107);

        WashingMachine wM107 = new WashingMachine(new WashingMachineSpec());
        wM107.setName("Washing Machine B107");
        wM107.setNominalPower(3.5);
        wM107.setAttributeValue(WashingMachineSpec.WM_CAPACITY, 0.0D); // NOT SPECIFIED ON GIVEN MOCK DATA
        wM107.getProgramList().add(new FixedTimeProgram("Wool", 0.0, 1.1)); //duration not specified on given mock data
        wM107.getProgramList().add(new FixedTimeProgram("Fast", 0.0, 1.8)); //duration not specified on given mock data
        wM107.getProgramList().add(new FixedTimeProgram("Fast Plus", 0.0, 2.7)); //duration not specified on given mock data
        wM107.getProgramList().add(new FixedTimeProgram("Synthetic 30º", 0.0, 2.8)); //duration not specified on given mock data

        createwM107Logs(wM107);

        roomB107.addDevice(wM107);
        return roomB107;
    }

    private Room createRoomB109() {
        Room roomB109 = new Room("B109", 1, 7, 11, 3.5);

        Sensor temperatureSensorB109 = new Sensor("Temperature B109", this.temperatureST, new GregorianCalendar(2018, Calendar.NOVEMBER, 15).getTime());
        roomB109.addSensor(temperatureSensorB109);

        //      createTemperatureSensorB109Readings(temperatureSensorB109);

        Sensor humiditySensorB109 = new Sensor("Humidity B109", this.humidityST, new GregorianCalendar(2018, Calendar.NOVEMBER, 22).getTime());
        roomB109.addSensor(humiditySensorB109);

        //      createHumiditySensorB109Readings(humiditySensorB109);

        WaterHeater wH109 = new WaterHeater(new WaterHeaterSpec());
        wH109.setName("EHW B109");
        wH109.setNominalPower(2);
        wH109.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 100D);
        wH109.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 55D);
        wH109.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 0.91);
        createWh109Logs(wH109);
        roomB109.addDevice(wH109);

        Dishwasher dW109 = new Dishwasher(new DishwasherSpec());
        dW109.setName("Dishwasher B109");
        dW109.setNominalPower(1.5);
        dW109.setAttributeValue(DishwasherSpec.DW_CAPACITY, 0D); // NOT SPECIFIED ON GIVEN MOCK DATA
        dW109.getProgramList().add(new FixedTimeProgram(GLASSES, 0.0, 0.9)); //duration not specified on given mock data
        dW109.getProgramList().add(new FixedTimeProgram("Eco", 0.0, 1.3));//duration not specified on given mock data
        dW109.getProgramList().add(new FixedTimeProgram("Eco Turbo", 0.0, 1.7));//duration not specified on given mock data
        dW109.getProgramList().add(new FixedTimeProgram(DISHES, 0.0, 2.1));//duration not specified on given mock data
        roomB109.addDevice(dW109);


        WashingMachine wM109 = new WashingMachine(new WashingMachineSpec());
        wM109.setName("Washing Machine B109");
        wM109.setNominalPower(2.5);
        wM109.setAttributeValue(WashingMachineSpec.WM_CAPACITY, 0.0D); // NOT SPECIFIED ON GIVEN MOCK DATA
        wM109.getProgramList().add(new FixedTimeProgram("Wool", 0, 0.9));//duration not specified on given mock data
        wM109.getProgramList().add(new FixedTimeProgram("Fast", 0, 1.3));//duration not specified on given mock data
        wM109.getProgramList().add(new FixedTimeProgram("Fast Plus", 0, 1.7));//duration not specified on given mock data
        wM109.getProgramList().add(new FixedTimeProgram("Synthetic 30º", 0, 2.1));//duration not specified on given mock data
        createWm109Logs(wM109);
        roomB109.addDevice(wM109);

        return roomB109;
    }

    private Room createRoomB106() {
        Room roomB106 = new Room("B106", 1, 7, 13, 3.5);
        WaterHeater wH106 = new WaterHeater(new WaterHeaterSpec());
        wH106.setName("EHW B106");
        wH106.setNominalPower(2.2);
        wH106.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 150D);
        wH106.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 55D);
        wH106.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 0.92);
        roomB106.addDevice(wH106);


        Dishwasher dW106 = new Dishwasher(new DishwasherSpec());
        dW106.setName("Dishwasher B106");
        dW106.setNominalPower(1.4);
        dW106.setAttributeValue(DishwasherSpec.DW_CAPACITY, 0D); // NOT SPECIFIED ON GIVEN MOCK DATA
        dW106.getProgramList().add(new FixedTimeProgram(GLASSES, 0.0, 0.8));//duration not specified on given mock data
        dW106.getProgramList().add(new FixedTimeProgram("Light", 0.0, 1.3));//duration not specified on given mock data
        dW106.getProgramList().add(new FixedTimeProgram("Light Turbo", 0.0, 1.9));//duration not specified on given mock data
        dW106.getProgramList().add(new FixedTimeProgram(DISHES, 0.0, 2.3));//duration not specified on given mock data
        roomB106.addDevice(dW106);

        return roomB106;
    }

    //METHODS TO CREATE LOGS ON DEVICES
    private void createWh107Logs(WaterHeater wH107) {
        wH107.addLog(new Log(0.2, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 7, 45).getTime(), new GregorianCalendar(2018, Calendar.DECEMBER, 31, 7, 59).getTime()));
        wH107.addLog(new Log(0.375, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 8, 0).getTime(), new GregorianCalendar(2018, Calendar.DECEMBER, 31, 8, 14).getTime()));
        wH107.addLog(new Log(0.375, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 8, 15).getTime(), new GregorianCalendar(2018, Calendar.DECEMBER, 31, 8, 29).getTime()));
        wH107.addLog(new Log(0.375, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 8, 30).getTime(), new GregorianCalendar(2018, Calendar.DECEMBER, 31, 8, 44).getTime()));
        wH107.addLog(new Log(0.375, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 8, 45).getTime(), new GregorianCalendar(2018, Calendar.DECEMBER, 31, 8, 59).getTime()));
        wH107.addLog(new Log(0.25, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 9, 0).getTime(), new GregorianCalendar(2018, Calendar.DECEMBER, 31, 9, 14).getTime()));
        wH107.addLog(new Log(0.2, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 10, 15).getTime(), new GregorianCalendar(2018, Calendar.DECEMBER, 31, 10, 29).getTime()));
        wH107.addLog(new Log(0.2, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 10, 30).getTime(), new GregorianCalendar(2018, Calendar.DECEMBER, 31, 10, 44).getTime()));
        wH107.addLog(new Log(0.2, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 11, 45).getTime(), new GregorianCalendar(2018, Calendar.DECEMBER, 31, 11, 59).getTime()));
        wH107.addLog(new Log(0.375, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 12, 0).getTime(), new GregorianCalendar(2018, Calendar.DECEMBER, 31, 12, 14).getTime()));
        wH107.addLog(new Log(0.375, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 12, 15).getTime(), new GregorianCalendar(2018, Calendar.DECEMBER, 31, 12, 29).getTime()));
        wH107.addLog(new Log(0.375, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 12, 30).getTime(), new GregorianCalendar(2018, Calendar.DECEMBER, 31, 12, 44).getTime()));
        wH107.addLog(new Log(0.375, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 12, 45).getTime(), new GregorianCalendar(2018, Calendar.DECEMBER, 31, 12, 59).getTime()));
        wH107.addLog(new Log(0.2, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 13, 0).getTime(), new GregorianCalendar(2018, Calendar.DECEMBER, 31, 13, 14).getTime()));
        wH107.addLog(new Log(0.1, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 20, 0).getTime(), new GregorianCalendar(2018, Calendar.DECEMBER, 31, 20, 14).getTime()));
        wH107.addLog(new Log(0.375, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 20, 15).getTime(), new GregorianCalendar(2018, Calendar.DECEMBER, 31, 20, 29).getTime()));
        wH107.addLog(new Log(0.375, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 20, 30).getTime(), new GregorianCalendar(2018, Calendar.DECEMBER, 31, 20, 44).getTime()));
        wH107.addLog(new Log(0.375, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 20, 45).getTime(), new GregorianCalendar(2018, Calendar.DECEMBER, 31, 20, 59).getTime()));
        wH107.addLog(new Log(0.15, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 21, 0).getTime(), new GregorianCalendar(2018, Calendar.DECEMBER, 31, 21, 14).getTime()));

    }

    private void createDw107Logs(Dishwasher dW107) {
        dW107.addLog(new Log(0.2, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 12, 45).getTime(), new GregorianCalendar(2018, Calendar.DECEMBER, 31, 12, 59).getTime()));
        dW107.addLog(new Log(0.3, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 13, 0).getTime(), new GregorianCalendar(2018, Calendar.DECEMBER, 31, 13, 14).getTime()));
        dW107.addLog(new Log(0.2, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 13, 15).getTime(), new GregorianCalendar(2018, Calendar.DECEMBER, 31, 13, 29).getTime()));
        dW107.addLog(new Log(0.2, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 13, 30).getTime(), new GregorianCalendar(2018, Calendar.DECEMBER, 31, 13, 44).getTime()));
        dW107.addLog(new Log(0.2, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 20, 0).getTime(), new GregorianCalendar(2018, Calendar.DECEMBER, 31, 20, 14).getTime()));
        dW107.addLog(new Log(0.1, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 21, 0).getTime(), new GregorianCalendar(2018, Calendar.DECEMBER, 31, 21, 14).getTime()));
        dW107.addLog(new Log(0.1, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 21, 15).getTime(), new GregorianCalendar(2018, Calendar.DECEMBER, 31, 21, 29).getTime()));
        dW107.addLog(new Log(0.375, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 21, 30).getTime(), new GregorianCalendar(2018, Calendar.DECEMBER, 31, 21, 44).getTime()));
        dW107.addLog(new Log(0.375, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 21, 45).getTime(), new GregorianCalendar(2018, Calendar.DECEMBER, 31, 21, 59).getTime()));
        dW107.addLog(new Log(0.2, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 22, 0).getTime(), new GregorianCalendar(2018, Calendar.DECEMBER, 31, 22, 14).getTime()));
    }

    private void createwM107Logs(WashingMachine wM107) {
        wM107.addLog(new Log(0.1, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 10, 15).getTime(), new GregorianCalendar(2018, Calendar.DECEMBER, 31, 10, 29).getTime()));
        wM107.addLog(new Log(0.375, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 10, 30).getTime(), new GregorianCalendar(2018, Calendar.DECEMBER, 31, 10, 44).getTime()));
        wM107.addLog(new Log(0.375, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 10, 45).getTime(), new GregorianCalendar(2018, Calendar.DECEMBER, 31, 10, 59).getTime()));
        wM107.addLog(new Log(0.2, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 11, 0).getTime(), new GregorianCalendar(2018, Calendar.DECEMBER, 31, 11, 14).getTime()));
    }

    private void createWh109Logs(WaterHeater wH109) {
        wH109.addLog(new Log(0.2, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 7, 45).getTime(), new GregorianCalendar(2018, Calendar.DECEMBER, 31, 7, 59).getTime()));
        wH109.addLog(new Log(0.5, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 8, 0).getTime(), new GregorianCalendar(2018, Calendar.DECEMBER, 31, 8, 14).getTime()));
        wH109.addLog(new Log(0.5, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 8, 15).getTime(), new GregorianCalendar(2018, Calendar.DECEMBER, 31, 8, 29).getTime()));
        wH109.addLog(new Log(0.5, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 8, 30).getTime(), new GregorianCalendar(2018, Calendar.DECEMBER, 31, 8, 44).getTime()));
        wH109.addLog(new Log(0.5, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 8, 45).getTime(), new GregorianCalendar(2018, Calendar.DECEMBER, 31, 8, 59).getTime()));
        wH109.addLog(new Log(0.25, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 9, 0).getTime(), new GregorianCalendar(2018, Calendar.DECEMBER, 31, 9, 14).getTime()));
        wH109.addLog(new Log(0.2, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 10, 15).getTime(), new GregorianCalendar(2018, Calendar.DECEMBER, 31, 10, 29).getTime()));
        wH109.addLog(new Log(0.2, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 10, 30).getTime(), new GregorianCalendar(2018, Calendar.DECEMBER, 31, 10, 44).getTime()));
        wH109.addLog(new Log(0.2, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 11, 45).getTime(), new GregorianCalendar(2018, Calendar.DECEMBER, 31, 11, 59).getTime()));
        wH109.addLog(new Log(0.5, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 12, 0).getTime(), new GregorianCalendar(2018, Calendar.DECEMBER, 31, 12, 14).getTime()));
        wH109.addLog(new Log(0.5, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 12, 15).getTime(), new GregorianCalendar(2018, Calendar.DECEMBER, 31, 12, 29).getTime()));
        wH109.addLog(new Log(0.5, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 12, 30).getTime(), new GregorianCalendar(2018, Calendar.DECEMBER, 31, 12, 44).getTime()));
        wH109.addLog(new Log(0.5, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 12, 45).getTime(), new GregorianCalendar(2018, Calendar.DECEMBER, 31, 12, 59).getTime()));
        wH109.addLog(new Log(0.2, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 13, 0).getTime(), new GregorianCalendar(2018, Calendar.DECEMBER, 31, 13, 14).getTime()));
        wH109.addLog(new Log(0.1, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 20, 0).getTime(), new GregorianCalendar(2018, Calendar.DECEMBER, 31, 20, 14).getTime()));
        wH109.addLog(new Log(0.5, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 20, 15).getTime(), new GregorianCalendar(2018, Calendar.DECEMBER, 31, 20, 29).getTime()));
        wH109.addLog(new Log(0.5, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 20, 30).getTime(), new GregorianCalendar(2018, Calendar.DECEMBER, 31, 20, 44).getTime()));
        wH109.addLog(new Log(0.5, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 20, 45).getTime(), new GregorianCalendar(2018, Calendar.DECEMBER, 31, 20, 59).getTime()));
        wH109.addLog(new Log(0.15, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 21, 0).getTime(), new GregorianCalendar(2018, Calendar.DECEMBER, 31, 21, 14).getTime()));
    }

    private void createWm109Logs(WashingMachine wM109) {
        wM109.addLog(new Log(0.4, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 10, 0).getTime(), new GregorianCalendar(2018, Calendar.DECEMBER, 31, 10, 14).getTime()));
        wM109.addLog(new Log(0.2, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 10, 15).getTime(), new GregorianCalendar(2018, Calendar.DECEMBER, 31, 10, 29).getTime()));
        wM109.addLog(new Log(0.2, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 10, 30).getTime(), new GregorianCalendar(2018, Calendar.DECEMBER, 31, 10, 44).getTime()));
        wM109.addLog(new Log(0.25, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 10, 45).getTime(), new GregorianCalendar(2018, Calendar.DECEMBER, 31, 10, 59).getTime()));
        wM109.addLog(new Log(0.25, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 11, 0).getTime(), new GregorianCalendar(2018, Calendar.DECEMBER, 31, 11, 14).getTime()));

    }

    //METHODS TO CREATE READINGS ON SENSORS

    /* Now created with CSV import.

    private void createRainfallIsepSensorReadings(Sensor rainfallIsepSensor) {
        rainfallIsepSensor.addReading(new Reading(0.5, new GregorianCalendar(2018, Calendar.DECEMBER, 29).getTime()));
        rainfallIsepSensor.addReading(new Reading(1.2, new GregorianCalendar(2018, Calendar.DECEMBER, 30).getTime()));
        rainfallIsepSensor.addReading(new Reading(1.5, new GregorianCalendar(2018, Calendar.DECEMBER, 31).getTime()));
        rainfallIsepSensor.addReading(new Reading(0.3, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime()));
        rainfallIsepSensor.addReading(new Reading(0.0, new GregorianCalendar(2019, Calendar.JANUARY, 2).getTime()));
        rainfallIsepSensor.addReading(new Reading(0.0, new GregorianCalendar(2019, Calendar.JANUARY, 2).getTime()));
        rainfallIsepSensor.addReading(new Reading(0.0, new GregorianCalendar(2019, Calendar.JANUARY, 3).getTime()));
    }

    private void createTemperatureIsepSensorReadings(Sensor temperatureIsepSensor) {
        temperatureIsepSensor.addReading(new Reading(8, new GregorianCalendar(2018, Calendar.DECEMBER, 30, 2, 0).getTime()));
        temperatureIsepSensor.addReading(new Reading(11.2, new GregorianCalendar(2018, Calendar.DECEMBER, 30, 20, 0).getTime()));
        temperatureIsepSensor.addReading(new Reading(7.2, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 2, 0).getTime()));
        temperatureIsepSensor.addReading(new Reading(5.3, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 8, 0).getTime()));
        temperatureIsepSensor.addReading(new Reading(15.1, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 14, 0).getTime()));
        temperatureIsepSensor.addReading(new Reading(9.2, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 20, 0).getTime()));
        temperatureIsepSensor.addReading(new Reading(6.5, new GregorianCalendar(2019, Calendar.JANUARY, 1, 2, 0).getTime()));
        temperatureIsepSensor.addReading(new Reading(4.3, new GregorianCalendar(2019, Calendar.JANUARY, 1, 8, 0).getTime()));
        temperatureIsepSensor.addReading(new Reading(14.8, new GregorianCalendar(2019, Calendar.JANUARY, 1, 14, 0).getTime()));
        temperatureIsepSensor.addReading(new Reading(8.9, new GregorianCalendar(2019, Calendar.JANUARY, 1, 20, 0).getTime()));
        temperatureIsepSensor.addReading(new Reading(6.1, new GregorianCalendar(2019, Calendar.JANUARY, 2, 2, 0).getTime()));
        temperatureIsepSensor.addReading(new Reading(3.2, new GregorianCalendar(2019, Calendar.JANUARY, 2, 8, 0).getTime()));
        temperatureIsepSensor.addReading(new Reading(14.1, new GregorianCalendar(2019, Calendar.JANUARY, 2, 14, 0).getTime()));
        temperatureIsepSensor.addReading(new Reading(8.3, new GregorianCalendar(2019, Calendar.JANUARY, 2, 20, 0).getTime()));
    }

    private void createTemperatureSensorB109Readings(Sensor temperatureSensorB109) {
        temperatureSensorB109.addReading(new Reading(14, new GregorianCalendar(2018, Calendar.DECEMBER, 30, 2, 0).getTime()));
        temperatureSensorB109.addReading(new Reading(13.7, new GregorianCalendar(2018, Calendar.DECEMBER, 30, 8, 0).getTime()));
        temperatureSensorB109.addReading(new Reading(16.5, new GregorianCalendar(2018, Calendar.DECEMBER, 30, 14, 0).getTime()));
        temperatureSensorB109.addReading(new Reading(15.1, new GregorianCalendar(2018, Calendar.DECEMBER, 30, 20, 0).getTime()));
        temperatureSensorB109.addReading(new Reading(13.8, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 2, 0).getTime()));
        temperatureSensorB109.addReading(new Reading(13.3, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 8, 0).getTime()));
        temperatureSensorB109.addReading(new Reading(15.5, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 14, 0).getTime()));
        temperatureSensorB109.addReading(new Reading(14.2, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 20, 0).getTime()));
        temperatureSensorB109.addReading(new Reading(12.5, new GregorianCalendar(2019, Calendar.JANUARY, 1, 2, 0).getTime()));
        temperatureSensorB109.addReading(new Reading(12.4, new GregorianCalendar(2019, Calendar.JANUARY, 1, 8, 0).getTime()));
        temperatureSensorB109.addReading(new Reading(13.8, new GregorianCalendar(2019, Calendar.JANUARY, 1, 14, 0).getTime()));
        temperatureSensorB109.addReading(new Reading(12.9, new GregorianCalendar(2019, Calendar.JANUARY, 1, 20, 0).getTime()));
        temperatureSensorB109.addReading(new Reading(11.5, new GregorianCalendar(2019, Calendar.JANUARY, 2, 2, 0).getTime()));
        temperatureSensorB109.addReading(new Reading(11.2, new GregorianCalendar(2019, Calendar.JANUARY, 2, 8, 0).getTime()));
        temperatureSensorB109.addReading(new Reading(13.5, new GregorianCalendar(2019, Calendar.JANUARY, 2, 14, 0).getTime()));
        temperatureSensorB109.addReading(new Reading(12.8, new GregorianCalendar(2019, Calendar.JANUARY, 2, 20, 0).getTime()));
    }

    private void createHumiditySensorB109Readings(Sensor humiditySensorB109) {
        humiditySensorB109.addReading(new Reading(84, new GregorianCalendar(2018, Calendar.DECEMBER, 30, 2, 0).getTime()));
        humiditySensorB109.addReading(new Reading(85.7, new GregorianCalendar(2018, Calendar.DECEMBER, 30, 8, 0).getTime()));
        humiditySensorB109.addReading(new Reading(76.5, new GregorianCalendar(2018, Calendar.DECEMBER, 30, 14, 0).getTime()));
        humiditySensorB109.addReading(new Reading(78.1, new GregorianCalendar(2018, Calendar.DECEMBER, 30, 20, 0).getTime()));
        humiditySensorB109.addReading(new Reading(83.8, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 2, 0).getTime()));
        humiditySensorB109.addReading(new Reading(83.9, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 8, 0).getTime()));
        humiditySensorB109.addReading(new Reading(75.5, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 14, 0).getTime()));
        humiditySensorB109.addReading(new Reading(77.2, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 20, 0).getTime()));
        humiditySensorB109.addReading(new Reading(82.5, new GregorianCalendar(2019, Calendar.JANUARY, 1, 2, 0).getTime()));
        humiditySensorB109.addReading(new Reading(82.4, new GregorianCalendar(2019, Calendar.JANUARY, 1, 8, 0).getTime()));
        humiditySensorB109.addReading(new Reading(73.8, new GregorianCalendar(2019, Calendar.JANUARY, 1, 14, 0).getTime()));
        humiditySensorB109.addReading(new Reading(72.9, new GregorianCalendar(2019, Calendar.JANUARY, 1, 20, 0).getTime()));
        humiditySensorB109.addReading(new Reading(80.5, new GregorianCalendar(2019, Calendar.JANUARY, 2, 2, 0).getTime()));
        humiditySensorB109.addReading(new Reading(79.2, new GregorianCalendar(2019, Calendar.JANUARY, 2, 8, 0).getTime()));
        humiditySensorB109.addReading(new Reading(71.5, new GregorianCalendar(2019, Calendar.JANUARY, 2, 14, 0).getTime()));
        humiditySensorB109.addReading(new Reading(72.8, new GregorianCalendar(2019, Calendar.JANUARY, 2, 20, 0).getTime()));
    }
    */
}
