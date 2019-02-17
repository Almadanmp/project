package pt.ipp.isep.dei.project.model.device.devicespecs;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.device.programs.Program;
import pt.ipp.isep.dei.project.model.device.programs.ProgramList;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * DishwasherSpec tests class.
 */

class DishwasherSpec2Test {

    @Test
    void seeIfGetAttributeNamesTestWorks() {
        Program program1 = new Program("programa", 2, 2);
        ProgramList listProgram = new ProgramList();
        listProgram.addProgram(program1);
        DishwasherSpec2 dishwasherSpec = new DishwasherSpec2();
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add(DishwasherSpec.CAPACITY);
        List<String> result = dishwasherSpec.getAttributeNames();
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAttributeValuesTestCapacityWorks() {
        DishwasherSpec2 dishwasherSpec = new DishwasherSpec2();
        dishwasherSpec.setAttributeValue(DishwasherSpec.CAPACITY, 1D);
        Double expectedResult = 1.0;
        Object result = dishwasherSpec.getAttributeValue("Capacity");
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAttributeValuesTestCapacityWorksFalse() {
        DishwasherSpec2 dishwasherSpec = new DishwasherSpec2();
        dishwasherSpec.setAttributeValue(DishwasherSpec.CAPACITY, 1D);
        Double expectedResult = 1.0;
        Object result = dishwasherSpec.getAttributeValue("Capacity");
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAttributeValuesTestReturn0Works() {
        //Arrange
        DishwasherSpec2 dishwasherSpec = new DishwasherSpec2();
        dishwasherSpec.setAttributeValue(DishwasherSpec.CAPACITY, 1D);
        //Act
        int expectedResult = 0;
        Object actualResult = dishwasherSpec.getAttributeValue("cenas");
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void setAttributeValueTest() {
        DishwasherSpec2 dishwasherSpec = new DishwasherSpec2();
        dishwasherSpec.setAttributeValue(DishwasherSpec.CAPACITY, 1D);
        dishwasherSpec.setAttributeValue(DishwasherSpec.NOMINAL_POWER, 1D);
        boolean result = dishwasherSpec.setAttributeValue("lisboa", 12);
        assertEquals(false, result);
    }

    @Test
    void seeIfSetAttributeValueTestTrueWorks() {
        //Arrange
        DishwasherSpec2 dishwasherSpec = new DishwasherSpec2();
        dishwasherSpec.setAttributeValue(DishwasherSpec.CAPACITY, 1D);
        //Act
        boolean actualResult = dishwasherSpec.setAttributeValue("Capacity", 12.0);
        //Assert
        assertTrue(actualResult);
    }

    @Test
    void getAttributeUnitTest() {
        DishwasherSpec2 dishwasherSpec = new DishwasherSpec2();
        dishwasherSpec.setAttributeValue(DishwasherSpec.CAPACITY, 5D);
        String expectedResult = "Kg";
        Object result = dishwasherSpec.getAttributeUnit("Capacity");
        assertEquals(expectedResult, result);
    }

    @Test
    void setAttributeValueTestCapacity() {
        DishwasherSpec2 dishwasherSpec = new DishwasherSpec2();
        dishwasherSpec.setAttributeValue(DishwasherSpec.CAPACITY, 1D);
        dishwasherSpec.setAttributeValue("Capacity", 5.0);
        Object result = dishwasherSpec.getAttributeValue("Capacity");
        assertEquals(5.0, result);
    }

    @Test
    void setAttributeValueTestDefault() {
        DishwasherSpec2 dishwasherSpec = new DishwasherSpec2();
        dishwasherSpec.setAttributeValue(DishwasherSpec.CAPACITY, 1D);
        dishwasherSpec.setAttributeValue("Capacity", 5.0);
        Object result = dishwasherSpec.getAttributeValue("lisbon");
        assertEquals(0, result);
    }

    @Test
    void setAttributeValueTestFalseAgain() {
        DishwasherSpec2 dishwasherSpec = new DishwasherSpec2();
        dishwasherSpec.setAttributeValue(DishwasherSpec.CAPACITY, 1D);
        Object result = dishwasherSpec.setAttributeValue("Capacity", 5);
        assertEquals(false, result);
    }


    @Test
    void testSetAttributeValueCoveringAllCases() {
        //Arrange
        DishwasherSpec2 dishwasherSpec = new DishwasherSpec2();
        Double attribute = 6.0;
        // original strings:
        assertTrue(dishwasherSpec.setAttributeValue(DishwasherSpec.CAPACITY, attribute));
        // same hash codes, but different strings:
        assertFalse(dishwasherSpec.setAttributeValue("notCAPACITY", attribute));
        // distinct hash code to cover default cases of switches
        assertFalse(dishwasherSpec.setAttributeValue("", attribute));
    }

    @Test
    void seeIfGetAttributeUnitWorksInAllCases() {
        //Arrange
        DishwasherSpec2 dishwasherSpec = new DishwasherSpec2();
        String attributeKg = "Kg";
        // original strings:
        assertEquals(attributeKg, dishwasherSpec.getAttributeUnit(DishwasherSpec.CAPACITY));
        // same hash codes, but different strings:
        assertEquals(0, dishwasherSpec.getAttributeUnit("notCAPACITY"));
        // distinct hash code to cover default cases of switches
        assertEquals(0, dishwasherSpec.getAttributeUnit(""));
    }

    @Test
    void setAttributeValueTestNull() {
        DishwasherSpec2 dishwasherSpec = new DishwasherSpec2();
        dishwasherSpec.setAttributeValue(DishwasherSpec.CAPACITY, 1D);
         boolean result = dishwasherSpec.setAttributeValue(null, 3D);
        assertEquals(false, result);
    }
}