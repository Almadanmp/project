package pt.ipp.isep.dei.project.controllerweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.ipp.isep.dei.project.dto.HouseWithoutGridsDTO;
import pt.ipp.isep.dei.project.dto.RoomDTOWeb;
import pt.ipp.isep.dei.project.dto.mappers.HouseMapper;
import pt.ipp.isep.dei.project.dto.mappers.RoomWebMapper;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.house.HouseRepository;
import pt.ipp.isep.dei.project.model.room.Room;
import pt.ipp.isep.dei.project.model.room.RoomRepository;
import pt.ipp.isep.dei.project.repository.HouseCrudeRepo;

@RestController
@RequestMapping("/houseSettings")
public class HouseConfigurationWebController {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private HouseCrudeRepo houseCrudeRepo;

    // USER STORY 101

    /**
     * @param houseDTO is the house we're going to change the location of.
     */
    @PutMapping(value = "/house")
    public ResponseEntity<Object> setHouseLocal(@RequestBody HouseWithoutGridsDTO houseDTO) {
        House house = HouseMapper.dtoWithoutGridsToObject(houseDTO);
        houseCrudeRepo.save(house);
        return new ResponseEntity<>("yay", HttpStatus.OK);
    }

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
    public String createRoom(@RequestBody RoomDTOWeb roomDTOWeb) {
        Room room = RoomWebMapper.dtoToObject(roomDTOWeb);
        String houseID = houseRepository.getHouseId();
        room.setHouseID(houseID);
        if (roomRepository.addRoomToCrudRepository(room)) {
            return "The room was successfully added.";
        }
        return "The room you are trying to create already exists.";
    }

}
