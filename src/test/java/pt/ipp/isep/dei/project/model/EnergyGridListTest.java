package pt.ipp.isep.dei.project.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


/**
 * EnergyGridList tests class.
 */

class EnergyGridListTest {

    @Test
    void seeIfAddEnergyGridToEnergyGridListTrue() {
        EnergyGridList pList1 = new EnergyGridList();
        EnergyGrid energyGrid1 = new EnergyGrid("Secundary Grid", 0);
        Boolean actualResult = pList1.addGrid(energyGrid1);

        assertEquals(true, actualResult);
    }

    @Test
    void seeIfAddEnergyGridToEnergyGridListFalse() {
        EnergyGridList pList1 = new EnergyGridList();
        EnergyGrid energyGrid1 = new EnergyGrid("Secundary Grid", 0);
        EnergyGrid energyGrid2 = new EnergyGrid("Secundary Grid", 0);
        pList1.addGrid(energyGrid1);

        Boolean actualResult = pList1.addGrid(energyGrid2);

        assertEquals(false, actualResult);
    }

    @Test
    void ensureThatAObjectIsAInstanceOf() {
        EnergyGridList pList1 = new EnergyGridList();
        EnergyGrid energyGrid1 = new EnergyGrid("Grid", 0);
        pList1.addGrid(energyGrid1);
        EnergyGridList pList2 = new EnergyGridList();
        EnergyGrid energyGrid2 = new EnergyGrid("Grid", 0);
        pList2.addGrid(energyGrid2);

        Boolean actualResult = pList1.equals(pList2);

        assertEquals(true, actualResult);
    }


    @Test
    void ensureThatAObjectIsAInstanceOf2() {
        EnergyGridList pList1 = new EnergyGridList();
        EnergyGrid energyGrid1 = new EnergyGrid("Grid", 0);
        pList1.addGrid(energyGrid1);
        EnergyGridList pList2 = new EnergyGridList();
        pList2.addGrid(energyGrid1);

        Boolean actualResult = pList1.equals(pList2);

        assertEquals(true, actualResult);
    }

    @Test
    void ensureThatAObjectIsAInstanceOf4() {
        EnergyGridList pList1 = new EnergyGridList();
        EnergyGrid energyGrid1 = new EnergyGrid("Grid", 0);
        pList1.addGrid(energyGrid1);

        Boolean actualResult = pList1.equals(pList1);

        assertEquals(true, actualResult);
    }

    @Test
    void ensureThatAObjectIsAInstanceOf3() {
        EnergyGridList pList1 = new EnergyGridList();
        EnergyGrid energyGrid1 = new EnergyGrid("Grid", 0);
        pList1.addGrid(energyGrid1);
        Boolean actualResult = pList1.equals(energyGrid1);
        assertEquals(false, actualResult);
    }


    @Test
    void ensureThatAObjectIsNotAInstanceOf() {
        EnergyGridList pList1 = new EnergyGridList();
        EnergyGridList pList2 = new EnergyGridList();
        EnergyGrid energyGrid1 = new EnergyGrid("Secundary Grid", 0);
        EnergyGrid energyGrid2 = new EnergyGrid("Main Grid", 0);
        pList1.addGrid(energyGrid1);
        pList2.addGrid(energyGrid2);

        Boolean actualResult = pList1.equals(pList2);

        assertFalse(actualResult);
    }

    @Test
    void isEmpty() {
        //Arrange
        EnergyGridList list1 = new EnergyGridList(); //EMPTY LIST
        EnergyGridList list2 = new EnergyGridList(); //ONE ENERGY GRID
        EnergyGridList list3 = new EnergyGridList(); //TWO ENERGY GRIDS

        EnergyGrid energyGrid1 = new EnergyGrid("Secundary Grid", 0);
        EnergyGrid energyGrid2 = new EnergyGrid("Main Grid", 0);
        list2.addGrid(energyGrid1);
        list3.addGrid(energyGrid1);
        list3.addGrid(energyGrid2);
        //Act
        boolean actualResult1 = list1.isEmpty();
        boolean actualResult2 = list2.isEmpty();
        boolean actualResult3 = list3.isEmpty();
        //Assert
        assertTrue(actualResult1);
        assertFalse(actualResult2);
        assertFalse(actualResult3);
    }

    @Test
    void hashCodeDummyTest() {
        EnergyGridList pList1 = new EnergyGridList();
        EnergyGrid energyGrid1 = new EnergyGrid("Main Grid", 0);
        pList1.addGrid(energyGrid1);
        int expectedResult = 1;
        int actualResult = pList1.hashCode();
        assertEquals(expectedResult, actualResult);
    }
}

