package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controller.GASettingsController;
import pt.ipp.isep.dei.project.controller.HouseConfigurationController;
import pt.ipp.isep.dei.project.model.GeographicArea;
import pt.ipp.isep.dei.project.model.GeographicAreaList;
import pt.ipp.isep.dei.project.model.TypeArea;
import pt.ipp.isep.dei.project.model.TypeAreaList;

import java.util.List;
import java.util.Scanner;

import static pt.ipp.isep.dei.project.io.ui.UtilsUI.INVALID_OPTION;
import static pt.ipp.isep.dei.project.io.ui.UtilsUI.readInputNumberAsInt;

public class GASettingsUI {
    private GASettingsController mController;
    private GeographicArea mGeoArea;
    private String mTypeAreaName;
    private TypeArea mTypeGA;
    private boolean mTypeAreaListCreated;
    private double geoAreaLat;
    private double geoAreaLong;
    private double geoAreaAlt;
    private double geoAreaLength;
    private double geoAreaWidth;
    private boolean areaAddedResult;
    private String nameOfGeoArea;
    private String mNameGeographicAreaDaughter;
    private String mNameGeographicAreaMother;
    private String geoName;
    private String mNameGeographicAreaContained;
    private String mNameGeographicAreaContainer;

    public GASettingsUI() {
        this.mController = new GASettingsController();
    }

    public void run(GeographicAreaList newGeoListUi, TypeAreaList typeAreaList) {
        if (newGeoListUi == null || newGeoListUi.getGeographicAreaList().size() == 0) {
            System.out.println("Invalid Geographic Area List - List Is Empty");
            return;
        }
        boolean activeInput = false;
        int option;
        System.out.println("--------------\n");
        System.out.println("Geographic Area Settings\n");
        System.out.println("--------------\n");
        while (!activeInput) {
            printOptionMessage();
            option = UtilsUI.readInputNumberAsInt();
            switch (option) {
                case 1:
                    getInputUS01();
                    updateModelUS01(typeAreaList);
                    displayStateUS01();
                    activeInput = true;
                    break;

                case 2:
                    updateModelUS02(typeAreaList);
                    displayStateUS02();
                    activeInput = true;
                    break;
                case 3:
                    getInputNameNewAreaUS03();
                    getInputTypeOfAreaUS03();
                    getLocalGeoAreaUS03();
                    updateGeoAreaUS03(typeAreaList);
                    updateModelUS03(newGeoListUi);
                    displayStateUS03();
                    activeInput = true;
                    break;
                case 4:
                    getInputTypeArea(typeAreaList);
                    if (!matchGAByTypeArea(newGeoListUi)) {
                        return;
                    } else {
                        displayGAListByTypeArea(newGeoListUi);
                    }
                    activeInput = true;
                    break;
                case 5:
                    getInputGeographicArea(newGeoListUi);
                    this.mNameGeographicAreaMother = mGeoArea.getId();
                    displayGeoArea(mNameGeographicAreaMother, newGeoListUi);
                    getInputGeographicArea(newGeoListUi);
                    this.mNameGeographicAreaDaughter = mGeoArea.getId();
                    displayGeoArea(mNameGeographicAreaDaughter, newGeoListUi);
                    updateStateUS07(newGeoListUi);
                    displayStateUS07();
                    activeInput = true;
                    break;
                case 6:
                    getInputGeographicContainerUS08(newGeoListUi);
                    getInputGeographicContainedUS08(newGeoListUi);
                    verifyAndDisplayStateUS08(newGeoListUi);
                    activeInput = true;
                    break;
                case 0:
                    return;
                default:
                    System.out.println(INVALID_OPTION);
                    break;
            }
        }
    }

    // OPÇÃO LISTAR POR NOMES / POR LISTA - TYPE AREA

    private void getInputTypeArea(TypeAreaList typeAreaList) {
        // this.controller = new HouseConfigurationController(typeAreaList);
        System.out.println(
                "We need to know what is the type of Geographic Area you want.\n" + "Would you like to:\n" + "1)Type the Geographic Area Type name;\n" + "2) Choose it from a list;\n" +
                        "0) Return;");
        boolean activeInput = false;
        while (!activeInput) {
            int option = readInputNumberAsInt();
            switch (option) {
                case 1:
                    getInputTypeAreaName();
                    if (!getTypeAreaByName(typeAreaList)) {
                        System.out.println("Unable to select a Geographic Area. Returning to main menu.");
                        return;
                    }
                    activeInput = true;
                    break;
                case 2:
                    getInputTypeAreaByList(typeAreaList);
                    activeInput = true;
                    break;
                case 0:
                    return;
                default:
                    System.out.println(INVALID_OPTION);
                    break;
            }
        }
    }


