package pt.ipp.isep.dei.project.model;

import pt.ipp.isep.dei.project.model.devicetypes.DeviceType;

/**
 * Represents device specifications.
 * Provides methods to get device information for a each device type.
 * (e.g. of device spec implementation: dishwasher, fridge, etc.
 */

public interface DeviceSpecs {

    /**
     * This method will return the energy consumption in kW/h.
     *
     * @return Energy consumption - measured in kW/h te basic trade unit of energy.
     */
    double getConsumption(); // To be added: time/period argument (once spec is closed)


    DeviceType getType();

}
