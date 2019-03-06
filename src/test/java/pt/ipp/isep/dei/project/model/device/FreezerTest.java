package pt.ipp.isep.dei.project.model.device;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Room;
import pt.ipp.isep.dei.project.model.device.devicespecs.FreezerSpec;
import pt.ipp.isep.dei.project.model.device.log.Log;
import pt.ipp.isep.dei.project.model.device.log.LogList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testng.Assert.*;

public class FreezerTest {
    private Freezer freezerValid = new Freezer(new FreezerSpec());
    private Log validLog;
    private LogList validLogList;
    private Date initialTime;
    private Date finalTime;
    private Date periodEnding01;
    private Date periodEnding02;
    private Date periodBeginning01;
    private Date periodBeginning02;
    private Date periodBeginning9AM;
    private Date periodEnding1120AM;
    private Log validLog01;
    private Log validLog02;

    @BeforeEach
    void arrangeArtifacts() {
        freezerValid.setName("Freezer");
        freezerValid.setNominalPower(30);
        freezerValid.setAnnualConsumption(3650);
        freezerValid.setAttributeValue("Capacity", 15.0);
        validLog = new Log(2, new GregorianCalendar(2019, 1, 1).getTime(),
                new GregorianCalendar(2019, 1, 1).getTime());
        validLogList = freezerValid.getLogList();
        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            initialTime = validSdf.parse("20/09/2018 10:00:00");
            finalTime = validSdf.parse("20/09/2018 11:00:00");
            periodEnding01 = validSdf.parse("20/09/2018 10:20:00");
            periodBeginning02 = validSdf.parse("20/09/2018 10:30:00");
            periodBeginning01 = validSdf.parse("20/09/2018 10:01:00");
            periodEnding02 = validSdf.parse("20/09/2018 10:59:00");
            periodBeginning9AM = validSdf.parse("20/09/2018 9:00:00");
            periodEnding1120AM = validSdf.parse("20/09/2018 11:20:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        validLog01 = new Log(20, initialTime, periodEnding01);
        validLog02 = new Log(20, periodBeginning02, finalTime);
    }

    @Test
    void seeIfGetDeviceTypeTest() {
        String dT = "Freezer";
        String expectedResult = dT;
        String result = freezerValid.getType();
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfEqualToSameObject() {
        boolean actualResult = freezerValid.equals(freezerValid);
        assertTrue(actualResult);
    }

    @Test
    void seeIfGetNominalPowerTest() {
        double expectedResult = 30;
        double actualResult = freezerValid.getNominalPower();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetAnnualConsumptionTest() {
        double expectedResult = 3650;
        double actualResult = freezerValid.getAnnualConsumption();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfEqualsToDifObject() {
        Freezer d2 = new Freezer(new FreezerSpec());
        d2.setName("FreezerTwo");
        d2.setNominalPower(12.0);
        d2.setAttributeValue(FreezerSpec.CAPACITY, 45);

        boolean actualResult = freezerValid.equals(d2);
        assertFalse(actualResult);
    }

    @Test
    void seeEqualsToDifTypeObject() {
        Room room = new Room("quarto", 1, 80, 2, 2);

        boolean actualResult = freezerValid.equals(room);
        assertFalse(actualResult);
    }

    @Test
    void seeEqualsToNullObject() {
        boolean actualResult = freezerValid.equals(null);

        assertFalse(actualResult);
    }

    @Test
    void seeIfPrintDeviceWorks() {
        String expectedResult = "The device Name is Freezer, and its NominalPower is 30.0 kW.\n";
        String actualResult = freezerValid.buildString();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetNameWorks() {
        String expectedResult = "Freezer";
        String actualResult = freezerValid.getName();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void hashCodeDummyTest() {
        int expectedResult = 1;
        int actualResult = freezerValid.hashCode();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetAttributeValue() {
        Double expectedResult = 15.0;
        Object actualResult = freezerValid.getAttributeValue(FreezerSpec.CAPACITY);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetAndSetAttributeUnit() {
        String expectedResult = "l";
        Object actualResult = freezerValid.getAttributeUnit(FreezerSpec.CAPACITY);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetAttributeNames() {

        List<String> actualResult = freezerValid.getAttributeNames();
        assertTrue(actualResult.contains(FreezerSpec.CAPACITY));

        assertEquals(actualResult.size(), 1);
    }

    @Test
    void ensureThatWeDeactivateADevice() {
        boolean expectedResult = true;
        boolean actualResult = freezerValid.deactivate();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void ensureThatWeDoNotDeactivate() {
        freezerValid.deactivate();
        boolean expectedResult = false;
        boolean actualResult = freezerValid.deactivate();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetEnergyConsumptionWithNominalPower() {
        double expectedResult = 60.0;
        double actualResult = freezerValid.getEnergyConsumption(2);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetDailyEnergyConsumption() {
        double expectedResult = 10.0;
        double actualResult = freezerValid.getDailyEnergyConsumption();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getLogList() {
        LogList actualResult = freezerValid.getLogList();
        LogList logList = new LogList();
        assertEquals(logList, actualResult);
    }

    @Test
    void getLogListEmpty() {
        freezerValid.addLog(validLog);
        freezerValid.addLog(validLog01);
        LogList actualResult = freezerValid.getLogList();
        assertEquals(validLogList, actualResult);
    }

    @Test
    void addLogToInactive() {
        this.freezerValid.deactivate();
        boolean result = this.freezerValid.addLog(validLog);
        assertFalse(result);
    }

    @Test
    void addLogTrue() {
        boolean result = freezerValid.addLog(validLog);
        assertTrue(result);
    }

    @Test
    void addLogFalse() {
        freezerValid.addLog(validLog);
        boolean result = freezerValid.addLog(validLog);
        assertFalse(result);
    }


    @Test
    void isLogListEmptyTrue() {
        boolean result = freezerValid.isLogListEmpty();
        assertTrue(result);
    }

    @Test
    void isLogListEmptyFalse() {
        freezerValid.addLog(validLog02);
        freezerValid.addLog(validLog01);
        boolean result = freezerValid.isLogListEmpty();
        assertFalse(result);
    }

    @Test
    void getTotalMeteredEnergyConsumptionInDeviceWithinGivenTimeIntervalEquals() {
        freezerValid.addLog(validLog01);
        freezerValid.addLog(validLog02);
        double expectedResult = 40.0;
        double actualResult = freezerValid.getConsumptionInInterval(initialTime, finalTime);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getTotalMeteredEnergyConsumptionInDeviceWithinGivenTimeIntervalAfterBefore() {
        Log log1 = new Log(10, periodBeginning01, periodEnding01);
        Log log2 = new Log(50, periodBeginning02, periodEnding02);
        freezerValid.addLog(log1);
        freezerValid.addLog(log2);
        double expectedResult = 60.0;
        double actualResult = freezerValid.getConsumptionInInterval(initialTime, finalTime);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getTotalMeteredEnergyConsumptionInDeviceWithinGivenTimeIntervalAfterBeforeReverseOutOfBounds() {

        Log log1 = new Log(10, periodBeginning9AM, periodEnding01);
        Log log2 = new Log(20, periodBeginning02, periodEnding1120AM);
        freezerValid.addLog(log1);
        freezerValid.addLog(log2);
        double expectedResult = 0.0;
        double actualResult = freezerValid.getConsumptionInInterval(initialTime, finalTime);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testCountLogsInInterval() {
        freezerValid.addLog(validLog01);
        freezerValid.addLog(validLog02);
        Log log = new Log(10, periodBeginning01, periodEnding01);
        freezerValid.addLog(log);
        Integer expectedResult = 3;
        Integer actualResult = freezerValid.countLogsInInterval(initialTime, finalTime);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testGetLogsInInterval() {
        freezerValid.addLog(validLog01);
        freezerValid.addLog(validLog02);
        freezerValid.setNominalPower(30);
        LogList expectedResult = new LogList();
        expectedResult.addLog(validLog01);
        expectedResult.addLog(validLog02);

        LogList actualResult = freezerValid.getLogsInInterval(initialTime, finalTime);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testGetLogsInIntervalOutOfBounds() {
        Date initialTime = new GregorianCalendar(2019, 10, 20, 10, 0).getTime();
        Date finalTime = new GregorianCalendar(2019, 10, 20, 11, 0).getTime();
        Date periodBeginning1 = new GregorianCalendar(2019, 10, 20, 9, 50).getTime();
        Date periodEnding1 = new GregorianCalendar(2019, 10, 20, 10, 10).getTime();
        Date periodBeginning2 = new GregorianCalendar(2019, 10, 20, 10, 50).getTime();
        Date periodEnding2 = new GregorianCalendar(2019, 10, 20, 11, 10).getTime();
        Log log1 = new Log(20, periodBeginning1, periodEnding1);
        Log log2 = new Log(20, periodBeginning2, periodEnding2);
        freezerValid.addLog(log1);
        freezerValid.addLog(log2);
        //Act
        LogList expectedResult = new LogList();
        LogList actualResult = freezerValid.getLogsInInterval(initialTime, finalTime);
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getConsumption() {
        double expectedResult = 720.0;
        double actualResult = freezerValid.getEnergyConsumption(24);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getConsumptionTimeZero() {
        double expectedResult = 0;
        double actualResult = freezerValid.getEnergyConsumption(0);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testSetAttributeValueForNotDouble() {
        Integer attribute = 6;
        Double attributeD = 9.0;
        assertFalse(freezerValid.setAttributeValue(FreezerSpec.CAPACITY, attribute));
        assertTrue(freezerValid.setAttributeValue(FreezerSpec.CAPACITY, attributeD));
        Assertions.assertFalse(freezerValid.setAttributeValue("notCapacity", attributeD));
        Assertions.assertFalse(freezerValid.setAttributeValue("", attributeD));
    }

}