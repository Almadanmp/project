package pt.ipp.isep.dei.project.controllerweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pt.ipp.isep.dei.project.dto.AreaSensorDTO;
import pt.ipp.isep.dei.project.dto.GeographicAreaDTO;
import pt.ipp.isep.dei.project.dto.mappers.AreaSensorMapper;
import pt.ipp.isep.dei.project.dto.mappers.GeographicAreaMapper;
import pt.ipp.isep.dei.project.model.geographicarea.AreaSensor;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicAreaRepository;
import pt.ipp.isep.dei.project.repository.GeographicAreaCrudeRepo;

import java.net.URI;
import java.util.List;

@RestController
@ApplicationScope
@RequestMapping("/sensorsettings")
public class SensorSettingsWebController {

    @Autowired
    private GeographicAreaRepository geographicAreaRepository;

    @Autowired
    GeographicAreaCrudeRepo geographicAreaCrudRepo;

    // Part 1 - Geographical Areas

    @GetMapping("/areas")
    public List<GeographicAreaDTO> retrieveAllGeographicAreas() {
        return geographicAreaRepository.getAllDTO();
    }

    @GetMapping("/areas/{id}")
    public GeographicAreaDTO retrieveGA(@PathVariable long id) {
        return geographicAreaRepository.getDTOById(id);
    }

    // Part 2 - Sensors

    @GetMapping("/areas/{id}/sensors")
    public List<AreaSensorDTO> retrieveAllSensors(@PathVariable long id) {
        return geographicAreaRepository.getDTOById(id).getSensorDTOs();
    }

    @PostMapping("/areas/{id}/create")
    public ResponseEntity<AreaSensorDTO> createAreaSensor(@RequestBody AreaSensorDTO areaSensorDTO,
                                                          @PathVariable long id) {
        GeographicAreaDTO geoArea = geographicAreaRepository.getDTOById(id);
        geographicAreaRepository.addSensorDTO(geoArea, areaSensorDTO);
        geographicAreaRepository.updateAreaDTO(geoArea);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(areaSensorDTO.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    // us010 deactivate geo area sensor


    //get all area sensors
    @PatchMapping(value = "/deactivate")
    public ResponseEntity<Object> deactivateAreaSensor(@RequestBody AreaSensorDTO areaSensorDTO) {
        areaSensorDTO.setActive(false);
        long id = areaSensorDTO.getGeographicAreaID();
        GeographicAreaDTO geographicArea = geographicAreaRepository.getDTOById(id);
        geographicAreaRepository.updateGeoArea(GeographicAreaMapper.dtoToObject(geographicArea));
        return new ResponseEntity <> ("body", HttpStatus.OK);
    }
}

// CODE TO TEST ON POSTMAN
/*
{
        "id": "Teste2",
        "name": "Mae estou na BD",
        "typeSensor": "temperature",
        "units": "mm",
        "latitude": 6,
        "longitude": 6,
        "altitude": 6,
        "dateStartedFunctioning": "2018-10-12"
        }
*/

