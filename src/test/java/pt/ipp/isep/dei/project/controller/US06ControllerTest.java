package pt.ipp.isep.dei.project.controller;

import pt.ipp.isep.dei.project.model.*;
import org.junit.jupiter.api.Test;
import java.util.Date;
import java.util.GregorianCalendar;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testng.Assert.*;

public class US06ControllerTest {

    @Test
    public void seeIfLocalIsCreated() {

        //Arrange
        US06Controller ctrl = new US06Controller();
        Double lat = 50.0;
        Double lon = 50.0;
        Double alt = 50.0;
        ctrl.createLocal(lat,lon,alt);
        Local expectedResult = new Local(50,50,50);

        //Act
        Local actualResult = ctrl.getLocal();

        //Assert
        assertEquals(expectedResult, actualResult);
    }
    @Test
    public void seeIfLocalIsCreated2() {

        //Arrange
        US06Controller ctrl = new US06Controller();
        Double lat = 500.0;
        Double lon = 500.0;
        Double alt = 500.0;
        ctrl.createLocal(lat,lon,alt);
        Local expectedResult = new Local(500,500,500);

        //Act
        Local actualResult = ctrl.getLocal();

        //Assert
        assertEquals(expectedResult, actualResult);
    }
    @Test
    public void seeIfLocalIsCreated3() {

        //Arrange
        US06Controller ctrl = new US06Controller();
        Double lat = -50.0;
        Double lon = -50.0;
        Double alt = -50.0;
        ctrl.createLocal(lat,lon,alt);
        Local expectedResult = new Local(-50,-50,-50);

        //Act
        Local actualResult = ctrl.getLocal();

        //Assert
        assertEquals(expectedResult, actualResult);
    }
    @Test
    public void seeIfTypeIsCreated() {

        //Arrange
        US06Controller ctrl = new US06Controller();
        String typeString = "Humedade";
        ctrl.createType(typeString);
        TypeSensor expectedResult = new TypeSensor("Humedade");

        //Act
        TypeSensor actualResult = ctrl.getType();

        //Assert
        assertEquals(expectedResult, actualResult);
    }
    @Test
    public void seeIfTypeIsCreated2() {

        //Arrange
        US06Controller ctrl = new US06Controller();
        String typeString = "Humedade";
        ctrl.createType(typeString);
        TypeSensor expectedResult = new TypeSensor(typeString);

        //Act
        TypeSensor actualResult = ctrl.getType();

        //Assert
        assertEquals(expectedResult, actualResult);
    }
    @Test
    public void seeIfDateIsCreated() {

        //Arrange
        US06Controller ctrl = new US06Controller();
        int year = 1989;
        int month = 7;
        int day = 12;
        ctrl.createDate(year,month,day);
        Date expectedResult = new GregorianCalendar(1989, 7, 12).getTime();

        //Act
        Date actualResult = ctrl.getDate();

        //Assert
        assertEquals(expectedResult, actualResult);
    }
    @Test
    public void seeIfSensorIsCreated() {

        //Arrange
        US06Controller ctrl = new US06Controller();
        String nameString = "XV-56D";
        Double lat = 50.0;
        Double lon = 50.0;
        Double alt = 50.0;
        Local loc1 = ctrl.createLocal(lat,lon,alt);
        String typeStr = "Humedade";
        TypeSensor type1 = ctrl.createType(typeStr);
        int year = 2018;
        int month = 8;
        int day = 9;
        Date date1 = ctrl.createDate(year,month,day);
        ctrl.createSensor(nameString,type1,loc1,date1);
        TypeSensor t1 = new TypeSensor("Humedade");
        Sensor expectedResult = new Sensor("XV-56D",t1,loc1,new GregorianCalendar(2018, 8,9).getTime());

        //Act
        Sensor actualResult = ctrl.createSensor(nameString,type1,loc1,date1);

        //Assert
        assertEquals(expectedResult, actualResult);
    }
    @Test
    public void seeIfSensorIsCreatedAndGetSensor() {

        //Arrange
        US06Controller ctrl = new US06Controller();
        String nameString = "XV-56D";
        Double lat = 50.0;
        Double lon = 50.0;
        Double alt = 50.0;
        Local loc1 = ctrl.createLocal(lat,lon,alt);
        String typeStr = "Humedade";
        TypeSensor type1 = ctrl.createType(typeStr);
        int year = 2018;
        int month = 8;
        int day = 9;
        Date date1 = ctrl.createDate(year,month,day);
        ctrl.createSensor(nameString,type1,loc1,date1);
        TypeSensor t1 = new TypeSensor("Humedade");
        Sensor expectedResult = new Sensor("XV-56D",t1,loc1,new GregorianCalendar(2018, 8,9).getTime());

        //Act
        Sensor actualResult = ctrl.getSensor();

        //Assert
        assertEquals(expectedResult, actualResult);
    }
    @Test
    public void seeIfSensorIsAddedToSensorList() {

        //Arrange
        US06Controller ctrl = new US06Controller();
        String nameString = "XV-56D";
        Double lat = 50.0;
        Double lon = 50.0;
        Double alt = 50.0;
        Local loc1 = ctrl.createLocal(lat,lon,alt);
        String typeStr = "Humedade";
        TypeSensor type1 = ctrl.createType(typeStr);
        int year = 2018;
        int month = 8;
        int day = 9;
        Date date1 = ctrl.createDate(year,month,day);
        Sensor sens1 = ctrl.createSensor(nameString,type1,loc1,date1);
        SensorList xSensorList = new SensorList();

        //Act
        boolean actualResult = ctrl.addSensor(sens1,xSensorList);

        //Assert
        assertTrue(actualResult);
    }
    @Test
    public void seeIfSensorIsNotAddedToSensorList() {

        //Arrange
        US06Controller ctrl = new US06Controller();
        String nameString = "XV-56D";
        Double lat = 50.0;
        Double lon = 50.0;
        Double alt = 50.0;
        Local loc1 = ctrl.createLocal(lat,lon,alt);
        String typeStr = "Humedade";
        TypeSensor type1 = ctrl.createType(typeStr);
        int year = 2018;
        int month = 8;
        int day = 9;
        Date date1 = ctrl.createDate(year,month,day);
        Sensor sens1 = ctrl.createSensor(nameString,type1,loc1,date1);
        SensorList xSensorList = new SensorList();
        xSensorList.addSensor(sens1);

        //Act
        boolean actualResult = ctrl.addSensor(sens1,xSensorList);

        //Assert
        assertFalse(actualResult);
    }

