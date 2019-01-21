package pt.ipp.isep.dei.project.model.device.devicetypes;

import pt.ipp.isep.dei.project.model.device.DeviceSpecs;
import pt.ipp.isep.dei.project.model.device.Program;
import pt.ipp.isep.dei.project.model.device.ProgramList;
import pt.ipp.isep.dei.project.model.device.Programmable;

import java.util.ArrayList;
import java.util.List;

public class WashingMachine implements DeviceSpecs, Programmable {
    private static final String CAPACITY = "capacity";

    private double mCapacity;
    private ProgramList mObjectProgramList;
    private List<Program> mProgramList;


    public WashingMachine(double capacity) {
        this.mCapacity = capacity;
        mProgramList = new ArrayList<>();
        mProgramList=new ArrayList<>();
    }

    public WashingMachine(double capacity,List<Program> programList) {
        this.mCapacity = capacity;
        mProgramList = programList;
    }

    public WashingMachine(double capacity, ProgramList programList) {
        this.mCapacity = capacity;
        mObjectProgramList = programList;
    }

    public List<Program> getProgramList() {
        return mProgramList;
    }

    public Program getProgramByName(String name) {
        for (Program p: mProgramList){
            if (p.getProgramName().equals(name)) {
                return p;
            }
        }
        return null;
    }

    public boolean addProgram(Program program){
        if (!(mProgramList.contains(program))) {
            mProgramList.add(program);
            return true;
        } else {
            return false;
        }
    }

    public boolean removeProgram(Program program){
        if (mProgramList.contains(program)) {
            mProgramList.remove(program);
            return true;
        } else {
            return false;
        }
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
