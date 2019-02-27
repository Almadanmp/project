package pt.ipp.isep.dei.project.model.device.log;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Room;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ReadingList tests class.
 */

class LogListTest {

    // Common artifacts for testing in this class.

    private SimpleDateFormat validSdf; // SimpleDateFormat dd/MM/yyyy HH:mm:ss
    private Date validDate1; // Date 09/08/2018
    private Date validDate2; // Date 11/02/2014

    private Log validLog1;

    private LogList validLogList1 = new LogList(); // Empty LogList
    private LogList validLogList2 = new LogList(); // One log

    @BeforeEach

    void arrangeArtifacts() {

        validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            validDate1 = validSdf.parse("20/10/2019 10:02:00");
            validDate2 = validSdf.parse("20/10/2018 10:02:00");
        } catch (ParseException c) {
            c.printStackTrace();
        }
        validLog1 = new Log(300, validDate1,validDate2);
        validLogList2.addLog(validLog1);
    }

    @Test
    void testForEmptyLogList() {
        //Act

        boolean result = validLogList1.isEmpty();
        //Assert

        assertTrue(result);
    }

    @Test
    void seeIfEmptyLogListWithLogs() {
        //Act

        boolean result = validLogList2.isEmpty();
        //Assert

        assertFalse(result);
    }

    @Test
    void seeEqualToEqualObject() {
        //Act

        LogList list1 = new LogList();
        LogList list2 = new LogList();
        boolean actualResult = list1.equals(list2);
        //Assert

        assertTrue(actualResult);
    }

    @Test
    void seeEqualToSameObject() {
        //Act

        boolean actualResult = validLogList1.equals(validLogList1);

        //Assert

        assertTrue(actualResult);
    }

    @Test
    void seeEqualsToDifObject() {
        //Act

        boolean actualResult = validLogList1.equals(validLogList2);

        //Assert

        assertFalse(actualResult);
    }

    @Test
    void addLog() {
        //Act

        boolean actualResult = validLogList1.addLog(validLog1);

        //Assert

        assertTrue(actualResult);
    }

    @Test
    void addEmptyLogList() {
        //Act

        boolean actualResult = validLogList2.addLogList(validLogList1);

        //Assert

        assertFalse(actualResult);
    }

    @Test
    void addSameEmptyLogList() {
        //Act

        boolean actualResult = validLogList2.addLogList(validLogList2);

        //Assert

        assertFalse(actualResult);
    }

    @Test
    void addLogListWithLog() {
        //Act

        boolean actualResult = validLogList1.addLogList(validLogList2);

        //Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfAddsLogTrue() {
        //Act

        boolean actualResult = validLogList1.addLog(validLog1);

        //Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfAddsLogFalse() {
        //Act

        boolean actualResult = validLogList2.addLog(validLog1);

        //Assert

        assertFalse(actualResult);
    }


    @Test
    void seeEqualsToDifTypeObject() {
        //Arrange

        Room room = new Room("quarto", 1, 80, 2, 2);

        //Act

        boolean actualResult = validLogList2.equals(room);

        //Assert

        assertFalse(actualResult);
    }

    @Test
    void hashCodeDummyTest() {
        //Act

        int result = validLogList1.hashCode();

        //Assert

        assertEquals(result, 1);
    }

    @Test
    void testToStringEmpty() {
        //Act

        String expectedResult = "There's no valid logs within that interval.";
        String actualResult = validLogList1.toString();

        //Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testToStringWithLogs() {

        //Arrange

        Log log1 = new Log(300, new GregorianCalendar(2018, 10, 20, 10, 2).getTime(), new GregorianCalendar(2018, 10, 20, 10, 10).getTime());
        Log log2 = new Log(300, new GregorianCalendar(2019, 10, 20, 10, 2).getTime(), new GregorianCalendar(2018, 10, 20, 10, 10).getTime());
        validLogList1.addLog(log1);
        validLogList1.addLog(log2);

        //Act

        String expectedResult = "\n" +
                "0) Start Date: 20/11/2018 10:02:00 | End Date: 20/11/2018 10:10:00 | Value: 300.0\n" +
                "1) Start Date: 20/11/2019 10:02:00 | End Date: 20/11/2018 10:10:00 | Value: 300.0";
        String actualResult = validLogList1.toString();

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

        boolean actualResult1 = validLogList1.contains(log1);
        boolean actualResult2 = list2.contains(log2);
        boolean actualResult3 = list3.contains(log3);


        //Assert

        assertFalse(actualResult1);
        assertTrue(actualResult2);
        assertFalse(actualResult3);

    }
}