    @Test
    public void seeIfSensorListIsAddedToGeographicArea() {

        //Arrange
        US06Controller ctrl = new US06Controller();
        String nameString = "XV-56D";
        Double lat = 50.0;
        Double lon = 50.0;
        Double alt = 50.0;
        Local loc1 = ctrl.createLocal(lat,lon,alt);
        String typeStr = "Humedade";
        TypeSensor type1 = ctrl.createType(typeStr);
        int year = 2018;
        int month = 8;
        int day = 9;
        Date date1 = ctrl.createDate(year,month,day);
        Sensor sens1 = ctrl.createSensor(nameString,type1,loc1,date1);
        SensorList xSensorList = new SensorList();
        xSensorList.addSensor(sens1);
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7);
        GeographicArea areaG = new GeographicArea(t1,l1);
        areaG.setName("Alegria");

        String areaNameInput = "Alegria";
        GeographicAreaList xgaList = new GeographicAreaList();
        xgaList.addGeographicAreaToGeographicAreaList(areaG);

        //Act
        boolean actualResult = ctrl.addSensorToGeographicArea(areaNameInput, xgaList, xSensorList);

        //Assert
        assertTrue(actualResult);
    }
    @Test
    public void seeIfSensorListIsAddedToGeographicAreaFalse() {

        //Arrange
        US06Controller ctrl = new US06Controller();
        String nameString = "XV-56D";
        Double lat = 50.0;
        Double lon = 50.0;
        Double alt = 50.0;
        Local loc1 = ctrl.createLocal(lat,lon,alt);
        String typeStr = "Humedade";
        TypeSensor type1 = ctrl.createType(typeStr);
        int year = 2018;
        int month = 8;
        int day = 9;
        Date date1 = ctrl.createDate(year,month,day);
        Sensor sens1 = ctrl.createSensor(nameString,type1,loc1,date1);
        SensorList xSensorList = new SensorList();
        xSensorList.addSensor(sens1);
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7);
        GeographicArea areaG = new GeographicArea(t1,l1);
        areaG.setName("Alegria");

        String areaNameInput = "Direita";
        GeographicAreaList xgaList = new GeographicAreaList();
        xgaList.addGeographicAreaToGeographicAreaList(areaG);

        //Act
        boolean actualResult = ctrl.addSensorToGeographicArea(areaNameInput, xgaList, xSensorList);

        //Assert
        assertFalse(actualResult);
    }
    @Test
    public void seeIfSensorListIsAddedToGeographicAreaEmptyList() {

        //Arrange
        US06Controller ctrl = new US06Controller();
        String nameString = "XV-56D";
        Double lat = 50.0;
        Double lon = 50.0;
        Double alt = 50.0;
        Local loc1 = ctrl.createLocal(lat,lon,alt);
        String typeStr = "Humedade";
        TypeSensor type1 = ctrl.createType(typeStr);
        int year = 2018;
        int month = 8;
        int day = 9;
        Date date1 = ctrl.createDate(year,month,day);
        Sensor sens1 = ctrl.createSensor(nameString,type1,loc1,date1);
        SensorList xSensorList = new SensorList();
        xSensorList.addSensor(sens1);
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7);
        GeographicArea areaG = new GeographicArea(t1,l1);
        areaG.setName("Alegria");

        String areaNameInput = "Alegria";
        GeographicAreaList xgaList = new GeographicAreaList();

        //Act
        boolean actualResult = ctrl.addSensorToGeographicArea(areaNameInput, xgaList, xSensorList);

        //Assert
        assertFalse(actualResult);
    }
}