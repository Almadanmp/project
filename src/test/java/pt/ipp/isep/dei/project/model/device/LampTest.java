package pt.ipp.isep.dei.project.model.device;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.device.devicespecs.WaterHeaterSpec;
import pt.ipp.isep.dei.project.model.device.devicespecs.LampSpec;
import pt.ipp.isep.dei.project.model.device.log.Log;
import pt.ipp.isep.dei.project.model.device.log.LogList;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Lamp Device tests class.
 */

class LampTest {
    // Common testing artifacts for this class.

    private Lamp validLamp;
    private Log validLog;

    @BeforeEach
    void arrangeArtifacts() {
        validLamp = new Lamp(new LampSpec());
        validLamp.setNominalPower(30);
        validLamp.setName("validLampOne");
        validLog = new Log(1, new GregorianCalendar(2019, Calendar.FEBRUARY, 1).getTime(),
                new GregorianCalendar(2019, Calendar.FEBRUARY, 1).getTime());
        validLamp.addLog(validLog);
    }

    @Test
    void getDeviceTypeTest() {
        // Arrange

        String expectedResult = "Lamp";

        // Act

        String result = validLamp.getType();

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfEqualsWorksSameObject() {
        // Act

        boolean actualResult = validLamp.equals(validLamp); // Needed for sonarqube testing purposes.

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfGetNominalPowerWorks() {
        // Arrange

        double expectedResult = 30;

        // Act

        double result = validLamp.getNominalPower();

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfEqualsWorksFalse() {
        // Arrange

        Lamp testLamp = new Lamp(new LampSpec());
        testLamp.setName("LampTest");
        testLamp.setNominalPower(12.0);
        testLamp.setAttributeValue(LampSpec.FLUX, 34);

        // Act

        boolean actualResult = testLamp.equals(validLamp);

        // Assert

        assertFalse(actualResult);
    }


    @Test
    void seeIfEqualsWorksNotAnInstance() {
        // Act

        boolean actualResult = validLamp.equals(new WaterHeater(new WaterHeaterSpec())); // Needed for sonarqube testing purposes.

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksNull() {
        // Act

        boolean actualResult = validLamp.equals(null); // Needed for sonarqube testing purposes.

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfPrintDeviceWorks() {
        // Arrange

        String expectedResult = "The device Name is validLampOne, and its NominalPower is 30.0 kW.\n";

        // Act

        String result = validLamp.buildString();

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfSetNameWorks() {
        // Arrange

        String expectedResult = "testName";
        validLamp.setName("testName");

        // Act

        String result = validLamp.getName();

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAndSetAttributeValueWork() {
        // Arrange

        Double expectedResult = 33.3;
        validLamp.setAttributeValue(LampSpec.FLUX, 33.3);

        // Act

        Object result = validLamp.getAttributeValue(LampSpec.FLUX);

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAttributeUnitWorks() {
        // Arrange

        String expectedResult = "lm";

        // Act

        Object result = validLamp.getAttributeUnit(LampSpec.FLUX);

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAttributeNamesWorks() {
        // Act

        List<String> result = validLamp.getAttributeNames();
        int expectedResult =1;
        // Assert

        assertTrue(result.contains(LampSpec.FLUX));
        assertEquals(expectedResult, result.size());
    }

    @Test
    void seeIfDeactivateWorks() {
        // Act

        boolean actualResult = validLamp.deactivate();

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfDeactivateWorksAlreadyDeactivated() {
        // Arrange

        validLamp.deactivate();

        // Act

        boolean actualResult = validLamp.deactivate();

        // Assert

        assertFalse(actualResult);
    }


    @Test
    void seeIfGetEnergyConsumptionWorks() {
        // Arrange

        double expectedResult = 60;

        // Act

        double actualResult = validLamp.getEnergyConsumption(2);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetLogListWorks() {
        // Arrange

        LogList expectedResult = new LogList();
        expectedResult.addLog(validLog);

        // Act

        LogList result = validLamp.getLogList();

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetLogListWorksEmpty() {
        // Arrange

        validLamp = new Lamp(new LampSpec());
        LogList expectedResult = new LogList();

        // Act

        LogList result = validLamp.getLogList();

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfAddLogWorksDuplicate() {
        // Act

        boolean result = validLamp.addLog(validLog);

        // Assert

        assertFalse(result);
    }


    @Test
    void seeIfAddLogWorksDeactivated() {
        // Arrange

        validLamp = new Lamp(new LampSpec());
        validLamp.deactivate();

        // Act

        boolean result = validLamp.addLog(validLog);

        // Assert

        assertFalse(result);
    }

    @Test
    void seeIfAddLogWorks() {
        // Arrange

        validLamp = new Lamp(new LampSpec());

        // Act

        boolean result = validLamp.addLog(validLog);

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

        double actualResult = validLamp.getConsumptionInInterval(initialTime, finalTime);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetConsumptionInIntervalWorksOutOfBounds() {
        // Arrange

        validLamp = new Lamp(new LampSpec());
        double expectedResult = 0.0;

        // Interval

        Date initialTime = new GregorianCalendar(2018, Calendar.SEPTEMBER, 20, 10, 0,
                0).getTime();
        Date finalTime = new GregorianCalendar(2018, Calendar.SEPTEMBER, 20, 11, 0,
                0).getTime();

        // Logs

        Log firstLog = new Log(21, new GregorianCalendar(2018, Calendar.SEPTEMBER, 19).getTime(),
                new GregorianCalendar(2018, Calendar.SEPTEMBER, 21).getTime());
        validLamp.addLog(firstLog);

        // Act

        double actualResult = validLamp.getConsumptionInInterval(initialTime, finalTime);

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

        Integer actualResult = validLamp.countLogsInInterval(initialTime, finalTime);
        LogList actualResultList = validLamp.getLogList();

        // Assert

        assertEquals(expectedResult, actualResult);
        assertEquals(expectedResultList, actualResultList);
    }

    @Test
    void seeIfGetLogsInIntervalWorksOutOfBounds() {
        // Arrange

        validLamp = new Lamp(new LampSpec());
        LogList expectedResult = new LogList();

        // Interval

        Date initialTime = new GregorianCalendar(2018, Calendar.SEPTEMBER, 20, 10, 0,
                0).getTime();
        Date finalTime = new GregorianCalendar(2018, Calendar.SEPTEMBER, 20, 11, 0,
                0).getTime();

        // Logs

        Log firstLog = new Log(21, new GregorianCalendar(2018, Calendar.SEPTEMBER, 19).getTime(),
                new GregorianCalendar(2018, Calendar.SEPTEMBER, 21).getTime());
        validLamp.addLog(firstLog);

        // Act

        LogList actualResult = validLamp.getLogsInInterval(initialTime, finalTime);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetConsumptionWorksTimeZero() {
        // Arrange

        double expectedResult = 0;

        // Act

        double result = validLamp.getEnergyConsumption(0);

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfSetAttributeValueWorks() {
        //Arrange

        Double attribute = 6.0;

        // Proper attribute name and object.

        assertTrue(validLamp.setAttributeValue(LampSpec.FLUX, attribute));

        // Proper value object, but incorrect attribute name.

        assertFalse(validLamp.setAttributeValue("notFLUX", attribute));

        // Empty attribute name, proper value object.

        assertFalse(validLamp.setAttributeValue("", attribute));
    }

    @Test
    void seeIfSetAttributeValueWorksNotDouble() {
        //Arrange
        Integer attribute = 6;

        // Proper attribute name, but value isn't double:

        assertFalse(validLamp.setAttributeValue(LampSpec.FLUX, attribute));

        // Improper attribute name and value isn't double:

        assertFalse(validLamp.setAttributeValue("notFLUX", attribute));
        assertFalse(validLamp.setAttributeValue("notNOMINAL_POWER", attribute));

        // Empty attribute name and value isn't double:

        assertFalse(validLamp.setAttributeValue("", attribute));
    }

    @Test
    void seeIfIsLogListEmptyWorks() {
        //Act

        boolean actualResult1 = validLamp.isLogListEmpty();

        //Assert

        assertFalse(actualResult1);

        // Arrange To Remove Log

        validLamp = new Lamp(new LampSpec());

        //Act

        boolean actualResult2 = validLamp.isLogListEmpty();

        //Assert

        assertTrue(actualResult2);
    }

    @Test
    void hashCodeDummyTest() {
        // Arrange

        int expectedResult = 1;

        // Act

        int actualResult = validLamp.hashCode();

        // Assert

        assertEquals(expectedResult, actualResult);
    }
}
