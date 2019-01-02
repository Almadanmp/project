package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controller.US600Controller;
import pt.ipp.isep.dei.project.model.GeographicArea;
import pt.ipp.isep.dei.project.model.GeographicAreaList;
import pt.ipp.isep.dei.project.model.House;

import java.util.List;
import java.util.Scanner;

public class US600UI {
    private boolean active;
    private String geoName;
    private GeographicArea mGeoArea;
    private List<Integer> listOfIndexesGeographicAreas;
    private House mHouse;
    private static final String INVALID_OPTION = "Please enter a valid option";
    private double mCurrentHouseAreaTemperature;

    public US600UI() {
        this.active = false;
    }

    public void run(GeographicAreaList list) {

        while (this.active = true) {
            if (list == null || list.getGeographicAreaList().size() == 0) {
                System.out.println("Invalid Geographic Area List - List Is Empty");
                return;
            }

            if (!getInputGeographicArea(list)) {
                return;
            }
            if(!getInputHouse(list)){
                if (mHouse == null) {
                    System.out.println("Unable to select a house. Returning to main menu.");
                    return;
                }
                return;
            }
            updateModel();
            displayState();
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
        return (!(geoName.equals("exit")));
    }

    private boolean getGeographicAreaByName(GeographicAreaList newGeoListUi) {
        US600Controller ctrl = new US600Controller(newGeoListUi);
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
        US600Controller ctrl = new US600Controller(newGeoListUi);
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
        US600Controller ctrl = new US600Controller(newGeoListUi);
        if (mGeoArea.getHouseList().getHouseList().size() == 0) {
            System.out.print("Invalid House List - List Is Empty\n/**/");
            return false;
        }

        boolean activeInput = false;
        System.out.println("Please select one of the existing houses on the selected geographic area: ");

        while (!activeInput) {
            ctrl.printHouseList(mGeoArea);
            int aux = readInputNumberAsInt();
            if (aux >= 0 && aux < mGeoArea.getHouseList().getHouseList().size()) {
                mHouse = mGeoArea.getHouseList().getHouseList().get(aux);
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

    public void updateModel(){
        US600Controller ctrl = new US600Controller(mHouse);
        mCurrentHouseAreaTemperature = ctrl.getCurrentTemperatureInTheHouseArea(mHouse,mGeoArea);
    }

    public void displayState(){
        System.out.println("The current temperature in the house area is: " + mCurrentHouseAreaTemperature + "°C.");
    }

}
