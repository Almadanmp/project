package pt.ipp.isep.dei.project.controllerweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.ipp.isep.dei.project.dto.AreaTypeDTO;
import pt.ipp.isep.dei.project.dto.GeographicAreaDTO;
import pt.ipp.isep.dei.project.dto.LocalDTO;
import pt.ipp.isep.dei.project.dto.mappers.AreaTypeMapper;
import pt.ipp.isep.dei.project.dto.mappers.GeographicAreaMapper;
import pt.ipp.isep.dei.project.dto.mappers.LocalMapper;
import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.model.areatype.AreaTypeRepository;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicAreaRepository;
import pt.ipp.isep.dei.project.repository.GeographicAreaCrudeRepo;
import pt.ipp.isep.dei.project.repository.SensorTypeCrudeRepo;

import java.util.List;

@RestController
@RequestMapping(value = "/geographic_area_settings")
public class GASettingsWebController {
    @Autowired
    private GeographicAreaCrudeRepo geographicAreaRepository;
    @Autowired
    private AreaTypeRepository areaTypeRepository;
    @Autowired
    private SensorTypeCrudeRepo sensorTypeRepo;
    @Autowired
    private GeographicAreaRepository geographicAreaService;

    /* User Story - 03 As a System Administrator I want to Create a new Geographic Area */

    /**
     * Method that creates a new Geographic Area and adds it to our database.
     *
     * @param geographicAreaService Is the service responsible for accessing the repository of geographic areas.
     * @return success if a new GA is added, false otherwise.
     */
    public boolean addNewGeoAreaToList(GeographicAreaRepository geographicAreaService, GeographicAreaDTO geoAreaDTO) {
        GeographicArea geoToAdd = GeographicAreaMapper.dtoToObject(geoAreaDTO);
        return geographicAreaService.addAndPersistGA(geoToAdd);
    }

    /**
     * Method to create a DTO of Geographic Area
     *
     * @return Geographic Area DTO
     */
    @PostMapping(value = "/areas")
    ResponseEntity<Object> createGeoAreaDTO(@RequestBody GeographicAreaDTO dto) {
        //   GeographicArea geoArea = geographicAreaService.createGA(newName,typeArea, length, width, local);
        //   GeographicAreaMapper.objectToDTO(geoArea);
        //  geographicAreaService.addAndPersistGA(geoArea);
        if (geographicAreaService.addAndPersistDTO(dto)) {
            return new ResponseEntity<>("The Geographic Area has been created.", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("The Geographic Area hasn't been created.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/areas")
    public @ResponseBody
    List<GeographicAreaDTO> getAllGeoAreasDTO() {
        return geographicAreaService.getAllDTO();
    }

    /**
     * Method to create a DTO Localization
     *
     * @param latitude  value for latitude
     * @param longitude value for longitude
     * @param altitude  value for altitude
     * @return returns a Local DTO
     */
    public LocalDTO createLocalDTO(double latitude, double longitude, double altitude) {
        Local local = new Local(latitude, longitude, altitude);
        return LocalMapper.objectToDTO(local);
    }

//{
//        "id": "1",
//        "name": "Gaia",
//        "typeArea": "urban area",
//        "length": "500",
//        "width":"100",
//        "latitude": 6,
//        "longitude": 6,
//        "altitude": 6,
//        "description": "cidade gloriosa"
//        }
}
