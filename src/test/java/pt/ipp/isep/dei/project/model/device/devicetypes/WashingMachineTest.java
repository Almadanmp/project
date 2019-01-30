
package pt.ipp.isep.dei.project.model.device.devicetypes;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.TestUtils;
import pt.ipp.isep.dei.project.model.device.deviceSpecs.WashingMachine;
import pt.ipp.isep.dei.project.model.device.programs.Program;
import pt.ipp.isep.dei.project.model.device.programs.ProgramList;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * WashingMachine tests class.
 */


class WashingMachineTest {

    @Test
    void getTypeTest() {
        WashingMachine washingMachine = new WashingMachine();
        String expectedResult = "WashingMachine";
        String result = washingMachine.getType();
        assertEquals(expectedResult, result);
    }

    @Test
    void getConsumptionTest() {
        WashingMachine washingMachine = new WashingMachine();
        washingMachine.setAttributeValue(TestUtils.WM_CAPACITY, 0D);
        double expectedResult = 0;
        double result = washingMachine.getConsumption();
        assertEquals(expectedResult, result);
    }

    @Test
    void getAttributeNamesTest() {
        WashingMachine washingMachine = new WashingMachine();
        washingMachine.setAttributeValue(TestUtils.WM_CAPACITY, 5D);
        ProgramList listProgram = washingMachine.getProgramList();
        Program program1 = new Program("programa", 2, 2);
        listProgram.addProgram(program1);
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("Capacity");
        List<String> result = washingMachine.getAttributeNames();
        assertEquals(expectedResult, result);
    }

    @Test
    void getAttributeValuesTest() {
        WashingMachine washingMachine = new WashingMachine();
        washingMachine.setAttributeValue(TestUtils.WM_CAPACITY, 5D);
        ProgramList listProgram = washingMachine.getProgramList();
        Program program1 = new Program("programa", 2, 2);
        listProgram.addProgram(program1);
        double expectedResult = 5.0;
        Object result = washingMachine.getAttributeValue("Capacity");
        assertEquals(expectedResult, result);
    }
    @Test
    void getAttributeUnitTest() {
        WashingMachine washingMachine = new WashingMachine();
        washingMachine.setAttributeValue(TestUtils.WM_CAPACITY, 5D);
        ProgramList listProgram = washingMachine.getProgramList();
        Program program1 = new Program("programa", 2, 2);
        listProgram.addProgram(program1);
        String expectedResult = "Kg";
        Object result = washingMachine.getAttributeUnit("Capacity");
        assertEquals(expectedResult, result);
    }

    @Test
    void getAttributeUnitTest2() {
        WashingMachine washingMachine = new WashingMachine();
        washingMachine.setAttributeValue(TestUtils.WM_CAPACITY, 5D);
        ProgramList listProgram = washingMachine.getProgramList();
        Program program1 = new Program("programa", 2, 2);
        listProgram.addProgram(program1);
        String expectedResult = "";
        Object result = washingMachine.getAttributeUnit("programList");
        assertEquals(expectedResult, result);
        assertEquals(0, washingMachine.getAttributeUnit(""));

    }

    @Test
    void getAttributeValuesTest1() {
        WashingMachine washingMachine = new WashingMachine();
        washingMachine.setAttributeValue(TestUtils.WM_CAPACITY, 5D);
        ProgramList listProgram = washingMachine.getProgramList();
        Program program1 = new Program("programa", 2, 2);
        listProgram.addProgram(program1);
        int expectedResult = 0;
        Object result = washingMachine.getAttributeValue("Capacity" + "programList");
        assertEquals(expectedResult, result);
    }

    @Test
    void getAttributeValuesWithCapacityEmptyTest() {
        WashingMachine washingMachine = new WashingMachine();
        washingMachine.setAttributeValue(TestUtils.WM_CAPACITY, 34D);
        ProgramList listProgram = washingMachine.getProgramList();
        Program program1 = new Program("programa", 2, 2);
        listProgram.addProgram(program1);
        double expectedResult = 34.0;
        Object result = washingMachine.getAttributeValue("Capacity");
        assertEquals(expectedResult, result);
    }

