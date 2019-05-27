package pt.ipp.isep.dei.project.controller.controllerweb;

import org.junit.jupiter.api.BeforeEach;
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
import pt.ipp.isep.dei.project.dto.DateIntervalDTO;
import pt.ipp.isep.dei.project.model.bridgeservices.GeographicAreaHouseService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class HouseMonitoringWebControllerTest {

    @Mock
    GeographicAreaHouseService geographicAreaHouseService;
    @InjectMocks
    HouseMonitoringWebController houseMonitoringWebController;
    @Autowired
    private MockMvc mockMvc;

    private Date date1; // Date 01/01/2020
    private Date date2; // Date 01/01/2019

    private static String SUCCESS = "Success";

    @BeforeEach
    void arrangeArtifacts() {
        MockitoAnnotations.initMocks(this);
        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            date1 = validSdf.parse("01/01/2020 00:00:00");
            date2 = validSdf.parse("01/01/2019 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    @Test
    void getHighestAmplitudeSuccessMockito() {

        DateIntervalDTO dateIntervalDTO = new DateIntervalDTO();
        dateIntervalDTO.setInitialDate(date2);
        dateIntervalDTO.setEndDate(date1);

        Mockito.when(geographicAreaHouseService.getHighestTemperatureAmplitude(dateIntervalDTO)).thenReturn(SUCCESS);

        ResponseEntity<String> expectedResult = new ResponseEntity<>(SUCCESS, HttpStatus.OK);

        ResponseEntity<String> actualResult = houseMonitoringWebController.getHighestTemperatureAmplitudeDate(dateIntervalDTO);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getHighestAmplitudeInvertedDates() throws Exception {

        this.mockMvc = MockMvcBuilders.standaloneSetup(houseMonitoringWebController).build();

        mockMvc.perform(post("/houseMonitoring/highestAmplitude")
                .content("\n" +
                        " {\n" +
                        "\"initialDate\": \"2019-01-01\",\n" +
                        "\"endDate\": \"2018-01-01\"\n" +
                        " }"))
                .andExpect(status().isUnsupportedMediaType());
    }

    @Test
    void getHighestAmplitudeInvertedDatesMockito() {

        DateIntervalDTO dateIntervalDTO = new DateIntervalDTO();
        dateIntervalDTO.setInitialDate(date1);
        dateIntervalDTO.setEndDate(date2);

        Mockito.when(geographicAreaHouseService.getHighestTemperatureAmplitude(dateIntervalDTO)).thenThrow(IllegalArgumentException.class);

        ResponseEntity<String> expectedResult = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        ResponseEntity<String> actualResult = houseMonitoringWebController.getHighestTemperatureAmplitudeDate(dateIntervalDTO);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getHighestAmplitudeIncompleteDatesMockito() throws IllegalArgumentException {

        DateIntervalDTO dateIntervalDTO = new DateIntervalDTO();
        dateIntervalDTO.setInitialDate(date1);

        Mockito.when(geographicAreaHouseService.getHighestTemperatureAmplitude(dateIntervalDTO)).thenThrow(IllegalArgumentException.class);

        ResponseEntity<String> expectedResult = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        ResponseEntity<String> actualResult = houseMonitoringWebController.getHighestTemperatureAmplitudeDate(dateIntervalDTO);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getHighestAmplitudeNoReadingsOnIntervalMVC() throws Exception {

        this.mockMvc = MockMvcBuilders.standaloneSetup(houseMonitoringWebController).build();

        mockMvc.perform(post("/houseMonitoring/highestAmplitude")
                .content("\n" +
                        " {\n" +
                        "\"initialDate\": \"2010-01-01\",\n" +
                        " }"))
                .andExpect(status().isUnsupportedMediaType());
    }


    @Test
    void getHighestAmplitudeSimulateServerErrorMockito() throws IllegalArgumentException {

        DateIntervalDTO dateIntervalDTO = new DateIntervalDTO();
        dateIntervalDTO.setInitialDate(date1);

        Mockito.when(geographicAreaHouseService.getHighestTemperatureAmplitude(dateIntervalDTO)).thenThrow(RuntimeException.class);

        ResponseEntity<String> expectedResult = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        ResponseEntity<String> actualResult = houseMonitoringWebController.getHighestTemperatureAmplitudeDate(dateIntervalDTO);

        assertEquals(expectedResult, actualResult);
    }
}
