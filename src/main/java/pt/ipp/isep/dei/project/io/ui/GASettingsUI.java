package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controller.GASettingsController;
import pt.ipp.isep.dei.project.model.GeographicArea;
import pt.ipp.isep.dei.project.model.GeographicAreaList;
import pt.ipp.isep.dei.project.model.TypeArea;
import pt.ipp.isep.dei.project.model.TypeAreaList;

import java.util.Scanner;

class GASettingsUI {
    private GASettingsController mController;
    private GeographicArea mGeoArea;
    private GeographicAreaList mGeoAreaList;
    private String mTypeAreaName;
    private TypeArea mTypeArea;
    private boolean mListWasCreated;
    private double mGeoAreaLat;
    private double mGeoAreaLong;
    private double mGeoAreaAlt;
    private double mGeoAreaLength;
    private double mGeoAreaWidth;
    private boolean mAreaAddedToList;
    private String nameOfGeoArea;
    private String mDaughterAreaName;
    private String mMotherAreaName;
    private String mContainedAreaName;
    private String mContainerAreaName;

    GASettingsUI() {
        this.mController = new GASettingsController();
    }

    void runGASettings(GeographicAreaList programGAList, TypeAreaList programTypeAreaList) {
        UtilsUI utils = new UtilsUI();
        InputUtils inputUtils = new InputUtils();
        boolean activeInput = true;
        int option;
        System.out.println("--------------\nGeographic Area Settings\n--------------\n");
        while (activeInput) {
            printOptionMessage();
            option = inputUtils.readInputNumberAsInt();
            switch (option) {
                case 1:
                    runUS01(programTypeAreaList);
                    activeInput = false;
                    break;

                case 2:
                    runUS02(programTypeAreaList);
                    activeInput = false;
                    break;
                case 3:
                    runUS03(programGAList, programTypeAreaList);
                    activeInput = false;
                    break;
                case 4:
                    runUS04(programGAList, programTypeAreaList);
                    activeInput = false;
                    break;
                case 5:
                    runUS07(programGAList);
                    activeInput = false;
                    break;
                case 6:
                    runUS08(programGAList);
                    activeInput = false;
                    break;
                case 0:
                    return;
                default:
                    System.out.println(utils.invalidOption);
                    break;
            }
        }
    }


    // SHARED METHODS //

    //GET INPUT TYPE AREA BY LIST - SÓ É USADO NESTA CLASSE POR ISSO NAO ESTÁ EM INPUT UTILS//
    private TypeArea getInputTypeAreaByList(TypeAreaList typeAreaList) {
        UtilsUI utils = new UtilsUI();
        InputUtils inputUtils = new InputUtils();
        while (true) {
            System.out.println("Please select the Geographic Area Type from the list: ");
            System.out.print(mController.buildGATypeListString(typeAreaList));
            int aux = inputUtils.readInputNumberAsInt();
            if (aux >= 0 && aux < typeAreaList.getTypeAreaList().size()) {
                this.mTypeArea = typeAreaList.getTypeAreaList().get(aux);
                this.mTypeAreaName = typeAreaList.getTypeAreaList().get(aux).getTypeOfGeographicArea();
                System.out.println("You have chosen the following Geographic Area Type:");
                System.out.println(mController.buildTypeAreaString(this.mTypeArea));
                return this.mTypeArea;
            } else {
                System.out.println(utils.invalidOption);
            }
        }
    }


    /* USER STORY 001 - As an Administrator, I want to add a new type of geographical area, in order to be able to create a
     classification of geographical areas.*/
    private void runUS01(TypeAreaList typeAreaList) {
        getInputUS01(typeAreaList);
        updateModelUS01(typeAreaList);
        displayStateUS01(typeAreaList);
    }

