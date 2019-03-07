package pt.ipp.isep.dei.project.model.device;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.RoomList;
import pt.ipp.isep.dei.project.model.device.devicespecs.FreezerSpec;
import pt.ipp.isep.dei.project.model.device.log.Log;
import pt.ipp.isep.dei.project.model.device.log.LogList;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testng.Assert.*;

class FreezerTest {
    private Freezer validFreezer = new Freezer(new FreezerSpec());
    private Log validLog; // February 1, 2019.
    private Log secondValidLog; // September 20th 2018, from 10h00 to 10h20.
    private Log thirdValidLog; // September 20th 2018, from 10h01 to 11h00.

    @BeforeEach
    void arrangeArtifacts() {
        validFreezer.setName("Freezer");
        validFreezer.setNominalPower(30);
        validFreezer.setAnnualConsumption(3650);
        validFreezer.setAttributeValue("Capacity", 15.0);
        validLog = new Log(2, new GregorianCalendar(2019, Calendar.FEBRUARY, 1).getTime(),
                new GregorianCalendar(2019, Calendar.FEBRUARY, 1).getTime());
        secondValidLog = new Log(20, new GregorianCalendar(2018, Calendar.SEPTEMBER, 20,
                10, 0, 0).getTime(), new GregorianCalendar(2018, Calendar.SEPTEMBER, 20
                , 10, 20, 0).getTime());
        thirdValidLog = new Log(20, new GregorianCalendar(2018, Calendar.SEPTEMBER, 20,
                10, 1, 0).getTime(), new GregorianCalendar(2018, Calendar.SEPTEMBER, 20,
                11, 0, 0).getTime());
        validFreezer.addLog(validLog);
        validFreezer.addLog(secondValidLog);
        validFreezer.addLog(thirdValidLog);
    }

