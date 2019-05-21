package pt.ipp.isep.dei.project.controllerweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.ipp.isep.dei.project.dto.GeographicAreaDTO;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicAreaRepository;

import java.util.List;

@RestController
@RequestMapping(value = "/geographic_area_settings")
public class GASettingsWebController {

    @Autowired
    private GeographicAreaRepository geographicAreaRepo;

    /* User Story - 03 As a System Administrator I want to Create a new Geographic Area */

    /**
     * Method to create a DTO of Geographic Area
     *
     * @return ResponseEntity
     */
    @PostMapping(value = "/areas")
    public ResponseEntity<Object> createGeoArea(@RequestBody GeographicAreaDTO dto) {
        if (geographicAreaRepo.addAndPersistDTO(dto) && dto.getId() != null && dto.getName() != null && dto.getTypeArea() != null && dto.getLocal() != null) {
            return new ResponseEntity<>("The Geographic Area has been created.", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("The Geographic Area hasn't been created. You have entered a repeated or" +
                    " invalid Area.", HttpStatus.CONFLICT);
        }
    }

    /**
     * this method displays all the information of the Geographic Areas DTOs
     *
     * @return
     */
    @GetMapping("/areas")
    public List<GeographicAreaDTO> getAllGeographicAreas() {
        return geographicAreaRepo.getAllDTO();
    }


    /**
     * US007 WEB controller: Insert a daughter Area in a mother Area
     *
     * @param idAreaMother   sensor id
     * @return ok status if the area sensor exists
     */
    @PutMapping("areas/{idMother}")
    public ResponseEntity<Object> getDaughterArea(@RequestBody long idAreaDaughter, @PathVariable("idMother") long idAreaMother) {
        GeographicAreaDTO geographicAreaMother = geographicAreaRepo.getDTOByIdWithMother(idAreaMother);
        GeographicAreaDTO geographicAreaDaughter = geographicAreaRepo.getDTOByIdWithMother(idAreaDaughter);
        if (geographicAreaRepo.addDaughterDTO(geographicAreaMother, geographicAreaDaughter)) {
            geographicAreaRepo.updateAreaDTOWithMother(geographicAreaMother);
            return new ResponseEntity<>("The Geographic Area has been added.", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("The Geographic Area hasn't been added. You have entered a repeated or" +
                " invalid Area.", HttpStatus.CONFLICT);
    }


    @GetMapping("areas/{id}/daughter")
    public List<GeographicAreaDTO> getDaughterAreas(@PathVariable("id") long id) {
        return geographicAreaRepo.getDTOById(id).getDaughterAreas();
    }

}
