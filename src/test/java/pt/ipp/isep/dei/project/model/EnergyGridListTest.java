package pt.ipp.isep.dei.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EnergyGridListTest {

    @Test
    public void seeIfAddEnergyGridToEnergyGridListTrue() {
        EnergyGridList pList1 = new EnergyGridList();
        EnergyGrid energyGrid1 = new EnergyGrid("Secundary Grid", 0);

        Boolean expectedResult = true;
        Boolean actualResult = pList1.addEnergyGridToEnergyGridList(energyGrid1);

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfAddEnergyGridToEnergyGridListFalse() {
        EnergyGridList pList1 = new EnergyGridList();
        EnergyGrid energyGrid1 = new EnergyGrid("Secundary Grid", 0);
        EnergyGrid energyGrid2 = new EnergyGrid("Secundary Grid", 0);
        pList1.addEnergyGridToEnergyGridList(energyGrid1);

        Boolean expectedResult = false;
        Boolean actualResult = pList1.addEnergyGridToEnergyGridList(energyGrid2);

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void ensureThatAObjectIsAInstanceOf() {
        PowerSourceList pList1 = new PowerSourceList();
        PowerSourceList pList2 = new PowerSourceList();
        Boolean expectedResult = true;

        Boolean actualResult = pList1.equals(pList2);

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void ensureThatAObjectIsNotAInstanceOf() {
        EnergyGridList pList1 = new EnergyGridList();
        EnergyGridList pList2 = new EnergyGridList();
        EnergyGrid energyGrid1 = new EnergyGrid("Secundary Grid", 0);
        EnergyGrid energyGrid2 = new EnergyGrid("Main Grid", 0);
        pList1.addEnergyGridToEnergyGridList(energyGrid1);
        pList2.addEnergyGridToEnergyGridList(energyGrid2);
        Boolean expectedResult = false;

        Boolean actualResult = pList1.equals(pList2);

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void hashCodeDummyTest() {
        EnergyGridList pList1 = new EnergyGridList();
        EnergyGrid energyGrid1 = new EnergyGrid("Main Grid", 0);
        pList1.addEnergyGridToEnergyGridList(energyGrid1);
        int expectedResult = 1;
        int actualResult = pList1.hashCode();
        Assertions.assertEquals(expectedResult, actualResult);
    }
}

