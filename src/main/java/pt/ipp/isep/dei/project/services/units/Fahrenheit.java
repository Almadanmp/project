package pt.ipp.isep.dei.project.services.units;

import java.io.IOException;

import static pt.ipp.isep.dei.project.services.units.UnitHelper.*;

public class Fahrenheit implements TemperatureUnit {

    public double toApplicationDefault(double valueToConvert) throws IOException {
        String defaultUnit = getApplicationTemperatureDefault();
        return toDefaultTemperatureUnit(defaultUnit, valueToConvert, this);
    }

    public double toUserDefault(double valueToConvert) throws IOException {
        String defaultUnit = getUserTemperatureDefault();
        return toDefaultTemperatureUnit(defaultUnit, valueToConvert, this);
    }

    public double toKelvin(double temperature) {
        return (temperature + 459.67) * 5 / 9;
    }

    public double toFahrenheit(double temperature) {
        return temperature;
    }

    public double toCelsius(double temperature) {
        return (temperature - 32) * 5 / 9;
    }

    public String buildString() {
        return "Fahrenheit";
    }

}
