package pt.ipp.isep.dei.project.model.device;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.device.devicespecs.KettlerSpec;
import pt.ipp.isep.dei.project.model.device.log.Log;
import pt.ipp.isep.dei.project.model.device.log.LogList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KettlerTest {

    private Kettler kettler;

    @BeforeEach
    void arrangeArtifacts() {
        kettler = new Kettler(new KettlerSpec());
        kettler.setName("validKettler1");
    }

    @Test
    void seeIfSetGetNameWorks() {

        //Act

        String actualResult = kettler.getName();

        //Assert

        assertEquals("validKettler1", actualResult);
    }

    @Test
    void seeIfGetTypeWorks() {

        //Act

        String actualResult = kettler.getType();

        //Assert

        assertEquals("Kettler", actualResult);
    }

    @Test
    void seeIfSetNominalPowerWorks() {

        //Act

        kettler.setNominalPower(900D);
        double actualResult1 = kettler.getNominalPower();

        //Assert

        assertEquals(900D, actualResult1);

        //Act

        kettler.setNominalPower(0D);
        double actualResult3 = kettler.getNominalPower();

        //Assert

        assertEquals(0D, actualResult3);
    }

    @Test
    void seeIfDeactivateWorks() {

        //Act

        boolean actualResult = kettler.deactivate();

        //Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfDeactivateFails() {

        //Act
        kettler.deactivate();
        boolean actualResult = kettler.deactivate();

        //Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfIsActiveWorks() {

        //Assert

        assertTrue(kettler.isActive());

        //Arrange

        kettler.deactivate();

        //Act

        boolean actualResult = kettler.isActive();

        //Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfBuildStringWorks() {

        //Arrange

        Kettler kettler = new Kettler(new KettlerSpec());
        kettler.setName("validKettler1");
        kettler.setNominalPower(900D);

        //Act

        String actualResult = kettler.buildString();

        //Assert

        assertEquals("The device Name is validKettler1, and its NominalPower is 900.0 kW.\n", actualResult);
    }

    @Test
    void seeIfIsLogListEmptyWorks() {

        //Assert

        assertTrue(kettler.isLogListEmpty());
    }

    @Test
    void seeIfGetLogListWorks() {

        //Arrange

        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date startInterval = new Date();
        Date endInterval = new Date();

        try {
            startInterval = validSdf.parse("11/01/2018 10:20:00");
            endInterval = validSdf.parse("11/02/2018 10:01:00");

        } catch (ParseException e) {
            e.printStackTrace();
        }

        kettler.addLog(new Log(20, startInterval, endInterval));
        LogList expectedResult = new LogList();
        expectedResult.addLog(new Log(20, startInterval, endInterval));

        //Act

        LogList actualResult = kettler.getLogList();

        //Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfIsLogListEmptyWorksWhenNotEmpty() {

        //Arrange

        kettler.addLog(new Log(20, new Date(), new Date()));


        //Assert

        assertFalse(kettler.isLogListEmpty());
    }


    @Test
    void seeIfCountLogsInIntervalWorks() {

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
    void seeIfGetLogsInIntervalWorks() {

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
    void seeIfGetConsumptionInIntervalWorks() {

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

    @Test
    void seeIfGetEnergyConsumptionWorks() {
        //Arrange

        kettler.setAttributeValue(KettlerSpec.COLD_WATER_TEMP, 50D);
        kettler.setAttributeValue(KettlerSpec.VOLUME_WATER, 1.5);

        //Act

        double consumption = kettler.getEnergyConsumption(10);

        //Assert
        assertEquals(82.86, consumption, 0.01);
    }

    @Test
    void seeIfGetEnergyConsumptionWorksWithInvalidTemperature() {
        //Arrange

        kettler.setAttributeValue(KettlerSpec.COLD_WATER_TEMP, 150D);
        kettler.setAttributeValue(KettlerSpec.VOLUME_WATER, 1.5);

        //Act

        double consumption = kettler.getEnergyConsumption(10);

        //Assert
        assertEquals(0, consumption, 0.01);
    }

    @Test
    void seeIfGetEnergyConsumptionWorksWithInvalidTemperatureOnLimit() {
        //Arrange

        kettler.setAttributeValue(KettlerSpec.COLD_WATER_TEMP, 100D);
        kettler.setAttributeValue(KettlerSpec.VOLUME_WATER, 1.5);

        //Act

        double consumption = kettler.getEnergyConsumption(10);

        //Assert
        assertEquals(0, consumption, 0.01);
    }

    @Test
    void seeIfdTemperatureWorks() {

        //Arrange Cold Temperature lower than boiling water

        kettler.setAttributeValue(KettlerSpec.COLD_WATER_TEMP, 99D);

        //Act

        double actualResult1 = kettler.dTemperature();

        //Assert

        assertEquals(1D, actualResult1, 0.01);

        //Arrange Cold Temperature same as boiling water

        kettler.setAttributeValue(KettlerSpec.COLD_WATER_TEMP, 100D);

        //Act

        double actualResult2 = kettler.dTemperature();

        //Assert

        assertEquals(0D, actualResult2, 0.01);

        //Arrange Cold Temperature bigger as boiling water

        kettler.setAttributeValue(KettlerSpec.COLD_WATER_TEMP, 101D);

        //Act

        double actualResult3 = kettler.dTemperature();

        //Assert

        assertEquals(0D, actualResult3, 0.01);
    }

    @Test
    void seeIfGetAttributeNamesWorks() {

        //Arrange

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
    void seeIfGetAttributeValueWorks() {

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
    void seeIfSetAttributeValueWorks() {

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
    void seeIfGetAttributeUnitWorks() {

        //Act

        Object actualResult1 = kettler.getAttributeUnit(KettlerSpec.COLD_WATER_TEMP);
        Object actualResult2 = kettler.getAttributeUnit(KettlerSpec.VOLUME_WATER);
        Object actualResult3 = kettler.getAttributeUnit(KettlerSpec.PERFORMANCE_RATIO);

        // Assert

        assertEquals("ºC", actualResult1);
        assertEquals("L", actualResult2);
        assertEquals("", actualResult3);
    }

    @Test
    void seeIfSetAttributeValueWorksWithInvalidValue() {

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
    void seeIfGetAttributeUnitIllegalThrowsArgument() {

        //Assert

        assertThrows(IllegalArgumentException.class,
                () -> kettler.getAttributeUnit("invalid string"));
    }

    @Test
    void seeIfGetAttributeValueThrowsIllegalArgument() {

        //Assert

        assertThrows(IllegalArgumentException.class,
                () -> kettler.getAttributeValue("invalid string"));

        //AssertThrows

        assertThrows(IllegalArgumentException.class,
                () -> kettler.setAttributeValue("invalid string", 200D));
    }

    @Test
    void seeIfSetAttributeValueThrowsIllegalArgument() {

        //Assert

        assertThrows(IllegalArgumentException.class,
                () -> kettler.setAttributeValue("invalid string", 200D));
    }

    @Test
    void seeIfAddLogWorks() {

        //Arrange

        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date startInterval = new Date();
        Date endInterval = new Date();

        try {
            startInterval = validSdf.parse("11/01/2018 10:00:00");
            endInterval = validSdf.parse("11/02/2018 10:00:00");

        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log log1 = new Log(20D, startInterval, endInterval);

        //Act

        boolean actualResult1 = kettler.addLog(log1);
        boolean actualResult2 = kettler.addLog(log1);

        //Assert

        assertTrue(actualResult1);
        assertFalse(actualResult2);
    }

    @Test
    void seeIfAddLogWorksWhenDeviceIsDeactivated() {

        //Arrange

        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date startInterval = new Date();
        Date endInterval = new Date();

        try {
            startInterval = validSdf.parse("11/01/2018 10:00:00");
            endInterval = validSdf.parse("11/02/2018 10:00:00");

        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log log1 = new Log(20D, startInterval, endInterval);
        kettler.deactivate();

        //Act

        boolean actualResult1 = kettler.addLog(log1);

        //Assert

        assertFalse(actualResult1);
    }

    @Test
    void seeIfEqualsWorks() {

        Kettler kettler2 = new Kettler(new KettlerSpec());
        Kettler kettler3 = new Kettler(new KettlerSpec());
        kettler2.setName("validKettler2");
        kettler3.setName("validKettler1");

        //Act

        boolean actualResult1 = kettler.equals(kettler2);
        boolean actualResult2 = kettler.equals(kettler3);
        boolean actualResult3 = kettler.equals(20D);
        boolean actualResult4 = kettler.equals(kettler);
        boolean actualResult5 = kettler.equals(null);

        //Assert

        assertFalse(actualResult1);
        assertTrue(actualResult2);
        assertFalse(actualResult3);
        assertTrue(actualResult4);
        assertFalse(actualResult5);
    }

    @Test
    void seeIfHashCodeWorks() {

        //Act

        int actualResult = kettler.hashCode();

        //Assert

        assertEquals(1, actualResult);

    }
}