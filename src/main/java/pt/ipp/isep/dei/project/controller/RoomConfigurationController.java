package pt.ipp.isep.dei.project.controller;

import pt.ipp.isep.dei.project.model.*;

import java.util.List;

/** US253: As an Administrator, I want to add a new sensor to a room from the list of available sensor types,
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

    public void addSensorToRoom(Room room, Sensor sensorToAdd) {
        this.mRoom = room;
        this.mRoom.getRoomSensorList().getSensorList().add(sensorToAdd);
    }

    public SensorList getSensorsFromRoom() {
        return this.mRoom.getRoomSensorList();
    }

    public boolean doesSensorListInAGeoAreaContainASensorByName(String name, GeographicAreaList ga) {
        boolean result = true;
        for (GeographicArea g : ga.getGeographicAreaList()) {
            result = g.doesSensorListInAGeoAreaContainASensorByName(name);
        }
        return result;
    }

    public Sensor getSensorFromName(String sensorName, GeographicArea ga) {
        for (Sensor s : ga.getSensorList().getSensorList()) {
            if (s.getName().equals(sensorName)) mSensor = s;
        }
        return mSensor;
    }

    public Room getRoomFromName(String roomName, House house) {
        for (Room r : house.getmRoomList().getListOfRooms()) {
            if (r.getRoomName().equals(roomName)) {
                mRoom = r;
            }
        }
        return mRoom;
    }

    public String printRoomList(House house) {
        return house.getmRoomList().printRoomList(house);
    }

    public String printRoom (Room room){
        return room.printRoom();
    }
    public String printRoomElementsByIndex(List<Integer> listOfIndexesOfRoom, House house) {
        return house.getmRoomList().printElementsByIndex(listOfIndexesOfRoom);
    }
    public List<Integer> matchRoomIndexByString(String input, House house){
        return house.getmRoomList().matchRoomIndexByString(input);
    }
}
