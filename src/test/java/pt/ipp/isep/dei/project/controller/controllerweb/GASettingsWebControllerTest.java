package pt.ipp.isep.dei.project.controller.controllerweb;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import pt.ipp.isep.dei.project.dto.GeographicAreaDTO;
import pt.ipp.isep.dei.project.dto.GeographicAreaPlainLocalDTO;
import pt.ipp.isep.dei.project.dto.LocalDTO;
import pt.ipp.isep.dei.project.model.areatype.AreaType;
import pt.ipp.isep.dei.project.model.areatype.AreaTypeRepository;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicAreaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@WebMvcTest
@ContextConfiguration(classes = HibernateJpaAutoConfiguration.class)
class GASettingsWebControllerTest {

    @Mock
    private GeographicAreaRepository geographicAreaRepository;
    @Mock
    private AreaTypeRepository areaTypeRepository;
    @InjectMocks
    private GASettingsWebController gaSettingsWebController;

    @BeforeEach
    void insertData() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void seeIfCreateGeoAreaWorks() {
        // Arrange

        GeographicAreaPlainLocalDTO validGeographicAreaDTO = new GeographicAreaPlainLocalDTO();

        validGeographicAreaDTO.setLatitude(60D);
        validGeographicAreaDTO.setLongitude(-50D);
        validGeographicAreaDTO.setAltitude(100D);
        validGeographicAreaDTO.setDescription("4rd biggest city");
        validGeographicAreaDTO.setName("Santa Maria de Lamas");
        validGeographicAreaDTO.setWidth(100);
        validGeographicAreaDTO.setLength(500);
        validGeographicAreaDTO.setTypeArea("Urban Area");

        Mockito.doReturn(true).when(geographicAreaRepository).addAndPersistPlainDTO(any(GeographicAreaPlainLocalDTO.class));

        Link link = linkTo(methodOn(GASettingsWebController.class).getAllGeographicAreas()).withRel("See all geographic areas");

        validGeographicAreaDTO.add(link);

        // Act

        ResponseEntity<Object> actualResult = gaSettingsWebController.createGeoArea(validGeographicAreaDTO);

        // Assert

        assertEquals(HttpStatus.CREATED, actualResult.getStatusCode());

    }


    @Test
    void seeIfCreateGeoAreaDoesntWorkIsRepeated() {
        // Arrange

        GeographicAreaPlainLocalDTO validGeographicAreaDTO = new GeographicAreaPlainLocalDTO();

        validGeographicAreaDTO.setLatitude(41D);
        validGeographicAreaDTO.setLongitude(-8D);
        validGeographicAreaDTO.setAltitude(100D);
        validGeographicAreaDTO.setDescription("3rd biggest city");
        validGeographicAreaDTO.setName("Gaia");
        validGeographicAreaDTO.setWidth(100);
        validGeographicAreaDTO.setLength(500);
        validGeographicAreaDTO.setTypeArea("urban area");

        Mockito.doReturn(false).when(geographicAreaRepository).addAndPersistPlainDTO(validGeographicAreaDTO);

        ResponseEntity<String> expectedResult = new ResponseEntity<>("The Geographic Area hasn't been created. That Area already exists.", HttpStatus.CONFLICT);

        // Act
        
        ResponseEntity<Object> actualResult = gaSettingsWebController.createGeoArea(validGeographicAreaDTO);

        // Assert
        
        assertEquals(expectedResult, actualResult);
    }
    