    @Test
    void setAttributeValueTestCapacity() {
        WashingMachine washingMachine = new WashingMachine();
        washingMachine.setAttributeValue("Capacity", 5.0D);
        Object result = washingMachine.getAttributeValue("Capacity");
        assertEquals(5.0, result);
    }

    @Test
    void setAttributeValueTestCapacity2() {
        WashingMachine washingMachine = new WashingMachine();
        washingMachine.setAttributeValue(TestUtils.WM_CAPACITY, 23D);
        Object result = washingMachine.getAttributeValue("Capacity");
        assertEquals(23.0, result);
    }

    @Test
    void seeIfSetAttributeValueTestTrueWorks() {
        //Arrange
        WashingMachine washingMachine = new WashingMachine();
        washingMachine.setAttributeValue(TestUtils.WM_CAPACITY, 5D);
        ProgramList listProgram = washingMachine.getProgramList();
        Program program1 = new Program("programa", 2, 2);
        listProgram.addProgram(program1);
        //Act
        boolean actualResult = washingMachine.setAttributeValue("Capacity", 12.0);
        //Assert
        assertEquals(true, actualResult);
    }

    @Test
    void getAttributeValuesTestListProgram() {
        WashingMachine washingMachine = new WashingMachine();
        Program program1 = new Program("programa", 2, 2);
        ProgramList expectedResult = washingMachine.getProgramList();
        expectedResult.addProgram(program1);
        Object result = washingMachine.getAttributeValue("programList");
        assertEquals(expectedResult, result);
    }

    @Test
    void getAttributeValuesTestListProgramWithProgramListEmpty() {
        WashingMachine washingMachine = new WashingMachine();
        ProgramList expectedResult = washingMachine.getProgramList();
        Object result = washingMachine.getAttributeValue("programList");
        assertEquals(expectedResult, result);
    }

    @Test
    void setAttributeValueTestDefault() {
        WashingMachine washingMachine = new WashingMachine();
        washingMachine.setAttributeValue("capacity", 5.0);
        Object result = washingMachine.getAttributeValue("lisbon");
        assertEquals(0, result);
    }

    @Test
    void setAttributeValueTestDefault3() {
        WashingMachine washingMachine = new WashingMachine();
        washingMachine.setAttributeValue("programList", 5.0);
        washingMachine.setAttributeValue("Capacity", 6.0);
        Object result = washingMachine.getAttributeValue("Capacity");
        Object expectedResult = 6.0;
        assertEquals(expectedResult, result);
    }

    @Test
    void setAttributeValueTestFalse() {
        WashingMachine washingMachine = new WashingMachine();
        Program program1 = new Program("programa", 2, 2);
        ProgramList listProgram = washingMachine.getProgramList();
        listProgram.addProgram(program1);
        boolean result = washingMachine.setAttributeValue("lisboa", listProgram);
        assertEquals(false, result);
    }

    @Test
    void setAttributeValueTestFalseAgain() {
        WashingMachine washingMachine = new WashingMachine();
        Object result = washingMachine.setAttributeValue("capacity", 5);
        assertEquals(false, result);
    }

    @Test
    void setAttributeValueTest() {
        WashingMachine washingMachine = new WashingMachine();
        Program program1 = new Program("programa", 2, 2);
        ProgramList listProgram = washingMachine.getProgramList();
        listProgram.addProgram(program1);
        boolean result = washingMachine.setAttributeValue("lisboa", 12);
        assertEquals(false, result);
    }

    @Test
    void testGetAttributeCoveringAllCases() {
        //Arrange
        WashingMachine washingMachine = new WashingMachine();
        washingMachine.setAttributeValue(TestUtils.WM_CAPACITY, 5D);
        Program program1 = new Program("programa", 2, 2);
        ProgramList listProgram = washingMachine.getProgramList();
        listProgram.addProgram(program1);
        // original strings:
        assertEquals(5.0, washingMachine.getAttributeValue("Capacity"));
        assertEquals(listProgram, washingMachine.getAttributeValue("programList"));

        // same hash codes, but different strings:
        assertEquals(0, washingMachine.getAttributeValue("\0Capacity"));
        assertEquals(0, washingMachine.getAttributeValue("\0programList"));

        // distinct hash code to cover default cases of switches
        assertEquals(0, washingMachine.getAttributeValue(""));
    }
}
