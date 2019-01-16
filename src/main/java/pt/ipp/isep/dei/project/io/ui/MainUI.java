package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.model.devicetypes.DeviceType;
import pt.ipp.isep.dei.project.model.devicetypes.Fridge;
import pt.ipp.isep.dei.project.model.devicetypes.WaterHeater;

import java.util.*;

public class MainUI {

    public static void main(String[] args) {

        //Program Variables

        // ***************************************************************************
        // ********************* < MOCK DATA FOR TESTING PURPOSES >*******************
        // ***************************************************************************

        // ******* MAIN MOCK *******************************************************

        // Geo List (1)

        GeographicAreaList geographicalAreaList = new GeographicAreaList();

        // Geo Areas (4)

        GeographicArea isep = new GeographicArea("ISEP", new TypeArea("urban area"), 52, 5, new Local(41.178553, -8.608035, 111));
        isep.setWidth(0.261);
        isep.setLength(0.249);
        isep.setDescription("Campus do ISEP");

        String msPorto = "Porto";

        GeographicArea porto = new GeographicArea(msPorto, new TypeArea("city"), 31, 6, new Local(41.164077, -8.620802, 118));
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
        RoomList roomListEdifB = new RoomList();

        roomListEdifB.addRoom(roomISEP1);
        roomListEdifB.addRoom(roomISEP2);
        roomListEdifB.addRoom(roomISEP3);

        RoomList gridRoomList = new RoomList();
        gridRoomList.addRoom(roomISEP2);
        gridRoomList.addRoom(roomISEP3);

        // Houses (1 per Geographical Area!)

        House edificioB = new House("Edificio B", "Rua Dr António Bernardino de Almeida, 431", "4200-072", msPorto, new Local(41.177748, -8.607745, 112), isep, roomListEdifB);
        edificioB.setMotherArea(isep);

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

        Sensor sensorRoom109 = new Sensor("Temperature B109", new TypeSensor("temperature", "ºC"), new Local(0, 0, 0), new GregorianCalendar(2018, 10, 16).getTime());
        sensorRoom109.setReadingList(readingListSensorRoom109);
        Sensor sensorRainfallISEP = new Sensor("Meteo station ISEP - rainfall", new TypeSensor("rainfall", "mm"), new Local(41.179230, -8.606409, 125), new GregorianCalendar(2016, 11, 15).getTime());
        sensorRainfallISEP.setReadingList(readingListISEPRainfall);
        Sensor sensorTemperatureISEP = new Sensor("Meteo station ISEP - temperature", new TypeSensor("temperature", "ºC"), new Local(41.179230, -8.606409, 125), new GregorianCalendar(2016, 11, 15).getTime());
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

        EnergyGrid mainGrid = new EnergyGrid("main grid", 0);
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

        List<TypeSensor> typeSensorList = new ArrayList<>();

        //Devices

        Device device1 = new Device("aquecedor",23.0, new WaterHeater(100.0,30.0,2.0));
        Device device2 = new Device("FridgeOne", 4, new Fridge());
        Device device3 = new Device("WHTwo", 3, new WaterHeater(500.0,25.0,10.0));
        device1.setmParentRoom(roomISEP2);
        device2.setmParentRoom(roomISEP2);
        device3.setmParentRoom(roomISEP2);
        DeviceList listDevices = new DeviceList();
        listDevices.addDevice(device1);
        listDevices.addDevice(device2);
        listDevices.addDevice(device3);
        roomISEP2.setDeviceList(listDevices);

   //     Device device4 = new Device("aquecedor1",23.0, new WaterHeater(100.0,30.0,2.0));
        Device device5 = new Device("FridgeOne1", 4, new Fridge());
 //       Device device6 = new Device("WHTwo1", 3, new WaterHeater(500.0,25.0,10.0));
       // device4.setmParentRoom(roomISEP3);
        device5.setmParentRoom(roomISEP3);
     //   device6.setmParentRoom(roomISEP3);
        DeviceList listDevices1 = new DeviceList();
   //     listDevices1.addDevice(device4);
        listDevices1.addDevice(device5);
     //   listDevices1.addDevice(device6);
        roomISEP3.setDeviceList(listDevices1);

        // ********* MOCKS EXTRA **********************************************

        List<DeviceType> deviceTypeList = new ArrayList<>();
        deviceTypeList.add(DeviceType.FRIDGE);
        deviceTypeList.add(DeviceType.WASHING_MACHINE);
        deviceTypeList.add(DeviceType.WATER_HEATER);
        deviceTypeList.add(DeviceType.DISHWASHER);
        deviceTypeList.add(DeviceType.LAMP);

        TypeAreaList emptyTypeAreaList = new TypeAreaList();




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


        // **************************************************************************
        // ******************* < / MOCK DATA FOR TESTING PURPOSES > ******************
        // **************************************************************************

         **/

        //MAIN CODE

        Scanner enterToReturnToConsole = new Scanner(System.in);
        int option;
        while (true) {

            System.out.println("\n**********************************\n" +
                    "******** Smart Grid Menu *********\n" +
                    "*********** sWitCh 2018 **********\n" +
                    "**********************************\n");

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
                        view1.runGASettings(geographicalAreaList, emptyTypeAreaList);
                        returnToMenu(enterToReturnToConsole);
                        activeInput = false;
                        break;
                    case 2:
                        HouseConfigurationUI houseC = new HouseConfigurationUI();
                        houseC.run(edificioB);
                        returnToMenu(enterToReturnToConsole);
                        activeInput = false;
                        break;
                    case 3:
                        RoomConfigurationUI roomConfiguration = new RoomConfigurationUI();
                        roomConfiguration.run(edificioB,deviceTypeList,typeSensorList);
                        returnToMenu(enterToReturnToConsole);
                        activeInput = false;
                        break;
                    case 4:
                        SensorSettingsUI sensorSettings = new SensorSettingsUI();
                        sensorSettings.run(geographicalAreaList, typeSensorList);
                        returnToMenu(enterToReturnToConsole);
                        activeInput = false;
                        break;
                    case 5:
                        EnergyGridSettingsUI energyGridSettings = new EnergyGridSettingsUI();
                        energyGridSettings.run(edificioB);
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
                        energyConsumptionUI.run(edificioB);
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