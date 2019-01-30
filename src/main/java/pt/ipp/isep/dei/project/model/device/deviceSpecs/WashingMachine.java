package pt.ipp.isep.dei.project.model.device.deviceSpecs;

import pt.ipp.isep.dei.project.model.device.programs.ProgramList;
import pt.ipp.isep.dei.project.model.device.programs.Programmable;

import java.util.ArrayList;
import java.util.List;

public class WashingMachine implements DeviceSpecs, Programmable {
    private static final String CAPACITY = "capacity";
    private static final String PROGRAM_LIST = "programList";

    private double mCapacity;
    private ProgramList mObjectProgramList;
    private String mType = "WashingMachine";


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
        return result;
    }

    public Object getAttributeValue(String attributeName) {
        switch (attributeName) {
            case CAPACITY:
                return mCapacity;
            case PROGRAM_LIST:
                return mObjectProgramList;
            default:
                return 0;
        }
    }

    public Object getAttributeUnit(String attributeName) {
        switch (attributeName) {
            case CAPACITY:
                return "Kg";
            case PROGRAM_LIST:
                return "";
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
