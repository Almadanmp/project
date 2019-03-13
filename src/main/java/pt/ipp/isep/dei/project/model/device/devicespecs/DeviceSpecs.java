package pt.ipp.isep.dei.project.model.device.devicespecs;

import java.util.List;

/**
 * Represents device technical specifications.
 * Provides methods to get device information for a each device type.
 * (e.g. of device spec implementation: dishwasher, fridge, etc.
 */

public interface DeviceSpecs {

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
     * @return attribute value
     */
    Object getAttributeValue(String attributeName);

    /**
     * sets attribute value
     *
     * @param attributeName  attribute name that will get a new value
     * @param attributeValue new value to set on the attribute
     * @return false if the attribute value fails (if attribute value name inputted doesn't exist on attributes.
     */
    boolean setAttributeValue(String attributeName, Object attributeValue);

    /**
     * get the unity of the attribute.
     *
     * @param attributeName attribute name that we want to get the unit from.
     * @return returns the attribute unit
     */
    Object getAttributeUnit(String attributeName);
}
