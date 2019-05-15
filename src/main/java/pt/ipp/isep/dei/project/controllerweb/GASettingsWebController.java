package pt.ipp.isep.dei.project.controllerweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pt.ipp.isep.dei.project.dto.GeographicAreaDTO;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicAreaRepository;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/geographic_area_settings")
public class GASettingsWebController {

    @Autowired
    private GeographicAreaRepository geographicAreaRepo;

    /* User Story - 03 As a System Administrator I want to Create a new Geographic Area */

    /**
     * getter for all Geographic Areas.
     * @return
     */
    @GetMapping(value = "/areas")
    public @ResponseBody
    List<GeographicAreaDTO> getAllGeoAreasDTO() {
        return geographicAreaRepo.getAllDTO();
    }
    /**
     * Method to create a DTO of Geographic Area
     *
     * @return Geographic Area DTO
     */
    @PostMapping(value = "/areas")
    public ResponseEntity<Object> createGeoAreaDTO(@RequestBody GeographicAreaDTO dto) {
        if (geographicAreaRepo.addAndPersistDTO(dto)) {
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(dto.getId()).toUri();
            return new ResponseEntity<>("The Geographic Area has been created.", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("The Geographic Area hasn't been created. You have entered a repeated or" +
                    " invalid Area.", HttpStatus.NOT_ACCEPTABLE);
        }
    }

//{       "id": 66,
//        "name": "Gaia",
//        "typeArea": "urban area",
//        "length": 500,
//        "width": 100,
//        "localDTO": {
//            "latitude": 41,
//            "longitude": -8,
//            "altitude": 100,
//            "id": 0
//        },
//        "description": "cidade gloriosa"
//        }
}
