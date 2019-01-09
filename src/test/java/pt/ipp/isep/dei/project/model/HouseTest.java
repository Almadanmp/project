package pt.ipp.isep.dei.project.model;


import org.junit.jupiter.api.Test;

import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HouseTest {

    @Test
        void seeIfCalculateDistanceToSensorWorks() {
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 6),ga, roomList);
        Sensor s = new Sensor("sensor1", new TypeSensor("temperatura","Celsius"), new Local(4, 5), new GregorianCalendar(2018, 10, 1).getTime());
        double result = house.calculateDistanceToSensor(s);
        assertEquals(110.91871788829754, result, 0.01);
    }

    @Test
    void seeIfgetTheMinDistanceFromTheHouseToTheSensorWorks() {
        RoomList roomList = new RoomList();
        Sensor s1 = new Sensor("sensor1", new TypeSensor("temperatura","Celsius"), new Local(4, 6), new GregorianCalendar(2018, 10, 1).getTime());
        Sensor s2 = new Sensor("sensor2", new TypeSensor("temperatura","Celsius"), new Local(4, 8), new GregorianCalendar(2018, 10, 1).getTime());
        SensorList sensorList = new SensorList();
        sensorList.addSensor(s1);
        sensorList.addSensor(s2);
        GeographicArea ga = new GeographicArea(new TypeArea("cidade"), new Local(4, 5, 50), sensorList);
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50),ga, roomList);
        double result = house.getMinDistanceFromHouseToSensor(ga);
        assertEquals(110.91871788829754, result, 0.01);
    }

    @Test
    void seeIfgetTheMinDistanceFromTheHouseToTheSensorWorks2() {
        RoomList roomList = new RoomList();
        Sensor s1 = new Sensor("sensor1", new TypeSensor("temperatura","Celsius"), new Local(4, 8), new GregorianCalendar(2018, 10, 1).getTime());
        Sensor s2 = new Sensor("sensor2", new TypeSensor("temperatura","Celsius"), new Local(4, 6), new GregorianCalendar(2018, 10, 1).getTime());
        SensorList sensorList = new SensorList(s1);
        sensorList.addSensor(s2);
        GeographicArea ga = new GeographicArea(new TypeArea("cidade"), new Local(4, 5, 50), sensorList);
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50),ga, roomList);
        double result = house.getMinDistanceFromHouseToSensor(ga);
        assertEquals(110.91871788829754, result, 0.01);
    }

    @Test
    void seeIfGetSensorWithTheMinDistanceToHouseWorks() {
        RoomList roomList = new RoomList();
        Sensor s1 = new Sensor("sensor1", new TypeSensor("temperature","Celsius"), new Local(4, 6), new GregorianCalendar(4, 4, 4).getTime());
        Sensor s2 = new Sensor("sensor2", new TypeSensor("temperature","Celsius"), new Local(4, 8), new GregorianCalendar(4, 4, 4).getTime());
        SensorList sensorList = new SensorList();
        sensorList.addSensor(s1);
        sensorList.addSensor(s2);
        GeographicArea ga = new GeographicArea(new TypeArea("cidade"), new Local(4, 5, 50), sensorList);
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50),ga, roomList);
        Sensor result = house.getSensorWithMinDistanceToHouse(ga, house);
        assertEquals(s1, result);
    }

    @Test
    void seeIfGetSensorWithTheMinDistanceToHouseWorks2() {
        RoomList roomList = new RoomList();
        Sensor s1 = new Sensor("sensor1", new TypeSensor("temperature","Celsius"), new Local(4, 8), new GregorianCalendar(4, 4, 4).getTime());
        Sensor s2 = new Sensor("sensor2", new TypeSensor("temperature","Celsius"), new Local(4, 6), new GregorianCalendar(4, 4, 4).getTime());
        SensorList sensorList = new SensorList();
        sensorList.addSensor(s1);
        sensorList.addSensor(s2);
        GeographicArea ga = new GeographicArea(new TypeArea("cidade"), new Local(4, 5, 50), sensorList);
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50),ga, roomList);
        Sensor result = house.getSensorWithMinDistanceToHouse(ga, house);
        assertEquals(s2, result);
    }

    @Test
    void seeIfGetSensorWithTheMinDistanceToHouseNull() {
        RoomList roomList = new RoomList();
        Sensor s1 = new Sensor("sensor1", new TypeSensor("temperature","Celsius"), new Local(4, 8), new GregorianCalendar(4, 4, 4).getTime());
        Sensor s2 = new Sensor("sensor2", new TypeSensor("temperature","Celsius"), new Local(4, 6), new GregorianCalendar(4, 4, 4).getTime());
        SensorList sensorList = new SensorList();
        sensorList.addSensor(s1);
        sensorList.addSensor(s2);
        GeographicArea ga = new GeographicArea(new TypeArea("cidade"), new Local(4, 5, 50));
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50),ga, roomList);
        Sensor result = house.getSensorWithMinDistanceToHouse(ga, house);
        assertEquals(null, result);
    }

    @Test
    void ensureThatAObjectIsAInstanceOf() {
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50),ga, roomList);
        House house2 = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50),ga, roomList);

        Boolean actualResult = house.equals(house2);

        assertEquals(true, actualResult);
    }

    @Test
    void ensureThatAObjectIsNotAInstanceOf() {
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50),ga, roomList);
        House house2 = new House("casa de campo", "Rua do Sol", "4516", "Porto", new Local(4, 5, 50),ga, roomList);

        Boolean actualResult = house.equals(house2);

        assertEquals(false, actualResult);
    }

    @Test
    void ensureThatAObjectIsNotAInstanceOfNull() {
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50),ga, roomList);
        House house2 = new House();

        Boolean actualResult = house.equals(house2);

        assertEquals(false, actualResult);
    }

    @Test
    void seeIfAddRoomToRoomList() {
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50),ga, roomList);
        RoomList rL1 = new RoomList();
        house.setmRoomList(rL1);
        Room room = new Room("quarto", 1, 80,2,2);
        Boolean result = house.addRoomToRoomList(room);

        assertEquals(true, result);
    }

    @Test
    void seeIfFailsAddRoomToRoomList() {
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50),ga, roomList);
        RoomList rL1 = new RoomList();
        house.setmRoomList(rL1);
        Room room = new Room("quarto", 1, 80,2,2);
        Room room2 = new Room("quarto", 2, 80,2,2);
        house.addRoomToRoomList(room2);
        Boolean result = house.addRoomToRoomList(room);

        assertEquals(false, result);
    }

    @Test
    void seeIfRecognizesEmptyGridList () {
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50),ga, roomList);
        EnergyGridList energyGridList = new EnergyGridList();
        house.setmEGList(energyGridList);
        String expectedResult = "Invalid List - List is Empty\n";
        String actualResult = house.printGridList();
        assertEquals(expectedResult,actualResult);
    }

    @Test
    void checkIfWeReceivePrintedGridList (){
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50),ga, roomList);
        EnergyGrid eg1 = new EnergyGrid("Rede",444);
        EnergyGrid eg2 = new EnergyGrid("Rede 2",555);
        EnergyGridList energyGridList = new EnergyGridList();
        energyGridList.addEnergyGridToEnergyGridList(eg1);
        energyGridList.addEnergyGridToEnergyGridList(eg2);
        house.setmEGList(energyGridList);
        String expectedResult = "---------------\n" +
                "0) Designation: Rede | Max Power: 444.0\n" +
                "1) Designation: Rede 2 | Max Power: 555.0\n" +
                "---------------\n";
        String actualResult = house.printGridList();
        assertEquals(expectedResult,actualResult);
    }

    @Test
    void seeIfsetHouseStreet(){
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        //Arrange
        String expectedResult = "Rua da Praia";
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50),ga, roomList);

        //Act
        house.setmStreet("Rua da Praia");
        String actualResult = house.getmStreet();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfsetHouseZipCode(){
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        //Arrange
        String expectedResult = "6327-09";
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50),ga, roomList);

        //Act
        house.setmZip("6327-09");
        String actualResult = house.getmZip();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSetTown() {
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        //Arrange
        String expectedResult = "Lisboa";
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50),ga, roomList);

        //Act
        house.setmTown("Lisboa");
        String actualResult = house.getmTown();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfsetHouseGPS(){
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        //Arrange
        Local expectedResult = new Local (7, 78, 50);

        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50),ga, roomList);

        //Act
        house.setmLocation(7, 78);
        Local actualResult = house.getmLocation();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

}

