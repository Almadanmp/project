package pt.ipp.isep.dei.project.model.device;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.device.devicespecs.WaterHeaterSpec;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * WaterHeater device tests class.
 */

public class WaterHeaterTest {

    @Test
    void getTypeTest() {
        Device waterHeater = new WaterHeater(new WaterHeaterSpec());
        String expectedResult = "WaterHeater";
        String result = waterHeater.getType();
        assertEquals(expectedResult, result);
    }

    //getConsumption Tests

    @Test
    void getConsumptionTest() {
        Device waterHeater = new WaterHeater(new WaterHeaterSpec());

        waterHeater.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 0.6D);
        waterHeater.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 30D);
        waterHeater.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 0.9D);
        Double coldT = 12.0;
        Double waterV = 300.0;
        Double hotT = 25.0;
        waterHeater.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, hotT);
        waterHeater.setAttributeValue(WaterHeaterSpec.COLD_WATER_TEMP, coldT);
        waterHeater.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER_HEAT, waterV);
        Double expectedResult = 0.0028348125;
        Double result = waterHeater.getEnergyConsumption(1);
        assertEquals(expectedResult, result);
    }

    @Test
    void getConsumptionWithSetsFailsTest() {
        Device waterHeater = new WaterHeater(new WaterHeaterSpec());

        Double coldT = 30.0;
        Double waterV = 200.0;
        Double hotT = 25.0;
        waterHeater.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, hotT);
        waterHeater.setAttributeValue(WaterHeaterSpec.COLD_WATER_TEMP, coldT);
        waterHeater.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER_HEAT, waterV);
        waterHeater.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 23D);
        double expectedResult = 0;
        double result = waterHeater.getEnergyConsumption(1);
        assertEquals(expectedResult, result);
    }

    @Test
    void getConsumptionTestFails() {
        Device waterHeater = new WaterHeater(new WaterHeaterSpec());

        waterHeater.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 0.6D);
        waterHeater.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 30D);
        waterHeater.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 0.9D);
        Double coldT = 200.0;
        Double waterV = 300.0;
        waterHeater.setAttributeValue(WaterHeaterSpec.COLD_WATER_TEMP, coldT);
        waterHeater.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER_HEAT, waterV);
        double expectedResult = 0;
        double result = waterHeater.getEnergyConsumption(1);
        assertEquals(expectedResult, result);
    }

    @Test
    void getConsumptionTestColdWaterEqualsHotWater() {
        Device waterHeater = new WaterHeater(new WaterHeaterSpec());
        Double coldT = 25.0;
        Double waterV = 100.0;
        Double hotT = 25.0;
        waterHeater.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, hotT);
        waterHeater.setAttributeValue(WaterHeaterSpec.COLD_WATER_TEMP, coldT);
        waterHeater.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER_HEAT, waterV);
        waterHeater.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 23D);
        double expectedResult = 0;
        double result = waterHeater.getEnergyConsumption(1);
        assertEquals(expectedResult, result);
    }

    @Test
    void getConsumptionTestColdWaterEqualsHotWaterDifString() {
        Device waterHeater = new WaterHeater(new WaterHeaterSpec());
        waterHeater.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 0.6D);
        waterHeater.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 30D);
        waterHeater.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 0.9D);
        Double coldT = 25.0;
        Double waterV = 100.0;
        Double hotT = 25.0;
        waterHeater.setAttributeValue("dgfhfjg", coldT);
        waterHeater.setAttributeValue("Volume Of Water To Heat", waterV);
        waterHeater.setAttributeValue("adsdfgh", hotT);
        double expectedResult = 0.002180625;
        double result = waterHeater.getEnergyConsumption(1);
        assertEquals(expectedResult, result);
    }

    @Test
    void getConsumptionTestColdWaterMinorHotWater() {
        Device waterHeater = new WaterHeater(new WaterHeaterSpec());

        waterHeater.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 0.6D);
        waterHeater.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 30D);
        waterHeater.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 0.9D);
        Double coldT = 2.0;
        Double waterV = 100.0;
        Double hotT = 25.0;
        waterHeater.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, hotT);
        waterHeater.setAttributeValue(WaterHeaterSpec.COLD_WATER_TEMP, coldT);
        waterHeater.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER_HEAT, waterV);
        double expectedResult = 0.0016718125000000001;
        double result = waterHeater.getEnergyConsumption(1);
        assertEquals(expectedResult, result);
    }

    @Test
    void getConsumptionTestColdWaterMinorHotWaterDifferentString() {
        Device waterHeater = new WaterHeater(new WaterHeaterSpec());

        waterHeater.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 0.6D);
        waterHeater.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 30D);
        waterHeater.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 0.9D);
        double expectedResult = 0.0;
        double result = waterHeater.getEnergyConsumption(1);
        assertEquals(expectedResult, result);
    }
}
