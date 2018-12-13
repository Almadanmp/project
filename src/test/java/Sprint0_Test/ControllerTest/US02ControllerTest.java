package Sprint0_Test.ControllerTest;

import Sprint0.Controller.US02Controller;
import Sprint0.Model.TypeArea;
import Sprint0.Model.TypeAreaList;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testng.Assert.assertTrue;

public class US02ControllerTest {

    @Test
    public void seeIfPrintTypeAreaListWorks(){
        TypeAreaList list =new TypeAreaList();
        TypeArea t1 = new TypeArea("Rua");
        list.addTypeArea(t1);
        String result = list.printTypeAreaList();
        String expectedResult = "\n" +
                "Area Types List:\n" +
                "\n" +
                "-Rua;";
        assertEquals(result,expectedResult);
    }

    @Test
    public void seeIfPrintTypeAreaListWorksWithTwoTypes(){
        TypeAreaList list =new TypeAreaList();
        TypeArea t1 = new TypeArea("Rua");
        TypeArea t2 = new TypeArea("Cidade");
        list.addTypeArea(t1);
        list.addTypeArea(t2);
        String result = list.printTypeAreaList();
        String expectedResult = "\n" +
                "Area Types List:\n" +
                "\n" +
                "-Rua;\n" +
                "-Cidade;";
        assertEquals(result,expectedResult);
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
        String result = list.printTypeAreaList();
        String expectedResult = "\n" +
                "Area Types List:\n" +
                "\n" +
                "-Rua;\n" +
                "-Cidade;\n" +
                "-Viela;";
        assertEquals(result,expectedResult);
    }
}
