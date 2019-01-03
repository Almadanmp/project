package pt.ipp.isep.dei.project.controller;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;

import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

public class HouseConfigurationControllerTest {
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
}
