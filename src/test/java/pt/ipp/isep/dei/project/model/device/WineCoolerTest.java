package pt.ipp.isep.dei.project.model.device;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Room;
import pt.ipp.isep.dei.project.model.device.devicespecs.WineCoolerSpec;
import pt.ipp.isep.dei.project.model.device.log.Log;
import pt.ipp.isep.dei.project.model.device.log.LogList;
import pt.ipp.isep.dei.project.model.device.program.WineCooler;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testng.Assert.*;

public class WineCoolerTest {
    @Test
    void seeIfGetDeviceTypeTest() {
        WineCooler d = new WineCooler(new WineCoolerSpec());
        d.setAttributeValue("Number Bottles", 12);
        String dT = "WineCooler";
        String expectedResult = dT;
        String result = d.getType();
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfEqualToSameObject() {
        WineCooler d = new WineCooler(new WineCoolerSpec());
        d.setName("WCOne");
        d.setNominalPower(12.0);
        d.setAnnualConsumption(12.3);
        d.setAttributeValue("Number Bottles", 12);
        boolean actualResult = d.equals(d);
        assertTrue(actualResult);
    }

    @Test
    void seeIfGetNominalPowerTest() {
        WineCooler d = new WineCooler(new WineCoolerSpec());
        d.setNominalPower(30);
        double expectedResult = 30;
        double result = d.getNominalPower();
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAnnualConsumptionTest() {
        WineCooler d = new WineCooler(new WineCoolerSpec());
        d.setAnnualConsumption(23);
        double expectedResult = 23;
        double result = d.getAnnualConsumption();
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfEqualsToDifObject() {
        WineCooler d = new WineCooler(new WineCoolerSpec());
        d.setName("WCOne");
        d.setNominalPower(12.0);
        d.setAttributeValue(WineCoolerSpec.NUMBER_BOTTLES, 34);
        WineCooler d2 = new WineCooler(new WineCoolerSpec());
        d2.setName("WCTwo");
        d2.setNominalPower(12.0);
        d.setAttributeValue(WineCoolerSpec.NUMBER_BOTTLES, 45);

        boolean actualResult = d.equals(d2);
        assertFalse(actualResult);
    }


    @Test
    void seeEqualsToDifTypeObject() {
        WineCooler d = new WineCooler(new WineCoolerSpec());
        d.setName("WCOne");
        d.setNominalPower(12.0);
        d.setAttributeValue(WineCoolerSpec.NUMBER_BOTTLES, 56);
        Room room = new Room("quarto", 1, 80, 2, 2);

        boolean actualResult = d.equals(room);
        assertFalse(actualResult);
    }

    @Test
    void seeEqualsToNullObject() {
        WineCooler d = new WineCooler(new WineCoolerSpec());
        d.setAttributeValue(WineCoolerSpec.NUMBER_BOTTLES, 34);
        boolean actualResult = d.equals(null);

        assertFalse(actualResult);
    }

    @Test
    void seeIfPrintDeviceWorks() {
        WineCooler d1 = new WineCooler(new WineCoolerSpec());
        d1.setAttributeValue(WineCoolerSpec.NUMBER_BOTTLES, 2);
        d1.setName("winneCooler");
        d1.setNominalPower(150.0);
        String result = d1.buildString();
        String expectedResult = "The device Name is winneCooler, and its NominalPower is 150.0 kW.\n";
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfSetNameWorks() {
        WineCooler d1 = new WineCooler(new WineCoolerSpec());
        d1.setName("frigo");
        d1.setAttributeValue(WineCoolerSpec.NUMBER_BOTTLES, 2D);
        d1.setName("frigorifico");
        String result = d1.getName();
        String expectedResult = "frigorifico";
        assertEquals(expectedResult, result);
    }

    @Test
    void hashCodeDummyTest() {
        WineCooler d1 = new WineCooler(new WineCoolerSpec());
        d1.setName("FridgeTwo");
        d1.setNominalPower(12.0);
        d1.setAttributeValue(WineCoolerSpec.NUMBER_BOTTLES, 4D);
        int expectedResult = 1;
        int actualResult = d1.hashCode();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetAndSetAttributeValue() {
        WineCooler d1 = new WineCooler(new WineCoolerSpec());
        d1.setAttributeValue(WineCoolerSpec.NUMBER_BOTTLES, 12D);
       Integer expectedResult = 33;
        d1.setAttributeValue(WineCoolerSpec.NUMBER_BOTTLES, 33);
        Object result = d1.getAttributeValue(WineCoolerSpec.NUMBER_BOTTLES);
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAndSetAttributeUnit() {
        WineCooler d1 = new WineCooler(new WineCoolerSpec());
        d1.setAttributeValue(WineCoolerSpec.NUMBER_BOTTLES, 12);
        String expectedResult = "bottles";
        d1.setAttributeValue(WineCoolerSpec.NUMBER_BOTTLES, 33);
        Object result = d1.getAttributeUnit(WineCoolerSpec.NUMBER_BOTTLES);
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAttributeNames() {
        WineCooler d1 = new WineCooler(new WineCoolerSpec());
        d1.setAttributeValue(WineCoolerSpec.NUMBER_BOTTLES, 12);

        List<String> result = d1.getAttributeNames();
        assertTrue(result.contains(WineCoolerSpec.NUMBER_BOTTLES));

        assertEquals(result.size(), 1);
    }

    @Test
    void ensureThatWeDeactivateADevice() {
        WineCooler d1 = new WineCooler(new WineCoolerSpec());
        boolean expectedResult = true;
        boolean actualResult = d1.deactivate();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void ensureThatWeDoNotDeactivate() {
        WineCooler d1 = new WineCooler(new WineCoolerSpec());
        d1.deactivate();
        boolean expectedResult = false;
        boolean actualResult = d1.deactivate();
        assertEquals(expectedResult, actualResult);
    }


    @Test
    void seeIfGetEnergyConsumption() {
        WineCooler d1 = new WineCooler(new WineCoolerSpec());
        double expectedResult = 0;
        double actualResult = d1.getEnergyConsumption(2);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetEnergyConsumptionWithNominalPower() {
        WineCooler d1 = new WineCooler(new WineCoolerSpec());
        d1.setNominalPower(5);
        double expectedResult = 10.0;
        double actualResult = d1.getEnergyConsumption(2);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetDailyEnergyConsumption() {
        WineCooler d1 = new WineCooler(new WineCoolerSpec());
        d1.setAnnualConsumption(3650.0);
        double expectedResult = 10.0;
        double actualResult = d1.getDailyEnergyConsumption();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getLogList() {
        WineCooler d1 = new WineCooler(new WineCoolerSpec());
        Log log = new Log(1, new GregorianCalendar(2019, 1, 1).getTime(),
                new GregorianCalendar(2019, 1, 1).getTime());
        LogList logList = d1.getLogList();
        d1.addLog(log);
        LogList result = d1.getLogList();
        assertEquals(logList, result);
    }

    @Test
    void getLogListBreakTest() {
        WineCooler d1 = new WineCooler(new WineCoolerSpec());
        LogList logList = new LogList();
        LogList result = d1.getLogList();
        assertEquals(logList, result);
    }

    @Test
    void addLogListFalse() {
        WineCooler d1 = new WineCooler(new WineCoolerSpec());
        Log log = new Log(1, new GregorianCalendar(2019, 1, 1).getTime(),
                new GregorianCalendar(2019, 1, 1).getTime());
        d1.addLog(log);
        assertFalse(d1.addLog(log));
    }

    @Test
    void addLogToInactive() {
        WineCooler d1 = new WineCooler(new WineCoolerSpec());
        Log log = new Log(1, new GregorianCalendar(2019, 1, 1).getTime(),
                new GregorianCalendar(2019, 1, 1).getTime());
        d1.deactivate();
        boolean result = d1.addLog(log);
        assertFalse(result);
    }

    @Test
    void addLogTrue() {
        WineCooler d1 = new WineCooler(new WineCoolerSpec());
        Log log = new Log(1, new GregorianCalendar(2019, 1, 1).getTime(),
                new GregorianCalendar(2019, 1, 1).getTime());
        boolean result = d1.addLog(log);
        assertTrue(result);
    }

    @Test
    void getTotalMeteredEnergyConsumptionInDeviceWithinGivenTimeIntervalEquals() {
        Date initialTime = new GregorianCalendar(2018, 10, 20, 10, 0).getTime();
        Date finalTime = new GregorianCalendar(2018, 10, 20, 11, 0).getTime();
        Date periodBeginning1 = new GregorianCalendar(2018, 10, 20, 10, 0).getTime();
        Date periodEnding1 = new GregorianCalendar(2018, 10, 20, 10, 20).getTime();
        Date periodBeginning2 = new GregorianCalendar(2018, 10, 20, 10, 30).getTime();
        Date periodEnding2 = new GregorianCalendar(2018, 10, 20, 11, 0).getTime();
        WineCooler device = new WineCooler(new WineCoolerSpec());
        device.setAttributeValue(WineCoolerSpec.NUMBER_BOTTLES, 40);
        Log log1 = new Log(56, periodBeginning1, periodEnding1);
        Log log2 = new Log(55, periodBeginning2, periodEnding2);
        device.addLog(log1);
        device.addLog(log2);
        double result = device.getConsumptionWithinGivenInterval(initialTime, finalTime);
        assertEquals(111, result);
    }

    @Test
    void getTotalMeteredEnergyConsumptionInDeviceWithinGivenTimeIntervalAfterBefore() {
        Date initialTime = new GregorianCalendar(2018, 10, 20, 10, 0).getTime();
        Date finalTime = new GregorianCalendar(2018, 10, 20, 11, 0).getTime();
        Date periodBeginning1 = new GregorianCalendar(2018, 10, 20, 10, 1).getTime();
        Date periodEnding1 = new GregorianCalendar(2018, 10, 20, 10, 20).getTime();
        Date periodBeginning2 = new GregorianCalendar(2018, 10, 20, 10, 30).getTime();
        Date periodEnding2 = new GregorianCalendar(2018, 10, 20, 10, 59).getTime();
        WineCooler device = new WineCooler(new WineCoolerSpec());
        device.setAttributeValue(WineCoolerSpec.NUMBER_BOTTLES, 40);
        Log log1 = new Log(56, periodBeginning1, periodEnding1);
        Log log2 = new Log(55, periodBeginning2, periodEnding2);
        device.addLog(log1);
        device.addLog(log2);
        double result = device.getConsumptionWithinGivenInterval(initialTime, finalTime);
        assertEquals(111, result);
    }

    @org.junit.jupiter.api.Test
    void getTotalMeteredEnergyConsumptionInDeviceWithinGivenTimeIntervalAfterBeforeReverseOutOfBounds() {
        Date initialTime = new GregorianCalendar(2018, 10, 20, 10, 0).getTime();
        Date finalTime = new GregorianCalendar(2018, 10, 20, 11, 0).getTime();
        Date periodBeginning1 = new GregorianCalendar(2018, 10, 20, 9, 0).getTime();
        Date periodEnding1 = new GregorianCalendar(2018, 10, 20, 10, 20).getTime();
        Date periodBeginning2 = new GregorianCalendar(2018, 10, 20, 10, 30).getTime();
        Date periodEnding2 = new GregorianCalendar(2018, 10, 20, 11, 20).getTime();
        WineCooler device = new WineCooler(new WineCoolerSpec());
        device.setAttributeValue(WineCoolerSpec.NUMBER_BOTTLES, 40);
        Log log1 = new Log(56, periodBeginning1, periodEnding1);
        Log log2 = new Log(55, periodBeginning2, periodEnding2);
        device.addLog(log1);
        device.addLog(log2);
        double result = device.getConsumptionWithinGivenInterval(initialTime, finalTime);
        assertEquals(0.0, result);
    }

    @org.junit.jupiter.api.Test
    void testCountLogsInInterval() {
        Date initialTime = new GregorianCalendar(2018, 10, 20, 10, 0).getTime();
        Date finalTime = new GregorianCalendar(2018, 10, 20, 11, 0).getTime();
        Date periodBeginning1 = new GregorianCalendar(2018, 10, 20, 10, 0).getTime();
        Date periodEnding1 = new GregorianCalendar(2018, 10, 20, 10, 20).getTime();
        Date periodBeginning2 = new GregorianCalendar(2018, 10, 20, 10, 20).getTime();
        Date periodEnding2 = new GregorianCalendar(2018, 10, 20, 10, 40).getTime();
        Date periodBeginning3 = new GregorianCalendar(2018, 10, 20, 10, 40).getTime();
        Date periodEnding3 = new GregorianCalendar(2018, 10, 20, 11, 0).getTime();
        WineCooler device = new WineCooler(new WineCoolerSpec());
        device.setAttributeValue(WineCoolerSpec.NUMBER_BOTTLES, 40);
        Log log1 = new Log(56, periodBeginning1, periodEnding1);
        Log log2 = new Log(55, periodBeginning2, periodEnding2);
        Log log3 = new Log(55, periodBeginning3, periodEnding3);
        device.addLog(log1);
        device.addLog(log2);
        device.addLog(log3);
        //Act
        Integer expectedResult = 3;
        Integer actualResult = device.countLogsInInterval(initialTime, finalTime);
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testGetLogsInInterval() {
        Date initialTime = new GregorianCalendar(2018, 10, 20, 10, 0).getTime();
        Date finalTime = new GregorianCalendar(2018, 10, 20, 11, 0).getTime();
        Date periodBeginning1 = new GregorianCalendar(2018, 10, 20, 10, 0).getTime();
        Date periodEnding1 = new GregorianCalendar(2018, 10, 20, 10, 20).getTime();
        Date periodBeginning2 = new GregorianCalendar(2018, 10, 20, 10, 20).getTime();
        Date periodEnding2 = new GregorianCalendar(2018, 10, 20, 10, 40).getTime();
        Date periodBeginning3 = new GregorianCalendar(2018, 10, 20, 10, 40).getTime();
        Date periodEnding3 = new GregorianCalendar(2018, 10, 20, 11, 0).getTime();
        Date periodBeginning4 = new GregorianCalendar(2018, 10, 20, 11, 0).getTime();
        Date periodEnding4 = new GregorianCalendar(2018, 10, 20, 11, 20).getTime();
        Date periodBeginning5 = new GregorianCalendar(2018, 10, 20, 9, 40).getTime();
        Date periodEnding5 = new GregorianCalendar(2018, 10, 20, 10, 0).getTime();
        WineCooler device = new WineCooler(new WineCoolerSpec());
        device.setAttributeValue(WineCoolerSpec.NUMBER_BOTTLES, 40);
        Log log1 = new Log(56, periodBeginning1, periodEnding1);
        Log log2 = new Log(55, periodBeginning2, periodEnding2);
        Log log3 = new Log(55, periodBeginning3, periodEnding3);
        Log log4 = new Log(55, periodBeginning4, periodEnding4);
        Log log5 = new Log(55, periodBeginning5, periodEnding5);
        device.addLog(log1);
        device.addLog(log2);
        device.addLog(log3);
        device.addLog(log4);
        device.addLog(log5);
        //Act
        LogList expectedResult = new LogList();
        expectedResult.addLog(log1);
        expectedResult.addLog(log2);
        expectedResult.addLog(log3);
        LogList actualResult = device.getLogsInInterval(initialTime, finalTime);
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testGetLogsInIntervalOutOfBounds() {
        Date initialTime = new GregorianCalendar(2018, 10, 20, 10, 0).getTime();
        Date finalTime = new GregorianCalendar(2018, 10, 20, 11, 0).getTime();
        Date periodBeginning1 = new GregorianCalendar(2018, 10, 20, 9, 50).getTime();
        Date periodEnding1 = new GregorianCalendar(2018, 10, 20, 10, 10).getTime();
        Date periodBeginning2 = new GregorianCalendar(2018, 10, 20, 10, 50).getTime();
        Date periodEnding2 = new GregorianCalendar(2018, 10, 20, 11, 10).getTime();
        WineCooler device = new WineCooler(new WineCoolerSpec());
        device.setAttributeValue(WineCoolerSpec.NUMBER_BOTTLES, 40);
        Log log1 = new Log(56, periodBeginning1, periodEnding1);
        Log log2 = new Log(55, periodBeginning2, periodEnding2);
        device.addLog(log1);
        device.addLog(log2);
        //Act
        LogList expectedResult = new LogList();
        LogList actualResult = device.getLogsInInterval(initialTime, finalTime);
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getConsumption() {
        WineCooler d = new WineCooler(new WineCoolerSpec());
        d.setNominalPower(15);
        double expectedResult = 360;
        double result = d.getEnergyConsumption(24);
        assertEquals(expectedResult, result);
    }

    @Test
    void getConsumptionTimeZero() {
        WineCooler d = new WineCooler(new WineCoolerSpec());
        d.setNominalPower(15);
        double expectedResult = 0;
        double result = d.getEnergyConsumption(0);
        assertEquals(expectedResult, result);
    }

    @Test
    void testSetAttributeValueForAllCases() {
        //Arrange
        WineCooler device = new WineCooler(new WineCoolerSpec());
        Integer attribute = 9;
        // original strings + double:
        assertTrue(device.setAttributeValue(WineCoolerSpec.NUMBER_BOTTLES, attribute));
        // same hash codes, but different strings + double:
        Assertions.assertFalse(device.setAttributeValue("notFLUX", attribute));
        // distinct hash code to cover default cases of switches + double
        Assertions.assertFalse(device.setAttributeValue("", attribute));
    }

    @Test
    void testSetAttributeValueForNotDouble() {
        //Arrange
        WineCooler device = new WineCooler(new WineCoolerSpec());
        Integer attributeD = 6;
        Double attribute = 6.0;
        device.setAttributeValue(WineCoolerSpec.NUMBER_BOTTLES, attributeD);
        // original strings + not int:
        Assertions.assertFalse(device.setAttributeValue(WineCoolerSpec.NUMBER_BOTTLES, attribute));
        // same hash codes, but different strings + not int:
        Assertions.assertFalse(device.setAttributeValue("notFLUX", attribute));
        Assertions.assertFalse(device.setAttributeValue("notNOMINAL_POWER", attribute));
        // distinct hash code to cover default cases of switches + not int
        Assertions.assertFalse(device.setAttributeValue("", attribute));
    }
}