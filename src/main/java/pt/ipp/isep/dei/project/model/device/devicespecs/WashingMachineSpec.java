package pt.ipp.isep.dei.project.model.device.devicespecs;

import pt.ipp.isep.dei.project.model.device.programs.ProgramList;
import pt.ipp.isep.dei.project.model.device.programs.Programmable;

import java.util.ArrayList;
import java.util.List;

public class WashingMachineSpec implements DeviceSpecs, Programmable {
    public static final String CAPACITY = "Capacity";
    public static final String PROGRAM_LIST = "programList";
    public static final String NOMINAL_POWER = "nominal power";

    private double mCapacity;
    private double mNominalPower;

    private ProgramList mProgramList;
    private String mType = "WashingMachine";


    public WashingMachineSpec() {
        mProgramList = new ProgramList();
    }

    public String getType() {
        return mType;
    }

    public ProgramList getProgramList() {
        return mProgramList;
    }

    public double getConsumption() {
        return 0.0; //To be implemented later, not yet specified
    }

    public List<String> getAttributeNames() {
        List<String> result = new ArrayList<>();
        result.add(NOMINAL_POWER);
        result.add(CAPACITY);
        return result;
    }

    public Object getAttributeValue(String attributeName) {
        switch (attributeName) {
            case NOMINAL_POWER:
                return mNominalPower;
            case CAPACITY:
                return mCapacity;
            case PROGRAM_LIST:
                return mProgramList;

            default:
                return 0;
        }
    }

    public Object getAttributeUnit(String attributeName) {
        switch (attributeName) {
            case NOMINAL_POWER:
                return "kW";
            case CAPACITY:
                return "Kg";
            default:
                return false;
        }
    }

    public boolean setAttributeValue(String attributeName, Object attributeValue) {
        switch (attributeName) {
            case NOMINAL_POWER:
                if (attributeValue instanceof Double) {
                    this.mNominalPower = (Double) attributeValue;
                    return true;
                }
                return false;
            case CAPACITY:
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

