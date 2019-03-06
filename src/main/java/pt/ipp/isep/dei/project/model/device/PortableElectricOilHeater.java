package pt.ipp.isep.dei.project.model.device;

import pt.ipp.isep.dei.project.model.Metered;
import pt.ipp.isep.dei.project.model.device.log.Log;
import pt.ipp.isep.dei.project.model.device.log.LogList;

import java.util.Date;
import java.util.List;

public class PortableElectricOilHeater implements Device, Metered {
    private String notSupported = "At the moment, this operation is not supported.";

    public PortableElectricOilHeater() {
        //This class throws UnsupportedOperationException because it has no attributes, atm.
    }

    public String getName() {
        throw new UnsupportedOperationException(notSupported);
    }

    public void setName(String name) {
        throw new UnsupportedOperationException(notSupported);
    }

    public String getType() {
    return "PortableElectricOilHeater";
    }

    public void setNominalPower(double nominalPower) {
        throw new UnsupportedOperationException(notSupported);
    }

    public double getNominalPower() {
        throw new UnsupportedOperationException(notSupported);
    }

    public boolean isActive() {
        throw new UnsupportedOperationException(notSupported);
    }

    public boolean deactivate() {
        throw new UnsupportedOperationException(notSupported);
    }


    public String buildString() {
        throw new UnsupportedOperationException(notSupported);
    }

    /**
     * This method returns the Device LogList.
     *
     * @return Device LogList.
     */
    public LogList getLogList() {
        throw new UnsupportedOperationException(notSupported);
    }

    /**
     * Method checks if device LogList is empty
     *
     * @return true if LogList is empty, false otherwise
     */
    public boolean isLogListEmpty() {
        throw new UnsupportedOperationException(notSupported);
    }

    /**
     * This method adds a Log to the device LogList, if the Log isn't already in the LogList.
     *
     * @param log - Parameter which will be used to add to the Device LogList.
     * @return true if log was added
     */
    public boolean addLog(Log log) {
        throw new UnsupportedOperationException(notSupported);
    }

    /**
     * Method determines the amount of data logs that fall within a given time interval.
     *
     * @param initialTime is the start time of the interval.
     * @param finalTime   is the end time of the interval.
     * @return is the number of valid data logs in the given interval.
     */
    public int countLogsInInterval(Date initialTime, Date finalTime) {
        throw new UnsupportedOperationException(notSupported);
    }

    public LogList getLogsInInterval(Date startDate, Date endDate) {
        throw new UnsupportedOperationException(notSupported);
    }

    /**
     * This method checks the Logs registered in a periods which are totally contained in the defined interval.
     *
     * @param initialTime - Beginning of the interval
     * @param finalTime   - Ending of the interval
     * @return total consumption within the defined interval
     */
    public double getConsumptionInInterval(Date initialTime, Date finalTime) {
        throw new UnsupportedOperationException(notSupported);
    }

    /**
     * Energy consumption = Nominal power * time (Wh)
     *
     * @param time the desired time
     * @return the energy consumed in the given time
     */
    public double getEnergyConsumption(float time) {
        throw new UnsupportedOperationException(notSupported);
    }


    // WRAPPER METHODS TO DEVICE SPECS
    public List<String> getAttributeNames() {
        throw new UnsupportedOperationException(notSupported);
    }

    public Object getAttributeValue(String attributeName) {
        throw new UnsupportedOperationException(notSupported);
    }

    public boolean setAttributeValue(String attributeName, Object attributeValue) {
        throw new UnsupportedOperationException(notSupported);
    }

    public Object getAttributeUnit(String attributeName) {
        throw new UnsupportedOperationException(notSupported);
    }
}
