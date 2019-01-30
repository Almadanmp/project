
package pt.ipp.isep.dei.project.model.device.deviceSpecs;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.TestUtils;
import pt.ipp.isep.dei.project.model.device.programs.Program;
import pt.ipp.isep.dei.project.model.device.programs.ProgramList;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * WashingMachineSpec tests class.
 */


class WashingMachineSpecTest {

    @Test
    void getTypeTest() {
        WashingMachineSpec washingMachineSpec = new WashingMachineSpec();
        String expectedResult = "WashingMachine";
        String result = washingMachineSpec.getType();
        assertEquals(expectedResult, result);
    }

    @Test
    void getConsumptionTest() {
        WashingMachineSpec washingMachineSpec = new WashingMachineSpec();
        washingMachineSpec.setAttributeValue(TestUtils.WM_CAPACITY, 0D);
        double expectedResult = 0;
        double result = washingMachineSpec.getConsumption();
        assertEquals(expectedResult, result);
    }

    @Test
    void getAttributeNamesTest() {
        WashingMachineSpec washingMachineSpec = new WashingMachineSpec();
        washingMachineSpec.setAttributeValue(WashingMachineSpec.NOMINAL_POWER, 5D);
        washingMachineSpec.setAttributeValue(WashingMachineSpec.CAPACITY, 5D);
        ProgramList listProgram = washingMachineSpec.getProgramList();
        Program program1 = new Program("programa", 2, 2);
        listProgram.addProgram(program1);
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add(WashingMachineSpec.NOMINAL_POWER);
        expectedResult.add(WashingMachineSpec.CAPACITY);
        List<String> result = washingMachineSpec.getAttributeNames();
        assertEquals(expectedResult, result);
    }

    @Test
    void getAttributeValuesTest() {
        WashingMachineSpec washingMachineSpec = new WashingMachineSpec();
        washingMachineSpec.setAttributeValue(TestUtils.WM_CAPACITY, 5D);
        ProgramList listProgram = washingMachineSpec.getProgramList();
        Program program1 = new Program("programa", 2, 2);
        listProgram.addProgram(program1);
        double expectedResult = 5.0;
        Object result = washingMachineSpec.getAttributeValue("Capacity");
        assertEquals(expectedResult, result);
    } @Test
    void getAttributeValuesTestNp() {
        WashingMachineSpec washingMachineSpec = new WashingMachineSpec();
        washingMachineSpec.setAttributeValue(TestUtils.NOMINAL_POWER, 5D);
        ProgramList listProgram = washingMachineSpec.getProgramList();
        Program program1 = new Program("programa", 2, 2);
        listProgram.addProgram(program1);
        double expectedResult = 5.0;
        Object result = washingMachineSpec.getAttributeValue("nominal power");
        assertEquals(expectedResult, result);
    }

    @Test
    void getAttributeUnitTest() {
        WashingMachineSpec washingMachineSpec = new WashingMachineSpec();
        washingMachineSpec.setAttributeValue(TestUtils.WM_CAPACITY, 5D);
        ProgramList listProgram = washingMachineSpec.getProgramList();
        Program program1 = new Program("programa", 2, 2);
        listProgram.addProgram(program1);
        String expectedResult = "Kg";
        Object result = washingMachineSpec.getAttributeUnit("Capacity");
        assertEquals(expectedResult, result);
    }

    @Test
    void getAttributeUnitTest2() {
        WashingMachineSpec washingMachineSpec = new WashingMachineSpec();
        washingMachineSpec.setAttributeValue(TestUtils.WM_CAPACITY, 5D);
        ProgramList listProgram = washingMachineSpec.getProgramList();
        Program program1 = new Program("programa", 2, 2);
        listProgram.addProgram(program1);
        Object result = washingMachineSpec.getAttributeUnit("programList");
        assertEquals(false, result);
        assertEquals(false, washingMachineSpec.getAttributeUnit(""));

    } @Test
    void getAttributeUnitTest3() {
        WashingMachineSpec washingMachineSpec = new WashingMachineSpec();
        washingMachineSpec.setAttributeValue(TestUtils.NOMINAL_POWER, 5D);
        ProgramList listProgram = washingMachineSpec.getProgramList();
        Program program1 = new Program("programa", 2, 2);
        listProgram.addProgram(program1);
        Object result = washingMachineSpec.getAttributeUnit("nominal power");
        assertEquals("kW", result);

    }

    @Test
    void getAttributeValuesTest1() {
        WashingMachineSpec washingMachineSpec = new WashingMachineSpec();
        washingMachineSpec.setAttributeValue(TestUtils.WM_CAPACITY, 5D);
        ProgramList listProgram = washingMachineSpec.getProgramList();
        Program program1 = new Program("programa", 2, 2);
        listProgram.addProgram(program1);
        int expectedResult = 0;
        Object result = washingMachineSpec.getAttributeValue("Capacity" + "programList");
        assertEquals(expectedResult, result);
    }

    @Test
    void getAttributeValuesWithCapacityEmptyTest() {
        WashingMachineSpec washingMachineSpec = new WashingMachineSpec();
        washingMachineSpec.setAttributeValue(TestUtils.WM_CAPACITY, 34D);
        ProgramList listProgram = washingMachineSpec.getProgramList();
        Program program1 = new Program("programa", 2, 2);
        listProgram.addProgram(program1);
        double expectedResult = 34.0;
        Object result = washingMachineSpec.getAttributeValue("Capacity");
        assertEquals(expectedResult, result);
    }

