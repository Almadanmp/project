package pt.ipp.isep.dei.project.services.units;

import java.io.IOException;

import static pt.ipp.isep.dei.project.services.units.UnitHelper.*;

public class Kelvin implements TemperatureUnit {


    /**
     * This method checks the application default temperature and then converts a Kelvin value into the default one.
     *
     * @param valueToConvert refers to the temperature value.
     * @return value converted into the default Unit.
     * @throws IOException in case the properties file is missing or doesn't have the property.
     */
    public double toApplicationDefault(double valueToConvert) throws IOException {
        String defaultUnit = getApplicationTemperatureConfig();
        return toDefaultTemperatureUnit(defaultUnit, valueToConvert, this);
    }

    /**
     * This method checks the user default temperature and then converts a Kelvin value into the default one.
     *
     * @param valueToConvert refers to the temperature value.
     * @return value converted into the default Unit.
     * @throws IOException in case the properties file is missing or doesn't have the property.
     */
    public double toUserDefault(double valueToConvert) throws IOException {
        String defaultUnit = getUserTemperatureConfig();
        return toDefaultTemperatureUnit(defaultUnit, valueToConvert, this);
    }

    /**
     * This method converts the temperature value into Kelvin.
     *
     * @param temperature refers to the temperature in Kelvin
     * @return temperature value in Kelvin.
     */
    public double toKelvin(double temperature) {
        return temperature;
    }

    /**
     * This method converts the temperature value into Fahrenheit.
     *
     * @param temperature refers to the temperature in Kelvin
     * @return temperature value in Fahrenheit.
     */
    public double toFahrenheit(double temperature) {
        return temperature * 9/5 - 459.67;
    }

    /**
     * This method converts the temperature value into Celsius.
     *
     * @param temperature refers to the temperature in Kelvin
     * @return temperature value in Celsius.
     */
    public double toCelsius(double temperature) {
        return temperature -273.15;
    }

    /**
     * @return the Temperature Unit type as a string
     */
    public String buildString() {
        return "Kelvin";
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
