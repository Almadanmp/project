package pt.ipp.isep.dei.project.controller.controllerweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.ipp.isep.dei.project.dto.AreaSensorDTO;
import pt.ipp.isep.dei.project.dto.AreaTypeDTO;
import pt.ipp.isep.dei.project.dto.GeographicAreaDTO;
import pt.ipp.isep.dei.project.dto.GeographicAreaPlainLocalDTO;
import pt.ipp.isep.dei.project.dto.mappers.AreaTypeMapper;
import pt.ipp.isep.dei.project.model.areatype.AreaTypeRepository;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicAreaRepository;
import pt.ipp.isep.dei.project.model.user.UserService;

import java.util.List;
import java.util.NoSuchElementException;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/geoAreas")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "http://localhost:3002"}, maxAge = 3600)
public class GeoAreasWebController {

    @Autowired
    private UserService userService;

    @Autowired
    private GeographicAreaRepository geographicAreaRepo;

    @Autowired
    private AreaTypeRepository areaTypeRepository;

    // USER STORY 01 - As an Administrator, I want to add a new type of Geographic Area.

    @PostMapping(value = "/areaTypes")
    public ResponseEntity<Object> addAreaType(@RequestBody AreaTypeDTO typeToAdd) {
        if (typeToAdd.getName() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (typeToAdd.getName().equals("")) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<AreaTypeDTO> repoTypes = areaTypeRepository.getAllTypesDTO();
        for (AreaTypeDTO a : repoTypes) {
            if (a.getName().equals(typeToAdd.getName())) {
                return new ResponseEntity<>(a, HttpStatus.CONFLICT); // The .add line already checks for duplicates, but we want to be able to return the original object in case there's a duplicate.
            }
        }
        areaTypeRepository.add(AreaTypeMapper.dtoToObject(typeToAdd));
        return new ResponseEntity<>(typeToAdd, HttpStatus.OK);
    }

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
    @PostMapping(value = "/")
    public ResponseEntity<Object> createGeoArea(@RequestBody GeographicAreaPlainLocalDTO dto) {
        if (dto.getName() != null && dto.getTypeArea() != null && dto.getLatitude() != null && dto.getLongitude() != null && dto.getAltitude() != null) {
            if (geographicAreaRepo.addAndPersistPlainDTO(dto)) {
                Link link = linkTo(methodOn(GeoAreasWebController.class).getAllGeographicAreas()).withRel("See all geographic areas");
                dto.add(link);
                return new ResponseEntity<>(dto, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("The Geographic Area hasn't been created. That Area already exists.", HttpStatus.CONFLICT);
            }
        }
        return new ResponseEntity<>("The Geographic Area hasn't been created. You have entered an" +
                " invalid Area.", HttpStatus.BAD_REQUEST);
    }

    /**
     * US004 - List all geographical areas of a given type:
     * This webController method displays all the information of the Geographic Areas DTOs
     * So that the FrontEnd UI can then list them according to type.
     *
     * @return ResponseEntity with all the geographic areas and their type information.
     */

    @GetMapping("/")
    public ResponseEntity<Object> getAllGeographicAreas() {
        List<GeographicAreaDTO> allDTO = geographicAreaRepo.getAllDTO();
        if (allDTO == null) {
            return new ResponseEntity<>("No Geographical Areas available", HttpStatus.BAD_REQUEST);
        }
        if (allDTO.isEmpty()) {
            return new ResponseEntity<>("No Geographical Areas available", HttpStatus.BAD_REQUEST);
        }
        AreaSensorDTO sensorDTO = new AreaSensorDTO();
        for (GeographicAreaDTO g : allDTO) {
            if (userService.getUsernameFromToken().equals("admin")) {
                Link getChildAreas = linkTo(methodOn(GeoAreasWebController.class).getChildAreas(g.getGeographicAreaId())).
                        withRel("List child areas.");
                Link addChildArea = linkTo(methodOn(GeoAreasWebController.class).addChildArea(0
                        , g.getGeographicAreaId())).withRel("Add child area.");

                Link sensors = linkTo(methodOn(GeoAreasWebController.class).getAreaSensors(g.getGeographicAreaId())).
                        withRel("List area sensors.");
                Link addSensor = linkTo(methodOn(SensorSettingsWebController.class).createAreaSensor(sensorDTO,g.getGeographicAreaId())).
                        withRel("Add a new area sensors.");
                g.add(addChildArea);
                g.add(getChildAreas);
                g.add(sensors);
                g.add(addSensor);
            }
        }
        return new ResponseEntity<>(allDTO, HttpStatus.OK);
    }

    /**
     * Shows the area sensors present in a given Geographical Area
     *
     * @param id is the geographical area id.
     * @return OK status and a list of Area Sensor DTOs.
     */
    @GetMapping("/areas/{id}/children")
    public ResponseEntity<List<GeographicAreaDTO>> getChildAreas(@PathVariable long id) {
        List<GeographicAreaDTO> childAreaDTOList = geographicAreaRepo.getDTOById(id).getDaughterAreas();
        for (GeographicAreaDTO g : childAreaDTOList) {
            if (userService.getUsernameFromToken().equals("admin")) {
                Link removeChildArea = linkTo(methodOn(GeoAreasWebController.class).removeChildArea(g.getGeographicAreaId(),
                        g.getGeographicAreaId())).withRel("Remove Child Area");
                g.add(removeChildArea);
            }
        }
        return new ResponseEntity<>(childAreaDTOList, HttpStatus.OK);
    }


    /**
     * Shows the area sensors present in a given Geographical Area
     *
     * @param id is the geographical area id.
     * @return OK status and a list of Area Sensor DTOs.
     */
    @GetMapping("/areas/{id}/sensors")
    public ResponseEntity<List<AreaSensorDTO>> getAreaSensors(@PathVariable long id) {
        List<AreaSensorDTO> areaSensorDTOList = geographicAreaRepo.getDTOById(id).getSensors();
        for (AreaSensorDTO s : areaSensorDTOList) {
            if (userService.getUsernameFromToken().equals("admin")) {
                Link deleteSelf = linkTo(methodOn(SensorSettingsWebController.class).removeAreaSensor(id, s.getSensorId())).
                        withRel("Delete this Sensor");
                Link deactivateSelf = linkTo(methodOn(SensorSettingsWebController.class).deactivateAreaSensor(id, s.getSensorId())).
                        withRel("Deactivate this Sensor");
                s.add(deleteSelf);
                s.add(deactivateSelf);
            }
        }
        return new ResponseEntity<>(areaSensorDTOList, HttpStatus.OK);
    }

    /**
     * Add daughter area to a mother area
     *
     * @param idAreaChild  of the geoArea to be added
     * @param idAreaParent of the geoArea with the daughter area
     * @return string with info if geoArea was added or not
     */
    @PutMapping("/{idParent}/{idChild}")
    public ResponseEntity<Object> addChildArea(@PathVariable("idChild") long idAreaChild,
                                               @PathVariable("idParent") long idAreaParent) {
        GeographicAreaDTO result;
        Link link;
        try {
            if (geographicAreaRepo.addChildArea(idAreaChild, idAreaParent)) {
                result = geographicAreaRepo.getDTOByIdWithParent(idAreaParent);
                link = linkTo(methodOn(GeoAreasWebController.class).getGeographicArea(idAreaChild)).withRel("See geographic area");
                result.add(link);
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("The Geographic Area hasn't been added. The daughter area is already contained in the mother area.", HttpStatus.CONFLICT);
            }
        } catch (NoSuchElementException ok) {
            return new ResponseEntity<>("There is no  Geographic Area with that ID.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/list/{idParent}/{idChild}")
    public ResponseEntity<Object> removeChildArea(@PathVariable("idChild") long idAreaChild,
                                                  @PathVariable("idParent") long idAreaParent) {
        GeographicAreaDTO result;
        Link link;
        try {
            if (geographicAreaRepo.removeChildArea(idAreaChild, idAreaParent)) {
                result = geographicAreaRepo.getDTOByIdWithParent(idAreaParent);
                link = linkTo(methodOn(GeoAreasWebController.class).getGeographicArea(idAreaChild)).withRel("See geographic area");
                result.add(link);
                return new ResponseEntity<>(geographicAreaRepo.getDTOByIdWithParent(idAreaParent), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("The Geographic Area hasn't been removed. The daughter area is already not contained in the mother area.", HttpStatus.CONFLICT);
            }
        } catch (NoSuchElementException ok) {
            return new ResponseEntity<>("There is no  Geographic Area with that ID.", HttpStatus.NOT_FOUND);
        }
    }

    /* USER STORY US010 - As an Administrator, I want to deactivate a sensor in a geographical area, so that it will no longer be used. It can be reactivated later.*/

    /**
     * This method deactivates a sensor selected from a list of sensors of a previously selected geographic area
     */
    @PutMapping("/{id}/sensors/{id2}")
    public ResponseEntity<Object> deactivateSensor(@PathVariable("id") long id,
                                                   @PathVariable("id2") String sensorId) {
        try {
            if (geographicAreaRepo.deactivateAreaSensor(id, sensorId)) {
                return new ResponseEntity<>("The sensor was successfully deactivated from the selected geographic area.", HttpStatus.OK);
            }
            return new ResponseEntity<>("There is no sensor with that ID in this geographic area.", HttpStatus.NOT_FOUND);
        } catch (NoSuchElementException ok) {
            return new ResponseEntity<>("There is no geographic area", HttpStatus.NOT_FOUND);
        }
    }

    /* USER STORY US011 - As an Administrator, I want to removeSensor a sensor from a geographical area, so that it will no
    longer be used.*/

    /**
     * This method removes a sensor selected from a list of sensors of a previously selected geographic area
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> removeSensor(@PathVariable("id") long id, @RequestBody String sensorId) {
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
    @GetMapping("/{id}")
    public ResponseEntity<Object> getGeographicArea(@PathVariable("id") long id) {
        return new ResponseEntity<>(geographicAreaRepo.getDTOByIdWithParent(id), HttpStatus.OK);
    }

}
