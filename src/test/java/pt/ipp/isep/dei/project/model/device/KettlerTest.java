package pt.ipp.isep.dei.project.model.device;

import org.testng.annotations.Test;
import pt.ipp.isep.dei.project.model.device.devicespecs.KettlerSpec;
import pt.ipp.isep.dei.project.model.device.log.Log;
import pt.ipp.isep.dei.project.model.device.log.LogList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.testng.Assert.*;

public class KettlerTest {

    @Test
    public void testSetGetName() {

        //Arrange

        Kettler kettler = new Kettler(new KettlerSpec());

        //Act

        kettler.setName("kettler1");
        String actualResult = kettler.getName();

        //Assert

        assertEquals("kettler1", actualResult);
    }

    @Test
    public void testGetType() {

        //Arrange

        Kettler kettler = new Kettler(new KettlerSpec());

        //Act

        String actualResult = kettler.getType();

        //Assert

        assertEquals("Kettler", actualResult);
    }

    @Test
    public void testSetNominalPower() {

        //Arrange

        Kettler kettler = new Kettler(new KettlerSpec());

        //Act

        kettler.setNominalPower(900D);
        double actualResult = kettler.getNominalPower();

        //Assert

        assertEquals(900D, actualResult);
    }

    @Test
    public void testThatDeactivateFails() {

        //Arrange

        Kettler kettler = new Kettler(new KettlerSpec());

        //Act
        kettler.deactivate();
        boolean actualResult1 = kettler.deactivate();

        //Assert

        assertFalse(actualResult1);
    }

    @Test
    public void testIsActive() {

        //Arrange

        Kettler kettler = new Kettler(new KettlerSpec());

        //Assert

        assertTrue(kettler.isActive());

        //Act

        kettler.deactivate();

        //

        assertFalse(kettler.isActive());
    }

    @Test
    public void testBuildString() {

        //Arrange

        Kettler kettler = new Kettler(new KettlerSpec());
        kettler.setName("kettler1");
        kettler.setNominalPower(900D);

        //Act

        String actualResult = kettler.buildString();

        //Assert

        assertEquals("The device Name is kettler1, and its Nominal Power is 900.0 kW.\n", actualResult);
    }

    @Test
    public void testIsLogListEmpty() {

        //Arrange

        Kettler kettler = new Kettler(new KettlerSpec());

        //Assert

        assertTrue(kettler.isLogListEmpty());
    }

    @Test
    public void testGetLogList() {

        //Arrange

        Kettler kettler = new Kettler(new KettlerSpec());
        kettler.addLog(new Log(20, new Date(), new Date()));
        LogList expectedResult = new LogList();
        expectedResult.addLog(new Log(20, new Date(), new Date()));

        //Act

        LogList actualResult = kettler.getLogList();

        //Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testLogListNotEmpty() {
        //Arrange

        Kettler kettler = new Kettler(new KettlerSpec());
        kettler.addLog(new Log(20, new Date(), new Date()));


        //Assert

        assertFalse(kettler.isLogListEmpty());
    }


    @Test
    public void testCountLogsInInterval() {

        //Arrange

        Kettler kettlerNoLogs = new Kettler(new KettlerSpec());
        Kettler kettlerWithLogs = new Kettler(new KettlerSpec());

        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date beforeInterval = new Date();
        Date startInterval = new Date();
        Date middleInterval = new Date();
        Date endInterval = new Date();

        try {
            beforeInterval = validSdf.parse("10/01/2018 09:59:59");
            startInterval = validSdf.parse("11/01/2018 10:00:00");
            middleInterval = validSdf.parse("16/01/2018 10:30:00");
            endInterval = validSdf.parse("11/02/2018 10:00:00");

        } catch (ParseException e) {
            e.printStackTrace();
        }

        Log insideInterval = new Log(200, startInterval, endInterval);
        Log outsideInterval = new Log(200, beforeInterval, middleInterval);

        kettlerWithLogs.addLog(insideInterval);
        kettlerWithLogs.addLog(outsideInterval);

        //Act

        int actualResult1 = kettlerNoLogs.countLogsInInterval(startInterval, endInterval);
        int actualResult2 = kettlerWithLogs.countLogsInInterval(startInterval, endInterval);

        //Assert

        assertEquals(0, actualResult1);
        assertEquals(1, actualResult2);
    }

