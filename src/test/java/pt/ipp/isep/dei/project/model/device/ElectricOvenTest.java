package pt.ipp.isep.dei.project.model.device;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Room;
import pt.ipp.isep.dei.project.model.device.devicespecs.ElectricOvenSpec;
import pt.ipp.isep.dei.project.model.device.log.Log;
import pt.ipp.isep.dei.project.model.device.log.LogList;
import pt.ipp.isep.dei.project.model.device.program.FixedTimeProgram;
import pt.ipp.isep.dei.project.model.device.program.ProgramList;
import pt.ipp.isep.dei.project.model.device.program.VariableTimeProgram;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

class ElectricOvenTest {
    private ElectricOven electricOvenValid = new ElectricOven(new ElectricOvenSpec());
    private VariableTimeProgram variableTimeProgramValid;
    private Log validLog;
    private LogList validLogList;
    private Date initialTime;
    private Date finalTime;
    private Date periodEnding1;
    private Date periodEnding2;
    private Date periodBeginning1;
    private Date periodBeginning2;
    private Date periodBeginning9AM;
    private Date periodEnding1120AM;
    private Log validLog1;
    private Log validLog2;


    @BeforeEach
    void arrangeArtifacts() {
        variableTimeProgramValid = new VariableTimeProgram("VariableTimeProgram 1", 70);
        electricOvenValid.setName("Electric Oven 3000");
        electricOvenValid.setNominalPower(15);
        validLog = new Log(1, new GregorianCalendar(2019, 1, 1).getTime(),
                new GregorianCalendar(2019, 1, 1).getTime());
        validLogList = electricOvenValid.getLogList();
        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            initialTime = validSdf.parse("20/10/2018 10:00:00");
            finalTime = validSdf.parse("20/10/2018 11:00:00");
            periodEnding1 = validSdf.parse("20/10/2018 10:20:00");
            periodBeginning2 = validSdf.parse("20/10/2018 10:30:00");
            periodBeginning1 = validSdf.parse("20/10/2018 10:01:00");
            periodEnding2 = validSdf.parse("20/10/2018 10:59:00");
            periodBeginning9AM = validSdf.parse("20/10/2018 9:00:00");
            periodEnding1120AM = validSdf.parse("20/10/2018 11:20:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        validLog1 = new Log(56, initialTime, periodEnding1);
        validLog2 = new Log(55, periodBeginning2, finalTime);
    }

    @Test
    void getConsumption() {
        double expectedResult = 360;
        double result = electricOvenValid.getEnergyConsumption(24);
        assertEquals(expectedResult, result);
    }

    @Test
    void getProgramEnergyConsumptionTest() {
        double result = electricOvenValid.getProgramEnergyConsumption(20, variableTimeProgramValid);
        double expectedResult = 1400;
        double expectedResultZero = 0;
        double resultZero = electricOvenValid.getEnergyConsumption(0);
        assertEquals(expectedResult, result);
        assertEquals(expectedResultZero, resultZero);
    }

    @Test
    void seeIfPrintDeviceWorks() {
        String result = electricOvenValid.buildString();
        String expectedResult = "The device Name is Electric Oven 3000, and its NominalPower is 15.0 kW.\n";
        assertEquals(expectedResult, result);
    }

    @Test
    void setAndGetNameTest() {
        electricOvenValid.setName("Fridge 3000");
        String result = electricOvenValid.getName();
        String expectedResult = "Fridge 3000";
        assertEquals(expectedResult, result);
    }

    @Test
    void getAndSetNominalPowerTest() {
        electricOvenValid.setNominalPower(150);
        double result = electricOvenValid.getNominalPower();
        double expectedResult = 150;
        assertEquals(expectedResult, result);
    }

    @Test
    void getAndSetAttributeValueTest() {
        Object result = electricOvenValid.getAttributeValue("");
        Object expectedResult = 0;
        assertEquals(expectedResult, result);
        boolean resultSet = electricOvenValid.setAttributeValue("", 10);
        assertFalse(resultSet);
        Object resultUnit = electricOvenValid.getAttributeUnit("");
        assertEquals(false, resultUnit);
        List<String> resultString = electricOvenValid.getAttributeNames();
        assertEquals(new ArrayList<>(), resultString);
    }

    @Test
    void deactivateElectricOven() {
        boolean expectedResult = true;
        boolean actualResult = electricOvenValid.deactivate();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void notDeactivateElectricOven() {
        electricOvenValid.deactivate();
        boolean expectedResult = false;
        boolean actualResult = electricOvenValid.deactivate();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getLogList() {
        electricOvenValid.addLog(validLog);
        LogList result = electricOvenValid.getLogList();
        assertEquals(validLogList, result);
    }

    @Test
    void seeIfGetDeviceTypeTest() {
        String dT = "Electric Oven";
        String result = electricOvenValid.getType();
        assertEquals(dT, result);
    }

    @Test
    void seeIfEqualToSameObject() {
        boolean actualResult = electricOvenValid.equals(electricOvenValid);
        assertTrue(actualResult);
    }

    @Test
    void seeIfEqualsToDifObject() {
        ElectricOven d2 = new ElectricOven(new ElectricOvenSpec());
        d2.setName("EOTwo");
        d2.setNominalPower(12.0);
        boolean actualResult = electricOvenValid.equals(d2);
        assertFalse(actualResult);
    }

    @Test
    void seeEqualsToDifTypeObject() {
        Room room = new Room("quarto", 1, 80, 2, 2);
        boolean actualResult = electricOvenValid.equals(room);
        assertFalse(actualResult);
    }

    @Test
    void seeEqualsToNullObject() {
        boolean actualResult = electricOvenValid == null;
        assertFalse(actualResult);
    }

    @Test
    void hashCodeDummyTest() {
        int expectedResult = 1;
        int actualResult = electricOvenValid.hashCode();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getTotalMeteredEnergyConsumptionInDeviceWithinGivenTimeIntervalEquals() {
        electricOvenValid.addLog(validLog1);
        electricOvenValid.addLog(validLog2);
        double result = electricOvenValid.getConsumptionWithinGivenInterval(initialTime, finalTime);
        assertEquals(111, result);
    }

    @Test
    void getTotalMeteredEnergyConsumptionInDeviceWithinGivenTimeIntervalAfterBefore() {
        Log log1 = new Log(120, periodBeginning1, periodEnding1);
        Log log2 = new Log(55, periodBeginning2, periodEnding2);
        electricOvenValid.addLog(log1);
        electricOvenValid.addLog(log2);
        double result = electricOvenValid.getConsumptionWithinGivenInterval(initialTime, finalTime);
        assertEquals(175, result);
    }

    @Test
    void getTotalMeteredEnergyConsumptionInDeviceWithinGivenTimeIntervalAfterBeforeReverseOutOfBounds() {
        Log log1 = new Log(56, periodBeginning9AM, periodEnding1);
        Log log2 = new Log(55, periodBeginning2, periodEnding1120AM);
        electricOvenValid.addLog(log1);
        electricOvenValid.addLog(log2);
        double result = electricOvenValid.getConsumptionWithinGivenInterval(initialTime, finalTime);
        assertEquals(0.0, result);
    }

    @Test
    void testCountLogsInInterval() {
        electricOvenValid.addLog(validLog1);
        electricOvenValid.addLog(validLog2);
        double result = electricOvenValid.countLogsInInterval(initialTime, finalTime);
        assertEquals(2, result);
    }

    @Test
    void getLogsInInterval() {
        electricOvenValid.addLog(validLog1);
        electricOvenValid.addLog(validLog2);
        LogList expectedResult = new LogList();
        expectedResult.addLog(validLog1);
        expectedResult.addLog(validLog2);
        LogList result = electricOvenValid.getLogsInInterval(initialTime, finalTime);
        assertEquals(expectedResult, result);
        assertFalse(electricOvenValid.isLogListEmpty());
    }

    @Test
    void getConsumptionTimeZero() {
        double expectedResult = 0;
        double result = electricOvenValid.getEnergyConsumption(0);
        assertEquals(expectedResult, result);
    }

    @Test
    void getProgramList() {
        FixedTimeProgram program1 = new FixedTimeProgram("programa", 2, 2);
        ProgramList listProgram = electricOvenValid.getProgramList();
        listProgram.addProgram(program1);
        ProgramList result = electricOvenValid.getProgramList();
        assertEquals(listProgram, result);
    }

    @Test
    void addLogListFalse() {
        electricOvenValid.addLog(validLog);
        assertFalse(electricOvenValid.addLog(validLog));
        assertFalse(electricOvenValid.isLogListEmpty());
    }
}


