package pt.ipp.isep.dei.project.services.units;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

class UnitHelper {

    /**
     * This method converts de temperature value into the default one.
     *
     * @param defaultUnit refers to the temperature unit that the value should be converted to.
     * @param valueToConvert refers to the temperature value.
     * @param unit refers to the TemperatureUnit type.
     * @return converted temperature.
     */
    static double toDefaultTemperatureUnit(String defaultUnit, double valueToConvert, TemperatureUnit unit) {
        if (defaultUnit.equals("Celsius")) {
            return unit.toCelsius(valueToConvert);
        } else if (defaultUnit.equals("Fahrenheit")) {
            return unit.toFahrenheit(valueToConvert);
        }
        return unit.toKelvin(valueToConvert);
    }

    /**
     * This method converts de temperature value into the default one.
     *
     * @param defaultUnit refers to the rainfall unit that the value should be converted to.
     * @param valueToConvert refers to the rainfall value.
     * @param unit refers to the RainfallUnit type.
     * @return converted rainfall.
     */
    static double toDefaultRainfallUnit(String defaultUnit, double valueToConvert, RainfallUnit unit) {
        if (defaultUnit.equals("Millimeter")) {
            return unit.toMillimeter(valueToConvert);
        }
        return unit.toLiterPerSquareMeter(valueToConvert);
    }

    /**
     * This method checks the properties file for the application default temperature unit.
     *
     * @return temperature unit as a String.
     * @throws IOException in case the file is not found or it doesn't have the property.
     */
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

    /**
     * This method checks the properties file for the user default temperature unit.
     *
     * @return temperature unit as a String.
     * @throws IOException in case the file is not found or it doesn't have the property.
     */
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

    /**
     * This method checks the properties file for the application default rainfall unit.
     *
     * @return rainfall unit as a String.
     * @throws IOException in case the file is not found or it doesn't have the property.
     */
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

    /**
     * This method checks the properties file for the user default rainfall unit.
     *
     * @return rainfall unit as a String.
     * @throws IOException in case the file is not found or it doesn't have the property.
     */
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

    /**
     * This method checks which type of Unit unitToConvert is and then converts its value into the application default.
     *
     * @param valueToConvert refers to unit value.
     * @param unitToConvert refers to unit type.
     * @return the value converted into the application default unit.
     *
     * @throws IOException in case the unit does not correspond to the Unit in the try.
     */
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

    /**
     * This method checks which type of Unit unitToConvert is and then converts its value into the user default.
     *
     * @param valueToConvert refers to unit value.
     * @param unitToConvert refers to unit type.
     * @return the value converted into the user default unit.
     *
     * @throws IOException in case the unit does not correspond to the Unit in the try.
     */
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
