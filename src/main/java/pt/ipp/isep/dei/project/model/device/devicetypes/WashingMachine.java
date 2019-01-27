package pt.ipp.isep.dei.project.model.device.devicetypes;

import pt.ipp.isep.dei.project.model.device.DeviceSpecs;
import pt.ipp.isep.dei.project.model.device.devicePrograms.ProgramList;
import pt.ipp.isep.dei.project.model.device.devicePrograms.Programmable;

import java.util.ArrayList;
import java.util.List;

public class WashingMachine implements DeviceSpecs, Programmable {
    private static final String CAPACITY = "capacity";

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
        result.add("programList");
        return result;
    }

    public Object getAttributeValue(String attributeName) {
        switch (attributeName) {
            case CAPACITY:
                return mCapacity;
            case "programList":
                return mObjectProgramList;
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
