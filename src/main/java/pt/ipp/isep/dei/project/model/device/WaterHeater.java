package pt.ipp.isep.dei.project.model.device;

import pt.ipp.isep.dei.project.model.Metered;
import pt.ipp.isep.dei.project.model.device.devicespecs.WaterHeaterSpec;

public class WaterHeater extends CommonDevice implements Device, Metered {

    public WaterHeater(WaterHeaterSpec waterHeaterSpec) {
        super(waterHeaterSpec);
    }

    public String getType() {
        return "WaterHeater";
    }

    /**
     * Method to calculate the difference in temperature to be used on getEnergyConsumptionMethod
     *
     * @return Dt -> difference in temperature = hot water temperature – cold water temperature
     */
    double dTQuotient() {
        double coldWaterTemperature = (double) this.getAttributeValue("Cold Water Temperature");
        double hotWaterTemperature = (double) this.getAttributeValue("Hot Water Temperature");
        double dTQuotient = hotWaterTemperature - coldWaterTemperature;
        if (dTQuotient <= -1) {
            return 0;
        }
        return dTQuotient;
    }

    /**
     * Estimate energy consumption for a water heater.
     * It is calculated by the following equation:
     * Energy consumption = C*V*dT*PR (kWh)
     * C - Specific heat of water = 1,163 Wh/kg°C
     * V - Volume of water to heat (water consumption in litres/min)
     * Dt - difference in temperature = hot water temperature – cold water temperature
     * PR - performance ratio (typically 0.9)
     * When the temperature of ColdWater is above the HotWaterTemperature, there will be no energy consumption, so we
     * return 0.
     *
     * @param time time in minutes
     * @return an estimate energy consumption for a water heater
     */
    public double getEnergyConsumption(float time) {
        double volumeOfWaterToHeat = (double) this.getAttributeValue("Volume Of Water To Heat");
        double performanceRatio = (double) this.getAttributeValue("Performance Ratio");
        double dT = dTQuotient();
        double volForMinute = volumeOfWaterToHeat / 1440; //calculate v in liters per minute
        double specificHeatOfWater = 1.163 / 1000;
        return specificHeatOfWater * volForMinute * dT * performanceRatio * time;
    }

}
