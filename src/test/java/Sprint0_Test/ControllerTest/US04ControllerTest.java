package Sprint0_Test.ControllerTest;

import Sprint0.Controller.US04Controller;
import Sprint0.Model.GeographicArea;
import Sprint0.Model.GeographicAreaList;
import Sprint0.Model.Local;
import Sprint0.Model.TypeArea;
import org.junit.Test;

import static org.testng.Assert.*;

public class US04ControllerTest {

    @Test
    public void seeIfConstructorSetsGeographicArea() {

        //Arrange
        GeographicAreaList geographicAreaList = new GeographicAreaList();
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7);
        GeographicArea ga1 = new GeographicArea(t1, l1);
        TypeArea t2 = new TypeArea("Cidade");
        Local l2 = new Local(40, 7);
        GeographicArea ga2 = new GeographicArea(t2, l2);
        TypeArea t3 = new TypeArea("Rua");
        Local l3 = new Local(38, 59);
        GeographicArea ga3 = new GeographicArea(t3, l3);
        TypeArea t4 = new TypeArea("Montanha");
        Local l4 = new Local(38, 32);
        GeographicArea ga4 = new GeographicArea(t4, l4);

        geographicAreaList.addGeographicAreaToGeographicAreaList(ga1);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga4);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga2);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga3);
        US04Controller ctrl = new US04Controller(geographicAreaList);
        GeographicAreaList expectedResult = new GeographicAreaList();
        expectedResult.addGeographicAreaToGeographicAreaList(ga1);
        expectedResult.addGeographicAreaToGeographicAreaList(ga2);
        expectedResult.addGeographicAreaToGeographicAreaList(ga3);
        expectedResult.addGeographicAreaToGeographicAreaList(ga4);
        GeographicAreaList actualResult = new GeographicAreaList();
        //Act
        ctrl.getGeographicAreaList();

        //Assert
        assertEquals(expectedResult, actualResult);
    }



}