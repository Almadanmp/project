package pt.ipp.isep.dei.project.model.device.devicespecs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.device.Freezer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.testng.Assert.*;

class FreezerSpecTest {
    private Freezer validFreezer = new Freezer(new FreezerSpec());

    @BeforeEach
    void arrangeArtifacts() {
        validFreezer.setName("Freezer");
        validFreezer.setNominalPower(30);
        validFreezer.setAnnualConsumption(3650);
        validFreezer.setAttributeValue("Capacity", 15.0);}


    @Test
    void seeIfGetAttributeNames() {
        // Arrange

        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("Capacity");

        // Act

        List<String> actualResult = validFreezer.getAttributeNames();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSetAttributeValueFalse() {
        // Act

        boolean result = validFreezer.setAttributeValue("Bottle", 12.0);

        // Assert

        assertFalse(result);
    }

    @Test
    void seeIfSetAttributeValueTrueAndFalseCases() {
        // Arrange

        double attribute = 16.0;

        // Act

        boolean actualResult = validFreezer.setAttributeValue("Capacity", 16.0);
        boolean actualResultDouble = validFreezer.setAttributeValue(FreezerSpec.CAPACITY, 16);

        // Assert

        assertTrue(validFreezer.setAttributeValue(FreezerSpec.CAPACITY, attribute));
        assertFalse(validFreezer.setAttributeValue("notBottle", attribute));
        assertFalse(validFreezer.setAttributeValue("", attribute));
        assertFalse(validFreezer.setAttributeValue(null, attribute));
        assertFalse(actualResultDouble);
        assertTrue(actualResult);
    }

    @Test
    void seeIfGetObjectAttributeValue() {
        // Arrange

        double expectedResult = 15.0;

        // Act

        Object actualResult = validFreezer.getAttributeValue(FreezerSpec.CAPACITY);
        Object actualResultFalse = validFreezer.getAttributeValue("bottle");
        Object actualResultEmpty = validFreezer.getAttributeValue("");

        // Assert

        assertEquals(expectedResult, actualResult);
        assertEquals(false, actualResultFalse);
        assertEquals(false, actualResultEmpty);
    }

    @Test
    void seeIfGetObjectAttributeUnit() {
        // Arrange

        String expectedResult = "l";

        // Act

        Object actualResult = validFreezer.getAttributeUnit(FreezerSpec.CAPACITY);
        Object actualResultFalse = validFreezer.getAttributeUnit("bottle");
        Object actualResultEmpty = validFreezer.getAttributeUnit("");

        // Assert

        assertEquals(expectedResult, actualResult);
        assertEquals(false, actualResultFalse);
        assertEquals(false, actualResultEmpty);

    }

    @Test
    void seeIfSetAttributeValueTrue() {
        // Arrange

        FreezerSpec spec = new FreezerSpec();
        spec.setAttributeValue(FreezerSpec.CAPACITY, 5.0);

        // Act

        Object result = spec.getAttributeValue("Capacity");

        // Arrange

        assertEquals(5.0, result);
    }


    @Test
    void seeIfSetAttributeValueFailsForInteger() {
        // Arrange

        Integer attributeD = 6;
        validFreezer.setAttributeValue(FreezerSpec.CAPACITY, attributeD);

        // Assert

        assertFalse(validFreezer.setAttributeValue(FreezerSpec.CAPACITY, attributeD));
        assertFalse(validFreezer.setAttributeValue("notBottle", attributeD));
        assertFalse(validFreezer.setAttributeValue("notNOMINAL_POWER", attributeD));
        assertFalse(validFreezer.setAttributeValue("", attributeD));
    }
}