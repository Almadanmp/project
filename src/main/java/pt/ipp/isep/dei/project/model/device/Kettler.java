package pt.ipp.isep.dei.project.model.device;

import pt.ipp.isep.dei.project.model.Metered;
import pt.ipp.isep.dei.project.model.device.devicespecs.KettlerSpec;

import java.util.List;
import java.util.Objects;

public class Kettler extends CommonDeviceAttributes implements Device, Metered {

    private final KettlerSpec kettlerSpec;

    public Kettler(KettlerSpec kettlerSpec) {
        super();
        this.kettlerSpec = kettlerSpec;
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
        double heatingVolume = (double) this.kettlerSpec.getAttributeValue(KettlerSpec.VOLUME_WATER);
        double pRatio = (double) this.kettlerSpec.getAttributeValue(KettlerSpec.PERFORMANCE_RATIO);

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
        double coldWaterTemperature = (double) this.kettlerSpec.getAttributeValue(KettlerSpec.COLD_WATER_TEMP);

        if (coldWaterTemperature > 99.0) {
            return 0;
        }
        return 100.0 - coldWaterTemperature;
    }

    /**
     * This method returns a list of every attributes names.
     *
     * @return list of strings containing all attributes names.
     **/
    @Override
    public List<String> getAttributeNames() {
        return this.kettlerSpec.getAttributeNames();
    }

    /**
     * This method receives a string of an attribute name
     * and returns the attribute value correspondent to that name.
     *
     * @param attributeName a string of a class attribute's name.
     * @return attribute value object.
     **/
    @Override
    public Object getAttributeValue(String attributeName) {
        return this.kettlerSpec.getAttributeValue(attributeName);
    }

    /**
     * This method receives an attribute name and an object,
     * and sets the value object as a class parameter (which name corresponds to
     * the name given).
     *
     * @param attributeName a string of a class attribute's name.
     * @return true in case the value is set as parameter, false otherwise.
     **/
    @Override
    public boolean setAttributeValue(String attributeName, Object attributeValue) {
        return this.kettlerSpec.setAttributeValue(attributeName, attributeValue);
    }

    /**
     * This method receives an attribute name and gets the attribute's with the given name
     * measurement unit.
     *
     * @param attributeName a string of a class attribute's name.
     * @return a string with the attribute's measurement unit.
     **/
    @Override
    public Object getAttributeUnit(String attributeName) {
        return this.kettlerSpec.getAttributeUnit(attributeName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Kettler device = (Kettler) o;
        return Objects.equals(this.getName(), device.getName());
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
