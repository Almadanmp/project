package pt.ipp.isep.dei.project.model.device;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.device.devicespecs.TvSpec;
import pt.ipp.isep.dei.project.model.device.log.Log;
import pt.ipp.isep.dei.project.model.device.log.LogList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.lang.Double.NaN;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.testng.Assert.*;

/**
 * TV Device tests class.
 */

class TVTest {

    // Common artifacts for testing in this class.

    private TV validTV;

    @BeforeEach
    void arrangeArtifacts() {
        validTV = new TV(new TvSpec());
        validTV.setName("Living Room TV");
        validTV.setNominalPower(15D);
        validTV.setAttributeValue(TvSpec.STANDBY_POWER, 15D);
        validTV.addLog(new Log(1, new GregorianCalendar(2019, Calendar.FEBRUARY, 1).getTime(),
                new GregorianCalendar(2019, Calendar.FEBRUARY, 1).getTime()));
    }

    @Test
    void seeIfGetSetNameWork() {
        // Happy Case
        validTV.setName("Room TV");
        String expectedResultHappy = "Room TV";

        String resultHappy = validTV.getName();

        assertEquals(expectedResultHappy, resultHappy);

        // Set Name at Null
        validTV.setName(null);
        String expectedResultNull = "TV";

        String resultNull = validTV.getType();

        assertEquals(expectedResultNull, resultNull);
    }

    @Test
    void seeIfGetTypeWorks() {
        String expectedResult = "TV";

        String result = validTV.getType();

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfSetNominalPowerWorksHappyCase() {
        validTV.setNominalPower(12D);
        double expectedResult = 12;

        double result = validTV.getNominalPower();

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfSetNominalPowerWorksNegative() {
        assertThrows(IllegalArgumentException.class,
                () -> validTV.setNominalPower(-20));
    }

    @Test
    void seeIfSetNominalPowerWorksIfZero() {
        validTV.setNominalPower(0D);
        double expectedResult = 0.0;

        double result = validTV.getNominalPower();

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfIsActiveWorks() {

        boolean result = validTV.isActive();

        assertTrue(result);
    }

    @Test
    void seeIfDeactivateWorks() {
        boolean resultHappy = validTV.deactivate();
        assertTrue(resultHappy);

        boolean resultFalse = validTV.deactivate();

        assertFalse(resultFalse);
    }

    @Test
    void seeIfBuildStringWorks() {
        String expectedResult = "The device Name is Living Room TV, and its NominalPower is 15.0 kW.\n";

        String result = validTV.buildString();

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetLogListWorks() {
        LogList expectedResult = new LogList();
        expectedResult.addLog(new Log(1, new GregorianCalendar(2019, Calendar.FEBRUARY, 1).getTime(),
                new GregorianCalendar(2019, Calendar.FEBRUARY, 1).getTime()));

        LogList actualResult = validTV.getLogList();

        assertEquals(expectedResult, actualResult);
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

        boolean actualResult1 = validTV.addLog(log1);
        boolean actualResult2 = validTV.addLog(log1);

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
        validTV.deactivate();

        //Act

        boolean actualResult1 = validTV.addLog(log1);

        //Assert

        assertFalse(actualResult1);
    }

    @Test
    void seeIfIsLogListEmptyWorksWhenNotEmpty() {
        validTV.addLog(new Log(20, new Date(), new Date()));

        assertFalse(validTV.isLogListEmpty());
    }

    @Test
    void seeIfCountLogsInIntervalWorks() {

        TV tvNoLogs = new TV(new TvSpec());

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

        validTV.addLog(insideInterval);
        validTV.addLog(outsideInterval);


        int actualResult1 = tvNoLogs.countLogsInInterval(startInterval, endInterval);
        int actualResult2 = validTV.countLogsInInterval(startInterval, endInterval);


        assertEquals(0, actualResult1);
        assertEquals(1, actualResult2);
    }

    @Test
    void seeIfGetLogsInIntervalWorks() {

        //Arrange

        TV tvNoLogs = new TV(new TvSpec());

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

        validTV.addLog(insideInterval);
        validTV.addLog(outsideInterval);

        LogList expectedResult1 = new LogList();
        LogList expectedResult2 = new LogList();
        expectedResult2.addLog(insideInterval);

        //Act

        LogList actualResult1 = tvNoLogs.getLogsInInterval(startInterval, endInterval);
        LogList actualResult2 = validTV.getLogsInInterval(startInterval, endInterval);

        //Assert

        assertEquals(expectedResult1, actualResult1);
        assertEquals(expectedResult2, actualResult2);
    }

    @Test
    void seeIfGetConsumptionInIntervalWorks() {

        //Arrange

        TV tvNoLogs = new TV(new TvSpec());

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

        validTV.addLog(insideInterval);
        validTV.addLog(outsideInterval);

        //Act

        double actualResult1 = tvNoLogs.getConsumptionInInterval(startInterval, endInterval);
        double actualResult2 = validTV.getConsumptionInInterval(startInterval, endInterval);

        //Assert

        assertEquals(0.0, actualResult1);
        assertEquals(200D, actualResult2);

    }

    @Test
    void seeIfGetEnergyConsumptionWorks() {
        double consumption = validTV.getEnergyConsumption(10);

        assertEquals(150.0, consumption, 0.01);
    }

    @Test
    void seeIfGetAttributeNamesWorks() {
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("Standby Power");

        List<String> actualResult = validTV.getAttributeNames();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetAttributeValueWorks() {
        Object actualResult1 = validTV.getAttributeValue(TvSpec.STANDBY_POWER);
        Object actualResult2 = validTV.getAttributeValue("fakeUnit");

        assertEquals(15.0, actualResult1);
        assertEquals(0, actualResult2);
    }


    @Test
    void seeIfSetAttributeValueWorks() {
        validTV.setAttributeValue(TvSpec.STANDBY_POWER, 300D);

        Object actualResult1 = validTV.getAttributeValue(TvSpec.STANDBY_POWER);

        assertEquals(300D, actualResult1);
    }

    @Test
    void seeIfSetAttributeValueWorksWithInvalidValue() {
        boolean actualResult1 = validTV.setAttributeValue(TvSpec.STANDBY_POWER, NaN);

        assertFalse(actualResult1);
    }

    @Test
    void seeIfGetAttributeUnitWorks() {

        Object actualResult1 = validTV.getAttributeUnit(TvSpec.STANDBY_POWER);
        Object actualResult2 = validTV.getAttributeUnit("fakeUnit");

        assertEquals("W", actualResult1);
        assertEquals(0, actualResult2);
    }

    @Test
    void seeIfEqualsWorks() {

        TV validTv2 = new TV(new TvSpec());
        TV validTv3 = new TV(new TvSpec());

        validTv2.setName("validTv2");
        validTv3.setName("Living Room TV");

        boolean actualResult1 = validTV.equals(validTv2);
        boolean actualResult2 = validTV.equals(validTv3);
        boolean actualResult3 = validTV.equals(20D);
        boolean actualResult4 = validTV.equals(validTV);
        boolean actualResult5 = validTV.equals(null);

        assertFalse(actualResult1);
        assertTrue(actualResult2);
        assertFalse(actualResult3);
        assertTrue(actualResult4);
        assertFalse(actualResult5);
    }

    @Test
    void seeIfHashCodeWorks() {
        int actualResult = validTV.hashCode();

        assertEquals(1, actualResult);

    }

}
