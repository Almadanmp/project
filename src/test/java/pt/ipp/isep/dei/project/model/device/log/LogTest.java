package pt.ipp.isep.dei.project.model.device.log;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * ReadingList tests class.
 */

class LogTest {

    // Common artifacts for testing in this class.

    private SimpleDateFormat validSdf; // SimpleDateFormat dd/MM/yyyy HH:mm:ss
    private Date validDate1; // Date 09/08/2018
    private Date validDate2; // Date 11/02/2014

    private Log validLog1;

    @BeforeEach
    void arrangeArtifacts() {

        validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            validDate1 = validSdf.parse("01/05/2018 00:00:00");
            validDate2 = validSdf.parse("01/06/2018 12:30:00");
        } catch (ParseException c) {
            c.printStackTrace();
        }
        validLog1 = new Log(300, validDate1, validDate2);
    }

    @Test
    void getValueTest() {
        //Act

        double result = validLog1.getValue();

        //Assert

        assertEquals(300, result);
    }

    @Test
    void getInitialDateTest() {
        //Act

        Date result = validLog1.getInitialDate();

        //Assert

        assertEquals(validDate1, result);
    }

    @Test
    void getFinalDateTest() {
        //Act

        Date result = validLog1.getFinalDate();

        //Assert

        assertEquals(validDate2, result);
    }

    @Test
    void isLogInInterval() {
        //Arrange

        Date validDate1 = new Date();
        Date validDate2 = new Date();
        Date validDate3 = new Date();
        Date validDate4 = new Date();
        Date validDate5 = new Date();
        Date validDate6 = new Date();

        SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            validDate1 = simpleDate.parse("01/03/2018 12:30:02");
            validDate2 = simpleDate.parse("01/04/2018 23:59:59");
            validDate3 = simpleDate.parse("01/05/2018 00:00:01");
            validDate4 = simpleDate.parse("01/06/2018 12:29:59");
            validDate5 = simpleDate.parse("01/06/2018 12:30:01");
            validDate6 = simpleDate.parse("01/08/2018 12:30:00");

        } catch (ParseException c) {
            c.printStackTrace();
        }

        Log log1 = new Log(200, validDate1, validDate6); // out of limits
        Log log2 = new Log(500, validDate2, validDate5); // outside limits
        Log log3 = new Log(500, this.validDate1, this.validDate2); // inside limits
        Log log4 = new Log(300, validDate3, validDate4); // inside of limits
        Log log5 = new Log(400, this.validDate1, validDate5); // start inside, end outside
        Log log6 = new Log(100, validDate2, this.validDate2); // start outside, end inside

        //Act

        boolean actualResult1 = log1.isLogInInterval(this.validDate1, this.validDate2);
        boolean actualResult2 = log2.isLogInInterval(this.validDate1, this.validDate2);
        boolean actualResult3 = log3.isLogInInterval(this.validDate1, this.validDate2);
        boolean actualResult4 = log4.isLogInInterval(this.validDate1, this.validDate2);
        boolean actualResult5 = log5.isLogInInterval(this.validDate1, this.validDate2);
        boolean actualResult6 = log6.isLogInInterval(this.validDate1, this.validDate2);

        //Assert

        assertFalse(actualResult1);
        assertFalse(actualResult2);
        assertTrue(actualResult3);
        assertTrue(actualResult4);
        assertFalse(actualResult5);
        assertFalse(actualResult6);
    }

    @Test
    void equals() {
        //Arrange

        Date validDate4 = new Date();

        SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            validDate4 = simpleDate.parse("01/03/2018 12:30:02");
            validDate4 = simpleDate.parse("01/05/2018 23:59:59");

        } catch (ParseException c) {
            c.printStackTrace();
        }
        Log log2 = new Log(300, this.validDate1, this.validDate2);
        Log log3 = new Log(300, validDate4, validDate4);

        //Act

        boolean actualResult1 = validLog1.equals(validLog1);
        boolean actualResult2 = validLog1.equals(log2);
        boolean actualResult3 = validLog1.equals(log3);
        boolean actualResult4 = validLog1.equals(2D);

        //Assert

        assertTrue(actualResult1);
        assertTrue(actualResult2);
        assertFalse(actualResult3);
        assertFalse(actualResult4);
    }

    @Test
    void hashcode() {
        //Act

        int actualResult = validLog1.hashCode();

        //Assert

        assertEquals(1, actualResult);
    }
}