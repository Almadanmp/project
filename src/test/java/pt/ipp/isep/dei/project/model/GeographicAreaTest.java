package pt.ipp.isep.dei.project.model;


import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class GeographicAreaTest {


    @Test
    void seeIfGetSetTypeWorksIfSameAsGivenConstructor() {
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
    void seeIfFirstConstructorSetsTypeArea() {
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7);
        TypeArea expectedResult = t1;
        TypeArea actualResult;
        GeographicArea c = new GeographicArea(t1, l1);
        actualResult = c.getTypeArea();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSecondConstructorSetsTypeArea() {
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
    void seeIfSecondConstructorSetsLocal() {
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
    void seeIfSecondConstructorSetsSensorList() {
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
    void seeIfConstructorWithVerticesWorksTypeArea() {
        //Arrange

        double width = 10;
        double length = 20;
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(21, 38, 40);
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosférico"), new Local(12, 31, 21), new GregorianCalendar(2010, 8, 9).getTime());
        SensorList list1 = new SensorList(s1);
        GeographicArea c = new GeographicArea(t1, l1, list1, width, length);
        TypeArea actualResult;

        //Act

        actualResult = c.getTypeArea();

        //Assert

        assertEquals(t1, actualResult);
    }

    @Test
    void seeIfConstructorWithVerticesWorksLocal() {
        //Arrange

        double width = 10;
        double length = 20;
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(21, 38, 40);
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosférico"), new Local(12, 31, 21), new GregorianCalendar(2010, 8, 9).getTime());
        SensorList list1 = new SensorList(s1);
        GeographicArea c = new GeographicArea(t1, l1, list1, width, length);
        Local actualResult;
        actualResult = c.getLocal();
        assertEquals(l1, actualResult);
    }

    @Test
    void seeIfConstructorWithVerticesWorksSensorList() {
        //Arrange

        double width = 10;
        double length = 20;
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(21, 38, 40);
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosférico"), new Local(12, 31, 21), new GregorianCalendar(2010, 8, 9).getTime());
        SensorList list1 = new SensorList(s1);
        GeographicArea c = new GeographicArea(t1, l1, list1, width, length);
        SensorList actualResult;
        actualResult = c.getSensorList();
        assertEquals(list1, actualResult);
    }

    @Test
    void seeIfGetSetTypeWorksIfDifferentOfGivenConstructor() {
        //Arrange
        TypeArea t1 = new TypeArea("Rua");
        TypeArea t2 = new TypeArea("Freguesia");
        Local l1 = new Local(38, 7);
        TypeArea actualResult;
        GeographicArea c = new GeographicArea(t1, l1);

        //Act
        c.setTypeArea(t2);
        actualResult = c.getTypeArea();
        //Assert
        assertEquals(t2, actualResult);
    }

    @Test
    void seeIfGetSetLocalIfSameAsConstructorWorks() {
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
    void seeIfGetSetLocalIfDifferentOfConstructorWorks() {
        //Arrange
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7);
        Local l2 = new Local(65, 56);
        GeographicArea c = new GeographicArea(t1, l1);
        Local actualResult;
        //Act
        c.setLocal(l2);
        actualResult = c.getLocal();

        //Assert
        assertEquals(l2, actualResult);
    }

    @Test
    void seeIfGetSetSensorListSameAsConstructorWorks() {
        //Arrange
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(21, 38, 40);
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosférico"), new Local(12, 31, 21), new GregorianCalendar(2010, 8, 9).getTime());
        SensorList list1 = new SensorList(s1);
        SensorList actualResult;
        GeographicArea c = new GeographicArea(t1, l1, list1);
        //Act
        c.setSensorList(list1);
        actualResult = c.getSensorList();
        //Assert
        assertEquals(list1, actualResult);
    }

    @Test
    void seeIfGetSetSensorListDifferentOfConstructorWorks() {
        //Arrange
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(21, 38, 40);
        Sensor s1 = new Sensor("Pluviosidade", new TypeSensor("Pluviosidade"), new Local(12, 31, 21), new GregorianCalendar(2010, 8, 9).getTime());
        Sensor s2 = new Sensor("Temperatura", new TypeSensor("Temperatura"), new Local(43, 23, 65), new GregorianCalendar(2010, 8, 9).getTime());
        SensorList list1 = new SensorList(s1);
        SensorList list2 = new SensorList(s2);
        SensorList actualResult;
        GeographicArea c = new GeographicArea(t1, l1, list1);
        //Act
        c.setSensorList(list2);
        actualResult = c.getSensorList();
        //Assert
        assertEquals(list2, actualResult);
    }

    @Test
    void seeIfSetSensorListAcceptsListSameAsConstructor() {
        //Arrange
        Sensor s1 = new Sensor("Vento1", new TypeSensor("Vento"), new Local(21, 65, 21), new GregorianCalendar(2016, 8, 6).getTime());
        Sensor s2 = new Sensor("Vento2", new TypeSensor("Vento"), new Local(32, 87, 21), new GregorianCalendar(2016, 2, 19).getTime());
        Sensor s3 = new Sensor("Pluviosidade1", new TypeSensor("Pluviosidade"), new Local(56, 31, 11), new GregorianCalendar(2011, 8, 2).getTime());
        SensorList l1 = new SensorList(new Sensor[]{s1, s2, s3});
        GeographicArea c = new GeographicArea(new TypeArea("Rua"), new Local(12, 35, 2), l1);
        SensorList actualResult;
        //Act
        c.setSensorList(l1);
        actualResult = c.getSensorList();

        //Assert
        assertEquals(l1, actualResult);
    }

    @Test
    void seeIfSetSensorListAcceptsListDifferentThenConstructor() {
        //Arrange
        Sensor s1 = new Sensor("Vento1", new TypeSensor("Vento"), new Local(21, 65, 21), new GregorianCalendar(2016, 8, 6).getTime());
        Sensor s2 = new Sensor("Vento2", new TypeSensor("Vento"), new Local(32, 87, 21), new GregorianCalendar(2016, 2, 19).getTime());
        Sensor s3 = new Sensor("Pluviosidade1", new TypeSensor("Pluviosidade"), new Local(56, 31, 11), new GregorianCalendar(2011, 8, 2).getTime());
        SensorList l1 = new SensorList(new Sensor[]{s1, s2, s3});
        GeographicArea c = new GeographicArea(new TypeArea("Rua"), new Local(12, 35, 2), new SensorList(s1));
        SensorList actualResult;
        //Act
        c.setSensorList(l1);
        actualResult = c.getSensorList();

        //Assert
        assertEquals(l1, actualResult);
    }


    @Test
    void seeIfCalculateDistanceToDifferentGAWorks() {
        //Arrange
        Local l1 = new Local(23, 46);
        Local l2 = new Local(25, 47);
        TypeArea t1 = new TypeArea("Porto");
        TypeArea t2 = new TypeArea("Braga");
        GeographicArea ga1 = new GeographicArea(t1, l1);
        GeographicArea ga2 = new GeographicArea(t2, l2);
        double result = ga1.calculateDistanceToGA(ga2);
        //Act
        double expectedResult = 244;
        //Assert
        assertEquals(expectedResult, result, 1);
    }

    @Test
    void seeIfCalculateDistanceToDifferentGAWithSameLocalizationWorks() {
        //Arrange
        Local l1 = new Local(23, 46);
        Local l2 = new Local(23, 46);
        TypeArea t1 = new TypeArea("Porto");
        TypeArea t2 = new TypeArea("Braga");
        GeographicArea ga1 = new GeographicArea(t1, l1);
        GeographicArea ga2 = new GeographicArea(t2, l2);
        double result = ga1.calculateDistanceToGA(ga2);
        //Act
        double expectedResult = 0;
        //Assert
        assertEquals(expectedResult, result, 1);
    }

    @Test
    void seeIfCalculateDistanceToSameGAWorks() {
        //Arrange
        Local l1 = new Local(23, 46);
        Local l2 = new Local(23, 46);
        TypeArea t1 = new TypeArea("Porto");
        TypeArea t2 = new TypeArea("Porto");
        GeographicArea ga1 = new GeographicArea(t1, l1);
        GeographicArea ga2 = new GeographicArea(t2, l2);
        double result = ga1.calculateDistanceToGA(ga2);
        //Act
        double expectedResult = 0;
        //Assert
        assertEquals(expectedResult, result, 1);
    }

    @Test
    void seeThatWeGetMostRecentReadingValueOfACertainTypeOfSensor() {
        //Arrange
        ReadingList readingList = new ReadingList();
        Reading r1 = new Reading(15, new GregorianCalendar(2018, 12, 3).getTime());
        Reading r2 = new Reading(19, new GregorianCalendar(2018, 12, 4).getTime());
        Reading r3 = new Reading(17, new GregorianCalendar(2018, 12, 1).getTime());
        readingList.addReading(r1);
        readingList.addReading(r2);
        readingList.addReading(r3);
        Sensor s1 = new Sensor("Sensor 1", new TypeSensor("Temperatura"), new Local(16, 17, 18), new GregorianCalendar(2010, 8, 9).getTime(), readingList);
        SensorList sensorList = new SensorList(s1);
        GeographicArea ga1 = new GeographicArea(new TypeArea("Rua"), new Local(16, 17, 18), sensorList);
        double expectedResult = 19;
        //Act
        double result = ga1.getMostRecentReadingValue("Temperatura");
        //Assert
        assertEquals(expectedResult, result, 0.01);

    }

    @Test
    void seeThatWeGetMostRecentReadingValueOfACertainTypeOfSensorWithMultipleSensorsOfSameType() {
        //Arrange
        ReadingList readingList = new ReadingList();
        Reading r1 = new Reading(15, new GregorianCalendar(2018, 12, 3).getTime());
        Reading r2 = new Reading(19, new GregorianCalendar(2018, 12, 4).getTime());
        Reading r3 = new Reading(17, new GregorianCalendar(2018, 12, 1).getTime());
        ReadingList readingList2 = new ReadingList();
        Reading r4 = new Reading(20, new GregorianCalendar(2018, 12, 20).getTime());
        Reading r5 = new Reading(25, new GregorianCalendar(2018, 12, 2).getTime());
        Reading r6 = new Reading(45, new GregorianCalendar(2018, 12, 1).getTime());
        readingList.addReading(r1);
        readingList.addReading(r2);
        readingList.addReading(r3);
        readingList2.addReading(r4);
        readingList2.addReading(r5);
        readingList2.addReading(r6);
        Sensor s1 = new Sensor("Sensor 1", new TypeSensor("Temperatura"), new Local(16, 17, 18), new GregorianCalendar(2010, 8, 9).getTime(), readingList);
        Sensor s2 = new Sensor("Sensor 2", new TypeSensor("Temperatura"), new Local(16, 17, 18), new GregorianCalendar(2010, 8, 9).getTime(), readingList2);
        SensorList sensorList = new SensorList(s1);
        sensorList.addSensor(s2);
        GeographicArea ga1 = new GeographicArea(new TypeArea("Rua"), new Local(16, 17, 18), sensorList);
        double expectedResult = 20;
        //Act
        double result = ga1.getMostRecentReadingValue("Temperatura");
        //Assert
        assertEquals(expectedResult, result, 0.01);
    }

    @Test
    void seeThatWeGetMostRecentReadingValueOfACertainTypeOfSensorWithMultipleSensorsOfDifferentType() {
        //Arrange
        ReadingList readingList = new ReadingList();
        Reading r1 = new Reading(15, new GregorianCalendar(2018, 12, 3).getTime());
        Reading r2 = new Reading(19, new GregorianCalendar(2018, 12, 21).getTime());
        Reading r3 = new Reading(17, new GregorianCalendar(2018, 12, 1).getTime());
        ReadingList readingList2 = new ReadingList();
        Reading r4 = new Reading(20, new GregorianCalendar(2018, 12, 20).getTime());
        Reading r5 = new Reading(25, new GregorianCalendar(2018, 12, 2).getTime());
        Reading r6 = new Reading(45, new GregorianCalendar(2018, 12, 1).getTime());
        readingList.addReading(r1);
        readingList.addReading(r2);
        readingList.addReading(r3);
        readingList2.addReading(r4);
        readingList2.addReading(r5);
        readingList2.addReading(r6);
        Sensor s1 = new Sensor("Sensor 1", new TypeSensor("Temperatura"), new Local(16, 17, 18), new GregorianCalendar(2010, 8, 9).getTime(), readingList);
        Sensor s2 = new Sensor("Sensor 2", new TypeSensor("Pluviosidade"), new Local(16, 17, 18), new GregorianCalendar(2010, 8, 9).getTime(), readingList2);
        SensorList sensorList = new SensorList(s1);
        sensorList.addSensor(s2);
        GeographicArea ga1 = new GeographicArea(new TypeArea("Rua"), new Local(16, 17, 18), sensorList);
        double expectedResult = 19;
        //Act
        double result = ga1.getMostRecentReadingValue("Temperatura");
        //Assert
        assertEquals(expectedResult, result, 0.01);

    }

    @Test
    void seeThatWeRemoveAListWithAnotherType() {
        //Arrange
        ReadingList readingList = new ReadingList();
        Reading r1 = new Reading(15, new GregorianCalendar(2018, 12, 3).getTime());
        Reading r2 = new Reading(19, new GregorianCalendar(2018, 12, 4).getTime());
        Reading r3 = new Reading(17, new GregorianCalendar(2018, 12, 1).getTime());
        ReadingList readingList2 = new ReadingList();
        Reading r4 = new Reading(20, new GregorianCalendar(2018, 12, 20).getTime());
        Reading r5 = new Reading(25, new GregorianCalendar(2018, 12, 2).getTime());
        Reading r6 = new Reading(45, new GregorianCalendar(2018, 12, 1).getTime());
        readingList.addReading(r1);
        readingList.addReading(r2);
        readingList.addReading(r3);
        readingList2.addReading(r4);
        readingList2.addReading(r5);
        readingList2.addReading(r6);
        Sensor s1 = new Sensor("Sensor 1", new TypeSensor("Temperatura"), new Local(16, 17, 18), new GregorianCalendar(2010, 8, 9).getTime(), readingList);
        Sensor s2 = new Sensor("Sensor 2", new TypeSensor("Pluviosidade"), new Local(16, 17, 18), new GregorianCalendar(2010, 8, 9).getTime(), readingList2);
        SensorList sensorList = new SensorList(s1);
        sensorList.addSensor(s2);
        GeographicArea ga1 = new GeographicArea(new TypeArea("Rua"), new Local(16, 17, 18), sensorList);
        double expectedResult = 19;
        //Act
        double result = ga1.getMostRecentReadingValue("Temperatura");
        //Assert
        assertEquals(expectedResult, result, 0.01);
    }


    @Test
    void seeIfAreaIsContainedInArea() {
        //Arrange
        TypeArea t1 = new TypeArea("Terriola");
        TypeArea t2 = new TypeArea("Cidade");
        Local l1 = new Local(20, 15);
        GeographicArea a1 = new GeographicArea(t1, l1);
        Local l2 = new Local(20, 20);
        GeographicArea a2 = new GeographicArea(t2, l2);
        //Act
        a1.setWidth(10);
        a1.setLength(20);
        a2.setWidth(5);
        a2.setLength(10);
        boolean result = a1.isAreaContainedInAnotherArea(a1, a2);

        //Assert
        assertEquals(true, result);
    }

    @Test
    void seeIfSetNameWorksNullAndThrowsStringMessage() {
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
    void seeIfSetNameWorksEmptyAndThrowsStringException() {
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
    void seeIfConstructorWithOneParameterWorks() {
        //Arrange
        GeographicArea ga1 = new GeographicArea("Porto");
        String expectedResult = "Porto";

        //Act
        String actualResult = ga1.getName();
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfWeSetMotherArea() {
        //Arrange
        GeographicArea ga1 = new GeographicArea("Porto", new TypeArea("Cidade"), new Local(22, 23));
        GeographicArea ga2 = new GeographicArea("Portugal", new TypeArea("País"), new Local(22, 17));
        ga1.setMotherArea(ga2);

        //Act
        GeographicArea actualResult = ga1.getMotherArea();

        //Assert
        assertEquals(ga2, actualResult);
    }

    @Test
    void ensureThatAObjectIsNotAInstanceOf() {
        GeographicArea ga1 = new GeographicArea("Porto");
        Sensor s1 = new Sensor("Sensor 1", new TypeSensor("Temperatura"), new Local(22, 22), new GregorianCalendar(2018, 11, 25).getTime(), new ReadingList());
        Boolean expectedResult = false;

        Boolean actualResult = ga1.equals(s1);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void ensureThatAreaIsContained() {
        Sensor s1 = new Sensor("Sensor 1", new TypeSensor("Temperatura"), new Local(22, 22), new GregorianCalendar(2018, 11, 25).getTime(), new ReadingList());
        GeographicArea ga1 = new GeographicArea(new TypeArea("Cidade"), new Local(22, 22), new SensorList(s1), 10, 20);
        GeographicArea ga2 = new GeographicArea(new TypeArea("Cidade"), new Local(22, 22), new SensorList(s1), 10, 20);
        ga1.setMotherArea(ga2);
        Boolean expectedResult = true;
        Boolean actualResult = ga1.checkIfAreaIsContained(ga1, ga2);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void ensureThatAreaIsNotContained() {
        Sensor s1 = new Sensor("Sensor 1", new TypeSensor("Temperatura"), new Local(22, 22), new GregorianCalendar(2018, 11, 25).getTime(), new ReadingList());
        GeographicArea ga1 = new GeographicArea(new TypeArea("Cidade"), new Local(22, 22), new SensorList(s1), 10, 20);
        GeographicArea ga2 = new GeographicArea(new TypeArea("Cidade"), new Local(22, 22), new SensorList(s1), 10, 20);
        ga1.setMotherArea(ga2);
        Boolean expectedResult = false;
        Boolean actualResult = ga1.checkIfAreaIsContained(ga2, ga1);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void ensureThatGrandsonAreaIsContainedInGrandmotherArea() {
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
    void seeIfGetsAverageOfSensorTypeOnAGeographicArea() {
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
    void seeIfChecksValidListOnAverageOfSensorTypeOnAGeographicArea() {
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
    void seeWhatReturnsIfListWithoutInputTypeSensor() {
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
    void seeIfPrintGeoHouseListWorks() {
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
    void seeIfPrintsGeoHouseListEmpty() {
        HouseList list = new HouseList();
        String result = list.printHouseList();
        String expectedResult = "The list is empty.";
        assertEquals(result, expectedResult);
    }

    @Test
    void seeIfSensorListIsContainedInGAList() {
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
    void seeIfSensorListIsNOTContainedInGAList() {
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

