package pt.ipp.isep.dei.project.model.device.devices;

import pt.ipp.isep.dei.project.model.Metered;
import pt.ipp.isep.dei.project.model.device.Log;
import pt.ipp.isep.dei.project.model.device.LogList;
import pt.ipp.isep.dei.project.model.device.devicespecs.WaterHeaterSpec;
import pt.ipp.isep.dei.project.model.device.programs.ProgramList;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class WaterHeater implements Device, Metered {
    private String mWHName;
    private double mWHNominalPower;
    private String mWHType = "WaterHeater";
    private WaterHeaterSpec mWHDeviceSpecs;
    private boolean mWHActive;
    private LogList mWHLogList;


    public WaterHeater(WaterHeaterSpec waterHeaterSpec) {
        this.mWHDeviceSpecs = waterHeaterSpec;
        this.mWHActive = true;
        mWHLogList = new LogList();
    }

    public String getName() {
        return this.mWHName;
    }

    public void setName(String name) {
        this.mWHName = name;
    }

    public String getType() {
        return this.mWHType;
    }

    public void setNominalPower(double nominalPower) {
        this.mWHNominalPower = nominalPower;
    }

    public double getNominalPower() {
        return this.mWHNominalPower;
    }

    public boolean isActive() {
        return this.mWHActive;
    }

    public boolean deactivate() {
        if (isActive()) {
            this.mWHActive = false;
            return true;
        } else {
            return false;
        }
    }

    public boolean isProgrammable() {
        return false;
    }

    public ProgramList getProgramList() throws IncompatibleClassChangeError {
        throw new IncompatibleClassChangeError("ERROR: Unable to get list. Device is not programmable.");
    }

    public String buildDeviceString() {
        return "The device Name is " + this.mWHName + ", and its NominalPower is " + this.mWHNominalPower + " kW.\n";
    }

    /**
     * This method returns the Device LogList.
     *
     * @return Device LogList.
     */
    public LogList getLogList() {
        return mWHLogList;
    }

    /**
     * This method adds a Log to the device LogList, if the Log isn't already in the LogList.
     *
     * @param log - Parameter which will be used to add to the Device LogList.
     * @return true if log was added
     */
    public boolean addLog(Log log) {
        if (!(mWHLogList.getLogList().contains(log)) && this.mWHActive) {
            mWHLogList.getLogList().add(log);
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
        return mWHLogList.countLogsInInterval(initialTime, finalTime);
    }

    public LogList getLogsInInterval(Date startDate, Date endDate) {
        return mWHLogList.getLogsInInterval(startDate, endDate);    }

    /**
     * This method checks the Logs registered in a periods which are totally contained in the defined interval.
     *
     * @param initialTime - Beginning of the interval
     * @param finalTime   - Ending of the interval
     * @return total consumption within the defined interval
     */
    public double getConsumptionWithinGivenInterval(Date initialTime, Date finalTime) {
        return mWHLogList.getConsumptionWithinGivenInterval(initialTime, finalTime);
    }

    /**
     * Energy consumption = energy consumption of the program (kWh)
     *
     * @param time
     * @return
     */
    public double getEnergyConsumption(float time) {
        double coldWaterTemperature = (double) mWHDeviceSpecs.getAttributeValue("Cold Water Temperature");
        double hotWaterTemperature = (double) mWHDeviceSpecs.getAttributeValue("Hot Water Temperature");
        double volumeOfWaterToHeat = (double) mWHDeviceSpecs.getAttributeValue("Volume Of Water To Heat");
        double performanceRatio = (double) mWHDeviceSpecs.getAttributeValue("Performance Ratio");

        if (coldWaterTemperature >= hotWaterTemperature) {
            return -1;
        }

        double dT = hotWaterTemperature - coldWaterTemperature;
        double volForMinute = volumeOfWaterToHeat / 1440; //calculate v in liters per minute
        double specificHeatOfWater = 1.163 / 1000;
        return specificHeatOfWater * volForMinute * dT * performanceRatio * 60;
    }


    // WRAPPER METHODS TO DEVICE SPECS
    public List<String> getAttributeNames() {
        return mWHDeviceSpecs.getAttributeNames();
    }

    public Object getAttributeValue(String attributeName) {
        return mWHDeviceSpecs.getAttributeValue(attributeName);
    }

    public boolean setAttributeValue(String attributeName, Object attributeValue) {
        return mWHDeviceSpecs.setAttributeValue(attributeName, attributeValue);
    }

    public Object getAttributeUnit(String attributeName) {
        return mWHDeviceSpecs.getAttributeUnit(attributeName);
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
        return Objects.equals(mWHName, device.getName());
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
