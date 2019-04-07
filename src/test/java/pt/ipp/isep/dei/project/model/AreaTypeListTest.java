package pt.ipp.isep.dei.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project.repository.AreaTypeRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TypeAreaList tests class.
 */
@ExtendWith(MockitoExtension.class)
class AreaTypeListTest {
    // Common testing artifacts for this class.

    private AreaType firstValidType;
    private AreaType secondValidType;

    @Mock
    private AreaTypeRepository areaTypeRepository;

    private AreaTypeList validList;

    @BeforeEach
    void arrangeArtifacts() {
        validList = new AreaTypeList(this.areaTypeRepository);
        firstValidType = new AreaType("Country");
        secondValidType = new AreaType("City");
        validList.addTypeArea(firstValidType);
        validList.addTypeArea(secondValidType);
    }

    @Test
    void seeIfCreateTypeAreaWorks() {
        // Act
        AreaType type1 = validList.createTypeArea("Country");
        // Assert
        assertEquals(type1, firstValidType);
    }

    @Test
    void seeIfPrintGAWholeList() {
        // Arrange

        String expectedResult = "---------------\n" +
                "0) Description: Country \n" +
                "1) Description: City \n" +
                "---------------\n";

        // Act

        String actualResult = validList.buildString();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfBuildListIfEmpty() {
        // Arrange
        AreaTypeList emptyList = new AreaTypeList(areaTypeRepository);
        String expectedResult = "Invalid List - List is Empty\n";

        // Act

        String actualResult = emptyList.buildString();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfIsEmptyWorks() {
        // Arrange

        AreaTypeList emptyList = new AreaTypeList(areaTypeRepository); // List is Empty.
        List<AreaType> oneElementList = new ArrayList<>(); // List has one element.
        oneElementList.add(firstValidType);

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
    void seeIfEqualsWorksFalse() {
        // Arrange

        List<AreaType> testList = new ArrayList<>();
        testList.add(secondValidType);

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

        AreaType actualResult1 = validList.get(0);
        AreaType actualResult2 = validList.get(1);

        //Assert

        assertEquals(firstValidType, actualResult1);
        assertEquals(secondValidType, actualResult2);
    }

    @Test
    void seeIfGetTypeAreasWorks() {
        List<AreaType> actualResult = validList.getAreaTypes();

        // Assert

        assertNotNull(actualResult);
    }

    @Test
    void getByIndexEmptyTypeAreaList() {
        // Arrange

        AreaTypeList emptyList = new AreaTypeList(areaTypeRepository);

        // Act

        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> emptyList.get(0));

        // Assert

        assertEquals("The type area list is empty.", exception.getMessage());
    }

    @Test
    void seeIfGetSizeWorks() {
        // Arrange

        AreaTypeList emptyList = new AreaTypeList(areaTypeRepository);

        // Act

        int actualResult1 = emptyList.size();
        int actualResult2 = validList.size();


        // Assert

        assertEquals(0, actualResult1);
        assertEquals(2, actualResult2);
    }

    @Test
    void seeIfEqualsWorksTrue() {
        // Assert

        AreaTypeList testList = new AreaTypeList(areaTypeRepository);
        validList = new AreaTypeList(areaTypeRepository);

        // Act

        boolean actualResult = validList.equals(testList);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfGetElementsAsArrayWorks() {
        // Arrange

        AreaType[] expectedResult1 = new AreaType[0];
        AreaType[] expectedResult2 = new AreaType[1];
        AreaType[] expectedResult3 = new AreaType[2];

        AreaTypeList emptyList = new AreaTypeList(areaTypeRepository);
        List<AreaType> oneAreaType = new ArrayList<>();

        oneAreaType.add(new AreaType("typeArea1"));

        expectedResult2[0] = new AreaType("typeArea1");
        expectedResult3[0] = firstValidType;
        expectedResult3[1] = secondValidType;

        //Act

        AreaType[] actualResult1 = emptyList.getElementsAsArray();
        AreaType[] actualResult3 = validList.getElementsAsArray();

        //Assert

        assertArrayEquals(expectedResult1, actualResult1);
        assertArrayEquals(expectedResult3, actualResult3);
    }

}