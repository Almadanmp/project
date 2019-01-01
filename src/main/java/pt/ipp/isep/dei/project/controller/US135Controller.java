package pt.ipp.isep.dei.project.controller;

import pt.ipp.isep.dei.project.model.EnergyGrid;
import pt.ipp.isep.dei.project.model.EnergyGridList;
import pt.ipp.isep.dei.project.model.HouseList;
import pt.ipp.isep.dei.project.model.PowerSource;

/** As an Administrator, I want to add a power source to an energy grid,
 * so that the produced energy may be used by all devices on that grid. **/

public class US135Controller {

    private PowerSource mPowerSource;
    private HouseList mHouseList;
    private EnergyGridList mEnergyGridList;
    private EnergyGrid mEnergyGrid;

    public US135Controller(HouseList houseList) {
        this.mHouseList = houseList;
    }

    /**
     * This method checks the house list which came from MainUI through UI for the given house name;
     */
    public boolean seeIfHouseExistsInHouseList(String houseName){
        if (mHouseList.checkIfHouseListContainsHouseWithGivenDesignation(houseName)){
            this.mEnergyGridList = mHouseList.getHouseByDesignation(houseName).getmEGList();
            return true;
        } else {
            return false;
        }
    }

    public void createPowerSource(String name, double maxPowerOutput, double maxEnergyStorage){this.mPowerSource = new PowerSource(name, maxPowerOutput, maxEnergyStorage);}

    public String seeIfEnergyGridListIsEmptyAndShowItsContent(){
        return this.mEnergyGridList.printEnergyGridList();
    }

    public boolean selectEnergyGrid(String name){
        this.mEnergyGrid = mEnergyGridList.matchEnergyGrid(name);
        if (this.mEnergyGrid == null){
            return false;
        }return true;
    }

    public boolean addPowerSourceToEnergyGrid(){
        return this.mEnergyGrid.addPowerSource(mPowerSource);
        }
}
