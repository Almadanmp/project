package pt.ipp.isep.dei.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * TypeAreaList tests class.
 */

class TypeAreaListTest {

    @Test
    void newTAG() {
        TypeAreaList newList = new TypeAreaList();
        boolean result = newList.newTAG("cidade");
        assertTrue(result);
    }

    @Test
    void seeIfNewTAGWorksWithAnother() {
        TypeArea type = new TypeArea("rua");
        TypeAreaList newList = new TypeAreaList();
        newList.addTypeArea(type);
        boolean result = newList.newTAG("cidade");
        assertTrue(result);
    }

    @Test
    void seeIfNewTAGDoesNotWorkWhenDuplicatedISAdded() {
        TypeArea tipo = new TypeArea("cidade");
        TypeAreaList expectedResult = new TypeAreaList();
        expectedResult.addTypeArea(tipo);
        boolean result = expectedResult.newTAG("cidade");
        assertFalse(result);
    }

    @Test
    void seeIfNewTAGDoesNotWorkWhenNullIsAdded() {
        TypeArea tipo = new TypeArea("cidade");
        TypeAreaList list = new TypeAreaList();
        list.addTypeArea(tipo);
        boolean result = list.newTAG(null);
        assertFalse(result);
    }

    @Test
    void seeIfNewTAGDoesNotWorkWhenNameIsEmpty() {
        TypeArea type = new TypeArea("cidade");
        TypeAreaList list = new TypeAreaList();
        list.addTypeArea(type);
        boolean result = list.newTAG("");
        assertFalse(result);
    }

    @Test
    void seeIfNewTAGDoesNotWorkWhenNumbersAreAdded() {
        TypeArea type = new TypeArea("cidade");
        TypeAreaList list = new TypeAreaList();
        list.addTypeArea(type);
        boolean result = list.newTAG("cidade1");
        assertFalse(result);
    }

    @Test
    void seeIfPrintGAWholeList() {
        TypeArea type1 = new TypeArea("cidade");
        TypeArea type2 = new TypeArea("distrito");
        TypeArea type3 = new TypeArea("aldeia");
        TypeAreaList list = new TypeAreaList();
        list.addTypeArea(type1);
        list.addTypeArea(type2);
        list.addTypeArea(type3);
        list.addTypeArea(type1);
        String expectedResult = "---------------\n" +
                "0) Name: cidade \n" +
                "1) Name: distrito \n" +
                "2) Name: aldeia \n" +
                "---------------\n";
        String result;
        result = list.buildString();
        assertEquals(expectedResult, result);
    }

    @Test
    void isEmpty() {
        //Arrange
        TypeArea type1 = new TypeArea("city");
        TypeArea type2 = new TypeArea("village");
        TypeAreaList list1 = new TypeAreaList(); //EMPTY LIST
        TypeAreaList list2 = new TypeAreaList(); //ONE TYPE AREA
        TypeAreaList list3 = new TypeAreaList(); //TWO TYPE AREAS

        list2.addTypeArea(type1);
        list3.addTypeArea(type1);
        list3.addTypeArea(type2);

        //Act
        boolean actualResult1 = list1.isEmpty();
        boolean actualResult2 = list2.isEmpty();
        boolean actualResult3 = list3.isEmpty();

        //Assert
        assertTrue(actualResult1);
        assertFalse(actualResult2);
        assertFalse(actualResult3);
    }

    @Test
    void ensureThatAObjectIsAInstanceOfDifferentLists() {
        //Assert
        TypeArea type1 = new TypeArea("cidade");
        TypeArea type2 = new TypeArea("distrito");
        TypeArea type3 = new TypeArea("aldeia");
        TypeAreaList list = new TypeAreaList();
        list.addTypeArea(type1);
        list.addTypeArea(type2);
        list.addTypeArea(type3);
        TypeAreaList list2 = new TypeAreaList();
        list2.addTypeArea(type1);
        list2.addTypeArea(type2);
        list2.addTypeArea(type3);

        //Act
        boolean actualResult = list.equals(list2);

        //Assert
        assertTrue(actualResult);
    }

    @Test
    void ensureThatAObjectIsAInstanceOfDifferentObjectsWithDifferentContent() {
        //Arrange
        TypeArea type1 = new TypeArea("cidade");
        TypeArea type2 = new TypeArea("distrito");
        TypeArea type3 = new TypeArea("aldeia");
        TypeArea type4 = new TypeArea("país");
        TypeArea type5 = new TypeArea("vila");
        TypeAreaList list = new TypeAreaList();
        list.addTypeArea(type1);
        list.addTypeArea(type2);
        list.addTypeArea(type3);
        TypeAreaList list2 = new TypeAreaList();
        list2.addTypeArea(type1);
        list2.addTypeArea(type4);
        list2.addTypeArea(type5);

        //Act
        boolean actualResult = list.equals(list2);

        //Assert
        assertFalse( actualResult);
    }

    @Test
    void ensureThatAObjectIsAInstanceOfDifferentObjectsWithSameContent() {
        //Arrange
        TypeArea type1 = new TypeArea("cidade");
        TypeArea type2 = new TypeArea("distrito");
        TypeArea type3 = new TypeArea("aldeia");
        TypeArea type4 = new TypeArea("cidade");
        TypeArea type5 = new TypeArea("distrito");
        TypeArea type6 = new TypeArea("aldeia");
        TypeAreaList list = new TypeAreaList();
        list.addTypeArea(type1);
        list.addTypeArea(type2);
        list.addTypeArea(type3);
        TypeAreaList list2 = new TypeAreaList();
        list2.addTypeArea(type4);
        list2.addTypeArea(type5);
        list2.addTypeArea(type6);

        //Act
        boolean actualResult = list.equals(list2);

        //Assert
        assertTrue( actualResult);
    }

    @Test
    void ensureThatAObjectIsAInstanceOfDifferentListTypes() {
        //Arrange
        TypeArea type1 = new TypeArea("cidade");
        TypeArea type2 = new TypeArea("distrito");
        TypeArea type3 = new TypeArea("aldeia");
        Room room1 = new Room("room1", 1, 12,12,12);
        Room room2 = new Room("room2", 3, 15,17,12);
        TypeAreaList list = new TypeAreaList();
        list.addTypeArea(type1);
        list.addTypeArea(type2);
        list.addTypeArea(type3);
        RoomList list2 = new RoomList();
        list2.add(room1);
        list2.add(room2);

        //Act
        boolean actualResult = list.equals(list2);

        //Assert
        assertFalse( actualResult);
    }

    @Test
    void ensureThatAObjectIsAInstanceOfSameList() {
        //Arrange
        TypeArea type1 = new TypeArea("cidade");
        TypeArea type2 = new TypeArea("distrito");
        TypeArea type3 = new TypeArea("aldeia");
        TypeAreaList list = new TypeAreaList();
        list.addTypeArea(type1);
        list.addTypeArea(type2);
        list.addTypeArea(type3);

        //Act
        boolean actualResult = list.equals(list);

        //Assert
        assertTrue( actualResult);
    }

    @Test
    void seeHashCodeDummyTest() {
        //Arrange
        TypeArea type1 = new TypeArea("cidade");
        TypeArea type2 = new TypeArea("distrito");
        TypeArea type3 = new TypeArea("aldeia");
        TypeAreaList list = new TypeAreaList();
        list.addTypeArea(type1);
        list.addTypeArea(type2);
        list.addTypeArea(type3);

        //Act
        int expectedResult = 1;
        int actualResult = list.hashCode();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

}