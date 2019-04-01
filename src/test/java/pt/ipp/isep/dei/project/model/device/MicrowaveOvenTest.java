package pt.ipp.isep.dei.project.model.device;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testng.Assert;
import pt.ipp.isep.dei.project.model.sensor.TypeSensor;
import pt.ipp.isep.dei.project.model.device.devicespecs.MicrowaveOvenSpec;
import pt.ipp.isep.dei.project.model.device.log.Log;
import pt.ipp.isep.dei.project.model.device.log.LogList;
import pt.ipp.isep.dei.project.model.device.program.ProgramList;
import pt.ipp.isep.dei.project.model.device.program.VariableTimeProgram;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Microwave Oven tests class.
 */

class MicrowaveOvenTest {

    // Common artifacts for testing in this class.

    private String validName = "KUNFT KMW"; // Sensor name "KUNFT KMW"
    private MicrowaveOvenSpec validSpec; // Microwave Oven Spec
    private VariableTimeProgram validVariableTimeProgram; // A variable time program named "Medium Power" running at 1200 Watt.
    private VariableTimeProgram validVariableTimeProgram2; // A variable time program named "Max Power" running at 1200 Watt.
    private MicrowaveOven validMicrowaveOven; // A generic Microwave Oven.
    private ProgramList validEmptyProgramList; // A program list containing no programs.
    private LogList validEmptyLogList; // a log list containing no logs.
    private Log validLog; // A log of a reading made of the device with a value of 200, read between the validDate1 and validDate2.
    private Log validLog2; // A log of a reading made of the device with a value of 2000, read between the validDate3 and validDate2.
    private Date validDate1; // Date 20/10/2018 09:02:00
    private Date validDate2; // Date 20/10/2018 10:02:00
    private Date validDate3; // Date 20/10/2017 09:02:00
    private Date validDate4; // Date 20/10/2019 10:02:00

    @BeforeEach
    void arrangeArtifacts() {
        validSpec = new MicrowaveOvenSpec();
        validVariableTimeProgram = new VariableTimeProgram("Medium Power", 1.2);
        validVariableTimeProgram2 = new VariableTimeProgram("Mex Power", 1.8);
        validMicrowaveOven = new MicrowaveOven(validSpec);
        validEmptyProgramList = new ProgramList();
        validEmptyLogList = new LogList();

        // SimpleDateFormat dd/MM/yyyy HH:mm:ss

        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            validDate1 = validSdf.parse("20/10/2018 09:02:00");
            validDate2 = validSdf.parse("20/10/2018 10:02:00");
            validDate3 = validSdf.parse("20/10/2017 09:02:00");
            validDate4 = validSdf.parse("20/10/2018 10:02:00");
        } catch (ParseException c) {
            c.printStackTrace();
        }

