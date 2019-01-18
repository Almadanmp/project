package pt.ipp.isep.dei.project.model.device.devicetypes;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.device.Program;
import pt.ipp.isep.dei.project.model.device.ProgramList;

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
        DeviceType expectedResult = DeviceType.DISHWASHER;
        DeviceType result = dishwasher.getType();
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
    void seeIfGetCapacityWorks() {
        Program program1 = new Program("programa",2,2);
        ProgramList listProgram= new ProgramList();
        listProgram.addProgram(program1);
        Dishwasher dishwasher = new Dishwasher(4,listProgram);
        double expectedResult = 6;
        dishwasher.getCapacity();
        dishwasher.setCapacity(6);
        double result = dishwasher.getCapacity();
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAttributeNamesTestWorks() {
        Program program1 = new Program("programa",2,2);
        ProgramList listProgram= new ProgramList();
        listProgram.addProgram(program1);
        Dishwasher dishwasher = new Dishwasher(1,listProgram);
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("capacity");
        expectedResult.add("programList");
        List<String> result = dishwasher.getAttributeNames();
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAttributeValuesTestCapacityWorks() {
        Dishwasher dishwasher = new Dishwasher(1.0);
        Double expectedResult = 1.0;
        Object result = dishwasher.getAttributeValue("capacity");
        assertEquals(expectedResult, result);
    }
    @Test
    void seeIfGetAttributeValuesTestCapacityWorks2() {
        Dishwasher dishwasher = new Dishwasher();
        Double expectedResult = 0.0;
        dishwasher.setAttributeValue("capacity",null);
        Object result = dishwasher.getAttributeValue("capacity");
        assertEquals(expectedResult, result);
    }
    @Test
    void seeIfGetAttributeValuesTestListProgramWorks() {
        //Arrange
        Program program1 = new Program("programa",2,2);
        ProgramList expectedResult= new ProgramList();
        expectedResult.addProgram(program1);
        Dishwasher dishwasher = new Dishwasher(1.0,expectedResult);
        //Act
        Object result = dishwasher.getAttributeValue("programList");
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAttributeValuesTestReturn0Works() {
        //Arrange
        Program program1 = new Program("programa",2,2);
        ProgramList pList= new ProgramList();
        pList.addProgram(program1);
        Dishwasher dishwasher = new Dishwasher(1.0,pList);
        //Act
        int expectedResult = 0;
        Object actualResult = dishwasher.getAttributeValue("cenas");
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void setAttributeValueTest() {
        Program program1 = new Program("programa",2,2);
        ProgramList listProgram= new ProgramList();
        listProgram.addProgram(program1);
        Dishwasher dishwasher = new Dishwasher(1,listProgram);
        boolean result = dishwasher.setAttributeValue("lisboa", 12);
        assertEquals(false, result);
    }

    @Test
    void seeIfSetAttributeValueTestTrueWorks() {
        //Arrange
        Program program1 = new Program("programa",2,2);
        ProgramList listProgram= new ProgramList();
        listProgram.addProgram(program1);
        Dishwasher dishwasher = new Dishwasher(1,listProgram);
        //Act
        boolean actualResult = dishwasher.setAttributeValue("capacity", 12.0);
        //Assert
        assertTrue(actualResult);
    }

    @Test
    void setAttributeValueTestFalse() {
        Program program1 = new Program("programa",2,2);
        ProgramList listProgram= new ProgramList();
        listProgram.addProgram(program1);
        Dishwasher dishwasher = new Dishwasher(1,listProgram);
        boolean result = dishwasher.setAttributeValue("lisboa", listProgram);
        assertEquals(false, result);
    }

    @Test
    void setAttributeValueTestCapacity() {
        Dishwasher dishwasher = new Dishwasher(1);
        dishwasher.setAttributeValue("capacity", 5.0);
        Object result = dishwasher.getAttributeValue("capacity");
        assertEquals(5.0, result);
    }
    @Test
    void setAttributeValueTestDefault() {
        Dishwasher dishwasher = new Dishwasher(1);
        dishwasher.setAttributeValue("capacity", 5.0);
        Object result = dishwasher.getAttributeValue("lisbon");
        assertEquals(0, result);
    }

    @Test
    void setAttributeValueTestFalseAgain() {
        Dishwasher dishwasher = new Dishwasher(1);
        Object result = dishwasher.setAttributeValue("capacity",5);
        assertEquals(false, result);
    }


}
