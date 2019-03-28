package pt.ipp.isep.dei.project.model.device;

import pt.ipp.isep.dei.project.model.Metered;
import pt.ipp.isep.dei.project.model.device.devicespecs.WaterHeaterSpec;
import pt.ipp.isep.dei.project.model.device.log.Log;
import pt.ipp.isep.dei.project.model.device.log.LogList;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class WaterHeater implements Device, Metered {
    private String name;
    private double nominalPower;
    private WaterHeaterSpec deviceSpecs;
    private boolean active;
    private LogList logList;

    public WaterHeater(WaterHeaterSpec waterHeaterSpec) {
        this.deviceSpecs = waterHeaterSpec;
        this.active = true;
        logList = new LogList();
    }

    //Getters and Setters
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return "WaterHeater";
    }

    public void setNominalPower(double nominalPower) {
        if (nominalPower <= -1) {
            throw new IllegalArgumentException("Invalid nominal power. Number should be positive");
        }
        this.nominalPower = nominalPower;
    }

    public double getNominalPower() {
        return this.nominalPower;
    }


    /**
     * This method shows if the device is activated or
     * deactivated.
     *
     * @return true if the device is active, false otherwise.
     **/
    public boolean isActive() {
        return this.active;
    }

    /**
     * This method deactivates the device.
     *
     * @return true if the device is deactivated, false otherwise.
     **/
    public boolean deactivate() {
        if (isActive()) {
            this.active = false;
            return true;
        } else {
            return false;
        }
    }

    /**
     * This builds a string containing information about the device
     * (device name and nominal power).
     *
     * @return A string with the device's name and nominal power.
     **/
    public String buildString() {
        String result;
        result = "The device Name is " + this.name + ", and its NominalPower is " + this.nominalPower + " kW.\n";
        return result;
    }

    /**
     * This method returns the Device LogList.
     *
     * @return Device LogList.
     */
    public LogList getLogList() {
        return logList;
    }

    /**
     * Method checks if device LogList is empty
     *
     * @return true if LogList is empty, false otherwise
     */
    public boolean isLogListEmpty() {
        return this.logList.isEmpty();
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
     * Method determines the amount of data logs that fall within a given time interval.getDeviceList
     *
     * @param initialTime is the start time of the interval.
     * @param finalTime   is the end time of the interval.
     * @return is the number of valid data logs in the given interval.
     */
    public int countLogsInInterval(Date initialTime, Date finalTime) {
        return logList.countLogsInInterval(initialTime, finalTime);
    }

    /**
     * This method receives an interval and gets the device's logs contained
     * in that time period, returning them in a list.
     *
     * @param startDate the interval's starting date.
     * @param endDate   the interval's end date.
     * @return a LogList with every device log contained in the interval.
     **/
    public LogList getLogsInInterval(Date startDate, Date endDate) {
        return logList.getLogsInInterval(startDate, endDate);
    }

    /**
     * This method checks the Logs registered in a periods which are totally contained in the defined interval.
     *
     * @param initialTime - Beginning of the interval
     * @param finalTime   - Ending of the interval
     * @return total consumption within the defined interval
     */
    public double getConsumptionInInterval(Date initialTime, Date finalTime) {
        return logList.getConsumptionWithinGivenInterval(initialTime, finalTime);
    }

    /**
     * Method to calculate the difference in temperature to be used on getEnergyConsumptionMethod
     *
     * @return Dt -> difference in temperature = hot water temperature – cold water temperature
     */
    double dTQuotient() {
        double coldWaterTemperature = (double) deviceSpecs.getAttributeValue("Cold Water Temperature");
        double hotWaterTemperature = (double) deviceSpecs.getAttributeValue("Hot Water Temperature");
        double dTQuotient = hotWaterTemperature - coldWaterTemperature;
        if (dTQuotient <= -1) {
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
        double volumeOfWaterToHeat = (double) deviceSpecs.getAttributeValue("Volume Of Water To Heat");
        double performanceRatio = (double) deviceSpecs.getAttributeValue("Performance Ratio");
        double dT = dTQuotient();
        double volForMinute = volumeOfWaterToHeat / 1440; //calculate v in liters per minute
        double specificHeatOfWater = 1.163 / 1000;
        return specificHeatOfWater * volForMinute * dT * performanceRatio * time;
    }

    // WRAPPER METHODS TO DEVICE SPECS
    /**
     * This method returns a list of every attributes names.
     *
     * @return list of strings containing all attributes names.
     **/
    public List<String> getAttributeNames() {
        return deviceSpecs.getAttributeNames();
    }

    /**
     * This method receives a string of an attribute name
     * and returns the attribute value correspondent to that name.
     *
     * @param attributeName a string of a class attribute's name.
     * @return attribute value object.
     **/
    public Object getAttributeValue(String attributeName) {
        return deviceSpecs.getAttributeValue(attributeName);
    }

    /**
     * This method receives an attribute name and an object, and sets the value object as a class parameter (which
     * name corresponds to the name given).
     *
     * @param attributeName a string of a class attribute's name.
     * @return true in case the value is set as parameter, false otherwise.
     **/
    public boolean setAttributeValue(String attributeName, Object attributeValue) {
        return deviceSpecs.setAttributeValue(attributeName, attributeValue);
    }

    /**
     * This method receives an attribute name and gets the attribute's with the given name
     * measurement unit.
     *
     * @param attributeName a string of a class attribute's name.
     * @return a string with the attribute's measurement unit.
     **/
    public Object getAttributeUnit(String attributeName) {
        return deviceSpecs.getAttributeUnit(attributeName);
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
        return Objects.equals(name, device.getName());
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
