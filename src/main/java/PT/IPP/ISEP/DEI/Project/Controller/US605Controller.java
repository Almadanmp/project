package PT.IPP.ISEP.DEI.Project.Controller;

import PT.IPP.ISEP.DEI.Project.Model.Room;
import PT.IPP.ISEP.DEI.Project.Model.RoomList;

import java.util.Date;
import java.util.GregorianCalendar;

public class US605Controller {

    /**
     * US605   As a Regular User, I want to get the current temperature in a room, in order to check
     * if it meets my personal comfort requirements.
     */

    private RoomList mListRoom;
    private Date mDate;

    public US605Controller(RoomList room) {
        this.mListRoom = room;
    }

    public double getCurrentRoomTemperature(Date day) {
        double currentTemperature = 0;
        for (Room r : this.mListRoom.getListOfRooms()) {
            currentTemperature = r.getCurrentRoomTemperature(day);
        }
        return currentTemperature;
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