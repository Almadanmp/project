package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controller.HouseMonitoringController;
import pt.ipp.isep.dei.project.controller.RoomConfigurationController;
import pt.ipp.isep.dei.project.model.*;

import java.util.List;
import java.util.Scanner;

public class RoomConfigurationUI {
    private Scanner mScanner;
    private House mHouse;
    private GeographicArea mGeoArea;
    private String geoName;
    private RoomConfigurationController mRoomConfigurationController;
    private HouseMonitoringController mHouseMonitoringcontroller;
    private static final String INVALID_OPTION = "Please enter a valid option";
    private String mNameRoom;
    private Room mRoom;
    private Sensor mSensor;
    private List<Integer> listOfIndexesGeographicAreas;
    private String mNameSensor;
    private SensorList mSensorList;
    private GeographicAreaList mGeoList;
    private RoomList mRoomList;

    public RoomConfigurationUI() {
        this.mScanner = new Scanner(System.in);
        this.mRoomConfigurationController = new RoomConfigurationController();
        this.mHouseMonitoringcontroller = new HouseMonitoringController();

    }

    public void run(GeographicAreaList newGeoListUi, RoomList roomList) {
        this.mGeoList = newGeoListUi;
        this.mRoomList = roomList;
        if (newGeoListUi == null || newGeoListUi.getGeographicAreaList().size() == 0) {
            System.out.println("Invalid Geographic Area List - List Is Empty");
            return;
        }
        boolean activeInput = false;
        String option;
        System.out.println("--------------\n");
        System.out.println("Room Configuration\n");
        System.out.println("--------------\n");
        getInputGeographicArea(newGeoListUi);
        getInputHouse();
        if (mHouse == null) {
            System.out.println("Unable to select a house. Returning to main menu.");
            return;
        }
        while (!activeInput) {
            printOptionMessage();
            option = mScanner.next();
            switch (option) {
                case "1":
                    if (!getInputRoom(roomList)) {
                        return;
                    }
                    if (!getInputSensorName(newGeoListUi)) {
                        return;
                    }
                    updateModelRoomConfiguration();
                    displayStateRoomConfiguration();
                    activeInput = true;
                    break;
                case "0":
                    return;

                default:
                    System.out.println(INVALID_OPTION);
                    break;
            }
            }
    }

    private boolean getInputRoom(RoomList list) {
        HouseMonitoringController ctrl = new HouseMonitoringController();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please insert the name of the Room you want to add the sensor to: ");
        this.mNameRoom = scanner.next();
        if (ctrl.doesListContainRoomByName(this.mNameRoom,list)) {
            System.out.println("You chose the Room " + this.mNameRoom);
        } else {
            System.out.println("This room does not exist in the list of rooms.");
            return false;
        }
        return true;
    }
    private void printOptionMessage() {
        System.out.println("Room Configuration Options:\n");
        System.out.println("1) Add a sensor to a Room");
        System.out.println("0) (Return to main menu)");
    }

