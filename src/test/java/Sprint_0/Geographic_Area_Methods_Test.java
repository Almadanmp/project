package Sprint_0;

import org.junit.Test;

import java.lang.reflect.Type;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class Geographic_Area_Methods_Test {


    @Test
    public void getLinearDistanceBetweenTwoGeographicAreas(){
        Local l1 = new Local(23,46);
        Local l2 = new Local(25,47);
        TypeArea t1 = new TypeArea("Porto");
        TypeArea t2 = new TypeArea("Braga");
        Geographic_Area ga1 = new Geographic_Area(t1, l1);
        Geographic_Area ga2 = new Geographic_Area(t2, l2);
        ga1.setLocal(l1);
        ga2.setLocal(l2);
        double result = Geographic_Area_Methods.getLinearDistanceBetweenTwoPoints( ga1, ga2);
        double expectedresult = 244;
        assertEquals(expectedresult, result, 1);
    }
}
