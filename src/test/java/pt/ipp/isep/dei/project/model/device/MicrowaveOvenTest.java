package pt.ipp.isep.dei.project.model.device;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.TypeSensor;
import pt.ipp.isep.dei.project.model.device.devicespecs.MicrowaveOvenSpec;
import pt.ipp.isep.dei.project.model.device.log.Log;
import pt.ipp.isep.dei.project.model.device.log.LogList;
import pt.ipp.isep.dei.project.model.device.program.Program;
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
    private String validName1 = "KUNFT KMW"; // Sensor name "KUNFT KMW"
    private MicrowaveOvenSpec validSpec; // Microwave Oven Spec
    private VariableTimeProgram validVariableTimeProgram; // A variable time program named "Medium Power" running at 1200 Watt.
    private VariableTimeProgram validVariableTimeProgram2; // A variable time program named "Max Power" running at 1200 Watt.
    private MicrowaveOven validMicrowaveOven; // A generic Microwave Oven.
    private ProgramList validProgramList; // A program list containing no programs.
    private LogList validLogList; // a log list containing no logs.
    private Log validLog; // A log of a reading made of the device with a value of 200, read between the validDate1 and validDate2.
    private Log validLog2; // A log of a reading made of the device with a value of 2000, read between the validDate3 and validDate2.
    private SimpleDateFormat validSdf; // SimpleDateFormat dd/MM/yyyy HH:mm:ss
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
        validProgramList = new ProgramList();
        validLogList = new LogList();
        validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
        validDate1 = validSdf.parse("20/10/2018 09:02:00");
        validDate2 = validSdf.parse("20/10/2018 10:02:00");
        validDate3 = validSdf.parse("20/10/2017 09:02:00");
        validDate4 = validSdf.parse("20/10/2018 10:02:00");
        } catch (ParseException c) {
            c.printStackTrace();
        }
        validLog = new Log(200,validDate1,validDate2);
        validLog2 = new Log(2000,validDate3,validDate2);
    }

    @Test
    void seeIfNameGetterWorks() {
        //Arrange
        validMicrowaveOven.setName(validName1);
        String expectedResult = "KUNFT KMW";

        // Act
        String result = validMicrowaveOven.getName();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfNameGetterFails() {
        //Arrange
        validMicrowaveOven.setName(validName1);
        String expectedResult = "KUNFT KMX";

        // Act
        String result = validMicrowaveOven.getName();

        //Assert
        assertNotEquals(expectedResult, result);
    }

    @Test
    void seeIfNameGetterFailsEmptyName() {
        //Arrange
        validMicrowaveOven.setName(validName1);
        String expectedResult = "";

        // Act
        String result = validMicrowaveOven.getName();

        //Assert
        assertNotEquals(expectedResult, result);
    }

    @Test
    void seeIfTypeGetterWorks() {
        //Arrange
        String expectedResult = "Microwave Oven";

        // Act
        String result = validMicrowaveOven.getType();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfTypeGetterFailsEmptyString() {
        //Arrange
        String expectedResult = "";

        // Act
        String result = validMicrowaveOven.getType();

        //Assert
        assertNotEquals(expectedResult, result);
    }

    @Test
    void seeIfTypeGetterFailsWrongName() {
        //Arrange
        String expectedResult = "Microwave Oven 2";

        // Act
        String result = validMicrowaveOven.getType();

        //Assert
        assertNotEquals(expectedResult, result);
    }

    @Test
    void seeIfNominalPowerGetterWorks() {
        //Arrange
        validMicrowaveOven.setNominalPower(1.2);
        Double expectedResult = 1.2;

        // Act
        Double result = validMicrowaveOven.getNominalPower();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfNominalPowerGetterFailsWrongValue() {
        //Arrange
        validMicrowaveOven.setNominalPower(1.2);
        Double expectedResult = 2.0;

        // Act
        Double result = validMicrowaveOven.getNominalPower();

        //Assert
        assertNotEquals(expectedResult, result);
    }

    @Test
    void seeIfNominalPowerGetterFailsWrongType() {
        //Arrange
        validMicrowaveOven.setNominalPower(1.2);
        int expectedResult = 1;

        // Act
        Double result = validMicrowaveOven.getNominalPower();

        //Assert
        assertNotEquals(expectedResult, result);
    }

    @Test
    void seeIfActiveConditionIsVerified() {
        //Arrange
        boolean expectedResult = true;

        // Act
        boolean result = validMicrowaveOven.isActive();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfActiveConditionChanges() {
        //Arrange
        boolean expectedResult = false;
        validMicrowaveOven.deactivate();

        // Act
        boolean result = validMicrowaveOven.isActive();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfDeactivationIsVerified() {
        //Arrange
        boolean expectedResult = true;

        // Act
        boolean result = validMicrowaveOven.deactivate();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfDeactivationFails() {
        //Arrange
        boolean expectedResult = true;
        validMicrowaveOven.deactivate();

        // Act
        boolean result = validMicrowaveOven.deactivate();

        //Assert
        assertNotEquals(expectedResult, result);
    }

    @Test
    void seeIfProgramListIsAdded() {
        //Arrange
        validProgramList.addProgram(validVariableTimeProgram);
        validMicrowaveOven.setProgramList(validProgramList);
        ProgramList expectedResult = validProgramList;

        // Act
        ProgramList result = validMicrowaveOven.getProgramList();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfProgramListFailsWithDifferentLists() {
        //Arrange
        validProgramList.addProgram(validVariableTimeProgram);
        validMicrowaveOven.setProgramList(validProgramList);
        ProgramList expectedResult = new ProgramList();

        // Act
        ProgramList result = validMicrowaveOven.getProgramList();

        //Assert
        assertNotEquals(expectedResult, result);
    }

    @Test
    void seeIfProgramListFails() {
        //Arrange
        validMicrowaveOven.setProgramList(validProgramList);
        Program expectedResult = new VariableTimeProgram("Med", 8);

        // Act
        ProgramList result = validMicrowaveOven.getProgramList();

        //Assert
        assertNotEquals(expectedResult, result);
    }

    @Test
    void seeIfStringBuilderWorks() {
        //Arrange
        validMicrowaveOven.setName("Panasonic MX");
        validMicrowaveOven.setNominalPower(1.8);
        String expectedResult = "The device Name is " + "Panasonic MX" + ", and its NominalPower is " + 1.8 + " kW.\n";

        // Act
        String result = validMicrowaveOven.buildString();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfStringBuilderFails() {
        //Arrange
        validMicrowaveOven.setName("Panasonic MX");
        validMicrowaveOven.setNominalPower(1.8);
        String expectedResult = "The device Name is " + "Panasonic" + ", and its NominalPower is " + 1.6 + " kW.\n";

        // Act
        String result = validMicrowaveOven.buildString();

        //Assert
        assertNotEquals(expectedResult, result);
    }

    @Test
    void seeIfLogListIsEmpty() {
        //Arrange
        boolean expectedResult = true;

        // Act
        boolean result = validMicrowaveOven.isLogListEmpty();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfLogSetterWorks() {
        //Arrange
        validMicrowaveOven.addLog(validLog);

        // Act
        boolean result = validMicrowaveOven.isLogListEmpty();

        //Assert
        assertFalse(result);
    }

    @Test
    void seeIfLogCounterWorksWithOneLog() {
        //Arrange
        validMicrowaveOven.addLog(validLog);
        int expectedResult = 1;

        // Act
        int result = validMicrowaveOven.countLogsInInterval(validDate3,validDate4);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfLogCounterWorksWithNoLogs() {
        //Arrange
        int expectedResult = 0;

        // Act
        int result = validMicrowaveOven.countLogsInInterval(validDate3,validDate4);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfLogGetterInAnIntervalWorksWithOneLog() {
        //Arrange
        validMicrowaveOven.addLog(validLog);
        validLogList.addLog(validLog);
        LogList expectedResult = validLogList;

        // Act
        LogList result = validMicrowaveOven.getLogsInInterval(validDate3,validDate4);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfLogGetterInAnIntervalWorksWithNoLogs() {
        //Arrange
        LogList expectedResult = validLogList;

        // Act
        LogList result = validMicrowaveOven.getLogsInInterval(validDate3,validDate4);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfLogListGetterWorks() {
        //Arrange
        validMicrowaveOven.addLog(validLog);
        validLogList.addLog(validLog);
        LogList expectedResult = validLogList;

        // Act
        LogList result = validMicrowaveOven.getLogList();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfLogListGetterFailsDifferentListSize() {
        //Arrange
        validMicrowaveOven.addLog(validLog);
        validLogList.addLog(validLog);
        validLogList.addLog(validLog2);
        LogList expectedResult = validLogList;

        // Act
        LogList result = validMicrowaveOven.getLogList();

        //Assert
        assertNotEquals(expectedResult, result);
    }


    @Test
    void seeIfLogListGetterFails() {
        //Arrange
        validMicrowaveOven.addLog(validLog);
        LogList expectedResult = validLogList;

        // Act
        LogList result = validMicrowaveOven.getLogList();

        //Assert
        assertNotEquals(expectedResult, result);
    }

    @Test
    void seeIfAddLogWorks() {
        //Arrange
        boolean expectedResult = true;

        // Act
        boolean result = validMicrowaveOven.addLog(validLog);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfAddLogFailsSameLog() {
        //Arrange
        validMicrowaveOven.addLog(validLog);
        boolean expectedResult = false;

        // Act
        boolean result = validMicrowaveOven.addLog(validLog);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfAddLogFailsOnDeactivatedDevice() {
        //Arrange
        validMicrowaveOven.deactivate();
        boolean expectedResult = false;

        // Act
        boolean result = validMicrowaveOven.addLog(validLog);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfRealConsumptionCalculatorInAnIntervalWorksWithOneLog() {
        //Arrange
        validMicrowaveOven.addLog(validLog);
        validLogList.addLog(validLog);
        double expectedResult = 200;

        // Act
        double result = validMicrowaveOven.getConsumptionWithinGivenInterval(validDate3,validDate4);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfRealConsumptionCalculatorInAnIntervalWorksWithNoLogs() {
        //Arrange
        double expectedResult = 0;

        // Act
        double result = validMicrowaveOven.getConsumptionWithinGivenInterval(validDate3,validDate4);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfEnergyConsumptionWorks() {
        //Arrange
        validMicrowaveOven.setNominalPower(1.8);
        double expectedResult = 36;

        // Act
        double result = validMicrowaveOven.getEnergyConsumption(20);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfEnergyConsumptionFailsWithNoNominalPower() {
        //Arrange
        double expectedResult = 36;

        // Act
        double result = validMicrowaveOven.getEnergyConsumption(20);

        //Assert
        assertNotEquals(expectedResult, result);
    }

    @Test
    void seeIfEnergyConsumptionWorksTimeZero() {
        //Arrange
        validMicrowaveOven.setNominalPower(1.8);
        double expectedResult = 0;

        // Act
        double result = validMicrowaveOven.getEnergyConsumption(0);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfProgramEnergyConsumptionWorks() {
        //Arrange
        double expectedResult = 24;

        // Act
        double result = validMicrowaveOven.getProgramConsumption(20,validVariableTimeProgram);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfProgramEnergyConsumptionWorksDifferentProgram() {
        //Arrange
        double expectedResult = 36;

        // Act
        double result = validMicrowaveOven.getProgramConsumption(20,validVariableTimeProgram2);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfAttributeNamesGetterWorks() {
        //Arrange
        List<String> expectedResult = new ArrayList<>();

        // Act
        List<String> result = validMicrowaveOven.getAttributeNames();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfAttributeValuesGetterWorks() {
        //Arrange
        int expectedResult = 0;
        validMicrowaveOven.setAttributeValue("Weight",2);

        // Act
        Object result = validMicrowaveOven.getAttributeValue("Weight");

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfAttributeValuesGetterWorksDifferentObject() {
        //Arrange
        int expectedResult = 0;
        validMicrowaveOven.setAttributeValue("Weight","Two");
        // Act
        Object result = validMicrowaveOven.getAttributeValue("Weight");

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfAttributeUnitsGetterWorks() {
        //Arrange
        boolean expectedResult = false;
        validMicrowaveOven.setAttributeValue("Kilograms","Kg");

        // Act
        Object result = validMicrowaveOven.getAttributeUnit("Kilograms");

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfEqualsWorks() {
        //Arrange
        // Act
        //Assert
        assertTrue(validMicrowaveOven.equals(validMicrowaveOven));
    }

    @Test
    void seeIfEqualsFailsDifferentObject() {
        //Arrange
        // Act
        //Assert
        assertFalse(validMicrowaveOven.equals(new TypeSensor("Rain","mm")));
    }

    @Test
    void seeIfEqualsFailsNullObject() {
        //Arrange
        Object obj = null;
        MicrowaveOven micro = null;

        // Act
        //Assert
        assertFalse(validMicrowaveOven.equals(obj));
        assertFalse(validMicrowaveOven.equals(micro));
    }

    @Test
    void seeIfEqualsWorksDeviceObject() {
        //Arrange
        Device micro = new MicrowaveOven(validSpec);
        micro.setName(validName1);
        validMicrowaveOven.setName(validName1);

        // Act

        //Assert
        assertTrue(micro.equals(validMicrowaveOven));
    }

    @Test
    void seeIfEqualsFailsDeviceObject() {
        //Arrange
        Device micro = new MicrowaveOven(validSpec);
        micro.setName(validName1);

        // Act

        //Assert
        assertFalse(micro.equals(validMicrowaveOven));
    }

    @Test
    public void hashCodeDummyTest() {
        //Arrange
        int expectedResult = 1;

        // Act
        int actualResult = validMicrowaveOven.hashCode();

        //Assert
        assertEquals(expectedResult, actualResult);
    }


}