    @Test
    public void testGetLogsInInterval() {

        //Arrange

        Kettler kettlerNoLogs = new Kettler(new KettlerSpec());
        Kettler kettlerWithLogs = new Kettler(new KettlerSpec());

        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date beforeInterval = new Date();
        Date startInterval = new Date();
        Date middleInterval = new Date();
        Date endInterval = new Date();

        try {
            beforeInterval = validSdf.parse("10/01/2018 09:59:59");
            startInterval = validSdf.parse("11/01/2018 10:00:00");
            middleInterval = validSdf.parse("16/01/2018 10:30:00");
            endInterval = validSdf.parse("11/02/2018 10:00:00");

        } catch (ParseException e) {
            e.printStackTrace();
        }

        Log insideInterval = new Log(200, startInterval, endInterval);
        Log outsideInterval = new Log(200, beforeInterval, middleInterval);

        kettlerWithLogs.addLog(insideInterval);
        kettlerWithLogs.addLog(outsideInterval);

        LogList expectedResult1 = new LogList();
        LogList expectedResult2 = new LogList();
        expectedResult2.addLog(insideInterval);

        //Act

        LogList actualResult1 = kettlerNoLogs.getLogsInInterval(startInterval, endInterval);
        LogList actualResult2 = kettlerWithLogs.getLogsInInterval(startInterval, endInterval);

        //Assert

        assertEquals(expectedResult1, actualResult1);
        assertEquals(expectedResult2, actualResult2);
    }

    @Test
    public void testGetConsumptionInInterval() {

        //Arrange

        Kettler kettlerNoLogs = new Kettler(new KettlerSpec());
        Kettler kettlerWithLogs = new Kettler(new KettlerSpec());

        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date beforeInterval = new Date();
        Date startInterval = new Date();
        Date middleInterval = new Date();
        Date endInterval = new Date();

        try {
            beforeInterval = validSdf.parse("10/01/2018 09:59:59");
            startInterval = validSdf.parse("11/01/2018 10:00:00");
            middleInterval = validSdf.parse("16/01/2018 10:30:00");
            endInterval = validSdf.parse("11/02/2018 10:00:00");

        } catch (ParseException e) {
            e.printStackTrace();
        }

        Log insideInterval = new Log(200, startInterval, endInterval);
        Log outsideInterval = new Log(200, beforeInterval, middleInterval);

        kettlerWithLogs.addLog(insideInterval);
        kettlerWithLogs.addLog(outsideInterval);

        //Act

        double actualResult1 = kettlerNoLogs.getConsumptionInInterval(startInterval, endInterval);
        double actualResult2 = kettlerWithLogs.getConsumptionInInterval(startInterval, endInterval);

        //Assert

        assertEquals(0.0, actualResult1);
        assertEquals(200D, actualResult2);

    }

    //TODO
    @Test
    public void testGetEnergyConsumption() {
        //Arrange

        Kettler kettler = new Kettler(new KettlerSpec());
        kettler.setAttributeValue(KettlerSpec.COLD_WATER_TEMP, 50D);
        kettler.setAttributeValue(KettlerSpec.VOLUME_WATER, 1.5);

        //Act

        double consumption = kettler.getEnergyConsumption(10);

        //Assert
        assertEquals(82.86, consumption, 0.01);
    }

    @Test
    public void testGetEnergyConsumptionWithInvalidTemperature() {
        //Arrange

        Kettler kettler = new Kettler(new KettlerSpec());
        kettler.setAttributeValue(KettlerSpec.COLD_WATER_TEMP, 150D);
        kettler.setAttributeValue(KettlerSpec.VOLUME_WATER, 1.5);

        //Act

        double consumption = kettler.getEnergyConsumption(10);

        //Assert
        assertEquals(0, consumption, 0.01);
    }

