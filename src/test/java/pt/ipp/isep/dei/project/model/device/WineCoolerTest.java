package pt.ipp.isep.dei.project.model.device;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Room;
import pt.ipp.isep.dei.project.model.device.devicespecs.WineCoolerSpec;
import pt.ipp.isep.dei.project.model.device.log.Log;
import pt.ipp.isep.dei.project.model.device.log.LogList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testng.Assert.*;

public class WineCoolerTest {
    private WineCooler wineCoolerValid = new WineCooler(new WineCoolerSpec());
    private Log validLog;
    private LogList validLogList;
    private Date initialTime;
    private Date finalTime;
    private Date periodEnding1;
    private Date periodEnding2;
    private Date periodBeginning1;
    private Date periodBeginning2;
    private Date periodBeginning9AM;
    private Date periodEnding1120AM;
    private Log validLog01;
    private Log validLog02;

    @BeforeEach
    void arrangeArtifacts() {
        wineCoolerValid.setName("Wine Cooler");
        wineCoolerValid.setNominalPower(15);
        wineCoolerValid.setAnnualConsumption(3650);
        wineCoolerValid.setAttributeValue("Number Bottles", 15);
        validLog = new Log(1, new GregorianCalendar(2019, 1, 1).getTime(),
                new GregorianCalendar(2019, 1, 1).getTime());
        validLogList = wineCoolerValid.getLogList();
        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            initialTime = validSdf.parse("20/11/2018 10:00:00");
            finalTime = validSdf.parse("20/11/2018 11:00:00");
            periodEnding1 = validSdf.parse("20/11/2018 10:20:00");
            periodBeginning2 = validSdf.parse("20/11/2018 10:30:00");
            periodBeginning1 = validSdf.parse("20/11/2018 10:01:00");
            periodEnding2 = validSdf.parse("20/11/2018 10:59:00");
            periodBeginning9AM = validSdf.parse("20/11/2018 9:00:00");
            periodEnding1120AM = validSdf.parse("20/11/2018 11:20:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        validLog01 = new Log(56, initialTime, periodEnding1);
        validLog02 = new Log(55, periodBeginning2, finalTime);
    }

    @Test
    void seeIfGetDeviceTypeTest() {
        String dT = "WineCooler";
        String expectedResult = dT;
        String result = wineCoolerValid.getType();
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfEqualToSameObject() {
        boolean actualResult = wineCoolerValid.equals(wineCoolerValid);
        assertTrue(actualResult);
    }

    @Test
    void seeIfGetNominalPowerTest() {
        double expectedResult = 15;
        double actualResult = wineCoolerValid.getNominalPower();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetAnnualConsumptionTest() {
        double expectedResult = 3650;
        double actualResult = wineCoolerValid.getAnnualConsumption();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfEqualsToDifObject() {
        WineCooler d2 = new WineCooler(new WineCoolerSpec());
        d2.setName("WCTwo");
        d2.setNominalPower(12.0);
        d2.setAttributeValue(WineCoolerSpec.NUMBER_BOTTLES, 45);

        boolean actualResult = wineCoolerValid.equals(d2);
        assertFalse(actualResult);
    }


    @Test
    void seeEqualsToDifTypeObject() {
        Room room = new Room("quarto", 1, 80, 2, 2);

        boolean actualResult = wineCoolerValid.equals(room);
        assertFalse(actualResult);
    }

    @Test
    void seeEqualsToNullObject() {
        boolean actualResult = wineCoolerValid.equals(null);

        assertFalse(actualResult);
    }

    @Test
    void seeIfPrintDeviceWorks() {
        String expectedResult = "The device Name is Wine Cooler, and its NominalPower is 15.0 kW.\n";
        String actualResult = wineCoolerValid.buildString();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetNameWorks() {
        String expectedResult = "Wine Cooler";
        String actualResult = wineCoolerValid.getName();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void hashCodeDummyTest() {
        int expectedResult = 1;
        int actualResult = wineCoolerValid.hashCode();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetAttributeValue() {
        Integer expectedResult = 15;
        Object actualResult = wineCoolerValid.getAttributeValue(WineCoolerSpec.NUMBER_BOTTLES);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetAndSetAttributeUnit() {
        String expectedResult = "bottles";
        Object actualResult = wineCoolerValid.getAttributeUnit(WineCoolerSpec.NUMBER_BOTTLES);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetAttributeNames() {

        List<String> actualResult = wineCoolerValid.getAttributeNames();
        assertTrue(actualResult.contains(WineCoolerSpec.NUMBER_BOTTLES));

        assertEquals(actualResult.size(), 1);
    }

    @Test
    void ensureThatWeDeactivateADevice() {
        boolean expectedResult = true;
        boolean actualResult = wineCoolerValid.deactivate();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void ensureThatWeDoNotDeactivate() {
        wineCoolerValid.deactivate();
        boolean expectedResult = false;
        boolean actualResult = wineCoolerValid.deactivate();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetEnergyConsumptionWithNominalPower() {
        double expectedResult = 30.0;
        double actualResult = wineCoolerValid.getEnergyConsumption(2);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetDailyEnergyConsumption() {
        double expectedResult = 10.0;
        double actualResult = wineCoolerValid.getDailyEnergyConsumption();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getLogList() {
        LogList actualResult = wineCoolerValid.getLogList();
        LogList logList = new LogList();
        assertEquals(logList, actualResult);
    }

    @Test
    void getLogListEmpty() {
       wineCoolerValid.addLog(validLog);
       wineCoolerValid.addLog(validLog01);
        LogList actualResult =wineCoolerValid.getLogList();
        assertEquals(validLogList, actualResult);
    }

    @Test
    void addLogToInactive() {
        this.wineCoolerValid.deactivate();
        boolean result = this.wineCoolerValid.addLog(validLog);
        assertFalse(result);
    }

    @Test
    void addLogTrue() {
        boolean result = wineCoolerValid.addLog(validLog);
        assertTrue(result);
    }

    @Test
    void addLogFalse() {
        wineCoolerValid.addLog(validLog);
        boolean result = wineCoolerValid.addLog(validLog);
        assertFalse(result);
    }


    @Test
    void isLogListEmptyTrue() {
        boolean result = wineCoolerValid.isLogListEmpty();
        assertTrue(result);
    }

    @Test
    void isLogListEmptyFalse() {
        wineCoolerValid.addLog(validLog02);
        wineCoolerValid.addLog(validLog01);
        boolean result = wineCoolerValid.isLogListEmpty();
        assertFalse(result);
    }

    @Test
    void getTotalMeteredEnergyConsumptionInDeviceWithinGivenTimeIntervalEquals() {
        wineCoolerValid.addLog(validLog01);
        wineCoolerValid.addLog(validLog02);
        double expectedResult = 111;
        double actualResult = wineCoolerValid.getConsumptionWithinGivenInterval(initialTime, finalTime);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getTotalMeteredEnergyConsumptionInDeviceWithinGivenTimeIntervalAfterBefore() {
        Log log1 = new Log(10, periodBeginning1, periodEnding1);
        Log log2 = new Log(55, periodBeginning2, periodEnding2);
        wineCoolerValid.addLog(log1);
        wineCoolerValid.addLog(log2);
        double expectedResult = 65;
        double actualResult = wineCoolerValid.getConsumptionWithinGivenInterval(initialTime, finalTime);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getTotalMeteredEnergyConsumptionInDeviceWithinGivenTimeIntervalAfterBeforeReverseOutOfBounds() {

        Log log1 = new Log(18, periodBeginning9AM, periodEnding1);
        Log log2 = new Log(23, periodBeginning2, periodEnding1120AM);
        wineCoolerValid.addLog(log1);
        wineCoolerValid.addLog(log2);
        double expectedResult = 0.0;
        double actualResult = wineCoolerValid.getConsumptionWithinGivenInterval(initialTime, finalTime);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testCountLogsInInterval() {
        wineCoolerValid.addLog(validLog01);
        wineCoolerValid.addLog(validLog02);
        //Act
        Integer expectedResult = 2;
        Integer actualResult = wineCoolerValid.countLogsInInterval(initialTime, finalTime);
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testGetLogsInInterval() {
        wineCoolerValid.addLog(validLog01);
        wineCoolerValid.addLog(validLog02);
        wineCoolerValid.setNominalPower(13);
        LogList expectedResult = new LogList();
        expectedResult.addLog(validLog01);
        expectedResult.addLog(validLog02);

        LogList actualResult = wineCoolerValid.getLogsInInterval(initialTime, finalTime);
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
        Log log1 = new Log(56, periodBeginning1, periodEnding1);
        Log log2 = new Log(55, periodBeginning2, periodEnding2);
        wineCoolerValid.addLog(log1);
        wineCoolerValid.addLog(log2);
        //Act
        LogList expectedResult = new LogList();
        LogList actualResult = wineCoolerValid.getLogsInInterval(initialTime, finalTime);
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getConsumption() {
        double expectedResult = 360;
        double actualResult = wineCoolerValid.getEnergyConsumption(24);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getConsumptionTimeZero() {
        double expectedResult = 0;
        double actualResult = wineCoolerValid.getEnergyConsumption(0);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testSetAttributeValueForNotDouble() {
        Integer attribute = 6;
        Double attributeD = 9.0;
        assertFalse(wineCoolerValid.setAttributeValue(WineCoolerSpec.NUMBER_BOTTLES, attributeD));
        assertTrue(wineCoolerValid.setAttributeValue(WineCoolerSpec.NUMBER_BOTTLES, attribute));
        Assertions.assertFalse(wineCoolerValid.setAttributeValue("notBottle", attribute));
        Assertions.assertFalse(wineCoolerValid.setAttributeValue("", attribute));
    }
}