    private void getInputUS01(TypeAreaList typeAreaList) {
        UtilsUI utils = new UtilsUI();
        if (typeAreaList != null) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Please insert the name of the new Geographic Area Type: ");
            while (!scanner.hasNext("[a-zA-Z_]+")) {
                System.out.println("That's not a valid name a Type Area. Please insert only Alphabetic Characters");
                scanner.next();
            }
            this.mTypeAreaName = scanner.next();
        } else {
            System.out.println(utils.invalidGATypeList);
        }
    }

    private void updateModelUS01(TypeAreaList typeAreaList) {
        if (typeAreaList != null) {
            this.mListWasCreated = mController.createAndAddTypeAreaToList(mTypeAreaName, typeAreaList);
        }
    }

    private void displayStateUS01(TypeAreaList typeAreaList) {
        if (typeAreaList != null) {
            if (mListWasCreated) {
                System.out.println("Success, you have inserted a new Type of Geographic Area.");
            } else {
                System.out.println("Failed, you have inserted an invalid or repeated Type of Geographic Area.");
            }
        }
    }

    /* USER STORY 002 - As a System Administrator I want to obtain a list of the types of Geographical Areas previously stated.
     * Class responsible for presenting the list. - NUNO AZEVEDO */
    private void runUS02(TypeAreaList typeAreaList) {
        updateAndDisplayUS02(typeAreaList);
    }

    private void updateAndDisplayUS02(TypeAreaList typeAreaList) {
        UtilsUI utils = new UtilsUI();
        if (utils.typeAreaListIsValid(typeAreaList)) {
            System.out.println(mController.getTypeAreaList(typeAreaList));
            System.out.println("\nList finished.");
        } else {
            System.out.println(utils.invalidGATypeList);
        }
    }

    /* User Story - 03 As a System Administrator I want to create a new Geographic Area */
    private void runUS03(GeographicAreaList geographicAreaList, TypeAreaList typeAreaList) {
        UtilsUI utils = new UtilsUI();
        if (!utils.typeAreaListIsValid(typeAreaList)) {
            System.out.println(utils.invalidGATypeList);
            return;
        }
        getAreaInputUS03(typeAreaList);
        updateGeoAreaUS03(geographicAreaList);
        displayStateUS03();
    }

    private void getAreaInputUS03(TypeAreaList typeAreaList) {
        getInputTypeAreaByList(typeAreaList);
        this.nameOfGeoArea = readInputString("name");
        this.mGeoAreaLat = readInputNumber("Latitude");
        this.mGeoAreaLong = readInputNumber("Longitude");
        this.mGeoAreaAlt = readInputNumber("Altitude");
        this.mGeoAreaLength = readInputNumber("Length");
        this.mGeoAreaWidth = readInputNumber("Width");
    }

    private void updateGeoAreaUS03(GeographicAreaList geographicAreaList) {
        System.out.print("The Geographic Area you want to create is " + nameOfGeoArea + " from the type " + mTypeAreaName +
                " and its " + "localization is on " + mGeoAreaLat + " latitude " + mGeoAreaLong + " longitude. The geographic area size" +
                " is " + this.mGeoAreaLength + " by " + this.mGeoAreaWidth + " kms\n");
        this.mAreaAddedToList = mController.addNewGeoAreaToList(geographicAreaList, nameOfGeoArea, mTypeArea, mGeoAreaLat, mGeoAreaLong, mGeoAreaAlt, mGeoAreaLength, mGeoAreaWidth);
    }

    private void displayStateUS03() {
        if (mAreaAddedToList) {
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
    private void runUS04(GeographicAreaList geographicAreaList, TypeAreaList typeAreaList) {
        UtilsUI utilsUI = new UtilsUI();
        if (!utilsUI.typeAreaListIsValid(typeAreaList)) {
            System.out.println(utilsUI.invalidGATypeList);
            return;
        }
        if (!utilsUI.geographicAreaListIsValid(geographicAreaList)) {
            System.out.println(utilsUI.invalidGAList);
            return;
        }
        mTypeArea = getInputTypeAreaByList(typeAreaList);
        mGeoAreaList = matchGAByTypeArea(geographicAreaList);
        displayGAListByTypeArea(geographicAreaList);
    }

    private GeographicAreaList matchGAByTypeArea(GeographicAreaList geographicAreaList) {
        this.mGeoAreaList = mController.matchGAByTypeArea(geographicAreaList, this.mTypeArea);
        this.mTypeAreaName = mController.getTypeAreaName(this.mTypeArea);
        return mGeoAreaList;
    }

    private void displayGAListByTypeArea(GeographicAreaList geographicAreaList) {
        System.out.println("Geographic Areas of the type " + this.mTypeAreaName + ":\n");
        System.out.println(mController.buildGAListString(this.mGeoAreaList));
    }

    /* USER STORY 07 -  Add an existing geographical area to another one. */
    private void runUS07(GeographicAreaList geographicAreaList) {
        UtilsUI utilsUI = new UtilsUI();
        if (!utilsUI.geographicAreaListIsValid(geographicAreaList)) {
            System.out.println(utilsUI.invalidGAList);
            return;
        }
        getInputDaughterGA(geographicAreaList);
        updateStateUS07(geographicAreaList);
        displayStateUS07(geographicAreaList);
    }

    private void getInputMotherGA(GeographicAreaList programGAList) {
        UtilsUI utils = new UtilsUI();
        if (utils.geographicAreaListIsValid(programGAList)) {
            this.mGeoArea = setGeographicAreaContainer(programGAList);
            this.mMotherAreaName = getGAId(this.mGeoArea);
            if (mController.checkIfGAExistsInGAList(mMotherAreaName, programGAList)) {
                System.out.println("Success, you have inserted a valid Geographic Area.");
            } else {
                System.out.println("Error! You have inserted a non-existent Geographic Area.");
            }
        } else {
            System.out.println(utils.invalidGAList);
        }
    }

    private void getInputDaughterGA(GeographicAreaList programGAList) {
        getInputMotherGA(programGAList);
        UtilsUI utils = new UtilsUI();
        if (utils.geographicAreaListIsValid(programGAList)) {
            this.mGeoArea = setGeographicAreaContained(programGAList);
            this.mDaughterAreaName = getGAId(this.mGeoArea);
            if (mController.checkIfGAExistsInGAList(mDaughterAreaName, programGAList)) {
                System.out.println("Success, you have inserted a valid Geographic Area.");
            } else System.out.println("Error! You have inserted a non-existent Geographic Area.");
        } else {
            System.out.println(utils.invalidGAList);
        }
    }

    private void updateStateUS07(GeographicAreaList newGeoListUi) {
        UtilsUI utils = new UtilsUI();
        if (utils.geographicAreaListIsValid(newGeoListUi)) {
            GeographicArea daughterArea = mController.matchGeoAreaByName(mDaughterAreaName, newGeoListUi);
            GeographicArea motherArea = mController.matchGeoAreaByName(mMotherAreaName, newGeoListUi);
            mController.setMotherArea(daughterArea, motherArea);
        } else {
            System.out.println(utils.invalidGAList);
        }
    }

    private void displayStateUS07(GeographicAreaList newGeoListUi) {
        UtilsUI utils = new UtilsUI();
        if (utils.geographicAreaListIsValid(newGeoListUi)) {
            System.out.print("The Geographic Area " + mDaughterAreaName + " is contained in " + mMotherAreaName + "\n");
        }
    }

    // METODOS CONTAINS GEOGRAPHIC AREA

    private GeographicArea setGeographicAreaContainer(GeographicAreaList geographicAreaList) {
        getInputGeographicAreaForContainer(geographicAreaList);
        return mGeoArea;
    }

    private GeographicArea setGeographicAreaContained(GeographicAreaList geographicAreaList) {
        getInputGeographicAreaForContained(geographicAreaList);
        return mGeoArea;
    }

    private void getInputGeographicAreaForContainer(GeographicAreaList programGAList) {
        if (programGAList.getGeographicAreaList().isEmpty()) {
            System.out.println("The list of Geographic Areas is empty.");
            return;
        }
        System.out.println("First you need to select the geographic area you wish to set as container.");
        InputUtils inputUtils = new InputUtils();
        mGeoArea = inputUtils.oldGetGeographicAreaByList(programGAList);
    }

    private void getInputGeographicAreaForContained(GeographicAreaList programGAList) {
        if (programGAList == null || programGAList.getGeographicAreaList().isEmpty()) {
            System.out.println("The list of Geographic Areas is empty.");
            return;
        }
        System.out.println("Second you need to select the geographic area you wish to set as contained.");
        InputUtils inputUtils = new InputUtils();
        mGeoArea = inputUtils.oldGetGeographicAreaByList(programGAList);
    }

    /* US08 - As an Administrator, I want to find out if a geographical area is included, directly
    or indirectly, in another one. */
    private void runUS08(GeographicAreaList geographicAreaList) {
        UtilsUI utilsUI = new UtilsUI();
        if (!utilsUI.geographicAreaListIsValid(geographicAreaList)) {
            System.out.println(utilsUI.invalidGAList);
            return;
        }
        getContainerArea(geographicAreaList);
        getContainedArea(geographicAreaList);
        checkIfContained(geographicAreaList);
    }

    /**
     * getInputGeographicContainer()
     * this method makes the user define the NAME of the GeographicArea CONTAINER
     */
    private void getContainerArea(GeographicAreaList geographicAreaList) {
        UtilsUI utils = new UtilsUI();
        if (utils.geographicAreaListIsValid(geographicAreaList)) {
            System.out.println("First you need to select the geographic area you wish to test if contains another geographic area.");
            InputUtils inputUtils = new InputUtils();
            mGeoArea = inputUtils.oldGetGeographicAreaByList(geographicAreaList);
            this.mContainerAreaName = getGAId(mGeoArea);
        } else {
            System.out.println(utils.invalidGAList);
        }
    }

    /**
     * getInputGeographicContainer()
     * this method makes the user define the NAME of the GeographicArea CONTAINED
     */
    private void getContainedArea(GeographicAreaList geographicAreaList) {
        UtilsUI utils = new UtilsUI();
        if ((utils.geographicAreaListIsValid(geographicAreaList)) && (this.mContainerAreaName != null)) {
            System.out.println("Second you need to select the geographic area you wish to test if is contained in the first one.");
            InputUtils inputUtils = new InputUtils();
            mGeoArea = inputUtils.oldGetGeographicAreaByList(geographicAreaList);
            this.mContainedAreaName = getGAId(mGeoArea);
        }
    }

    private String getGAId(GeographicArea geographicArea) {
        if (geographicArea != null) {
            return geographicArea.getId();
        } else {
            return null;
        }
    }

    /**
     * @param geographicAreaList is the MainUI List
     *                           First we check if the Geographic Areas that we are testing exist in the MainUI list.
     *                           Then we check the GeographicAreaContained for its flag
     *                           And finally it tests the flag (Geographic Area) is equal to the testing GeographicArea Container
     */

    private void checkIfContained(GeographicAreaList geographicAreaList) {
        UtilsUI utils = new UtilsUI();
        if ((utils.geographicAreaListIsValid(geographicAreaList)) && (mContainedAreaName != null) && (mContainerAreaName != null)) {
            if (!(mController.seeIfGAListContainsAreasByName(mContainedAreaName, mContainerAreaName, geographicAreaList))) {
                System.out.println("The given areas are invalid!");
                return;
            }
            if (!(mController.seeIfItsContained())) {
                System.out.println(mContainedAreaName + " is NOT contained in " + mContainerAreaName);
                return;
            }
            System.out.println(mContainedAreaName + " is contained in " + mContainerAreaName);
        }
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
