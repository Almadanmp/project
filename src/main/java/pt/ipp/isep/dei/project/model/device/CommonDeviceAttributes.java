package pt.ipp.isep.dei.project.model.device;

import pt.ipp.isep.dei.project.model.device.log.Log;
import pt.ipp.isep.dei.project.model.device.log.LogList;

import java.util.Date;

public class CommonDeviceAttributes {
    private String name;
    private double nominalPower;
    private boolean active;
    private final LogList logList;

    CommonDeviceAttributes() {
        logList = new LogList();
        this.active = true;
    }

    public static String getName(String name) {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNominalPower(double nominalPower) {
        this.nominalPower = nominalPower;
    }

    public static double getNominalPower(double nominalPower) {
        return nominalPower;
    }

    public static boolean isActive(boolean active) {
        return active;
    }

    public String buildString() {
        return "The device Name is " + this.name + ", and its NominalPower is " + this.nominalPower + " kW.\n";
    }

    /**
     * This method returns the Device LogList.
     *
     * @return Device LogList.
     */
    public static LogList getLogList(LogList logList) {
        return logList;
    }

    /**
     * Method checks if device LogList is empty
     *
     * @return true if LogList is empty, false otherwise
     */
    public static boolean isLogListEmpty(LogList logList) {
        return logList.isEmpty();
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
    public static int countLogsInInterval(LogList logList, Date initialTime, Date finalTime) {
        return logList.countLogsInInterval(initialTime, finalTime);
    }

    public static LogList getLogsInInterval(LogList logList, Date startDate, Date endDate) {
        return logList.getLogsInInterval(startDate, endDate);
    }

    /**
     * This method checks the Logs registered in a periods which are totally contained in the defined interval.
     *
     * @param initialTime - Beginning of the interval
     * @param finalTime   - Ending of the interval
     * @return total consumption within the defined interval
     */
    public static double getConsumptionInInterval(LogList logList, Date initialTime, Date finalTime) {
        return logList.getConsumptionWithinGivenInterval(initialTime, finalTime);
    }

    /**
     * Energy consumption = energy consumption of the program (kWh)
     *
     * @param time the desired time
     * @return the energy consumed in the given time
     */
    public static double getEnergyConsumption(double nominalPower, float time) {
        return nominalPower * time;
    }

}
