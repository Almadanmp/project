package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controller.HouseConfigurationController;
import pt.ipp.isep.dei.project.model.*;

import java.util.List;
import java.util.Scanner;

class HouseConfigurationUI {
    HouseConfigurationController controller;

     /**
      * US001UI
      */

     private boolean mTypeAreaList;
     private String mTypeArea;

     void runUS01UI(TypeAreaList list) {
         getInputUS01();
         updateModelUS01(list);
         displayStateUS01();
     }

     private void getInputUS01() {
         Scanner scanner = new Scanner(System.in);
         System.out.print("Please insert the name of the new Geographic Area Type: ");
         while (!scanner.hasNext("[a-zA-Z_]+")) {
             System.out.println("That's not a valid name a Type Area. Please insert only Alphabetic Characters");
             scanner.next();
         }
         this.mTypeArea = scanner.next();
     }

     private void updateModelUS01(TypeAreaList list) {
         HouseConfigurationController ctrl = new HouseConfigurationController(list);
         this.mTypeAreaList = ctrl.createAndAddTypeAreaToList(mTypeArea);
     }

     private void displayStateUS01() {
         if (mTypeAreaList) {
             System.out.println("Success, you have inserted a new Type of Geographic Area.");
         } else {
             System.out.println("Failed, you have inserted an invalid or repeated Type of Geographic Area.");
         }
     }

    /**
     * US101 UI
     */

    private int indexOfHouse;
    private double mHouseLat;
    private double mHouseLon;
    private String mHouseAddress;
    private String mHouseZipCode;
    private House mHouse;
    private String geoName;
    private GeographicArea mGeoArea;
    private static final String INVALID_OPTION = "Please enter a valid option";


    public void runUS101(HouseList listOfHouses, GeographicAreaList list) {
        this.controller = new HouseConfigurationController(listOfHouses);

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
        HouseConfigurationController ctrl = new HouseConfigurationController(newGeoListUi);
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
        HouseConfigurationController ctrl = new HouseConfigurationController(newGeoListUi);
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
        HouseConfigurationController ctrl = new HouseConfigurationController(newGeoListUi);
        if (mGeoArea.getHouseList().getHouseList().isEmpty()) {
            System.out.print("Invalid House List - List Is Empty\n/**/");
            return false;
        }

        boolean activeInput = false;
        System.out.println("Please select one of the existing houses on the selected geographic area: ");

        while (!activeInput) {
            ctrl.printHouseList(mGeoArea);
            this.indexOfHouse = readInputNumberAsInt();
            if (indexOfHouse >= 0 && indexOfHouse < mGeoArea.getHouseList().getHouseList().size()) {
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
        HouseConfigurationController ctrl = new HouseConfigurationController(listOfHouses);
        ctrl.setHouseLocal(mHouseLat, mHouseLon, indexOfHouse);
        ctrl.setHouseZIPCode(mHouseZipCode, indexOfHouse);
        ctrl.setHouseAddress(mHouseAddress, indexOfHouse);
    }

    private void displayStateUS101() {
        System.out.println("You have successfully changed the location of the house " + mHouse.getHouseDesignation() + ". \n" + "Address: " +
                mHouseAddress + ". \n" + "ZipCode: " + mHouseZipCode + ". \n" + "Latitude: " + mHouseLat + ". \n" +
                "Longitude: " + mHouseLon + ". \n");
    }


    /**
     * US 130 UI
     */

    void runUS130(HouseList houseList) {
        this.controller = new HouseConfigurationController(houseList);
        getInputHouseName();
        getInputAndAddEnergyGrid();
        updateEnergyGridList();
    }

    private void getInputHouseName() {
        System.out.println("Please insert the house name you want To create an energy grid on: ");
        Scanner scanner = new Scanner(System.in);
        String houseName = scanner.nextLine();
        if (controller.seeIfHouseExistsInHouseList(houseName)) {
            System.out.println("The House you have inserted is on the List.");
        } else {
            System.out.println("The House you have inserted is not on the List.");
        }
    }

    private void getInputAndAddEnergyGrid() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type the designation of the energy grid you want to create: ");
        String name = scanner.next();
        System.out.println("Set the maximum potency of this energy grid: ");
        double maxPower = scanner.nextDouble();
        controller.createEnergyGrid(name, maxPower);
    }

    private void updateEnergyGridList() {
        if (controller.addEnergyGridToHouse()) {
            System.out.println("The energy grid was successfully added to the selected house.");
        } else {
            System.out.println("The energy grid was NOT added to the selected house.");
        }
    }

    /**
     * US135 UI
     */

    void runUS135(HouseList houseList) {
        this.controller = new HouseConfigurationController(houseList);
        getInputAndUpdateHouseName();
        getInputAndSelectEnergyGrid();
        getInputAndCreatePowerSource();
        updateModelAndDisplayState();
    }

    private void getInputAndUpdateHouseName() {
        System.out.println("Please insert the house name that you want to add a power source to one of its energy grids: ");
        Scanner scanner = new Scanner(System.in);
        String houseName = scanner.nextLine();
        if (controller.seeIfHouseExistsInHouseList(houseName)) {
            System.out.println("The house you have inserted is on the list.");
        } else {
            System.out.println("The house you have inserted is not on the list.");
        }
    }

    private void getInputAndSelectEnergyGrid() {
        System.out.println(controller.seeIfEnergyGridListIsEmptyAndShowItsContent());
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type the designation of the energy grid you want to add a power source to: ");
        String name = scanner.next();
        if (controller.selectEnergyGrid(name)) {
            System.out.println("The energy grid was selected with success.");
        }
    }

    private void getInputAndCreatePowerSource() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type the designation of the power source you want to add: ");
        String name = scanner.next();
        System.out.println("Type the maximum power output of the power source you want to add: ");
        double maxPowerOutput = scanner.nextDouble();
        System.out.println("Type the maximum energy storage of the power source you want to add (type 0 if the power source can't storage energy.): ");
        double maxEnergyStorage = scanner.nextDouble();
        controller.createPowerSource(name, maxPowerOutput, maxEnergyStorage);
    }

    private void updateModelAndDisplayState() {
        if (controller.addPowerSourceToEnergyGrid()) {
            System.out.println("The power source was added with success!");
        } else {
            System.out.println("The power source was NOT added to the energy grid!");
        }
    }

    /**
     * US005
     */

    private String mTypeSensor;
    private String mNameSensor;
    private boolean mActive;
    private boolean mTypeAdded;

    public void runUS05(SensorList list) {
        this.mActive = true;
        while (this.mActive) {
            getInput05Sensor05();
            getInput05();
            updateModel05(list);
            displayState05();
        }
    }

    private void getInput05Sensor05() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Type the name of the sensor to add the type to: ");
        this.mNameSensor = scanner.next();
    }

    private void getInput05() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Type the type of sensor you want to assign to the sensor: ");
        while (!scanner.hasNext("[a-zA-Z_]+")) {
            System.out.println("That's not a valid name of Type Area. Please insert only Alphabetic Characters");
            scanner.next();
        }
        this.mTypeSensor = scanner.next();
    }

    private void updateModel05(SensorList list) {
        HouseConfigurationController controller = new HouseConfigurationController(list);
        this.mTypeAdded = controller.setTypeSensor(mNameSensor, mTypeSensor);
    }

    private void displayState05() {
        if (mTypeAdded) {
            System.out.print("The type has been successfully assigned.");
        } else System.out.print("The type of sensor wasn't added. There's no sensor with that name.");
        mActive = false;
    }
}
