package pt.ipp.isep.dei.project.model.Device.devicetypes;

/**
 * Represents the common types of devices that one can find at home.
 */
public enum DeviceType {
    WATER_HEATER(),
    WASHING_MACHINE(),
    DISHWASHER(),
    FRIDGE(),
    LAMP();


    public String buildDeviceTypesByIndexString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < DeviceType.values().length; i++) {
            result.append(i + ") ").append("Device Type: ").append(DeviceType.values()[i]).append(";\n");
        }

        return result.toString();
    }

    public String buildDeviceTypeString(DeviceType deviceType) {
        StringBuilder result = new StringBuilder();
        result.append(deviceType);
        return result.toString();
    }
}