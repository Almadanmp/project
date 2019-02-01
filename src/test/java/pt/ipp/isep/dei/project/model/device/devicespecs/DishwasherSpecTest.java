package pt.ipp.isep.dei.project.model.device.devicespecs;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.TestUtils;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.programs.Program;
import pt.ipp.isep.dei.project.model.device.programs.ProgramList;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * DishwasherSpec tests class.
 */

class DishwasherSpecTest {

    static final String CAPACITY = "Capacity";
    static final String PROGRAM_LIST = "programList";
    static final String NOMINAL_POWER = "nominal power";
    static final String notCAPACITY = "\0Capacity";
    static final String notPROGRAM_LIST = "\0programList";
    static final String notNOMINAL_POWER = "\0nominal power";

    @Test
    void seeIfGetTypeTestWorks() {
        DishwasherSpec dishwasherSpec = new DishwasherSpec();
        String expectedResult = "Dishwasher";
        String result = dishwasherSpec.getType();
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetConsumptionTestWorks() {
        DishwasherSpec dishwasherSpec = new DishwasherSpec();
        double expectedResult = 0;
        double result = dishwasherSpec.getConsumption();
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAttributeNamesTestWorks() {
        Program program1 = new Program("programa", 2, 2);
        ProgramList listProgram = new ProgramList();
        listProgram.addProgram(program1);
        DishwasherSpec dishwasherSpec = new DishwasherSpec();
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add(DishwasherSpec.CAPACITY);
        expectedResult.add(DishwasherSpec.NOMINAL_POWER);
        List<String> result = dishwasherSpec.getAttributeNames();
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAttributeValuesTestCapacityWorks() {
        DishwasherSpec dishwasherSpec = new DishwasherSpec();
        dishwasherSpec.setAttributeValue(TestUtils.DW_CAPACITY, 1D);
        dishwasherSpec.setAttributeValue(TestUtils.NOMINAL_POWER, 1D);
        Double expectedResult = 1.0;
        Object result = dishwasherSpec.getAttributeValue("Capacity");
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAttributeValuesTestCapacityWorksFalse() {
        DishwasherSpec dishwasherSpec = new DishwasherSpec();
        dishwasherSpec.setAttributeValue(TestUtils.DW_CAPACITY, 1D);
        dishwasherSpec.setAttributeValue(TestUtils.NOMINAL_POWER, "Error");
        Double expectedResult = 1.0;
        Object result = dishwasherSpec.getAttributeValue("Capacity");
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAttributeValuesTestNPWorks() {
        DishwasherSpec dishwasherSpec = new DishwasherSpec();
        dishwasherSpec.setAttributeValue(TestUtils.DW_CAPACITY, 1D);
        dishwasherSpec.setAttributeValue(TestUtils.NOMINAL_POWER, 1D);
        Double expectedResult = 1.0;
        Object result = dishwasherSpec.getAttributeValue("nominal power");
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAttributeValuesTestListProgramWorks() {
        //Arrange
        DishwasherSpec dishwasherSpec = new DishwasherSpec();
        dishwasherSpec.setAttributeValue(TestUtils.DW_CAPACITY, 1D);
        Program program1 = new Program("programa", 2, 2);
        ProgramList expectedResult = dishwasherSpec.getProgramList();
        expectedResult.addProgram(program1);
        //Act
        Object result = dishwasherSpec.getAttributeValue("programList");
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAttributeValuesTestReturn0Works() {
        //Arrange
        DishwasherSpec dishwasherSpec = new DishwasherSpec();
        dishwasherSpec.setAttributeValue(TestUtils.DW_CAPACITY, 1D);
        dishwasherSpec.setAttributeValue(TestUtils.NOMINAL_POWER, 1D);
        Program program1 = new Program("programa", 2, 2);
        ProgramList pList = dishwasherSpec.getProgramList();
        pList.addProgram(program1);
        //Act
        int expectedResult = 0;
        Object actualResult = dishwasherSpec.getAttributeValue("cenas");
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void setAttributeValueTest() {
        DishwasherSpec dishwasherSpec = new DishwasherSpec();
        dishwasherSpec.setAttributeValue(TestUtils.DW_CAPACITY, 1D);
        dishwasherSpec.setAttributeValue(TestUtils.NOMINAL_POWER, 1D);
        Program program1 = new Program("programa", 2, 2);
        ProgramList listProgram = dishwasherSpec.getProgramList();
        listProgram.addProgram(program1);
        boolean result = dishwasherSpec.setAttributeValue("lisboa", 12);
        assertEquals(false, result);
    }

    @Test
    void seeIfSetAttributeValueTestTrueWorks() {
        //Arrange
        DishwasherSpec dishwasherSpec = new DishwasherSpec();
        dishwasherSpec.setAttributeValue(TestUtils.DW_CAPACITY, 1D);
        Program program1 = new Program("programa", 2, 2);
        ProgramList listProgram = dishwasherSpec.getProgramList();
        listProgram.addProgram(program1);
        //Act
        boolean actualResult = dishwasherSpec.setAttributeValue("Capacity", 12.0);
        //Assert
        assertTrue(actualResult);
    }

    @Test
    void getAttributeUnitTest() {
        DishwasherSpec dishwasherSpec = new DishwasherSpec();
        dishwasherSpec.setAttributeValue(TestUtils.WM_CAPACITY, 5D);
        dishwasherSpec.setAttributeValue(TestUtils.NOMINAL_POWER, 5D);
        ProgramList listProgram = dishwasherSpec.getProgramList();
        Program program1 = new Program("programa", 2, 2);
        listProgram.addProgram(program1);
        String expectedResult = "Kg";
        Object result = dishwasherSpec.getAttributeUnit("Capacity");
        assertEquals(expectedResult, result);
    }

    @Test
    void getAttributeUnitTestNP() {
        DishwasherSpec dishwasherSpec = new DishwasherSpec();
        dishwasherSpec.setAttributeValue(TestUtils.WM_CAPACITY, 5D);
        dishwasherSpec.setAttributeValue(TestUtils.NOMINAL_POWER, 5D);
        ProgramList listProgram = dishwasherSpec.getProgramList();
        Program program1 = new Program("programa", 2, 2);
        listProgram.addProgram(program1);
        String expectedResult = "kW";
        Object result = dishwasherSpec.getAttributeUnit("nominal power");
        assertEquals(expectedResult, result);
    }

    @Test
    void getAttributeUnitTest2() {
        DishwasherSpec dishwasherSpec = new DishwasherSpec();
        dishwasherSpec.setAttributeValue(TestUtils.WM_CAPACITY, 5D);
        ProgramList listProgram = dishwasherSpec.getProgramList();
        Program program1 = new Program("programa", 2, 2);
        listProgram.addProgram(program1);
        String expectedResult = " ";
        Object result = dishwasherSpec.getAttributeUnit("programList");
        assertEquals(expectedResult, result);
        assertEquals(0, dishwasherSpec.getAttributeUnit(""));

    }

    @Test
    void setAttributeValueTestFalse() {
        DishwasherSpec dishwasherSpec = new DishwasherSpec();
        dishwasherSpec.setAttributeValue(TestUtils.DW_CAPACITY, 1D);
        Program program1 = new Program("programa", 2, 2);
        ProgramList listProgram = dishwasherSpec.getProgramList();
        listProgram.addProgram(program1);
        boolean result = dishwasherSpec.setAttributeValue("lisboa", listProgram);
        assertEquals(false, result);
    }

    @Test
    void setAttributeValueTestNull() {
        DishwasherSpec dishwasherSpec = new DishwasherSpec();
        dishwasherSpec.setAttributeValue(TestUtils.DW_CAPACITY, 1D);
        Program program1 = new Program("programa", 2, 2);
        ProgramList listProgram = dishwasherSpec.getProgramList();
        listProgram.addProgram(program1);
        boolean result = dishwasherSpec.setAttributeValue(null, listProgram);
        assertEquals(false, result);
    }

    @Test
    void setAttributeValueTestCapacity() {
        DishwasherSpec dishwasherSpec = new DishwasherSpec();
        dishwasherSpec.setAttributeValue(TestUtils.DW_CAPACITY, 1D);
        dishwasherSpec.setAttributeValue("Capacity", 5.0);
        Object result = dishwasherSpec.getAttributeValue("Capacity");
        assertEquals(5.0, result);
    }

    @Test
    void setAttributeValueTestNP() {
        DishwasherSpec dishwasherSpec = new DishwasherSpec();
        dishwasherSpec.setAttributeValue(TestUtils.NOMINAL_POWER, 1D);
        dishwasherSpec.setAttributeValue("nominal power", 5.0);
        Object result = dishwasherSpec.getAttributeValue("nominal power");
        assertEquals(5.0, result);
    }

    @Test
    void setAttributeValueTestDefault() {
        DishwasherSpec dishwasherSpec = new DishwasherSpec();
        dishwasherSpec.setAttributeValue(TestUtils.DW_CAPACITY, 1D);
        dishwasherSpec.setAttributeValue("Capacity", 5.0);
        Object result = dishwasherSpec.getAttributeValue("lisbon");
        assertEquals(0, result);
    }

    @Test
    void setAttributeValueTestFalseAgain() {
        DishwasherSpec dishwasherSpec = new DishwasherSpec();
        dishwasherSpec.setAttributeValue(TestUtils.DW_CAPACITY, 1D);
        Object result = dishwasherSpec.setAttributeValue("Capacity", 5);
        assertEquals(false, result);
    }

    @Test
    void setAttributeValueTestFalseAgain1() {
        DishwasherSpec dishwasherSpec = new DishwasherSpec();
        dishwasherSpec.setAttributeValue(TestUtils.NOMINAL_POWER, 1D);
        Object result = dishwasherSpec.setAttributeValue("nominal power", 5);
        assertEquals(false, result);
    }

    @Test
    void testSetAttributeValueCoveringAllCases() {
        //Arrange
        DishwasherSpec dWasherSpec = new DishwasherSpec();
        Double attribute = 6.0;
        // original strings:
        assertTrue(dWasherSpec.setAttributeValue(NOMINAL_POWER, attribute));
        assertTrue(dWasherSpec.setAttributeValue(CAPACITY, attribute));
        // same hash codes, but different strings:
        assertFalse(dWasherSpec.setAttributeValue(notNOMINAL_POWER, attribute));
        assertFalse(dWasherSpec.setAttributeValue(notCAPACITY, attribute));
        // distinct hash code to cover default cases of switches
        assertFalse(dWasherSpec.setAttributeValue("", attribute));
    }

    @Test
    void testSetAttributeValueDeviceCoveringAllCases() {
        //Arrange
        DishwasherSpec dWasherSpec = new DishwasherSpec();
        Device device = new Device(dWasherSpec);
        Double attribute = 6.0;
        // original strings:
        assertTrue(device.setAttributeValue(NOMINAL_POWER, attribute));
        assertTrue(device.setAttributeValue(CAPACITY, attribute));
        // same hash codes, but different strings:
        assertFalse(device.setAttributeValue(notNOMINAL_POWER, attribute));
        assertFalse(device.setAttributeValue(notCAPACITY, attribute));
        // distinct hash code to cover default cases of switches
        assertFalse(device.setAttributeValue("", attribute));
    }

    @Test
    void seeIfGetAttributeUnitWorksInAllCases() {
        //Arrange
        DishwasherSpec dWasherSpec = new DishwasherSpec();
        String attributeKg = "Kg";
        String attributePl = " ";
        String attributeKW = "kW";
        // original strings:
        assertEquals(attributeKg, dWasherSpec.getAttributeUnit(CAPACITY));
        assertEquals(attributePl, dWasherSpec.getAttributeUnit(PROGRAM_LIST));
        assertEquals(attributeKW, dWasherSpec.getAttributeUnit(NOMINAL_POWER));
        // same hash codes, but different strings:
        assertEquals(0, dWasherSpec.getAttributeUnit(notCAPACITY));
        assertEquals(0, dWasherSpec.getAttributeUnit(notPROGRAM_LIST));
        assertEquals(0, dWasherSpec.getAttributeUnit(notNOMINAL_POWER));
        // distinct hash code to cover default cases of switches
        assertEquals(0, dWasherSpec.getAttributeUnit(""));
    }
}