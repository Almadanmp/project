package pt.ipp.isep.dei.project.model;

import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.DeviceList;
import pt.ipp.isep.dei.project.model.device.log.LogList;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static java.lang.Double.NaN;

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
        setRoomName(name);
        setRoomHouseFloor(houseFloor);
        setRoomWidth(width);
        setRoomLength(length);
        setRoomHeight(height);
        this.roomSensorList = new SensorList();
        this.deviceList = new DeviceList();
    }

    /**
     * Method that returns the SensorList of the room.
     *
     * @return
     */
    public SensorList getSensorList() {
        return roomSensorList;
    }

    /**
     * Room Name Setter.
     *
     * @param name
     */
    private void setRoomName(String name) {
        roomName = name;
    }

    /**
     * Room House Floor Setter.
     *
     * @param houseFloor
     */
    private void setRoomHouseFloor(int houseFloor) {
        this.houseFloor = houseFloor;
    }

    /**
     * Room Height Setter.
     *
     * @param height
     */
    private void setRoomHeight(double height) {
        roomHeight = height;
    }

    /**
     * Room Length Setter.
     *
     * @param length
     */
    private void setRoomLength(double length) {
        roomLength = length;
    }

    /**
     * Room Width Setter.
     *
     * @param width
     */
    private void setRoomWidth(double width) {
        roomWidth = width;
    }

    /**
     * Room Height Getter.
     *
     * @return
     */
    double getRoomHeight() {
        return roomHeight;
    }

    /**
     * Room Length Getter.
     *
     * @return
     */
    double getRoomLength() {
        return roomLength;
    }

    /**
     * Room Width Getter.
     *
     * @return
     */
    double getRoomWidth() {
        return roomWidth;
    }

    /**
     * Room Sensor List Setter.
     *
     * @param sensorList to set to the Room
     */
    public void setSensorList(SensorList sensorList) {
        roomSensorList = sensorList;
    }

    /**
     * Room Name Getter.
     *
     * @return
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     * House Floor Getter.
     *
     * @return
     */
    int getHouseFloor() {
        return houseFloor;
    }

    /**
     * Room DeviceList Setter.
     *
     * @param deviceList
     */
    public void setDeviceList(DeviceList deviceList) {
        this.deviceList = deviceList;
    }

    /**
     * Room DeviceList Getter.
     *
     * @return
     */
    public List<Device> getDeviceList() {
        return this.deviceList.getList();
    }

    /**
     * Method for printing all available devices in a room.
     * Used in US201 and US215
     *
     * @return string with devices in room.
     */

    public String buildDeviceListString() {
        StringBuilder result = new StringBuilder("---------------\n");
        for (int i = 0; i < this.getDeviceList().size(); i++) {
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
        double result = 0;
        for (Device d : this.getDeviceList()) {
            result += d.getNominalPower();
        }
        return result;
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
        double result = 0;
        for (Device d : this.getDeviceList()) {
            result += d.getConsumptionWithinGivenInterval(initialDate, finalDate);
        }
        return result;
    }

    /**
     * Method receives a date of a given day and looks for the max temperature
     * recorded in every sensor that measure temperature, in the room.
     *
     * @return the max temperature recorded in a sensor that measures temperature or
     * NaN in case there are no readings in the given day or
     * in case the room has no readings whatsoever
     * @date day where we want to look for max temperature
     **/
    public double getMaxTemperatureOnGivenDay(Date day) {
        double maxTemp = -1000;
        SensorList tempSensors = getSensorsOfGivenType("Temperature");
        if (tempSensors.getSensorList().isEmpty() || !tempSensors.hasReadings()) {
            throw new IllegalArgumentException("There aren't any temperature readings available.");
        }
        for (Sensor s : tempSensors.getSensorList()) {
            ReadingList readingList = s.getReadingList();
            double sensorMax = readingList.getMaximumOfGivenDayValueReadings(day);
            maxTemp = Math.max(sensorMax, maxTemp);
        }
        return maxTemp;
    }

    /**
     * Method that looks in sensor list for sensors of given type and returns a list of
     * those sensors. Method will look at the sensor's type.
     *
     * @return a sensor list that contains sensors of given type
     **/
    SensorList getSensorsOfGivenType(String type) {
        SensorList tempSensors = new SensorList();
        for (Sensor s : this.roomSensorList.getSensorList()) {
            String typeTest = s.getTypeSensor().getName();
            if (typeTest.equalsIgnoreCase(type)) {
                tempSensors.addSensor(s);
            }
        }
        return tempSensors;

    }

    /**
     * Method that removes a Device from the Room.
     *
     * @param device we want to remove.
     * @return
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
     * @param sensor we want to add.
     * @return
     */
    public boolean addSensor(Sensor sensor) {
        if (!(roomSensorList.getSensorList().contains(sensor))) {
            roomSensorList.getSensorList().add(sensor);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Adds a device to a room
     *
     * @param device to be added
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
        if (tempSensors.getSensorList().isEmpty() || !tempSensors.hasReadings()) {
            throw new IllegalArgumentException("There aren't any temperature readings available.");
        }
        ReadingList readingList = tempSensors.getReadings();
        currentT = readingList.getMostRecentValue();

        return currentT;
    }

    /**
     * String Builder of the Room.
     *
     * @return
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
        List<Device> devicesOfGivenType = new ArrayList<>();
        for (Device d : getDeviceList()) {
            if (d.getType().equals(deviceType)) {
                devicesOfGivenType.add(d);
            }
        }
        return devicesOfGivenType;
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
        for (Device d : this.getDeviceList()) {
            if (!(list.containsDevice(d))) {
                list.addDevice(d);
            }
        }
    }

    /**
     * Removes all of the room's devices from a given list.
     *
     * @param list is the list we want to remove devices from.
     * @return false if the list is invalid (null), true otherwise.
     */

    public boolean removeRoomDevicesFromDeviceList(DeviceList list) {
        if (list == null) {
            return false;
        }
        for (Device d : this.getDeviceList()) {
            list.removeDevice(d);
        }
        return true;
    }

    /**
     * Method that returns a LogList of a given interval of time.
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public LogList getLogsInInterval(Date startDate, Date endDate) {
        LogList result = new LogList();
        for (Device d : this.getDeviceList()) {
            LogList tempList = d.getLogsInInterval(startDate, endDate);
            result.addLogList(tempList);
        }
        return result;
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



