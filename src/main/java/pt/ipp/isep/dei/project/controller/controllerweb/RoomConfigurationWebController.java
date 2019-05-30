package pt.ipp.isep.dei.project.controller.controllerweb;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.ApplicationScope;
import pt.ipp.isep.dei.project.dto.*;
import pt.ipp.isep.dei.project.model.room.RoomRepository;
import pt.ipp.isep.dei.project.model.sensortype.SensorTypeRepository;

import java.util.ArrayList;
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

    /**
     * US253 Web Controller:
     * Creates a new sensor and adds it to a Room.
     *
     * @param idRoom is the room id.
     * @return OK status if the area sensor is successfully created. Returns htttp status 'not acceptable' if the sensor already exists.
     */
    @PostMapping("/rooms/{idRoom}/sensors")
    public ResponseEntity<Object> createRoomSensor(@RequestBody RoomSensorDTO roomSensorDTO,
                                                   @PathVariable String idRoom) {
        RoomDTO roomDTO;
        List<SensorTypeDTO> sensorTypeDTOS = sensorTypeRepository.getAllSensorTypeDTO();
        try {
            roomDTO = roomRepository.getRoomDTOByName(idRoom);
        } catch (IndexOutOfBoundsException e) {
            return new ResponseEntity<>("That ID does not belong to any Room", HttpStatus.NOT_FOUND);
        }

        if (roomSensorDTO.getName() != null && roomSensorDTO.getId() != null && roomSensorDTO.getType() != null && roomSensorDTO.getDateStartedFunctioning() != null) {
            if (roomSensorDTO.getName().equals("")) {
                return new ResponseEntity<>("The sensor name is not valid.", HttpStatus.UNPROCESSABLE_ENTITY);
            }
            List<String> types = new ArrayList<>();
            for (SensorTypeDTO st : sensorTypeDTOS) {
                types.add(st.getName());
            }
            if (!types.contains(roomSensorDTO.getType())) {
                return new ResponseEntity<>("The sensor type is not valid.", HttpStatus.UNPROCESSABLE_ENTITY);
            }
            if (roomDTO.addSensor(roomSensorDTO)) {
                roomRepository.updateDTORoom(roomDTO);
                //  Link link = linkTo(methodOn(SensorSettingsWebController.class).removeAreaSensor(id, areaSensorDTO.getSensorId())).withRel("Delete the created sensor");
                //  roomSensorDTO.add(link);
                return new ResponseEntity<>(roomSensorDTO, HttpStatus.CREATED);
            }
            return new ResponseEntity<>("The sensor already exists in the database", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("There was a problem creating the Room Sensor, because one or more components are missing!",
                HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/rooms/{idRoom}/sensors/{idSensor}")
    public ResponseEntity<String> removeRoomSensor(@PathVariable String idRoom, @PathVariable String idSensor) {
        RoomDTO roomDTO = roomRepository.getRoomDTOByName(idRoom);
        if (roomRepository.removeSensorDTO(roomDTO, idSensor)) {
            roomRepository.updateDTORoom(roomDTO);
            return new ResponseEntity<>("Sensor was removed successfully from geographic area", HttpStatus.OK);
        }
        return new ResponseEntity<>("Sensor doesn't exist or wasn't found.", HttpStatus.NOT_FOUND);
    }

}
