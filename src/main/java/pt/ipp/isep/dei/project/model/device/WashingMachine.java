package pt.ipp.isep.dei.project.model.device;

import pt.ipp.isep.dei.project.model.Metered;
import pt.ipp.isep.dei.project.model.device.log.Log;
import pt.ipp.isep.dei.project.model.device.log.LogList;
import pt.ipp.isep.dei.project.model.device.devicespecs.WashingMachineSpec;
import pt.ipp.isep.dei.project.model.device.program.ProgramList;
import pt.ipp.isep.dei.project.model.device.program.Programmable;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class WashingMachine implements Device, Metered, Programmable {
    private String mWMName;
    private double mWMNominalPower;
    private String mWMType = "Washing Machine";
    private WashingMachineSpec mWMDeviceSpecs;
    private boolean mWMActive;
    private ProgramList mWMProgramList;
    private LogList mWMLogList;


    public WashingMachine(WashingMachineSpec washingMachineSpec) {
        this.mWMDeviceSpecs = washingMachineSpec;
        this.mWMActive = true;
        mWMProgramList = new ProgramList();
        mWMLogList = new LogList();
    }

    public String getName() {
        return this.mWMName;
    }

    public void setName(String name) {
        this.mWMName = name;
    }

    public String getType() {
        return this.mWMType;
    }

    public void setNominalPower(double nominalPower) {
        this.mWMNominalPower = nominalPower;
    }

    public double getNominalPower() {
        return this.mWMNominalPower;
    }

    public boolean isActive() {
        return this.mWMActive;
    }

    public boolean deactivate() {
        if (isActive()) {
            this.mWMActive = false;
            return true;
        } else {
            return false;
        }
    }

    public boolean isProgrammable() {
        return true;
    }

    public ProgramList getProgramList() throws IncompatibleClassChangeError {
        return this.mWMProgramList;
    }



    public String buildDeviceString() {
        return "The device Name is " + this.mWMName + ", and its NominalPower is " + this.mWMNominalPower + " kW.\n";
    }

    /**
     * This method returns the Device LogList.
     *
     * @return Device LogList.
     */
    public LogList getLogList() {
        return mWMLogList;
    }

    /**
     * This method adds a Log to the device LogList, if the Log isn't already in the LogList.
     *
     * @param log - Parameter which will be used to add to the Device LogList.
     * @return true if log was added
     */
    public boolean addLog(Log log) {
        if (!(mWMLogList.getLogList().contains(log)) && this.mWMActive) {
            mWMLogList.getLogList().add(log);
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
        return mWMLogList.countLogsInInterval(initialTime, finalTime);
    }

    public LogList getLogsInInterval(Date startDate, Date endDate) {
        return mWMLogList.getLogsInInterval(startDate, endDate);
    }

    /**
     * This method checks the Logs registered in a periods which are totally contained in the defined interval.
     *
     * @param initialTime - Beginning of the interval
     * @param finalTime   - Ending of the interval
     * @return total consumption within the defined interval
     */
    public double getConsumptionWithinGivenInterval(Date initialTime, Date finalTime) {
        return mWMLogList.getConsumptionWithinGivenInterval(initialTime, finalTime);
    }

    /**
     * Energy consumption = energy consumption of the program (kWh)
     *
     * @param time
     * @return
     */
    public double getEnergyConsumption(float time) {
        return mWMNominalPower * time;
    }


    // WRAPPER METHODS TO DEVICE SPECS
    public List<String> getAttributeNames() {
        return mWMDeviceSpecs.getAttributeNames();
    }

    public Object getAttributeValue(String attributeName) {
        return mWMDeviceSpecs.getAttributeValue(attributeName);
    }

    public boolean setAttributeValue(String attributeName, Object attributeValue) {
        return mWMDeviceSpecs.setAttributeValue(attributeName, attributeValue);
    }

    public Object getAttributeUnit(String attributeName) {
        return mWMDeviceSpecs.getAttributeUnit(attributeName);
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
        return Objects.equals(mWMName, device.getName());
    }

    @Override
    public int hashCode() {
        return 1;
    }

}
