package PT.IPP.ISEP.DEI.Project.Model;

import java.util.ArrayList;
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

}
