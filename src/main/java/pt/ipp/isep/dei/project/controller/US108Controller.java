package pt.ipp.isep.dei.project.controller;

import pt.ipp.isep.dei.project.model.*;

import java.util.List;

/**
 * As an Administrator, I want to have a list of existing rooms, so that I can choose one
 * to edit it.
 **/

public class US108Controller {

    public List<Integer> matchGeographicAreaIndexByString(String input, GeographicAreaList geoAreaList) {
        return geoAreaList.matchGeographicAreaIndexByString(input);
    }

    public String printGeoGraphicAreaElementsByIndex(List<Integer> listOfIndexesGeographicAreas, GeographicAreaList geoAreaList) {
        return geoAreaList.printGeoGraphicAreaElementsByIndex(listOfIndexesGeographicAreas);
    }

    public String printGA(GeographicArea geoArea) {
        return geoArea.printGeographicArea();
    }

    public String printGAList(GeographicAreaList geoAreaList) {
        return geoAreaList.printGaWholeList(geoAreaList);
    }

    public List<Integer> matchHouseIndexByString(String input, GeographicArea geoArea){
        return geoArea.getHouseList().matchHouseIndexByString(input);
    }

    public String printHouseElementsByIndex(List<Integer> listOfIndexesOfHouses, GeographicArea geoArea) {
        return geoArea.getHouseList().printElementsByIndex(listOfIndexesOfHouses);
    }

    public String printHouseList(GeographicArea geoArea) {
        return geoArea.getHouseList().printHouseList(geoArea);
    }

    public String printHouse (House house){
        return house.printHouse();
    }

    public List<Integer> matchRoomIndexByString(String input, House house){
        return house.getmRoomList().matchRoomIndexByString(input);
    }

    public String printRoomElementsByIndex(List<Integer> listOfIndexesOfRoom, House house) {
        return house.getmRoomList().printElementsByIndex(listOfIndexesOfRoom);
    }

    public String printRoomList(House house) {
        return house.getmRoomList().printRoomList(house);
    }

    public String printRoom (Room room){
        return room.printRoom();
    }

    /**
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
**/
}

