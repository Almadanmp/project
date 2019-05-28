package pt.ipp.isep.dei.project.controller.controllerweb;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pt.ipp.isep.dei.project.dto.DateIntervalDTO;
import pt.ipp.isep.dei.project.dto.DateValueDTO;
import pt.ipp.isep.dei.project.model.bridgeservices.GeographicAreaHouseService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@ExtendWith(MockitoExtension.class)
class HouseMonitoringWebControllerTest {

    @Mock
    GeographicAreaHouseService geographicAreaHouseService;
    @InjectMocks
    HouseMonitoringWebController houseMonitoringWebController;

    private Date date1; // Date 01/01/2020
    private Date date2; // Date 01/01/2019

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
    void getColdestDaySuccessMockito() {

        DateIntervalDTO dateIntervalDTO = new DateIntervalDTO(date2, date1);
        DateValueDTO dateValueDTO = new DateValueDTO(date2, 12);
        Link link = linkTo(methodOn(HouseMonitoringWebController.class).getLastColdestDay(dateIntervalDTO)).withRel("Retry with a different period.");
        dateValueDTO.add(link);
        Mockito.when(geographicAreaHouseService.getLastColdestDay(dateIntervalDTO)).thenReturn(dateValueDTO);

        ResponseEntity<Object> expectedResult = new ResponseEntity<>(dateValueDTO, HttpStatus.OK);

        ResponseEntity<Object> actualResult = houseMonitoringWebController.getLastColdestDay(dateIntervalDTO);

        assertEquals(expectedResult, actualResult);
    }


