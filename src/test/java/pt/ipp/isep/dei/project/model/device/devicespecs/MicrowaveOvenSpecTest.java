package pt.ipp.isep.dei.project.model.device.devicespecs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class MicrowaveOvenSpecTest {
    // Common testing artifacts for testing in this class.

    private MicrowaveOvenSpec validOvenSpec;

    @BeforeEach
    void arrangeArtifacts(){
        validOvenSpec = new MicrowaveOvenSpec();
    }

    @Test
    void seeIfGetAttributeNamesWorks() {
        // Arrange

        List<String> expectedResult = new ArrayList<>();

        // Act

        List<String> result = validOvenSpec.getAttributeNames();

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfSetAttributeValuesWorksWrongAttributeName() {
        // Act

        boolean result = validOvenSpec.setAttributeValue("Lisbon", 12);

        // Assert

        assertFalse(result);
    }

    @Test
    void seeIfGetAttributeUnitWorks() {
        // Act

        Object result = validOvenSpec.getAttributeUnit("Capacity");

        // Assert

        assertEquals(false, result);
    }

    @Test
    void seeIfSetAttributeValueWorksCapacity() {
        // Arrange

        validOvenSpec.setAttributeValue("Capacity", 5.0);

        // Act

        Object result = validOvenSpec.getAttributeValue("Capacity");

        // Act

        assertEquals(0, result);
    }

}