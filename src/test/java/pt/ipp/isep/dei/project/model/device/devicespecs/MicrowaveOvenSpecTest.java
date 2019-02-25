package pt.ipp.isep.dei.project.model.device.devicespecs;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.device.program.Program;
import pt.ipp.isep.dei.project.model.device.program.ProgramList;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MicrowaveOvenSpecTest {

    @Test
    void seeIfGetAttributeNamesTestWorks() {
        Program program1 = new Program("programa", 78);
        ProgramList listProgram = new ProgramList();
        listProgram.addProgram(program1);
        MicrowaveOvenSpec microwaveOvenSpec = new MicrowaveOvenSpec();
        List<String> expectedResult = new ArrayList<>();
        List<String> result = microwaveOvenSpec.getAttributeNames();
        assertEquals(expectedResult, result);
    }

    @Test
    void setAttributeValueTest() {
        MicrowaveOvenSpec microwaveOvenSpec = new MicrowaveOvenSpec();
        microwaveOvenSpec.setAttributeValue(DishwasherSpec.DW_CAPACITY, 1D);
        boolean result = microwaveOvenSpec.setAttributeValue("lisboa", 12);
        assertEquals(false, result);
    }

    @Test
    void getAttributeUnitTest() {
        MicrowaveOvenSpec microwaveOvenSpec = new MicrowaveOvenSpec();
        microwaveOvenSpec.setAttributeValue(DishwasherSpec.DW_CAPACITY, 5D);
        Object result = microwaveOvenSpec.getAttributeUnit("Capacity");
        assertEquals(false, result);
    }

    @Test
    void setAttributeValueTestCapacity() {
        MicrowaveOvenSpec microwaveOvenSpec = new MicrowaveOvenSpec();
        microwaveOvenSpec.setAttributeValue(DishwasherSpec.DW_CAPACITY, 1D);
        microwaveOvenSpec.setAttributeValue("Capacity", 5.0);
        Object result = microwaveOvenSpec.getAttributeValue("Capacity");
        assertEquals(0, result);
    }

}