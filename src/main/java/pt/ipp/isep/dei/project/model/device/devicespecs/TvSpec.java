package pt.ipp.isep.dei.project.model.device.devicespecs;

import java.util.ArrayList;
import java.util.List;

public class TvSpec implements DeviceSpecs {

    public static final String STANDBY_POWER = "Standby Power";

    private double standbyPower;

    public List<String> getAttributeNames() {
        List<String> result = new ArrayList<>();
        result.add(STANDBY_POWER);
        return result;
    }

    /**
     * Method to get an attribute value of the TVSPEC.
     *
     * @param attributeName attribute name form which we want the attribute value
     * @return attribute value
     */
    public Object getAttributeValue(String attributeName) {
        if (attributeName.equals(STANDBY_POWER)) {
            return this.standbyPower;
        }
        return 0;
    }

    /**
     * Method to get the corresponding unit for an attribute
     *
     * @param attributeName attribute name that we want to get the unit from.
     * @return attribute unit
     */
    public Object getAttributeUnit(String attributeName) {
        if (attributeName.equals((STANDBY_POWER))) {
            return "W";
        }
        return 0;
    }

    public boolean setAttributeValue(String attributeName, Object attributeValue) {
        if (attributeName.equals(STANDBY_POWER)) {
            this.standbyPower = (Double) attributeValue;
        }
        return false;
    }

}
