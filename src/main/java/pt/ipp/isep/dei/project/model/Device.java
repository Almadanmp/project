package pt.ipp.isep.dei.project.model;

import pt.ipp.isep.dei.project.model.devicetypes.DeviceType;

import java.util.Objects;

public class Device implements Metered {
    private String mName;
    //private Room mParentRoom;
    private Room mParentRoom;
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

    public void setName(String name){this.mName = name;}


    public double getNominalPower(){
        return this.mNominalPower;
    }

    public void setmName(String name){
        this.mName = name;
    }

    public String getName(){return this.mName;}

    public void setmParentRoom(Room room){this.mParentRoom=room;}

    public Room getmParentRoom(){return this.mParentRoom;}


    public String printDevice(){
        String result;
        result = "The Device Name is " + this.mName + ", which is in the Room " + this.mParentRoom.getRoomName() + ", and its NominalPower is " +
                getNominalPower() + "\n";
        return result;
    }
    public double getConsumption() {
        return mDeviceSpecs.getConsumption();
    }

    public DeviceType getDeviceType() {
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




