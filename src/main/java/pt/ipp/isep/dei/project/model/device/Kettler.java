package pt.ipp.isep.dei.project.model.device;

import pt.ipp.isep.dei.project.model.Metered;
import pt.ipp.isep.dei.project.model.device.devicespecs.KettlerSpec;
import pt.ipp.isep.dei.project.model.device.log.Log;
import pt.ipp.isep.dei.project.model.device.log.LogList;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Kettler implements Device, Metered {

    private String name;
    private double nominalPower;
    private KettlerSpec kettlerSpec;
    private boolean active;
    private LogList logList;

    public Kettler(KettlerSpec kettlerSpec) {
        this.kettlerSpec = kettlerSpec;
        this.active = true;
        this.logList = new LogList();
    }

    /**
     * This method gets the device name as a string.
     *
     * @return a string of the device's name.
     **/
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * This method receives a the device's name and
     * sets it as a parameter.
     * <p>
     * *
     **/
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method gets the device's type as a string.
     *
     * @return a string of the device's type.
     **/
    @Override
    public String getType() {
        return "Kettler";
    }

    /**
     * This method receives the device's nominal power and
     * sets it as a parameter.
     * <p>
     * *
     **/
    @Override
    public void setNominalPower(double nominalPower) {
        int comp = Double.compare(nominalPower, 0.0);
        if (comp >= 0) {
            this.nominalPower = nominalPower;
        }
    }

    /**
     * This method gets the device nominal power.
     *
     * @return a double of the device's nominal power.
     **/
    @Override
    public double getNominalPower() {
        return this.nominalPower;
    }

    /**
     * This method shows if the device is activated or
     * deactivated.
     *
     * @return true if the device is active, false otherwise.
     **/
    @Override
    public boolean isActive() {
        return this.active;
    }

    /**
     * This method deactivates the device.
     *
     * @return true if the device is deactivated, false otherwise.
     **/
    @Override
    public boolean deactivate() {
        if (this.active) {
            this.active = false;
            return true;
        }
        return false;
    }

    /**
     * This builds a string containing information about the device
     * (device name and nominal power).
     *
     * @return A string with the device's name and nominal power.
     **/
    @Override
    public String buildString() {
        return "The device Name is " + this.name + ", and its Nominal Power is " + this.nominalPower + " kW.\n";
    }

    /**
     * This method returns a list containing all the device logs.
     *
     * @return a LogList containing all the device's logs.
     **/
    @Override
    public LogList getLogList() {
        return this.logList;
    }

    /**
     * This method checks if the device log list is empty.
     *
     * @return true in case the device's log list is empty, false otherwise.
     **/
    @Override
    public boolean isLogListEmpty() {
        return this.logList.isEmpty();
    }

    /**
     * This method adds a new log to the device log list.
     *
     * @return true in case log was added to the device's log list, false
     * in case the log already exists.
     **/
    @Override
    public boolean addLog(Log log) {
        if (this.active) {
            return this.logList.addLog(log);
        }
        return false;
    }

    /**
     * This method receives an interval and gets the number of logs contained
     * in that time period.
     *
     * @param startDate the interval's starting date.
     * @param endDate   the interval's end date.
     * @return an int of the number of log's contained in the interval.
     **/
    @Override
    public int countLogsInInterval(Date startDate, Date endDate) {
        return logList.countLogsInInterval(startDate, endDate);
    }

    /**
     * This method receives an interval and gets the device's logs contained
     * in that time period, returning them in a list.
     *
     * @param startDate the interval's starting date.
     * @param endDate   the interval's end date.
     * @return a LogList with every device log contained in the interval.
     **/
    @Override
    public LogList getLogsInInterval(Date startDate, Date endDate) {
        return logList.getLogsInInterval(startDate, endDate);
    }

    /**
     * This method receives an interval and gets the device's energy consumption
     * in that time period.
     *
     * @param startDate the interval's starting date.
     * @param endDate   the interval's end date.
     * @return a double of the device's energy consumption in the interval given.
     **/
    @Override
    public double getConsumptionInInterval(Date startDate, Date endDate) {
        return this.logList.getConsumptionWithinGivenInterval(startDate, endDate);
    }

    /**
     * This method provides an estimate of the device's energy consumption.
     *
     * @return a double of the device's estimated energy consumption.
     **/
    @Override
    public double getEnergyConsumption(float time) {
        double specificHeat = 1.163;
        double heatingVolume = (double) this.kettlerSpec.getAttributeValue(KettlerSpec.VOLUME_WATER);
        double pRatio = (double) this.kettlerSpec.getAttributeValue(KettlerSpec.PERFORMANCE_RATIO);

        double dT = dTemperature();

        return specificHeat * heatingVolume * dT * pRatio;
    }

    /**
     * This method returns the difference in temperature between the cold water
     * and boiling water temperature. This method is used to calculate the kettler's
     * energy comsuption, so it will return 0 in case the cold water temperature is
     * bigger than boiling water temperature.
     *
     * @return cold water and boiling water temperature difference
     ***/
    double dTemperature() {
        double coldWaterTemperature = (double) this.kettlerSpec.getAttributeValue(KettlerSpec.COLD_WATER_TEMP);

        if (coldWaterTemperature > 99.0) {
            return 0;
        }
        return 100.0 - coldWaterTemperature;
    }

    /**
     * This method returns a list of every attributes names.
     *
     * @return list of strings containing all attributes names.
     **/
    @Override
    public List<String> getAttributeNames() {
        return this.kettlerSpec.getAttributeNames();
    }

    /**
     * This method receives a string of an attribute name
     * and returns the attribute value correspondent to that name.
     *
     * @param attributeName a string of a class attribute's name.
     * @return attribute value object.
     **/
    @Override
    public Object getAttributeValue(String attributeName) {
        return this.kettlerSpec.getAttributeValue(attributeName);
    }

    /**
     * This method receives an attribute name and an object,
     * and sets the value object as a class parameter (which name corresponds to
     * the name given).
     *
     * @param attributeName a string of a class attribute's name.
     * @return true in case the value is set as parameter, false otherwise.
     **/
    @Override
    public boolean setAttributeValue(String attributeName, Object attributeValue) {
        return this.kettlerSpec.setAttributeValue(attributeName, attributeValue);
    }

    /**
     * This method receives an attribute name and gets the attribute's with the given name
     * measurement unit.
     *
     * @param attributeName a string of a class attribute's name.
     * @return a string with the attribute's measurement unit.
     **/
    @Override
    public Object getAttributeUnit(String attributeName) {
        return this.kettlerSpec.getAttributeUnit(attributeName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Kettler device = (Kettler) o;
        return Objects.equals(name, device.getName());
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
