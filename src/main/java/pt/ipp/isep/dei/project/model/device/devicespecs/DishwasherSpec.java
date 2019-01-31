package pt.ipp.isep.dei.project.model.device.devicespecs;

import pt.ipp.isep.dei.project.model.device.programs.ProgramList;
import pt.ipp.isep.dei.project.model.device.programs.Programmable;

import java.util.ArrayList;
import java.util.List;

public class DishwasherSpec implements DeviceSpecs, Programmable {

    public static final String CAPACITY = "Capacity";
    public static final String PROGRAM_LIST = "programList";
    public static final String NOMINAL_POWER = "nominal power";

    private Double mCapacity;
    private ProgramList mProgramList;
    private String mType = "Dishwasher";
    private Double mNominalPower;

    public DishwasherSpec() {
        this.mProgramList = new ProgramList();
    }

    public String getType() {
        return mType;
    }

    public double getConsumption() {
        return 0.0; //To be implemented later, not yet specified
    }

    public ProgramList getProgramList() {
        return mProgramList;
    }

    public List<String> getAttributeNames() {
        List<String> result = new ArrayList<>();
        result.add(CAPACITY);
        result.add(NOMINAL_POWER);
        return result;
    }

    public Object getAttributeValue(String attributeName) {
        if (attributeName.equals(CAPACITY)) {
            return mCapacity;
        }
        if (attributeName.equals(PROGRAM_LIST)) {
            return mProgramList;
        }
        if (attributeName.equals(NOMINAL_POWER)) {
            return mNominalPower;
        }
        return 0;
    }

    public Object getAttributeUnit(String attributeName) {
        switch (attributeName) {
            case CAPACITY:
                return "Kg";
            case PROGRAM_LIST:
                return " ";
            case NOMINAL_POWER:
                return "kW";
            default:
                return 0;
        }
    }

    public boolean setAttributeValue(String attributeName, Object attributeValue) {
        if (attributeName == null) {
            return false;
        }
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