    private boolean getInputSensorName(GeographicAreaList newGeoListUi) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please insert the name of the Sensor you want to add to " +mNameRoom + " : ");
        this.mNameSensor = scanner.next();
        RoomConfigurationController ctrl = new RoomConfigurationController();
        if (ctrl.doesSensorListInAGeoAreaContainASensorByName(this.mNameSensor,newGeoListUi)) {
            System.out.println("You chose the Sensor " + this.mNameSensor);
        } else {
            System.out.println("This sensor does not exist in the list of sensors.");
            return false;
        }
        return true;
    }

    private void getInputGeographicArea(GeographicAreaList newGeoListUi) {
        System.out.println(
                "We need to know where your house is located\n" + "Would you like to:\n" + "1) Type the Geographic Area name;\n" + "2) Choose it from a list;\n" +
                        "0) Return;");
        String option = mScanner.nextLine();
        switch (option) {
            case "1":
                getInputGeographicAreaName();
                if (!getGeographicAreaByName(newGeoListUi)) {
                    System.out.println("Unable to select a Geographic Area. Returning to main menu.");
                    return;
                }
                break;
            case "2":
                getInputGeographicAreaByList(newGeoListUi);
                break;
            case "0":
                return;
            default:
                System.out.println(INVALID_OPTION);
                break;
        }
    }

    private boolean getInputGeographicAreaName() {
        System.out.println("Please type the name of the Geographic Area Where Your House Is Located.");
        this.geoName = mScanner.nextLine();
        return (!(geoName.equals("exit")));
    }

    private boolean getGeographicAreaByName(GeographicAreaList newGeoListUi) {
        HouseMonitoringController ctrl = new HouseMonitoringController();
        listOfIndexesGeographicAreas = ctrl.matchGeographicAreaIndexByString(geoName, newGeoListUi);

        while (listOfIndexesGeographicAreas.isEmpty()) {
            System.out.println("There is no Geographic Area with that name. Please insert the name of a Geographic Area" +
                    " that exists or  Type 'exit' to cancel and create a new Geographic Area on the Main Menu.");
            if (!getInputGeographicAreaName()) {
                return false;
            }
            listOfIndexesGeographicAreas = ctrl.matchGeographicAreaIndexByString(geoName, newGeoListUi);
        }

        if (listOfIndexesGeographicAreas.size() > 1) {
            System.out.println("There are multiple Geographic Areas with that name. Please choose the right one.");
            System.out.println(ctrl.printGeoGraphicAreaElementsByIndex(listOfIndexesGeographicAreas, newGeoListUi));
            int aux = readInputNumberAsInt();
            if (listOfIndexesGeographicAreas.contains(aux)) {
                mGeoArea = newGeoListUi.getGeographicAreaList().get(aux);
                System.out.println("You have chosen the following Geographic Area:");
                System.out.println(ctrl.printGA(mGeoArea));
            } else {
                System.out.println(INVALID_OPTION);
            }
        } else {
            System.out.println("You have chosen the following Geographic Area:");
            mGeoArea = newGeoListUi.getGeographicAreaList().get(listOfIndexesGeographicAreas.get(0));
            System.out.println(ctrl.printGA(mGeoArea));
        }
        return true;
    }

    private void getInputGeographicAreaByList(GeographicAreaList newGeoListUi) {
        boolean activeInput = false;
        System.out.println("Please select the Geographic Area in which your House is in from the list: ");

        while (!activeInput) {
            mHouseMonitoringcontroller.printGAList(newGeoListUi);
            int aux = readInputNumberAsInt();
            if (aux >= 0 && aux < newGeoListUi.getGeographicAreaList().size()) {
                mGeoArea = newGeoListUi.getGeographicAreaList().get(aux);
                activeInput = true;
            } else {
                System.out.println(INVALID_OPTION);
            }
        }
    }

    private int readInputNumberAsInt() {

        while (!mScanner.hasNextDouble()) {
            System.out.println(INVALID_OPTION);
            mScanner.next();
        }
        Double option = mScanner.nextDouble();
        return option.intValue();
    }

    private void getInputHouse() {
        if (mGeoArea.getHouseList().getHouseList().size() == 0) {
            System.out.print("Invalid House List - List Is Empty\n/**/");
            return;
        }

        boolean activeInput = false;
        System.out.println("Please select one of the existing houses on the selected geographic area: ");

        while (!activeInput) {
            mHouseMonitoringcontroller.printHouseList(mGeoArea);
            int aux = readInputNumberAsInt();
            if (aux >= 0 && aux < mGeoArea.getHouseList().getHouseList().size()) {
                mHouse = mGeoArea.getHouseList().getHouseList().get(aux);
                activeInput = true;
            } else {
                System.out.println(INVALID_OPTION);
            }
        }
    }

    public void updateModelRoomConfiguration() {
        RoomConfigurationController ctrl = new RoomConfigurationController();
        this.mRoom = ctrl.getRoomFromName(mNameRoom,mHouse);
        this.mSensor = ctrl.getSensorFromName(mNameSensor,mGeoArea);
        ctrl.addSensorToRoom(mRoom,mSensor);
    }

    private void displayStateRoomConfiguration() {
        System.out.print("Sensor " + mSensor.getName() + " was successefully added to " + this.mNameRoom);
    }
}
