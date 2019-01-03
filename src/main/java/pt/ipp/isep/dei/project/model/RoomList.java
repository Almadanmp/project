package pt.ipp.isep.dei.project.model;

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

    public List<Integer> matchRoomIndexByString(String input){
        List<Integer> result = new ArrayList<>();
        for(int i = 0; i < mRoomList.size(); i++){
            if (mRoomList.get(i).getRoomName().equals(input)){
                result.add(i);
            }
        }
        return result;
    }

    public String printElementsByIndex (List<Integer> indexes){
        String result = "";
        for (int i = 0; i < indexes.size(); i++){
            result += indexes.get(i) + ") " + mRoomList.get(i).getRoomName() + ", " + mRoomList.get(i).getHouseFloor() + ", " + mRoomList.get(i).getRoomDimensions() + ".\n";
        }
        return result;
    }

    public String printRoomList(House house) {
        String result = "---------------\n";
        if (house.getmRoomList().getListOfRooms().isEmpty()) {
            return "Invalid List - List is Empty\n";
        }
        for (int i = 0; i < house.getmRoomList().getListOfRooms().size(); i++) {
            Room aux = house.getmRoomList().getListOfRooms().get(i);
            result = result + i + ") Designation: " + aux.getRoomName() + " | ";
            result = result + "House Floor: " + aux.getHouseFloor() + " | ";
            result = result + "Dimensions: " + aux.getRoomDimensions() + "\n";
        }
        result = result + "---------------\n";
        System.out.print(result); //TODO remove this and print on UIS, this allows us to test method
        return result;
    }

    public String printRooms() {
        String result = "---------------\n";
        if (this.getListOfRooms().isEmpty()) {
            return "Invalid List - List is Empty\n";
        }
        for (int i = 0; i < this.getListOfRooms().size(); i++) {
            Room aux = this.getListOfRooms().get(i);
            result = result + i + ") Designation: " + aux.getRoomName() + " | ";
            result = result + "House Floor: " + aux.getHouseFloor() + " | ";
            result = result + "Dimensions: " + aux.getRoomDimensions() + "\n";
        }
        result = result + "---------------\n";
        return result;
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
        return Arrays.equals(this.getListOfRooms().toArray(), list.getListOfRooms().toArray());
    }

    @Override
    public int hashCode() {
        return 1;
    }


}

