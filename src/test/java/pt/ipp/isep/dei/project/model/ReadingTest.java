package pt.ipp.isep.dei.project.model;


import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Reading tests class.
 */

class ReadingTest{

    @Test
    void getmDateTest() {
        //Arrange
        Date date1 = new Date();
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            date1 = s.parse("11/05/110 15:30:26");
        }
        catch (ParseException c){
            c.printStackTrace();
        }
        Reading l1 = new Reading(15.0,date1);
        Date expectedResult = date1;
        //Act
        Date result =l1.getmDate();
        //Assert
        assertEquals(expectedResult,result);
    }

    @Test
    void setDateSeeIfItWorks() {
        //Arrange
        Date date1 = new Date();
        Date date2 = new Date();
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            date1 = s.parse("11/05/110 15:30:26");
            date2 = s.parse("11/05/118 15:30:26");
        }
        catch (ParseException c){
            c.printStackTrace();
        }
        Reading l1 = new Reading(15,date1);
        l1.setData(date2);
        Date expectedResult = date2;
        //Act
        Date result = l1.getmDate();
        //Assert
        assertEquals(expectedResult,result);
    }
    @Test
    void getmValueTest() {
        //Arrange
        double valor1 = 15.0;
        Date date1 = new Date();
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
        try {
            date1 = s.parse("30/12/2018");
        }
        catch (ParseException c){
            c.printStackTrace();
        }
        Reading l1 = new Reading(valor1,date1);
        //Act
        double result =l1.getmValue();
        //Assert
        assertEquals(valor1,result,0.01);
    }

    @Test
    void setValueSeeIfItWorks() {
        //Arrange
        double valor1 = 15.0;
        Date date1 = new Date();
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
        try {
            date1 = s.parse("30/12/2018");
        }
        catch (ParseException c){
            c.printStackTrace();
        }
        Reading l1 = new Reading(valor1,date1);
        double valor2 = 18.0;
        l1.setmValue(valor2);
        //Act
        double result = l1.getmValue();
        //Assert
        assertEquals(valor2,result,0.01);
    }
    @Test
    void testSetEGetValueOfReading(){
        //Arrange
        Date date1 = new Date();
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
        try {
            date1 = s.parse("26/10/118");
        }
        catch (ParseException c){
            c.printStackTrace();
        }
        ReadingList listal = new ReadingList();
        Reading leitura1 = new Reading(15,date1);
        listal.addReading(leitura1);
        leitura1.setmValue(19);
        double expectedResult = 19;
        //Act
        double result = leitura1.getmValue();
        //Assert
        assertEquals(result,expectedResult,0.01);
    }


    @Test
    void testEqualsReturnFalse(){
        //Arrange
        Date date1 = new Date();
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
        try {
            date1 = s.parse("12/10/110");
        }
        catch (ParseException c){
            c.printStackTrace();
        }
        Reading r1 = new Reading(15,date1);
        int i1 = 0;
        boolean result = r1.equals(i1);
        boolean expectedResult = false;
        assertEquals(result,expectedResult);
    }

    @Test
    void testEqualsReturnTrue(){
        //Arrange
        Date date1 = new Date();
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
        try {
            date1 = s.parse("12/10/110");
        }
        catch (ParseException c){
            c.printStackTrace();
        }
        Reading r1 = new Reading(15,date1);
        Reading r2 = new Reading(15,date1);
        boolean result = r1.equals(r2);
        boolean expectedResult = true;
        assertEquals(result,expectedResult);
    }

    @Test
    void hashCodeDummyTest(){
        Date date1 = new Date();
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            date1 = s.parse("03/09/2010 05:06:07");
        }
        catch (ParseException c){
            c.printStackTrace();
        }
        Reading r1 = new Reading(2,date1);
        int expectedResult = 1;
        int actualResult = r1.hashCode();
        assertEquals(expectedResult,actualResult);
    }
}