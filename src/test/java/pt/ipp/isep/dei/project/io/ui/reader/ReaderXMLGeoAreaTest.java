package pt.ipp.isep.dei.project.io.ui.reader;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.w3c.dom.NodeList;
import pt.ipp.isep.dei.project.controllercli.ReaderController;
import pt.ipp.isep.dei.project.dto.GeographicAreaDTO;
import pt.ipp.isep.dei.project.dto.LocalDTO;
import pt.ipp.isep.dei.project.dto.mappers.GeographicAreaMapper;
import pt.ipp.isep.dei.project.model.areatype.AreaTypeRepository;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicAreaRepository;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class ReaderXMLGeoAreaTest {
    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;
    @Mock
    ReaderController readerController;
    @Mock
    private AreaTypeRepository areaTypeRepository;
    private static final Logger logger = Logger.getLogger(ReaderController.class.getName());
    @Mock
    private GeographicAreaRepository geographicAreaRepository;
    @InjectMocks
    private ReaderXMLGeoArea validReaderXMLGeoArea;

    @BeforeEach
    void setUpOutput() {
        ByteArrayOutputStream testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
        logger.setLevel(Level.FINE);
    }

    @AfterEach
    void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    @Test
    void seeIfReadFileXMLGeoAreaWorksWrongPath() {
        // Arrange
        // Act

        File fileToRead = new File("src/test/resources/readingsFiles/test1XMLReadings.xml");
        String absolutePath = fileToRead.getAbsolutePath();
        double areasAdded = validReaderXMLGeoArea.readFileXMLAndAddAreas(absolutePath, geographicAreaRepository, areaTypeRepository);

        // Assert

        assertEquals(0, areasAdded);
    }

    @Test
    void seeIfReadFileXMLGeoAreaWorksWithoutGeoAreas() {
        // Arrange

        // Act

        File fileToRead = new File("src/test/resources/geoAreaFiles/DataSet_sprint05_GA_test_no_GAs.xml");
        String absolutePath = fileToRead.getAbsolutePath();

        double areasAdded = validReaderXMLGeoArea.readFileXMLAndAddAreas(absolutePath, geographicAreaRepository, areaTypeRepository);

        // Assert

        assertEquals(0, areasAdded);
    }

    @org.junit.jupiter.api.Test
    void seeIfReadFileXMLGeoAreaWorksWrongPathNotXml() {
        // Arrange
        // Act

        File fileToRead = new File("src/test/resources/readingsFiles/test1XMLReadings.json");
        String absolutePath = fileToRead.getAbsolutePath();

        // Assert

        assertThrows(IllegalArgumentException.class, () -> validReaderXMLGeoArea.readFileXMLAndAddAreas(absolutePath, geographicAreaRepository, areaTypeRepository));
    }

    @org.junit.jupiter.api.Test
    void seeIfReadFileXMLGeoAreaWorks() {
        // Arrange

        // Act

        File fileToRead = new File("src/test/resources/geoAreaFiles/DataSet_sprint05_GA.xml");
        String absolutePath = fileToRead.getAbsolutePath();

        Mockito.when(readerController.addGeoAreaNodeListToList(ArgumentMatchers.any(NodeList.class),
                ArgumentMatchers.any(GeographicAreaRepository.class), ArgumentMatchers.any(AreaTypeRepository.class))).thenReturn(2);
        double areasAdded = validReaderXMLGeoArea.readFileXMLAndAddAreas(absolutePath, geographicAreaRepository, areaTypeRepository);

        // Assert

        assertEquals(2, areasAdded);

    }

    @Test
    void seeIfReadFileXMLGeoAreaWorksWithAnotherDateFormat() {
        // Arrange
        // Act

        File fileToRead = new File("src/test/resources/geoAreaFiles/DataSet_sprint05_GA_test_wrong_date.xml");
        String absolutePath = fileToRead.getAbsolutePath();

        Mockito.when(readerController.addGeoAreaNodeListToList(ArgumentMatchers.any(NodeList.class),
                ArgumentMatchers.any(GeographicAreaRepository.class), ArgumentMatchers.any(AreaTypeRepository.class))).thenReturn(2);
        double areasAdded = validReaderXMLGeoArea.readFileXMLAndAddAreas(absolutePath, geographicAreaRepository, areaTypeRepository);

        // Assert

        assertEquals(2, areasAdded);
    }

    @Test
    void seeIfReadFileXMLGeoAreaWorksWithOneGeoArea() {
        // Arrange

        // Act

        File fileToRead = new File("src/test/resources/geoAreaFiles/DataSet_sprint05_GA_test_one_GA.xml");
        String absolutePath = fileToRead.getAbsolutePath();
        Mockito.when(readerController.addGeoAreaNodeListToList(ArgumentMatchers.any(NodeList.class),
                ArgumentMatchers.any(GeographicAreaRepository.class), ArgumentMatchers.any(AreaTypeRepository.class))).thenReturn(1);
        double areasAdded = validReaderXMLGeoArea.readFileXMLAndAddAreas(absolutePath, geographicAreaRepository, areaTypeRepository);

        // Assert

        assertEquals(1, areasAdded);
    }

    @Test
    void seeIfReadAndAddAreasWorks() {
        // Arrange

        // Act

        File fileToRead = new File("src/test/resources/geoAreaFiles/DataSet_sprint05_GA.xml");
        String absolutePath = fileToRead.getAbsolutePath();

        Mockito.when(readerController.addGeoAreaNodeListToList(ArgumentMatchers.any(NodeList.class),
                ArgumentMatchers.any(GeographicAreaRepository.class), ArgumentMatchers.any(AreaTypeRepository.class))).thenReturn(2);
        double areasAdded = validReaderXMLGeoArea.readFileXMLAndAddAreas(absolutePath, geographicAreaRepository, areaTypeRepository);

        // Assert

        assertEquals(2, areasAdded);
    }

    @Test
    void seeIfReadAndAddAreasWorksWithRepeatedArea() {
        // Arrange

        GeographicAreaDTO firstArea = new GeographicAreaDTO();
        firstArea.setName("ISEP");
        firstArea.setDescription("Campus do ISEP");
        firstArea.setTypeArea("urban area");
        firstArea.setWidth(0.261);
        firstArea.setLength(0.249);
        firstArea.setLocal(new LocalDTO(41.178553, -8.608035, 111));

        // Populate expectedResult array

        GeographicArea areaOne = GeographicAreaMapper.dtoToObject(firstArea);

        List<GeographicArea> validList = new ArrayList<>();
        validList.add(areaOne);

        // Act

        File fileToRead = new File("src/test/resources/geoAreaFiles/DataSet_sprint05_GA_test_one_GA.xml");
        String absolutePath = fileToRead.getAbsolutePath();
        Mockito.when(readerController.addGeoAreaNodeListToList(ArgumentMatchers.any(NodeList.class),
                ArgumentMatchers.any(GeographicAreaRepository.class), ArgumentMatchers.any(AreaTypeRepository.class))).thenReturn(0);
        double areasAdded = validReaderXMLGeoArea.readFileXMLAndAddAreas(absolutePath, geographicAreaRepository, areaTypeRepository);

        // Assert

        assertEquals(0, areasAdded);
    }
}
