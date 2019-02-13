package pt.ipp.isep.dei.project.model;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class TypeSensorListTest {

    @Test
    public void testAdd() {
        TypeSensor typeSensor1 = new TypeSensor("temperature", "celsius");
        TypeSensor typeSensor2 = new TypeSensor("temperature", "kelvin");
        TypeSensor typeSensor3 = new TypeSensor("temperature", "celsius");
        TypeSensor typeSensor4 = new TypeSensor("humidity", "percentage");
        TypeSensorList typeList = new TypeSensorList();
        //ACT
        boolean actualResult1 = typeList.add(typeSensor1);
        boolean actualResult2 = typeList.add(typeSensor2);
        boolean actualResult3 = typeList.add(typeSensor3);
        boolean actualResult4 = typeList.add(typeSensor4);
        //ASSERT
        assertTrue(actualResult1);
        assertTrue(actualResult2);
        assertFalse(actualResult3);
        assertTrue(actualResult4);
    }

    @Test
    public void testGetSensorList() {
        TypeSensorList typeList1 = new TypeSensorList();
        TypeSensorList typeList2 = new TypeSensorList();
        TypeSensorList typeList3 = new TypeSensorList();
        List<TypeSensor> expectedResult1 = new ArrayList<>();
        List<TypeSensor> expectedResult2 = new ArrayList<>();
        List<TypeSensor> expectedResult3 = new ArrayList<>();

        TypeSensor typeSensor1 = new TypeSensor("temperature", "celsius");
        TypeSensor typeSensor2 = new TypeSensor("temperature", "kelvin");

        typeList2.add(typeSensor1);
        typeList3.add(typeSensor1);
        typeList3.add(typeSensor2);

        expectedResult2.add(typeSensor1);
        expectedResult3.add(typeSensor1);
        expectedResult3.add(typeSensor2);
        //ACT
        List<TypeSensor> actualResult1 = typeList1.getSensorList();
        List<TypeSensor> actualResult2 = typeList2.getSensorList();
        List<TypeSensor> actualResult3 = typeList3.getSensorList();
        //ASSERT
        assertEquals(actualResult1,expectedResult1);
        assertEquals(actualResult2,expectedResult2);
        assertEquals(actualResult3,expectedResult3);
    }

    @Test
    public void testBuildString() {
        TypeSensor typeSensor1 = new TypeSensor("temperature", "celsius");
        TypeSensor typeSensor2 = new TypeSensor("temperature", "kelvin");
        TypeSensorList typeList1 = new TypeSensorList();
        TypeSensorList typeList2 = new TypeSensorList();
        typeList1.add(typeSensor1);
        typeList1.add(typeSensor2);
        String expectedResult2 = "Invalid List - List is Empty\n";
        String expectedResult1 = "---------------\n" +
                "0) Name: temperature | Unit: celsius\n" +
                "1) Name: temperature | Unit: kelvin\n" +
                "---------------\n";
        //ACT
        String actualResult1= typeList1.buildString();
        String actualResult2= typeList2.buildString();
        //ASSERT
        assertEquals(actualResult1,expectedResult1);
        assertEquals(actualResult2,expectedResult2);
    }
}