package pt.ipp.isep.dei.project.model.device.devicetypes;

import pt.ipp.isep.dei.project.model.device.DeviceTemporary;

public interface DeviceType {

    DeviceTemporary createDeviceType();

    String getDeviceType();
}
