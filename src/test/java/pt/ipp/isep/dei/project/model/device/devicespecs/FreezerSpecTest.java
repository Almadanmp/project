package pt.ipp.isep.dei.project.model.device.devicespecs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.device.Freezer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.testng.Assert.*;

public class FreezerSpecTest {
    private Freezer freezerValid = new Freezer(new FreezerSpec());

    @BeforeEach
    void arrangeArtifacts() {
        freezerValid.setName("Freezer");
        freezerValid.setNominalPower(30);
        freezerValid.setAnnualConsumption(3650);
        freezerValid.setAttributeValue("Capacity", 15.0);}


    @Test
    void seeIfGetAttributeNames() {
        // Arrange

        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("Capacity");

        // Act

        List<String> actualResult = freezerValid.getAttributeNames();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSetAttributeValueFalse() {
        // Act

        boolean result = freezerValid.setAttributeValue("Bottle", 12.0);

        // Assert

        assertFalse(result);
    }

    @Test
    void seeIfSetAttributeValueTrueAndFalseCases() {
        // Arrange

        double attribute = 16.0;

        // Act

        boolean actualResult = freezerValid.setAttributeValue("Capacity", 16.0);
        boolean actualResultDouble = freezerValid.setAttributeValue(FreezerSpec.CAPACITY, 16);

        // Assert

        assertTrue(freezerValid.setAttributeValue(FreezerSpec.CAPACITY, attribute));
        assertFalse(freezerValid.setAttributeValue("notBottle", attribute));
        assertFalse(freezerValid.setAttributeValue("", attribute));
        assertFalse(freezerValid.setAttributeValue(null, attribute));
        assertFalse(actualResultDouble);
        assertTrue(actualResult);
    }

    @Test
    void seeIfGetObjectAttributeValue() {
        // Arrange

        double expectedResult = 15.0;

        // Act

        Object actualResult = freezerValid.getAttributeValue(FreezerSpec.CAPACITY);
        Object actualResultFalse = freezerValid.getAttributeValue("bottle");
        Object actualResultEmpty = freezerValid.getAttributeValue("");

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

        Object actualResult = freezerValid.getAttributeUnit(FreezerSpec.CAPACITY);
        Object actualResultFalse = freezerValid.getAttributeUnit("bottle");
        Object actualResultEmpty = freezerValid.getAttributeUnit("");

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
        freezerValid.setAttributeValue(FreezerSpec.CAPACITY, attributeD);

        // Assert

        assertFalse(freezerValid.setAttributeValue(FreezerSpec.CAPACITY, attributeD));
        assertFalse(freezerValid.setAttributeValue("notBottle", attributeD));
        assertFalse(freezerValid.setAttributeValue("notNOMINAL_POWER", attributeD));
        assertFalse(freezerValid.setAttributeValue("", attributeD));
    }
}