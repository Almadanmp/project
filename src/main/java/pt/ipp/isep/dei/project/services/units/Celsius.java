package pt.ipp.isep.dei.project.services.units;

import java.io.IOException;

import static pt.ipp.isep.dei.project.services.units.UnitHelper.*;

public class Celsius implements TemperatureUnit {

    public double toApplicationDefault(double valueToConvert) throws IOException {
        String defaultUnit = getApplicationTemperatureDefault();
        return toDefaultTemperatureUnit(defaultUnit, valueToConvert, this);
    }

    public double toUserDefault(double valueToConvert) throws IOException {
        String defaultUnit = getUserTemperatureDefault();
        return toDefaultTemperatureUnit(defaultUnit, valueToConvert, this);
    }

    public double toKelvin(double temperature) {
        return temperature + 273.15;
    }

    public double toFahrenheit(double temperature) {
        return temperature * 9 / 5 + 32;
    }

    public double toCelsius(double temperature) {
        return temperature;
    }

    public String buildString() {
        return "Celsius";
    }

}
