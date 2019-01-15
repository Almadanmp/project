package pt.ipp.isep.dei.project.model;

import pt.ipp.isep.dei.project.model.devicetypes.DeviceType;

import java.util.List;

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

    /**
     * Method to get a device type from a device spec
     *
     * @return a device type
     */
    DeviceType getType();

    /**
     * Get a list with the attribute names of a device spec
     *
     * @return a list with attribute names
     */
    List<String> getAttributeNames();

    /**
     * get a attribute value
     *
     * @param attributeName attribute name form which we want the attribute value
     * @return attribue value
     */
    double getAttributeValue(String attributeName);

    /**
     * sets attribute value
     *
     * @param attributeName  attribute name that will get a new value
     * @param attributeValue new value to set on the attribute
     * @return false if the attribute value fails (if attribute value name inputted doesnt exist on attributes.
     */
    boolean setAttributeValue(String attributeName, double attributeValue);
}
