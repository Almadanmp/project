package pt.ipp.isep.dei.project.model.devicetypes;


import org.junit.jupiter.api.Test;


import static org.testng.Assert.*;

public class ProgramTest {

    @Test
    public void seeIfSetProgramName() {
        Program program = new Program("programa1", 12,12);
        program.getProgramName();
        program.setProgramName("programa2");
        String expectedResult = "programa2";
        String result = program.getProgramName();
        assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfTestSetDuration() {
        Program program = new Program("programa1", 12,12);
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

}