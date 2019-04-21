package pt.ipp.isep.dei.project.model.room;

import pt.ipp.isep.dei.project.model.Metered;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.DeviceList;
import pt.ipp.isep.dei.project.model.device.log.LogList;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Date;
import java.util.Objects;

/**
 * Class that represents a Room of a House.
 */
@Entity
public class Room implements Metered {

    @Id
    private String roomName;

    private String description;
    private int houseFloor;
    private double roomWidth;
    private double roomLength;
    private double roomHeight;

    @Transient
    private DeviceList deviceList;

    private String houseID;
    private String energyGridId;

    /**
     * Room() Constructor receiving 5 parameters and initializing 2 Lists, AreaSensorList and DeviceList.
     *
     * @param name       of the room
     * @param houseFloor of the room
     * @param width      of the room
     * @param length     of the room
     * @param height     of the room
     */
    public Room(String name, String description, int houseFloor, double width, double length, double height,
                String houseID, String energyGridId) {
        this.roomName = name;
        this.description = description;
        this.houseFloor = houseFloor;
        this.roomWidth = width;
        this.roomLength = length;
        this.roomHeight = height;
        this.deviceList = new DeviceList();
        this.houseID = houseID;
        this.energyGridId = energyGridId;
    }

    protected Room() {
        this.deviceList = new DeviceList();
    }

    /**
     * Room Height Getter.
     *
     * @return a double that represents the room height.
     */
    public double getHeight() {
        return roomHeight;
    }

    /**
     * Room Length Getter.
     *
     * @return a double that represents the room length.
     */
    public double getLength() {
        return roomLength;
    }

    /**
     * Room Width Getter.
     *
     * @return a double that represents the room width.
     */
    public double getWidth() {
        return roomWidth;
    }

    /**
     * Room description getter.
     *
     * @return String of the description of the room.
     */
    public String getDescription() {
        return description;
    }

    public String getHouseID() {
        return houseID;
    }

    public String getEnergyGridID() {
        return energyGridId;
    }

    /**
     * House Floor Getter.
     *
     * @return a int that represents the room house floor.
     */
    public int getFloor() {
        return houseFloor;
    }

    public String getId() {
        return roomName;
    }

    /**
     * Method that gets the room's device list.
     *
     * @return room's DeviceList
     */
    public DeviceList getDeviceList() {
        return this.deviceList;
    }

    public void setDeviceList(DeviceList deviceList) {
        this.deviceList = deviceList;
    }

    /**
     * Method for printing all available devices in a room.
     * Used in US201 and US215
     *
     * @return string with devices in room.
     */

    public String buildDeviceListString() {
        return deviceList.buildString();
    }

    /**
     * This method will go through the room's device list and addWithoutPersisting all the devices'
     * The result is the room's total nominal power and will be returned as a double
     *
     * @return room's total nominal power (double)
     */

    public double getNominalPower() {
        return this.deviceList.getNominalPower();
    }

    /**
     * Returns the energy consumption in a given time interval, that is, the sum of the energy consumption
     * of all metered devices' logs in the room that are fully within the given time interval.
     *
     * @param initialDate defines the start of the interval.
     * @param finalDate   defines the end of the interval.
     * @return is the energy consumption.
     */

    public double getConsumptionInInterval(Date initialDate, Date finalDate) {
        return this.deviceList.getConsumptionInInterval(initialDate, finalDate);
    }

    /**
     * Checks how many devices are associated to room list.\
     *
     * @return number of devices associated to room list as int
     **/
    public int getNumberOfDevices() {
        return deviceList.size();
    }
    /**
     * Method that removes a Device from the Room.
     *
     * @param device is the device we want to removeGeographicArea.
     * @return true if device was successfully removed from the room, false otherwise.
     */
    public boolean removeDevice(Device device) {
        return deviceList.removeDevice(device);
    }


