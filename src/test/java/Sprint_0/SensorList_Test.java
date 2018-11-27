package Sprint_0;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class SensorList_Test {

    @Test
    public void seeIfGetAddSensorsWorks() {
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere"),
                new Local(12, 31, 21), new Date());
        Sensor s2 = new Sensor("Chuva", new TypeSensor("Atmosphere"),
                new Local(10, 30, 20), new Date());
        SensorList sl = new SensorList(s1);
        sl.addSensor(s2);
        boolean expectedResult = true;

        boolean actualResult = sl.containsSensor(s1);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfAddSensorWorks() {
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere"),
                new Local(12, 31, 21), new Date());
        Sensor s2 = new Sensor("Chuva", new TypeSensor("Atmosphere"),
                new Local(10, 30, 20), new Date());
        SensorList sl = new SensorList(s1);
        boolean actualResult = sl.containsSensor(s2);
        boolean expectedResult = false;
        assertEquals(expectedResult, actualResult);
        System.out.println(actualResult);
    }

    @Test
    public void seeIfRemoveSensor() {
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere"), new Local(12, 31, 21), new Date());
        Sensor s2 = new Sensor("Chuva", new TypeSensor("Atmosphere"), new Local(10, 30, 20), new Date());
        SensorList lc = new SensorList(s1);
        lc.addSensor(s2);
        lc.removeSensor(s1);
        boolean expectedResult = true;
        boolean result = lc.containsSensor(s2);
        assertEquals(result, expectedResult);
    }

    @Test
    public void seeIfArrayGetSensors() {


    }


}
