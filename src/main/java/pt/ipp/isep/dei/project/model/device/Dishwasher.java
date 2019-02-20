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
    private String dWName;
    private double dWNominalPower;
    private DishwasherSpec dWDeviceSpecs;
    private boolean dWActive;
    private ProgramList dWProgramList;
    private LogList dWLogList;

    public Dishwasher(DishwasherSpec dishwasherSpec) {
        this.dWDeviceSpecs = dishwasherSpec;
        this.dWActive = true;
        this.dWProgramList = new ProgramList();
        this.dWLogList = new LogList();
    }

    public String getName() {
        return this.dWName;
    }

    public void setName(String name) {
        this.dWName = name;
    }

    public String getType() {
        return "Dishwasher";
    }

    public void setNominalPower(double nominalPower) {
        this.dWNominalPower = nominalPower;
    }

    public double getNominalPower() {
        return this.dWNominalPower;
    }

    public boolean isActive() {
        return this.dWActive;
    }

    public boolean deactivate() {
        if (isActive()) {
            this.dWActive = false;
            return true;
        } else {
            return false;
        }
    }

    public boolean isProgrammable() {
        return true;
    }

    public ProgramList getProgramList() throws IncompatibleClassChangeError {
        return this.dWProgramList;
    }

    public String buildDeviceString() {
        return "The device Name is " + this.dWName + ", and its NominalPower is " + this.dWNominalPower + " kW.\n";
    }

    /**
     * This method returns the Device LogList.
     *
     * @return Device LogList.
     */
    public LogList getLogList() {
        return dWLogList;
    }

    /**
     * Method checks if device LogList is empty
     * @return true if LogList is empty, false otherwise
     * */
    public boolean isLogListEmpty(){
        return this.dWLogList.isEmpty();
    }

    /**
     * This method adds a Log to the device LogList, if the Log isn't already in the LogList.
     *
     * @param log - Parameter which will be used to add to the Device LogList.
     * @return true if log was added
     */
    public boolean addLog(Log log) {
        if (!(dWLogList.getLogListAttribute().contains(log)) && this.dWActive) {
            dWLogList.getLogListAttribute().add(log);
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
        return dWLogList.countLogsInInterval(initialTime, finalTime);
    }

    public LogList getLogsInInterval(Date startDate, Date endDate) {
        return dWLogList.getLogsInInterval(startDate, endDate);
    }

    /**
     * This method checks the Logs registered in a periods which are totally contained in the defined interval.
     *
     * @param initialTime - Beginning of the interval
     * @param finalTime   - Ending of the interval
     * @return total consumption within the defined interval
     */
    public double getConsumptionWithinGivenInterval(Date initialTime, Date finalTime) {
        return dWLogList.getConsumptionWithinGivenInterval(initialTime, finalTime);
    }

    /**
     * Energy consumption = energy consumption of the program (kWh)
     *
     * @param time
     * @return
     */
    public double getEnergyConsumption(float time) {
        return dWNominalPower * time;
    }


    // WRAPPER METHODS TO DEVICE SPECS
    public List<String> getAttributeNames() {
        return dWDeviceSpecs.getAttributeNames();
    }

    public Object getAttributeValue(String attributeName) {
        return dWDeviceSpecs.getAttributeValue(attributeName);
    }

    public boolean setAttributeValue(String attributeName, Object attributeValue) {
        return dWDeviceSpecs.setAttributeValue(attributeName, attributeValue);
    }

    public Object getAttributeUnit(String attributeName) {
        return dWDeviceSpecs.getAttributeUnit(attributeName);
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
        return Objects.equals(dWName, device.getName());
    }

    @Override
    public int hashCode() {
        return 1;
    }


}
