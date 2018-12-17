package PT.IPP.ISEP.DEI.Project.Controller;

import PT.IPP.ISEP.DEI.Project.Model.Room;

import java.util.*;

/**
 * As a Regular User, I want to get the maximum temperature in a room in a given day,
 * in order to check if heating/cooling in that room was effective.
 */
public class US610Controller {
    private Room mRoom;

    public US610Controller(Room room){
        this.mRoom=room;
    }

    public double getMaxTemperatureInARoomOnAGivenDay(Date day){
        return mRoom.getMaxTemperatureInARoomOnAGivenDay(day);
    }
}
