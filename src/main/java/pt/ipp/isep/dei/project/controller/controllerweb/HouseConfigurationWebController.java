package pt.ipp.isep.dei.project.controller.controllerweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.ipp.isep.dei.project.dto.AddressLocalGeographicAreaIdDTO;
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
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "http://localhost:3002"}, maxAge = 3600)
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
     * @param dto is a DTO with the location of the house we want to get changed.
     */
    @PutMapping(value = "/house")
    public ResponseEntity<Object> configureHouseLocation(@RequestBody AddressLocalGeographicAreaIdDTO dto) {
        HouseWithoutGridsDTO houseWithoutGridsDTO = houseRepository.getHouseWithoutGridsDTO();
        houseWithoutGridsDTO.setAddressAndLocalToDTOWithoutGrids(dto);
        if (houseRepository.updateHouseDTOWithoutGrids(houseWithoutGridsDTO)) {
            Link link = linkTo(methodOn(HouseConfigurationWebController.class).retrieveHouse()).withRel("Click here to see the House updated");
            houseWithoutGridsDTO.add(link);
            return new ResponseEntity<>(houseWithoutGridsDTO, HttpStatus.OK);
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
     * @param roomDTOMinimal roomDTOWeb to be added to repository
     * @return the room DTO in case the corresponding room was created in database
     * and HTTP Status OK, an error message and an error HTTP status otherwise.
     **/
    @PostMapping(value = "/room")
    public ResponseEntity<Object> createRoom(@RequestBody RoomDTOMinimal roomDTOMinimal) {
        if (!roomDTOMinimal.isNameValid()) {
            return new ResponseEntity<>("The room you introduced is invalid.", HttpStatus.BAD_REQUEST);
        }
        if (!roomDTOMinimal.areDimensionsValid()) {
            return new ResponseEntity<>("The room you introduced is invalid.", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        if (houseRoomService.addMinimalRoomDTOToHouse(roomDTOMinimal)) {
            Link link = linkTo(methodOn(HouseConfigurationWebController.class).deleteRoom(roomDTOMinimal)).withRel("Click here to delete room.");
            roomDTOMinimal.add(link);
            return new ResponseEntity<>(roomDTOMinimal, HttpStatus.CREATED);
        }
        return new ResponseEntity<>("The room you are trying to create already exists.", HttpStatus.CONFLICT);
    }

    //US 108

    /**
     * This method will show every House Room that is saved in the repository.
     *
     * @return A list of House Room DTOs and HTTP Status OK
     **/
    @GetMapping(value = "/houseRooms")
    public ResponseEntity<Object> getHouseRooms() {
        List<RoomDTOMinimal> roomDTOBarebones = roomRepository.getAllRoomDTOMinimal();
        for (RoomDTOMinimal roomDTO : roomDTOBarebones) {
            String roomID = roomDTO.getName();
            Link linkCurrentTemp = linkTo(methodOn(RoomMonitoringWebController.class).getCurrentRoomTemperature(roomID)).withRel("1.Click here to get current temperature.");
            roomDTO.add(linkCurrentTemp);
        }
        return new ResponseEntity<>(roomDTOBarebones, HttpStatus.OK);
    }

    //US 109 - As an Administrator, I want to edit the configuration of an existing room, so that I can reconfigure it

    /**
     * This method receives a room DTO and tries to edit the room with the corresponding ID in the repository
     * with the information provided in the DTO.
     *
     * @param roomDTOMinimal with the room ID and the information to be altered
     * @return the room DTO and Http Status OK in case the room is edited in the repository,
     * an error message and Http Status NOT FOUND in case the room does not exist
     * *
     **/
    @PutMapping(value = "/room")
    public ResponseEntity<Object> configureRoom(@RequestBody RoomDTOMinimal roomDTOMinimal) {
        if (!roomDTOMinimal.areDimensionsValid()) {
            return new ResponseEntity<>("The room you entered has invalid parameters.", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        if (roomRepository.configureRoom(roomDTOMinimal)) {
            return new ResponseEntity<>(roomDTOMinimal, HttpStatus.OK);
        }
        return new ResponseEntity<>("The room you are trying to edit does not exist in the database.", HttpStatus.NOT_FOUND);
    }

    //DELETE ROOM FROM HOUSE - To be used in Postman tests. NOT a User Story

    /**
     * This method receives a room DTO Web and will try to remove the corresponding
     * room from repository
     *
     * @param roomDTOMinimal roomDTOWeb to be deleted from repository
     * @return the Room DTO Web and HTTP status OK in case the room is deleted, an error
     * message and HTTP status NOT FOUND in case the room is not deleted
     **/
    @DeleteMapping(value = "/room")
    public ResponseEntity<Object> deleteRoom(@RequestBody RoomDTOMinimal roomDTOMinimal) {
        if (roomRepository.deleteRoom(roomDTOMinimal)) {
            return new ResponseEntity<>(roomDTOMinimal, HttpStatus.OK);
        }
        return new ResponseEntity<>("The room you are trying to delete does not exist in the database.", HttpStatus.NOT_FOUND);
    }

}
