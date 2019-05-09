package pt.ipp.isep.dei.project.model.device;

import pt.ipp.isep.dei.project.model.device.devicespecs.FanSpec;
import pt.ipp.isep.dei.project.model.device.log.Log;
import pt.ipp.isep.dei.project.model.device.log.LogList;
import pt.ipp.isep.dei.project.model.device.program.ProgramList;
import java.util.Date;
import java.util.List;

public class Fan implements Device {
    private String name;
    private ProgramList programList;
    private final FanSpec deviceSpecs;
    private final CommonDeviceAttributes commonDeviceAttributes;

    /**
     * Default constructor for fan devices.
     * @param fanSpec is one deviceSpec specific for fans.
     */

    public Fan(FanSpec fanSpec) {
        commonDeviceAttributes = new CommonDeviceAttributes();
        this.deviceSpecs = fanSpec;
        programList = new ProgramList();
    }

    /**
     * Method to get the name of the device.
     * @return is a string that corresponds to the device's name.
     */

    public String getName() {
        return CommonDeviceAttributes.getName(this.name);
    }

    /**
     * Method to determine the name of the device.
     * @param name is the string we want to set as the device's name.
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns a hard coded string that corresponds to the type of device.
     * @return is the device's type.
     */

    public String getType() {
        return "Fan";
    }

    /**
     * Method to determine the nominal power of the device.
     * @param nominalPower is the provided double to set as the nominal power of the device.
     */

    public void setNominalPower(double nominalPower) {
        this.commonDeviceAttributes.setNominalPower(nominalPower);
    }

    /**
     * Method to retrieve the nominal power of the device.
     * @return is the device's nominal power.
     */

    public double getNominalPower() {
        return this.commonDeviceAttributes.getNominalPower();
    }

    /**
     * Method to determine if the device is active or not. An inactive device can't store logs.
     * @return is true if the device is active, false if it's not.
     */

    public boolean isActive() {
        return this.commonDeviceAttributes.isActive();
    }

    /**
     * Method to deactivate the device.
     * @return is true if the device was deactivated, is false if the device was already inactive.
     */

    public boolean deactivate() {
        return this.commonDeviceAttributes.deactivate();
    }

    /**
     * Method that turns the device into a printable string.
     * @return is a string that contains the relevant information about the device.
     */

    public String buildString() {
        return "The fan name is " + this.name + " and its nominal power is: " + this.commonDeviceAttributes.getNominalPower();
    }

    /**
     * Method that returns the device's logList.
     * @return is the device's LogList.
     */

    public LogList getLogList() {
        return this.commonDeviceAttributes.getLogList();
    }

    /**
     * Method used to check if the device has any valid logs.
     * @return is true
     */

    public boolean isLogListEmpty() {
        return this.commonDeviceAttributes.isLogListEmpty();
    }

    /**
     * Method that adds a log to the device's log list.
     * @param log is the log we want to addWithoutPersisting to the device's list.
     * @return is true if the log was added, and false if the log was already contained in the device's list.
     */

    public boolean addLog(Log log) {
        return this.commonDeviceAttributes.addLog(log);
    }

    /**
     * Method that counts the logs in a given interval.
     * @param initialTime is the start of the interval.
     * @param finalTime   is the end of the interval.
     * @return is the number of logs in the interval.
     */

    public int countLogsInInterval(Date initialTime, Date finalTime) {
        return this.commonDeviceAttributes.countLogsInInterval(initialTime, finalTime);
    }

    /**
     * Method that returns all the logs fully contained in a given time interval.
     * @param startDate is the start of the interval.
     * @param endDate is the end of the interval.
     * @return is the number of logs fully contained in the interval.
     */

    public LogList getLogsInInterval(Date startDate, Date endDate) {
        return this.commonDeviceAttributes.getLogsInInterval(startDate, endDate);
    }

    /**
     * Method that returns the list of programs that the device has.
     * @return is the list of programs that the device has.
     */

    public ProgramList getProgramList() {
        return programList;
    }


    /**
     * Method that determines the program list of a given device.
     * @param plist is the program list we want to assign to the device.
     */

    public void setProgramList(ProgramList plist) {
        this.programList = plist;
    }


    /**
     * This method checks the Logs registered in a periods which are totally contained in the defined interval.
     *
     * @param initialTime - Beginning of the interval
     * @param finalTime   - Ending of the interval
     * @return total consumption within the defined interval
     */

    public double getConsumptionInInterval(Date initialTime, Date finalTime) {
        return this.commonDeviceAttributes.getConsumptionInInterval(initialTime, finalTime);
    }

    /**
     * Energy consumption = energy consumption of the program (kWh)
     *
     * @param time the desired time
     * @return the energy consumed in the given time
     */

    public double getEnergyConsumption(float time) {
        return this.commonDeviceAttributes.getEnergyConsumption(time);
    }

    // WRAPPER METHODS TO DEVICE SPECS

    /**
     * Method that returns the name of all attributes unique to the device.
     * @return returns an empty list, because the device has no unique attributes.
     */

    public List<String> getAttributeNames() {
        return deviceSpecs.getAttributeNames();
    }

    /**
     * Method that returns the value of a particular unique attribute.
     * @param attributeName is the name of the unique attribute from which we want to get a value.
     * @return throws unsupported operation exception since there are no unique attributes.
     */

    public Object getAttributeValue(String attributeName) {
        throw new UnsupportedOperationException();
    }

    /**
     * Method that determines the value of a particular unique attribute.
     * @param attributeName  is the name of the attribute we want to assign a value to.
     * @param attributeValue is the value we want to assign to the attribute.
     * @return throws unsupported operation exception since there are no unique attributes.
     */

    public boolean setAttributeValue(String attributeName, Object attributeValue) {
        throw new UnsupportedOperationException();
    }

    /**
     * Method that returns a string that corresponds to the unit in which a particular attribute is measured.
     * @param attributeName name of the attribute we want to get the unit from.
     * @return throws unsupported operation exception since there are no unique attributes.
     */

    public Object getAttributeUnit(String attributeName) {
        throw new UnsupportedOperationException();
    }

}
