package pt.ipp.isep.dei.project.controller;

import pt.ipp.isep.dei.project.model.*;

import java.util.List;

public class HouseConfigurationController {
    private Room mRoom;

    /* USER STORY 101 - As an Administrator, I want to configure the location of the house */

    /**
     *
     * @param geoAreaList is the list we're going to print.
     * @return builds a striing of all the individual members of the geoArea list.
     */

    String printGAList(GeographicAreaList geoAreaList) {
        return geoAreaList.printGaWholeList(geoAreaList);
    }

    /**
     *
     * @param address is the address of the house.
     * @param house is the house we're going to set the address of.
     */

    public void setHouseAddress(String address, House house) {
        house.setStreet(address);
    }

    /**
     *
     * @param input is the string we're going to look for in the list of Geographic Areas.
     * @param geoAreaList is the list of Geographic Areas we're going to look for the string in.
     * @return is a list of integers that contains the indexes of all the Geographic Areas in the given list whose name
     * matches the given string.
     */

    public List<Integer> matchGeographicAreaIndexByString(String input, GeographicAreaList geoAreaList) {
        return geoAreaList.matchGeographicAreaIndexByString(input);
    }

    /**
     *
     * @param zipCode is the zip code we're going to set.
     * @param house is the house we're going to change the zip code to.
     */

    public void setHouseZIPCode(String zipCode, House house) {
        house.setZip(zipCode);
    }

    /**
     *
     * @param latitude is the latitude we're going to set.
     * @param longitude is the longitude we're going to set.
     * @param altitude is the altitude we're going to set.
     * @param house is the house we're going to change the zip code to.
     */

    public void setHouseLocal(double latitude, double longitude, double altitude, House house) {
        house.setLocation(latitude, longitude, altitude);
    }

    /**
     *
     * @param listOfIndexesGeographicAreas is a list of all the indexes in a list where relevant objects are.
     * @param geoAreaList is the list from which we're getting the objects.
     * @return builds a string of all the geoAreas contained in the list at the positions given in the list of indexes.
     */

    public String printGeoAreasByIndex(List<Integer> listOfIndexesGeographicAreas, GeographicAreaList geoAreaList) {
        return geoAreaList.printGeoGraphicAreaElementsByIndex(listOfIndexesGeographicAreas);
    }

    /**
     *
     * @param geoArea is the area we're going to print.
     * @return builds a string with the given area.
     */

    public String printGeoArea(GeographicArea geoArea) {
        return geoArea.printGeographicArea();
    }

    /* USER STORY 105 - As an Administrator, I want to add a new room to the house, in order to configure it (name,
     house floor and dimensions) */

    /**
     *
     * @param roomDesignation is the name of the room we're going to create.
     * @param roomHouseFloor is the floor of the room we're going to create.
     * @param width is the width of the room we're going to create.
     * @param length is the length of the room we're going to create.
     * @param height is the height of the room we're going to create.
     */
    public void createNewRoom(String roomDesignation, int roomHouseFloor, double width, double length, double height) {
        this.mRoom = new Room(roomDesignation, roomHouseFloor, width, length, height);
    }

    /**
     *
     * @param house is the house we're going to add a room to.
     * @return is true if the house was successfully added, false if it wasn't.
     */

    public boolean addRoomToHouse(House house) {
        return (house.addRoomToRoomList(this.mRoom));
    }

    /**
     *
     * @param house is the house we want to get the name from.
     * @return is the name of the given house.
     */

    public String getHouseName(House house) {
        return house.getHouseId();
    }


    /* USER STORY 108 - As an Administrator, I want to have a list of existing rooms, so that I can choose one to edit it. */

    /**
     *
     * @param house is the house.
     * @return builds a string of all the individual members in the given list.
     */

    public String printRooms(House house) {
        return house.printRoomList();
    }
}
