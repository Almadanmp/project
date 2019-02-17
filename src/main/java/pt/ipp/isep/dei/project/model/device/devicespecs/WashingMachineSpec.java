package pt.ipp.isep.dei.project.model.device.devicespecs;

import java.util.ArrayList;
import java.util.List;

public class WashingMachineSpec implements DeviceSpecs {

    public static final String WM_CAPACITY = "Capacity";

    private Double mWMCapacity;


    public List<String> getAttributeNames() {
        List<String> result = new ArrayList<>();
        result.add(WM_CAPACITY);
        return result;
    }

    public Object getAttributeValue(String attributeName) {
        if (attributeName.equals(WM_CAPACITY)) {
            return mWMCapacity;
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
        if (attributeName == WM_CAPACITY && attributeValue instanceof Double) {
            this.mWMCapacity = (Double) attributeValue;
            return true;
        }
        return false;
    }

}
