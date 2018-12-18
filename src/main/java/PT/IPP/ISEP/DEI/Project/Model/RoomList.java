package PT.IPP.ISEP.DEI.Project.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RoomList {
    private List<Room> mRooms;

    public RoomList() {
        this.mRooms = new ArrayList<>();
    }

    public RoomList(Room roomToAdd) {
        mRooms = new ArrayList<>();
        mRooms.add(roomToAdd);
    }

    public boolean doesListOfRoomsContainRoomByName(String name) {
        for (Room room : getListOfRooms()) {
            if ((room.getRoomName().equals(name))) {
                return true;
            }
        }
        return false;
    }

    public List<Room> getListOfRooms() {
        return this.mRooms;
    }


    public boolean addRoom(Room room) {
        if (!(mRooms.contains(room))) {
            mRooms.add(room);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean equals(Object testObject) {
        if (this == testObject) {
            return true;
        }
        if (!(testObject instanceof HouseList)) {
            return false;
        }
        RoomList list = (RoomList) testObject;
        return Arrays.equals(this.getListOfRooms().toArray(), list.getListOfRooms().toArray());
    }

    @Override
    public int hashCode() {
        return 1;
    }

}

