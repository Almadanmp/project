package pt.ipp.isep.dei.project.controller.controllerweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.ApplicationScope;
import pt.ipp.isep.dei.project.dto.AreaSensorDTO;
import pt.ipp.isep.dei.project.dto.GeographicAreaDTO;
import pt.ipp.isep.dei.project.dto.SensorTypeDTO;
import pt.ipp.isep.dei.project.dto.mappers.SensorTypeMapper;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicAreaRepository;
import pt.ipp.isep.dei.project.model.sensortype.SensorTypeRepository;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@ApplicationScope
@RequestMapping("/sensors")
public class SensorSettingsWebController {

    @Autowired
    private GeographicAreaRepository geographicAreaRepository;

    @Autowired
    private SensorTypeRepository sensorTypeRepository;

    // Part 1 - Geographical Areas

    /**
     * Shows a Geographical Area selected by it's ID, given that it is present in the database.
     *
     * @param id is the geographical area id.
     * @return OK status and a Geographic Area DTO.
     */
    @GetMapping("/areas/{id}")
    public ResponseEntity<GeographicAreaDTO> retrieveGA(@PathVariable long id) {
        GeographicAreaDTO geographicAreaDTO = geographicAreaRepository.getDTOById(id);
        return new ResponseEntity<>(geographicAreaDTO, HttpStatus.OK);
    }

    // Part 2 - Sensors


    /**
     * US006 Web Controller:
     * Creates a new sensor and adds it to a Geographical Area.
     *
     * @param id is the geographical area id.
     * @return OK status if the area sensor is successfully created. Returns htttp status 'not acceptable' if the sensor already exists.
     */
    @PostMapping("/areas/{id}/sensors")
    public ResponseEntity<Object> createAreaSensor(@RequestBody AreaSensorDTO areaSensorDTO,
                                                   @PathVariable long id) {
        GeographicAreaDTO geographicAreaDTO;
        try {
            geographicAreaDTO = geographicAreaRepository.getDTOById(id);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("That ID does not belong to any Geographic Area", HttpStatus.NOT_FOUND);
        }
        if (areaSensorDTO.getSensorId() != "" && areaSensorDTO.getSensorId() != null && areaSensorDTO.getDateStartedFunctioning() != ""
                && areaSensorDTO.getType() != "") {
            if (areaSensorDTO.getName().equals("")) {
                return new ResponseEntity<>("The sensor name is not valid.", HttpStatus.UNPROCESSABLE_ENTITY);
            }
            if (!geographicAreaRepository.sensorExists(areaSensorDTO.getSensorId())) {
                geographicAreaRepository.addSensorDTO(geographicAreaDTO, areaSensorDTO);
                geographicAreaRepository.updateAreaDTO(geographicAreaDTO);
                return new ResponseEntity<>(areaSensorDTO, HttpStatus.CREATED);
            }
            return new ResponseEntity<>("The sensor already exists in the database", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("There was a problem creating the Area Sensor, because one or more components are missing!",
                HttpStatus.BAD_REQUEST);
    }

    /**
     * US010 WEB controller: deactivate area sensor with id sensor
     *
     * @param idArea   area id where the area sensor id
     * @param idSensor sensor id
     * @return ok status if the area sensor exists
     */
    @PutMapping("areas/{id}/sensors/{id2}")
    public ResponseEntity<Object> deactivateAreaSensor(@PathVariable("id") long idArea, @PathVariable("id2") String idSensor) {
        try {
            if (geographicAreaRepository.deactivateAreaSensor(idArea, idSensor)) {
                return new ResponseEntity<>("The Area Sensor has been deactivated.", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("The Area Sensor is already deactivated", HttpStatus.CONFLICT);
            }
        } catch (NoSuchElementException ok) {
            return new ResponseEntity<>("There is no Geographic Area or Sensor with that ID.", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * US011 Web Controller:
     * Method for removing area sensors from repository.
     *
     * @param id  geographic area id.
     * @param id2 area sensor id.
     * @return OK status if area sensor is found and removed or NOT_FOUND status if not found.
     */
    @DeleteMapping(value = "/areas/{id}/sensors/{id2}")
    public ResponseEntity<String> removeAreaSensor(@PathVariable long id, @PathVariable String id2) {
        GeographicAreaDTO geoArea = geographicAreaRepository.getDTOById(id);
        if (geographicAreaRepository.removeSensorDTO(geoArea, id2)) {
            geographicAreaRepository.updateAreaDTO(geoArea);
            return new ResponseEntity<>("Sensor was removed successfully from geographic area", HttpStatus.OK);
        }
        return new ResponseEntity<>("Sensor doesn't exist or wasn't found.", HttpStatus.NOT_FOUND);
    }

    /**
     * WEb controller: get area sensor dto by id (and by area id)
     *
     * @param idArea   area id where the area sensor is
     * @param idSensor sensor id
     * @return ok status if the sensor with the selected id exists
     */
    @GetMapping("areas/{id}/sensors/{id2}")
    public AreaSensorDTO getAreaSensor(@PathVariable("id") long idArea, @PathVariable("id2") String idSensor) {
        GeographicAreaDTO geographicArea = geographicAreaRepository.getDTOById(idArea);
        return geographicArea.getAreaSensorByID(idSensor);
    }


    // USER STORY 05 - As an Administrator, I want to define the sensor types.

    @PostMapping(value = "/sensorTypes")
    public ResponseEntity<Object> addSensorType(@RequestBody SensorTypeDTO sensorTypeDTO) {
        if (sensorTypeDTO.getName() == null) {
            return new ResponseEntity<>("Null.", HttpStatus.BAD_REQUEST);
        }
        if (sensorTypeDTO.getUnits() == null) {
            return new ResponseEntity<>("Null.", HttpStatus.BAD_REQUEST);
        }
        if (sensorTypeDTO.getUnits().equals("")) {
            return new ResponseEntity<>("Empty information.", HttpStatus.BAD_REQUEST);
        }
        if (sensorTypeDTO.getName().equals("")) {
            return new ResponseEntity<>("Empty information.", HttpStatus.BAD_REQUEST);
        }
        List<SensorTypeDTO> repoTypes = sensorTypeRepository.getAllSensorTypeDTO();
        for (SensorTypeDTO a : repoTypes) {
            if (a.getName().equals(sensorTypeDTO.getName())) {
                return new ResponseEntity<>("The sensor already exists.", HttpStatus.CONFLICT);
            }
        }
        sensorTypeRepository.add(SensorTypeMapper.dtoToObject(sensorTypeDTO));
        return new ResponseEntity<>(sensorTypeDTO, HttpStatus.OK);
    }

    // USER STORY 05 - EXTRA - Display the already defined sensor types

    /**
     * This method displays all Sensor Types
     *
     * @return ResponseEntity with all the sensor types.
     */
    @GetMapping(value = "/sensorTypes")
    public ResponseEntity<Object> getSensorTypes() {
        return new ResponseEntity<>(sensorTypeRepository.getAllSensorTypeDTO(), HttpStatus.OK);
    }
}
