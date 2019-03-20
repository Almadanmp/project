package pt.ipp.isep.dei.project.controller;

import pt.ipp.isep.dei.project.model.GeographicArea;
import pt.ipp.isep.dei.project.model.GeographicAreaList;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.Room;
import pt.ipp.isep.dei.project.reader.ReaderJSONGeographicAreas;

/**
 * Controller class for House Configuration UI
 */


public class HouseConfigurationController {

    // USER STORY 15 - As an Administrator, I want to import geographical areas and sensors from a JSON file.

    /**
     * The given method receives a list of geographic areas and calls mapper to convert every DTO created upon reading
     * the json file, before adding the newly created Geographic Areas (and their sensors) to the list.
     *
     * @param fileAreas is the list of Geographic Area DTOs created by reading a given .json file.
     * @param list      comes from mainUI because there is no database yet. Is the program's static list of geographic areas.
     */
    public int addGeoAreasToList(GeographicArea[] fileAreas, GeographicAreaList list) {
        int result = 0;
        for (GeographicArea area : fileAreas) {
            if (list.addGeographicArea(area)) {
                result++;
            }
        }
        return result;
    }

    /**
     * Method calls upon a ReaderJSONGeographicAreas class to read a given filepath.
     * @param filePath is the filepath where the file we want to read is.
     * @param list is the list we want to import the data into.
     * @return is the number of areas imported.
     */

    public int readFile(String filePath, GeographicAreaList list){
        ReaderJSONGeographicAreas reader = new ReaderJSONGeographicAreas();
        return reader.readFile(filePath, list);
    }


    /* USER STORY 101 - As an Administrator, I want to configure the location of the house */

    /**
     * Sets the address of a given House.
     *
     * @param street the house's street.
     * @param town   The house's town.
     * @param zip    The house's zip code.
     * @param house  is the house we're going to set the address of.
     */

    public void setHouseAddress(String street, String zip, String town, House house) {
        house.setAddress(street, zip, town);
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
     * This method receives a house and a geographic area and sets this as
     * the house mother area.
     **/
    public void setHouseMotherArea(House house, GeographicArea geographicArea) {
        house.setMotherArea(geographicArea);
    }

    /**
     * @param house is the house we want to get the name from.
     * @return is the name of the given house.
     */

    public String getHouseName(House house) {
        return house.getHouseId();
    }


    /* USER STORY 105 - As an Administrator, I want to add a new room to the house, in order to configure it (name,
     house floor and dimensions) */

    /**
     * This method receives the house and room parameters and creates a new roomDTO.
     *
     * @param house           house that will create the room
     * @param roomDesignation is the name of the room we're going to create.
     * @param roomHouseFloor  is the floor of the room we're going to create.
     * @param width           is the width of the room we're going to create.
     * @param length          is the length of the room we're going to create.
     * @param height          is the height of the room we're going to create.
     * @return a new Room
     */
    public Room createNewRoom(House house, String roomDesignation, int roomHouseFloor, double width, double length, double height) {
        return house.createRoom(roomDesignation, roomHouseFloor, width, length, height);
    }

    /**
     * The method receives a house and a roomDTO, transforms it into a room, and tries to add it to house.
     *
     * @param house the project House.
     * @param room  the DTO of a Room.
     * @return true if room was added, false otherwise.
     **/
    public boolean addRoomToHouse(House house, Room room) {
        return house.addRoom(room);
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
