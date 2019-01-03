package pt.ipp.isep.dei.project.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testng.Assert;
import pt.ipp.isep.dei.project.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class HouseConfigurationControllerTest {
    
    //USER STORY 001 TESTS

    @Test
    public void seeIfnewTAGWorks() {
        TypeAreaList newList = new TypeAreaList();
        HouseConfigurationController ctrl = new HouseConfigurationController(newList);
        boolean result = ctrl.createAndAddTypeAreaToList("cidade");
        assertTrue(result);
    }

    @Test
    public void seeIfnewTAGWorksWithAnother() {
        TypeArea tipo = new TypeArea("rua");
        TypeAreaList newList = new TypeAreaList();
        newList.addTypeArea(tipo);
        HouseConfigurationController ctrl = new HouseConfigurationController(newList);
        boolean result = ctrl.createAndAddTypeAreaToList("cidade");
        assertTrue(result);
    }

    @Test
    public void seeIfnewTAGDoesntWorkWhenDuplicatedISAdded() {
        TypeArea tipo = new TypeArea("cidade");
        TypeAreaList expectedResult = new TypeAreaList();
        expectedResult.addTypeArea(tipo);
        HouseConfigurationController ctrl = new HouseConfigurationController(expectedResult);
        boolean result = ctrl.createAndAddTypeAreaToList("cidade");
        assertFalse(result);
    }

    @Test
    public void seeIfNewTAGDoesntWorkWhenNullIsAdded(){
        TypeArea tipo = new TypeArea("cidade");
        TypeAreaList list = new TypeAreaList();
        list.addTypeArea(tipo);
        HouseConfigurationController ctrl = new HouseConfigurationController(list);
        boolean result = ctrl.createAndAddTypeAreaToList(null);
        assertFalse(result);
    }

    @Test
    public void seeIfNewTAGDoesntWorkWhenNameIsEmpty(){
        TypeArea tipo = new TypeArea("cidade");
        TypeAreaList list = new TypeAreaList();
        list.addTypeArea(tipo);
        HouseConfigurationController ctrl = new HouseConfigurationController(list);
        boolean result = ctrl.createAndAddTypeAreaToList("");
        assertFalse(result);
    }
    @Test
    public void seeIfNewTAGDoesntWorkWhenNumbersAreAdded(){
        TypeArea tipo = new TypeArea("cidade");
        TypeAreaList list = new TypeAreaList();
        list.addTypeArea(tipo);
        HouseConfigurationController ctrl = new HouseConfigurationController(list);
        boolean result = ctrl.createAndAddTypeAreaToList("cidade1");
        assertFalse(result);
    }

    //USER STORY 002 TESTS

    @Test
    public void seeIfPrintTypeAreaListWorks(){
        TypeAreaList list =new TypeAreaList();
        TypeArea t1 = new TypeArea("Rua");
        list.addTypeArea(t1);
        HouseConfigurationController ctrl = new HouseConfigurationController(list);
        String actualResult = ctrl.getTypeAreaList();
        String expectedResult = "\n" +
                "Area Types List:\n" +
                "\n" +
                "-Rua;";
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfPrintTypeAreaListWorksWithTwoTypes(){
        TypeAreaList list =new TypeAreaList();
        TypeArea t1 = new TypeArea("Rua");
        TypeArea t2 = new TypeArea("Cidade");
        list.addTypeArea(t1);
        list.addTypeArea(t2);
        HouseConfigurationController ctrl = new HouseConfigurationController(list);
        String actualResult = ctrl.getTypeAreaList();
        String expectedResult = "\n" +
                "Area Types List:\n" +
                "\n" +
                "-Rua;\n" +
                "-Cidade;";
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfPrintTypeAreaListWorksWithThreeTypes(){
        TypeAreaList list =new TypeAreaList();
        TypeArea t1 = new TypeArea("Rua");
        TypeArea t2 = new TypeArea("Cidade");
        TypeArea t3 = new TypeArea("Viela");
        list.addTypeArea(t1);
        list.addTypeArea(t2);
        list.addTypeArea(t3);
        HouseConfigurationController ctrl = new HouseConfigurationController(list);
        String actualResult = ctrl.getTypeAreaList();
        String expectedResult = "\n" +
                "Area Types List:\n" +
                "\n" +
                "-Rua;\n" +
                "-Cidade;\n" +
                "-Viela;";
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfPrintTypeAreaListWorksWithEmptyList(){
        TypeAreaList list =new TypeAreaList();
        HouseConfigurationController ctrl = new HouseConfigurationController(list);
        String actualResult = ctrl.getTypeAreaList();
        String expectedResult = "\n" +
                "Area Types List:\n" +
                "\n" +
                "|||| List is Empty ||||\n" +
                "Add types to list first";
        assertEquals(expectedResult, actualResult);
    }

    //USER STORY 003 TESTS

    @Test
    public void seeIfCreatesGeographicAreaAndAddsItToList() {
        GeographicAreaList geoList = new GeographicAreaList();
        String name = "Porto";
        String typeArea = "Distrito";
        double latitude = 38;
        double longitude = 7;
        HouseConfigurationController us3 = new HouseConfigurationController();
        boolean result = us3.addNewGeoAreaToList(geoList, name, typeArea, latitude, longitude);

        assertTrue(result);
        assertEquals(1, geoList.getGeographicAreaList().size());
    }

    @Test
    public void seeIfFailsCreatingSecondEqualGeographicArea() {
        GeographicAreaList geoList = new GeographicAreaList();
        String name = "Porto";
        String typeArea = "Distrito";
        double latitude = 38;
        double longitude = 7;

        HouseConfigurationController us3 = new HouseConfigurationController();
        boolean result1 = us3.addNewGeoAreaToList(geoList, name, typeArea, latitude, longitude);
        boolean result2 = us3.addNewGeoAreaToList(geoList, name, typeArea, latitude, longitude);

        assertTrue(result1); //safety check (already covered on previous test)
        Assertions.assertFalse(result2);
        assertEquals(1, geoList.getGeographicAreaList().size());
    }

    @Test
    public void seeIfCreatesTwoDifferentGeographicAreas() {
        GeographicAreaList geoList = new GeographicAreaList();
        String name1 = "Porto";
        String typeArea = "Distrito";
        double latitude = 38;
        double longitude = 7;
        String name2 = "Lisboa";

        HouseConfigurationController us3 = new HouseConfigurationController();
        boolean result1 = us3.addNewGeoAreaToList(geoList, name1, typeArea, latitude, longitude);
        boolean result2 = us3.addNewGeoAreaToList(geoList, name2, typeArea, latitude, longitude);

        assertTrue(result1); //safety check (already covered on previous test)
        assertTrue(result2);
        assertEquals(2, geoList.getGeographicAreaList().size());
    }

    @Test
    public void seeIfFailsWithNullInputGeoList() {
        String name1 = "Porto";
        String typeArea = "Distrito";
        double latitude = 38;
        double longitude = 7;

        HouseConfigurationController us3 = new HouseConfigurationController();
        boolean result = us3.addNewGeoAreaToList(null, name1, typeArea, latitude, longitude);

        Assertions.assertFalse(result);
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

    //USER STORY 101 TESTS

    @Test
    void seeIfConstructorWorksUS101(){
        //Arrange

        GeographicAreaList geographicAreaList = new GeographicAreaList();
        GeographicArea ga1 = new GeographicArea();
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga1);
        GeographicAreaList expectedResult = new GeographicAreaList(ga1);

        //Act
        HouseConfigurationController controller = new HouseConfigurationController(geographicAreaList);
        GeographicAreaList actualResult = controller.getGeoList();

        //Assert
        assertEquals(expectedResult,actualResult);

    }


    @Test
    void seeIfPrintGAList(){
        //Arrange
        GeographicArea gA1 = new GeographicArea("Portugal", new TypeArea("Country"), new Local(21, 33));
        GeographicArea gA2 = new GeographicArea("Oporto", new TypeArea("City"), new Local(14, 14));
        GeographicArea gA3 = new GeographicArea("Lisbon", new TypeArea("Village"), new Local(3, 3));
        GeographicAreaList gAL1 = new GeographicAreaList();
        gAL1.addGeographicAreaToGeographicAreaList(gA1);
        gAL1.addGeographicAreaToGeographicAreaList(gA2);
        gAL1.addGeographicAreaToGeographicAreaList(gA3);

        //Act
        String expectedResult = "---------------\n" +
                "0) Name: Portugal | Type: Country | Latitude: 21.0 | Longitude: 33.0\n" +
                "1) Name: Oporto | Type: City | Latitude: 14.0 | Longitude: 14.0\n" +
                "2) Name: Lisbon | Type: Village | Latitude: 3.0 | Longitude: 3.0\n" +
                "---------------";
        HouseConfigurationController ctrl = new HouseConfigurationController(gAL1);
        String result = ctrl.printGAList(gAL1);

        //Assert
        assertEquals(expectedResult,result);
    }

    @Test
    void seeIfGeographicAreaIndexMatchByString(){
        //Arrange
        HouseConfigurationController ctrl = new HouseConfigurationController();
        GeographicAreaList mGeographicAreaList = new GeographicAreaList();
        GeographicArea gA1 = new GeographicArea("porto", new TypeArea("cidade"), new Local(4, 4));
        GeographicArea gA2 = new GeographicArea("lisboa", new TypeArea("aldeia"), new Local(4, 4));
        mGeographicAreaList.addGeographicAreaToGeographicAreaList(gA1);
        mGeographicAreaList.addGeographicAreaToGeographicAreaList(gA2);

        //Act
        List<Integer> result = ctrl.matchGeographicAreaIndexByString("lisboa", mGeographicAreaList);
        List<Integer> expectedResult = Collections.singletonList(mGeographicAreaList.getGeographicAreaList().indexOf(gA2));

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfPrintsHouseList() {
        //Assert
        HouseConfigurationController ctrl = new HouseConfigurationController();
        House house1 = new House("vacationHouse", "Flower Street", new Local(11, 13), "4230-111");
        House house2 = new House("workHouse", "Torrinha", new Local(12, 12), "4345-000");
        House house3 = new House("dreamHouse", "New York", new Local(122, 122), "6666-000");
        HouseList hL1 = new HouseList(house1);
        hL1.addHouseToHouseList(house2);
        hL1.addHouseToHouseList(house3);
        GeographicArea gA1 = new GeographicArea();
        gA1.setHouseList(hL1);

        //Act
        String expectedResult = "---------------\n" +
                "0) Designation: vacationHouse | Address: Flower Street | ZipCode: 4230-111\n" +
                "1) Designation: workHouse | Address: Torrinha | ZipCode: 4345-000\n" +
                "2) Designation: dreamHouse | Address: New York | ZipCode: 6666-000\n" +
                "---------------\n";
        String result = ctrl.printHouseList(gA1);

        //Assert
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfPrintsHouseListIfEmpty() {
        //Arrange
        HouseConfigurationController ctrl = new HouseConfigurationController();
        HouseList hL1 = new HouseList();
        GeographicArea gA1 = new GeographicArea();
        gA1.setHouseList(hL1);

        //Act
        String expectedResult = "Invalid List - List is Empty\n";
        String result = ctrl.printHouseList(gA1);

        //Assert
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfPrintGeoGraphicAreaElementsByIndex() {
        //Arrange
        HouseConfigurationController ctrl = new HouseConfigurationController();
        List<Integer> list = new ArrayList<>();
        Integer i = 1;
        list.add(i);
        GeographicAreaList mGeographicAreaList = new GeographicAreaList();
        GeographicArea geoa1 = new GeographicArea("porto", new TypeArea("cidade"), new Local(4, 4));
        GeographicArea geoa2 = new GeographicArea("lisboa", new TypeArea("aldeia"), new Local(4, 4));
        mGeographicAreaList.addGeographicAreaToGeographicAreaList(geoa1);
        mGeographicAreaList.addGeographicAreaToGeographicAreaList(geoa2);

        //Act
        String result = ctrl.printGeoGraphicAreaElementsByIndex(list, mGeographicAreaList);
        String expectedResult = "---------------\n" +
                "1) lisboa, aldeia, 4.0º lat, 4.0º long\n" +
                "---------------";

        //Assert
        assertEquals(expectedResult, result);
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

    @Test
    public void seeIfPrintsGeoA() {
        HouseConfigurationController US101 = new HouseConfigurationController();
        GeographicArea gA1 = new GeographicArea("Portugal", new TypeArea("Country"), new Local(21, 33));
        GeographicArea gA2 = new GeographicArea("Oporto", new TypeArea("City"), new Local(14, 14));
        GeographicArea gA3 = new GeographicArea("Lisbon", new TypeArea("Village"), new Local(3, 3));
        GeographicAreaList gAL1 = new GeographicAreaList();
        gAL1.addGeographicAreaToGeographicAreaList(gA1);
        gAL1.addGeographicAreaToGeographicAreaList(gA2);
        gAL1.addGeographicAreaToGeographicAreaList(gA3);
        String expectedResult =
                "Portugal, Country, 21.0º lat, 33.0º long\n";
        String result = US101.printGA(gA1);
        assertEquals(expectedResult, result);
    }









}
