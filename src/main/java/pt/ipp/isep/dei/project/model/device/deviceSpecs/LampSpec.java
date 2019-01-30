package pt.ipp.isep.dei.project.model.device.deviceSpecs;

import java.util.ArrayList;
import java.util.List;

public class LampSpec implements DeviceSpecs {
    public static final String FLUX = "Luminous Flux";
    public static final String NOMINAL_POWER = "nominal power";

    private Double mLuminousFlux;
    private String mType = "Lamp";
    private Double mNominalPower;

    public LampSpec() {
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
        switch (attributeName) {
            case FLUX:
                return mLuminousFlux;

            case NOMINAL_POWER:
                return mNominalPower;

            default:
                return false;
        }
    }

    public Object getAttributeUnit(String attributeName) {
        switch (attributeName) {
            case FLUX:
                return "lm";

            case NOMINAL_POWER:
                return "kW";

            default:
                return false;
        }
    }

    public boolean setAttributeValue(String attributeName, Object attributeValue) {
        switch (attributeName) {
            case FLUX:
                if (attributeName.equals(FLUX) && attributeValue instanceof Double) {
                    this.mLuminousFlux = (Double) attributeValue;
                    return true;
                }
                return false;
            case NOMINAL_POWER:
                if (attributeName.equals(NOMINAL_POWER) && attributeValue instanceof Double) {
                    this.mNominalPower = (Double) attributeValue;
                    return true;
                }
                return false;
            default:
                return false;
        }

    }
}