    /**
     * Adds a device to a room
     *
     * @param device is the device to be added
     * @return the result of the operation (true if successful, false otherwise)
     */
    public boolean addDevice(Device device) {
        return deviceList.add(device);
    }

    /**
     * String Builder of the Room.
     *
     * @return a String that represents the Room Specifications.
     */
    public String buildString() {
        String result;
        result = this.roomName + ", " + this.getFloor() + ", " +
                this.getWidth() + ", " + this.getLength() + ", " + this.getHeight() + ".\n";
        return result;
    }



    /**
     * Returns a list of devices of a given type, in a room
     *
     * @param deviceType the device type
     * @return the list with all devices of a given type
     */
    public DeviceList getDevicesOfGivenType(String deviceType) {
        return this.deviceList.getDevicesOfGivenType(deviceType);
    }

    /**
     * Returns the approximate consumption of all devices of a given type in this room, if they were to work
     * for the determined period of time.
     *
     * @param deviceType is the device type we want to filter for.
     * @param time       represents the period of time the device is working for, in number of minutes. 1440 is a day.
     * @return is the sum of approximate consumptions for the given time period of all devices of a given type.
     */

    double getEstimateConsumptionOverTimeByDeviceType(String deviceType, int time) {
        return deviceList.getDailyConsumptionByDeviceType(deviceType, time);
    }

    /**
     * Adds all of this room's devices to a given list. Skips duplicates.
     *
     * @param list is the list we want to addWithoutPersisting the room's devices to.
     */

    public void addRoomDevicesToDeviceList(DeviceList list) {
        this.deviceList.addDevicesToDeviceList(list);
    }

    /**
     * Removes all of the room's devices from a given list.
     *
     * @param list is the list we want to removeGeographicArea devices from.
     * @return false if the list is invalid (null), true otherwise.
     */

    public boolean removeRoomDevicesFromDeviceList(DeviceList list) {
        return this.deviceList.removeDevicesFromGivenList(list);
    }

    /**
     * Method that returns a LogList of a given interval of time.
     *
     * @param startDate is the Starting Date of the log.
     * @param endDate   is the End Date of the Log.
     * @return a LogList
     */
    public LogList getLogsInInterval(Date startDate, Date endDate) {
        return this.deviceList.getLogsInInterval(startDate, endDate);
    }

    /**
     * This method checks room's DeviceList size.
     *
     * @return DeviceList size as int.
     **/
    public int getDeviceListSize() {
        return this.deviceList.size();
    }

    /**
     * This method receives an index as parameter and gets a device from room's room list.
     *
     * @param index the index of the device
     * @return returns device that corresponds to index.
     */
    public Device getDeviceByIndex(int index) {
        if (this.deviceList.isEmpty()) {
            throw new IndexOutOfBoundsException("The device list is empty.");
        }
        return this.deviceList.get(index);
    }


    /**
     * This method checks if room's Device List is empty.
     *
     * @return true if room's DeviceList is empty, false otherwise.
     **/
    public boolean isDeviceListEmpty() {
        return this.deviceList.isEmpty();
    }

    /**
     * Creates a string that displays all devices of a given type in a given room.
     *
     * @param type type of device to display
     * @return a string that displays all devices of given type.
     */
   public String buildDevicesStringByType(String type) {
        StringBuilder result = new StringBuilder();
        for (int x = 0; x < deviceList.size(); x++) {
            if (type.equals(deviceList.getTypeByIndex(x))) {
                Device device = deviceList.get(x);
                result.append("Device type: ").append(type).append(" | ");
                result.append("Device name: ").append(device.getName()).append(" | ");
                result.append("Nominal power: ").append(device.getNominalPower()).append(" | ");
                result.append("Room: ").append(this.roomName).append(" | \n");
            }
        }
        return result.toString();
    }

    public double getEnergyConsumption(float time) {
        return this.deviceList.getEnergyConsumption(time);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Room room = (Room) o;
        return Objects.equals(roomName, room.roomName);
    }


    @Override
    public int hashCode() {
        return 1;
    }
}



