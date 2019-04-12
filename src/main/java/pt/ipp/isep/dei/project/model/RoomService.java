package pt.ipp.isep.dei.project.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.model.device.DeviceList;
import pt.ipp.isep.dei.project.model.device.log.LogList;
import pt.ipp.isep.dei.project.repository.RoomRepository;

import java.util.*;

/**
 * Class that groups a number of Rooms in a House.
 */
@Service
public class RoomService {

    @Autowired
    RoomRepository roomRepository;

    private List<Room> rooms;

    /**
     * RoomList() empty constructor that initializes an ArrayList of Rooms.
     */
    public RoomService() {
        this.rooms = new ArrayList<>();
    }

    /**
     * RoomList() empty constructor that initializes an ArrayList of Rooms.
     */
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
        this.rooms = new ArrayList<>();
    }


//TODO UPDATED METHODS


    public List<Room> getAllRooms() {
        List<Room> finalList = new ArrayList<>();
        Iterable<Room> rooms = roomRepository.findAll();

        for (Room r : rooms) {
            finalList.add(r);
        }
        return finalList;
    }


    /**
     * Method to check if a room with the given ID exists in the repository.
     *
     * @param idToCheck is the id that we want to check for being present.
     * @return is true if a room with the given ID exists, false if it doesn't.
     */
    public boolean idExists(String idToCheck) {
        for (Room r : roomRepository.findAll()) {
            if (r.getName().equals(idToCheck)) {
                return true;
            }
        }
        return false;
    }

    public boolean addPersistence(Room room) {
        Room room2 = roomRepository.findByName(room.getName());
        if (room2 != null) {
            roomRepository.delete(room2);
        }
        roomRepository.save(room);
        return true;
    }

    /**
     * String Builder of the RoomList.
     *
     * @return a String of the Rooms in the RoomList.
     */
    public String buildStringDB() {
        List<Room> rooms = getAllRooms();
        StringBuilder result = new StringBuilder("---------------\n");
        if (rooms.isEmpty()) {
            return "Invalid List - List is Empty\n";
        }
        for (Room r : rooms) {
            result.append(r.getName()).append(") Description: ").append(r.getDescription()).append(" | ");
            result.append("House Floor: ").append(r.getFloor()).append(" | ");
            result.append("Width: ").append(r.getWidth()).append(" | ");
            result.append("Length: ").append(r.getLength()).append(" | ");
            result.append("Height: ").append(r.getHeight()).append("\n");

        }
        result.append("---------------\n");
        return result.toString();
    }

    /**
     * Method for creating a new room with all it's parameters,
     * the method checks the room name to see if it already exists before creating it
     *
     * @param roomDesignation room name
     * @param roomHouseFloor  floor of the house where room is located
     * @param width           from room sizeDB
     * @param length          from room sizeDB
     * @param height          from room sizeDB
     * @return new created room
     */

    public Room createRoom(String roomDesignation, String roomDescription, int roomHouseFloor, double width, double length, double height, String houseID, String energyGridID) {
        for (Room r : getAllRooms()) {
            String designation = r.getName();
            if (roomDesignation.equals(designation)) {
                return r;
            }
        }
        return new Room(roomDesignation, roomDescription, roomHouseFloor, width, length, height, houseID, energyGridID);
    }

    /**
     * Method that save a Room to the RoomRepository.
     * <p>
     * It is also adding to the local list while the project is being refactored an lists removed
     *
     * @param room is the room we want to save.
     * @return true if the room was successfully saved to the repository, false otherwise.
     */
    public void addWithPersistence(Room room) {
        roomRepository.save(room);
    }

    /**
     * Method that removes a Room from the RoomList.
     *
     * @param room is the room we want to removeGeographicArea from the roomList.
     * @return true if room was successfully removed from the roomList, false otherwise.
     */
    public boolean removeRoom(Room room) {
        Optional<Room> aux = roomRepository.findById(room.getName());
        if (aux.isPresent()) {
            roomRepository.delete(room);
            return true;
        }
        return false;
    }

    /**
     * This method checks if room list is empty.
     *
     * @return true if list is empty, false otherwise.
     **/
    public boolean isEmptyDB() {
        return getAllRooms().isEmpty();
    }

    /**
     * Checks the room list sizeDB and returns the sizeDB as int.\
     *
     * @return RoomList sizeDB as int
     **/
    public int sizeDB() {
        return getAllRooms().size();
    }

    /**
     * This method receives an index as parameter and gets a room from room list.
     *
     * @param name the name of the room
     * @return returns room that corresponds to index.
     */
    public Room getDB(String name) {
        Room room;
        Optional<Room> aux = roomRepository.findById(name);
        if (aux.isPresent()) {
            room = aux.get();
            return room;
        }
        throw new IndexOutOfBoundsException("ERROR: No Room was file with the following name: " + name + " .");
    }

//TODO OLD METHODS

    /**
     * String Builder of the RoomList.
     *
     * @return a String of the Rooms in the RoomList.
     */
    public String buildString() {
        StringBuilder result = new StringBuilder("---------------\n");
        if (this.isEmptyDB()) {
            return "Invalid List - List is Empty\n";
        }
        for (int i = 0; i < this.sizeDB(); i++) {
            Room aux = this.get(i);
            result.append(i).append(") Designation: ").append(aux.getName()).append(" | ");
            result.append("Description: ").append(aux.getDescription()).append(" | ");
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
     * Method that finds a given object in the repository by its ID
     *
     * @param idToFind is the ID that we want to look for.
     * @return is an Optional that either contains the object if it existed, or a null.
     */

    public Optional<Room> findByID(String idToFind) {
        return roomRepository.findById(idToFind);
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
        if (!(testObject instanceof RoomService)) {
            return false;
        }
        RoomService list = (RoomService) testObject;
        return Arrays.equals(this.getElementsAsArray(), list.getElementsAsArray());
    }

    @Override
    public int hashCode() {
        return 1;
    }


}

