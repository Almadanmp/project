package pt.ipp.isep.dei.project.model.device;

import pt.ipp.isep.dei.project.model.Metered;
import pt.ipp.isep.dei.project.model.device.log.Log;
import pt.ipp.isep.dei.project.model.device.log.LogList;
import pt.ipp.isep.dei.project.model.device.devicespecs.DishwasherSpec;
import pt.ipp.isep.dei.project.model.device.program.ProgramList;
import pt.ipp.isep.dei.project.model.device.program.Programmable;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Dishwasher implements Device, Metered, Programmable {
    private String mDWName;
    private double mDWNominalPower;
    private String mDWType = "Dishwasher";
    private DishwasherSpec mDWDeviceSpecs;
    private boolean mDWActive;
    private ProgramList mDWProgramList;
    private LogList mDWLogList;

    public Dishwasher(DishwasherSpec dishwasherSpec) {
        this.mDWDeviceSpecs = dishwasherSpec;
        this.mDWActive = true;
        mDWProgramList = new ProgramList();
        mDWLogList = new LogList();
    }

    public String getName() {
        return this.mDWName;
    }

    public void setName(String name) {
        this.mDWName = name;
    }

    public String getType() {
        return this.mDWType;
    }

    public void setNominalPower(double nominalPower) {
        this.mDWNominalPower = nominalPower;
    }

    public double getNominalPower() {
        return this.mDWNominalPower;
    }

    public boolean isActive() {
        return this.mDWActive;
    }

    public boolean deactivate() {
        if (isActive()) {
            this.mDWActive = false;
            return true;
        } else {
            return false;
        }
    }

    public boolean isProgrammable() {
        return true;
    }

    public ProgramList getProgramList() throws IncompatibleClassChangeError {
        return this.mDWProgramList;
    }

    public String buildDeviceString() {
        return "The device Name is " + this.mDWName + ", and its NominalPower is " + this.mDWNominalPower + " kW.\n";
    }

    /**
     * This method returns the Device LogList.
     *
     * @return Device LogList.
     */
    public LogList getLogList() {
        return mDWLogList;
    }

    /**
     * This method adds a Log to the device LogList, if the Log isn't already in the LogList.
     *
     * @param log - Parameter which will be used to add to the Device LogList.
     * @return true if log was added
     */
    public boolean addLog(Log log) {
        if (!(mDWLogList.getLogList().contains(log)) && this.mDWActive) {
            mDWLogList.getLogList().add(log);
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
        return mDWLogList.countLogsInInterval(initialTime, finalTime);
    }

    public LogList getLogsInInterval(Date startDate, Date endDate) {
        return mDWLogList.getLogsInInterval(startDate, endDate);
    }

    /**
     * This method checks the Logs registered in a periods which are totally contained in the defined interval.
     *
     * @param initialTime - Beginning of the interval
     * @param finalTime   - Ending of the interval
     * @return total consumption within the defined interval
     */
    public double getConsumptionWithinGivenInterval(Date initialTime, Date finalTime) {
        return mDWLogList.getConsumptionWithinGivenInterval(initialTime, finalTime);
    }

    /**
     * Energy consumption = energy consumption of the program (kWh)
     *
     * @param time
     * @return
     */
    public double getEnergyConsumption(float time) {
        return mDWNominalPower * time;
    }


    // WRAPPER METHODS TO DEVICE SPECS
    public List<String> getAttributeNames() {
        return mDWDeviceSpecs.getAttributeNames();
    }

    public Object getAttributeValue(String attributeName) {
        return mDWDeviceSpecs.getAttributeValue(attributeName);
    }

    public boolean setAttributeValue(String attributeName, Object attributeValue) {
        return mDWDeviceSpecs.setAttributeValue(attributeName, attributeValue);
    }

    public Object getAttributeUnit(String attributeName) {
        return mDWDeviceSpecs.getAttributeUnit(attributeName);
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
        return Objects.equals(mDWName, device.getName());
    }

    @Override
    public int hashCode() {
        return 1;
    }


}
