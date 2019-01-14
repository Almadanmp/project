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

    public String printListOfDevicesFromRoom(Room room) {
        StringBuilder result = new StringBuilder("---------------\n");
        if (room.getDeviceList().isEmpty()) {
            return "This room has no devices on it\n";
        }
        for (int i = 0; i < room.getDeviceList().size(); i++) {
            Device device = room.getDeviceList().get(i);
            result.append("\n" + i).append(") Device Name: ").append(device.getName());
            result.append(", Device Type: ").append(device.getDeviceType());
            result.append(", Device Nominal Power: ").append(device.getNominalPower());
        }
        result.append("\n---------------\n");
        return result.toString();
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

