package pt.ipp.isep.dei.project.model.device.devicespecs;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class WallTowelHeaterSpecTest {
    // Common artifacts for testing in this class.

    private WallTowelHeaterSpec validWTHSpec = new WallTowelHeaterSpec();

    @Test
    void seeIfGetAttributeNamesWorks() {
        // Arrange

        List<String> expectedResult = new ArrayList<>();

        // Act

        List<String> actualResult = validWTHSpec.getAttributeNames();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeifSetAttributeValueWorks() {
        // Act

        boolean actualResult = validWTHSpec.setAttributeValue("anything", 12);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfGetAttributeUnitWorks() {
        // Act

        Object actualResult = validWTHSpec.getAttributeUnit("anything");

        // Assert

        assertEquals(false, actualResult);
    }

    @Test
    void seeIfGetSetAttributeValueWorksWrongName() {
        // Arrange

        validWTHSpec.setAttributeValue("Anything", 5.0);
        int expectedResult = 0;

        // Act

        Object actualResult = validWTHSpec.getAttributeValue("Anything");

        // Assert

        assertEquals(expectedResult, actualResult);
    }
}
