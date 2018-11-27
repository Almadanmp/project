package Sprint_0;

import org.junit.Test;

import java.util.Date;
import java.util.GregorianCalendar;
/*
import static junit.framework.Assert.assertEquals;

public class AuxiliaryMethods_Test {

    //Funçao ainda nao existente, nome é PLACEHOLDER.
    //1- Haverá à partida uma função que cria arraylist com todos os maximos de cada dia
    //2- Outra que cria com todos os minimos
    //3- Uma ultima que faz a média (seria a maneira ideal).
    //4- Será depois preciso implementar para cada tipo de sensor

    //Testa média de temperatura minima por dia ao longo do mês.
    //Ou seja, pega na minima de cada dia e faz a média para o mês. É suposto dar sempre 10.
    @Test
    public void seeIfGetMaximumValuesMonth() {
        //Arrange
        Sensor c = new Sensor("Vento", new TypeSensor("Atmosphere"), new Local(12, 31, 21), new Date());
        ReadingList rl1 = new ReadingList();
        Reading reading1 = new Reading(10, new GregorianCalendar(118, 11, 1, 0, 0, 0));
        Reading reading2 = new Reading(15, new GregorianCalendar(118, 11, 1, 1, 0, 0));
        Reading reading3 = new Reading(20, new GregorianCalendar(118, 11, 1, 2, 0, 0));
        Reading reading4 = new Reading(10, new GregorianCalendar(118, 11, 5, 1, 0, 0));
        Reading reading5 = new Reading(20, new GregorianCalendar(118, 11, 5, 2, 0, 0));
        Reading reading6 = new Reading(10, new GregorianCalendar(118, 11, 10, 1, 0, 0));
        Reading reading7 = new Reading(20, new GregorianCalendar(118, 11, 10, 2, 0, 0));
        Reading reading8 = new Reading(10, new GregorianCalendar(118, 11, 15, 1, 0, 0));
        Reading reading9 = new Reading(15, new GregorianCalendar(118, 11, 15, 5, 0, 0));
        Reading reading10 = new Reading(20, new GregorianCalendar(118, 11, 15, 20, 0, 0));
        Reading reading11 = new Reading(10, new GregorianCalendar(118, 11, 20, 0, 0, 0));
        Reading reading12 = new Reading(15, new GregorianCalendar(118, 11, 20, 1, 0, 0));
        Reading reading13 = new Reading(20, new GregorianCalendar(118, 11, 20, 2, 0, 0));

        rl1.addReading(reading1);
        rl1.addReading(reading2);
        rl1.addReading(reading3);
        rl1.addReading(reading4);
        rl1.addReading(reading5);
        rl1.addReading(reading6);
        rl1.addReading(reading7);
        rl1.addReading(reading8);
        rl1.addReading(reading9);
        rl1.addReading(reading10);
        rl1.addReading(reading11);
        rl1.addReading(reading12);
        rl1.addReading(reading13);

        //Act
        c.setReadingList(rl1);
        double expectedResult = 10;
        double result = c.getMaxOrMinAveragePLACEHOLDER();

        //Assert
        assertEquals(expectedResult, result);
    }

    //Teste igual mas para máximo, é suposto dar sempre 20.
    @Test
    public void seeIfGetMaximumValuesMonth() {
        //Arrange
        Sensor c = new Sensor("Vento", new TypeSensor("Atmosphere"), new Local(12, 31, 21), new Date());
        ReadingList rl1 = new ReadingList();
        Reading reading1 = new Reading(10, new GregorianCalendar(118, 11, 1, 0, 0, 0));
        Reading reading2 = new Reading(15, new GregorianCalendar(118, 11, 1, 1, 0, 0));
        Reading reading3 = new Reading(20, new GregorianCalendar(118, 11, 1, 2, 0, 0));
        Reading reading4 = new Reading(10, new GregorianCalendar(118, 11, 5, 1, 0, 0));
        Reading reading5 = new Reading(20, new GregorianCalendar(118, 11, 5, 2, 0, 0));
        Reading reading6 = new Reading(10, new GregorianCalendar(118, 11, 10, 1, 0, 0));
        Reading reading7 = new Reading(20, new GregorianCalendar(118, 11, 10, 2, 0, 0));
        Reading reading8 = new Reading(10, new GregorianCalendar(118, 11, 15, 1, 0, 0));
        Reading reading9 = new Reading(15, new GregorianCalendar(118, 11, 15, 5, 0, 0));
        Reading reading10 = new Reading(20, new GregorianCalendar(118, 11, 15, 20, 0, 0));
        Reading reading11 = new Reading(10, new GregorianCalendar(118, 11, 20, 0, 0, 0));
        Reading reading12 = new Reading(15, new GregorianCalendar(118, 11, 20, 1, 0, 0));
        Reading reading13 = new Reading(20, new GregorianCalendar(118, 11, 20, 2, 0, 0));

        rl1.addReading(reading1);
        rl1.addReading(reading2);
        rl1.addReading(reading3);
        rl1.addReading(reading4);
        rl1.addReading(reading5);
        rl1.addReading(reading6);
        rl1.addReading(reading7);
        rl1.addReading(reading8);
        rl1.addReading(reading9);
        rl1.addReading(reading10);
        rl1.addReading(reading11);
        rl1.addReading(reading12);
        rl1.addReading(reading13);

        //Act
        c.setReadingList(rl1);
        double expectedResult = 20;
        double result = c.getMaxOrMinAveragePLACEHOLDER();

        //Assert
        assertEquals(expectedResult, result);
    }

    //Teste para máximo mas com acrescento de dias pertencentes a outro mês.
    //Se resultado não for 20, é porque foram lidas Ventos de meses não supostos.
    @Test
    public void seeIfGetMaximumValuesMonth() {
        //Arrange
        Sensor c = new Sensor("Vento", new TypeSensor("Atmosphere"), new Local(12, 31, 21), new Date());
        ReadingList rl1 = new ReadingList();
        Reading reading1 = new Reading(10, new GregorianCalendar(118, 11, 1, 0, 0, 0));
        Reading reading2 = new Reading(15, new GregorianCalendar(118, 11, 1, 1, 0, 0));
        Reading reading3 = new Reading(20, new GregorianCalendar(118, 11, 1, 2, 0, 0));
        Reading reading4 = new Reading(10, new GregorianCalendar(118, 11, 5, 1, 0, 0));
        Reading reading5 = new Reading(20, new GregorianCalendar(118, 11, 5, 2, 0, 0));
        Reading reading6 = new Reading(10, new GregorianCalendar(118, 11, 10, 1, 0, 0));
        Reading reading7 = new Reading(20, new GregorianCalendar(118, 11, 10, 2, 0, 0));
        Reading reading8 = new Reading(10, new GregorianCalendar(118, 11, 15, 1, 0, 0));
        Reading reading9 = new Reading(15, new GregorianCalendar(118, 11, 15, 5, 0, 0));
        Reading reading10 = new Reading(20, new GregorianCalendar(118, 11, 15, 20, 0, 0));
        Reading reading11 = new Reading(10, new GregorianCalendar(118, 11, 20, 0, 0, 0));
        Reading reading12 = new Reading(15, new GregorianCalendar(118, 11, 20, 1, 0, 0));
        Reading reading13 = new Reading(20, new GregorianCalendar(118, 11, 20, 2, 0, 0));
        Reading reading14 = new Reading(20, new GregorianCalendar(118, 10, 20, 1, 0, 0));
        Reading reading15 = new Reading(40, new GregorianCalendar(118, 10, 20, 2, 0, 0));
        Reading reading16 = new Reading(20, new GregorianCalendar(118, 12, 20, 1, 0, 0));
        Reading reading17 = new Reading(40, new GregorianCalendar(118, 12, 20, 2, 0, 0));

        rl1.addReading(reading1);
        rl1.addReading(reading2);
        rl1.addReading(reading3);
        rl1.addReading(reading4);
        rl1.addReading(reading5);
        rl1.addReading(reading6);
        rl1.addReading(reading7);
        rl1.addReading(reading8);
        rl1.addReading(reading9);
        rl1.addReading(reading10);
        rl1.addReading(reading11);
        rl1.addReading(reading12);
        rl1.addReading(reading13);
        rl1.addReading(reading14);
        rl1.addReading(reading15);
        rl1.addReading(reading16);
        rl1.addReading(reading17);

        //Act
        c.setReadingList(rl1);
        double expectedResult = 20;
        double result = c.getMaxOrMinAveragePLACEHOLDER();

        //Assert
        assertEquals(expectedResult, result);
    }
}
*/