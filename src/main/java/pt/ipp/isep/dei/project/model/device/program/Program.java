package pt.ipp.isep.dei.project.model.device.program;

import java.util.ArrayList;
import java.util.List;

public class Program {

    public static final String DURATION = "Duration";
    public static final String ENERGY_CONSUMPTION = "Energy Consumption";

    private String mProgramName;
    private double mDuration;
    private double mEnergyConsumption;


    public Program() {
    }

    public Program(String name, double duration, double energyConsumption) {
        setDuration(duration);
        setProgramName(name);
        setEnergyConsumption(energyConsumption);
    }

    public String buildProgramString() {
        String result;
        result = "- The Program Name is " + getProgramName() + ", its Duration is " +
                getDuration() + " hours and its Energy Consumption is " + getEnergyConsumption() + ".\n";
        return result;
    }

    public void setProgramName(String name) {
        this.mProgramName = name;
    }

    public void setDuration(double duration) {
        this.mDuration = duration;
    }

    public void setEnergyConsumption(double energyConsumption) {
        this.mEnergyConsumption = energyConsumption;
    }

    public String getProgramName() {
        return this.mProgramName;
    }

    public double getDuration() {
        return this.mDuration;
    }

    public double getEnergyConsumption() {
        return this.mEnergyConsumption;
    }

    public List<String> getAttributeNames() {
        List<String> result = new ArrayList<>();
        result.add(DURATION);
        result.add(ENERGY_CONSUMPTION);
        return result;
    }

    public boolean setAttributeValue(String attributeName, Object attributeValue) {
        switch (attributeName) {
            case DURATION:
                if (attributeValue instanceof Double) {
                    this.mDuration = (Double) attributeValue;
                    return true;
                }
                return false;
            case ENERGY_CONSUMPTION:
                if (attributeValue instanceof Double) {
                    this.mEnergyConsumption = (Double) attributeValue;
                    return true;
                }
                return false;

            default:
                return false;
        }
    }

    public Object getAttributeUnit(String attributeName) {
        switch (attributeName) {
            case DURATION:
                return "min";
            case ENERGY_CONSUMPTION:
                return "kWh";
            default:
                return false;
        }
    }


    public Object getAttributeValue(String attributeName) {
        switch (attributeName) {
            case DURATION:
                return mDuration;
            case ENERGY_CONSUMPTION:
                return mEnergyConsumption;
            default:
                return 0;
        }
    }

}
