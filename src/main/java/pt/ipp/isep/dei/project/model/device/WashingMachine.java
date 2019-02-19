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
    private String wMName;
    private double wMNominalPower;
    private WashingMachineSpec wMDeviceSpecs;
    private boolean wMActive;
    private ProgramList wMProgramList;
    private LogList wMLogList;


    public WashingMachine(WashingMachineSpec washingMachineSpec) {
        this.wMDeviceSpecs = washingMachineSpec;
        this.wMActive = true;
        wMProgramList = new ProgramList();
        wMLogList = new LogList();
    }

    public String getName() {
        return this.wMName;
    }

    public void setName(String name) {
        this.wMName = name;
    }

    public String getType() {
        return "Washing Machine";
    }

    public void setNominalPower(double nominalPower) {
        this.wMNominalPower = nominalPower;
    }

    public double getNominalPower() {
        return this.wMNominalPower;
    }

    public boolean isActive() {
        return this.wMActive;
    }

    public boolean deactivate() {
        if (isActive()) {
            this.wMActive = false;
            return true;
        } else {
            return false;
        }
    }

    public boolean isProgrammable() {
        return true;
    }

    public ProgramList getProgramList() throws IncompatibleClassChangeError {
        return this.wMProgramList;
    }



    public String buildDeviceString() {
        return "The device Name is " + this.wMName + ", and its NominalPower is " + this.wMNominalPower + " kW.\n";
    }

    /**
     * This method returns the Device LogList.
     *
     * @return Device LogList.
     */
    public LogList getLogList() {
        return wMLogList;
    }

    /**
     * This method adds a Log to the device LogList, if the Log isn't already in the LogList.
     *
     * @param log - Parameter which will be used to add to the Device LogList.
     * @return true if log was added
     */
    public boolean addLog(Log log) {
        if (!(wMLogList.getLogList().contains(log)) && this.wMActive) {
            wMLogList.getLogList().add(log);
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
        return wMLogList.countLogsInInterval(initialTime, finalTime);
    }

    public LogList getLogsInInterval(Date startDate, Date endDate) {
        return wMLogList.getLogsInInterval(startDate, endDate);
    }

    /**
     * This method checks the Logs registered in a periods which are totally contained in the defined interval.
     *
     * @param initialTime - Beginning of the interval
     * @param finalTime   - Ending of the interval
     * @return total consumption within the defined interval
     */
    public double getConsumptionWithinGivenInterval(Date initialTime, Date finalTime) {
        return wMLogList.getConsumptionWithinGivenInterval(initialTime, finalTime);
    }

    /**
     * Energy consumption = energy consumption of the program (kWh)
     *
     * @param time
     * @return
     */
    public double getEnergyConsumption(float time) {
        return wMNominalPower * time;
    }


    // WRAPPER METHODS TO DEVICE SPECS
    public List<String> getAttributeNames() {
        return wMDeviceSpecs.getAttributeNames();
    }

    public Object getAttributeValue(String attributeName) {
        return wMDeviceSpecs.getAttributeValue(attributeName);
    }

    public boolean setAttributeValue(String attributeName, Object attributeValue) {
        return wMDeviceSpecs.setAttributeValue(attributeName, attributeValue);
    }

    public Object getAttributeUnit(String attributeName) {
        return wMDeviceSpecs.getAttributeUnit(attributeName);
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
        return Objects.equals(wMName, device.getName());
    }

    @Override
    public int hashCode() {
        return 1;
    }

}
