package pt.ipp.isep.dei.project.model.device.devicetypes;

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
            result.append(i).append(") ").append("device Type: ").append(DeviceType.values()[i]).append(";\n");
        }

        return result.toString();
    }

    public String buildDeviceTypeString(DeviceType deviceType) {
        return String.valueOf(deviceType);
    }
}