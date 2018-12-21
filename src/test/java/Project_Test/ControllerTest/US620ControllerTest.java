package Project_Test.ControllerTest;

        import PT.IPP.ISEP.DEI.Project.Controller.US620Controller;
        import PT.IPP.ISEP.DEI.Project.Model.*;
        import org.junit.jupiter.api.Test;

        import java.util.Date;
        import java.util.GregorianCalendar;

        import static org.junit.jupiter.api.Assertions.assertEquals;

public class US620ControllerTest {

    @Test
    public void seeIfGetSumOfReadingInGivenDay() {
        //Arrange
        ReadingList rList1 = new ReadingList();
        GregorianCalendar g0 = new GregorianCalendar(2018, 10, 23, 23, 26, 21);
        GregorianCalendar g1 = new GregorianCalendar(2018, 10, 27, 8, 21, 22);
        Reading r0 = new Reading(5, g0.getTime());
        Reading r1 = new Reading(25, g1.getTime());
        rList1.addReading(r0);
        rList1.addReading(r1);

        ReadingList rList2 = new ReadingList();
        GregorianCalendar g2 = new GregorianCalendar(2018, 10, 23, 23, 26, 21);
        GregorianCalendar g3 = new GregorianCalendar(2018, 10, 27, 8, 21, 22);
        Reading r2 = new Reading(1, g2.getTime());
        Reading r3 = new Reading(20, g3.getTime());
        rList2.addReading(r2);
        rList2.addReading(r3);

        ReadingList rList3 = new ReadingList();
        GregorianCalendar g4 = new GregorianCalendar(2018, 10, 23, 23, 26, 21);
        GregorianCalendar g5 = new GregorianCalendar(2018, 10, 27, 8, 21, 22);
        Reading r4 = new Reading(1, g4.getTime());
        Reading r5 = new Reading(20, g5.getTime());
        rList3.addReading(r4);
        rList3.addReading(r5);

        ReadingList rList4 = new ReadingList();
        GregorianCalendar g6 = new GregorianCalendar(2018, 10, 23, 23, 26, 21);
        GregorianCalendar g7 = new GregorianCalendar(2018, 10, 27, 8, 21, 22);
        Reading r6 = new Reading(1, g6.getTime());
        Reading r7 = new Reading(30, g7.getTime());
        rList4.addReading(r6);
        rList4.addReading(r7);

        ReadingList rList5 = new ReadingList();
        GregorianCalendar g8 = new GregorianCalendar(2018, 10, 23, 23, 26, 21);
        GregorianCalendar g9 = new GregorianCalendar(2018, 10, 27, 8, 21, 22);
        Reading r8 = new Reading(1, g8.getTime());
        Reading r9 = new Reading(15, g9.getTime());
        rList5.addReading(r8);
        rList5.addReading(r9);

        ReadingList rList6 = new ReadingList();
        GregorianCalendar g10 = new GregorianCalendar(2018, 10, 23, 23, 26, 21);
        GregorianCalendar g11 = new GregorianCalendar(2018, 10, 27, 8, 21, 22);
        Reading r10 = new Reading(5, g10.getTime());
        Reading r11 = new Reading(25, g11.getTime());
        rList6.addReading(r10);
        rList6.addReading(r11);

        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7);
        GeographicArea ga1 = new GeographicArea(t1, l1);

        Sensor s1 = new Sensor("XV1", new TypeSensor("Atmosphere"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 10, 4).getTime());
        Sensor s2 = new Sensor("XV2", new TypeSensor("Rain"),
                new Local(10, 30, 20),
                new GregorianCalendar(118, 12, 4).getTime());
        Sensor s3 = new Sensor("XV3", new TypeSensor("Rain"),
                new Local(10, 30, 20),
                new GregorianCalendar(118, 12, 4).getTime());
        Sensor s4 = new Sensor("XV4", new TypeSensor("Rain"),
                new Local(10, 30, 20),
                new GregorianCalendar(118, 12, 4).getTime());
        Sensor s5 = new Sensor("XV5", new TypeSensor("Rain"),
                new Local(10, 30, 20),
                new GregorianCalendar(118, 12, 4).getTime());
        Sensor s6 = new Sensor("XV6", new TypeSensor("Motion"),
                new Local(10, 30, 20),
                new GregorianCalendar(118, 12, 4).getTime());

        SensorList slist1 = new SensorList();
        s1.setReadingList(rList1);
        s2.setReadingList(rList2);
        s3.setReadingList(rList3);
        s4.setReadingList(rList4);
        s5.setReadingList(rList5);
        s6.setReadingList(rList6);
        slist1.addSensor(s1);
        slist1.addSensor(s2);
        slist1.addSensor(s3);
        slist1.addSensor(s4);
        slist1.addSensor(s5);
        slist1.addSensor(s6);
        ga1.setSensorList(slist1);
        House casa1 = new House();

        //Act
        double expectedResult = 1;
        US620Controller ctrl = new US620Controller();
        GregorianCalendar cal = new GregorianCalendar(2018, 10, 23);
        Date dateToTest = cal.getTime();
        double actualResult = ctrl.getTotalRainfallOnGivenDayHouseArea(ga1, dateToTest);
        //Assert
        assertEquals(expectedResult, actualResult, 0.001);
    }


    @Test
    public void testeCenas() {

        //Arrange
        GeographicAreaList mGeographicAreaList = new GeographicAreaList();
        SensorList mSensorList = new SensorList();
        TypeAreaList mTypeAreaList = new TypeAreaList();
        HouseList mHouseList = new HouseList();
        House house = new House("casa", "coise", new Local(4, 4), "coise");
        mHouseList.addHouseToHouseList(house);
        RoomList mRoomList = new RoomList();
        EnergyGridList mEnergyGridList = new EnergyGridList();
        ReadingList readingList = new ReadingList();
        Reading reading = new Reading(30, new GregorianCalendar(2018, 8, 6).getTime());
        Reading reading1 = new Reading(40, new GregorianCalendar(2018, 8, 5).getTime());
        Reading reading3 = new Reading(40, new GregorianCalendar(2018, 8, 5).getTime());
        readingList.addReading(reading);
        readingList.addReading(reading1);
        readingList.addReading(reading3);
        Sensor sensor1 = new Sensor("sensor", new TypeSensor("temperature"), new Local(4, 4), new GregorianCalendar(8, 8, 8).getTime(), readingList);
        Sensor sensor2 = new Sensor("sensor2", new TypeSensor("Rain"), new Local(4, 4), new GregorianCalendar(8, 8, 8).getTime(), readingList);
        SensorList sensorList = new SensorList();
        sensorList.addSensor(sensor1);
        sensorList.addSensor(sensor2);
        GeographicArea geoa = new GeographicArea("porto", new TypeArea("cidade"), new Local(4, 4), sensorList, mHouseList);
        GeographicArea geoa2 = new GeographicArea("lisboa", new TypeArea("aldeia"), new Local(4, 4), sensorList, mHouseList);
        mGeographicAreaList.addGeographicAreaToGeographicAreaList(geoa);
        mGeographicAreaList.addGeographicAreaToGeographicAreaList(geoa2);
        Room room = new Room("cozinha", 8, 2, sensorList);
        mRoomList.addRoom(room);

        //Act
        double expectedResult = 30;
        US620Controller ctrl = new US620Controller();
        GregorianCalendar cal = new GregorianCalendar(2018, 8, 6);
        Date dateToTest = cal.getTime();
        double actualResult = ctrl.getTotalRainfallOnGivenDayHouseArea(geoa, dateToTest);

        //Assert
        assertEquals(expectedResult, actualResult, 0.001);

    }
}