    @Test
    void setAttributeValueTestCapacity() {
        WashingMachineSpec washingMachineSpec = new WashingMachineSpec();
        washingMachineSpec.setAttributeValue("Capacity", 5.0D);
        Object result = washingMachineSpec.getAttributeValue("Capacity");
        assertEquals(5.0, result);
    }

    @Test
    void setAttributeValueTestCapacity2() {
        WashingMachineSpec washingMachineSpec = new WashingMachineSpec();
        washingMachineSpec.setAttributeValue(TestUtils.WM_CAPACITY, 23D);
        Object result = washingMachineSpec.getAttributeValue("Capacity");
        assertEquals(23.0, result);
    }

    @Test
    void seeIfSetAttributeValueTestTrueWorks() {
        //Arrange
        WashingMachineSpec washingMachineSpec = new WashingMachineSpec();
        washingMachineSpec.setAttributeValue(TestUtils.WM_CAPACITY, 5D);
        ProgramList listProgram = washingMachineSpec.getProgramList();
        Program program1 = new Program("programa", 2, 2);
        listProgram.addProgram(program1);
        //Act
        boolean actualResult = washingMachineSpec.setAttributeValue("Capacity", 12.0);
        //Assert
        assertEquals(true, actualResult);
    }

    @Test
    void getAttributeValuesTestListProgram() {
        WashingMachineSpec washingMachineSpec = new WashingMachineSpec();
        Program program1 = new Program("programa", 2, 2);
        ProgramList expectedResult = washingMachineSpec.getProgramList();
        expectedResult.addProgram(program1);
        Object result = washingMachineSpec.getAttributeValue("programList");
        assertEquals(expectedResult, result);
    }

    @Test
    void getAttributeValuesTestListProgramWithProgramListEmpty() {
        WashingMachineSpec washingMachineSpec = new WashingMachineSpec();
        ProgramList expectedResult = washingMachineSpec.getProgramList();
        Object result = washingMachineSpec.getAttributeValue("programList");
        assertEquals(expectedResult, result);
    }

    @Test
    void setAttributeValueTestDefault() {
        WashingMachineSpec washingMachineSpec = new WashingMachineSpec();
        washingMachineSpec.setAttributeValue("capacity", 5.0);
        Object result = washingMachineSpec.getAttributeValue("lisbon");
        assertEquals(0, result);
    }

    @Test
    void setAttributeValueTestDefault3() {
        WashingMachineSpec washingMachineSpec = new WashingMachineSpec();
        washingMachineSpec.setAttributeValue("programList", 5.0);
        washingMachineSpec.setAttributeValue("nominal power", 5.0);
        washingMachineSpec.setAttributeValue("Capacity", 6.0);
        Object result = washingMachineSpec.getAttributeValue("Capacity");
        Object expectedResult = 6.0;
        assertEquals(expectedResult, result);
    }
    @Test
    void setAttributeValueTestDefault4() {
        WashingMachineSpec washingMachineSpec = new WashingMachineSpec();
        washingMachineSpec.setAttributeValue("programList", 5.0);
        washingMachineSpec.setAttributeValue("nominal power", 5.0);
        washingMachineSpec.setAttributeValue("Capacity", 6.0);
        Object result = washingMachineSpec.getAttributeValue("nominal power");
        Object expectedResult = 5.0;
        assertEquals(expectedResult, result);
    }

    @Test
    void setAttributeValueTestFalse() {
        WashingMachineSpec washingMachineSpec = new WashingMachineSpec();
        Program program1 = new Program("programa", 2, 2);
        ProgramList listProgram = washingMachineSpec.getProgramList();
        listProgram.addProgram(program1);
        boolean result = washingMachineSpec.setAttributeValue("lisboa", listProgram);
        assertEquals(false, result);
    }

    @Test
    void setAttributeValueTestFalseAgain() {
        WashingMachineSpec washingMachineSpec = new WashingMachineSpec();
        Object result = washingMachineSpec.setAttributeValue("capacity", 5);
        assertEquals(false, result);
    }

    @Test
    void setAttributeValueTest() {
        WashingMachineSpec washingMachineSpec = new WashingMachineSpec();
        Program program1 = new Program("programa", 2, 2);
        ProgramList listProgram = washingMachineSpec.getProgramList();
        listProgram.addProgram(program1);
        boolean result = washingMachineSpec.setAttributeValue("lisboa", 12);
        assertEquals(false, result);
    }

    @Test
    void testGetAttributeCoveringAllCases() {
        //Arrange
        WashingMachineSpec washingMachineSpec = new WashingMachineSpec();
        washingMachineSpec.setAttributeValue(TestUtils.WM_CAPACITY, 5D);
        washingMachineSpec.setAttributeValue(TestUtils.NOMINAL_POWER, 5D);
        Program program1 = new Program("programa", 2, 2);
        ProgramList listProgram = washingMachineSpec.getProgramList();
        listProgram.addProgram(program1);
        // original strings:
        assertEquals(5.0, washingMachineSpec.getAttributeValue("Capacity"));
        assertEquals(5.0, washingMachineSpec.getAttributeValue("nominal power"));
        assertEquals(listProgram, washingMachineSpec.getAttributeValue("programList"));

        // same hash codes, but different strings:
        assertEquals(0, washingMachineSpec.getAttributeValue("\0Capacity"));
        assertEquals(0, washingMachineSpec.getAttributeValue("\0nominal power"));
        assertEquals(0, washingMachineSpec.getAttributeValue("\0programList"));

        // distinct hash code to cover default cases of switches
        assertEquals(0, washingMachineSpec.getAttributeValue(""));
    }
}
