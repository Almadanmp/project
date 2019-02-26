package pt.ipp.isep.dei.project.model.device.program;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ProgramListTest {

    @Test
     void testBuildProgramListString() {
        Program program = new Program("program", 2, 3);
        ProgramList plist = new ProgramList();
        plist.addProgram(program);
        String expectedResult = "---------------\n" + "\n0) Program Name: program, Duration: 2.0, Energy Consumption: 3.0"
                + "\n---------------\n";
        String result = plist.buildString();
        assertEquals(expectedResult, result);
    }

    @Test
     void testBuildProgramListString2() {
        ProgramList plist = new ProgramList();
        String expectedResult = "This device has no programs\n";
        String result = plist.buildString();
        assertEquals(expectedResult, result);
    }


    @Test
     void seeIfAddProgram() {
        Program program = new Program("program", 2, 3);
        ProgramList list = new ProgramList();
        boolean result = list.addProgram(program);
        assertTrue(result);
    }

    @Test
     void seeIfAddProgram2() {
        Program program = new Program("program", 2, 3);
        ProgramList list = new ProgramList();
        list.addProgram(program);
        boolean result = list.addProgram(program);
        assertFalse(result);
    }

    @Test
     void seeIfRemoveProgram() {
        Program program = new Program("program", 2, 3);
        ProgramList list = new ProgramList();
        boolean result = list.removeProgram(program);
        assertFalse(result);
    }

    @Test
     void seeIfRemoveProgram2() {
        Program program = new Program("program", 2, 3);
        ProgramList list = new ProgramList();
        list.addProgram(program);
        boolean result = list.removeProgram(program);
        assertTrue(result);
    }
}
