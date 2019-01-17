package pt.ipp.isep.dei.project.model.devicetypes;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * WaterHeater tests class.
 */

public class WaterHeaterTest {

    @Test
    public void getTypeTest() {
        WaterHeater waterHeater = new WaterHeater(200.0, 25.0, 10.0);
        DeviceType expectedResult = DeviceType.WATER_HEATER;
        DeviceType result = waterHeater.getType();
        assertEquals(expectedResult, result);
    }

    @Test
    public void getConsumptionTest() {
        WaterHeater waterHeater = new WaterHeater(200.0, 25.0, 0.9);
        Double expectedResult = 0.0;
        Double result = waterHeater.getConsumption();
        assertEquals(expectedResult, result);
    }

    @Test
    public void getConsumptionTestFails() {
        WaterHeater waterHeater = new WaterHeater(200.0, 25.0, 0.9);
       waterHeater.setAttributeValue("coldWaterTemperature", 200);
        Double expectedResult = 0.0;
        Double result = waterHeater.getConsumption();
        assertEquals(expectedResult, result);
    }


    @Test
    public void getConsumptionWithRatioTest() {
        WaterHeater waterHeater = new WaterHeater(200.0, 25.0, 1.8);
        Double expectedResult = 0.0;
        Double result = waterHeater.getConsumption();
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfsetVolumeWaterWorks() {
        WaterHeater waterHeater = new WaterHeater();
        waterHeater.setVolumeOfWater(12);
        Double result = waterHeater.getVolumeWater();
        Double expectedResult = 12.0;
        assertEquals(expectedResult, result);
    }


    @Test
    public void seeIfGetAndSetAttributeValue() {
        WaterHeater waterHeater = new WaterHeater();
        String attribute = "volumeOfWater";
        Double expectedResult = 6.0;
        boolean setResult = waterHeater.setAttributeValue(attribute, 6.0);
        Object getResult = waterHeater.getAttributeValue(attribute);
        assertEquals(expectedResult, getResult);
        assertTrue(setResult);
    }



    @Test
    public void seeIfSetAttributeValueInvalid() {
        WaterHeater waterHeater = new WaterHeater();
        String attribute = "invalid";
        boolean result = waterHeater.setAttributeValue(attribute, 6);
        assertFalse(result);
    }

    @Test
    public void seeIfGetAttributeNames() {
        WaterHeater waterHeater = new WaterHeater();
        List<String> result = waterHeater.getAttributeNames();
        assertTrue(result.contains("volumeOfWater"));
        assertTrue(result.contains("hotWaterTemperature"));
        assertTrue(result.contains("coldWaterTemperature"));
        assertTrue(result.contains("performanceRatio"));
        assertTrue(result.contains("volumeOfWaterToHeat"));
        assertEquals(result.size(), 5);
    }

    @Test
    public void seeIfGetAttributeValueDefaultTest() {
        WaterHeater waterHeater = new WaterHeater();
        String attribute = "Lisboa";
        int expectedResult = 0;
        Object getResult = waterHeater.getAttributeValue(attribute);
        assertEquals(expectedResult, getResult);
    }

    @Test
    public void seeIfGetAndSetAttributeValues() {
        WaterHeater waterHeater = new WaterHeater();
        String attribute = "volumeOfWater";
        Double expectedResult = 2.0;
        boolean setResult = waterHeater.setAttributeValue(attribute, 2.0);
        Object getResult = waterHeater.getAttributeValue(attribute);
        assertEquals(expectedResult, getResult);
        assertTrue(setResult);

        attribute = "hotWaterTemperature";
        expectedResult = 3.0;
        setResult = waterHeater.setAttributeValue(attribute, 3.0);
        getResult = waterHeater.getAttributeValue(attribute);
        assertEquals(expectedResult, getResult);
        assertTrue(setResult);

        attribute = "coldWaterTemperature";
        expectedResult = 4.0;
        setResult = waterHeater.setAttributeValue(attribute, 4.0);
        getResult = waterHeater.getAttributeValue(attribute);
        assertEquals(expectedResult, getResult);
        assertTrue(setResult);

        attribute = "performanceRatio";
        expectedResult = 5.0;
        setResult = waterHeater.setAttributeValue(attribute, 5.0);
        getResult = waterHeater.getAttributeValue(attribute);
        assertEquals(expectedResult, getResult);
        assertTrue(setResult);

        attribute = "volumeOfWaterToHeat";
        expectedResult = 10.0;
        setResult = waterHeater.setAttributeValue(attribute, 10.0);
        getResult = waterHeater.getAttributeValue(attribute);
        assertEquals(expectedResult, getResult);
        assertTrue(setResult);
    }

    @Test
    public void seeIFSetAttributeValuesFails() {
        WaterHeater waterHeater = new WaterHeater();
        String attribute = "volumeOfWater";
        boolean setResult = waterHeater.setAttributeValue(attribute, 2);
        assertFalse(setResult);

        attribute = "hotWaterTemperature";
        setResult = waterHeater.setAttributeValue(attribute, 3);
        assertFalse(setResult);

        attribute = "coldWaterTemperature";
        setResult = waterHeater.setAttributeValue(attribute, 4);
        assertFalse(setResult);

        attribute = "performanceRatio";
        setResult = waterHeater.setAttributeValue(attribute, 5);
        assertFalse(setResult);

        attribute = "volumeOfWaterToHeat";
        setResult = waterHeater.setAttributeValue(attribute, 10);
        assertFalse(setResult);
    }
}
