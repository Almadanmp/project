package pt.ipp.isep.dei.project.model.device;

import pt.ipp.isep.dei.project.model.Metered;
import pt.ipp.isep.dei.project.model.device.devicespecs.WaterHeaterSpec;

import java.util.List;
import java.util.Objects;

public class WaterHeater extends CommonDeviceAttributes implements Device, Metered {
    private final WaterHeaterSpec deviceSpecs;

    public WaterHeater(WaterHeaterSpec waterHeaterSpec) {
        super();
        this.deviceSpecs = waterHeaterSpec;
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
        double coldWaterTemperature = (double) deviceSpecs.getAttributeValue("Cold Water Temperature");
        double hotWaterTemperature = (double) deviceSpecs.getAttributeValue("Hot Water Temperature");
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
        double volumeOfWaterToHeat = (double) deviceSpecs.getAttributeValue("Volume Of Water To Heat");
        double performanceRatio = (double) deviceSpecs.getAttributeValue("Performance Ratio");
        double dT = dTQuotient();
        double volForMinute = volumeOfWaterToHeat / 1440; //calculate v in liters per minute
        double specificHeatOfWater = 1.163 / 1000;
        return specificHeatOfWater * volForMinute * dT * performanceRatio * time;
    }

    // WRAPPER METHODS TO DEVICE SPECS

    /**
     * This method returns a list of every attributes names.
     *
     * @return list of strings containing all attributes names.
     **/
    public List<String> getAttributeNames() {
        return deviceSpecs.getAttributeNames();
    }

    /**
     * This method receives a string of an attribute name
     * and returns the attribute value correspondent to that name.
     *
     * @param attributeName a string of a class attribute's name.
     * @return attribute value object.
     **/
    public Object getAttributeValue(String attributeName) {
        return deviceSpecs.getAttributeValue(attributeName);
    }

    /**
     * This method receives an attribute name and an object, and sets the value object as a class parameter (which
     * name corresponds to the name given).
     *
     * @param attributeName a string of a class attribute's name.
     * @return true in case the value is set as parameter, false otherwise.
     **/
    public boolean setAttributeValue(String attributeName, Object attributeValue) {
        return deviceSpecs.setAttributeValue(attributeName, attributeValue);
    }

    /**
     * This method receives an attribute name and gets the attribute's with the given name
     * measurement unit.
     *
     * @param attributeName a string of a class attribute's name.
     * @return a string with the attribute's measurement unit.
     **/
    public Object getAttributeUnit(String attributeName) {
        return deviceSpecs.getAttributeUnit(attributeName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Device device = (Device) o;
        return Objects.equals(this.getName(), device.getName());
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
