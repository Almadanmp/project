package pt.ipp.isep.dei.project.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DeviceList {

    private List<Device> mDeviceList;


    public DeviceList() {
        this.mDeviceList = new ArrayList<>();
    }


    public List<Device> getDeviceList() {
        return this.mDeviceList;
    }

    public boolean containsDevice(Device device) {
        return mDeviceList.contains(device);
    }

    public boolean addDevices (Device device) {
        if(!(mDeviceList.contains(device))){
            mDeviceList.add(device);
            return true;
        }
        return false;
    }

    public void removeDevice(Device deviceToRemove) {
        mDeviceList.remove(deviceToRemove);
    }

    public boolean checkIfListIsValid() {
        return !mDeviceList.isEmpty();
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
        return Arrays.equals(this.getDeviceList().toArray(), list.getDeviceList().toArray());
    }

    @Override
    public int hashCode() {
        return 1;
    }
}

