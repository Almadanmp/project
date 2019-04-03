package pt.ipp.isep.dei.project.services.units;

import java.io.IOException;

import static pt.ipp.isep.dei.project.services.units.UnitHelper.*;

public class Celsius implements TemperatureUnit {


    /**
     * This method checks the application default temperature and then converts a Celsius value into the default one.
     *
     * @param valueToConvert refers to the temperature value.
     * @return value converted into the default Unit.
     * @throws IOException in case the properties file is missing or doesn't have the property.
     */
    public double toApplicationDefault(double valueToConvert) throws IOException {
        String defaultUnit = getApplicationTemperatureDefault();
        return toDefaultTemperatureUnit(defaultUnit, valueToConvert, this);
    }

    /**
     * This method checks the user default temperature and then converts a Celsius value into the default one.
     *
     * @param valueToConvert refers to the temperature value.
     * @return value converted into the default Unit.
     * @throws IOException in case the properties file is missing or doesn't have the property.
     */
    public double toUserDefault(double valueToConvert) throws IOException {
        String defaultUnit = getUserTemperatureDefault();
        return toDefaultTemperatureUnit(defaultUnit, valueToConvert, this);
    }

    /**
     * This method converts the temperature value into Kelvin.
     *
     * @param temperature refers to the temperature in Celsius
     * @return temperature value in Kelvin.
     */
    public double toKelvin(double temperature) {
        return temperature + 273.15;
    }

    /**
     * This method converts the temperature value into Fahrenheit.
     *
     * @param temperature refers to the temperature in Celsius
     * @return temperature value in Fahrenheit.
     */
    public double toFahrenheit(double temperature) {
        return temperature * 9 / 5 + 32;
    }

    /**
     * This method converts the temperature value into Celsius.
     *
     * @param temperature refers to the temperature in Celsius
     * @return temperature value in Celsius.
     */
    public double toCelsius(double temperature) {
        return temperature;
    }

    /**
     * @return the Temperature Unit type as a string
     */
    public String buildString() {
        return "Celsius";
    }

}
