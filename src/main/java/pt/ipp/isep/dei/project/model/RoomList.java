package pt.ipp.isep.dei.project.model;

import pt.ipp.isep.dei.project.model.device.DeviceList;
import pt.ipp.isep.dei.project.model.device.log.LogList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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

    public List<Room> getRooms() {
        return rooms;
    }

    /**
     * Method that adds a Room to the RoomList.
     *
     * @param room is the room we want to addWithoutPersisting.
     * @return true if the room was successfully added to the RoomList, false otherwise.
     */
    public boolean add(Room room) {
        if (!(rooms.contains(room))) {
            rooms.add(room);
            return true;
        } else {
            return false;
        }
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
     * Method for creating a new room with all it's parameters,
     * the method checks the room name to see if it already exists before creating it
     *
     * @param roomDesignation room name
     * @param roomHouseFloor floor of the house where room is located
     * @param width from room size
     * @param length from room size
     * @param height from room size
     * @return new created room
     */

    Room createRoom(String roomDesignation, int roomHouseFloor, double width, double length, double height) {
        for (Room r : this.rooms) {
            String designation = r.getName();
            if (roomDesignation.equals(designation)) {
                return r;
            }
        }
        return new Room(roomDesignation, roomHouseFloor, width, length, height);
    }

    /**
     * String Builder of the RoomList.
     *
     * @return a String of the Rooms in the RoomList.
     */
    public String buildString() {
        StringBuilder result = new StringBuilder("---------------\n");
        if (this.isEmpty()) {
            return "Invalid List - List is Empty\n";
        }
        for (int i = 0; i < this.size(); i++) {
            Room aux = this.get(i);
            result.append(i).append(") Designation: ").append(aux.getName()).append(" | ");
            result.append("House Floor: ").append(aux.getFloor()).append(" | ");
            result.append("Width: ").append(aux.getWidth()).append(" | ");
            result.append("Length: ").append(aux.getLength()).append(" | ");
            result.append("Height: ").append(aux.getHeight()).append("\n");

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
     * @param room is the room we want to removeGeographicArea from the roomList.
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
            result += r.getEstimateConsumptionOverTimeByDeviceType(deviceType, time);
        }
        return Math.floor(result * 10) / 10;
    }

    /**
     * Method accesses the sum of nominal powers of all devices connected to every room in room list.
     *
     * @return is the sum of nominal powers of all rooms.
     */

    public double getNominalPower() {
        double result = 0;
        for (Room r : rooms) {
            result += r.getNominalPower();
        }
        return result;
    }

    /**
     * This method goes through the room list and returns the consumption from every room in the
     * interval given.
     *
     * @param initialDate for metering period.
     * @param finalDate   for metering period.
     * @return total metered energy consumption of a room list in a given time interval.
     */

    double getConsumptionInInterval(Date initialDate, Date finalDate) {
        double consumption = 0;
        for (Room r : this.rooms) {
            consumption += r.getConsumptionInInterval(initialDate, finalDate);
        }
        return consumption;
    }

    /**
     * This method goes through every room in list and returns logs contained in interval given.
     *
     * @param initialDate the date of the beginning of the interval
     * @param finalDate   the date of the emd of the interval
     * @return log list with every log contained in interval given.
     */
    public LogList getLogsInInterval(Date initialDate, Date finalDate) {
        LogList result = new LogList();
        for (Room r : this.rooms) {
            LogList tempList = r.getLogsInInterval(initialDate, finalDate);
            result.addLogList(tempList);
        }
        return result;
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
     * Checks the room list size and returns the size as int.\
     *
     * @return RoomList size as int
     **/
    public int size() {
        return this.rooms.size();
    }

    /**
     * This method receives an index as parameter and gets a room from room list.
     *
     * @param index the index of the room
     * @return returns room that corresponds to index.
     */
    public Room get(int index) {
        if (this.rooms.isEmpty()) {
            throw new IndexOutOfBoundsException("The room list is empty.");
        }
        return this.rooms.get(index);
    }

    /**
     * This method checks if every room in room list has no devices.
     *
     * @return true if list has no devices, false otherwise.
     **/
    boolean isDeviceListEmpty() {
        return this.getDeviceList().isEmpty();
    }

    /**
     * Checks how many devices are associated to room list.\
     *
     * @return number of devices associated to room list as int
     **/
    int getNumberOfDevices() {
        int sum = 0;
        for (Room r : rooms) {
            sum = sum + r.getDeviceListSize();
        }
        return sum;
    }

    /**
     * Method for building string to be displayed to user so he can see Devices of a certain type listed
     *
     * @param deviceType type of device user wants to list
     * @return list of devices of that type param
     */

    StringBuilder buildDeviceListByType(String deviceType) {
        StringBuilder result = new StringBuilder();
        for (Room r : this.rooms) {
            if (r != null) {
                result.append(r.buildDevicesStringByType(deviceType));
            }
        }
        return result;
    }

    /**
     * Getter (array of rooms)
     *
     * @return array of rooms
     */
    Room[] getElementsAsArray() {
        int sizeOfResultArray = rooms.size();
        Room[] result = new Room[sizeOfResultArray];
        for (int i = 0; i < rooms.size(); i++) {
            result[i] = rooms.get(i);
        }
        return result;
    }
    /**
     * Method to check if an instance of this class is equal to another object.
     * Necessary for adding rooms to list.
     *
     * @param testObject is the object we want to check for equality.
     * @return is true if the object is a power source list with the same contents.
     */

    @Override
    public boolean equals(Object testObject) {
        if (this == testObject) {
            return true;
        }
        if (!(testObject instanceof RoomList)) {
            return false;
        }
        RoomList list = (RoomList) testObject;
        return Arrays.equals(this.getElementsAsArray(), list.getElementsAsArray());
    }

    @Override
    public int hashCode() {
        return 1;
    }


}

