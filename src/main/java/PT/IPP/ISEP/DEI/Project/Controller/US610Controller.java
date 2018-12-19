package PT.IPP.ISEP.DEI.Project.Controller;

import PT.IPP.ISEP.DEI.Project.Model.Room;
import PT.IPP.ISEP.DEI.Project.Model.RoomList;

import java.util.*;

/**
 * As a Regular User, I want to get the maximum temperature in a room in a given day,
 * in order to check if heating/cooling in that room was effective.
 */
public class US610Controller {
    private RoomList mListRoom;
    private Date mDate;


    public US610Controller(RoomList room){
        this.mListRoom=room;
    }

    public double getMaxTemperatureInARoomOnAGivenDay(Date day){
        double max=-275;
        for(Room r: this.mListRoom.getListOfRooms()){
        max=r.getMaxTemperatureInARoomOnAGivenDay(day);
    } return max;
    }

    public boolean doesListContainRoomByName(String name){
        return this.mListRoom.doesListOfRoomsContainRoomByName(name);
    }
    public boolean doesSensorListInARoomContainASensorByName(String name){
        boolean result = true;
        for (Room r: mListRoom.getRooms()){
        result= r.getRoomSensorList().doesSensorListContainSensorByName(name);}
        return result;
    }

    public Date createDate(int year, int month, int day) {
        Date date = new GregorianCalendar(year, month, day).getTime();
        this.mDate = date;
        return this.mDate;
    }
}
