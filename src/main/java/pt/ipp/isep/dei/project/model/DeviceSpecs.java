package pt.ipp.isep.dei.project.model;

import pt.ipp.isep.dei.project.model.devicetypes.DeviceType;

/**
 * Represents device specifications.
 * Provides methods to get device information for a each device type.
 * (e.g. of device spec implementation: dishwasher, fridge, etc.
 */

public interface DeviceSpecs {

    /**
     * This method will return the total amount of energy produced/consumed during a certain period.
     *
     * @return Energy consumption - measured in KWh te basic trade unit of energy.
     */
    double getConsumption(); // To be added: time/period argument (once spec is closed)


    DeviceType getType();
}
