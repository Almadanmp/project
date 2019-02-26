package pt.ipp.isep.dei.project.model.device;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.device.devicespecs.MicrowaveOvenSpec;
import pt.ipp.isep.dei.project.model.device.program.VariableProgram;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MicrowaveOvenTest {

    @Test
    void getProgramEnergyConsumptionTest() {
        MicrowaveOven d1 = new MicrowaveOven(new MicrowaveOvenSpec());
        d1.setName("Microwave Oven 3000");
        VariableProgram program1 = new VariableProgram("Program 1",70);
        double result = d1.getProgramConsumption(20,program1);
        double expectedResult = 1400;
        assertEquals(expectedResult, result);
    }

}