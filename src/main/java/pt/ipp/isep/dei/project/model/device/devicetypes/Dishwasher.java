package pt.ipp.isep.dei.project.model.device.devicetypes;

import pt.ipp.isep.dei.project.model.device.DeviceSpecs;
import pt.ipp.isep.dei.project.model.device.programs.ProgramList;
import pt.ipp.isep.dei.project.model.device.programs.Programmable;

import java.util.ArrayList;
import java.util.List;

public class Dishwasher implements DeviceSpecs, Programmable {

    private static final String CAPACITY = "capacity";
    private static final String PROGRAM_LIST = "programList";

    private double mCapacity;
    private ProgramList mProgramList;
    private String mType = "Dishwasher";


    public Dishwasher() {
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
        result.add(PROGRAM_LIST);
        return result;
    }

    public Object getAttributeValue(String attributeName) {
        if (attributeName.equals(CAPACITY)) {
            return mCapacity;
        }
        if (attributeName.equals(PROGRAM_LIST)) {
            return mProgramList;
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
