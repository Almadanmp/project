package pt.ipp.isep.dei.project.model.device;

import pt.ipp.isep.dei.project.model.Metered;
import pt.ipp.isep.dei.project.model.device.devicespecs.PortableElectricConvectionHeaterSpec;
import pt.ipp.isep.dei.project.model.device.log.Log;
import pt.ipp.isep.dei.project.model.device.log.LogList;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class PortableElectricConvectionHeater extends CommonDeviceAttributes implements Device, Metered {
    private static final String NOT_SUPPORTED = "At the moment, this operation is not supported.";

    private final PortableElectricConvectionHeaterSpec deviceSpecs;


    public PortableElectricConvectionHeater(PortableElectricConvectionHeaterSpec portableElectricConvectionHeaterSpec) {
        super();
        this.deviceSpecs = portableElectricConvectionHeaterSpec;
    }


    public String getType() {
        return "PortableElectricConvectionHeater";
    }


    /**
     * This method returns the Device LogList.
     *
     * @return Device LogList.
     */
    @Override
    public LogList getLogList() {
        throw new UnsupportedOperationException(NOT_SUPPORTED);
    }

    /**
     * Method checks if device LogList is empty
     *
     * @return true if LogList is empty, false otherwise
     */
    @Override
    public boolean isLogListEmpty() {
        return true;
    }

    /**
     * This method adds a Log to the device LogList, if the Log isn't already in the LogList.
     *
     * @param log - Parameter which will be used to addWithoutPersisting to the Device LogList.
     * @return true if log was added
     */
    @Override
    public boolean addLog(Log log) {
        throw new UnsupportedOperationException(NOT_SUPPORTED);
    }

    /**
     * Method determines the amount of data logs that fall within a given time interval.
     *
     * @param initialTime is the start time of the interval.
     * @param finalTime   is the end time of the interval.
     * @return is the number of valid data logs in the given interval.
     */
    @Override
    public int countLogsInInterval(Date initialTime, Date finalTime) {
        throw new UnsupportedOperationException(NOT_SUPPORTED);
    }

    @Override
    public LogList getLogsInInterval(Date startDate, Date endDate) {
        throw new UnsupportedOperationException(NOT_SUPPORTED);
    }

    /**
     * This method checks the Logs registered in a periods which are totally contained in the defined interval.
     *
     * @param initialTime - Beginning of the interval
     * @param finalTime   - Ending of the interval
     * @return total consumption within the defined interval
     */
    @Override
    public double getConsumptionInInterval(Date initialTime, Date finalTime) {
        throw new UnsupportedOperationException(NOT_SUPPORTED);
    }

    /**
     * Energy consumption = Nominal power * time (Wh)
     *
     * @param time the desired time
     * @return the energy consumed in the given time
     */
    @Override
    public double getEnergyConsumption(float time) {
        throw new UnsupportedOperationException(NOT_SUPPORTED);
    }


    // WRAPPER METHODS TO DEVICE SPECS
    public List<String> getAttributeNames() {
        return deviceSpecs.getAttributeNames();
    }

    public Object getAttributeValue(String attributeName) {
        return deviceSpecs.getAttributeValue(attributeName);
    }

    public boolean setAttributeValue(String attributeName, Object attributeValue) {
        return deviceSpecs.setAttributeValue(attributeName, attributeValue);
    }

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
        return Objects.equals(this.getName(), device.getName());
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
