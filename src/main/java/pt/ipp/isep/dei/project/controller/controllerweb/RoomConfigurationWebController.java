package pt.ipp.isep.dei.project.controller.controllerweb;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.ApplicationScope;
import pt.ipp.isep.dei.project.dto.*;
import pt.ipp.isep.dei.project.model.room.RoomRepository;
import pt.ipp.isep.dei.project.model.sensortype.SensorTypeRepository;

import java.util.List;

@RestController
@ApplicationScope
@RequestMapping("/roomConfiguration")
public class RoomConfigurationWebController {

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    SensorTypeRepository sensorTypeRepository;

    /**
     * Shows all the Sensor Types present in the database.
     *
     * @return OK status and a list of sensor Types.
     */
    @GetMapping(path = "/types", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> retrieveAllSensorTypes() {
        List<SensorTypeDTO> sensorTypeDTOS = sensorTypeRepository.getAllSensorTypeDTO();
        return new ResponseEntity<>(sensorTypeDTOS, HttpStatus.OK);
    }

    /**
     * Shows all the Rooms present in the database.
     *
     * @return OK status and a list of Room Minimal DTO.
     */
    @GetMapping(path = "/rooms", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> retrieveAllRooms() {
        List<RoomDTOMinimal> roomDTOList = roomRepository.getAllDTOWebInformation();
        return new ResponseEntity<>(roomDTOList, HttpStatus.OK);
    }

    /**
     * Shows the Room Sensors present in a given Room
     *
     * @param id is the geographical area id.
     * @return OK status and a list of Area Sensor DTOs.
     */
    @GetMapping("/rooms/{id}/sensors")
    public ResponseEntity<List<RoomSensorDTOMinimal>> retrieveAllSensors(@PathVariable String id) {
        List<RoomSensorDTOMinimal> roomSensorDTOList = roomRepository.getRoomDTOByName(id).getSensorDTOMinimalistList();
        return new ResponseEntity<>(roomSensorDTOList, HttpStatus.OK);
    }


}
