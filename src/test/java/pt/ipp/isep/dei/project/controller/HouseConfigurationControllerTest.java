package pt.ipp.isep.dei.project.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

class HouseConfigurationControllerTest {

    //USER STORY 005 TESTS

    @Test
    void seeIfConstructorWorks() {
        //Arrange
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere", "Km/h"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 10, 4).getTime());
        Sensor s2 = new Sensor("Chuva", new TypeSensor("Atmosphere", "l/m2"),
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
        HouseConfigurationController ctrl = new HouseConfigurationController(lc);

        //Act
        boolean actualResult = ctrl.setTypeSensor("Portugal", "Movement");

        //Assert
        assertEquals(expectedResult, actualResult);
    }

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
        HouseConfigurationController ctrl = new HouseConfigurationController(lc);

        //Act
        boolean actualResult = ctrl.setTypeSensor("Vento", "Movement");

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    //USER STORY 006 TESTS

    @Test
    void seeIfLocalIsCreated() {

        //Arrange
        HouseConfigurationController ctrl = new HouseConfigurationController();
        double lat = 50.0;
        double lon = 50.0;
        double alt = 50.0;
        ctrl.createLocal(lat, lon, alt);
        Local expectedResult = new Local(50, 50, 50);

        //Act
        Local actualResult = ctrl.getLocal();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfLocalIsCreated2() {

        //Arrange
        HouseConfigurationController ctrl = new HouseConfigurationController();
        double lat = 500.0;
        double lon = 500.0;
        double alt = 500.0;
        ctrl.createLocal(lat, lon, alt);
        Local expectedResult = new Local(500, 500, 500);

        //Act
        Local actualResult = ctrl.getLocal();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfLocalIsCreated3() {

        //Arrange
        HouseConfigurationController ctrl = new HouseConfigurationController();
        double lat = -50.0;
        double lon = -50.0;
        double alt = -50.0;
        ctrl.createLocal(lat, lon, alt);
        Local expectedResult = new Local(-50, -50, -50);

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
        String units = "kg/m³";
        ctrl.createType(typeString, units);
        String expectedResult = "Humedade";

        //Act
        String actualResult = ctrl.getType().getName();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfTypeIsCreated2() {

        //Arrange
        HouseConfigurationController ctrl = new HouseConfigurationController();
        String typeString = "Humedade";
        String units = "kg/m³";
        ctrl.createType(typeString, units);
        String expectedResult = "Humedade";

        //Act
        String actualResult = ctrl.getType().getName();

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
        ctrl.createDate(year, month, day);
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
        TypeSensor t1 = new TypeSensor("Humedade", "kg/m³");
        Sensor expectedResult = new Sensor("XV-56D", t1, loc1, new GregorianCalendar(2018, 8, 9).getTime());

        //Act
        Sensor actualResult = ctrl.createSensor(nameString, type1, loc1, date1);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSensorIsCreatedAndGetSensor() {

        //Arrange
        HouseConfigurationController ctrl = new HouseConfigurationController();
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
        TypeSensor t1 = new TypeSensor("Humedade", "kg/m³");
        Sensor expectedResult = new Sensor("XV-56D", t1, loc1, new GregorianCalendar(2018, 8, 9).getTime());

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

        //Act
        boolean actualResult = ctrl.addSensor(sens1, xSensorList);

        //Assert
        assertTrue(actualResult);
    }

    @Test
    void seeIfSensorIsNotAddedToSensorList() {

        //Arrange
        HouseConfigurationController ctrl = new HouseConfigurationController();
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

        //Act
        boolean actualResult = ctrl.addSensor(sens1, xSensorList);

        //Assert
        assertFalse(actualResult);
    }

    @Test
    void seeIfSensorListIsAddedToGeographicArea() {

        //Arrange
        HouseConfigurationController ctrl = new HouseConfigurationController();
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
        GeographicArea areaG = new GeographicArea("Porto",t1,2,3,l1);
        areaG.setmId("Alegria");

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
        GeographicArea areaG = new GeographicArea("Porto",t1,2,3,l1);
        areaG.setmId("Alegria");

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
        GeographicArea areaG = new GeographicArea("Porto",t1,2,3,l1);
        areaG.setmId("Alegria");

        String areaNameInput = "Alegria";
        GeographicAreaList xgaList = new GeographicAreaList();

        //Act
        boolean actualResult = ctrl.addSensorToGeographicArea(areaNameInput, xgaList, xSensorList);

        //Assert
        assertFalse(actualResult);
    }



    //USER STORY 101 TESTS

    @Test
    void seeIfConstructorWorksUS101() {
        //Arrange

        GeographicAreaList geographicAreaList = new GeographicAreaList();
        GeographicArea ga1 = new GeographicArea();
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga1);
        GeographicAreaList expectedResult = new GeographicAreaList(ga1);

        //Act
        HouseConfigurationController controller = new HouseConfigurationController(geographicAreaList);
        GeographicAreaList actualResult = controller.getGeoList();

        //Assert
        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfPrintGAList() {
        //Arrange
        GeographicArea gA1 = new GeographicArea("Portugal",new TypeArea("Country"),2,3,new Local(21, 33, 100));
        GeographicArea gA2 = new GeographicArea("Oporto",new TypeArea("City"),2,3,new Local(14, 14, 100));
        GeographicArea gA3 = new GeographicArea("Lisbon",new TypeArea("Village"),2,3,new Local(3, 3, 100));

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
        HouseConfigurationController ctrl = new HouseConfigurationController(gAL1);
        String result = ctrl.printGAList(gAL1);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGeographicAreaIndexMatchByString() {
        //Arrange
        HouseConfigurationController ctrl = new HouseConfigurationController();
        GeographicAreaList mGeographicAreaList = new GeographicAreaList();
        GeographicArea gA1 = new GeographicArea("porto",new TypeArea("cidade"),2,3,new Local(4, 4, 100));
        GeographicArea gA2 = new GeographicArea("lisboa",new TypeArea("aldeia"),2,3,new Local(4, 4, 100));

        mGeographicAreaList.addGeographicAreaToGeographicAreaList(gA1);
        mGeographicAreaList.addGeographicAreaToGeographicAreaList(gA2);
        //Act
        List<Integer> result = ctrl.matchGeographicAreaIndexByString("lisboa", mGeographicAreaList);
        List<Integer> expectedResult = Collections.singletonList(mGeographicAreaList.getGeographicAreaList().indexOf(gA2));
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfPrintGeoGraphicAreaElementsByIndex() {
        //Arrange
        HouseConfigurationController ctrl = new HouseConfigurationController();
        List<Integer> list = new ArrayList<>();
        Integer i = 1;
        list.add(i);
        GeographicAreaList mGeographicAreaList = new GeographicAreaList();
        GeographicArea geoa1 = new GeographicArea("porto",new TypeArea("cidade"),2,3,new Local(4, 4, 100));
        GeographicArea geoa2 = new GeographicArea("lisboa",new TypeArea("aldeia"),2,3,new Local(4, 4, 100));

        mGeographicAreaList.addGeographicAreaToGeographicAreaList(geoa1);
        mGeographicAreaList.addGeographicAreaToGeographicAreaList(geoa2);

        //Act
        String result = ctrl.printGeoGraphicAreaElementsByIndex(list, mGeographicAreaList);
        String expectedResult = "---------------\n" +
                "1) lisboa, aldeia, 4.0º lat, 4.0º long\n" +
                "---------------\n";

        //Assert
        assertEquals(expectedResult, result);
    }

    //USER STORY 105


    // USER STORY 108

    @Test
    void seeIfMatchGeographicAreaIndexByStringWorks() {
        //Arrange -------------------------------------
        HouseConfigurationController ctrl = new HouseConfigurationController();
        //Geo Area List
        GeographicAreaList mGeographicAreaList = new GeographicAreaList();
        GeographicArea geoa1 = new GeographicArea("porto",new TypeArea("cidade"),2,3,new Local(4, 4, 100));
        GeographicArea geoa2 = new GeographicArea("lisboa",new TypeArea("aldeia"),2,3,new Local(4, 4, 100));

        mGeographicAreaList.addGeographicAreaToGeographicAreaList(geoa1);
        mGeographicAreaList.addGeographicAreaToGeographicAreaList(geoa2);
        //Act -----------------------------------------
        List<Integer> result = ctrl.matchGeographicAreaIndexByString("lisboa", mGeographicAreaList);
        List<Integer> expectedResult = Collections.singletonList(mGeographicAreaList.getGeographicAreaList().indexOf(geoa2));
        //Assert --------------------------------------
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfPrintGeoGraphicAreaElementsByIndex2() {
        //Arrange -----------------------------------------
        HouseConfigurationController ctrl = new HouseConfigurationController();
        //Geo Area List
        GeographicAreaList mGeographicAreaList = new GeographicAreaList();
        GeographicArea geoa1 = new GeographicArea("porto",new TypeArea("cidade"),2,3,new Local(4, 4, 100));
        GeographicArea geoa2 = new GeographicArea("lisboa",new TypeArea("aldeia"),2,3,new Local(4, 4, 100));
        mGeographicAreaList.addGeographicAreaToGeographicAreaList(geoa1);
        mGeographicAreaList.addGeographicAreaToGeographicAreaList(geoa2);
        //Act ---------------------------------------------
        List<Integer> list = new ArrayList<>();
        Integer i = 1;
        list.add(i);
        String result = ctrl.printGeoGraphicAreaElementsByIndex(list, mGeographicAreaList);
        String expectedResult = "---------------\n" +
                "1) lisboa, aldeia, 4.0º lat, 4.0º long\n" +
                "---------------\n";
        //Assert ------------------------------------------
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void seeIfPrintsGeoA() {
        HouseConfigurationController US101 = new HouseConfigurationController();
        GeographicArea gA1 = new GeographicArea("Portugal",new TypeArea("Country"),2,3,new Local(21, 33, 100));
        GeographicArea gA2 = new GeographicArea("Oporto",new TypeArea("City"),2,3,new Local(14, 14, 100));
        GeographicArea gA3 = new GeographicArea("Lisbon",new TypeArea("Village"),2,3,new Local(3, 3, 100));

        GeographicAreaList gAL1 = new GeographicAreaList();
        gAL1.addGeographicAreaToGeographicAreaList(gA1);
        gAL1.addGeographicAreaToGeographicAreaList(gA2);
        gAL1.addGeographicAreaToGeographicAreaList(gA3);
        String expectedResult =
                "Portugal, Country, 21.0º lat, 33.0º long\n";
        String result = US101.printGA(gA1);
        assertEquals(expectedResult, result);
    }

    //USER STORY 145

    @Test
    void seeIfIndexIsMatchedByString() {
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50),ga, roomList);
        Room room = new Room("Quarto", 1, 20,2,2);
        EnergyGrid energyGrid1 = new EnergyGrid("EG1", 400);
        EnergyGrid energyGrid2 = new EnergyGrid("EG2", 200);
        EnergyGridList energyGridList = new EnergyGridList();
        energyGridList.addEnergyGridToEnergyGridList(energyGrid1);
        energyGridList.addEnergyGridToEnergyGridList(energyGrid2);
        roomList.addRoom(room);
        house.setmRoomList(roomList);
        house.setmEGList(energyGridList);
        HouseConfigurationController ctrlUS145 = new HouseConfigurationController(room);
        List<Integer> result = ctrlUS145.matchGridIndexByString("EG2", house);
        List<Integer> expectedResult = new ArrayList<>();
        expectedResult.add(1);
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfEnergyGridIsPrintedByIndex() {
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50),ga, roomList);
        Room room = new Room("Quarto", 1, 20,2,2);
        EnergyGrid energyGrid1 = new EnergyGrid("EG1", 400);
        EnergyGrid energyGrid2 = new EnergyGrid("EG2", 200);
        EnergyGridList energyGridList = new EnergyGridList();
        energyGridList.addEnergyGridToEnergyGridList(energyGrid1);
        energyGridList.addEnergyGridToEnergyGridList(energyGrid2);
        roomList.addRoom(room);
        house.setmRoomList(roomList);
        house.setmEGList(energyGridList);
        HouseConfigurationController ctrlUS145 = new HouseConfigurationController(energyGridList);
        List<Integer> list = ctrlUS145.matchGridIndexByString("EG1", house);
        String actualResult = ctrlUS145.printEnergyGridByIndex(list);
        String expectedResult = "0) EG1, 400.0, null.\n";
        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfEnergyGridPrints() {
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50),ga, roomList);
        Room room = new Room("Quarto", 1, 20,2,2);
        EnergyGrid energyGrid1 = new EnergyGrid("EG1", 400);
        EnergyGridList energyGridList = new EnergyGridList();
        energyGridList.addEnergyGridToEnergyGridList(energyGrid1);
        roomList.addRoom(room);
        house.setmRoomList(roomList);
        house.setmEGList(energyGridList);
        HouseConfigurationController ctrlUS145 = new HouseConfigurationController(room);
        String result = ctrlUS145.printEnergyGrid(energyGrid1);
        String expectedResult = "Energy Grid: EG1, Max Power: 400.0";
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGridListPrints() {
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50),ga, roomList);
        Room room = new Room("Quarto", 1, 20,2,2);
        EnergyGrid energyGrid1 = new EnergyGrid("EG1", 400);
        EnergyGridList energyGridList = new EnergyGridList();
        energyGridList.addEnergyGridToEnergyGridList(energyGrid1);
        roomList.addRoom(room);
        house.setmRoomList(roomList);
        house.setmEGList(energyGridList);
        HouseConfigurationController ctrlUS145 = new HouseConfigurationController(room);
        String result = ctrlUS145.printGridList(house);
        String expectedResult = "---------------\n" +
                "0) Designation: EG1 | Max Power: 400.0\n" +
                "---------------\n";
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfRoomsPrint() {
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50),ga, roomList);
        Room room = new Room("Quarto", 1, 20,2,2);
        EnergyGrid energyGrid1 = new EnergyGrid("EG1", 400);
        EnergyGridList energyGridList = new EnergyGridList();
        energyGridList.addEnergyGridToEnergyGridList(energyGrid1);
        roomList.addRoom(room);
        house.setmRoomList(roomList);
        house.setmEGList(energyGridList);
        HouseConfigurationController ctrlUS145 = new HouseConfigurationController(room);
        String result = ctrlUS145.printRooms(roomList);
        String expectedResult = "---------------\n" +
                "0) Designation: Quarto | House Floor: 1 | Width: 20.0 | Length: 2.0 | Height: 2.0\n" +
                "---------------\n";
        assertEquals(expectedResult, result);
    }

    //USER STORY 149

    @Test
    void seeIfRoomIsRemovedFromGrid() {
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50),ga, roomList);
        Room room = new Room("Quarto", 1, 20,2,2);
        EnergyGrid energyGrid1 = new EnergyGrid("EG1", 400);
        EnergyGrid energyGrid2 = new EnergyGrid("EG2", 200);
        EnergyGridList energyGridList = new EnergyGridList();
        energyGridList.addEnergyGridToEnergyGridList(energyGrid1);
        energyGridList.addEnergyGridToEnergyGridList(energyGrid2);
        roomList.addRoom(room);
        energyGrid1.setListOfRooms(roomList);
        house.setmEGList(energyGridList);
        HouseConfigurationController ctrlUS145 = new HouseConfigurationController(room);
        boolean result = ctrlUS145.removeRoomFromGrid(energyGrid1, room);
        assertTrue(result);
    }
    }
