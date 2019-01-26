package pt.ipp.isep.dei.project.model.device;

import pt.ipp.isep.dei.project.model.Metered;
import pt.ipp.isep.dei.project.model.device.devicetypes.DeviceType;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * Class that represents device present in a Room.
 */

public class Device implements Metered {
    private String mName;
    private double mNominalPower;
    private DeviceSpecs mDeviceSpecs;
    private LogList mLogList;
    private int mMeteringPeriod;

    //Empty constructor for test purposes
    public Device(String name, double nominalPower, DeviceSpecs deviceSpecs) {
        this.mName = name;
        this.mDeviceSpecs = deviceSpecs;
        this.mNominalPower = nominalPower;
        this.mLogList = new LogList();
    }

    //temporary before is gets moved to DeviceSpecs
    public void setNominalPower(Double nomPower) {
        this.mNominalPower = nomPower;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public double getNominalPower() {
        return this.mNominalPower;
    }

    public void setmName(String name) {
        this.mName = name;
    }

    public String getName() {
        return this.mName;
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
            //return null;
            throw new NullPointerException("ERROR: Unable to get list. Device is not programmable.");
        }
    }

    public DeviceType getType() {
        return mDeviceSpecs.getType();
    }

    public int getMeteringPeriod() {
        return this.mMeteringPeriod;}


    public void setMeteringPeriod(){
        String ValorMetering;
        Properties prop = new Properties();
        try {
            FileInputStream input = new FileInputStream("resources/meteringPeriods.properties");
            prop.load(input);
            ValorMetering = prop.getProperty("DevicesMeteringPeriod");
        } catch (FileNotFoundException fnfe) {
            System.out.println("Ficheiro não encontrado.");
            return;
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return;
        }
        this.mMeteringPeriod = Integer.parseInt(ValorMetering);
    }

    public LogList getLogList() {
        return this.mLogList;
    }

    public double getConsumptionWithinGivenInterval(Date initialTime, Date finalTime){
        double result = 0;
        for (Log l: mLogList.getLogList()) {
            if(initialTime.before(l.getInitialDate()) || initialTime.equals(l.getInitialDate()) && finalTime.after(l.getFinalDate()) || finalTime.equals(l.getFinalDate())){
                result += l.getValue();
            }
        }return result;
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