        validLog = new Log(200, validDate1, validDate2);
        validLog2 = new Log(2000, validDate3, validDate2);
    }

    @Test
    void seeIfGetNameWorks() {
        // Arrange

        validMicrowaveOven.setName(validName);
        String expectedResult = "KUNFT KMW";

        // Act

        String result = validMicrowaveOven.getName();

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetNameWorksEmptyName() {
        // Arrange

        validMicrowaveOven.setName(validName);
        String expectedResult = "";

        // Act
        String result = validMicrowaveOven.getName();

        // Assert

        assertNotEquals(expectedResult, result);
    }

    @Test
    void seeIfGetTypeWorks() {
        // Arrange

        String expectedResult = "Microwave Oven";

        // Act

        String result = validMicrowaveOven.getType();

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetNominalPowerWorks() {
        // Arrange

        validMicrowaveOven.setNominalPower(1.2);
        double expectedResult = 1.2;

        // Act

        double result = validMicrowaveOven.getNominalPower();

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetNominalPowerWorksWrongValue() {
        // Arrange

        validMicrowaveOven.setNominalPower(1.2);
        Double expectedResult = 2.0;

        // Act

        Double result = validMicrowaveOven.getNominalPower();

        // Assert

        assertNotEquals(expectedResult, result);
    }

    @Test
    void seeIfGetNominalPowerWorksWrongType() {
        // Arrange

        validMicrowaveOven.setNominalPower(1.2);
        int expectedResult = 1;

        // Act

        Double result = validMicrowaveOven.getNominalPower();

        // Assert

        assertNotEquals(expectedResult, result);
    }

    @Test
    void seeIfIsActiveWorks() {
        // Act

        boolean result = validMicrowaveOven.isActive();

        // Assert

        assertTrue(result);
    }

    @Test
    void seeIfDeactivateWorks() {
        // Arrange

        validMicrowaveOven.deactivate();

        // Act

        boolean result = validMicrowaveOven.isActive();

        // Assert

        assertFalse(result);
    }

    @Test
    void seeIfDeactivateWorksTrue() {
        // Act

        boolean result = validMicrowaveOven.deactivate();

        // Assert

        assertTrue(result);
    }

    @Test
    void seeIfDeactivateWorksAlreadyDeactivated() {
        // Arrange

        validMicrowaveOven.deactivate();

        // Act

        boolean result = validMicrowaveOven.deactivate();

        // Assert

        assertFalse(result);
    }

    @Test
    void seeIfSetGetProgramListWork() {
        // Arrange

        validEmptyProgramList.add(validVariableTimeProgram);
        validMicrowaveOven.setProgramList(validEmptyProgramList);
        ProgramList expectedResult = new ProgramList();
        expectedResult.add(validVariableTimeProgram);

        // Act

        ProgramList result = validMicrowaveOven.getProgramList();

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetSetProgramListWorksDifferentLists() {
        // Arrange

        validEmptyProgramList.add(validVariableTimeProgram);
        validMicrowaveOven.setProgramList(validEmptyProgramList);
        ProgramList expectedResult = new ProgramList();

        // Act

        ProgramList result = validMicrowaveOven.getProgramList();

        // Assert

        assertNotEquals(expectedResult, result);
    }

    @Test
    void seeIfStringBuilderWorks() {
        // Arrange

        validMicrowaveOven.setName("Panasonic MX");
        validMicrowaveOven.setNominalPower(1.8);
        String expectedResult = "The device Name is " + "Panasonic MX" + ", and its NominalPower is " + 1.8 + " kW.\n";

        // Act

        String result = validMicrowaveOven.buildString();

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfStringBuilderFails() {
        // Arrange

        validMicrowaveOven.setName("Panasonic MX");
        validMicrowaveOven.setNominalPower(1.8);
        String expectedResult = "The device Name is " + "Panasonic" + ", and its NominalPower is " + 1.6 + " kW.\n";

        // Act
        String result = validMicrowaveOven.buildString();

        // Assert

        assertNotEquals(expectedResult, result);
    }

    @Test
    void seeIfIsLogListEmptyWorks() {
        // Act

        boolean result = validMicrowaveOven.isLogListEmpty();

        // Assert

        assertTrue(result);
    }

    @Test
    void seeIfIsLogListEmptyWorksFalse() {
        // Arrange

        validMicrowaveOven.addLog(validLog);

        // Act
        boolean result = validMicrowaveOven.isLogListEmpty();

        // Assert

        assertFalse(result);
    }

    @Test
    void seeIfLogCounterWorksWithOneLog() {
        // Arrange

        validMicrowaveOven.addLog(validLog);
        int expectedResult = 1;

        // Act
        int result = validMicrowaveOven.countLogsInInterval(validDate3, validDate4);

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfLogCounterWorksWithNoLogs() {
        // Arrange

        int expectedResult = 0;

        // Act
        int result = validMicrowaveOven.countLogsInInterval(validDate3, validDate4);

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetLogsInIntervalWorksWithOneLog() {
        // Arrange

        validMicrowaveOven.addLog(validLog);
        validEmptyLogList.addLog(validLog);
        LogList expectedResult = validEmptyLogList;

        // Act

        LogList result = validMicrowaveOven.getLogsInInterval(validDate3, validDate4);

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetLogsInIntervalWorksWithNoLogs() {
        // Arrange

        LogList expectedResult = validEmptyLogList;

        // Act
        LogList result = validMicrowaveOven.getLogsInInterval(validDate3, validDate4);

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetLogListWorks() {
        // Arrange

        validMicrowaveOven.addLog(validLog);
        validEmptyLogList.addLog(validLog);
        LogList expectedResult = validEmptyLogList;

        // Act

        LogList result = validMicrowaveOven.getLogList();

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetLogListFailsDifferentListSize() {
        // Arrange

        validMicrowaveOven.addLog(validLog);
        validEmptyLogList.addLog(validLog);
        validEmptyLogList.addLog(validLog2);
        LogList expectedResult = validEmptyLogList;

        // Act

        LogList result = validMicrowaveOven.getLogList();

        // Assert

        assertNotEquals(expectedResult, result);
    }


    @Test
    void seeIfGetLogListFailsEmpty() {
        // Arrange

        validMicrowaveOven.addLog(validLog);
        LogList expectedResult = validEmptyLogList;

        // Act

        LogList result = validMicrowaveOven.getLogList();

        // Assert

        assertNotEquals(expectedResult, result);
    }

    @Test
    void seeIfAddLogWorks() {
        // Act

        boolean result = validMicrowaveOven.addLog(validLog);

        // Assert

        assertTrue(result);
    }

    @Test
    void seeIfAddLogWorksDuplicate() {
        // Arrange

        validMicrowaveOven.addLog(validLog);

        // Act

        boolean result = validMicrowaveOven.addLog(validLog);

        // Assert

        assertFalse(result);
    }

    @Test
    void seeIfAddLogFailsOnDeactivatedDevice() {
        // Arrange

        validMicrowaveOven.deactivate();

        // Act

        boolean result = validMicrowaveOven.addLog(validLog);

        // Assert

        assertFalse(result);
    }

    @Test
    void seeIfGetConsumptionInIntervalWorksOneLog() {
        // Arrange

        validMicrowaveOven.addLog(validLog);
        validEmptyLogList.addLog(validLog);
        double expectedResult = 200;

        // Act

        double result = validMicrowaveOven.getConsumptionInInterval(validDate3, validDate4);

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetConsumptionInIntervalWorksNoLogs() {
        // Arrange

        double expectedResult = 0;

        // Act

        double result = validMicrowaveOven.getConsumptionInInterval(validDate3, validDate4);

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetEnergyConsumptionWorks() {
        // Arrange

        validMicrowaveOven.setNominalPower(1.8);
        double expectedResult = 36;

        // Act

        double result = validMicrowaveOven.getEnergyConsumption(20);

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetEnergyConsumptionWorksNoNominalPower() {
        // Arrange

        double expectedResult = 36;

        // Act

        double result = validMicrowaveOven.getEnergyConsumption(20);

        // Assert

        assertNotEquals(expectedResult, result);
    }

    @Test
    void seeIfGetEnergyConsumptionWorksTimeZero() {
        // Arrange

        validMicrowaveOven.setNominalPower(1.8);
        double expectedResult = 0;

        // Act

        double result = validMicrowaveOven.getEnergyConsumption(0);

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetProgramConsumptionWorks() {
        // Arrange

        double expectedResult = 24;

        // Act

        double result = validMicrowaveOven.getProgramConsumption(20, validVariableTimeProgram);

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetProgramConsumptionWorksDifferentProgram() {
        // Arrange

        double expectedResult = 36;

        // Act

        double result = validMicrowaveOven.getProgramConsumption(20, validVariableTimeProgram2);

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeGetAttributeNamesWorks() {
        // Arrange

        List<String> expectedResult = new ArrayList<>();

        // Act
        List<String> result = validMicrowaveOven.getAttributeNames();

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeAttributeMethods() {
        // Arrange

        Object expectedResult1 = 0;
        List<String> expectedResult2 = new ArrayList<>();

        // Act

        Object actualResult1 = validMicrowaveOven.getAttributeValue("Non-existent");
        List<String> actualResult2 = validMicrowaveOven.getAttributeNames();
        Object actualResult3 = validMicrowaveOven.getAttributeUnit("Non-existent");
        boolean actualResult4 = validMicrowaveOven.setAttributeValue("Non-existent", 20D);

        // Assert

        Assert.assertEquals(expectedResult1, actualResult1);
        Assert.assertEquals(expectedResult2, actualResult2);
        Assert.assertEquals(false, actualResult3);
        Assert.assertFalse(actualResult4);
    }

    @Test
    void seeIfGetAttributeValueWorksDifferentObject() {
        // Arrange

        int expectedResult = 0;
        validMicrowaveOven.setAttributeValue("Weight", "Two");

        // Act

        Object result = validMicrowaveOven.getAttributeValue("Weight");

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAttributeUnitWorks() {
        // Arrange

        validMicrowaveOven.setAttributeValue("Kilograms", "Kg");

        // Act

        Object result = validMicrowaveOven.getAttributeUnit("Kilograms");

        // Assert

        assertEquals(false, result);
    }

    @Test
    void seeIfEqualsWorksOnItself() {
        // Assert

        assertEquals(validMicrowaveOven, validMicrowaveOven);
    }

    @Test
    void seeIfEqualsFailsDifferentObject() {
        // Assert

        assertNotEquals(validMicrowaveOven, new TypeSensor("Rain", "mm"));
    }

    @Test
    void seeIfEqualsFailsNullObject() {
        // Assert

        assertNotEquals(validMicrowaveOven, null);
        assertNotEquals(validMicrowaveOven, null);
    }

    @Test
    void seeIfEqualsWorksDeviceObject() {
        // Arrange

        Device microwave = new MicrowaveOven(validSpec);
        microwave.setName(validName);
        validMicrowaveOven.setName(validName);

        // Assert

        assertEquals(microwave, validMicrowaveOven);
    }

    @Test
    void seeIfEqualsFailsDeviceObject() {
        // Arrange

        Device microwave = new MicrowaveOven(validSpec);
        microwave.setName(validName);

        // Assert

        assertNotEquals(microwave, validMicrowaveOven);
    }

    @Test
    void hashCodeDummyTest() {
        // Arrange

        int expectedResult = 1;

        // Act

        int actualResult = validMicrowaveOven.hashCode();

        // Assert

        assertEquals(expectedResult, actualResult);
    }
}