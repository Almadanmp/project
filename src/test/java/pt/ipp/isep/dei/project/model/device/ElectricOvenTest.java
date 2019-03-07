package pt.ipp.isep.dei.project.model.device;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Room;
import pt.ipp.isep.dei.project.model.RoomList;
import pt.ipp.isep.dei.project.model.device.devicespecs.ElectricOvenSpec;
import pt.ipp.isep.dei.project.model.device.log.Log;
import pt.ipp.isep.dei.project.model.device.log.LogList;
import pt.ipp.isep.dei.project.model.device.program.FixedTimeProgram;
import pt.ipp.isep.dei.project.model.device.program.ProgramList;
import pt.ipp.isep.dei.project.model.device.program.VariableTimeProgram;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

class ElectricOvenTest {
    private ElectricOven validOven = new ElectricOven(new ElectricOvenSpec());
    private VariableTimeProgram validVariableTimeProgram;
    private Log validLog;


    @BeforeEach
    void arrangeArtifacts() {
        validVariableTimeProgram = new VariableTimeProgram("VariableTimeProgram 1", 70);
        validOven.setName("Electric Oven 3000");
        validOven.setNominalPower(15);
        validLog = new Log(1, new GregorianCalendar(2019, Calendar.FEBRUARY, 1).getTime(),
                new GregorianCalendar(2019, Calendar.FEBRUARY, 1).getTime());
        validOven.addLog(validLog);
    }

    @Test
    void seeIfGetConsumptionWorks() {
        // Arrange

        double expectedResult = 360;
        double expectedResultZero = 0;

        // Act

        double result = validOven.getEnergyConsumption(24);
        double resultZero = validOven.getEnergyConsumption(0);

        // Assert

        assertEquals(expectedResult, result);
        assertEquals(expectedResultZero, resultZero);
    }

    @Test
    void seeIfGetProgramConsumptionWorks() {
        // Arrange

        double expectedResult = 1400;

        // Act

        double result = validOven.getProgramEnergyConsumption(20, validVariableTimeProgram);

        // Assert

        assertEquals(expectedResult, result);

    }

