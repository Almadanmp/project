package pt.ipp.isep.dei.project.model.device;

import pt.ipp.isep.dei.project.model.Metered;
import pt.ipp.isep.dei.project.model.device.devicespecs.DeviceSpecs;
import pt.ipp.isep.dei.project.model.device.programs.ProgramList;
import pt.ipp.isep.dei.project.model.device.programs.Programmable;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Class that represents device present in a Room.
 */

public class Device implements Metered {
    private String mName;
    private double mNominalPower;
    private DeviceSpecs mDeviceSpecs;
    private LogList mLogList;
    private boolean mActive;


    //TODO delete nominalpower from constructor and as an attribut after changes are made (moving nominal power to each spec)

    /**
     * Constructor with path by configuration file approach
     *
     * @param name           name
     * @param nominalPower   nominal power
     * @param deviceTypePath device type path
     */
    public Device(String name, double nominalPower, String deviceTypePath) throws IllegalArgumentException {
        this.mName = name;
        this.mNominalPower = nominalPower;
        this.mLogList = new LogList();
        this.mActive = true;
        DeviceSpecs aux;
        try {
            aux = (DeviceSpecs) Class.forName(deviceTypePath).newInstance();
        } catch (Exception e) {
            throw new IllegalArgumentException("ERROR: Unable to create device type from path - " + e.getMessage());
        }

        this.mDeviceSpecs = aux;

    }

    public void setNominalPower(Double nomPower) {
        this.mNominalPower = nomPower;
    }

    /**
     * @param deviceSpecs
     */
    public Device(DeviceSpecs deviceSpecs) {
        this.mDeviceSpecs = deviceSpecs;
    }

    public double getNominalPower() {
        return this.mNominalPower;
    }

    /**
     * Boolean to get Device Status. Either if is Active or Not.
     *
     * @return true if device is active
     */
    private boolean isActive() {
        return this.mActive;
    }

    public void setmName(String name) {
        this.mName = name;
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public List<String> getAttributeNames() {
        return mDeviceSpecs.getAttributeNames();
    }

    public Object getAttributeValue(String attributeName) {
        return mDeviceSpecs.getAttributeValue(attributeName);
    }

    public boolean setAttributeValue(String attributeName, Object attributeValue) {
        return mDeviceSpecs.setAttributeValue(attributeName, attributeValue);
    }

    public Object getAttributeUnit(String attributeName) {
        return mDeviceSpecs.getAttributeUnit(attributeName);
    }

    public String buildDeviceString() {
        String result;
        result = "The device Name is " + this.mName + ", and its NominalPower is " +
                getNominalPower() + " kW.\n";
        return result;
    }

    @Override
    public double getEnergyConsumption(float time) {
        return 0;
    }

    /**
     * get daily estimate consumption on a day (24hours)
     *
     * @return the estimateConsumption/24 hours
     */
    double getDailyEstimateConsumption() {
        return (mDeviceSpecs.getConsumption()) * 24;
    }


    /**
     * //TODO incomplete
     *
     * @return
     */
    public boolean isProgrammable() {
        return mDeviceSpecs instanceof Programmable;
    }

    /**
     * //TODO describe usage of throw
     *
     * @return
     * @throws NullPointerException
     */
    public ProgramList getProgramList() throws NullPointerException {
        if (isProgrammable()) {
            Programmable aux = (Programmable) mDeviceSpecs;
            return aux.getProgramList();
        } else {
            throw new NullPointerException("ERROR: Unable to get list. Device is not programmable.");
        }
    }

    public String getType() {
        return mDeviceSpecs.getType();
    }

    /**
     * This method returns the Device LogList.
     *
     * @return Device LogList.
     */
    public LogList getLogList() {
        return this.mLogList;
    }

    /**
     * This method checks the Logs registered in a periods which are totally contained in the defined interval.
     *
     * @param initialTime - Beginning of the interval
     * @param finalTime   - Ending of the interval
     * @return total consumption within the defined interval
     */

    public double getConsumptionWithinGivenInterval(Date initialTime, Date finalTime) {
        double result = 0;
        for (Log l : mLogList.getLogList()) {
            if (initialTime.before(l.getInitialDate()) || initialTime.equals(l.getInitialDate()) &&
                    finalTime.after(l.getFinalDate()) || finalTime.equals(l.getFinalDate())) {
                result += l.getValue();
            }
        }
        return result;
    }

    /**
     * Method determines the amount of data logs that fall within a given time interval.
     *
     * @param initialTime is the start time of the interval.
     * @param finalTime   is the end time of the interval.
     * @return is the number of valid data logs in the given interval.
     */

    public int countLogsInInterval(Date initialTime, Date finalTime) {
        int counter = 0;
        for (Log l : mLogList.getLogList()) {
            if ((initialTime.before(l.getInitialDate()) || initialTime.equals(l.getInitialDate())) &&
                    (finalTime.after(l.getFinalDate()) || finalTime.equals(l.getFinalDate()))) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * This method adds a Log to the device LogList, if the Log isn't already in the LogList.
     *
     * @param log - Parameter which will be used to add to the Device LogList.
     * @return true if log was added
     */

    public boolean addLog(Log log) {
        if (!(mLogList.getLogList().contains(log)) && this.mActive) {
            mLogList.getLogList().add(log);
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method deactivates the device so it no longer accepts logs.
     *
     * @return true if deactivated
     */
    public boolean deactivate() {
        if (isActive()) {
            this.mActive = false;
            return true;
        } else {
            return false;
        }
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
        return Objects.equals(mName, device.mName);
    }

    @Override
    public int hashCode() {
        return 1;
    }
}




