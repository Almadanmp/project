package pt.ipp.isep.dei.project.model.device.devicespecs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.device.program.FixedTimeProgram;
import pt.ipp.isep.dei.project.model.device.program.ProgramList;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * DishwasherSpec tests class.
 */

class DishwasherSpecTest {
    private DishwasherSpec dishwasherSpec;

    @BeforeEach
    void arrangeArtifacts(){
        dishwasherSpec = new DishwasherSpec();
    }

    @Test
    void seeIfGetAttributeNames() {
        // Arrange

        FixedTimeProgram program1 = new FixedTimeProgram("program", 2, 2);
        ProgramList listProgram = new ProgramList();
        listProgram.add(program1);
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add(DishwasherSpec.DW_CAPACITY);

        // Act

        List<String> result = dishwasherSpec.getAttributeNames();

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAttributeValuesCapacity() {
        // Arrange

        dishwasherSpec.setAttributeValue(DishwasherSpec.DW_CAPACITY, 1D);
        double expectedResult = 1.0;

        // Act

        Object result = dishwasherSpec.getAttributeValue("Capacity");

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAttributeValuesCapacityFails() {
        // Arrange

        dishwasherSpec.setAttributeValue(DishwasherSpec.DW_CAPACITY, 1D);
        double expectedResult = 1.0;

        // Act

        Object result = dishwasherSpec.getAttributeValue("Capacity");

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAttributeValuesNonExistent() {
        // Arrange

        dishwasherSpec.setAttributeValue(DishwasherSpec.DW_CAPACITY, 1D);

        // Act

        int expectedResult = 0;
        Object actualResult = dishwasherSpec.getAttributeValue("Anything");

        //Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void setAttributeValueFalse() {
        // Arrange

        dishwasherSpec.setAttributeValue(DishwasherSpec.DW_CAPACITY, 1D);

        // Act

        boolean result = dishwasherSpec.setAttributeValue("EnergyGrid", 12);

        // Assert

        assertFalse(result);
    }

    @Test
    void seeIfSetAttributeValue() {
        // Arrange

        dishwasherSpec.setAttributeValue(DishwasherSpec.DW_CAPACITY, 1D);

        //Act

        boolean actualResult = dishwasherSpec.setAttributeValue("Capacity", 12.0);

        //Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfGetAttributeUnit() {
        // Arrange

        dishwasherSpec.setAttributeValue(DishwasherSpec.DW_CAPACITY, 5D);
        String expectedResult = "Kg";

        // Act

        Object result = dishwasherSpec.getAttributeUnit("Capacity");

        // Assert

        assertEquals(expectedResult, result);
    }
    @Test
    void seeIfSetAttributeValueCapacityFalse() {
        // Arrange

        dishwasherSpec.setAttributeValue(DishwasherSpec.DW_CAPACITY, 1D);

        // Act

        Object result = dishwasherSpec.setAttributeValue("Capacity", 5);

        // Assert

        assertEquals(false, result);
    }

    @Test
    void seeIfSetAttributeValueCoveringAllCases() {
        // Arrange

        double attribute = 6.0;

        // Assert

        assertTrue(dishwasherSpec.setAttributeValue(DishwasherSpec.DW_CAPACITY, attribute)); // original strings:
        assertFalse(dishwasherSpec.setAttributeValue("notCAPACITY", attribute)); // same hash codes, but different strings:
        assertFalse(dishwasherSpec.setAttributeValue("", attribute)); // distinct hash code to cover default cases of switches
    }

    @Test
    void seeIfGetAttributeUnitAllCases() {
        // Arrange

        String attributeKg = "Kg";

        // Assert

        assertEquals(attributeKg, dishwasherSpec.getAttributeUnit(DishwasherSpec.DW_CAPACITY)); // original strings:
        assertEquals(0, dishwasherSpec.getAttributeUnit("notCAPACITY")); // same hash codes, but different strings:
        assertEquals(0, dishwasherSpec.getAttributeUnit("")); // distinct hash code to cover default cases of switches
    }

    @Test
    void setAttributeValueNull() {
        // Arrange

        dishwasherSpec.setAttributeValue(DishwasherSpec.DW_CAPACITY, 1D);

        // Act

        boolean result = dishwasherSpec.setAttributeValue(null, 3D);

        // Assert

        assertFalse(result);
    }
}