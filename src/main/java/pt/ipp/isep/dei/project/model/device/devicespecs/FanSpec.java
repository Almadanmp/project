package pt.ipp.isep.dei.project.model.device.devicespecs;

import java.util.ArrayList;
import java.util.List;

public class FanSpec implements DeviceSpecs {
    private static final String NOT_SUPPORTED = "This operation isn't supported yet.";
    public List<String> getAttributeNames() {
        return new ArrayList<>();
    }

    public Object getAttributeValue(String attributeName) {
        throw new UnsupportedOperationException(NOT_SUPPORTED);
    }

    public Object getAttributeUnit(String attributeName) {
        throw new UnsupportedOperationException(NOT_SUPPORTED);
    }

    public boolean setAttributeValue(String attributeName, Object attributeValue) {
        throw new UnsupportedOperationException(NOT_SUPPORTED);
    }
}
