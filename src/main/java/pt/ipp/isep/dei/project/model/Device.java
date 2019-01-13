package pt.ipp.isep.dei.project.model;

import pt.ipp.isep.dei.project.model.devicetypes.DeviceType;

public class Device implements Metered {
    private String mName;
    //private Room mParentRoom;
    private double mNominalPower;
    private DeviceSpecs mDeviceSpecs;

    //Empty constructor for test purposes
    public Device() {
    }

    public Device(String name, double nominalPower, DeviceSpecs deviceSpecs) {
        this.mName = name;
        this.mNominalPower = nominalPower;
        this.mDeviceSpecs = deviceSpecs;
    }

    //temporary before is gets moved to DeviceSpecs
    public void setNominalPower(Double nomPower) {
        this.mNominalPower = nomPower;
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public double getNominalPower() {
        return this.mNominalPower;
    }

    public double getConsumption() {
        return mDeviceSpecs.getConsumption();
    }

    public DeviceType getDeviceType() {
        return mDeviceSpecs.getType();
    }
}
