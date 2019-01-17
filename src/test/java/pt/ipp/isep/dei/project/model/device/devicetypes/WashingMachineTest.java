package pt.ipp.isep.dei.project.model.device.devicetypes;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.device.Program;
import pt.ipp.isep.dei.project.model.device.ProgramList;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * WashingMachine tests class.
 */

public class WashingMachineTest {

    @Test
    public void getTypeTest() {
        WashingMachine washingMachine = new WashingMachine();
        DeviceType expectedResult = DeviceType.WASHING_MACHINE;
        DeviceType result = washingMachine.getType();
        assertEquals(expectedResult, result);
    }

    @Test
    public void getConsumptionTest() {
        WashingMachine washingMachine = new WashingMachine();
        double expectedResult = 0;
        double result = washingMachine.getConsumption();
        assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfGetCapacity() {
        Program program1 = new Program("programa", 2, 2);
        ProgramList listProgram = new ProgramList();
        listProgram.addProgram(program1);
        WashingMachine washingMachine = new WashingMachine(5, listProgram);
        double expectedResult = 6;
        washingMachine.setCapacity(6);
        double result = washingMachine.getCapacity();
        assertEquals(expectedResult, result);
    }

    @Test
    public void getAttributeNamesTest() {
        Program program1 = new Program("programa", 2, 2);
        ProgramList listProgram = new ProgramList();
        listProgram.addProgram(program1);
        WashingMachine washingMachine = new WashingMachine(5, listProgram);
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("capacity");
        expectedResult.add("programList");
        List<String> result = washingMachine.getAttributeNames();
        assertEquals(expectedResult, result);
    }

    @Test
    public void getAttributeValuesTest() {
        Program program1 = new Program("programa", 2, 2);
        ProgramList listProgram = new ProgramList();
        listProgram.addProgram(program1);
        WashingMachine washingMachine = new WashingMachine(5, listProgram);
        double expectedResult = 5.0;
        Object result = washingMachine.getAttributeValue("capacity");
        assertEquals(expectedResult, result);
    }

    @Test
    public void getAttributeValuesWithmCapacityEmptyTest() {
        Program program1 = new Program("programa", 2, 2);
        ProgramList listProgram = new ProgramList();
        listProgram.addProgram(program1);
        WashingMachine washingMachine = new WashingMachine();
        double expectedResult = 0.0;
        Object result = washingMachine.getAttributeValue("capacity");
        assertEquals(expectedResult, result);
    }

    @Test
    public void setAttributeValueTest() {
        Program program1 = new Program("programa", 2, 2);
        ProgramList listProgram = new ProgramList();
        listProgram.addProgram(program1);
        WashingMachine washingMachine = new WashingMachine(5, listProgram);
        boolean result = washingMachine.setAttributeValue("lisboa", 12);
        assertEquals(false, result);
    }


    @Test
    public void setAttributeValueTestCapacity() {
        WashingMachine washingMachine = new WashingMachine(1);
        washingMachine.setAttributeValue("capacity", 5.0);
        Object result = washingMachine.getAttributeValue("capacity");
        assertEquals(5.0, result);
    }

    @Test
    public void getAttributeValuesTestListProgram() {
        Program program1 = new Program("programa", 2, 2);
        ProgramList expectedResult = new ProgramList();
        expectedResult.addProgram(program1);
        WashingMachine washingMachine = new WashingMachine(1.0, expectedResult);
        Object result = washingMachine.getAttributeValue("programList");
        assertEquals(expectedResult, result);
    }

    @Test
    public void setAttributeValueTestDefault() {
        WashingMachine washingMachine = new WashingMachine(1);
        washingMachine.setAttributeValue("capacity", 5.0);
        Object result = washingMachine.getAttributeValue("lisbon");
        assertEquals(0, result);
    }

    @Test
    public void setAttributeValueTestFalse() {
        Program program1 = new Program("programa", 2, 2);
        ProgramList listProgram = new ProgramList();
        listProgram.addProgram(program1);
        WashingMachine washingMachine = new WashingMachine(1, listProgram);
        boolean result = washingMachine.setAttributeValue("lisboa", listProgram);
        assertEquals(false, result);
    }

    @Test
    public void setAttributeValueTestFalseAgain() {
        WashingMachine washingMachine = new WashingMachine(1);
        Object result = washingMachine.setAttributeValue("capacity", 5);
        assertEquals(false, result);
    }
}
