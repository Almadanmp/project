package pt.ipp.isep.dei.project.model.device.devicetypes;

import pt.ipp.isep.dei.project.model.device.DeviceSpecs;
import pt.ipp.isep.dei.project.model.device.Program;
import pt.ipp.isep.dei.project.model.device.ProgramList;
import pt.ipp.isep.dei.project.model.device.Programmable;

import java.util.ArrayList;
import java.util.List;

public class Dishwasher implements DeviceSpecs, Programmable {

    private double mCapacity;
    private List<Program> mProgramList;
    private ProgramList mObjectProgramList;
    private static final String CAP = "capacity";
    private String mCapacityString = CAP;
    private String mProgramListString = "programList";

    public Dishwasher() {
        mProgramList = new ArrayList<>();
    }

    public Dishwasher(double capacity) {
        this.mCapacity = capacity;
        mProgramList = new ArrayList<>();
    }

    public Dishwasher(double capacity, List<Program> programList) {
        this.mCapacity = capacity;
        mProgramList = programList;
    }

    public Dishwasher(double capacity, ProgramList programList) {
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
        result.add(mCapacityString);
        result.add(mProgramListString);
        return result;
    }


    public Object getAttributeValue(String attributeName) {
        if (attributeName.equals(CAP)) {
            return mCapacity;
        } else if ("programList".equals(attributeName)) {
            return mObjectProgramList;
        }
        return 0;
    }

    public boolean setAttributeValue(String attributeName, Object attributeValue) {
        if (attributeName.equals(CAP) && attributeValue instanceof Double) {
            this.mCapacity = (Double) attributeValue;
            return true;
        }
        return false;
    }
}
