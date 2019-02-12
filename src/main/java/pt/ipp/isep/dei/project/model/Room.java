package pt.ipp.isep.dei.project.model;

import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.DeviceList;
import pt.ipp.isep.dei.project.model.device.LogList;

import java.util.*;

import static java.lang.Double.NaN;

/**
 * Class that represents a Room of a House.
 */

public class Room implements Metered {

    private String mRoomName;
    private int mHouseFloor;
    private double mRoomWidth;
    private double mRoomLength;
    private double mRoomHeight;
    private SensorList mRoomSensorList;
    private DeviceList mDeviceList;


    public Room(String name, int houseFloor, double width, double length, double height) {
        setRoomName(name);
        setRoomHouseFloor(houseFloor);
        setRoomWidth(width);
        setRoomLength(length);
        setRoomHeight(height);
        this.mRoomSensorList = new SensorList();
        this.mDeviceList = new DeviceList();
    }

    public SensorList getSensorList() {
        return mRoomSensorList;
    }

    private void setRoomName(String name) {
        mRoomName = name;
    }

    private void setRoomHouseFloor(int houseFloor) {
        mHouseFloor = houseFloor;
    }


    private void setRoomHeight(double height) {
        mRoomHeight = height;
    }

    private void setRoomLength(double length) {
        mRoomLength = length;
    }

    private void setRoomWidth(double width) {
        mRoomWidth = width;
    }

    double getRoomHeight() {
        return mRoomHeight;
    }

    double getRoomLength() {
        return mRoomLength;
    }

    double getRoomWidth() {
        return mRoomWidth;
    }

    public void setRoomSensorList(SensorList sensorList) {
        mRoomSensorList = sensorList;
    }

    public String getRoomName() {
        return mRoomName;
    }

    public int getHouseFloor() {
        return mHouseFloor;
    }

    public void setDeviceList(DeviceList deviceList) {
        this.mDeviceList = deviceList;
    }

    public List<Device> getDeviceList() {
        return this.mDeviceList.getList();
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
            result.append("\n" + i).append(") device Name: ").append(device.getName());
            result.append(", device Type: ").append(device.getType());
            result.append(", device Nominal Power: ").append(device.getNominalPower());
        }
        result.append("\n---------------\n");
        return result.toString();
    }

    public DeviceList getObjectDeviceList() {
        return this.mDeviceList;
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
     * @param initialDate defines the start of the interval.
     * @param finalDate defines the end of the interval.
     * @return is the energy consumption.
     */

    public double getConsumptionInInterval(Date initialDate, Date finalDate){
        double result = 0;
        for (Device d : this.getDeviceList()){
            result += d.getConsumptionWithinGivenInterval(initialDate,finalDate);
        }
        return result;
    }

    /**Method receives a date of a given day and looks for the max temperature
     * recorded in every sensor that measure temperature, in the room.
     * @date day where we want to look for max temperature
     * @return the max temperature recorded in a sensor that measures temperature or
     * NaN in case there are no readings in the given day or
     * in case the room has no readings whatsoever
     * **/
    public double getMaxTemperatureOnGivenDay(Date day){
        double maxTemp = -1000;
        SensorList tempSensors = getSensorsOfGivenType("temperature");
        if(tempSensors.getSensorList().isEmpty() || !tempSensors.hasReadings()){
            return NaN;
        }
        for(Sensor s: tempSensors.getSensorList()) {
            ReadingList readingList = s.getReadingList();
            double sensorMax = readingList.getMaximumOfGivenDayValueReadings(day);
            maxTemp = Math.max(sensorMax,maxTemp);
        }
        return maxTemp;
    }

    /**Method that looks in sensor list for sensors of given type and returns a list of
     * those sensors. Method will look at the sensor's type.
     * @return a sensor list that contains sensors of given type
     * **/
    public SensorList getSensorsOfGivenType(String type){
        SensorList tempSensors = new SensorList();
        for(Sensor s : this.mRoomSensorList.getSensorList()){
            String typeTest = s.getTypeSensor().getName();
            if(typeTest.equals(type)){
                tempSensors.addSensor(s);
            }
        }
        return tempSensors;

    }

    boolean doesSensorListInARoomContainASensorByName(String name) {
        for (Sensor s : mRoomSensorList.getSensorList()) {
            if (s.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean removeDevice(Device device) {
        if ((mDeviceList.getList().contains(device))) {
            mDeviceList.getList().remove(device);
            return true;
        } else {
            return false;
        }
    }

    public boolean addSensor(Sensor sensor) {
        if (!(mRoomSensorList.getSensorList().contains(sensor))) {
            mRoomSensorList.getSensorList().add(sensor);
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
        if (!(mDeviceList.containsDevice(device))) {
            mDeviceList.addDevice(device);
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
        double currentT = NaN;
        SensorList tempSensors = getSensorsOfGivenType("temperature");
        if(!tempSensors.getSensorList().isEmpty()){
            ReadingList readingList = tempSensors.getReadings();
            currentT = readingList.getMostRecentValueOfReading();
        }
        return currentT;
    }


    public String buildRoomString() {
        String result;
        result = this.mRoomName + ", " + this.getHouseFloor() + ", " +
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
     * @return the sum of all daily estimate consumptions of that type
     */
    double getDailyConsumptionByDeviceType(String deviceType) {
        return mDeviceList.getDailyConsumptionByDeviceType(deviceType);
    }

    /**
     * Adds all of this room's devices to a given list. Skips duplicates.
     *
     * @param list is the list we want to add the room's devices to.
     * @return is true if at least one device was added, false if no devices were added.
     */

    public boolean addRoomDevicesToDeviceList(DeviceList list) {
        int counter = 0;
        for (Device d : this.getDeviceList()) {
            if (!(list.containsDevice(d))) {
                list.addDevice(d);
                counter++;
            }
        }
        return counter != 0;
    }

    /**
     * Removes all of the room's devices from a given list.
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

    public LogList getLogsInInterval(Date startDate, Date endDate){
        LogList result = new LogList();
        for (Device d: this.getDeviceList()){
            LogList tempList = d.getLogsInInterval(startDate,endDate);
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
        return Objects.equals(mRoomName, room.mRoomName);
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



