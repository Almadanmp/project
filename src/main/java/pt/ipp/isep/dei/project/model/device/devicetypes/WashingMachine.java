package pt.ipp.isep.dei.project.model.device.devicetypes;

import pt.ipp.isep.dei.project.model.device.DeviceSpecs;
import pt.ipp.isep.dei.project.model.device.ProgramList;

import java.util.ArrayList;
import java.util.List;

public class WashingMachine implements DeviceSpecs {
    private static final String CAPACITY = "capacity";

    private double mCapacity;
    private ProgramList mProgramList;

    public WashingMachine(double capacity) {
        this.mCapacity = capacity;
        mProgramList = new ProgramList();
    }

    public WashingMachine(double capacity, ProgramList programList) {
        this.mCapacity = capacity;
        mProgramList = programList;
    }

    public DeviceType getType() {
        return DeviceType.WASHING_MACHINE;
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
        result.add(CAPACITY);
        result.add("programList");
        return result;
    }

    public Object getAttributeValue(String attributeName) {
        switch (attributeName) {
            case CAPACITY:
                return mCapacity;
            case "programList":
                return mProgramList;
            default:
                return 0;
        }
    }


    public boolean setAttributeValue(String attributeName, Object attributeValue) {
        if (CAPACITY.equals(attributeName)) {
            if (attributeValue instanceof Double) {
                this.mCapacity = (Double) attributeValue;
                return true;
            }
            return false;
        }
        return false;
    }
}
