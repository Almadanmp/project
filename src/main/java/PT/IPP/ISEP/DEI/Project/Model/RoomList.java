package PT.IPP.ISEP.DEI.Project.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RoomList {
    private List<Room> mRoomList;

    public RoomList() {
        this.mRoomList = new ArrayList<>();
    }

    public RoomList(Room roomToAdd) {
        mRoomList = new ArrayList<>();
        mRoomList.add(roomToAdd);
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
        return this.mRoomList;
    }

    public Room[] getRooms() {
        int sizeOfResultArray = mRoomList.size();
        Room[] result = new Room[sizeOfResultArray];
        for (int i = 0; i < mRoomList.size(); i++) {
            result[i] = mRoomList.get(i);
        }
        return result;
    }


    public boolean addRoom(Room room) {
        if (!(mRoomList.contains(room))) {
            mRoomList.add(room);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Exercise: Get Room By Name From The Room List(String).
     *
     * @param roomName - user input will be designation of the Room.
     * @return it will return the Room if it exists on the arrayList. it will return Null if there is no Room with that
     * name.
     * Description: we create a for cycle to iterate on the arrayList. the index is "i" and it will iterate until the last
     * position of the Room arraylist. we increment at the end of each cycle to go to the next index.
     * On the for cycle we create an auxiliary variable (aux) that will be equal to the Room on the index position at the moment.
     * (we use the get method to get the information from the index).
     * If the user input - roomName equals the name of the Room on that index, we return the Room, if not it will compare with the next position.
     * if no Room with the name of found, it will return null.
     */
    public Room getRoomByName(String roomName) {
        for (int i = 0; i < mRoomList.size(); i++) {
            Room aux = mRoomList.get(i);
            if (roomName.equals(aux.getRoomName())) {
            }
            return aux;

        }
        return null;
    }

    public boolean matchRoom (String roomToMatch){
        for (Room r: mRoomList){
            if (r.getRoomName().equals(roomToMatch)){
                return true;
            }
        }
        return false;
    }

    public boolean checkIfListIsValid() {
        return !mRoomList.isEmpty();
    }

    public String printRoomList() {
        StringBuilder finalString = new StringBuilder();
        String emptyList = "The list is empty.";
        if (mRoomList.isEmpty()) {
            return emptyList;
        }
        finalString.append("Room List:");
        for (Room room : mRoomList) {
            finalString.append(" \n" + "-").append(room.getRoomName()).append(";");
        }
        return finalString.toString();
    }

    public double getSumRoomListMaxPower() {
        double sum = 0;
        for (Room room : mRoomList) {
            sum = +room.getRoomMaxPower();
        }
        return sum;
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

