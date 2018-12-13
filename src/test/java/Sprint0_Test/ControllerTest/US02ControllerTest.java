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
        TypeArea t1 = new TypeArea("rua");
        list.addTypeArea(t1);
        String result = list.printTypeAreaList();
        String expectedResult = "\n" +
                "Area Types List:\n" +
                " \n" +
                "-rua;";
        assertEquals(result,expectedResult);
    }

    @Test
    public void seeIfPrintTypeAreaListWorksWithTwoTypes(){
        TypeAreaList list =new TypeAreaList();
        TypeArea t1 = new TypeArea("rua");
        TypeArea t2 = new TypeArea("cidade");
        list.addTypeArea(t1);
        list.addTypeArea(t2);
        String result = list.printTypeAreaList();
        String expectedResult = "\n" +
                "Area Types List:\n" +
                " \n" +
                "-rua; \n" +
                "-cidade;";
        assertEquals(result,expectedResult);
    }

}
