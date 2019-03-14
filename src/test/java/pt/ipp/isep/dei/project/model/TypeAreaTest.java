package pt.ipp.isep.dei.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TypeArea test class.
 */

class TypeAreaTest {

    // Common artifacts for testing in this class.
    private TypeArea validType;

    @BeforeEach
    void arrangeArtifacts() {
        validType = new TypeArea("Country");
    }

    @Test
    void seeIfGetNameWorks() {
        // Arrange

        String expectedResult = "Country";

        // Act

        String actualResult = validType.getName();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfEqualsWorksTrue() {
        // Arrange

        TypeArea testType = new TypeArea("Country");

        // Act

        boolean actualResult = testType.equals(validType);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfEqualsWorksFalse() {
        // Arrange

        TypeArea testTypeArea = new TypeArea("City");

        // Act

        boolean actualResult = testTypeArea.equals(validType);

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

        boolean actualResult = validType.equals(new RoomList()); // Needed for sonarqube testing purposes.

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

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            validType.setName("");
        });
    }

    @Test
    void seeIfSetNameWorksNull() {
        // Assert

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            validType.setName(null);
        });
    }

    @Test
    void seeIfSetNameWorksNumbers() {
        // Assert

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            validType.setName("123");
        });
    }

    @Test
    void seeIfSetNameWorksNumbersAndLetters() {
        // Assert

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            validType.setName("City1");
        });
    }

}
