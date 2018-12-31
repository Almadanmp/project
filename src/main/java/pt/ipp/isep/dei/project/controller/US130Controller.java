package pt.ipp.isep.dei.project.controller;

import pt.ipp.isep.dei.project.model.EnergyGrid;
import pt.ipp.isep.dei.project.model.EnergyGridList;
import pt.ipp.isep.dei.project.model.HouseList;
import pt.ipp.isep.dei.project.model.RoomList;

/**
 * As an Administrator, I want to create a energy grid,
 * so that I can define the rooms that are attached to it and the contracted maximum power for that grid.
 **/

public class US130Controller {

    private EnergyGrid mEnergyGrid;
    private HouseList mHouseList;
    private String mHouseName;


    /**
     * The controller is initialized with the houseList given from the UI, which came from MainUI;
     */
    public US130Controller(HouseList houseList) {
        this.mHouseList = houseList;
    }

    /**
     * This method checks the house list which came from MainUI through UI for the given house name;
     */
    public boolean seeIfHouseExistsInHouseList(String houseName){
        if (mHouseList.checkIfHouseListContainsHouseWithGivenDesignation(houseName)){
            this.mHouseName = houseName;
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method directly adds the desired energy grid to the energy grid list from a selected house;
     */
    public boolean addEnergyGridToHouse(){
        return mHouseList.getHouseByDesignation(mHouseName).getmEGList().addEnergyGridToEnergyGridList(mEnergyGrid);
    }

    /**
     * This method creates an energy grid using a name and a max potency.
     */
    public void createEnergyGrid(String designation, double maxPower) {
        this.mEnergyGrid = new EnergyGrid(designation, maxPower);
    }
}
