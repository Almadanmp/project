package pt.ipp.isep.dei.project.model.device;

import pt.ipp.isep.dei.project.model.Metered;
import pt.ipp.isep.dei.project.model.device.log.Log;
import pt.ipp.isep.dei.project.model.device.log.LogList;
import pt.ipp.isep.dei.project.model.device.devicespecs.LampSpec;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Lamp implements Device, Metered {
    private String lampName;
    private double lampNominalPower;
    private LampSpec lampDeviceSpecs;
    private boolean lampActive;
    private LogList lampLogList;


    public Lamp(LampSpec lampSpec) {
        this.lampDeviceSpecs = lampSpec;
        this.lampActive = true;
        lampLogList = new LogList();
    }

    public String getName() {
        return this.lampName;
    }

    public void setName(String name) {
        this.lampName = name;
    }

    public String getType() {
        return  "Lamp";
    }

    public void setNominalPower(double nominalPower) {
        this.lampNominalPower = nominalPower;
    }

    public double getNominalPower() {
        return this.lampNominalPower;
    }

    public boolean isActive() {
        return this.lampActive;
    }

    public boolean deactivate() {
        if (isActive()) {
            this.lampActive = false;
            return true;
        } else {
            return false;
        }
    }

    public boolean isProgrammable() {
        return false;
    }

    public String buildDeviceString() {
        return "The device Name is " + this.lampName + ", and its NominalPower is " + this.lampNominalPower + " kW.\n";
    }

    /**
     * This method returns the Device LogList.
     *
     * @return Device LogList.
     */
    public LogList getLogList() {
        return lampLogList;
    }

    /**
     * Method checks if device LogList is empty
     * @return true if LogList is empty, false otherwise
     * */
    public boolean isLogListEmpty(){
        return this.lampLogList.isEmpty();
    }

    /**
     * This method adds a Log to the device LogList, if the Log isn't already in the LogList.
     *
     * @param log - Parameter which will be used to add to the Device LogList.
     * @return true if log was added
     */
    public boolean addLog(Log log) {
        if (!(lampLogList.getLogListAttribute().contains(log)) && this.lampActive) {
            lampLogList.getLogListAttribute().add(log);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method determines the amount of data logs that fall within a given time interval.
     *
     * @param initialTime is the start time of the interval.
     * @param finalTime   is the end time of the interval.
     * @return is the number of valid data logs in the given interval.
     */
    public int countLogsInInterval(Date initialTime, Date finalTime) {
        return lampLogList.countLogsInInterval(initialTime, finalTime);
    }

    public LogList getLogsInInterval(Date startDate, Date endDate) {
        return lampLogList.getLogsInInterval(startDate, endDate);
    }

    /**
     * This method checks the Logs registered in a periods which are totally contained in the defined interval.
     *
     * @param initialTime - Beginning of the interval
     * @param finalTime   - Ending of the interval
     * @return total consumption within the defined interval
     */
    public double getConsumptionWithinGivenInterval(Date initialTime, Date finalTime) {
        return lampLogList.getConsumptionWithinGivenInterval(initialTime, finalTime);
    }

    /**
     * Energy consumption = Nominal power * time (Wh)
     *
     * @param time
     * @return
     */
    public double getEnergyConsumption(float time) {
        return lampNominalPower * time;
    }


    // WRAPPER METHODS TO DEVICE SPECS
    public List<String> getAttributeNames() {
        return lampDeviceSpecs.getAttributeNames();
    }

    public Object getAttributeValue(String attributeName) {
        return lampDeviceSpecs.getAttributeValue(attributeName);
    }

    public boolean setAttributeValue(String attributeName, Object attributeValue) {
        return lampDeviceSpecs.setAttributeValue(attributeName, attributeValue);
    }

    public Object getAttributeUnit(String attributeName) {
        return lampDeviceSpecs.getAttributeUnit(attributeName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Device device = (Device) o;
        return Objects.equals(lampName, device.getName());
    }

    @Override
    public int hashCode() {
        return 1;
    }

}
