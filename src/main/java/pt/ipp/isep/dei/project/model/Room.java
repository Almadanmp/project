package pt.ipp.isep.dei.project.model;

import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.DeviceList;
import pt.ipp.isep.dei.project.model.device.log.LogList;

import java.util.*;

/**
 * Class that represents a Room of a House.
 */

public class Room implements Metered {

    private String roomName;
    private int houseFloor;
    private double roomWidth;
    private double roomLength;
    private double roomHeight;
    private SensorList roomSensorList;
    private DeviceList deviceList;

    /**
     * Room() Constructor receiving 5 parameters and initializing 2 Lists, SensorList and DeviceList.
     *
     * @param name       of the room
     * @param houseFloor of the room
     * @param width      of the room
     * @param length     of the room
     * @param height     of the room
     */
    public Room(String name, int houseFloor, double width, double length, double height) {
        this.roomName = name;
        this.houseFloor = houseFloor;
        this.roomWidth = width;
        this.roomLength = length;
        this.roomHeight = height;
        this.roomSensorList = new SensorList();
        this.deviceList = new DeviceList();
    }

    /**
     * Method that returns the SensorList of the room.
     *
     * @return SensorList of the Room.
     */
    public SensorList getSensorList() {
        return roomSensorList;
    }

    /**
     * Room Height Getter.
     *
     * @return a double that represents the room height.
     */
    double getRoomHeight() {
        return roomHeight;
    }

    /**
     * Room Length Getter.
     *
     * @return a double that represents the room length.
     */
    double getRoomLength() {
        return roomLength;
    }

    /**
     * Room Width Getter.
     *
     * @return a double that represents the room width.
     */
    double getRoomWidth() {
        return roomWidth;
    }

    /**
     * Room Sensor List Setter.
     *
     * @param sensorList is the sensorList to set to the Room
     */
    public void setSensorList(SensorList sensorList) {
        roomSensorList = sensorList;
    }

    /**
     * Room Name Getter.
     *
     * @return a string that represents the room name.
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     * House Floor Getter.
     *
     * @return a int that represents the room house floor.
     */
    int getHouseFloor() {
        return houseFloor;
    }

    /**
     * Method that gets the room's device list.
     *
     * @return room's DeviceList
     */
    public DeviceList getDeviceList() {
        return this.deviceList;
    }

    /**
     * Method for printing all available devices in a room.
     * Used in US201 and US215
     *
     * @return string with devices in room.
     */

    public String buildDeviceListString() {
        StringBuilder result = new StringBuilder("---------------\n");
        for (int i = 0; i < this.getDeviceListSize(); i++) {
            Device device = this.getDeviceList().get(i);
            result.append("\n").append(i).append(") device Name: ").append(device.getName());
            result.append(", device Type: ").append(device.getType());
            result.append(", device Nominal Power: ").append(device.getNominalPower());
        }
        result.append("\n---------------\n");
        return result.toString();
    }

    /**
     * This method will go through the room's device list and add all the devices'
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
     * Method receives a date of a given day and looks for the max temperature
     * recorded in every sensor that measure temperature, in the room.
     *
     * @return the max temperature recorded in a sensor that measures temperature or
     * NaN in case there are no readings in the given day or
     * in case the room has no readings whatsoever
     * @param day where we want to look for max temperature
     **/
    public double getMaxTemperatureOnGivenDay(Date day) {
        SensorList tempSensors = getSensorsOfGivenType("Temperature");
        if (tempSensors.isEmpty()) {
            throw new IllegalArgumentException("There aren't any temperature readings available.");
        } else {
            if (!tempSensors.getReadings().getValuesOfSpecificDayReadings(day).isEmpty()) {
                return Collections.max(tempSensors.getReadings().getValuesOfSpecificDayReadings(day));
            }
            throw new NoSuchElementException("There aren't any temperature readings available.");
        }
    }

    /**
     * Method that looks in sensor list for sensors of given type and returns a list of
     * those sensors. Method will look at the sensor's type.
     *
     * @return a sensor list that contains sensors of given type
     **/
    SensorList getSensorsOfGivenType(String type) {
        return this.roomSensorList.getSensorListByType(type);
    }

    /**
     * Method that removes a Device from the Room.
     *
     * @param device is the device we want to remove.
     * @return true if device was successfully removed from the room, false otherwise.
     */
    public boolean removeDevice(Device device) {
        if ((deviceList.getList().contains(device))) {
            deviceList.getList().remove(device);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method to add a Sensor to the Room.
     *
     * @param sensor is the sensor we want to add.
     * @return true if sensor was successfully added to the room, false otherwise.
     */
    public boolean addSensor(Sensor sensor) {
        if (!(roomSensorList.contains(sensor))) {
            roomSensorList.add(sensor);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Adds a device to a room
     *
     * @param device is the device to be added
     * @return the result of the operation (true if successful, false otherwise)
     */
    public boolean addDevice(Device device) {
        if (!(deviceList.containsDevice(device))) {
            deviceList.addDevice(device);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method that goes through every Sensor in the room of the type "temperature" returning
     * the value of the most recent reading.
     *
     * @return the most recent temperature reading or NaN in case the room has no temperature
     * sensors and/or when temperature sensors have no readings
     */

    public double getCurrentRoomTemperature() {
        double currentT;
        SensorList tempSensors = getSensorsOfGivenType("Temperature");
        if (tempSensors.isEmpty()) {
            throw new IllegalArgumentException("There aren't any temperature readings available.");
        }
        ReadingList readingList = tempSensors.getReadings();
        currentT = readingList.getMostRecentValue();

        return currentT;
    }

    /**
     * String Builder of the Room.
     *
     * @return a String that represents the Room Specifications.
     */
    public String buildRoomString() {
        String result;
        result = this.roomName + ", " + this.getHouseFloor() + ", " +
                this.getRoomWidth() + ", " + this.getRoomLength() + ", " + this.getRoomHeight() + ".\n";
        return result;
    }


    /**
     * Returns a list of devices of a given type, in a room
     *
     * @param deviceType the device type
     * @return the list with all devices of a given type
     */
    List<Device> getDevicesOfGivenType(String deviceType) {
        return this.deviceList.getDevicesOfGivenType(deviceType);
    }

    /**
     * Returns the daily estimate consumption of all devices of a given type in this room.
     *
     * @param deviceType the device type
     * @param time       represents a day in minutes
     * @return the sum of all daily estimate consumptions of that type
     */

    double getDailyConsumptionByDeviceType(String deviceType, int time) {
        return deviceList.getDailyConsumptionByDeviceType(deviceType, time);
    }

    /**
     * Adds all of this room's devices to a given list. Skips duplicates.
     *
     * @param list is the list we want to add the room's devices to.
     */

    public void addRoomDevicesToDeviceList(DeviceList list) {
        this.deviceList.addDevicesToDeviceList(list);
    }

    /**
     * Removes all of the room's devices from a given list.
     *
     * @param list is the list we want to remove devices from.
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
     * This method checks if room's SensorList is empty.
     *
     * @return true if room's SensorList is empty, false otherwise.
     **/
    public boolean isSensorListEmpty() {
        return this.roomSensorList.isEmpty();
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
     * @param index the index of the device
     * @return returns device that corresponds to index.
     */
    public Device getDeviceByIndex(int index) {
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
    public double getEnergyConsumption(float time) {
        return 0;
    }


    @Override
    public int hashCode() {
        return 1;
    }
}



