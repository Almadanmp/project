package pt.ipp.isep.dei.project.model.device.devicetypes;

import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.PortableElectricConvectionHeater;

public class PortableElectricConvectionHeaterDT implements DeviceType {

    public Device createDevice() {
        return new PortableElectricConvectionHeater();
    }

    public String getDeviceType() {
        return "PortableElectricConvectionHeater";
    }
}
