package pt.ipp.isep.dei.project.model.Device.devicetypes;

import pt.ipp.isep.dei.project.model.Device.DeviceSpecs;
import pt.ipp.isep.dei.project.model.Device.ProgramList;

import java.util.ArrayList;
import java.util.List;

public class Dishwasher implements DeviceSpecs {

    private double mCapacity;
    private ProgramList mProgramList;

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
        result.add("capacity");
        result.add("programList");
        return result;
    }


    public Object getAttributeValue(String attributeName) {
        switch (attributeName) {
            case "capacity":
                return mCapacity;
            case "programList":
                return mProgramList;
            default:
                return 0;
        }
    }

    public boolean setAttributeValue(String attributeName, Object attributeValue) {
        switch (attributeName) {
            case "capacity":
                if (attributeValue instanceof Double) {
                    this.mCapacity = (Double) attributeValue;
                    return true;
                }
                    return false;
            default:
                return false;
        }
    }
}
