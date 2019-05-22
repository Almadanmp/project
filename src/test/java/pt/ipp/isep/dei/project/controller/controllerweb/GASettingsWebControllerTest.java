package pt.ipp.isep.dei.project.controller.controllerweb;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pt.ipp.isep.dei.project.dto.GeographicAreaDTO;
import pt.ipp.isep.dei.project.dto.LocalDTO;
import pt.ipp.isep.dei.project.dto.mappers.GeographicAreaMapper;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicAreaRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith({MockitoExtension.class})
class GASettingsWebControllerTest {

    @Autowired
    private MockMvc mvc;
    @Mock
    private GeographicAreaRepository geographicAreaRepository;
    @InjectMocks
    private GASettingsWebController gaSettingsWebController;

    @Before
    public void insertData() {
        MockitoAnnotations.initMocks(this);

        this.mvc = MockMvcBuilders.standaloneSetup(gaSettingsWebController).build();

    }

    @Test
    void seeIfCreateGeoAreaWorks() {
        //Arrange

        GeographicAreaDTO validGeographicAreaDTO = new GeographicAreaDTO();
        LocalDTO localDTO = new LocalDTO();

        localDTO.setLatitude(41);
        localDTO.setLongitude(-8);
        localDTO.setAltitude(100);

        validGeographicAreaDTO.setLocal(localDTO);
        validGeographicAreaDTO.setDescription("3rd biggest city");
        validGeographicAreaDTO.setName("Gaia");
        validGeographicAreaDTO.setId(66L);
        validGeographicAreaDTO.setWidth(100);
        validGeographicAreaDTO.setLength(500);
        validGeographicAreaDTO.setTypeArea("urban area");

        Mockito.doReturn(true).when(geographicAreaRepository).addAndPersistDTO(any(GeographicAreaDTO.class));

        ResponseEntity<String> expectedResult = new ResponseEntity<>("The Geographic Area has been created.", HttpStatus.CREATED);

        //Act
        ResponseEntity<Object> actualResult = gaSettingsWebController.createGeoArea(validGeographicAreaDTO);

        //Assert
        assertEquals(expectedResult, actualResult);
    }


