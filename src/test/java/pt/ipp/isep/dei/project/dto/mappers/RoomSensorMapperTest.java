package pt.ipp.isep.dei.project.dto.mappers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.dto.ReadingDTO;
import pt.ipp.isep.dei.project.dto.RoomSensorDTO;
import pt.ipp.isep.dei.project.model.room.RoomSensor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoomSensorMapperTest {

    private RoomSensor roomSensor;
    private RoomSensorDTO validRoomSensorDTO;

    @BeforeEach
    void arrangeArtifacts() {

        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();

        try {
            date = validSdf.parse("11/02/2018 10:00:00");

        } catch (ParseException e) {
            e.printStackTrace();
        }

        roomSensor = new RoomSensor("SensorDTO1", "test", "Temperature", date);
        validRoomSensorDTO = new RoomSensorDTO();
        validRoomSensorDTO.setActive(true);
        validRoomSensorDTO.setSensorId("12");
        validRoomSensorDTO.setName("test");
        validRoomSensorDTO.setTypeSensor("Temperature");
        validRoomSensorDTO.setUnits("Celsius");

        validRoomSensorDTO.setDateStartedFunctioning("11/02/2018 10:00:00");

        ReadingDTO readingDTO = new ReadingDTO();
        readingDTO.setSensorId("SensorID");
        readingDTO.setDate(date);
        List<ReadingDTO> readingDTOList = new ArrayList<>();
        readingDTOList.add(readingDTO);

        validRoomSensorDTO.setReadingList(readingDTOList);

    }

    @Test
    void dtoToObject() {
        //Act

        RoomSensor actualResult = RoomSensorMapper.dtoToObject(validRoomSensorDTO);

        //Assert

        assertEquals(roomSensor.getName(), actualResult.getName());
    }

    @Test
    void objectToDTO() {
        //Act

        RoomSensorDTO actualResult = RoomSensorMapper.objectToDTO(roomSensor);
        //Assert

        assertEquals(validRoomSensorDTO.getName(), actualResult.getName());
    }
}