package pt.ipp.isep.dei.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * GeographicAreaList tests class.
 */

class GeographicAreaListTest {

    @Test
    void seeIfConstructorGeographicAreaListWorks() {
        //Arrange
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7, 100);
        GeographicArea ga = new GeographicArea("Porto", t1, 2, 3, l1);
        GeographicAreaList geographicAreaList = new GeographicAreaList(ga);
        List<GeographicArea> expectedResult = new ArrayList<>();
        List<GeographicArea> actualResult;
        //Act
        expectedResult.add(ga);
        actualResult = geographicAreaList.getGeographicAreaList();
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfAddsGeographicAreaToGeographicAreaListIfSameAsConstructor() {
        //Arrange
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7, 100);
        GeographicArea ga1 = new GeographicArea("Porto", t1, 2, 3, l1);
        GeographicArea ga2 = new GeographicArea("Porto", t1, 2, 3, l1);
        GeographicAreaList geographicAreaList = new GeographicAreaList(ga1);
        boolean actualResult;
        //Act
        actualResult = geographicAreaList.addGeographicArea(ga2);
        //Assert
        assertFalse(actualResult);
    }

    @Test
    void seeIfAddsGeographicAreaToGeographicAreaListIfDifferentFromConstructor() {
        //Arrange
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7, 100);
        Local l2 = new Local(87, 67, 100);
        GeographicArea ga1 = new GeographicArea("Porto", t1, 2, 3, l1);
        GeographicArea ga2 = new GeographicArea("Porto", t1, 2, 3, l2);
        GeographicAreaList geographicAreaList = new GeographicAreaList(ga1);
        boolean actualResult;
        //Act
        actualResult = geographicAreaList.addGeographicArea(ga2);
        //Assert
        assertTrue(actualResult);
    }

    @Test
    void seeIfTrueWhenGivenGeoAreaIsFirstInGeographicAreaList() {
        //Arrange
        TypeArea t1 = new TypeArea("Cidade");
        Local l1 = new Local(38, 7, 100);
        String n1 = "Porto";

        Local l2 = new Local(39, 67, 100);
        String n2 = "Braga";

        Local l3 = new Local(87, 67, 100);
        String n3 = "Lisboa";

        GeographicArea ga1 = new GeographicArea(n1, t1, 2, 3, l1);
        GeographicArea ga2 = new GeographicArea(n2, t1, 2, 3, l2);
        GeographicArea ga3 = new GeographicArea(n3, t1, 2, 3, l3);

        GeographicAreaList geographicAreaList = new GeographicAreaList(ga1);
        geographicAreaList.addGeographicArea(ga2);
        geographicAreaList.addGeographicArea(ga3);
        GeographicArea gaToTest = new GeographicArea(n1, t1, 2, 3, l1);
        gaToTest.setId(n1);

        //Act
        boolean actualResult = geographicAreaList.containsGA(gaToTest);
        //Assert
        assertTrue(actualResult);
    }

    @Test
    void seeIfTrueWhenGivenGeoAreaIsInMiddleOfGeographicAreaList() {
        //Arrange
        boolean actualResult;

        TypeArea t1 = new TypeArea("Cidade");
        Local l1 = new Local(38, 7, 100);
        String n1 = "Porto";

        Local l2 = new Local(39, 67, 100);
        String n2 = "Braga";

        Local l3 = new Local(87, 67, 100);
        String n3 = "Lisboa";


        GeographicArea ga1 = new GeographicArea(n1, t1, 2, 3, l1);
        GeographicArea ga2 = new GeographicArea(n2, t1, 2, 3, l2);
        GeographicArea ga3 = new GeographicArea(n3, t1, 2, 3, l3);


        GeographicAreaList geographicAreaList = new GeographicAreaList(ga1);
        geographicAreaList.addGeographicArea(ga2);
        geographicAreaList.addGeographicArea(ga3);
        GeographicArea gaToTest = new GeographicArea(n2, t1, 2, 3, l2);
        gaToTest.setId(n2);

        //Act
        actualResult = geographicAreaList.containsGA(gaToTest);
        //Assert
        assertTrue(actualResult);
    }

    @Test
    void seeIfTrueWhenGivenGeoAreaIsInLastOfGeographicAreaList() {
        //Arrange
        boolean actualResult;

        TypeArea t1 = new TypeArea("Cidade");
        Local l1 = new Local(38, 7, 100);
        String n1 = "Porto";

        Local l2 = new Local(39, 67, 100);
        String n2 = "Braga";

        Local l3 = new Local(87, 67, 100);
        String n3 = "Lisboa";

        GeographicArea ga1 = new GeographicArea(n1, t1, 2, 3, l1);
        GeographicArea ga2 = new GeographicArea(n2, t1, 2, 3, l2);
        GeographicArea ga3 = new GeographicArea(n3, t1, 2, 3, l3);

        GeographicAreaList geographicAreaList = new GeographicAreaList(ga1);
        geographicAreaList.addGeographicArea(ga2);
        geographicAreaList.addGeographicArea(ga3);
        GeographicArea gaToTest = new GeographicArea(n3, t1, 2, 3, l3);
        gaToTest.setId(n3);

        //Act
        actualResult = geographicAreaList.containsGA(gaToTest);
        //Assert
        assertTrue(actualResult);
    }

    @Test
    void seeIfFalseWhenGivenGeoAreaIsNotContainedInGeographicAreaList() {
        //Arrange
        boolean actualResult;

        TypeArea t1 = new TypeArea("Cidade");
        Local l1 = new Local(38, 7, 100);
        String n1 = "Porto";

        Local l2 = new Local(39, 67, 100);
        String n2 = "Braga";

        Local l3 = new Local(87, 67, 100);
        String n3 = "Lisboa";

        Local localTest = new Local(98, 54, 100);
        GeographicArea gaToTest = new GeographicArea("Coimbra", t1, 2, 3, localTest);
        gaToTest.setId("Madrid");

        GeographicArea ga1 = new GeographicArea(n1, t1, 2, 3, l1);
        GeographicArea ga2 = new GeographicArea(n2, t1, 2, 3, l2);
        GeographicArea ga3 = new GeographicArea(n3, t1, 2, 3, l3);

        GeographicAreaList geographicAreaList = new GeographicAreaList(ga1);
        geographicAreaList.addGeographicArea(ga2);
        geographicAreaList.addGeographicArea(ga3);

        //Act
        actualResult = geographicAreaList.containsGA(gaToTest);
        //Assert
        assertFalse(actualResult);
    }


    @Test
    void seeIfGetGeographicAreaListOfSameTypeAsLastGeoAreaInList() {
        //Arrange
        String typeToTest = "Freguesia";
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7, 100);

        TypeArea t2 = new TypeArea("País");
        Local l2 = new Local(43, 71, 100);

        TypeArea t3 = new TypeArea("Freguesia");
        Local l3 = new Local(43, 74, 100);

        GeographicArea ga1 = new GeographicArea("Porto", t1, 2, 3, l1);
        GeographicArea ga2 = new GeographicArea("Coimbra", t2, 2, 3, l2);
        GeographicArea ga3 = new GeographicArea("Lisboa", t3, 2, 3, l3);

        GeographicAreaList geographicAreaList = new GeographicAreaList(ga1);
        geographicAreaList.addGeographicArea(ga2);
        geographicAreaList.addGeographicArea(ga3);

        GeographicAreaList expectedResult = new GeographicAreaList();
        expectedResult.addGeographicArea(ga3);
        GeographicAreaList actualResult;
        //Act
        actualResult = geographicAreaList.matchGeographicAreaWithInputType(typeToTest);
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetGeographicAreaListOfSameTypeAsFirstGeoAreaInList() {
        //Arrange
        String typeToTest = "Rua";
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7, 100);

        TypeArea t2 = new TypeArea("País");
        Local l2 = new Local(43, 71, 100);

        TypeArea t3 = new TypeArea("Freguesia");
        Local l3 = new Local(43, 74, 100);

        GeographicArea ga1 = new GeographicArea("Porto", t1, 2, 3, l1);
        GeographicArea ga2 = new GeographicArea("Coimbra", t2, 2, 3, l2);
        GeographicArea ga3 = new GeographicArea("Lisboa", t3, 2, 3, l3);

        GeographicAreaList geographicAreaList = new GeographicAreaList(ga1);
        geographicAreaList.addGeographicArea(ga2);
        geographicAreaList.addGeographicArea(ga3);

        GeographicAreaList expectedResult = new GeographicAreaList();
        expectedResult.addGeographicArea(ga1);
        GeographicAreaList actualResult;
        //Act
        actualResult = geographicAreaList.matchGeographicAreaWithInputType(typeToTest);
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetGeographicAreaListOfSameTypeAsGeoAreaInMiddleOfList() {
        //Arrange
        String typeToTest = "País";
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7, 100);

        TypeArea t2 = new TypeArea("País");
        Local l2 = new Local(43, 71, 100);

        TypeArea t3 = new TypeArea("Freguesia");
        Local l3 = new Local(43, 74, 100);

        GeographicArea ga1 = new GeographicArea("Porto", t1, 2, 3, l1);
        GeographicArea ga2 = new GeographicArea("Coimbra", t2, 2, 3, l2);
        GeographicArea ga3 = new GeographicArea("Lisboa", t3, 2, 3, l3);

        GeographicAreaList geographicAreaList = new GeographicAreaList(ga1);
        geographicAreaList.addGeographicArea(ga2);
        geographicAreaList.addGeographicArea(ga3);

        GeographicAreaList expectedResult = new GeographicAreaList();
        expectedResult.addGeographicArea(ga2);
        GeographicAreaList actualResult;
        //Act
        actualResult = geographicAreaList.matchGeographicAreaWithInputType(typeToTest);
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetEmptyGeographicAreaListFromTypeDifferentOfTypeInList() {
        //Arrange
        String typeToTest = "Cidade";
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7, 100);
        GeographicArea ga1 = new GeographicArea("Porto", t1, 2, 3, l1);

        TypeArea t2 = new TypeArea("Freguesia");
        Local l2 = new Local(43, 71, 100);
        GeographicArea ga2 = new GeographicArea("Coimbra", t2, 2, 3, l2);

        GeographicAreaList geographicAreaList = new GeographicAreaList(ga1);
        geographicAreaList.addGeographicArea(ga2);

        GeographicAreaList expectedResult = new GeographicAreaList();
        GeographicAreaList actualResult;
        //Act
        actualResult = geographicAreaList.matchGeographicAreaWithInputType(typeToTest);
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetEmptyGeographicAreaListFromEmptyStartingList() {
        //Arrange
        String typeToTest = "Cidade";

        GeographicAreaList geographicAreaList = new GeographicAreaList();

        GeographicAreaList expectedResult = new GeographicAreaList();
        GeographicAreaList actualResult;
        //Act
        actualResult = geographicAreaList.matchGeographicAreaWithInputType(typeToTest);
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfEqualsWhenObjectsAreDifferentButWithSameContent() {
        //Arrange
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7, 100);
        GeographicArea ga1 = new GeographicArea("Porto", t1, 2, 3, l1);

        TypeArea t2 = new TypeArea("Freguesia");
        Local l2 = new Local(43, 71, 100);
        GeographicArea ga2 = new GeographicArea("Coimbra", t2, 2, 3, l2);

        GeographicAreaList geographicAreaList1 = new GeographicAreaList(ga1);
        geographicAreaList1.addGeographicArea(ga2);

        GeographicAreaList geographicAreaList2 = new GeographicAreaList(ga1);
        geographicAreaList2.addGeographicArea(ga2);

        boolean expectedResult = true;
        boolean actualResult;
        //Act
        actualResult = geographicAreaList1.equals(geographicAreaList2);
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfEqualsToSameObject() {
        //Arrange
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7, 100);
        GeographicArea ga1 = new GeographicArea("Porto", t1, 2, 3, l1);

        TypeArea t2 = new TypeArea("Freguesia");
        Local l2 = new Local(43, 71, 100);
        GeographicArea ga2 = new GeographicArea("Coimbra", t2, 2, 3, l2);

        GeographicAreaList geographicAreaList1 = new GeographicAreaList(ga1);
        geographicAreaList1.addGeographicArea(ga2);

        GeographicAreaList geographicAreaList2 = new GeographicAreaList(ga1);
        geographicAreaList2.addGeographicArea(ga2);

        boolean actualResult;
        //Act
        actualResult = geographicAreaList1.equals(geographicAreaList1);
        //Assert
        assertTrue(actualResult);
    }

    @Test
    void seeIfFalseWhenObjectsAreDifferentWithDifferentContent() {
        //Arrange
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7, 100);

        TypeArea t2 = new TypeArea("Freguesia");
        Local l2 = new Local(43, 71, 100);

        TypeArea t3 = new TypeArea("Rua");
        Local l3 = new Local(54, 98, 100);

        TypeArea t4 = new TypeArea("Freguesia");
        Local l4 = new Local(73, 74, 100);

        GeographicArea ga1 = new GeographicArea("Porto", t1, 2, 3, l1);
        GeographicArea ga2 = new GeographicArea("Coimbra", t2, 2, 3, l2);
        GeographicArea ga3 = new GeographicArea("Braga", t3, 2, 3, l3);
        GeographicArea ga4 = new GeographicArea("Lisboa", t4, 2, 3, l4);


        GeographicAreaList geographicAreaList1 = new GeographicAreaList(ga1);
        geographicAreaList1.addGeographicArea(ga2);

        GeographicAreaList geographicAreaList2 = new GeographicAreaList(ga3);
        geographicAreaList2.addGeographicArea(ga4);

        boolean actualResult;
        //Act
        actualResult = geographicAreaList1.equals(geographicAreaList2);
        //Assert
        assertFalse(actualResult);
    }

    @Test
    void seeIfFalseWhenObjectsAreFromDifferentClass() {
        //Arrange
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7, 100);

        TypeArea t2 = new TypeArea("Freguesia");
        Local l2 = new Local(43, 71, 100);

        GeographicArea ga1 = new GeographicArea("Porto", t1, 2, 3, l1);
        GeographicArea ga2 = new GeographicArea("Coimbra", t2, 2, 3, l2);

        GeographicAreaList geographicAreaList1 = new GeographicAreaList(ga1);
        geographicAreaList1.addGeographicArea(ga2);


        boolean actualResult;
        //Act
        actualResult = geographicAreaList1.equals(l2);
        //Assert
        assertFalse(actualResult);
    }

    @Test
    void seeIfPrintsGeoAList() {
        GeographicArea gA1 = new GeographicArea("Portugal", new TypeArea("Country"), 10, 20, new Local(21, 33, 18));
        GeographicArea gA2 = new GeographicArea("Oporto", new TypeArea("City"), 10, 20, new Local(14, 14, 18));
        GeographicArea gA3 = new GeographicArea("Lisbon", new TypeArea("Village"), 10, 20, new Local(3, 3, 18));

        GeographicAreaList gAL1 = new GeographicAreaList();
        gAL1.addGeographicArea(gA1);
        gAL1.addGeographicArea(gA2);
        gAL1.addGeographicArea(gA3);
        String expectedResult = "---------------\n" +
                "0) Name: Portugal | Type: Country | Latitude: 21.0 | Longitude: 33.0\n" +
                "1) Name: Oporto | Type: City | Latitude: 14.0 | Longitude: 14.0\n" +
                "2) Name: Lisbon | Type: Village | Latitude: 3.0 | Longitude: 3.0\n" +
                "---------------\n";
        String result = gAL1.buildGaWholeListString(gAL1);
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfPrintsGeoAListIfEmpty() {
        GeographicAreaList gAL1 = new GeographicAreaList();
        String expectedResult = "Invalid List - List is Empty\n";
        String result = gAL1.buildGaWholeListString(gAL1);
        assertEquals(expectedResult, result);
    }

    @Test
    void hashCodeDummyTest() {
        GeographicAreaList gAL1 = new GeographicAreaList();
        int expectedResult = 1;
        int actualResult = gAL1.hashCode();
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void getGeographicAreaSuccess() {
        GeographicAreaList gAL1 = new GeographicAreaList();
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7, 100);
        GeographicArea c = new GeographicArea("Porto", t1, 2, 3, l1);
        gAL1.addGeographicArea(c);
        GeographicArea result = gAL1.getGeographicArea("Porto", t1, l1);
        assertEquals(c, result);
    }

    @Test
    void getGeographicAreaFails() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    GeographicAreaList gAL1 = new GeographicAreaList();
                    TypeArea t1 = new TypeArea("Rua");
                    Local l1 = new Local(38, 7, 100);
                    GeographicArea c = new GeographicArea("Porto", t1, 2, 3, l1);
                    gAL1.addGeographicArea(c);
                    gAL1.getGeographicArea("Lisboa", t1, l1);

                });
    }

    @Test
    void checkIfGAExistsFalse() {
        GeographicAreaList gAL1 = new GeographicAreaList();
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7, 100);
        GeographicArea c = new GeographicArea("Porto", t1, 2, 3, l1);
        gAL1.addGeographicArea(c);
        boolean result = gAL1.checkIfGAExists("Porto", t1, 38, 7, 100);
        assertFalse(result);
    }

    @Test
    void checkIfGAExistsTrue() {
        GeographicAreaList gAL1 = new GeographicAreaList();
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7, 100);
        GeographicArea c = new GeographicArea("Porto", t1, 2, 3, l1);
        gAL1.addGeographicArea(c);
        boolean result = gAL1.checkIfGAExists("Lisboa", t1, 38, 7, 100);
        assertTrue(result);
    }

    @Test
    void checkIfGAExistsIfEmptyList() {
        GeographicAreaList gAL1 = new GeographicAreaList();
        TypeArea t1 = new TypeArea("Rua");
        boolean result = gAL1.checkIfGAExists("Lisboa", t1, 38, 7, 100);
        assertTrue(result);
    }

    @Test
    void isEmpty() {
        //Arrange
        GeographicAreaList geographicAreaList1 = new GeographicAreaList(); //EMPTY LIST
        GeographicAreaList geographicAreaList2 = new GeographicAreaList(); //LIST WITH ONE ELEMENT
        GeographicAreaList geographicAreaList3 = new GeographicAreaList(); //LIST WITH TWO ELEMENTS

        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7, 100);
        GeographicArea geographicArea1 = new GeographicArea("Porto", t1, 2, 3, l1);
        GeographicArea geographicArea2 = new GeographicArea("Porto", t1, 2, 3, l1);

        geographicAreaList2.addGeographicArea(geographicArea1);
        geographicAreaList3.addGeographicArea(geographicArea1);
        geographicAreaList3.addGeographicArea(geographicArea2);

        //Act
        boolean actualResult1 = geographicAreaList1.isEmpty();
        boolean actualResult2 = geographicAreaList2.isEmpty();
        boolean actualResult3 = geographicAreaList3.isEmpty();

        //Assert
        assertTrue(actualResult1);
        assertFalse(actualResult2);
        assertFalse(actualResult3);
    }


}