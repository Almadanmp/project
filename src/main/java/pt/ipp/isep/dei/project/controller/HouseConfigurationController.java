package pt.ipp.isep.dei.project.controller;

import pt.ipp.isep.dei.project.model.*;

import java.util.List;

public class HouseConfigurationController {
    private Room mRoom;

    /* USER STORY 101 - As an Administrator, I want to configure the location of the house */

    public String printGAList(GeographicAreaList geoAreaList) {
        return geoAreaList.printGaWholeList(geoAreaList);
    }

    public void setHouseAddress(String address, House house) {
        house.setStreet(address);
    }

    public List<Integer> matchGeographicAreaIndexByString(String input, GeographicAreaList geoAreaList) {
        return geoAreaList.matchGeographicAreaIndexByString(input);
    }

    public void setHouseZIPCode(String zipCode, House house) {
        house.setZip(zipCode);
    }

    public void setHouseLocal(double latitude, double longitude, double altitude, House house) {
        house.setLocation(latitude, longitude, altitude);
    }

    public String printGeoGraphicAreaElementsByIndex(List<Integer> listOfIndexesGeographicAreas, GeographicAreaList geoAreaList) {
        return geoAreaList.printGeoGraphicAreaElementsByIndex(listOfIndexesGeographicAreas);
    }

    public String printGA(GeographicArea geoArea) {
        return geoArea.printGeographicArea();
    }

    /* USER STORY 105 - As an Administrator, I want to add a new room to the house, in order to configure it (name,
     house floor and dimensions) */


    public void createNewRoom(String roomDesignation, int roomHouseFloor, double width, double length, double height) {
        this.mRoom = new Room(roomDesignation, roomHouseFloor, width, length, height);
    }

    public boolean addRoomToHouse(House house) {
        return (house.addRoomToRoomList(this.mRoom));
    }

    public String getHouseName(House house) {
        return house.getHouseId();
    }


    /* USER STORY 108 - As an Administrator, I want to have a list of existing rooms, so that I can choose one to edit it. */

    public String printRooms(RoomList roomList) {
        return roomList.printRooms();
    }
}
