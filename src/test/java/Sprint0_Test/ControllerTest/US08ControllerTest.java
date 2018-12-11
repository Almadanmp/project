package Sprint0_Test.ControllerTest;

import Sprint0.Controller.US08Controller;
import Sprint0.Model.GeographicArea;
import Sprint0.Model.GeographicAreaList;
import Sprint0.Model.Local;
import Sprint0.Model.TypeArea;
import Sprint0.UI.MainUI;
import org.junit.jupiter.api.Test;
import org.testng.reporters.jq.Main;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class US08ControllerTest {

    @Test
    public void seeIfSetGeographicAreasAddValidGAs(){
        //Arrange
        US08Controller controller = new US08Controller();
        Local l1 = new Local(43,55);
        TypeArea t1 = new TypeArea("Cidade");
        Local l2 = new Local(40,50);
        TypeArea t2 = new TypeArea("Rua");
        GeographicArea ga1 = new GeographicArea("Porto",t1, l1);
        GeographicArea ga2 = new GeographicArea("Rua Portuense",t2, l2);
        GeographicAreaList lista = new GeographicAreaList(ga1);
        lista.addGeographicAreaToGeographicAreaList(ga2);
        //Act
        boolean result = controller.setGeographicAreas("Porto","Rua Portuense",lista);
        //Assert
        assertEquals(true,result);
    }

    @Test
    public void seeIfSetGeographicAreasAddInvalidGAs(){
        //Arrange
        US08Controller controller = new US08Controller();
        Local l1 = new Local(43,55);
        TypeArea t1 = new TypeArea("Cidade");
        Local l2 = new Local(40,50);
        TypeArea t2 = new TypeArea("Rua");
        GeographicArea ga1 = new GeographicArea("Porto",t1, l1);
        GeographicArea ga2 = new GeographicArea("Rua Portuense",t2, l2);
        GeographicAreaList lista = new GeographicAreaList(ga1);
        lista.addGeographicAreaToGeographicAreaList(ga2);
        //Act
        boolean result = controller.setGeographicAreas("Porto","Rua dos Bragas",lista);
        //Assert
        assertEquals(false,result);
    }
}