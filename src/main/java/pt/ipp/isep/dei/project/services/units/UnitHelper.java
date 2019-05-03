package pt.ipp.isep.dei.project.services.units;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UnitHelper {

    private UnitHelper() {
        throw new IllegalStateException("Utility class");
    }

    private static final String UNITS_PROPERTIES = "resources/units.properties";

    /**
     * This method converts the temperature value into the default one.
     *
     * @param defaultUnit    refers to the temperature unit that the value should be converted to.
     * @param valueToConvert refers to the temperature value.
     * @param unit           refers to the TemperatureUnit type.
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
     * This method converts the temperature value into the default one.
     *
     * @param defaultUnit    refers to the rainfall unit that the value should be converted to.
     * @param valueToConvert refers to the rainfall value.
     * @param unit           refers to the RainfallUnit type.
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
     */
    private static String getApplicationTemperatureDefault(String propFileName) {
        String temperatureDefault = "Celsius";
        Properties prop = new Properties();
        try (FileInputStream input = new FileInputStream(propFileName)) {
            prop.load(input);
            temperatureDefault = prop.getProperty("defaultApplicationTemperatureUnit");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return temperatureDefault;
    }

    /**
     * Method to get a String with the path to configuration file
     * This method is separated from the above so we can test with different propfilenames (wrong etc).
     *
     * @return String with ApplicationTemperatureDefault path
     */
    static String getApplicationTemperatureConfig() {
        return getApplicationTemperatureDefault(UNITS_PROPERTIES);
    }

    /**
     * This method checks the properties file for the user default temperature unit.
     *
     * @return temperature unit as a String.
     * @throws IOException in case the file is not found or it doesn't have the property.
     */
    static String getUserTemperatureDefault(String propFileName) throws IOException {
        String temperatureDefault;
        Properties prop = new Properties();
        try (FileInputStream input = new FileInputStream(propFileName)) {
            prop.load(input);
            temperatureDefault = prop.getProperty("defaultDisplayTemperatureUnit");
        } catch (IOException ioe) {
            throw new IOException("ERROR: Unable to process configuration file.");
        }
        return temperatureDefault;
    }

    /**
     * Method to get a String with the path to configuration file
     * This method is separated from the above so we can test with different propfilenames (wrong etc).
     *
     * @return String with ApplicationTemperatureDefault path
     */
    static String getUserTemperatureConfig() throws IOException {
        return getUserTemperatureDefault(UNITS_PROPERTIES);
    }

    /**
     * This method checks the properties file for the application default rainfall unit.
     *
     * @return rainfall unit as a String.
     */
    private static String getApplicationRainfallDefault(String propFileName) {
        String rainfallDefault = "Millimeter";
        Properties prop = new Properties();
        try (FileInputStream input = new FileInputStream(propFileName)) {
            prop.load(input);
            rainfallDefault = prop.getProperty("defaultApplicationRainfallUnit");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return rainfallDefault;
    }

    /**
     * Method to get a String with the path to configuration file
     * This method is separated from the above so we can test with different propfilenames (wrong etc).
     *
     * @return String with ApplicationTemperatureDefault path
     */
    static String getApplicationRainfallConfig() {
        return getApplicationRainfallDefault(UNITS_PROPERTIES);
    }

    /**
     * This method checks the properties file for the user default rainfall unit.
     *
     * @return rainfall unit as a String.
     * @throws IOException in case the file is not found or it doesn't have the property.
     */
    static String getUserRainfallDefault(String propFileName) throws IOException {
        String rainfallDefault;
        Properties prop = new Properties();
        try (FileInputStream input = new FileInputStream(propFileName)) {
            prop.load(input);
            rainfallDefault = prop.getProperty("defaultDisplayRainfallUnit");
        } catch (IOException ioe) {
            throw new IOException("ERROR: Unable to process configuration file.");
        }
        return rainfallDefault;
    }

    /**
     * Method to get a String with the path to configuration file
     * This method is separated from the above so we can test with different propfilenames (wrong etc).
     *
     * @return String with ApplicationTemperatureDefault path
     * @throws IOException the exception to the method
     */
    static String getUserRainfallConfig() throws IOException {
        return getUserRainfallDefault(UNITS_PROPERTIES);
    }

    /**
     * This method checks which type of Unit unitToConvert is and then converts its value into the application default.
     *
     * @param valueToConvert refers to unit value.
     * @param unitToConvert  refers to unit type.
     * @return the value converted into the application default unit.
     * @throws IOException in case the unit does not correspond to the Unit in the try.
     */
    static double convertToSystemDefault(double valueToConvert, Unit unitToConvert) throws IOException {
        try {
            TemperatureUnit specificUnit = (TemperatureUnit) unitToConvert;
            return specificUnit.toApplicationDefault(valueToConvert);
        } catch (ClassCastException ok) {
            ok.getMessage();
        }
        RainfallUnit specificUnit = (RainfallUnit) unitToConvert;
        return specificUnit.toApplicationDefault(valueToConvert);
    }

    /**
     * This method checks which type of Unit unitToConvert is and then converts its value into the user default.
     *
     * @param valueToConvert refers to unit value.
     * @param unitToConvert  refers to unit type.
     * @return the value converted into the user default unit.
     */
    static double convertToUserDefault(double valueToConvert, Unit unitToConvert) throws IOException {
        try {
            TemperatureUnit specificUnit = (TemperatureUnit) unitToConvert;
            return specificUnit.toUserDefault(valueToConvert);
        } catch (ClassCastException ok) {
            ok.getMessage();
        }
        RainfallUnit specificUnit = (RainfallUnit) unitToConvert;
        return specificUnit.toUserDefault(valueToConvert);
    }


    /**
     * This method receives a Unit object and returns another Unit object from same measuring system
     * that was previously defined as System Default.
     *
     * @param unit Unit object to convert.
     * @return Unit object defined as System Default
     */
    static Unit convertUnitToSystemDefault(Unit unit) {
        if (unit instanceof TemperatureUnit) {
            String defaultTemperatureString = getApplicationTemperatureConfig();
            return convertStringToUnit(defaultTemperatureString);
        } else if (unit instanceof RainfallUnit) {
            String defaultRainfallString = getApplicationRainfallConfig();
            return convertStringToUnit(defaultRainfallString);
        }
        return null;
    }

    /**
     * This method receives a String that corresponds to a given Measuring Unit and
     * converts that String to an object of the corresponding Measuring Unit.
     *
     * @param unit String that corresponds to a given Unit.
     * @return Unit object that was created from the given String.
     */
    static Unit convertStringToUnit(String unit) {
        try {
            String classToInstance = getReaderClassToInstance(unit); //retrieves class to instance

            Class<?> unitClass = Class.forName("pt.ipp.isep.dei.project.services.units." + classToInstance);
            return (Unit) unitClass.newInstance(); // invokes empty constructor
        } catch (IOException io) {
            throw new IllegalArgumentException(io.getMessage());
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * This method receives a String that corresponds to a given Unit and returns
     * another String that corresponds to the Class that instances objects from the given Unit.
     *
     * @param unit String that corresponds to a given Unit.
     * @return String of the Class that corresponds to the given Unit.
     */
    static String getReaderClassToInstance(String unit) throws IOException {
        String classToInstance;
        Properties props = new Properties();
        try (InputStream input = new FileInputStream("resources/unit.properties")) {
            props.load(input);
            classToInstance = props.getProperty(unit);

        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
        return classToInstance;
    }
}