    @Test
    void seeIfCreateGeoAreaDoesntWorkNameNull() {
        // Arrange

        GeographicAreaPlainLocalDTO validGeographicAreaDTO = new GeographicAreaPlainLocalDTO();

        validGeographicAreaDTO.setLatitude(41D);
        validGeographicAreaDTO.setLongitude(-8D);
        validGeographicAreaDTO.setAltitude(100D);
        validGeographicAreaDTO.setDescription("3rd biggest city");
        validGeographicAreaDTO.setWidth(100);
        validGeographicAreaDTO.setLength(500);
        validGeographicAreaDTO.setTypeArea("urban area");

//        Mockito.doReturn(false).when(geographicAreaRepository).addAndPersistDTO(any(GeographicAreaDTO.class));

        ResponseEntity<String> expectedResult = new ResponseEntity<>("The Geographic Area hasn't been created. You have entered an invalid Area.", HttpStatus.BAD_REQUEST);

        // Act
        ResponseEntity<Object> actualResult = gaSettingsWebController.createGeoArea(validGeographicAreaDTO);

        // Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfCreateGeoAreaDoesntWorkTypeNull() {
        // Arrange

        GeographicAreaPlainLocalDTO validGeographicAreaDTO = new GeographicAreaPlainLocalDTO();

        validGeographicAreaDTO.setLatitude(41D);
        validGeographicAreaDTO.setLongitude(-8D);
        validGeographicAreaDTO.setAltitude(100D);
        validGeographicAreaDTO.setDescription("3rd biggest city");
        validGeographicAreaDTO.setName("Porto");
        validGeographicAreaDTO.setWidth(100);
        validGeographicAreaDTO.setLength(500);

//        Mockito.doReturn(false).when(geographicAreaRepository).addAndPersistDTO(any(GeographicAreaDTO.class));

        ResponseEntity<String> expectedResult = new ResponseEntity<>("The Geographic Area hasn't been created. You have entered an invalid Area.", HttpStatus.BAD_REQUEST);

        // Act
        ResponseEntity<Object> actualResult = gaSettingsWebController.createGeoArea(validGeographicAreaDTO);

        // Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfCreateGeoAreaDoesntWorkLatitudeNull() {
        // Arrange

        GeographicAreaPlainLocalDTO validGeographicAreaDTO = new GeographicAreaPlainLocalDTO();

        validGeographicAreaDTO.setLongitude(-8D);
        validGeographicAreaDTO.setAltitude(100D);
        validGeographicAreaDTO.setDescription("3rd biggest city");
        validGeographicAreaDTO.setName("Porto");
        validGeographicAreaDTO.setWidth(100);
        validGeographicAreaDTO.setLength(500);
        validGeographicAreaDTO.setTypeArea("urban area");
//        Mockito.doReturn(false).when(geographicAreaRepository).addAndPersistDTO(any(GeographicAreaDTO.class));

        ResponseEntity<String> expectedResult = new ResponseEntity<>("The Geographic Area hasn't been created. You have entered an invalid Area.", HttpStatus.BAD_REQUEST);

        // Act

        ResponseEntity<Object> actualResult = gaSettingsWebController.createGeoArea(validGeographicAreaDTO);

        // Assert

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfCreateGeoAreaDoesntWorkLongitudeNull() {
        // Arrange

        GeographicAreaPlainLocalDTO validGeographicAreaDTO = new GeographicAreaPlainLocalDTO();

        validGeographicAreaDTO.setLatitude(41D);
        validGeographicAreaDTO.setAltitude(100D);
        validGeographicAreaDTO.setDescription("3rd biggest city");
        validGeographicAreaDTO.setName("Porto");
        validGeographicAreaDTO.setWidth(100);
        validGeographicAreaDTO.setLength(500);
        validGeographicAreaDTO.setTypeArea("urban area");
//        Mockito.doReturn(false).when(geographicAreaRepository).addAndPersistDTO(any(GeographicAreaDTO.class));

        ResponseEntity<String> expectedResult = new ResponseEntity<>("The Geographic Area hasn't been created. You have entered an invalid Area.", HttpStatus.BAD_REQUEST);

        // Act

        ResponseEntity<Object> actualResult = gaSettingsWebController.createGeoArea(validGeographicAreaDTO);

        // Assert

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfCreateGeoAreaDoesntWorkAltitudeNull() {
        // Arrange

        GeographicAreaPlainLocalDTO validGeographicAreaDTO = new GeographicAreaPlainLocalDTO();

        validGeographicAreaDTO.setLatitude(41D);
        validGeographicAreaDTO.setLongitude(-8D);
        validGeographicAreaDTO.setDescription("3rd biggest city");
        validGeographicAreaDTO.setName("Porto");
        validGeographicAreaDTO.setWidth(100);
        validGeographicAreaDTO.setLength(500);
        validGeographicAreaDTO.setTypeArea("urban area");
//        Mockito.doReturn(false).when(geographicAreaRepository).addAndPersistDTO(any(GeographicAreaDTO.class));

        ResponseEntity<String> expectedResult = new ResponseEntity<>("The Geographic Area hasn't been created. You have entered an invalid Area.", HttpStatus.BAD_REQUEST);

        // Act

        ResponseEntity<Object> actualResult = gaSettingsWebController.createGeoArea(validGeographicAreaDTO);

        // Assert

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfCreateGeoAreaWorksAllNull() {
        // Arrange

        GeographicAreaPlainLocalDTO validGeographicAreaDTO = new GeographicAreaPlainLocalDTO();

        validGeographicAreaDTO.setDescription("3rd biggest city");
        validGeographicAreaDTO.setWidth(100);
        validGeographicAreaDTO.setLength(500);

        ResponseEntity<String> expectedResult = new ResponseEntity<>("The Geographic Area hasn't been created. You have entered an invalid Area.", HttpStatus.BAD_REQUEST);

        // Act
        ResponseEntity<Object> actualResult = gaSettingsWebController.createGeoArea(validGeographicAreaDTO);

        // Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfCreateGeoAreaIDNotNull() {
        // Arrange

        GeographicAreaPlainLocalDTO validGeographicAreaDTO = new GeographicAreaPlainLocalDTO();
        validGeographicAreaDTO.setWidth(100);
        validGeographicAreaDTO.setLength(500);

        ResponseEntity<String> expectedResult = new ResponseEntity<>("The Geographic Area hasn't been created. You have entered an invalid Area.", HttpStatus.BAD_REQUEST);

        // Act
        ResponseEntity<Object> actualResult = gaSettingsWebController.createGeoArea(validGeographicAreaDTO);

        // Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfCreateGeoAreaNameNotNull() {
        // Arrange

        GeographicAreaPlainLocalDTO validGeographicAreaDTO = new GeographicAreaPlainLocalDTO();
        validGeographicAreaDTO.setName("Gaia");
        validGeographicAreaDTO.setWidth(100);
        validGeographicAreaDTO.setLength(500);

        ResponseEntity<String> expectedResult = new ResponseEntity<>("The Geographic Area hasn't been created. You have entered an invalid Area.", HttpStatus.BAD_REQUEST);

        // Act

        ResponseEntity<Object> actualResult = gaSettingsWebController.createGeoArea(validGeographicAreaDTO);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfCreateGeoAreaTypeNotNull() {
        // Arrange

        GeographicAreaPlainLocalDTO validGeographicAreaDTO = new GeographicAreaPlainLocalDTO();
        validGeographicAreaDTO.setTypeArea("urban");
        validGeographicAreaDTO.setWidth(100);
        validGeographicAreaDTO.setLength(500);

        ResponseEntity<String> expectedResult = new ResponseEntity<>("The Geographic Area hasn't been created. You have entered an invalid Area.", HttpStatus.BAD_REQUEST);

        // Act
        ResponseEntity<Object> actualResult = gaSettingsWebController.createGeoArea(validGeographicAreaDTO);

        // Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfCreateGeoAreaDoesntWorkLocalNotNull() {
        // Arrange

        GeographicAreaPlainLocalDTO validGeographicAreaDTO = new GeographicAreaPlainLocalDTO();

        validGeographicAreaDTO.setLatitude(41D);
        validGeographicAreaDTO.setLongitude(-8D);
        validGeographicAreaDTO.setAltitude(100D);
        validGeographicAreaDTO.setDescription("3rd biggest city");
        validGeographicAreaDTO.setWidth(100);
        validGeographicAreaDTO.setLength(500);

//        Mockito.doReturn(false).when(geographicAreaRepository).addAndPersistDTO(any(GeographicAreaDTO.class));

        ResponseEntity<String> expectedResult = new ResponseEntity<>("The Geographic Area hasn't been created. You have entered an invalid Area.", HttpStatus.BAD_REQUEST);

        // Act
        ResponseEntity<Object> actualResult = gaSettingsWebController.createGeoArea(validGeographicAreaDTO);

        // Assert
        assertEquals(expectedResult, actualResult);
    }


    @Test
    void getAllGeoAreasDTO() {
        // Arrange
        GeographicAreaDTO validGeographicAreaDTO = new GeographicAreaDTO();

        LocalDTO localDTO = new LocalDTO();

        localDTO.setLatitude(41D);
        localDTO.setLongitude(-8D);
        localDTO.setAltitude(100D);

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

        // Act
        ResponseEntity<Object> actualResult = gaSettingsWebController.getAllGeographicAreas();

        // Assert
        assertEquals(expectedResult, actualResult);

    }

    @Test
    void addChildArea() {
        GeographicAreaDTO validGeographicAreaDTO = new GeographicAreaDTO();
        List<GeographicAreaDTO> childAreas = new ArrayList<>();

        validGeographicAreaDTO.setDescription("3rd biggest city");
        validGeographicAreaDTO.setId(2L);
        validGeographicAreaDTO.setWidth(100);
        validGeographicAreaDTO.setLength(500);
        validGeographicAreaDTO.setTypeArea("urban area");
        validGeographicAreaDTO.setDaughterAreaList(childAreas);

        Mockito.doReturn(true).when(geographicAreaRepository).addChildArea(any(long.class), any(long.class));
        Mockito.doReturn(validGeographicAreaDTO).when(geographicAreaRepository).getDTOByIdWithParent(validGeographicAreaDTO.getGeographicAreaId());

        Link link = linkTo(methodOn(GASettingsWebController.class).getGeographicArea(validGeographicAreaDTO.getGeographicAreaId())).withRel("See geographic area");
        validGeographicAreaDTO.add(link);

        // Act
        ResponseEntity<Object> actualResult = gaSettingsWebController.addChildArea(validGeographicAreaDTO.getGeographicAreaId(),validGeographicAreaDTO.getGeographicAreaId());

        // Assert
        assertEquals(HttpStatus.OK, actualResult.getStatusCode());
    }

    @Test
    void addDaughterAreaContainsDaughter() {
        GeographicAreaDTO validGeographicAreaDTO = new GeographicAreaDTO();

        validGeographicAreaDTO.setDescription("3rd biggest city");
        validGeographicAreaDTO.setId(2L);
        validGeographicAreaDTO.setWidth(100);
        validGeographicAreaDTO.setLength(500);
        validGeographicAreaDTO.setTypeArea("urban area");

        Mockito.doReturn(false).when(geographicAreaRepository).addChildArea(any(long.class), any(long.class));

        ResponseEntity<String> expectedResult = new ResponseEntity<>("The Geographic Area hasn't been added. The daughter area is already contained in the mother area.", HttpStatus.CONFLICT);

        // Act
        ResponseEntity<Object> actualResult = gaSettingsWebController.addChildArea(6L, validGeographicAreaDTO.getGeographicAreaId());

        // Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void addDaughterAreaNotFound() {

        Mockito.doThrow(NoSuchElementException.class).when(geographicAreaRepository).addChildArea(any(long.class), any(long.class));

        ResponseEntity<Object> actualResult = gaSettingsWebController.addChildArea(6L, 3L);

        assertEquals(HttpStatus.NOT_FOUND, actualResult.getStatusCode());
    }

    @Test
    void getGeoAreaDTO() {
        // Arrange
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

        ResponseEntity<Object> expectedResult = new ResponseEntity<>(HttpStatus.OK);

        // Act
        ResponseEntity<Object> actualResult = gaSettingsWebController.getGeographicArea(validGeographicAreaDTO.getGeographicAreaId());

        // Assert
        assertEquals(expectedResult, actualResult);

    }


    @Test
    void removeChildArea() {
        GeographicAreaDTO validGeographicAreaDTO = new GeographicAreaDTO();
        List<GeographicAreaDTO> childAreas = new ArrayList<>();

        validGeographicAreaDTO.setDescription("3rd biggest city");
        validGeographicAreaDTO.setId(2L);
        validGeographicAreaDTO.setWidth(100);
        validGeographicAreaDTO.setLength(500);
        validGeographicAreaDTO.setTypeArea("urban area");
        validGeographicAreaDTO.setDaughterAreaList(childAreas);

        Mockito.doReturn(true).when(geographicAreaRepository).removeChildArea(any(long.class), any(long.class));
        Mockito.doReturn(validGeographicAreaDTO).when(geographicAreaRepository).getDTOByIdWithParent(validGeographicAreaDTO.getGeographicAreaId());

        Link link = linkTo(methodOn(GASettingsWebController.class).getGeographicArea(validGeographicAreaDTO.getGeographicAreaId())).withRel("See geographic area");
        validGeographicAreaDTO.add(link);

        // Act
        ResponseEntity<Object> actualResult = gaSettingsWebController.removeChildArea(validGeographicAreaDTO.getGeographicAreaId(),validGeographicAreaDTO.getGeographicAreaId());

        // Assert
        assertEquals(HttpStatus.OK, actualResult.getStatusCode());
    }

    @Test
    void removeDaughterAreaContainsDaughter() {
        // Arrange

        GeographicAreaDTO validGeographicAreaDTO = new GeographicAreaDTO();

        validGeographicAreaDTO.setDescription("3rd biggest city");
        validGeographicAreaDTO.setId(2L);
        validGeographicAreaDTO.setWidth(100);
        validGeographicAreaDTO.setLength(500);
        validGeographicAreaDTO.setTypeArea("urban area");

        Mockito.doReturn(false).when(geographicAreaRepository).removeChildArea(any(long.class), any(long.class));

        ResponseEntity<String> expectedResult = new ResponseEntity<>("The Geographic Area hasn't been removed. The daughter area is already not contained in the mother area.", HttpStatus.CONFLICT);

        // Act

        ResponseEntity<Object> actualResult = gaSettingsWebController.removeChildArea(6L, validGeographicAreaDTO.getGeographicAreaId());

        // Assert

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfRemoveDaughterAreaNotFound() {
        // Arrange

        Mockito.doThrow(NoSuchElementException.class).when(geographicAreaRepository).removeChildArea(any(long.class), any(long.class));

        // Act

        ResponseEntity<Object> actualResult = gaSettingsWebController.removeChildArea(6L, 3L);

        // Assert

        assertEquals(HttpStatus.NOT_FOUND, actualResult.getStatusCode());

    }

    @Test
    void seeIfGetAreaTypesWorks(){
        // Arrange

        AreaType areaType = new AreaType("City");

        areaTypeRepository.add(areaType);

        // Act

        ResponseEntity<Object> actualResult = gaSettingsWebController.getAreaTypes();

        // Assert

        assertEquals(HttpStatus.OK, actualResult.getStatusCode());

    }

    /**
     * Method tests for US004 - WebController
     */

    @Test
    void seeIfGetAllAreasOfGivenType() {
        // ARRANGE
            //GeoArea List
        List<GeographicArea> geoAreasOfGivenType = new ArrayList<>();
        GeographicArea validGeographicArea = new GeographicArea();
        validGeographicArea.setDescription("4rd biggest city");
        validGeographicArea.setName("Santa Maria de Lamas");
        validGeographicArea.setWidth(100);
        validGeographicArea.setLength(500);
        validGeographicArea.setAreaTypeID("Urban Area");
        geoAreasOfGivenType.add(validGeographicArea);
            //Mockito
        String areaType = "Urban Area";
        Mockito.doReturn(geoAreasOfGivenType).when(geographicAreaRepository).getGeoAreasByType(areaType);
            //Expected Result
        HttpStatus expectedResult = HttpStatus.OK;

        // ACT
            //Actual Result
        ResponseEntity<Object> controllerMethodCall = gaSettingsWebController.getAllAreasOfGivenType(areaType);
        HttpStatus actualResult = controllerMethodCall.getStatusCode();

        // ASSERT
        assertEquals(expectedResult, actualResult);
    }
}


