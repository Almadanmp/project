package pt.ipp.isep.dei.project.model.device;

import pt.ipp.isep.dei.project.model.device.log.Log;
import pt.ipp.isep.dei.project.model.device.log.LogList;

import java.util.Date;
import java.util.List;

/**
 * Represents device.
 * Provides methods to get device information.
 */

public interface Device {

    String getName();

    void setName(String name);

    /**
     * Method to get a device type Description from a device spec
     *
     * @return a device type Description
     */
    String getType();

    void setNominalPower(double nominalPower);

    double getNominalPower();

    /**
     * Boolean to get DeviceTemporary Status. Either if is Active or Not.
     *
     * @return true if device is active
     */
    boolean isActive();

    /**
     * This method deactivates the device so it no longer accepts logs.
     *
     * @return true if deactivated
     */
    boolean deactivate();


    /**
     * Method that will build a string with information about the device (nominal power and name)
     *
     * @return String
     */
    String buildString();


    /* LOG RELATED METHODS */

    /**
     * This method returns the DeviceTemporary LogList.
     *
     * @return DeviceTemporary LogList.
     */
    LogList getLogList();

    /**
     * Method checks if device LogList is empty
     * @return true if LogList is empty, false otherwise
     * */
    boolean isLogListEmpty();

    /**
     * This method adds a Log to the device LogList, if the Log isn't already in the LogList.
     *
     * @param log - Parameter which will be used to addWithoutPersisting to the DeviceTemporary LogList.
     * @return true if log was added
     */
    boolean addLog(Log log);

    /**
     * Method determines the amount of data logs that fall within a given time interval.
     *
     * @param initialTime is the start time of the interval.
     * @param finalTime   is the end time of the interval.
     * @return is the number of valid data logs in the given interval.
     */
    int countLogsInInterval(Date initialTime, Date finalTime);


    LogList getLogsInInterval(Date startDate, Date endDate);

    /**
     * This method checks the Logs registered in a periods which are totally contained in the defined interval.
     *
     * @param initialTime - Beginning of the interval
     * @param finalTime   - Ending of the interval
     * @return total consumption within the defined interval
     */
    double getConsumptionInInterval(Date initialTime, Date finalTime);


// Wrapper Methods to Metered

    /**
     * Energy Consumption for a certain time
     * @param time time for each we want to know the energy consumption of the device
     * @return energy consumption
     */
    double getEnergyConsumption(float time);


// Wrapper Methods to DeviceSpecs

    /**
     * get the attribute names
     * @return will return a list of string with all the attribute names of a specific devie
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
