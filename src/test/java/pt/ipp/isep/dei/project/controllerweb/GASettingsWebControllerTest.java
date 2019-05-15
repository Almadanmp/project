package pt.ipp.isep.dei.project.controllerweb;

import org.eclipse.persistence.oxm.MediaType;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pt.ipp.isep.dei.project.dto.GeographicAreaDTO;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicAreaRepository;
import pt.ipp.isep.dei.project.repository.AreaTypeCrudeRepo;
import pt.ipp.isep.dei.project.repository.GeographicAreaCrudeRepo;
import pt.ipp.isep.dei.project.repository.SensorTypeCrudeRepo;

import static org.junit.jupiter.api.Assertions.*;

class GASettingsWebControllerTest {
    @Autowired
    private GeographicAreaCrudeRepo geographicAreaCrudeRepo;
    @Autowired
    private static AreaTypeCrudeRepo areaTypeCrudeRepo;
    @Autowired
    SensorTypeCrudeRepo sensorTypeCrudeRepo;
    private GeographicAreaRepository geographicAreaRepo;

    @Autowired
    MockMvc mvc;

    @Test
    void getAllGeoAreasDTO() throws Exception {

//        GeographicAreaDTO dto = new GeographicAreaDTO();
////        Mockito.when(geographicAreaRepo.addAndPersistDTO(dto)).thenReturn(true);
//
//        RequestBuilder requestBuilder = MockMvcRequestBuilders
//                .post("/geographic_area_settings/areas");
//
//        MvcResult result = mvc.perform(requestBuilder).andReturn();
//
//        MockHttpServletResponse response = result.getResponse();
//
//        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    @Test
    void createGeoAreaDTO() {
    }
}