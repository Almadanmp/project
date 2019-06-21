package pt.ipp.isep.dei.project.model.device;

import pt.ipp.isep.dei.project.model.Metered;
import pt.ipp.isep.dei.project.model.device.devicespecs.KettlerSpec;

public class Kettler extends CommonDevice implements Device, Metered {

    public Kettler(KettlerSpec kettlerSpec) {
        super(kettlerSpec);
    }

    /**
     * This method gets the device's type as a string.
     *
     * @return a string of the device's type.
     **/
    @Override
    public String getType() {
        return "Kettler";
    }


    /**
     * This method provides an estimate of the device's energy consumption.
     *
     * @return a double of the device's estimated energy consumption.
     **/
    @Override
    public double getEnergyConsumption(float time) {
        double specificHeat = 1.163;
        double heatingVolume = (double) this.getAttributeValue(KettlerSpec.VOLUME_WATER);
        double pRatio = (double) this.getAttributeValue(KettlerSpec.PERFORMANCE_RATIO);

        double dT = dTemperature();

        return specificHeat * heatingVolume * dT * pRatio;
    }

    /**
     * This method returns the difference in temperature between the cold water
     * and boiling water temperature. This method is used to calculate the kettler's
     * energy comsuption, so it will return 0 in case the cold water temperature is
     * bigger than boiling water temperature.
     *
     * @return cold water and boiling water temperature difference
     ***/
    double dTemperature() {
        double coldWaterTemperature = (double) this.getAttributeValue(KettlerSpec.COLD_WATER_TEMP);

        if (coldWaterTemperature > 99.0) {
            return 0;
        }
        return 100.0 - coldWaterTemperature;
    }

}
