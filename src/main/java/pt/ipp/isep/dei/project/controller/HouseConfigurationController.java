package pt.ipp.isep.dei.project.controller;

import pt.ipp.isep.dei.project.model.EnergyGrid;
import pt.ipp.isep.dei.project.model.EnergyGridList;
import pt.ipp.isep.dei.project.model.HouseList;
import pt.ipp.isep.dei.project.model.PowerSource;

public class HouseConfigurationController {

    private EnergyGrid mEnergyGrid;
    private HouseList mHouseList;
    private String mHouseName;

    /** US130
     * As an Administrator, I want to create a energy grid,
     * so that I can define the rooms that are attached to it and the contracted maximum power for that grid.
     **/

    /**
     * The controller is initialized with the houseList given from the UI, which came from MainUI;
     */
    public HouseConfigurationController(HouseList houseList) {
        this.mHouseList = houseList;
    }

    /**
     * This method checks the house list which came from MainUI through UI for the given house name;
     */
    public boolean seeIfHouseExistsInHouseList(String houseName) {
        if (mHouseList.checkIfHouseListContainsHouseWithGivenDesignation(houseName)) {
            this.mHouseName = houseName;
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method directly adds the desired energy grid to the energy grid list from a selected house;
     */
    public boolean addEnergyGridToHouse() {
        return mHouseList.getHouseByDesignation(mHouseName).getmEGList().addEnergyGridToEnergyGridList(mEnergyGrid);
    }

    /**
     * This method creates an energy grid using a name and a max potency.
     */
    public void createEnergyGrid(String designation, double maxPower) {
        this.mEnergyGrid = new EnergyGrid(designation, maxPower);
    }

    /**
     * Method used only to test the createEnergyGrid method.
     */
    EnergyGrid getEnergyGrid() {
        return this.mEnergyGrid;
    }

    /**
     * US135
     * As an Administrator, I want to add a power source to an energy grid,
     * so that the produced energy may be used by all devices on that grid. **/

    private PowerSource mPowerSource;
    private EnergyGridList mEnergyGridList;

    public void createPowerSource(String name, double maxPowerOutput, double maxEnergyStorage){this.mPowerSource = new PowerSource(name, maxPowerOutput, maxEnergyStorage);}

    PowerSource getPowerSource(){return this.mPowerSource;}

    public String seeIfEnergyGridListIsEmptyAndShowItsContent(){
        return this.mEnergyGridList.printEnergyGridList();
    }

    public boolean selectEnergyGrid(String name){
        this.mEnergyGrid = mEnergyGridList.matchEnergyGrid(name);
        return this.mEnergyGrid != null;
    }

    public boolean addPowerSourceToEnergyGrid(){
        return this.mEnergyGrid.addPowerSource(mPowerSource);
    }
}
