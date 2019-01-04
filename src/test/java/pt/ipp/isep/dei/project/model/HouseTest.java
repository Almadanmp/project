package pt.ipp.isep.dei.project.model;


import org.junit.jupiter.api.Test;

import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HouseTest {

    @Test
    public void seeIfCalculateDistanceToSensorWorks() {
        House house = new House("rua coise e tal", new Local(4, 5), "447-56");
        Sensor s = new Sensor("sensr1", new TypeSensor("temperatura"), new Local(4, 6), new GregorianCalendar(2018, 10, 1).getTime());
        double result = house.calculateDistanceToSensor(s);
        assertEquals(110.91871788829754, result, 0.01);
    }

    @Test
    public void seeIfgetTheMinorDistanceFromTheHouseToTheSensorWorks() {
        Sensor s1 = new Sensor("sensr1", new TypeSensor("temperatura"), new Local(4, 6), new GregorianCalendar(2018, 10, 1).getTime());
        Sensor s2 = new Sensor("sensr2", new TypeSensor("temperatura"), new Local(4, 8), new GregorianCalendar(2018, 10, 1).getTime());
        SensorList sensorList = new SensorList();
        sensorList.addSensor(s1);
        sensorList.addSensor(s2);
        GeographicArea ga = new GeographicArea(new TypeArea("cidade"), new Local(4, 5), sensorList);
        House house = new House("casa de praia", "rua coise e tal", new Local(4, 5), "447-56");
        double result = house.getTheMinorDistanceFromTheHouseToTheSensor(ga);
        assertEquals(110.91871788829754, result, 0.01);
    }

    @Test
    public void seeIfgetTheMinorDistanceFromTheHouseToTheSensorWorks2() {
        Sensor s1 = new Sensor("sensr1", new TypeSensor("temperatura"), new Local(4, 8), new GregorianCalendar(2018, 10, 1).getTime());
        Sensor s2 = new Sensor("sensr5", new TypeSensor("temperatura"), new Local(4, 6), new GregorianCalendar(2018, 10, 1).getTime());
        SensorList sensorList = new SensorList(s1);
        sensorList.addSensor(s2);
        GeographicArea ga = new GeographicArea(new TypeArea("cidade"), new Local(4, 5), sensorList);
        House house = new House("casa de rua", "rua coise e tal", new Local(4, 5), "447-56");
        double result = house.getTheMinorDistanceFromTheHouseToTheSensor(ga);
        assertEquals(110.91871788829754, result, 0.01);
    }

    @Test
    public void seeIfGetSensorWithTheMinimumDistanceToHouseWorks() {
        Sensor s1 = new Sensor("sensor1", new TypeSensor("temperature"), new Local(4, 6), new GregorianCalendar(4, 4, 4).getTime());
        Sensor s2 = new Sensor("sensor2", new TypeSensor("temperature"), new Local(4, 8), new GregorianCalendar(4, 4, 4).getTime());
        SensorList sensorList = new SensorList();
        sensorList.addSensor(s1);
        sensorList.addSensor(s2);
        GeographicArea ga = new GeographicArea(new TypeArea("cidade"), new Local(4, 5), sensorList);
        House house = new House("casa", "rua coise", new Local(4, 5), "440-4");
        Sensor result = house.getSensorWithTheMinimumDistanceToHouse(ga, house);
        assertEquals(s1, result);
    }

    @Test
    public void seeIfGetSensorWithTheMinimumDistanceToHouseWorks2() {
        Sensor s1 = new Sensor("sensor1", new TypeSensor("temperature"), new Local(4, 8), new GregorianCalendar(4, 4, 4).getTime());
        Sensor s2 = new Sensor("sensor2", new TypeSensor("temperature"), new Local(4, 6), new GregorianCalendar(4, 4, 4).getTime());
        SensorList sensorList = new SensorList();
        sensorList.addSensor(s1);
        sensorList.addSensor(s2);
        GeographicArea ga = new GeographicArea(new TypeArea("cidade"), new Local(4, 5), sensorList);
        House house = new House("casa", "rua coise", new Local(4, 5), "440-4");
        Sensor result = house.getSensorWithTheMinimumDistanceToHouse(ga, house);
        assertEquals(s2, result);
    }

    @Test
    public void seeIfGetSensorWithTheMinimumDistanceToHouseNull() {
        Sensor s1 = new Sensor("sensor1", new TypeSensor("temperature"), new Local(4, 8), new GregorianCalendar(4, 4, 4).getTime());
        Sensor s2 = new Sensor("sensor2", new TypeSensor("temperature"), new Local(4, 6), new GregorianCalendar(4, 4, 4).getTime());
        SensorList sensorList = new SensorList();
        sensorList.addSensor(s1);
        sensorList.addSensor(s2);
        GeographicArea ga = new GeographicArea(new TypeArea("cidade"), new Local(4, 5));
        House house = new House("casa", "rua coise", new Local(4, 5), "440-4");
        Sensor result = house.getSensorWithTheMinimumDistanceToHouse(ga, house);
        assertEquals(null, result);
    }

    @Test
    public void ensureThatAObjectIsAInstanceOf() {
        House house = new House("casa", "rua coise", new Local(4, 5), "440-4");
        House house2 = new House("casa", "rua coise", new Local(4, 5), "440-4");
        Boolean expectedResult = true;

        Boolean actualResult = house.equals(house2);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void ensureThatAObjectIsNotAInstanceOf() {
        House house = new House("casa", "rua coise", new Local(4, 5), "440-4");
        House house2 = new House("casa", "rua coisa", new Local(4, 5), "440-4");
        Boolean expectedResult = false;

        Boolean actualResult = house.equals(house2);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void ensureThatAObjectIsNotAInstanceOfNull() {
        House house = new House("casa", "rua coise", new Local(4, 5), "440-4");
        Boolean expectedResult = false;

        Boolean actualResult = house.equals(null);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfAddRoomToRoomList() {
        House house = new House("casa", "rua coise", new Local(4, 5), "440-4");
        RoomList rL1 = new RoomList();
        house.setmRoomList(rL1);
        Room room = new Room("quarto", 1, 80);
        Boolean expectedResult = true;
        Boolean result = house.addRoomToRoomList(room);

        assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfFailsAddRoomToRoomList() {
        House house = new House("casa", "rua coise", new Local(4, 5), "440-4");
        RoomList rL1 = new RoomList();
        house.setmRoomList(rL1);
        Room room = new Room("quarto", 1, 80);
        Room room2 = new Room("quarto", 2, 80);
        Boolean expectedResult = false;
        house.addRoomToRoomList(room2);
        Boolean result = house.addRoomToRoomList(room);

        assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfRecognizesEmptyGridList () {
        House house = new House("casa", "rua coise", new Local(4, 5),"5150-657");
        EnergyGridList energyGridList = new EnergyGridList();
        house.setmEGList(energyGridList);
        String expectedResult = "Invalid List - List is Empty\n";
        String actualResult = house.printWholeGridList();
        assertEquals(expectedResult,actualResult);
    }

    @Test
    public void checkIfWeReceivePrintedGridList (){
        House house = new House("casa", "rua coise", new Local(4, 5),"5150-657");
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
        String actualResult = house.printWholeGridList();
        assertEquals(expectedResult,actualResult);
    }
}