    @Test
    public void testGetAttributeNames() {

        //Arrange

        Kettler kettler = new Kettler(new KettlerSpec());
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("Cold Water Temperature");
        expectedResult.add("Volume Water To Heat");
        expectedResult.add("Performance Ratio");

        //Act

        List<String> actualResult = kettler.getAttributeNames();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testGetAttributeValue() {

        //Arrange

        Kettler kettler = new Kettler(new KettlerSpec());

        //Act

        Object actualResult1 = kettler.getAttributeValue(KettlerSpec.COLD_WATER_TEMP);
        Object actualResult2 = kettler.getAttributeValue(KettlerSpec.VOLUME_WATER);
        Object actualResult3 = kettler.getAttributeValue(KettlerSpec.PERFORMANCE_RATIO);

        // Assert

        assertEquals(0.0, actualResult1);
        assertEquals(0.0, actualResult2);
        assertEquals(0.95, actualResult3);
    }

    @Test
    public void testSetAttributeValue() {

        //Arrange

        Kettler kettler = new Kettler(new KettlerSpec());

        //Act

        kettler.setAttributeValue(KettlerSpec.COLD_WATER_TEMP, 100D);
        kettler.setAttributeValue(KettlerSpec.VOLUME_WATER, 200D);
        kettler.setAttributeValue(KettlerSpec.PERFORMANCE_RATIO, 0.99);

        Object actualResult1 = kettler.getAttributeValue(KettlerSpec.COLD_WATER_TEMP);
        Object actualResult2 = kettler.getAttributeValue(KettlerSpec.VOLUME_WATER);
        Object actualResult3 = kettler.getAttributeValue(KettlerSpec.PERFORMANCE_RATIO);

        // Assert

        assertEquals(100D, actualResult1);
        assertEquals(200D, actualResult2);
        assertEquals(0.99, actualResult3);
    }

    @Test
    public void testGetAttributeUnit() {

        //Arrange

        Kettler kettler = new Kettler(new KettlerSpec());

        //Act

        Object actualResult1 = kettler.getAttributeUnit(KettlerSpec.COLD_WATER_TEMP);
        Object actualResult2 = kettler.getAttributeUnit(KettlerSpec.VOLUME_WATER);
        Object actualResult3 = kettler.getAttributeUnit(KettlerSpec.PERFORMANCE_RATIO);

        // Assert

        assertEquals("ºC", actualResult1);
        assertEquals("L", actualResult2);
        assertEquals("Performance ratio doesn't have a measurement unit.", actualResult3);
    }

    @Test
    void testSetAttributeValueInvalidValue() {

        //Arrange

        Kettler kettler = new Kettler(new KettlerSpec());

        //Act

        boolean actualResult1 = kettler.setAttributeValue(KettlerSpec.COLD_WATER_TEMP, 100);
        boolean actualResult2 = kettler.setAttributeValue(KettlerSpec.VOLUME_WATER, 200);
        boolean actualResult3 = kettler.setAttributeValue(KettlerSpec.PERFORMANCE_RATIO, "invalid type");
        boolean actualResult4 = kettler.setAttributeValue(KettlerSpec.PERFORMANCE_RATIO, -0.99);
        boolean actualResult5 = kettler.setAttributeValue(KettlerSpec.PERFORMANCE_RATIO, 1.45);

        // Assert

        assertFalse(actualResult1);
        assertFalse(actualResult2);
        assertFalse(actualResult3);
        assertFalse(actualResult4);
        assertFalse(actualResult5);
    }

    @Test
    void testGetAttributeUnitIllegalArgument() {

        //Arrange

        Kettler kettler = new Kettler(new KettlerSpec());

        //Assert

        assertThrows(IllegalArgumentException.class,
                () -> kettler.getAttributeUnit("invalid string"));
    }

    @Test
    void testGetAttributeValueIllegalArgument() {

        //Arrange

        Kettler kettler = new Kettler(new KettlerSpec());

        //Assert

        assertThrows(IllegalArgumentException.class,
                () -> kettler.getAttributeValue("invalid string"));

        //AssertThrows

        assertThrows(IllegalArgumentException.class,
                () -> kettler.setAttributeValue("invalid string", 200D));
    }

    @Test
    void testSetAttributeValueIllegalArgument() {

        //Arrange

        Kettler kettler = new Kettler(new KettlerSpec());

        //Assert

        assertThrows(IllegalArgumentException.class,
                () -> kettler.setAttributeValue("invalid string", 200D));
    }

    @Test
    public void testEquals() {

        //Arrange

        Kettler kettler1 = new Kettler(new KettlerSpec());
        Kettler kettler2 = new Kettler(new KettlerSpec());
        Kettler kettler3 = new Kettler(new KettlerSpec());
        kettler1.setName("kettler1");
        kettler2.setName("kettler2");
        kettler3.setName("kettler1");

        //Act

        boolean actualResult1 = kettler1.equals(kettler2);
        boolean actualResult2 = kettler1.equals(kettler3);
        boolean actualResult3 = kettler1.equals(20D);
        boolean actualResult4 = kettler1.equals(kettler1);

        //Assert

        assertFalse(actualResult1);
        assertTrue(actualResult2);
        assertFalse(actualResult3);
        assertTrue(actualResult4);
    }

    @Test
    public void testHashCode() {

        //Arrange

        Kettler kettler = new Kettler(new KettlerSpec());

        //Act

        int actualResult = kettler.hashCode();

        //Assert

        assertEquals(1, actualResult);

    }
}