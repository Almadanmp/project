package pt.ipp.isep.dei.project.model.device.devicespecs;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * WaterHeaterSpec tests class.
 */

class WaterHeaterSpecTest {


    //Test Attributes

    @Test
    void seeIfGetAndSetAttributeValue() {
        WaterHeaterSpec waterHeaterSpec = new WaterHeaterSpec();
        waterHeaterSpec.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 0.6D);
        waterHeaterSpec.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 30D);
        waterHeaterSpec.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 0.9D);
        Double volumeOfWater = 0.6;
        String attribute = "Volume Of Water";
        Double expectedResult = 0.6;
        boolean setResult = waterHeaterSpec.setAttributeValue(attribute, volumeOfWater);
        Object getResult = waterHeaterSpec.getAttributeValue(attribute);
        assertEquals(expectedResult, getResult);
        assertTrue(setResult);
    }


    @Test
    void seeIfSetAttributeValueInvalid() {
        WaterHeaterSpec waterHeaterSpec = new WaterHeaterSpec();
        waterHeaterSpec.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 12D);
        waterHeaterSpec.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 40D);
        waterHeaterSpec.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 234D);
        Double value = 0.6;
        String attribute = "invalid";
        boolean result = waterHeaterSpec.setAttributeValue(attribute, value);
        assertFalse(result);
    }

    @Test
    void seeIfGetAttributeNames() {
        WaterHeaterSpec waterHeaterSpec = new WaterHeaterSpec();
        waterHeaterSpec.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 12D);
        waterHeaterSpec.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 40D);
        waterHeaterSpec.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 234D);
        List<String> result = waterHeaterSpec.getAttributeNames();
        assertTrue(result.contains(WaterHeaterSpec.VOLUME_OF_WATER));
        assertTrue(result.contains(WaterHeaterSpec.HOT_WATER_TEMP));
        assertTrue(result.contains(WaterHeaterSpec.PERFORMANCE_RATIO));
        assertEquals(result.size(), 3);
    }

    @Test
    void seeIfGetAttributeValueDefaultTest() {
        WaterHeaterSpec waterHeaterSpec = new WaterHeaterSpec();
        String attribute = "Lisboa";
        Double expectedResult = 0.0;
        Object getResult = waterHeaterSpec.getAttributeValue(attribute);
        assertEquals(expectedResult, getResult);
    }


    @Test
    void seeIfGetAndSetAttributeValues() {
        WaterHeaterSpec waterHeaterSpec = new WaterHeaterSpec();
        waterHeaterSpec.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 12D);
        waterHeaterSpec.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 40D);
        waterHeaterSpec.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 234D);
        String attribute = "Volume Of Water";
        Double expectedResult = 2.0;
        Double attributeValue = 2.0;
        boolean setResult = waterHeaterSpec.setAttributeValue(attribute, attributeValue);
        Object getResult = waterHeaterSpec.getAttributeValue(attribute);
        assertEquals(expectedResult, getResult);
        assertTrue(setResult);

        attribute = "Hot Water Temperature";
        attributeValue = 3.0;
        expectedResult = 3.0;
        setResult = waterHeaterSpec.setAttributeValue(attribute, attributeValue);
        getResult = waterHeaterSpec.getAttributeValue(attribute);
        assertEquals(expectedResult, getResult);
        assertTrue(setResult);

        attribute = "Cold Water Temperature";
        attributeValue = 4.0;
        expectedResult = 4.0;
        setResult = waterHeaterSpec.setAttributeValue(attribute, attributeValue);
        getResult = waterHeaterSpec.getAttributeValue(attribute);
        assertEquals(expectedResult, getResult);
        assertTrue(setResult);

        attribute = "Performance Ratio";
        expectedResult = 5.0;
        attributeValue = 5.0;
        setResult = waterHeaterSpec.setAttributeValue(attribute, attributeValue);
        getResult = waterHeaterSpec.getAttributeValue(attribute);
        assertEquals(expectedResult, getResult);
        assertTrue(setResult);

        attribute = "Volume Of Water To Heat";
        expectedResult = 10.0;
        attributeValue = 10.0;
        setResult = waterHeaterSpec.setAttributeValue(attribute, attributeValue);
        getResult = waterHeaterSpec.getAttributeValue(attribute);
        assertEquals(expectedResult, getResult);
        assertTrue(setResult);
    }

    @Test
    void seeIFSetAttributeValuesFails() {
        WaterHeaterSpec waterHeaterSpec = new WaterHeaterSpec();
        waterHeaterSpec.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 12D);
        waterHeaterSpec.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 40D);
        waterHeaterSpec.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 234D);
        String attribute = "Volume Of Water";
        int attributeValue = 2;
        boolean setResult = waterHeaterSpec.setAttributeValue(attribute, attributeValue);
        assertFalse(setResult);

        attribute = "Hot Water Temperature";
        attributeValue = 3;
        setResult = waterHeaterSpec.setAttributeValue(attribute, attributeValue);
        assertFalse(setResult);

        attribute = "Cold Water Temperature";
        attributeValue = 4;
        setResult = waterHeaterSpec.setAttributeValue(attribute, attributeValue);
        assertFalse(setResult);

        attribute = "Performance Ratio";
        attributeValue = 5;
        setResult = waterHeaterSpec.setAttributeValue(attribute, attributeValue);
        assertFalse(setResult);

        attribute = "Volume Of Water To Heat";
        attributeValue = 10;
        setResult = waterHeaterSpec.setAttributeValue(attribute, attributeValue);
        assertFalse(setResult);
    }

    @Test
    void seeIFSetAttributeValuesFails2() {
        WaterHeaterSpec waterHeaterSpec = new WaterHeaterSpec();
        waterHeaterSpec.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 12D);
        waterHeaterSpec.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 40D);
        waterHeaterSpec.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 234D);

        String attribute = "njfdjkndfk";
        int attributeValue = 2;
        boolean setResult = waterHeaterSpec.setAttributeValue(attribute, attributeValue);
        assertFalse(setResult);

        attribute = "htfcf";
        attributeValue = 3;
        setResult = waterHeaterSpec.setAttributeValue(attribute, attributeValue);
        assertFalse(setResult);

        attribute = "fhj";
        attributeValue = 4;
        setResult = waterHeaterSpec.setAttributeValue(attribute, attributeValue);
        assertFalse(setResult);

        attribute = "fhjg";
        attributeValue = 5;
        setResult = waterHeaterSpec.setAttributeValue(attribute, attributeValue);
        assertFalse(setResult);

        attribute = "gfdjcktuyvuh";
        attributeValue = 10;
        setResult = waterHeaterSpec.setAttributeValue(attribute, attributeValue);
        assertFalse(setResult);
    }

    @Test
    void testSetAttributeCoveringAllCasesFalse() {
        //Arrange
        WaterHeaterSpec waterHeaterSpec = new WaterHeaterSpec();
        Double attribute = 6.0;
        waterHeaterSpec.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, null);
        waterHeaterSpec.setAttributeValue(FridgeSpec.REFRIGERATOR_CAPACITY, null);
        waterHeaterSpec.setAttributeValue(FridgeSpec.ANNUAL_CONSUMPTION, null);

        // original strings:
        assertTrue(waterHeaterSpec.setAttributeValue("Volume Of Water", attribute));
        assertTrue(waterHeaterSpec.setAttributeValue("Cold Water Temperature", attribute));
        assertTrue(waterHeaterSpec.setAttributeValue("Hot Water Temperature", attribute));
        assertTrue(waterHeaterSpec.setAttributeValue("Performance Ratio", attribute));
        assertTrue(waterHeaterSpec.setAttributeValue("Volume Of Water To Heat", attribute));

        // same hash codes, but different strings:
        assertFalse(waterHeaterSpec.setAttributeValue("\0Volume Of Water", attribute));
        assertFalse(waterHeaterSpec.setAttributeValue("\0Cold Water Temperature", attribute));
        assertFalse(waterHeaterSpec.setAttributeValue("\0Hot Water Temperature", attribute));
        assertFalse(waterHeaterSpec.setAttributeValue("\0Performance Ratio", attribute));
        assertFalse(waterHeaterSpec.setAttributeValue("\0Volume Of Water To Heat", attribute));

        // distinct hash code to cover default cases of switches
        assertFalse(waterHeaterSpec.setAttributeValue("", attribute));
    }


    @Test
    void seeIfGetAndSetAttributeValues2() {
        WaterHeaterSpec waterHeaterSpec = new WaterHeaterSpec();
        waterHeaterSpec.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 12D);
        waterHeaterSpec.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 40D);
        waterHeaterSpec.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 234D);
        Double attributeValue = 3.0;
        boolean setResult = waterHeaterSpec.setAttributeValue(null, attributeValue);
        assertFalse(setResult);
    }


    @Test
    void testGetAttributeCoveringAllCases() {
        //Arrange
        WaterHeaterSpec waterHeaterSpec = new WaterHeaterSpec();
        waterHeaterSpec.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 5D);
        waterHeaterSpec.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 5D);
        waterHeaterSpec.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 5D);

        // original strings:
        assertEquals(5.0, waterHeaterSpec.getAttributeValue("Volume Of Water"));
        assertEquals(5.0, waterHeaterSpec.getAttributeValue("Hot Water Temperature"));
        assertEquals(0.0, waterHeaterSpec.getAttributeValue("Cold Water Temperature"));
        assertEquals(5.0, waterHeaterSpec.getAttributeValue("Performance Ratio"));
        assertEquals(0.0, waterHeaterSpec.getAttributeValue("Volume Of Water To Heat"));

        // same hash codes, but different strings:
        assertEquals(0.0, waterHeaterSpec.getAttributeValue("\0Volume Of Water"));
        assertEquals(0.0, waterHeaterSpec.getAttributeValue("\0Hot Water Temperature"));
        assertEquals(0.0, waterHeaterSpec.getAttributeValue("\0Cold Water Temperature"));
        assertEquals(0.0, waterHeaterSpec.getAttributeValue("\0Performance Ratio"));
        assertEquals(0.0, waterHeaterSpec.getAttributeValue("\0Volume Of Water To Heat"));

        // distinct hash code to cover default cases of switches
        assertEquals(0.0, waterHeaterSpec.getAttributeValue(""));
    }

    @Test
    void testGetAttributeUnitCoveringAllCases() {
        //Arrange
        WaterHeaterSpec waterHeaterSpec = new WaterHeaterSpec();
        waterHeaterSpec.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 5D);
        waterHeaterSpec.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 5D);
        waterHeaterSpec.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 5D);

        // original strings:
        assertEquals("L", waterHeaterSpec.getAttributeUnit("Volume Of Water"));
        assertEquals("ºC", waterHeaterSpec.getAttributeUnit("Hot Water Temperature"));
        assertEquals("ºC", waterHeaterSpec.getAttributeUnit("Cold Water Temperature"));
        assertEquals("", waterHeaterSpec.getAttributeUnit("Performance Ratio"));
        assertEquals("L", waterHeaterSpec.getAttributeUnit("Volume Of Water To Heat"));

        // same hash codes, but different strings:
        assertEquals(false, waterHeaterSpec.getAttributeUnit("\0VolumeOfWater"));
        assertEquals(false, waterHeaterSpec.getAttributeUnit("\0HotWaterTemperature"));
        assertEquals(false, waterHeaterSpec.getAttributeUnit("\0ColdWaterTemperature"));
        assertEquals(false, waterHeaterSpec.getAttributeUnit("\0PerformanceRatio"));
        assertEquals(false, waterHeaterSpec.getAttributeUnit("\0VolumeOfWaterToHeat"));

        // distinct hash code to cover default cases of switches
        assertEquals(false, waterHeaterSpec.getAttributeUnit(""));
    }

    @Test
    void testSetAttributeCoveringAllCases() {
        //Arrange
        WaterHeaterSpec waterHeaterSpec = new WaterHeaterSpec();
        Double attribute = 6.0;

        // original strings:
        assertTrue(waterHeaterSpec.setAttributeValue("Volume Of Water", attribute));
        assertTrue(waterHeaterSpec.setAttributeValue("Hot Water Temperature", attribute));
        assertTrue(waterHeaterSpec.setAttributeValue("Cold Water Temperature", attribute));
        assertTrue(waterHeaterSpec.setAttributeValue("Performance Ratio", attribute));
        assertTrue(waterHeaterSpec.setAttributeValue("Volume Of Water To Heat", attribute));

        // same hash codes, but different strings:
        assertFalse(waterHeaterSpec.setAttributeValue("\0Volume Of Water", attribute));
        assertFalse(waterHeaterSpec.setAttributeValue("\0Hot Water Temperature", attribute));
        assertFalse(waterHeaterSpec.setAttributeValue("\0Cold Water Temperature", attribute));
        assertFalse(waterHeaterSpec.setAttributeValue("\0Performance Ratio", attribute));
        assertFalse(waterHeaterSpec.setAttributeValue("\0Volume Of Water To Heat", attribute));

        // distinct hash code to cover default cases of switches
        assertFalse(waterHeaterSpec.setAttributeValue("", attribute));
    }

    @Test
    void testGetAttributeUnitForAllConditions() {
        //Arrange
        WaterHeaterSpec wHeaterSpec = new WaterHeaterSpec();
        String attributeL = "L";
        String attributeC = "ºC";
        String attribute = "";
        // original strings:
        assertEquals(attributeL, wHeaterSpec.getAttributeUnit(WaterHeaterSpec.VOLUME_OF_WATER));
        assertEquals(attributeC, wHeaterSpec.getAttributeUnit(WaterHeaterSpec.HOT_WATER_TEMP));
        assertEquals(attributeC, wHeaterSpec.getAttributeUnit(WaterHeaterSpec.COLD_WATER_TEMP));
        assertEquals(attribute, wHeaterSpec.getAttributeUnit(WaterHeaterSpec.PERFORMANCE_RATIO));
        assertEquals(attributeL, wHeaterSpec.getAttributeUnit(WaterHeaterSpec.VOLUME_OF_WATER_HEAT));
        // same hash codes, but different strings:
        assertEquals(false, wHeaterSpec.getAttributeUnit("notATTRIBUTE_VOLUME_OF_WATER"));
        assertEquals(false, wHeaterSpec.getAttributeUnit("notATTRIBUTE_HOT_WATER_TEMP"));
        assertEquals(false, wHeaterSpec.getAttributeUnit("notATTRIBUTE_COLD_WATER_TEMP"));
        assertEquals(false, wHeaterSpec.getAttributeUnit("notATTRIBUTE_PERFORMANCE_RATIO"));
        assertEquals(false, wHeaterSpec.getAttributeUnit("notATTRIBUTE_VOLUME_OF_WATER_HEAT"));
        // distinct hash code to cover default cases of switches
        assertEquals(false, wHeaterSpec.getAttributeUnit(""));
    }
}
