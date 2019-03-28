package pt.ipp.isep.dei.project.model.device;

import pt.ipp.isep.dei.project.model.device.log.LogList;

import java.util.*;

/**
 * Class that groups a number of Devices.
 */

public class DeviceList {

    private List<Device> devices;

    /**
     * DeviceList() Empty Constructor that initializes an ArrayList of Devices.
     */
    public DeviceList() {
        this.devices = new ArrayList<>();
    }

    /**
     * Method to get the device list
     * @return device list
     */
    public List<Device> getList() {
        return this.devices;
    }

    /**
     * Method to check if a DeviceList contains a certain Device.
     *
     * @param device is the device that we want to check if it's contained in the list.
     * @return true if devices is contained in the list, false otherwise.
     */
    public boolean containsDevice(Device device) {
        return this.devices.contains(device);
    }

    /**
     * Method to addWithoutPersisting a Device to the DeviceList.
     *
     * @param device is the device that we want to addWithoutPersisting.
     * @return true if the device was successfully added, false otherwise(device already exists in the list)
     */
    public boolean add(Device device) {
        if (!devices.contains(device)) {
            devices.add(device);
            return true;
        }
        return false;
    }

    /**
     * Energy Consumption for a certain time of a the device list
     * @param time time for each we want to know the energy consumption of all devices on device list
     * @return energy consumption of all devices on device list
     */
    public double getEnergyConsumption(float time) {
        double result = 0;
        for (Device d : this.devices) {
            result += d.getEnergyConsumption(time);
        }
        return result;
    }

    /**
     * Method to removeGeographicArea a Device from the DeviceList.
     *
     * @param device is the device that we want to removeGeographicArea.
     * @return true if it was successfully removed, false otherwise(it doesn't exists on the list already).
     */
    public boolean removeDevice(Device device) {
        if (this.containsDevice(device)) {
            devices.remove(device);
            return true;
        }
        return false;
    }

    /**
     * Validation for the DeviceList.
     *
     * @return true if List is not empty, false otherwise.
     */
    public boolean isEmpty() {
        return devices.isEmpty();
    }

    /**
     * Method that returns the NominalPower of the DeviceList.
     *
     * @return a double that represents the NominalPower of the DeviceList.
     */
    public double getNominalPower() {
        double result = 0;
        for (Device d : this.devices) {
            result += d.getNominalPower();
        }
        return result;
    }

    /**
     * String Builder of the DeviceList.
     *
     * @return a String of Devices from the List.
     */
    public String buildString() {
        StringBuilder result = new StringBuilder("---------------\n");
        for (int i = 0; i < devices.size(); i++) {
            Device device = devices.get(i);
            result.append(i).append(") device Name: ").append(device.getName());
            result.append(", device Type: ").append(device.getType());
            result.append(", device Nominal Power: ").append(device.getNominalPower()).append("\n");
        }
        result.append("---------------\n");
        return result.toString();
    }

    /**
     * Returns the daily estimate consumption of all devices on this list.
     *
     * @param deviceType the device type
     * @param time       represents a day in minutes
     * @return the sum of all daily estimate consumptions of that type
     */
    public double getDailyConsumptionByDeviceType(String deviceType, int time) {
        double result = 0;
        for (Device d : devices) {
            if (d.getType().equals(deviceType)) {
                result += d.getEnergyConsumption(time);
            }
        }
        return result;
    }

    /**
     * Adds all devices of a given DeviceList to target list, rejecting duplicates.
     *
     * @param deviceList The list to be added to the target list
     * @return A parallel deviceList with all the devices that could be added
     **/
    public DeviceList appendListNoDuplicates(DeviceList deviceList) {
        Device[] deviceLNoDuplicates = deviceList.getElementsAsArray();
        for (Device d : deviceLNoDuplicates) {
            this.add(d);
        }
        return this;
    }

    /**
     * Checks the device list size and returns the size as int.\
     *
     * @return DeviceList size as int
     **/
    public int size() {
        return this.devices.size();
    }

    /**
     * This method receives an index as parameter and gets a device from device list.
     *
     * @param index The index of the desired Device
     * @return returns device that corresponds to index.
     */
    public Device get(int index) {
        if(this.devices.isEmpty()){
            throw new IndexOutOfBoundsException("The device list is empty.");
        }
        return this.devices.get(index);
    }

    /**
     * This method receives an index as parameter and gets a device type from device list.
     *
     * @param index The index of the desired Device type
     * @return returns device type that corresponds to index.
     */
    public String getTypeByIndex(int index) {
        if(this.devices.isEmpty()){
            throw new IndexOutOfBoundsException("The device list is empty.");
        }
        Device device = this.devices.get(index);
        return device.getType();
    }


    /**
     * Returns the energy consumption in a given time interval, that is, the sum of the energy consumption
     * of all metered devices' logs in the device list that are fully within the given time interval.
     *
     * @param initialDate defines the start of the interval.
     * @param finalDate   defines the end of the interval.
     * @return the energy consumption as double.
     */
    public double getConsumptionInInterval(Date initialDate, Date finalDate) {
        double result = 0;
        for (Device d : this.devices) {
            result += d.getConsumptionInInterval(initialDate, finalDate);
        }
        return result;
    }

    /**
     * Returns a list of devices of a given typ
     *
     * @param deviceType the device type
     * @return the list with all devices of a given type
     */
    public DeviceList getDevicesOfGivenType(String deviceType) {
        DeviceList devicesOfGivenType = new DeviceList();
        for (Device d : this.devices) {
            if (d.getType().equals(deviceType)) {
                devicesOfGivenType.add(d);
            }
        }
        return devicesOfGivenType;
    }

    /**
     * Adds all of this devices to a given list. Skips duplicates.
     *
     * @param list is the list we want to addWithoutPersisting the room's devices to.
     */
    public void addDevicesToDeviceList(DeviceList list) {
        for (Device d : this.devices) {
            if (!(list.containsDevice(d))) {
                list.add(d);
            }
        }
    }

    /**
     * Removes all devices in list from the list given.
     *
     * @param list is the list we want to removeGeographicArea devices from.
     * @return false if the list is invalid (null), true otherwise.
     */
    public boolean removeDevicesFromGivenList(DeviceList list) {
        if (list == null) {
            return false;
        }
        for (Device d : this.devices) {
            list.removeDevice(d);
        }
        return true;
    }

    /**
     * Method that returns a LogList of a given interval of time.
     *
     * @param startDate is the Starting Date of the log.
     * @param endDate   is the End Date of the Log.
     * @return a LogList
     */
    public LogList getLogsInInterval(Date startDate, Date endDate) {
        LogList result = new LogList();
        for (Device d : this.devices) {
            LogList tempList = d.getLogsInInterval(startDate, endDate);
            result.addLogList(tempList);
        }
        return result;
    }

    /**
     * Getter (array of devices)
     *
     * @return array of devices
     */
    public Device[] getElementsAsArray() {
        int sizeOfResultArray = devices.size();
        Device[] result = new Device[sizeOfResultArray];
        for (int i = 0; i < devices.size(); i++) {
            result[i] = devices.get(i);
        }
        return result;
    }

    @Override
    public boolean equals(Object testObject) {
        if (this == testObject) {
            return true;
        }
        if (!(testObject instanceof DeviceList)) {
            return false;
        }
        DeviceList list = (DeviceList) testObject;
        return Arrays.equals(this.getElementsAsArray(), list.getElementsAsArray());
    }

    @Override
    public int hashCode() {
        return 1;
    }
}

