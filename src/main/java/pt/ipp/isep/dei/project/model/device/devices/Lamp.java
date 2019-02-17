package pt.ipp.isep.dei.project.model.device.devices;

import pt.ipp.isep.dei.project.model.Metered;
import pt.ipp.isep.dei.project.model.device.Log;
import pt.ipp.isep.dei.project.model.device.LogList;
import pt.ipp.isep.dei.project.model.device.devicespecs.LampSpec;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Lamp implements Device, Metered {
    private String mLampName;
    private double mLampNominalPower;
    private String mLampType = "Lamp";
    private LampSpec mLampDeviceSpecs;
    private boolean mLampActive;
    private LogList mLampLogList;


    public Lamp(LampSpec lampSpec) {
        this.mLampDeviceSpecs = lampSpec;
        this.mLampActive = true;
        mLampLogList = new LogList();
    }

    public String getName() {
        return this.mLampName;
    }

    public void setName(String name) {
        this.mLampName = name;
    }

    public String getType() {
        return this.mLampType;
    }

    public void setNominalPower(double nominalPower) {
        this.mLampNominalPower = nominalPower;
    }

    public double getNominalPower() {
        return this.mLampNominalPower;
    }

    public boolean isActive() {
        return this.mLampActive;
    }

    public boolean deactivate() {
        if (isActive()) {
            this.mLampActive = false;
            return true;
        } else {
            return false;
        }
    }

    public boolean isProgrammable() {
        return false;
    }

    public String buildDeviceString() {
        return "The device Name is " + this.mLampName + ", and its NominalPower is " + this.mLampNominalPower + " kW.\n";
    }

    /**
     * This method returns the Device LogList.
     *
     * @return Device LogList.
     */
    public LogList getLogList() {
        return mLampLogList;
    }

    /**
     * This method adds a Log to the device LogList, if the Log isn't already in the LogList.
     *
     * @param log - Parameter which will be used to add to the Device LogList.
     * @return true if log was added
     */
    public boolean addLog(Log log) {
        if (!(mLampLogList.getLogList().contains(log)) && this.mLampActive) {
            mLampLogList.getLogList().add(log);
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
        return mLampLogList.countLogsInInterval(initialTime, finalTime);
    }

    public LogList getLogsInInterval(Date startDate, Date endDate) {
        return mLampLogList.getLogsInInterval(startDate, endDate);
    }

    /**
     * This method checks the Logs registered in a periods which are totally contained in the defined interval.
     *
     * @param initialTime - Beginning of the interval
     * @param finalTime   - Ending of the interval
     * @return total consumption within the defined interval
     */
    public double getConsumptionWithinGivenInterval(Date initialTime, Date finalTime) {
        return mLampLogList.getConsumptionWithinGivenInterval(initialTime, finalTime);
    }

    /**
     * Energy consumption = Nominal power * time (Wh)
     *
     * @param time
     * @return
     */
    public double getEnergyConsumption(float time) {
        return mLampNominalPower * time;
    }


    // WRAPPER METHODS TO DEVICE SPECS
    public List<String> getAttributeNames() {
        return mLampDeviceSpecs.getAttributeNames();
    }

    public Object getAttributeValue(String attributeName) {
        return mLampDeviceSpecs.getAttributeValue(attributeName);
    }

    public boolean setAttributeValue(String attributeName, Object attributeValue) {
        return mLampDeviceSpecs.setAttributeValue(attributeName, attributeValue);
    }

    public Object getAttributeUnit(String attributeName) {
        return mLampDeviceSpecs.getAttributeUnit(attributeName);
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
        return Objects.equals(mLampName, device.getName());
    }

    @Override
    public int hashCode() {
        return 1;
    }

}
