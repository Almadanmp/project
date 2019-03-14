package pt.ipp.isep.dei.project.model.device.program;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.RoomList;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * VariableTimeProgram test class.
 */
class VariableProgramTest {

    // Common artifacts for testing in this class.

    private VariableTimeProgram validProgramOne; // A variable time validProgramOne named "ProgramOne" running at 1200 Watt.
    private VariableTimeProgram validVariableTimeProgram; // A variable time validProgramOne named "Medium Power" running at 1200 Watt.

    @BeforeEach
    void arrangeArtifacts() {
        validProgramOne = new VariableTimeProgram("program1", 125);
        validVariableTimeProgram = new VariableTimeProgram("Medium Power", 1.2);
    }

    @Test
    void seeIfGetSetProgramNameWorks() {
        // Arrange

        String expectedResult = "programTwo";

        // Act

        validProgramOne.setProgramName("programTwo");
        String result = validProgramOne.getProgramName();

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetSetNominalPowerWorks() {
        // Arrange

        validProgramOne.setNominalPower(4);
        double expectedResult = 4;

        // Act

        double result = validProgramOne.getNominalPower();

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetEnergyConsumptionWorks() {
        // Arrange

        double expectedResult = 500.0;

        // Act

        double result = validProgramOne.getEnergyConsumption(4);

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfBuildStringWorks() {
        // Arrange

        String expected = "- The FixedTimeProgram Name is program1, its Nominal Power is 125.0 kW.\n";

        // Act

        String result = validProgramOne.buildString();

        // Assert

        assertEquals(expected, result);
    }

    @Test
    void seeIfGetAttributeNamesWorks() {
        // Arrange

        List<String> expectedResult = new ArrayList<>();
        expectedResult.add(VariableTimeProgram.NOMINAL_POWER);

        // Act

        List<String> result = validProgramOne.getAttributeNames();

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfSetAttributeValueWorks() {
        // Act

        boolean actualResult = validProgramOne.setAttributeValue("Nominal Power", 12.0);

        // Assert

        assertTrue(actualResult);
    }


    @Test
    void seeIfSetAttributeValueWorksWrongName() {
        // Act

        boolean result = validProgramOne.setAttributeValue("Lisbbon", 5);

        // Assert

        assertFalse(result);
    }

    @Test
    void seeIfSetAttributeValueWorksWrongNameAndNotDouble() {
        // Act

        boolean result = validProgramOne.setAttributeValue("Lisbbon", "kjsjjd");

        // Assert

        assertFalse(result);
    }

    @Test
    void seeIfSetAttributeValueWorksNotDouble() {
        // Act

        Object result = validProgramOne.setAttributeValue(VariableTimeProgram.NOMINAL_POWER, 5);

        // Assert

        assertEquals(false, result);
    }


    @Test
    void seeIfGetAttributeValueWorks() {
        // Correct attribute names.

        assertEquals(125D, validProgramOne.getAttributeValue(VariableTimeProgram.NOMINAL_POWER));

        // Wrong attribute names.

        assertEquals(0, validProgramOne.getAttributeValue("\0Capacity"));

        // Empty attribute names.

        assertEquals(0, validProgramOne.getAttributeValue(""));
    }


    @Test
    void seeIfGetAttributeUnitWorks() {
        // Arrange

        String expectedResult = "kW";

        // Correct attribute names.

        assertEquals(expectedResult, validProgramOne.getAttributeUnit(VariableTimeProgram.NOMINAL_POWER));

        // Wrong attribute names.

        assertEquals(false, validProgramOne.getAttributeUnit("notNominalPower"));

        // Empty attribute names.

        assertEquals(false, validProgramOne.getAttributeUnit(""));
    }

    @Test
    void seeIfEqualsWorksTrue() {
        // Arrange

        Program var = new VariableTimeProgram("Program", 30);
        var.setProgramName("Medium Power");

        //Assert

        assertEquals(var, validVariableTimeProgram);
    }

    @Test
    void seeIfEqualsWorksFalse() {
        // Arrange

        Program fixedProgram = new FixedTimeProgram("Program", 30, 1.2);
        fixedProgram.setProgramName("Medium Power 2");

        // Assert

        assertNotEquals(fixedProgram, validVariableTimeProgram);
    }

    @Test
    void seeIfEqualsWorksNull() {
        assertNotEquals(validVariableTimeProgram, null);
    }

    @Test
    void seeIfEqualsWorksNotAnInstance() {
        assertNotEquals(validVariableTimeProgram, new RoomList());
    }

    @Test
    void seeIfEqualsWorksOnItself() {
        assertEquals(validVariableTimeProgram, validVariableTimeProgram);
    }
    @Test
    void hashCodeDummyTest() {
        //Arrange
        int expectedResult = 1;

        // Act
        int actualResult = validVariableTimeProgram.hashCode();

        //Assert
        assertEquals(expectedResult, actualResult);
    }
}