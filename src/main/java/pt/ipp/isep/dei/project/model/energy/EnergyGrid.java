package pt.ipp.isep.dei.project.model.energy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import pt.ipp.isep.dei.project.dddplaceholders.Root;
import pt.ipp.isep.dei.project.model.Metered;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.DeviceList;
import pt.ipp.isep.dei.project.model.device.log.LogList;
import pt.ipp.isep.dei.project.model.room.Room;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Class that represents an Energy Grid present in a House.
 */
@Entity
public class EnergyGrid implements Metered, Root {

    @Id
    private String name;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "energyGridId")
    private List<Room> rooms;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval=true)
    @LazyCollection(LazyCollectionOption.FALSE) // LazyCollection fixes MultipleBag fetch Problem without having to
    // change fetch type from EAGER to LAZY
    @JoinColumn(name = "energyGridId")
    private List<PowerSource> powerSourceList;
    private Double maxContractedPower;
    private String houseId;

    private static final String BUILDER = "---------------\n";

    /**
     * Empty Constructor to use when importing Energy Grids from XML files.
     */
    public EnergyGrid() {
        this.rooms = new ArrayList<>();
        this.powerSourceList = new ArrayList<>();
    }

    /**
     * Standard Energy Grid constructor, used for creating energy grids.
     *
     * @param name               is the name of the grid.
     * @param maxContractedPower is the value of the maximum power contracted.
     */
    public EnergyGrid(String name, Double maxContractedPower, String houseId) {
        this.rooms = new ArrayList<>();
        this.powerSourceList = new ArrayList<>();
        this.name = name;
        this.maxContractedPower = maxContractedPower;
        this.houseId = houseId;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = new ArrayList<>(rooms);
    }

    /**
     * Getter for name of the energy grid.
     *
     * @return returns attribute name of the energy grid.
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for list of rooms that are contained in the energy grid.
     *
     * @return returns a list with all the rooms contained in this energy grid.
     */
    public List<Room> getRoomList() {
        return new ArrayList<>(rooms);
    }

    /**
     * Standard getter method, to return the Maximum contracted power.
     *
     * @return the number of the max power contracted.
     */
    public Double getMaxContractedPower() {
        return maxContractedPower;
    }

    /**
     * Method accesses the sum of nominal powers of all rooms and devices connected to a grid..
     *
     * @return is the sum of nominal powers of all rooms and devices connected to a grid.
     */
    public double getNominalPower() {
        double result = 0;
        for (Room r : rooms) {
            result += r.getNominalPower();

        }
        return result;
    }

    /**
     * Getter for the list of power sources connected to the energy grid.
     *
     * @return the list of power sources connected to the energy grid.
     */
    @JsonIgnore
    public List<PowerSource> getPowerSourceList() {
        return new ArrayList<>(powerSourceList);
    }

    /**
     * Standard setter method, to define the list of power sources.
     *
     * @param powerSourceList is the PowerSourceList we want to add to the Energy Grid.
     */
    public void setPowerSourceList(List<PowerSource> powerSourceList) {
        this.powerSourceList = new ArrayList<>(powerSourceList);
    }

    /**
     * Standard setter method, to define the value of the max contracted power.
     *
     * @param power is the value of the max contracted power.
     */
    boolean setMaxContractedPower(double power) {
        if (power < 0) {
            return false;
        }
        this.maxContractedPower = power;
        return true;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getHouseId() {
        return this.houseId;
    }

    /**
     * Setter for a power source to the energy grid.
     *
     * @param powerSource power source to be added to the energy grid.
     * @return returns true case the power source to be added is already associated to the grid.
     */
    public boolean addPowerSource(PowerSource powerSource) {
        if (powerSourceList.contains(powerSource)) {
            return false;
        }
        return powerSourceList.add(powerSource);
    }

    /**
     * Setter for a new room to be added to the energy grid.
     *
     * @param room Room to be added to the list of rooms in the energy grid.
     * @return returns true if the room is actually added to the energy grid.
     */
    public boolean addRoom(Room room) {
        if (rooms.contains(room)) {
            return false;
        }
        return this.rooms.add(room);
    }

    /**
     * Creates a string displaying the name of the grid and respective max nominal power.
     *
     * @return returns a string displaying the name of the grid and respective max nominal power.
     */
    public String buildString() {
        String result;
        result = "Energy Grid: " + this.name + ", Max Power: " + this.getMaxContractedPower();
        return result;

    }

    /**
     * This method receives an index as parameter and gets a room from energy grid's room list.
     *
     * @param index the index of the Room.
     * @return returns room that corresponds to index.
     */
    public Room getRoom(int index) {
        if (this.rooms.isEmpty()) {
            throw new IndexOutOfBoundsException("The room list is empty.");
        }
        return rooms.get(index);
    }


    /**
     * String Builder of the RoomList.
     *
     * @return a String of the Rooms in the RoomList.
     */
    public String buildEnergyGridRoomsAsString() {
        StringBuilder result = new StringBuilder(BUILDER);
        if (rooms.isEmpty()) {
            return "Invalid List - List is Empty\n";
        }
        for (int i = 0; i < rooms.size(); i++) {
            Room aux = rooms.get(i);
            result.append(i).append(") ID: ").append(aux.getId()).append(" |").
                    append(" Description: ").append(aux.getDescription()).append(" |\n");
        }
        result.append(BUILDER);
        return result.toString();
    }


    /**
     * Method gets all devices associated to energy grid
     *
     * @return energy grid's entire DeviceList
     */
    public DeviceList getDeviceList() {
        DeviceList devices = new DeviceList();
        for (Room r : rooms) {
            devices.addDevicesToThisDeviceList(r.getDeviceList());
        }
        return devices;
    }

    /**
     * Method goes through all rooms associated to energy grid and returns
     * the number of devices in each room
     *
     * @return energy grid's associated devices as int
     */
    public int getNumberOfDevices() {
        int sum = 0;
        for (Room r : rooms) {
            sum += r.getNumberOfDevices();

        }
        return sum;
    }

    /**
     * This method receives an index as parameter and gets a device from energy grid's device list.
     *
     * @param index the index of the device
     * @return returns device that corresponds to index.
     */
    public Device getDeviceByIndex(int index) {
        DeviceList deviceList = this.getDeviceList();
        if (deviceList.isEmpty()) {
            throw new IndexOutOfBoundsException("The device list is empty.");
        }
        return deviceList.get(index);
    }

    /**
     * Method to removeGeographicArea a room from the energy grid.
     *
     * @param room the room we want to removeGeographicArea from the energy grid.
     * @return returns true if the room is successfully removed from the energy grid.
     */
    public boolean removeRoom(Room room) {
        if (rooms.contains(room)) {
            rooms.remove(room);
            return true;
        }
        return false;
    }

    boolean removeRoomById(String roomID) {
        for (Room r : this.rooms) {
            if (r.getId().equals(roomID)) {
                rooms.remove(r);
                return true;
            }
        }
        return false;
    }

    /**
     * Creates a string displaying the name of the devices in the energy grid.
     *
     * @return returns a string displaying the name of the devices in the energy grid.
     */
    public String buildDeviceListString() {
        return this.getDeviceList().buildString();
    }

    /**
     * Creates a String with the device index, device type, device name and the room in which the device is contained.
     *
     * @return a String with the device index, device type, device name and the room in which the device is contained.
     */
    public String buildDeviceListWithTypeString() {
        StringBuilder result = new StringBuilder(BUILDER);
        for (Room r : rooms) {
            DeviceList devices = r.getDeviceList();
            for (int i = 0; i < devices.size(); i++) {
                String deviceType = devices.get(i).getType();
                result.append(r.buildDevicesStringByType(deviceType));
            }
        }
        result.append(BUILDER);
        return result.toString();
    }

    /**
     * US722 As a Power User [or Administrator], I want the sum of the energy consumption of all energy-metered rooms
     * in the grid in the interval.
     *
     * @param initialDate for metering period.
     * @param finalDate   for metering period.
     * @return total metered energy consumption of a grid in a given time interval.
     */
    public double getGridConsumptionInInterval(Date initialDate, Date finalDate) {
        double consumption = 0;
        for (Room r : rooms) {
            consumption += r.getConsumptionInInterval(initialDate, finalDate);

        }
        return consumption;
    }

    /**
     * This method goes through every room in list and returns logs contained in interval given.
     *
     * @param startDate the initial date of the interval
     * @param endDate   the final date of the interval
     * @return log list with every log contained in interval given.
     */
    public LogList getLogsInInterval(Date startDate, Date endDate) {
        LogList logsInInterval = new LogList();
        for (Room r : rooms) {
            logsInInterval.addLogList(r.getLogsInInterval(startDate, endDate));
        }
        return logsInInterval;
    }

    /**
     * This method checks if energy grid's Device List is empty.
     *
     * @return true if energy grid's DeviceList is empty, false otherwise.
     **/
    public boolean isDeviceListEmpty() {
        int sum = 0;
        for (Room r : rooms) {
            if (r.getDeviceListSize() != 0) {
                sum = sum + 1;
            }
        }
        return sum <= 0;
    }

    /**
     * This method checks if energy grid's Room List is empty.
     *
     * @return true if energy grid's RoomList is empty, false otherwise.
     **/
    public boolean isRoomListEmpty() {
        return rooms.isEmpty();
    }

    /**
     * This method checks the Room List's size.
     *
     * @return energy grid's RoomList size as int
     **/
    public int roomListSize() {
        return rooms.size();
    }

    /**
     * Method to calculate the energy consumed in a given time interval
     *
     * @param time is the timer interval we wish to use to measure the energy consumed
     * @return the energy consumed.
     */
    @Override
    public double getEnergyConsumption(float time) {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EnergyGrid eg = (EnergyGrid) o;
        return Objects.equals(name, eg.getName());
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
