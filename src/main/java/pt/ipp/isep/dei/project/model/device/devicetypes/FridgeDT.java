package pt.ipp.isep.dei.project.model.device.devicetypes;

import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.deviceSpecs.DeviceSpecs;
import pt.ipp.isep.dei.project.model.device.deviceSpecs.Fridge;
import pt.ipp.isep.dei.project.model.device.deviceSpecs.WaterHeater;

public class FridgeDT implements DeviceType {

    public Device createDevice() {
        DeviceSpecs ds = new Fridge();
        Device device = new Device(ds);
        return device;
    }
}
