package pt.ipp.isep.dei.project.model.device.devicespecs;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class WallTowelHeaterSpecTest {

    // Common artifacts for testing in this class.
    private WallTowelHeaterSpec validWTHSpec = new WallTowelHeaterSpec();

    @Test
    void testGetAttributeNames() {
        //Arrange
        List<String> expectedResult = new ArrayList<>();
        //Act
        List<String> actualResult = validWTHSpec.getAttributeNames();
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testSetAttributeValue() {
        //Act
        boolean actualResult = validWTHSpec.setAttributeValue("anything", 12);
        //Assert
        assertFalse(actualResult);
    }

    @Test
    void testGetAttributeUnit() {
        //Arrange
        boolean expectedResult = false;
        //Act
        Object actualResult = validWTHSpec.getAttributeUnit("anything");
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testSetAttributeValueAnything() {
        //Arrange
        validWTHSpec.setAttributeValue("Anything", 5.0);
        int expectedResult = 0;
        //Act
        Object actualResult = validWTHSpec.getAttributeValue("Anything");
        //Assert
        assertEquals(expectedResult, actualResult);
    }
}
