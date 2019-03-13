package pt.ipp.isep.dei.project.model.device;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.RoomList;
import pt.ipp.isep.dei.project.model.device.devicespecs.StoveSpec;
import pt.ipp.isep.dei.project.model.device.log.Log;
import pt.ipp.isep.dei.project.model.device.log.LogList;
import pt.ipp.isep.dei.project.model.device.program.ProgramList;
import pt.ipp.isep.dei.project.model.device.program.VariableTimeProgram;

import java.util.*;

import static org.testng.Assert.*;

class StoveTest {
    // Common testing artifacts for this class

    private Stove validStove;
    private Log validLog; // February 1st, 2019.

    @BeforeEach
    void arrangeArtifacts() {
        validStove = new Stove(new StoveSpec());
        validStove.setName("Stove 3000");
        validStove.setNominalPower(150.0);
        validLog = new Log(1, new GregorianCalendar(2019, Calendar.FEBRUARY, 1).getTime(),
                new GregorianCalendar(2019, Calendar.FEBRUARY, 1).getTime());
        validStove.addLog(validLog);
    }

    @Test
    void seeIfGetDeviceTypeWorks() {
        // Arrange

        String expectedResult = "Stove";

        // Act

        String result = validStove.getType();

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfSetAttributeValueWorksNull() {
        // Act

        boolean result = validStove.setAttributeValue(null, 12D);

        // Assert

        assertFalse(result);
    }

    @Test
    void seeIfEqualsWorks() {

        Stove validStove2 = new Stove(new StoveSpec());
        Stove validStove3 = new Stove(new StoveSpec());

        validStove2.setName("validStove2");
        validStove3.setName("Kitchen Stove");

        boolean actualResult1 = validStove.equals(validStove2);
        boolean actualResult2 = validStove.equals(validStove3);
        boolean actualResult3 = validStove.equals(20D);
        boolean actualResult4 = validStove.equals(validStove);
        boolean actualResult5 = validStove.equals(null);

        assertFalse(actualResult1);
        assertFalse(actualResult2);
        assertFalse(actualResult3);
        assertTrue(actualResult4);
        assertFalse(actualResult5);
    }

    @Test
    void seeIfBuildStringWorks() {
        // Arrange

        String expectedResult = "The device Name is Stove 3000, and its NominalPower is 150.0 kW.\n";

        // Act

        String result = validStove.buildString();

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfSetNameWorks() {
        // Arrange

        validStove.setName("Stove 2000");
        String expectedResult = "Stove 2000";

        // Act

        String result = validStove.getName();

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfSetNominalPowerWorksHappyCase() {
        validStove.setNominalPower(12D);
        double expectedResult = 12;

        double result = validStove.getNominalPower();

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfSetNominalPowerWorksNegative() {
        assertThrows(IllegalArgumentException.class,
                () -> validStove.setNominalPower(-20));
    }

    @Test
    void seeIfSetNominalPowerWorksIfZero() {
        validStove.setNominalPower(0D);
        double expectedResult = 0.0;

        double result = validStove.getNominalPower();

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfDeactivateWorks() {
        // Act

        boolean actualResult = validStove.deactivate();

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfDeactivateWorksAlreadyDeactivated() {
        // Arrange

        validStove.deactivate();

        // Act

        boolean actualResult = validStove.deactivate();

        // Assert

        assertFalse(actualResult);
    }


    @Test
    void seeIfGetEnergyConsumptionWorks() {
        // Arrange

        double expectedResult = 300;

        // Act

        double actualResult = validStove.getEnergyConsumption(2);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetLogListWorks() {
        // Arrange

        LogList expectedResult = new LogList();
        expectedResult.addLog(validLog);

        // Act

        LogList result = validStove.getLogList();

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetLogListWorksEmpty() {
        // Arrange

        LogList expectedResult = new LogList();
        validStove = new Stove(new StoveSpec());

        // Act

        LogList result = validStove.getLogList();

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfAddLogWorksDuplicate() {
        // Arrange

        validStove = new Stove(new StoveSpec());

        // Act

        boolean result = validStove.addLog(validLog);

        // Assert

        assertTrue(result);
    }

    @Test
    void seeIfAddLogWorksDeactivated() {
        // Arrange

        validStove = new Stove(new StoveSpec());
        validStove.deactivate();

        // Act

        boolean result = validStove.addLog(validLog);

        // Assert

        assertFalse(result);
    }

    @Test
    void seeIfAddLogWorks() {
        // Arrange

        validStove = new Stove(new StoveSpec());
        validStove.addLog(validLog);

        // Act

        boolean result = validStove.addLog(validLog);

        // Assert

        assertFalse(result);
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

        double actualResult = validStove.getConsumptionInInterval(initialTime, finalTime);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetConsumptionInIntervalWorksOutOfBounds() {
        // Arrange

        validStove = new Stove(new StoveSpec());
        double expectedResult = 0.0;

        // Interval

        Date initialTime = new GregorianCalendar(2018, Calendar.SEPTEMBER, 20, 10, 0,
                0).getTime();
        Date finalTime = new GregorianCalendar(2018, Calendar.SEPTEMBER, 20, 11, 0,
                0).getTime();

        // Logs

        Log firstLog = new Log(21, new GregorianCalendar(2018, Calendar.SEPTEMBER, 19).getTime(),
                new GregorianCalendar(2018, Calendar.SEPTEMBER, 21).getTime());
        validStove.addLog(firstLog);

        // Act

        double actualResult = validStove.getConsumptionInInterval(initialTime, finalTime);

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

        Integer actualResult = validStove.countLogsInInterval(initialTime, finalTime);
        LogList actualResultList = validStove.getLogList();

        // Assert

        assertEquals(expectedResult, actualResult);
        assertEquals(expectedResultList, actualResultList);
    }

    @Test
    void seeIfGetLogsInIntervalWorksOutOfBounds() {
        // Arrange

        validStove = new Stove(new StoveSpec());
        LogList expectedResult = new LogList();

        // Interval

        Date initialTime = new GregorianCalendar(2018, Calendar.SEPTEMBER, 20, 10, 0,
                0).getTime();
        Date finalTime = new GregorianCalendar(2018, Calendar.SEPTEMBER, 20, 11, 0,
                0).getTime();

        // Logs

        Log firstLog = new Log(21, new GregorianCalendar(2018, Calendar.SEPTEMBER, 19).getTime(),
                new GregorianCalendar(2018, Calendar.SEPTEMBER, 21).getTime());
        validStove.addLog(firstLog);

        // Act

        LogList actualResult = validStove.getLogsInInterval(initialTime, finalTime);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetConsumptionWorks() {
        // Arrange

        double expectedResult = 3600;

        // Act

        double result = validStove.getEnergyConsumption(24);

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetConsumptionWorksTimeZero() {
        // Arrange

        double expectedResult = 0;

        // Act

        double result = validStove.getEnergyConsumption(0);

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetProgramConsumptioNWorks() {
        // Arrange

        VariableTimeProgram program = new VariableTimeProgram("Program 1", 100);
        double expectedResult = 300;

        // Act

        double actualResult = validStove.getProgramConsumption(3, program);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfIsLogListEmptyWorks() {
        // Assert

        assertFalse(validStove.isLogListEmpty());
    }

    @Test
    void seeIfIsLogListEmptyTrue() {
        // Arrange

        validStove = new Stove(new StoveSpec());

        // Assert

        assertTrue(validStove.isLogListEmpty());
    }

    @Test
    void seeIfGetAttributeNamesWorks() {
        // Arrange

        List<String> expectedResult = new ArrayList<>();

        // Act

        List<String> actualResult = validStove.getAttributeNames();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetProgramListWorks() {
        // Arrange

        VariableTimeProgram program = new VariableTimeProgram("Program 1", 100);
        ProgramList expectedResult = new ProgramList();
        expectedResult.add(program);
        validStove.setProgramList(expectedResult);

        // Act

        ProgramList actualResult = validStove.getProgramList();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetAttributeUnitWorksWrongAttributeName() {
        // Act

        Object actualResult = validStove.getAttributeUnit("Unit");

        // Assert

        assertEquals(false, actualResult);
    }

    @Test
    void seeIfGetAttributeValueWorksWrongAttributeName() {
        // Act

        Object actualResult = validStove.getAttributeValue("Unit");

        // Assert

        assertEquals(0, actualResult);
    }

    @Test
    void hashCodeDummyTest() {
        // Arrange

        int expectedResult = 1;

        // Act

        int actualResult = validStove.hashCode();

        // Assert

        assertEquals(expectedResult, actualResult);
    }
}