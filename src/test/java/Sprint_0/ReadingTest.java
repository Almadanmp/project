package Sprint_0;

import org.junit.Test;

import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

public class ReadingTest{
    @Test
    public void getmDateTest() {
        GregorianCalendar date1 = new GregorianCalendar(110,5,11,15,30,26);
        Reading l1 = new Reading(15.0,date1);
        GregorianCalendar result =l1.getmDate();
        GregorianCalendar expectedResult = date1;
        assertEquals(expectedResult,result);
    }

    @Test
    public void setDateSeeIfItWorks() {
        GregorianCalendar date1 = new GregorianCalendar(110,5,11,15,30,26);
        Reading l1 = new Reading(15,date1);
        GregorianCalendar date2 = new GregorianCalendar(118,5,11,15,30,26);
        l1.setData(date2);
        GregorianCalendar expectedResult = date2;
        GregorianCalendar result = l1.getmDate();
        assertEquals(expectedResult,result);
    }
    @Test
    public void getmValueTest() {
        double valor1 = 15.0;
        Reading l1 = new Reading(valor1);
        double result =l1.getmValue();
        double expectedResult = valor1;
        assertEquals(expectedResult,result,0.01);
    }

    @Test
    public void setValueSeeIfItWorks() {
        double valor1 = 15.0;
        Reading l1 = new Reading(valor1);
        double valor2 = 18.0;
        l1.setmValue(valor2);
        double expectedResult = valor2;
        double result = l1.getmValue();
        assertEquals(expectedResult,result,0.01);
    }
    @Test
    public void testSetEGetValueOfReading(){
        GregorianCalendar date1 = new GregorianCalendar(118,10,26);
        ReadingList listal = new ReadingList();
        Reading leitura1 = new Reading(15,date1);
        listal.addReading(leitura1);
        leitura1.setmValue(19);
        double result = leitura1.getmValue();
        double expectedResult = 19;
        assertEquals(result,expectedResult,0.01);
    }


}