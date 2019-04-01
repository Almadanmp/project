package pt.ipp.isep.dei.project.model;

import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.DeviceList;
import pt.ipp.isep.dei.project.model.device.devicetypes.DeviceType;
import pt.ipp.isep.dei.project.model.device.log.LogList;

import java.util.Date;
import java.util.Objects;

/**
 * Class that represents an Energy Grid present in a House.
 */

public class EnergyGrid implements Metered {
    private String name;
    private RoomList roomList;
    private PowerSourceList listPowerSources;
    private double maxContractedPower;

    /**
     * Standard Energy Grid constructor, used for creating energy grids.
     *
     * @param name               is the name of the grid.
     * @param maxContractedPower is the value of the maximum power contracted.
     */
    public EnergyGrid(String name, double maxContractedPower) {
        this.roomList = new RoomList();
        this.listPowerSources = new PowerSourceList();
        this.name = name;
        this.maxContractedPower = maxContractedPower;
    }

    /**
     * Getter for name of the energy grid.
     *
     * @return returns attribute name of the energy grid.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for list of rooms that are contained in the energy grid.
     *
     * @return returns a list with all the rooms contained in this energy grid.
     */
    public RoomList getRoomList() {
        return roomList;
    }

    /**
     * Standard getter method, to return the Maximum contracted power.
     *
     * @return the number of the max power contracted.
     */
    public double getMaxContractedPower() {
        return maxContractedPower;
    }

    /**
     * Method accesses the sum of nominal powers of all rooms and devices connected to a grid..
     *
     * @return is the sum of nominal powers of all rooms and devices connected to a grid.
     */
    public double getNominalPower() {
        return roomList.getNominalPower();
    }

    /**
     * Getter for the list of power sources connected to the energy grid.
     *
     * @return the list of power sources connected to the energy grid.
     */
    public PowerSourceList getPowerSourceList() {
        return listPowerSources;
    }

    /**
     * Standard setter method, to define the list of power sources.
     *
     * @param listPowerSources is the PowerSourceList we want to add to the Energy Grid.
     */
    public void setPowerSourceList(PowerSourceList listPowerSources) {
        this.listPowerSources = listPowerSources;
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

    /**
     * Setter for the list of rooms connected the energy grid.
     *
     * @param roomList List of rooms in the energy grid.
     */
    public void setRoomList(RoomList roomList) {
        if (roomList != null) {
            this.roomList = roomList;
        }
    }

    /**
     * Setter for a power source to the energy grid.
     *
     * @param powerSource power source to be added to the energy grid.
     * @return returns true case the power source to be added is already associated to the grid.
     */
    public boolean addPowerSource(PowerSource powerSource) {
        return listPowerSources.add(powerSource);
    }

    /**
     * Setter for a new room to be added to the energy grid.
     *
     * @param room Room to be added to the list of rooms in the energy grid.
     * @return returns true if the room is actually added to the energy grid.
     */
    public boolean addRoom(Room room) {
        return this.roomList.add(room);
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
        if (this.roomList.isEmpty()) {
            throw new IndexOutOfBoundsException("The room list is empty.");
        }
        return this.roomList.get(index);
    }

    /**
     * Method gets all devices associated to energy grid
     *
     * @return energy grid's entire DeviceList
     */
    public DeviceList getDeviceList() {
        return this.roomList.getDeviceList();
    }

    /**
     * Method goes through all rooms associated to energy grid and returns
     * the number of devices in each room
     *
     * @return energy grid's associated devices as int
     */
    public int getNumberOfDevices() {
        return this.roomList.getNumberOfDevices();
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
        if (this.roomList.contains(room)) {
            this.roomList.removeRoom(room);
            return true;
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
     * Creates a string displaying the rooms in the energy grid and the room's attributes.
     *
     * @return returns a string displaying the rooms in the energy grid and the room's attributes.
     */
    public String buildRoomListString() {
        return this.roomList.buildString();
    }


    /**
     * Creates a String with the device index, device type, device name and the room in which the device is contained.
     *
     * @param house the house that contains the device types.
     * @return a String with the device index, device type, device name and the room in which the device is contained.
     */
    public String buildDeviceListWithTypeString(House house) {
        String stringSpacer = "---------------\n";
        StringBuilder result = new StringBuilder(stringSpacer);
        for (DeviceType d : house.getDeviceTypeList()) {
            String deviceType = d.getDeviceType();
            result.append(roomList.buildDeviceListByType(deviceType));
        }
        result.append(stringSpacer);
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
        return this.roomList.getConsumptionInInterval(initialDate, finalDate);
    }

    /**
     * This method goes through every room in list and returns logs contained in interval given.
     *
     * @param startDate the initial date of the interval
     * @param endDate   the final date of the interval
     * @return log list with every log contained in interval given.
     */
    public LogList getLogsInInterval(Date startDate, Date endDate) {
        return this.roomList.getLogsInInterval(startDate, endDate);
    }

    /**
     * This method checks if energy grid's Device List is empty.
     *
     * @return true if energy grid's DeviceList is empty, false otherwise.
     **/
    public boolean isDeviceListEmpty() {
        return roomList.isDeviceListEmpty();
    }

    /**
     * This method checks if energy grid's Room List is empty.
     *
     * @return true if energy grid's RoomList is empty, false otherwise.
     **/
    public boolean isRoomListEmpty() {
        return roomList.isEmpty();
    }

    /**
     * This method checks the Room List's size.
     *
     * @return energy grid's RoomList size as int
     **/
    public int roomListSize() {
        return roomList.size();
    }

    /**
     * This method creates a power source
     *
     * @param name             the name of the power source to be created
     * @param maxEnergyStorage the maximum storable energy for the power source
     * @param maxPowerOutput   the maximum power for the power source
     * @return creates a new power source.
     **/
    public PowerSource createPowerSource(String name, double maxPowerOutput, double maxEnergyStorage) {
        return this.listPowerSources.createPowerSource(name, maxPowerOutput, maxEnergyStorage);
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
