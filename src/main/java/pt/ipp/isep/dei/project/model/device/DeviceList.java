package pt.ipp.isep.dei.project.model.device;

import pt.ipp.isep.dei.project.model.device.devices.Device;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class that groups a number of Devices.
 */

public class DeviceList {

    private List<Device> mDeviceList;


    public DeviceList() {
        this.mDeviceList = new ArrayList<>();
    }


    public List<Device> getList() {
        return this.mDeviceList;
    }

    public boolean containsDevice(Device device) {
        return mDeviceList.contains(device);
    }

    public boolean addDevice(Device device) {
        if (!mDeviceList.contains(device)) {
            mDeviceList.add(device);
            return true;
        }
        return false;
    }

    public boolean removeDevice(Device device) {
        if (this.contains(device)) {
            mDeviceList.remove(device);
            return true;
        }
        return false;
    }

    public boolean checkIfListIsValid() {
        return !mDeviceList.isEmpty();
    }

    public double getNominalPower() {
        double result = 0;
        for (Device d : this.getList()) {
            result += d.getNominalPower();
        }
        return result;
    }

    public List<Integer> matchDeviceIndexByString(String input) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < mDeviceList.size(); i++) {
            if (mDeviceList.get(i).getName().equals(input)) {
                result.add(i);
            }
        }
        return result;
    }

    public String buildElementByIndexString(List<Integer> indexes) {
        StringBuilder result = new StringBuilder();
        for (Integer indexe : indexes) {
            int pos = indexe;
            result.append(indexe).append(") ").append(mDeviceList.get(pos).getName()).append(", ").append(mDeviceList.get(pos).getNominalPower()).append(".\n");
        }
        return result.toString();
    }

    public boolean contains(Device device) {
        return this.mDeviceList.contains(device);
    }

    public String buildDevicesString() {
        int counter = 0;
        StringBuilder result = new StringBuilder();
        for (Device d : this.mDeviceList) {
            result.append(counter).append(") ").append(d.buildDeviceString());
            counter++;
        }
        return result.toString();
    }


    //TODO FIX THIS METHOD - USED ON US 752. NOT WORKING NOW, has to send time. I put the 24 temporary

    /**
     * Returns the daily estimate consumption of all devices on this list.
     *
     * @param deviceType the device type
     * @return the sum of all daily estimate consumptions of that type
     */
    public double getDailyConsumptionByDeviceType(String deviceType) {
        double result = 0;
        for (Device d : mDeviceList) {
            if (d.getType().equals(deviceType)) {
                result += d.getEnergyConsumption(24);
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

