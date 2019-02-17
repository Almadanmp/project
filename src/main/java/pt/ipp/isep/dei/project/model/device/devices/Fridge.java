package pt.ipp.isep.dei.project.model.device.devices;

import pt.ipp.isep.dei.project.model.Metered;
import pt.ipp.isep.dei.project.model.device.Log;
import pt.ipp.isep.dei.project.model.device.LogList;
import pt.ipp.isep.dei.project.model.device.devicespecs.FridgeSpec;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Fridge implements Device, Metered {
    private String mFridgeName;
    private double mFridgeNominalPower;
    private String mFridgeType = "Fridge";
    private FridgeSpec mFridgeDeviceSpecs;
    private boolean mFridgeActive;
    private LogList mFridgeLogList;


    public Fridge(FridgeSpec fridgeSpec) {
        this.mFridgeDeviceSpecs = fridgeSpec;
        this.mFridgeActive = true;
        mFridgeLogList = new LogList();
    }

    public String getName() {
        return this.mFridgeName;
    }

    public void setName(String name) {
        this.mFridgeName = name;
    }

    public String getType() {
        return this.mFridgeType;
    }

    public void setNominalPower(double nominalPower) {
        this.mFridgeNominalPower = nominalPower;
    }

    public double getNominalPower() {
        return this.mFridgeNominalPower;
    }

    public boolean isActive() {
        return this.mFridgeActive;
    }

    public boolean deactivate() {
        if (isActive()) {
            this.mFridgeActive = false;
            return true;
        } else {
            return false;
        }
    }

    public boolean isProgrammable() {
        return false;
    }

    public String buildDeviceString() {
        return "The device Name is " + this.mFridgeName + ", and its NominalPower is " + this.mFridgeNominalPower + " kW.\n";
    }

    /**
     * This method returns the Device LogList.
     *
     * @return Device LogList.
     */
    public LogList getLogList() {
        return mFridgeLogList;
    }

    /**
     * This method adds a Log to the device LogList, if the Log isn't already in the LogList.
     *
     * @param log - Parameter which will be used to add to the Device LogList.
     * @return true if log was added
     */
    public boolean addLog(Log log) {
        if (!(mFridgeLogList.getLogList().contains(log)) && this.mFridgeActive) {
            mFridgeLogList.getLogList().add(log);
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
        return mFridgeLogList.countLogsInInterval(initialTime, finalTime);
    }

    public LogList getLogsInInterval(Date startDate, Date endDate) {
        return mFridgeLogList.getLogsInInterval(startDate, endDate);
    }

    /**
     * This method checks the Logs registered in a periods which are totally contained in the defined interval.
     *
     * @param initialTime - Beginning of the interval
     * @param finalTime   - Ending of the interval
     * @return total consumption within the defined interval
     */
    public double getConsumptionWithinGivenInterval(Date initialTime, Date finalTime) {
        return mFridgeLogList.getConsumptionWithinGivenInterval(initialTime, finalTime);
    }

    /**
     * Energy consumption (daily) = annual energy consumption / 365 (kWh)
     *
     * @param time
     * @return
     */
    public double getEnergyConsumption(float time) {
        return mFridgeNominalPower * time;
    }


    // WRAPPER METHODS TO DEVICE SPECS
    public List<String> getAttributeNames() {
        return mFridgeDeviceSpecs.getAttributeNames();
    }

    public Object getAttributeValue(String attributeName) {
        return mFridgeDeviceSpecs.getAttributeValue(attributeName);
    }

    public boolean setAttributeValue(String attributeName, Object attributeValue) {
        return mFridgeDeviceSpecs.setAttributeValue(attributeName, attributeValue);
    }

    public Object getAttributeUnit(String attributeName) {
        return mFridgeDeviceSpecs.getAttributeUnit(attributeName);
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
        return Objects.equals(mFridgeName, device.getName());
    }

    @Override
    public int hashCode() {
        return 1;
    }

}
