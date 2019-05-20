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
        if (geographicAreaRepo.addAndPersistDTO(dto) && dto.getId()!=null && dto.getName()!= null && dto.getTypeArea()!=null && dto.getLocalDTO()!=null) {
            return new ResponseEntity<>("The Geographic Area has been created.", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("The Geographic Area hasn't been created. You have entered a repeated or" +
                    " invalid Area.", HttpStatus.CONFLICT);
        }
    }

    /**
     * this method displays all the information of the Geographic Areas DTOs
     * @return
     */
    @GetMapping("/areas")
    public List<GeographicAreaDTO> getAllGeographicAreas() {
        return geographicAreaRepo.getAllDTO();
    }


    /**
     * US007 WEB controller: deactivate arya sensor with id sensor
     * @param idAreaDaughter arya id where the arya sensor id
     * @param idAreaMother sensor id
     * @return ok status if the area sensor exists
     */
    @PutMapping("areas/{idDaughter}/{idMother}")
    public ResponseEntity<Object> setMotherArea(@PathVariable("idDaughter") long idAreaDaughter, @PathVariable("idMother") long idAreaMother) {
        GeographicAreaDTO geographicAreaDaughter = geographicAreaRepo.getDTOById(idAreaDaughter);
        GeographicAreaDTO geographicAreaMother = geographicAreaRepo.getDTOById(idAreaMother);
        geographicAreaRepo.setMotherDTO(geographicAreaDaughter,geographicAreaMother);
        geographicAreaRepo.updateAreaDTOWithMother(geographicAreaDaughter, geographicAreaMother);
        if (geographicAreaDaughter.getMotherAreaID().equals(geographicAreaMother.getId())) {
            return new ResponseEntity<>("maezinga", HttpStatus.OK);
        }
        return new ResponseEntity<>("orfaaaa", HttpStatus.NOT_FOUND);
    }

    @GetMapping("areas/{idDaughter}/{idMother}")
    public Long getMotherArea (@PathVariable("idDaughter") long idAreaDaughter, @PathVariable("idMother") long idAreaMother){
        GeographicAreaDTO geographicAreaDaughter = geographicAreaRepo.getDTOById(idAreaDaughter);
        return geographicAreaRepo.getMotherDTO(geographicAreaDaughter);
    }


    @GetMapping("areas/{id}")
    public GeographicAreaDTO getArea(@PathVariable("id") long id) {
        return geographicAreaRepo.getDTOById(id);
    }

    }
