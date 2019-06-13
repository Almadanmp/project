package pt.ipp.isep.dei.project.model.room;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import pt.ipp.isep.dei.project.dddplaceholders.Root;
import pt.ipp.isep.dei.project.model.Metered;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.ReadingUtils;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.DeviceList;
import pt.ipp.isep.dei.project.model.device.log.LogList;

import javax.persistence.*;
import java.util.*;

/**
 * Class that represents a Room of a House.
 */
@Entity
public class Room implements Metered, Root {

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

    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "roomId")
    private List<RoomSensor> roomSensors;

    private static final String STRING_BUILDER = "---------------\n";
    private static final String TEMPERATURE = "temperature";
    private static final String NO_TEMP_READINGS = "There aren't any temperature readings available.";

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
                String houseID) {
        this.roomName = name;
        this.description = description;
        this.houseFloor = houseFloor;
        this.roomWidth = width;
        this.roomLength = length;
        this.roomHeight = height;
        this.deviceList = new DeviceList();
        this.houseID = houseID;
        this.roomSensors = new ArrayList<>();
    }

    /**
     * Room() Constructor receiving 5 parameters and initializing 2 Lists, AreaSensorList and DeviceList.
     *
     * @param name           of the room
     * @param houseFloor     of the room
     * @param roomDimensions contains width, length and height, respectively.
     */
    public Room(String name, String description, int houseFloor, List<Double> roomDimensions,
                String houseID) {
        this.roomName = name;
        this.description = description;
        this.houseFloor = houseFloor;
        this.roomWidth = roomDimensions.get(0);
        this.roomLength = roomDimensions.get(1);
        this.roomHeight = roomDimensions.get(2);
        this.deviceList = new DeviceList();
        this.houseID = houseID;
        this.roomSensors = new ArrayList<>();
    }

    protected Room() {
        this.deviceList = new DeviceList();
        this.roomSensors = new ArrayList<>();
    }

    /**
     * This method sets the description.
     *
     * @param description as a string
     **/
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This method sets the house floor.
     *
     * @param floor as a int
     **/
    public void setHouseFloor(int floor) {
        this.houseFloor = floor;
    }

    /**
     * This method sets the room width.
     *
     * @param width as a double
     **/
    public void setRoomWidth(double width) {
        this.roomWidth = width;
    }

    /**
     * This method sets the room Length.
     *
     * @param roomLength as a double
     **/
    public void setRoomLength(double roomLength) {
        this.roomLength = roomLength;
    }

    /**
     * This method sets the room height.
     *
     * @param roomHeight as a double
     **/
    public void setRoomHeight(double roomHeight) {
        this.roomHeight = roomHeight;
    }

    /**
     * This method sets the house ID.
     *
     * @param houseID house ID as a string
     **/
    public void setHouseID(String houseID) {
        this.houseID = houseID;
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


    //SENSOR METHODS

    @JsonIgnore
    public List<RoomSensor> getRoomSensors() {
        return new ArrayList<>(this.roomSensors);
    }

    public void setRoomSensors(List<RoomSensor> listSensors) {
        this.roomSensors = new ArrayList<>(listSensors);
    }


    RoomSensor getSensor(int index) {
        return this.roomSensors.get(index);
    }

    public boolean addSensor(RoomSensor areaSensor) {
        if (!this.roomSensors.contains(areaSensor)) {
            this.roomSensors.add(areaSensor);
            return true;
        }
        return false;
    }

    /**
     * Method to print a Whole Sensor List.
     * It will print the attributes needed to check if a Sensor is different from another Sensor
     * (name, type of Sensor and Units)
     *
     * @return a string of the sensors contained in the list.
     */

    public String buildRoomSensorsAsString() {
        StringBuilder result = new StringBuilder(new StringBuilder(STRING_BUILDER));

        if (roomSensors.isEmpty()) {
            return "Invalid List - List is Empty\n";
        }
        for (RoomSensor hS : roomSensors) {
            result.append("ID: ").append(hS.getId()).append(" | ").append(hS.getName()).append(" | ");
            result.append("Type: ").append(hS.getSensorType()).append(" | ")
                    .append(hS.printActive()).append("\n");
        }
        result.append(STRING_BUILDER);
        return result.toString();
    }


    /**
     * Method that goes through every sensor in the sensor list and gets
     * every reading within that sensor. In the end we will get a Reading list
     * that contains every reading from every sensor of the sensor list.
     *
     * @return a list with all readings from sensor list
     **/
    private List<Reading> getReadings(List<RoomSensor> roomSensors) {
        List<Reading> finalList = new ArrayList<>();
        for (RoomSensor s : roomSensors) {
            finalList.addAll(s.getReadings());
        }
        return finalList;
    }

    /**
     * This method goes through every sensor reading list and returns the
     * reading values of a given day. This day is given to method as parameter.
     *
     * @param day date of day the method will use to get reading values
     * @return returns value readings from every sensor from given day
     **/
    private List<Double> getValuesOfSpecificDayReadings(List<RoomSensor> roomSensors, Date day) {
        List<Reading> sensorReadings = new ArrayList<>();
        for (RoomSensor hS : roomSensors) {
            sensorReadings.addAll(hS.getReadings());
        }
        return ReadingUtils.getValuesOfSpecificDayReadings(sensorReadings, day);
    }


    /**
     * Method receives a date of a given day and looks for the max temperature
     * recorded in every sensor that measure temperature, in the room.
     *
     * @param day where we want to look for max temperature
     * @return the max temperature recorded in a sensor that measures temperature or
     * NaN in case there are no readings in the given day or
     * in case the room has no readings whatsoever
     **/
    public double getMaxTemperatureOnGivenDay(Date day) {
        List<RoomSensor> tempSensors = getRoomSensorsOfGivenType(TEMPERATURE);
        if (tempSensors.isEmpty()) {
            throw new IllegalArgumentException(NO_TEMP_READINGS);
        } else {
            List<Double> values = getValuesOfSpecificDayReadings(tempSensors, day);
            if (!values.isEmpty()) {
                return Collections.max(values);
            }
            throw new NoSuchElementException(NO_TEMP_READINGS);
        }
    }


    /**
     * Method that goes through every Sensor in the room of the type "temperature" returning
     * the value of the most recent reading.
     *
     * @return the most recent temperature reading or NaN in case the room has no temperature
     * sensors and/or when temperature sensors have no readings
     */

    @JsonIgnore
    public double getCurrentRoomTemperature() {
        List<RoomSensor> tempSensors = getRoomSensorsOfGivenType(TEMPERATURE);
        if (tempSensors.isEmpty()) {
            throw new IllegalArgumentException(NO_TEMP_READINGS);
        }
        List<Reading> sensorReadings = getReadings(tempSensors);
        return ReadingUtils.getMostRecentValue(sensorReadings);
    }

    /**
     * @param name String of the sensor we wish to compare with the existent sensors on the sensor list.
     * @return builds a list of sensors with the same type as the one introduced as parameter.
     */

    List<RoomSensor> getRoomSensorsOfGivenType(String name) {
        List<RoomSensor> containedTypeSensors = new ArrayList<>();
        for (RoomSensor sensor : roomSensors) {
            if (name.equals(sensor.getSensorType())) {
                containedTypeSensors.add(sensor);
            }
        }
        return containedTypeSensors;
    }


    /**
     * This method will receive a string of a sensor ID and
     * will look for a sensor with the given ID from the sensors list.
     *
     * @param sensorID string of sensor ID
     * @return the sensor of the given sensor ID
     **/
    RoomSensor getRoomSensorByID(String sensorID) {
        for (RoomSensor sensor : this.roomSensors) {
            String tempSensorName = sensor.getId();
            if (sensorID.equals(tempSensorName)) {
                return sensor;
            }
        }
        throw new IllegalArgumentException();
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

    /**
     * Method to check if an instance of this class is equal to another object.
     * Necessary for adding rooms to list.
     *
     * @return is true if the object is a power source list with the same contents.
     */
    public RoomSensor createRoomSensor(String id, String name, String sensorType, Date dateStartedFunctioning) {
        return new RoomSensor(id, name, sensorType, dateStartedFunctioning);
    }


    @Override
    public int hashCode() {
        return 1;
    }
}



