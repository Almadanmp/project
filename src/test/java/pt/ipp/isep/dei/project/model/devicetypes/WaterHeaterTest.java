package pt.ipp.isep.dei.project.model.devicetypes;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WaterHeaterTest {

    @Test
    public void getTypeTest() {
        WaterHeater waterHeater = new WaterHeater(200, 25, 10);
        DeviceType expectedResult = DeviceType.WATER_HEATER;
        DeviceType result = waterHeater.getType();
        assertEquals(expectedResult, result);
    }

    @Test
    public void getConsumptionTest() {
        WaterHeater waterHeater = new WaterHeater(200, 25, 10, 0.9);
        double expectedResult = 3140.1;
        double result = waterHeater.getConsumption();
        assertEquals(expectedResult, result);
    }

    @Test
    public void getConsumptionWithRatioTest() {
        WaterHeater waterHeater = new WaterHeater(200, 25, 10, 1.8);
        double expectedResult = 6280.2;
        double result = waterHeater.getConsumption();
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfsetVolumeWaterWorks() {
        WaterHeater waterHeater = new WaterHeater();
        waterHeater.setVolumeOfWater(12);
        double result = waterHeater.getVolumeWater();
        double expectedResult = 12.0;
        assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfGetNominalPower() {
        WaterHeater waterHeater = new WaterHeater();
        double expectedResult = 6;
        waterHeater.getNominalPower();
        waterHeater.setNominalPower(6);
        double result = waterHeater.getNominalPower();
        assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfGetAndSetAttributeValue() {
        WaterHeater waterHeater = new WaterHeater();
        String attribute = "nominalPower";
        double expectedResult = 6;
        boolean setResult = waterHeater.setAttributeValue(attribute, 6);
        double getResult = waterHeater.getAttributeValue(attribute);
        assertEquals(expectedResult, getResult);
        assertTrue(setResult);
    }

    @Test
    public void seeIfGetAttributeValueInvalid() {
        WaterHeater waterHeater = new WaterHeater();
        String attribute = "invalid";
        double expectedResult = 0;
        double result = waterHeater.getAttributeValue(attribute);
        assertEquals(expectedResult, result);
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
        assertTrue(result.contains("nominalPower"));
        assertTrue(result.contains("volumeOfWater"));
        assertTrue(result.contains("hotWaterTemperature"));
        assertTrue(result.contains("coldWaterTemperature"));
        assertTrue(result.contains("performanceRatio"));
        assertEquals(result.size(), 5);
    }

    @Test
    public void seeIfGetAndSetAttributeValues() {
        WaterHeater waterHeater = new WaterHeater();
        String attribute = "nominalPower";
        double expectedResult = 1;
        boolean setResult = waterHeater.setAttributeValue(attribute, 1);
        double getResult = waterHeater.getAttributeValue(attribute);
        assertEquals(expectedResult, getResult);
        assertTrue(setResult);

        attribute = "volumeOfWater";
        expectedResult = 2;
        setResult = waterHeater.setAttributeValue(attribute, 2);
        getResult = waterHeater.getAttributeValue(attribute);
        assertEquals(expectedResult, getResult);
        assertTrue(setResult);

        attribute = "hotWaterTemperature";
        expectedResult = 3;
        setResult = waterHeater.setAttributeValue(attribute, 3);
        getResult = waterHeater.getAttributeValue(attribute);
        assertEquals(expectedResult, getResult);
        assertTrue(setResult);

        attribute = "coldWaterTemperature";
        expectedResult = 4;
        setResult = waterHeater.setAttributeValue(attribute, 4);
        getResult = waterHeater.getAttributeValue(attribute);
        assertEquals(expectedResult, getResult);
        assertTrue(setResult);

        attribute = "performanceRatio";
        expectedResult = 5;
        setResult = waterHeater.setAttributeValue(attribute, 5);
        getResult = waterHeater.getAttributeValue(attribute);
        assertEquals(expectedResult, getResult);
        assertTrue(setResult);
    }
}
