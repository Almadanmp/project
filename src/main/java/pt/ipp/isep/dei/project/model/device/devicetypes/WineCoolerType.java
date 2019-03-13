package pt.ipp.isep.dei.project.model.device.devicetypes;

import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.devicespecs.WineCoolerSpec;
import pt.ipp.isep.dei.project.model.device.WineCooler;


public class WineCoolerType implements DeviceType {

    public Device createDevice() {
        WineCoolerSpec ds = new WineCoolerSpec();
        return new WineCooler(ds);
    }

    public String getDeviceType() {
        return "WineCooler";
    }
}