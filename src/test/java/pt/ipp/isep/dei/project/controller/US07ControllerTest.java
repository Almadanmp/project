package pt.ipp.isep.dei.project.controller;

import pt.ipp.isep.dei.project.model.GeographicArea;
import pt.ipp.isep.dei.project.model.GeographicAreaList;
import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.model.TypeArea;
import org.junit.jupiter.api.Test;

import static org.testng.Assert.*;

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
    public void seeIfMatchGeoAreaNull() {
        GeographicAreaList geographicAreaList = new GeographicAreaList();
        GeographicArea ga1 = new GeographicArea("Gaia");
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga1);
        US07Controller ctrl = new US07Controller(geographicAreaList);

        GeographicArea actualResult = ctrl.matchGeoArea("Porto");

        assertNull(actualResult);
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
    public void seeIfPrintGeographicAreaList (){
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

        assertTrue( actualResult);


    }
    @Test
    public void seeIfValidateGeoAreaFalse (){
        GeographicAreaList geographicAreaList = new GeographicAreaList();
        GeographicArea ga1 = new GeographicArea("Porto");
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga1);
        US07Controller ctrl = new US07Controller(geographicAreaList);
        boolean actualResult = ctrl.validateGeoArea("Gaia");

        assertFalse(actualResult);


    }

    @Test
    public void seeIfGetGeographicAreaList (){
        GeographicAreaList geographicAreaList = new GeographicAreaList();

        TypeArea t1 = new TypeArea("Cidade");
        Local l1 = new Local(38, 7);
        String n1 = "Porto";
        GeographicArea ga1 = new GeographicArea(t1, l1);
        ga1.setName(n1);

        Local l2 = new Local(39, 67);
        String n2 = "Braga";
        GeographicArea ga2 = new GeographicArea(t1, l2);
        ga2.setName(n2);

        geographicAreaList.addGeographicAreaToGeographicAreaList(ga1);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga2);
        US07Controller ctrl = new US07Controller(geographicAreaList);
        GeographicAreaList actualResult = ctrl.getGeographicAreaList();
        assertEquals(geographicAreaList, actualResult);
    }

}
