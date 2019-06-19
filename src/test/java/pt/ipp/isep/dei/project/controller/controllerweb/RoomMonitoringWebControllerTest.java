package pt.ipp.isep.dei.project.controller.controllerweb;

import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pt.ipp.isep.dei.project.dto.DateWithRoomIdDTO;
import pt.ipp.isep.dei.project.dto.RoomDTOMinimal;
import pt.ipp.isep.dei.project.model.bridgeservices.HouseRoomService;
import pt.ipp.isep.dei.project.model.room.Room;
import pt.ipp.isep.dei.project.model.room.RoomRepository;
import pt.ipp.isep.dei.project.model.user.UserService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class RoomMonitoringWebControllerTest {

    @Mock
    RoomRepository roomRepository;
    @Mock
    UserService userService;
    @Mock
    HouseRoomService houseRoomService;
    @InjectMocks
    RoomsWebController roomMonitoringWebController;

    @Autowired
    private MockMvc mockMvc;

    private Room room1;
    private Date date1; // Date 01/01/2020
    private Date date2; // Date 01/01/2019

    @BeforeEach
    void arrangeArtifacts() {
        MockitoAnnotations.initMocks(this);
        room1 = new Room("Bedroom", "Cosy", 3, 2, 1, 5, "7");
        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            date1 = validSdf.parse("01/01/2020 00:00:00");
            date2 = validSdf.parse("01/01/2019 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.mockMvc = MockMvcBuilders.standaloneSetup(roomMonitoringWebController).build();

    }

    @Test
    void seeIfGetCurrentRoomTemperatureWorks() {
        // Arrange

        Room room = room1;
        double resultTemp = 15;
        ResponseEntity<Object> expectedResult = new ResponseEntity<>(resultTemp, HttpStatus.OK);

        // Act

        Mockito.when(roomRepository.getCurrentRoomTempByRoomId(room.getId())).thenReturn(resultTemp);
        ResponseEntity<Object> actualResult = roomMonitoringWebController.getCurrentRoomTemperature(room.getId());

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetCurrentRoomTemperatureIllegalArgument() {
        // Arrange
        Room room = room1;
        ResponseEntity<Object> expectedResult = new ResponseEntity<>("The room does not exist.", HttpStatus.BAD_REQUEST);

        // Act
        Mockito.when(roomRepository.getCurrentRoomTempByRoomId(room.getId())).thenThrow(IllegalArgumentException.class);
        ResponseEntity<Object> actualResult = roomMonitoringWebController.getCurrentRoomTemperature(room.getId());

        // Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetCurrentRoomTemperatureNoSuchElement() {
        // Arrange
        Room room = room1;
        ResponseEntity<Object> expectedResult = new ResponseEntity<>("There are no temperature readings for that room", HttpStatus.BAD_REQUEST);

        // Act
        Mockito.when(roomRepository.getCurrentRoomTempByRoomId(room.getId())).thenThrow(NoSuchElementException.class);
        ResponseEntity<Object> actualResult = roomMonitoringWebController.getCurrentRoomTemperature(room.getId());

        // Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetCurrentRoomTemperatureRuntimeException() {
        // Arrange
        Room room = room1;
        ResponseEntity<Object> expectedResult = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        // Act
        Mockito.when(roomRepository.getCurrentRoomTempByRoomId(room.getId())).thenThrow(RuntimeException.class);
        ResponseEntity<Object> actualResult = roomMonitoringWebController.getCurrentRoomTemperature(room.getId());

        // Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfCreateRoomWorksWithMvcWhenRoomNameIsInvalid() throws Exception {
        //Arrange

        RoomDTOMinimal invalidDTO = new RoomDTOMinimal();
        invalidDTO.setHeight(2D);
        invalidDTO.setLength(5D);
        invalidDTO.setWidth(4D);
        invalidDTO.setName(null);
        invalidDTO.setFloor(1);


        //Arrange

        Gson gson = new Gson();
        String requestJson = gson.toJson(invalidDTO);

        //Act

        this.mockMvc.perform(post("/rooms/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void seeIfCreateRoomWorksWithMvcWhenRoomDTODimensionsAreInvalid() throws Exception {
        //Arrange

        RoomDTOMinimal invalidDTO = new RoomDTOMinimal();
        invalidDTO.setHeight(2D);
        invalidDTO.setLength(0.0D);
        invalidDTO.setWidth(4D);
        invalidDTO.setName("InvalidDimensionsRoom");
        invalidDTO.setFloor(1);

        //Arrange

        Gson gson = new Gson();
        String requestJson = gson.toJson(invalidDTO);

        //Act

        this.mockMvc.perform(post("/rooms/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isUnprocessableEntity());
    }


    @Test
    void seeIfGetHouseRoomsWorksWithMvc() throws Exception {
        //Arrange

        RoomDTOMinimal roomDTOMinimal = new RoomDTOMinimal();
        roomDTOMinimal.setName("Name");
        roomDTOMinimal.setWidth(2D);
        roomDTOMinimal.setLength(4D);
        roomDTOMinimal.setHeight(1D);
        roomDTOMinimal.setFloor(1);

        RoomDTOMinimal roomDTOMinimal2 = new RoomDTOMinimal();
        roomDTOMinimal2.setHeight(2D);
        roomDTOMinimal2.setLength(4D);
        roomDTOMinimal2.setWidth(4D);
        roomDTOMinimal2.setName("roomDTOMinimal2");
        roomDTOMinimal2.setFloor(1);

        List<RoomDTOMinimal> expectedResult = new ArrayList<>();
        expectedResult.add(roomDTOMinimal);
        expectedResult.add(roomDTOMinimal2);

        Mockito.doReturn(expectedResult).when(this.roomRepository).getAllRoomsAsMinimalDTOs();
        Mockito.when(userService.getUsernameFromToken()).thenReturn("ADMIN");


        //Arrange

        Gson gson = new Gson();
        String requestJson = gson.toJson(expectedResult);

        //Act

        this.mockMvc.perform(get("/rooms/").contentType(MediaType.APPLICATION_JSON).content(requestJson)).andExpect(status().isOk()).andReturn();
    }


    @Test
    void seeIfCreateRoomWorksWhenRoomExistsWithMvc() throws Exception {
        //Arrange

        RoomDTOMinimal roomDTOMinimal = new RoomDTOMinimal();
        roomDTOMinimal.setName("Name");
        roomDTOMinimal.setWidth(2D);
        roomDTOMinimal.setLength(4D);
        roomDTOMinimal.setHeight(1D);
        roomDTOMinimal.setFloor(1);


        Mockito.doReturn(false).when(this.houseRoomService).addMinimalRoomDTOToHouse(roomDTOMinimal);

        Gson gson = new Gson();
        String requestJson = gson.toJson(roomDTOMinimal);

        //Act

        this.mockMvc.perform(post("/rooms/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isConflict());
    }




    @Test
    void seeIfCreateRoomWorksWithMvc() throws Exception {
        //Arrange
        RoomDTOMinimal roomDTOMinimal = new RoomDTOMinimal();
        roomDTOMinimal.setName("Name");
        roomDTOMinimal.setWidth(2D);
        roomDTOMinimal.setLength(4D);
        roomDTOMinimal.setHeight(1D);
        roomDTOMinimal.setFloor(1);
        Mockito.doReturn(true).when(this.houseRoomService).addMinimalRoomDTOToHouse(roomDTOMinimal);

        Gson gson = new Gson();
        String requestJson = gson.toJson(roomDTOMinimal);

        //Act

        this.mockMvc.perform(post("/rooms/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isCreated());
    }
//    @Test
//    void seeIfGetRoomMaxTempInDaySuccessMockito() {
//        // Arrange
//        Room room = room1;
//
//        DateWithRoomIdDTO dateWithRoomIdDTO = new DateWithRoomIdDTO(date1, "Bedroom");
//
//        // Act
//
//        Mockito.when(roomRepository.getRoomMaxTempById(room.getSensorID(), date1)).thenReturn(30D);
//
//        ResponseEntity<Object> expectedResult = new ResponseEntity<>(30D, HttpStatus.OK);
//
//        ResponseEntity<Object> actualResult = roomMonitoringWebController.getRoomMaxTempInDay(dateWithRoomIdDTO);
//
//        // Assert
//
//        assertEquals(expectedResult, actualResult);
//
//    }
//
//    @Test
//    void seeIfGetRoomMaxTempInDayInvalidDate() {
//        // Arrange
//
//        DateWithRoomIdDTO dateWithRoomIdDTO = new DateWithRoomIdDTO(null, "Bedroom");
//        Link link = linkTo(methodOn(RoomMonitoringWebController.class).
//                getRoomMaxTempInDay(dateWithRoomIdDTO)).withRel("This date is not valid.");
//
//        // Act
//
//        ResponseEntity<Object> expectedResult = new ResponseEntity<>(link, HttpStatus.OK);
//
//        ResponseEntity<Object> actualResult = roomMonitoringWebController.getRoomMaxTempInDay(dateWithRoomIdDTO);
//
//        // Assert
//
//        assertEquals(expectedResult, actualResult);
//
//    }

//    @Test
//    void seeIfGetRoomMaxTempInDayInvalidRoom() {
//        // Arrange
//
//        DateWithRoomIdDTO dateWithRoomIdDTO = new DateWithRoomIdDTO(date1, null);
//        Link link = linkTo(methodOn(RoomMonitoringWebController.class).
//                getRoomMaxTempInDay(dateWithRoomIdDTO)).withRel("This date is not valid.");
//
//        // Act
//
//        Mockito.when()
//
//        ResponseEntity<Object> expectedResult = new ResponseEntity<>(link, HttpStatus.OK);
//
//        ResponseEntity<Object> actualResult = roomMonitoringWebController.getRoomMaxTempInDay(dateWithRoomIdDTO);
//
//        // Assert
//
//        assertEquals(expectedResult, actualResult);
//
//    }
//
//    @Test
//    void seeIfGetRoomMaxTempInDayRuntimeException() {
//        // Arrange
//
//        Room room = room1;
//        DateWithRoomIdDTO dateWithRoomIdDTO = new DateWithRoomIdDTO(date1, "Bedroom");
//
//        // Act
//
//        Mockito.when(roomRepository.getRoomMaxTempById(room.getSensorID(), date1)).thenThrow(RuntimeException.class);
//
//        ResponseEntity<Object> expectedResult = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//
//        ResponseEntity<Object> actualResult = roomMonitoringWebController.getRoomMaxTempInDay(dateWithRoomIdDTO);
//
//        // Assert
//
//        assertEquals(expectedResult, actualResult);
//
//    }
//
//    @Test
//    void seeIfGetRoomMaxTempInDayNoSuchElementException() {
//        // Arrange
//
//        Room room = room1;
//        DateWithRoomIdDTO dateWithRoomIdDTO = new DateWithRoomIdDTO(date1, "Bedroom");
//
//        // Act
//
//        Mockito.when(roomRepository.getRoomMaxTempById(room.getSensorID(), date1)).thenThrow(NoSuchElementException.class);
//
//        ResponseEntity<Object> expectedResult = new ResponseEntity<>("There are no readings for the given date.", HttpStatus.BAD_REQUEST);
//
//        ResponseEntity<Object> actualResult = roomMonitoringWebController.getRoomMaxTempInDay(dateWithRoomIdDTO);
//
//        // Assert
//
//        assertEquals(expectedResult, actualResult);
//
//    }

}
