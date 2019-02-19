package pt.ipp.isep.dei.project.model.device;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class that groups a number of Devices.
 */

public class DeviceList {

    private List<Device> deviceList;


    public DeviceList() {
        this.deviceList = new ArrayList<>();
    }


    public List<Device> getList() {
        return this.deviceList;
    }

    public boolean containsDevice(Device device) {
        return deviceList.contains(device);
    }

    public boolean addDevice(Device device) {
        if (!deviceList.contains(device)) {
            deviceList.add(device);
            return true;
        }
        return false;
    }

    public boolean removeDevice(Device device) {
        if (this.contains(device)) {
            deviceList.remove(device);
            return true;
        }
        return false;
    }

    public boolean checkIfListIsValid() {
        return !deviceList.isEmpty();
    }

    public double getNominalPower() {
        double result = 0;
        for (Device d : this.getList()) {
            result += d.getNominalPower();
        }
        return result;
    }

    public boolean contains(Device device) {
        return this.deviceList.contains(device);
    }

    public String buildDevicesString() {
        int counter = 0;
        StringBuilder result = new StringBuilder();
        for (Device d : this.deviceList) {
            result.append(counter).append(") ").append(d.buildDeviceString());
            counter++;
        }
        return result.toString();
    }

    /**
     * Returns the daily estimate consumption of all devices on this list.
     *
     * @param deviceType the device type
     * @param time represents a day in minutes
     * @return the sum of all daily estimate consumptions of that type
     */
    public double getDailyConsumptionByDeviceType(String deviceType, int time) {
        double result = 0;
        for (Device d : deviceList) {
            if (d.getType().equals(deviceType)) {
                result += d.getEnergyConsumption(time);
            }
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
        return Arrays.equals(this.getList().toArray(), list.getList().toArray());
    }

    @Override
    public int hashCode() {
        return 1;
    }
}

