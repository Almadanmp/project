package pt.ipp.isep.dei.project.model.device.devicespecs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.device.WineCooler;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.testng.Assert.*;

class WineCoolerSpecTest {
    private WineCooler wineCoolerValid = new WineCooler(new WineCoolerSpec());

    @BeforeEach
    void arrangeArtifacts() {
        wineCoolerValid.setName("Wine Cooler");
        wineCoolerValid.setNominalPower(15);
        wineCoolerValid.setAnnualConsumption(3650);
        wineCoolerValid.setAttributeValue("Number of Bottles", 15.0);}


    @Test
    void seeIfGetAttributeNamesWorks() {
        // Arrange

        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("Number of Bottles");

        // Act

        List<String> actualResult = wineCoolerValid.getAttributeNames();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSetAttributeValueWorksWrongNameNotDouble() {
        // Act

        boolean result = wineCoolerValid.setAttributeValue("Flux", 12);

        // Assert

        assertFalse(result);
    }

    @Test
    void seeIfSetAttributeValueWorks() {
        // Act

        boolean actualResult = wineCoolerValid.setAttributeValue("Number of Bottles", 12.0);
        boolean actualResultDouble = wineCoolerValid.setAttributeValue(WineCoolerSpec.NUMBER_BOTTLES, 5);

        // Assert

        assertTrue(wineCoolerValid.setAttributeValue(WineCoolerSpec.NUMBER_BOTTLES, 6D));
        assertFalse(wineCoolerValid.setAttributeValue("notFLUX", 6D));
        assertFalse(wineCoolerValid.setAttributeValue("", 6D));
        assertFalse(wineCoolerValid.setAttributeValue(null, 6D));
        assertFalse(actualResultDouble);
        assertTrue(actualResult);
    }

    @Test
    void seeIfGetAttributeValueWorks() {
        // Arrange

        Double expectedResult = 15.0;

        // Act

        Object actualResult = wineCoolerValid.getAttributeValue(WineCoolerSpec.NUMBER_BOTTLES);
        Object actualResultFalse = wineCoolerValid.getAttributeValue("flux");
        Object actualResultEmpty = wineCoolerValid.getAttributeValue("");

        // Assert

        assertEquals(expectedResult, actualResult);
        assertEquals(false, actualResultFalse);
        assertEquals(false, actualResultEmpty);
    }

    @Test
    void seeIfGetAttributeUnitWorks() {
        // Arrange

        String expectedResult = "bottles";

        // Act

        Object actualResult = wineCoolerValid.getAttributeUnit(WineCoolerSpec.NUMBER_BOTTLES);
        Object actualResultFalse = wineCoolerValid.getAttributeUnit("flux");
        Object actualResultEmpty = wineCoolerValid.getAttributeUnit("");

        // Assert

        assertEquals(expectedResult, actualResult);
        assertEquals(false, actualResultFalse);
        assertEquals(false, actualResultEmpty);

    }


    @Test
    void seeIfSetAttributeValueWorksNotDouble() {
        // Assert

        assertFalse(wineCoolerValid.setAttributeValue(WineCoolerSpec.NUMBER_BOTTLES, 5));
        assertFalse(wineCoolerValid.setAttributeValue("notFLUX", 5));
        assertFalse(wineCoolerValid.setAttributeValue("notNOMINAL_POWER", 5));
        assertFalse(wineCoolerValid.setAttributeValue("", 5));
    }
}