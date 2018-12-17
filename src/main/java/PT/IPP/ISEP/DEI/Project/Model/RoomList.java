package PT.IPP.ISEP.DEI.Project.Model;

import java.util.ArrayList;
import java.util.List;

public class RoomList {
    private List<Room> mRooms;

    public RoomList () {
        this.mRooms = new ArrayList<>();
    }
    public RoomList(Room roomToAdd) {
        mRooms = new ArrayList<>();
        mRooms.add(roomToAdd);
    }
    public List<Room> getListOfRooms() {
        return this.mRooms;
    }
    @Override
    public int hashCode() {
        return 1;
    }

}

