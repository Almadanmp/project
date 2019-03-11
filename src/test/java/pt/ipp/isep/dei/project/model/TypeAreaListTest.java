package pt.ipp.isep.dei.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * TypeAreaList tests class.
 */

class TypeAreaListTest {
    // Common testing artifacts for this class.

    private TypeAreaList validList;
    private TypeArea firstValidType;
    private TypeArea secondValidType;

    @BeforeEach
    void arrangeArtifacts() {
        validList = new TypeAreaList();
        firstValidType = new TypeArea("Country");
        secondValidType = new TypeArea("City");
        validList.addTypeArea(firstValidType);
        validList.addTypeArea(secondValidType);
    }


    @Test
    void seeIfCreateTypeAreaWorks() {
        // Act

        boolean result = validList.createTypeArea("Village");

        // Assert

        assertTrue(result);
    }


    @Test
    void seeIfCreateTypeAreaWorksDuplicate() {
        // Act

        boolean result = validList.createTypeArea("City");

        // Assert

        assertFalse(result);
    }

    @Test
    void seeIfCreateTypeAreaWorksNull() {
        // Act

        boolean result = validList.createTypeArea(null);

        // Assert

        assertFalse(result);
    }

    @Test
    void seeIfCreateTypeAreaWorksEmpty() {
        // Act

        boolean result = validList.createTypeArea("");

        // Assert

        assertFalse(result);
    }

    @Test
    void seeIfCreateTypeAreaWorkNumbers() {
        // Act

        boolean result = validList.createTypeArea("City1");

        // Assert

        assertFalse(result);
    }

    @Test
    void seeIfPrintGAWholeList() {
        // Arrange

        String expectedResult = "---------------\n" +
                "0) Name: Country \n" +
                "1) Name: City \n" +
                "---------------\n";

        // Act

        String actualResult = validList.buildString();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfIsEmptyWorks() {
        // Arrange

        TypeAreaList emptyList = new TypeAreaList(); // List is Empty.
        TypeAreaList oneElementList = new TypeAreaList(); // List has one element.
        oneElementList.addTypeArea(firstValidType);

        // Act

        boolean actualResult1 = emptyList.isEmpty();
        boolean actualResult2 = oneElementList.isEmpty();
        boolean actualResult3 = validList.isEmpty();

        //Assert

        assertTrue(actualResult1);
        assertFalse(actualResult2);
        assertFalse(actualResult3);
    }

    @Test
    void seeIfEqualsWorksTrue() {
        // Assert

        TypeAreaList testList = new TypeAreaList();
        testList.addTypeArea(firstValidType);
        testList.addTypeArea(secondValidType);

        // Act

        boolean actualResult = validList.equals(testList);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfEqualsWorksFalse() {
        // Arrange

        TypeAreaList testList = new TypeAreaList();
        testList.addTypeArea(secondValidType);

        // Act

        boolean actualResult = validList.equals(testList);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksNotAnInstance() {
        // Act

        boolean actualResult = validList.equals(new RoomList()); // Needed for sonarqube testing purposes.

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksForItself() {
        // Act

        boolean actualResult = validList.equals(validList); // Needed for sonarqube testing purposes.

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void hashCodeDummyTest() {
        //Arrange

        int expectedResult = 1;

        // Act

        int actualResult = validList.hashCode();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetTypeAreaByIndexWorks() {
        //Act

        TypeArea actualResult1 = validList.get(0);
        TypeArea actualResult2 = validList.get(1);

        //Assert

        assertEquals(firstValidType, actualResult1);
        assertEquals(secondValidType, actualResult2);
    }

    @Test
    void getByIndexEmptyTypeAreaList() {
        // Arrange

        TypeAreaList emptyList = new TypeAreaList();

        // Act

        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> emptyList.get(0));

        // Assert

        assertEquals("The type area list is empty.", exception.getMessage());
    }

    @Test
    void seeIfGetElementsAsArrayWorks() {
        // Arrange

        TypeArea[] expectedResult1 = new TypeArea[0];
        TypeArea[] expectedResult2 = new TypeArea[1];
        TypeArea[] expectedResult3 = new TypeArea[2];

        TypeAreaList emptyList = new TypeAreaList();
        TypeAreaList oneTypeArea = new TypeAreaList();

        oneTypeArea.addTypeArea(new TypeArea("typeArea1"));

        expectedResult2[0] = new TypeArea("typeArea1");
        expectedResult3[0] = firstValidType;
        expectedResult3[1] = secondValidType;

        //Act

        TypeArea[] actualResult1 = emptyList.getElementsAsArray();
        TypeArea[] actualResult2 = oneTypeArea.getElementsAsArray();
        TypeArea[] actualResult3 = validList.getElementsAsArray();

        //Assert

        assertArrayEquals(expectedResult1, actualResult1);
        assertArrayEquals(expectedResult2, actualResult2);
        assertArrayEquals(expectedResult3, actualResult3);
    }

    @Test
    void seeIfGetSizeWorks() {
        // Arrange

        TypeAreaList emptyList = new TypeAreaList();

        // Act

        int actualResult1 = emptyList.size();
        int actualResult2 = validList.size();


        // Assert

        assertEquals(0, actualResult1);
        assertEquals(2, actualResult2);
    }
}