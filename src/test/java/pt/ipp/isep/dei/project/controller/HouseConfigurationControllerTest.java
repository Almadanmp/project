package pt.ipp.isep.dei.project.controller;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;

import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

class HouseConfigurationControllerTest {
    
    //USER STORY 001 TESTS

    @Test
    void seeIfNewTAGWorks() {
        TypeAreaList newList = new TypeAreaList();
        HouseConfigurationController ctrl = new HouseConfigurationController(newList);
        boolean result = ctrl.createAndAddTypeAreaToList("cidade");
        assertTrue(result);
    }

    @Test
    void seeIfNewTAGWorksWithAnother() {
        TypeArea tipo = new TypeArea("rua");
        TypeAreaList newList = new TypeAreaList();
        newList.addTypeArea(tipo);
        HouseConfigurationController ctrl = new HouseConfigurationController(newList);
        boolean result = ctrl.createAndAddTypeAreaToList("cidade");
        assertTrue(result);
    }

    @Test
    void seeIfNewTAGDoesNotWorkWhenDuplicatedISAdded() {
        TypeArea tipo = new TypeArea("cidade");
        TypeAreaList expectedResult = new TypeAreaList();
        expectedResult.addTypeArea(tipo);
        HouseConfigurationController ctrl = new HouseConfigurationController(expectedResult);
        boolean result = ctrl.createAndAddTypeAreaToList("cidade");
        assertFalse(result);
    }

    @Test
    void seeIfNewTAGDoesntWorkWhenNullIsAdded(){
        TypeArea tipo = new TypeArea("cidade");
        TypeAreaList list = new TypeAreaList();
        list.addTypeArea(tipo);
        HouseConfigurationController ctrl = new HouseConfigurationController(list);
        boolean result = ctrl.createAndAddTypeAreaToList(null);
        assertFalse(result);
    }

    @Test
    void seeIfNewTAGDoesntWorkWhenNameIsEmpty(){
        TypeArea tipo = new TypeArea("cidade");
        TypeAreaList list = new TypeAreaList();
        list.addTypeArea(tipo);
        HouseConfigurationController ctrl = new HouseConfigurationController(list);
        boolean result = ctrl.createAndAddTypeAreaToList("");
        assertFalse(result);
    }
    @Test
    void seeIfNewTAGDoesntWorkWhenNumbersAreAdded(){
        TypeArea tipo = new TypeArea("cidade");
        TypeAreaList list = new TypeAreaList();
        list.addTypeArea(tipo);
        HouseConfigurationController ctrl = new HouseConfigurationController(list);
        boolean result = ctrl.createAndAddTypeAreaToList("cidade1");
        assertFalse(result);
    }

    //USER STORY 005 TESTS