    @Test
    void seeIfCreateGeoAreaDoesntWorkIsRepeated() {
        //Arrange

        GeographicAreaDTO validGeographicAreaDTO = new GeographicAreaDTO();
        LocalDTO localDTO = new LocalDTO();

        localDTO.setLatitude(41);
        localDTO.setLongitude(-8);
        localDTO.setAltitude(100);

        validGeographicAreaDTO.setLocal(localDTO);
        validGeographicAreaDTO.setDescription("3rd biggest city");
        validGeographicAreaDTO.setName("Gaia");
        validGeographicAreaDTO.setId(66L);
        validGeographicAreaDTO.setWidth(100);
        validGeographicAreaDTO.setLength(500);
        validGeographicAreaDTO.setTypeArea("urban area");

        Mockito.doReturn(false).when(geographicAreaRepository).addAndPersistDTO(validGeographicAreaDTO);

        ResponseEntity<String> expectedResult = new ResponseEntity<>("The Geographic Area hasn't been created. That Area already exists.", HttpStatus.CONFLICT);

        //Act
        ResponseEntity<Object> actualResult = gaSettingsWebController.createGeoArea(validGeographicAreaDTO);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfCreateGeoAreaDoesntWorkIDNull() {
        //Arrange

        GeographicAreaDTO validGeographicAreaDTO = new GeographicAreaDTO();
        LocalDTO localDTO = new LocalDTO();

        localDTO.setLatitude(41);
        localDTO.setLongitude(-8);
        localDTO.setAltitude(100);

        validGeographicAreaDTO.setLocal(localDTO);
        validGeographicAreaDTO.setDescription("3rd biggest city");
        validGeographicAreaDTO.setName("Gaia");
        validGeographicAreaDTO.setWidth(100);
        validGeographicAreaDTO.setLength(500);
        validGeographicAreaDTO.setTypeArea("urban area");

//        Mockito.doReturn(false).when(geographicAreaRepository).addAndPersistDTO(any(GeographicAreaDTO.class));

        ResponseEntity<String> expectedResult = new ResponseEntity<>("The Geographic Area hasn't been created. You have entered an invalid Area.", HttpStatus.BAD_REQUEST);

        //Act
        ResponseEntity<Object> actualResult = gaSettingsWebController.createGeoArea(validGeographicAreaDTO);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfCreateGeoAreaDoesntWorkNameNull() {
        //Arrange

        GeographicAreaDTO validGeographicAreaDTO = new GeographicAreaDTO();
        LocalDTO localDTO = new LocalDTO();

        localDTO.setLatitude(41);
        localDTO.setLongitude(-8);
        localDTO.setAltitude(100);

        validGeographicAreaDTO.setLocal(localDTO);
        validGeographicAreaDTO.setDescription("3rd biggest city");
        validGeographicAreaDTO.setId(2L);
        validGeographicAreaDTO.setWidth(100);
        validGeographicAreaDTO.setLength(500);
        validGeographicAreaDTO.setTypeArea("urban area");

//        Mockito.doReturn(false).when(geographicAreaRepository).addAndPersistDTO(any(GeographicAreaDTO.class));

        ResponseEntity<String> expectedResult = new ResponseEntity<>("The Geographic Area hasn't been created. You have entered an invalid Area.", HttpStatus.BAD_REQUEST);

        //Act
        ResponseEntity<Object> actualResult = gaSettingsWebController.createGeoArea(validGeographicAreaDTO);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfCreateGeoAreaDoesntWorkTypeNull() {
        //Arrange

        GeographicAreaDTO validGeographicAreaDTO = new GeographicAreaDTO();
        LocalDTO localDTO = new LocalDTO();

        localDTO.setLatitude(41);
        localDTO.setLongitude(-8);
        localDTO.setAltitude(100);

        validGeographicAreaDTO.setLocal(localDTO);
        validGeographicAreaDTO.setDescription("3rd biggest city");
        validGeographicAreaDTO.setId(2L);
        validGeographicAreaDTO.setName("Porto");
        validGeographicAreaDTO.setWidth(100);
        validGeographicAreaDTO.setLength(500);

//        Mockito.doReturn(false).when(geographicAreaRepository).addAndPersistDTO(any(GeographicAreaDTO.class));

        ResponseEntity<String> expectedResult = new ResponseEntity<>("The Geographic Area hasn't been created. You have entered an invalid Area.", HttpStatus.BAD_REQUEST);

        //Act
        ResponseEntity<Object> actualResult = gaSettingsWebController.createGeoArea(validGeographicAreaDTO);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfCreateGeoAreaDoesntWorkLocalNull() {
        //Arrange

        GeographicAreaDTO validGeographicAreaDTO = new GeographicAreaDTO();

        validGeographicAreaDTO.setDescription("3rd biggest city");
        validGeographicAreaDTO.setId(2L);
        validGeographicAreaDTO.setWidth(100);
        validGeographicAreaDTO.setLength(500);
        validGeographicAreaDTO.setTypeArea("urban area");
//        Mockito.doReturn(false).when(geographicAreaRepository).addAndPersistDTO(any(GeographicAreaDTO.class));

        ResponseEntity<String> expectedResult = new ResponseEntity<>("The Geographic Area hasn't been created. You have entered an invalid Area.", HttpStatus.BAD_REQUEST);

        //Act
        ResponseEntity<Object> actualResult = gaSettingsWebController.createGeoArea(validGeographicAreaDTO);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfCreateGeoAreaWorksAllNull() {
        //Arrange

        GeographicAreaDTO validGeographicAreaDTO = new GeographicAreaDTO();

        validGeographicAreaDTO.setDescription("3rd biggest city");
        validGeographicAreaDTO.setWidth(100);
        validGeographicAreaDTO.setLength(500);

        ResponseEntity<String> expectedResult = new ResponseEntity<>("The Geographic Area hasn't been created. You have entered an invalid Area.", HttpStatus.BAD_REQUEST);

        //Act
        ResponseEntity<Object> actualResult = gaSettingsWebController.createGeoArea(validGeographicAreaDTO);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfCreateGeoAreaIDNotNull() {
        //Arrange

        GeographicAreaDTO validGeographicAreaDTO = new GeographicAreaDTO();
        validGeographicAreaDTO.setId(66L);
        validGeographicAreaDTO.setWidth(100);
        validGeographicAreaDTO.setLength(500);

        ResponseEntity<String> expectedResult = new ResponseEntity<>("The Geographic Area hasn't been created. You have entered an invalid Area.", HttpStatus.BAD_REQUEST);

        //Act
        ResponseEntity<Object> actualResult = gaSettingsWebController.createGeoArea(validGeographicAreaDTO);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfCreateGeoAreaNameNotNull() {
        //Arrange

        GeographicAreaDTO validGeographicAreaDTO = new GeographicAreaDTO();
        validGeographicAreaDTO.setName("Gaia");
        validGeographicAreaDTO.setWidth(100);
        validGeographicAreaDTO.setLength(500);

        ResponseEntity<String> expectedResult = new ResponseEntity<>("The Geographic Area hasn't been created. You have entered an invalid Area.", HttpStatus.BAD_REQUEST);

        //Act
        ResponseEntity<Object> actualResult = gaSettingsWebController.createGeoArea(validGeographicAreaDTO);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfCreateGeoAreaTypeNotNull() {
        //Arrange

        GeographicAreaDTO validGeographicAreaDTO = new GeographicAreaDTO();
        validGeographicAreaDTO.setTypeArea("urban");
        validGeographicAreaDTO.setWidth(100);
        validGeographicAreaDTO.setLength(500);

        ResponseEntity<String> expectedResult = new ResponseEntity<>("The Geographic Area hasn't been created. You have entered an invalid Area.", HttpStatus.BAD_REQUEST);

        //Act
        ResponseEntity<Object> actualResult = gaSettingsWebController.createGeoArea(validGeographicAreaDTO);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfCreateGeoAreaDoesntWorkLocalNotNull() {
        //Arrange

        GeographicAreaDTO validGeographicAreaDTO = new GeographicAreaDTO();
        LocalDTO localDTO = new LocalDTO();

        localDTO.setLatitude(41);
        localDTO.setLongitude(-8);
        localDTO.setAltitude(100);

        validGeographicAreaDTO.setLocal(localDTO);
        validGeographicAreaDTO.setDescription("3rd biggest city");
        validGeographicAreaDTO.setWidth(100);
        validGeographicAreaDTO.setLength(500);

//        Mockito.doReturn(false).when(geographicAreaRepository).addAndPersistDTO(any(GeographicAreaDTO.class));

        ResponseEntity<String> expectedResult = new ResponseEntity<>("The Geographic Area hasn't been created. You have entered an invalid Area.", HttpStatus.BAD_REQUEST);

        //Act
        ResponseEntity<Object> actualResult = gaSettingsWebController.createGeoArea(validGeographicAreaDTO);

        //Assert
        assertEquals(expectedResult, actualResult);
    }


    @Test
    void getAllGeoAreasDTO() {
            //Arrange
        GeographicAreaDTO validGeographicAreaDTO = new GeographicAreaDTO();
        LocalDTO localDTO = new LocalDTO();

        localDTO.setLatitude(41);
        localDTO.setLongitude(-8);
        localDTO.setAltitude(100);

        validGeographicAreaDTO.setLocal(localDTO);
        validGeographicAreaDTO.setDescription("3rd biggest city");
        validGeographicAreaDTO.setName("Gaia");
        validGeographicAreaDTO.setId(66L);
        validGeographicAreaDTO.setWidth(100);
        validGeographicAreaDTO.setLength(500);
        validGeographicAreaDTO.setTypeArea("urban area");

        List<GeographicAreaDTO> geographicAreas = new ArrayList<>();
        geographicAreas.add(validGeographicAreaDTO);

            Mockito.when(geographicAreaRepository.getAllDTO()).thenReturn(geographicAreas);

            ResponseEntity<Object> expectedResult = new ResponseEntity<>(geographicAreas, HttpStatus.OK);

            //Act
            ResponseEntity<Object> actualResult = gaSettingsWebController.getAllGeographicAreas();

            //Assert
            assertEquals(expectedResult, actualResult);

    }

//    @Test
//    void addDaughterAreaInvalidMother() {
//        GeographicAreaDTO validGeographicAreaDTO = new GeographicAreaDTO();
//
//        validGeographicAreaDTO.setDescription("3rd biggest city");
//        validGeographicAreaDTO.setId(2L);
//        validGeographicAreaDTO.setWidth(100);
//        validGeographicAreaDTO.setLength(500);
//        validGeographicAreaDTO.setTypeArea("urban area");
//
//
//        ResponseEntity<String> expectedResult = new ResponseEntity<>("The Geographic Area hasn't been added. You have entered a repeated or invalid Area.", HttpStatus.CONFLICT);
//
//        //Act
//        ResponseEntity<Object> actualResult = gaSettingsWebController.addDaughterArea(1L, 3L);
//
//        //Assert
//        assertEquals(expectedResult, actualResult);
//    }
//
//    @Test
//    void addDaughterAreaInvalidDaughter() {
//        GeographicAreaDTO validGeographicAreaDTO = new GeographicAreaDTO();
//
//        validGeographicAreaDTO.setDescription("3rd biggest city");
//        validGeographicAreaDTO.setId(2L);
//        validGeographicAreaDTO.setWidth(100);
//        validGeographicAreaDTO.setLength(500);
//        validGeographicAreaDTO.setTypeArea("urban area");
//
//        ResponseEntity<String> expectedResult = new ResponseEntity<>("The Geographic Area hasn't been added. You have entered a repeated or invalid Area.", HttpStatus.CONFLICT);
//
//        //Act
//        ResponseEntity<Object> actualResult = gaSettingsWebController.addDaughterArea(6L, validGeographicAreaDTO.getId());
//
//        //Assert
//        assertEquals(expectedResult, actualResult);
//    }
//
//    @Test
//    void addDaughterArea() {
//        GeographicAreaDTO motherDTO = new GeographicAreaDTO();
//
//        motherDTO.setDescription("3rd biggest city");
//        motherDTO.setId(1L);
//        motherDTO.setWidth(100);
//        motherDTO.setLength(500);
//        motherDTO.setTypeArea("urban area");
//
//        GeographicAreaDTO daughterDTO = new GeographicAreaDTO();
//
//        daughterDTO.setDescription("3rd biggest city");
//        daughterDTO.setId(2L);
//        daughterDTO.setWidth(100);
//        daughterDTO.setLength(500);
//        daughterDTO.setTypeArea("urban area");
//
//        Mockito.doReturn(motherDTO).when(geographicAreaRepository).getDTOByIdWithMother(1L);
//
//        Mockito.doReturn(daughterDTO).when(geographicAreaRepository).getDTOByIdWithMother(2L);
//
//        Mockito.doReturn(true).when(geographicAreaRepository).updateAreaDTOWithMother(motherDTO);
//
//
//        ResponseEntity<String> expectedResult = new ResponseEntity<>("The Geographic Area has been added.", HttpStatus.CREATED);
//
//        //Act
//        ResponseEntity<Object> actualResult = gaSettingsWebController.addDaughterArea(daughterDTO.getId(), motherDTO.getId());
//
//        //Assert
//        assertEquals(expectedResult, actualResult);
//    }

}