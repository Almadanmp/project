package pt.ipp.isep.dei.project.model.device;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Room;
import pt.ipp.isep.dei.project.model.RoomList;
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
 * WashingMachine device tests class.
 */

class WashingMachineTest {
    // Common testing artifacts for this class.

    private WashingMachine validMachine;
    private Log validLog;

    @BeforeEach
    void arrangeArtifacts() {
        validMachine = new WashingMachine(new WashingMachineSpec());
        validMachine.setName("WashingMachine");
        validMachine.setNominalPower(12.0);
        validMachine.setAttributeValue(WashingMachineSpec.WM_CAPACITY, 34);
        validLog = new Log(1, new GregorianCalendar(2019, Calendar.FEBRUARY, 1).getTime(),
                new GregorianCalendar(2019, Calendar.FEBRUARY, 1).getTime());
        validMachine.addLog(validLog);
    }

    @Test
    void seeIfGetDeviceTypeWorks() {
        // Arrange

        String expectedResult = "Washing Machine";

        // Act

        String result = validMachine.getType();

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfSetAttributeValueWorksNull() {
        // Act

        boolean result = validMachine.setAttributeValue(null, 12D);

        // Assert

        assertFalse(result);
    }

    @Test
    void seeIfEqualsWorksOnItself() {
        assertEquals(validMachine, validMachine);
    }

    @Test
    void seeEqualsWorksFalse() {
        // Arrange

        Device testMachine = new WashingMachine(new WashingMachineSpec());
        testMachine.setName("WMTwo");
        testMachine.setNominalPower(12.0);

        // Act

        boolean actualResult = validMachine.equals(testMachine);

        // Assert

        assertFalse(actualResult);
    }


    @Test
    void seeIfEqualsWorksNotAnInstance() {
        assertNotEquals(validMachine, new RoomList());
    }

    @Test
    void seeIfEqualsWorksNull() {
        assertNotEquals(validMachine, null);
    }

    @Test
    void seeIfBuildStringWorks() {
        // Arrange

        String expectedResult = "The device Name is WashingMachine, and its NominalPower is 12.0 kW.\n";

        // Act

        String result = validMachine.buildString();

        // Assert


        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfSetNameWorks() {
        // Arrange

        String expectedResult = "WashingMachine 3000";

        // Act

        validMachine.setName("WashingMachine 3000");
        String result = validMachine.getName();

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfSetGetNominalPowerWork() {
        // Arrange

        double expectedResult = 150;

        // Act

        validMachine.setNominalPower(150);
        double result = validMachine.getNominalPower();

        // Assert

        assertEquals(expectedResult, result);
    }


    @Test
    void seeIfGetSetAttributeValueWork() {
        // Arrange

        Double expectedResult = 33.3;

        // Act

        validMachine.setAttributeValue(WashingMachineSpec.WM_CAPACITY, 33.3);
        Object result = validMachine.getAttributeValue(WashingMachineSpec.WM_CAPACITY);

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAttributeUnitWorks() {
        // Arrange

        String expectedResult = "Kg";

        // Act

        Object result = validMachine.getAttributeUnit(WashingMachineSpec.WM_CAPACITY);

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAttributeNamesWorks() {
        // Act

        List<String> result = validMachine.getAttributeNames();

        // Assert

        assertTrue(result.contains(WashingMachineSpec.WM_CAPACITY));
        assertEquals(result.size(), 1);
    }

    @Test
    void seeIfDeactivateWorks() {
        // Act

        boolean actualResult = validMachine.deactivate();

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfDeactivateWorksAlreadyDeactivated() {
        // Arrange

        validMachine.deactivate();

        // Act

        boolean actualResult = validMachine.deactivate();


        assertFalse(actualResult);
    }

    @Test
    void seeIfGetEnergyConsumptionWorks() {
        // Arrange

        double expectedResult = 24;

        // Act

        double actualResult = validMachine.getEnergyConsumption(2);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetProgramListWorks() {
        // Arrange

        FixedTimeProgram testProgram = new FixedTimeProgram("programa", 2, 2);
        ProgramList expectedResult = new ProgramList();
        expectedResult.add(testProgram);
        ProgramList actualList = new ProgramList();
        actualList.add(testProgram);
        validMachine.setProgramList(actualList);

        // Act

        ProgramList result = validMachine.getProgramList();

        // Assert

        assertEquals(expectedResult, result);
    }


    @Test
    void seeIfGetLogListWorks() {
        // Arrange

        LogList expectedResult = new LogList();
        expectedResult.addLog(validLog);

        // Act

        LogList result = validMachine.getLogList();

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetLogListWorksEmpty() {
        // Arrange

        validMachine = new WashingMachine(new WashingMachineSpec());
        LogList expectedResult = new LogList();

        // Act

        LogList result = validMachine.getLogList();

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfAddLogWorks() {
        // Arrange

        validMachine = new WashingMachine(new WashingMachineSpec());

        // Act

        boolean result = validMachine.addLog(validLog);

        // Assert

       assertTrue(result);
    }

    @Test
    void seeIfAddLogWorksInactive() {
        // Arrange

        validMachine = new WashingMachine(new WashingMachineSpec());
        validMachine.deactivate();

        // Act

        boolean result = validMachine.addLog(validLog);

        // Assert

        assertFalse(result);
    }

    @Test
    void seeIfAddLogWorksDuplicate() {
        // Act

        boolean result = validMachine.addLog(validLog);

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

        double actualResult = validMachine.getConsumptionInInterval(initialTime, finalTime);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetConsumptionInIntervalWorksOutOfBounds() {
        // Arrange

        validMachine = new WashingMachine(new WashingMachineSpec());
        double expectedResult = 0.0;

        // Interval

        Date initialTime = new GregorianCalendar(2018, Calendar.SEPTEMBER, 20, 10, 0,
                0).getTime();
        Date finalTime = new GregorianCalendar(2018, Calendar.SEPTEMBER, 20, 11, 0,
                0).getTime();

        // Logs

        Log firstLog = new Log(21, new GregorianCalendar(2018, Calendar.SEPTEMBER, 19).getTime(),
                new GregorianCalendar(2018, Calendar.SEPTEMBER, 21).getTime());
        validMachine.addLog(firstLog);

        // Act

        double actualResult = validMachine.getConsumptionInInterval(initialTime, finalTime);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfCountLogsInIntervalWorks() {
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

        Integer actualResult = validMachine.countLogsInInterval(initialTime, finalTime);
        LogList actualResultList = validMachine.getLogList();

        // Assert

        assertEquals(expectedResult, actualResult);
        assertEquals(expectedResultList, actualResultList);
    }

    @Test
    void seeIfGetLogsInIntervalWorksOutOfBounds() {
        // Arrange

        validMachine = new WashingMachine(new WashingMachineSpec());
        LogList expectedResult = new LogList();

        // Interval

        Date initialTime = new GregorianCalendar(2018, Calendar.SEPTEMBER, 20, 10, 0,
                0).getTime();
        Date finalTime = new GregorianCalendar(2018, Calendar.SEPTEMBER, 20, 11, 0,
                0).getTime();

        // Logs

        Log firstLog = new Log(21, new GregorianCalendar(2018, Calendar.SEPTEMBER, 19).getTime(),
                new GregorianCalendar(2018, Calendar.SEPTEMBER, 21).getTime());
        validMachine.addLog(firstLog);

        // Act

        LogList actualResult = validMachine.getLogsInInterval(initialTime, finalTime);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetConsumptionWorks() {
        // Arrange

        validMachine.setNominalPower(15);
        double expectedResult = 360;

        // Act

        double result = validMachine.getEnergyConsumption(24);

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetConsumptionWorksTimeZero() {
        // Arrange

        validMachine.setNominalPower(15);
        double expectedResult = 0;

        // Act

        double result = validMachine.getEnergyConsumption(0);

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfIsLogListEmptyWorks() {
        //Act

        boolean actualResult1 = validMachine.isLogListEmpty();

        //Assert

        assertFalse(actualResult1);

        //Arrange To Remove Log

        validMachine = new WashingMachine(new WashingMachineSpec());

        //Act

        boolean actualResult2 = validMachine.isLogListEmpty();

        //Assert

        assertTrue(actualResult2);
    }

    @Test
    void hashCodeDummyTest() {
        // Arrange

        int expectedResult = 1;

        // Act

        int actualResult = validMachine.hashCode();

        // Assert

        assertEquals(expectedResult, actualResult);
    }
}
