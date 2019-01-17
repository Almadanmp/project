package pt.ipp.isep.dei.project.model.Device.devicetypes;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Device.Program;
import pt.ipp.isep.dei.project.model.Device.ProgramList;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Dishwasher tests class.
 */

public class DishwasherTest {

    @Test
    public void seeIfGetTypeTest() {
        Dishwasher dishwasher = new Dishwasher();
        DeviceType expectedResult = DeviceType.DISHWASHER;
        DeviceType result = dishwasher.getType();
        assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfGetConsumptionTest() {
        Dishwasher dishwasher = new Dishwasher();
        double expectedResult = 0;
        double result = dishwasher.getConsumption();
        assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfGetCapacity() {
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
    public void getAttributeNamesTest() {
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
    public void getAttributeValuesTest() {
        Program program1 = new Program("programa",2,2);
        ProgramList listProgram= new ProgramList();
        listProgram.addProgram(program1);
        Dishwasher dishwasher = new Dishwasher(1.0,listProgram);
        Double expectedResult = 1.0;
        Object result = dishwasher.getAttributeValue("capacity");
        assertEquals(expectedResult, result);
    }

    @Test
    public void setAttributeValueTest() {
        Program program1 = new Program("programa",2,2);
        ProgramList listProgram= new ProgramList();
        listProgram.addProgram(program1);
        Dishwasher dishwasher = new Dishwasher(1,listProgram);
        boolean result = dishwasher.setAttributeValue("lisboa", 12);
        assertEquals(false, result);
    }


}
