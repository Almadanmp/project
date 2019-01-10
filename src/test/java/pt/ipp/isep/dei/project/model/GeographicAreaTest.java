package pt.ipp.isep.dei.project.model;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class GeographicAreaTest {


    @Test
    void seeIfGetSetTypeWorksIfSameAsGivenConstructor() {
        //Arrange
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7, 100);
        TypeArea expectedResult = new TypeArea("Rua");
        TypeArea actualResult;
        GeographicArea c = new GeographicArea("Porto",t1,2,3,l1);
        //Act
        c.setTypeArea(t1);
        actualResult = c.getTypeArea();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfConstructorSetsTypeArea() {
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7, 100);
        TypeArea expectedResult = new TypeArea("Rua");
        TypeArea actualResult;
        GeographicArea c = new GeographicArea("Porto",t1,2,3,l1);
        actualResult = c.getTypeArea();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetSetTypeWorksIfDifferentOfGivenConstructor() {
        //Arrange
        TypeArea t1 = new TypeArea("Rua");
        TypeArea t2 = new TypeArea("Freguesia");
        Local l1 = new Local(38, 7, 100);
        TypeArea actualResult;
        GeographicArea c = new GeographicArea("Porto",t1,2,3,l1);

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
        Local l1 = new Local(38, 7, 100);
        GeographicArea c = new GeographicArea("Porto",t1,2,3,l1);
        //Act
        c.setLocal(l1);
        boolean actualResult = c.getLocal().equals(new Local(38, 7, 100));

        //Assert
        assertTrue(actualResult);
    }

    @Test
    void seeIfGetSetLocalIfDifferentOfConstructorWorks() {
        //Arrange
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7, 100);
        Local l2 = new Local(65, 56, 100);
        GeographicArea c = new GeographicArea("Porto",t1,2,3,l1);
        Local actualResult;
        //Act
        c.setLocal(l2);
        actualResult = c.getLocal();

        //Assert
        assertEquals(l2, actualResult);
    }

    @Test
    void seeIfGetSetSensorList() {
        //Arrange
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(21, 38, 40);
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosférico", "km/h"), new Local(12, 31, 21), new GregorianCalendar(2010, 8, 9).getTime());
        SensorList list1 = new SensorList(s1);
        SensorList actualResult;
        GeographicArea c = new GeographicArea("Porto",t1,2,3,l1);
        //Act
        c.setSensorList(list1);
        actualResult = c.getSensorList();
        //Assert
        assertEquals(list1, actualResult);
    }


    @Test
    void seeIfCalculateDistanceToDifferentGAWorks() {
        //Arrange
        Local l1 = new Local(23, 46, 100);
        Local l2 = new Local(25, 47, 100);
        TypeArea t1 = new TypeArea("Porto");
        TypeArea t2 = new TypeArea("Braga");
        GeographicArea ga1 = new GeographicArea("Porto",t1,2,3,l1);
        GeographicArea ga2 = new GeographicArea("Porto",t2,2,3,l2);
        double result = ga1.calculateDistanceToGA(ga2);
        //Act
        double expectedResult = 244;
        //Assert
        assertEquals(expectedResult, result, 1);
    }

    @Test
    void seeIfCalculateDistanceToDifferentGAWithSameLocalizationWorks() {
        //Arrange
        Local l1 = new Local(23, 46, 100);
        Local l2 = new Local(23, 46, 100);
        TypeArea t1 = new TypeArea("Porto");
        TypeArea t2 = new TypeArea("Braga");
        GeographicArea ga1 = new GeographicArea("Porto",t1,2,3,l1);
        GeographicArea ga2 = new GeographicArea("Porto",t2,2,3,l2);
        double result = ga1.calculateDistanceToGA(ga2);
        //Act
        double expectedResult = 0;
        //Assert
        assertEquals(expectedResult, result, 1);
    }

    @Test
    void seeIfCalculateDistanceToSameGAWorks() {
        //Arrange
        Local l1 = new Local(23, 46, 100);
        Local l2 = new Local(23, 46, 100);
        TypeArea t1 = new TypeArea("Porto");
        TypeArea t2 = new TypeArea("Porto");
        GeographicArea ga1 = new GeographicArea("Porto",t1,2,3,l1);
        GeographicArea ga2 = new GeographicArea("Porto",t2,2,3,l2);
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
        Sensor s1 = new Sensor("Sensor 1", new TypeSensor("Temperatura", "Celsius"), new Local(16, 17, 18), new GregorianCalendar(2010, 8, 9).getTime());
        s1.setReadingList(readingList);
        GeographicArea ga1 = new GeographicArea("Porto",new TypeArea("Rua"),2,3,new Local(16, 17, 18));
        ga1.setSensorList(new SensorList(s1));        double expectedResult = 19;
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
        Sensor s1 = new Sensor("Sensor 1", new TypeSensor("Temperatura", "Celsius"), new Local(16, 17, 18), new GregorianCalendar(2010, 8, 9).getTime());
        s1.setReadingList(readingList);
        Sensor s2 = new Sensor("Sensor 2", new TypeSensor("Temperatura", "Celsius"), new Local(16, 17, 18), new GregorianCalendar(2010, 8, 9).getTime());
        s2.setReadingList(readingList2);
        SensorList sensorList = new SensorList(s1);
        sensorList.addSensor(s2);
        GeographicArea ga1 = new GeographicArea("Porto",new TypeArea("Rua"),2,3,new Local(16, 17, 18));
        ga1.setSensorList(sensorList);
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
        Sensor s1 = new Sensor("Sensor 1", new TypeSensor("Temperatura", "Celsius"), new Local(16, 17, 18), new GregorianCalendar(2010, 8, 9).getTime());
        s1.setReadingList(readingList);
        Sensor s2 = new Sensor("Sensor 2", new TypeSensor("Pluviosidade", "l/m2"), new Local(16, 17, 18), new GregorianCalendar(2010, 8, 9).getTime());
        s2.setReadingList(readingList2);
        SensorList sensorList = new SensorList(s1);
        sensorList.addSensor(s2);
        GeographicArea ga1 = new GeographicArea("Porto",new TypeArea("Rua"),2,3,new Local(16, 17, 18));
        ga1.setSensorList(sensorList);
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
        Sensor s1 = new Sensor("Sensor 1", new TypeSensor("Temperatura", "Celsius"), new Local(16, 17, 18), new GregorianCalendar(2010, 8, 9).getTime());
        s1.setReadingList(readingList);
        Sensor s2 = new Sensor("Sensor 2", new TypeSensor("Pluviosidade", "l/m2"), new Local(16, 17, 18), new GregorianCalendar(2010, 8, 9).getTime());
        s2.setReadingList(readingList2);
        SensorList sensorList = new SensorList(s1);
        sensorList.addSensor(s2);
        GeographicArea ga1 = new GeographicArea("Porto",new TypeArea("Rua"),2,3,new Local(16, 17, 18));
        ga1.setSensorList(sensorList);
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
        Local l1 = new Local(20, 15, 100);
        Local l2 = new Local(20, 20, 100);
        GeographicArea ga1 = new GeographicArea("Porto",t1,2,3,l1);
        GeographicArea ga2 = new GeographicArea("Porto",t2,2,3,l2);
        //Act
        ga1.setWidth(10);
        ga1.setLength(20);
        ga2.setWidth(5);
        ga2.setLength(10);
        boolean result = ga1.isAreaContainedInAnotherArea(ga1, ga2);

        //Assert
        assertTrue(result);
    }

    @Test
    void seeIfAreaIsContainedInArea2() {
        //Arrange
        TypeArea t1 = new TypeArea("Terriola");
        TypeArea t2 = new TypeArea("Cidade");
        Local l1 = new Local(20, 15, 100);
        Local l2 = new Local(20, 20, 100);
        GeographicArea ga1 = new GeographicArea("Porto",t1,2,3,l1);
        GeographicArea ga2 = new GeographicArea("Porto",t2,2,3,l2);
        //Act
        ga1.setWidth(20);
        ga1.setLength(10);
        ga2.setWidth(10);
        ga2.setLength(5);
        boolean result = ga1.isAreaContainedInAnotherArea(ga1, ga2);

        //Assert
        assertFalse(result);
    }

    @Test
    void seeIfAreaIsContainedInArea3() {
        //Arrange
        TypeArea t1 = new TypeArea("Terriola");
        TypeArea t2 = new TypeArea("Cidade");
        Local l1 = new Local(20, 15, 100);
        Local l2 = new Local(20, 20, 100);
        GeographicArea ga1 = new GeographicArea("Porto",t1,2,3,l1);
        GeographicArea ga2 = new GeographicArea("Porto",t2,2,3,l2);
        //Act
        ga1.setWidth(20);
        ga1.setLength(20);
        ga2.setWidth(10);
        ga2.setLength(10);
        boolean result = ga1.isAreaContainedInAnotherArea(ga1, ga2);

        //Assert
        assertTrue(result);
    }

    @Test
    void seeIfSetNameWorksNullAndThrowsStringMessage() {
        //Arrange
        String name = "Porto";
        TypeArea t1 = new TypeArea("rua");
        Local l1 = new Local(11, 12, 100);
        GeographicArea ga1 = new GeographicArea(name,t1,2,3,l1);

        //Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            ga1.setId(null);
        });

        //Assert
        assertEquals("Please Insert Valid Name", exception.getMessage());
    }

    @Test
    void seeIfSetNameWorksEmptyAndThrowsStringException() {
        //Arrange
        String name = "Porto";
        TypeArea t1 = new TypeArea("rua");
        Local l1 = new Local(11, 12, 100);
        GeographicArea ga1 = new GeographicArea(name,t1,2,3,l1);
        //Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            ga1.setId("");
        });
        //Assert
        assertEquals("Please Insert Valid Name", exception.getMessage());
    }


    @Test
    void seeIfWeSetMotherArea() {
        //Arrange
        GeographicArea ga1 = new GeographicArea("Porto", new TypeArea("Cidade"), 2,5, new Local(22, 23, 100));
        GeographicArea ga2 = new GeographicArea("Portugal", new TypeArea("País"), 6,7, new Local(22, 17, 100));
        ga1.setMotherArea(ga2);

        //Act
        GeographicArea actualResult = ga1.getMotherArea();

        //Assert
        assertEquals(ga2, actualResult);
    }

    @Test
    void seeIfSetDescription(){
        GeographicArea ga1 = new GeographicArea("Porto", new TypeArea("Cidade"), 2,5, new Local(22, 23, 100));
        ga1.setDescription("cidade do Porto");

       String actualResult = ga1.getDescription();

        assertEquals("cidade do Porto",actualResult);
    }

    @Test
    void ensureThatAObjectIsNotAInstanceOf() {
        GeographicArea ga1 = new GeographicArea("Porto", new TypeArea("Cidade"), 2,5, new Local(22, 23, 100));
        Sensor s1 = new Sensor("Sensor 1", new TypeSensor("Temperatura", "Celsius"), new Local(22, 22, 100), new GregorianCalendar(2018, 11, 25).getTime());

        boolean actualResult = ga1.equals(s1);

        assertFalse(actualResult);
    }


    @Test
    void ensureThatAreaIsContained() {
        GeographicArea ga1 = new GeographicArea("Porto", new TypeArea("Cidade"), 10,20, new Local(22, 23, 100));
        GeographicArea ga2 = new GeographicArea("Portugal", new TypeArea("Cidade"), 200,521, new Local(22, 23, 100));
        ga1.setMotherArea(ga2);
        ga1.setMotherArea(ga2);
        boolean actualResult = ga1.checkIfAreaIsContained(ga1, ga2);

        assertTrue(actualResult);
    }

    @Test
    void ensureThatAreaIsNotContained() {
        GeographicArea ga1 = new GeographicArea("Porto", new TypeArea("Cidade"), 10,20, new Local(22, 23, 100));
        GeographicArea ga2 = new GeographicArea("Portugal", new TypeArea("Cidade"), 200,521, new Local(22, 23, 100));
        ga1.setMotherArea(ga2);
        ga1.setMotherArea(ga2);
        boolean actualResult = ga1.checkIfAreaIsContained(ga2, ga1);

        assertFalse(actualResult);
    }

    @Test
    void ensureThatGrandsonAreaIsContainedInGrandmotherArea() {
        GeographicArea ga1 = new GeographicArea("Porto", new TypeArea("Cidade"), 2,4,new Local(22, 22, 100));
        GeographicArea ga2 = new GeographicArea("Portugal", new TypeArea("Cidade"), 20,40,new Local(22, 22, 100));
        GeographicArea ga3 = new GeographicArea("Europa", new TypeArea("Cidade"), 200,400,new Local(22, 22, 100));
        ga1.setMotherArea(ga2);
        ga2.setMotherArea(ga3);
        boolean actualResult = ga1.checkIfAreaIsContained(ga1, ga3);

        assertTrue(actualResult);
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
        Sensor s1 = new Sensor("Sensor 1", new TypeSensor("Rainfall", "l/m2"), new Local(16, 17, 18), new GregorianCalendar(2010, 8, 9).getTime());
        s1.setReadingList(readingList);
        Sensor s2 = new Sensor("Sensor 2", new TypeSensor("Temperature", "Celsius"), new Local(16, 17, 18), new GregorianCalendar(2010, 8, 9).getTime());
        s2.setReadingList(readingList);
        Sensor s3 = new Sensor("Sensor 3", new TypeSensor("Rainfall", "l/m2"), new Local(16, 17, 18), new GregorianCalendar(2010, 8, 9).getTime());
        s3.setReadingList(readingList2);
        SensorList sensorList = new SensorList();
        sensorList.addSensor(s2);
        sensorList.addSensor(s1);
        sensorList.addSensor(s3);

        GeographicArea ga1 = new GeographicArea("Porto", new TypeArea("Rua"), 2,5, new Local(16, 17, 18));
        ga1.setSensorList(sensorList);
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
        GeographicArea ga1 = new GeographicArea("Porto", new TypeArea("Rua"), 16,17, new Local(16, 17, 18));
        ga1.setSensorList(sensorList);
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
        Sensor s1 = new Sensor("Sensor 1", new TypeSensor("Rainfall", "l/m2"), new Local(16, 17, 18), new GregorianCalendar(2010, 8, 9).getTime());
        s1.setReadingList(readingList);
        Sensor s2 = new Sensor("Sensor 2", new TypeSensor("Temperature", "Celsius"), new Local(16, 17, 18), new GregorianCalendar(2010, 8, 9).getTime());
        s2.setReadingList(readingList2);
        Sensor s3 = new Sensor("Sensor 3", new TypeSensor("Rainfall", "l/m2"), new Local(16, 17, 18), new GregorianCalendar(2010, 8, 9).getTime());
        s3.setReadingList(readingList2);
        SensorList sensorList = new SensorList();
        sensorList.addSensor(s2);
        sensorList.addSensor(s1);
        sensorList.addSensor(s3);

        GeographicArea ga1 = new GeographicArea("Porto", new TypeArea("Rua"), 16,17, new Local(16, 17, 18));
        ga1.setSensorList(sensorList);
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
    void seeIfSensorListIsContainedInGAList() {
        //Arrange

        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 10, 4).getTime());
        Sensor s2 = new Sensor("Pluviosidade", new TypeSensor("Pluviosidade", "l/m2"),
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

        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 10, 4).getTime());
        Sensor s2 = new Sensor("Pluviosidade", new TypeSensor("Pluviosidade", "l/m2"),
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

    @Test
    void hashCodeDummyTest(){
        GeographicArea ga1 = new GeographicArea("Porto", new TypeArea("Rua"), 16,17, new Local(16, 17, 18));
        int expectedResult = 1;
        int actualResult = ga1.hashCode();
        Assertions.assertEquals(expectedResult, actualResult);
    }
}

