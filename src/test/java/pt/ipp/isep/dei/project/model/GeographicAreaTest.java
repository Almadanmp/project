package pt.ipp.isep.dei.project.model;


import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

public class GeographicAreaTest {


    @Test
    public void seeIfGetSetTypeWorksIfSameAsGivenConstructor() {
        //Arrange
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7);
        TypeArea expectedResult = t1;
        TypeArea actualResult;
        GeographicArea c = new GeographicArea(t1, l1);
        //Act
        c.setTypeArea(t1);
        actualResult = c.getTypeArea();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfFirstConstructorSetsTypeArea() {
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7);
        TypeArea expectedResult = t1;
        TypeArea actualResult;
        GeographicArea c = new GeographicArea(t1, l1);
        actualResult = c.getTypeArea();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfSecondConstructorSetsTypeArea() {
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(21, 38, 40);
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosférico"), new Local(12, 31, 21), new GregorianCalendar(2010, 8, 9).getTime());
        SensorList list1 = new SensorList(s1);
        GeographicArea c = new GeographicArea(t1, l1, list1);
        TypeArea expectedResult = t1;
        TypeArea actualResult;
        actualResult = c.getTypeArea();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfSecondConstructorSetsLocal() {
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(21, 38, 40);
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosférico"), new Local(12, 31, 21), new GregorianCalendar(2010, 8, 9).getTime());
        SensorList list1 = new SensorList(s1);
        GeographicArea c = new GeographicArea(t1, l1, list1);
        Local expectedResult = l1;
        Local actualResult;
        actualResult = c.getLocal();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfSecondConstructorSetsSensorList() {
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(21, 38, 40);
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosférico"), new Local(12, 31, 21), new GregorianCalendar(2010, 8, 9).getTime());
        SensorList list1 = new SensorList(s1);
        GeographicArea c = new GeographicArea(t1, l1, list1);
        SensorList expectedResult = list1;
        SensorList actualResult;
        actualResult = c.getSensorList();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfConstructorWithVerticesWorksTopLeft() {
        //Arrange

        Local v1 = new Local(0, 30);
        Local v2 = new Local(30, 0);
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(21, 25, 40);
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosférico"), new Local(12, 31, 21), new GregorianCalendar(2010, 8, 9).getTime());
        SensorList list1 = new SensorList(s1);
        GeographicArea c = new GeographicArea(t1, l1, list1, v1, v2);
        Local expectedResult = new Local(0, 30);
        Local actualResult;

        //Act

        actualResult = c.getTopLeftVertex();

        //Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfConstructorWithVerticesWorksBottomRight() {
        //Arrange

        Local v1 = new Local(0, 30);
        Local v2 = new Local(30, 0);
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(10, 10, 40);
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosférico"), new Local(12, 31, 21), new GregorianCalendar(2010, 8, 9).getTime());
        SensorList list1 = new SensorList(s1);
        GeographicArea c = new GeographicArea(t1, l1, list1, v1, v2);
        Local expectedResult = new Local(30, 0);
        Local actualResult;

        //Act

        actualResult = c.getBottomRightVertex();

        //Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfConstructorWithVerticesWorksTypeArea() {
        //Arrange

        Local v1 = new Local(21, 31);
        Local v2 = new Local(32, 66);
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(21, 38, 40);
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosférico"), new Local(12, 31, 21), new GregorianCalendar(2010, 8, 9).getTime());
        SensorList list1 = new SensorList(s1);
        GeographicArea c = new GeographicArea(t1, l1, list1, v1, v2);
        TypeArea expectedResult = t1;
        TypeArea actualResult;

        //Act

        actualResult = c.getTypeArea();

        //Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfConstructorWithVerticesWorksLocal() {
        //Arrange

        Local v1 = new Local(21, 31);
        Local v2 = new Local(32, 66);
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(21, 38, 40);
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosférico"), new Local(12, 31, 21), new GregorianCalendar(2010, 8, 9).getTime());
        SensorList list1 = new SensorList(s1);
        GeographicArea c = new GeographicArea(t1, l1, list1, v1, v2);
        Local expectedResult = l1;
        Local actualResult;
        actualResult = c.getLocal();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfConstructorWithVerticesWorksSensorList() {
        //Arrange

        Local v1 = new Local(21, 31);
        Local v2 = new Local(32, 66);
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(21, 38, 40);
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosférico"), new Local(12, 31, 21), new GregorianCalendar(2010, 8, 9).getTime());
        SensorList list1 = new SensorList(s1);
        GeographicArea c = new GeographicArea(t1, l1, list1, v1, v2);
        SensorList expectedResult = list1;
        SensorList actualResult;
        actualResult = c.getSensorList();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfGetSetWorksTopLeftVertex() {
        //Arrange

        Local topLeftVertex = new Local(15, 88);
        Local v1 = new Local(21, 31);
        Local v2 = new Local(32, 66);
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(21, 38, 40);
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosférico"), new Local(12, 31, 21), new GregorianCalendar(2010, 8, 9).getTime());
        SensorList list1 = new SensorList(s1);
        GeographicArea c = new GeographicArea(t1, l1, list1, v1, v2);
        Local expectedResult = new Local(15, 88);
        Local actualResult;

        //Act

        c.setTopLeftVertex(topLeftVertex);
        actualResult = c.getTopLeftVertex();

        //Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfGetSetWorksBottomRightVertex() {
        //Arrange

        Local bottomRightVertex = new Local(40, 10);
        Local v1 = new Local(0, 30);
        Local v2 = new Local(30, 0);
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(10, 10, 40);
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosférico"), new Local(12, 31, 21), new GregorianCalendar(2010, 8, 9).getTime());
        SensorList list1 = new SensorList(s1);
        GeographicArea c = new GeographicArea(t1, l1, list1, v1, v2);
        Local expectedResult = new Local(40, 10);
        Local actualResult;

        //Act

        c.setBottomRightVertex(bottomRightVertex);
        actualResult = c.getBottomRightVertex();

        //Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfGetSetTypeWorksIfDifferentOfGivenConstructor() {
        //Arrange
        TypeArea t1 = new TypeArea("Rua");
        TypeArea t2 = new TypeArea("Freguesia");
        Local l1 = new Local(38, 7);
        TypeArea expectedResult = t2;
        TypeArea actualResult;
        GeographicArea c = new GeographicArea(t1, l1);

        //Act
        c.setTypeArea(t2);
        actualResult = c.getTypeArea();
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfGetSetLocalIfSameAsConstructorWorks() {
        //Arrange
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7);
        GeographicArea c = new GeographicArea(t1, l1);
        boolean expectedResult = true;

        //Act
        c.setLocal(l1);
        boolean actualResult = c.getLocal().equals(new Local(38, 7));

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfGetSetLocalIfDifferentOfConstructorWorks() {
        //Arrange
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7);
        Local l2 = new Local(65, 56);
        GeographicArea c = new GeographicArea(t1, l1);
        Local expectedResult = l2;
        Local actualResult;
        //Act
        c.setLocal(l2);
        actualResult = c.getLocal();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfGetSetSensorListSameAsConstructorWorks() {
        //Arrange
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(21, 38, 40);
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosférico"), new Local(12, 31, 21), new GregorianCalendar(2010, 8, 9).getTime());
        SensorList list1 = new SensorList(s1);
        SensorList expectedResult = list1;
        SensorList actualResult;
        GeographicArea c = new GeographicArea(t1, l1, list1);

        //Act
        c.setSensorList(list1);
        actualResult = c.getSensorList();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfGetSetSensorListDifferentOfConstructorWorks() {
        //Arrange
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(21, 38, 40);
        Sensor s1 = new Sensor("Pluviosidade", new TypeSensor("Pluviosidade"), new Local(12, 31, 21), new GregorianCalendar(2010, 8, 9).getTime());
        Sensor s2 = new Sensor("Temperatura", new TypeSensor("Temperatura"), new Local(43, 23, 65), new GregorianCalendar(2010, 8, 9).getTime());
        SensorList list1 = new SensorList(s1);
        SensorList list2 = new SensorList(s2);
        SensorList expectedResult = list2;
        SensorList actualResult;
        GeographicArea c = new GeographicArea(t1, l1, list1);

        //Act
        c.setSensorList(list2);
        actualResult = c.getSensorList();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfSetSensorListAcceptsListSameAsConstructor() {
        //Arrange
        Sensor s1 = new Sensor("Vento1", new TypeSensor("Vento"), new Local(21, 65, 21), new GregorianCalendar(2016, 8, 6).getTime());
        Sensor s2 = new Sensor("Vento2", new TypeSensor("Vento"), new Local(32, 87, 21), new GregorianCalendar(2016, 2, 19).getTime());
        Sensor s3 = new Sensor("Pluviosidade1", new TypeSensor("Pluviosidade"), new Local(56, 31, 11), new GregorianCalendar(2011, 8, 2).getTime());
        SensorList l1 = new SensorList(new Sensor[]{s1, s2, s3});
        GeographicArea c = new GeographicArea(new TypeArea("Rua"), new Local(12, 35, 2), l1);
        SensorList expectedResult = l1;
        SensorList actualResult;
        //Act
        c.setSensorList(l1);
        actualResult = c.getSensorList();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfSetSensorListAcceptsListDifferentThenConstructor() {
        //Arrange
        Sensor s1 = new Sensor("Vento1", new TypeSensor("Vento"), new Local(21, 65, 21), new GregorianCalendar(2016, 8, 6).getTime());
        Sensor s2 = new Sensor("Vento2", new TypeSensor("Vento"), new Local(32, 87, 21), new GregorianCalendar(2016, 2, 19).getTime());
        Sensor s3 = new Sensor("Pluviosidade1", new TypeSensor("Pluviosidade"), new Local(56, 31, 11), new GregorianCalendar(2011, 8, 2).getTime());
        SensorList l1 = new SensorList(new Sensor[]{s1, s2, s3});
        GeographicArea c = new GeographicArea(new TypeArea("Rua"), new Local(12, 35, 2), new SensorList(s1));
        SensorList expectedResult = l1;
        SensorList actualResult;
        //Act
        c.setSensorList(l1);
        actualResult = c.getSensorList();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfSetNameWorksNullAndThrowsStringMessage() {
        //Arrange
        String name1 = "Porto";
        TypeArea tA1 = new TypeArea("rua");
        Local l1 = new Local(11, 12);
        GeographicArea gA1 = new GeographicArea(name1, tA1, l1);

        //Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            gA1.setName(null);
        });

        //Assert
        assertEquals("Please Insert Valid Name", exception.getMessage());
    }

    @Test
    public void seeIfSetNameWorksEmptyAndThrowsStringException() {
        //Arrange
        String name1 = "Porto";
        TypeArea tA1 = new TypeArea("rua");
        Local l1 = new Local(11, 12);
        GeographicArea gA1 = new GeographicArea(name1, tA1, l1);

        //Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            gA1.setName("");
        });

        //Assert
        assertEquals("Please Insert Valid Name", exception.getMessage());
    }

    @Test
    public void seeIfConstructorWithOneParameterWorks() {
        //Arrange
        GeographicArea ga1 = new GeographicArea("Porto");
        String expectedResult = "Porto";

        //Act
        String actualResult = ga1.getName();
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfWeSetMotherArea() {
        //Arrange
        GeographicArea ga1 = new GeographicArea("Porto", new TypeArea("Cidade"), new Local(22, 23));
        GeographicArea ga2 = new GeographicArea("Portugal", new TypeArea("País"), new Local(22, 17));
        ga1.setMotherArea(ga2);
        GeographicArea expectedResult = ga2;

        //Act
        GeographicArea actualResult = ga1.getMotherArea();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void ensureThatAObjectIsNotAInstanceOf() {
        GeographicArea ga1 = new GeographicArea("Porto");
        Sensor s1 = new Sensor("Sensor 1", new TypeSensor("Temperatura"), new Local(22, 22), new GregorianCalendar(2018, 11, 25).getTime(), new ReadingList());
        Boolean expectedResult = false;

        Boolean actualResult = ga1.equals(s1);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void ensureThatAreaIsContained() {
        Sensor s1 = new Sensor("Sensor 1", new TypeSensor("Temperatura"), new Local(22, 22), new GregorianCalendar(2018, 11, 25).getTime(), new ReadingList());
        GeographicArea ga1 = new GeographicArea(new TypeArea("Cidade"), new Local(22, 22), new SensorList(s1), new Local(20, 24), new Local(24, 20));
        GeographicArea ga2 = new GeographicArea(new TypeArea("Cidade"), new Local(22, 22), new SensorList(s1), new Local(20, 24), new Local(24, 20));
        ga1.setMotherArea(ga2);
        Boolean expectedResult = true;
        Boolean actualResult = ga1.checkIfAreaIsContained(ga1, ga2);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void ensureThatAreaIsNotContained() {
        Sensor s1 = new Sensor("Sensor 1", new TypeSensor("Temperatura"), new Local(22, 22), new GregorianCalendar(2018, 11, 25).getTime(), new ReadingList());
        GeographicArea ga1 = new GeographicArea(new TypeArea("Cidade"), new Local(22, 22), new SensorList(s1), new Local(20, 24), new Local(24, 20));
        GeographicArea ga2 = new GeographicArea(new TypeArea("Cidade"), new Local(22, 22), new SensorList(s1), new Local(20, 24), new Local(24, 20));
        ga1.setMotherArea(ga2);
        Boolean expectedResult = false;
        Boolean actualResult = ga1.checkIfAreaIsContained(ga2, ga1);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void ensureThatGrandsonAreaIsContainedInGrandmotherArea() {
        Sensor s1 = new Sensor("Sensor 1", new TypeSensor("Temperatura"), new Local(22, 22), new GregorianCalendar(2018, 11, 25).getTime(), new ReadingList());
        GeographicArea grandDaughter = new GeographicArea("Porto", new TypeArea("Cidade"), new Local(22, 22));
        GeographicArea mother = new GeographicArea("Portugal", new TypeArea("Cidade"), new Local(22, 22));
        GeographicArea grandMother = new GeographicArea("Europa", new TypeArea("Cidade"), new Local(22, 22));
        grandDaughter.setMotherArea(mother);
        mother.setMotherArea(grandMother);
        Boolean expectedResult = true;
        Boolean actualResult = grandDaughter.checkIfAreaIsContained(grandDaughter, grandMother);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfGetsAverageOfSensorTypeOnAGeographicArea() {
        //Arrange
        ReadingList readingList = new ReadingList();
        Reading r1 = new Reading(15, new GregorianCalendar(2018, 11, 3).getTime());
        Reading r2 = new Reading(19, new GregorianCalendar(2018, 11, 4).getTime());
        Reading r3 = new Reading(17, new GregorianCalendar(2018, 11, 1).getTime());
        ReadingList readingList2 = new ReadingList();
        readingList.addReading(r1);
        readingList.addReading(r2);
        readingList.addReading(r3);
        Reading r4 = new Reading(20, new GregorianCalendar(2018, 11, 20).getTime());
        Reading r5 = new Reading(25, new GregorianCalendar(2018, 11, 2).getTime());
        Reading r6 = new Reading(45, new GregorianCalendar(2018, 11, 1).getTime());
        readingList2.addReading(r4);
        readingList2.addReading(r5);
        readingList2.addReading(r6);
        Sensor s1 = new Sensor("Sensor 1", new TypeSensor("Rainfall"), new Local(16, 17, 18), new GregorianCalendar(2010, 8, 9).getTime(), readingList);
        Sensor s2 = new Sensor("Sensor 2", new TypeSensor("Temperature"), new Local(16, 17, 18), new GregorianCalendar(2010, 8, 9).getTime(), readingList2);
        Sensor s3 = new Sensor("Sensor 3", new TypeSensor("Rainfall"), new Local(16, 17, 18), new GregorianCalendar(2010, 8, 9).getTime(), readingList2);
        SensorList sensorList = new SensorList();
        sensorList.addSensor(s2);
        sensorList.addSensor(s1);
        sensorList.addSensor(s3);

        GeographicArea ga1 = new GeographicArea(new TypeArea("Rua"), new Local(16, 17, 18), sensorList);
        GregorianCalendar dateMin = new GregorianCalendar(2018, 11, 1);
        GregorianCalendar dateMax = new GregorianCalendar(2018, 11, 20);
        Date dateToTest1 = dateMin.getTime();
        Date dateToTest2 = dateMax.getTime();
        double expectedResult = 23.5;
        //Act
        double result = ga1.getAvgReadingsFromSensorTypeInGA("Rainfall", dateToTest1, dateToTest2);
        //Assert
        assertEquals(expectedResult, result, 0.01);
    }

    @Test
    public void seeIFChecksvalidListOnAverageOfSensorTypeOnAGeographicArea() {
        //Arrange
        ReadingList readingList = new ReadingList();
        Reading r1 = new Reading(15, new GregorianCalendar(2018, 11, 3).getTime());
        Reading r2 = new Reading(19, new GregorianCalendar(2018, 11, 4).getTime());
        Reading r3 = new Reading(17, new GregorianCalendar(2018, 11, 1).getTime());
        ReadingList readingList2 = new ReadingList();
        readingList.addReading(r1);
        readingList.addReading(r2);
        readingList.addReading(r3);
        Reading r4 = new Reading(20, new GregorianCalendar(2018, 11, 20).getTime());
        Reading r5 = new Reading(25, new GregorianCalendar(2018, 11, 2).getTime());
        Reading r6 = new Reading(45, new GregorianCalendar(2018, 11, 1).getTime());
        readingList2.addReading(r4);
        readingList2.addReading(r5);
        readingList2.addReading(r6);
        SensorList sensorList = new SensorList();
        GeographicArea ga1 = new GeographicArea(new TypeArea("Rua"), new Local(16, 17, 18), sensorList);
        GregorianCalendar dateMin = new GregorianCalendar(2018, 11, 1);
        GregorianCalendar dateMax = new GregorianCalendar(2018, 11, 20);
        Date dateToTest1 = dateMin.getTime();
        Date dateToTest2 = dateMax.getTime();
        double expectedResult = -1;
        //Act
        double result = ga1.getAvgReadingsFromSensorTypeInGA("Rainfall", dateToTest1, dateToTest2);
        //Assert
        assertEquals(expectedResult, result, 0.01);
    }

    @Test
    public void seeWhatReturnsIfListWithoutInputTypeSensor() {
        //Arrange
        ReadingList readingList = new ReadingList();
        Reading r1 = new Reading(15, new GregorianCalendar(2018, 11, 3).getTime());
        Reading r2 = new Reading(19, new GregorianCalendar(2018, 11, 4).getTime());
        Reading r3 = new Reading(17, new GregorianCalendar(2018, 11, 1).getTime());
        ReadingList readingList2 = new ReadingList();
        readingList.addReading(r1);
        readingList.addReading(r2);
        readingList.addReading(r3);
        Reading r4 = new Reading(20, new GregorianCalendar(2018, 11, 20).getTime());
        Reading r5 = new Reading(25, new GregorianCalendar(2018, 11, 2).getTime());
        Reading r6 = new Reading(45, new GregorianCalendar(2018, 11, 1).getTime());
        readingList2.addReading(r4);
        readingList2.addReading(r5);
        readingList2.addReading(r6);
        Sensor s1 = new Sensor("Sensor 1", new TypeSensor("Rainfall"), new Local(16, 17, 18), new GregorianCalendar(2010, 8, 9).getTime(), readingList);
        Sensor s2 = new Sensor("Sensor 2", new TypeSensor("Temperature"), new Local(16, 17, 18), new GregorianCalendar(2010, 8, 9).getTime(), readingList2);
        Sensor s3 = new Sensor("Sensor 3", new TypeSensor("Rainfall"), new Local(16, 17, 18), new GregorianCalendar(2010, 8, 9).getTime(), readingList2);
        SensorList sensorList = new SensorList();
        sensorList.addSensor(s2);
        sensorList.addSensor(s1);
        sensorList.addSensor(s3);

        GeographicArea ga1 = new GeographicArea(new TypeArea("Rua"), new Local(16, 17, 18), sensorList);
        GregorianCalendar dateMin = new GregorianCalendar(2018, 11, 1);
        GregorianCalendar dateMax = new GregorianCalendar(2018, 11, 20);
        Date dateToTest1 = dateMin.getTime();
        Date dateToTest2 = dateMax.getTime();
        double expectedResult = -1;
        //Act
        double result = ga1.getAvgReadingsFromSensorTypeInGA("Wind", dateToTest1, dateToTest2);
        //Assert
        assertEquals(expectedResult, result, 0.01);
    }

    @Test
    public void seeIfPrintGeoHouseListWorks() {
        String address1 = "rua da rua 345";
        String address2 = "rua da rua 455";
        String zipCode = "4450";
        double latitude = 38;
        double longitude = 7;
        Local local = new Local(latitude, longitude);
        House house1 = new House("1175", address1, local, zipCode);
        House house2 = new House("4350", address2, local, zipCode);
        HouseList houseList = new HouseList(house1);
        houseList.addHouseToHouseList(house2);

        String result = houseList.printHouseList();
        String expectedResult =
                "House List: \n" +
                        "-1175; " +
                        "\n" +
                        "-4350;";
        assertEquals(result, expectedResult);
    }

    @Test
    public void seeIfPrintsGeoHouseListEmpty() {
        HouseList list = new HouseList();
        String result = list.printHouseList();
        String expectedResult = "The list is empty.";
        assertEquals(result, expectedResult);
    }

    @Test
    public void seeIfSensorListIsContainedInGAList() {
        //Arrange

        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 10, 4).getTime());
        Sensor s2 = new Sensor("Pluviosidade", new TypeSensor("Pluviosidade"),
                new Local(10, 30, 20),
                new GregorianCalendar(118, 12, 4).getTime());

        SensorList slist1 = new SensorList();
        slist1.addSensor(s1);
        slist1.addSensor(s2);
        GeographicArea ga1 = new GeographicArea();
        ga1.setSensorList(slist1);
        GeographicAreaList glist1 = new GeographicAreaList();
        glist1.addGeographicAreaToGeographicAreaList(ga1);
        //Act

        boolean actualResult = ga1.doesSensorListInAGeoAreaContainASensorByName("Vento");
        //Assert
        assertTrue(actualResult);
    }

    @Test
    public void seeIfSensorListIsNOTContainedInGAList() {
        //Arrange

        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 10, 4).getTime());
        Sensor s2 = new Sensor("Pluviosidade", new TypeSensor("Pluviosidade"),
                new Local(10, 30, 20),
                new GregorianCalendar(118, 12, 4).getTime());

        SensorList slist1 = new SensorList();
        slist1.addSensor(s1);
        slist1.addSensor(s2);
        GeographicArea ga1 = new GeographicArea();
        ga1.setSensorList(slist1);
        GeographicAreaList glist1 = new GeographicAreaList();
        glist1.addGeographicAreaToGeographicAreaList(ga1);
        //Act

        boolean actualResult = ga1.doesSensorListInAGeoAreaContainASensorByName("Humidade");
        //Assert
        assertFalse(actualResult);
    }
}

