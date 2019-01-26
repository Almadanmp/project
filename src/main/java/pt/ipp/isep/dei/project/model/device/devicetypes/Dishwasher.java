package pt.ipp.isep.dei.project.model.device.devicetypes;

import pt.ipp.isep.dei.project.model.device.DeviceSpecs;
import pt.ipp.isep.dei.project.model.device.ProgramList;
import pt.ipp.isep.dei.project.model.device.Programmable;

import java.util.ArrayList;
import java.util.List;

public class Dishwasher implements DeviceSpecs, Programmable {

    private static final String CAPACITY = "capacity";
    private static final String PROGRAM_LIST = "programList";

    private double mCapacity;
    private ProgramList mObjectProgramList;


    public Dishwasher() {
        this.mObjectProgramList = mObjectProgramList;
    }

    public Dishwasher(double capacity, ProgramList programList) {
        this.mCapacity = capacity;
        mObjectProgramList = programList;
    }

    public DeviceType getType() {
        return DeviceType.DISHWASHER;
    }

    public double getConsumption() {
        return 0.0; //To be implemented later, not yet specified
    }

    public ProgramList getProgramList() {
        return mObjectProgramList;
    }

    public double getCapacity() {
        return this.mCapacity;
    }

    public void setCapacity(double capacity) {
        this.mCapacity = capacity;
    }

    public List<String> getAttributeNames() {
        List<String> result = new ArrayList<>();
        result.add(CAPACITY);
        result.add(PROGRAM_LIST);
        return result;
    }

    public Object getAttributeValue(String attributeName) {
        if (attributeName.equals(CAPACITY)) {
            return mCapacity;
        }
        return 0;
    }

    public boolean setAttributeValue(String attributeName, Object attributeValue) {
        if (attributeName.equals(CAPACITY) && attributeValue instanceof Double) {
            this.mCapacity = (Double) attributeValue;
            return true;
        }
        return false;
    }
}
