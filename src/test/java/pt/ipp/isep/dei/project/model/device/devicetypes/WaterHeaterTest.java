package pt.ipp.isep.dei.project.model.device.devicetypes;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.TestUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * WaterHeater tests class.
 */

class WaterHeaterTest {

    @Test
    void getTypeTest() {
        WaterHeater waterHeater = new WaterHeater();
        DeviceType expectedResult = DeviceType.WATER_HEATER;
        DeviceType result = waterHeater.getType();
        assertEquals(expectedResult, result);
    }

    //getConsumption Tests

    @Test
    void getConsumptionTest() {
        WaterHeater waterHeater = new WaterHeater();
        waterHeater.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 0.6D);
        waterHeater.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 30D);
        waterHeater.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 0.9D);
        Double coldT = 12.0;
        Double waterV = 300.0;
        Double hotT = 25.0;
        waterHeater.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, hotT);
        waterHeater.setAttributeValue("coldWaterTemperature", coldT);
        waterHeater.setAttributeValue("volumeOfWaterToHeat", waterV);
        Double expectedResult = 0.17008875;
        Double result = waterHeater.getConsumption();
        assertEquals(expectedResult, result);
    }

    @Test
    void getConsumptionWithSetsFailsTest() {
        WaterHeater waterHeater = new WaterHeater();
        Double coldT = 30.0;
        Double waterV = 200.0;
        Double hotT = 25.0;
        waterHeater.setAttributeValue("coldWaterTemperature", coldT);
        waterHeater.setAttributeValue("hotWaterTemperature", hotT);
        waterHeater.setAttributeValue("volumeOfWaterToHeat", waterV);
        double expectedResult = -1;
        double result = waterHeater.getConsumption();
        assertEquals(expectedResult, result);
    }

    @Test
    void getConsumptionTestFails() {
        WaterHeater waterHeater = new WaterHeater();
        waterHeater.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 0.6D);
        waterHeater.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 30D);
        waterHeater.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 0.9D);
        Double coldT = 200.0;
        Double waterV = 300.0;
        waterHeater.setAttributeValue("coldWaterTemperature", coldT);
        waterHeater.setAttributeValue("volumeOfWaterToHeat", waterV);
        double expectedResult = -1;
        double result = waterHeater.getConsumption();
        assertEquals(expectedResult, result);
    }

    @Test
    void getConsumptionTestColdWaterEqualsHotWater() {
        WaterHeater waterHeater = new WaterHeater();
        Double coldT = 25.0;
        Double waterV = 100.0;
        Double hotT = 25.0;
        waterHeater.setAttributeValue("coldWaterTemperature", coldT);
        waterHeater.setAttributeValue("volumeOfWaterToHeat", waterV);
        waterHeater.setAttributeValue("hotWaterTemperature", hotT);
        double expectedResult = -1;
        double result = waterHeater.getConsumption();
        assertEquals(expectedResult, result);
    }

    @Test
    void getConsumptionTestColdWaterEqualsHotWaterDifString() {
        WaterHeater waterHeater = new WaterHeater();
        waterHeater.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 0.6D);
        waterHeater.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 30D);
        waterHeater.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 0.9D);
        Double coldT = 25.0;
        Double waterV = 100.0;
        Double hotT = 25.0;
        waterHeater.setAttributeValue("dgfhfjg", coldT);
        waterHeater.setAttributeValue("volumeOfWaterToHeat", waterV);
        waterHeater.setAttributeValue("adsdfgh", hotT);
        double expectedResult = 0.1308375;
        double result = waterHeater.getConsumption();
        assertEquals(expectedResult, result);
    }

    @Test
    void getConsumptionTestColdWaterMinorHotWater() {
        WaterHeater waterHeater = new WaterHeater();
        waterHeater.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 0.6D);
        waterHeater.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 30D);
        waterHeater.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 0.9D);
        Double coldT = 2.0;
        Double waterV = 100.0;
        Double hotT = 25.0;
        waterHeater.setAttributeValue("coldWaterTemperature", coldT);
        waterHeater.setAttributeValue("volumeOfWaterToHeat", waterV);
        waterHeater.setAttributeValue("hotWaterTemperature", hotT);
        double expectedResult = 0.10030875;
        double result = waterHeater.getConsumption();
        assertEquals(expectedResult, result);
    }

    @Test
    void getConsumptionTestColdWaterMinorHotWaterDifferentString() {
        WaterHeater waterHeater = new WaterHeater();
        waterHeater.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 0.6D);
        waterHeater.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 30D);
        waterHeater.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 0.9D);
        double expectedResult = 0.0;
        double result = waterHeater.getConsumption();
        assertEquals(expectedResult, result);
    }

    @Test
    void getConsumptionTestColdWaterMinorHotWater2() {
        WaterHeater waterHeater = new WaterHeater();
        Double coldT = 30.0;
        Double waterV = 800.0;
        Double hotT = 25.0;
        waterHeater.setAttributeValue("coldWaterTemperature", coldT);
        waterHeater.setAttributeValue("volumeOfWaterToHeat", waterV);
        waterHeater.setAttributeValue("hotWaterTemperature", hotT);
        double expectedResult = -1;
        double result = waterHeater.getConsumption();
        assertEquals(expectedResult, result);
    }

    @Test
    void getConsumptionTestColdWaterMinorHotWater3() {
        WaterHeater waterHeater = new WaterHeater();
        Double coldT = 25.0;
        Double waterV = 800.0;
        Double hotT = 25.0;
        waterHeater.setAttributeValue("coldWaterTemperature", coldT);
        waterHeater.setAttributeValue("volumeOfWaterToHeat", waterV);
        waterHeater.setAttributeValue("hotWaterTemperature", hotT);
        double expectedResult = -1;
        double result = waterHeater.getConsumption();
        assertEquals(expectedResult, result);
    }


    //Test Attributes

    @Test
    void seeIfGetAndSetAttributeValue() {
        WaterHeater waterHeater = new WaterHeater();
        waterHeater.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 0.6D);
        waterHeater.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 30D);
        waterHeater.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 0.9D);
        Double volumeOfWater = 0.6;
        String attribute = "volumeOfWater";
        Double expectedResult = 0.6;
        boolean setResult = waterHeater.setAttributeValue(attribute, volumeOfWater);
        Object getResult = waterHeater.getAttributeValue(attribute);
        assertEquals(expectedResult, getResult);
        assertTrue(setResult);
    }


    @Test
    void seeIfSetAttributeValueInvalid() {
        WaterHeater waterHeater = new WaterHeater();
        waterHeater.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 12D);
        waterHeater.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 40D);
        waterHeater.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 234D);
        Double value = 0.6;
        String attribute = "invalid";
        boolean result = waterHeater.setAttributeValue(attribute, value);
        assertFalse(result);
    }

    @Test
    void seeIfGetAttributeNames() {
        WaterHeater waterHeater = new WaterHeater();
        waterHeater.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 12D);
        waterHeater.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 40D);
        waterHeater.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 234D);
        List<String> result = waterHeater.getAttributeNames();
        assertTrue(result.contains("volumeOfWater"));
        assertTrue(result.contains("hotWaterTemperature"));
        assertTrue(result.contains("coldWaterTemperature"));
        assertTrue(result.contains("performanceRatio"));
        assertTrue(result.contains("volumeOfWaterToHeat"));
        assertEquals(result.size(), 5);
    }

    @Test
    void seeIfGetAttributeValueDefaultTest() {
        WaterHeater waterHeater = new WaterHeater();
        String attribute = "Lisboa";
        Double expectedResult = 0.0;
        Object getResult = waterHeater.getAttributeValue(attribute);
        assertEquals(expectedResult, getResult);
    }

    @Test
    void seeIfGetAndSetAttributeValues() {
        WaterHeater waterHeater = new WaterHeater();
        waterHeater.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 12D);
        waterHeater.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 40D);
        waterHeater.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 234D);
        String attribute = "volumeOfWater";
        Double expectedResult = 2.0;
        Double attributeValue = 2.0;
        boolean setResult = waterHeater.setAttributeValue(attribute, attributeValue);
        Object getResult = waterHeater.getAttributeValue(attribute);
        assertEquals(expectedResult, getResult);
        assertTrue(setResult);

        attribute = "hotWaterTemperature";
        attributeValue = 3.0;
        expectedResult = 3.0;
        setResult = waterHeater.setAttributeValue(attribute, attributeValue);
        getResult = waterHeater.getAttributeValue(attribute);
        assertEquals(expectedResult, getResult);
        assertTrue(setResult);

        attribute = "coldWaterTemperature";
        attributeValue = 4.0;
        expectedResult = 4.0;
        setResult = waterHeater.setAttributeValue(attribute, attributeValue);
        getResult = waterHeater.getAttributeValue(attribute);
        assertEquals(expectedResult, getResult);
        assertTrue(setResult);

        attribute = "performanceRatio";
        expectedResult = 5.0;
        attributeValue = 5.0;
        setResult = waterHeater.setAttributeValue(attribute, attributeValue);
        getResult = waterHeater.getAttributeValue(attribute);
        assertEquals(expectedResult, getResult);
        assertTrue(setResult);

        attribute = "volumeOfWaterToHeat";
        expectedResult = 10.0;
        attributeValue = 10.0;
        setResult = waterHeater.setAttributeValue(attribute, attributeValue);
        getResult = waterHeater.getAttributeValue(attribute);
        assertEquals(expectedResult, getResult);
        assertTrue(setResult);
    }

    @Test
    void seeIFSetAttributeValuesFails() {
        WaterHeater waterHeater = new WaterHeater();
        waterHeater.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 12D);
        waterHeater.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 40D);
        waterHeater.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 234D);
        String attribute = "volumeOfWater";
        int attributeValue = 2;
        boolean setResult = waterHeater.setAttributeValue(attribute, attributeValue);
        assertFalse(setResult);

        attribute = "hotWaterTemperature";
        attributeValue = 3;
        setResult = waterHeater.setAttributeValue(attribute, attributeValue);
        assertFalse(setResult);

        attribute = "coldWaterTemperature";
        attributeValue = 4;
        setResult = waterHeater.setAttributeValue(attribute, attributeValue);
        assertFalse(setResult);

        attribute = "performanceRatio";
        attributeValue = 5;
        setResult = waterHeater.setAttributeValue(attribute, attributeValue);
        assertFalse(setResult);

        attribute = "volumeOfWaterToHeat";
        attributeValue = 10;
        setResult = waterHeater.setAttributeValue(attribute, attributeValue);
        assertFalse(setResult);
    }

    @Test
    void seeIFSetAttributeValuesFails2() {
        WaterHeater waterHeater = new WaterHeater();
        waterHeater.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 12D);
        waterHeater.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 40D);
        waterHeater.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 234D);

        String attribute = "njfdjkndfk";
        int attributeValue = 2;
        boolean setResult = waterHeater.setAttributeValue(attribute, attributeValue);
        assertFalse(setResult);

        attribute = "htfcf";
        attributeValue = 3;
        setResult = waterHeater.setAttributeValue(attribute, attributeValue);
        assertFalse(setResult);

        attribute = "fhj";
        attributeValue = 4;
        setResult = waterHeater.setAttributeValue(attribute, attributeValue);
        assertFalse(setResult);

        attribute = "fhjg";
        attributeValue = 5;
        setResult = waterHeater.setAttributeValue(attribute, attributeValue);
        assertFalse(setResult);

        attribute = "gfdjcktuyvuh";
        attributeValue = 10;
        setResult = waterHeater.setAttributeValue(attribute, attributeValue);
        assertFalse(setResult);
    }

    @Test
    void seeIfGetAndSetAttributeValues2() {
        WaterHeater waterHeater = new WaterHeater();
        waterHeater.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 12D);
        waterHeater.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 40D);
        waterHeater.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 234D);
        Double attributeValue = 3.0;
        boolean setResult = waterHeater.setAttributeValue(null, attributeValue);
        assertFalse(setResult);
    }

    @Test
    void testGetAttributeCoveringAllCases() {
        //Arrange
        WaterHeater waterHeater = new WaterHeater();
        waterHeater.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 5D);
        waterHeater.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 5D);
        waterHeater.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 5D);

        // original strings:
        assertEquals(5.0, waterHeater.getAttributeValue("volumeOfWater"));
        assertEquals(5.0, waterHeater.getAttributeValue("hotWaterTemperature"));
        assertEquals(0.0, waterHeater.getAttributeValue("coldWaterTemperature"));
        assertEquals(5.0, waterHeater.getAttributeValue("performanceRatio"));
        assertEquals(0.0, waterHeater.getAttributeValue("volumeOfWaterToHeat"));

        // same hash codes, but different strings:
        assertEquals(0.0, waterHeater.getAttributeValue("\0volumeOfWater"));
        assertEquals(0.0, waterHeater.getAttributeValue("\0hotWaterTemperature"));
        assertEquals(0.0, waterHeater.getAttributeValue("\0coldWaterTemperature"));
        assertEquals(0.0, waterHeater.getAttributeValue("\0performanceRatio"));
        assertEquals(0.0, waterHeater.getAttributeValue("\0volumeOfWaterToHeat"));

        // distinct hash code to cover default cases of switches
        assertEquals(0.0, waterHeater.getAttributeValue(""));
    }

    @Test
    void testSetAttributeCoveringAllCases() {
        //Arrange
        WaterHeater waterHeater = new WaterHeater();
        Double attribute = 6.0;

        // original strings:
        assertTrue(waterHeater.setAttributeValue("volumeOfWater", attribute));
        assertTrue(waterHeater.setAttributeValue("hotWaterTemperature", attribute));
        assertTrue(waterHeater.setAttributeValue("coldWaterTemperature", attribute));
        assertTrue(waterHeater.setAttributeValue("performanceRatio", attribute));
        assertTrue(waterHeater.setAttributeValue("volumeOfWaterToHeat", attribute));

        // same hash codes, but different strings:
        assertFalse(waterHeater.setAttributeValue("\0volumeOfWater", attribute));
        assertFalse(waterHeater.setAttributeValue("\0hotWaterTemperature", attribute));
        assertFalse(waterHeater.setAttributeValue("\0coldWaterTemperature", attribute));
        assertFalse(waterHeater.setAttributeValue("\0performanceRatio", attribute));
        assertFalse(waterHeater.setAttributeValue("\0volumeOfWaterToHeat", attribute));

        // distinct hash code to cover default cases of switches
        assertFalse(waterHeater.setAttributeValue("", attribute));
    }
}
