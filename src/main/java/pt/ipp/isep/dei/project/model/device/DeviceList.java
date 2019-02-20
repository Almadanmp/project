package pt.ipp.isep.dei.project.model.device;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
     * Getter of the DeviceList.
     *
     * @return List of Devices.
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
     * Method to add a Device to the DeviceList.
     *
     * @param device is the device that we want to add.
     * @return true if the device was successfully added, false otherwise(device already exists in the list)
     */
    public boolean addDevice(Device device) {
        if (!devices.contains(device)) {
            devices.add(device);
            return true;
        }
        return false;
    }

    /**
     * Method to remove a Device from the DeviceList.
     *
     * @param device is the device that we want to remove.
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
        for (Device d : this.getList()) {
            result += d.getNominalPower();
        }
        return result;
    }

    /**
     * String Builder of the DeviceList.
     *
     * @return a String of Devices from the List.
     */
    public String buildDevicesString() {
        int counter = 0;
        StringBuilder result = new StringBuilder();
        for (Device d : this.devices) {
            result.append(counter).append(") ").append(d.buildDeviceString());
            counter++;
        }
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
    /** Adds all devices of a given DeviceList to target list, rejecting duplicates.\
     * @return DeviceList completed
     * **/
    public DeviceList appendListNoDuplicates(DeviceList deviceList){
        for(Device d : deviceList.getList()){
            this.addDevice(d);
        }
        return this;
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
        return Arrays.equals(this.getList().toArray(), list.getList().toArray());
    }

    @Override
    public int hashCode() {
        return 1;
    }
}

