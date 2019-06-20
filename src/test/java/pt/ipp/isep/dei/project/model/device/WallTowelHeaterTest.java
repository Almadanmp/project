package pt.ipp.isep.dei.project.model.device;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.device.devicespecs.WallTowelHeaterSpec;
import pt.ipp.isep.dei.project.model.device.log.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class WallTowelHeaterTest {
    // Common artifacts for testing in this class.

    private WallTowelHeater validWTHeater;
    private Log validLog;

    @BeforeEach
    void arrangeArtifacts() {
        validWTHeater = new WallTowelHeater(new WallTowelHeaterSpec());
        validWTHeater.setNominalPower(20);
        validWTHeater.setName("WallTowelHeater");
        validLog = new Log(1, new GregorianCalendar(2019, Calendar.FEBRUARY, 1).getTime(),
                new GregorianCalendar(2019, Calendar.FEBRUARY, 1).getTime());
        validWTHeater.addLog(validLog);
    }

    @Test
    void testIsLogListEmpty() {
        // Arrange
        WallTowelHeater emptyWallTH = new WallTowelHeater(new WallTowelHeaterSpec());
        // Act
       boolean actualResult1 = validWTHeater.isLogListEmpty();
       boolean actualResult2 = emptyWallTH.isLogListEmpty();
        // Assert
        assertFalse(actualResult1);
        assertTrue(actualResult2);
    }

    @Test
    void seeIIfGetDeviceTypeWorks() {
        // Arrange

        String expectedResult = "WallTowelHeater";

        // Act

        String actualResult = validWTHeater.getType();

        // Assert

        assertEquals(actualResult, expectedResult);
    }

    @Test
    void seeIfSetGetNameWork() {
        // Arrange

        validWTHeater.setName("TowelHeater");
        String expectedResult = "TowelHeater";

        // Act

        String actualResult = validWTHeater.getName();

        // Assert

        assertEquals(actualResult, expectedResult);
    }

    @Test
    void seeIfSetGetNominalPowerWork() {
        // Arrange

        validWTHeater.setNominalPower(20);
        double expectedResult = 20;

        // Act

        double actualResult = validWTHeater.getNominalPower();

        // Assert

        assertEquals(actualResult, expectedResult);
    }

    @Test
    void seeIfEqualsWorksOnItself() {
        assertEquals(validWTHeater, validWTHeater);
    }

    @Test
    void seeIfEqualsWorksFalse() {
        // Arrange

        WallTowelHeater testHeater = new WallTowelHeater(new WallTowelHeaterSpec());
        testHeater.setName("WTHeater2");
        testHeater.setNominalPower(45);

        // Act

        boolean actualResult = validWTHeater.equals(testHeater);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksNull() {


        assertNotEquals(null, validWTHeater);
    }

    @Test
    void seeIfBuildStringWorks() {
        // Arrange

        String expectedResult = "The device Name is WallTowelHeater, and its NominalPower is 20.0 kW.\n";

        // Act

        String actualResult = validWTHeater.buildString();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfEqualsWorks() {

        WallTowelHeater wTH = new WallTowelHeater(new WallTowelHeaterSpec());
        WallTowelHeater wTH2 = new WallTowelHeater(new WallTowelHeaterSpec());

        wTH.setName("validWallTH");
        wTH2.setName("WC WallTowerlHeater");

        boolean actualResult1 = validWTHeater.equals(wTH);
        boolean actualResult2 = validWTHeater.equals(wTH2);
        boolean actualResult3 = validWTHeater.equals(20D);
        boolean actualResult4 = validWTHeater.equals(validWTHeater);
        boolean actualResult5 = validWTHeater.equals(null);

        assertFalse(actualResult1);
        assertFalse(actualResult2);
        assertFalse(actualResult3);
        assertTrue(actualResult4);
        assertFalse(actualResult5);
    }

    @Test
    void seeIfSetGetAttributeValueWorkWrongAttributeName() {
        // Arrange

        validWTHeater.setAttributeValue("Anything", 10);
        Integer expectedResult = 0;

        // Act

        Object actualResult = validWTHeater.getAttributeValue("Anything");

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSetGetAttributeUnitWorkWrongAttributeName() {
        // Arrange

        validWTHeater.setAttributeValue("Anything", 10);

        // Act

        Object actualResult = validWTHeater.getAttributeUnit("Anything");

        // Assert

        assertEquals(false, actualResult);
    }

    @Test
    void seeIfGetAttributeNamesWorks() {
        // Arrange

        validWTHeater.setAttributeValue("Anything", 10);
        List<String> expectedResult = new ArrayList<>();

        // Act

        Object actualResult = validWTHeater.getAttributeNames();

        // Assert

        assertEquals(actualResult, expectedResult);
    }

    @Test
    void seeIfDeactivateWorks() {
        // Act

        boolean actualResult = validWTHeater.deactivate();

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfDeactivateWorksAlreadyDeactivated() {
        // Arrange

        validWTHeater.deactivate();

        // Act

        boolean actualResult = validWTHeater.deactivate();

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfGetEnergyConsumptionWorks() {
        // Arrange

        validWTHeater.setNominalPower(20.0);
        double expectedResult = 40.0;

        // Act

        double actualResult = validWTHeater.getEnergyConsumption(2);

        // Assert

        assertEquals(actualResult, expectedResult);
    }

    @Test
    void seeIfGetLogListWorks() {
        // Arrange

        LogList expectedResult = new LogList();
        expectedResult.addLog(validLog);

        // Act

        LogList actualResult = validWTHeater.getLogList();

        // Assert

        assertEquals(actualResult, expectedResult);
    }

    @Test
    void seeIfAddLogWorksDuplicate() {
        // Act

        boolean actualResult = validWTHeater.addLog(validLog);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfAddLogWorks() {
        // Arrange

        validWTHeater = new WallTowelHeater(new WallTowelHeaterSpec());

        // Act

        boolean actualResult = validWTHeater.addLog(validLog);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfAddLogWorksDeactivated() {
        // Arrange

        validWTHeater = new WallTowelHeater(new WallTowelHeaterSpec());
        validWTHeater.deactivate();

        // Act

        boolean actualResult = validWTHeater.addLog(validLog);

        // Assert

        assertFalse(actualResult);
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

        double actualResult = validWTHeater.getConsumptionInInterval(initialTime, finalTime);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetConsumptionInIntervalWorksOutOfBounds() {
        // Arrange

        validWTHeater = new WallTowelHeater(new WallTowelHeaterSpec());
        double expectedResult = 0.0;

        // Interval

        Date initialTime = new GregorianCalendar(2018, Calendar.SEPTEMBER, 20, 10, 0,
                0).getTime();
        Date finalTime = new GregorianCalendar(2018, Calendar.SEPTEMBER, 20, 11, 0,
                0).getTime();

        // Logs

        Log firstLog = new Log(21, new GregorianCalendar(2018, Calendar.SEPTEMBER, 19).getTime(),
                new GregorianCalendar(2018, Calendar.SEPTEMBER, 21).getTime());
        validWTHeater.addLog(firstLog);

        // Act

        double actualResult = validWTHeater.getConsumptionInInterval(initialTime, finalTime);

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

        Integer actualResult = validWTHeater.countLogsInInterval(initialTime, finalTime);
        LogList actualResultList = validWTHeater.getLogList();

        // Assert

        assertEquals(expectedResult, actualResult);
        assertEquals(expectedResultList, actualResultList);
    }

    @Test
    void seeIfGetLogsInIntervalWorksOutOfBounds() {
        // Arrange

        validWTHeater = new WallTowelHeater(new WallTowelHeaterSpec());
        LogList expectedResult = new LogList();

        // Interval

        Date initialTime = new GregorianCalendar(2018, Calendar.SEPTEMBER, 20, 10, 0,
                0).getTime();
        Date finalTime = new GregorianCalendar(2018, Calendar.SEPTEMBER, 20, 11, 0,
                0).getTime();

        // Logs

        Log firstLog = new Log(21, new GregorianCalendar(2018, Calendar.SEPTEMBER, 19).getTime(),
                new GregorianCalendar(2018, Calendar.SEPTEMBER, 21).getTime());
        validWTHeater.addLog(firstLog);

        // Act

        LogList actualResult = validWTHeater.getLogsInInterval(initialTime, finalTime);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetConsumptionWorks() {
        // Arrange

        validWTHeater.setNominalPower(15);
        double expectedResult = 360;

        // Act

        double result = validWTHeater.getEnergyConsumption(24);

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetConsumptionWorksTimeZero() {
        // Arrange

        validWTHeater.setNominalPower(15);
        double expectedResult = 0;

        // Act

        double result = validWTHeater.getEnergyConsumption(0);

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfSetAttributeValueWorksWrongName() {
        // Arrange

        Double attribute = 6.0;

        // Wrong attribute name.

        assertFalse(validWTHeater.setAttributeValue("Anything", attribute));

        // Empty attribute name.

        assertFalse(validWTHeater.setAttributeValue("", attribute));
    }

    @Test
    void seeIfSetAttributeValueWorksNotDouble() {
        // Arrange

        Integer attribute = 6;

        // Wrong attribute name and not double:

        assertFalse(validWTHeater.setAttributeValue("Anything", attribute));
        assertFalse(validWTHeater.setAttributeValue("notAnything", attribute));
        assertFalse(validWTHeater.setAttributeValue("notNOMINAL_POWER", attribute));

        // Empty attribute name and not double:

        assertFalse(validWTHeater.setAttributeValue("", attribute));
    }

    @Test
    void hashCodeDummyTest() {
        // Arrange

        int expectedResult = 1;

        // Act

        int actualResult = validWTHeater.hashCode();

        // Assert

        assertEquals(actualResult, expectedResult);
    }
}
