package Sprint0_Test.ControllerTest;

import Sprint0.Controller.US08Controller;
import Sprint0.Model.GeographicArea;
import Sprint0.Model.GeographicAreaList;
import Sprint0.Model.Local;
import Sprint0.Model.TypeArea;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class US08ControllerTest {

    @Test
    public void seeIfAreaContainedMatchToAreasOfList(){
        GeographicAreaList list = new GeographicAreaList();
        GeographicArea area1 = new GeographicArea("Porto", new TypeArea("Cidade"), new Local(45,45));
        GeographicArea area2 = new GeographicArea("Portugal", new TypeArea("Pais"), new Local(45,45));
        GeographicArea area3 = new GeographicArea("Europa", new TypeArea("Continente"), new Local(45,45));
        list.addGeographicAreaToGeographicAreaList(area1);
        list.addGeographicAreaToGeographicAreaList(area2);
        list.addGeographicAreaToGeographicAreaList(area3);
        US08Controller ctrl = new US08Controller(list);

        ctrl.matchGeographicAreas("Porto","Europa");
        GeographicArea expectedResult = area1;
        GeographicArea actualResult = ctrl.getmGeographicAreaContained();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfAreaContainerMatchToAreasOfList(){
        GeographicAreaList list = new GeographicAreaList();
        GeographicArea area1 = new GeographicArea("Porto", new TypeArea("Cidade"), new Local(45,45));
        GeographicArea area2 = new GeographicArea("Portugal", new TypeArea("Pais"), new Local(45,45));
        GeographicArea area3 = new GeographicArea("Europa", new TypeArea("Continente"), new Local(45,45));
        list.addGeographicAreaToGeographicAreaList(area1);
        list.addGeographicAreaToGeographicAreaList(area2);
        list.addGeographicAreaToGeographicAreaList(area3);
        US08Controller ctrl = new US08Controller(list);

        ctrl.matchGeographicAreas("Porto","Europa");
        GeographicArea expectedResult = area3;
        GeographicArea actualResult = ctrl.getmGeographicAreaContainer();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfAreaContainedMatchToAreasOfListFails(){
        GeographicAreaList list = new GeographicAreaList();
        GeographicArea area1 = new GeographicArea("Porto", new TypeArea("Cidade"), new Local(45,45));
        GeographicArea area2 = new GeographicArea("Portugal", new TypeArea("Pais"), new Local(45,45));
        GeographicArea area3 = new GeographicArea("Europa", new TypeArea("Continente"), new Local(45,45));
        list.addGeographicAreaToGeographicAreaList(area1);
        list.addGeographicAreaToGeographicAreaList(area2);
        list.addGeographicAreaToGeographicAreaList(area3);
        US08Controller ctrl = new US08Controller(list);

        boolean expectedResult = false;
        boolean actualResult = ctrl.matchGeographicAreas("Lisboa", "Europa");

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfAreaContainedInAnotherAreaWorks(){
        GeographicAreaList list = new GeographicAreaList();
        GeographicArea area1 = new GeographicArea("Porto", new TypeArea("Cidade"), new Local(45,45));
        GeographicArea area2 = new GeographicArea("Portugal", new TypeArea("Pais"), new Local(45,45));
        GeographicArea area3 = new GeographicArea("Europa", new TypeArea("Continente"), new Local(45,45));
        list.addGeographicAreaToGeographicAreaList(area1);
        list.addGeographicAreaToGeographicAreaList(area2);
        list.addGeographicAreaToGeographicAreaList(area3);
        US08Controller ctrl = new US08Controller(list);

        area1.setMotherArea(area2);
        area2.setMotherArea(area3);
        ctrl.matchGeographicAreas("Porto","Europa");
        boolean actualResult = ctrl.seeIfItsContained();
        boolean expectedResult = true;

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfAreaContainedInAnotherAreaFails(){
        GeographicAreaList list = new GeographicAreaList();
        GeographicArea area1 = new GeographicArea("Porto", new TypeArea("Cidade"), new Local(45,45));
        GeographicArea area2 = new GeographicArea("Portugal", new TypeArea("Pais"), new Local(45,45));
        GeographicArea area3 = new GeographicArea("Europa", new TypeArea("Continente"), new Local(45,45));
        list.addGeographicAreaToGeographicAreaList(area1);
        list.addGeographicAreaToGeographicAreaList(area2);
        list.addGeographicAreaToGeographicAreaList(area3);
        US08Controller ctrl = new US08Controller(list);

        area1.setMotherArea(area2);
        ctrl.matchGeographicAreas("Porto","Europa");
        boolean actualResult = ctrl.seeIfItsContained();
        boolean expectedResult = false;

        assertEquals(expectedResult, actualResult);
    }
}
