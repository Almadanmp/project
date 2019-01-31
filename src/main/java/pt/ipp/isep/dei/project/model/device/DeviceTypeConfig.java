package pt.ipp.isep.dei.project.model.device;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 *
 */

public class DeviceTypeConfig {

    public static List<String> getDeviceTypeConfig() throws IOException {
        String propFileName = "resources/devices.properties";
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
        } catch (IOException e) {
            throw new IOException("ERROR: Unable to process device configuration file - " + e.getMessage());
        }

        return deviceTypeConfig;
    }

    static String getPropertyValueFromKey(Properties p, String key) throws IOException {
        String result = p.getProperty(key);
        if (result == null) {
            throw new IOException("Could not read " + key + " property value.");
        }
        return result;
    }
}

