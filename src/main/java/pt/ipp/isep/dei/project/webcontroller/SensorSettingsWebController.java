package pt.ipp.isep.dei.project.webcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pt.ipp.isep.dei.project.model.geographicarea.AreaSensor;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicAreaRepository;
import pt.ipp.isep.dei.project.repository.GeographicAreaCrudeRepo;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@ApplicationScope
@RequestMapping("/sensorsettings")
public class SensorSettingsWebController {

    @Autowired
    private GeographicAreaRepository geographicAreaService;

    @Autowired
    private GeographicAreaCrudeRepo geographicAreaRepository;


    // Part 1 - Geographical Areas

    @GetMapping("/areas")
    public List<GeographicArea> retrieveAllGeographicAreas() {
        return geographicAreaService.getAll();
    }

    @GetMapping("/areas/{id}")
    public GeographicArea retrieveGA(@PathVariable long id) {
        GeographicArea geographicArea;
        Optional<GeographicArea> aux = geographicAreaRepository.findById(id);
        if (!aux.isPresent()) {
            throw new IllegalArgumentException("Geographic Area not found - 404");
        }
        geographicArea = aux.get();
        return geographicArea;
    }

    // Part 2 - Sensors

    @GetMapping("/areas/{id}/sensors")
    public List<AreaSensor> retrieveAllSensors(@PathVariable long id) {
        Optional<GeographicArea> aux = geographicAreaRepository.findById(id);
        if (!aux.isPresent()) {
            throw new IllegalArgumentException("Geographic Area not found - 404");
        }
        GeographicArea geographicArea = aux.get();
        return geographicArea.getAreaSensors();
    }

    @PostMapping("/areasensors")
    public ResponseEntity<Object> createAreaSensor(@RequestBody AreaSensor areaSensor) {
        GeographicArea cenas = new GeographicArea();
        cenas.addSensor(areaSensor);
        geographicAreaService.updateGeoArea(cenas);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(areaSensor.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

//    @RequestMapping(method = RequestMethod.POST, path = "/createsensor")
//    public void testing(
//            @RequestBody(required = true) AreaSensorDTO areaSensorDTO) {
//        AreaSensorDTO areaSensor = new AreaSensorDTO();
//        areaSensor.setId();
//        areaSensor.setName();
//        areaSensor.setDateStartedFunctioning();
//        areaSensor.setUnits();
//        areaSensor.setGeographicAreaID();
//        areaSensor.setLatitude();
//        areaSensor.setLongitude();
//        areaSensor.setAltitude();
//        areaSensor.setActive(true);
//    }

}

