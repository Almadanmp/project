package pt.ipp.isep.dei.project.model.device.devicespecs;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.device.program.VariableTimeProgram;
import pt.ipp.isep.dei.project.model.device.program.VariableTimeProgramList;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class StoveSpecTest {

    @Test
    void seeIfGetAttributeNamesTestWorks() {
        VariableTimeProgram program1 = new VariableTimeProgram("Program 1", 78);
        VariableTimeProgramList listProgram = new VariableTimeProgramList();
        listProgram.addProgram(program1);
        StoveSpec stoveSpec = new StoveSpec();
        List<String> expectedResult = new ArrayList<>();
        List<String> result = stoveSpec.getAttributeNames();
        assertEquals(expectedResult, result);
    }

    @Test
    void setAttributeValueTest() {
        StoveSpec stoveSpec = new StoveSpec();
        stoveSpec.setAttributeValue(DishwasherSpec.DW_CAPACITY, 1D);
        boolean result = stoveSpec.setAttributeValue("Fast Heating", 100);
        assertEquals(false, result);
    }

    @Test
    void getAttributeUnitTest() {
        StoveSpec stoveSpec = new StoveSpec();
        stoveSpec.setAttributeValue(DishwasherSpec.DW_CAPACITY, 5D);
        Object result = stoveSpec.getAttributeUnit("Capacity");
        assertEquals(false, result);
    }

    @Test
    void setAttributeValueTestCapacity() {
        StoveSpec stoveSpec = new StoveSpec();
        stoveSpec.setAttributeValue(DishwasherSpec.DW_CAPACITY, 1D);
        stoveSpec.setAttributeValue("Capacity", 5.0);
        Object result = stoveSpec.getAttributeValue("Capacity");
        assertEquals(0, result);
    }

}