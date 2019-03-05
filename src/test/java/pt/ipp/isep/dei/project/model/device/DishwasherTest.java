package pt.ipp.isep.dei.project.model.device;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Room;
import pt.ipp.isep.dei.project.model.device.devicespecs.DishwasherSpec;
import pt.ipp.isep.dei.project.model.device.devicespecs.WashingMachineSpec;
import pt.ipp.isep.dei.project.model.device.devicespecs.WaterHeaterSpec;
import pt.ipp.isep.dei.project.model.device.log.Log;
import pt.ipp.isep.dei.project.model.device.log.LogList;
import pt.ipp.isep.dei.project.model.device.program.FixedTimeProgram;
import pt.ipp.isep.dei.project.model.device.program.ProgramList;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Dishwasher device tests class.
 */

public class DishwasherTest {

    @Test
    void getDeviceTypeTest() {
        Dishwasher d = new Dishwasher(new DishwasherSpec());
        d.setAttributeValue("capacity", 12D);
        String dT = "Dishwasher";
        String expectedResult = dT;
        String result = d.getType();
        assertEquals(expectedResult, result);
    }

    @Test
    void testSetAttributeForNullAttribute() {
        //Arrange
        Dishwasher d = new Dishwasher(new DishwasherSpec());

        //Act
        boolean result = d.setAttributeValue(null, 12D);

        //Assert
        assertFalse(result);
    }

    @Test
    void seeEqualToSameObject() {
        Dishwasher d = new Dishwasher(new DishwasherSpec());
        d.setName("WMOne");
        d.setNominalPower(12.0);
        d.setAttributeValue("capacity", 12D);
        boolean actualResult = d.equals(d);
        assertTrue(actualResult);
    }

    @Test
    void seeEqualsToDifObject() {
        Dishwasher d = new Dishwasher(new DishwasherSpec());
        d.setName("WMOne");
        d.setNominalPower(12.0);
        d.setAttributeValue(WashingMachineSpec.WM_CAPACITY, 34);
        Device d2 = new WashingMachine(new WashingMachineSpec());
        d2.setName("WMTwo");
        d2.setNominalPower(12.0);
        d.setAttributeValue(WashingMachineSpec.WM_CAPACITY, 45);

        boolean actualResult = d.equals(d2);
        assertFalse(actualResult);
    }


    @Test
    void seeEqualsToDifTypeObject() {
        Dishwasher d = new Dishwasher(new DishwasherSpec());
        d.setName("WMOne");
        d.setNominalPower(12.0);
        d.setAttributeValue(WashingMachineSpec.WM_CAPACITY, 56);
        Room room = new Room("quarto", 1, 80, 2, 2);

        boolean actualResult = d.equals(room);
        assertFalse(actualResult);
    }

    @Test
    void seeEqualsToNullObject() {
        Dishwasher d = new Dishwasher(new DishwasherSpec());
        d.setAttributeValue(WashingMachineSpec.WM_CAPACITY, 34);
        boolean actualResult = d.equals(null);

        assertFalse(actualResult);
    }

