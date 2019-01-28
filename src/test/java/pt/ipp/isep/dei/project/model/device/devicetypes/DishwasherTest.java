package pt.ipp.isep.dei.project.model.device.devicetypes;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.TestUtils;
import pt.ipp.isep.dei.project.model.device.programs.Program;
import pt.ipp.isep.dei.project.model.device.programs.ProgramList;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Dishwasher tests class.
 */

class DishwasherTest {

    @Test
    void seeIfGetTypeTestWorks() {
        Dishwasher dishwasher = new Dishwasher();
        String expectedResult = "Dishwasher";
        String result = dishwasher.getType();
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetConsumptionTestWorks() {
        Dishwasher dishwasher = new Dishwasher();
        double expectedResult = 0;
        double result = dishwasher.getConsumption();
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAttributeNamesTestWorks() {
        Program program1 = new Program("programa", 2, 2);
        ProgramList listProgram = new ProgramList();
        listProgram.addProgram(program1);
        Dishwasher dishwasher = new Dishwasher();
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("capacity");
        expectedResult.add("programList");
        List<String> result = dishwasher.getAttributeNames();
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAttributeValuesTestCapacityWorks() {
        Dishwasher dishwasher = new Dishwasher();
        dishwasher.setAttributeValue(TestUtils.DW_CAPACITY, 1D);
        Double expectedResult = 1.0;
        Object result = dishwasher.getAttributeValue("capacity");
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAttributeValuesTestListProgramWorks() {
        //Arrange
        Dishwasher dishwasher = new Dishwasher();
        dishwasher.setAttributeValue(TestUtils.DW_CAPACITY, 1D);
        Program program1 = new Program("programa", 2, 2);
        ProgramList expectedResult = dishwasher.getProgramList();
        expectedResult.addProgram(program1);
        //Act
        Object result = dishwasher.getAttributeValue("programList");
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAttributeValuesTestReturn0Works() {
        //Arrange
        Dishwasher dishwasher = new Dishwasher();
        dishwasher.setAttributeValue(TestUtils.DW_CAPACITY, 1D);
        Program program1 = new Program("programa", 2, 2);
        ProgramList pList = dishwasher.getProgramList();
        pList.addProgram(program1);
        //Act
        int expectedResult = 0;
        Object actualResult = dishwasher.getAttributeValue("cenas");
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void setAttributeValueTest() {
        Dishwasher dishwasher = new Dishwasher();
        dishwasher.setAttributeValue(TestUtils.DW_CAPACITY, 1D);
        Program program1 = new Program("programa", 2, 2);
        ProgramList listProgram = dishwasher.getProgramList();
        listProgram.addProgram(program1);
        boolean result = dishwasher.setAttributeValue("lisboa", 12);
        assertEquals(false, result);
    }

    @Test
    void seeIfSetAttributeValueTestTrueWorks() {
        //Arrange
        Dishwasher dishwasher = new Dishwasher();
        dishwasher.setAttributeValue(TestUtils.DW_CAPACITY, 1D);
        Program program1 = new Program("programa", 2, 2);
        ProgramList listProgram = dishwasher.getProgramList();
        listProgram.addProgram(program1);
        //Act
        boolean actualResult = dishwasher.setAttributeValue("capacity", 12.0);
        //Assert
        assertTrue(actualResult);
    }

    @Test
    void setAttributeValueTestFalse() {
        Dishwasher dishwasher = new Dishwasher();
        dishwasher.setAttributeValue(TestUtils.DW_CAPACITY, 1D);
        Program program1 = new Program("programa", 2, 2);
        ProgramList listProgram = dishwasher.getProgramList();
        listProgram.addProgram(program1);
        boolean result = dishwasher.setAttributeValue("lisboa", listProgram);
        assertEquals(false, result);
    }

    @Test
    void setAttributeValueTestCapacity() {
        Dishwasher dishwasher = new Dishwasher();
        dishwasher.setAttributeValue(TestUtils.DW_CAPACITY, 1D);
        dishwasher.setAttributeValue("capacity", 5.0);
        Object result = dishwasher.getAttributeValue("capacity");
        assertEquals(5.0, result);
    }

    @Test
    void setAttributeValueTestDefault() {
        Dishwasher dishwasher = new Dishwasher();
        dishwasher.setAttributeValue(TestUtils.DW_CAPACITY, 1D);
        dishwasher.setAttributeValue("capacity", 5.0);
        Object result = dishwasher.getAttributeValue("lisbon");
        assertEquals(0, result);
    }

    @Test
    void setAttributeValueTestFalseAgain() {
        Dishwasher dishwasher = new Dishwasher();
        dishwasher.setAttributeValue(TestUtils.DW_CAPACITY, 1D);
        Object result = dishwasher.setAttributeValue("capacity", 5);
        assertEquals(false, result);
    }
}