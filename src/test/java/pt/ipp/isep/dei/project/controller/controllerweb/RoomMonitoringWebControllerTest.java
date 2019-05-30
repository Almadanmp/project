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
import pt.ipp.isep.dei.project.dto.DateWithRoomIdDTO;
import pt.ipp.isep.dei.project.model.room.Room;
import pt.ipp.isep.dei.project.model.room.RoomRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@ExtendWith(MockitoExtension.class)
class RoomMonitoringWebControllerTest {

    @Mock
    RoomRepository roomRepository;
    @InjectMocks
    RoomMonitoringWebController roomMonitoringWebController;

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
    void seeIfGetRoomMaxTempInDaySuccessMockito() {
        // Arrange

        Room room = new Room("Bedroom", "Cosy", 3, 2, 1, 5, "7");
        DateWithRoomIdDTO dateWithRoomIdDTO = new DateWithRoomIdDTO(date1, "Bedroom");

        // Act

        Mockito.when(roomRepository.getRoomMaxTempById(room.getId(), date1)).thenReturn(30D);

        ResponseEntity<Object> expectedResult = new ResponseEntity<>(30D, HttpStatus.OK);

        ResponseEntity<Object> actualResult = roomMonitoringWebController.getRoomMaxTempInDay(dateWithRoomIdDTO);

        // Assert

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfGetRoomMaxTempInDayIllegalArgumentException() {
        // Arrange

        Room room = new Room("Bedroom", "Cosy", 3, 2, 1, 5, "7");
        DateWithRoomIdDTO dateWithRoomIdDTO = new DateWithRoomIdDTO(date1, "Bedroom");
        Link link = linkTo(methodOn(RoomMonitoringWebController.class).
                getRoomMaxTempInDay(dateWithRoomIdDTO)).withRel("This room does not exist.");

        // Act

        Mockito.when(roomRepository.getRoomMaxTempById(room.getId(), date1)).thenThrow(IllegalArgumentException.class);

        ResponseEntity<Object> expectedResult = new ResponseEntity<>(link, HttpStatus.OK);

        ResponseEntity<Object> actualResult = roomMonitoringWebController.getRoomMaxTempInDay(dateWithRoomIdDTO);

        // Assert

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfGetRoomMaxTempInDayRuntimeException() {
        // Arrange

        Room room = new Room("Bedroom", "Cosy", 3, 2, 1, 5, "7");
        DateWithRoomIdDTO dateWithRoomIdDTO = new DateWithRoomIdDTO(date1, "Bedroom");

        // Act

        Mockito.when(roomRepository.getRoomMaxTempById(room.getId(), date1)).thenThrow(RuntimeException.class);

        ResponseEntity<Object> expectedResult = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        ResponseEntity<Object> actualResult = roomMonitoringWebController.getRoomMaxTempInDay(dateWithRoomIdDTO);

        // Assert

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfGetRoomMaxTempInDayNoSuchElementException() {
        // Arrange

        Room room = new Room("Bedroom", "Cosy", 3, 2, 1, 5, "7");
        DateWithRoomIdDTO dateWithRoomIdDTO = new DateWithRoomIdDTO(date1, "Bedroom");

        // Act

        Mockito.when(roomRepository.getRoomMaxTempById(room.getId(), date1)).thenThrow(NoSuchElementException.class);

        ResponseEntity<Object> expectedResult = new ResponseEntity<>("There are no readings for the given date.", HttpStatus.BAD_REQUEST);

        ResponseEntity<Object> actualResult = roomMonitoringWebController.getRoomMaxTempInDay(dateWithRoomIdDTO);

        // Assert

        assertEquals(expectedResult, actualResult);

    }

}
