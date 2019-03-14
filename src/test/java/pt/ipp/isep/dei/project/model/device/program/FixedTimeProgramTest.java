package pt.ipp.isep.dei.project.model.device.program;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.RoomList;
import pt.ipp.isep.dei.project.model.device.DeviceList;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * FixedTimeProgram test class.
 */
class FixedTimeProgramTest {

    // Common artifacts for testing in this class.
    private FixedTimeProgram validFixedTimeProgram;


    @BeforeEach
    void arrangeArtifacts() {
        validFixedTimeProgram = new FixedTimeProgram("Medium Power", 30D, 1.2D);
    }

    @Test
    void seeIfSetGetProgramNameWorks() {
        // Arrange

        validFixedTimeProgram.setProgramName("Program");
        String expectedResult = "Program";

        // Act

        String result = validFixedTimeProgram.getProgramName();

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetSetDurationWorks() {
        // Arrange

        validFixedTimeProgram.setDuration(4);
        double expectedResult = 4;

        // Act

        double result = validFixedTimeProgram.getDuration();

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetSetEnergyConsumptionWorks() {
        // Arrange

        validFixedTimeProgram.setEnergyConsumption(4);
        double expectedResult = 4;

        // Act

        double result = validFixedTimeProgram.getEnergyConsumption();

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfBuildStringWorks() {
        // Arrange

        String expected = "- The FixedTimeProgram Name is Medium Power, its Duration is 30.0 hours and its Energy Consumption is 1.2.\n";

        // Act

        String result = validFixedTimeProgram.buildString();

        // Assert

        assertEquals(expected, result);
    }

    @Test
    void seeIfGetAttributeNamesWorks() {
        // Arrange

        List<String> expectedResult = new ArrayList<>();
        expectedResult.add(FixedTimeProgram.DURATION);
        expectedResult.add(FixedTimeProgram.ENERGY_CONSUMPTION);

        // Act

        List<String> result = validFixedTimeProgram.getAttributeNames();

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAttributeValuesWorksDuration() {
        // Arrange

        validFixedTimeProgram.setAttributeValue(FixedTimeProgram.DURATION, 5D);
        double expectedResult = 5.0;

        // Act

        Object result = validFixedTimeProgram.getAttributeValue("Duration");

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAttributeValuesWorksConsumption() {
        // Arrange

        validFixedTimeProgram.setAttributeValue(FixedTimeProgram.ENERGY_CONSUMPTION, 5D);
        double expectedResult = 5.0;

        // Act

        Object result = validFixedTimeProgram.getAttributeValue("Energy Consumption");

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAttributeUnitWorksDuration() {
        // Arrange

        validFixedTimeProgram.setAttributeValue(FixedTimeProgram.DURATION, 5D);
        String expectedResult = "min";

        // Act

        Object result = validFixedTimeProgram.getAttributeUnit("Duration");

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAttributeUnitWorksEmptyAndWrongName() {
        // Arrange

        Object result = validFixedTimeProgram.getAttributeUnit("programList");

        // Assert

        assertEquals(false, result);
        assertEquals(false, validFixedTimeProgram.getAttributeUnit(""));
    }

    @Test
    void seeIfGetAttributeUnitWorksConsumption() {
        // Act

        Object result = validFixedTimeProgram.getAttributeUnit("Energy Consumption");

        // Assert

        assertEquals("kWh", result);
    }

    @Test
    void seeIfSetAttributeValueWorksDuration() {
        // Act

        boolean actualResult = validFixedTimeProgram.setAttributeValue("Duration", 12.0);

        // Assert

        assertTrue(actualResult);
    }


    @Test
    void seeIfGetAttributeValueWorksWrongName() {
        // Act

        Object result = validFixedTimeProgram.getAttributeValue("Lisbon");

        // Assert

        assertEquals(0, result);
    }

    @Test
    void seeIfSetGetAttributeValueWorksConsumption() {
        // Arrange

        Object expectedResult = 1.2;

        // Act

        Object result = validFixedTimeProgram.getAttributeValue(FixedTimeProgram.ENERGY_CONSUMPTION);

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfSetAttributeValueWorksWrongName() {
        // Act

        boolean result = validFixedTimeProgram.setAttributeValue("Lisbon", 5);

        // Assert

        assertFalse(result);
    }

    @Test
    void seeIfSetAttributeValueWorksNotDouble() {
        // Act

        Object result = validFixedTimeProgram.setAttributeValue(FixedTimeProgram.DURATION, 5);

        // Assert

        assertEquals(false, result);
    }

    @Test
    void setIfSetAttributeValueWorks() {
        // Act

        Object result = validFixedTimeProgram.setAttributeValue(FixedTimeProgram.ENERGY_CONSUMPTION, 5.0);

        // Assert

        assertEquals(true, result);
    }


    @Test
    void seeIfGetAttributeValuesWorks() {
        //Arrange

        validFixedTimeProgram.setAttributeValue(FixedTimeProgram.DURATION, 5D);
        validFixedTimeProgram.setAttributeValue(FixedTimeProgram.ENERGY_CONSUMPTION, 5D);

        validFixedTimeProgram.setAttributeValue("Duration", 5D);
        validFixedTimeProgram.setAttributeValue("Energy Consumption", 5D);

        // Correct attribute names.

        assertEquals(5.0, validFixedTimeProgram.getAttributeValue(FixedTimeProgram.DURATION));
        assertEquals(5.0, validFixedTimeProgram.getAttributeValue(FixedTimeProgram.ENERGY_CONSUMPTION));
        assertEquals(5.0, validFixedTimeProgram.getAttributeValue("Duration"));
        assertEquals(5.0, validFixedTimeProgram.getAttributeValue("Energy Consumption"));

        // Wrong attribute names.

        assertEquals(0, validFixedTimeProgram.getAttributeValue("\0Capacity"));
        assertEquals(0, validFixedTimeProgram.getAttributeValue("\0nominal power"));

        // Empty attribute names.

        assertEquals(0, validFixedTimeProgram.getAttributeValue(""));
    }

    @Test
    void seeIfSetAttributeValuesWorks() {
        // Correct attribute names.

        assertTrue(validFixedTimeProgram.setAttributeValue(FixedTimeProgram.ENERGY_CONSUMPTION, 6D));
        assertTrue(validFixedTimeProgram.setAttributeValue(FixedTimeProgram.DURATION, 7D));

        // Wrong attribute names.

        assertFalse(validFixedTimeProgram.setAttributeValue("notNominalPower", 6D));
        assertFalse(validFixedTimeProgram.setAttributeValue("notCapacity", 7D));

        // Empty attribute names.

        assertFalse(validFixedTimeProgram.setAttributeValue("", 6D));
    }

    @Test
    void seeIfGetAttributeUnitWorks() {
        // Arrange

        String attributeKg = "min";
        String attributeKW = "kWh";

        // Correct attribute names

        assertEquals(attributeKW, validFixedTimeProgram.getAttributeUnit(FixedTimeProgram.ENERGY_CONSUMPTION));
        assertEquals(attributeKg, validFixedTimeProgram.getAttributeUnit(FixedTimeProgram.DURATION));
        assertEquals(attributeKW, validFixedTimeProgram.getAttributeUnit("Energy Consumption"));
        assertEquals(attributeKg, validFixedTimeProgram.getAttributeUnit("Duration"));

        // Incorrect attribute names.

        assertEquals(false, validFixedTimeProgram.getAttributeUnit("notNominalPower"));
        assertEquals(false, validFixedTimeProgram.getAttributeUnit("notCapacity"));

        // Empty attribute names.

        assertEquals(false, validFixedTimeProgram.getAttributeUnit(""));
    }
    @Test
    void seeIfSetAttributeValueWorksFalseNotDouble() {
        // Act

        Object result = validFixedTimeProgram.setAttributeValue(FixedTimeProgram.ENERGY_CONSUMPTION, 5);

        // Assert

        assertEquals(false, result);
    }
    @Test
    void seeIfSetAttributeValuesWorksJustNames() {
        // Happy Cases

        assertTrue(validFixedTimeProgram.setAttributeValue("Duration", 6D));
        assertTrue(validFixedTimeProgram.setAttributeValue("Energy Consumption", 6D));

        // Wrong Attribute Names

        assertFalse(validFixedTimeProgram.setAttributeValue("\0Duration", 5D));
        assertFalse(validFixedTimeProgram.setAttributeValue("\0Energy Consumption", 5D));

        // Empty Attribute Name

        assertFalse(validFixedTimeProgram.setAttributeValue("", 6D));
    }
    @Test
    void seeIfGetAttributeValueUnitWorks() {
        // Happy Cases

        validFixedTimeProgram.setAttributeValue(FixedTimeProgram.DURATION, 5D);
        validFixedTimeProgram.setAttributeValue(FixedTimeProgram.ENERGY_CONSUMPTION, 5D);

        validFixedTimeProgram.setAttributeValue("Duration", 5D);
        validFixedTimeProgram.setAttributeValue("Energy Consumption", 5D);

        assertEquals(5.0, validFixedTimeProgram.getAttributeValue(FixedTimeProgram.DURATION));
        assertEquals(5.0, validFixedTimeProgram.getAttributeValue(FixedTimeProgram.ENERGY_CONSUMPTION));

        assertEquals(5.0, validFixedTimeProgram.getAttributeValue("Duration"));
        assertEquals(5.0, validFixedTimeProgram.getAttributeValue("Energy Consumption"));

        assertEquals("min", validFixedTimeProgram.getAttributeUnit("Duration"));
        assertEquals("kWh", validFixedTimeProgram.getAttributeUnit("Energy Consumption"));

        // Wrong attribute Names.

        assertEquals(0, validFixedTimeProgram.getAttributeValue("\0Duration"));
        assertEquals(0, validFixedTimeProgram.getAttributeValue("\0Energy Consumption"));

        assertEquals(false, validFixedTimeProgram.getAttributeUnit("\0Duration"));
        assertEquals(false, validFixedTimeProgram.getAttributeUnit("\0Energy Consumption"));

        // Empty attribute name.

        assertEquals(0, validFixedTimeProgram.getAttributeValue(" "));
        assertEquals(false, validFixedTimeProgram.getAttributeUnit(" "));
        assertEquals(0, validFixedTimeProgram.getAttributeValue(""));
        assertEquals(false, validFixedTimeProgram.getAttributeUnit(""));

    }

    @Test
    void seeIfEqualsWorksTrue() {
        // Arrange

        Program fixedProgram = new FixedTimeProgram("Program", 30, 1.2);
        fixedProgram.setProgramName("Medium Power");

        //Assert

        assertEquals(fixedProgram, validFixedTimeProgram);
    }

    @Test
    void seeIfEqualsWorksFalse() {
        // Arrange

        Program fixedProgram = new FixedTimeProgram("Program", 30, 1.2);
        fixedProgram.setProgramName("Medium Power 2");

        // Assert

        assertNotEquals(fixedProgram, validFixedTimeProgram);
    }

    @Test
    void seeIfEqualsWorksNull() {
       assertNotEquals(validFixedTimeProgram, null);
    }

    @Test
    void seeIfEqualsWorksNotAnInstance() {
        assertNotEquals(validFixedTimeProgram, new RoomList());
    }

    @Test
    void seeIfEqualsWorksOnItself() {
        assertEquals(validFixedTimeProgram, validFixedTimeProgram);
    }

    @Test
    void hashCodeDummyTest() {
        // Arrange

        int expectedResult = 1;

        // Act

        int actualResult = validFixedTimeProgram.hashCode();

        // Assert

        assertEquals(expectedResult, actualResult);
    }
}
