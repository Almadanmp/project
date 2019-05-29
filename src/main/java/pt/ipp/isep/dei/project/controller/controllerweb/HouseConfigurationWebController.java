package pt.ipp.isep.dei.project.controller.controllerweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.ipp.isep.dei.project.dto.AddressAndLocalDTO;
import pt.ipp.isep.dei.project.dto.HouseWithoutGridsDTO;
import pt.ipp.isep.dei.project.dto.RoomDTOMinimal;
import pt.ipp.isep.dei.project.dto.mappers.HouseMapper;
import pt.ipp.isep.dei.project.model.bridgeservices.HouseRoomService;
import pt.ipp.isep.dei.project.model.house.HouseRepository;
import pt.ipp.isep.dei.project.model.room.RoomRepository;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/houseSettings")
public class HouseConfigurationWebController {

    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private HouseRoomService houseRoomService;

    // USER STORY 101

     /**
     * This is a PUT method for US101 - change the location of the house
     *
     * @param addressAndLocalDTO is a DTO with the location of the house we want to get changed.
     */
    @PutMapping(value = "/house")
    public ResponseEntity<Object> configureHouseLocation(@RequestBody AddressAndLocalDTO addressAndLocalDTO) {
        HouseWithoutGridsDTO house = houseRepository.getHouseWithoutGridsDTO();
        house.setAddressAndLocalToDTOWithoutGrids(addressAndLocalDTO);
        if (houseRepository.updateHouseDTOWithoutGrids(house)) {
            Link link = linkTo(methodOn(HouseConfigurationWebController.class).retrieveHouse()).withRel("Click here to see the House updated");
            house.add(link);
            return new ResponseEntity<>(house, HttpStatus.OK);
        }
        return new ResponseEntity<>("The house hasn't been altered. Please try again", HttpStatus.BAD_REQUEST);
    }

    /**
     * Method to retrieve the house from the repository
     *
     * @return house
     */
    @GetMapping(path = "/house", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> retrieveHouse() {
        HouseWithoutGridsDTO house = houseRepository.getHouseWithoutGridsDTO();
        return new ResponseEntity<>(HouseMapper.dtoWithoutGridsToObject(house), HttpStatus.OK);
    }

    //US 105

    /**
     * This method creates receives a roomDTOMinimal and tries to add the corresponding
     * room to the repository. The method will return a success message in case the
     * room is added, and a failure message in case it is not.
     *
     * @param roomDTOMinimal roomDTOMinimal to be added to repository
     * @return message that informs if room was added or not
     **/
    @PostMapping(value = "/room")
    public ResponseEntity<String> createRoom(@RequestBody RoomDTOMinimal roomDTOMinimal) {
        if (!roomDTOMinimal.isNameValid()) {
            return new ResponseEntity<>("The room you introduced is invalid.", HttpStatus.BAD_REQUEST);
        }
        if(!roomDTOMinimal.areDimensionsValid()){
            return new ResponseEntity<>("The room you introduced is invalid.", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        if (houseRoomService.addRoomDTOWebToHouse(roomDTOMinimal)) {
            return new ResponseEntity<>("The room was successfully added.", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("The room you are trying to create already exists.", HttpStatus.CONFLICT);
    }

    //US 108

    /**
     * This method will show every House Room that is saved in the repository.
     *
     * @return List of House Rooms
     **/
    @GetMapping(value = "/houseRooms")
    public ResponseEntity<Object> getHouseRooms() {
        List<RoomDTOMinimal> roomDTOBarebones = roomRepository.getAllRoomWebDTOs();
        for (RoomDTOMinimal roomDTO : roomDTOBarebones) {
            Link link = ControllerLinkBuilder.linkTo(HouseConfigurationWebController.class).slash(roomDTO.getName()).withRel("roomName");
            roomDTO.add(link);
        }
        return new ResponseEntity<>(roomDTOBarebones, HttpStatus.OK);
    }

}
