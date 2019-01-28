package pt.ipp.isep.dei.project.model.device;

import pt.ipp.isep.dei.project.model.Metered;
import pt.ipp.isep.dei.project.model.device.programs.ProgramList;
import pt.ipp.isep.dei.project.model.device.programs.Programmable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

/**
 * Class that represents device present in a Room.
 */

public class Device implements Metered {
    private String mName;
    private double mNominalPower;
    private DeviceSpecs mDeviceSpecs;
    private LogList mLogList;
    private int mMeteringPeriod;

    /**
     * Constructor with path by configuration file approach
     *
     * @param name
     * @param nominalPower
     * @param deviceTypePath
     * @throws IllegalArgumentException
     */
    public Device(String name, double nominalPower, String deviceTypePath) throws IllegalArgumentException {
        this.mName = name;
        this.mNominalPower = nominalPower;
        this.mLogList = new LogList();
        DeviceSpecs aux;
        try {
            aux = (DeviceSpecs) Class.forName(deviceTypePath).newInstance();
        } catch (Exception e) {
            throw new IllegalArgumentException("ERROR: Unable to create device type from path - " + e.getMessage());
        }

        this.mDeviceSpecs = aux;

        if (!setMeteringPeriod()) {
            throw new IllegalArgumentException("Configuration file values are not supported.");
        }
    }

    public double getNominalPower() {
        return this.mNominalPower;
    }

    public void setNominalPower(Double nomPower) {
        this.mNominalPower = nomPower;
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
    public double getDailyEstimateConsumption() {
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

    public int getMeteringPeriod() {
        return this.mMeteringPeriod;
    }


    public boolean setMeteringPeriod() {
        String GridMeteringPeriod;
        String DeviceMeteringPeriod;
        Properties prop = new Properties();
        try {
            FileInputStream input = new FileInputStream("resources/meteringPeriods.properties");
            prop.load(input);
            DeviceMeteringPeriod = prop.getProperty("DevicesMeteringPeriod");
            GridMeteringPeriod = prop.getProperty("GridMeteringPeriod");
        } catch (FileNotFoundException fnfe) {
            System.out.println("File not found.");
            return false;
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return false;
        }
        Integer deviceMPValue = (Integer) Integer.parseInt(DeviceMeteringPeriod);
        Integer gridMPValue = (Integer) Integer.parseInt(GridMeteringPeriod);
        if (deviceMeteringPeriodValidation(deviceMPValue, gridMPValue)) {
            this.mMeteringPeriod = deviceMPValue;
            return true;
        }
        System.out.println("Configuration file values are not supported.");
        return false;
    }

    public boolean deviceMeteringPeriodValidation(int deviceValue, int gridValue) {
        if (1440 % deviceValue != 0) {
            return false;
        } else if (deviceValue % gridValue != 0) {
            return false;
        }
        return true;
    }

    public LogList getLogList() {
        return this.mLogList;
    }

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

    public int meteringPeriodOcurrences(Date initialTime, Date finalTime) {
        int counter = 0;
        for (Log l : mLogList.getLogList()) {
            if ((initialTime.before(l.getInitialDate()) || initialTime.equals(l.getInitialDate())) &&
                    (finalTime.after(l.getFinalDate()) || finalTime.equals(l.getFinalDate()))) {
                counter++;
            }
        }
        return counter;
    }

    public boolean addLogToLogList(Log log) {
        if (!(mLogList.getLogList().contains(log))) {
            mLogList.getLogList().add(log);
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




