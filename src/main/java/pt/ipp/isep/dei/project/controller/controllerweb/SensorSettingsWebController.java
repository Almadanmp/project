package pt.ipp.isep.dei.project.controller.controllerweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.ApplicationScope;
import pt.ipp.isep.dei.project.dto.AreaSensorDTO;
import pt.ipp.isep.dei.project.dto.GeographicAreaDTO;
import pt.ipp.isep.dei.project.dto.GeographicAreaWebDTO;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicAreaRepository;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@ApplicationScope
@RequestMapping("/sensorsettings")
public class SensorSettingsWebController {

    @Autowired
    private GeographicAreaRepository geographicAreaRepository;

    // Part 0 - Main menu

    /**
     * Shows all the possible operations relatable to sensors and the respective link.
     */
    @GetMapping("")
    public String intro() {
        return "Welcome to the Sensor Settings Menu: \nGET[/sensorsettings/areas] \nGET[/sensorsettings/areas/{id}] " +
                "\nGET[/sensorsettings/areas/{id}/sensors] \nPOST[/areas/{id}/sensors] \nPUT[/sensorsettings/areas/{id}/sensors/{id2}] " +
                "\nDELETE[/sensorsettings/areas/{id}/sensors/{id2}]";
    }

    // Part 1 - Geographical Areas

    /**
     * Shows all the Geographical Areas present in the database.
     * @return OK status and a list of Geographic Area DTO.
     */
    @GetMapping(path = "/areas", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> retrieveAllGeographicAreas() {
        List<GeographicAreaWebDTO> geographicAreaDTOList = geographicAreaRepository.getAllDTOWebInformation();
        return new ResponseEntity<>(geographicAreaDTOList, HttpStatus.OK);
    }

    /**
     * Shows a Geographical Area selected by it's ID, given that it is present in the database.
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
     * Shows the area sensors present in a given Geographical Area
     * @param id is the geographical area id.
     * @return OK status and a list of Area Sensor DTOs.
     */
    @GetMapping("/areas/{id}/sensors")
    public ResponseEntity<List<AreaSensorDTO>> retrieveAllSensors(@PathVariable long id) {
        List<AreaSensorDTO> areaSensorDTOList = geographicAreaRepository.getDTOById(id).getSensors();
        return new ResponseEntity<>(areaSensorDTOList, HttpStatus.OK);
    }


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
        if (areaSensorDTO.getName() != null && areaSensorDTO.getSensorId() != null && areaSensorDTO.getType() != null && areaSensorDTO.getDateStartedFunctioning() != null) {
            if (areaSensorDTO.getName().equals("")) {
                return new ResponseEntity<>("The sensor name is not valid.", HttpStatus.UNPROCESSABLE_ENTITY);
            }
            if (geographicAreaRepository.addSensorDTO(geographicAreaDTO, areaSensorDTO)) {
                geographicAreaRepository.updateAreaDTO(geographicAreaDTO);
                Link link = linkTo(methodOn(SensorSettingsWebController.class).removeAreaSensor(id, areaSensorDTO.getSensorId())).withRel("Delete the created sensor");
                areaSensorDTO.add(link);
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
        GeographicAreaDTO geographicArea = geographicAreaRepository.getDTOById(idArea);
        AreaSensorDTO areaSensorDTO = geographicArea.getAreaSensorByID(idSensor);
        if (geographicArea.deactivateSensorDTO(areaSensorDTO)) {
            geographicAreaRepository.updateAreaDTO(geographicArea);
            return new ResponseEntity<>("Area Sensor is deactivated", HttpStatus.OK);
        }
        return new ResponseEntity<>("Area Sensor is active", HttpStatus.NOT_ACCEPTABLE);
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

}
