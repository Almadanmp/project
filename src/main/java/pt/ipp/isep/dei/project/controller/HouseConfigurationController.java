package pt.ipp.isep.dei.project.controller;

import pt.ipp.isep.dei.project.model.GeographicArea;
import pt.ipp.isep.dei.project.model.GeographicAreaList;
import pt.ipp.isep.dei.project.model.House;
import java.util.List;

/**
 * Controller class for House Configuration UI
 */

public class HouseConfigurationController {

    /* USER STORY 101 - As an Administrator, I want to configure the location of the house */

    /**
     * @param geoAreaList is the list we're going to print.
     * @return builds a striing of all the individual members of the geoArea list.
     */

    String buildGAListString(GeographicAreaList geoAreaList) {
        return geoAreaList.buildGaWholeListString(geoAreaList);
    }

    /**
     * @param address is the address of the house.
     * @param house   is the house we're going to set the address of.
     */

    public void setHouseAddress(String address, House house) {
        house.setStreet(address);
    }

    /**
     * @param zipCode is the zip code we're going to set.
     * @param house   is the house we're going to change the zip code to.
     */

    public void setHouseZIPCode(String zipCode, House house) {
        house.setZip(zipCode);
    }

    /**
     * @param latitude  is the latitude we're going to set.
     * @param longitude is the longitude we're going to set.
     * @param altitude  is the altitude we're going to set.
     * @param house     is the house we're going to change the zip code to.
     */

    public void setHouseLocal(double latitude, double longitude, double altitude, House house) {
        house.setLocation(latitude, longitude, altitude);
    }

    /**
     * @param geoArea is the area we're going to print.
     * @return builds a string with the given area.
     */

    String buildGeoAreaString(GeographicArea geoArea) {
        return geoArea.buildGeographicAreaString();
    }

    /* USER STORY 105 - As an Administrator, I want to add a new room to the house, in order to configure it (name,
     house floor and dimensions) */

    /** This method receives the house and room parameter, creates a new room and adds it to house in case it the room
     * does not exist in house.
     * @param house program house that will create the room
     * @param roomDesignation is the name of the room we're going to create.
     * @param roomHouseFloor  is the floor of the room we're going to create.
     * @param width           is the width of the room we're going to create.
     * @param length          is the length of the room we're going to create.
     * @param height          is the height of the room we're going to create.
     * @return true in case the room is created and added to house, false otherwise
     */
    public boolean createNewRoom(House house, String roomDesignation, int roomHouseFloor, double width, double length, double height) {
        return house.createRoom(roomDesignation, roomHouseFloor, width, length, height);
    }

    /**
     * @param house is the house we want to get the name from.
     * @return is the name of the given house.
     */

    public String getHouseName(House house) {
        return house.getHouseId();
    }


    /* USER STORY 108 - As an Administrator, I want to have a list of existing rooms, so that I can choose one to edit it. */

    /**
     * @param house is the house.
     * @return builds a string of all the individual members in the given list.
     */

    public String buildRoomsString(House house) {
        return house.buildRoomListString();
    }
}
