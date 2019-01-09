package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.model.*;

import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class MainUI {

    public static void main(String[] args) {

        //Program Variables

        boolean activeProgram = true;
        House programHouse = new House();

        //TEST MAIN LISTS
        GeographicAreaList mGeographicAreaList = new GeographicAreaList();
        TypeAreaList mTypeAreaList = new TypeAreaList();

        //TYPE AREA
        TypeArea typeArea1 = new TypeArea("cidade");
        TypeArea typeArea2 = new TypeArea("aldeia");
        mTypeAreaList.addTypeArea(typeArea1);
        mTypeAreaList.addTypeArea(typeArea2);

        //TEST READINGS
        ReadingList readingList = new ReadingList();
        Reading reading1 = new Reading(30, new GregorianCalendar(2018, 8, 6).getTime());
        Reading reading2 = new Reading(20, new GregorianCalendar(2018, 8, 5).getTime());
        Reading reading3 = new Reading(40, new GregorianCalendar(2018, 8, 5).getTime());
        readingList.addReading(reading1);
        readingList.addReading(reading2);
        readingList.addReading(reading3);

        //TEST ENERGYGRID
        EnergyGridList energyGridList2 = new EnergyGridList();
        EnergyGrid eg1 = new EnergyGrid("rede", 56789);
        energyGridList2.addEnergyGridToEnergyGridList(eg1);

        //TEST SENSORS
        SensorList sensorList1 = new SensorList();
        SensorList sensorList2 = new SensorList();
        SensorList sensorList3 = new SensorList();
        Sensor sensor1 = new Sensor("sensor", new TypeSensor("temperature", "Celsius"), new Local(4, 4), new GregorianCalendar(8, 8, 8).getTime());
        Sensor sensor2 = new Sensor("sensor2", new TypeSensor("Rain", "l/m2"), new Local(4, 4), new GregorianCalendar(8, 8, 8).getTime());
        Sensor sensor3 = new Sensor("teste", new TypeSensor("Rain", "l/m2"), new Local(4, 4), new GregorianCalendar(8, 8, 8).getTime());
        sensor1.setReadingList(readingList);
        sensor2.setReadingList(readingList);
        sensor3.setReadingList(readingList);
        sensorList2.addSensor(sensor1);
        sensorList2.addSensor(sensor2);
        sensorList2.addSensor(sensor3);
        sensorList1.addSensor(sensor1);
        sensorList1.addSensor(sensor2);
        sensorList1.addSensor(sensor3);

        //TEST ROOMS
        RoomList roomList1 = new RoomList();
        RoomList roomList2 = new RoomList();
        Room room1 = new Room("wc", 19, 23456789, 2,2);
        room1.setRoomSensorList(sensorList2);
        Room room2 = new Room("kitchen", 8, 2, 2,2);
        room2.setRoomSensorList(sensorList2);
        Room room3 = new Room("room1", 19, 23456789, 2,2);
        room3.setRoomSensorList(sensorList2);
        Room room4 = new Room("room2", 8, 2, 2,2);
        room4.setRoomSensorList(sensorList2);
        Device d1 = new Device("aspirador", "eletrodomestico", room2, new ReadingList(),20 );
        DeviceList deviceList = new DeviceList();
        deviceList.addDevices(d1);
        roomList1.addRoom(room1);
        roomList1.addRoom(room2);
        roomList2.addRoom(room3);
        roomList2.addRoom(room4);
        eg1.setListOfRooms(roomList2);

        //TEST GEOGRAPHIC AREAS
        GeographicArea geoa1 = new GeographicArea("porto", new TypeArea("cidade"), 2,3,new Local(4, 4));
        GeographicArea geoa2 = new GeographicArea("lisboa", new TypeArea("aldeia"), 2,5,new Local(4, 4));
        geoa1.setSensorList(sensorList1);
        geoa2.setSensorList(sensorList2);

        //TEST HOUSES
        House house1 = new House("HouseOne", "Rua das Flores", "4643-221", "Porto", new Local(23, 32),geoa2, roomList1);
        House house2 = new House("HouseOne", "Rua do Sol", "7645-765", "Lisboa", new Local(13, 76),geoa1, roomList1);
        House house3 = new House("HouseTwo", "Rua da Praia", "7923-223", "Braga", new Local(24, 54),geoa1, roomList1);
        House house4 = new House("HouseTwo", "Rua do Mar", "4512-231", "Coimbra", new Local(12, 76),geoa1, roomList1);
        house1.setmRoomList(roomList1);
        house2.setmMotherArea(geoa1);
        house4.setmMotherArea(geoa1);
        house2.setmRoomList(roomList1);
        house3.setmMotherArea(geoa1);
        house3.setmRoomList(roomList1);
        house1.setmMotherArea(geoa2);

        //TEST ORGANIZATION SETTERS
        house1.setmEGList(energyGridList2);

        geoa1.setSensorList(sensorList2);
        geoa2.setSensorList(sensorList2);

        mGeographicAreaList.addGeographicAreaToGeographicAreaList(geoa1);
        mGeographicAreaList.addGeographicAreaToGeographicAreaList(geoa2);

        house2.setmEGList(energyGridList2);


        /******************************************************************************
         ************************ < MOCK DATA FOR TESTING PUPOSES >********************
         ******************************************************************************/

        // Geo List (1)
        GeographicAreaList geographicalAreaList = new GeographicAreaList();

        // Geo Areas (4)

        GeographicArea ISEP = new GeographicArea("ISEP", new TypeArea("urban area"), 52,5,new Local(41.178553, -8.608035, 111));
        ISEP.setWidth(0.261);
        ISEP.setLength(0.249);

        GeographicArea Porto = new GeographicArea("Porto", new TypeArea("city"), 31,6,new Local(41.164077, -8.620802, 118));
        Porto.setWidth(10.09);
        Porto.setLength(10.09);

        GeographicArea Portugal = new GeographicArea("Portugal", new TypeArea("pais"), 22,9,new Local(60, 0, 110));
        Portugal.setWidth(100);
        Portugal.setLength(800);

        GeographicArea PortoSanto = new GeographicArea("Porto Santo", new TypeArea("city"), 42,12,new Local(-45, 67, 67));
        PortoSanto.setWidth(156);
        PortoSanto.setLength(235);

        geographicalAreaList.addGeographicAreaToGeographicAreaList(ISEP);
        geographicalAreaList.addGeographicAreaToGeographicAreaList(Porto);
        geographicalAreaList.addGeographicAreaToGeographicAreaList(Portugal);
        geographicalAreaList.addGeographicAreaToGeographicAreaList(PortoSanto);

        /** FALTA DESCRICAO NA GEO AREA **/

        //Rooms

        //Edificio B Rooms
        Room roomISEP1 = new Room("B107", 1, 7,11,3.5);
        Room roomISEP2 = new Room("B109", 1, 7,11,3.5);
        Room roomISEP3 = new Room("B106", 1, 7,13,3.5);
        RoomList roomListEdifB = new RoomList();
        roomListEdifB.addRoom(roomISEP1);
        roomListEdifB.addRoom(roomISEP2);
        roomListEdifB.addRoom(roomISEP3);

        // Houses (1 per Geographical Area!)

        House EdificioB = new House("Edificio B", "Rua Dr António Bernardino de Almeida, 431", "4200-072", "Porto", new Local(41.177748, -8.607745,112),ISEP, roomListEdifB);


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
        Reading reading45 = new Reading(6.1, new GregorianCalendar(2019, GregorianCalendar.JANUARY, 2,2,0).getTime());
        Reading reading46 = new Reading(3.2, new GregorianCalendar(2019, GregorianCalendar.JANUARY, 2,8,0).getTime());
        Reading reading47 = new Reading(14.1, new GregorianCalendar(2019, GregorianCalendar.JANUARY, 2,14,0).getTime());
        Reading reading48 = new Reading(8.3, new GregorianCalendar(2019, GregorianCalendar.JANUARY, 2,20,0).getTime());
        readingListISEPtemperature.addReading(reading33);
        readingListISEPtemperature.addReading(reading34);
        readingListISEPtemperature.addReading(reading35);
        readingListISEPtemperature.addReading(reading36);
        readingListISEPtemperature.addReading(reading37);
        readingListISEPtemperature.addReading(reading38);
        readingListISEPtemperature.addReading(reading39);
        readingListISEPtemperature.addReading(reading40);
        readingListISEPtemperature.addReading(reading41);
        readingListISEPtemperature.addReading(reading42);
        readingListISEPtemperature.addReading(reading43);
        readingListISEPtemperature.addReading(reading44);
        readingListISEPtemperature.addReading(reading45);
        readingListISEPtemperature.addReading(reading46);
        readingListISEPtemperature.addReading(reading47);
        readingListISEPtemperature.addReading(reading48);

        // Sensors

        Sensor sensorRoom109 = new Sensor("Temperature B109", new TypeSensor("temperature", "ºC"), new Local(0, 0), new GregorianCalendar(2018, 10, 16).getTime());
        sensorRoom109.setReadingList(readingListSensorRoom109);
        Sensor sensorRainfallISEP = new Sensor("Meteo station ISEP - rainfall", new TypeSensor("rainfall","mm"), new Local(41.179230, -8.606409,125), new GregorianCalendar(2016, 11, 15).getTime());
        sensorRainfallISEP.setReadingList(readingListISEPRainfall);
        Sensor sensorTemperatureISEP = new Sensor("Meteo station ISEP - temperature", new TypeSensor("temperature","ºC"), new Local(41.179230, -8.606409,125), new GregorianCalendar(2016, 11, 15).getTime());
        sensorTemperatureISEP.setReadingList(readingListISEPtemperature);

        // Sensor Lists

        SensorList room109SensorList = new SensorList();
        room109SensorList.addSensor(sensorRoom109);
        roomISEP2.setRoomSensorList(room109SensorList);

        SensorList ISEPSensorList = new SensorList();
        ISEPSensorList.addSensor(sensorRainfallISEP);
        ISEPSensorList.addSensor(sensorTemperatureISEP);
        ISEP.setSensorList(ISEPSensorList);


        // Energy Grid

        EnergyGrid mainGrid = new EnergyGrid("main grid",0);
        EnergyGridList EnergyGridListISEP = new EnergyGridList();
        EnergyGridListISEP.addEnergyGridToEnergyGridList(mainGrid);
        mainGrid.setListOfRooms(roomListEdifB);


        /******************************************************************************
         ********************** < / MOCK DATA FOR TESTING PUPOSES > *******************
         ******************************************************************************/

        //MAIN CODE

        Scanner enterToReturnToConsole = new Scanner(System.in);
        int option;
        while (activeProgram) {

            System.out.println("\n**********************************\n" +
                               "******** Smart Grid Menu *********\n" +
                               "*********** sWitCh 2018 **********\n" +
                               "**********************************\n");

            // Submenus Input selection

            String[] menu = {" 0. Exit Application\n",
                    "1 Geographic Area Settings\n",
                    "5. Determine the type of a sensor.\n",
                    "6. Create a new Sensor and add it to a GA.\n",
                    "9. Display all available sensors.\n",
                    "10. Display all available Geographic Areas.\n",
                    "11. Set the location of a house.\n",
                    "12. Add a new room to a house.\n",
                    "14. Create an energy grid.\n",
                    "15. Add a power source to an energy grid.\n",
                    "16. See Room List.\n",
                    "17. Get Current Temperature in a House Area.\n",
                    "18. Change Room Settings.\n",
                    "19. List the rooms on Energy Grid\n",
                    "20. Add a Room to the Energy Grid\n",
                    "21. Remove a Room from an Energy Grid\n",
                    "22. Room Configuration.\n",
                    "23. House Monitoring.\n"};

            System.out.println("Select the task you want to do:");

            String formattedString = Arrays.toString(menu)
                    .replace(",", "")  //remove the commas
                    .replace("[", "")  //remove the right bracket
                    .replace("]", "");  //remove the left bracket

            System.out.print(formattedString);
            System.out.print("\nEnter option number:\n");
            boolean activeInput = false;

            while (!activeInput) {
                option = UtilsUI.readInputNumberAsInt();
                switch (option) {
                    case 0:
                        return;
                    case 1:
                        GASettingsUI view1 = new GASettingsUI ();
                        view1.run(mGeographicAreaList, mTypeAreaList);
                        returnToMenu(enterToReturnToConsole);
                        activeInput = true;
                        break;
                    case 5:
                        HouseConfigurationUI view5 = new HouseConfigurationUI(sensorList1, mGeographicAreaList);
                        view5.runUS05(sensorList1);
                        returnToMenu(enterToReturnToConsole);
                        activeInput = true;
                        break;
                    case 6:
                        HouseConfigurationUI view6 = new HouseConfigurationUI(sensorList1, mGeographicAreaList);
                        view6.run06();
                        returnToMenu(enterToReturnToConsole);
                        activeInput = true;
                        break;
                    case 9:
                        if (sensorList1.checkIfListIsValid())
                            System.out.println("The list of sensors is empty!");
                        for (Sensor sensor : sensorList1.getSensorList()) {
                            System.out.println(sensor.getName());
                        }
                        returnToMenu(enterToReturnToConsole);
                        activeInput = true;
                        break;
                    case 10:
                        if (mGeographicAreaList.checkIfListIsValid())
                            System.out.println("The list of geographic areas is empty!");
                        for (GeographicArea ga1 : mGeographicAreaList.getGeographicAreaList()) {
                            System.out.println(ga1.getId());
                        }
                        returnToMenu(enterToReturnToConsole);
                        activeInput = true;
                        break;
                    case 11:
                        HouseConfigurationUI view11 = new HouseConfigurationUI(sensorList1, mGeographicAreaList);
                        view11.runUS101(programHouse);
                        returnToMenu(enterToReturnToConsole);
                        activeInput = true;
                        break;
                    case 12:
                        HouseConfigurationUI view12 = new HouseConfigurationUI(sensorList1, mGeographicAreaList);
                        view12.runUS105(programHouse);
                        returnToMenu(enterToReturnToConsole);
                        activeInput = true;
                        break;
                    case 14:
                        HouseConfigurationUI view14 = new HouseConfigurationUI(sensorList1, mGeographicAreaList);
                        view14.runUS130(programHouse);
                        returnToMenu(enterToReturnToConsole);
                        activeInput = true;
                        break;
                    case 15:
                        HouseConfigurationUI view15 = new HouseConfigurationUI(sensorList1, mGeographicAreaList);
                        view15.runUS135(programHouse);
                        returnToMenu(enterToReturnToConsole);
                        activeInput = true;
                        break;
                    case 18:
                        HouseConfigurationUI view16 = new HouseConfigurationUI(sensorList1, mGeographicAreaList);
                        view16.runUS108UI(programHouse);
                        returnToMenu(enterToReturnToConsole);
                        activeInput = true;
                        break;
                    case 19:
                        HouseConfigurationUI view145 = new HouseConfigurationUI(sensorList1, mGeographicAreaList);
                        view145.runUS145(programHouse);
                        returnToMenu(enterToReturnToConsole);
                        activeInput = true;
                        break;
                    case 20:
                        HouseConfigurationUI view147 = new HouseConfigurationUI(sensorList1, mGeographicAreaList);
                        view147.runUS147(programHouse);
                        returnToMenu(enterToReturnToConsole);
                        activeInput = true;
                        break;
                    case 21:
                        HouseConfigurationUI view149 = new HouseConfigurationUI(sensorList1, mGeographicAreaList);
                        view149.runUS149(programHouse);
                        returnToMenu(enterToReturnToConsole);
                        activeInput = true;
                        break;
                    case 22:
                        RoomConfigurationUI viewRC = new RoomConfigurationUI();
                        viewRC.run(mGeographicAreaList, programHouse);
                        returnToMenu(enterToReturnToConsole);
                        activeInput = true;
                        break;
                    case 23:
                        HouseMonitoringUI houseM = new HouseMonitoringUI();
                        houseM.run(geographicalAreaList, programHouse);
                        returnToMenu(enterToReturnToConsole);
                        activeInput = true;
                        break;
                    default:
                        System.out.println(UtilsUI.INVALID_OPTION);
                        break;
                }
            }
        }
    }

    private static void returnToMenu(Scanner scanner){
        String pressEnter = "\nPress ENTER to return.";
        System.out.println(pressEnter);
        scanner.nextLine();
    }
}