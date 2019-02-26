package pt.ipp.isep.dei.project.model.device.devicespecs;

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

    @Test
    void seeIfGetAttributeNamesTestWorks() {
        FixedTimeProgram program1 = new FixedTimeProgram("programa", 2, 2);
        ProgramList listProgram = new ProgramList();
        listProgram.addProgram(program1);
        DishwasherSpec dishwasherSpec = new DishwasherSpec();
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add(DishwasherSpec.DW_CAPACITY);
        List<String> result = dishwasherSpec.getAttributeNames();
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAttributeValuesTestCapacityWorks() {
        DishwasherSpec dishwasherSpec = new DishwasherSpec();
        dishwasherSpec.setAttributeValue(DishwasherSpec.DW_CAPACITY, 1D);
        Double expectedResult = 1.0;
        Object result = dishwasherSpec.getAttributeValue("Capacity");
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAttributeValuesTestCapacityWorksFalse() {
        DishwasherSpec dishwasherSpec = new DishwasherSpec();
        dishwasherSpec.setAttributeValue(DishwasherSpec.DW_CAPACITY, 1D);
        Double expectedResult = 1.0;
        Object result = dishwasherSpec.getAttributeValue("Capacity");
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAttributeValuesTestReturn0Works() {
        //Arrange
        DishwasherSpec dishwasherSpec = new DishwasherSpec();
        dishwasherSpec.setAttributeValue(DishwasherSpec.DW_CAPACITY, 1D);
        //Act
        int expectedResult = 0;
        Object actualResult = dishwasherSpec.getAttributeValue("cenas");
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void setAttributeValueTest() {
        DishwasherSpec dishwasherSpec = new DishwasherSpec();
        dishwasherSpec.setAttributeValue(DishwasherSpec.DW_CAPACITY, 1D);
        boolean result = dishwasherSpec.setAttributeValue("lisboa", 12);
        assertEquals(false, result);
    }

    @Test
    void seeIfSetAttributeValueTestTrueWorks() {
        //Arrange
        DishwasherSpec dishwasherSpec = new DishwasherSpec();
        dishwasherSpec.setAttributeValue(DishwasherSpec.DW_CAPACITY, 1D);
        //Act
        boolean actualResult = dishwasherSpec.setAttributeValue("Capacity", 12.0);
        //Assert
        assertTrue(actualResult);
    }

    @Test
    void getAttributeUnitTest() {
        DishwasherSpec dishwasherSpec = new DishwasherSpec();
        dishwasherSpec.setAttributeValue(DishwasherSpec.DW_CAPACITY, 5D);
        String expectedResult = "Kg";
        Object result = dishwasherSpec.getAttributeUnit("Capacity");
        assertEquals(expectedResult, result);
    }

    @Test
    void setAttributeValueTestCapacity() {
        DishwasherSpec dishwasherSpec = new DishwasherSpec();
        dishwasherSpec.setAttributeValue(DishwasherSpec.DW_CAPACITY, 1D);
        dishwasherSpec.setAttributeValue("Capacity", 5.0);
        Object result = dishwasherSpec.getAttributeValue("Capacity");
        assertEquals(5.0, result);
    }

    @Test
    void setAttributeValueTestDefault() {
        DishwasherSpec dishwasherSpec = new DishwasherSpec();
        dishwasherSpec.setAttributeValue(DishwasherSpec.DW_CAPACITY, 1D);
        dishwasherSpec.setAttributeValue("Capacity", 5.0);
        Object result = dishwasherSpec.getAttributeValue("lisbon");
        assertEquals(0, result);
    }

    @Test
    void setAttributeValueTestFalseAgain() {
        DishwasherSpec dishwasherSpec = new DishwasherSpec();
        dishwasherSpec.setAttributeValue(DishwasherSpec.DW_CAPACITY, 1D);
        Object result = dishwasherSpec.setAttributeValue("Capacity", 5);
        assertEquals(false, result);
    }


    @Test
    void testSetAttributeValueCoveringAllCases() {
        //Arrange
        DishwasherSpec dishwasherSpec = new DishwasherSpec();
        Double attribute = 6.0;
        // original strings:
        assertTrue(dishwasherSpec.setAttributeValue(DishwasherSpec.DW_CAPACITY, attribute));
        // same hash codes, but different strings:
        assertFalse(dishwasherSpec.setAttributeValue("notCAPACITY", attribute));
        // distinct hash code to cover default cases of switches
        assertFalse(dishwasherSpec.setAttributeValue("", attribute));
    }

    @Test
    void seeIfGetAttributeUnitWorksInAllCases() {
        //Arrange
        DishwasherSpec dishwasherSpec = new DishwasherSpec();
        String attributeKg = "Kg";
        // original strings:
        assertEquals(attributeKg, dishwasherSpec.getAttributeUnit(DishwasherSpec.DW_CAPACITY));
        // same hash codes, but different strings:
        assertEquals(0, dishwasherSpec.getAttributeUnit("notCAPACITY"));
        // distinct hash code to cover default cases of switches
        assertEquals(0, dishwasherSpec.getAttributeUnit(""));
    }

    @Test
    void setAttributeValueTestNull() {
        DishwasherSpec dishwasherSpec = new DishwasherSpec();
        dishwasherSpec.setAttributeValue(DishwasherSpec.DW_CAPACITY, 1D);
        boolean result = dishwasherSpec.setAttributeValue(null, 3D);
        assertEquals(false, result);
    }
}