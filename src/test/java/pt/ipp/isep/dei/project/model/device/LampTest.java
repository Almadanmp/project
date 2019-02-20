package pt.ipp.isep.dei.project.model.device;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Room;
import pt.ipp.isep.dei.project.model.device.devicespecs.LampSpec;
import pt.ipp.isep.dei.project.model.device.log.Log;
import pt.ipp.isep.dei.project.model.device.log.LogList;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Lamp Device tests class.
 */

 class LampTest {

    @Test
     void getDeviceTypeTest() {
        Lamp d = new Lamp(new LampSpec());
        d.setAttributeValue("capacity", 12D);
        String dT = "Lamp";
        String expectedResult = dT;
        String result = d.getType();
        assertEquals(expectedResult, result);
    }

    @Test
    void seeEqualToSameObject() {
        Lamp d = new Lamp(new LampSpec());
        d.setName("WMOne");
        d.setNominalPower(12.0);
        d.setAttributeValue("capacity", 12D);
        boolean actualResult = d.equals(d);
        assertTrue(actualResult);
    }

    @Test
    void getNominalPowerTest() {
        Lamp d = new Lamp(new LampSpec());
        d.setNominalPower(30);
        double expectedResult = 30;
        double result = d.getNominalPower();
        assertEquals(expectedResult, result);
    }

    @Test
    void seeEqualsToDifObject() {
        Lamp d = new Lamp(new LampSpec());
        d.setName("WMOne");
        d.setNominalPower(12.0);
        d.setAttributeValue(LampSpec.FLUX, 34);
        Lamp d2 = new Lamp(new LampSpec());
        d2.setName("WMTwo");
        d2.setNominalPower(12.0);
        d.setAttributeValue(LampSpec.FLUX, 45);

        boolean actualResult = d.equals(d2);
        assertFalse(actualResult);
    }


    @Test
    void seeEqualsToDifTypeObject() {
        Lamp d = new Lamp(new LampSpec());
        d.setName("WMOne");
        d.setNominalPower(12.0);
        d.setAttributeValue(LampSpec.FLUX, 56);
        Room room = new Room("quarto", 1, 80, 2, 2);

        boolean actualResult = d.equals(room);
        assertFalse(actualResult);
    }

    @Test
    void seeEqualsToNullObject() {
        Lamp d = new Lamp(new LampSpec());
        d.setAttributeValue(LampSpec.FLUX, 34);
        boolean actualResult = d.equals(null);

        assertFalse(actualResult);
    }

    @Test
    void seeIfPrintDeviceWorks() {
        Lamp d1 = new Lamp(new LampSpec());
        d1.setAttributeValue(LampSpec.FLUX, 2D);
        d1.setName("frigo");
        d1.setNominalPower(150.0);
        String result = d1.buildDeviceString();
        String expectedResult = "The device Name is frigo, and its NominalPower is 150.0 kW.\n";
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfSetNameWorks() {
        Lamp d1 = new Lamp(new LampSpec());
        d1.setName("frigo");
        d1.setAttributeValue(LampSpec.FLUX, 2D);
        d1.setName("frigorifico");
        String result = d1.getName();
        String expectedResult = "frigorifico";
        assertEquals(expectedResult, result);
    }

    @Test
     void hashCodeDummyTest() {
        Lamp d1 = new Lamp(new LampSpec());
        d1.setName("FridgeTwo");
        d1.setNominalPower(12.0);
        d1.setAttributeValue(LampSpec.FLUX, 4D);
        int expectedResult = 1;
        int actualResult = d1.hashCode();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetAndSetAttributeValue() {
        Lamp d1 = new Lamp(new LampSpec());
        d1.setAttributeValue(LampSpec.FLUX, 12D);
        Double expectedResult = 33.3;
        d1.setAttributeValue(LampSpec.FLUX, 33.3);
        Object result = d1.getAttributeValue(LampSpec.FLUX);
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAndSetAttributeUnit() {
        Lamp d1 = new Lamp(new LampSpec());
        d1.setAttributeValue(LampSpec.FLUX, 12D);
        String expectedResult = "lm";
        d1.setAttributeValue(LampSpec.FLUX, 33.3);
        Object result = d1.getAttributeUnit(LampSpec.FLUX);
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAttributeNames() {
        Lamp d1 = new Lamp(new LampSpec());
        d1.setAttributeValue(LampSpec.FLUX, 12D);

        List<String> result = d1.getAttributeNames();
        assertTrue(result.contains(LampSpec.FLUX));

        assertEquals(result.size(), 1);
    }

    @Test
    void ensureThatWeDeactivateADevice() {
        Lamp d1 = new Lamp(new LampSpec());
        boolean expectedResult = true;
        boolean actualResult = d1.deactivate();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void ensureThatWeDoNotDeactivate() {
        Lamp d1 = new Lamp(new LampSpec());
        d1.setAttributeValue(LampSpec.FLUX, 12D);
        d1.deactivate();
        boolean expectedResult = false;
        boolean actualResult = d1.deactivate();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void ensureThatDeviceIsNotProg() {
        Lamp d1 = new Lamp(new LampSpec());
        d1.setAttributeValue(LampSpec.FLUX, 12D);
        d1.deactivate();
        boolean expectedResult = false;
        boolean actualResult = d1.isProgrammable();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetEnergyConsumption() {
        Lamp d1 = new Lamp(new LampSpec());
        d1.setAttributeValue(LampSpec.FLUX, 12D);
        double expectedResult = 0;
        double actualResult = d1.getEnergyConsumption(2);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getLogList() {
        Lamp d1 = new Lamp(new LampSpec());
        Log log = new Log(1, new GregorianCalendar(2019, 1, 1).getTime(),
                new GregorianCalendar(2019, 1, 1).getTime());
        LogList logList = d1.getLogList();
        d1.addLog(log);
        LogList result = d1.getLogList();
        assertEquals(logList, result);
    }

    @Test
    void getLogListBreakTest() {
        Lamp d1 = new Lamp(new LampSpec());
        LogList logList = new LogList();
        LogList result = d1.getLogList();
        assertEquals(logList, result);
    }

    @Test
    void addLogListFalse() {
        Lamp d1 = new Lamp(new LampSpec());
        Log log = new Log(1, new GregorianCalendar(2019, 1, 1).getTime(),
                new GregorianCalendar(2019, 1, 1).getTime());
        d1.addLog(log);
        assertFalse(d1.addLog(log));
    }

    @Test
    void addLogToInactive() {
        Lamp d1 = new Lamp(new LampSpec());
        Log log = new Log(1, new GregorianCalendar(2019, 1, 1).getTime(),
                new GregorianCalendar(2019, 1, 1).getTime());
        d1.deactivate();
        boolean result = d1.addLog(log);
        assertFalse(result);
    }

    @Test
    void addLogTrue() {
        Lamp d1 = new Lamp(new LampSpec());
        Log log = new Log(1, new GregorianCalendar(2019, 1, 1).getTime(),
                new GregorianCalendar(2019, 1, 1).getTime());
        boolean result = d1.addLog(log);
        assertTrue(result);
    }

    @Test
    void getTotalMeteredEnergyConsumptionInDeviceWithinGivenTimeIntervalEquals() {
        Date initialTime = new GregorianCalendar(2018, 10, 20, 10, 0).getTime();
        Date finalTime = new GregorianCalendar(2018, 10, 20, 11, 0).getTime();
        Date periodBeginning1 = new GregorianCalendar(2018, 10, 20, 10, 0).getTime();
        Date periodEnding1 = new GregorianCalendar(2018, 10, 20, 10, 20).getTime();
        Date periodBeginning2 = new GregorianCalendar(2018, 10, 20, 10, 30).getTime();
        Date periodEnding2 = new GregorianCalendar(2018, 10, 20, 11, 0).getTime();
        Lamp device = new Lamp(new LampSpec());
        device.setAttributeValue(LampSpec.FLUX, 400D);
        Log log1 = new Log(56, periodBeginning1, periodEnding1);
        Log log2 = new Log(55, periodBeginning2, periodEnding2);
        device.addLog(log1);
        device.addLog(log2);
        double result = device.getConsumptionWithinGivenInterval(initialTime, finalTime);
        assertEquals(111, result);
    }

    @Test
    void getTotalMeteredEnergyConsumptionInDeviceWithinGivenTimeIntervalAfterBefore() {
        Date initialTime = new GregorianCalendar(2018, 10, 20, 10, 0).getTime();
        Date finalTime = new GregorianCalendar(2018, 10, 20, 11, 0).getTime();
        Date periodBeginning1 = new GregorianCalendar(2018, 10, 20, 10, 1).getTime();
        Date periodEnding1 = new GregorianCalendar(2018, 10, 20, 10, 20).getTime();
        Date periodBeginning2 = new GregorianCalendar(2018, 10, 20, 10, 30).getTime();
        Date periodEnding2 = new GregorianCalendar(2018, 10, 20, 10, 59).getTime();
        Lamp device = new Lamp(new LampSpec());
        device.setAttributeValue(LampSpec.FLUX, 400D);
        Log log1 = new Log(56, periodBeginning1, periodEnding1);
        Log log2 = new Log(55, periodBeginning2, periodEnding2);
        device.addLog(log1);
        device.addLog(log2);
        double result = device.getConsumptionWithinGivenInterval(initialTime, finalTime);
        assertEquals(111, result);
    }

    @Test
    void getTotalMeteredEnergyConsumptionInDeviceWithinGivenTimeIntervalAfterBeforeReverseOutOfBounds() {
        Date initialTime = new GregorianCalendar(2018, 10, 20, 10, 0).getTime();
        Date finalTime = new GregorianCalendar(2018, 10, 20, 11, 0).getTime();
        Date periodBeginning1 = new GregorianCalendar(2018, 10, 20, 9, 0).getTime();
        Date periodEnding1 = new GregorianCalendar(2018, 10, 20, 10, 20).getTime();
        Date periodBeginning2 = new GregorianCalendar(2018, 10, 20, 10, 30).getTime();
        Date periodEnding2 = new GregorianCalendar(2018, 10, 20, 11, 20).getTime();
        Lamp device = new Lamp(new LampSpec());
        device.setAttributeValue(LampSpec.FLUX, 400D);
        Log log1 = new Log(56, periodBeginning1, periodEnding1);
        Log log2 = new Log(55, periodBeginning2, periodEnding2);
        device.addLog(log1);
        device.addLog(log2);
        double result = device.getConsumptionWithinGivenInterval(initialTime, finalTime);
        assertEquals(0.0, result);
    }

    @Test
    void testCountLogsInInterval() {
        Date initialTime = new GregorianCalendar(2018, 10, 20, 10, 0).getTime();
        Date finalTime = new GregorianCalendar(2018, 10, 20, 11, 0).getTime();
        Date periodBeginning1 = new GregorianCalendar(2018, 10, 20, 10, 0).getTime();
        Date periodEnding1 = new GregorianCalendar(2018, 10, 20, 10, 20).getTime();
        Date periodBeginning2 = new GregorianCalendar(2018, 10, 20, 10, 20).getTime();
        Date periodEnding2 = new GregorianCalendar(2018, 10, 20, 10, 40).getTime();
        Date periodBeginning3 = new GregorianCalendar(2018, 10, 20, 10, 40).getTime();
        Date periodEnding3 = new GregorianCalendar(2018, 10, 20, 11, 0).getTime();
        Lamp device = new Lamp(new LampSpec());
        device.setAttributeValue(LampSpec.FLUX, 400D);
        Log log1 = new Log(56, periodBeginning1, periodEnding1);
        Log log2 = new Log(55, periodBeginning2, periodEnding2);
        Log log3 = new Log(55, periodBeginning3, periodEnding3);
        device.addLog(log1);
        device.addLog(log2);
        device.addLog(log3);
        //Act
        Integer expectedResult = 3;
        Integer actualResult = device.countLogsInInterval(initialTime, finalTime);
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testGetLogsInInterval() {
        Date initialTime = new GregorianCalendar(2018, 10, 20, 10, 0).getTime();
        Date finalTime = new GregorianCalendar(2018, 10, 20, 11, 0).getTime();
        Date periodBeginning1 = new GregorianCalendar(2018, 10, 20, 10, 0).getTime();
        Date periodEnding1 = new GregorianCalendar(2018, 10, 20, 10, 20).getTime();
        Date periodBeginning2 = new GregorianCalendar(2018, 10, 20, 10, 20).getTime();
        Date periodEnding2 = new GregorianCalendar(2018, 10, 20, 10, 40).getTime();
        Date periodBeginning3 = new GregorianCalendar(2018, 10, 20, 10, 40).getTime();
        Date periodEnding3 = new GregorianCalendar(2018, 10, 20, 11, 0).getTime();
        Date periodBeginning4 = new GregorianCalendar(2018, 10, 20, 11, 0).getTime();
        Date periodEnding4 = new GregorianCalendar(2018, 10, 20, 11, 20).getTime();
        Date periodBeginning5 = new GregorianCalendar(2018, 10, 20, 9, 40).getTime();
        Date periodEnding5 = new GregorianCalendar(2018, 10, 20, 10, 0).getTime();
        Lamp device = new Lamp(new LampSpec());
        device.setAttributeValue(LampSpec.FLUX, 400D);
        Log log1 = new Log(56, periodBeginning1, periodEnding1);
        Log log2 = new Log(55, periodBeginning2, periodEnding2);
        Log log3 = new Log(55, periodBeginning3, periodEnding3);
        Log log4 = new Log(55, periodBeginning4, periodEnding4);
        Log log5 = new Log(55, periodBeginning5, periodEnding5);
        device.addLog(log1);
        device.addLog(log2);
        device.addLog(log3);
        device.addLog(log4);
        device.addLog(log5);
        //Act
        LogList expectedResult = new LogList();
        expectedResult.addLog(log1);
        expectedResult.addLog(log2);
        expectedResult.addLog(log3);
        LogList actualResult = device.getLogsInInterval(initialTime, finalTime);
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testGetLogsInIntervalOutOfBounds() {
        Date initialTime = new GregorianCalendar(2018, 10, 20, 10, 0).getTime();
        Date finalTime = new GregorianCalendar(2018, 10, 20, 11, 0).getTime();
        Date periodBeginning1 = new GregorianCalendar(2018, 10, 20, 9, 50).getTime();
        Date periodEnding1 = new GregorianCalendar(2018, 10, 20, 10, 10).getTime();
        Date periodBeginning2 = new GregorianCalendar(2018, 10, 20, 10, 50).getTime();
        Date periodEnding2 = new GregorianCalendar(2018, 10, 20, 11, 10).getTime();
        Lamp device = new Lamp(new LampSpec());
        device.setAttributeValue(LampSpec.FLUX, 400D);
        Log log1 = new Log(56, periodBeginning1, periodEnding1);
        Log log2 = new Log(55, periodBeginning2, periodEnding2);
        device.addLog(log1);
        device.addLog(log2);
        //Act
        LogList expectedResult = new LogList();
        LogList actualResult = device.getLogsInInterval(initialTime, finalTime);
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getConsumption() {
        Lamp d = new Lamp(new LampSpec());
        d.setNominalPower(15);
        double expectedResult = 360;
        double result = d.getEnergyConsumption(24);
        assertEquals(expectedResult, result);
    }

    @Test
    void getConsumptionTimeZero() {
        Lamp d = new Lamp(new LampSpec());
        d.setNominalPower(15);
        double expectedResult = 0;
        double result = d.getEnergyConsumption(0);
        assertEquals(expectedResult, result);
    }

    @Test
    void testSetAttributeValueForAllCases() {
        //Arrange
        LampSpec lspec = new LampSpec();
        Lamp lamp = new Lamp(lspec);
        Double attribute = 6.0;
        // original strings + double:
        assertTrue(lamp.setAttributeValue(LampSpec.FLUX, attribute));
        // same hash codes, but different strings + double:
        Assertions.assertFalse(lamp.setAttributeValue("notFLUX", attribute));
        // distinct hash code to cover default cases of switches + double
        Assertions.assertFalse(lamp.setAttributeValue("", attribute));
    }

    @Test
    void testSetAttributeValueForNotDouble() {
        //Arrange
        LampSpec lspec = new LampSpec();
        Lamp lamp = new Lamp(lspec);
        Double attributeD = 6.0;
        Integer attribute = 6;
        lamp.setAttributeValue(LampSpec.FLUX, attributeD);
        // original strings + not double:
        Assertions.assertFalse(lamp.setAttributeValue(LampSpec.FLUX, attribute));
        // same hash codes, but different strings + not double:
        Assertions.assertFalse(lamp.setAttributeValue("notFLUX", attribute));
        Assertions.assertFalse(lamp.setAttributeValue("notNOMINAL_POWER", attribute));
        // distinct hash code to cover default cases of switches + not double
        Assertions.assertFalse(lamp.setAttributeValue("", attribute));
    }

}
