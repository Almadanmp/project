package pt.ipp.isep.dei.project.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.testng.Assert.*;

class MapperTest {

    private GeographicArea geoArea;
    private GeographicArea geoAreaWithSensors;
    private Mapper mapper;
    private Sensor sensor;
    private House validHouse;
    private Date date; // Wed Nov 21 05:12:00 WET 2018

    @BeforeEach
    void arrangeArtifacts() {

        geoArea = new GeographicArea("Portugal", new TypeArea("Country"), 300, 200,
                new Local(50, 50, 10));
        mapper = new Mapper();
        SimpleDateFormat day = new SimpleDateFormat("dd-MM-yyyy");
        try {
            date = day.parse("12-12-2018");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String PATH_TO_FRIDGE = "pt.ipp.isep.dei.project.model.device.devicetypes.FridgeType";
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        validHouse = new House("ISEP", new Address("Rua Dr. António Bernardino de Almeida",
                "4455-125", "Porto"),
                new Local(20, 20, 20), 60,
                180, deviceTypeString);
        sensor = new Sensor("RF12345", "SensOne", new TypeSensor("Temperature", "Celsius"),
                new Local(31, 15, 3), date);
        geoAreaWithSensors = new GeographicArea("Espanha", new TypeArea("Country"), 300, 200,
                new Local(50, 50, 10));
        geoAreaWithSensors.addSensor(sensor);
    }

    @Test
    void seeIfCreateDTOFromGA() {
        // Act
        GeographicAreaDTO resultDTO = mapper.geographicAreaToDTO(geoArea);
        GeographicArea result = mapper.geographicAreaDTOToObject(resultDTO);
        geoArea.addSensor(sensor);

        // Assert
        assertTrue(resultDTO instanceof GeographicAreaDTO);
        assertTrue(result instanceof GeographicArea);
    }

    @Test
    void seeIfCreateDTOFromSensor() {

        //Act
        SensorDTO resultDTO = mapper.sensorToDTO(sensor);
        Sensor result = mapper.sensorDTOToObject(resultDTO);

        //Assert
        assertTrue(resultDTO instanceof SensorDTO);
        assertTrue(result instanceof Sensor);
    }

    @Test
    void seeIfSensorToDTOWorks() {
        //Act
        SensorDTO sensorDTO = mapper.sensorToDTO(sensor);

        sensorDTO.setUniqueID(UUID.randomUUID());

        //Assert
        assertTrue(sensorDTO instanceof SensorDTO);
        assertTrue(sensorDTO.getUniqueID() instanceof UUID);
    }

    @Test
    void seeIfAddSensorDTOWorks() {
        //Act
        GeographicAreaDTO resultDTO = mapper.geographicAreaToDTO(geoArea);
        SensorDTO sensorDTO = mapper.sensorToDTO(sensor);

        resultDTO.addSensorDTO(sensorDTO);

        //Assert
        assertTrue(resultDTO instanceof GeographicAreaDTO);
    }

    @Test
    void seeIfAddSensorDTOWorksWithSensors() {
        //Act
        GeographicAreaDTO resultDTO = mapper.geographicAreaToDTO(geoAreaWithSensors);

        //Assert
        assertTrue(resultDTO instanceof GeographicAreaDTO);
    }

    @Test
    void seeIfAddSensorDTOWorksWithoutSensors() {
        //Act
        GeographicAreaDTO resultDTO = mapper.geographicAreaToDTO(geoArea);

        //Assert
        assertTrue(resultDTO instanceof GeographicAreaDTO);
    }

    @Test
    void seeIfUpdateHouseRoom() {
        //Act
        Room result = mapper.updateHouseRoom(null, validHouse);
        //Assert
        assertNull(result);
    }

    @Test
    void seeIfUpdateHouseRoomWorks() {
        //Arrange
        Room room1 = new Room("room", 2, 50, 50, 2);
        validHouse.addRoom(room1);
        RoomDTO roomDTO = mapper.roomToDTO(room1);
        //Act
        Room result = mapper.updateHouseRoom(roomDTO, validHouse);
        //Assert
        assertEquals(room1, result);
    }

    @Test
    void seeIfUpdateHouseRoomDoesNotWorks() {
        //Arrange
        Room room1 = new Room("room1", 2, 50, 50, 2);
        Room room2 = new Room("room2", 3, 50, 50, 2);
        validHouse.addRoom(room1);
        RoomDTO roomDTO = mapper.roomToDTO(room1);
        //Act
        Room result = mapper.updateHouseRoom(roomDTO, validHouse);
        //Assert
        assertNotEquals(room2, result);
    }

    @Test
    void seeIfUpdateHouseRoomRoomList() {
        //Arrange
        Room room1 = new Room("room1", 2, 50, 50, 2);
        Room room2 = new Room("room2", 3, 50, 50, 2);
        validHouse.addRoom(room1);
        RoomDTO roomDTO = mapper.roomToDTO(room2);
        //Act
        Room result = mapper.updateHouseRoom(roomDTO, validHouse);
        //Assert
        assertNull(result);
    }

    @Test
    void seeIfUpdateHouseRoomDoesNotWorkNull() {
        //Arrange
        Room room1 = new Room("room1", 2, 50, 50, 2);
        RoomDTO roomDTO = mapper.roomToDTO(room1);
        //Act
        Room result = mapper.updateHouseRoom(roomDTO, validHouse);
        //Assert
        assertNull(result);
    }


}