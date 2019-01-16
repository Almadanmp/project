package pt.ipp.isep.dei.project.model.devicetypes;

import pt.ipp.isep.dei.project.model.DeviceSpecs;
import pt.ipp.isep.dei.project.model.Metered;

import java.util.ArrayList;
import java.util.List;

public class WashingMachine implements DeviceSpecs {

    private double mCapacity;

    public WashingMachine() {
    }

    public WashingMachine(double capacity) {
        this.mCapacity = capacity;
    }

    public DeviceType getType() {

        return DeviceType.WASHING_MACHINE;
    }

    public double getConsumption() {
        return 0; //To be implemented later, not yet specified
    }


    public double getCapacity() {
        return this.mCapacity;
    }

    public void setCapacity(double capacity) {
        this.mCapacity = capacity;
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

    /*public List<String> getAttributeNames() {
        List<String> result = new ArrayList<>();
        result.add("nominalPower");
        result.add("capacity");

        return result;
    }


    public double getAttributeValue(String attributeName) {
        switch (attributeName) {
            case "nominalPower":
                return mNominalPower;
            case "capacity":
                return mCapacity;
            default:
                return 0;
        }
    }


    public boolean setAttributeValue(String attributeName, double attributeValue) {
        switch (attributeName) {
            case "nominalPower":
                this.mNominalPower = attributeValue;
                return true;
            case "capacity":
                this.mCapacity = attributeValue;
                return true;
            default:
                return false;
        }
    }*/
}
