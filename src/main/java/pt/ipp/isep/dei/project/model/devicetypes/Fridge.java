package pt.ipp.isep.dei.project.model.devicetypes;

import pt.ipp.isep.dei.project.model.DeviceSpecs;
import pt.ipp.isep.dei.project.model.Metered;

public class Fridge implements DeviceSpecs, Metered {

    private double mNominalPower;
    private double mFreezerCapacity;
    private double mRefrigeratorCapacity;

    public Fridge() {
    }

    public Fridge(double mFreezerCapacity, double mRefrigeratorCapacity) {
        this.mFreezerCapacity = mFreezerCapacity;
        this.mRefrigeratorCapacity = mRefrigeratorCapacity;
    }

    void setNominalPower(double nominalPower) {
        this.mNominalPower = nominalPower;
    }

    public DeviceType getType() {
        return DeviceType.FRIDGE;
    }

    public double getConsumption() {
        return 0; //To be implemented later, not yet specified
    }

    public double getNominalPower() {
        return this.mNominalPower;
    }

    public double getFreezerCapacity (){
        return this.mFreezerCapacity;
    }

    public void setFreezerCapacity(double freezerCapacity){
        this.mFreezerCapacity = freezerCapacity;
    }

    public double getRefrigeratorCapacity (){
        return this.mRefrigeratorCapacity;
    }

    public void setRefrigeratorCapacity(double refrigeratorCapacity){
        this.mRefrigeratorCapacity = refrigeratorCapacity;
    }
}

