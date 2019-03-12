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
        String expectedResult = deviceTypePath + "WaterHeaterDT";

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
        expectedResult.add(deviceTypePath + "FridgeDT");
        expectedResult.add(deviceTypePath + "DishwasherDT");
        expectedResult.add(deviceTypePath + "WashingMachineDT");
        expectedResult.add(deviceTypePath + "WaterHeaterDT");
        expectedResult.add(deviceTypePath + "LampDT");
        expectedResult.add(deviceTypePath + "WallTowelHeaterDT");
        expectedResult.add(deviceTypePath + "PortableElectricConvectionHeaterDT");
        expectedResult.add(deviceTypePath + "PortableElectricOilHeaterDT");
        expectedResult.add(deviceTypePath + "WallElectricHeaterDT");
        expectedResult.add(deviceTypePath + "StoveDT");
        expectedResult.add(deviceTypePath + "WineCoolerDT");
        expectedResult.add(deviceTypePath + "FreezerDT");
        expectedResult.add(deviceTypePath + "ElectricOvenDT");
        expectedResult.add(deviceTypePath + "TvDT");

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
        expectedResult.add(deviceTypePath + "FridgeDT");
        expectedResult.add(deviceTypePath + "DishwasherDT");
        expectedResult.add(deviceTypePath + "WashingMachineDT");
        expectedResult.add(deviceTypePath + "WaterHeaterDT");
        expectedResult.add(deviceTypePath + "LampDT");
        expectedResult.add(deviceTypePath + "WallTowelHeaterDT");
        expectedResult.add(deviceTypePath + "PortableElectricConvectionHeaterDT");
        expectedResult.add(deviceTypePath + "PortableElectricOilHeaterDT");
        expectedResult.add(deviceTypePath + "WallElectricHeaterDT");
        expectedResult.add(deviceTypePath + "StoveDT");
        expectedResult.add(deviceTypePath + "WineCoolerDT");
        expectedResult.add(deviceTypePath + "FreezerDT");
        expectedResult.add(deviceTypePath + "ElectricOvenDT");
        expectedResult.add(deviceTypePath + "TvDT");


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

