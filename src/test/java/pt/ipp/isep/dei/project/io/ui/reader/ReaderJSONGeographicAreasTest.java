package pt.ipp.isep.dei.project.io.ui.reader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project.dto.AreaSensorDTO;
import pt.ipp.isep.dei.project.dto.GeographicAreaDTO;
import pt.ipp.isep.dei.project.dto.LocalDTO;
import pt.ipp.isep.dei.project.dto.mappers.GeographicAreaMapper;
import pt.ipp.isep.dei.project.model.areatype.AreaType;
import pt.ipp.isep.dei.project.model.areatype.AreaTypeRepository;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicAreaRepository;
import pt.ipp.isep.dei.project.model.sensortype.SensorTypeRepository;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ReaderJSONGeographicAreasTest {
    // Common testing artifacts for testing in this class.

    @Mock
    private GeographicAreaRepository geographicAreaRepository;
    @Mock
    private SensorTypeRepository sensorTypeRepository;
    @Mock
    private AreaTypeRepository areaTypeRepository;

    ReaderJSONGeographicAreas readerJSONGeographicAreas;

    @BeforeEach
    void setUpOutput() {
        readerJSONGeographicAreas = new ReaderJSONGeographicAreas();
    }

    @Test
    void seeIfReadFileWorks() {
        // Arrange

        // First Area

        GeographicAreaDTO firstArea = new GeographicAreaDTO();
        firstArea.setName("ISEP");
        firstArea.setDescription("Campus do ISEP");
        firstArea.setTypeArea("urban area");
        firstArea.setWidth(0.261);
        firstArea.setLength(0.249);
        firstArea.setLocal(new LocalDTO(41.178553, -8.608035, 111));


        // First Sensor in First Area

        AreaSensorDTO firstAreaFirstSensor = new AreaSensorDTO();
        firstAreaFirstSensor.setId("RF12345");
        firstAreaFirstSensor.setName("Meteo station ISEP - rainfall");
        firstAreaFirstSensor.setDateStartedFunctioning("2016-11-15");
        firstAreaFirstSensor.setTypeSensor("rainfall");
        firstAreaFirstSensor.setUnits("l/m2");
        firstAreaFirstSensor.setLatitude(41.179230);
        firstAreaFirstSensor.setLongitude(-8.606409);
        firstAreaFirstSensor.setAltitude(125);

        // Second sensor in First Area

        AreaSensorDTO firstAreaSecondSensor = new AreaSensorDTO();
        firstAreaSecondSensor.setId("TT12346");
        firstAreaSecondSensor.setName("Meteo station ISEP - temperature");
        firstAreaSecondSensor.setDateStartedFunctioning("2016-11-15");
        firstAreaSecondSensor.setTypeSensor("temperature");
        firstAreaSecondSensor.setUnits("C");
        firstAreaSecondSensor.setLatitude(41.179230);
        firstAreaSecondSensor.setLongitude(-8.606409);
        firstAreaSecondSensor.setAltitude(125);


        // Second Area

        GeographicAreaDTO secondArea = new GeographicAreaDTO();
        secondArea.setName("Porto");
        secondArea.setDescription("City of Porto");
        secondArea.setTypeArea("city");
        secondArea.setWidth(10.09);
        secondArea.setLength(3.3);
        secondArea.setLocal(new LocalDTO(41.149935, -8.610857, 118.0));

        // First Sensor in Second Area

        AreaSensorDTO secondAreaFirstSensor = new AreaSensorDTO();
        secondAreaFirstSensor.setId("RF12334");
        secondAreaFirstSensor.setName("Meteo station CMP - rainfall");
        secondAreaFirstSensor.setDateStartedFunctioning("2017-11-15");
        secondAreaFirstSensor.setTypeSensor("rainfall");
        secondAreaFirstSensor.setUnits("l/m2");
        secondAreaFirstSensor.setLatitude(41.179230);
        secondAreaFirstSensor.setLongitude(-8.606409);
        secondAreaFirstSensor.setAltitude(125);


        // Second Sensor in Second Area

        AreaSensorDTO secondAreaSecondSensor = new AreaSensorDTO();
        secondAreaSecondSensor.setId("TT1236A");
        secondAreaSecondSensor.setName("Meteo station CMP - temperature");
        secondAreaSecondSensor.setDateStartedFunctioning("2017-11-16");
        secondAreaSecondSensor.setTypeSensor("temperature");
        secondAreaSecondSensor.setUnits("C");
        secondAreaSecondSensor.setLatitude(41.179230);
        secondAreaSecondSensor.setLongitude(-8.606409);
        secondAreaSecondSensor.setAltitude(125);


        // Populate expectedResult array

        GeographicArea areaOne = GeographicAreaMapper.dtoToObject(firstArea);
        GeographicArea areaTwo = GeographicAreaMapper.dtoToObject(secondArea);

        AreaType city = new AreaType("city");
        AreaType urbanArea = new AreaType("urban area");

        // Act

        File fileToRead = new File("src/test/resources/geoAreaFiles/DataSet_sprint04_GA.json");
        String absolutePath = fileToRead.getAbsolutePath();
        Mockito.doReturn(new AreaType(urbanArea.getName())).when(areaTypeRepository).getAreaTypeByName(urbanArea.getName());
        Mockito.doReturn(areaOne).when(geographicAreaRepository).createGA(areaOne.getName(), areaOne.getAreaTypeID(), areaOne.getWidth(), areaOne.getLength(), areaOne.getLocal());
        Mockito.doReturn(true).when(geographicAreaRepository).addAndPersistGA(areaOne);

        Mockito.doReturn(new AreaType(city.getName())).when(areaTypeRepository).getAreaTypeByName(city.getName());
        Mockito.doReturn(areaTwo).when(geographicAreaRepository).createGA(areaTwo.getName(), areaTwo.getAreaTypeID(), areaTwo.getWidth(), areaTwo.getLength(), areaTwo.getLocal());
        Mockito.doReturn(true).when(geographicAreaRepository).addAndPersistGA(areaTwo);
        double areasAdded = readerJSONGeographicAreas.readJSONFileAndAddGeoAreas(absolutePath, geographicAreaRepository, sensorTypeRepository, areaTypeRepository);

        // Assert

        assertEquals(2, areasAdded);
    }


    @Test
    void seeIfReadFileWorksWrongPath() {
        // Arrange

        String invalidPath = ("invalidfilepath");
        // Act

        double actualResult = readerJSONGeographicAreas.readJSONFileAndAddGeoAreas(invalidPath, geographicAreaRepository, sensorTypeRepository, areaTypeRepository);

        // Assert

        assertEquals(0, actualResult);
    }

    @Test
    void seeIfReadFileWorksAndSkipsSensorsWithWrongDateFormat() {
        // Arrange
        //  Mapper mapper = new Mapper();

        String urbanAreaString = "urban area";
        String cityString = "city";
        // First Area

        GeographicAreaDTO firstArea = new GeographicAreaDTO();
        firstArea.setName("ISEP");
        firstArea.setDescription("Campus do ISEP");
        firstArea.setTypeArea(urbanAreaString);
        firstArea.setWidth(0.261);
        firstArea.setLength(0.249);
        firstArea.setLocal(new LocalDTO(41.178553, -8.608035, 111));

        // First Sensor in First Area

        AreaSensorDTO firstAreaFirstSensor = new AreaSensorDTO();
        firstAreaFirstSensor.setId("TT12346");
        firstAreaFirstSensor.setName("Meteo station ISEP - temperature");
        firstAreaFirstSensor.setDateStartedFunctioning("2016-11-15");
        firstAreaFirstSensor.setTypeSensor("temperature");
        firstAreaFirstSensor.setUnits("C");
        firstAreaFirstSensor.setLatitude(41.179230);
        firstAreaFirstSensor.setLongitude(-8.606409);
        firstAreaFirstSensor.setAltitude(125);

        // Second Area

        GeographicAreaDTO secondArea = new GeographicAreaDTO();
        secondArea.setName("Porto");
        secondArea.setDescription("City of Porto");
        secondArea.setTypeArea(cityString);
        secondArea.setWidth(10.09);
        secondArea.setLength(3.30);
        secondArea.setLocal(new LocalDTO(41.149935, -8.610857, 118));


        // First Sensor in Second Area

        AreaSensorDTO secondAreaFirstSensor = new AreaSensorDTO();
        secondAreaFirstSensor.setId("RF12334");
        secondAreaFirstSensor.setName("Meteo station CMP - rainfall");
        secondAreaFirstSensor.setDateStartedFunctioning("2017-11-15");
        secondAreaFirstSensor.setTypeSensor("rainfall");
        secondAreaFirstSensor.setUnits("l/m2");
        secondAreaFirstSensor.setLatitude(41.179230);
        secondAreaFirstSensor.setLongitude(-8.606409);
        secondAreaFirstSensor.setAltitude(125);


        // Second Sensor in Second Area

        AreaSensorDTO secondAreaSecondSensor = new AreaSensorDTO();
        secondAreaSecondSensor.setId("TT1236A");
        secondAreaSecondSensor.setName("Meteo station CMP - temperature");
        secondAreaSecondSensor.setDateStartedFunctioning("2017-11-16");
        secondAreaSecondSensor.setTypeSensor("temperature");
        secondAreaSecondSensor.setUnits("C");
        secondAreaSecondSensor.setLatitude(41.179230);
        secondAreaSecondSensor.setLongitude(-8.606409);
        secondAreaSecondSensor.setAltitude(125);

        // Populate expectedResult array

        GeographicArea areaOne = GeographicAreaMapper.dtoToObject(firstArea);
        GeographicArea areaTwo = GeographicAreaMapper.dtoToObject(secondArea);


        //Act
        Mockito.doReturn(new AreaType(urbanAreaString)).when(areaTypeRepository).getAreaTypeByName(urbanAreaString);
        Mockito.doReturn(areaOne).when(geographicAreaRepository).createGA(areaOne.getName(), areaOne.getAreaTypeID(), areaOne.getWidth(), areaOne.getLength(), areaOne.getLocal());
        Mockito.doReturn(true).when(geographicAreaRepository).addAndPersistGA(areaOne);

        Mockito.doReturn(new AreaType(cityString)).when(areaTypeRepository).getAreaTypeByName(cityString);
        Mockito.doReturn(areaTwo).when(geographicAreaRepository).createGA(areaTwo.getName(), areaTwo.getAreaTypeID(), areaTwo.getWidth(), areaTwo.getLength(), areaTwo.getLocal());
        Mockito.doReturn(true).when(geographicAreaRepository).addAndPersistGA(areaTwo);

        File fileToRead = new File("src/test/resources/geoAreaFiles/InvalidJSONWrongDates.json");
        String absolutePath = fileToRead.getAbsolutePath();
        double areasAdded = readerJSONGeographicAreas.readJSONFileAndAddGeoAreas(absolutePath, geographicAreaRepository, sensorTypeRepository, areaTypeRepository);

        // Assert

        assertEquals(2, areasAdded);
    }
}
