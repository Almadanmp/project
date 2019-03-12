package pt.ipp.isep.dei.project.reader;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.dto.GeographicAreaDTO;
import pt.ipp.isep.dei.project.dto.SensorDTO;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class JSONReaderTest {
    // Common testing artifacts for testing in this class.

    private JSONReader reader = new JSONReader();

    /*
    @Test
    void seeIfReadFileWorksFileNotFound(){
        // Arrange

        String invalidFilePath = "invalidfilepath";

        // Act and Assert

        assertThrows(FileNotFoundException.class, () -> {
            reader.readFile(invalidFilePath);});
    }*/

    @Test
    void seeIfReadFileWorks(){
        // Arrange

        GeographicAreaDTO[] expectedResult = new GeographicAreaDTO[2];

        // First Area

        GeographicAreaDTO firstArea = new GeographicAreaDTO();
        firstArea.setId("ISEP");
        firstArea.setDescription("Campus do ISEP");
        firstArea.setTypeArea("urban area");
        firstArea.setWidth(0.261);
        firstArea.setLength(0.249);
        firstArea.setLatitudeGeoAreaDTO(41.178553);
        firstArea.setLongitudeGeoAreaDTO(-8.608035);
        firstArea.setAltitudeGeoAreaDTO(111);

        // First Sensor in First Area

        SensorDTO firstAreaFirstSensor = new SensorDTO();
        firstAreaFirstSensor.setId("RF12345");
        firstAreaFirstSensor.setName("Meteo station ISEP - rainfall");
        firstAreaFirstSensor.setDateStartedFunctioning("2016-11-15");
        firstAreaFirstSensor.setTypeSensor("rainfall");
        firstAreaFirstSensor.setUnits("l/m2");
        firstAreaFirstSensor.setLatitude(41.179230);
        firstAreaFirstSensor.setLongitude(-8.606409);
        firstAreaFirstSensor.setAltitude(125);
        firstArea.addSensorDTO(firstAreaFirstSensor);

        // Second sensor in First Area

        SensorDTO firstAreaSecondSensor = new SensorDTO();
        firstAreaSecondSensor.setId("TT12346");
        firstAreaSecondSensor.setName("Meteo station ISEP - temperature");
        firstAreaSecondSensor.setDateStartedFunctioning("2016-11-15");
        firstAreaSecondSensor.setTypeSensor("temperature");
        firstAreaSecondSensor.setUnits("C");
        firstAreaSecondSensor.setLatitude(41.179230);
        firstAreaSecondSensor.setLongitude(-8.606409);
        firstAreaSecondSensor.setAltitude(125);
        firstArea.addSensorDTO(firstAreaSecondSensor);

        // Second Area

        GeographicAreaDTO secondArea = new GeographicAreaDTO();
        secondArea.setId("Porto");
        secondArea.setDescription("City of Porto");
        secondArea.setTypeArea("city");
        secondArea.setWidth(10.09);
        secondArea.setLength(3.30);
        secondArea.setLatitudeGeoAreaDTO(41.149935);
        secondArea.setLongitudeGeoAreaDTO(-8.610857);
        secondArea.setAltitudeGeoAreaDTO(118);

        // First Sensor in Second Area

        SensorDTO secondAreaFirstSensor = new SensorDTO();
        secondAreaFirstSensor.setId("RF12334");
        secondAreaFirstSensor.setName("Meteo station CMP - rainfall");
        secondAreaFirstSensor.setDateStartedFunctioning("2017-11-15");
        secondAreaFirstSensor.setTypeSensor("rainfall");
        secondAreaFirstSensor.setUnits("l/m2");
        secondAreaFirstSensor.setLatitude(41.179230);
        secondAreaFirstSensor.setLongitude(-8.606409);
        secondAreaFirstSensor.setAltitude(139);
        secondArea.addSensorDTO(secondAreaFirstSensor);

        // Second Sensor in Second Area

        SensorDTO secondAreaSecondSensor = new SensorDTO();
        secondAreaSecondSensor.setId("TT1236A");
        secondAreaSecondSensor.setName("Meteo station CMP - temperature");
        secondAreaSecondSensor.setDateStartedFunctioning("2017-11-16");
        secondAreaSecondSensor.setTypeSensor("temperature");
        secondAreaSecondSensor.setUnits("C");
        secondAreaSecondSensor.setLatitude(41.179230);
        secondAreaSecondSensor.setLongitude(-8.606409);
        secondAreaSecondSensor.setAltitude(139);
        secondArea.addSensorDTO(secondAreaSecondSensor);

        // Populate expectedResult array

        expectedResult[0] = firstArea;
        expectedResult[1] = secondArea;

        // Act

        File fileToRead = new File("src/test/resources/DataSet_sprint04_GA.json");
        String absolutePath = fileToRead.getAbsolutePath();
        GeographicAreaDTO[] actualResult = reader.readFile(absolutePath);

        // Assert

        assertArrayEquals(expectedResult, actualResult);
    }

}
