package pt.ipp.isep.dei.project.model.device.devicespecs;

import java.util.ArrayList;
import java.util.List;

public class WashingMachineSpec implements DeviceSpecs {

    public static final String WM_CAPACITY = "Capacity";

    private Double wMCapacity;


    public List<String> getAttributeNames() {
        List<String> result = new ArrayList<>();
        result.add(WM_CAPACITY);
        return result;
    }

    public Object getAttributeValue(String attributeName) {
        if (attributeName.equals(WM_CAPACITY)) {
            return wMCapacity;
        }
        return 0;
    }

    public Object getAttributeUnit(String attributeName) {
        if (attributeName.equals(WM_CAPACITY)) {
            return "Kg";

        }
        return false;
    }

    public boolean setAttributeValue(String attributeName, Object attributeValue) {
        if (attributeName == null) {
            return false;
        }
        if (attributeName.equals(WM_CAPACITY) && attributeValue instanceof Double) {
            this.wMCapacity = (Double) attributeValue;
            return true;
        }
        return false;
    }

}
