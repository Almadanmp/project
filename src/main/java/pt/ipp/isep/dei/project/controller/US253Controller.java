package pt.ipp.isep.dei.project.controller;

import pt.ipp.isep.dei.project.model.Room;
import pt.ipp.isep.dei.project.model.Sensor;
import pt.ipp.isep.dei.project.model.SensorList;

/** US253: As an Administrator, I want to add a new sensor to a room from the list of available sensor types,
 * in order to configure it.
 **/

public class US253Controller {

    private Room mRoom;

    public US253Controller() {
        /*
         * Builder US253Controller(), with no parameters,
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
}
