package pt.ipp.isep.dei.project.model.device.devicetypes;

import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.PortableElectricOilHeater;

public class PortableElectricOilHeaterDT implements DeviceType {

    public Device createDevice() {
        return new PortableElectricOilHeater();
    }

    public String getDeviceType() {
        return "PortableElectricOilHeater";
    }
}
