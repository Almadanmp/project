package pt.ipp.isep.dei.project.model;

import pt.ipp.isep.dei.project.model.devicetypes.DeviceType;

public class Device implements Metered {
    private String mName;
    //private Room mParentRoom;
    private double mNominalPower;
    private DeviceSpecs deviceSpecs;
    private DeviceType mDeviceType;

    public Device(){
    }

    //temporary before is gets moved to DeviceSpecs
    public void setmNominalPower(Double nompower) {
        this.mNominalPower = nompower;
    }

    public String getName(){return this.mName;}

    public void setName(String name){this.mName = name;}

    public void setDeviceType (DeviceType deviceType){this.mDeviceType = deviceType;}

    public DeviceType getDeviceType(){return this.mDeviceType;}

    public double getNominalPower(){
        return this.mNominalPower;
    }

}
