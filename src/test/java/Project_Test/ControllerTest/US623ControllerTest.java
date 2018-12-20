package Project_Test.ControllerTest;

import PT.IPP.ISEP.DEI.Project.Controller.US623Controller;
import PT.IPP.ISEP.DEI.Project.Model.*;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * User Story 623 - Controller Tests
 */
public class US623ControllerTest {

    @Test
    public void seeIfGetsAverageRainfallOfGA() {

        GeographicArea gA1 = new GeographicArea("Portugal", new TypeArea("Pais"), new Local(21, 33));
        House house = new House("1742", "Costa Cabral", new Local(21, 33), "4450-145", gA1);
        house.setmMotherGA(gA1);
        HouseList hL1 = new HouseList();
        hL1.addHouseToHouseList(house);
        gA1.setmHouseList(hL1);
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
        Sensor s1 = new Sensor("Sensor 1", new TypeSensor("rainfall"), new Local(16, 17, 18), new GregorianCalendar(2010, 8, 9).getTime(), readingList);
        Sensor s2 = new Sensor("Sensor 2", new TypeSensor("Temperature"), new Local(16, 17, 18), new GregorianCalendar(2010, 8, 9).getTime(), readingList2);
        Sensor s3 = new Sensor("Sensor 3", new TypeSensor("rainfall"), new Local(16, 17, 18), new GregorianCalendar(2010, 8, 9).getTime(), readingList2);
        SensorList sensorList = new SensorList();
        sensorList.addSensor(s2);
        sensorList.addSensor(s1);
        sensorList.addSensor(s3);
        gA1.setSensorList(sensorList);

        US623Controller US623 = new US623Controller();
        GregorianCalendar dateMin = new GregorianCalendar(2018, 11, 1);
        GregorianCalendar dateMax = new GregorianCalendar(2018, 11, 20);
        Date dateToTest1 = dateMin.getTime();
        Date dateToTest2 = dateMax.getTime();
        double expectedResult = 23.5;
        double actualResult = US623.getAVGDailyRainfallOnGivenPeriod(house, dateToTest1, dateToTest2);
        //Assert
        assertEquals(expectedResult, actualResult);
    }
}