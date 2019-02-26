package pt.ipp.isep.dei.project.model.device.devicespecs;

import java.util.ArrayList;
import java.util.List;

public class WineCoolerSpec implements DeviceSpecs {

    public static final String NUMBER_BOTTLES = "Number Bottles";

    private Integer numberBottles;


    public List<String> getAttributeNames() {
        List<String> result = new ArrayList<>();
        result.add(NUMBER_BOTTLES);
        return result;
    }

    public Object getAttributeValue(String attributeName) {
        if (attributeName.equals(NUMBER_BOTTLES)) {
            return numberBottles;
        }
        return false;
    }

    public Object getAttributeUnit(String attributeName) {
        if (attributeName.equals(NUMBER_BOTTLES)) {
            return "bottles";

        }
        return false;
    }

    public boolean setAttributeValue(String attributeName, Object attributeValue) {
        if (attributeName == null) {
            return false;
        }
        if (attributeName.equals(NUMBER_BOTTLES) && attributeValue instanceof Integer) {
            this.numberBottles = (Integer) attributeValue;
            return true;
        }
        return false;
    }

}