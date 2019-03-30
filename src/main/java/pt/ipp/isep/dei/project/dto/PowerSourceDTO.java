package pt.ipp.isep.dei.project.dto;

public class PowerSourceDTO {
    private String name;
    private double maxPowerOutput;
    private double maxEnergyStorage;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setMaxPowerOutput(double maxPowerOutput) {
        this.maxPowerOutput = maxPowerOutput;
    }

    public double getMaxPowerOutput() {
        return maxPowerOutput;
    }

    public void setMaxEnergyStorage(double maxEnergyStorage) {
        this.maxEnergyStorage = maxEnergyStorage;
    }

    public double getMaxEnergyStorage() {
        return maxEnergyStorage;
    }
}
