package Sprint0_Test.ModelTest;


import Sprint0.Model.*;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.GregorianCalendar;

import static javafx.scene.input.KeyCode.M;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    public void seeIfCalculateDistanceToDifferentGAWorks() {
        //Arrange
        Local l1 = new Local(23, 46);
        Local l2 = new Local(25, 47);
        TypeArea t1 = new TypeArea("Porto");
        TypeArea t2 = new TypeArea("Braga");
        GeographicArea ga1 = new GeographicArea(t1, l1);
        GeographicArea ga2 = new GeographicArea(t2, l2);
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
        GeographicArea ga1 = new GeographicArea(t1, l1);
        GeographicArea ga2 = new GeographicArea(t2, l2);
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
        GeographicArea ga1 = new GeographicArea(t1, l1);
        GeographicArea ga2 = new GeographicArea(t2, l2);
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
    public void seeIfGetSetGeographicAreaListSameAsConstructorWorks() {
        //Arrange

        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(21, 38, 40);
        GeographicArea gArea1 = new GeographicArea(t1,l1);
        GeographicAreaList GAl = new GeographicAreaList(gArea1);

        GeographicAreaList expectedResult =  GAl;
        GeographicAreaList actualResult;

        //Act
        gArea1.setGeoAreaList(GAl);
        actualResult = gArea1.getmGeographicAreaList();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfAreaIsContainedInArea() {
        //Arrange
        TypeArea t1 = new TypeArea("Terriola");
        TypeArea t2 = new TypeArea("Cidade");
        Local l1 = new Local(20, 15);
        GeographicArea a1 = new GeographicArea(t1,l1);
        Local l2 = new Local(20, 20);
        GeographicArea a2 = new GeographicArea(t2,l2);
        Local v1 = new Local (15,20);
        Local v2 = new Local (20,15);
        Local v3 = new Local(10,25);
        Local v4 = new Local(25,10);
        //Act
        a1.setBottomRightVertex(v2);
        a1.setTopLeftVertex(v1);
        a2.setBottomRightVertex(v4);
        a2.setTopLeftVertex(v3);
        boolean result = a1.isAreaContainedInAnotherArea(a1,a2);

        //Assert
        assertEquals(true,result);
    }

    @Test
    public void seeIfSetNameWorksNullAndThrowsStringMessage() {
        //Arrange
        String name1 = "Porto";
        TypeArea tA1 = new TypeArea("rua");
        Local l1 = new Local (11,12);
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
        Local l1 = new Local (11,12);
        GeographicArea gA1 = new GeographicArea(name1, tA1, l1);

        //Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            gA1.setName("");
        });

        //Assert
        assertEquals("Please Insert Valid Name", exception.getMessage());
    }
}
