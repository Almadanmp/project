package pt.ipp.isep.dei.project.model.device.program;

public class VariableProgram implements DeviceProgram {

    public static final String NOMINAL_POWER = "Nominal Power";

    private String programName;

    private double programNominalPower;

    public VariableProgram(String name, double nominalPower) {
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

    public String buildProgramString() {
        String result;
        result = "- The Program Name is " + getProgramName() + ", its Nomimal Power is " +
                getNominalPower() + " kW.\n";
        return result;
    }

}
