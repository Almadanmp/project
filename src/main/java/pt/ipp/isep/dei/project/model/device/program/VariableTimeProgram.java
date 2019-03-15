package pt.ipp.isep.dei.project.model.device.program;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class VariableTimeProgram implements Program {

    public static final String NOMINAL_POWER = "Nominal Power";

    private String programName;

    private double programNominalPower;

    public VariableTimeProgram(String name, double nominalPower) {
        setProgramName(name);
        setNominalPower(nominalPower);
    }

    public void setProgramName(String name) {
        this.programName = name;
    }

    public String getProgramName() {
        return this.programName;
    }

    public void setNominalPower(double nominalPower) {
        this.programNominalPower = nominalPower;
    }

    public double getNominalPower() {
        return this.programNominalPower;
    }

    public String buildString() {
        String result;
        result = "- The FixedTimeProgram Name is " + getProgramName() + ", its Nominal Power is " +
                getNominalPower() + " kW.\n";
        return result;
    }

    public double getEnergyConsumption(double time) {
        return this.programNominalPower * time;
    }

    public List<String> getAttributeNames() {
        List<String> result = new ArrayList<>();
        result.add(NOMINAL_POWER);
        return result;
    }

    public boolean setAttributeValue(String attributeName, Object attributeValue) {
        if (attributeName.equals(NOMINAL_POWER)) {
            if (attributeValue instanceof Double) {
                this.programNominalPower = (Double) attributeValue;
                return true;
            }
            return false;
        }
        return false;

    }

    public Object getAttributeUnit(String attributeName) {
        if (attributeName.equals(NOMINAL_POWER)) {
            return "kW";
        }
        return false;

    }


    public Object getAttributeValue(String attributeName) {
        if (attributeName.equals(NOMINAL_POWER)) {
            return programNominalPower;
        }
        return 0;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        VariableTimeProgram program = (VariableTimeProgram) o;
        return Objects.equals(programName, program.getProgramName());
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
