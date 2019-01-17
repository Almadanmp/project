package pt.ipp.isep.dei.project.model.device;

public class Program {
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

    public String buildProgramString(){
        String result;
        result = "- The Program Name is " + getProgramName() + ", its Duration is " +
                getDuration() + " hours and its Energy Consumption is "+getEnergyConsumption()+".\n";
        return result;
    }
    public void setProgramName(String name){
        this.mProgramName=name;
    }

    public void setDuration(double duration){
        this.mDuration=duration;
    }

    public void setEnergyConsumption(double energyConsumption){
        this.mEnergyConsumption=energyConsumption;
    }

    public String getProgramName(){
        return this.mProgramName;
    }

    public double getDuration(){
        return this.mDuration;
    }

    public double getEnergyConsumption(){
        return this.mEnergyConsumption;
    }
}
