package pt.ipp.isep.dei.project.controller.controllerweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.ipp.isep.dei.project.dto.GeographicAreaDTO;
import pt.ipp.isep.dei.project.dto.GeographicAreaPlainLocalDTO;
import pt.ipp.isep.dei.project.model.areatype.AreaTypeRepository;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicAreaRepository;

import java.util.NoSuchElementException;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/geographic_area_settings")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "http://localhost:3002"}, maxAge = 3600)
public class GASettingsWebController {

    @Autowired
    private GeographicAreaRepository geographicAreaRepo;

    @Autowired
    private AreaTypeRepository areaTypeRepository;

    /* User Story 02 - As a System Administrator I want to receive a list of all the previously stated Types of area. */

    /**
     * This method displays all Geographical Area Types
     *
     * @return ResponseEntity with all the geographic area types.
     */
    @GetMapping(value = "/areaTypes")
    public ResponseEntity<Object> getAreaTypes() {
        return new ResponseEntity<>(areaTypeRepository.getAllTypesDTO(), HttpStatus.OK);
    }

    /* User Story - 03 As a System Administrator I want to Create a new Geographic Area */

    /**
     * Method to create a DTO of Geographic Area
     *
     * @return ResponseEntity
     */
    @PostMapping(value = "/areas")
    public ResponseEntity<Object> createGeoArea(@RequestBody GeographicAreaPlainLocalDTO dto) {
        if (dto.getName() != null && dto.getTypeArea() != null && dto.getLatitude() != null && dto.getLongitude() != null && dto.getAltitude() != null) {
            if (geographicAreaRepo.addAndPersistPlainDTO(dto)) {
                Link link = linkTo(methodOn(GASettingsWebController.class).getAllGeographicAreas()).withRel("See all geographic areas");
                dto.add(link);
                return new ResponseEntity<>(dto, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("The Geographic Area hasn't been created. That Area already exists.", HttpStatus.CONFLICT);
            }
        }
        return new ResponseEntity<>("The Geographic Area hasn't been created. You have entered an" +
                " invalid Area.", HttpStatus.BAD_REQUEST);
    }

    /* User Story 04 - I want to list all geo areas of given Type. */

    /**
     * This method displays all Geographical Area of a given Type
     *
     * @return ResponseEntity with all the geographic areas of a given type.
     */
    @GetMapping(value = "/areasOfType/{typeAreaId}")
    public ResponseEntity<Object> getAllAreasOfGivenType(@PathVariable String typeAreaId) {
        return new ResponseEntity<>(geographicAreaRepo.getGeoAreasDTOByType(typeAreaId), HttpStatus.OK);
    }

    /**
     * this method displays all the information of the Geographic Areas DTOs
     *
     * @return ResponseEntity with all the geographic areas
     */
    @GetMapping("/areas")
    public ResponseEntity<Object> getAllGeographicAreas() {
        return new ResponseEntity<>(geographicAreaRepo.getAllDTO(), HttpStatus.OK);
    }


    /**
     * Add daughter area to a mother area
     *
     * @param idAreaChild  of the geoArea to be added
     * @param idAreaParent of the geoArea with the daughter area
     * @return string with info if geoArea was added or not
     */
    @PutMapping("areas/list/{idParent}")
    public ResponseEntity<Object> addChildArea(@RequestBody long idAreaChild, @PathVariable("idParent") long idAreaParent) {
        GeographicAreaDTO result;
        Link link;
        try {
            if (geographicAreaRepo.addChildArea(idAreaChild, idAreaParent)) {
                result = geographicAreaRepo.getDTOByIdWithParent(idAreaParent);
                link = linkTo(methodOn(GASettingsWebController.class).getGeographicArea(idAreaChild)).withRel("See geographic area");
                result.add(link);
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("The Geographic Area hasn't been added. The daughter area is already contained in the mother area.", HttpStatus.CONFLICT);
            }
        } catch (NoSuchElementException ok) {
            return new ResponseEntity<>("There is no  Geographic Area with that ID.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("areas/list/{idParent}/{idChild}")
    public ResponseEntity<Object> removeChildArea(@PathVariable("idChild") long idAreaChild, @PathVariable("idParent") long idAreaParent) {
        GeographicAreaDTO result;
        Link link;
        try {
            if (geographicAreaRepo.removeChildArea(idAreaChild, idAreaParent)) {
                result = geographicAreaRepo.getDTOByIdWithParent(idAreaParent);
                link = linkTo(methodOn(GASettingsWebController.class).getGeographicArea(idAreaChild)).withRel("See geographic area");
                result.add(link);
                return new ResponseEntity<>(geographicAreaRepo.getDTOByIdWithParent(idAreaParent), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("The Geographic Area hasn't been removed. The daughter area is already not contained in the mother area.", HttpStatus.CONFLICT);
            }
        } catch (NoSuchElementException ok) {
            return new ResponseEntity<>("There is no  Geographic Area with that ID.", HttpStatus.NOT_FOUND);
        }
    }

    /* USER STORY US011 - As an Administrator, I want to removeSensor a sensor from a geographical area, so that it will no
    longer be used.*/

    /**
     * This method removes a sensor selected from a list of sensors of a previously selected geographic area
     */
    @DeleteMapping("areas/{id}")
    public ResponseEntity<String> removeSensor(@PathVariable("id") long id, @RequestBody String sensorId) {
        try {
            if (geographicAreaRepo.removeSensorById(id, sensorId)) {
                return new ResponseEntity<>("The sensor was successfully removed from the selected geographic area.", HttpStatus.OK);
            }
            return new ResponseEntity<>("There is no sensor with that ID in this geographic area.", HttpStatus.NOT_FOUND);
        } catch (NoSuchElementException ok) {
            return new ResponseEntity<>("There is no geographic area", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Get daughter areas from an geo area
     *
     * @param id mother area id
     * @return list of daughter areas on a mother area
     */
    @GetMapping("areas/{id}")
    public ResponseEntity<Object> getGeographicArea(@PathVariable("id") long id) {
        return new ResponseEntity<>(geographicAreaRepo.getDTOByIdWithParent(id), HttpStatus.OK);
    }

}
