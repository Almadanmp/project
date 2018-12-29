package pt.ipp.isep.dei.project.controller;

import pt.ipp.isep.dei.project.model.TypeArea;
import pt.ipp.isep.dei.project.model.TypeAreaList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testng.Assert.assertTrue;

public class US02ControllerTest {

    @Test
    public void seeIfPrintTypeAreaListWorks(){
        TypeAreaList list =new TypeAreaList();
        TypeArea t1 = new TypeArea("Rua");
        list.addTypeArea(t1);
        US02Controller ctrl = new US02Controller(list);
        String actualResult = ctrl.getTypeAreaList();
        String expectedResult = "\n" +
                "Area Types List:\n" +
                "\n" +
                "-Rua;";
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfPrintTypeAreaListWorksWithTwoTypes(){
        TypeAreaList list =new TypeAreaList();
        TypeArea t1 = new TypeArea("Rua");
        TypeArea t2 = new TypeArea("Cidade");
        list.addTypeArea(t1);
        list.addTypeArea(t2);
        US02Controller ctrl = new US02Controller(list);
        String actualResult = ctrl.getTypeAreaList();
        String expectedResult = "\n" +
                "Area Types List:\n" +
                "\n" +
                "-Rua;\n" +
                "-Cidade;";
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfPrintTypeAreaListWorksWithThreeTypes(){
        TypeAreaList list =new TypeAreaList();
        TypeArea t1 = new TypeArea("Rua");
        TypeArea t2 = new TypeArea("Cidade");
        TypeArea t3 = new TypeArea("Viela");
        list.addTypeArea(t1);
        list.addTypeArea(t2);
        list.addTypeArea(t3);
        US02Controller ctrl = new US02Controller(list);
        String actualResult = ctrl.getTypeAreaList();
        String expectedResult = "\n" +
                "Area Types List:\n" +
                "\n" +
                "-Rua;\n" +
                "-Cidade;\n" +
                "-Viela;";
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfPrintTypeAreaListWorksWithEmptyList(){
        TypeAreaList list =new TypeAreaList();
        US02Controller ctrl = new US02Controller(list);
        String actualResult = ctrl.getTypeAreaList();
        String expectedResult = "\n" +
                "Area Types List:\n" +
                "\n" +
                "|||| List is Empty ||||\n" +
                "Add types to list first";
        assertEquals(expectedResult, actualResult);
    }
}
