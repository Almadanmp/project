package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controller.EGConsumptionController;
import pt.ipp.isep.dei.project.model.EnergyGrid;
import pt.ipp.isep.dei.project.model.House;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


class EGConsumptionUI {
    private EGConsumptionController controller = new EGConsumptionController();

    EGConsumptionUI() {
        this.controller = new EGConsumptionController();
    }

    Scanner enterToReturnToConsole = new Scanner(System.in);

    void run(House programHouse) {
        InputUtils inputUtils = new InputUtils();
        UtilsUI utils = new UtilsUI();
        if (programHouse == null) {
            System.out.println("Invalid House - This house doesn't meet the necessary requirements, please configure" +
                    " your house first through the main menu");
            return;
        }
        boolean activeInput = false;
        int option;
        System.out.println("--------------\n");
        System.out.println("Energy Consumption Monitoring\n");
        System.out.println("--------------\n");
        while (!activeInput) {
            printOptionMessage();
            option = inputUtils.readInputNumberAsInt();
            switch (option) {
                case 1:
                    runUS705(programHouse);
                    activeInput = true;
                    break;

                case 2:
                    runUS752(programHouse);
                    activeInput = true;
                    break;
                case 0:
                    return;
                default:
                    System.out.println(utils.invalidOption);
                    break;
            }
        }
    }

    //US705

    private void runUS705(House programHouse) {
        List<Integer> indexes = new ArrayList<>();
        boolean activeInput = true;
        InputUtils inputs = new InputUtils();
        Scanner scanner = new Scanner(System.in);
        EnergyGrid grid = inputs.getInputGridByList(programHouse);
        while (activeInput) {
            System.out.println(controller.printRoomsAndDevices(grid));
            System.out.println("Please choose a room or device from the list, or type 'exit' to leave:");
            String userInput = scanner.nextLine();
            try {
                int inputNumber = Integer.parseInt(userInput);
                if (indexes.contains(inputNumber)) {
                    System.out.println("You have already selected that device. Please try again.");
                    inputs.returnToMenu(enterToReturnToConsole);
                } else if (controller.printRoomsAndDevices(grid).contains(Integer.toString(inputNumber))) {
                    indexes.add(inputNumber);
                    System.out.println("Room / Device successfully selected!");
                    inputs.returnToMenu(enterToReturnToConsole);
                } else System.out.println("\nPlease insert a number in the list.\n");
            } catch (NumberFormatException exit) { //If exception occurred, it means user has inserted something that is not an int.
                if ("exit".equals(userInput)) {
                    System.out.println("Exiting.");
                    break;
                } else {
                    System.out.println("Invalid input! Insert a number or 'exit' to continue.");
                    inputs.returnToMenu(enterToReturnToConsole);
                }
            }
        }
        System.out.println("The total nominal power of the chosen subset is " + controller.getSumNominalPowerByIndex(indexes, grid) + " .\n");
    }


    /**
     * /**
     * US752
     * As a Regular User [or Power User], I want to estimate the total energy used in heating water in a given day,
     * given the cold-water temperature and the volume of water produced in each water heater.
     */

    private void runUS752(House house) {
        double result = controller.getDailyHouseConsumptionWaterHeater(house);
        System.out.println("The estimate total energy used in heating water in a day is: " + result + " kW.");
    }

    private void printOptionMessage() {
        System.out.println("Energy Consumption Management Options:\n");
        System.out.println("1) Get total nominal power of a subset of rooms and/or devices connected to a grid." +
                " (US705)");
        System.out.println("2) Estimate the total energy used in heating water in a day. (US752)");
        System.out.println("0) (Return to main menu)\n");
    }
}
