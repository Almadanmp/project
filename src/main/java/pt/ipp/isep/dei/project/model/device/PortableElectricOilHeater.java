package pt.ipp.isep.dei.project.model.device;

import pt.ipp.isep.dei.project.model.Metered;
import pt.ipp.isep.dei.project.model.device.devicespecs.PortableElectricOilHeaterSpec;
import pt.ipp.isep.dei.project.model.device.log.Log;
import pt.ipp.isep.dei.project.model.device.log.LogList;

import java.util.Date;

public class PortableElectricOilHeater extends CommonDevice implements Device, Metered {
    private static final String NOT_SUPPORTED = "At the moment, this operation is not supported.";

    public PortableElectricOilHeater(PortableElectricOilHeaterSpec portableElectricOilHeaterSpec) {
        super(portableElectricOilHeaterSpec);
    }

    public String getType() {
        return "PortableElectricOilHeater";
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

}
