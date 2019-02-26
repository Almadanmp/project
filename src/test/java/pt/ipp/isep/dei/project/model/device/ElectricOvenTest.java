package pt.ipp.isep.dei.project.model.device;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.device.devicespecs.ElectricOvenSpec;
import pt.ipp.isep.dei.project.model.device.devicespecs.MicrowaveOvenSpec;
import pt.ipp.isep.dei.project.model.device.log.Log;
import pt.ipp.isep.dei.project.model.device.log.LogList;
import pt.ipp.isep.dei.project.model.device.program.VariableTimeProgram;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testng.Assert.assertFalse;

public class ElectricOvenTest {
    private ElectricOven electricOvenValid = new ElectricOven(new ElectricOvenSpec());
    private VariableTimeProgram variableTimeProgramValid;
    private Log validLog;
    private LogList validLogList;


    @BeforeEach
    void arrangeArtifacts() {
        variableTimeProgramValid = new VariableTimeProgram("VariableTimeProgram 1",70);
        electricOvenValid.setName("Electric Oven 3000");
        electricOvenValid.setNominalPower(15);
        validLog = new Log(1, new GregorianCalendar(2019, 1, 1).getTime(),
                new GregorianCalendar(2019, 1, 1).getTime());
        validLogList = electricOvenValid.getLogList();
    }

    @Test
     void getConsumption() {
        double expectedResult = 360;
        double result = electricOvenValid.getEnergyConsumption(24);
        assertEquals(expectedResult, result);
    }

    @Test
    void getProgramEnergyConsumptionTest() {
        double result = electricOvenValid.getProgramEnergyConsumption(20,variableTimeProgramValid);
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
    void getAndSetNominalPowerTest(){
        electricOvenValid.setNominalPower(150);
        double result = electricOvenValid.getNominalPower();
        double expectedResult = 150;
        assertEquals(expectedResult, result);
    }

    @Test
    void getAndSetAttributeValueTest(){
        Object result =  electricOvenValid.getAttributeValue("");
        Object expectedResult = 0;
        assertEquals(expectedResult, result);
        boolean resultSet = electricOvenValid.setAttributeValue("",10);
        assertFalse(resultSet);
        Object resultUnit = electricOvenValid.getAttributeUnit("");
        assertEquals(false,resultUnit);
        List<String> resultString = electricOvenValid.getAttributeNames();
        assertEquals(new ArrayList<>(),resultString);
    }

    @Test
    void deactivateElectricOven(){
        boolean expectedResult = true;
        boolean actualResult = electricOvenValid.deactivate();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void notDeactivateElectricOven(){
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
}


