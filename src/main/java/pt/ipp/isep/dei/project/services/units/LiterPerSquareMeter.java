package pt.ipp.isep.dei.project.services.units;

import java.io.IOException;

import static pt.ipp.isep.dei.project.services.units.UnitHelper.*;

public class LiterPerSquareMeter implements RainfallUnit {


    /**
     * This method checks the application default rainfall unit and then converts a LiterPerSquareMeter value into the default one.
     *
     * @param valueToConvert refers to the rainfall value.
     * @return value converted into the default Unit.
     * @throws IOException in case the properties file is missing or doesn't have the property.
     */
    public double toApplicationDefault(double valueToConvert) throws IOException {
        String defaultUnit = getApplicationRainfallConfig();
        return toDefaultRainfallUnit(defaultUnit, valueToConvert, this);
    }

    /**
     * This method checks the user default rainfall unit and then converts a LiterPerSquareMeter value into the default one.
     *
     * @param valueToConvert refers to the rainfall value.
     * @return value converted into the default Unit.
     * @throws IOException in case the properties file is missing or doesn't have the property.
     */
    public double toUserDefault(double valueToConvert) throws IOException {
        String defaultUnit = getUserRainfallConfig();
        return toDefaultRainfallUnit(defaultUnit, valueToConvert, this);
    }

    /**
     * This method converts the rainfall value into Millimeter.
     *
     * @param volume refers to the rainfall in LiterPerSquareMeter.
     * @return rainfall value in Millimeter.
     */
    public double toMillimeter(double volume) {
        return volume;
    }

    /**
     * This method converts the rainfall value into LiterPerSquareMeter.
     *
     * @param volume refers to the rainfall in LiterPerSquareMeter.
     * @return rainfall value in LiterPerSquareMeter.
     */
    public double toLiterPerSquareMeter(double volume) {
        return volume;
    }

    /**
     * @return the rainfall unit type as a string
     */
    public String buildString() {
        return "LiterPerSquareMeter";
    }

    @Override
    public boolean equals(Object o) {
        return o != null && getClass() == o.getClass();
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
