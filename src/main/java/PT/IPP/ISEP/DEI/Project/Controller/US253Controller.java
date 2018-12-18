package PT.IPP.ISEP.DEI.Project.Controller;

import PT.IPP.ISEP.DEI.Project.Model.Room;
import PT.IPP.ISEP.DEI.Project.Model.Sensor;
import PT.IPP.ISEP.DEI.Project.Model.SensorList;

/** US253: As an Administrator, I want to add a new sensor to a room from the list of available sensor types,
 * in order to configure it.
 **/

public class US253Controller {


    public US253Controller() {
        /*
         * Builder US253Controller(), with no parameters,
         * as it will only be used in UI to apply methods on given inputs
         */
    }
    public void addSensorToRoom(Room room,Sensor sensorToAdd) {
        room.getRoomSensorList().getSensorList().add(sensorToAdd);
    }
}
