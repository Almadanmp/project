package pt.ipp.isep.dei.project.model.devicetypes;

import pt.ipp.isep.dei.project.model.DeviceSpecs;
import pt.ipp.isep.dei.project.model.Metered;

public class Dishwasher implements DeviceSpecs, Metered {

    private double mNominalPower;
    private double mCapacity;

    public Dishwasher(){}
    public Dishwasher (double capacity){
        this.mCapacity = capacity;
    }

    void setNominalPower(double nominalPower) { this.mNominalPower = nominalPower;}

    public DeviceType getType() {
        return DeviceType.DISHWASHER;
    }

    public double getConsumption() {
        return 0; //To be implemented later, not yet specified
    }

    public double getNominalPower() { return this.mNominalPower;}

    public double getmCapacity(){return this.mCapacity;}

    public void setmCapacity(double capacity){this.mCapacity=capacity;}

}
