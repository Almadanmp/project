package pt.ipp.isep.dei.project.model.device;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Room;
import pt.ipp.isep.dei.project.model.device.devicespecs.WaterHeaterSpec;
import pt.ipp.isep.dei.project.model.device.log.Log;
import pt.ipp.isep.dei.project.model.device.log.LogList;

import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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
    void seeIfPrintDeviceWorks() {
        Device d = new WaterHeater(new WaterHeaterSpec());
        d.setName("WaterHeater 3000");
        d.setNominalPower(150.0);
        String result = d.buildDeviceString();
        String expectedResult = "The device Name is WaterHeater 3000, and its NominalPower is 150.0 kW.\n";
        assertEquals(expectedResult, result);
    }

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
    void getConsumptionTestFailsWithoutColdTemp() {
        Device waterHeater = new WaterHeater(new WaterHeaterSpec());

        waterHeater.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 0.6D);
        waterHeater.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 0.9D);
        waterHeater.setAttributeValue(WaterHeaterSpec.COLD_WATER_TEMP,5D);
        Double hotT = 1.0;
        Double waterV = 300.0;
        waterHeater.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, hotT);
        waterHeater.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER_HEAT, waterV);
        double expectedResult = 0;
        double result = waterHeater.getEnergyConsumption(1);
        assertEquals(expectedResult, result);
    }

    @Test
    void getConsumptionTestFailsWithoutWaterVolume() {
        Device waterHeater = new WaterHeater(new WaterHeaterSpec());

        waterHeater.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 0.6D);
        waterHeater.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 0.9D);
        waterHeater.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER_HEAT,5D);
        Double hotT = 1.0;
        Double coldT = 300.0;
        waterHeater.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, hotT);
        waterHeater.setAttributeValue(WaterHeaterSpec.COLD_WATER_TEMP, coldT);
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

    @Test
    void seeIfGetAndSetAttributeUnit() {
        WaterHeater d1 = new WaterHeater(new WaterHeaterSpec());
        d1.getAttributeUnit(WaterHeaterSpec.HOT_WATER_TEMP);
        d1.getAttributeUnit(WaterHeaterSpec.PERFORMANCE_RATIO);
        d1.getAttributeUnit(WaterHeaterSpec.VOLUME_OF_WATER);
        d1.getAttributeUnit(WaterHeaterSpec.COLD_WATER_TEMP);
        d1.getAttributeUnit(WaterHeaterSpec.VOLUME_OF_WATER_HEAT);
        String expectedResult1 = "L";
        String expectedResult2 = "ºC";
        String expectedResult3 = "";
        Object result1 = d1.getAttributeUnit(WaterHeaterSpec.HOT_WATER_TEMP);
        Object result2 = d1.getAttributeUnit(WaterHeaterSpec.PERFORMANCE_RATIO);
        Object result3 = d1.getAttributeUnit(WaterHeaterSpec.VOLUME_OF_WATER);
        Object result4 = d1.getAttributeUnit(WaterHeaterSpec.COLD_WATER_TEMP);
        Object result5 = d1.getAttributeUnit(WaterHeaterSpec.VOLUME_OF_WATER_HEAT);
        assertEquals(expectedResult2, result1);
        assertEquals(expectedResult3, result2);
        assertEquals(expectedResult1, result3);
        assertEquals(expectedResult2, result4);
        assertEquals(expectedResult1, result5);
    }
    @Test
    void seeIfGetAndSetAttributeNames() {
        WaterHeater d1 = new WaterHeater(new WaterHeaterSpec());
        d1.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 12D);
        d1.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 40D);
        d1.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 234D);
        List<String> result = d1.getAttributeNames();
        assertTrue(result.contains(WaterHeaterSpec.VOLUME_OF_WATER));
        assertTrue(result.contains(WaterHeaterSpec.HOT_WATER_TEMP));
        assertTrue(result.contains(WaterHeaterSpec.PERFORMANCE_RATIO));
        assertEquals(result.size(), 3);
    }
    @Test
    void seeIfGetAndSetAttributeValues() {
        WaterHeater d1 = new WaterHeater(new WaterHeaterSpec());
        d1.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 12D);
        d1.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 40D);
        d1.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 234D);
        String attribute = "Volume Of Water";
        Double expectedResult = 2.0;
        double attributeValue = 2.0;
        boolean setResult = d1.setAttributeValue(attribute, attributeValue);
        Object getResult = d1.getAttributeValue(attribute);
        assertEquals(expectedResult, getResult);
        assertTrue(setResult);

        attribute = "Hot Water Temperature";
        attributeValue = 3.0;
        expectedResult = 3.0;
        setResult = d1.setAttributeValue(attribute, attributeValue);
        getResult = d1.getAttributeValue(attribute);
        assertEquals(expectedResult, getResult);
        assertTrue(setResult);

        attribute = "Cold Water Temperature";
        attributeValue = 4.0;
        expectedResult = 4.0;
        setResult = d1.setAttributeValue(attribute, attributeValue);
        getResult = d1.getAttributeValue(attribute);
        assertEquals(expectedResult, getResult);
        assertTrue(setResult);

        attribute = "Performance Ratio";
        expectedResult = 5.0;
        attributeValue = 5.0;
        setResult = d1.setAttributeValue(attribute, attributeValue);
        getResult = d1.getAttributeValue(attribute);
        assertEquals(expectedResult, getResult);
        assertTrue(setResult);

        attribute = "Volume Of Water To Heat";
        expectedResult = 10.0;
        attributeValue = 10.0;
        setResult = d1.setAttributeValue(attribute, attributeValue);
        getResult = d1.getAttributeValue(attribute);
        assertEquals(expectedResult, getResult);
        assertTrue(setResult);
    }
    @Test
    void getLogList() {
        WaterHeater d1 = new WaterHeater(new WaterHeaterSpec());
        d1.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER_HEAT, 12D);
        Log log = new Log(1, new GregorianCalendar(2019, 1, 1).getTime(),
                new GregorianCalendar(2019, 1, 1).getTime());
        LogList logList = d1.getLogList();
        d1.addLog(log);
        LogList result = d1.getLogList();
        assertEquals(logList, result);
    }

    @Test
    void getLogListBreakTest() {
        WaterHeater d1 = new WaterHeater(new WaterHeaterSpec());
        d1.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER_HEAT, 12D);
        LogList logList = new LogList();
        LogList result = d1.getLogList();
        assertEquals(logList, result);
    }

    @Test
    void addLogListFalse() {
        WaterHeater d1 = new WaterHeater(new WaterHeaterSpec());
        d1.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER_HEAT, 12D);
        Log log = new Log(1, new GregorianCalendar(2019, 1, 1).getTime(),
                new GregorianCalendar(2019, 1, 1).getTime());
        d1.addLog(log);
        assertFalse(d1.addLog(log));
    }

    @Test
    void testDeactivateBothConditions() {
        //Arrange
        WaterHeater d1 = new WaterHeater(new WaterHeaterSpec());
        d1.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER_HEAT, 12D);
        WaterHeater d2 = new WaterHeater(new WaterHeaterSpec());
        d2.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER_HEAT, 12D);
        //Act
        d1.deactivate();
        boolean actualResult1 = d1.deactivate(); //Deactivates a second time to get false return.
        boolean actualResult2 = d2.deactivate(); //Deactivate once for true return.
        //Assert
        assertFalse(actualResult1);
        assertTrue(actualResult2);
    }

    @Test
    void addLogToInactiveTrue() {
        //Arrange
        WaterHeater d1 = new WaterHeater(new WaterHeaterSpec());
        d1.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER_HEAT, 12D);
        Log log = new Log(1, new GregorianCalendar(2019, 1, 1).getTime(),
                new GregorianCalendar(2019, 1, 1).getTime());
        //Act
        d1.deactivate();
        boolean result = d1.addLog(log);
        //Assert
        assertFalse(result);
    }


    @Test
    void ensureThatWeDeactivateADevice() {
        WaterHeater d1 = new WaterHeater(new WaterHeaterSpec());
        d1.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER_HEAT, 12D);
        boolean actualResult = d1.deactivate();
        assertTrue(actualResult);
    }

    @Test
    void ensureThatWeDoNotDeactivate() {
        WaterHeater d1 = new WaterHeater(new WaterHeaterSpec());
        d1.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER_HEAT, 12D);
        d1.deactivate();
        boolean actualResult = d1.deactivate();
        assertFalse(actualResult);
    }

    @Test
    void addLogTrue() {
        WaterHeater d1 = new WaterHeater(new WaterHeaterSpec());
        d1.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER_HEAT, 12D);
        Log log = new Log(1, new GregorianCalendar(2019, 1, 1).getTime(),
                new GregorianCalendar(2019, 1, 1).getTime());
        boolean result = d1.addLog(log);
        assertTrue(result);
    }

    @Test
    void seeEqualToSameObject() {
        WaterHeater d = new WaterHeater(new WaterHeaterSpec());
        d.setName("WMOne");
        boolean actualResult = d.equals(d);
        assertTrue(actualResult);
    }

    @Test
    void seeEqualsToDifObject() {
        WaterHeater d = new WaterHeater(new WaterHeaterSpec());
        d.setName("WHOne");
        d.setNominalPower(12.0);
        d.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER_HEAT, 34);
        WaterHeater d2 = new WaterHeater(new WaterHeaterSpec());
        d2.setName("WHTwo");
        d2.setNominalPower(12.0);
        d.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER_HEAT, 45);

        boolean actualResult = d.equals(d2);
        assertFalse(actualResult);
    }


    @Test
    void seeEqualsToDifTypeObject() {
        WaterHeater d = new WaterHeater(new WaterHeaterSpec());
        d.setName("WMOne");
        d.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER_HEAT, 56);
        Room room = new Room("quarto", 1, 80, 2, 2);

        boolean actualResult = d.equals(room);
        assertFalse(actualResult);
    }

    @Test
    void seeEqualsToNullObject() {
        WaterHeater d = new WaterHeater(new WaterHeaterSpec());
        d.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER_HEAT, 34);
        boolean actualResult = d.equals(null);

        assertFalse(actualResult);
    }

    @Test
    void getConsumption() {
        WaterHeater d = new WaterHeater(new WaterHeaterSpec());
        d.setNominalPower(15);
        d.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER_HEAT, 12D);
        d.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 12D);
        d.setAttributeValue(WaterHeaterSpec.COLD_WATER_TEMP, 5D);
        d.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 12D);
        d.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 12D);
        double expectedResult = 0.019538399999999997;
        double result = d.getEnergyConsumption(24);
        assertEquals(expectedResult, result);
    }

    @Test
    void getConsumptionTimeZero() {
        WaterHeater d = new WaterHeater(new WaterHeaterSpec());
        d.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER_HEAT, 12D);
        d.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 12D);
        d.setAttributeValue(WaterHeaterSpec.COLD_WATER_TEMP, 12D);
        d.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 12D);
        d.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 12D);
        d.setNominalPower(15);
        double expectedResult = 0;
        double result = d.getEnergyConsumption(0);
        assertEquals(expectedResult, result);
    }



    @Test
     void hashCodeDummyTest() {
        WaterHeater d1 = new WaterHeater(new WaterHeaterSpec());
        d1.setNominalPower(12.0);
        d1.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER_HEAT,4D);
        int expectedResult = 1;
        int actualResult = d1.hashCode();
        assertEquals(expectedResult, actualResult);
    }
}