    @Test
    void seeIfConstructorWorks() {
        //Arrange
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 10, 4).getTime());
        Sensor s2 = new Sensor("Chuva", new TypeSensor("Atmosphere"),
                new Local(10, 30, 20),
                new GregorianCalendar(118, 1, 4).getTime());
        SensorList lc = new SensorList(new Sensor[]{s1, s2});
        SensorList expectedResult = new SensorList();


        //Act
        expectedResult.addSensor(s1);
        expectedResult.addSensor(s2);
        HouseConfigurationController constructedList = new HouseConfigurationController(lc);
        SensorList actualResult = constructedList.getSensorList();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSetTypeWorksFalse() {
        //Arrange
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 10, 4).getTime());
        Sensor s2 = new Sensor("Chuva", new TypeSensor("Atmosphere"),
                new Local(10, 30, 20),
                new GregorianCalendar(118, 1, 4).getTime());
        SensorList lc = new SensorList();
        lc.addSensor(s1);
        lc.addSensor(s2);
        boolean expectedResult = false;
        HouseConfigurationController ctrl = new HouseConfigurationController(lc);

        //Act
        boolean actualResult = ctrl.setTypeSensor("Portugal", "Movement");

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSetTypeWorks() {
        //Arrange
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 10, 4).getTime());
        Sensor s2 = new Sensor("Chuva", new TypeSensor("Atmosphere"),
                new Local(10, 30, 20),
                new GregorianCalendar(118, 1, 4).getTime());
        SensorList lc = new SensorList();
        lc.addSensor(s1);
        lc.addSensor(s2);
        boolean expectedResult = true;
        HouseConfigurationController ctrl = new HouseConfigurationController(lc);

        //Act
        boolean actualResult = ctrl.setTypeSensor("Vento", "Movement");

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    //USER STORY 06 TESTS

    @Test
    void seeIfLocalIsCreated() {

        //Arrange
        HouseConfigurationController ctrl = new HouseConfigurationController();
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
    void seeIfLocalIsCreated2() {

        //Arrange
        HouseConfigurationController ctrl = new HouseConfigurationController();
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
    void seeIfLocalIsCreated3() {

        //Arrange
        HouseConfigurationController ctrl = new HouseConfigurationController();
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
    void seeIfTypeIsCreated() {

        //Arrange
        HouseConfigurationController ctrl = new HouseConfigurationController();
        String typeString = "Humedade";
        ctrl.createType(typeString);
        TypeSensor expectedResult = new TypeSensor("Humedade");

        //Act
        TypeSensor actualResult = ctrl.getType();

        //Assert
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void seeIfTypeIsCreated2() {

        //Arrange
        HouseConfigurationController ctrl = new HouseConfigurationController();
        String typeString = "Humedade";
        ctrl.createType(typeString);
        TypeSensor expectedResult = new TypeSensor(typeString);

        //Act
        TypeSensor actualResult = ctrl.getType();

        //Assert
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void seeIfDateIsCreated() {

        //Arrange
        HouseConfigurationController ctrl = new HouseConfigurationController();
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
    void seeIfSensorIsCreated() {

        //Arrange
        HouseConfigurationController ctrl = new HouseConfigurationController();
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
    void seeIfSensorIsCreatedAndGetSensor() {

        //Arrange
        HouseConfigurationController ctrl = new HouseConfigurationController();
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
    void seeIfSensorIsAddedToSensorList() {

        //Arrange
        HouseConfigurationController ctrl = new HouseConfigurationController();
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
    void seeIfSensorIsNotAddedToSensorList() {

        //Arrange
        HouseConfigurationController ctrl = new HouseConfigurationController();
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
    void seeIfSensorListIsAddedToGeographicArea() {

        //Arrange
        HouseConfigurationController ctrl = new HouseConfigurationController();
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
    void seeIfSensorListIsAddedToGeographicAreaFalse() {

        //Arrange
        HouseConfigurationController ctrl = new HouseConfigurationController();
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
    void seeIfSensorListIsAddedToGeographicAreaEmptyList() {

        //Arrange
        HouseConfigurationController ctrl = new HouseConfigurationController();
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
    
    //USER STORY 130 TESTS

    @Test
    void seeIfHouExistsInListWorks() {
        HouseList list = new HouseList();
        House house = new House("Casa Oliveira", "Santa Maria de Lamas", new Local(42, 50), "4535");
        list.addHouseToHouseList(house);
        HouseConfigurationController ctrl130 = new HouseConfigurationController(list);
        String houseName = "Casa Oliveira";
        boolean result = ctrl130.seeIfHouseExistsInHouseList(houseName);
        assertTrue(result);
    }

    @Test
    void seeIfHouExistsInListFails() {
        HouseList list = new HouseList();
        House house = new House("Casa Oliveira", "Santa Maria de Lamas", new Local(42, 50), "4535");
        list.addHouseToHouseList(house);
        HouseConfigurationController ctrl130 = new HouseConfigurationController(list);
        String houseName = "Casa Marques";
        boolean result = ctrl130.seeIfHouseExistsInHouseList(houseName);
        assertTrue(!result);
    }

    @Test
    void seeIfEnergyGridIsAddedToAnHouse(){
        HouseList list = new HouseList();
        EnergyGridList listEG = new EnergyGridList();
        EnergyGrid energyGrid = new EnergyGrid("grid", 400);
        House house = new House("Casa Oliveira", "Santa Maria de Lamas", new Local(42, 50), "4535");
        listEG.addEnergyGridToEnergyGridList(energyGrid);
        house.setmEGList(listEG);
        list.addHouseToHouseList(house);
        HouseConfigurationController ctrl130 = new HouseConfigurationController(list);
        ctrl130.seeIfHouseExistsInHouseList("Casa Oliveira");
        boolean result = ctrl130.addEnergyGridToHouse();
        assertTrue(result);
    }

    @Test
    void seeIfEnergyGridIsCreated(){
        HouseList list = new HouseList();
        HouseConfigurationController ctrl130 = new HouseConfigurationController(list);
        ctrl130.createEnergyGrid("EG", 400);
        EnergyGrid expectedResult = new EnergyGrid("EG", 400);
        EnergyGrid result = ctrl130.getEnergyGrid();
        assertEquals(expectedResult,result);
    }

    //USER STORY 135

    @Test
    void seeIfPowerSourceIsCreated(){
        HouseList list = new HouseList();
        House house = new House("Casa Oliveira", "Santa Maria de Lamas", new Local(42, 50), "4535");
        list.addHouseToHouseList(house);
        HouseConfigurationController ctrl135 = new HouseConfigurationController(list);
        ctrl135.createPowerSource("Gerador", 400, 200);
        PowerSource result = ctrl135.getPowerSource();
        PowerSource expectedResult = new PowerSource("Gerador", 400, 200);
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfEnergyGridListIsSelected(){
        HouseList list = new HouseList();
        House house = new House("Casa Oliveira", "Santa Maria de Lamas", new Local(42, 50), "4535");
        EnergyGridList listEG = new EnergyGridList();
        EnergyGrid energyGrid = new EnergyGrid("EG1",400);
        EnergyGrid energyGrid2 = new EnergyGrid("EG2",300);
        listEG.addEnergyGridToEnergyGridList(energyGrid);
        listEG.addEnergyGridToEnergyGridList(energyGrid2);
        house.setmEGList(listEG);
        list.addHouseToHouseList(house);
        HouseConfigurationController ctrl135 = new HouseConfigurationController(list);
        ctrl135.seeIfHouseExistsInHouseList("Casa Oliveira");
        ctrl135.selectEnergyGrid("EG1");
        EnergyGrid result = ctrl135.getEnergyGrid();
        EnergyGrid expectedResult = new EnergyGrid("EG1",400);
        assertEquals(expectedResult,result);
    }

    @Test
    void seeIfEnergyGridListIsSelectedFails(){
        HouseList list = new HouseList();
        House house = new House("Casa Oliveira", "Santa Maria de Lamas", new Local(42, 50), "4535");
        EnergyGridList listEG = new EnergyGridList();
        EnergyGrid energyGrid = new EnergyGrid("EG1",400);
        EnergyGrid energyGrid2 = new EnergyGrid("EG2",300);
        listEG.addEnergyGridToEnergyGridList(energyGrid);
        listEG.addEnergyGridToEnergyGridList(energyGrid2);
        house.setmEGList(listEG);
        list.addHouseToHouseList(house);
        HouseConfigurationController ctrl135 = new HouseConfigurationController(list);
        ctrl135.seeIfHouseExistsInHouseList("Casa Oliveira");
        ctrl135.selectEnergyGrid("EG3");
        EnergyGrid result = ctrl135.getEnergyGrid();
        assertNull(result);
    }

    @Test
    void seeIfPowerSourceIsAddedToEnergyGrid(){
        HouseList list = new HouseList();
        House house = new House("Casa Oliveira", "Santa Maria de Lamas", new Local(42, 50), "4535");
        EnergyGridList listEG = new EnergyGridList();
        EnergyGrid energyGrid = new EnergyGrid("EG1",400);
        PowerSourceList powerSourceList = new PowerSourceList();
        energyGrid.setListPowerSources(powerSourceList);
        listEG.addEnergyGridToEnergyGridList(energyGrid);
        house.setmEGList(listEG);
        list.addHouseToHouseList(house);
        HouseConfigurationController ctrl135 = new HouseConfigurationController(list);
        PowerSource powerSource = new PowerSource("Gerador", 400, 200);
        ctrl135.seeIfHouseExistsInHouseList("Casa Oliveira");
        ctrl135.selectEnergyGrid("EG1");
        ctrl135.createPowerSource("Gerador", 400, 200);
        ctrl135.addPowerSourceToEnergyGrid();
        boolean result = ctrl135.getEnergyGrid().getmListPowerSources().containsPowerSource(powerSource);
        assertTrue(result);
    }

    @Test
    void seeIfPowerSourceIsAddedToEnergyGridFails(){
        HouseList list = new HouseList();
        House house = new House("Casa Oliveira", "Santa Maria de Lamas", new Local(42, 50), "4535");
        EnergyGridList listEG = new EnergyGridList();
        EnergyGrid energyGrid = new EnergyGrid("EG1",400);
        PowerSourceList powerSourceList = new PowerSourceList();
        energyGrid.setListPowerSources(powerSourceList);
        listEG.addEnergyGridToEnergyGridList(energyGrid);
        house.setmEGList(listEG);
        list.addHouseToHouseList(house);
        HouseConfigurationController ctrl135 = new HouseConfigurationController(list);
        PowerSource powerSource = new PowerSource("Painel Solar", 200, 200);
        ctrl135.seeIfHouseExistsInHouseList("Casa Oliveira");
        ctrl135.selectEnergyGrid("EG1");
        ctrl135.createPowerSource("Gerador", 400, 200);
        ctrl135.addPowerSourceToEnergyGrid();
        boolean result = ctrl135.getEnergyGrid().getmListPowerSources().containsPowerSource(powerSource);
        assertTrue(!result);
    }

    @Test
    void seeIfEnergyGridListIsDisplayed(){
        HouseList list = new HouseList();
        House house = new House("Casa Oliveira", "Santa Maria de Lamas", new Local(42, 50), "4535");
        EnergyGridList listEG = new EnergyGridList();
        EnergyGrid energyGrid = new EnergyGrid("EG1",400);
        PowerSourceList powerSourceList = new PowerSourceList();
        energyGrid.setListPowerSources(powerSourceList);
        listEG.addEnergyGridToEnergyGridList(energyGrid);
        house.setmEGList(listEG);
        list.addHouseToHouseList(house);
        HouseConfigurationController ctrl135 = new HouseConfigurationController(list);
        ctrl135.seeIfHouseExistsInHouseList("Casa Oliveira");
        String result = ctrl135.seeIfEnergyGridListIsEmptyAndShowItsContent();
        String expectedResult = "Energy grid list: \n" + "-EG1;";
        assertEquals(expectedResult,result);
    }

    @Test
    void seeIfEnergyGridListIsEmpty(){
        HouseList list = new HouseList();
        House house = new House("Casa Oliveira", "Santa Maria de Lamas", new Local(42, 50), "4535");
        EnergyGridList listEG = new EnergyGridList();
        house.setmEGList(listEG);
        list.addHouseToHouseList(house);
        HouseConfigurationController ctrl135 = new HouseConfigurationController(list);
        ctrl135.seeIfHouseExistsInHouseList("Casa Oliveira");
        String result = ctrl135.seeIfEnergyGridListIsEmptyAndShowItsContent();
        String expectedResult = "The list is empty.";
        assertEquals(expectedResult,result);
    }
}
