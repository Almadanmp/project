package pt.ipp.isep.dei.project.model.device.program;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.TypeSensor;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * VariableTimeProgram test class.
 */
class VariableProgramTest {

    // Common artifacts for testing in this class.
    private VariableTimeProgram program;
    private VariableTimeProgram validVariableTimeProgram; // A variable time program named "Medium Power" running at 1200 Watt.
    private VariableTimeProgram validVariableTimeProgram2; // A variable time program named "Max Power" running at 1200 Watt.

    @BeforeEach
    void arrangeArtifacts() {
        program = new VariableTimeProgram("program1", 125);
        program.setAttributeValue(VariableTimeProgram.NOMINAL_POWER, 5D);
        validVariableTimeProgram = new VariableTimeProgram("Medium Power", 1.2);
        validVariableTimeProgram2 = new VariableTimeProgram("Mex Power", 1.8);
    }

    @Test
    void seeIfSetProgramName() {
        program.setProgramName("programa2");
        String expectedResult = "programa2";
        String result = program.getProgramName();
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfTestSetDuration() {
        program.setNominalPower(4);
        double expectedResult = 4;
        double result = program.getNominalPower();
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfTestSetEnergyConsumption() {
        double expectedResult = 20.0;
        double result = program.getEnergyConsumption(4);
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfPrintRoomWorks() {
        String result = program.buildString();
        String expected = "- The FixedTimeProgram Name is program1, its Nominal Power is 5.0 kW.\n";
        assertEquals(expected, result);
    }

    @Test
    void getAttributeNamesTest() {
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add(VariableTimeProgram.NOMINAL_POWER);
        List<String> result = program.getAttributeNames();
        assertEquals(expectedResult, result);
    }

    @Test
    void getAttributeValuesTest() {
        double expectedResult = 5.0;
        Object result = program.getAttributeValue("Nominal Power");
        assertEquals(expectedResult, result);
    }


    @Test
    void getAttributeUnitTest() {
        String expectedResult = "kW";
        Object result = program.getAttributeUnit("Nominal Power");
        assertEquals(expectedResult, result);
    }

    @Test
    void getAttributeUnitTest2() {
        Object result = program.getAttributeUnit("programList");
        assertEquals(false, result);
        assertEquals(false, program.getAttributeUnit(""));
    }

    @Test
    void getAttributeValuesTest1() {
        int expectedResult = 0;
        Object result = program.getAttributeValue("Duration" + "Energy Consumption");
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfSetAttributeValueTestTrueWorks() {
        //Act
        boolean actualResult = program.setAttributeValue("Nominal Power", 12.0);
        //Assert
        assertTrue(actualResult);
    }

    @Test
    void setAttributeValueTestDefault() {
        program.setAttributeValue("capacity", 5.0);
        Object result = program.getAttributeValue("lisbon");
        assertEquals(0, result);
    }


    @Test
    void setAttributeValueTestFalse() {
        boolean result = program.setAttributeValue("lisboa", 5);
        assertFalse(result);
    }

    @Test
    void setAttributeValueTestFalseAgain() {
        Object result = program.setAttributeValue(VariableTimeProgram.NOMINAL_POWER, 5);
        assertEquals(false, result);
    }

    @Test
    void setAttributeValueTestTrue() {
        Object result = program.setAttributeValue(VariableTimeProgram.NOMINAL_POWER, 5.0);
        assertEquals(true, result);
    }

    @Test
    void setAttributeValueTestFalseCap() {
        Object result = program.setAttributeValue(VariableTimeProgram.NOMINAL_POWER, "sjfhbfhfh");
        assertEquals(false, result);
    }


    @Test
    void testGetAttributeCoveringAllCases() {
        // original strings:
        assertEquals(5.0, program.getAttributeValue(VariableTimeProgram.NOMINAL_POWER));

        // same hash codes, but different strings:
        assertEquals(0, program.getAttributeValue("\0Capacity"));

        // distinct hash code to cover default cases of switches
        assertEquals(0, program.getAttributeValue(""));
    }


    @Test
    void seeIfGetAttributeUnitWorksInAllCases() {
        //Arrange
        String attributeKW = "kW";
        // original strings:
        assertEquals(attributeKW, program.getAttributeUnit(VariableTimeProgram.NOMINAL_POWER));
        // same hash codes, but different strings:
        assertEquals(false, program.getAttributeUnit("notNominalPower"));
        // distinct hash code to cover default cases of switches
        assertEquals(false, program.getAttributeUnit(""));
    }

    @Test
    public void hashCodeDummyTest() {
        //Arrange
        int expectedResult = 1;

        // Act
        int actualResult = validVariableTimeProgram.hashCode();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfEqualsProgramObject() {
        //Arrange
        Program variableProgram = new VariableTimeProgram("Program", 1.2);
        variableProgram.setProgramName("Medium Power");

        //Assert
        assertTrue(variableProgram.equals(validVariableTimeProgram));
    }

    @Test
    void seeIfEqualsFailsProgramObject() {
        //Arrange
        Program variableProgram = new VariableTimeProgram("Program", 1.2);
        variableProgram.setProgramName("Medium Power 2");

        //Assert
        assertFalse(variableProgram.equals(validVariableTimeProgram));
    }

    @Test
    void seeIfEqualsFailsNullObject() {
        //Arrange
        Object obj = null;
        VariableTimeProgram micro = null;

        // Act
        //Assert
        assertFalse(validVariableTimeProgram.equals(obj));
        assertFalse(validVariableTimeProgram.equals(micro));
    }

    @Test
    void seeIfEqualsFailsDifferentObject() {
        //Arrange
        // Act
        //Assert
        assertFalse(validVariableTimeProgram.equals(new TypeSensor("Rain", "mm")));
    }

    @Test
    void seeIfEqualsWorks() {
        //Arrange
        // Act
        //Assert
        assertTrue(validVariableTimeProgram.equals(validVariableTimeProgram));
    }
}