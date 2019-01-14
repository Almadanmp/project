package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controller.GASettingsController;
import pt.ipp.isep.dei.project.controller.HouseConfigurationController;
import pt.ipp.isep.dei.project.model.GeographicArea;
import pt.ipp.isep.dei.project.model.GeographicAreaList;

import java.util.List;
import java.util.Scanner;

/**
 * Utility class that aggregates common methods used by the UI classes.
 */
class UtilsUI {

    private GeographicArea mGeographicArea;
    private String mGeographicAreaName;
    private String mStringChosenGeographicArea = "You have chosen the following Geographic Area:";
    private String mReturnString = "0) Return;";

    String invalidOption = "Please enter a valid option";

       // OPÇÃO LISTAR POR NOMES / POR LISTA - GEOGRAPHIC AREA

    GeographicArea getInputGeographicArea(GeographicAreaList geographicAreaList) {
        System.out.println(
                "We need to know what Geographic Area you want to work with.\n" + "Would you like to:\n" + "1) Type the Geographic Area name;\n" + "2) Choose it from a list;\n" +
                        mReturnString);
        InputUtils inputUtils = new InputUtils();
        int option = inputUtils.readInputNumberAsInt();
        switch (option) {
            case 1:
                getInputGeographicAreaName();
                if (!getGeographicAreaByName(geographicAreaList)) {
                    return this.mGeographicArea;
                }
                break;
            case 2:
                getInputGeographicAreaByList(geographicAreaList);
                return this.mGeographicArea;
            case 0:
                break;
            default:
                System.out.println(invalidOption);
                break;
        }
        return this.mGeographicArea;
    }

    GeographicArea setGeographicAreaContainer(GeographicAreaList geographicAreaList) {
        System.out.println(
                "1 )Do you want to select container from a list.\n" + "Or\n" +
                        mReturnString);
        return selectGeographicAreaByList(geographicAreaList);
    }

    GeographicArea selectGeographicAreaByList(GeographicAreaList geographicAreaList){
        InputUtils inputUtils = new InputUtils();
        int option = inputUtils.readInputNumberAsInt();
        switch (option) {
            case 1:
                getInputGeographicAreaByList(geographicAreaList);
                return this.mGeographicArea;
            case 0:
                break;
            default:
                System.out.println(invalidOption);
                break;
        }
        return this.mGeographicArea;
    }

    GeographicArea setGeographicAreaContained(GeographicAreaList geographicAreaList) {
        System.out.println(
                "1 )Do you want to select contained from a list.\n" + "Or\n" +
                        mReturnString);
        return selectGeographicAreaByList(geographicAreaList);
    }

    private boolean getGeographicAreaByName(GeographicAreaList newGeoListUi) {
        InputUtils inputUtils = new InputUtils();
        HouseConfigurationController ctrl = new HouseConfigurationController();
        List<Integer> listOfIndexesGeographicAreas = ctrl.matchGeographicAreaIndexByString(mGeographicAreaName, newGeoListUi);

        while (listOfIndexesGeographicAreas.isEmpty()) {
            System.out.println("There is no Geographic Area with that name. Please insert the name of a Geographic Area" +
                    " that exists or  Type 'exit' to cancel and create a new Geographic Area on the Main Menu.");
            if (!getInputGeographicAreaName()) {
                System.out.println("Unable to select a Geographic Area. Returning to main menu.");
                return false;
            }
            listOfIndexesGeographicAreas = ctrl.matchGeographicAreaIndexByString(mGeographicAreaName, newGeoListUi);
        }

        if (listOfIndexesGeographicAreas.size() > 1) {
            System.out.println("There are multiple Geographic Areas with that name. Please choose the right one.");
            System.out.println(ctrl.printGeoAreasByIndex(listOfIndexesGeographicAreas, newGeoListUi));
            int aux = inputUtils.readInputNumberAsInt();
            if (listOfIndexesGeographicAreas.contains(aux)) {
                mGeographicArea = newGeoListUi.getGeographicAreaList().get(aux);
                System.out.println(mStringChosenGeographicArea);
                System.out.println(ctrl.printGeoArea(mGeographicArea));
            } else {
                System.out.println(invalidOption);
            }
        } else {
            System.out.println(mStringChosenGeographicArea);
            mGeographicArea = newGeoListUi.getGeographicAreaList().get(listOfIndexesGeographicAreas.get(0));
            System.out.println(ctrl.printGeoArea(mGeographicArea));
        }
        return true;
    }

    private boolean getInputGeographicAreaName() {
        System.out.println("Please type the name of the Geographic Area Where Your House Is Located.");
        Scanner scanner = new Scanner(System.in);
        this.mGeographicAreaName = scanner.nextLine();
        return (!("exit".equals(mGeographicAreaName)));
    }


    private void getInputGeographicAreaByList(GeographicAreaList newGeoListUi) {
        InputUtils inputUtils = new InputUtils();
        boolean activeInput = false;
        System.out.println("Please select the Geographic Area in which your House is in from the list: ");
        while (!activeInput) {
            GASettingsController controller = new GASettingsController();
            System.out.println(controller.printGAList(newGeoListUi));
            int aux = inputUtils.readInputNumberAsInt();
            if (aux >= 0 && aux < newGeoListUi.getGeographicAreaList().size()) {
                mGeographicArea = newGeoListUi.getGeographicAreaList().get(aux);
                activeInput = true;
                System.out.println(mStringChosenGeographicArea);
                System.out.println((mGeographicArea.printGeographicArea()));
            } else {
                System.out.println(invalidOption);
            }
        }
    }
}
