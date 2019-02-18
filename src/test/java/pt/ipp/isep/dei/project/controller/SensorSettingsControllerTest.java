package pt.ipp.isep.dei.project.controller;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;

import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testng.Assert.*;

/**
 * SensorSettingsController tests class.
 */

class SensorSettingsControllerTest {

    //USER STORY 006 TESTS

    @Test
    void seeIfLocalIsCreated() {

        //Arrange
        SensorSettingsController ctrl = new SensorSettingsController();
        double lat = 50.0;
        double lon = 50.0;
        double alt = 50.0;
        Local expectedResult = new Local(50, 50, 50);

        //Act
        Local actualResult = ctrl.createLocal(lat, lon, alt);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfTypeIsCreated() {

        //Arrange
        SensorSettingsController ctrl = new SensorSettingsController();
        String typeString = "Humidade";
        String units = "kg/m³";
        String expectedResult = "Humidade";

        //Act
        String actualResult = ctrl.createType(typeString, units).getName();

        //Assert
        assertEquals(expectedResult, actualResult);
    }


    @Test
    void seeIfDateIsCreated() {

        //Arrange
        SensorSettingsController ctrl = new SensorSettingsController();
        int year = 1989;
        int month = 7;
        int day = 12;
        Date expectedResult = new GregorianCalendar(1989, 7, 12).getTime();

        //Act
        Date actualResult = ctrl.createDate(year, month, day);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSensorIsCreated() {

        //Arrange
        SensorSettingsController ctrl = new SensorSettingsController();
        String nameString = "XV-56D";
        double lat = 50.0;
        double lon = 50.0;
        double alt = 50.0;
        Local loc1 = ctrl.createLocal(lat, lon, alt);
        String typeStr = "Humedade";
        String unit = "kg/m³";
        TypeSensor type1 = ctrl.createType(typeStr, unit);
        int year = 2018;
        int month = 8;
        int day = 9;
        Date date1 = ctrl.createDate(year, month, day);
        ctrl.createSensor(nameString, type1, loc1, date1);
        TypeSensor t1 = new TypeSensor("Humidade", "kg/m³");
        Sensor expectedResult = new Sensor("XV-56D", t1, loc1, new GregorianCalendar(2018, 8, 9).getTime());

        //Act
        Sensor actualResult = ctrl.createSensor(nameString, type1, loc1, date1);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

//    @Test
//    void seeIfAddSensorToSensorList() {
//        SensorSettingsController ctrl = new SensorSettingsController();
//        GeographicArea ga = new GeographicArea();
//        Sensor sensor = new Sensor();
//        ga.addSensorToSensorList(sensor);
//        boolean result = ctrl.addSensorToGeographicArea(ga);
//        boolean expectedResult = true;
//        assertEquals(expectedResult, result);
//    }

    @Test
    void seeIfAddSensorToSensorListFalse() {
        GeographicArea ga = new GeographicArea("porto", new TypeArea("cidade"), 1, 1, new Local(1, 1, 1));
        Sensor sensor = new Sensor("coise", new TypeSensor("temp", "celsius"), new Local(1, 1, 1), new GregorianCalendar(1, 1, 1, 1, 1).getTime());
        Sensor sensor1 = new Sensor("coise1", new TypeSensor("temp", "celsius"), new Local(1, 1, 1), new GregorianCalendar(1, 1, 1, 1, 1).getTime());
        SensorList sensorList = new SensorList();
        sensorList.addSensor(sensor);
        ga.setSensorList(sensorList);
        boolean result = ga.addSensorToSensorList(sensor1);
        boolean expectedResult = true;
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfPrintGAList() {
        //Arrange
        GeographicArea gA1 = new GeographicArea("Portugal", new TypeArea("Country"), 2, 5, new Local(21, 33, 5));
        GeographicArea gA2 = new GeographicArea("Oporto", new TypeArea("City"), 2, 4, new Local(14, 14, 5));
        GeographicArea gA3 = new GeographicArea("Lisbon", new TypeArea("Village"), 2, 4, new Local(3, 3, 5));
        GeographicAreaList gAL1 = new GeographicAreaList();
        gAL1.addGeographicAreaToGeographicAreaList(gA1);
        gAL1.addGeographicAreaToGeographicAreaList(gA2);
        gAL1.addGeographicAreaToGeographicAreaList(gA3);

        //Act
        String expectedResult = "---------------\n" +
                "0) Name: Portugal | Type: Country | Latitude: 21.0 | Longitude: 33.0\n" +
                "1) Name: Oporto | Type: City | Latitude: 14.0 | Longitude: 14.0\n" +
                "2) Name: Lisbon | Type: Village | Latitude: 3.0 | Longitude: 3.0\n" +
                "---------------\n";
        SensorSettingsController ctrl = new SensorSettingsController();
        String result = ctrl.buildGAListString(gAL1);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfTypeListIsPrinted() {
        //Arrange
        TypeSensorList list1 = new TypeSensorList();
        TypeSensor t1 = new TypeSensor("rain", "mm");
        TypeSensor t2 = new TypeSensor("wind", "km/h");
        list1.add(t1);
        list1.add(t2);
        //Act
        SensorSettingsController ctrl = new SensorSettingsController();
        String result = "---------------\n" +
                "0) Name: rain | Unit: mm\n" +
                "1) Name: wind | Unit: km/h\n" +
                "---------------\n";
        String actualResult = ctrl.buildSensorTypesString(list1);
        //Assert
        assertEquals(result, actualResult);
    }

    @Test
    void ensureThatWeCreateARoomSensor() {

        //Arrange
        SensorSettingsController ctrl = new SensorSettingsController();
        String nameString = "XV-56D";
        String typeStr = "Temperatura";
        String unit = "Celsius";
        TypeSensor type1 = ctrl.createType(typeStr, unit);
        int year = 2018;
        int month = 8;
        int day = 9;
        Date date1 = ctrl.createDate(year, month, day);
        ctrl.createRoomSensor(nameString, type1, date1);
        TypeSensor t1 = new TypeSensor("Temperature", "Celsius³");
        Sensor expectedResult = new Sensor("XV-56D", t1, new GregorianCalendar(2018, 8, 9).getTime());

        //Act
        Sensor actualResult = ctrl.createRoomSensor(nameString, type1, date1);

        //Assert
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void addTypeSensorToList() {
        SensorSettingsController ctrl = new SensorSettingsController();
        TypeSensor typeSensor1 = new TypeSensor("temperature", "celsius");
        TypeSensor typeSensor2 = new TypeSensor("temperature", "kelvin");
        TypeSensor typeSensor3 = new TypeSensor("temperature", "celsius");
        TypeSensor typeSensor4 = new TypeSensor("humidity", "percentage");
        TypeSensorList typeList = new TypeSensorList();
        //ACT
        boolean actualResult1 = ctrl.addTypeSensorToList(typeSensor1, typeList);
        boolean actualResult2 = ctrl.addTypeSensorToList(typeSensor2, typeList);
        boolean actualResult3 = ctrl.addTypeSensorToList(typeSensor3, typeList);
        boolean actualResult4 = ctrl.addTypeSensorToList(typeSensor4, typeList);

        //ASSERT
        assertTrue(actualResult1);
        assertTrue(actualResult2);
        assertFalse(actualResult3);
        assertTrue(actualResult4);
    }
    @Test
    void addSensorToGeographicArea() {
        SensorSettingsController ctrl = new SensorSettingsController();
        GeographicArea ga1 = new GeographicArea("Porto", new TypeArea("City"), 2, 3, new Local(4, 4, 100));
        Sensor sensor1 = new Sensor("sensor1", new TypeSensor("temperature", "celsius"), new Local(1, 1, 1), new GregorianCalendar(1, 1, 1, 1, 1).getTime());
        Sensor sensor2 = new Sensor("sensor1", new TypeSensor("temperature", "celsius"), new Local(1, 1, 1), new GregorianCalendar(1, 1, 1, 1, 1).getTime());
        Sensor sensor3 = new Sensor("sensor3", new TypeSensor("temperature", "celsius"), new Local(1, 1, 1), new GregorianCalendar(1, 1, 1, 1, 1).getTime());

        //ACT
        boolean actualResult1 = ctrl.addSensorToGeographicArea(sensor1, ga1);
        boolean actualResult2 = ctrl.addSensorToGeographicArea(sensor2, ga1);
        boolean actualResult3 = ctrl.addSensorToGeographicArea(sensor3, ga1);

        //ASSERT
        assertTrue(actualResult1);
        assertFalse(actualResult2);
        assertTrue(actualResult3);
    }

    // Testing for method: public String buildSensorString(Sensor sensor)
    @Test
    void testBuildSensorString() {
        //Arrange
        SensorSettingsController ctrl = new SensorSettingsController();
        Sensor sensor = new Sensor("sensor", new TypeSensor("temperature", "celsius"), new Local(1, 1, 1), new GregorianCalendar(1, 1, 1, 1, 1).getTime());
        //Act
        String expectedResult = "sensor, temperature, 1.0º lat, 1.0º long\n";
        String actualResult = ctrl.buildSensorString(sensor);
        //Assert
        assertEquals(expectedResult, actualResult);
    }

//    @Test
//    void seeIfAddSensorToSensorListFalsen() {
//        GeographicArea ga = new GeographicArea();
//        Sensor sensor = new Sensor();
//        SensorList sensorList = new SensorList();
//        sensorList.addSensor(sensor);
//        ga.setSensorList(sensorList);
//        boolean result = ga.addSensorToSensorList(sensor);
//        boolean expectedResult = false;
//        assertEquals(expectedResult, result);
//    }

/*    @Test
    void seeIfSensorListIsAddedToGeographicArea() {

        //Arrange
        SensorSettingsController ctrl = new SensorSettingsController();
        String nameString = "XV-56D";
        double lat = 50.0;
        double lon = 50.0;
        double alt = 50.0;
        Local loc1 = ctrl.createLocal(lat, lon, alt);
        String typeStr = "Humedade";
        String unit = "kg/m³";
        TypeSensor type1 = ctrl.createType(typeStr, unit);
        int year = 2018;
        int month = 8;
        int day = 9;
        Date date1 = ctrl.createDate(year, month, day);
        Sensor sens1 = ctrl.createSensor(nameString, type1, loc1, date1);
        SensorList xSensorList = new SensorList();
        xSensorList.addSensor(sens1);
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7, 100);
        GeographicArea areaG = new GeographicArea("Porto", t1, 2, 3, l1);
        areaG.setId("Alegria");

        GeographicAreaList xgaList = new GeographicAreaList();
        xgaList.addGeographicAreaToGeographicAreaList(areaG);

        //Act
        boolean actualResult = ctrl.addSensorToGeographicArea(areaG);
        //Assert
        assertTrue(actualResult);
    }

    @Test
    void seeIfSensorListIsAddedToGeographicAreaFalse() {

        //Arrange
        SensorSettingsController ctrl = new SensorSettingsController();
        String nameString = "XV-56D";
        double lat = 50.0;
        double lon = 50.0;
        double alt = 50.0;
        Local loc1 = ctrl.createLocal(lat, lon, alt);
        String typeStr = "Humedade";
        String unit = "kg/m³";
        TypeSensor type1 = ctrl.createType(typeStr, unit);
        int year = 2018;
        int month = 8;
        int day = 9;
        Date date1 = ctrl.createDate(year, month, day);
        Sensor sens1 = ctrl.createSensor(nameString, type1, loc1, date1);
        SensorList xSensorList = new SensorList();
        xSensorList.addSensor(sens1);
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7, 100);
        GeographicArea areaG = new GeographicArea("Porto", t1, 2, 3, l1);
        areaG.setId("Alegria");
        GeographicAreaList xgaList = new GeographicAreaList();
        xgaList.addGeographicAreaToGeographicAreaList(areaG);

        //Act
        boolean actualResult = ctrl.addSensorToGeographicArea(areaG);

        //Assert
        assertFalse(actualResult);
    }

    @Test
    void seeIfSensorListIsAddedToGeographicAreaEmptyList() {

        //Arrange
        SensorSettingsController ctrl = new SensorSettingsController();
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7, 100);
        GeographicArea areaG = new GeographicArea("Porto", t1, 2, 3, l1);

        //Act
        boolean actualResult = ctrl.addSensorToGeographicArea(areaG);

        //Assert
        assertFalse(actualResult);
    }*/


   /*
    @Test
    void seeIfSetTypeWorksFalse() {

       Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere", "Km/h"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 10, 4).getTime());
        Sensor s2 = new Sensor("Chuva", new TypeSensor("Atmosphere", "l/m2"),
                new Local(10, 30, 20),
                new GregorianCalendar(118, 1, 4).getTime());
        SensorList lc = new SensorList();
        lc.addSensor(s1);
        lc.addSensor(s2);
        boolean expectedResult = false;
        SensorSettingsController ctrl = new SensorSettingsController();

        //Act
        boolean actualResult = ctrl.setTypeSensor(lc,"Portugal", "Movement");

        //Assert
    /*
        assertEquals(expectedResult, actualResult);
    }

/*
@Test
void seeIfSetTypeWorks() {
    //Arrange
    Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere", "Km/h"),
            new Local(12, 31, 21),
            new GregorianCalendar(118, 10, 4).getTime());
    Sensor s2 = new Sensor("Chuva", new TypeSensor("Atmosphere", "l/m2"),
            new Local(10, 30, 20),
            new GregorianCalendar(118, 1, 4).getTime());
    SensorList lc = new SensorList();
    lc.addSensor(s1);
    lc.addSensor(s2);
    boolean expectedResult = true;
    SensorSettingsController ctrl = new SensorSettingsController();

    //Act
    boolean actualResult = ctrl.setTypeSensor("Vento", "Movement");

    //Assert
    assertEquals(expectedResult, actualResult);
}
*/

}
