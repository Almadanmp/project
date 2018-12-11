package Sprint0_Test.ControllerTest;

import Sprint0.Controller.US01Controller;
import Sprint0.Model.*;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class US01ControllerTest {


    @Test
    public void seeIfnewTAGWorks() {
        TypeArea tipo = new TypeArea("cidade");
        TypeAreaList list = new TypeAreaList();
        list.addTypeArea(tipo);
        US01Controller ctrl = new US01Controller();
        boolean result = ctrl.newTAG("cidade");
        assertTrue(result);
    }

    @Test
    public void seeIfNewTAGDoesntWork(){
        TypeArea tipo = new TypeArea("cidade");
        TypeAreaList list = new TypeAreaList();
        list.addTypeArea(tipo);
        US01Controller ctrl = new US01Controller();
        boolean result = ctrl.newTAG(null);
        assertFalse(result);
    }


}