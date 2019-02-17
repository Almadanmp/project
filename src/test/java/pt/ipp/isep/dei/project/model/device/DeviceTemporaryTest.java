package pt.ipp.isep.dei.project.model.device;

/**
 * device tests class.
 */

public class DeviceTemporaryTest {

//    @Test
//    public void getDeviceTypeTest() {
//        DeviceTemporary d = new DeviceTemporary(new WashingMachineSpec());
//        d.setAttributeValue("capacity", 12D);
//        String dT = "WashingMachine";
//        String expectedResult = dT;
//        String result = d.getType();
//        assertEquals(expectedResult, result);
//    }
//
//    @Test
//    void seeEqualToSameObject() {
//        DeviceTemporary d = new DeviceTemporary(new WashingMachineSpec());
//        d.setName("WMOne");
//        d.setNominalPower(12.0);
//        d.setAttributeValue("capacity", 12D);
//        boolean actualResult = d.equals(d);
//        assertTrue(actualResult);
//    }
//
//    @Test
//    void seeEqualsToDifObject() {
//        DeviceTemporary d = new DeviceTemporary(new WashingMachineSpec());
//        d.setName("WMOne");
//        d.setNominalPower(12.0);
//        d.setAttributeValue(WashingMachineSpec.WM_CAPACITY, 34);
//        DeviceTemporary d2 = new DeviceTemporary(new WashingMachineSpec());
//        d2.setName("WMTwo");
//        d2.setNominalPower(12.0);
//        d.setAttributeValue(WashingMachineSpec.WM_CAPACITY, 45);
//
//        boolean actualResult = d.equals(d2);
//        assertFalse(actualResult);
//    }
//
//
//    @Test
//    void seeEqualsToDifTypeObject() {
//        DeviceTemporary d = new DeviceTemporary(new WashingMachineSpec());
//        d.setName("WMOne");
//        d.setNominalPower(12.0);
//        d.setAttributeValue(WashingMachineSpec.WM_CAPACITY, 56);
//        Room room = new Room("quarto", 1, 80, 2, 2);
//
//        boolean actualResult = d.equals(room);
//        assertFalse(actualResult);
//    }
//
//    @Test
//    void seeEqualsToNullObject() {
//        DeviceTemporary d = new DeviceTemporary(new WashingMachineSpec());
//        d.setAttributeValue(WashingMachineSpec.WM_CAPACITY, 34);
//        boolean actualResult = d.equals(null);
//
//        assertFalse(actualResult);
//    }
//
//    @Test
//    void seeIfPrintDeviceWorks() {
//        DeviceTemporary d1 = new DeviceTemporary(new FridgeSpec());
//        d1.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 2D);
//        d1.setAttributeValue(FridgeSpec.REFRIGERATOR_CAPACITY, 2D);
//        d1.setAttributeValue(FridgeSpec.ANNUAL_CONSUMPTION, 45D);
//        d1.setName("frigo");
//        d1.setNominalPower(150.0);
//        String result = d1.buildDeviceString();
//        String expectedResult = "The device Name is frigo, and its NominalPower is 150.0 kW.\n";
//        assertEquals(expectedResult, result);
//    }
//
//    @Test
//    void seeIfSetNameWorks() {
//        DeviceTemporary d1 = new DeviceTemporary(new FridgeSpec());
//        d1.setName("frigo");
//        d1.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 2D);
//        d1.setAttributeValue(FridgeSpec.REFRIGERATOR_CAPACITY, 2D);
//        d1.setAttributeValue(FridgeSpec.ANNUAL_CONSUMPTION, 23D);
//        d1.setName("frigorifico");
//        String result = d1.getName();
//        String expectedResult = "frigorifico";
//        assertEquals(expectedResult, result);
//    }
//
//    @Test
//    public void hashCodeDummyTest() {
//        DeviceTemporary d1 = new DeviceTemporary(new FridgeSpec());
//        d1.setName("FridgeTwo");
//        d1.setNominalPower(12.0);
//        d1.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 4D);
//        d1.setAttributeValue(FridgeSpec.REFRIGERATOR_CAPACITY, 56D);
//        d1.setAttributeValue(FridgeSpec.ANNUAL_CONSUMPTION, 345D);
//        int expectedResult = 1;
//        int actualResult = d1.hashCode();
//        assertEquals(expectedResult, actualResult);
//    }
//
//    @Test
//    void seeIfGetAndSetAttributeValue() {
//        DeviceTemporary d1 = new DeviceTemporary(new WaterHeaterSpec());
//        d1.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 12D);
//        d1.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 40D);
//        d1.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 234D);
//        Double expectedResult = 33.3;
//        d1.setAttributeValue("Volume Of Water", 33.3);
//        Object result = d1.getAttributeValue("Volume Of Water");
//        assertEquals(expectedResult, result);
//    }
//
//    @Test
//    void seeIfGetAndSetAttributeUnit() {
//        DeviceTemporary d1 = new DeviceTemporary(new WaterHeaterSpec());
//        d1.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 12D);
//        d1.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 40D);
//        d1.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 234D);
//        String expectedResult = "L";
//        d1.setAttributeValue("Volume Of Water", 33.3);
//        Object result = d1.getAttributeUnit("Volume Of Water");
//        assertEquals(expectedResult, result);
//    }
//
//    @Test
//    void seeIfGetAndSetAttributeUnit2() {
//        DeviceTemporary d1 = new DeviceTemporary(new WaterHeaterSpec());
//        d1.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 12D);
//        d1.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 40D);
//        d1.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 234D);
//        String expectedResult = "ºC";
//        d1.setAttributeValue("Hot Water Temperature", 33.3);
//        Object result = d1.getAttributeUnit("Hot Water Temperature");
//        assertEquals(expectedResult, result);
//    }
//
//    @Test
//    void seeIfGetAndSetAttributeUnit3() {
//        DeviceTemporary d1 = new DeviceTemporary(new WaterHeaterSpec());
//        d1.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 12D);
//        d1.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 40D);
//        d1.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 234D);
//        String expectedResult = "";
//        d1.setAttributeValue("Performance Ratio", 33.3);
//        Object result = d1.getAttributeUnit("Performance Ratio");
//        assertEquals(expectedResult, result);
//    }
//
//    @Test
//    void seeIfGetAndSetAttributeUnit4() {
//        DeviceTemporary d1 = new DeviceTemporary(new WaterHeaterSpec());
//        d1.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 12D);
//        d1.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 40D);
//        d1.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 234D);
//        d1.setAttributeValue(WaterHeaterSpec.NOMINAL_POWER, 234D);
//        String expectedResult = "kW";
//        d1.setAttributeValue("nominal power", 33.3);
//        Object result = d1.getAttributeUnit("nominal power");
//        assertEquals(expectedResult, result);
//    }
//
//    @Test
//    void seeIfGetAttributeNames() {
//        DeviceTemporary d1 = new DeviceTemporary(new WaterHeaterSpec());
//        d1.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 12D);
//        d1.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 40D);
//        d1.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 234D);
//        d1.setAttributeValue(WaterHeaterSpec.NOMINAL_POWER, 234D);
//        List<String> result = d1.getAttributeNames();
//        assertTrue(result.contains(WaterHeaterSpec.VOLUME_OF_WATER));
//        assertTrue(result.contains(WaterHeaterSpec.HOT_WATER_TEMP));
//        assertTrue(result.contains(WaterHeaterSpec.PERFORMANCE_RATIO));
//        assertTrue(result.contains(WaterHeaterSpec.NOMINAL_POWER));
//        assertEquals(result.size(), 4);
//    }
//
//    @Test
//    void ensureThatWeDeactivateADevice() {
//        DeviceTemporary d1 = new DeviceTemporary(new WaterHeaterSpec());
//        d1.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 12D);
//        d1.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 40D);
//        d1.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 234D);
//        boolean expectedResult = true;
//        boolean actualResult = d1.deactivate();
//        assertEquals(expectedResult, actualResult);
//    }
//
//    @Test
//    void ensureThatWeDoNotDeactivate() {
//        DeviceTemporary d1 = new DeviceTemporary(new WaterHeaterSpec());
//        d1.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 12D);
//        d1.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 40D);
//        d1.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 234D);
//        d1.deactivate();
//        boolean expectedResult = false;
//        boolean actualResult = d1.deactivate();
//        assertEquals(expectedResult, actualResult);
//    }
//
//    @Test
//    void ensureThatDeviceIsNotProg() {
//        DeviceTemporary d1 = new DeviceTemporary(new WaterHeaterSpec());
//        d1.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 12D);
//        d1.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 40D);
//        d1.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 234D);
//        d1.deactivate();
//        boolean expectedResult = false;
//        boolean actualResult = d1.isProgrammable();
//        assertEquals(expectedResult, actualResult);
//    }
//
//    @Test
//    void ensureThatDeviceIsProg() {
//        DeviceTemporary d1 = new DeviceTemporary(new WashingMachineSpec());
//        d1.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 12D);
//        d1.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 40D);
//        d1.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 234D);
//        d1.deactivate();
//        boolean expectedResult = true;
//        boolean actualResult = d1.isProgrammable();
//        assertEquals(expectedResult, actualResult);
//    }
//
//    @Test
//    void seeIfGetEnergyConsumption() {
//        DeviceTemporary d1 = new DeviceTemporary(new WaterHeaterSpec());
//        d1.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 12D);
//        d1.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 40D);
//        d1.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 234D);
//        d1.deactivate();
//        double expectedResult = 0;
//        double actualResult = d1.getEnergyConsumption(2);
//        assertEquals(expectedResult, actualResult);
//    }
//
//    @Test
//    void getProgramList() {
//        DeviceTemporary d1 = new DeviceTemporary(new WashingMachineSpec());
//        Program program1 = new Program("programa", 2, 2);
//        ProgramList listProgram = d1.getProgramList();
//        listProgram.addProgram(program1);
//        ProgramList result = d1.getProgramList();
//        assertEquals(listProgram, result);
//    }
//
//    @Test
//    void getProgramListFalse() {
//
//        assertThrows(IncompatibleClassChangeError.class,
//                () -> {
//                    DeviceTemporary d1 = new DeviceTemporary(new WaterHeaterSpec());
//                    d1.getProgramList();
//                });
//    }
//
//    @Test
//    void getLogList() {
//        DeviceTemporary d1 = new DeviceTemporary(new WaterHeaterSpec());
//        Log log = new Log(1, new GregorianCalendar(2019, 1, 1).getTime(),
//                new GregorianCalendar(2019, 1, 1).getTime());
//        LogList logList = d1.getLogList();
//        d1.addLog(log);
//        LogList result = d1.getLogList();
//        assertEquals(logList, result);
//    }
//
//    @Test
//    void getLogListBreakTest() {
//        DeviceTemporary d1 = new DeviceTemporary(new WaterHeaterSpec());
//        LogList logList = new LogList();
//        LogList result = d1.getLogList();
//        assertEquals(logList, result);
//    }
//
//    @Test
//    void addLogListFalse() {
//        DeviceTemporary d1 = new DeviceTemporary(new WaterHeaterSpec());
//        Log log = new Log(1, new GregorianCalendar(2019, 1, 1).getTime(),
//                new GregorianCalendar(2019, 1, 1).getTime());
//        d1.addLog(log);
//        assertFalse(d1.addLog(log));
//    }
//
//    @Test
//    void addLogToInactive() {
//        DeviceTemporary d1 = new DeviceTemporary(new WaterHeaterSpec());
//        Log log = new Log(1, new GregorianCalendar(2019, 1, 1).getTime(),
//                new GregorianCalendar(2019, 1, 1).getTime());
//        d1.deactivate();
//        boolean result = d1.addLog(log);
//        assertFalse(result);
//    }
//
//    @Test
//    void addLogTrue() {
//        DeviceTemporary d1 = new DeviceTemporary(new WaterHeaterSpec());
//        Log log = new Log(1, new GregorianCalendar(2019, 1, 1).getTime(),
//                new GregorianCalendar(2019, 1, 1).getTime());
//        boolean result = d1.addLog(log);
//        assertTrue(result);
//    }
//
//    @Test
//    void getTotalMeteredEnergyConsumptionInDeviceWithinGivenTimeIntervalEquals() {
//        Date initialTime = new GregorianCalendar(2018, 10, 20, 10, 0).getTime();
//        Date finalTime = new GregorianCalendar(2018, 10, 20, 11, 0).getTime();
//        Date periodBeginning1 = new GregorianCalendar(2018, 10, 20, 10, 0).getTime();
//        Date periodEnding1 = new GregorianCalendar(2018, 10, 20, 10, 20).getTime();
//        Date periodBeginning2 = new GregorianCalendar(2018, 10, 20, 10, 30).getTime();
//        Date periodEnding2 = new GregorianCalendar(2018, 10, 20, 11, 0).getTime();
//        DeviceTemporary device = new DeviceTemporary(new WaterHeaterSpec());
//        device.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 400D);
//        device.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 400D);
//        device.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 0.9D);
//        Log log1 = new Log(56, periodBeginning1, periodEnding1);
//        Log log2 = new Log(55, periodBeginning2, periodEnding2);
//        device.addLog(log1);
//        device.addLog(log2);
//        double result = device.getConsumptionWithinGivenInterval(initialTime, finalTime);
//        assertEquals(111, result);
//    }
//
//    @Test
//    void getTotalMeteredEnergyConsumptionInDeviceWithinGivenTimeIntervalAfterBefore() {
//        Date initialTime = new GregorianCalendar(2018, 10, 20, 10, 0).getTime();
//        Date finalTime = new GregorianCalendar(2018, 10, 20, 11, 0).getTime();
//        Date periodBeginning1 = new GregorianCalendar(2018, 10, 20, 10, 1).getTime();
//        Date periodEnding1 = new GregorianCalendar(2018, 10, 20, 10, 20).getTime();
//        Date periodBeginning2 = new GregorianCalendar(2018, 10, 20, 10, 30).getTime();
//        Date periodEnding2 = new GregorianCalendar(2018, 10, 20, 10, 59).getTime();
//        DeviceTemporary device = new DeviceTemporary(new WaterHeaterSpec());
//        device.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 400D);
//        device.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 400D);
//        device.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 0.9D);
//        Log log1 = new Log(56, periodBeginning1, periodEnding1);
//        Log log2 = new Log(55, periodBeginning2, periodEnding2);
//        device.addLog(log1);
//        device.addLog(log2);
//        double result = device.getConsumptionWithinGivenInterval(initialTime, finalTime);
//        assertEquals(111, result);
//    }
//
//    @Test
//    void getTotalMeteredEnergyConsumptionInDeviceWithinGivenTimeIntervalAfterBeforeReverseOutOfBounds() {
//        Date initialTime = new GregorianCalendar(2018, 10, 20, 10, 0).getTime();
//        Date finalTime = new GregorianCalendar(2018, 10, 20, 11, 0).getTime();
//        Date periodBeginning1 = new GregorianCalendar(2018, 10, 20, 9, 0).getTime();
//        Date periodEnding1 = new GregorianCalendar(2018, 10, 20, 10, 20).getTime();
//        Date periodBeginning2 = new GregorianCalendar(2018, 10, 20, 10, 30).getTime();
//        Date periodEnding2 = new GregorianCalendar(2018, 10, 20, 11, 20).getTime();
//        DeviceTemporary device = new DeviceTemporary(new WaterHeaterSpec());
//        device.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 400D);
//        device.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 400D);
//        device.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 0.9D);
//        Log log1 = new Log(56, periodBeginning1, periodEnding1);
//        Log log2 = new Log(55, periodBeginning2, periodEnding2);
//        device.addLog(log1);
//        device.addLog(log2);
//        double result = device.getConsumptionWithinGivenInterval(initialTime, finalTime);
//        assertEquals(0.0, result);
//    }
//
//    @Test
//    void testCountLogsInInterval() {
//        Date initialTime = new GregorianCalendar(2018, 10, 20, 10, 0).getTime();
//        Date finalTime = new GregorianCalendar(2018, 10, 20, 11, 0).getTime();
//        Date periodBeginning1 = new GregorianCalendar(2018, 10, 20, 10, 0).getTime();
//        Date periodEnding1 = new GregorianCalendar(2018, 10, 20, 10, 20).getTime();
//        Date periodBeginning2 = new GregorianCalendar(2018, 10, 20, 10, 20).getTime();
//        Date periodEnding2 = new GregorianCalendar(2018, 10, 20, 10, 40).getTime();
//        Date periodBeginning3 = new GregorianCalendar(2018, 10, 20, 10, 40).getTime();
//        Date periodEnding3 = new GregorianCalendar(2018, 10, 20, 11, 0).getTime();
//        DeviceTemporary device = new DeviceTemporary(new WaterHeaterSpec());
//        device.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 400D);
//        device.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 400D);
//        device.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 0.9D);
//        Log log1 = new Log(56, periodBeginning1, periodEnding1);
//        Log log2 = new Log(55, periodBeginning2, periodEnding2);
//        Log log3 = new Log(55, periodBeginning3, periodEnding3);
//        device.addLog(log1);
//        device.addLog(log2);
//        device.addLog(log3);
//        //Act
//        Integer expectedResult = 3;
//        Integer actualResult = device.countLogsInInterval(initialTime, finalTime);
//        //Assert
//        assertEquals(expectedResult, actualResult);
//    }
//
//    @Test
//    void testGetLogsInInterval() {
//        Date initialTime = new GregorianCalendar(2018, 10, 20, 10, 0).getTime();
//        Date finalTime = new GregorianCalendar(2018, 10, 20, 11, 0).getTime();
//        Date periodBeginning1 = new GregorianCalendar(2018, 10, 20, 10, 0).getTime();
//        Date periodEnding1 = new GregorianCalendar(2018, 10, 20, 10, 20).getTime();
//        Date periodBeginning2 = new GregorianCalendar(2018, 10, 20, 10, 20).getTime();
//        Date periodEnding2 = new GregorianCalendar(2018, 10, 20, 10, 40).getTime();
//        Date periodBeginning3 = new GregorianCalendar(2018, 10, 20, 10, 40).getTime();
//        Date periodEnding3 = new GregorianCalendar(2018, 10, 20, 11, 0).getTime();
//        Date periodBeginning4 = new GregorianCalendar(2018, 10, 20, 11, 0).getTime();
//        Date periodEnding4 = new GregorianCalendar(2018, 10, 20, 11, 20).getTime();
//        Date periodBeginning5 = new GregorianCalendar(2018, 10, 20, 9, 40).getTime();
//        Date periodEnding5 = new GregorianCalendar(2018, 10, 20, 10, 0).getTime();
//        DeviceTemporary device = new DeviceTemporary(new WaterHeaterSpec());
//        device.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 400D);
//        device.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 400D);
//        device.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 0.9D);
//        Log log1 = new Log(56, periodBeginning1, periodEnding1);
//        Log log2 = new Log(55, periodBeginning2, periodEnding2);
//        Log log3 = new Log(55, periodBeginning3, periodEnding3);
//        Log log4 = new Log(55, periodBeginning4, periodEnding4);
//        Log log5 = new Log(55, periodBeginning5, periodEnding5);
//        device.addLog(log1);
//        device.addLog(log2);
//        device.addLog(log3);
//        device.addLog(log4);
//        device.addLog(log5);
//        //Act
//        LogList expectedResult = new LogList();
//        expectedResult.addLog(log1);
//        expectedResult.addLog(log2);
//        expectedResult.addLog(log3);
//        LogList actualResult = device.getLogsInInterval(initialTime, finalTime);
//        //Assert
//        assertEquals(expectedResult, actualResult);
//    }
//
//    @Test
//    void testGetLogsInIntervalOutOfBounds() {
//        Date initialTime = new GregorianCalendar(2018, 10, 20, 10, 0).getTime();
//        Date finalTime = new GregorianCalendar(2018, 10, 20, 11, 0).getTime();
//        Date periodBeginning1 = new GregorianCalendar(2018, 10, 20, 9, 50).getTime();
//        Date periodEnding1 = new GregorianCalendar(2018, 10, 20, 10, 10).getTime();
//        Date periodBeginning2 = new GregorianCalendar(2018, 10, 20, 10, 50).getTime();
//        Date periodEnding2 = new GregorianCalendar(2018, 10, 20, 11, 10).getTime();
//        DeviceTemporary device = new DeviceTemporary(new WaterHeaterSpec());
//        device.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 400D);
//        device.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 400D);
//        device.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 0.9D);
//        Log log1 = new Log(56, periodBeginning1, periodEnding1);
//        Log log2 = new Log(55, periodBeginning2, periodEnding2);
//        device.addLog(log1);
//        device.addLog(log2);
//        //Act
//        LogList expectedResult = new LogList();
//        LogList actualResult = device.getLogsInInterval(initialTime, finalTime);
//        //Assert
//        assertEquals(expectedResult, actualResult);
//    }
}
