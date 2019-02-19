package pt.ipp.isep.dei.project.model;

import pt.ipp.isep.dei.project.model.device.Device;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class that groups a number of Rooms in a House.
 */

public class RoomList {
    private List<Room> mRoomList;
    private String mStringResult = "---------------\n";
    private String mStringInvalidList = "Invalid List - List is Empty\n";
    private String mStringDesignation = ") Designation: ";
    private String mStringHouseFloor = "House Floor: ";
    private String mStringWidth = "Width: ";
    private String mStringLength = "Length: ";
    private String mStringHeight = "Height: ";


    public RoomList() {
        this.mRoomList = new ArrayList<>();
    }

    public List<Room> getList() {
        return this.mRoomList;
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

    List<Device> getDeviceList() {
        List<Device> result = new ArrayList<>();
        for (Room r : this.getList()) {
            result.addAll(r.getDeviceList());
        }
        return result;
    }


    public String buildRoomsString() {
        StringBuilder result = new StringBuilder(this.mStringResult);
        if (this.getList().isEmpty()) {
            return this.mStringInvalidList;
        }
        for (int i = 0; i < this.getList().size(); i++) {
            Room aux = this.getList().get(i);
            result.append(i).append(this.mStringDesignation).append(aux.getRoomName()).append(" | ");
            result.append(this.mStringHouseFloor).append(aux.getHouseFloor()).append(" | ");
            result.append(this.mStringWidth).append(aux.getRoomWidth()).append(" | ");
            result.append(this.mStringLength).append(aux.getRoomLength()).append(" | ");
            result.append(this.mStringHeight).append(aux.getRoomHeight()).append("\n");

        }
        result.append(this.mStringResult);
        return result.toString();
    }

    public boolean contains(Room room) {
        return (this.mRoomList.contains(room));
    }

    public boolean removeRoom(Room room) {
        if (this.contains(room)) {
            this.mRoomList.remove(room);
            return true;
        }
        return false;
    }

    /**
     * Returns the daily estimate of the consumption of all devices of a given type, in all rooms on this list.
     *
     * @param deviceType the device type
     * @param time represents a day in minutes
     * @return the sum of all daily estimate consumptions of that type
     */
    double getDailyConsumptionByDeviceType(String deviceType, int time) {
        double result = 0;
        for (Room r : mRoomList) {
            result += r.getDailyConsumptionByDeviceType(deviceType, time);
        }
        return Math.floor(result * 10) / 10;
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
        return Arrays.equals(this.getList().toArray(), list.getList().toArray());
    }

    @Override
    public int hashCode() {
        return 1;
    }


}

