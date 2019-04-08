package pt.ipp.isep.dei.project.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import pt.ipp.isep.dei.project.model.GeographicArea;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.Room;
import pt.ipp.isep.dei.project.model.RoomList;
import pt.ipp.isep.dei.project.model.sensor.HouseSensor;
import pt.ipp.isep.dei.project.model.sensor.SensorType;
import pt.ipp.isep.dei.project.reader.JSONSensorsReader;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Controller class for House Configuration UI
 */


public class HouseConfigurationController {


    /* USER STORY 101 - As an Administrator, I want to configure the location of the house */

    /**
     * Sets the address of a given House.
     *
     * @param street  the house's street.
     * @param number  the house's number.
     * @param town    The house's town.
     * @param zip     The house's zip code.
     * @param country The houses's country.
     * @param house   is the house we're going to set the address of.
     */

    public void setHouseAddress(String street, String number, String zip, String town, String country, House house) {
        house.setAddress(street, number, zip, town, country);
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


    /* USER STORY 105 - As an Administrator, I want to addWithoutPersisting a new room to the house, in order to configure it (name,
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
    public Room createNewRoom(House house, String roomDesignation, String roomDescription, int roomHouseFloor, double width, double length, double height) {
        return house.createRoom(roomDesignation, roomDescription, roomHouseFloor, width, length, height);
    }

    /**
     * The method receives a house and a roomDTO, transforms it into a room, and tries to addWithoutPersisting it to house.
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

    /* USER STORY 260 -  As an Administrator, I want to import a list of sensors for the house rooms.
       Sensors without a valid room shouldn’t be imported but registered in the application log. */

    public int readSensors(String filepath, House programHouse) {
        JSONSensorsReader reader = new JSONSensorsReader();
        int addedSensors = 0;
        RoomList houseRooms = programHouse.getRoomList();
        if (houseRooms.isEmpty()) {
            return addedSensors;
        }
        JSONArray importedArray = reader.readFile(filepath);
        String roomID;
        for (int x = 0; x < importedArray.length(); x++) {
            JSONObject sensorToImport = importedArray.getJSONObject(x);
            try {
                roomID = sensorToImport.getString("room");
            } catch (NullPointerException ok) {
                continue;
            }
            try {
                String sensorName = sensorToImport.getString("name");
                String sensorType = sensorToImport.getString("type");
                String sensorUnit = sensorToImport.getString("units");
                String sensorStartDate = sensorToImport.getString("start_date");
                Date objectDate = null;
                List<SimpleDateFormat> knownPatterns = new ArrayList<>();
                knownPatterns.add(new SimpleDateFormat("dd-MM-yyyy", new Locale("en", "US")));
                knownPatterns.add(new SimpleDateFormat("dd/MM/yyyy", new Locale("en", "US")));
                knownPatterns.add(new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", new Locale("en", "US")));
                for (SimpleDateFormat pattern : knownPatterns) {
                    try {
                        objectDate = pattern.parse(sensorStartDate);
                    } catch (ParseException c) {
                        c.getErrorOffset();
                    }
                }
                HouseSensor importedSensor = new HouseSensor(sensorName, new SensorType(sensorType, sensorUnit), objectDate);
                for (Room r : houseRooms.getRooms()) {
                    if (r.getName().equals(roomID)) {
                        r.addSensor(importedSensor);
                        addedSensors++;
                    }
                }
            } catch (NullPointerException ok) {
                // Do stuff}
            }
        }
        return addedSensors;
    }
}
