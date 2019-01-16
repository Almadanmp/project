package pt.ipp.isep.dei.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnergyGridListTest {

    @Test
    public void seeIfAddEnergyGridToEnergyGridListTrue() {
        EnergyGridList pList1 = new EnergyGridList();
        EnergyGrid energyGrid1 = new EnergyGrid("Secundary Grid", 0);

        Boolean expectedResult = true;
        Boolean actualResult = pList1.addGrid(energyGrid1);

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfAddEnergyGridToEnergyGridListFalse() {
        EnergyGridList pList1 = new EnergyGridList();
        EnergyGrid energyGrid1 = new EnergyGrid("Secundary Grid", 0);
        EnergyGrid energyGrid2 = new EnergyGrid("Secundary Grid", 0);
        pList1.addGrid(energyGrid1);

        Boolean expectedResult = false;
        Boolean actualResult = pList1.addGrid(energyGrid2);

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfPrintsEnergyGridList() {
        EnergyGridList pList1 = new EnergyGridList();
        EnergyGrid energyGrid1 = new EnergyGrid("Secundary Grid", 0);
        EnergyGrid energyGrid2 = new EnergyGrid("Secundary Grid", 0);
        pList1.addGrid(energyGrid1);
        pList1.addGrid(energyGrid2);
        String expectedResult = "Energy grid list: \n" +
                "-Secundary Grid;";
        String result = pList1.buildEnergyGridListString();
        assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfPrintsInvalidList() {
        EnergyGridList pList1 = new EnergyGridList();

        String expectedResult = "The list is empty.";
        String result = pList1.buildEnergyGridListString();
        assertEquals(expectedResult, result);
    }

    @Test
    public void ensureThatAObjectIsAInstanceOf() {
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
    public void ensureThatAObjectIsAInstanceOf2() {
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
    public void ensureThatAObjectIsAInstanceOf4() {
        EnergyGridList pList1 = new EnergyGridList();
        EnergyGrid energyGrid1 = new EnergyGrid("Grid", 0);
        pList1.addGrid(energyGrid1);
        Boolean expectedResult = true;

        Boolean actualResult = pList1.equals(pList1);

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void ensureThatAObjectIsAInstanceOf3() {
        EnergyGridList pList1 = new EnergyGridList();
        EnergyGrid energyGrid1 = new EnergyGrid("Grid", 0);
        pList1.addGrid(energyGrid1);
        Boolean expectedResult = false;
        Boolean actualResult = pList1.equals(energyGrid1);
        Assertions.assertEquals(expectedResult, actualResult);
    }


    @Test
    public void ensureThatAObjectIsNotAInstanceOf() {
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
    public void hashCodeDummyTest() {
        EnergyGridList pList1 = new EnergyGridList();
        EnergyGrid energyGrid1 = new EnergyGrid("Main Grid", 0);
        pList1.addGrid(energyGrid1);
        int expectedResult = 1;
        int actualResult = pList1.hashCode();
        Assertions.assertEquals(expectedResult, actualResult);
    }
}

