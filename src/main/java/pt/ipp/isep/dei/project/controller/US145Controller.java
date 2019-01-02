package pt.ipp.isep.dei.project.controller;

import pt.ipp.isep.dei.project.model.*;

public class US145Controller {
    /**
     * US145Controller As an Administrator, I want to have a list of existing rooms attached to a house grid,
     * so that I can attach/detach rooms from it.
     * US147 As an Administrator, I want to attach a room to a house grid, so that the room’s
     * power and energy consumption is included in that grid.
     * US149 As an Administrator, I want to detach a room from a house grid, so that the room’s
     * power and energy consumption is not included in that grid. The room’s
     * characteristics are not changed.
     */


    private Room mRoom;
    private EnergyGrid mEnergyGrid;
    private String mHouseName;
    private HouseList mHouseList;
    private RoomList mRoomList;
    private String mRoomName;
    private EnergyGridList mEnergyGridList;
    private String mEnergyGridName;


    public US145Controller() {


        /*
         * Builder US253Controller(), with no parameters,
         * as it will only be used in ui to apply methods on given inputs
         */
    }
    public US145Controller(HouseList houseList){this.mHouseList = houseList;}
    public boolean seeIfHouseExistsInHouseList(String houseName){
        if (this.mHouseList.checkIfHouseListContainsHouseWithGivenDesignation(houseName)){
            this.mHouseName = houseName;
            return true;
        } else {
            return false;
        }
    }

    public RoomList getRoomListByHouseName (String houseName){
        return this.mHouseList.getHouseByDesignation(houseName).getmRoomList();
    }
    public boolean seeIfRoomExistsInHouse(String roomName){
        if (this.mHouseList.getHouseByDesignation(this.mHouseName).getmRoomList().matchRoom(roomName)){
            this.mRoomName = roomName;
            return true;
        } else {
            return false;
        }
    }
    public EnergyGridList getmEnergyGridListByHouseName (){
        return this.mHouseList.getHouseByDesignation(this.mHouseName).getmEGList();
    }

    public boolean seeIfEnergyGridExistsInEnergyGridList(String energyGridName){
        if(this.mHouseList.getHouseByDesignation(this.mHouseName).getmEGList().seeIfContainsEnergyGrid(energyGridName)) {
            this.mEnergyGridName = energyGridName;
            return true;
        }else {
            return false;
        }
    }

    public EnergyGrid getEnergyGrid (String energyGrid){
        return this.mHouseList.getHouseByDesignation(mHouseName).getmEGList().matchEnergyGrid(energyGrid);
    }


    public boolean addRoomToEnergyGrid() {
        if (this.mHouseList.getHouseByDesignation(mHouseName).getmEGList().matchEnergyGrid(mEnergyGridName).addRoomToAEnergyGrid(mRoom)){
            return true;
        } else{
            return false;
        }
    }
}