    //GET INPUT TYPE AREA NAME BY NAME OR BY LIST//
    private boolean getInputTypeAreaName() {
        Scanner mScanner = new Scanner(System.in);
        System.out.println("Please type the name of the Geographic Area Type Where: ");
        this.mTypeAreaName = mScanner.nextLine();
        return (!(this.mTypeAreaName.equals("exit")));
    }

    private boolean getTypeAreaByName(TypeAreaList typeAreaList) {
        List<Integer> listOfIndexesTypeArea = mController.matchTypeAreaIndexByString(this.mTypeAreaName, typeAreaList);

        while (listOfIndexesTypeArea.isEmpty()) {
            System.out.println("There is no Geographic Area Type with that name. Please insert the name of a Geographic Area Type" +
                    " that exists or type 'exit' to cancel and create a new Geographic Area Type on the Main Menu.");
            if (!getInputTypeAreaName()) {
                return false;
            }
            listOfIndexesTypeArea = mController.matchTypeAreaIndexByString(this.mTypeAreaName, typeAreaList);
        }

        if (listOfIndexesTypeArea.size() > 1) {
            System.out.println("There are multiple Geographic Area Types with that name. Please choose the right one.");
            System.out.println(mController.printTypeAreaElementsByIndex(listOfIndexesTypeArea, typeAreaList));
            int aux = readInputNumberAsInt();
            if (listOfIndexesTypeArea.contains(aux)) {
                this.mTypeGA = typeAreaList.getTypeAreaList().get(aux);
                System.out.println("You have chosen the following Geographic Area Type:");
                System.out.println(mController.printTypeArea(this.mTypeGA));
            } else {
                System.out.println(INVALID_OPTION);
            }
        } else {
            System.out.println("You have chosen the following Geographic Area Type:");
            this.mTypeGA = typeAreaList.getTypeAreaList().get(listOfIndexesTypeArea.get(0));
            System.out.println(mController.printTypeArea(this.mTypeGA));
        }
        return true;
    }


    private void getInputTypeAreaByList(TypeAreaList typeAreaList) {
        boolean activeInput = false;
        System.out.println("Please select the Geographic Area Type from the list: ");

        while (!activeInput) {
            System.out.print(mController.printGATypeList(typeAreaList));
            int aux = readInputNumberAsInt();
            if (aux >= 0 && aux < typeAreaList.getTypeAreaList().size()) {
                this.mTypeGA = typeAreaList.getTypeAreaList().get(aux);
                activeInput = true;
                //TODO fazer um print bonito
                System.out.println("You have chosen the following Geographic Area Type:");
                System.out.println(mController.printTypeArea(this.mTypeGA));
            } else {
                System.out.println(INVALID_OPTION);
            }
        }
    }


    // OPÇÃO LISTAR POR NOMES / POR LISTA - GEOGRAPHIC AREA

    private void getInputGeographicArea(GeographicAreaList newGeoListUi) {
        System.out.println(
                "We need to know what Geographic Area you want to work with. This can be the Geographic Area your house is in, or any Geographic Area you want to edit.\n" + "Would you like to:\n" + "1) Type the Geographic Area name;\n" + "2) Choose it from a list;\n" +
                        "0) Return;");
        boolean activeInput = false;
        while (!activeInput) {
            int option = readInputNumberAsInt();
            switch (option) {
                case 1:
                    getInputGeographicAreaName();
                    if (!getGeographicAreaByName(newGeoListUi)) {
                        System.out.println("Unable to select a Geographic Area. Returning to main menu.");
                        return;
                    }
                    activeInput = true;
                    break;
                case 2:
                    getInputGeographicAreaByList(newGeoListUi);
                    activeInput = true;
                    break;
                case 0:
                    return;
                default:
                    System.out.println(INVALID_OPTION);
                    break;
            }
        }
    }

