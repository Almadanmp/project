package pt.ipp.isep.dei.project.model.device;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.RoomList;
import pt.ipp.isep.dei.project.model.device.devicespecs.WaterHeaterSpec;
import pt.ipp.isep.dei.project.model.device.log.Log;
import pt.ipp.isep.dei.project.model.device.log.LogList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * WaterHeater device tests class.
 */

class WaterHeaterTest {
    // Common testing artifacts for tests in this class.

    private WaterHeater validHeater;
    private Log validLog;

    @BeforeEach
    void arrangeArtifacts() {
        validHeater = new WaterHeater(new WaterHeaterSpec());
        validHeater.setName("WaterHeater");
        validHeater.setNominalPower(12.0);
        validHeater.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER_HEAT, 300D);
        validHeater.setAttributeValue(WaterHeaterSpec.COLD_WATER_TEMP, 12D);
        validHeater.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 0.6D);
        validHeater.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 30D);
        validHeater.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 0.9D);
        validLog = new Log(1, new GregorianCalendar(2019, Calendar.FEBRUARY, 1).getTime(),
                new GregorianCalendar(2019, Calendar.FEBRUARY, 1).getTime());
        validHeater.addLog(validLog);
    }

    @Test
    void seeIfGetDeviceTypeWorks() {
        // Arrange

        String expectedResult = "WaterHeater";

        // Act

        String result = validHeater.getType();

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfBuildDeviceWorks() {
        // Arrange

        String expectedResult = "The device Name is WaterHeater, and its NominalPower is 12.0 kW.\n";

        // Act

        String result = validHeater.buildString();

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetConsumptionWorks() {
        // Arrange

        Double expectedResult = 0.003925125;

        // Act

        Double result = validHeater.getEnergyConsumption(1);

        // Assert

        assertEquals(expectedResult, result);
    }


    @Test
    void seeIfGetConsumptionWorksZero() {
        // Arrange

        validHeater.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 0.6D);
        validHeater.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 30D);
        validHeater.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 0.9D);
        validHeater.setAttributeValue(WaterHeaterSpec.COLD_WATER_TEMP, 200D);
        validHeater.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER_HEAT, 300D);
        double expectedResult = 0;

        // Act

        double result = validHeater.getEnergyConsumption(1);

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetConsumptionWorksColdWaterEqualsHotWater() {
        // Arrange

        Double coldT = 25.0;
        Double hotT = 25.0;
        validHeater.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, hotT);
        validHeater.setAttributeValue(WaterHeaterSpec.COLD_WATER_TEMP, coldT);
        double expectedResult = 0;

        // Act

        double result = validHeater.getEnergyConsumption(1);

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetConsumptionWorksColdWaterSmallerThanHotWater() {
        // Arrange

        Double coldT = 2.0;
        Double hotT = 25.0;
        validHeater.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, hotT);
        validHeater.setAttributeValue(WaterHeaterSpec.COLD_WATER_TEMP, coldT);
        double expectedResult = 0.0050154375;

        // Act

        double result = validHeater.getEnergyConsumption(1);

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAttributeUnitWorks() {
        // Arrange

        String expectedResult1 = "L";
        String expectedResult2 = "ºC";
        String expectedResult3 = "";

        // Act

        Object result1 = validHeater.getAttributeUnit(WaterHeaterSpec.HOT_WATER_TEMP);
        Object result2 = validHeater.getAttributeUnit(WaterHeaterSpec.PERFORMANCE_RATIO);
        Object result3 = validHeater.getAttributeUnit(WaterHeaterSpec.VOLUME_OF_WATER);
        Object result4 = validHeater.getAttributeUnit(WaterHeaterSpec.COLD_WATER_TEMP);
        Object result5 = validHeater.getAttributeUnit(WaterHeaterSpec.VOLUME_OF_WATER_HEAT);

        // Assert

        assertEquals(expectedResult2, result1);
        assertEquals(expectedResult3, result2);
        assertEquals(expectedResult1, result3);
        assertEquals(expectedResult2, result4);
        assertEquals(expectedResult1, result5);
    }

    @Test
    void seeIfGetAttributeNamesWorks() {
        // Act

        List<String> result = validHeater.getAttributeNames();

        // Assert

        assertTrue(result.contains(WaterHeaterSpec.VOLUME_OF_WATER));
        assertTrue(result.contains(WaterHeaterSpec.HOT_WATER_TEMP));
        assertTrue(result.contains(WaterHeaterSpec.PERFORMANCE_RATIO));
        assertEquals(result.size(), 3);
    }

    @Test
    void seeIfSetGetAttributeValueWork() {
        // Arrange

        String attribute = "Volume Of Water";
        Double expectedResult = 2.0;

        // Act

        boolean setResult = validHeater.setAttributeValue(attribute, 2.0);
        Object getResult = validHeater.getAttributeValue(attribute);

        // Assert

        assertEquals(expectedResult, getResult);
        assertTrue(setResult);

        // Arrange

        attribute = "Hot Water Temperature";
        expectedResult = 3.0;

        // Act

        setResult = validHeater.setAttributeValue(attribute, 3.0);
        getResult = validHeater.getAttributeValue(attribute);

        // Assert

        assertEquals(expectedResult, getResult);
        assertTrue(setResult);

        // Arrange

        attribute = "Cold Water Temperature";
        expectedResult = 4.0;

        // Act

        setResult = validHeater.setAttributeValue(attribute, 4.0);
        getResult = validHeater.getAttributeValue(attribute);

        // Assert

        assertEquals(expectedResult, getResult);
        assertTrue(setResult);

        // Arrange

        attribute = "Performance Ratio";
        expectedResult = 5.0;

        // Act

        setResult = validHeater.setAttributeValue(attribute, 5.0);
        getResult = validHeater.getAttributeValue(attribute);

        // Assert

        assertEquals(expectedResult, getResult);
        assertTrue(setResult);

        // Arrange

        attribute = "Volume Of Water To Heat";
        expectedResult = 10.0;

        // Act

        setResult = validHeater.setAttributeValue(attribute, 10.0);
        getResult = validHeater.getAttributeValue(attribute);

        // Assert

        assertEquals(expectedResult, getResult);
        assertTrue(setResult);
    }

    @Test
    void seeIfGetLogListWorks() {
        // Arrange

        LogList expectedResult = new LogList();
        expectedResult.addLog(validLog);

        // Act

        LogList result = validHeater.getLogList();

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetLogListWorksEmpty() {
        // Arrange

        validHeater = new WaterHeater(new WaterHeaterSpec());
        LogList expectedResult = new LogList();

        // Act

        LogList result = validHeater.getLogList();

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfAddLogWorksDuplicate() {
        //Arrange


        // Act

        boolean result = validHeater.addLog(validLog);

        // Assert

        assertFalse(result);
    }

    @Test
    void seeIfDeactivateWorks() {
        // Act

        boolean actualResult1 = validHeater.deactivate(); // Deactivates device.
        boolean actualResult2 = validHeater.deactivate(); // Fails to deactivate because device was already deactivated.

        // Assert

        assertTrue(actualResult1);
        assertFalse(actualResult2);
    }

    @Test
    void seeIfAddLogWorksDeactivatedDevice() {
        // Act

        validHeater.deactivate();
        boolean result = validHeater.addLog(validLog);

        //Assert

        assertFalse(result);
    }


    @Test
    void seeIfAddLogWorks() {
        // Arrange

        validHeater = new WaterHeater(new WaterHeaterSpec());

        // Act

        boolean result = validHeater.addLog(validLog);

        // Assert

        assertTrue(result);
    }

    @Test
    void seeIfEqualsWorksOnItself() {
        assertEquals(validHeater, validHeater);
    }

    @Test
    void seeIfEqualsWorksFalse() {
        // Arrange

        WaterHeater testHeater = new WaterHeater(new WaterHeaterSpec());
        testHeater.setName("WHTwo");
        testHeater.setNominalPower(12.0);

        // Act

        boolean actualResult = validHeater.equals(testHeater);

        // Assert

        assertFalse(actualResult);
    }


    @Test
    void seeIfdTQuotientWorks() {
        // Arrange

        validHeater.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 12D);
        validHeater.setAttributeValue(WaterHeaterSpec.COLD_WATER_TEMP, 300D);
        double expectedResultColdWater = 0;

        // Act

        double resultColdWater = validHeater.dTQuotient();

        // Assert

        assertEquals(expectedResultColdWater, resultColdWater);

        // Arrange

        validHeater.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 12D);
        validHeater.setAttributeValue(WaterHeaterSpec.COLD_WATER_TEMP, 12D);
        double expectedResultSameValues = 0;

        // Act

        double resultSameValues = validHeater.dTQuotient();

        // Assert

        assertEquals(expectedResultSameValues, resultSameValues);

        // Arrange

        validHeater.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 300D);
        validHeater.setAttributeValue(WaterHeaterSpec.COLD_WATER_TEMP, 12D);
        double expectedResultHotWater = 288.0;

        // Act

        double resultHotWater = validHeater.dTQuotient();

        // Assert

        assertEquals(expectedResultHotWater, resultHotWater);
    }

    @Test
    void seeIfEqualsWorksNotAnInstance() {
        assertNotEquals(validHeater, new RoomList());
    }

    @Test
    void seeEqualsToNullObject() {
        assertNotEquals(validHeater, null);
    }

    @Test
    void seeIfGetConsumptionWorksTimeZero() {
        // Arrange

        double expectedResult = 0;

        // Act

        double result = validHeater.getEnergyConsumption(0);

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfIsLogListEmptyWorks() {
        //Act

        boolean actualResult1 = validHeater.isLogListEmpty();

        //Assert

        assertFalse(actualResult1);

        //Arrange To Remove Log

        validHeater = new WaterHeater(new WaterHeaterSpec());

        //Act

        boolean actualResult2 = validHeater.isLogListEmpty();

        //Assert

        assertTrue(actualResult2);
    }


    @Test
    void hashCodeDummyTest() {
        // Arrange

        int expectedResult = 1;

        // Act

        int actualResult = validHeater.hashCode();

        // Assert

        assertEquals(expectedResult, actualResult);
    }
}
