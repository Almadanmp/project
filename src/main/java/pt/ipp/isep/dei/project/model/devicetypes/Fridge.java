package pt.ipp.isep.dei.project.model.devicetypes;

import pt.ipp.isep.dei.project.model.DeviceSpecs;
import pt.ipp.isep.dei.project.model.Metered;

import java.util.ArrayList;
import java.util.List;

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

    public double getFreezerCapacity() {
        return this.mFreezerCapacity;
    }

    public void setFreezerCapacity(double freezerCapacity) {
        this.mFreezerCapacity = freezerCapacity;
    }

    public double getRefrigeratorCapacity() {
        return this.mRefrigeratorCapacity;
    }

    public void setRefrigeratorCapacity(double refrigeratorCapacity) {
        this.mRefrigeratorCapacity = refrigeratorCapacity;
    }
    @Override
    public List<String> getAttributeNames() {
        List<String> aux = new ArrayList<>();
        return aux;
    }

    @Override
    public double getAttributeValue(String attributeName) {
        return 0;
    }

    @Override
    public boolean setAttributeValue(String attributeName, double attributeValue) {
        return false;

    }

   /* public List<String> getAttributeNames() {
        List<String> result = new ArrayList<>();
        result.add("nominalPower");
        result.add("freezerCapacity");
        result.add("refrigeratorCapacity");

        return result;
    }


    public double getAttributeValue(String attributeName) {
        switch (attributeName) {
            case "nominalPower":
                return mNominalPower;
            case "freezerCapacity":
                return mFreezerCapacity;
            case "refrigeratorCapacity":
                return mRefrigeratorCapacity;
            default:
                return 0;
        }
    }


    public boolean setAttributeValue(String attributeName, double attributeValue) {
        switch (attributeName) {
            case "nominalPower":
                this.mNominalPower = attributeValue;
                return true;
            case "freezerCapacity":
                this.mFreezerCapacity = attributeValue;
                return true;
            case "refrigeratorCapacity":
                this.mRefrigeratorCapacity = attributeValue;
                return true;
            default:
                return false;
        }
    }*/
}

