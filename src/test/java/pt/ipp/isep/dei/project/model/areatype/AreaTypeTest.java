package pt.ipp.isep.dei.project.model.areatype;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.room.RoomService;

import static org.junit.jupiter.api.Assertions.*;

/**
 * AreaType test class.
 */

class AreaTypeTest {

    // Common artifacts for testing in this class.
    private AreaType validType;

    @BeforeEach
    void arrangeArtifacts() {
        validType = new AreaType("Country");
    }

    @Test
    void seeIfGetNameWorks() {
        // Arrange

        String expectedResult = "Country";
        AreaType areaType = new AreaType();
        areaType.setName("Country");

        // Act

        String actualResult = areaType.getName();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfEqualsWorksTrue() {
        // Arrange

        AreaType testType = new AreaType("Country");

        // Act

        boolean actualResult = testType.equals(validType);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfEqualsWorksFalse() {
        // Arrange

        AreaType testAreaType = new AreaType("City");

        // Act

        boolean actualResult = testAreaType.equals(validType);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeEqualsWorksSameObject() {
        // Act

        boolean actualResult = validType.equals(validType); // Needed for sonarqube testing purposes.

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfEqualsWorksNotAnInstance() {
        // Act

        boolean actualResult = validType.equals(new RoomService()); // Needed for sonarqube testing purposes.

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void hashCodeDummyTest() {
        // Arrange

        int expectedResult = 1;

        // Act

        int actualResult = validType.hashCode();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfIsValidWorks() {
        // Act

        boolean result = validType.nameIsValid("City");

        // Assert

        assertTrue(result);
    }

    @Test
    void seeIfIsValidWorksFalse() {
        // Assert
        assertFalse(validType.nameIsValid("123"));
    }

    @Test
    void seeIfIsValidWorksEmpty() {
        // Assert
        assertFalse(validType.nameIsValid(""));
    }

    @Test
    void seeIfIsValidWorksFalseNumbers() {
        // Assert
        assertFalse(validType.nameIsValid("Country1"));
    }

    @Test
    void seeIfIsValidWorksNull() {
        // Assert
        assertFalse(validType.nameIsValid(null));
    }

    @Test
    void seeIfSetGetNameWorks() {
        // Arrange

        String expectedResult = "City";

        // Act

        validType.setName("City");
        String actualResult = validType.getName();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSetNameWorksEmptyString() {
        // Assert

        Assertions.assertThrows(IllegalArgumentException.class, () -> validType.setName(""));
    }

    @Test
    void seeIfSetNameWorksNull() {
        // Assert

        Assertions.assertThrows(IllegalArgumentException.class, () -> validType.setName(null));
    }

    @Test
    void seeIfSetNameWorksNumbers() {
        // Assert

        Assertions.assertThrows(IllegalArgumentException.class, () -> validType.setName("123"));
    }

    @Test
    void seeIfSetNameWorksNumbersAndLetters() {
        // Assert

        Assertions.assertThrows(IllegalArgumentException.class, () -> validType.setName("City1"));
    }

}
