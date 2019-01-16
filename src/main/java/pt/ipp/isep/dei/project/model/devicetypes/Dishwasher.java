package pt.ipp.isep.dei.project.model.devicetypes;

import pt.ipp.isep.dei.project.model.DeviceSpecs;

import java.util.ArrayList;
import java.util.List;

public class Dishwasher implements DeviceSpecs, ProgramList {

    private double mCapacity;
    private List<Program> mProgramList = new ArrayList<>();

    public Dishwasher() {
    }

    public Dishwasher(double capacity) {
        this.mCapacity = capacity;
        mProgramList = new ArrayList<>();
    }

    @Override
    public List<Program> getProgramList() {
        return mProgramList;
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
                } else {
                    return false;
                }
            case "programList":
                if(attributeValue instanceof Program){
                    this.mProgramList.add((Program)attributeValue);
                    return true;
                } else {
                    return false;
                }

            default:
                return false;
        }
    }
}
