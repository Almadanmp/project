package pt.ipp.isep.dei.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * EnergyGridList tests class.
 */

public class EnergyGridListTest {

    @Test
    void seeIfAddEnergyGridToEnergyGridListTrue() {
        EnergyGridList pList1 = new EnergyGridList();
        EnergyGrid energyGrid1 = new EnergyGrid("Secundary Grid", 0);

        Boolean expectedResult = true;
        Boolean actualResult = pList1.addGrid(energyGrid1);

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfAddEnergyGridToEnergyGridListFalse() {
        EnergyGridList pList1 = new EnergyGridList();
        EnergyGrid energyGrid1 = new EnergyGrid("Secundary Grid", 0);
        EnergyGrid energyGrid2 = new EnergyGrid("Secundary Grid", 0);
        pList1.addGrid(energyGrid1);

        Boolean expectedResult = false;
        Boolean actualResult = pList1.addGrid(energyGrid2);

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void ensureThatAObjectIsAInstanceOf() {
        EnergyGridList pList1 = new EnergyGridList();
        EnergyGrid energyGrid1 = new EnergyGrid("Grid", 0);
        pList1.addGrid(energyGrid1);
        EnergyGridList pList2 = new EnergyGridList();
        EnergyGrid energyGrid2 = new EnergyGrid("Grid", 0);
        pList2.addGrid(energyGrid2);
        Boolean expectedResult = true;

        Boolean actualResult = pList1.equals(pList2);

        Assertions.assertEquals(expectedResult, actualResult);
    }


    @Test
    void ensureThatAObjectIsAInstanceOf2() {
        EnergyGridList pList1 = new EnergyGridList();
        EnergyGrid energyGrid1 = new EnergyGrid("Grid", 0);
        pList1.addGrid(energyGrid1);
        EnergyGridList pList2 = new EnergyGridList();
        pList2.addGrid(energyGrid1);
        Boolean expectedResult = true;

        Boolean actualResult = pList1.equals(pList2);

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void ensureThatAObjectIsAInstanceOf4() {
        EnergyGridList pList1 = new EnergyGridList();
        EnergyGrid energyGrid1 = new EnergyGrid("Grid", 0);
        pList1.addGrid(energyGrid1);
        Boolean expectedResult = true;

        Boolean actualResult = pList1.equals(pList1);

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void ensureThatAObjectIsAInstanceOf3() {
        EnergyGridList pList1 = new EnergyGridList();
        EnergyGrid energyGrid1 = new EnergyGrid("Grid", 0);
        pList1.addGrid(energyGrid1);
        Boolean expectedResult = false;
        Boolean actualResult = pList1.equals(energyGrid1);
        Assertions.assertEquals(expectedResult, actualResult);
    }


    @Test
    void ensureThatAObjectIsNotAInstanceOf() {
        EnergyGridList pList1 = new EnergyGridList();
        EnergyGridList pList2 = new EnergyGridList();
        EnergyGrid energyGrid1 = new EnergyGrid("Secundary Grid", 0);
        EnergyGrid energyGrid2 = new EnergyGrid("Main Grid", 0);
        pList1.addGrid(energyGrid1);
        pList2.addGrid(energyGrid2);
        Boolean expectedResult = false;

        Boolean actualResult = pList1.equals(pList2);

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void hashCodeDummyTest() {
        EnergyGridList pList1 = new EnergyGridList();
        EnergyGrid energyGrid1 = new EnergyGrid("Main Grid", 0);
        pList1.addGrid(energyGrid1);
        int expectedResult = 1;
        int actualResult = pList1.hashCode();
        Assertions.assertEquals(expectedResult, actualResult);
    }
}

