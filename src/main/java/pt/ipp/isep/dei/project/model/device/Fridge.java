package pt.ipp.isep.dei.project.model.device;

import pt.ipp.isep.dei.project.model.Metered;
import pt.ipp.isep.dei.project.model.device.log.Log;
import pt.ipp.isep.dei.project.model.device.log.LogList;
import pt.ipp.isep.dei.project.model.device.devicespecs.FridgeSpec;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Fridge implements Device, Metered {
    private String fridgeName;
    private double fridgeNominalPower;
    private FridgeSpec fridgeDeviceSpecs;
    private boolean fridgeActive;
    private LogList fridgeLogList;


    public Fridge(FridgeSpec fridgeSpec) {
        this.fridgeDeviceSpecs = fridgeSpec;
        this.fridgeActive = true;
        fridgeLogList = new LogList();
    }

    public String getName() {
        return this.fridgeName;
    }

    public void setName(String name) {
        this.fridgeName = name;
    }

    public String getType() {
        return "Fridge";
    }

    public void setNominalPower(double nominalPower) {
        this.fridgeNominalPower = nominalPower;
    }

    public double getNominalPower() {
        return this.fridgeNominalPower;
    }

    public boolean isActive() {
        return this.fridgeActive;
    }

    public boolean deactivate() {
        if (isActive()) {
            this.fridgeActive = false;
            return true;
        } else {
            return false;
        }
    }

    public boolean isProgrammable() {
        return false;
    }

    public String buildDeviceString() {
        return "The device Name is " + this.fridgeName + ", and its NominalPower is " + this.fridgeNominalPower + " kW.\n";
    }

    /**
     * This method returns the Device LogList.
     *
     * @return Device LogList.
     */
    public LogList getLogList() {
        return fridgeLogList;
    }

    /**
     * Method checks if device LogList is empty
     * @return true if LogList is empty, false otherwise
     * */
    public boolean isLogListEmpty(){
        return this.fridgeLogList.isEmpty();
    }

    /**
     * This method adds a Log to the device LogList, if the Log isn't already in the LogList.
     *
     * @param log - Parameter which will be used to add to the Device LogList.
     * @return true if log was added
     */
    public boolean addLog(Log log) {
        if (!(fridgeLogList.getLogListAttribute().contains(log)) && this.fridgeActive) {
            fridgeLogList.getLogListAttribute().add(log);
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
        return fridgeLogList.countLogsInInterval(initialTime, finalTime);
    }

    public LogList getLogsInInterval(Date startDate, Date endDate) {
        return fridgeLogList.getLogsInInterval(startDate, endDate);
    }

    /**
     * This method checks the Logs registered in a periods which are totally contained in the defined interval.
     *
     * @param initialTime - Beginning of the interval
     * @param finalTime   - Ending of the interval
     * @return total consumption within the defined interval
     */
    public double getConsumptionWithinGivenInterval(Date initialTime, Date finalTime) {
        return fridgeLogList.getConsumptionWithinGivenInterval(initialTime, finalTime);
    }

    /**
     * Energy consumption (daily) = annual energy consumption / 365 (kWh)
     *
     * @param time
     * @return
     */
    public double getEnergyConsumption(float time) {
        return fridgeNominalPower * time;
    }


    // WRAPPER METHODS TO DEVICE SPECS
    public List<String> getAttributeNames() {
        return fridgeDeviceSpecs.getAttributeNames();
    }

    public Object getAttributeValue(String attributeName) {
        return fridgeDeviceSpecs.getAttributeValue(attributeName);
    }

    public boolean setAttributeValue(String attributeName, Object attributeValue) {
        return fridgeDeviceSpecs.setAttributeValue(attributeName, attributeValue);
    }

    public Object getAttributeUnit(String attributeName) {
        return fridgeDeviceSpecs.getAttributeUnit(attributeName);
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
        return Objects.equals(fridgeName, device.getName());
    }

    @Override
    public int hashCode() {
        return 1;
    }

}
