package pt.ipp.isep.dei.project.model.device.devicespecs;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FanSpecTest {
    // Common testing artifacts for testing in this class.

    private FanSpec validSpec = new FanSpec();

    @Test
    void seeIfGetAttributeNamesWorks() {
        // Arrange

        List<String> expectedResult = new ArrayList<String>();

        // Act

        List<String> actualResult = validSpec.getAttributeNames();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetAttributeValueWorks() {
        assertThrows(UnsupportedOperationException.class, () -> validSpec.getAttributeValue("Attribute"));
    }

    @Test
    void seeIfGetAttributeUnitWorks() {
        assertThrows(UnsupportedOperationException.class, () -> validSpec.getAttributeUnit("Attribute"));
    }

    @Test
    void seeIfSetAttributeValueWorks(){
        assertThrows(UnsupportedOperationException.class, () -> validSpec.setAttributeValue("Attribute", "String"));
    }
}
