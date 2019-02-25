package pt.ipp.isep.dei.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TypeArea tests class.
 */

class TypeAreaTest {

    @Test
    void seeTypeOfGeographicAreaConstructor() {
        //Arrange
        String expectedResult = "Rua";
        String actualResult;
        //Act
        TypeArea typeArea1 = new TypeArea("Rua");
        actualResult = typeArea1.getName();

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
        boolean actualResult;
        TypeArea typeArea1 = new TypeArea("Rua");
        TypeArea typeArea2 = new TypeArea("Freguesia");
        //Act
        actualResult = typeArea1.equals(typeArea2);
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeEqualsBetweenSameObject() {
        //Arrange
        boolean actualResult;
        TypeArea typeArea1 = new TypeArea("Rua");
        //Act
        actualResult = typeArea1.equals(typeArea1);
        //Assert
        assertTrue(actualResult);
    }

    @Test
    void seeEqualsBetweenObjectsFromDifferentClasses() {
        //Arrange
        boolean actualResult;
        TypeArea typeArea1 = new TypeArea("Rua");
        int number = 1;
        //Act
        actualResult = typeArea1.equals(number);
        //Assert
        assertFalse(actualResult);
    }

    @Test
    void hashCodeDummyTest() {
        TypeArea t1 = new TypeArea("Rua");
        int expectedResult = 1;
        int actualResult = t1.hashCode();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfNameValid() {
        //Arrange
        TypeArea a1 = new TypeArea("Humidade");

        //Act
        boolean result = a1.isNameValid("Vale");

        //Assert
        assertTrue(result);
    }

    @Test
    void seeIfNameInvalidNameEmpty() {

        //Arrange
        TypeArea a1 = new TypeArea("Humidade");
        //Act

        //Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            a1.isNameValid("");
        });
    }

    @Test
    void seeIfNameInvalidNameWithNumbers() {

        //Arrange
        TypeArea a1 = new TypeArea("Humidade");
        //Act

        //Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            a1.isNameValid("Humidade1");
        });
    }

    @Test
    void seeIfNameInvalidNameNull() {

        //Arrange
        TypeArea a1 = new TypeArea("Humidade");
        //Act

        //Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            a1.isNameValid(null);
        });
    }

}
