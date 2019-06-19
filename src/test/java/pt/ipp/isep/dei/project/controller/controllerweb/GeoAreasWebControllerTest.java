package pt.ipp.isep.dei.project.controller.controllerweb;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pt.ipp.isep.dei.project.dto.*;
import pt.ipp.isep.dei.project.dto.mappers.GeographicAreaMapper;
import pt.ipp.isep.dei.project.model.areatype.AreaType;
import pt.ipp.isep.dei.project.model.areatype.AreaTypeRepository;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicAreaRepository;
import pt.ipp.isep.dei.project.model.user.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ContextConfiguration(classes = HibernateJpaAutoConfiguration.class)
class GeoAreasWebControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Mock
    private UserService userService;
    @Mock
    private GeographicAreaRepository geographicAreaRepository;
    @Mock
    private AreaTypeRepository areaTypeRepository;
    @InjectMocks
    private GeoAreasWebController geoAreasWebController;

    @BeforeEach
    void insertData() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(geoAreasWebController).build();
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

        Link link = linkTo(methodOn(GeoAreasWebController.class).getAllGeographicAreas()).withRel("See all geographic areas");

        validGeographicAreaDTO.add(link);

        // Act

        ResponseEntity<Object> actualResult = geoAreasWebController.createGeoArea(validGeographicAreaDTO);

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
        
        ResponseEntity<Object> actualResult = geoAreasWebController.createGeoArea(validGeographicAreaDTO);

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
        ResponseEntity<Object> actualResult = geoAreasWebController.createGeoArea(validGeographicAreaDTO);

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
        ResponseEntity<Object> actualResult = geoAreasWebController.createGeoArea(validGeographicAreaDTO);

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

        ResponseEntity<Object> actualResult = geoAreasWebController.createGeoArea(validGeographicAreaDTO);

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

        ResponseEntity<Object> actualResult = geoAreasWebController.createGeoArea(validGeographicAreaDTO);

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

        ResponseEntity<Object> actualResult = geoAreasWebController.createGeoArea(validGeographicAreaDTO);

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
        ResponseEntity<Object> actualResult = geoAreasWebController.createGeoArea(validGeographicAreaDTO);

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
        ResponseEntity<Object> actualResult = geoAreasWebController.createGeoArea(validGeographicAreaDTO);

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

        ResponseEntity<Object> actualResult = geoAreasWebController.createGeoArea(validGeographicAreaDTO);

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
        ResponseEntity<Object> actualResult = geoAreasWebController.createGeoArea(validGeographicAreaDTO);

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
        ResponseEntity<Object> actualResult = geoAreasWebController.createGeoArea(validGeographicAreaDTO);

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
        Mockito.when(userService.getUsernameFromToken()).thenReturn("admin");

        ResponseEntity<Object> expectedResult = new ResponseEntity<>(geographicAreas, HttpStatus.OK);

        // Act
        ResponseEntity<Object> actualResult = geoAreasWebController.getAllGeographicAreas();

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

        Link link = linkTo(methodOn(GeoAreasWebController.class).getGeographicArea(validGeographicAreaDTO.getGeographicAreaId())).withRel("See geographic area");
        validGeographicAreaDTO.add(link);

        // Act
        ResponseEntity<Object> actualResult = geoAreasWebController.addChildArea(validGeographicAreaDTO.getGeographicAreaId(),validGeographicAreaDTO.getGeographicAreaId());

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
        ResponseEntity<Object> actualResult = geoAreasWebController.addChildArea(6L, validGeographicAreaDTO.getGeographicAreaId());

        // Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void addDaughterAreaNotFound() {

        Mockito.doThrow(NoSuchElementException.class).when(geographicAreaRepository).addChildArea(any(long.class), any(long.class));

        ResponseEntity<Object> actualResult = geoAreasWebController.addChildArea(6L, 3L);

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
        ResponseEntity<Object> actualResult = geoAreasWebController.getGeographicArea(validGeographicAreaDTO.getGeographicAreaId());

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

        Link link = linkTo(methodOn(GeoAreasWebController.class).getGeographicArea(validGeographicAreaDTO.getGeographicAreaId())).withRel("See geographic area");
        validGeographicAreaDTO.add(link);

        // Act
        ResponseEntity<Object> actualResult = geoAreasWebController.removeChildArea(validGeographicAreaDTO.getGeographicAreaId(),validGeographicAreaDTO.getGeographicAreaId());

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

        ResponseEntity<Object> actualResult = geoAreasWebController.removeChildArea(6L, validGeographicAreaDTO.getGeographicAreaId());

        // Assert

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfRemoveDaughterAreaNotFound() {
        // Arrange

        Mockito.doThrow(NoSuchElementException.class).when(geographicAreaRepository).removeChildArea(any(long.class), any(long.class));

        // Act

        ResponseEntity<Object> actualResult = geoAreasWebController.removeChildArea(6L, 3L);

        // Assert

        assertEquals(HttpStatus.NOT_FOUND, actualResult.getStatusCode());

    }

    @Test
    void seeIfGetAreaTypesWorks(){
        // Arrange

        AreaType areaType = new AreaType("City");

        areaTypeRepository.add(areaType);

        // Act

        ResponseEntity<Object> actualResult = geoAreasWebController.getAreaTypes();

        // Assert

        assertEquals(HttpStatus.OK, actualResult.getStatusCode());

    }

    @Test
    void seeIfRemoveSensorWorks(){
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

        AreaSensorDTO areaSensorDTO = new AreaSensorDTO();

        areaSensorDTO.setId("area sensor");
        areaSensorDTO.setName("sensor 1");
        areaSensorDTO.setTypeSensor("Temperature");
        areaSensorDTO.setUnits("Celsius");
        areaSensorDTO.setLatitude(10D);
        areaSensorDTO.setLongitude(10D);
        areaSensorDTO.setAltitude(10D);
        areaSensorDTO.setDateStartedFunctioning("10-12-2018");
        areaSensorDTO.setActive(true);

        validGeographicAreaDTO.addSensor(areaSensorDTO);

        geographicAreaRepository.addAndPersistGA(GeographicAreaMapper.dtoToObject(validGeographicAreaDTO));

        // Act

        Mockito.when(geographicAreaRepository.removeSensorById(any(Long.class), any(String.class)))
                .thenReturn(true);

        ResponseEntity<Object> actualResult = geoAreasWebController.removeSensor(validGeographicAreaDTO.getGeographicAreaId(), areaSensorDTO.getSensorId());

        // Assert

        assertEquals(HttpStatus.OK, actualResult.getStatusCode());

    }

    @Test
    void seeIfRemoveSensorFailsWithoutSensor(){
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

        AreaSensorDTO areaSensorDTO = new AreaSensorDTO();

        areaSensorDTO.setId("area sensor");
        areaSensorDTO.setName("sensor 1");
        areaSensorDTO.setTypeSensor("Temperature");
        areaSensorDTO.setUnits("Celsius");
        areaSensorDTO.setLatitude(10D);
        areaSensorDTO.setLongitude(10D);
        areaSensorDTO.setAltitude(10D);
        areaSensorDTO.setDateStartedFunctioning("10-12-2018");
        areaSensorDTO.setActive(true);

        geographicAreaRepository.addAndPersistGA(GeographicAreaMapper.dtoToObject(validGeographicAreaDTO));

        // Act

        Mockito.when(geographicAreaRepository.removeSensorById(any(Long.class), any(String.class)))
                .thenReturn(false);

        ResponseEntity<Object> actualResult = geoAreasWebController.removeSensor(validGeographicAreaDTO.getGeographicAreaId(), areaSensorDTO.getSensorId());

        // Assert

        assertEquals(HttpStatus.NOT_FOUND, actualResult.getStatusCode());

    }

    @Test
    void seeIfRemoveSensorFailsWithoutGeographicArea(){
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

        AreaSensorDTO areaSensorDTO = new AreaSensorDTO();

        areaSensorDTO.setId("area sensor");
        areaSensorDTO.setName("sensor 1");
        areaSensorDTO.setTypeSensor("Temperature");
        areaSensorDTO.setUnits("Celsius");
        areaSensorDTO.setLatitude(10D);
        areaSensorDTO.setLongitude(10D);
        areaSensorDTO.setAltitude(10D);
        areaSensorDTO.setDateStartedFunctioning("10-12-2018");
        areaSensorDTO.setActive(true);

        validGeographicAreaDTO.addSensor(areaSensorDTO);

        // Act

        Mockito.when(geographicAreaRepository.removeSensorById(any(Long.class), any(String.class)))
                .thenThrow(NoSuchElementException.class);

        ResponseEntity<Object> actualResult = geoAreasWebController.removeSensor(validGeographicAreaDTO.getGeographicAreaId(), areaSensorDTO.getSensorId());

        // Assert

        assertEquals(HttpStatus.NOT_FOUND, actualResult.getStatusCode());

    }

    /**
     * Method tests for US004 - WebController
     */


    @Test
    void seeIfAddAreaTypeWorks(){
        // Arrange

        List<AreaTypeDTO> emptyList = new ArrayList<>();
        Mockito.when(areaTypeRepository.getAllTypesDTO()).thenReturn(emptyList);
        AreaTypeDTO typeToAdd = new AreaTypeDTO();
        typeToAdd.setName("Area");
        ResponseEntity<Object> expectedResult = new ResponseEntity<>(typeToAdd, HttpStatus.OK);

        // Act

        ResponseEntity<Object> actualResult = geoAreasWebController.addAreaType(typeToAdd);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfAddAreaTypeWorksDuplicate(){
        // Arrange

        List<AreaTypeDTO> repoList = new ArrayList<>();
        AreaTypeDTO typeInRepo = new AreaTypeDTO();
        typeInRepo.setName("Area");
        repoList.add(typeInRepo);

        Mockito.when(areaTypeRepository.getAllTypesDTO()).thenReturn(repoList);

        ResponseEntity<Object> expectedResult = new ResponseEntity<>(typeInRepo, HttpStatus.CONFLICT);
        AreaTypeDTO typeToAdd = new AreaTypeDTO();
        typeToAdd.setName("Area");

        // Act

        ResponseEntity<Object> actualResult = geoAreasWebController.addAreaType(typeToAdd);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfAddAreaTypeWorksDuplicateConflict(){
        // Arrange

        List<AreaTypeDTO> repoList = new ArrayList<>();
        AreaTypeDTO typeInRepo = new AreaTypeDTO();
        typeInRepo.setName("Area");
        repoList.add(typeInRepo);

        AreaTypeDTO typeInRepo2 = new AreaTypeDTO();
        typeInRepo.setName("Areosa");
        repoList.add(typeInRepo2);

        Mockito.when(areaTypeRepository.getAllTypesDTO()).thenReturn(repoList);

        ResponseEntity<Object> expectedResult = new ResponseEntity<>(typeInRepo, HttpStatus.CONFLICT);

        // Act
        ResponseEntity<Object> actualResult = geoAreasWebController.addAreaType(typeInRepo);

        // Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfAddAreaTypeWorksInvalidInputEmpty(){
        // Arrange

        List<AreaTypeDTO> emptyList = new ArrayList<>();
        Mockito.when(areaTypeRepository.getAllTypesDTO()).thenReturn(emptyList);

        ResponseEntity<Object> expectedResult = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        AreaTypeDTO typeToAdd = new AreaTypeDTO();
        typeToAdd.setName(null);

        // Act

        ResponseEntity<Object> actualResult = geoAreasWebController.addAreaType(typeToAdd);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfAddAreaTypeWorksInvalidInputNull(){
        // Arrange

        List<AreaTypeDTO> emptyList = new ArrayList<>();
        Mockito.when(areaTypeRepository.getAllTypesDTO()).thenReturn(emptyList);

        ResponseEntity<Object> expectedResult = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        AreaTypeDTO typeToAdd = new AreaTypeDTO();
        typeToAdd.setName("");

        // Act

        ResponseEntity<Object> actualResult = geoAreasWebController.addAreaType(typeToAdd);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfDeactivateAreaSensorWorks() {

        Mockito.doReturn(true).when(geographicAreaRepository).deactivateAreaSensor(any(long.class), any(String.class));

        ResponseEntity<Object> expectedResult = new ResponseEntity<>("The sensor was successfully deactivated from the selected geographic area.", HttpStatus.OK);

        //Act
        ResponseEntity<Object> actualResult = geoAreasWebController.deactivateSensor(1L, "id");

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfDeactivateAreaSensorNotFoundOrNonExistent() {
        // Arrange

        Mockito.doReturn(false).when(geographicAreaRepository).deactivateAreaSensor(any(long.class), any(String.class));

        ResponseEntity<Object> expectedResult = new ResponseEntity<>("There is no sensor with that ID in this geographic area.", HttpStatus.NOT_FOUND);

        //Act

        ResponseEntity<Object> actualResult = geoAreasWebController.deactivateSensor(6L, "ID");

        //Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfDeactivateAreaSensorNotFound() {

        Mockito.doThrow(NoSuchElementException.class).when(geographicAreaRepository).deactivateAreaSensor(any(long.class), any(String.class));

        ResponseEntity<Object> actualResult = geoAreasWebController.deactivateSensor(6L, "id");

        assertEquals(HttpStatus.NOT_FOUND, actualResult.getStatusCode());
    }

    @Test
    void seeIfGetAllGeographicAreasWorks() throws Exception {

        // Arrange

        List<GeographicAreaDTO> list = new ArrayList<>();
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

        AreaSensorDTO areaSensorDTO = new AreaSensorDTO();

        areaSensorDTO.setId("area sensor");
        areaSensorDTO.setName("sensor 1");
        areaSensorDTO.setTypeSensor("Temperature");
        areaSensorDTO.setUnits("Celsius");
        areaSensorDTO.setLatitude(10D);
        areaSensorDTO.setLongitude(10D);
        areaSensorDTO.setAltitude(10D);
        areaSensorDTO.setDateStartedFunctioning("10-12-2018");
        areaSensorDTO.setActive(true);

        validGeographicAreaDTO.addSensor(areaSensorDTO);
        list.add(validGeographicAreaDTO);

        Mockito.when(geographicAreaRepository.getAllDTO()).thenReturn(list);
        Mockito.when(userService.getUsernameFromToken()).thenReturn("admin");

        // Perform

        this.mockMvc.perform(get("/geoAreas/"))
                .andExpect(status().isOk());
    }
}


