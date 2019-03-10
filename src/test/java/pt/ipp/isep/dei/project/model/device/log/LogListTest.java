package pt.ipp.isep.dei.project.model.device.log;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Room;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ReadingList tests class.
 */

class LogListTest {

    // Common artifacts for testing in this class.

    private Date validDate1; // Date 09/08/2018
    private Date validDate2; // Date 11/02/2014

    private Log validLog1;

    private LogList emptyLogList = new LogList(); // Empty LogList
    private LogList validLogList = new LogList(); // One log

    @BeforeEach

    void arrangeArtifacts() {

        // SimpleDateFormat dd/MM/yyyy HH:mm:ss
        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            validDate1 = validSdf.parse("20/10/2019 10:02:00");
            validDate2 = validSdf.parse("20/10/2018 10:02:00");
        } catch (ParseException c) {
            c.printStackTrace();
        }
        validLog1 = new Log(300, validDate1,validDate2);
        validLogList.addLog(validLog1);
    }

    @Test
    void testForEmptyLogList() {
        //Act

        boolean result = emptyLogList.isEmpty();
        //Assert

        assertTrue(result);
    }

    @Test
    void seeIfEmptyLogListWithLogs() {
        //Act

        boolean result = validLogList.isEmpty();

        //Assert

        assertFalse(result);
    }

    @Test
    void seeEqualToEqualObject() {
        // Act

        LogList list1 = new LogList();
        LogList list2 = new LogList();
        boolean actualResult = list1.equals(list2);

        //Assert

        assertTrue(actualResult);
    }

    @Test
    void seeEqualToSameObject() {
        //Act

        boolean actualResult = emptyLogList.equals(emptyLogList); // Needed for sonarqube testing purposes.

        //Assert

        assertTrue(actualResult);
    }

    @Test
    void seeEqualsToDifObject() {
        //Act

        boolean actualResult = emptyLogList.equals(validLogList);

        //Assert

        assertFalse(actualResult);
    }

    @Test
    void addLog() {
        //Act

        boolean actualResult = emptyLogList.addLog(validLog1);

        //Assert

        assertTrue(actualResult);
    }

    @Test
    void addEmptyLogList() {
        //Act

        boolean actualResult = validLogList.addLogList(emptyLogList);

        //Assert

        assertFalse(actualResult);
    }

    @Test
    void addSameEmptyLogList() {
        //Act

        boolean actualResult = validLogList.addLogList(validLogList);

        //Assert

        assertFalse(actualResult);
    }

    @Test
    void addLogListWithLog() {
        //Act

        boolean actualResult = emptyLogList.addLogList(validLogList);

        //Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfAddsLogTrue() {
        //Act

        boolean actualResult = emptyLogList.addLog(validLog1);

        //Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfAddsLogFalse() {
        //Act

        boolean actualResult = validLogList.addLog(validLog1);

        //Assert

        assertFalse(actualResult);
    }


    @Test
    void seeEqualsToDifTypeObject() {
        //Arrange

        Room room = new Room("quarto", 1, 80, 2, 2);

        //Act

        boolean actualResult = validLogList.equals(room); // Needed for sonarqube testing purposes.

        //Assert

        assertFalse(actualResult);
    }

    @Test
    void hashCodeDummyTest() {
        //Act

        int result = emptyLogList.hashCode();

        //Assert

        assertEquals(result, 1);
    }

    @Test
    void testToStringEmpty() {
        //Act

        String expectedResult = "There's no valid logs within that interval.";
        String actualResult = emptyLogList.toString();

        //Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testToStringWithLogs() {

        //Arrange

        Log log1 = new Log(300, new GregorianCalendar(2018, Calendar.NOVEMBER, 20, 10,
                2).getTime(), new GregorianCalendar(2018, Calendar.NOVEMBER, 20, 10,
                10).getTime());
        Log log2 = new Log(300, new GregorianCalendar(2019, Calendar.NOVEMBER, 20, 10,
                2).getTime(), new GregorianCalendar(2018, Calendar.NOVEMBER, 20, 10,
                10).getTime());
        emptyLogList.addLog(log1);
        emptyLogList.addLog(log2);

        //Act

        String expectedResult = "\n" +
                "0) Start Date: 20/11/2018 10:02:00 | End Date: 20/11/2018 10:10:00 | Value: 300.0\n" +
                "1) Start Date: 20/11/2019 10:02:00 | End Date: 20/11/2018 10:10:00 | Value: 300.0";
        String actualResult = emptyLogList.toString();

        //Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void containsLog() {
        //Arrange

        Date validDate3 = new Date();

        SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            validDate3 = simpleDate.parse("01/05/2018 23:59:59");

        } catch (ParseException c) {
            c.printStackTrace();
        }
        Log log1 = new Log(300, validDate1, validDate2);
        Log log2 = new Log(300, validDate1, validDate2);
        Log log3 = new Log(300, validDate1, validDate3);

        LogList list2 = new LogList(); //SAME LOG
        LogList list3 = new LogList(); //DIFF LOG

        list2.addLog(log1);
        list3.addLog(log1);

        //Act

        boolean actualResult1 = emptyLogList.contains(log1);
        boolean actualResult2 = list2.contains(log2);
        boolean actualResult3 = list3.contains(log3);


        //Assert

        assertFalse(actualResult1);
        assertTrue(actualResult2);
        assertFalse(actualResult3);

    }

    @Test
    void getElementsAsArray() {
        //Arrange

        Date date1 = new Date();

        SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            date1 = simpleDate.parse("01/10/2018 23:30:59");

        } catch (ParseException c) {
            c.printStackTrace();
        }

        Log[] expectedResult1 = new Log[0];
        Log[] expectedResult2 = new Log[1];
        Log[] expectedResult3 = new Log[2];

        LogList validLogList2 = new LogList();
        validLogList2.addLog(validLog1);
        validLogList2.addLog(new Log(220, validDate1, date1));

        expectedResult2[0] = validLog1;
        expectedResult3[0] = validLog1;
        expectedResult3[1] = new Log(220, validDate1, date1);

        //Act

        Log[] actualResult1 = emptyLogList.getElementsAsArray();
        Log[] actualResult2 = validLogList.getElementsAsArray();
        Log[] actualResult3 = validLogList2.getElementsAsArray();

        //Assert

        assertArrayEquals(expectedResult1, actualResult1);
        assertArrayEquals(expectedResult2, actualResult2);
        assertArrayEquals(expectedResult3, actualResult3);
    }
}