package pt.ipp.isep.dei.project.model.device.devicespecs;

import java.util.ArrayList;
import java.util.List;

public class WashingMachineSpec2 implements DeviceSpecs2 {

    public static final String CAPACITY = "Capacity";

    private Double mCapacity;


    public List<String> getAttributeNames() {
        List<String> result = new ArrayList<>();
        result.add(CAPACITY);
        return result;
    }

    public Object getAttributeValue(String attributeName) {
        if (attributeName.equals(CAPACITY)) {
            return mCapacity;
        }
        return 0;
    }

    public Object getAttributeUnit(String attributeName) {
        if (attributeName.equals(CAPACITY)) {
            return "Kg";

        }
        return 0;
    }

    public boolean setAttributeValue(String attributeName, Object attributeValue) {
        if (attributeName == null) {
            return false;
        }
        if (attributeName == CAPACITY  && attributeValue instanceof Double) {
            this.mCapacity = (Double) attributeValue;
            return true;
        }
        return false;
    }

}
