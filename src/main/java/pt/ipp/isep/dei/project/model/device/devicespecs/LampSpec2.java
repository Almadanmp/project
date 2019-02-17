package pt.ipp.isep.dei.project.model.device.devicespecs;

import java.util.ArrayList;
import java.util.List;

public class LampSpec2 implements DeviceSpecs2 {

    public static final String FLUX = "Luminous Flux";

    private Double mLuminousFlux;


    public List<String> getAttributeNames() {
        List<String> result = new ArrayList<>();
        result.add(FLUX);
        return result;
    }

    public Object getAttributeValue(String attributeName) {
        if (attributeName.equals(FLUX)) {
            return mLuminousFlux;
        }
        return false;
    }

    public Object getAttributeUnit(String attributeName) {
        if (attributeName.equals(FLUX)){
            return "lm";

        }
        return false;
    }

    public boolean setAttributeValue(String attributeName, Object attributeValue) {
        if (attributeName == null) {
            return false;
        }
        if (attributeName == FLUX  && attributeValue instanceof Double) {
            this.mLuminousFlux = (Double) attributeValue;
            return true;
        }
        return false;
    }

}