    private boolean getGeographicAreaByName(GeographicAreaList newGeoListUi) {
        HouseConfigurationController ctrl = new HouseConfigurationController();
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

    private boolean getInputGeographicAreaName() {
        System.out.println("Please type the name of the Geographic Area Where Your House Is Located.");
        Scanner scanner = new Scanner(System.in);
        this.geoName = scanner.nextLine();
        return (!("exit".equals(geoName)));
    }


    private void getInputGeographicAreaByList(GeographicAreaList newGeoListUi) {
        boolean activeInput = false;
        System.out.println("Please select the Geographic Area in which your House is in from the list: ");
        while (!activeInput) {
            GASettingsController controller = new GASettingsController();
            System.out.println(controller.printGAList(newGeoListUi));
            int aux = readInputNumberAsInt();
            if (aux >= 0 && aux < newGeoListUi.getGeographicAreaList().size()) {
                mGeoArea = newGeoListUi.getGeographicAreaList().get(aux);
                activeInput = true;
                System.out.println("You have chosen the following Geographic Area:");
                System.out.println((mGeoArea.printGeographicArea()));
            } else {
                System.out.println(INVALID_OPTION);
            }
        }
    }



/* USER STORY 001 - As an Administrator, I want to add a new type of geographical area, in order to be able to create a
 classification of geographical areas.*/

    private void getInputUS01() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please insert the name of the new Geographic Area Type: ");
        while (!scanner.hasNext("[a-zA-Z_]+")) {
            System.out.println("That's not a valid name a Type Area. Please insert only Alphabetic Characters");
            scanner.next();
        }
        this.mTypeAreaName = scanner.next();
    }

    private void updateModelUS01(TypeAreaList typeAreaList) {
        this.mTypeAreaListCreated = mController.createAndAddTypeAreaToList(mTypeAreaName, typeAreaList);
    }

    private void displayStateUS01() {
        if (mTypeAreaListCreated) {
            System.out.println("Success, you have inserted a new Type of Geographic Area.");
        } else {
            System.out.println("Failed, you have inserted an invalid or repeated Type of Geographic Area.");
        }
    }

    /* USER STORY 002 - As a System Administrator I want to obtain a list of the types of Geographical Areas previously stated.
     * Class responsible for presenting the list.  */
    private void updateModelUS02(TypeAreaList typeAreaList) {
        System.out.println(mController.getTypeAreaList(typeAreaList));
    }

    private void displayStateUS02() {
        System.out.println("\nList finished.");
    }

    /* User Story - 03 As a System Administrator I want to Create a new Geographic Area */
    private void getInputNameNewAreaUS03() {
        this.nameOfGeoArea = readInputString("name");
    }

    private void getInputTypeOfAreaUS03() {
        this.mTypeAreaName = readInputString("Type Area");
    }

    private void getLocalGeoAreaUS03() {
        this.geoAreaLat = readInputNumber("Latitude");
        this.geoAreaLong = readInputNumber("Longitude");
        this.geoAreaAlt = readInputNumber("Altitude");
        this.geoAreaLength = readInputNumber("Comprimento");
        this.geoAreaWidth = readInputNumber("Largura");

    }

    private void updateGeoAreaUS03(TypeAreaList typeAreaList) {
        System.out.print("The Geographic Area you want to create is " + nameOfGeoArea + " with the type " + mTypeAreaName +
                " and its localization is on " + geoAreaLat + " latitude " + geoAreaLong + " longitude. The size is " + this.geoAreaLength
                + " by " + this.geoAreaWidth + " kms\n");
        typeAreaList.newTAG(mTypeAreaName);
    }

    private void updateModelUS03(GeographicAreaList newGeoListUi) {
        this.areaAddedResult = mController.addNewGeoAreaToList(newGeoListUi, nameOfGeoArea, mTypeAreaName, geoAreaLat, geoAreaLong, geoAreaAlt, geoAreaLength, geoAreaWidth);
    }

    private void displayStateUS03() {
        if (areaAddedResult) {
            System.out.print("The Geographic Area has been successfully added.");
        } else
            System.out.print("The Geographic Area hasn't been added to the list. " +
                    "There is already an area with those input values.");
    }

    private String createInputMsg(String inputType) {
        return "Please Insert " + inputType + " for the New Geographic Area: ";
    }

    private String createInvalidStringMsg(String inputType) {
        return "That's not a valid " + inputType + ". Please insert only Alphabetic Characters";
    }

    private String createInvalidNumberMsg(String inputType) {
        return "That's not a valid " + inputType + ". Please insert only Numbers";
    }

    private String readInputString(String inputType) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(createInputMsg(inputType));

