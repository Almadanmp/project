package pt.ipp.isep.dei.project.controllerweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.ipp.isep.dei.project.dto.AddressAndLocalDTO;
import pt.ipp.isep.dei.project.dto.HouseWithoutGridsDTO;
import pt.ipp.isep.dei.project.dto.RoomDTOWeb;
import pt.ipp.isep.dei.project.dto.mappers.HouseMapper;
import pt.ipp.isep.dei.project.model.bridgeservices.HouseRoomService;
import pt.ipp.isep.dei.project.model.house.HouseRepository;
import pt.ipp.isep.dei.project.repository.HouseCrudRepo;

@RestController
@RequestMapping("/houseSettings")
public class HouseConfigurationWebController {

    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private HouseRoomService houseRoomService;

    @Autowired
    private HouseCrudRepo houseCrudRepo;

    // USER STORY 101

    /**
     * This is a PUT method for US101 -
     *
     * @param addressAndLocalDTO is the location of the house we want to get changed.
     */
    @PutMapping(value = "/house")
    public ResponseEntity<Object> configureHouseLocation(@RequestBody AddressAndLocalDTO addressAndLocalDTO) {
        HouseWithoutGridsDTO house = houseRepository.getHouseWithoutGridsDTO();
        house.setAddressAndLocalToDTOWithoutGrids(addressAndLocalDTO);
        if (houseRepository.updateHouseDTOWithoutGrids(house)) {
            return new ResponseEntity<>("The house has been altered.", HttpStatus.OK);
        }
        return new ResponseEntity<>("The house hasn't been altered. Please try again", HttpStatus.NOT_ACCEPTABLE);
    }

    /**
     * Method to get the house
     *
     * @return
     */
    @GetMapping(path = "/house", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> retrieveHouse() {
        HouseWithoutGridsDTO house = houseRepository.getHouseWithoutGridsDTO();
        return new ResponseEntity<>(HouseMapper.dtoWithoutGridsToObject(house), HttpStatus.OK);
    }

    //US 105

    /**
     * This method creates receives a roomDTOWeb and tries to add the corresponding
     * room to the repository. The method will return a success message in case the
     * room is added, and a failure message in case it is not.
     *
     * @param roomDTOWeb roomDTOWeb to be added to repository
     * @return message that informs if room was added or not
     **/
    @PostMapping(value = "/room")
    public ResponseEntity<String> createRoom(@RequestBody RoomDTOWeb roomDTOWeb) {
        if (!isRoomDTOWebValid(roomDTOWeb)) {
            return new ResponseEntity<>("The room you introduced is invalid.", HttpStatus.BAD_REQUEST);
        }
        if (houseRoomService.addRoomDTOWebToHouse(roomDTOWeb)) {
            return new ResponseEntity<>("The room was successfully added.", HttpStatus.OK);
        }
        return new ResponseEntity<>("The room you are trying to create already exists.", HttpStatus.CONFLICT);
    }

    /**
     * This method checks if a roomDTOWeb is valid by
     * checking every attribute.
     *
     * @param roomDTOWeb to be validated
     * @return true in case it is valid, false otherwise.
     **/
    public boolean isRoomDTOWebValid(RoomDTOWeb roomDTOWeb) {
        String name = roomDTOWeb.getName();
        Double width = roomDTOWeb.getWidth();
        Double length = roomDTOWeb.getLength();
        Double height = roomDTOWeb.getHeight();

        if (name == null || Double.compare(width, 0.0) == 0) {
            return false;
        } else return Double.compare(length, 0.0) != 0 && Double.compare(height, 0.0) != 0;
    }

}
