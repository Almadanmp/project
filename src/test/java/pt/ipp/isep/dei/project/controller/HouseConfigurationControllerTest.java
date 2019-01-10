package pt.ipp.isep.dei.project.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

class HouseConfigurationControllerTest {

         @Test
    void seeIfPrintGAList() {
        //Arrange
        GeographicArea gA1 = new GeographicArea("Portugal",new TypeArea("Country"),2,3,new Local(21, 33, 100));
        GeographicArea gA2 = new GeographicArea("Oporto",new TypeArea("City"),2,3,new Local(14, 14, 100));
        GeographicArea gA3 = new GeographicArea("Lisbon",new TypeArea("Village"),2,3,new Local(3, 3, 100));

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
        HouseConfigurationController ctrl = new HouseConfigurationController();
        String result = ctrl.printGAList(gAL1);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGeographicAreaIndexMatchByString() {
        //Arrange
        HouseConfigurationController ctrl = new HouseConfigurationController();
        GeographicAreaList mGeographicAreaList = new GeographicAreaList();
        GeographicArea gA1 = new GeographicArea("porto",new TypeArea("cidade"),2,3,new Local(4, 4, 100));
        GeographicArea gA2 = new GeographicArea("lisboa",new TypeArea("aldeia"),2,3,new Local(4, 4, 100));

        mGeographicAreaList.addGeographicAreaToGeographicAreaList(gA1);
        mGeographicAreaList.addGeographicAreaToGeographicAreaList(gA2);
        //Act
        List<Integer> result = ctrl.matchGeographicAreaIndexByString("lisboa", mGeographicAreaList);
        List<Integer> expectedResult = Collections.singletonList(mGeographicAreaList.getGeographicAreaList().indexOf(gA2));
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfPrintGeoGraphicAreaElementsByIndex() {
        //Arrange
        HouseConfigurationController ctrl = new HouseConfigurationController();
        List<Integer> list = new ArrayList<>();
        Integer i = 1;
        list.add(i);
        GeographicAreaList mGeographicAreaList = new GeographicAreaList();
        GeographicArea geoa1 = new GeographicArea("porto",new TypeArea("cidade"),2,3,new Local(4, 4, 100));
        GeographicArea geoa2 = new GeographicArea("lisboa",new TypeArea("aldeia"),2,3,new Local(4, 4, 100));

        mGeographicAreaList.addGeographicAreaToGeographicAreaList(geoa1);
        mGeographicAreaList.addGeographicAreaToGeographicAreaList(geoa2);

        //Act
        String result = ctrl.printGeoGraphicAreaElementsByIndex(list, mGeographicAreaList);
        String expectedResult = "---------------\n" +
                "1) lisboa, aldeia, 4.0º lat, 4.0º long\n" +
                "---------------\n";

        //Assert
        assertEquals(expectedResult, result);
    }

    //USER STORY 105


    // USER STORY 108

    @Test
    void seeIfMatchGeographicAreaIndexByStringWorks() {
        //Arrange -------------------------------------
        HouseConfigurationController ctrl = new HouseConfigurationController();
        //Geo Area List
        GeographicAreaList mGeographicAreaList = new GeographicAreaList();
        GeographicArea geoa1 = new GeographicArea("porto",new TypeArea("cidade"),2,3,new Local(4, 4, 100));
        GeographicArea geoa2 = new GeographicArea("lisboa",new TypeArea("aldeia"),2,3,new Local(4, 4, 100));

        mGeographicAreaList.addGeographicAreaToGeographicAreaList(geoa1);
        mGeographicAreaList.addGeographicAreaToGeographicAreaList(geoa2);
        //Act -----------------------------------------
        List<Integer> result = ctrl.matchGeographicAreaIndexByString("lisboa", mGeographicAreaList);
        List<Integer> expectedResult = Collections.singletonList(mGeographicAreaList.getGeographicAreaList().indexOf(geoa2));
        //Assert --------------------------------------
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfPrintGeoGraphicAreaElementsByIndex2() {
        //Arrange -----------------------------------------
        HouseConfigurationController ctrl = new HouseConfigurationController();
        //Geo Area List
        GeographicAreaList mGeographicAreaList = new GeographicAreaList();
        GeographicArea geoa1 = new GeographicArea("porto",new TypeArea("cidade"),2,3,new Local(4, 4, 100));
        GeographicArea geoa2 = new GeographicArea("lisboa",new TypeArea("aldeia"),2,3,new Local(4, 4, 100));
        mGeographicAreaList.addGeographicAreaToGeographicAreaList(geoa1);
        mGeographicAreaList.addGeographicAreaToGeographicAreaList(geoa2);
        //Act ---------------------------------------------
        List<Integer> list = new ArrayList<>();
        Integer i = 1;
        list.add(i);
        String result = ctrl.printGeoGraphicAreaElementsByIndex(list, mGeographicAreaList);
        String expectedResult = "---------------\n" +
                "1) lisboa, aldeia, 4.0º lat, 4.0º long\n" +
                "---------------\n";
        //Assert ------------------------------------------
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void seeIfPrintsGeoA() {
        HouseConfigurationController US101 = new HouseConfigurationController();
        GeographicArea gA1 = new GeographicArea("Portugal",new TypeArea("Country"),2,3,new Local(21, 33, 100));
        GeographicArea gA2 = new GeographicArea("Oporto",new TypeArea("City"),2,3,new Local(14, 14, 100));
        GeographicArea gA3 = new GeographicArea("Lisbon",new TypeArea("Village"),2,3,new Local(3, 3, 100));

        GeographicAreaList gAL1 = new GeographicAreaList();
        gAL1.addGeographicAreaToGeographicAreaList(gA1);
        gAL1.addGeographicAreaToGeographicAreaList(gA2);
        gAL1.addGeographicAreaToGeographicAreaList(gA3);
        String expectedResult =
                "Portugal, Country, 21.0º lat, 33.0º long\n";
        String result = US101.printGA(gA1);
        assertEquals(expectedResult, result);
    }





    }
