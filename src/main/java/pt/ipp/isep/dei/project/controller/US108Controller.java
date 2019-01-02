package pt.ipp.isep.dei.project.controller;

import pt.ipp.isep.dei.project.model.*;

/**
 * As an Administrator, I want to have a list of existing rooms, so that I can choose one
 * to edit it.
 **/

public class US108Controller {

    private HouseList mHouseList;
    private RoomList mRoomList;
    private Room mRoom;
    private House mHouse;

    public US108Controller(HouseList houseList) {
        this.mHouseList = houseList;
    }

    public HouseList getHouseList() {
        return this.mHouseList;
    }

    public String printHouseListNames() {
        return mHouseList.printHouseList();
    }

    public boolean matchHouseByName(String houseDesignation) {
       if(!(mHouseList.getHouseByDesignation(houseDesignation) == null)) {
           this.mHouse = mHouseList.getHouseByDesignation(houseDesignation);
           return true;
       }
       return false;
    }

    public RoomList getRoomList() {
        this.mRoomList = this.mHouse.getmRoomList();
        return this.mRoomList;
    }

    public String printRoomListNames() {
        return mRoomList.printRoomList();
    }

    public boolean matchRoom(String roomDesignation) {
        if(!(mRoomList.getRoomByName(roomDesignation) == null)) {
            mRoom = mRoomList.getRoomByName(roomDesignation);
            return true;
        }
        return false;
    }

    public void setRoom(String roomDesignation, int roomHouseFloor, double roomDimensions) {
        this.mRoom.setRoomName(roomDesignation);
        this.mRoom.setRoomHouseFloor(roomHouseFloor);
        this.mRoom.setRoomDimensions(roomDimensions);
    }

}

