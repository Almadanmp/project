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

        // ***************************************************************************
        // ********************* < MOCK DATA FOR TESTING PURPOSES >*******************
        // ***************************************************************************

        // Geo List (1)
        GeographicAreaList geographicalAreaList = new GeographicAreaList();

        // Geo Areas (4)

        GeographicArea ISEP = new GeographicArea("ISEP", new TypeArea("urban area"), 52,5,new Local(41.178553, -8.608035, 111));
        ISEP.setWidth(0.261);
        ISEP.setLength(0.249);
        ISEP.setDescription("Campus do ISEP");

        GeographicArea Porto = new GeographicArea("Porto", new TypeArea("city"), 31,6,new Local(41.164077, -8.620802, 118));
        Porto.setWidth(10.09);
        Porto.setLength(10.09);
        Porto.setDescription("City of Porto");

        GeographicArea Portugal = new GeographicArea("Portugal", new TypeArea("pais"), 22,9,new Local(60, 0, 110));
        Portugal.setWidth(100);
        Portugal.setLength(800);
        Portugal.setDescription("Country of Portugal");

        GeographicArea PortoSanto = new GeographicArea("Porto Santo", new TypeArea("city"), 42,12,new Local(-45, 67, 67));
        PortoSanto.setWidth(156);
        PortoSanto.setLength(235);
        PortoSanto.setDescription("City of Porto Santo");

        geographicalAreaList.addGeographicAreaToGeographicAreaList(ISEP);
        geographicalAreaList.addGeographicAreaToGeographicAreaList(Porto);
        geographicalAreaList.addGeographicAreaToGeographicAreaList(Portugal);
        geographicalAreaList.addGeographicAreaToGeographicAreaList(PortoSanto);

        //fixme : setDescription() tests missing **/


        //Rooms

        //Edificio B Rooms
        Room roomISEP1 = new Room("B107", 1, 7, 11, 3.5);
        Room roomISEP2 = new Room("B109", 1, 7, 11, 3.5);
        Room roomISEP3 = new Room("B106", 1, 7, 13, 3.5);
        RoomList roomListEdifB = new RoomList();
        roomListEdifB.addRoom(roomISEP1);
        roomListEdifB.addRoom(roomISEP2);
        roomListEdifB.addRoom(roomISEP3);

        // Houses (1 per Geographical Area!)

        House EdificioB = new House("Edificio B", "Rua Dr António Bernardino de Almeida, 431", "4200-072", "Porto", new Local(41.177748, -8.607745, 112), ISEP, roomListEdifB);


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

        SensorList ISEPSensorList = new SensorList();
        ISEPSensorList.addSensor(sensorRainfallISEP);
        ISEPSensorList.addSensor(sensorTemperatureISEP);
        ISEP.setSensorList(ISEPSensorList);


        // Energy Grid

        EnergyGrid mainGrid = new EnergyGrid("main grid", 0);
        EnergyGridList EnergyGridListISEP = new EnergyGridList();
        EnergyGridListISEP.addEnergyGridToEnergyGridList(mainGrid);
        mainGrid.setListOfRooms(roomListEdifB);

        // Type  Area List

        TypeAreaList mTypeAreaList = new TypeAreaList();
        TypeArea typeAreaA = new TypeArea("city");
        TypeArea typeAreaB = new TypeArea("country");
        mTypeAreaList.addTypeArea(typeAreaA);
        mTypeAreaList.addTypeArea(typeAreaB);

        // **************************************************************************
        // ******************* < / MOCK DATA FOR TESTING PUPOSES > ******************
        // **************************************************************************

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
                    "1. Geographic Area Settings\n",
                    "2. House Settings.\n",
                    "3. Room Settings.\n",
                    "4. Sensor Settings.\n",
                    "5. Energy Grid Settings.\n",
                    "6. House Monitoring.\n",};

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
                        GASettingsUI view1 = new GASettingsUI();
                        view1.run(geographicalAreaList, mTypeAreaList);
                        returnToMenu(enterToReturnToConsole);
                        activeInput = true;
                        break;
                   case 2:
                       HouseConfigurationUI houseC = new HouseConfigurationUI();
                       houseC.run(geographicalAreaList, mTypeAreaList, EdificioB);
                       returnToMenu(enterToReturnToConsole);
                       activeInput = true;
                       break;
                    case 3:
                        RoomConfigurationUI roomConfiguration = new RoomConfigurationUI();
                        roomConfiguration.run(geographicalAreaList, EdificioB);
                        returnToMenu(enterToReturnToConsole);
                        activeInput = true;
                        break;
                    case 4:
                        SensorSettingsUI sensorSettings = new  SensorSettingsUI();
                        sensorSettings.run(geographicalAreaList);
                        returnToMenu(enterToReturnToConsole);
                        activeInput = true;
                        break;
                      case 5:
                        EnergyGridSettingsUI energyGridSettings = new EnergyGridSettingsUI();
                        energyGridSettings.run(geographicalAreaList,mTypeAreaList, EdificioB);
                        returnToMenu(enterToReturnToConsole);
                        activeInput = true;
                        break;
                    case 6:
                        HouseMonitoringUI houseM = new HouseMonitoringUI();
                        houseM.run(geographicalAreaList, EdificioB);
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

    private static void returnToMenu(Scanner scanner) {
        String pressEnter = "\nPress ENTER to return.";
        System.out.println(pressEnter);
        scanner.nextLine();
    }
}