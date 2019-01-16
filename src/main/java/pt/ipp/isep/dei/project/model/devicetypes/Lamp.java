package pt.ipp.isep.dei.project.model.devicetypes;

import pt.ipp.isep.dei.project.model.DeviceSpecs;
import pt.ipp.isep.dei.project.model.Metered;

import java.util.ArrayList;
import java.util.List;

public class Lamp implements DeviceSpecs, Metered {
    private double mNominalPower;
    private double mLuminousFlux;

    public Lamp(){}

    public Lamp(double luminousFlux){
        this.mLuminousFlux=luminousFlux;
    }

    public DeviceType getType() {
        return DeviceType.LAMP;
    }

    public double getConsumption() {
        return 0; //To be implemented later, not yet specified
    }

    public double getNominalPower() {
        return this.mNominalPower;
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

}