    @Test
    void getColdestDayInvertedDatesMockito() {

        DateIntervalDTO dateIntervalDTO = new DateIntervalDTO(date1, date2);

        Mockito.when(geographicAreaHouseService.getLastColdestDay(dateIntervalDTO)).thenThrow(IllegalArgumentException.class);

        ResponseEntity<Object> expectedResult = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        ResponseEntity<Object> actualResult = houseMonitoringWebController.getLastColdestDay(dateIntervalDTO);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getColdestDayIncompleteDatesMockito() throws IllegalArgumentException {

        DateIntervalDTO dateIntervalDTO = new DateIntervalDTO(date1, date2);

        Mockito.when(geographicAreaHouseService.getLastColdestDay(dateIntervalDTO)).thenThrow(IllegalArgumentException.class);

        ResponseEntity<Object> expectedResult = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        ResponseEntity<Object> actualResult = houseMonitoringWebController.getLastColdestDay(dateIntervalDTO);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getColdestDaySimulateServerErrorMockito() throws IllegalArgumentException {

        DateIntervalDTO dateIntervalDTO = new DateIntervalDTO(date1, date2);

        Mockito.when(geographicAreaHouseService.getLastColdestDay(dateIntervalDTO)).thenThrow(RuntimeException.class);

        ResponseEntity<Object> expectedResult = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        ResponseEntity<Object> actualResult = houseMonitoringWebController.getLastColdestDay(dateIntervalDTO);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getHottestDaySuccessMockito() {

        DateIntervalDTO dateIntervalDTO = new DateIntervalDTO(date2, date1);
        DateValueDTO dateValueDTO = new DateValueDTO(date2, 12);
        Link link = linkTo(methodOn(HouseMonitoringWebController.class).getHottestDay(dateIntervalDTO)).withRel("Retry with a different period.");
        dateValueDTO.add(link);
        Mockito.when(geographicAreaHouseService.getHottestDay(dateIntervalDTO)).thenReturn(dateValueDTO);

        ResponseEntity<Object> expectedResult = new ResponseEntity<>(dateValueDTO, HttpStatus.OK);

        ResponseEntity<Object> actualResult = houseMonitoringWebController.getHottestDay(dateIntervalDTO);

        assertEquals(expectedResult, actualResult);
    }


    @Test
    void getHottestDayInvertedDatesMockito() {

        DateIntervalDTO dateIntervalDTO = new DateIntervalDTO(date1, date2);

        Mockito.when(geographicAreaHouseService.getHottestDay(dateIntervalDTO)).thenThrow(IllegalArgumentException.class);

        ResponseEntity<Object> expectedResult = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        ResponseEntity<Object> actualResult = houseMonitoringWebController.getHottestDay(dateIntervalDTO);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getHottestDayIncompleteDatesMockito() throws IllegalArgumentException {

        DateIntervalDTO dateIntervalDTO = new DateIntervalDTO(date1, date2);

        Mockito.when(geographicAreaHouseService.getHottestDay(dateIntervalDTO)).thenThrow(IllegalArgumentException.class);

        ResponseEntity<Object> expectedResult = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        ResponseEntity<Object> actualResult = houseMonitoringWebController.getHottestDay(dateIntervalDTO);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getHottestDaySimulateServerErrorMockito() throws IllegalArgumentException {

        DateIntervalDTO dateIntervalDTO = new DateIntervalDTO(date1, date2);

        Mockito.when(geographicAreaHouseService.getHottestDay(dateIntervalDTO)).thenThrow(RuntimeException.class);

        ResponseEntity<Object> expectedResult = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        ResponseEntity<Object> actualResult = houseMonitoringWebController.getHottestDay(dateIntervalDTO);

        assertEquals(expectedResult, actualResult);
    }


    @Test
    void getHighestAmplitudeSuccessMockito() {

        DateIntervalDTO dateIntervalDTO = new DateIntervalDTO(date2, date1);
        DateValueDTO dateValueDTO = new DateValueDTO(date2, 12);
        Link link = linkTo(methodOn(HouseMonitoringWebController.class).getHighestTemperatureAmplitudeDate(dateIntervalDTO)).withRel("Retry with a different period.");
        dateValueDTO.add(link);
        Mockito.when(geographicAreaHouseService.getHighestTemperatureAmplitude(dateIntervalDTO)).thenReturn(dateValueDTO);

        ResponseEntity<Object> expectedResult = new ResponseEntity<>(dateValueDTO, HttpStatus.OK);

        ResponseEntity<Object> actualResult = houseMonitoringWebController.getHighestTemperatureAmplitudeDate(dateIntervalDTO);

        assertEquals(expectedResult, actualResult);
    }


    @Test
    void getHighestAmplitudeInvertedDatesMockito() {

        DateIntervalDTO dateIntervalDTO = new DateIntervalDTO(date1, date2);

        Mockito.when(geographicAreaHouseService.getHighestTemperatureAmplitude(dateIntervalDTO)).thenThrow(IllegalArgumentException.class);

        ResponseEntity<Object> expectedResult = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        ResponseEntity<Object> actualResult = houseMonitoringWebController.getHighestTemperatureAmplitudeDate(dateIntervalDTO);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getHighestAmplitudeIncompleteDatesMockito() throws IllegalArgumentException {

        DateIntervalDTO dateIntervalDTO = new DateIntervalDTO(date1, date2);

        Mockito.when(geographicAreaHouseService.getHighestTemperatureAmplitude(dateIntervalDTO)).thenThrow(IllegalArgumentException.class);

        ResponseEntity<Object> expectedResult = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        ResponseEntity<Object> actualResult = houseMonitoringWebController.getHighestTemperatureAmplitudeDate(dateIntervalDTO);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getHighestAmplitudeSimulateServerErrorMockito() throws IllegalArgumentException {

        DateIntervalDTO dateIntervalDTO = new DateIntervalDTO(date1, date2);

        Mockito.when(geographicAreaHouseService.getHighestTemperatureAmplitude(dateIntervalDTO)).thenThrow(RuntimeException.class);

        ResponseEntity<Object> expectedResult = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        ResponseEntity<Object> actualResult = houseMonitoringWebController.getHighestTemperatureAmplitudeDate(dateIntervalDTO);

        assertEquals(expectedResult, actualResult);
    }
}
