
package pt.ipp.isep.dei.project.model.device.devicespecs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * WashingMachineSpec tests class.
 */


class WashingMachineSpecTest {
    private WashingMachineSpec validWashingMachineSpec;

    @BeforeEach
    void arrangeArtifacts() {
        validWashingMachineSpec = new WashingMachineSpec();
    }

    @Test
    void seeIfGetAttributeNamesWorks() {
        // Arrange

        List<String> expectedResult = new ArrayList<>();
        expectedResult.add(WashingMachineSpec.WM_CAPACITY);

        // Act

        List<String> result = validWashingMachineSpec.getAttributeNames();

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetSetAttributeValueWorks() {
        // Arrange

        validWashingMachineSpec.setAttributeValue(WashingMachineSpec.WM_CAPACITY, 5D);
        double expectedResult = 5.0;

        // Act

        Object result = validWashingMachineSpec.getAttributeValue("Capacity");

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAttributeUnitWorks() {
        // Arrange

        String expectedResult = "Kg";

        // Act

        Object result = validWashingMachineSpec.getAttributeUnit("Capacity");

        // Assert

        assertEquals(expectedResult, result);
    }


    @Test
    void seeIfGetAttributeValueWorksWrongName() {
        // Arrange

        int expectedResult = 0;

        // Act

        Object result = validWashingMachineSpec.getAttributeValue("Capacity" + "programList");

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAttributeValueWorksCapacity() {
        // Arrange

        validWashingMachineSpec.setAttributeValue(WashingMachineSpec.WM_CAPACITY, 34D);
        double expectedResult = 34.0;

        // Act

        Object result = validWashingMachineSpec.getAttributeValue("Capacity");

        // Assert

        assertEquals(expectedResult, result);
    }


    @Test
    void seeIfSetAttributeValueWorks() {
        // Act

        boolean actualResult = validWashingMachineSpec.setAttributeValue("Capacity", 12.0);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfSetAttributeValueWorksNotDouble() {
        // Act

        Object result = validWashingMachineSpec.setAttributeValue(WashingMachineSpec.WM_CAPACITY, 5);

        // Assert

        assertEquals(false, result);
    }

    @Test
    void seeIfSetAttributeValueWorksWrongName() {
        // Act

        boolean result = validWashingMachineSpec.setAttributeValue("Lisbon", 12);

        // Assert

        assertFalse(result);
    }

    @Test
    void seeIfGetAttributeValueWorks() {
        // Arrange

        validWashingMachineSpec.setAttributeValue(WashingMachineSpec.WM_CAPACITY, 5D);

        // Happy Case

        assertEquals(5.0, validWashingMachineSpec.getAttributeValue(WashingMachineSpec.WM_CAPACITY));

        //  Wrong Attribute Name.

        assertEquals(0, validWashingMachineSpec.getAttributeValue("\0Capacity"));

        // Empty attribute name.

        assertEquals(0, validWashingMachineSpec.getAttributeValue(""));
    }

    @Test
    void seeIfSetAttributeValueWorksEmptyName() {
        assertFalse(validWashingMachineSpec.setAttributeValue("", 6D));
    }

    @Test
    void seeIifGetAttributeUnitWorks() {
        // Arrange

        String attributeKg = "Kg";

        // Happy case

        assertEquals(attributeKg, validWashingMachineSpec.getAttributeUnit(WashingMachineSpec.WM_CAPACITY));

        // Wrong attribute name.

        assertEquals(false, validWashingMachineSpec.getAttributeUnit("notCapacity"));

        // Empty attribute name.

        assertEquals(false, validWashingMachineSpec.getAttributeUnit(""));
    }
}
