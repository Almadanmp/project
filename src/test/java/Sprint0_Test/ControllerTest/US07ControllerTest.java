package Sprint0_Test.ControllerTest;

import Sprint0.Controller.US07Controller;
import Sprint0.Model.GeographicArea;
import Sprint0.Model.GeographicAreaList;
import Sprint0.Model.Local;
import Sprint0.Model.TypeArea;
import org.junit.jupiter.api.Test;

import static org.testng.Assert.assertEquals;

public class US07ControllerTest {
    @Test
    public void seeIfMatchGeoArea() {
        GeographicAreaList geographicAreaList = new GeographicAreaList();
        GeographicArea ga1 = new GeographicArea("Porto");
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga1);
        US07Controller ctrl = new US07Controller(geographicAreaList);

        GeographicArea actualResult = ctrl.matchGeoArea("Porto");

        assertEquals(ga1, actualResult);
    }

    @Test
    public void seeIfMatchGeoArea_null() {
        GeographicAreaList geographicAreaList = new GeographicAreaList();
        GeographicArea ga1 = new GeographicArea("Gaia");
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga1);
        US07Controller ctrl = new US07Controller(geographicAreaList);

        GeographicArea actualResult = ctrl.matchGeoArea("Porto");

        assertEquals(null, actualResult);
    }

    @Test
    public void seeIfSetMotherArea() {
        GeographicAreaList geographicAreaList = new GeographicAreaList();
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7);
        GeographicArea ga1 = new GeographicArea("Cedofeita", t1, l1);
        GeographicArea ga2 = new GeographicArea("Porto", t1, l1);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga1);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga2);
        US07Controller ctrl = new US07Controller(geographicAreaList);

        ctrl.setMotherArea(ga1, ga2);

        GeographicArea result = ctrl.getMotherArea();

        assertEquals(ga2, result);

    }

    @Test
    public void seeIfGetGeographicAreaList (){
        GeographicAreaList geographicAreaList = new GeographicAreaList();
        GeographicArea ga1 = new GeographicArea("Porto");
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga1);
        US07Controller ctrl = new US07Controller(geographicAreaList);

        String actualResult = ctrl.printGeographicAreaListNames();

        assertEquals("Geographic Area List: \n" +
                "-Porto;", actualResult);
    }
    @Test
    public void seeIfValidateGeoAreaTrue (){
        GeographicAreaList geographicAreaList = new GeographicAreaList();
        GeographicArea ga1 = new GeographicArea("Porto");
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga1);
        US07Controller ctrl = new US07Controller(geographicAreaList);
        boolean actualResult = ctrl.validateGeoArea("Porto");

        assertEquals(true, actualResult);


    }
    @Test
    public void seeIfValidateGeoAreaFalse (){
        GeographicAreaList geographicAreaList = new GeographicAreaList();
        GeographicArea ga1 = new GeographicArea("Porto");
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga1);
        US07Controller ctrl = new US07Controller(geographicAreaList);
        boolean actualResult = ctrl.validateGeoArea("Gaia");

        assertEquals(false, actualResult);


    }


}
