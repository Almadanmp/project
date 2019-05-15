package pt.ipp.isep.dei.project.controllerweb;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import pt.ipp.isep.dei.project.dto.RoomDTOWeb;
import pt.ipp.isep.dei.project.model.device.WaterHeater;
import pt.ipp.isep.dei.project.model.device.devicespecs.WaterHeaterSpec;
import pt.ipp.isep.dei.project.model.room.Room;
import pt.ipp.isep.dei.project.model.room.RoomRepository;
import pt.ipp.isep.dei.project.model.room.RoomSensor;
import pt.ipp.isep.dei.project.repository.HouseCrudeRepo;
import pt.ipp.isep.dei.project.repository.RoomCrudeRepo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HouseConfigurationWebControllerTest {

    @Mock
    HouseCrudeRepo houseCrudeRepo;

    @Mock
    private RoomCrudeRepo roomCrudeRepo;

    @Autowired
    private HouseConfigurationWebController webController;

    private Room validRoom;

    @BeforeEach
    void arrangeArtifacts() {
        MockitoAnnotations.initMocks(this);
        validRoom = new Room("Kitchen", "1st Floor Kitchen", 1, 4, 5, 3, "01");
    }

//    @Test
//    void seeIfCreateRoomWorks() {
//        //Arrange
//
//        String expectedResult = "The room was successfully added.";
//
//        RoomDTOWeb roomDTOWeb = new RoomDTOWeb();
//        roomDTOWeb.setFloor(1);
//        roomDTOWeb.setHeight(3);
//        roomDTOWeb.setLength(5);
//        roomDTOWeb.setWidth(4);
//        roomDTOWeb.setName("Kitchen");
//
//        //Act
//
//        String actualResult = webController.createRoom(roomDTOWeb);
//
//        //Assert
//        assertEquals(expectedResult, actualResult);
//    }
}