    @Test
    void seeIfPrintDeviceWorks() {
        // Arrange

        String expectedResult = "The device Name is Electric Oven 3000, and its NominalPower is 15.0 kW.\n";

        // Act

        String result = validOven.buildString();

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetSetNameWorks() {
        // Arrange

        validOven.setName("Fridge 3000");
        String expectedResult = "Fridge 3000";

        // Act

        String result = validOven.getName();

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetSetNominalPowerWorks() {
        // Arrange

        validOven.setNominalPower(150);
        double expectedResult = 150;

        // Act

        double result = validOven.getNominalPower();

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetSetAttributeValueWorks() {
        // Test for get with an empty string.

        // Arrange

        Object expectedResult = 0;

        // Act

        Object result = validOven.getAttributeValue("");

        // Assert

        assertEquals(expectedResult, result);

        // Test for set with an empty string.

        // Act

        boolean resultSet = validOven.setAttributeValue("", 10);

        // Assert

        assertFalse(resultSet);
    }

    @Test
    void seeIfGetAttributeUnitsWorksEmptyAttribute(){
        // Test for getting an attribute unit for an empty attribute string.

        // Act

        Object resultUnit = validOven.getAttributeUnit("");

        // Assert

        assertEquals(false, resultUnit);
    }

    @Test
    void seeIfGetAttributeNamesWorksEmpty(){
        // Act

        List<String> resultString = validOven.getAttributeNames();

        // Assert

        assertEquals(new ArrayList<>(), resultString);
    }

    @Test
    void seeIfDeactivateWorks() {
        // Act

        boolean actualResult = validOven.deactivate();

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfDeactivateWorksAlreadyDeactivated() {
        // Arrange

        validOven.deactivate();

        // Act

        boolean actualResult = validOven.deactivate();

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfGetLogListWorks() {
        // Arrange

        LogList expectedResult = new LogList();
        expectedResult.addLog(validLog);

        // Act

        LogList result = validOven.getLogList();

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetDeviceTypeWorks() {
        // Arrange

        String expectedResult = "Electric Oven";

        // Act

        String result = validOven.getType();

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfEqualsWorksOnItself() {
        // Act

        boolean actualResult = validOven.equals(validOven); // Needed for sonarqube testing purposes.

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfEqualsWorksFalse() {
        // Arrange

        ElectricOven testOven = new ElectricOven(new ElectricOvenSpec());
        testOven.setName("EOTwo");
        testOven.setNominalPower(12.0);

        // Act

        boolean actualResult = validOven.equals(testOven);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeEqualsWorksNotAnInstance() {
        // Act

        boolean actualResult = validOven.equals(new RoomList()); // Needed for sonarqube testing purposes.

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksNull() {
        // Act

        boolean actualResult = validOven.equals(null);  // Needed for sonarqube testing purposes.

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfGetConsumptionInIntervalWorksBounds() {
        // Act

        Date initialTime = new GregorianCalendar(2019, Calendar.JANUARY, 31, 10,0,
                0).getTime();
        Date finalTime = new GregorianCalendar(2019, Calendar.FEBRUARY, 2, 11,0,
                0).getTime();
        double result = validOven.getConsumptionInInterval(initialTime, finalTime);

        // Assert

        assertEquals(1, result);
    }

    @Test
    void seeIfGetConsumptionInIntervalWorksInBounds() {
        // Arrange

        // Time Interval

        Date initialTime = new GregorianCalendar(2018, Calendar.OCTOBER, 20, 10,0,
                0).getTime();
        Date finalTime = new GregorianCalendar(2018, Calendar.OCTOBER, 20, 11,0,
                0).getTime();

        // Logs

        Log firstLog = new Log(120, new GregorianCalendar(2018, Calendar.OCTOBER, 20, 10,
                1, 0).getTime(), new GregorianCalendar(2018, Calendar.OCTOBER, 20,
                10, 20, 0).getTime());
        Log secondLog = new Log(55, new GregorianCalendar(2018, Calendar.OCTOBER, 20, 10,
                30, 0).getTime(), new GregorianCalendar(2018, Calendar.OCTOBER, 20,
                10, 59, 0).getTime());
        validOven.addLog(firstLog);
        validOven.addLog(secondLog);

        // Act

        double result = validOven.getConsumptionInInterval(initialTime, finalTime);

        // Assert
        assertEquals(175, result);
    }

    @Test
    void seeIfGetConsumptionInIntervalWorksOutOfBounds() {
        // Arrange

        // Time Interval

        Date initialTime = new GregorianCalendar(2018, Calendar.OCTOBER, 20, 10,0,
                0).getTime();
        Date finalTime = new GregorianCalendar(2018, Calendar.OCTOBER, 20, 11,0,
                0).getTime();

        // Logs

        Log firstLog = new Log(56, new GregorianCalendar(2018, Calendar.OCTOBER, 20, 9,
                1, 0).getTime(), new GregorianCalendar(2018, Calendar.OCTOBER, 20,
                10, 20, 0).getTime());
        Log secondLog = new Log(55, new GregorianCalendar(2018, Calendar.OCTOBER, 20,
                10, 30, 0).getTime(), new GregorianCalendar(2018, Calendar.OCTOBER,
                20, 11, 20, 0).getTime());
        validOven.addLog(firstLog);
        validOven.addLog(secondLog);

        // Act

        double result = validOven.getConsumptionInInterval(initialTime, finalTime);

        // Assert

        assertEquals(0.0, result);
    }

    @Test
    void seeIfCountLogsInIntervalWorks() {
        // Arrange

        // Interval

        Date initialTime = new GregorianCalendar(2019, Calendar.JANUARY, 31, 10,0,
                0).getTime();
        Date finalTime = new GregorianCalendar(2019, Calendar.FEBRUARY, 2, 11,0,
                0).getTime();

        // Act

        double result = validOven.countLogsInInterval(initialTime, finalTime);

        // Assert

        assertEquals(1, result);
    }

    @Test
    void seeIfGetLogsInIntervalWorks() {
        // Arrange

        // Interval

        Date initialTime = new GregorianCalendar(2019, Calendar.JANUARY, 31, 10,0,
                0).getTime();
        Date finalTime = new GregorianCalendar(2019, Calendar.FEBRUARY, 2, 11,0,
                0).getTime();

        // Expected

        LogList expectedResult = new LogList();
        expectedResult.addLog(validLog);

        // Act

        LogList result = validOven.getLogsInInterval(initialTime, finalTime);

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetConsumptionWorksTimeZero() {
        // Arrange

        double expectedResult = 0;

        // Act

        double result = validOven.getEnergyConsumption(0);

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetProgramListWorks() {
        // Arrange

        FixedTimeProgram testProgram = new FixedTimeProgram("programa", 2, 2);
        ProgramList testList = new ProgramList();
        testList.add(testProgram);
        validOven.setProgramList(testList);

        // Act

        ProgramList result = validOven.getProgramList();

        // Assert

        assertEquals(testList, result);
    }

    @Test
    void seeIfAddLogWorksDuplicate() {
        // Act and Assert

        assertFalse(validOven.addLog(validLog));
    }


    @Test
    void seeIfGetLogListWorksEmpty() {
        // Arrange

        validOven = new ElectricOven(new ElectricOvenSpec());
        LogList expectedResult = new LogList();

        // Act

        LogList actualResult = validOven.getLogList();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfAddLogWorksInactive() {
        // Arrange

        this.validOven.deactivate();

        // Act

        boolean result = this.validOven.addLog(validLog);

        // Assert

        assertFalse(result);
    }

    @Test
    void seeIfAddLogWorks() {
        // Arrange

        validOven = new ElectricOven(new ElectricOvenSpec());

        // Act

        boolean result = validOven.addLog(validLog);

        // Assert

        assertTrue(result);
    }


    @Test
    void seeIfIsLogListEmptyWorks() {
        // Arrange

        validOven = new ElectricOven(new ElectricOvenSpec());

        // Act

        boolean result = validOven.isLogListEmpty();

        // Assert

        assertTrue(result);
    }

    @Test
    void seeIfIsLogListEmptyWorksFalse() {
        // Act

        boolean result = validOven.isLogListEmpty();

        // Assert

        assertFalse(result);
    }

    @Test
    void hashCodeDummyTest() {
        int expectedResult = 1;
        int actualResult = validOven.hashCode();
        assertEquals(expectedResult, actualResult);
    }
}


