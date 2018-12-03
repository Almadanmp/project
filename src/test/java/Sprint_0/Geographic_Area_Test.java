package Sprint_0;


import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Geographic_Area_Test {


    @Test
    public void seeIfGetSetTypeWorksIfSameAsGivenConstructor() {
        //Arrange
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7);
        TypeArea expectedResult = t1;
        TypeArea actualResult;
        Geographic_Area c = new Geographic_Area(t1, l1);
        //Act
        c.setTypeArea(t1);
        actualResult = c.getTypeArea();

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
        Geographic_Area c = new Geographic_Area(t1, l1);

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
        Geographic_Area c = new Geographic_Area(t1, l1);
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
        Geographic_Area c = new Geographic_Area(t1, l1);
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
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosférico"), new Local(12, 31, 21), new GregorianCalendar(2010,8,9).getTime());
        SensorList list1 = new SensorList(s1);
        SensorList expectedResult = list1;
        SensorList actualResult;
        Geographic_Area c = new Geographic_Area(t1, l1, list1);

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
        Sensor s1 = new Sensor("Pluviosidade", new TypeSensor("Pluviosidade"), new Local(12, 31, 21), new GregorianCalendar(2010,8,9).getTime());
        Sensor s2 = new Sensor("Temperatura", new TypeSensor("Temperatura"), new Local(43, 23, 65), new GregorianCalendar(2010,8,9).getTime());
        SensorList list1 = new SensorList(s1);
        SensorList list2 = new SensorList(s2);
        SensorList expectedResult = list2;
        SensorList actualResult;
        Geographic_Area c = new Geographic_Area(t1, l1, list1);

        //Act
        c.setSensorList(list2);
        actualResult = c.getSensorList();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfSetSensorListAcceptsListWithoutDuplicates() {
        //Arrange
        Sensor s1 = new Sensor("Vento1", new TypeSensor("Vento"), new Local(21, 65, 21), new GregorianCalendar(2016,8,6).getTime());
        Sensor s2 = new Sensor("Vento2", new TypeSensor("Vento"), new Local(32, 87, 21), new GregorianCalendar(2016,2,19).getTime());
        Sensor s3 = new Sensor("Pluviosidade1", new TypeSensor("Pluviosidade"), new Local(56, 31, 11), new GregorianCalendar(2011,8,2).getTime());
        SensorList l1 = new SensorList(new Sensor[]{s1, s2, s3});
        Geographic_Area c = new Geographic_Area(new TypeArea("Rua"), new Local(12, 35, 2), new SensorList(s1));
        boolean expectedResult = true;
        boolean actualResult;

        //Act
        actualResult = c.setSensorList(l1);

        //Assert
        assertEquals(expectedResult, actualResult);
    }
    @Test
    public void seeIfSetSensorListRefusesListWithDuplicates() {
        //Arrange
        Sensor s1 = new Sensor("Vento1", new TypeSensor("Vento"), new Local(12, 31, 21), new GregorianCalendar(2010,8,9).getTime());
        Sensor s2 = new Sensor("Vento1", new TypeSensor("Vento"), new Local(12, 31, 21), new GregorianCalendar(2010,8,9).getTime());
        Sensor s3 = new Sensor("Pluviosidade1", new TypeSensor("Pluviosidade"), new Local(12, 31, 21), new GregorianCalendar(2010,8,9).getTime());
        SensorList l1 = new SensorList(new Sensor[]{s1, s2, s3});
        Geographic_Area c = new Geographic_Area(new TypeArea("Rua"), new Local(12, 35, 2), new SensorList(s1));
        boolean expectedResult = false;
        boolean actualResult;

        //Act
        actualResult = c.setSensorList(l1);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfCalculateDistanceToDifferentGAWorks() {
        //Arrange
        Local l1 = new Local(23, 46);
        Local l2 = new Local(25, 47);
        TypeArea t1 = new TypeArea("Porto");
        TypeArea t2 = new TypeArea("Braga");
        Geographic_Area ga1 = new Geographic_Area(t1, l1);
        Geographic_Area ga2 = new Geographic_Area(t2, l2);
        double result = ga1.calculateDistanceToGA(ga2);
        //Act
        double expectedresult = 244;
        //Assert
        assertEquals(expectedresult, result, 1);
    }
    @Test
    public void seeIfCalculateDistanceToDifferentGAWithSameLocalizationWorks() {
        //Arrange
        Local l1 = new Local(23, 46);
        Local l2 = new Local(23, 46);
        TypeArea t1 = new TypeArea("Porto");
        TypeArea t2 = new TypeArea("Braga");
        Geographic_Area ga1 = new Geographic_Area(t1, l1);
        Geographic_Area ga2 = new Geographic_Area(t2, l2);
        double result = ga1.calculateDistanceToGA(ga2);
        //Act
        double expectedresult = 0;
        //Assert
        assertEquals(expectedresult, result, 1);
    }
    @Test
    public void seeIfCalculateDistanceToSameGAWorks() {
        //Arrange
        Local l1 = new Local(23, 46);
        Local l2 = new Local(23, 46);
        TypeArea t1 = new TypeArea("Porto");
        TypeArea t2 = new TypeArea("Porto");
        Geographic_Area ga1 = new Geographic_Area(t1, l1);
        Geographic_Area ga2 = new Geographic_Area(t2, l2);
        double result = ga1.calculateDistanceToGA(ga2);
        //Act
        double expectedresult = 0;
        //Assert
        assertEquals(expectedresult, result, 1);
    }

    @Test
    public void seeThatWeGetMostRecentReadingValueOfACertainTypeOfSensor() {
        //Arrange
        ReadingList readingList = new ReadingList();
        Reading r1 = new Reading(15, new GregorianCalendar(2018, 12, 3).getTime());
        Reading r2 = new Reading(19, new GregorianCalendar(2018, 12, 4).getTime());
        Reading r3 = new Reading(17, new GregorianCalendar(2018, 12, 1).getTime());
        readingList.addReading(r1);
        readingList.addReading(r2);
        readingList.addReading(r3);
        Sensor s1 = new Sensor("Sensor 1", new TypeSensor("Temperatura"), new Local(16, 17, 18), new GregorianCalendar(2010,8,9).getTime(), readingList);
        SensorList sensorList = new SensorList(s1);
        Geographic_Area ga1 = new Geographic_Area(new TypeArea("Rua"), new Local(16, 17, 18), sensorList);
        double expectedResult = 19;
        //Act
        double result = ga1.getMostRecentReadingValue("Temperatura");
        //Assert
        assertEquals(expectedResult, result, 0.01);

    }

    @Test
    public void seeThatWeGetMostRecentReadingValueOfACertainTypeOfSensorWithMultipleSensorsOfSameType() {
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
        Sensor s1 = new Sensor("Sensor 1", new TypeSensor("Temperatura"), new Local(16, 17, 18), new GregorianCalendar(2010,8,9).getTime(), readingList);
        Sensor s2 = new Sensor("Sensor 2", new TypeSensor("Temperatura"), new Local(16, 17, 18), new GregorianCalendar(2010,8,9).getTime(), readingList2);
        SensorList sensorList = new SensorList(s1);
        sensorList.addSensor(s2);
        Geographic_Area ga1 = new Geographic_Area(new TypeArea("Rua"), new Local(16, 17, 18), sensorList);
        double expectedResult = 20;
        //Act
        double result = ga1.getMostRecentReadingValue("Temperatura");
        //Assert
        assertEquals(expectedResult, result, 0.01);
    }

    @Test
    public void seeThatWeGetMostRecentReadingValueOfACertainTypeOfSensorWithMultipleSensorsOfDifferentType() {
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
        Sensor s1 = new Sensor("Sensor 1", new TypeSensor("Temperatura"), new Local(16, 17, 18), new GregorianCalendar(2010,8,9).getTime(), readingList);
        Sensor s2 = new Sensor("Sensor 2", new TypeSensor("Pluviosidade"), new Local(16, 17, 18), new GregorianCalendar(2010,8,9).getTime(), readingList2);
        SensorList sensorList = new SensorList(s1);
        sensorList.addSensor(s2);
        Geographic_Area ga1 = new Geographic_Area(new TypeArea("Rua"), new Local(16, 17, 18), sensorList);
        double expectedResult = 19;
        //Act
        double result = ga1.getMostRecentReadingValue("Temperatura");
        //Assert
        assertEquals(expectedResult, result, 0.01);

    }
    @Test
    public void seeThatWeRemoveAListWithAnotherType() {
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
        Sensor s1 = new Sensor("Sensor 1", new TypeSensor("Temperatura"), new Local(16, 17, 18), new GregorianCalendar(2010,8,9).getTime(), readingList);
        Sensor s2 = new Sensor("Sensor 2", new TypeSensor("Pluviosidade"), new Local(16, 17, 18), new GregorianCalendar(2010,8,9).getTime(), readingList2);
        SensorList sensorList = new SensorList(s1);
        sensorList.addSensor(s2);
        Geographic_Area ga1 = new Geographic_Area(new TypeArea("Rua"), new Local(16, 17, 18), sensorList);
        double expectedResult = 19;
        //Act
        double result = ga1.getMostRecentReadingValue("Temperatura");
        //Assert
        assertEquals(expectedResult, result, 0.01);
    }
}
