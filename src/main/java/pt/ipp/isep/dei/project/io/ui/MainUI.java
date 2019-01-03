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

        //TEST READINGS
        ReadingList readingList = new ReadingList();
        Reading reading1 = new Reading(30, new GregorianCalendar(2018, 8, 6).getTime());
        Reading reading2 = new Reading(40, new GregorianCalendar(2018, 8, 5).getTime());
        Reading reading3 = new Reading(40, new GregorianCalendar(2018, 8, 5).getTime());
        readingList.addReading(reading1);
        readingList.addReading(reading2);
        readingList.addReading(reading3);

        //TEST ENERGYGRID
        EnergyGridList energyGridList1 = new EnergyGridList();
        EnergyGridList energyGridList2 = new EnergyGridList();
        EnergyGrid eg1 = new EnergyGrid("rede", 56789);
        energyGridList2.addEnergyGridToEnergyGridList(eg1);

        //TEST SENSORS
        SensorList sensorList1 = new SensorList();
        SensorList sensorList2 = new SensorList();
        Sensor sensor1 = new Sensor("sensor", new TypeSensor("temperature"), new Local(4, 4), new GregorianCalendar(8, 8, 8).getTime(), readingList);
        Sensor sensor2 = new Sensor("sensor2", new TypeSensor("Rain"), new Local(4, 4), new GregorianCalendar(8, 8, 8).getTime(), readingList);
        sensorList2.addSensor(sensor1);
        sensorList2.addSensor(sensor2);

        //TEST ROOMS
        RoomList roomList1 = new RoomList();
        RoomList roomList2 = new RoomList();
        Room room1 = new Room("room1", 19, 23456789, sensorList2);
        Room room2 = new Room("kitchen", 8, 2, sensorList2);
        roomList1.addRoom(room2);
        roomList1.addRoom(room1);
        RoomList roomListGrid = new RoomList();

        //TEST GEOGRAPHIC AREAS
        GeographicArea geoa1 = new GeographicArea("porto", new TypeArea("cidade"), new Local(4, 4));
        GeographicArea geoa2 = new GeographicArea("lisboa", new TypeArea("aldeia"), new Local(4, 4));

        //TEST HOUSES
        HouseList houseList1 = new HouseList();
        HouseList houseList2 = new HouseList();

        House house1 = new House("houseOne", "Address1", new Local(22, 3), "4150-657", roomList1);
        House house2 = new House("houseTwo", "Address2", new Local(4, 4), "3456-123");
        House house3 = new House("houseThree", "Address3", new Local(18, 10), "3555-555");
        house2.setmMotherArea(geoa1);
        house3.setmMotherArea(geoa1);
        houseList1.addHouseToHouseList(house2);
        houseList1.addHouseToHouseList(house3);
        house1.setmMotherArea(geoa2);
        houseList2.addHouseToHouseList(house1);





        //TEST ORGANIZATION SETTERS
        eg1.setListOfRooms(roomListGrid);
        house1.setmEGList(energyGridList2);

        geoa1.setSensorList(sensorList2);
        geoa1.setHouseList(houseList1);
        geoa2.setSensorList(sensorList2);
        geoa2.setHouseList(houseList2);

        mGeographicAreaList.addGeographicAreaToGeographicAreaList(geoa1);
        mGeographicAreaList.addGeographicAreaToGeographicAreaList(geoa2);


        //MAIN CODE
        Scanner enterToReturnToConsole = new Scanner(System.in);
        Scanner scanner = new Scanner(System.in);

        int option;
        int loginFlag = 0;
        String loginAdmin;
        String loginRegular;
        boolean activeLogin = false;

        // Login Input

        while (!activeLogin) {

            System.out.println("User Login");
            System.out.println("Login:");
            String login = scanner.nextLine();
            System.out.println("\nPassword:");
            String password = scanner.nextLine();

            // Login Definition

            if ("admin".equals(login) && "admin".equals(password)) {
                loginFlag = 1;
                activeLogin = true;
            } else if ("user".equals(login) && "user".equals(password)) {
                loginFlag = 2;
                activeLogin = true;
            } else System.out.println("Invalid user\n");
        }
        System.out.println("User Flag is:\n" + loginFlag);


        while (activeProgram) {

            System.out.println("\n***************************************************\n" +
                    "***************** Main Menu Test ******************\n" +
                    "****************** sWitCh 2018 ********************\n" +
                    "***************************************************\n");

            // Submenus Input selection

            String[] menu = {" 0. Exit.\n",
                    "1. Create a new Type of Geographic Area.\n",
                    "2. List all area Types.\n",
                    "3. Create a new Geographic Area.\n",
                    "4. List All Geographic Areas of a certain type.\n",
                    "5. Determine the type of a sensor.\n",
                    "6. Create a new Sensor and add it to a GA.\n",
                    "7. Say that an area is contained in another area.\n",
                    "8. See if an area is contained in another area.\n",
                    "9. Display all available sensors.\n",
                    "10. Display all available Geographic Areas.\n",
                    "11. Configure a new house.\n",
                    "12. Add a new room to a house.\n",
                    "13. Get Maximum Temperature in a Room in a Given Day.\n",
                    "14. Create an energy grid.\n",
                    "15. Add a power source to an energy grid.\n",
                    "16. See Room List.\n",
                    "17. Get Current Temperature in a House Area.\n",
                    "18. Change Room Settings.\n",
                    "623. Get Average Rainfall in the House Area.\n",
                    "420. Room Configuration.\n",
                    "19. Add A Room to a Energy Grid\n",
                    "20. Menu for Admin only test.\n"};

            System.out.println("Select the task you want to do:");

            String formattedString = Arrays.toString(menu)
                    .replace(",", "")  //remove the commas
                    .replace("[", "")  //remove the right bracket
                    .replace("]", "");  //remove the left bracket

            System.out.print(formattedString);
            System.out.print("\nEnter option number:\n");
            String pressEnter = "\nPress ENTER to return.";
            option = scanner.nextInt();


            switch (option) {
                case 0:
                    activeProgram = false;
                    break;
                case 1:
                    US01UI view1 = new US01UI();
                    view1.runUS01UI(mTypeAreaList);
                    System.out.println(pressEnter);
                    enterToReturnToConsole.nextLine();
                    break;
                case 2:
                    US02UI view2 = new US02UI();
                    view2.runUS02UI(mTypeAreaList);
                    System.out.println(pressEnter);
                    enterToReturnToConsole.nextLine();
                    break;
                case 3:
                    US03UI view3 = new US03UI();
                    view3.run(mGeographicAreaList);
                    System.out.println(pressEnter);
                    enterToReturnToConsole.nextLine();
                    break;
                case 4:
                    US04UI view4 = new US04UI();
                    view4.run(mGeographicAreaList);
                    System.out.println(pressEnter);
                    enterToReturnToConsole.nextLine();
                    break;
                case 5:
                    US05UI view5 = new US05UI();
                    view5.runUS05UI(sensorList1);
                    System.out.println(pressEnter);
                    enterToReturnToConsole.nextLine();
                    break;
                case 6:
                    US06UI view6 = new US06UI(sensorList1, mGeographicAreaList);
                    view6.run();
                    System.out.println(pressEnter);
                    enterToReturnToConsole.nextLine();
                    break;
                case 7:
                    US07UI view7 = new US07UI();
                    view7.run(mGeographicAreaList);
                    System.out.println(pressEnter);
                    enterToReturnToConsole.nextLine();
                    break;
                case 8:
                    US08UI view8 = new US08UI();
                    view8.run(mGeographicAreaList);
                    System.out.println(pressEnter);
                    enterToReturnToConsole.nextLine();
                    break;
                case 9:
                    if (sensorList1.checkIfListIsValid())
                        System.out.println("The list of sensors is empty!");
                    for (Sensor sensor : sensorList1.getSensorList()) {
                        System.out.println(sensor.getName());
                    }
                    System.out.println(pressEnter);
                    enterToReturnToConsole.nextLine();
                    break;
                case 10:
                    if (mGeographicAreaList.checkIfListIsValid())
                        System.out.println("The list of geographic areas is empty!");
                    for (GeographicArea ga : mGeographicAreaList.getGeographicAreaList()) {
                        System.out.println(ga.getName());
                    }
                    System.out.println(pressEnter);
                    enterToReturnToConsole.nextLine();
                    break;
                case 11:
                    if (loginFlag == 1) {
                        US101UI view11 = new US101UI();
                        view11.runUS101(houseList1, mGeographicAreaList);
                        System.out.println(pressEnter);
                        enterToReturnToConsole.nextLine();
                        break;

                    } else System.out.println("No permissions\n");
                    enterToReturnToConsole.nextLine();
                    break;

                case 12:
                    US105UI view12 = new US105UI();
                    view12.run(houseList1);
                    System.out.println(pressEnter);
                    enterToReturnToConsole.nextLine();
                    break;
                case 14:
                    HouseConfigurationUI view14 = new HouseConfigurationUI();
                    view14.runUS130(houseList1);
                    System.out.println(pressEnter);
                    enterToReturnToConsole.nextLine();
                    break;
                case 15:
                    US135UI view15 = new US135UI();
                    view15.run(houseList1);
                    System.out.println(pressEnter);
                    enterToReturnToConsole.nextLine();
                    break;
                case 16:
                    US108UI view16 = new US108UI();
                    view16.run(houseList1);
                    System.out.println(pressEnter);
                    enterToReturnToConsole.nextLine();
                    break;
                case 18:
                    US108UI view18 = new US108UI();
                    view18.run(houseList1);
                    System.out.println(pressEnter);
                    enterToReturnToConsole.nextLine();
                    break;
                case 623:
                    HouseMonitoringUI view623 = new HouseMonitoringUI();
                    view623.run(mGeographicAreaList, roomList1);
                    System.out.println(pressEnter);
                    enterToReturnToConsole.nextLine();
                    break;
                case 420:
                    RoomConfigurationUI viewRC = new RoomConfigurationUI();
                    viewRC.run(mGeographicAreaList, roomList1);
                    System.out.println(pressEnter);
                    enterToReturnToConsole.nextLine();
                    break;
                case 19:
                    US145UI view145 = new US145UI();
                    view145.run(houseList2);
                    System.out.println(pressEnter);
                    enterToReturnToConsole.nextLine();
                    break;
                case 20:
                    if (loginFlag == 1) {
                        System.out.println("Bravo!");
                        System.out.println(pressEnter);
                        enterToReturnToConsole.nextLine();
                        break;
                    } else System.out.println("No permissions");
                    break;
                default:
                    System.out.println("Invalid option. Try again");
                    enterToReturnToConsole.nextLine();
                    break;
            }
        }
    }
}
