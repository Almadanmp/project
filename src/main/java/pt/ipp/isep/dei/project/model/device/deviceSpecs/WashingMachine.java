package pt.ipp.isep.dei.project.model.device.deviceSpecs;

import pt.ipp.isep.dei.project.model.device.programs.ProgramList;
import pt.ipp.isep.dei.project.model.device.programs.Programmable;

import java.util.ArrayList;
import java.util.List;

public class WashingMachine implements DeviceSpecs, Programmable {
    public static final String CAPACITY = "Capacity";
    public static final String PROGRAM_LIST = "programList";
    public static final String NOMINAL_POWER = "nominal power";

    public double mCapacity;
    public ProgramList mObjectProgramList;
    public String mType = "WashingMachine";
    public Double mNominalPower;


    public WashingMachine() {
        mObjectProgramList = new ProgramList();
    }

    public String getType() {
        return mType;
    }

    public ProgramList getProgramList() {
        return mObjectProgramList;
    }

    public double getConsumption() {
        return 0.0; //To be implemented later, not yet specified
    }

    public List<String> getAttributeNames() {
        List<String> result = new ArrayList<>();
        result.add(CAPACITY);
        result.add(NOMINAL_POWER);
        return result;
    }

    public Object getAttributeValue(String attributeName) {
        switch (attributeName) {
            case CAPACITY:
                return mCapacity;
            case PROGRAM_LIST:
                return mObjectProgramList;
            case NOMINAL_POWER:
                return mNominalPower;
            default:
                return 0;
        }
    }

    public Object getAttributeUnit(String attributeName) {
        switch (attributeName) {
            case CAPACITY:
                return "Kg";
            case NOMINAL_POWER:
                return "kW";
            default:
                return false;
        }
    }

    public boolean setAttributeValue(String attributeName, Object attributeValue) {
        switch (attributeName) {
            case CAPACITY:
                if (attributeValue instanceof Double) {
                    this.mCapacity = (Double) attributeValue;
                    return true;
                }
                return false;
            case NOMINAL_POWER:
                if (attributeValue instanceof Double) {
                    this.mNominalPower = (Double) attributeValue;
                    return true;
                }
                return false;
            default:
                return false;
        }
    }
}

