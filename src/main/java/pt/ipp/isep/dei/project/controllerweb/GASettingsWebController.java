package pt.ipp.isep.dei.project.controllerweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.ipp.isep.dei.project.dto.GeographicAreaDTO;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicAreaRepository;

import java.util.List;

@RestController
@RequestMapping(value = "/.")
public class GASettingsWebController {

    @Autowired
    private GeographicAreaRepository geographicAreaService;

    /* User Story - 03 As a System Administrator I want to Create a new Geographic Area */

    @GetMapping(value = "/areas")
    public @ResponseBody
    List<GeographicAreaDTO> getAllGeoAreasDTO() {
        return geographicAreaService.getAllDTO();
    }
    /**
     * Method to create a DTO of Geographic Area
     *
     * @return Geographic Area DTO
     */
    @PostMapping(value = "/areas")
    ResponseEntity<Object> createGeoAreaDTO(@RequestBody GeographicAreaDTO dto) {
        if (geographicAreaService.addAndPersistDTO(dto)) {
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
