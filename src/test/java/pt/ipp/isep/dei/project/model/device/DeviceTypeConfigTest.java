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
    public void getPropertyValueFromKeySuccess() throws IOException {
        Properties props = new Properties();
        DeviceTypeConfig dtc = new DeviceTypeConfig();
        String propFileName = "resources/devices.properties";
        InputStream input = new FileInputStream(propFileName);
        props.load(input);
        String key = "WaterHeater";
        String result = dtc.getPropertyValueFromKey(props, key);
        String expectedResult = "pt.ipp.isep.dei.project.model.device.devicetypes.WaterHeaterDT";
        assertEquals(expectedResult, result);
    }

    @Test
    public void getPropertyValueFromKeyNullProperty() {
        assertThrows(IOException.class,
                () -> {
                    Properties props = new Properties();
                    DeviceTypeConfig dtc = new DeviceTypeConfig();
                    String key = "WaterHeater";
                    dtc.getPropertyValueFromKey(props, key);
                });
    }

    @Test
    public void getDeviceTypeConfigSuccess() throws IOException {
        Properties props = new Properties();
        String propFileName = "resources/devices.properties";
        InputStream input = new FileInputStream(propFileName);
        props.load(input);
        DeviceTypeConfig dtc = new DeviceTypeConfig();
        List<String> result = dtc.getDeviceTypeConfig();
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
                    DeviceTypeConfig dtc = new DeviceTypeConfig();
                    Properties props = new Properties();
                    String propFileName = "resources/deces.properties";
                    InputStream input = new FileInputStream(propFileName);
                    props.load(input);
                    dtc.getDeviceTypeConfig();
                });
    }

    @Test
    public void getDeviceTypeConfigNullPropertiesAndWrongName() {
        assertThrows(IOException.class,
                () -> {
                    Properties props = null;
                    DeviceTypeConfig dtc = new DeviceTypeConfig();
                    String propFileName = "resources/deces.properties";
                    InputStream input = new FileInputStream(propFileName);
                    props.load(input);
                    dtc.getDeviceTypeConfig();
                });
    }

    @Test
    public void getDeviceTypeConfigNullpropFileName() {
        assertThrows(NullPointerException.class,
                () -> {
                    Properties props = new Properties();
                    DeviceTypeConfig dtc = new DeviceTypeConfig();
                    String propFileName = null;
                    InputStream input = new FileInputStream(propFileName);
                    props.load(input);
                    dtc.getDeviceTypeConfig();
                });
    }

    @Test
    public void getDeviceTypeInputStreamNull() {
        assertThrows(FileNotFoundException.class,
                () -> {
                    Properties props = new Properties();
                    DeviceTypeConfig dtc = new DeviceTypeConfig();
                    InputStream input = new FileInputStream("");
                    props.load(input);
                    dtc.getDeviceTypeConfig();
                });
    }

    @Test
    public void getDeviceTypeConfigInvalidFileIOException() {
        assertThrows(IOException.class,
                () -> {
                    DeviceTypeConfig dtc = new DeviceTypeConfig();
                    dtc.getDeviceTypeConfigFromSpecificFile( "resources/devices_TEST.properties");
                });
    }
}

