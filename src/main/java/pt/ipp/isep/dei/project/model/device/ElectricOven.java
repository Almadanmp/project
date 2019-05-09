package pt.ipp.isep.dei.project.model.device;

import pt.ipp.isep.dei.project.model.Metered;
import pt.ipp.isep.dei.project.model.device.devicespecs.ElectricOvenSpec;
import pt.ipp.isep.dei.project.model.device.log.Log;
import pt.ipp.isep.dei.project.model.device.log.LogList;
import pt.ipp.isep.dei.project.model.device.program.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ElectricOven implements Device, Metered, Programmable {
    private String name;
    private double nominalPower;
    private boolean active;
    private LogList logList;
    private final ElectricOvenSpec deviceSpecsElectricOven;
    private ProgramList programListElectricOven;

    public ElectricOven(ElectricOvenSpec electricOvenSpec) {
        this.deviceSpecsElectricOven = electricOvenSpec;
        programListElectricOven = new ProgramList();
        logList = new LogList();
        this.active = true;
    }

    public String getName() {
        return CommonDeviceAttributes.getName(this.name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return "Electric Oven";
    }

    public void setNominalPower(double nominalPower) {
        this.nominalPower = nominalPower;
    }

    public double getNominalPower() {
        return CommonDeviceAttributes.getNominalPower(this.nominalPower);
    }

    public boolean isActive() {
        return CommonDeviceAttributes.isActive(this.active);
    }

    public boolean deactivate() {
        if (isActive()) {
            this.active = false;
            return true;
        } else {
            return false;
        }
    }

    public ProgramList getProgramList() throws IncompatibleClassChangeError {
        return this.programListElectricOven;
    }

    public void setProgramList(ProgramList plist) {
        this.programListElectricOven = plist;
    }


    public String buildString() {
        String result;
        result = "The device Name is " + this.name + ", and its NominalPower is " + getNominalPower() + " kW.\n";
        return result;
    }

    /**
     * This method returns the Device LogList.
     *
     * @return Device LogList.
     */
    public LogList getLogList() {
        return CommonDeviceAttributes.getLogList(this.logList);
    }

    /**
     * Method checks if device LogList is empty
     *
     * @return true if LogList is empty, false otherwise
     */
    public boolean isLogListEmpty() {
        return CommonDeviceAttributes.isLogListEmpty(this.logList);
    }

    /**
     * This method adds a Log to the device LogList, if the Log isn't already in the LogList.
     *
     * @param log - Parameter which will be used to addWithoutPersisting to the Device LogList.
     * @return true if log was added
     */
    public boolean addLog(Log log) {
        if (!(logList.contains(log)) && this.active) {
            logList.addLog(log);
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
        return CommonDeviceAttributes.countLogsInInterval(this.logList, initialTime, finalTime);
    }

    public LogList getLogsInInterval(Date startDate, Date endDate) {
        return CommonDeviceAttributes.getLogsInInterval(this.logList, startDate, endDate);
    }

    /**
     * This method checks the Logs registered in a periods which are totally contained in the defined interval.
     *
     * @param initialTime - Beginning of the interval
     * @param finalTime   - Ending of the interval
     * @return total consumption within the defined interval
     */
    public double getConsumptionInInterval(Date initialTime, Date finalTime) {
        return CommonDeviceAttributes.getConsumptionInInterval(this.logList, initialTime, finalTime);
    }

    /**
     * Energy consumption = energy consumption of the program (kWh)
     *
     * @param time the desired time
     * @return the energy consumed in the given time
     */
    public double getEnergyConsumption(float time) {
        return CommonDeviceAttributes.getEnergyConsumption(this.nominalPower, time);
    }

    /**
     * Energy consumption = energy consumption in a program for a defined period of time (kWh)
     *
     * @param time    the desired time
     * @param program the desired program
     * @return the energy consumed in the given time
     */
    double getProgramEnergyConsumption(float time, VariableTimeProgram program) {
        return program.getNominalPower() * time;
    }

    // WRAPPER METHODS TO DEVICE SPECS

    public List<String> getAttributeNames() {
        return deviceSpecsElectricOven.getAttributeNames();
    }

    public Object getAttributeValue(String attributeName) {
        return deviceSpecsElectricOven.getAttributeValue(attributeName);
    }

    public boolean setAttributeValue(String attributeName, Object attributeValue) {
        return deviceSpecsElectricOven.setAttributeValue(attributeName, attributeValue);
    }

    public Object getAttributeUnit(String attributeName) {
        return deviceSpecsElectricOven.getAttributeUnit(attributeName);
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
        return Objects.equals(this.name, device.getName());
    }

    @Override
    public int hashCode() {
        return 1;
    }

}
