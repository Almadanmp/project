package pt.ipp.isep.dei.project.model.device.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class DeviceTypeConfig {

    /**
     * @param p   - properties file
     * @param key - key to the value we want to get
     * @return - string "value" from the key
     * @throws IOException - will throw an IOException if we cannot read the input key
     */
    String getPropertyValueFromKey(Properties p, String key) throws IOException {
        String result = p.getProperty(key);
        if (result == null) {
            throw new IOException("Could not read " + key + " property value.");
        }
        return result;
    }

    /**
     * Method to get a List of Strings with all the Paths contained on configuration file to the different device types
     * available
     *
     * @param propFileName - the properties configuration file where the device types are
     * @return - list of strings with device paths
     * @throws IOException - will be thrown if there is any error with the configuration file
     */
    List<String> getDeviceTypeConfigFromSpecificFile(String propFileName) throws IOException {
        String allDevicesKey = "allDeviceTypes";
        Properties props = new Properties();

        List<String> deviceTypeConfig = new ArrayList<>();

        try (InputStream input = new FileInputStream(propFileName)) {
            props.load(input);
            String deviceTypes = getPropertyValueFromKey(props, allDevicesKey);
            List<String> deviceTypeList = Arrays.asList(deviceTypes.split(","));
            for (String s : deviceTypeList) {
                String aux = getPropertyValueFromKey(props, s);
                deviceTypeConfig.add(aux);
            }
        } catch (IOException e) { // NOSONAR
            throw new IOException("ERROR: Unable to process device configuration file - " + e.getMessage());
        }
        return deviceTypeConfig;
    }

    /**
     * Method to get a List of Strings with all the Paths contained on configuration file to the different device types
     * available
     * This method is separated from the above so we can test with different propfilenames (wrong etc).
     *
     * @return list of strings with device paths
     * @throws IOException the exception to the method
     */
    public List<String> getDeviceTypeConfig() throws IOException {
        return getDeviceTypeConfigFromSpecificFile("resources/devices.properties");
    }

}

