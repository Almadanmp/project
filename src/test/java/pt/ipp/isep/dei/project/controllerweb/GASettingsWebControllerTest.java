//package pt.ipp.isep.dei.project.controllerweb;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.mockito.junit.jupiter.MockitoSettings;
//import org.mockito.quality.Strictness;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockHttpServletResponse;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MockMvcBuilder;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.RequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import pt.ipp.isep.dei.project.dto.GeographicAreaDTO;
//import pt.ipp.isep.dei.project.model.geographicarea.GeographicAreaRepository;
//import pt.ipp.isep.dei.project.repository.AreaTypeCrudeRepo;
//import pt.ipp.isep.dei.project.repository.GeographicAreaCrudeRepo;
//import pt.ipp.isep.dei.project.repository.SensorTypeCrudeRepo;
//
//import static org.hamcrest.core.StringContains.containsString;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringRunner.class)
////@SpringBootTest
//@ContextConfiguration(classes = GASettingsWebController.class)
//@WebMvcTest
//class GASettingsWebControllerTest {
//
//    @Autowired
//    private MockMvc mvc;
//
//    private GASettingsWebController gaSettingsWebController = new GASettingsWebController();
//
//    @Test
//    public void createGeoAreaDTO() throws Exception {
//
//        this.mvc = MockMvcBuilders.standaloneSetup(gaSettingsWebController).build();
//
//        mvc.perform(post("/geographic_area_settings/areas")
//                .param("id","66")
//                .param("name", "Gaia")
//                .param("typeArea","urban area")
//                .param("length","500")
//                .param("width","100")
//                .param("localDTO","{\n" +
//                        "    \"latitude\": 41,\n" +
//                        "    \"longitude\": -8,\n" +
//                        "    \"altitude\": 100,\n" +
//                        "    \"id\": 0\n" +
//                        "  }")
//                .param("description","cidade gloriosa"))
//                .andExpect(status().isCreated());
//
////        RequestBuilder requestBuilder = post("/geographic_area_settings/areas")
////                .accept(MediaType.APPLICATION_JSON)
////                .content("src/test/resources/webcontroller/testGeoArea.json")
////                .contentType(MediaType.APPLICATION_JSON);
////
////        MvcResult result = mvc.perform(requestBuilder).andReturn();
////
////        MockHttpServletResponse response = result.getResponse();
////
////        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
//    }
//
//    @Test
//    void getAllGeoAreasDTO() {
//    }
//}