        while (!scanner.hasNext("[a-zA-Z\\sà-ùÀ-Ù]*")) {
            System.out.println(createInvalidStringMsg(inputType));
            scanner.next();
        }
        return scanner.next();
    }

    private double readInputNumber(String inputType) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(createInputMsg(inputType));

        while (!scanner.hasNextDouble()) {
            System.out.println(createInvalidNumberMsg(inputType));
            scanner.next();
        }
        return scanner.nextDouble();
    }

    /* USER STORY 04 -  As an Administrator, I want to get a list of existing geographical areas of a given type. */
    private boolean matchGAByTypeArea(GeographicAreaList newGeoListUi) {
        if ((newGeoListUi.getGeographicAreaList().isEmpty()) || (newGeoListUi == null)) {
            System.out.print("The list of Geographic Areas is currently empty.\n Please return to main menu and add a Geographic Area to the list first.");
            return false;
        } else {
            newGeoListUi = mController.matchGAByTypeArea(newGeoListUi, this.mTypeGA);
            this.mTypeAreaName = mController.getTypeAreaName(this.mTypeGA);
            return true;
        }
    }

    private void displayGAListByTypeArea(GeographicAreaList newGeoListUi) {
        if (newGeoListUi.getGeographicAreaList().isEmpty()) {
            System.out.println("There are no Geographic Areas of that Area Type.");
        } else {
            System.out.println("Geographic Areas of the type " + this.mTypeAreaName + ":\n");
            mController.printGAList(newGeoListUi);
        }
    }

    /* USER STORY 07 - */

    private void displayGeoArea(String name, GeographicAreaList newGeoListUi) {
        if (mController.validateGeoArea(name, newGeoListUi)) {
            System.out.println("Success, you have inserted a valid Geographic Area.");
        } else {
            System.out.println("Error! You have inserted a non-existant Geographic Area.");
        }
    }

    private void updateStateUS07(GeographicAreaList newGeoListUi) {
        GeographicArea daughterArea = mController.matchGeoArea(mNameGeographicAreaDaughter, newGeoListUi);
        GeographicArea motherArea = mController.matchGeoArea(mNameGeographicAreaMother, newGeoListUi);
        mController.setMotherArea(daughterArea, motherArea);
    }

    private void displayStateUS07() {
        System.out.print("The Geographic Area " + mNameGeographicAreaDaughter + " is contained in " + mNameGeographicAreaMother + "\n");
    }

/* US08 - As an Administrator, I want to find out if a geographical area is included, directly
or indirectly, in another one. */

    /**
     * getInputGeographicContainer()
     * this method makes the user define the NAME of the GeographicArea CONTAINER
     */
    private void getInputGeographicContainerUS08(GeographicAreaList newGeoListUi) {
        getInputGeographicArea(newGeoListUi);
        this.mNameGeographicAreaContainer = mGeoArea.getId();
    }

    /**
     * getInputGeographicContainer()
     * this method makes the user define the NAME of the GeographicArea CONTAINED
     */
    private void getInputGeographicContainedUS08(GeographicAreaList newGeoListUi) {
        getInputGeographicArea(newGeoListUi);
        this.mNameGeographicAreaContained = mGeoArea.getId();
    }

    /**
     * @param newGeoListUi is the MainUI List
     *                     First we check if the Geographic Areas that we are testing exist in the MainUI list.
     *                     Then we check the GeographicAreaContained for its flag
     *                     And finally it tests the flag (Geographic Area) is equal to the testing GeographicArea Container
     */

    private void verifyAndDisplayStateUS08(GeographicAreaList newGeoListUi) {
        if (!(mController.matchGeographicAreas(mNameGeographicAreaContained, mNameGeographicAreaContainer, newGeoListUi))) {
            System.out.println("The given areas are invalid!");
            return;
        }
        if (!(mController.seeIfItsContained())) {
            System.out.println(mNameGeographicAreaContained + " is NOT contained in " + mNameGeographicAreaContainer);
            return;
        }
        System.out.println(mNameGeographicAreaContained + " is contained in " + mNameGeographicAreaContainer);
        return;
    }


    /* UI SPECIFIC METHODS - NOT USED ON USER STORIES */
    private void printOptionMessage() {
        System.out.println("Geographic Settings Options:\n");
        System.out.println("1) Create a new type of Geographical Area. (US001)");
        System.out.println("2) List the existing types of Geographical Areas. (US002)");
        System.out.println("3) Add a new geographical area. (US003)");
        System.out.println("4) List of existing geographical areas of a given type. (US004)");
        System.out.println("5) Add an existing geographical area to another one. (US007)");
        System.out.println("6) See if a geographical area is included, directly or indirectly, in another one. (US008)");
        System.out.println("0) (Return to main menu)\n");
    }
}
