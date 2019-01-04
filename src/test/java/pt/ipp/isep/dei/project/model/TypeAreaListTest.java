package pt.ipp.isep.dei.project.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

class TypeAreaListTest {

    @Test
    public void seeIfPrintTypeAreaListWorks() {
        TypeAreaList list = new TypeAreaList();
        TypeArea t1 = new TypeArea("Rua");
        list.addTypeArea(t1);
        String result = list.printTypeAreaList();
        String expectedResult = "\n" +
                "Area Types List:\n" +
                "\n" +
                "-Rua;";
        assertEquals(result, expectedResult);
    }

    @Test
    public void seeIfPrintsWhenTypeAreaListIsEmpty() {
        TypeAreaList list = new TypeAreaList();
        String result = list.printTypeAreaList();
        String expectedResult = "\nArea Types List:\n\n|||| List is Empty ||||\nAdd types to list first";
        assertEquals(result, expectedResult);
    }

    @Test
    public void seeIfPrintsWhenTypeAreaListIsNull() {
        TypeAreaList list = new TypeAreaList();
        List<TypeArea> mTypeAreaList = list.getTypeAreaList();   //sem esta linha o teste não passava
        String result = list.printTypeAreaList();
        String expectedResult = "\nArea Types List:\n\n|||| List is Empty ||||\nAdd types to list first";
        assertEquals(result, expectedResult);
    }

    @Test
    void newTAG() {
        TypeAreaList newList = new TypeAreaList();
        boolean result = newList.newTAG("cidade");
        assertTrue(result);
    }

    @Test
    public void seeIfnewTAGWorksWithAnother() {
        TypeArea tipo = new TypeArea("rua");
        TypeAreaList newList = new TypeAreaList();
        newList.addTypeArea(tipo);
        boolean result = newList.newTAG("cidade");
        assertTrue(result);
    }

    @Test
    public void seeIfnewTAGDoesntWorkWhenDuplicatedISAdded() {
        TypeArea tipo = new TypeArea("cidade");
        TypeAreaList expectedResult = new TypeAreaList();
        expectedResult.addTypeArea(tipo);
        boolean result = expectedResult.newTAG("cidade");
        assertFalse(result);
    }

    @Test
    public void seeIfNewTAGDoesntWorkWhenNullIsAdded() {
        TypeArea tipo = new TypeArea("cidade");
        TypeAreaList list = new TypeAreaList();
        list.addTypeArea(tipo);
        boolean result = list.newTAG(null);
        assertFalse(result);
    }

    @Test
    public void seeIfNewTAGDoesntWorkWhenNameIsEmpty() {
        TypeArea tipo = new TypeArea("cidade");
        TypeAreaList list = new TypeAreaList();
        list.addTypeArea(tipo);
        boolean result = list.newTAG("");
        assertFalse(result);
    }

    @Test
    public void seeIfNewTAGDoesntWorkWhenNumbersAreAdded() {
        TypeArea tipo = new TypeArea("cidade");
        TypeAreaList list = new TypeAreaList();
        list.addTypeArea(tipo);
        boolean result = list.newTAG("cidade1");
        assertFalse(result);
    }

    @Test
    public void seeIfMatchGeographicAreaTypeIndexByString() {
        TypeArea type = new TypeArea("cidade");
        TypeAreaList list = new TypeAreaList();
        String input = "cidade";
        list.addTypeArea(type);
        List <Integer> expectedResult = new ArrayList<>();
        expectedResult.add(0);
        List<Integer> result;
        result = list.matchGeographicAreaTypeIndexByString(input);
        assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfMatchGeographicAreaTypeIndexByStringNotMatch() {
        TypeArea type = new TypeArea("cidade");
        TypeAreaList list = new TypeAreaList();
        String input = "distrito";
        list.addTypeArea(type);
        List <Integer> expectedResult = new ArrayList<>();
        List<Integer> result;
        result = list.matchGeographicAreaTypeIndexByString(input);
        assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfPrintGATypeElementsByIndex() {
        TypeArea type1 = new TypeArea("cidade");
        TypeArea type2 = new TypeArea("distrito");
        TypeArea type3 = new TypeArea("aldeia");
        TypeAreaList list = new TypeAreaList();
        list.addTypeArea(type1);
        list.addTypeArea(type2);
        list.addTypeArea(type3);
        list.addTypeArea(type1);
        List <Integer> indexList = new ArrayList<>();
        indexList.add(0);
        indexList.add(2);
       String expectedResult = "---------------\n" +
               "0) Type Area: cidade\n" +
               "2) Type Area: aldeia\n" +
               "---------------\n";
        String result;
        result = list.printGATypeElementsByIndex(indexList);
        assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfPrintGAWholeList() {
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
        result = list.printGATypeWholeList(list);
        assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfPrintGAWholeListFails() {
            TypeAreaList list = new TypeAreaList();
             String expectedResult = "Invalid List - List is Empty\n";
        String result;
        result = list.printGATypeWholeList(list);
        assertEquals(expectedResult, result);
    }
}