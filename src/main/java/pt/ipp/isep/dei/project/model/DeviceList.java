package pt.ipp.isep.dei.project.model;

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


    public List<Device> getDeviceList() {
        return this.mDeviceList;
    }

    boolean containsDevice(Device device) {
        return mDeviceList.contains(device);
    }

    public boolean addDevices(Device device) {
        if (!mDeviceList.contains(device)) {
            mDeviceList.add(device);
            return true;
        }
        return false;
    }

    void removeDevice(Device deviceToRemove) {
        mDeviceList.remove(deviceToRemove);
    }

    boolean checkIfListIsValid() {
        return !mDeviceList.isEmpty();
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

    public String printElementsByIndex(List<Integer> indexes) {
        StringBuilder result = new StringBuilder();
        for (Integer indexe : indexes) {
            int pos = indexe;
            result.append(indexe).append(") ").append(mDeviceList.get(pos).getName()).append(", ").append(mDeviceList.get(pos).getmParentRoom().getRoomName()).append(", ").append(mDeviceList.get(pos).getNominalPower()).append(".\n");
        }
        return result.toString();
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

