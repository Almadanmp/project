package pt.ipp.isep.dei.project.model.device.devicetypes;

import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.PortableElectricOilHeater;
import pt.ipp.isep.dei.project.model.device.devicespecs.PortableElectricOilHeaterSpec;

public class PortableElectricOilHeaterType implements DeviceType {

    public Device createDevice() {
        return new PortableElectricOilHeater(new PortableElectricOilHeaterSpec());
    }

    public String getDeviceType() {
        return "PortableElectricOilHeater";
    }
}
