package pt.ipp.isep.dei.project.model.device.devicetypes;

import pt.ipp.isep.dei.project.model.device.DeviceSpecs;

import java.util.ArrayList;
import java.util.List;

public class Lamp implements DeviceSpecs {
    private double mLuminousFlux;
    private static final String FLUX = "luminousFlux";

    public Lamp() {
    }

    public Lamp(double luminousFlux) {
        this.mLuminousFlux = luminousFlux;
    }

    public DeviceType getType() {
        return DeviceType.LAMP;
    }

    public double getConsumption() {
        return 0.0; //To be implemented later, not yet specified
    }

    public List<String> getAttributeNames() {
        List<String> result = new ArrayList<>();
        result.add(FLUX);
        return result;
    }

    public Object getAttributeValue(String attributeName) {
        if (attributeName.equals(FLUX)) {
            return mLuminousFlux;
        } else {
            return 0;
        }
    }

    public boolean setAttributeValue(String attributeName, Object attributeValue) {
        if (attributeName.equals(FLUX) && attributeValue instanceof Double) {
            this.mLuminousFlux = (Double) attributeValue;
            return true;
        }
        return false;
    }
}
