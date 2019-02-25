package pt.ipp.isep.dei.project.model.device.devicespecs;

import java.util.ArrayList;
import java.util.List;

public class DishwasherSpec implements DeviceSpecs {

    public static final String DW_CAPACITY = "Capacity";

    private Double dWCapacity;


    public List<String> getAttributeNames() {
        List<String> result = new ArrayList<>();
        result.add(DW_CAPACITY);
        return result;
    }

    public Object getAttributeValue(String attributeName) {
        if (attributeName.equals(DW_CAPACITY)) {
            return dWCapacity;
        }
        return 0;
    }

    public Object getAttributeUnit(String attributeName) {
        if (attributeName.equals(DW_CAPACITY)) {
            return "Kg";

        }
        return 0;
    }

    public boolean setAttributeValue(String attributeName, Object attributeValue) {
        if (attributeName == null) {
            return false;
        }
        if (attributeName.equals(DW_CAPACITY) && attributeValue instanceof Double) {
            this.dWCapacity = (Double) attributeValue;
            return true;
        }
        return false;
    }

}
