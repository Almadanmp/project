package pt.ipp.isep.dei.project.model.device.config;

import org.junit.jupiter.api.BeforeEach;
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

class DeviceTypeConfigTest {
    private Properties props;
    private DeviceTypeConfig dtc;
    private String propFileName = "resources/devices.properties";
    private InputStream input;
    private String deviceTypePath = "pt.ipp.isep.dei.project.model.device.devicetypes.";

    @BeforeEach
    void arrangeArtifacts() {
        props = new Properties();
        dtc = new DeviceTypeConfig();
        try {
            input = new FileInputStream(propFileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getPropertyValueFromKeySuccess() throws IOException {
        // Act

        props.load(input);
        String key = "WaterHeater";
        String result = dtc.getPropertyValueFromKey(props, key);
        String expectedResult = deviceTypePath + "WaterHeaterType";

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void getPropertyValueFromKeyNullProperty() {
        // Assert

        assertThrows(IOException.class,
                () -> {
                    String key = "WaterHeater";
                    dtc.getPropertyValueFromKey(props, key);
                });
    }

    @Test
    void getDeviceTypeConfigSuccess() throws IOException {
        // Arrange

        List<String> expectedResult = new ArrayList<>();
        expectedResult.add(deviceTypePath + "FridgeType");
        expectedResult.add(deviceTypePath + "DishwasherType");
        expectedResult.add(deviceTypePath + "WashingMachineType");
        expectedResult.add(deviceTypePath + "WaterHeaterType");
        expectedResult.add(deviceTypePath + "LampType");
        expectedResult.add(deviceTypePath + "WallTowelHeaterType");
        expectedResult.add(deviceTypePath + "PortableElectricConvectionHeaterType");
        expectedResult.add(deviceTypePath + "PortableElectricOilHeaterType");
        expectedResult.add(deviceTypePath + "WallElectricHeaterType");
        expectedResult.add(deviceTypePath + "StoveType");
        expectedResult.add(deviceTypePath + "WineCoolerType");
        expectedResult.add(deviceTypePath + "FreezerType");
        expectedResult.add(deviceTypePath + "ElectricOvenType");
        expectedResult.add(deviceTypePath + "TvType");

        // Act

        props.load(input);
        List<String> result = dtc.getDeviceTypeConfig();

        // Assert

        assertEquals(expectedResult, result);
    }


    @Test
    void getDeviceTypeConfigWrongFileName() {
        // Assert

        assertThrows(IOException.class,
                () -> {
                    propFileName = "res/deces.prrties";
                    input = new FileInputStream(propFileName);
                    props.load(input);
                    dtc.getDeviceTypeConfig();
                });
    }

    @Test
    void getDeviceTypeConfigNullPropertiesAndWrongName() {
        // Assert

        assertThrows(IOException.class,
                () -> {
                    props = null;
                    propFileName = "resources/deces.properties";
                    input = new FileInputStream(propFileName);
                    props.load(input);
                    dtc.getDeviceTypeConfig();
                });
    }

    @Test
    void getDeviceTypeConfigNullPropFileName() {
        // Assert

        assertThrows(NullPointerException.class,
                () -> {
                    propFileName = null;
                    input = new FileInputStream(propFileName); // Necessary for Sonarqube testing coverage.
                    props.load(input);
                    dtc.getDeviceTypeConfig();
                });
    }

    @Test
    void getDeviceTypeRightPropFileNameButWithNoValuesInside() {
        // Assert

        assertThrows(FileNotFoundException.class,
                () -> {
                    propFileName = "resources/devices_test.properties";
                    input = new FileInputStream(propFileName);
                    props.load(input);
                    dtc.getDeviceTypeConfig();
                });
    }

    // DEVICE TYPE CONFIG SPECIFIC FILE METHOD

    @Test
    void getDeviceTypeConfigSpecificSuccess() throws IOException {
        // Arrange

        List<String> expectedResult = new ArrayList<>();
        expectedResult.add(deviceTypePath + "FridgeType");
        expectedResult.add(deviceTypePath + "DishwasherType");
        expectedResult.add(deviceTypePath + "WashingMachineType");
        expectedResult.add(deviceTypePath + "WaterHeaterType");
        expectedResult.add(deviceTypePath + "LampType");
        expectedResult.add(deviceTypePath + "WallTowelHeaterType");
        expectedResult.add(deviceTypePath + "PortableElectricConvectionHeaterType");
        expectedResult.add(deviceTypePath + "PortableElectricOilHeaterType");
        expectedResult.add(deviceTypePath + "WallElectricHeaterType");
        expectedResult.add(deviceTypePath + "StoveType");
        expectedResult.add(deviceTypePath + "WineCoolerType");
        expectedResult.add(deviceTypePath + "FreezerType");
        expectedResult.add(deviceTypePath + "ElectricOvenType");
        expectedResult.add(deviceTypePath + "TvType");


        // Act

        props.load(input);
        List<String> result = dtc.getDeviceTypeConfigFromSpecificFile(propFileName);

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void getDeviceTypeConfigInvalidFileIOException() {
        // Assert

        assertThrows(IOException.class,
                () -> dtc.getDeviceTypeConfigFromSpecificFile("resources/devices_test.properties"));
    }

    @Test
    void getDeviceTypeConfigSpecificFileWrongPropFileName() {
        // Assert

        assertThrows(IOException.class,
                () -> {
                    props = null;
                    propFileName = "resources/deces.properties";
                    input = new FileInputStream(propFileName);
                    props.load(input);
                    dtc.getDeviceTypeConfigFromSpecificFile(propFileName);
                });
    }

    @Test
    void getDeviceTypeSpecificFileRightPropFileNameButWithNoValuesInside() {
        // Assert

        assertThrows(FileNotFoundException.class,
                () -> {
                    propFileName = "resources/devices_test.properties";
                    input = new FileInputStream(propFileName);
                    props.load(input);
                    dtc.getDeviceTypeConfigFromSpecificFile(propFileName);
                });
    }
}

