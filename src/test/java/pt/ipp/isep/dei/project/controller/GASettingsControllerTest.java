package pt.ipp.isep.dei.project.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testng.Assert;
import pt.ipp.isep.dei.project.model.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.testng.Assert.*;

class GASettingsControllerTest {


    //SHARED METHODS

    @Test
    public void seeIfMatchGeographicAreaTypeIndexByString() {
        GASettingsController ctrl = new GASettingsController();
        TypeArea type = new TypeArea("cidade");
        TypeAreaList list = new TypeAreaList();
        String input = "cidade";
        list.addTypeArea(type);
        List<Integer> expectedResult = new ArrayList<>();
        expectedResult.add(0);
        List<Integer> result;
        result = ctrl.matchTypeAreaIndexByString(input, list);
        assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfMatchGeographicAreaTypeIndexByStringNotMatch() {
        GASettingsController ctrl = new GASettingsController();
        TypeArea type = new TypeArea("cidade");
        TypeAreaList list = new TypeAreaList();
        String input = "distrito";
        list.addTypeArea(type);
        List<Integer> expectedResult = new ArrayList<>();
        List<Integer> result;
        result = ctrl.matchTypeAreaIndexByString(input, list);
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfPrintGAWholeList() {
        GASettingsController ctrl = new GASettingsController();
        TypeArea type1 = new TypeArea("cidade");
        TypeArea type2 = new TypeArea("distrito");
        TypeArea type3 = new TypeArea("aldeia");
        TypeAreaList list = new TypeAreaList();
        list.addTypeArea(type1);
        list.addTypeArea(type2);
        list.addTypeArea(type3);
        list.addTypeArea(type1);
        List<Integer> listIndex = new ArrayList<>();
        listIndex.add(0);
        listIndex.add(1);
        listIndex.add(2);
        String expectedResult = "---------------\n" +
                "0) Type Area: cidade\n" +
                "1) Type Area: distrito\n" +
                "2) Type Area: aldeia\n" +
                "---------------\n";
        String result = ctrl.printTypeAreaElementsByIndex(listIndex, list);
        assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfPrintTypeAreaWorks() {
        GASettingsController ctrl = new GASettingsController();
        TypeArea type1 = new TypeArea("cidade");
        TypeAreaList list = new TypeAreaList();
        list.addTypeArea(type1);
        String actualResult = ctrl.printTypeArea(type1);
        String expectedResult = "Type Area: cidade\n";
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfPrintGATypeListWorks() {
        GASettingsController ctrl = new GASettingsController();
        TypeArea type1 = new TypeArea("cidade");
        TypeArea type2 = new TypeArea("bairro");
        TypeAreaList list = new TypeAreaList();
        list.addTypeArea(type1);
        list.addTypeArea(type2);
        String actualResult = ctrl.printGATypeList(list);
        String expectedResult = "---------------\n" +
                "0) Name: cidade \n" +
                "1) Name: bairro \n" +
                "---------------\n";
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfMatchGAByTypeAreaWorks() {
        //Arrange
        GASettingsController ctrl = new GASettingsController();
        GeographicArea gA1 = new GeographicArea("Portugal", new TypeArea("Country"), 2, 5, new Local(21, 33, 5));
        GeographicArea gA2 = new GeographicArea("Oporto", new TypeArea("City"), 2, 4, new Local(14, 14, 5));
        GeographicArea gA3 = new GeographicArea("Lisbon", new TypeArea("Village"), 2, 4, new Local(3, 3, 5));
        GeographicAreaList gAL1 = new GeographicAreaList();
        TypeArea type = new TypeArea("City");
        gAL1.addGeographicAreaToGeographicAreaList(gA1);
        gAL1.addGeographicAreaToGeographicAreaList(gA2);
        gAL1.addGeographicAreaToGeographicAreaList(gA3);
        //Arrange expectedResult
        GeographicAreaList gAL2 = new GeographicAreaList();
        gAL2.addGeographicAreaToGeographicAreaList(gA2);
        //Act
        GeographicAreaList actualResult = ctrl.matchGAByTypeArea(gAL1, type);
        GeographicAreaList expectedResult = gAL2;
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfGetTypeAreaName() {
        //Arrange
        GASettingsController ctrl = new GASettingsController();
        TypeArea type = new TypeArea("Bairro");
        //Act
        String actualResult = ctrl.getTypeAreaName(type);
        String expectedResult = "Bairro";
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    //USER STORY 001 TESTS

    @Test
    void seeIfnewTAGWorks() {
        TypeAreaList newList = new TypeAreaList();
        GASettingsController ctrl = new GASettingsController();
        boolean result = ctrl.createAndAddTypeAreaToList("cidade", newList);
        assertTrue(result);
    }

    @Test
    void seeIfnewTAGWorksWithAnother() {
        GASettingsController ctrl = new GASettingsController();
        TypeArea tipo = new TypeArea("rua");
        TypeAreaList newList = new TypeAreaList();
        newList.addTypeArea(tipo);
        boolean result = ctrl.createAndAddTypeAreaToList("cidade", newList);
        assertTrue(result);
    }

    @Test
    void seeIfnewTAGDoesntWorkWhenDuplicatedISAdded() {
        TypeArea tipo = new TypeArea("cidade");
        TypeAreaList expectedResult = new TypeAreaList();
        expectedResult.addTypeArea(tipo);
        GASettingsController ctrl = new GASettingsController();
        boolean result = ctrl.createAndAddTypeAreaToList("cidade", expectedResult);
        assertFalse(result);
    }

    @Test
    void seeIfNewTAGDoesntWorkWhenNullIsAdded() {
        TypeArea tipo = new TypeArea("cidade");
        TypeAreaList list = new TypeAreaList();
        list.addTypeArea(tipo);
        GASettingsController ctrl = new GASettingsController();
        boolean result = ctrl.createAndAddTypeAreaToList(null, list);
        assertFalse(result);
    }

    @Test
    void seeIfNewTAGDoesntWorkWhenNameIsEmpty() {
        TypeArea tipo = new TypeArea("cidade");
        TypeAreaList list = new TypeAreaList();
        list.addTypeArea(tipo);
        GASettingsController ctrl = new GASettingsController();
        boolean result = ctrl.createAndAddTypeAreaToList("", list);
        assertFalse(result);
    }

    @Test
    void seeIfNewTAGDoesntWorkWhenNumbersAreAdded() {
        TypeArea tipo = new TypeArea("cidade");
        TypeAreaList list = new TypeAreaList();
        list.addTypeArea(tipo);
        GASettingsController ctrl = new GASettingsController();
        boolean result = ctrl.createAndAddTypeAreaToList("cidade1", list);
        assertFalse(result);
    }


    //USER STORY 002 TESTS

    @Test
    void seeIfPrintTypeAreaListWorks() {
        TypeAreaList list = new TypeAreaList();
        TypeArea t1 = new TypeArea("Rua");
        list.addTypeArea(t1);
        GASettingsController ctrl = new GASettingsController();
        String actualResult = ctrl.getTypeAreaList(list);
        String expectedResult = "---------------\n" +
                "0) Name: Rua \n" +
                "---------------\n";
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfPrintTypeAreaListWorksWithTwoTypes() {
        TypeAreaList list = new TypeAreaList();
        TypeArea t1 = new TypeArea("Rua");
        TypeArea t2 = new TypeArea("Cidade");
        list.addTypeArea(t1);
        list.addTypeArea(t2);
        GASettingsController ctrl = new GASettingsController();
        String actualResult = ctrl.getTypeAreaList(list);
        String expectedResult = "---------------\n" +
                "0) Name: Rua \n" +
                "1) Name: Cidade \n" +
                "---------------\n";
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfPrintTypeAreaListWorksWithThreeTypes() {
        TypeAreaList list = new TypeAreaList();
        TypeArea t1 = new TypeArea("Rua");
        TypeArea t2 = new TypeArea("Cidade");
        TypeArea t3 = new TypeArea("Viela");
        list.addTypeArea(t1);
        list.addTypeArea(t2);
        list.addTypeArea(t3);
        GASettingsController ctrl = new GASettingsController();
        String actualResult = ctrl.getTypeAreaList(list);
        String expectedResult = "---------------\n" +
                "0) Name: Rua \n" +
                "1) Name: Cidade \n" +
                "2) Name: Viela \n" +
                "---------------\n";
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfPrintTypeAreaListWorksWithEmptyList() {
        TypeAreaList list = new TypeAreaList();
        GASettingsController ctrl = new GASettingsController();
        String actualResult = ctrl.getTypeAreaList(list);
        String expectedResult = "Invalid List - List is Empty\n";
        assertEquals(expectedResult, actualResult);
    }

    //USER STORY 003 TESTS

    @Test
    void seeIfCreatesGeographicAreaAndAddsItToList() {
        GeographicAreaList geoList = new GeographicAreaList();
        String name = "Porto";
        String typeArea = "Distrito";
        double latitude = 38;
        double longitude = 7;
        double altitude = 5;
        double length = 2;
        double width = 4;
        GASettingsController ctrl = new GASettingsController();
        boolean result = ctrl.addNewGeoAreaToList(geoList, name, typeArea, length, width, latitude, longitude, altitude);

        assertTrue(result);
        assertEquals(1, geoList.getGeographicAreaList().size());
    }

    @Test
    void seeIfFailsCreatingSecondEqualGeographicArea() {
        GeographicAreaList geoList = new GeographicAreaList();
        String name = "Porto";
        String typeArea = "Distrito";
        double latitude = 38;
        double longitude = 7;
        double altitude = 5;
        double length = 2;
        double width = 4;

        GASettingsController us3 = new GASettingsController();

        boolean result1 = us3.addNewGeoAreaToList(geoList, name, typeArea, length, width, latitude, longitude, altitude);
        boolean result2 = us3.addNewGeoAreaToList(geoList, name, typeArea, length, width, latitude, longitude, altitude);

        assertTrue(result1); //safety check (already covered on previous test)
        Assertions.assertFalse(result2);
        assertEquals(1, geoList.getGeographicAreaList().size());
    }

    @Test
    void seeIfCreatesTwoDifferentGeographicAreas() {
        GeographicAreaList geoList = new GeographicAreaList();
        String name1 = "Porto";
        String typeArea = "Distrito";
        double latitude = 38;
        double longitude = 7;
        double altitude = 5;
        double length = 2;
        double width = 4;
        String name2 = "Lisboa";

        GASettingsController us3 = new GASettingsController();
        boolean result1 = us3.addNewGeoAreaToList(geoList, name1, typeArea, length, width, latitude, longitude, altitude);
        boolean result2 = us3.addNewGeoAreaToList(geoList, name2, typeArea, length, width, latitude, longitude, altitude);

        assertTrue(result1); //safety check (already covered on previous test)
        assertTrue(result2);
        assertEquals(2, geoList.getGeographicAreaList().size());
    }

    @Test
    void seeIfFailsWithNullInputGeoList() {
        String name1 = "Porto";
        String typeArea = "Distrito";
        double latitude = 38;
        double longitude = 7;
        double altitude = 5;
        double length = 2;
        double width = 4;
        GASettingsController us3 = new GASettingsController();
        boolean result = us3.addNewGeoAreaToList(null, name1, typeArea, length, width, latitude, longitude, altitude);

        Assertions.assertFalse(result);
    }

    //USER STORY 004 TESTS

    /**
     * @Test void seeIfConstructorAddsGeographicAreas() {
     * <p>
     * //Arrange
     * GeographicAreaList geographicAreaList = new GeographicAreaList();
     * TypeArea t1 = new TypeArea("Rua");
     * Local l1 = new Local(38, 7);
     * GeographicArea ga1 = new GeographicArea(t1, l1);
     * TypeArea t2 = new TypeArea("Cidade");
     * Local l2 = new Local(40, 7);
     * GeographicArea ga2 = new GeographicArea(t2, l2);
     * TypeArea t3 = new TypeArea("Rua");
     * Local l3 = new Local(38, 59);
     * GeographicArea ga3 = new GeographicArea(t3, l3);
     * TypeArea t4 = new TypeArea("Montanha");
     * Local l4 = new Local(38, 32);
     * GeographicArea ga4 = new GeographicArea(t4, l4);
     * <p>
     * geographicAreaList.addGeographicAreaToGeographicAreaList(ga1);
     * geographicAreaList.addGeographicAreaToGeographicAreaList(ga2);
     * geographicAreaList.addGeographicAreaToGeographicAreaList(ga3);
     * geographicAreaList.addGeographicAreaToGeographicAreaList(ga4);
     * HouseConfigurationController ctrl = new HouseConfigurationController(geographicAreaList);
     * GeographicAreaList expectedResult = new GeographicAreaList();
     * expectedResult.addGeographicAreaToGeographicAreaList(ga1);
     * expectedResult.addGeographicAreaToGeographicAreaList(ga2);
     * expectedResult.addGeographicAreaToGeographicAreaList(ga3);
     * expectedResult.addGeographicAreaToGeographicAreaList(ga4);
     * <p>
     * //Act
     * GeographicAreaList actualResult = ctrl.getGeographicAreaList();
     * <p>
     * //Assert
     * assertEquals(expectedResult, actualResult);
     * }
     * @Test void seeIfConstructorDoesNotAddGeographicArea() {
     * <p>
     * //Arrange
     * GeographicAreaList geographicAreaList = new GeographicAreaList();
     * TypeArea t1 = new TypeArea("Rua");
     * Local l1 = new Local(38, 7);
     * GeographicArea ga1 = new GeographicArea(t1, l1);
     * TypeArea t2 = new TypeArea("Cidade");
     * Local l2 = new Local(40, 7);
     * GeographicArea ga2 = new GeographicArea(t2, l2);
     * TypeArea t3 = new TypeArea("Rua");
     * Local l3 = new Local(38, 59);
     * GeographicArea ga3 = new GeographicArea(t3, l3);
     * TypeArea t4 = new TypeArea("Montanha");
     * Local l4 = new Local(38, 32);
     * GeographicArea ga4 = new GeographicArea(t4, l4);
     * <p>
     * geographicAreaList.addGeographicAreaToGeographicAreaList(ga1);
     * geographicAreaList.addGeographicAreaToGeographicAreaList(ga2);
     * geographicAreaList.addGeographicAreaToGeographicAreaList(ga4);
     * HouseConfigurationController ctrl = new HouseConfigurationController(geographicAreaList);
     * GeographicAreaList expectedResult = new GeographicAreaList();
     * expectedResult.addGeographicAreaToGeographicAreaList(ga1);
     * expectedResult.addGeographicAreaToGeographicAreaList(ga2);
     * expectedResult.addGeographicAreaToGeographicAreaList(ga4);
     * <p>
     * //Act
     * GeographicAreaList actualResult = ctrl.getGeographicAreaList();
     * <p>
     * //Assert
     * assertEquals(expectedResult, actualResult);
     * }
     * @Test void seeThatWeMatchGeographicAreaListWithGeographicAreasFromTypeGivenInTheBeginning() {
     * <p>
     * //Arrange
     * GeographicAreaList geographicAreaList = new GeographicAreaList();
     * TypeArea t1 = new TypeArea("Rua");
     * Local l1 = new Local(38, 7);
     * GeographicArea ga1 = new GeographicArea(t1, l1);
     * TypeArea t2 = new TypeArea("Cidade");
     * Local l2 = new Local(40, 7);
     * GeographicArea ga2 = new GeographicArea(t2, l2);
     * TypeArea t4 = new TypeArea("Montanha");
     * Local l4 = new Local(38, 32);
     * GeographicArea ga4 = new GeographicArea(t4, l4);
     * <p>
     * geographicAreaList.addGeographicAreaToGeographicAreaList(ga1);
     * geographicAreaList.addGeographicAreaToGeographicAreaList(ga4);
     * geographicAreaList.addGeographicAreaToGeographicAreaList(ga2);
     * HouseConfigurationController ctrl = new HouseConfigurationController(geographicAreaList);
     * GeographicAreaList expectedResult = new GeographicAreaList();
     * expectedResult.addGeographicAreaToGeographicAreaList(ga1);
     * <p>
     * <p>
     * //Act
     * GeographicAreaList actualResult;
     * ctrl.matchGAByTypeArea("Rua");
     * actualResult = ctrl.getGeographicAreaList();
     * <p>
     * //Assert
     * assertEquals(expectedResult, actualResult);
     * }
     * @Test void seeThatWeMatchGeographicAreaListWithGeographicAreasFromTypeGivenInTheMiddle() {
     * <p>
     * //Arrange
     * GeographicAreaList geographicAreaList = new GeographicAreaList();
     * TypeArea t1 = new TypeArea("Rua");
     * Local l1 = new Local(38, 7);
     * GeographicArea ga1 = new GeographicArea(t1, l1);
     * TypeArea t2 = new TypeArea("Cidade");
     * Local l2 = new Local(40, 7);
     * GeographicArea ga2 = new GeographicArea(t2, l2);
     * TypeArea t4 = new TypeArea("Montanha");
     * Local l4 = new Local(38, 32);
     * GeographicArea ga4 = new GeographicArea(t4, l4);
     * <p>
     * geographicAreaList.addGeographicAreaToGeographicAreaList(ga1);
     * geographicAreaList.addGeographicAreaToGeographicAreaList(ga4);
     * geographicAreaList.addGeographicAreaToGeographicAreaList(ga2);
     * HouseConfigurationController ctrl = new HouseConfigurationController(geographicAreaList);
     * GeographicAreaList expectedResult = new GeographicAreaList();
     * expectedResult.addGeographicAreaToGeographicAreaList(ga2);
     * <p>
     * <p>
     * //Act
     * GeographicAreaList actualResult;
     * ctrl.matchGAByTypeArea("Cidade");
     * actualResult = ctrl.getGeographicAreaList();
     * <p>
     * //Assert
     * assertEquals(expectedResult, actualResult);
     * }
     * @Test void seeThatWeMatchGeographicAreaListWithGeographicAreasFromTypeGivenInTheEnd() {
     * <p>
     * //Arrange
     * GeographicAreaList geographicAreaList = new GeographicAreaList();
     * TypeArea t1 = new TypeArea("Rua");
     * Local l1 = new Local(38, 7);
     * GeographicArea ga1 = new GeographicArea(t1, l1);
     * TypeArea t2 = new TypeArea("Cidade");
     * Local l2 = new Local(40, 7);
     * GeographicArea ga2 = new GeographicArea(t2, l2);
     * TypeArea t4 = new TypeArea("Montanha");
     * Local l4 = new Local(38, 32);
     * GeographicArea ga4 = new GeographicArea(t4, l4);
     * <p>
     * geographicAreaList.addGeographicAreaToGeographicAreaList(ga1);
     * geographicAreaList.addGeographicAreaToGeographicAreaList(ga4);
     * geographicAreaList.addGeographicAreaToGeographicAreaList(ga2);
     * HouseConfigurationController ctrl = new HouseConfigurationController(geographicAreaList);
     * GeographicAreaList expectedResult = new GeographicAreaList();
     * expectedResult.addGeographicAreaToGeographicAreaList(ga4);
     * <p>
     * <p>
     * //Act
     * GeographicAreaList actualResult;
     * ctrl.matchGAByTypeArea("Montanha");
     * actualResult = ctrl.getGeographicAreaList();
     * <p>
     * //Assert
     * assertEquals(expectedResult, actualResult);
     * }
     * @Test void seeThatWeDontMatchGeographicAreaListWithGeographicAreasFromTypeGivenBecauseOfUpperCaseDifference() {
     * <p>
     * //Arrange
     * GeographicAreaList geographicAreaList = new GeographicAreaList();
     * TypeArea t1 = new TypeArea("Rua");
     * Local l1 = new Local(38, 7);
     * GeographicArea ga1 = new GeographicArea(t1, l1);
     * TypeArea t2 = new TypeArea("Cidade");
     * Local l2 = new Local(40, 7);
     * GeographicArea ga2 = new GeographicArea(t2, l2);
     * TypeArea t3 = new TypeArea("Rua");
     * Local l3 = new Local(38, 59);
     * GeographicArea ga3 = new GeographicArea(t3, l3);
     * TypeArea t4 = new TypeArea("Montanha");
     * Local l4 = new Local(38, 32);
     * GeographicArea ga4 = new GeographicArea(t4, l4);
     * <p>
     * geographicAreaList.addGeographicAreaToGeographicAreaList(ga1);
     * geographicAreaList.addGeographicAreaToGeographicAreaList(ga4);
     * geographicAreaList.addGeographicAreaToGeographicAreaList(ga2);
     * geographicAreaList.addGeographicAreaToGeographicAreaList(ga3);
     * HouseConfigurationController ctrl = new HouseConfigurationController(geographicAreaList);
     * GeographicAreaList expectedResult = new GeographicAreaList();
     * expectedResult.addGeographicAreaToGeographicAreaList(ga1);
     * expectedResult.addGeographicAreaToGeographicAreaList(ga3);
     * <p>
     * //Act
     * GeographicAreaList actualResult;
     * ctrl.matchGAByTypeArea("rua");
     * actualResult = ctrl.getGeographicAreaList();
     * <p>
     * //Assert
     * assertNotEquals(expectedResult, actualResult);
     * }
     * @Test void seeThatWeDontMatchGeographicAreaListWithGeographicAreasFromTypeGiven() {
     * <p>
     * //Arrange
     * GeographicAreaList geographicAreaList = new GeographicAreaList();
     * TypeArea t1 = new TypeArea("Rua");
     * Local l1 = new Local(38, 7);
     * GeographicArea ga1 = new GeographicArea(t1, l1);
     * TypeArea t2 = new TypeArea("Cidade");
     * Local l2 = new Local(40, 7);
     * GeographicArea ga2 = new GeographicArea(t2, l2);
     * TypeArea t3 = new TypeArea("Rua");
     * Local l3 = new Local(38, 59);
     * GeographicArea ga3 = new GeographicArea(t3, l3);
     * TypeArea t4 = new TypeArea("Montanha");
     * Local l4 = new Local(38, 32);
     * GeographicArea ga4 = new GeographicArea(t4, l4);
     * <p>
     * geographicAreaList.addGeographicAreaToGeographicAreaList(ga1);
     * geographicAreaList.addGeographicAreaToGeographicAreaList(ga4);
     * geographicAreaList.addGeographicAreaToGeographicAreaList(ga2);
     * geographicAreaList.addGeographicAreaToGeographicAreaList(ga3);
     * HouseConfigurationController ctrl = new HouseConfigurationController(geographicAreaList);
     * GeographicAreaList expectedResult = new GeographicAreaList();
     * <p>
     * //Act
     * GeographicAreaList actualResult;
     * ctrl.matchGAByTypeArea("Pais");
     * actualResult = ctrl.getGeographicAreaList();
     * <p>
     * //Assert
     * assertEquals(expectedResult, actualResult);
     * }
     **/

    @Test
    void seeIfPrintGAList() {
        //Arrange
        GeographicArea gA1 = new GeographicArea("Portugal", new TypeArea("Country"), 2, 5, new Local(21, 33, 5));
        GeographicArea gA2 = new GeographicArea("Oporto", new TypeArea("City"), 2, 4, new Local(14, 14, 5));
        GeographicArea gA3 = new GeographicArea("Lisbon", new TypeArea("Village"), 2, 4, new Local(3, 3, 5));
        GeographicAreaList gAL1 = new GeographicAreaList();
        gAL1.addGeographicAreaToGeographicAreaList(gA1);
        gAL1.addGeographicAreaToGeographicAreaList(gA2);
        gAL1.addGeographicAreaToGeographicAreaList(gA3);

        //Act
        String expectedResult = "---------------\n" +
                "0) Name: Portugal | Type: Country | Latitude: 21.0 | Longitude: 33.0\n" +
                "1) Name: Oporto | Type: City | Latitude: 14.0 | Longitude: 14.0\n" +
                "2) Name: Lisbon | Type: Village | Latitude: 3.0 | Longitude: 3.0\n" +
                "---------------\n";
        GASettingsController ctrl = new GASettingsController();
        String result = ctrl.printGAList(gAL1);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfMatchGeoArea() {
        GeographicAreaList geographicAreaList = new GeographicAreaList();
        GeographicArea ga1 = new GeographicArea("Oporto", new TypeArea("City"), 2,4,new Local(14, 14, 5));
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga1);
        GASettingsController ctrl = new GASettingsController();

        GeographicArea actualResult = ctrl.matchGeoArea("Oporto",geographicAreaList);

        assertEquals(ga1, actualResult);
    }

    @Test
    void seeIfMatchGeoAreaNull() {
        GeographicAreaList geographicAreaList = new GeographicAreaList();
        GeographicArea ga1 = new GeographicArea("Oporto", new TypeArea("City"), 2,4,new Local(14, 14, 5));
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga1);
        GASettingsController ctrl = new GASettingsController();

        GeographicArea actualResult = ctrl.matchGeoArea("Gaia", geographicAreaList);

        assertNull(actualResult);
    }

    @Test
    void seeIfSetMotherArea() {
        GeographicAreaList geographicAreaList = new GeographicAreaList();
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7, 100);
        GeographicArea ga1 = new GeographicArea("Oporto", new TypeArea("City"), 2,4,new Local(14, 14, 5));
        GeographicArea ga2 = new GeographicArea("Lisbon", new TypeArea("Village"), 2,4,new Local(3, 3, 5));

        geographicAreaList.addGeographicAreaToGeographicAreaList(ga1);
        geographicAreaList.addGeographicAreaToGeographicAreaList(ga2);
        GASettingsController ctrl = new GASettingsController();

        ctrl.setMotherArea(ga1, ga2);

        GeographicArea result = ctrl.getMotherArea();

        assertEquals(ga2, result);
    }

//USER STORY 007 Tests

    @Test
    public void seeIfMatchGeoAreaWorks() {
        //Arrange
        GASettingsController ctrl = new GASettingsController();
        GeographicArea gA1 = new GeographicArea("Portugal", new TypeArea("Country"), 2, 5, new Local(21, 33, 5));
        GeographicArea gA2 = new GeographicArea("Oporto", new TypeArea("City"), 2, 4, new Local(14, 14, 5));
        GeographicArea gA3 = new GeographicArea("Lisbon", new TypeArea("Village"), 2, 4, new Local(3, 3, 5));
        GeographicAreaList gAL1 = new GeographicAreaList();
        gAL1.addGeographicAreaToGeographicAreaList(gA1);
        gAL1.addGeographicAreaToGeographicAreaList(gA2);
        gAL1.addGeographicAreaToGeographicAreaList(gA3);
        //Act
        GeographicArea actualResult = ctrl.matchGeoArea("Portugal", gAL1);
        GeographicArea expectedResult = gA1;
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfSetAndGetMotherAreaWorks() {
        //Arrange
        GASettingsController ctrl = new GASettingsController();
        GeographicArea motherA = new GeographicArea("Oporto", new TypeArea("City"), 2, 4, new Local(14, 14, 5));
        GeographicArea daughterA = new GeographicArea("Valongo", new TypeArea("City"), 1, 1, new Local(15, 13, 5));
        //Act
        ctrl.setMotherArea(daughterA, motherA);
        GeographicArea actualResult = ctrl.getMotherArea();
        GeographicArea expectedResult = motherA;
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfPrintGeographicAreaListNamesWorks() {
        //Arrange
        GASettingsController ctrl = new GASettingsController();
        GeographicArea gA1 = new GeographicArea("Portugal", new TypeArea("Country"), 2, 5, new Local(21, 33, 5));
        GeographicArea gA2 = new GeographicArea("Oporto", new TypeArea("City"), 2, 4, new Local(14, 14, 5));
        GeographicArea gA3 = new GeographicArea("Lisbon", new TypeArea("Village"), 2, 4, new Local(3, 3, 5));
        GeographicAreaList gAL1 = new GeographicAreaList();
        gAL1.addGeographicAreaToGeographicAreaList(gA1);
        gAL1.addGeographicAreaToGeographicAreaList(gA2);
        gAL1.addGeographicAreaToGeographicAreaList(gA3);
        //Act
        String actualResult = ctrl.printGeographicAreaListNames(gAL1);
        String expectedResult = "Geographic Area List: \n" +
                "-Portugal; \n" +
                "-Oporto; \n" +
                "-Lisbon;";
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfValidateGeoAreaWorks() {
        //Arrange
        GASettingsController ctrl = new GASettingsController();
        GeographicArea gA1 = new GeographicArea("Portugal", new TypeArea("Country"), 2, 5, new Local(21, 33, 5));
        GeographicArea gA2 = new GeographicArea("Oporto", new TypeArea("City"), 2, 4, new Local(14, 14, 5));
        GeographicArea gA3 = new GeographicArea("Lisbon", new TypeArea("Village"), 2, 4, new Local(3, 3, 5));
        GeographicAreaList gAL1 = new GeographicAreaList();
        gAL1.addGeographicAreaToGeographicAreaList(gA1);
        gAL1.addGeographicAreaToGeographicAreaList(gA2);
        gAL1.addGeographicAreaToGeographicAreaList(gA3);
        //Act
        boolean actualResult = ctrl.validateGeoArea("Oporto", gAL1);
        //Assert
        assertTrue(actualResult);
    }

    @Test
    void seeIfMatchGeographicAreasWorks() {
        //Arrange
        GASettingsController ctrl = new GASettingsController();
        GeographicArea gA1 = new GeographicArea("Portugal", new TypeArea("Country"), 40, 20, new Local(21, 33, 5));
        GeographicArea gA2 = new GeographicArea("Oporto", new TypeArea("City"), 2, 4, new Local(21, 33, 5));
        GeographicArea gA3 = new GeographicArea("Lisbon", new TypeArea("Village"), 2, 4, new Local(3, 3, 5));
        GeographicAreaList gAL1 = new GeographicAreaList();
        gAL1.addGeographicAreaToGeographicAreaList(gA1);
        gAL1.addGeographicAreaToGeographicAreaList(gA2);
        gAL1.addGeographicAreaToGeographicAreaList(gA3);
        //Act
        boolean actualResult = ctrl.matchGeographicAreas("Oporto", "Portugal", gAL1);
        //Assert
        assertTrue(actualResult);
    }
}
