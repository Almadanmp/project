package pt.ipp.isep.dei.project.model.device.devicespecs;

import java.util.ArrayList;
import java.util.List;

public class FreezerSpec implements DeviceSpecs {

    public static final String CAPACITY = "Capacity";

    private Double freezerCapacity;


    public List<String> getAttributeNames() {
        List<String> result = new ArrayList<>();
        result.add(CAPACITY);
        return result;
    }

    public Object getAttributeValue(String attributeName) {
        if (attributeName.equals(CAPACITY)) {
            return freezerCapacity;
        }
        return false;
    }

    public Object getAttributeUnit(String attributeName) {
        if (attributeName.equals(CAPACITY)) {
            return "l";

        }
        return false;
    }

    public boolean setAttributeValue(String attributeName, Object attributeValue) {
        if (attributeName == null) {
            return false;
        }
        if (attributeName.equals(CAPACITY) && attributeValue instanceof Double) {
            this.freezerCapacity = (Double) attributeValue;
            return true;
        }
        return false;
    }

}