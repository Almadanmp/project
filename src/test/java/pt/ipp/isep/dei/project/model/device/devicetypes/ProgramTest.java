package pt.ipp.isep.dei.project.model.device.devicetypes;


import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.device.Program;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProgramTest {

    @Test
    public void seeIfSetProgramName() {
        Program program = new Program("programa1", 12, 12);
        program.getProgramName();
        program.setProgramName("programa2");
        String expectedResult = "programa2";
        String result = program.getProgramName();
        assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfTestSetDuration() {
        Program program = new Program("programa1", 12, 12);
        program.getDuration();
        program.setDuration(4);
        double expectedResult = 4;
        double result = program.getDuration();
        assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfTestSetEnergyConsumption() {
        Program program = new Program();
        program.getEnergyConsumption();
        program.setEnergyConsumption(4);
        double expectedResult = 4;
        double result = program.getEnergyConsumption();
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfPrintRoomWorks() {
        Program program = new Program("programa1", 12, 12);
        String result = program.buildProgramString();
        String expected = "- The Program Name is programa1, its Duration is 12.0 hours and its" +
                " Energy Consumption is 12.0.\n";
        assertEquals(expected, result);
    }


}