    @Test
    void seeIfPrintDeviceWorks() {
        Dishwasher d = new Dishwasher(new DishwasherSpec());
        d.setName("dishwasher 3000");
        d.setNominalPower(150.0);
        String result = d.buildString();
        String expectedResult = "The device Name is dishwasher 3000, and its NominalPower is 150.0 kW.\n";
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfSetNameWorks() {
        Dishwasher d1 = new Dishwasher(new DishwasherSpec());
        d1.setName("dishwasher40");
        String result = d1.getName();
        String expectedResult = "dishwasher40";
        assertEquals(expectedResult, result);
    }

    @Test
    void getNominalPowerTest() {
        Dishwasher d1 = new Dishwasher(new DishwasherSpec());
        d1.setName("dishwasher40");
        d1.setNominalPower(150);
        double result = d1.getNominalPower();
        double expectedResult = 150;
        assertEquals(expectedResult, result);
    }


    @Test
    void seeIfGetAndSetAttributeValue() {
        Dishwasher d1 = new Dishwasher(new DishwasherSpec());
        d1.setAttributeValue(DishwasherSpec.DW_CAPACITY, 12D);
        Double expectedResult = 33.3;
        d1.setAttributeValue(DishwasherSpec.DW_CAPACITY, 33.3);
        Object result = d1.getAttributeValue(DishwasherSpec.DW_CAPACITY);
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAndSetAttributeUnit() {
        Dishwasher d1 = new Dishwasher(new DishwasherSpec());
        d1.setAttributeValue(DishwasherSpec.DW_CAPACITY, 12D);
        String expectedResult = "Kg";
        d1.setAttributeValue(DishwasherSpec.DW_CAPACITY, 33.3);
        Object result = d1.getAttributeUnit(DishwasherSpec.DW_CAPACITY);
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAttributeNames() {
        Dishwasher d1 = new Dishwasher(new DishwasherSpec());
        d1.setAttributeValue(DishwasherSpec.DW_CAPACITY, 12D);
        List<String> result = d1.getAttributeNames();
        assertTrue(result.contains(DishwasherSpec.DW_CAPACITY));
        assertEquals(result.size(), 1);
    }

    @Test
    void ensureThatWeDeactivateADevice() {
        Dishwasher d1 = new Dishwasher(new DishwasherSpec());
        d1.setAttributeValue(DishwasherSpec.DW_CAPACITY, 12D);
        boolean expectedResult = true;
        boolean actualResult = d1.deactivate();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void ensureThatWeDoNotDeactivate() {
        Dishwasher d1 = new Dishwasher(new DishwasherSpec());
        d1.setAttributeValue(DishwasherSpec.DW_CAPACITY, 12D);
        d1.deactivate();
        boolean expectedResult = false;
        boolean actualResult = d1.deactivate();
        assertEquals(expectedResult, actualResult);
    }



    @Test
    void seeIfGetEnergyConsumption() {
        Dishwasher d1 = new Dishwasher(new DishwasherSpec());
        d1.setAttributeValue(DishwasherSpec.DW_CAPACITY, 12D);
        d1.deactivate();
        double expectedResult = 0;
        double actualResult = d1.getEnergyConsumption(2);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getProgramList() {
        Dishwasher d1 = new Dishwasher(new DishwasherSpec());
        d1.setAttributeValue(DishwasherSpec.DW_CAPACITY, 12D);
        FixedTimeProgram program1 = new FixedTimeProgram("programa", 2, 2);
        ProgramList listProgram = d1.getProgramList();
        listProgram.addProgram(program1);
        ProgramList result = d1.getProgramList();
        assertEquals(listProgram, result);
    }

    @Test
    void getLogList() {
        Dishwasher d1 = new Dishwasher(new DishwasherSpec());
        d1.setAttributeValue(DishwasherSpec.DW_CAPACITY, 12D);
        Log log = new Log(1, new GregorianCalendar(2019, 1, 1).getTime(),
                new GregorianCalendar(2019, 1, 1).getTime());
        LogList logList = d1.getLogList();
        d1.addLog(log);
        LogList result = d1.getLogList();
        assertEquals(logList, result);
    }

    @Test
    void getLogListBreakTest() {
        Dishwasher d1 = new Dishwasher(new DishwasherSpec());
        d1.setAttributeValue(DishwasherSpec.DW_CAPACITY, 12D);
        LogList logList = new LogList();
        LogList result = d1.getLogList();
        assertEquals(logList, result);
    }

    @Test
    void addLogListFalse() {
        Dishwasher d1 = new Dishwasher(new DishwasherSpec());
        d1.setAttributeValue(DishwasherSpec.DW_CAPACITY, 12D);
        Log log = new Log(1, new GregorianCalendar(2019, 1, 1).getTime(),
                new GregorianCalendar(2019, 1, 1).getTime());
        d1.addLog(log);
        assertFalse(d1.addLog(log));
    }

    @Test
    void addLogToInactive() {
        Dishwasher d1 = new Dishwasher(new DishwasherSpec());
        d1.setAttributeValue(DishwasherSpec.DW_CAPACITY, 12D);
        Log log = new Log(1, new GregorianCalendar(2019, 1, 1).getTime(),
                new GregorianCalendar(2019, 1, 1).getTime());
        d1.deactivate();
        boolean result = d1.addLog(log);
        assertFalse(result);
    }

    @Test
    void addLogTrue() {
        Dishwasher d1 = new Dishwasher(new DishwasherSpec());
        d1.setAttributeValue(DishwasherSpec.DW_CAPACITY, 12D);
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
        Device device = new WaterHeater(new WaterHeaterSpec());
        Dishwasher d1 = new Dishwasher(new DishwasherSpec());
        d1.setAttributeValue(DishwasherSpec.DW_CAPACITY, 12D);
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
        Dishwasher d1 = new Dishwasher(new DishwasherSpec());
        d1.setAttributeValue(DishwasherSpec.DW_CAPACITY, 12D);
        Log log1 = new Log(56, periodBeginning1, periodEnding1);
        Log log2 = new Log(55, periodBeginning2, periodEnding2);
        d1.addLog(log1);
        d1.addLog(log2);
        double result = d1.getConsumptionWithinGivenInterval(initialTime, finalTime);
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
        Dishwasher d1 = new Dishwasher(new DishwasherSpec());
        d1.setAttributeValue(DishwasherSpec.DW_CAPACITY, 12D);
        Log log1 = new Log(56, periodBeginning1, periodEnding1);
        Log log2 = new Log(55, periodBeginning2, periodEnding2);
        d1.addLog(log1);
        d1.addLog(log2);
        double result = d1.getConsumptionWithinGivenInterval(initialTime, finalTime);
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
        Dishwasher device = new Dishwasher(new DishwasherSpec());
        device.setAttributeValue(DishwasherSpec.DW_CAPACITY, 12D);
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
        Dishwasher device = new Dishwasher(new DishwasherSpec());
        device.setAttributeValue(DishwasherSpec.DW_CAPACITY, 12D);
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
        Dishwasher device = new Dishwasher(new DishwasherSpec());
        device.setAttributeValue(DishwasherSpec.DW_CAPACITY, 12D);
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
    public void getConsumption() {
        Dishwasher d = new Dishwasher(new DishwasherSpec());
        d.setNominalPower(15);
        double expectedResult = 360;
        double result = d.getEnergyConsumption(24);
        assertEquals(expectedResult, result);
    }

    @Test
    public void getConsumptionTimeZero() {
        Dishwasher d = new Dishwasher(new DishwasherSpec());
        d.setNominalPower(15);
        double expectedResult = 0;
        double result = d.getEnergyConsumption(0);
        assertEquals(expectedResult, result);
    }

    @Test
    void isLogListEmpty() {
        //Arrange

        Dishwasher dishwasher = new Dishwasher(new DishwasherSpec());


        //Act

        boolean actualResult1 = dishwasher.isLogListEmpty();

        //Assert

        Assertions.assertTrue(actualResult1);

        //Arrange To Add Log

        Log log = new Log(20, new Date(), new Date());
        dishwasher.addLog(log);

        //Act

        boolean actualResult2 = dishwasher.isLogListEmpty();

        //Assert

        Assertions.assertFalse(actualResult2);
    }

    @Test
    public void hashCodeDummyTest() {
        Dishwasher d1 = new Dishwasher(new DishwasherSpec());
        d1.setName("FridgeTwo");
        d1.setNominalPower(12.0);
        d1.setAttributeValue(DishwasherSpec.DW_CAPACITY, 4D);
        int expectedResult = 1;
        int actualResult = d1.hashCode();
        assertEquals(expectedResult, actualResult);
    }


}
