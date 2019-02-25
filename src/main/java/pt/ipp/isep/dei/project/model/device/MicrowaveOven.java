package pt.ipp.isep.dei.project.model.device;

import pt.ipp.isep.dei.project.model.Metered;
import pt.ipp.isep.dei.project.model.device.devicespecs.MicrowaveOvenSpec;
import pt.ipp.isep.dei.project.model.device.log.Log;
import pt.ipp.isep.dei.project.model.device.log.LogList;
import pt.ipp.isep.dei.project.model.device.program.Program;
import pt.ipp.isep.dei.project.model.device.program.ProgramList;
import pt.ipp.isep.dei.project.model.device.program.Programmable;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class MicrowaveOven implements Device, Metered, Programmable {
    private String mOName;
    private double mONominalPower;
    private MicrowaveOvenSpec mODeviceSpecs;
    private boolean mOActive;
    private ProgramList mOProgramList;
    private LogList mOLogList;


    public MicrowaveOven(MicrowaveOvenSpec microwaveOvenSpec) {
        this.mODeviceSpecs = microwaveOvenSpec;
        this.mOActive = true;
        mOProgramList = new ProgramList();
        mOLogList = new LogList();
    }

    public String getName() {
        return this.mOName;
    }

    public void setName(String name) {
        this.mOName = name;
    }

    public String getType() {
        return "Microwave Oven";
    }

    public void setNominalPower(double nominalPower) {
        this.mONominalPower = nominalPower;
    }

    public double getNominalPower() {
        return this.mONominalPower;
    }

    public boolean isActive() {
        return this.mOActive;
    }

    public boolean deactivate() {
        if (isActive()) {
            this.mOActive = false;
            return true;
        } else {
            return false;
        }
    }

    public boolean isProgrammable() {
        return true;
    }

    public ProgramList getProgramList() throws IncompatibleClassChangeError {
        return this.mOProgramList;
    }

    public String buildDeviceString() {
        return "The device Name is " + this.mOName + ", and its NominalPower is " + this.mONominalPower + " kW.\n";
    }

    /**
     * This method returns the Device LogList.
     *
     * @return Device LogList.
     */
    public LogList getLogList() {
        return mOLogList;
    }

    /**
     * Method checks if device LogList is empty
     *
     * @return true if LogList is empty, false otherwise
     */
    public boolean isLogListEmpty() {
        return this.mOLogList.isEmpty();
    }

    /**
     * This method adds a Log to the device LogList, if the Log isn't already in the LogList.
     *
     * @param log - Parameter which will be used to add to the Device LogList.
     * @return true if log was added
     */
    public boolean addLog(Log log) {
        if (!(mOLogList.getLogListAttribute().contains(log)) && this.mOActive) {
            mOLogList.getLogListAttribute().add(log);
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
        return mOLogList.countLogsInInterval(initialTime, finalTime);
    }

    public LogList getLogsInInterval(Date startDate, Date endDate) {
        return mOLogList.getLogsInInterval(startDate, endDate);
    }

    /**
     * This method checks the Logs registered in a periods which are totally contained in the defined interval.
     *
     * @param initialTime - Beginning of the interval
     * @param finalTime   - Ending of the interval
     * @return total consumption within the defined interval
     */
    public double getConsumptionWithinGivenInterval(Date initialTime, Date finalTime) {
        return mOLogList.getConsumptionWithinGivenInterval(initialTime, finalTime);
    }

    /**
     * Energy consumption = energy consumption of the program (kWh)
     *
     * @param time the desired time
     * @return the energy consumed in the given time
     */
    public double getEnergyConsumption(float time) {
        return mONominalPower * time;
    }

    /**
     * Energy consumption = energy consumption in a program for a defined period of time (kWh)
     *
     * @param time the desired time
     * @param program the desired program
     * @return the energy consumed in the given time
     */
    public double getProgramEnergyConsumption(float time, Program program) {
        return program.getNominalPower() * time;
    }


    // WRAPPER METHODS TO DEVICE SPECS

    public List<String> getAttributeNames() {
        return mODeviceSpecs.getAttributeNames();
    }

    public Object getAttributeValue(String attributeName) {
        return mODeviceSpecs.getAttributeValue(attributeName);
    }

    public boolean setAttributeValue(String attributeName, Object attributeValue) {
        return mODeviceSpecs.setAttributeValue(attributeName, attributeValue);
    }

    public Object getAttributeUnit(String attributeName) {
        return mODeviceSpecs.getAttributeUnit(attributeName);
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
        return Objects.equals(mOName, device.getName());
    }

    @Override
    public int hashCode() {
        return 1;
    }

}
