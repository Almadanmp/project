package Sprint0_Test.ControllerTest;


import Sprint0.Controller.US03Controller;
import Sprint0.Model.GeographicArea;
import Sprint0.Model.GeographicAreaList;
import Sprint0.Model.Local;
import Sprint0.Model.TypeArea;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.testng.Assert.assertTrue;


public class US03ControllerTest {

    @Test
    public void seeIfCreatesGeographicAreaAndAddsItToList() {
        GeographicAreaList geographicAreaList = new GeographicAreaList();
        String name1 = "Porto";
        TypeArea t1 = new TypeArea("Distrito");
        Local l1 = new Local(38, 7);
        GeographicArea g1 = new GeographicArea(name1, t1, l1);
        US03Controller us3 = new US03Controller(g1, geographicAreaList);

        boolean result = us3.addNewGeoArea(g1, geographicAreaList);

        assertTrue(result);
    }

    @Test
    public void seeIfFailsCreatingSecondEqualGeographicArea() {
        GeographicAreaList geolist1 = new GeographicAreaList();

        String name1 = "Porto";
        TypeArea t1 = new TypeArea("Distrito");
        Local l1 = new Local(38, 7);
        GeographicArea g1 = new GeographicArea(name1, t1, l1);

        String name2 = "Porto";
        TypeArea t2 = new TypeArea("Distrito");
        Local l2 = new Local(38, 7);
        GeographicArea g2 = new GeographicArea(name2, t2, l2);
        geolist1.addGeographicAreaToGeographicAreaList(g1);

        US03Controller us3 = new US03Controller(g1, geolist1);

        boolean result = us3.addNewGeoArea(g2, geolist1);

        assertFalse(result);
    }

    @Test
    public void seeIfCreatesGeographicAreaWithNullNameAndThrowsException() {
        GeographicAreaList geolist1 = new GeographicAreaList();

        //Arrange
        String name1 = "Porto";
        TypeArea tA1 = new TypeArea("Distrito");
        Local l1 = new Local(11, 12);
        GeographicArea gA1 = new GeographicArea(name1, tA1, l1);

        TypeArea tA2 = new TypeArea("rua");
        Local l2 = new Local(11, 12);
        GeographicArea gA2 = new GeographicArea(null, tA2, l2);

        US03Controller us3 = new US03Controller(gA1, geolist1);

        //Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            us3.addNewGeoArea(gA2, geolist1);
        });

        //Assert
        assertEquals("Please Insert Valid Name", exception.getMessage());
    }

}