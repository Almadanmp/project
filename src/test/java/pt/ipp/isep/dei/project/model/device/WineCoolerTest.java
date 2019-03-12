package pt.ipp.isep.dei.project.model.device;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.RoomList;
import pt.ipp.isep.dei.project.model.device.devicespecs.FridgeSpec;
import pt.ipp.isep.dei.project.model.device.devicespecs.WineCoolerSpec;
import pt.ipp.isep.dei.project.model.device.log.Log;
import pt.ipp.isep.dei.project.model.device.log.LogList;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testng.Assert.*;

class WineCoolerTest {
    private WineCooler validCooler = new WineCooler(new WineCoolerSpec());
    private Log validLog;

    @BeforeEach
    void arrangeArtifacts() {
        validCooler.setName("Wine Cooler");
        validCooler.setNominalPower(15);
        validCooler.setAnnualConsumption(3650);
        validCooler.setAttributeValue("Number of Bottles", 15.0);
        validLog = new Log(1, new GregorianCalendar(2019, Calendar.FEBRUARY, 1).getTime(),
                new GregorianCalendar(2019, Calendar.FEBRUARY, 1).getTime());
        validCooler.addLog(validLog);
    }

    @Test
    void seeIfGetDeviceTypeWorks() {
        // Arrange

        String expectedResult = "WineCooler";

        // Act

        String result = validCooler.getType();

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfEqualsWorksOnItself() {
        assertEquals(validCooler, validCooler);
    }

    @Test
    void seeIfGetNominalPowerWorks() {
        // Arrange

        double expectedResult = 15;

        // Act

        double actualResult = validCooler.getNominalPower();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetAnnualConsumptionWorks() {
        // Arrange

        double expectedResult = 3650;

        // Act

        double actualResult = validCooler.getAnnualConsumption();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfEqualsWorksFalse() {
        // Arrange

        WineCooler testCooler = new WineCooler(new WineCoolerSpec());
        testCooler.setName("WCTwo");
        testCooler.setNominalPower(12.0);
        testCooler.setAttributeValue(WineCoolerSpec.NUMBER_BOTTLES, 45);
        Fridge fridge = new Fridge(new FridgeSpec());

        // Act

        boolean actualResultObject = validCooler.equals(fridge);
        boolean actualResultNull = validCooler.equals(null);
        boolean actualResult = validCooler.equals(testCooler);

        // Assert

        assertFalse(actualResult);
        assertFalse(actualResultNull);
        assertFalse(actualResultObject);
    }


    @Test
    void seeIfEqualsWorksNotAnInstance() {
        assertNotEquals(validCooler, new RoomList());
    }

    @Test
    void seeIfEqualsWorksNull() {
        assertNotEquals(validCooler, null);
    }

    @Test
    void seeIfBuildStringWorks() {
        // Arrange

        String expectedResult = "The device Name is Wine Cooler, and its NominalPower is 15.0 kW.\n";

        // Act

        String actualResult = validCooler.buildString();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetNameWorks() {
        // Arrange

        String expectedResult = "Wine Cooler";

        // Act

        String actualResult = validCooler.getName();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetAttributeValueWorks() {
        // Arrange

        Double expectedResult = 15.0;

        // Act

        Object actualResult = validCooler.getAttributeValue(WineCoolerSpec.NUMBER_BOTTLES);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetAttributeUnitWorks() {
        // Arrange

        String expectedResult = "bottles";

        // Act

        Object actualResult = validCooler.getAttributeUnit(WineCoolerSpec.NUMBER_BOTTLES);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetAttributeNamesWorks() {
        // Act

        List<String> actualResult = validCooler.getAttributeNames();

        // Assert

        assertTrue(actualResult.contains(WineCoolerSpec.NUMBER_BOTTLES));
        assertEquals(actualResult.size(), 1);
    }

    @Test
    void seeIfDeactivateWorks() {
        // Act

        boolean actualResult = validCooler.deactivate();

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfDeactivateWorksAlreadyDeactivated() {
        // Arrange

        validCooler.deactivate();

        // Act

        boolean actualResult = validCooler.deactivate();

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfGetEnergyConsumptionWorks() {
        // Arrange

        double expectedResult = 30.0;

        // Act

        double actualResult = validCooler.getEnergyConsumption(2);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetDailyEnergyConsumptionWorks() {
        // Arrange

        double expectedResult = 10.0;

        // Act

        double actualResult = validCooler.getDailyEnergyConsumption();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetLogListWorks() {
        // Arrange

        LogList expectedResult = new LogList();
        expectedResult.addLog(validLog);

        // Act

        LogList actualResult = validCooler.getLogList();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetLogListWorksEmpty() {
        // Arrange

        validCooler = new WineCooler(new WineCoolerSpec());
        LogList expectedResult = new LogList();

        // Act

        LogList actualResult = validCooler.getLogList();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfAddLogWorksDeactivated() {
        // Arrange

        validCooler = new WineCooler(new WineCoolerSpec());
        validCooler.deactivate();

        // Act

        boolean result = this.validCooler.addLog(validLog);

        // Assert

        assertFalse(result);
    }

    @Test
    void seeIfAddLogWorksDuplicate() {
        // Act

        boolean result = validCooler.addLog(validLog);

        // Assert

        assertFalse(result);
    }

    @Test
    void seeIfAddLogWorks() {
        // Arrange

        validCooler = new WineCooler(new WineCoolerSpec());

        // Act

        boolean result = validCooler.addLog(validLog);

        // Assert

        assertTrue(result);
    }


    @Test
    void seeIfIsLogListEmptyWorksFalse() {
        // Act

        boolean result = validCooler.isLogListEmpty();

        // Assert

        assertFalse(result);
    }

    @Test
    void seeIfIsLogListEmptyWorks() {
        // Arrange

        validCooler = new WineCooler(new WineCoolerSpec());

        // Act

        boolean result = validCooler.isLogListEmpty();

        // Assert

        assertTrue(result);
    }

    @Test
    void seeIfGetConsumptionInIntervalWorks() {
        // Arrange

        double expectedResult = 1;

        // Interval

        Date initialTime = new GregorianCalendar(2019, Calendar.JANUARY, 20, 10, 0,
                0).getTime();
        Date finalTime = new GregorianCalendar(2019, Calendar.FEBRUARY, 20, 11, 0,
                0).getTime();

        // Act

        double actualResult = validCooler.getConsumptionInInterval(initialTime, finalTime);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetConsumptionInIntervalWorksOutOfBounds() {
        // Arrange

        validCooler = new WineCooler(new WineCoolerSpec());
        double expectedResult = 0.0;

        // Interval

        Date initialTime = new GregorianCalendar(2018, Calendar.SEPTEMBER, 20, 10, 0,
                0).getTime();
        Date finalTime = new GregorianCalendar(2018, Calendar.SEPTEMBER, 20, 11, 0,
                0).getTime();

        // Logs

        Log firstLog = new Log(21, new GregorianCalendar(2018, Calendar.SEPTEMBER, 19).getTime(),
                new GregorianCalendar(2018, Calendar.SEPTEMBER, 21).getTime());
        validCooler.addLog(firstLog);

        // Act

        double actualResult = validCooler.getConsumptionInInterval(initialTime, finalTime);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfCountGetLogsInIntervalWorks() {
        // Arrange

        Integer expectedResult = 1;
        LogList expectedResultList = new LogList();
        expectedResultList.addLog(validLog);

        // Interval

        Date initialTime = new GregorianCalendar(2019, Calendar.JANUARY, 20, 10, 0,
                0).getTime();
        Date finalTime = new GregorianCalendar(2019, Calendar.FEBRUARY, 20, 11, 0,
                0).getTime();

        // Act

        Integer actualResult = validCooler.countLogsInInterval(initialTime, finalTime);
        LogList actualResultList = validCooler.getLogList();

        // Assert

        assertEquals(expectedResult, actualResult);
        assertEquals(expectedResultList, actualResultList);
    }

    @Test
    void seeIfGetLogsInIntervalWorksOutOfBounds() {
        // Arrange

        validCooler = new WineCooler(new WineCoolerSpec());
        LogList expectedResult = new LogList();

        // Interval

        Date initialTime = new GregorianCalendar(2018, Calendar.SEPTEMBER, 20, 10, 0,
                0).getTime();
        Date finalTime = new GregorianCalendar(2018, Calendar.SEPTEMBER, 20, 11, 0,
                0).getTime();

        // Logs

        Log firstLog = new Log(21, new GregorianCalendar(2018, Calendar.SEPTEMBER, 19).getTime(),
                new GregorianCalendar(2018, Calendar.SEPTEMBER, 21).getTime());
        validCooler.addLog(firstLog);

        // Act

        LogList actualResult = validCooler.getLogsInInterval(initialTime, finalTime);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetConsumptionWorksTimeZero() {
        // Arrange

        double expectedResult = 0;

        // Act

        double actualResult = validCooler.getEnergyConsumption(0);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSetAttriubteValueWorks() {
        // Arrange

        Integer attribute = 6;
        Double attributeD = 9.0;

        // Assert

        assertFalse(validCooler.setAttributeValue(WineCoolerSpec.NUMBER_BOTTLES, attribute));
        assertTrue(validCooler.setAttributeValue(WineCoolerSpec.NUMBER_BOTTLES, attributeD));
        Assertions.assertFalse(validCooler.setAttributeValue("notBottle", attributeD));
        Assertions.assertFalse(validCooler.setAttributeValue("", attributeD));
    }

    @Test
    void hashCodeDummyTest() {
        // Arrange

        int expectedResult = 1;

        // Act

        int actualResult = validCooler.hashCode();

        // Assert

        assertEquals(expectedResult, actualResult);
    }
}