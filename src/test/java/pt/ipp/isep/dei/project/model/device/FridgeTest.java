package pt.ipp.isep.dei.project.model.device;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.EnergyGridList;
import pt.ipp.isep.dei.project.model.device.devicespecs.FridgeSpec;
import pt.ipp.isep.dei.project.model.device.log.Log;
import pt.ipp.isep.dei.project.model.device.log.LogList;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Fridge Device tests class.
 */

class FridgeTest {
    // Common testing artifacts for this class.

    private Fridge validFridge;
    private Log validLog; // February 1, 2019.

    @BeforeEach
    void arrangeArtifacts() {
        validFridge = new Fridge(new FridgeSpec());
        validFridge.setNominalPower(15);
        validFridge.setName("FridgeOne");
        validFridge.setNominalPower(12.0);
        validFridge.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 34);
        validLog = new Log(1, new GregorianCalendar(2018, Calendar.FEBRUARY, 1).getTime(),
                new GregorianCalendar(2018, Calendar.FEBRUARY, 1).getTime());
        validFridge.addLog(validLog);
    }

    @Test
    void seeIfGetDeviceTypeWorks() {
        // Arrange

        String expectedResult = "Fridge";

        // Act

        String result = validFridge.getType();

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetConsumptionWorks() {
        // Arrange

        double expectedResult = 288;

        // Act

        double result = validFridge.getEnergyConsumption(24);

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfgetConsumptionWorksTimeZero() {
        // Arrange

        double expectedResult = 0;

        // Act

        double result = validFridge.getEnergyConsumption(0);

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfEqualsWorksSameObject() {
        // Act

        boolean actualResult = validFridge.equals(validFridge); // Needed for sonarqube testing purposes.

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfEqualsWorksFalse() {
        // Arrange

        Fridge testFridge = new Fridge(new FridgeSpec());
        testFridge.setName("FridgeTwo");
        testFridge.setNominalPower(12.0);
        testFridge.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 45);

        // Act

        boolean actualResult = validFridge.equals(testFridge);

        // Assert

        assertFalse(actualResult);
    }


    @Test
    void seeIfEqualsWorksNotAnInstance() {
        // Act

        boolean actualResult = validFridge.equals(new EnergyGridList()); // Needed for sonarqube testing purposes.

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeEqualWorksNull() {
        // Act

        boolean actualResult = validFridge.equals(null); // Needed for sonarqube testing purposes.

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfPrintDeviceWorks() {
        // Arrange

        String expectedResult = "The device Name is FridgeOne, and its NominalPower is 12.0 kW.\n";

        // Act

        String result = validFridge.buildString();

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfSetNameWorks() {
        // Arrange

        validFridge.setName("Fridge 3000");
        String expectedResult = "Fridge 3000";

        // Act

        String actualResult = validFridge.getName();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetNominalPowerWorks() {
        // Arrange

        double expectedResult = 12;

        // Act

        double result = validFridge.getNominalPower();

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAttributeUnitWorks() {
        // Arrange

        String expectedResult1 = "Kg";
        String expectedResult2 = "kWh";

        // Act

        Object result1 = validFridge.getAttributeUnit(FridgeSpec.FREEZER_CAPACITY);
        Object result2 = validFridge.getAttributeUnit(FridgeSpec.REFRIGERATOR_CAPACITY);
        Object result3 = validFridge.getAttributeUnit(FridgeSpec.ANNUAL_CONSUMPTION);

        // Assert

        assertEquals(expectedResult1, result1);
        assertEquals(expectedResult1, result2);
        assertEquals(expectedResult2, result3);
    }

    @Test
    void seeIfGetSetAttributeValuesWorks() {
        // Arrange

        validFridge.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 13D);
        validFridge.setAttributeValue(FridgeSpec.REFRIGERATOR_CAPACITY, 14D);
        validFridge.setAttributeValue(FridgeSpec.ANNUAL_CONSUMPTION, 16D);

        // Act

        Object result1 = validFridge.getAttributeValue(FridgeSpec.ANNUAL_CONSUMPTION);
        Object result2 = validFridge.getAttributeValue(FridgeSpec.REFRIGERATOR_CAPACITY);
        Object result3 = validFridge.getAttributeValue(FridgeSpec.FREEZER_CAPACITY);

        // Assert

        assertEquals(16D, result1);
        assertEquals(14D, result2);
        assertEquals(13D, result3);
    }

    @Test
    void seeIfGetAttributeNamesWorks() {
        // Arrange

        List<String> expectedResult = new ArrayList<>();
        expectedResult.add(FridgeSpec.FREEZER_CAPACITY);
        expectedResult.add(FridgeSpec.REFRIGERATOR_CAPACITY);
        expectedResult.add(FridgeSpec.ANNUAL_CONSUMPTION);

        // Act

        List<String> result = validFridge.getAttributeNames();

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfDeactivateWorks() {
        // Act

        boolean actualResult = validFridge.deactivate();

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfDeactivateWorksAlreadyInactive() {
        // Arrange

        validFridge.deactivate();

        // Act

        boolean actualResult = validFridge.deactivate();

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfGetEnergyConsumptionWorks() {
        // Arrange

        double expectedResult = 288;

        // Act

        double actualResult = validFridge.getEnergyConsumption(24);

        // Assert

        assertEquals(expectedResult, actualResult);
    }


    @Test
    void seeIfGetLogListWorks() {
        // Arrange

        LogList expectedResult = new LogList();
        expectedResult.addLog(validLog);

        // Act

        LogList result = validFridge.getLogList();

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetLogListWorksEmpty() {
        // Arrange

        validFridge = new Fridge(new FridgeSpec());
        LogList expectedResult = new LogList();

        // Act

        LogList result = validFridge.getLogList();

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfAddLogWorksDuplicate() {
        // Act

        boolean result = validFridge.addLog(validLog);

        // Assert

        assertFalse(result);
    }

    @Test
    void seeIfAddLogWorksDeactivated() {
        // Arrange

        validFridge = new Fridge(new FridgeSpec());
        validFridge.deactivate();

        // Act

        boolean result = validFridge.addLog(validLog);

        // Assert

        assertFalse(result);
    }

    @Test
    void seeIfAddLogWorks() {
        // Arrange

        validFridge = new Fridge(new FridgeSpec());

        // Act

        boolean result = validFridge.addLog(validLog);

        // Assert

        assertTrue(result);
    }

    @Test
    void seeIfGetConsumptionInIntervalWorks() {
        // Arrange

        double expectedResult = 1;

        // Interval

        Date initialTime = new GregorianCalendar(2018, Calendar.JANUARY, 20, 10, 0,
                0).getTime();
        Date finalTime = new GregorianCalendar(2018, Calendar.FEBRUARY, 20, 11, 0,
                0).getTime();

        // Act

        double actualResult = validFridge.getConsumptionInInterval(initialTime, finalTime);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetConsumptionInIntervalWorksOutOfBounds() {
        // Arrange

        validFridge = new Fridge(new FridgeSpec());
        double expectedResult = 0.0;

        // Interval

        Date initialTime = new GregorianCalendar(2018, Calendar.SEPTEMBER, 20, 10, 0,
                0).getTime();
        Date finalTime = new GregorianCalendar(2018, Calendar.SEPTEMBER, 20, 11, 0,
                0).getTime();

        // Logs

        Log firstLog = new Log(21, new GregorianCalendar(2018, Calendar.SEPTEMBER, 19).getTime(),
                new GregorianCalendar(2018, Calendar.SEPTEMBER, 21).getTime());
        validFridge.addLog(firstLog);

        // Act

        double actualResult = validFridge.getConsumptionInInterval(initialTime, finalTime);

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

        Date initialTime = new GregorianCalendar(2018, Calendar.JANUARY, 20, 10, 0,
                0).getTime();
        Date finalTime = new GregorianCalendar(2018, Calendar.FEBRUARY, 20, 11, 0,
                0).getTime();

        // Act

        Integer actualResult = validFridge.countLogsInInterval(initialTime, finalTime);
        LogList actualResultList = validFridge.getLogList();

        // Assert

        assertEquals(expectedResult, actualResult);
        assertEquals(expectedResultList, actualResultList);
    }

    @Test
    void seeIfGetLogsInIntervalWorksOutOfBounds() {
        // Arrange

        validFridge = new Fridge(new FridgeSpec());
        LogList expectedResult = new LogList();

        // Interval

        Date initialTime = new GregorianCalendar(2018, Calendar.SEPTEMBER, 20, 10, 0,
                0).getTime();
        Date finalTime = new GregorianCalendar(2018, Calendar.SEPTEMBER, 20, 11, 0,
                0).getTime();

        // Logs

        Log firstLog = new Log(21, new GregorianCalendar(2018, Calendar.SEPTEMBER, 19).getTime(),
                new GregorianCalendar(2018, Calendar.SEPTEMBER, 21).getTime());
        validFridge.addLog(firstLog);

        // Act

        LogList actualResult = validFridge.getLogsInInterval(initialTime, finalTime);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfIsLogListEmptyWorks() {
        // Act

        boolean actualResult1 = validFridge.isLogListEmpty();

        // Assert

        assertFalse(actualResult1);

        // Arrange To Remove Log

        validFridge = new Fridge(new FridgeSpec());

        // Act

        boolean actualResult2 = validFridge.isLogListEmpty();

        // Assert

        assertTrue(actualResult2);
    }

    @Test
    void hashCodeDummyTest() {
        // Arrange

        int expectedResult = 1;

        // Act

        int actualResult = validFridge.hashCode();

        // Assert

        assertEquals(expectedResult, actualResult);
    }
}
