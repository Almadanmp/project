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
    void seeTypeOfGeographicAreaConstructor() {
        //Arrange
        String expectedResult = "Country";

        //Act
        String actualResult = validType.getName();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeEqualsBetweenTwoGeographicAreaTypesWithSameDesignation() {
        //Arrange
        boolean actualResult;
        TypeArea typeArea1 = new TypeArea("Rua");
        TypeArea typeArea2 = new TypeArea("Rua");
        //Act
        actualResult = typeArea1.equals(typeArea2);
        //Assert
        assertTrue(actualResult);
    }

    @Test
    void seeEqualsBetweenTwoGeographicAreaTypesWithDifferentDesignation() {
        //Arrange
        boolean expectedResult = false;
        TypeArea testTypeArea = new TypeArea("City");

        //Act
        boolean actualResult = testTypeArea.equals(validType);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeEqualsBetweenSameObject() {
        //Arrange

        //Act
        boolean actualResult = validType.equals(validType);

        //Assert
        assertTrue(actualResult);
    }

    @Test
    void seeEqualsBetweenObjectsFromDifferentClasses() {
        //Arrange
        int number = 1;

        //Act
        boolean actualResult = validType.equals(number);

        //Assert
        assertFalse(actualResult);
    }

    @Test
    void hashCodeDummyTest() {
        //Arrange
        int expectedResult = 1;

        //Act
        int actualResult = validType.hashCode();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfNameValid() {
        //Arrange

        //Act
        boolean result = validType.isNameValid("City");

        //Assert
        assertTrue(result);
    }

    @Test
    void seeIfNameIsNotValidNumericName() {
        //Arrange

        //Act

        //Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            validType.isNameValid("123");
        });
    }

    @Test
    void seeIfNameInvalidNameEmpty() {
        //Arrange

        //Act

        //Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            validType.isNameValid("");
        });
    }

    @Test
    void seeIfNameInvalidNameWithNumbers() {
        //Arrange

        //Act

        //Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            validType.isNameValid("Country1");
        });
    }

    @Test
    void seeIfNameInvalidNameNull() {
        //Arrange

        //Act

        //Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            validType.isNameValid(null);
        });
    }

    @Test
    void seeIfNameSetterWorks() {
        //Arrange
        String expectedResult = "City";
        //Act
        validType.setName("City");
        String actualResult = validType.getName();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfNameSetterFailsEmptyName() {
        //Arrange

        //Act

        //Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            validType.setName("");
        });
    }

    @Test
    void seeIfNameSetterFailsNullName() {
        //Arrange

        //Act

        //Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            validType.setName(null);
        });
    }

    @Test
    void seeIfNameSetterFailsNumericName() {
        //Arrange

        //Act

        //Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            validType.setName("123");
        });
    }

    @Test
    void seeIfNameSetterFailsNumbersInName() {
        //Arrange

        //Act

        //Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            validType.setName("City1");
        });
    }

}
