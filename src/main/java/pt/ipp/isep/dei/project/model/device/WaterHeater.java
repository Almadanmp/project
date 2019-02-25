package pt.ipp.isep.dei.project.model.device;

import pt.ipp.isep.dei.project.model.Metered;
import pt.ipp.isep.dei.project.model.device.devicespecs.WaterHeaterSpec;
import pt.ipp.isep.dei.project.model.device.log.Log;
import pt.ipp.isep.dei.project.model.device.log.LogList;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class WaterHeater implements Device, Metered {
    private String wHName;
    private double wHNominalPower;
    private WaterHeaterSpec wHDeviceSpecs;
    private boolean wHActive;
    private LogList wHLogList;

    public WaterHeater(WaterHeaterSpec waterHeaterSpec) {
        this.wHDeviceSpecs = waterHeaterSpec;
        this.wHActive = true;
        wHLogList = new LogList();
    }

    public String getName() {
        return this.wHName;
    }

    public void setName(String name) {
        this.wHName = name;
    }

    public String getType() {
        return "WaterHeater";
    }

    public void setNominalPower(double nominalPower) {
        this.wHNominalPower = nominalPower;
    }

    public double getNominalPower() {
        return this.wHNominalPower;
    }

    public boolean isActive() {
        return this.wHActive;
    }

    public boolean deactivate() {
        if (isActive()) {
            this.wHActive = false;
            return true;
        } else {
            return false;
        }
    }

    public boolean isProgrammable() {
        return false;
    }

    public String buildDeviceString() {
        return "The device Name is " + this.wHName + ", and its NominalPower is " + this.wHNominalPower + " kW.\n";
    }

    /**
     * This method returns the Device LogList.
     *
     * @return Device LogList.
     */
    public LogList getLogList() {
        return wHLogList;
    }

    /**
     * Method checks if device LogList is empty
     *
     * @return true if LogList is empty, false otherwise
     */
    public boolean isLogListEmpty() {
        return this.wHLogList.isEmpty();
    }

    /**
     * This method adds a Log to the device LogList, if the Log isn't already in the LogList.
     *
     * @param log - Parameter which will be used to add to the Device LogList.
     * @return true if log was added
     */
    public boolean addLog(Log log) {
        if (!(wHLogList.getLogListAttribute().contains(log)) && this.wHActive) {
            wHLogList.getLogListAttribute().add(log);
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
        return wHLogList.countLogsInInterval(initialTime, finalTime);
    }

    public LogList getLogsInInterval(Date startDate, Date endDate) {
        return wHLogList.getLogsInInterval(startDate, endDate);
    }

    /**
     * This method checks the Logs registered in a periods which are totally contained in the defined interval.
     *
     * @param initialTime - Beginning of the interval
     * @param finalTime   - Ending of the interval
     * @return total consumption within the defined interval
     */
    public double getConsumptionWithinGivenInterval(Date initialTime, Date finalTime) {
        return wHLogList.getConsumptionWithinGivenInterval(initialTime, finalTime);
    }

    /**
     * Method to calculate the difference in temperature to be used on getEnergyConsumptionMethod
     *
     * @return Dt -> difference in temperature = hot water temperature – cold water temperature
     */
    double dTQuotient() {
        double coldWaterTemperature = (double) wHDeviceSpecs.getAttributeValue("Cold Water Temperature");
        double hotWaterTemperature = (double) wHDeviceSpecs.getAttributeValue("Hot Water Temperature");
        double dTQuotient = hotWaterTemperature - coldWaterTemperature;
        if (dTQuotient <= 0) {
            return 0;
        }
        return dTQuotient;
    }

    /**
     * Estimate energy consumption for a water heater.
     * It is calculated by the following equation:
     * Energy consumption = C*V*dT*PR (kWh)
     * C - Specific heat of water = 1,163 Wh/kg°C
     * V - Volume of water to heat (water consumption in litres/min)
     * Dt - difference in temperature = hot water temperature – cold water temperature
     * PR - performance ratio (typically 0.9)
     * When the temperature of ColdWater is above the HotWaterTemperature, there will be no energy consumption, so we
     * return 0.
     *
     * @param time time in minutes
     * @return an estimate energy consumption for a water heater
     */
    public double getEnergyConsumption(float time) {
        double volumeOfWaterToHeat = (double) wHDeviceSpecs.getAttributeValue("Volume Of Water To Heat");
        double performanceRatio = (double) wHDeviceSpecs.getAttributeValue("Performance Ratio");
        double dT = dTQuotient();
        double volForMinute = volumeOfWaterToHeat / 1440; //calculate v in liters per minute
        double specificHeatOfWater = 1.163 / 1000;
        return specificHeatOfWater * volForMinute * dT * performanceRatio * time;
    }

    // WRAPPER METHODS TO DEVICE SPECS
    public List<String> getAttributeNames() {
        return wHDeviceSpecs.getAttributeNames();
    }

    public Object getAttributeValue(String attributeName) {
        return wHDeviceSpecs.getAttributeValue(attributeName);
    }

    public boolean setAttributeValue(String attributeName, Object attributeValue) {
        return wHDeviceSpecs.setAttributeValue(attributeName, attributeValue);
    }

    public Object getAttributeUnit(String attributeName) {
        return wHDeviceSpecs.getAttributeUnit(attributeName);
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
        return Objects.equals(wHName, device.getName());
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
