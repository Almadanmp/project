package pt.ipp.isep.dei.project.model.device;

import pt.ipp.isep.dei.project.model.device.devicespecs.FanSpec;
import pt.ipp.isep.dei.project.model.device.program.ProgramList;

import java.util.List;

public class Fan extends CommonDeviceAttributes implements Device {
    private ProgramList programList;
    private final FanSpec deviceSpecs;

    /**
     * Default constructor for fan devices.
     *
     * @param fanSpec is one deviceSpec specific for fans.
     */

    public Fan(FanSpec fanSpec) {
        super();
        this.deviceSpecs = fanSpec;
        programList = new ProgramList();
    }

    /**
     * Returns a hard coded string that corresponds to the type of device.
     *
     * @return is the device's type.
     */

    public String getType() {
        return "Fan";
    }

    /**
     * Method that returns the list of programs that the device has.
     *
     * @return is the list of programs that the device has.
     */

    public ProgramList getProgramList() {
        return programList;
    }


    /**
     * Method that determines the program list of a given device.
     *
     * @param plist is the program list we want to assign to the device.
     */

    public void setProgramList(ProgramList plist) {
        this.programList = plist;
    }


    // WRAPPER METHODS TO DEVICE SPECS

    /**
     * Method that returns the name of all attributes unique to the device.
     *
     * @return returns an empty list, because the device has no unique attributes.
     */

    public List<String> getAttributeNames() {
        return deviceSpecs.getAttributeNames();
    }

    /**
     * Method that returns the value of a particular unique attribute.
     *
     * @param attributeName is the name of the unique attribute from which we want to get a value.
     * @return throws unsupported operation exception since there are no unique attributes.
     */

    public Object getAttributeValue(String attributeName) {
        throw new UnsupportedOperationException();
    }

    /**
     * Method that determines the value of a particular unique attribute.
     *
     * @param attributeName  is the name of the attribute we want to assign a value to.
     * @param attributeValue is the value we want to assign to the attribute.
     * @return throws unsupported operation exception since there are no unique attributes.
     */

    public boolean setAttributeValue(String attributeName, Object attributeValue) {
        throw new UnsupportedOperationException();
    }

    /**
     * Method that returns a string that corresponds to the unit in which a particular attribute is measured.
     *
     * @param attributeName name of the attribute we want to get the unit from.
     * @return throws unsupported operation exception since there are no unique attributes.
     */

    public Object getAttributeUnit(String attributeName) {
        throw new UnsupportedOperationException();
    }

}
