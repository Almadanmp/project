package pt.ipp.isep.dei.project.controllerweb;

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
import pt.ipp.isep.dei.project.dto.DateDTO;
import pt.ipp.isep.dei.project.model.bridgeservices.GeographicAreaHouseService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({MockitoExtension.class})
class HouseMonitoringWebControllerTest {

    @Mock
    GeographicAreaHouseService geographicAreaHouseService;

    @InjectMocks
    HouseMonitoringWebController houseMonitoringWebController;
    @Autowired
    private MockMvc mockMvc;

    private Date date1; // Date 01/01/2020
    private Date date2; // Date 01/01/2019
    private Date date3; // Date 01/01/2018
    private Date date4; // Date 01/01/2017
    private Date date5; // Date 01/01/2016
    private Date date6; // Date 01/01/1998
    private Date date7; // Date 01/01/1985

    private static String SUCCESS = "Success";
    private static String FAIL = "Fail";

    @BeforeEach
    void arrangeArtifacts() {
        MockitoAnnotations.initMocks(this);
        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            date1 = validSdf.parse("01/01/2020 00:00:00");
            date2 = validSdf.parse("01/01/2019 00:00:00");
            date3 = validSdf.parse("01/01/2018 00:00:00");
            date4 = validSdf.parse("01/01/2017 00:00:00");
            date5 = validSdf.parse("01/01/2016 00:00:00");
            date6 = validSdf.parse("12/10/1998 00:00:00");
            date7 = validSdf.parse("01/10/1985 00:00:00");

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    @Test
    void getHighestAmplitudeSuccessMockito() {

        DateDTO dateDTO = new DateDTO();
        dateDTO.setInitialDate(date2);
        dateDTO.setEndDate(date1);

        Mockito.when(geographicAreaHouseService.getHighestTemperatureAmplitude(dateDTO)).thenReturn(SUCCESS);

        ResponseEntity<String> expectedResult = new ResponseEntity<>(SUCCESS, HttpStatus.OK);

        ResponseEntity<String> actualResult = houseMonitoringWebController.getHighestTemperatureAmplitudeDate(dateDTO);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getHighestAmplitudeInvertedDates() throws Exception {

        this.mockMvc = MockMvcBuilders.standaloneSetup(houseMonitoringWebController).build();

        mockMvc.perform(get("/houseMonitoring/highestAmplitude")
                .content("\n" +
                        " {\n" +
                        "\"initialDate\": \"2019-01-01\",\n" +
                        "\"endDate\": \"2018-01-01\"\n" +
                        " }"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getHighestAmplitudeInvertedDatesMockito() {

        DateDTO dateDTO = new DateDTO();
        dateDTO.setInitialDate(date1);
        dateDTO.setEndDate(date2);

        Mockito.when(geographicAreaHouseService.getHighestTemperatureAmplitude(dateDTO)).thenThrow(IllegalArgumentException.class);

        ResponseEntity<String> expectedResult = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        ResponseEntity<String> actualResult = houseMonitoringWebController.getHighestTemperatureAmplitudeDate(dateDTO);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getHighestAmplitudeNoReadingsOnInterval() throws Exception {

        this.mockMvc = MockMvcBuilders.standaloneSetup(houseMonitoringWebController).build();

        mockMvc.perform(get("/houseMonitoring/highestAmplitude")
                .content("\n" +
                        " {\n" +
                        "\"initialDate\": \"2010-01-01\",\n" +
                        "\"endDate\": \"2011-01-01\"\n" +
                        " }"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getHighestAmplitudeIncompleteDatesMockito() throws IllegalArgumentException {

        DateDTO dateDTO = new DateDTO();
        dateDTO.setInitialDate(date1);

        Mockito.when(geographicAreaHouseService.getHighestTemperatureAmplitude(dateDTO)).thenThrow(IllegalArgumentException.class);

        ResponseEntity<String> expectedResult = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        ResponseEntity<String> actualResult = houseMonitoringWebController.getHighestTemperatureAmplitudeDate(dateDTO);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getHighestAmplitudeSimulateServerErrorMockito() throws IllegalArgumentException {

        DateDTO dateDTO = new DateDTO();
        dateDTO.setInitialDate(date1);

        Mockito.when(geographicAreaHouseService.getHighestTemperatureAmplitude(dateDTO)).thenThrow(RuntimeException.class);

        ResponseEntity<String> expectedResult = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        ResponseEntity<String> actualResult = houseMonitoringWebController.getHighestTemperatureAmplitudeDate(dateDTO);

        assertEquals(expectedResult, actualResult);
    }

}
