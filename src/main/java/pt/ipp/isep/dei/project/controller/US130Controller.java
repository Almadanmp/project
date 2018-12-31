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

    public US130Controller(HouseList houseList) {
        this.mHouseList = houseList;
    }

    public boolean seeIfHouseExistsInHouseList(String houseName){
        if (mHouseList.checkIfHouseListContainsHouseWithGivenDesignation(houseName)){
            this.mHouseName = houseName;
            return true;
        } else {
            return false;
        }
    }

    public boolean addEnergyGridToHouse(){
        return mHouseList.getHouseByDesignation(mHouseName).getmEGList().addEnergyGridToEnergyGridList(mEnergyGrid);
    }

    public void createEnergyGrid(String designation, double maxPower) {
        this.mEnergyGrid = new EnergyGrid(designation, maxPower);
    }
}
