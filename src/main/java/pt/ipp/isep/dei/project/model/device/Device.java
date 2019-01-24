package pt.ipp.isep.dei.project.model.device;

import pt.ipp.isep.dei.project.model.Metered;
import pt.ipp.isep.dei.project.model.device.devicetypes.DeviceType;

import java.util.List;
import java.util.Objects;

/**
 * Class that represents device present in a Room.
 */

public class Device implements Metered {
    private String mName;
    private double mNominalPower;
    private DeviceSpecs mDeviceSpecs;

    //Empty constructor for test purposes
    public Device(String name, double nominalPower, DeviceSpecs deviceSpecs) {
        this.mName = name;
        this.mDeviceSpecs = deviceSpecs;
        this.mNominalPower = nominalPower;
    }

    //temporary before is gets moved to DeviceSpecs
    public void setNominalPower(Double nomPower) {
        this.mNominalPower = nomPower;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public double getRoomListNominalPower() {
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
                getRoomListNominalPower() + " kW.\n";
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




