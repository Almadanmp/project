package PT.IPP.ISEP.DEI.Project.Model;

public class PowerSource {

    private String mPowerSourceType;
    private double mMaxPowerOutput;
    private double mMaxEnergyStorage;
    private boolean mActivePowerSource;

    public PowerSource(String powerSourceType, double maxPowerOutput, double maxEnergyStorage){
        this.mPowerSourceType = powerSourceType;
        this.mMaxPowerOutput = maxPowerOutput;
        this.mMaxEnergyStorage = maxEnergyStorage;
    }

    public void setPowerSourceType(String powerSourceType){this.mPowerSourceType = powerSourceType;}

    public String getPowerSourceType(){return this.mPowerSourceType;}

    public void setMaxPowerOutput(double maxPowerOutput){this.mMaxPowerOutput = maxPowerOutput;}

    public double getMaxPowerOutput(){return this.mMaxPowerOutput;}

    public void setMaxEnergyStorage(double maxEnergyStorage){this.mMaxEnergyStorage = maxEnergyStorage;}

    public double getMaxEnergyStorage (){return this.mMaxEnergyStorage;}

}
