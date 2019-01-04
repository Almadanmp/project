package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.model.*;

import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class MainUI {

    public static void main(String[] args) {

        //Program Variables

        boolean activeProgram = true;

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
        Sensor sensor1 = new Sensor("sensor", new TypeSensor("temperature"), new Local(4, 4), new GregorianCalendar(8, 8, 8).getTime(), readingList);
        Sensor sensor2 = new Sensor("sensor2", new TypeSensor("Rain"), new Local(4, 4), new GregorianCalendar(8, 8, 8).getTime(), readingList);
        Sensor sensor3 = new Sensor("teste", new TypeSensor("Rain"), new Local(4, 4), new GregorianCalendar(8, 8, 8).getTime(), readingList);
        sensorList2.addSensor(sensor1);
        sensorList2.addSensor(sensor2);
        sensorList2.addSensor(sensor3);
        sensorList1.addSensor(sensor1);
        sensorList1.addSensor(sensor2);
        sensorList1.addSensor(sensor3);

        //TEST ROOMS
        RoomList roomList1 = new RoomList();
        Room room1 = new Room("room1", 19, 23456789, sensorList2);
        Room room2 = new Room("kitchen", 8, 2, sensorList2);
        Device d1 = new Device("aspirador", "eletrodomestico", room2, new ReadingList(),20 );
        DeviceList deviceList = new DeviceList();
        deviceList.addDevices(d1);
        room2.setRoomDeviceList(deviceList);
        roomList1.addRoom(room2);
        roomList1.addRoom(room1);
        RoomList roomListGrid = new RoomList();
        eg1.setListOfRooms(roomList1);

        //TEST GEOGRAPHIC AREAS
        GeographicArea geoa1 = new GeographicArea("porto", new TypeArea("cidade"), new Local(4, 4));
        GeographicArea geoa2 = new GeographicArea("lisboa", new TypeArea("aldeia"), new Local(4, 4));
        geoa1.setSensorList(sensorList1);
        geoa2.setSensorList(sensorList2);

        //TEST HOUSES
        HouseList houseList1 = new HouseList();
        HouseList houseList2 = new HouseList();

        House house1 = new House("houseOne", "Address1", new Local(22, 3), "4150-657");
        House house2 = new House("houseTwo", "Address2", new Local(4, 4), "3456-123");
        House house3 = new House("houseTwo", "Address3", new Local(18, 10), "3555-555");
        House house4 = new House("houseOne", "Address4", new Local(18, 10), "3555-555");
        house1.setmRoomList(roomList1);
        house2.setmMotherArea(geoa1);
        house4.setmMotherArea(geoa1);
        house2.setmRoomList(roomList1);
        house3.setmMotherArea(geoa1);
        house3.setmRoomList(roomList1);
        houseList1.addHouseToHouseList(house2);
        houseList1.addHouseToHouseList(house3);
        houseList1.addHouseToHouseList(house4);
        houseList1.addHouseToHouseList(house1);
        house1.setmMotherArea(geoa2);
        houseList2.addHouseToHouseList(house1);

        //TEST ORGANIZATION SETTERS
        house1.setmEGList(energyGridList2);

        geoa1.setSensorList(sensorList2);
        geoa1.setHouseList(houseList1);
        geoa2.setSensorList(sensorList2);
        geoa2.setHouseList(houseList2);

        mGeographicAreaList.addGeographicAreaToGeographicAreaList(geoa1);
        mGeographicAreaList.addGeographicAreaToGeographicAreaList(geoa2);

        house2.setmEGList(energyGridList2);

        //SPRINT 1 DATA

        GeographicAreaList geographical_area_list = new GeographicAreaList();

        GeographicArea ISEP = new GeographicArea("Campus do ISEP", new TypeArea("urban area"), new Local(41.178553, -8.608035, 111));
        House house = new House("Edificio B", "Rua Dr António Bernardino de Almeida, 431", new Local(41.177748, -8.607745,112), "4200-072");
        RoomList roomList = new RoomList();
        house.setmRoomList(roomList);
        Room room_a = new Room("B107", 1, 24.5);
        Room room_b = new Room("B109", 1, 24.5);

        ReadingList readingList_a = new ReadingList();
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
        readingList_a.addReading(reading10);
        readingList_a.addReading(reading11);
        readingList_a.addReading(reading12);
        readingList_a.addReading(reading13);
        readingList_a.addReading(reading14);
        readingList_a.addReading(reading15);
        readingList_a.addReading(reading16);
        readingList_a.addReading(reading17);
        readingList_a.addReading(reading18);
        readingList_a.addReading(reading19);
        readingList_a.addReading(reading20);
        readingList_a.addReading(reading21);
        readingList_a.addReading(reading22);
        readingList_a.addReading(reading23);
        readingList_a.addReading(reading24);
        readingList_a.addReading(reading25);

        Sensor sensor_a = new Sensor("Temperature B109", new TypeSensor("temperature"), new Local(0, 0), new GregorianCalendar(0, 0, 0).getTime(), readingList_a);
        Room room_c = new Room("B106", 1, 318.5);
        house.addRoomToRoomList(room_a);
        house.addRoomToRoomList(room_b);
        house.addRoomToRoomList(room_c);
        HouseList Rooms = new HouseList();
        Rooms.addHouseToHouseList(house);
        ISEP.setHouseList(Rooms);
        SensorList roomBSList = new SensorList();
        roomBSList.addSensor(sensor_a);
        room_b.setRoomSensorList(roomBSList);

        ReadingList readingList_b = new ReadingList();
        Reading reading26 = new Reading(0.5, new GregorianCalendar(2018, GregorianCalendar.DECEMBER, 29).getTime());
        Reading reading27 = new Reading(1.2, new GregorianCalendar(2018, GregorianCalendar.DECEMBER, 30).getTime());
        Reading reading28 = new Reading(1.5, new GregorianCalendar(2018, GregorianCalendar.DECEMBER, 31).getTime());
        Reading reading29 = new Reading(0.3, new GregorianCalendar(2019, GregorianCalendar.JANUARY, 1).getTime());
        Reading reading30 = new Reading(0.0, new GregorianCalendar(2019, GregorianCalendar.JANUARY, 2).getTime());
        Reading reading31 = new Reading(0.0, new GregorianCalendar(2019, GregorianCalendar.JANUARY, 2).getTime());
        Reading reading32 = new Reading(0.0, new GregorianCalendar(2019, GregorianCalendar.JANUARY, 3).getTime());
        readingList_b.addReading(reading26);
        readingList_b.addReading(reading27);
        readingList_b.addReading(reading28);
        readingList_b.addReading(reading29);
        readingList_b.addReading(reading30);
        readingList_b.addReading(reading31);
        readingList_b.addReading(reading32);

        Sensor sensor_b = new Sensor("Meteo station ISEP - rainfall", new TypeSensor("rainfall"), new Local(41.179230, -8.606409,125), new GregorianCalendar(2016, 11, 15).getTime(), readingList_b);

        ReadingList readingList_c = new ReadingList();
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
        readingList_c.addReading(reading33);
        readingList_c.addReading(reading34);
        readingList_c.addReading(reading35);
        readingList_c.addReading(reading36);
        readingList_c.addReading(reading37);
        readingList_c.addReading(reading38);
        readingList_c.addReading(reading39);
        readingList_c.addReading(reading40);
        readingList_c.addReading(reading41);
        readingList_c.addReading(reading42);
        readingList_c.addReading(reading43);
        readingList_c.addReading(reading44);
        readingList_c.addReading(reading45);
        readingList_c.addReading(reading46);
        readingList_c.addReading(reading47);
        readingList_c.addReading(reading48);

        Sensor sensor_c = new Sensor("Meteo station ISEP - temperature", new TypeSensor("temperature"), new Local(41.179230, -8.606409,125), new GregorianCalendar(2016, 11, 15).getTime(), readingList_c);

        SensorList areaSList = new SensorList();
        areaSList.addSensor(sensor_c);
        areaSList.addSensor(sensor_b);
        ISEP.setSensorList(areaSList);
        roomList.addRoom(room_a);
        roomList.addRoom(room_b);
        roomList.addRoom(room_c);

        GeographicArea Porto = new GeographicArea("City of Porto", new TypeArea("city"), new Local(41.164077, -8.620802, 118));

        geographical_area_list.addGeographicAreaToGeographicAreaList(ISEP);
        geographical_area_list.addGeographicAreaToGeographicAreaList(Porto);

        EnergyGrid mainGrid = new EnergyGrid("main grid",0);
        EnergyGridList grid_list = new EnergyGridList();
        mainGrid.setListOfRooms(roomList);
        mainGrid.addRoomToAEnergyGrid(room_b);
        mainGrid.addRoomToAEnergyGrid(room_c);
        grid_list.addEnergyGridToEnergyGridList(mainGrid);
        house.setmEGList(grid_list);

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
                    "1 Create a new Type of Geographic Areas\n",
                    "2 List the existing types of Geographic Areas\n",
                    "3 Create a new Geographic Area\n",
                    "4. List all Geographic Areas of a certain type.\n",
                    "5. Determine the type of a sensor.\n",
                    "6. Create a new Sensor and add it to a GA.\n",
                    "7. Define that an area is contained in another area.\n",
                    "8. Check if an area is contained in another area.\n",
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
                    "20. Remove a Room from an Energy Grid\n",
                    "21. Room Configuration.\n",
                    "22. House Monitoring.\n"};

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
                        HouseConfigurationUI view1 = new HouseConfigurationUI();
                        view1.runUS01UI(mTypeAreaList);
                        returnToMenu(enterToReturnToConsole);
                        activeInput = true;
                        break;
                    case 2:
                        HouseConfigurationUI view2 = new HouseConfigurationUI();
                        view2.runUS02(mTypeAreaList);
                        returnToMenu(enterToReturnToConsole);
                        activeInput = true;
                        break;
                    case 3:
                        HouseConfigurationUI view3 = new HouseConfigurationUI();
                        view3.runUS03(mGeographicAreaList, mTypeAreaList);
                        returnToMenu(enterToReturnToConsole);
                        activeInput = true;
                        break;
                    case 4:
                        HouseConfigurationUI view4 = new HouseConfigurationUI();
                        view4.runUS04(mTypeAreaList);
                        returnToMenu(enterToReturnToConsole);
                        activeInput = true;
                        break;
                    case 5:
                        HouseConfigurationUI view5 = new HouseConfigurationUI();
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
                    case 7:
                        HouseConfigurationUI view7 = new HouseConfigurationUI();
                        view7.runUS07(mGeographicAreaList);
                        returnToMenu(enterToReturnToConsole);
                        activeInput = true;
                        break;
                    case 8:
                        HouseConfigurationUI view8 = new HouseConfigurationUI();
                        view8.runUS08(mGeographicAreaList);
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
                        for (GeographicArea ga : mGeographicAreaList.getGeographicAreaList()) {
                            System.out.println(ga.getName());
                        }
                        returnToMenu(enterToReturnToConsole);
                        activeInput = true;
                        break;
                    case 11:
                        HouseConfigurationUI view11 = new HouseConfigurationUI();
                        view11.runUS101(mGeographicAreaList);
                        returnToMenu(enterToReturnToConsole);
                        activeInput = true;
                        break;
                    case 12:
                        HouseConfigurationUI view12 = new HouseConfigurationUI();
                        view12.runUS105(mGeographicAreaList);
                        returnToMenu(enterToReturnToConsole);
                        activeInput = true;
                        break;
                    case 14:
                        HouseConfigurationUI view14 = new HouseConfigurationUI();
                        view14.runUS130(houseList1);
                        returnToMenu(enterToReturnToConsole);
                        activeInput = true;
                        break;
                    case 15:
                        HouseConfigurationUI view15 = new HouseConfigurationUI();
                        view15.runUS135(houseList1);
                        returnToMenu(enterToReturnToConsole);
                        activeInput = true;
                        break;
                    case 18:
                        HouseConfigurationUI view16 = new HouseConfigurationUI();
                        view16.runUS108UI(mGeographicAreaList);
                        returnToMenu(enterToReturnToConsole);
                        activeInput = true;
                        break;
                    case 19:
                        HouseConfigurationUI view145 = new HouseConfigurationUI();
                        view145.runUS145(geographical_area_list);
                        returnToMenu(enterToReturnToConsole);
                        activeInput = true;
                        break;
                    case 20:
                        HouseConfigurationUI view149 = new HouseConfigurationUI();
                        view149.runUS149(mGeographicAreaList);
                        returnToMenu(enterToReturnToConsole);
                        activeInput = true;
                        break;
                    case 21:
                        RoomConfigurationUI viewRC = new RoomConfigurationUI();
                        viewRC.run(mGeographicAreaList);
                        returnToMenu(enterToReturnToConsole);
                        activeInput = true;
                        break;
                    case 22:
                        HouseMonitoringUI houseM = new HouseMonitoringUI();
                        houseM.run(geographical_area_list, roomList);
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