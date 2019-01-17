package pt.ipp.isep.dei.project.model.Device.devicetypes;

import pt.ipp.isep.dei.project.model.Device.DeviceSpecs;

import java.util.ArrayList;
import java.util.List;

public class Lamp implements DeviceSpecs{
    private double mLuminousFlux;

    public Lamp(){}

    public Lamp(double luminousFlux){
        this.mLuminousFlux=luminousFlux;
    }

    public DeviceType getType() {
        return DeviceType.LAMP;
    }

    public double getConsumption() {
        return 0.0; //To be implemented later, not yet specified
    }


    public List<String> getAttributeNames() {
        List<String> result = new ArrayList<>();
        result.add("LuminousFlux");
        return result;
    }


    public Object getAttributeValue(String attributeName) {
        switch (attributeName) {
            case "LuminousFlux":
                return mLuminousFlux;
            default:
                return 0;
        }
    }

    public boolean setAttributeValue(String attributeName, Object attributeValue) {
        switch (attributeName) {
            case "LuminousFlux":
                if (attributeValue instanceof Double) {
                    this.mLuminousFlux= (Double) attributeValue;
                    return true;
                } else {
                    return false;
                }
            default:
                return false;
        }
    }
}
