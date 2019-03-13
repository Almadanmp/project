package pt.ipp.isep.dei.project.model.device.devicetypes;

import pt.ipp.isep.dei.project.model.device.Device;

public interface DeviceType {
    Device createDevice();

    String getDeviceType();
}
