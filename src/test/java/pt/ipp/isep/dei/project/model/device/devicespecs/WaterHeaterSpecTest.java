package pt.ipp.isep.dei.project.model.device.devicespecs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.device.WaterHeater;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.testng.Assert.assertEquals;

/**
 * WaterHeaterSpec tests class.
 */

class WaterHeaterSpecTest {
    private WaterHeaterSpec validHeaterSpec;

    @BeforeEach
    void arrangeArtifacts() {
        validHeaterSpec = new WaterHeaterSpec();
        validHeaterSpec.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 5D);
        validHeaterSpec.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 5D);
        validHeaterSpec.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 5D);
        validHeaterSpec.setAttributeValue(WaterHeaterSpec.COLD_WATER_TEMP, 5D);
        validHeaterSpec.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER_HEAT, 5D);
    }

    //Test Attributes

    @Test
    void seeIfGetAndSetAttributeValueWork() {
        // Arrange

        String attribute = "Volume Of Water";
        Double expectedResult = 0.6;

        // Act

        boolean setResult = validHeaterSpec.setAttributeValue(attribute, 0.6D);
        Object getResult = validHeaterSpec.getAttributeValue(attribute);

        // Assert

        assertEquals(expectedResult, getResult);
        assertTrue(setResult);
    }


    @Test
    void seeIfSetAttributeValueWorksWrongName() {
        // Act

        boolean result = validHeaterSpec.setAttributeValue("Wrong", 0.6D);

        // Assert

        assertFalse(result);
    }

    @Test
    void seeIfGetAttributeNamesWorks() {
        // Act

        List<String> result = validHeaterSpec.getAttributeNames();

        // Assert

        assertTrue(result.contains("Volume Of Water"));
        assertTrue(result.contains(WaterHeaterSpec.HOT_WATER_TEMP));
        assertTrue(result.contains(WaterHeaterSpec.PERFORMANCE_RATIO));
        assertEquals(result.size(), 3);
    }

    @Test
    void seeIfGetAttributeValueWorksWrongName() {
        // Act

        Object getResult = validHeaterSpec.getAttributeValue("Lisbon");

        // Assert

        assertEquals(0D, getResult);
    }


    @Test
    void seeIfGetAndSetAttributeValuesWork() {
        // Arrange

        Double expectedResult = 2.0;

        // Act

        boolean setResult = validHeaterSpec.setAttributeValue("Volume Of Water", 2.0);
        Object getResult = validHeaterSpec.getAttributeValue("Volume Of Water");

        // Assert

        assertEquals(expectedResult, getResult);
        assertTrue(setResult);

        // Arrange

        expectedResult = 3.0;

        // Act

        setResult = validHeaterSpec.setAttributeValue("Hot Water Temperature", 3D);
        getResult = validHeaterSpec.getAttributeValue("Hot Water Temperature");

        // Assert

        assertEquals(expectedResult, getResult);
        assertTrue(setResult);

        // Arrange

        expectedResult = 4.0;

        // Act

        setResult = validHeaterSpec.setAttributeValue("Cold Water Temperature", 4D);
        getResult = validHeaterSpec.getAttributeValue("Cold Water Temperature");

        // Assert

        assertEquals(expectedResult, getResult);
        assertTrue(setResult);


        // Arrange

        expectedResult = 5.0;

        // Act

        setResult = validHeaterSpec.setAttributeValue("Performance Ratio", 5D);
        getResult = validHeaterSpec.getAttributeValue("Performance Ratio");

        // Assert

        assertEquals(expectedResult, getResult);
        assertTrue(setResult);

        // Arrange

        expectedResult = 10.0;
        setResult = validHeaterSpec.setAttributeValue("Volume Of Water To Heat", 10D);
        getResult = validHeaterSpec.getAttributeValue("Volume Of Water To Heat");

        // Assert

        assertEquals(expectedResult, getResult);
        assertTrue(setResult);
    }

    @Test
    void seeIfSetAttributeValuesWorksNotDouble() {
        // Volume of Water

        String attribute = "Volume Of Water";
        int attributeValue = 2;
        boolean setResult = validHeaterSpec.setAttributeValue(attribute, attributeValue);
        assertFalse(setResult);

        // Hot Water Temp

        attribute = "Hot Water Temperature";
        attributeValue = 3;
        setResult = validHeaterSpec.setAttributeValue(attribute, attributeValue);
        assertFalse(setResult);

        // Cold Water Temp

        attribute = "Cold Water Temperature";
        attributeValue = 4;
        setResult = validHeaterSpec.setAttributeValue(attribute, attributeValue);
        assertFalse(setResult);

        // Performance Ratio

        attribute = "Performance Ratio";
        attributeValue = 5;
        setResult = validHeaterSpec.setAttributeValue(attribute, attributeValue);
        assertFalse(setResult);

        // Volume of Water to Heat

        attribute = "Volume Of Water To Heat";
        attributeValue = 10;
        setResult = validHeaterSpec.setAttributeValue(attribute, attributeValue);
        assertFalse(setResult);
    }

    @Test
    void seeIfSetAttributeValueFailsWrongName() {
        // Arrange

        String attribute = "Wrong";

        // Act

        boolean setResult = validHeaterSpec.setAttributeValue(attribute, 2D);

        // Assert

        assertFalse(setResult);
    }

    @Test
    void seeIfSetAttributeValuesWorks() {
        // Happy Cases

        assertTrue(validHeaterSpec.setAttributeValue("Volume Of Water", 6D));
        assertTrue(validHeaterSpec.setAttributeValue("Cold Water Temperature", 6D));
        assertTrue(validHeaterSpec.setAttributeValue("Hot Water Temperature", 6D));
        assertTrue(validHeaterSpec.setAttributeValue("Performance Ratio", 6D));
        assertTrue(validHeaterSpec.setAttributeValue("Volume Of Water To Heat", 6D));

        // Wrong Attribute Names

        assertFalse(validHeaterSpec.setAttributeValue("\0Volume Of Water", 5D));
        assertFalse(validHeaterSpec.setAttributeValue("\0Cold Water Temperature", 5D));
        assertFalse(validHeaterSpec.setAttributeValue("\0Hot Water Temperature", 5D));
        assertFalse(validHeaterSpec.setAttributeValue("\0Performance Ratio", 5D));
        assertFalse(validHeaterSpec.setAttributeValue("\0Volume Of Water To Heat", 5D));

        // Empty Attribute Name

        assertFalse(validHeaterSpec.setAttributeValue("", 6D));
    }


    @Test
    void seeIfSetAttributeValueWorksNull() {
        // Act

        boolean setResult = validHeaterSpec.setAttributeValue(null, 6D);

        // Assert

        assertFalse(setResult);
    }


    @Test
    void seeIfGetAttributeValueWorks() {
        // Happy Cases

        assertEquals(5.0, validHeaterSpec.getAttributeValue("Volume Of Water"));
        assertEquals(5.0, validHeaterSpec.getAttributeValue("Hot Water Temperature"));
        assertEquals(5.0, validHeaterSpec.getAttributeValue("Cold Water Temperature"));
        assertEquals(5.0, validHeaterSpec.getAttributeValue("Performance Ratio"));
        assertEquals(5.0, validHeaterSpec.getAttributeValue("Volume Of Water To Heat"));

        // Wrong attribute Names.

        assertEquals(0.0, validHeaterSpec.getAttributeValue("\0Volume Of Water"));
        assertEquals(0.0, validHeaterSpec.getAttributeValue("\0Hot Water Temperature"));
        assertEquals(0.0, validHeaterSpec.getAttributeValue("\0Cold Water Temperature"));
        assertEquals(0.0, validHeaterSpec.getAttributeValue("\0Performance Ratio"));
        assertEquals(0.0, validHeaterSpec.getAttributeValue("\0Volume Of Water To Heat"));

        // Empty attribute name.

        assertEquals(0.0, validHeaterSpec.getAttributeValue(""));
    }

    @Test
    void seeIfGetAttributeUnitWorks() {
        // Arrange

        String expectedResult1 = "L";
        String expectedResult2 = "ºC";
        String expectedResult3 = "";

        // Act

        Object result1 = validHeaterSpec.getAttributeUnit(WaterHeaterSpec.HOT_WATER_TEMP);
        Object result2 = validHeaterSpec.getAttributeUnit(WaterHeaterSpec.PERFORMANCE_RATIO);
        Object result3 = validHeaterSpec.getAttributeUnit(WaterHeaterSpec.VOLUME_OF_WATER);
        Object result4 = validHeaterSpec.getAttributeUnit(WaterHeaterSpec.COLD_WATER_TEMP);
        Object result5 = validHeaterSpec.getAttributeUnit(WaterHeaterSpec.VOLUME_OF_WATER_HEAT);

        // Assert

        assertEquals(expectedResult2, result1);
        assertEquals(expectedResult3, result2);
        assertEquals(expectedResult1, result3);
        assertEquals(expectedResult2, result4);
        assertEquals(expectedResult1, result5);
    }

    @Test
    void seeIfGetAttributeUnitWorkFails() {

        // Wrong attribute Names
        Object result1 = validHeaterSpec.getAttributeUnit("\0" + WaterHeaterSpec.HOT_WATER_TEMP);
        Object result2 = validHeaterSpec.getAttributeUnit("\0" + WaterHeaterSpec.PERFORMANCE_RATIO);
        Object result3 = validHeaterSpec.getAttributeUnit("\0" + WaterHeaterSpec.VOLUME_OF_WATER);
        Object result4 = validHeaterSpec.getAttributeUnit("\0" + WaterHeaterSpec.COLD_WATER_TEMP);
        Object result5 = validHeaterSpec.getAttributeUnit("\0" + WaterHeaterSpec.VOLUME_OF_WATER_HEAT);
        Object result6 = validHeaterSpec.getAttributeUnit("");
        Object result7 = validHeaterSpec.getAttributeUnit(" ");

        assertEquals(false, result1);
        assertEquals(false, result2);
        assertEquals(false, result3);
        assertEquals(false, result4);
        assertEquals(false, result5);
        assertEquals(false, result6);
        assertEquals(false, result7);

    }
}
