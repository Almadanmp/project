package Sprint0_Test.ControllerTest;

import Sprint0.Controller.US01Controller;
import Sprint0.Model.*;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class US01ControllerTest {


    @Test
    public void seeIfnewTAGWorks() {
        TypeArea tipo = new TypeArea("cidade");
        TypeAreaList list = new TypeAreaList();
        list.addTypeArea(tipo);
        US01Controller ctrl = new US01Controller();
        boolean result = ctrl.newTAG("cidade");
        boolean expectedResult = true;
        assertEquals(expectedResult, result);
    }

}