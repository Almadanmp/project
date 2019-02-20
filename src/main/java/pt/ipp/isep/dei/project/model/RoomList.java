package pt.ipp.isep.dei.project.model;

import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.DeviceList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class that groups a number of Rooms in a House.
 */

public class RoomList {
    private List<Room> rooms;

    /**
     * RoomList() empty constructor that initializes an ArrayList of Rooms.
     */
    public RoomList() {
        this.rooms = new ArrayList<>();
    }

    /**
     * RoomList Getter.
     *
     * @return a List of Rooms.
     */
    public List<Room> getList() {
        return this.rooms;
    }

    /**
     * Method that adds a Room to the RoomList.
     *
     * @param room is the room we want to add.
     * @return true if the room was successfully added to the RoomList, false otherwise.
     */
    public boolean addRoom(Room room) {
        if (!(rooms.contains(room))) {
            rooms.add(room);
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
    Room getRoomByName(String roomName) {
        for (Room aux : rooms) {
            if (roomName.equals(aux.getRoomName())) {
                return aux;
            }
        }
        return null;
    }

    /**
     * Method that returns a DeviceList with all the devices of the RoomList.
     *
     * @return a DeviceList of all the devices in the RoomList.
     */
    DeviceList getDeviceList() {
        DeviceList finalList = new DeviceList();
        for (Room r : this.rooms) {
            finalList.appendListNoDuplicates(r.getDeviceList());
        }
        return finalList;
    }

    /**
     * String Builder of the RoomList.
     *
     * @return a String of the Rooms in the RoomList.
     */
    public String buildRoomsString() {
        StringBuilder result = new StringBuilder("---------------\n");
        if (this.getList().isEmpty()) {
            return "Invalid List - List is Empty\n";
        }
        for (int i = 0; i < this.getList().size(); i++) {
            Room aux = this.getList().get(i);
            result.append(i).append(") Designation: ").append(aux.getRoomName()).append(" | ");
            result.append("House Floor: ").append(aux.getHouseFloor()).append(" | ");
            result.append("Width: ").append(aux.getRoomWidth()).append(" | ");
            result.append("Length: ").append(aux.getRoomLength()).append(" | ");
            result.append("Height: ").append(aux.getRoomHeight()).append("\n");

        }
        result.append("---------------\n");
        return result.toString();
    }

    /**
     * Method that checks if a Room is contained in the RoomList.
     *
     * @param room is the room that we want to see if it's contained in the roomList.
     * @return true if room is contained in the RoomList, false otherwise.
     */
    public boolean contains(Room room) {
        return (this.rooms.contains(room));
    }

    /**
     * Method that removes a Room from the RoomList.
     *
     * @param room is the room we want to remove from the roomList.
     * @return true if room was successfully removed from the roomList, false otherwise.
     */
    public boolean removeRoom(Room room) {
        if (this.contains(room)) {
            this.rooms.remove(room);
            return true;
        }
        return false;
    }

    /**
     * Returns the daily estimate of the consumption of all devices of a given type, in all rooms on this list.
     *
     * @param deviceType the device type
     * @param time       represents a day in minutes
     * @return the sum of all daily estimate consumptions of that type
     */
    double getDailyConsumptionByDeviceType(String deviceType, int time) {
        double result = 0;
        for (Room r : rooms) {
            result += r.getDailyConsumptionByDeviceType(deviceType, time);
        }
        return Math.floor(result * 10) / 10;
    }

    /**
     * This method checks if room list is empty.
     *
     * @return true if list is empty, false otherwise.
     **/
    public boolean isEmpty() {
        return this.rooms.isEmpty();
    }

    /**
     * This method checks if every room in room list has no devices.
     *
     * @return true if list has no devices, false otherwise.
     **/
    public boolean isDeviceListEmpty() {
        return this.getDeviceList().isEmpty();
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

