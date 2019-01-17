package pt.ipp.isep.dei.project.model.device.devicetypes;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.device.Program;
import pt.ipp.isep.dei.project.model.device.ProgramList;

import java.util.ArrayList;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ProgramListTest {

    @Test
    public void seeIfGetProgramList() {
        Program program = new Program("program", 2, 3);
        ProgramList plist = new ProgramList();
        plist.addProgram(program);
        List<Program> expectedResult = new ArrayList<>();
        List<Program> result;
        ProgramList lc = new ProgramList();
        lc.addProgram(program);
        result = lc.getProgramList();
        expectedResult.add(program);
        assertEquals(expectedResult, result);
    }

    @Test
    public void testBuildProgramListString() {
        Program program = new Program("program", 2, 3);
        ProgramList plist = new ProgramList();
        plist.addProgram(program);
        String expectedResult = "---------------\n" + "\n0) Program Name: program, Duration: 2.0, Energy Consumption: 3.0"
                + "\n---------------\n";
        String result = plist.buildProgramListString();
        assertEquals(expectedResult, result);
    }

    @Test
    public void testBuildProgramListString2() {
        ProgramList plist = new ProgramList();
        String expectedResult = "This device has no programs\n";
        String result = plist.buildProgramListString();
        assertEquals(expectedResult, result);
    }


    @Test
    public void seeIfAddProgram() {
        Program program = new Program("program", 2, 3);
        ProgramList list = new ProgramList();
        boolean result = list.addProgram(program);
        assertEquals(true, result);
    }

    @Test
    public void seeIfAddProgram2() {
        Program program = new Program("program", 2, 3);
        ProgramList list = new ProgramList();
        list.addProgram(program);
        boolean result = list.addProgram(program);
        assertEquals(false, result);
    }

    @Test
    public void seeIfRemoveProgram() {
        Program program = new Program("program", 2, 3);
        ProgramList list = new ProgramList();
        boolean result = list.removeProgram(program);
        assertEquals(false, result);
    }

    @Test
    public void seeIfRemoveProgram2() {
        Program program = new Program("program", 2, 3);
        ProgramList list = new ProgramList();
        list.addProgram(program);
        boolean result = list.removeProgram(program);
        assertEquals(true, result);
    }

    @Test
    void ensureThatWeBuildProgramListStringForEach() {
        //Arrange
        Program program = new Program("Program 1",2,5);
        Program program2 = new Program("Program 2",1,3);
        ProgramList programList = new ProgramList();
        programList.addProgram(program);
        programList.addProgram(program2);
        String expectedResult = "\n" +
                "- The Program Name is Program 1, its Duration is 2.0 hours and its Energy Consumption is 5.0.\n" +
                "\n" +
                "- The Program Name is Program 2, its Duration is 1.0 hours and its Energy Consumption is 3.0.\n";
        //Act
        String actualResult = programList.buildProgramListStringForEach();
        //Assert
        assertEquals(expectedResult,actualResult);
    }

    @Test
    void ensureThatWeGetMessageWithProgramListEmpty() {
        //Arrange
        ProgramList programList = new ProgramList();
        String expectedResult = "This device has no programs\n";
        //Act
        String actualResult = programList.buildProgramListStringForEach();
        //Assert
        assertEquals(expectedResult,actualResult);
    }
}
