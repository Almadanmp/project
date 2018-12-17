package PT.IPP.ISEP.DEI.Project.Model;

import java.util.List;

public class RoomList {
    List<Room> mRooms;

    Public RoomList () {
        this.mRooms = new List<Room>();
    }

    public List<Reading> getListOfRooms() {
        return this.mRooms;
    }

}
