package pt.ipp.isep.dei.project.model.device.deviceSpecs;

import java.util.ArrayList;
import java.util.List;

public class Lamp implements DeviceSpecs {
    private static final String FLUX = "Luminous Flux";

    private Double mLuminousFlux;
    private String mType = "Lamp";

    public Lamp() {
    }

    public String getType() {
        return mType;
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

    public Object getAttributeUnit(String attributeName) {
        if (attributeName.equals(FLUX)) {
            return "lm";
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
