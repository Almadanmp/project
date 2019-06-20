package pt.ipp.isep.dei.project.model.device;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.device.devicespecs.FanSpec;
import pt.ipp.isep.dei.project.model.device.log.Log;
import pt.ipp.isep.dei.project.model.device.log.LogList;
import pt.ipp.isep.dei.project.model.device.program.ProgramList;
import pt.ipp.isep.dei.project.model.device.program.VariableTimeProgram;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.jupiter.api.Assertions.*;

class FanTest {
    // Common testing artifacts for testing in this class.

    private Fan validFan = new Fan(new FanSpec());
    private Log validLog;

    @BeforeEach
    void arrangeArtifacts() {
        validFan = new Fan(new FanSpec());
        validFan.setName("Fan");
        validFan.setNominalPower(150.0);
        validLog = new Log(1, new GregorianCalendar(2019, Calendar.FEBRUARY, 1).getTime(),
                new GregorianCalendar(2019, Calendar.FEBRUARY, 1).getTime());
        validFan.addLog(validLog);
    }

    @Test
    void seeIfConstructorWorks(){
        // Assert

        assertThat(validFan, instanceOf(Fan.class));
    }

    @Test
    void seeIfSetGetNameWorks(){
        // Arrange

        String expectedResult = "Mock";
        validFan.setName("Mock");

        // Act

        String actualResult = validFan.getName();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetTypeWorks(){
        // Arrange

        String expectedResult = "Fan";

        // Act

        String actualResult = validFan.getType();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSetGetNominalPowerWorks(){
        // Arrange

        double expectedResult = 31;
        validFan.setNominalPower(31);

        // Act

        double actualResult = validFan.getNominalPower();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfIsActiveDeactivateWorks(){
        // Act

        boolean actualResult = validFan.isActive();

        // Assert

        assertTrue(actualResult);

        // Arrange

        boolean expectedResult = validFan.deactivate();

        // Assert

        assertTrue(expectedResult);

        // Act

        actualResult =  validFan.isActive();
        boolean actualResultDeactivateAgain = validFan.deactivate();

        // Assert

        assertFalse(actualResultDeactivateAgain);
        assertFalse(actualResult);
    }

    @Test
    void seeIfBuildString(){
        // Arrange

        String expectedResult = "The device Name is Fan, and its NominalPower is 150.0 kW.\n";

        // Act

        String actualResult = validFan.buildString();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetLogListWorks() {
        // Arrange

        LogList expectedResult = new LogList();
        expectedResult.addLog(validLog);

        // Act

        LogList result = validFan.getLogList();

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetLogListWorksEmpty() {
        // Arrange

        LogList expectedResult = new LogList();
        validFan = new Fan(new FanSpec());

        // Act

        LogList result = validFan.getLogList();

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfAddLogWorksDuplicate() {
        // Arrange

        validFan = new Fan(new FanSpec());

        // Act

        boolean result = validFan.addLog(validLog);

        // Assert

        assertTrue(result);
    }

    @Test
    void seeIfAddLogWorksDeactivated() {
        // Arrange

        validFan = new Fan(new FanSpec());
        validFan.deactivate();

        // Act

        boolean result = validFan.addLog(validLog);

        // Assert

        assertFalse(result);
    }

    @Test
    void seeIfAddLogWorks() {
        // Arrange

        validFan = new Fan(new FanSpec());
        validFan.addLog(validLog);

        // Act

        boolean result = validFan.addLog(validLog);

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

        double actualResult = validFan.getConsumptionInInterval(initialTime, finalTime);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetConsumptionInIntervalWorksOutOfBounds() {
        // Arrange

        validFan = new Fan(new FanSpec());
        double expectedResult = 0.0;

        // Interval

        Date initialTime = new GregorianCalendar(2018, Calendar.SEPTEMBER, 20, 10, 0,
                0).getTime();
        Date finalTime = new GregorianCalendar(2018, Calendar.SEPTEMBER, 20, 11, 0,
                0).getTime();

        // Logs

        Log firstLog = new Log(21, new GregorianCalendar(2018, Calendar.SEPTEMBER, 19).getTime(),
                new GregorianCalendar(2018, Calendar.SEPTEMBER, 21).getTime());
        validFan.addLog(firstLog);

        // Act

        double actualResult = validFan.getConsumptionInInterval(initialTime, finalTime);

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

        Integer actualResult = validFan.countLogsInInterval(initialTime, finalTime);
        LogList actualResultList = validFan.getLogList();

        // Assert

        assertEquals(expectedResult, actualResult);
        assertEquals(expectedResultList, actualResultList);
    }

    @Test
    void seeIfGetLogsInIntervalWorksOutOfBounds() {
        // Arrange

        validFan = new Fan(new FanSpec());
        LogList expectedResult = new LogList();

        // Interval

        Date initialTime = new GregorianCalendar(2018, Calendar.SEPTEMBER, 20, 10, 0,
                0).getTime();
        Date finalTime = new GregorianCalendar(2018, Calendar.SEPTEMBER, 20, 11, 0,
                0).getTime();

        // Logs

        Log firstLog = new Log(21, new GregorianCalendar(2018, Calendar.SEPTEMBER, 19).getTime(),
                new GregorianCalendar(2018, Calendar.SEPTEMBER, 21).getTime());
        validFan.addLog(firstLog);

        // Act

        LogList actualResult = validFan.getLogsInInterval(initialTime, finalTime);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetConsumptionWorks() {
        // Arrange

        double expectedResult = 3600;

        // Act

        double result = validFan.getEnergyConsumption(24);

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetConsumptionWorksTimeZero() {
        // Arrange

        double expectedResult = 0;

        // Act

        double result = validFan.getEnergyConsumption(0);

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfIsLogListEmptyWorks() {
        // Assert

        assertFalse(validFan.isLogListEmpty());
    }

    @Test
    void seeIfIsLogListEmptyTrue() {
        // Arrange

        validFan = new Fan(new FanSpec());

        // Assert

        assertTrue(validFan.isLogListEmpty());
    }

    @Test
    void seeIfGetProgramListWorks() {
        // Arrange

        VariableTimeProgram program = new VariableTimeProgram("Program 1", 100);
        ProgramList expectedResult = new ProgramList();
        expectedResult.add(program);
        validFan.setProgramList(expectedResult);

        // Act

        ProgramList actualResult = validFan.getProgramList();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetAttributeNamesWorks() {
        // Arrange

        List<String> expectedResult = new ArrayList<>();

        // Act

        List<String> actualResult = validFan.getAttributeNames();

        // Assert

        assertEquals(expectedResult, actualResult);
    }
    
    @Test
    void seeIfGetAttributeValueWorks() {
        assertThrows(UnsupportedOperationException.class, () -> validFan.getAttributeValue("Attribute"));
    }

    @Test
    void seeIfGetAttributeUnitWorks() {
        assertThrows(UnsupportedOperationException.class, () -> validFan.getAttributeUnit("Attribute"));
    }

    @Test
    void seeIfSetAttributeValueWorks(){
        assertThrows(UnsupportedOperationException.class, () -> validFan.setAttributeValue("Attribute", "String"));
    }
}