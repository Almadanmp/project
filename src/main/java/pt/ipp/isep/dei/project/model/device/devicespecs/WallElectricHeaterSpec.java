package pt.ipp.isep.dei.project.model.device.devicespecs;

import java.util.List;


public class WallElectricHeaterSpec implements DeviceSpecs {

    private String notSupported = "At the moment, this operation is not supported.";
    //This class throws UnsupportedOperationException because it has no attributes, atm.


    public List<String> getAttributeNames() {
        throw new UnsupportedOperationException(notSupported);
    }

    public Object getAttributeValue(String attributeName) {
        throw new UnsupportedOperationException(notSupported);
    }

    public Object getAttributeUnit(String attributeName) {
        throw new UnsupportedOperationException(notSupported);
    }

    public boolean setAttributeValue(String attributeName, Object attributeValue) {
        throw new UnsupportedOperationException(notSupported);
    }
}
