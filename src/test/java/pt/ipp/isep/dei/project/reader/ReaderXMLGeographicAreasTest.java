package pt.ipp.isep.dei.project.reader;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controller.ReaderController;
import pt.ipp.isep.dei.project.model.*;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReaderXMLGeographicAreasTest {
    // Common testing artifacts for testing in this class.

    private ReaderXMLGeographicAreas validReader = new ReaderXMLGeographicAreas();

    @Test
    void seeIfReadFileWorks(){
        // Arrange

        GeographicAreaList expectedResult = new GeographicAreaList();
        GeographicAreaList actualResult = new GeographicAreaList();

        // First Area

        GeographicArea firstArea = new GeographicArea();
        firstArea.setId("ISEP");
        firstArea.setDescription("Campus do ISEP");
        firstArea.setTypeArea(new TypeArea("urban area"));
        firstArea.setWidth(0.261);
        firstArea.setLength(0.249);
        firstArea.setLocation(new Local(41.178553,-8.608035,111));
        // First Sensor in First Area

        Sensor firstAreaFirstSensor = new Sensor();
        firstAreaFirstSensor.setId("RF12345");
        firstAreaFirstSensor.setName("Meteo station ISEP - rainfall");

        SimpleDateFormat validSdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = validSdf.parse("2016-11-15");

        } catch (ParseException e) {
            e.printStackTrace();
        }
        firstAreaFirstSensor.setDateStartedFunctioning(date);
        firstAreaFirstSensor.setTypeSensor(new TypeSensor("rainfall","l/m2"));
        firstAreaFirstSensor.setLocal(new Local(41.179230,-8.606409,125));
        firstArea.addSensor(firstAreaFirstSensor);

        // Second sensor in First Area

        Sensor firstAreaSecondSensor = new Sensor();
        firstAreaSecondSensor.setId("TT12346");
        firstAreaSecondSensor.setName("Meteo station ISEP - temperature");
        firstAreaSecondSensor.setDateStartedFunctioning(date);
        firstAreaSecondSensor.setTypeSensor(new TypeSensor("temperature","C"));
        firstAreaSecondSensor.setLocal(new Local(41.179230,-8.606409,125));
        firstArea.addSensor(firstAreaSecondSensor);

        // Second Area

        GeographicArea secondArea = new GeographicArea();
        secondArea.setId("Porto");
        secondArea.setDescription("City of Porto");
        secondArea.setTypeArea(new TypeArea("city"));
        secondArea.setWidth(10.09);
        secondArea.setLength(3.30);
        secondArea.setLocation(new Local(41.149935,-8.610857,118));

        // First Sensor in Second Area

        Sensor secondAreaFirstSensor = new Sensor();
        secondAreaFirstSensor.setId("RF12334");
        secondAreaFirstSensor.setName("Meteo station CMP - rainfall");
        Date date2 = new Date();
        try {
            date2 = validSdf.parse("2017-11-15");

        } catch (ParseException e) {
            e.printStackTrace();
        }
        secondAreaFirstSensor.setDateStartedFunctioning(date2);
        secondAreaFirstSensor.setTypeSensor(new TypeSensor("rainfall","l/m2"));
        secondAreaFirstSensor.setLocal(new Local(41.179230,-8.606409,139));
        secondArea.addSensor(secondAreaFirstSensor);

        // Second Sensor in Second Area

        Sensor secondAreaSecondSensor = new Sensor();
        secondAreaSecondSensor.setId("TT1236A");
        secondAreaSecondSensor.setName("Meteo station CMP - temperature");
        Date date3 = new Date();
        try {
            date3 = validSdf.parse("2017-11-16");

        } catch (ParseException e) {
            e.printStackTrace();
        }
        secondAreaSecondSensor.setDateStartedFunctioning(date3);
        secondAreaSecondSensor.setTypeSensor(new TypeSensor("temperature","C"));
        secondAreaSecondSensor.setLocal(new Local(41.179230,-8.606409,139));
        secondArea.addSensor(secondAreaSecondSensor);

        // Populate expectedResult array

        expectedResult.addGeographicArea(firstArea);
        expectedResult.addGeographicArea(secondArea);

        // Act

        File fileToRead = new File("src/test/resources/DataSet_sprint05_GA.xml");
        String absolutePath = fileToRead.getAbsolutePath();
        double areasAdded = validReader.readFileAndAdd(absolutePath, actualResult);

        // Assert

        assertEquals(2, areasAdded);

        // Get one of the areas to  check its contents.

        GeographicArea actualArea = actualResult.get(0);
        SensorList firstAreaSensors = actualArea.getSensorList();

        // Declare expected area / sensors.

        SensorList expectedSensors = new SensorList();
        expectedSensors.add(actualArea.getSensorList().get(0));
        expectedSensors.add(actualArea.getSensorList().get(1));

        GeographicArea expectedArea = new GeographicArea("ISEP", new TypeArea("urban area"), 0.249,
                0.261, new Local(41.178553, -8.608035, 139));

        // Assert

        assertEquals(expectedArea, actualArea);
        assertEquals(expectedSensors, firstAreaSensors);
    }

}