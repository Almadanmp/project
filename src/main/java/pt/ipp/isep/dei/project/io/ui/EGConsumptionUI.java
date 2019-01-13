package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controller.EGConsumptionController;
import pt.ipp.isep.dei.project.model.House;


public class EGConsumptionUI {
    EGConsumptionController controller = new EGConsumptionController();


    public EGConsumptionUI() {
        this.controller = new EGConsumptionController();

    }

    void run(House programHouse) {
        InputUtils inputUtils = new InputUtils();
        UtilsUI utils = new UtilsUI();
        if (programHouse == null || programHouse.getMotherArea() == null) {
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
                    activeInput = true;
                    break;

                case 2:
                    inputUtils.getInputStartDate();
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


    /**
     * /**
     * US752
     * As a Regular User [or Power User], I want to estimate the total energy used in heating water in a given day,
     * given the cold-water temperature and the volume of water produced in each water heater.
     */


    private void printOptionMessage() {
        System.out.println("Energy Consumption Management Options:\n");
        System.out.println("1) Get total nominal power of a subset of rooms and/or devices connected to a grid." +
                " (US705)");
        System.out.println("2) Estimate the total energy used in heating water in a given day. (US752)");
        System.out.println("0) (Return to main menu)\n");
    }
}
