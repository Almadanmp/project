package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controller.US101Controller;


import pt.ipp.isep.dei.project.model.GeographicArea;
import pt.ipp.isep.dei.project.model.GeographicAreaList;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.HouseList;

import java.util.List;
import java.util.Scanner;

/**
 * As an Administrator, I want to configure the location of the house.
 **/

public class US101UI {
    private int indexOfHouse;
    private double mHouseLat;
    private double mHouseLon;
    private String mHouseAddress;
    private String mHouseZipCode;
    private boolean mActive;
    private House mHouse;
    US101Controller ctrl101;
    private String geoName;
    private GeographicArea mGeoArea;
    private static final String INVALID_OPTION = "Please enter a valid option";


    US101UI() {
        mActive = false;
    }

    public void runUS101(HouseList listOfHouses, GeographicAreaList list) {
        this.ctrl101 = new US101Controller(listOfHouses);
        while (this.mActive = true) {
            if (list == null || list.getGeographicAreaList().isEmpty()) {
                System.out.println("Invalid Geographic Area List - List Is Empty");
                return;
            }

            if (!getInputGeographicArea(list)) {
                return;
            }
            if (!getInputHouse(list)) {
                if (mHouse == null) {
                    System.out.println("Unable to select a house. Returning to main menu.");
                    return;
                }
                return;
            }
            getInputHouse();
            updateModelUS101(listOfHouses);
            displayStateUS101();
            return;
        }
    }

    private boolean getInputGeographicArea(GeographicAreaList newGeoListUi) {
        System.out.println(
                "We need to know where your house is located\n" + "Would you like to:\n" + "1) Type the Geographic Area name;\n" + "2) Choose it from a list;\n" +
                        "0) Return;");
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();
        switch (option) {
            case "1":
                getInputGeographicAreaName();
                if (!getGeographicAreaByName(newGeoListUi)) {
                    System.out.println("Unable to select a Geographic Area. Returning to main menu.");
                    return false;
                }
                break;
            case "2":
                getInputGeographicAreaByList(newGeoListUi);
                break;
            case "0":
                return false;
            default:
                System.out.println(INVALID_OPTION);
                break;
        }
        return true;
    }

    private boolean getInputGeographicAreaName() {
        System.out.println("Please type the name of the Geographic Area Where Your House Is Located.");
        Scanner scanner = new Scanner(System.in);
        this.geoName = scanner.nextLine();
        return (!("exit".equals(geoName)));
    }

    private boolean getGeographicAreaByName(GeographicAreaList newGeoListUi) {
        US101Controller ctrl = new US101Controller(newGeoListUi);
        List<Integer> listOfIndexesGeographicAreas = ctrl.matchGeographicAreaIndexByString(geoName, newGeoListUi);

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
        US101Controller ctrl = new US101Controller(newGeoListUi);
        boolean activeInput = false;
        System.out.println("Please select the Geographic Area in which your House is in from the list: ");

        while (!activeInput) {
            ctrl.printGAList(newGeoListUi);
            int aux = readInputNumberAsInt();
            if (aux >= 0 && aux < newGeoListUi.getGeographicAreaList().size()) {
                mGeoArea = newGeoListUi.getGeographicAreaList().get(aux);
                activeInput = true;
            } else {
                System.out.println(INVALID_OPTION);
            }
        }
    }

    private boolean getInputHouse(GeographicAreaList newGeoListUi) {
        US101Controller ctrl = new US101Controller(newGeoListUi);
        if (mGeoArea.getHouseList().getHouseList().isEmpty()) {
            System.out.print("Invalid House List - List Is Empty\n/**/");
            return false;
        }

        boolean activeInput = false;
        System.out.println("Please select one of the existing houses on the selected geographic area: ");

        while (!activeInput) {
            ctrl.printHouseList(mGeoArea);
            this.indexOfHouse = readInputNumberAsInt();
            if (indexOfHouse >= 0 && indexOfHouse< mGeoArea.getHouseList().getHouseList().size()) {
                mHouse = mGeoArea.getHouseList().getHouseList().get(indexOfHouse);
                activeInput = true;
            } else {
                System.out.println(INVALID_OPTION);
            }
        }
        return true;
    }


    private int readInputNumberAsInt() {
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNextDouble()) {
            System.out.println(INVALID_OPTION);
            scanner.next();
        }
        Double option = scanner.nextDouble();
        return option.intValue();
    }

    private void getInputHouse() {

        Scanner scanner = new Scanner(System.in);

        //gethouseaddress
        System.out.print("Please, type the address of the house: ");
        this.mHouseAddress = scanner.nextLine();


        //getzipcode
        System.out.print("Please, type the Zip Code of the house: ");
        this.mHouseZipCode = scanner.nextLine();


        //getlatitude
        System.out.print("Please, type the latitude: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Please,try again. Only numbers this time:");
            scanner.next();
        }
        this.mHouseLat = scanner.nextDouble();


        //getlongitude
        System.out.print("Please, type the latitude: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Please,try again. Only numbers this time:");
            scanner.next();
        }
        this.mHouseLon = scanner.nextDouble();

    }

    private void updateModelUS101(HouseList listOfHouses) {
        US101Controller ctrl = new US101Controller(listOfHouses);
        ctrl.setHouseLocal(mHouseLat, mHouseLon, indexOfHouse);
        ctrl.setHouseZIPCode(mHouseZipCode, indexOfHouse);
        ctrl.setHouseAddress(mHouseAddress, indexOfHouse);
    }

    private void displayStateUS101() {
        System.out.println("You have successfully changed the location of the house " + mHouse.getHouseDesignation() + ". \n" + "Address: " +
                mHouseAddress + ". \n" + "ZipCode: " + mHouseZipCode + ". \n" + "Latitude: " + mHouseLat + ". \n" +
                "Longitude: " + mHouseLon + ". \n");
    }
}


