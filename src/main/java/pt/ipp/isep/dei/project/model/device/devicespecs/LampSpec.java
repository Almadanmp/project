package pt.ipp.isep.dei.project.model.device.devicespecs;

import java.util.ArrayList;
import java.util.List;

public class LampSpec implements DeviceSpecs {

    public static final String FLUX = "Luminous Flux";

    private Double luminousFlux;


    public List<String> getAttributeNames() {
        List<String> result = new ArrayList<>();
        result.add(FLUX);
        return result;
    }

    public Object getAttributeValue(String attributeName) {
        if (attributeName.equals(FLUX)) {
            return luminousFlux;
        }
        return false;
    }

    public Object getAttributeUnit(String attributeName) {
        if (attributeName.equals(FLUX)) {
            return "lm";

        }
        return false;
    }

    public boolean setAttributeValue(String attributeName, Object attributeValue) {
        if (attributeName == null) {
            return false;
        }
        if (attributeName.equals(FLUX) && attributeValue instanceof Double) {
            this.luminousFlux = (Double) attributeValue;
            return true;
        }
        return false;
    }

}
