package pt.ipp.isep.dei.project.model.device.devicespecs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.testng.Assert.*;

class StoveSpecTest {
    private StoveSpec validStoveSpec;

    @BeforeEach
    void arrangeArtifacts() {
        validStoveSpec = new StoveSpec();
    }

    @Test
    void seeIfGetAttributeNamesWorks() {
        // Arrange

        List<String> expectedResult = new ArrayList<>();

        // Act

        List<String> result = validStoveSpec.getAttributeNames();

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfSetAttributeValueWorksWrongName() {
        // Act
        boolean result = validStoveSpec.setAttributeValue("Fast Heating", 100);

        // Assert

        assertFalse(result);
    }

    @Test
    void seeIfGetAttributeUnitWorks() {
        // Act

        Object result = validStoveSpec.getAttributeUnit("Capacity");

        // Assert

        assertEquals(false, result);
    }

    @Test
    void seeIfSetAttributeValueWorksCapacity() {
        // Arrange

        validStoveSpec.setAttributeValue("Capacity", 5.0);

        // Act

        Object result = validStoveSpec.getAttributeValue("Capacity");

        // Assert

        assertEquals(0, result);
    }

}