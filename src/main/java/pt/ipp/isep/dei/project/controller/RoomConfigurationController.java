package pt.ipp.isep.dei.project.controller;

import pt.ipp.isep.dei.project.model.*;

import java.util.List;

public class RoomConfigurationController {

    private Room mRoom;
    private Sensor mSensor;

    /**
     * Empty constructor.
     */

    public RoomConfigurationController() {
        /*
         * Builder RoomConfigurationController() with no parameters.
         * It will only be used in ui to apply methods on given inputs.
         */
    }

    /*USER STORY 230 - As a Room Owner [or Power User, or Administrator], I want to know the total
    nominal power of a room, i.e. the sum of the nominal power of all devices in the
    room. - TERESA VARELA */

    public double getRoomNominalPower(Room room) {
        return room.getNominalPower();
    }


    /* USER STORY 253 - As an Administrator, I want to add a new sensor to a room from the list of available
    sensor types, in order to configure it. - ANDRÉ RUA */

    /**
     * @param name is the name of the sensor we want to look for.
     * @param ga   is the ga where we want to see if the sensor exists.
     * @return is true if the geoArea contains a sensor with given name, false if it doesn't.
     */

    boolean checkIfGAContainsSensorByString(String name, GeographicArea ga) {
        for (Sensor s : ga.getSensorList().getSensorList()) {
            if (s.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param sensorName is the name of the sensor we want to look for
     * @param ga is the ga we want to look for the sensor in.
     * @return is the sensor in the Geo Area with the name that matches given string. Is null if no sensor matches.
     */

    Sensor getSensorFromGAByName(String sensorName, GeographicArea ga) {
        for (Sensor s : ga.getSensorList().getSensorList()) {
            if (s.getName().equals(sensorName))
                mSensor = s;
        }
        return mSensor;
    }

    /**
     *
     * @param roomName is the name of the room we want to look for.
     * @param house is the house where we want to look for the room.
     * @return is the room in the House with the name that matches given string. Is null if no Room matches.
     */

    Room getRoomFromHouseByName(String roomName, House house) {
        for (Room r : house.getRoomList().getRoomList()) {
            if (r.getRoomName().equals(roomName)) {
                mRoom = r;
            }
        }
        return mRoom;
    }

    /**
     *
     * @param house is the house we want to print the roomList from.
     * @return builds a string from the House's room list.
     */

    public String printRoomList(House house) {
        return house.getRoomList().printRoomList(house);
    }

    /**
     *
     * @param room is the room we want to print.
     * @return builds a string from given room.
     */

    public String printRoom(Room room) {
        return room.printRoom();
    }

    /**
     *
     * @param listOfIndexesOfRoom is a list of integers that represent positions in a list.
     * @param house is the house where we want to get lists from.
     * @return builds a string from the individual elements in the House's RoomList that are contained in the positions
     * given by the list of indexes.
     */

    public String printRoomElementsByIndex(List<Integer> listOfIndexesOfRoom, House house) {
        return house.getRoomList().printElementsByIndex(listOfIndexesOfRoom);
    }

    /**
     *
     * @param input is the name of room we want to look for.
     * @param house is the house where we want to look for rooms.
     * @return is a list of integers representing positions in the house's roomList of rooms whose name matches
     * input string.
     */

    public List<Integer> matchRoomIndexByString(String input, House house) {
        return house.getRoomList().matchRoomIndexByString(input);
    }

    /**
     *
     * @param input is the name of sensor we want to look for.
     * @param slist is the sensorlist where we want to look for sensors.
     * @return is a list of integers representing positions in the sensorlist of sensors whose name matches
     * input string.
     */

    public List<Integer> matchSensorIndexByString(String input, SensorList slist) {
        return slist.matchSensorListIndexByString(input);
    }

    /**
     *
     * @param sensorList is the sensor list to print.
     * @return builds a string from given sensor list.
     */

    public String printSensorList(SensorList sensorList) {
        return sensorList.printSensorWholeList(sensorList);
    }

    /**
     *
     * @param sensor is sensor we want to print.
     * @return builds a string from given sensor.
     */

    public String printSensor(Sensor sensor) {
        return sensor.printSensor();
    }

    /**
     *
     * @param listOfIndexesOfSensor is a list of integers that represent positions in a list.
     * @param sensorList is the sensorList where we want to get sensors from.
     * @return builds a string from the individual elements in the SensorList that are contained in the positions
     * given by the list of indexes.
     */

    public String printSensorElementsByIndex(List<Integer> listOfIndexesOfSensor, SensorList sensorList) {
        return sensorList.printElementsByIndex(listOfIndexesOfSensor);
    }
}
