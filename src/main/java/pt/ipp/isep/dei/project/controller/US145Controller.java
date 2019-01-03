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
    private House mHouse;
    private GeographicAreaList mGeographicAreaList;
    private EnergyGrid mEnergyGrid;
    private String mHouseName;
    private HouseList mHouseList;
    private RoomList mRoomList;
    private EnergyGridList mEnergyGridList;
    private String mGeographicAreaName;
    private GeographicArea mGeographicArea;


    public US145Controller() {


        /*
         * Builder RoomConfigurationController(), with no parameters,
         * as it will only be used in ui to apply methods on given inputs
         */
    }

    public US145Controller(GeographicAreaList geographicAreaList) {
        this.mGeographicAreaList = geographicAreaList;
    }


    public boolean seeIfGeographicAreaExistsInGeographicAreaList(String geographicAreaName) {
        if (this.mGeographicAreaList.seeIfGeographicAreaByNameExists(geographicAreaName)) {
            this.mGeographicAreaName = geographicAreaName;
            return true;
        } else {
            return false;
        }
    }

    public GeographicArea getGeographicArea(String geographicAreaName) {
        this.mGeographicArea = this.mGeographicAreaList.getGeographicAreaByName(geographicAreaName);
        return this.mGeographicArea;
    }

    public boolean seeIfHouseExistsInHouseList(String houseName) {
        if (this.mGeographicArea.getHouseList().checkIfHouseListContainsHouseWithGivenDesignation(houseName)) {
            this.mHouseName = houseName;
            return true;
        } else {
            return false;
        }
    }

    public House getHouseByHouseName(String houseName) {
        this.mHouse = this.mGeographicArea.getHouseList().getHouseByDesignation(houseName);
        return this.mHouse;
    }

    public RoomList getRoomListByHouseName() {
        this.mRoomList = this.mHouse.getmRoomList();
        return this.mRoomList;
    }

    public boolean seeIfRoomExistsInHouse(String roomName) {
        if (this.mRoomList.matchRoom(roomName)) {
            return true;
        } else {
            return false;
        }
    }

    public Room getRoomByRoomName(String roomName) {
        this.mRoom = this.mRoomList.getRoomByName(roomName);
        return this.mRoom;
    }

    public EnergyGridList getmEnergyGridListByHouseName() {
        this.mEnergyGridList = this.mHouse.getmEGList();
        return this.mEnergyGridList;
    }

    public boolean seeIfEnergyGridExistsInEnergyGridList(String energyGridName) {
        if (this.mEnergyGridList.seeIfContainsEnergyGrid(energyGridName)) {
            return true;
        } else {
            return false;
        }
    }

    public EnergyGrid getEnergyGrid(String energyGrid) {
        this.mEnergyGrid = this.mEnergyGridList.matchEnergyGrid(energyGrid);
        return this.mEnergyGrid;
    }


    public boolean addRoomToEnergyGrid() {
        if (this.mEnergyGrid.addRoomToAEnergyGrid(this.mRoom)) {
            return true;
        } else {
            return false;
        }
    }
}
