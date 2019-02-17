package pt.ipp.isep.dei.project.model.device.program;


import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        Program program = new Program("Eco", 130, 23);
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

    @Test
    void getAttributeNamesTest() {
        Program program = new Program("program",2,2);
        program.setAttributeValue(Program.DURATION, 5D);
        program.setAttributeValue(Program.ENERGY_CONSUMPTION, 5D);
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add(Program.DURATION);
        expectedResult.add(Program.ENERGY_CONSUMPTION);
        List<String> result = program.getAttributeNames();
        assertEquals(expectedResult, result);
    }

    @Test
    void getAttributeValuesTest() {
        Program program = new Program("program",2,2);
        program.setAttributeValue(Program.DURATION, 5D);
        double expectedResult = 5.0;
        Object result = program.getAttributeValue("Duration");
        assertEquals(expectedResult, result);
    } @Test
    void getAttributeValuesTestNp() {
        Program program = new Program("program",2,2);
        program.setAttributeValue(Program.ENERGY_CONSUMPTION, 5D);
        double expectedResult = 5.0;
        Object result = program.getAttributeValue("Energy Consumption");
        assertEquals(expectedResult, result);
    }

    @Test
    void getAttributeUnitTest() {
        Program program = new Program("program",2,2);
        program.setAttributeValue(Program.DURATION, 5D);
        String expectedResult = "min";
        Object result = program.getAttributeUnit("Duration");
        assertEquals(expectedResult, result);
    }

    @Test
    void getAttributeUnitTest2() {
        Program program = new Program("program",2,2);
        program.setAttributeValue(Program.DURATION, 5D);
        Object result = program.getAttributeUnit("programList");
        assertEquals(false, result);
        assertEquals(false, program.getAttributeUnit(""));
    }

    @Test
    void getAttributeUnitTest3() {
        Program program = new Program("program",2,2);
        program.setAttributeValue(Program.DURATION, 5D);
        Object result = program.getAttributeUnit("Duration");
        assertEquals("min", result);
    }

    @Test
    void getAttributeUnitTest4() {
        Program program = new Program("program",2,2);
        program.setAttributeValue(Program.ENERGY_CONSUMPTION, 5D);
        Object result = program.getAttributeUnit("Energy Consumption");
        assertEquals("kWh", result);
    }

    @Test
    void getAttributeValuesTest1() {
        Program program = new Program("program",2,2);
        program.setAttributeValue(Program.DURATION, 5D);
        int expectedResult = 0;
        Object result = program.getAttributeValue("Duration" + "Energy Consumption");
        assertEquals(expectedResult, result);
    }

    @Test
    void getAttributeValuesWithCapacityEmptyTest() {
        Program program = new Program("program",2,2);
        program.setAttributeValue(Program.ENERGY_CONSUMPTION, 34D);
        double expectedResult = 34.0;
        Object result = program.getAttributeValue("Energy Consumption");
        assertEquals(expectedResult, result);
    }

    @Test
    void setAttributeValueTestCapacity() {
        Program program = new Program("program",2,2);
        program.setAttributeValue("Duration", 5.0D);
        Object result = program.getAttributeValue("Duration");
        assertEquals(5.0, result);
    }

    @Test
    void setAttributeValueTestCapacity2() {
        Program program = new Program("program",2,2);
        program.setAttributeValue(Program.DURATION, 23D);
        Object result = program.getAttributeValue("Duration");
        assertEquals(23.0, result);
    }

    @Test
    void seeIfSetAttributeValueTestTrueWorks() {
        //Arrange
        Program program = new Program("program",2,2);
        program.setAttributeValue(Program.DURATION, 5D);
        //Act
        boolean actualResult = program.setAttributeValue("Duration", 12.0);
        //Assert
        assertEquals(true, actualResult);
    }



    @Test
    void setAttributeValueTestDefault() {
        Program program = new Program("program",2,2);
        program.setAttributeValue("capacity", 5.0);
        Object result = program.getAttributeValue("lisbon");
        assertEquals(0, result);
    }

    @Test
    void setAttributeValueTestDefault3() {
        Program program = new Program("program",2,2);
        program.setAttributeValue("Energy Consumption", 5.0);
        program.setAttributeValue("Duration", 6.0);
        Object result = program.getAttributeValue("Duration");
        Object expectedResult = 6.0;
        assertEquals(expectedResult, result);
    }
    @Test
    void setAttributeValueTestDefault4() {
        Program program = new Program("program",2,2);
        program.setAttributeValue(Program.ENERGY_CONSUMPTION, 5.0);
        program.setAttributeValue(Program.DURATION, 6.0);
        Object result = program.getAttributeValue(Program.ENERGY_CONSUMPTION);
        Object expectedResult = 5.0;
        assertEquals(expectedResult, result);
    }

    @Test
    void setAttributeValueTestFalse() {
        Program program = new Program("program",2,2);
        boolean result = program.setAttributeValue("lisboa", 5);
        assertEquals(false, result);
    }

    @Test
    void setAttributeValueTestFalseAgain() {
        Program program = new Program("program",2,2);
        Object result = program.setAttributeValue(Program.DURATION, 5);
        assertEquals(false, result);
    }

    @Test
    void setAttributeValueTestFalseAgain2() {
        Program program = new Program("program",2,2);
        Object result = program.setAttributeValue(Program.ENERGY_CONSUMPTION, "sjfhbfhfh");
        assertEquals(false, result);
    }

    @Test
    void setAttributeValueTestTrue() {
        Program program = new Program("program",2,2);
        Object result = program.setAttributeValue(Program.ENERGY_CONSUMPTION, 5.0);
        assertEquals(true, result);
    }

    @Test
    void setAttributeValueTestFalseCap() {
        Program program = new Program("program",2,2);
        Object result = program.setAttributeValue(Program.DURATION, "sjfhbfhfh");
        assertEquals(false, result);
    }


    @Test
    void testGetAttributeCoveringAllCases() {
        //Arrange
        Program program = new Program("program",2,2);
        program.setAttributeValue(Program.DURATION, 5D);
        program.setAttributeValue(Program.ENERGY_CONSUMPTION, 5D);
        // original strings:
        assertEquals(5.0, program.getAttributeValue(Program.DURATION));
        assertEquals(5.0, program.getAttributeValue(Program.ENERGY_CONSUMPTION));

        // same hash codes, but different strings:
        assertEquals(0, program.getAttributeValue("\0Capacity"));
        assertEquals(0, program.getAttributeValue("\0nominal power"));

        // distinct hash code to cover default cases of switches
        assertEquals(0, program.getAttributeValue(""));
    }

    @Test
    void testSetAttributeValueCoveringAllCases() {
        //Arrange
        Program program = new Program("program",2,2);
        Double attribute = 6.0;
        // original strings:
        assertTrue(program.setAttributeValue(Program.ENERGY_CONSUMPTION, attribute));
        assertTrue(program.setAttributeValue(Program.DURATION, attribute));
        // same hash codes, but different strings:
        assertFalse(program.setAttributeValue("notNominalPower", attribute));
        assertFalse(program.setAttributeValue("notCapacity", attribute));
        // distinct hash code to cover default cases of switches
        assertFalse(program.setAttributeValue("", attribute));
    }

    @Test
    void seeIfGetAttributeUnitWorksInAllCases() {
        //Arrange
        Program program = new Program("program",2,2);
        String attributeKg = "min";
        String attributeKW = "kWh";
        // original strings:
        assertEquals(attributeKW, program.getAttributeUnit(Program.ENERGY_CONSUMPTION));
        assertEquals(attributeKg, program.getAttributeUnit(Program.DURATION));
        // same hash codes, but different strings:
        assertEquals(false, program.getAttributeUnit("notNominalPower"));
        assertEquals(false, program.getAttributeUnit("notCapacity"));
        // distinct hash code to cover default cases of switches
        assertEquals(false, program.getAttributeUnit(""));
    }
}
