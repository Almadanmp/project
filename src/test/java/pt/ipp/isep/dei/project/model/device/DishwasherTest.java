package pt.ipp.isep.dei.project.model.device;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.RoomList;
import pt.ipp.isep.dei.project.model.device.devicespecs.DishwasherSpec;
import pt.ipp.isep.dei.project.model.device.devicespecs.WashingMachineSpec;
import pt.ipp.isep.dei.project.model.device.log.Log;
import pt.ipp.isep.dei.project.model.device.log.LogList;
import pt.ipp.isep.dei.project.model.device.program.FixedTimeProgram;
import pt.ipp.isep.dei.project.model.device.program.ProgramList;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Dishwasher device tests class.
 */

class DishwasherTest {
    // Common testing artifacts for this class.

    private Dishwasher validDishwasher;

    @BeforeEach
    void arrangeArtifacts() {
        validDishwasher = new Dishwasher(new DishwasherSpec());
        validDishwasher.setAttributeValue("capacity", 12D);
        validDishwasher.setName("DishwasherOne");
        validDishwasher.setNominalPower(12.0);
    }

    @Test
    void seeIfGetTypeWorks() {
        //Arrange

        String expectedResult = "Dishwasher";

        // Act

        String actualResult = validDishwasher.getType();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSetAttributeValueWorksNull() {
        // Act

        boolean result = validDishwasher.setAttributeValue(null, 12D);

        // Assert

        assertFalse(result);
    }

    @Test
    void seeIfEqualsWorksSameObject() {
        // Act

        boolean actualResult = validDishwasher.equals(validDishwasher); // Needed for Sonarqube testing purposes.

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeEqualsWorksFalse() {
        // Arrange

        Dishwasher testDishWasher = new Dishwasher(new DishwasherSpec());
        testDishWasher.setName("WashingMachineTwo");
        testDishWasher.setNominalPower(12.0);
        testDishWasher.setAttributeValue(WashingMachineSpec.WM_CAPACITY, 34);
        testDishWasher.setAttributeValue(WashingMachineSpec.WM_CAPACITY, 45);

        // Act

        boolean actualResult = testDishWasher.equals(validDishwasher);

        // Assert

        assertFalse(actualResult);
    }


    @Test
    void seeIfEqualsWorksNotAnInstance() {
        // Act

        boolean actualResult = validDishwasher.equals(new RoomList()); // Needed for sonarqube testing purposes.

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksNull() {
        // Act

        boolean actualResult = validDishwasher.equals(null); // Needed for sonarqube testing purposes.

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfBuildStringWorks() {
        // Arrange

        String expectedResult = "The device Name is DishwasherOne, and its NominalPower is 12.0 kW.\n";

        // Act

        String actualResult = validDishwasher.buildString();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSetNameWorks() {
        // Arrange

        String expectedResult = "One";
        validDishwasher.setName("One");

        // Act

        String actualResult = validDishwasher.getName();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetNominalPowerWorks() {
        // Arrange

        double expectedResult = 12;

        // Act

        double actualResult = validDishwasher.getNominalPower();

        // Assert

        assertEquals(expectedResult, actualResult);
    }


    @Test
    void seeIfGetAndSetAttributeValueWork() {
        // Arrange

        Double expectedResult = 33.3;
        validDishwasher.setAttributeValue(DishwasherSpec.DW_CAPACITY, 33.3);

        // Act

        Object actualResult = validDishwasher.getAttributeValue(DishwasherSpec.DW_CAPACITY);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetAttributeUnitWorks() {
        // Arrange

        String expectedResult = "Kg";

        // Act

        Object result = validDishwasher.getAttributeUnit(DishwasherSpec.DW_CAPACITY);

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAttributeNames() {
        // Act

        List<String> result = validDishwasher.getAttributeNames();

        // Assert

        assertTrue(result.contains(DishwasherSpec.DW_CAPACITY));
        assertEquals(result.size(), 1);
    }

    @Test
    void seeIfDeactivateWorks() {
        // Act

        boolean actualResult = validDishwasher.deactivate();

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfDeactivateWorksAlreadyDeactivated() {
        // Arrange

        validDishwasher.deactivate();

        // Act

        boolean actualResult = validDishwasher.deactivate();

        // Assert

        assertFalse(actualResult);
    }


    @Test
    void seeIfGetEnergyConsumptionWorks() {
        // Arrange

        double expectedResult = 24;

        // Act

        double actualResult = validDishwasher.getEnergyConsumption(2);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetProgramListWorks() {
        // Arrange

        FixedTimeProgram testProgram = new FixedTimeProgram("programa", 2, 2);
        ProgramList testList = new ProgramList();
        testList.add(testProgram);
        ProgramList expectedResult = new ProgramList();
        expectedResult.add(testProgram);
        validDishwasher.setProgramList(testList);

        // Act

        ProgramList actualResult = validDishwasher.getProgramList();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetLogListWorks() {
        // Arrange

        Log testLog = new Log(1, new GregorianCalendar(2019, Calendar.FEBRUARY, 1).getTime(),
                new GregorianCalendar(2019, Calendar.FEBRUARY, 1).getTime());
        LogList expectedResult = new LogList();
        expectedResult.addLog(testLog);
        validDishwasher.addLog(testLog);

        // Act

        LogList actualResult = validDishwasher.getLogList();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetLogListWorksEmptyList() {
        // Arrange

        LogList expectedResult = new LogList();

        // Act

        LogList actualResult = validDishwasher.getLogList();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfAddLogWorksDuplicate() {
        // Arrange

        Log testLog = new Log(1, new GregorianCalendar(2019, Calendar.FEBRUARY, 1).getTime(),
                new GregorianCalendar(2019, Calendar.FEBRUARY, 1).getTime());
        validDishwasher.addLog(testLog);

        // Act

        boolean actualResult = validDishwasher.addLog(testLog);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfAddLogWorksDeactivated() {
        // Arrange

        Log testLog = new Log(1, new GregorianCalendar(2019, Calendar.FEBRUARY, 1).getTime(),
                new GregorianCalendar(2019, Calendar.FEBRUARY, 1).getTime());
        validDishwasher.deactivate();

        // Act

        boolean result = validDishwasher.addLog(testLog);

        // Assert

        assertFalse(result);
    }

    @Test
    void seeIfAddLogWorks() {
        // Arrange

        Log testLog = new Log(1, new GregorianCalendar(2019, Calendar.FEBRUARY, 1).getTime(),
                new GregorianCalendar(2019, Calendar.FEBRUARY, 1).getTime());

        // Act

        boolean result = validDishwasher.addLog(testLog);

        // Assert

        assertTrue(result);
    }

    @Test
    void seeIfGetConsumptionInIntervalWorks() {
        // Arrange

        // Time interval.

        Date initialTime = new GregorianCalendar(2018, Calendar.NOVEMBER, 20, 10, 0).getTime();
        Date finalTime = new GregorianCalendar(2018, Calendar.NOVEMBER, 20, 11, 0).getTime();

        // Dates for the logs.

        Date periodBeginning1 = new GregorianCalendar(2018, Calendar.NOVEMBER, 20, 10, 0).getTime();
        Date periodEnding1 = new GregorianCalendar(2018, Calendar.NOVEMBER, 20, 10, 20).getTime();
        Date periodBeginning2 = new GregorianCalendar(2018, Calendar.NOVEMBER, 20, 10, 30).getTime();
        Date periodEnding2 = new GregorianCalendar(2018, Calendar.NOVEMBER, 20, 11, 0).getTime();

        // Assigning the logs to the device.

        Log log1 = new Log(56, periodBeginning1, periodEnding1);
        Log log2 = new Log(55, periodBeginning2, periodEnding2);
        validDishwasher.addLog(log1);
        validDishwasher.addLog(log2);

        // Act

        double result = validDishwasher.getConsumptionInInterval(initialTime, finalTime);

        // Assert

        assertEquals(111, result);
    }

    @Test
    void seeIfGetConsumptionInIntervalWorksOutOfBounds() {
        // Arrange

        // Time interval.

        Date initialTime = new GregorianCalendar(2018, Calendar.NOVEMBER, 20, 10, 0).getTime();
        Date finalTime = new GregorianCalendar(2018, Calendar.NOVEMBER, 20, 11, 0).getTime();

        // Dates for the logs.

        Date periodBeginning1 = new GregorianCalendar(2018, Calendar.NOVEMBER, 20, 9, 0).getTime();
        Date periodEnding1 = new GregorianCalendar(2018, Calendar.NOVEMBER, 20, 10, 20).getTime();
        Date periodBeginning2 = new GregorianCalendar(2018, Calendar.NOVEMBER, 20, 10, 30).getTime();
        Date periodEnding2 = new GregorianCalendar(2018, Calendar.NOVEMBER, 20, 11, 20).getTime();

        // Assigning the logs to device.

        Log log1 = new Log(56, periodBeginning1, periodEnding1);
        Log log2 = new Log(55, periodBeginning2, periodEnding2);
        validDishwasher.addLog(log1);
        validDishwasher.addLog(log2);

        // Act

        double result = validDishwasher.getConsumptionInInterval(initialTime, finalTime);

        // Assert

        assertEquals(0.0, result);
    }

    @Test
    void seeIfCountAndGetLogsInIntervalWork() {
        // Arrange

        Integer expectedResult = 2;
        LogList expectedResult2 = new LogList();

        // Interval.

        Date initialTime = new GregorianCalendar(2018, Calendar.NOVEMBER, 20, 10, 0).getTime();
        Date finalTime = new GregorianCalendar(2018, Calendar.NOVEMBER, 20, 11, 0).getTime();

        // Dates for the logs.

        Date periodBeginning1 = new GregorianCalendar(2018, Calendar.NOVEMBER, 20, 10, 0).getTime();
        Date periodEnding1 = new GregorianCalendar(2018, Calendar.NOVEMBER, 20, 10, 20).getTime();
        Date periodBeginning2 = new GregorianCalendar(2018, Calendar.NOVEMBER, 20, 10, 20).getTime();
        Date periodEnding2 = new GregorianCalendar(2018, Calendar.DECEMBER, 10, 10, 40).getTime();
        Date periodBeginning3 = new GregorianCalendar(2018, Calendar.NOVEMBER, 20, 10, 40).getTime();
        Date periodEnding3 = new GregorianCalendar(2018, Calendar.NOVEMBER, 20, 11, 0).getTime();

        // Assigning the logs to the device.

        Log log1 = new Log(56, periodBeginning1, periodEnding1);
        Log log2 = new Log(55, periodBeginning2, periodEnding2);
        Log log3 = new Log(55, periodBeginning3, periodEnding3);
        validDishwasher.addLog(log1);
        validDishwasher.addLog(log2);
        validDishwasher.addLog(log3);
        expectedResult2.addLog(log1);
        expectedResult2.addLog(log3);

        // Act

        Integer actualResult = validDishwasher.countLogsInInterval(initialTime, finalTime);
        LogList actualResult2 = validDishwasher.getLogsInInterval(initialTime, finalTime);

        // Assert

        assertEquals(expectedResult, actualResult);
        assertEquals(expectedResult2, actualResult2);
    }

    @Test
    void seeIfGetLogsInIntervalWorksOutOfBounds() {
        // Arrange

        LogList expectedResult = new LogList();

        // Interval.

        Date initialTime = new GregorianCalendar(2018, Calendar.NOVEMBER, 20, 10, 0).getTime();
        Date finalTime = new GregorianCalendar(2018, Calendar.NOVEMBER, 20, 11, 0).getTime();

        // Dates for the logs.

        Date periodBeginning1 = new GregorianCalendar(2018, Calendar.NOVEMBER, 20, 9, 50).getTime();
        Date periodEnding1 = new GregorianCalendar(2018, Calendar.NOVEMBER, 20, 10, 10).getTime();
        Date periodBeginning2 = new GregorianCalendar(2018, Calendar.NOVEMBER, 20, 10, 50).getTime();
        Date periodEnding2 = new GregorianCalendar(2018, Calendar.NOVEMBER, 20, 11, 10).getTime();

        // Assigning dates to the device.

        Log log1 = new Log(56, periodBeginning1, periodEnding1);
        Log log2 = new Log(55, periodBeginning2, periodEnding2);
        validDishwasher.addLog(log1);
        validDishwasher.addLog(log2);

        // Act

        LogList actualResult = validDishwasher.getLogsInInterval(initialTime, finalTime);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetConsumptionWorks() {
        // Arrange

        double expectedResult = 288;

        // Act

        double result = validDishwasher.getEnergyConsumption(24);

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetConsumptionWorksZero() {
        // Arrange

        double expectedResult = 0;

        // Act

        double result = validDishwasher.getEnergyConsumption(0);

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfIsLogListEmptyWorks() {
        //Act

        boolean actualResult1 = validDishwasher.isLogListEmpty();

        //Assert

        Assertions.assertTrue(actualResult1);

        //Arrange To Add Log

        Log testLog = new Log(20, new Date(), new Date());
        validDishwasher.addLog(testLog);

        //Act

        boolean actualResult2 = validDishwasher.isLogListEmpty();

        //Assert

        Assertions.assertFalse(actualResult2);
    }

    @Test
    void hashCodeDummyTest() {
        // Arrange

        int expectedResult = 1;

        // Act

        int actualResult = validDishwasher.hashCode();

        // Assert

        assertEquals(expectedResult, actualResult);
    }


}
