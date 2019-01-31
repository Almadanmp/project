package pt.ipp.isep.dei.project.model.device;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * deviceTypeConfig tests class.
 */

public class DeviceTypeConfigTest {

    @Test
    public void getPropertyValueFromKeySucess() throws IOException {
        Properties props = new Properties();
        String propFileName = "resources/devices.properties";
        InputStream input = new FileInputStream(propFileName);
        props.load(input);
        String key = "WaterHeater";
        String result = DeviceTypeConfig.getPropertyValueFromKey(props, key);
        String expectedResult = "pt.ipp.isep.dei.project.model.device.devicetypes.WaterHeaterDT";
        assertEquals(expectedResult, result);
    }

    @Test
    public void getPropertyValueFromKeyNullProperty() {
        assertThrows(IOException.class,
                () -> {
                    Properties props = new Properties();
                    String key = "WaterHeater";
                    DeviceTypeConfig.getPropertyValueFromKey(props, key);
                });
    }

    @Test
    public void getDeviceTypeConfigSuccess() throws IOException {
        Properties props = new Properties();
        String propFileName = "resources/devices.properties";
        InputStream input = new FileInputStream(propFileName);
        props.load(input);
        List<String> result = DeviceTypeConfig.getDeviceTypeConfig();
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("pt.ipp.isep.dei.project.model.device.devicetypes.FridgeDT");
        expectedResult.add("pt.ipp.isep.dei.project.model.device.devicetypes.DishwasherDT");
        expectedResult.add("pt.ipp.isep.dei.project.model.device.devicetypes.WashingMachineDT");
        expectedResult.add("pt.ipp.isep.dei.project.model.device.devicetypes.WaterHeaterDT");
        expectedResult.add("pt.ipp.isep.dei.project.model.device.devicetypes.LampDT");

        assertEquals(expectedResult, result);
    }

    @Test
    public void getDeviceTypeConfigWrongFileName() {
        assertThrows(IOException.class,
                () -> {
                    Properties props = new Properties();
                    String propFileName = "resources/deces.properties";
                    InputStream input = new FileInputStream(propFileName);
                    props.load(input);
                    DeviceTypeConfig.getDeviceTypeConfig();
                });
    }

    @Test
    public void getDeviceTypeConfigNullPropertiesAndWrongName() {
        assertThrows(IOException.class,
                () -> {
                    Properties props = null;
                    String propFileName = "resources/deces.properties";
                    InputStream input = new FileInputStream(propFileName);
                    props.load(input);
                    DeviceTypeConfig.getDeviceTypeConfig();
                });
    }

    @Test
    public void getDeviceTypeConfigNullpropFileName() {
        assertThrows(NullPointerException.class,
                () -> {
                    Properties props = new Properties();
                    String propFileName = null;
                    InputStream input = new FileInputStream(propFileName);
                    props.load(input);
                    DeviceTypeConfig.getDeviceTypeConfig();
                });
    }

    @Test
    public void getDeviceTypeInputStreamNull() {
        assertThrows(FileNotFoundException.class,
                () -> {
                    Properties props = new Properties();
                    InputStream input = new FileInputStream("");
                    props.load(input);
                    DeviceTypeConfig.getDeviceTypeConfig();
                });
    }
}

