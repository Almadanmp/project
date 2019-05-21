package pt.ipp.isep.dei.project.controllerweb;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pt.ipp.isep.dei.project.dto.GeographicAreaDTO;
import pt.ipp.isep.dei.project.dto.LocalDTO;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicAreaRepository;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@WebMvcTest
@ContextConfiguration(classes = HibernateJpaAutoConfiguration.class)
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

//    @Test
//    void createGeoAreaDTO() throws Exception {
//
//        mvc.perform(post("/geographic_area_settings/areas")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{\n" +
//                        "  \"id\": 66,\n" +
//                        "  \"name\": \"Gaia\",\n" +
//                        "  \"typeArea\": \"urban area\",\n" +
//                        "  \"length\": 500,\n" +
//                        "  \"width\": 100,\n" +
//                        "  \"localDTO\": {\n" +
//                        "    \"latitude\": 41,\n" +
//                        "    \"longitude\": -8,\n" +
//                        "    \"altitude\": 100,\n" +
//                        "    \"id\": 0\n" +
//                        "  },\n" +
//                        "  \"description\": \"3rd biggest city\"\n" +
//                        "}"))
//                .andExpect(status().isCreated());
//    }

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
    void getAllGeoAreasDTO() {
    }
}