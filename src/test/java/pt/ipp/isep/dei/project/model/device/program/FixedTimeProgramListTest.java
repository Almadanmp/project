package pt.ipp.isep.dei.project.model.device.program;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class FixedTimeProgramListTest {

    @Test
     void testBuildProgramListString() {
        FixedTimeProgram program = new FixedTimeProgram("program", 2, 3);
        ProgramList plist = new ProgramList();
        plist.addProgram(program);
        String expectedResult = "---------------\n" + "\n0) FixedTimeProgram Name: program, Duration: 2.0, Energy Consumption: 3.0"
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
        FixedTimeProgram program = new FixedTimeProgram("program", 2, 3);
        ProgramList list = new ProgramList();
        boolean result = list.addProgram(program);
        assertTrue(result);
    }

    @Test
     void seeIfAddProgram2() {
        FixedTimeProgram program = new FixedTimeProgram("program", 2, 3);
        ProgramList list = new ProgramList();
        list.addProgram(program);
        boolean result = list.addProgram(program);
        assertFalse(result);
    }

    @Test
     void seeIfRemoveProgram() {
        FixedTimeProgram program = new FixedTimeProgram("program", 2, 3);
        ProgramList list = new ProgramList();
        boolean result = list.removeProgram(program);
        assertFalse(result);
    }

    @Test
     void seeIfRemoveProgram2() {
        FixedTimeProgram program = new FixedTimeProgram("program", 2, 3);
        ProgramList list = new ProgramList();
        list.addProgram(program);
        boolean result = list.removeProgram(program);
        assertTrue(result);
    }
}