    @Test
    void seeIfGetDeviceTypeWorks() {
        // Arrange

        String expectedResult = "Freezer";

        // Act

        String result = validFreezer.getType();

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfEqualsWorksOnItself() {
        // Act

        boolean actualResult = validFreezer.equals(validFreezer); // Needed for sonarqube testing purposes.

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfGetNominalPowerWorks() {
        // Arrange

        double expectedResult = 30;

        // Act

        double actualResult = validFreezer.getNominalPower();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetAnnualConsumptionWorks() {
        // Arrange

        double expectedResult = 3650;

        // Act

        double actualResult = validFreezer.getAnnualConsumption();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfEqualsWorksDifferentObject() {
        // Arrange

        Freezer testFreezer = new Freezer(new FreezerSpec());
        testFreezer.setName("FreezerTwo");
        testFreezer.setNominalPower(12.0);
        testFreezer.setAttributeValue(FreezerSpec.CAPACITY, 45);

        // Act

        boolean actualResult = validFreezer.equals(testFreezer);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksNotAnInstance() {
        // Act

        boolean actualResult = validFreezer.equals(new RoomList()); // Needed for sonarqube testing purposes.

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeEqualsToNullObject() {
        // Act

        boolean actualResult = validFreezer.equals(null); // Needed for sonarqube testing purposes.

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfPrintDeviceWorks() {
        // Arrange

        String expectedResult = "The device Name is Freezer, and its NominalPower is 30.0 kW.\n";

        // Act

        String actualResult = validFreezer.buildString();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetNameWorks() {
        // Arrange

        String expectedResult = "Freezer";

        // Act

        String actualResult = validFreezer.getName();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetAttributeValueWorks() {
        // Arrange

        Double expectedResult = 15.0;

        // Act

        Object actualResult = validFreezer.getAttributeValue(FreezerSpec.CAPACITY);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetAttributeUnitWorks() {
        // Arrange

        String expectedResult = "l";

        // Act

        Object actualResult = validFreezer.getAttributeUnit(FreezerSpec.CAPACITY);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetAttributeNamesWorks() {
        // Act

        List<String> actualResult = validFreezer.getAttributeNames();

        // Assert

        assertTrue(actualResult.contains(FreezerSpec.CAPACITY));
        assertEquals(actualResult.size(), 1);
    }

    @Test
    void seeIfDeactivateWorks() {
        // Act

        boolean actualResult = validFreezer.deactivate();

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfDeactivateWorksAlreadyInactive() {
        // Arrange

        validFreezer.deactivate();

        // Act

        boolean actualResult = validFreezer.deactivate();

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfGetEnergyConsumptionWorks() {
        // Arrange

        double expectedResult = 60.0;

        // Act

        double actualResult = validFreezer.getEnergyConsumption(2);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetDailyEnergyConsumptionWorks() {
        // Arrange

        double expectedResult = 10.0;

        // Act

        double actualResult = validFreezer.getDailyEnergyConsumption();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetLogListWorksEmptyList() {
        // Arrange

        validFreezer = new Freezer(new FreezerSpec());
        LogList expectedResult = new LogList();

        // Act

        LogList actualResult = validFreezer.getLogList();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetLogListWorks() {
        // Arrange

        LogList expectedResult = new LogList();
        expectedResult.addLog(validLog);
        expectedResult.addLog(secondValidLog);
        expectedResult.addLog(thirdValidLog);

        // Act

        LogList actualResult = validFreezer.getLogList();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfAddLogWorksDeactivated() {
        // Arrange

        validFreezer.deactivate();

        // Act

        boolean result = this.validFreezer.addLog(validLog);

        // Assert

        assertFalse(result);
    }

    @Test
    void seeIfAddLogWorks() {
        // Act

        validFreezer = new Freezer(new FreezerSpec());
        boolean result = validFreezer.addLog(validLog);

        // Assert

        assertTrue(result);
    }

    @Test
    void seeIfAddLogWorksDuplicate() {
        // Arrange

        validFreezer.addLog(validLog);

        // Act

        boolean result = validFreezer.addLog(validLog);

        // Assert

        assertFalse(result);
    }


    @Test
    void seeIfIsEmptyWorks() {
        // Act

        validFreezer = new Freezer(new FreezerSpec());
        boolean result = validFreezer.isLogListEmpty();

        // Assert

        assertTrue(result);
    }

    @Test
    void seeIfIsEmptyWorksFalse() {
        // Act

        boolean result = validFreezer.isLogListEmpty();

        // Assert

        assertFalse(result);
    }

    @Test
    void seeIfGetConsumptionInIntervalWorks() {
        // Arrange

        double expectedResult = 40.0;

        // Interval

        Date initialTime = new GregorianCalendar(2018, Calendar.SEPTEMBER, 20, 10, 0,
                0).getTime();
        Date finalTime = new GregorianCalendar(2018, Calendar.SEPTEMBER, 20, 11, 0,
                0).getTime();

        // Act

        double actualResult = validFreezer.getConsumptionInInterval(initialTime, finalTime);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetConsumptionInIntervalWorksOutOfBounds() {
        // Arrange

        validFreezer = new Freezer(new FreezerSpec());
        double expectedResult = 0.0;

        // Interval

        Date initialTime = new GregorianCalendar(2018, Calendar.SEPTEMBER, 20, 10, 0,
                0).getTime();
        Date finalTime = new GregorianCalendar(2018, Calendar.SEPTEMBER, 20, 11, 0,
                0).getTime();

        // Logs

        Log firstLog = new Log(21, new GregorianCalendar(2018, Calendar.SEPTEMBER, 19).getTime(),
                new GregorianCalendar(2018, Calendar.SEPTEMBER, 21).getTime());
        validFreezer.addLog(firstLog);

        // Act

        double actualResult = validFreezer.getConsumptionInInterval(initialTime, finalTime);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfCountGetLogsInIntervalWorks() {
        // Arrange

        Integer expectedResult = 2;
        LogList expectedResultList = new LogList();
        expectedResultList.addLog(validLog);
        expectedResultList.addLog(secondValidLog);
        expectedResultList.addLog(thirdValidLog);

        // Interval

        Date initialTime = new GregorianCalendar(2018, Calendar.SEPTEMBER, 20, 10, 0,
                0).getTime();
        Date finalTime = new GregorianCalendar(2018, Calendar.SEPTEMBER, 20, 11, 0,
                0).getTime();

        // Act

        Integer actualResult = validFreezer.countLogsInInterval(initialTime, finalTime);
        LogList actualResultList = validFreezer.getLogList();

        // Assert

        assertEquals(expectedResult, actualResult);
        assertEquals(expectedResultList, actualResultList);
    }

    @Test
    void seeIfGetLogsInIntervalWorksOutOfBounds() {
        // Arrange

        validFreezer = new Freezer(new FreezerSpec());
        LogList expectedResult = new LogList();

        // Interval

        Date initialTime = new GregorianCalendar(2018, Calendar.SEPTEMBER, 20, 10, 0,
                0).getTime();
        Date finalTime = new GregorianCalendar(2018, Calendar.SEPTEMBER, 20, 11, 0,
                0).getTime();

        // Logs

        Log firstLog = new Log(21, new GregorianCalendar(2018, Calendar.SEPTEMBER, 19).getTime(),
                new GregorianCalendar(2018, Calendar.SEPTEMBER, 21).getTime());
        validFreezer.addLog(firstLog);

        // Act

        LogList actualResult = validFreezer.getLogsInInterval(initialTime, finalTime);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetConsumptionWorks() {
        // Arrange

        double expectedResult = 720.0;

        // Act

        double actualResult = validFreezer.getEnergyConsumption(24);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetConsumptionWorksTimeZero() {
        // Arrange

        double expectedResult = 0;

        // Act

        double actualResult = validFreezer.getEnergyConsumption(0);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSetAttributeValueWorks() {
        // Arrange

        Integer testAttribute = 6;
        Double testAttributeDouble = 9.0;

        // Act and Assert

        assertFalse(validFreezer.setAttributeValue(FreezerSpec.CAPACITY, testAttribute));
        assertTrue(validFreezer.setAttributeValue(FreezerSpec.CAPACITY, testAttributeDouble));
        Assertions.assertFalse(validFreezer.setAttributeValue("notCapacity", testAttributeDouble));
        Assertions.assertFalse(validFreezer.setAttributeValue("", testAttributeDouble));
    }

    @Test
    void hashCodeDummyTest() {
        int expectedResult = 1;
        int actualResult = validFreezer.hashCode();
        assertEquals(expectedResult, actualResult);
    }
}