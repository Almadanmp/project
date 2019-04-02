package pt.ipp.isep.dei.project.services.units;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

class UnitHelper {

    static double toDefaultTemperatureUnit(String defaultUnit, double valueToConvert, TemperatureUnit unit) {

        if (defaultUnit.equals("Celsius")) {
            return unit.toCelsius(valueToConvert);
        } else if (defaultUnit.equals("Fahrenheit")) {
            return unit.toFahrenheit(valueToConvert);
        }
        return unit.toKelvin(valueToConvert);
    }

    static double toDefaultRainfallUnit(String defaultUnit, double valueToConvert, RainfallUnit unit) {
        if (defaultUnit.equals("Millimeter")) {
            return unit.toLiterPerSquareMeter(valueToConvert);
        }
        return unit.toMillimeter(valueToConvert);
    }

    static String getApplicationTemperatureDefault() throws IOException {
        String temperatureDefault;
        Properties prop = new Properties();
        try (FileInputStream input = new FileInputStream("resources/units.properties")) {
            prop.load(input);
            temperatureDefault = prop.getProperty("defaultApplicationTemperatureUnit");
        } catch (IOException ioe) {
            throw new IOException("ERROR: Unable to process configuration file.");
        }
        return temperatureDefault;
    }

    static String getUserTemperatureDefault() throws IOException {
        String temperatureDefault;
        Properties prop = new Properties();
        try (FileInputStream input = new FileInputStream("resources/units.properties")) {
            prop.load(input);
            temperatureDefault = prop.getProperty("defaultDisplayTemperatureUnit");
        } catch (IOException ioe) {
            throw new IOException("ERROR: Unable to process configuration file.");
        }
        return temperatureDefault;
    }

    static String getApplicationRainfallDefault() throws IOException {
        String rainfallDefault;
        Properties prop = new Properties();
        try (FileInputStream input = new FileInputStream("resources/units.properties")) {
            prop.load(input);
            rainfallDefault = prop.getProperty("defaultApplicationRainfallUnit");
        } catch (IOException ioe) {
            throw new IOException("ERROR: Unable to process configuration file.");
        }
        return rainfallDefault;
    }

    static String getUserRainfallDefault() throws IOException {
        String rainfallDefault;
        Properties prop = new Properties();
        try (FileInputStream input = new FileInputStream("resources/units.properties")) {
            prop.load(input);
            rainfallDefault = prop.getProperty("defaultDisplayRainfallUnit");
        } catch (IOException ioe) {
            throw new IOException("ERROR: Unable to process configuration file.");
        }
        return rainfallDefault;
    }

    static double convertToSystemDefault(double valueToConvert, Unit unitToConvert) throws IOException {
        try {
            TemperatureUnit specificUnit = (TemperatureUnit) unitToConvert;
            return specificUnit.toApplicationDefault(valueToConvert);
        } catch (ClassCastException ok) {
            ok.printStackTrace();
        }
        try {
            RainfallUnit specificUnit = (RainfallUnit) unitToConvert;
            return specificUnit.toApplicationDefault(valueToConvert);
        } catch (ClassCastException ok) {
            ok.printStackTrace();
        }
        return valueToConvert;
    }

    static double convertToUserDefault(double valueToConvert, Unit unitToConvert) throws IOException {
        try {
            TemperatureUnit specificUnit = (TemperatureUnit) unitToConvert;
            return specificUnit.toUserDefault(valueToConvert);
        } catch (ClassCastException ok) {
            ok.printStackTrace();
        }
        try {
            RainfallUnit specificUnit = (RainfallUnit) unitToConvert;
            return specificUnit.toUserDefault(valueToConvert);
        } catch (ClassCastException ok) {
            ok.printStackTrace();
        }
        return valueToConvert;
    }

}
