package pt.ipp.isep.dei.project.controller;

import pt.ipp.isep.dei.project.model.*;

import java.util.List;

/**
 * US253: As an Administrator, I want to add a new sensor to a room from the list of available sensor types,
 * in order to configure it.
 **/

public class RoomConfigurationController {

    private Room mRoom;
    private Sensor mSensor;

    public RoomConfigurationController() {
        /*
         * Builder RoomConfigurationController(), with no parameters,
         * as it will only be used in ui to apply methods on given inputs
         */
    }


    public boolean doesSensorListInAGeoAreaContainASensorByName(String name, GeographicArea ga) {
        for (Sensor s : ga.getSensorList().getSensorList()) {
            if (s.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public Sensor getSensorFromName(String sensorName, GeographicArea ga) {
        for (Sensor s : ga.getSensorList().getSensorList()) {
            if (s.getName().equals(sensorName))
                mSensor = s;
        }
        return mSensor;
    }

    public Room getRoomFromName(String roomName, House house) {
        for (Room r : house.getRoomList().getRoomList()) {
            if (r.getRoomName().equals(roomName)) {
                mRoom = r;
            }
        }
        return mRoom;
    }

    public String printRoomList(House house) {
        return house.getRoomList().printRoomList(house);
    }

    public String printRoom(Room room) {
        return room.printRoom();
    }

    public String printRoomElementsByIndex(List<Integer> listOfIndexesOfRoom, House house) {
        return house.getRoomList().printElementsByIndex(listOfIndexesOfRoom);
    }

    public List<Integer> matchRoomIndexByString(String input, House house) {
        return house.getRoomList().matchRoomIndexByString(input);
    }

    public List<Integer> matchSensorIndexByString(String input,SensorList slist) {
        return slist.matchSensorListIndexByString(input);
    }

    public String printSensorList(SensorList sensorList) {
        return sensorList.printSensorWholeList(sensorList);
    }

    public String printSensor(Sensor sensor) {
        return sensor.printSensor();
    }
    public String printSensorElementsByIndex(List<Integer> listOfIndexesOfSensor, SensorList sensorList){
        return sensorList.printElementsByIndex(listOfIndexesOfSensor);
    }
}
