package pt.ipp.isep.dei.project.model.device.devicetypes;

import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.PortableElectricConvectionHeater;
import pt.ipp.isep.dei.project.model.device.devicespecs.PortableElectricConvectionHeaterSpec;

public class PortableElectricConvectionHeaterType implements DeviceType {

    public Device createDevice() {
        PortableElectricConvectionHeaterSpec ds = new PortableElectricConvectionHeaterSpec();
        return new PortableElectricConvectionHeater(ds);
    }

    public String getDeviceType() {
        return "PortableElectricConvectionHeater";
    }
}
