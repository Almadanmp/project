package pt.ipp.isep.dei.project.io.ui.commandline;

import pt.ipp.isep.dei.project.model.areatype.AreaType;
import pt.ipp.isep.dei.project.model.areatype.AreaTypeRepository;
import pt.ipp.isep.dei.project.model.sensortype.SensorType;
import pt.ipp.isep.dei.project.model.sensortype.SensorTypeRepository;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class FileInputUtils {

    int deviceMeteringPeriod;
    int gridMeteringPeriod;
    List<String> areaTypes;
    List<String> sensorTypesName = new ArrayList<>();
    List<String> sensorTypesUnit = new ArrayList<>();
    private static final String ERROR_CONFIGURATION_FILE = "ERROR: Unable to process configuration file.";
    private static final String PROPERTY_VALUE = " property value.";


    /**
     * This method will readSensors the configuration file and validate the value that corresponds
     * to the grid metering period. In case of a valid value, the value will be stored has a
     * class attribute
     *
     * @return it will return true in case the value is valid and false if not
     **/
    public boolean gridMeteringPeriodIsValid() throws IOException {
        int gridMetPeriod = readGridMeteringPeriod();
        if (gridMeteringPeriodValidation(gridMetPeriod)) {
            this.gridMeteringPeriod = gridMetPeriod;
            return true;
        }
        return false;
    }

    /**
     * This method will readSensors the configuration file, get the string that corresponds to the
     * grid metering period and turn it into an integer
     *
     * @return the integer that corresponds to the grid metering period
     **/
    private int readGridMeteringPeriod() throws IOException {
        String gridMetPeriod;
        Properties prop = new Properties();
        try (FileInputStream input = new FileInputStream("resources/meteringPeriods.properties")) {
            prop.load(input);
            gridMetPeriod = prop.getProperty("GridMeteringPeriod");
        } catch (IOException ioe) {
            throw new IOException(ERROR_CONFIGURATION_FILE);
        }
        try {
            return Integer.parseInt(gridMetPeriod);
        } catch (NumberFormatException nfe) {
            throw new NumberFormatException("ERROR: Configuration File value is not a numeric value.");
        }
    }

    /**
     * This method will receive an integer and check if the value is valid. The sum of
     * all metering periods in a day should be 24 hours (1440 minutes)
     *
     * @param gridMetPeriod integer to be tested
     * @return true in case the value is valid, false if not
     **/
    public boolean gridMeteringPeriodValidation(int gridMetPeriod) {
        if (gridMetPeriod == 0) {
            System.out.println("Grid metering value must be greater than 0.");
            return false;
        }
        return 1440 % gridMetPeriod == 0;
    }

    //Device

    boolean validDeviceMetering() {
        int deviceMetPeriod = readDeviceMeteringPeriod();

        if (deviceMeteringPeriodValidation(deviceMetPeriod)) {
            this.deviceMeteringPeriod = deviceMetPeriod;
            return true;
        }
        System.out.println("ERROR: Configuration File values are incorrect. Devices cannot be created.\n" +
                "Please fix the configuration file before continuing.");
        return false;
    }

    private int readDeviceMeteringPeriod() {
        String deviceMetPeriod = "";
        Properties prop = new Properties();
        try {
            FileInputStream input = new FileInputStream("resources/meteringPeriods.properties");
            prop.load(input);
            deviceMetPeriod = prop.getProperty("DevicesMeteringPeriod");
            input.close();
        } catch (FileNotFoundException fnfe) {
            System.out.println("File not found.");
        } catch (IOException ioe) {
            System.out.println(ERROR_CONFIGURATION_FILE);
        }
        int deviceMPvalue = 0;
        try {
            deviceMPvalue = (Integer) Integer.parseInt(deviceMetPeriod);
        } catch (NumberFormatException nfe) {
            System.out.println("Configuration file values are not numeric.");
        }
        return deviceMPvalue;
    }

    private boolean deviceMeteringPeriodValidation(int deviceValue) {
        if (deviceValue == 0) {
            System.out.println("Device metering value must be greater than 0.");
            return false;
        }
        if (1440 % deviceValue != 0) {
            return false;
        }
        return deviceValue % this.gridMeteringPeriod == 0;
    }

    String getSensorTypesPropertyValueFromKey(Properties p, String key) throws IOException {
        String result = p.getProperty(key);
        if (result == null) {
            throw new IOException("Could not read the sensor type " + key + PROPERTY_VALUE);
        }
        return result;
    }

    String[] getSensorTypesMultipleValues(Properties p, String key) throws IOException {
        String[] result = p.getProperty(key).split(",");
        if (result == null) {
            throw new IOException("Could not read the sensor type " + key + PROPERTY_VALUE);
        }
        return result;
    }

    /**
     * This method will read types of Sensors from a configuration file, get the string that corresponds to the
     * type and turn it into a Sensor Type
     *
     * @return the SensorType List
     **/
    void readSensorTypesFromPropertiesFile(String fileName) throws IOException {
        String fullKey = "sensorTypes";
        Properties props = new Properties();

        try (FileInputStream input = new FileInputStream(fileName)) {
            props.load(input);
            String deviceTypes = getSensorTypesPropertyValueFromKey(props, fullKey);
            List<String> sensorTypeList = Arrays.asList(deviceTypes.split(","));
            for (String s : sensorTypeList) {
                String[] aux = getSensorTypesMultipleValues(props, s);

                this.sensorTypesName.add(aux[0]);
                this.sensorTypesUnit.add(aux[1]);
            }
        } catch (IOException ioe) {
            throw new IOException(ERROR_CONFIGURATION_FILE);
        }
    }

    void getSensorTypeConfig() throws IOException {
        readSensorTypesFromPropertiesFile("resources/sensorTypes.properties");
    }

    void addSensorTypesToRepository(SensorTypeRepository sensorTypeRepository) {

        for (int i = 0; i < this.sensorTypesName.size(); i++) {
            SensorType type = new SensorType();
            type.setName(sensorTypesName.get(i));
            type.setUnits(this.sensorTypesUnit.get(i));
            sensorTypeRepository.add(type);
        }
    }

    String getAreaTypesPropertyValueFromKey(Properties p, String key) throws IOException {
        String result = p.getProperty(key);
        if (result == null) {
            throw new IOException("Could not read the area type " + key + PROPERTY_VALUE);
        }
        return result;
    }

    /**
     * This method will read types of Areas from a configuration file, get the string that corresponds to the
     * type and turn it into a Area Type
     *
     * @return the AreaType List
     **/
    List<String> readAreaTypesFromPropertiesFile(String fileName) throws IOException {
        String fullKey = "areaTypes";
        Properties props = new Properties();
        List<String> areaTypeConfiguration = new ArrayList<>();
        try (FileInputStream input = new FileInputStream(fileName)) {
            props.load(input);
            String deviceTypes = getAreaTypesPropertyValueFromKey(props, fullKey);
            List<String> areaTypeList = Arrays.asList(deviceTypes.split(","));

            for (String s : areaTypeList) {
                String aux = getAreaTypesPropertyValueFromKey(props, s);
                areaTypeConfiguration.add(aux);
            }

        } catch (IOException ioe) {
            throw new IOException(ERROR_CONFIGURATION_FILE);
        }
        return areaTypeConfiguration;
    }

    void getAreaTypeConfig() throws IOException {
        this.areaTypes = readAreaTypesFromPropertiesFile("resources/areaTypes.properties");
    }

    void addAreatypesToRepository(AreaTypeRepository areaTypeRepository) {
        for (String s : this.areaTypes) {
            AreaType area = new AreaType(s);
            areaTypeRepository.add(area);
        }
    }
}
