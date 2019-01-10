package pt.ipp.isep.dei.project.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RoomList {
    private List<Room> mRoomList;
    private String mStringResult = "---------------\n";
    private String mStringInvalidList = "Invalid List - List is Empty\n";
    private String mStringDesignation =") Designation: ";
    private String mStringHouseFloor = "House Floor: ";
    private String mStringWidth = "Width: ";
    private String mStringLength ="Length: ";
    private String mStringHeight ="Height: ";


    public RoomList() {
        this.mRoomList = new ArrayList<>();
    }

    public boolean doesListOfRoomsContainRoomByName(String name) {
        for (Room room : getRoomList()) {
            if ((room.getRoomName().equals(name))) {
                return true;
            }
        }
        return false;
    }

    public List<Room> getRoomList() {
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
        for (Room aux : mRoomList) {
            if (roomName.equals(aux.getRoomName())) {
                return aux;
            }
        }
        return null;
    }

    public boolean matchRoom(String roomToMatch) {
        for (Room r : mRoomList) {
            if (r.getRoomName().equals(roomToMatch)) {
                return true;
            }
        }
        return false;
    }

    public List<Integer> matchRoomIndexByString(String input) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < mRoomList.size(); i++) {
            if (mRoomList.get(i).getRoomName().equals(input)) {
                result.add(i);
            }
        }
        return result;
    }

    public String printElementsByIndex(List<Integer> indexes) {
        StringBuilder result = new StringBuilder();
        for (Integer indexe : indexes) {
            int pos = indexe;
            result.append(indexe).append(") ").append(mRoomList.get(pos).getRoomName()).append(", ").append(mRoomList.get(pos).getHouseFloor()).append(", ").append(mRoomList.get(pos).getRoomWidth()).append(", ").append(mRoomList.get(pos).getRoomLength()).append(", ").append(this.mRoomList.get(pos).getRoomHeight()).append(".\n");
        }
        return result.toString();
    }

    public String printRoomList(House house) {
        StringBuilder result = new StringBuilder(this.mStringResult);
        if (house.getRoomList().getRoomList().isEmpty()) {
            return this.mStringInvalidList;
        }
        for (int i = 0; i < house.getRoomList().getRoomList().size(); i++) {
            Room aux = house.getRoomList().getRoomList().get(i);
            result.append(i).append(this.mStringDesignation).append(aux.getRoomName()).append(" | ");
            result.append(this.mStringHouseFloor).append(aux.getHouseFloor()).append(" | ");
            result.append(this.mStringWidth).append(aux.getRoomWidth()).append(" | ");
            result.append(this.mStringLength).append(aux.getRoomLength()).append(" | ");
            result.append(this.mStringHeight).append(aux.getRoomHeight()).append("\n");
        }
        result.append(this.mStringResult);
        return result.toString();
    }

    public String printRooms() {
        StringBuilder result = new StringBuilder(this.mStringResult);
        if (this.getRoomList().isEmpty()) {
            return  this.mStringInvalidList;
        }
        for (int i = 0; i < this.getRoomList().size(); i++) {
            Room aux = this.getRoomList().get(i);
            result.append(i).append(this.mStringDesignation).append(aux.getRoomName()).append(" | ");
            result.append(this.mStringHouseFloor).append(aux.getHouseFloor()).append(" | ");
            result.append(this.mStringWidth).append(aux.getRoomWidth()).append(" | ");
            result.append(this.mStringLength).append(aux.getRoomLength()).append(" | ");
            result.append(this.mStringHeight).append(aux.getRoomHeight()).append("\n");

        }
        result.append(this.mStringResult);
        return result.toString();
    }


    @Override
    public boolean equals(Object testObject) {
        if (this == testObject) {
            return true;
        }
        if (!(testObject instanceof RoomList)) {
            return false;
        }
        RoomList list = (RoomList) testObject;
        return Arrays.equals(this.getRoomList().toArray(), list.getRoomList().toArray());
    }

    @Override
    public int hashCode() {
        return 1;
    }


}

