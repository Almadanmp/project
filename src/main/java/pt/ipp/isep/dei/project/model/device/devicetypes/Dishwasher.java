package pt.ipp.isep.dei.project.model.device.devicetypes;

import pt.ipp.isep.dei.project.model.device.DeviceSpecs;
import pt.ipp.isep.dei.project.model.device.ProgramList;

import java.util.ArrayList;
import java.util.List;

public class Dishwasher implements DeviceSpecs {

    private double mCapacity;
    private ProgramList mProgramList;
    private static final String CAP = "capacity";
    private String mCapacityString = CAP;
    private String mProgramListString = "programList";

    public Dishwasher() {
        mProgramList = new ProgramList();
    }

    public Dishwasher(double capacity) {
        this.mCapacity = capacity;
        mProgramList = new ProgramList();
    }

    public Dishwasher(double capacity, ProgramList programList) {
        this.mCapacity = capacity;
        mProgramList = programList;
    }


    public DeviceType getType() {
        return DeviceType.DISHWASHER;
    }

    public double getConsumption() {
        return 0.0; //To be implemented later, not yet specified
    }


    public double getCapacity() {
        return this.mCapacity;
    }

    public void setCapacity(double capacity) {
        this.mCapacity = capacity;
    }

    public List<String> getAttributeNames() {
        List<String> result = new ArrayList<>();
        result.add(mCapacityString);
        result.add(mProgramListString);
        return result;
    }


    public Object getAttributeValue(String attributeName) {
        if (attributeName.equals(CAP)) {
            return mCapacity;
        } else if (attributeName.equals("programList")) {
            return mProgramList;
        }
        return 0;
    }

    public boolean setAttributeValue(String attributeName, Object attributeValue) {
        if (attributeName.equals(CAP) && attributeValue instanceof Double) {
            this.mCapacity = (Double) attributeValue;
            return true;
        }
        return false;
    }
}
