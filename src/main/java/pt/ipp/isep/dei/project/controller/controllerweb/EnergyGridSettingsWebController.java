package pt.ipp.isep.dei.project.controller.controllerweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.ipp.isep.dei.project.dto.EnergyGridDTO;
import pt.ipp.isep.dei.project.dto.RoomDTOMinimal;
import pt.ipp.isep.dei.project.model.energy.EnergyGridRepository;
import pt.ipp.isep.dei.project.model.room.RoomRepository;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/gridSettings")
public class EnergyGridSettingsWebController {

    private static final String NO_GRID = "There is no grid with that ID.";

    @Autowired
    private EnergyGridRepository energyGridRepository;

    @Autowired
    private RoomRepository roomRepository;

    /* US 145 - As an Administrator, I want to have a list of existing rooms attached to a house grid, so that I can
     * attach/detach rooms from it.
     */
    @GetMapping(value = "/grids/{energyGridId}")
    public ResponseEntity<Object> getRoomsWebDtoInGrid(@PathVariable("energyGridId") String gridId) {
        try {
            List<RoomDTOMinimal> minimalRoomDTOs = energyGridRepository.getRoomsDtoWebInGrid(gridId);
            for (RoomDTOMinimal roomDTOMinimal : minimalRoomDTOs) {
                Link link = ControllerLinkBuilder.linkTo(HouseConfigurationWebController.class).slash(roomDTOMinimal.getName()).withRel("roomName");
                roomDTOMinimal.add(link);
            }
            return new ResponseEntity<>(minimalRoomDTOs, HttpStatus.OK);
        } catch (NullPointerException ok) {
            return new ResponseEntity<>(NO_GRID, HttpStatus.NOT_FOUND);
        }
    }

    /* US 147 - As an Administrator, I want to attach a room to a house grid, so that the room’s power and energy
     * consumption is included in that grid.
     */
    @PostMapping(value = "/grids/{energyGridId}")
    public ResponseEntity<Object> attachRoomToGrid(@RequestBody String roomID, @PathVariable("energyGridId") String gridId) {
        if (roomRepository.findRoomByID(roomID).isPresent()) {
            try {
                if (energyGridRepository.attachRoomToGrid(roomID, gridId)) {
                    RoomDTOMinimal roomDTOMinimal = energyGridRepository.getMinimalRoomDTOById(gridId, roomID);
                    return new ResponseEntity<>(roomDTOMinimal,
                            HttpStatus.OK);
                }
                return new ResponseEntity<>("It wasn't possible to add the room. Please try again.", HttpStatus.CONFLICT);
            } catch (NoSuchElementException e) {
                return new ResponseEntity<>(NO_GRID, HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>("There is no room with that ID.", HttpStatus.NOT_FOUND);
    }

    /*
     * US 130 - As an Administrator, I want to create a energy grid, so that I can define the rooms that are
     * attached to it and the contracted maximum power for that grid.
     */
    @PostMapping(value = "/grids")
    public ResponseEntity<String> createEnergyGrid(@RequestBody EnergyGridDTO energyGridDTO) {
        if (energyGridDTO.getHouseID() != null && energyGridDTO.getMaxContractedPower() != null && energyGridDTO.getName() != null) {
            if (energyGridRepository.createEnergyGrid(energyGridDTO)) {
                return new ResponseEntity<>(
                        "Energy grid created and added to the house with success!",
                        HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(
                        "A grid with the same name already exists!",
                        HttpStatus.CONFLICT);
            }
        }
        return new ResponseEntity<>("There was a problem creating the Energy grid, because one component is missing!",
                HttpStatus.BAD_REQUEST);
    }

    // USER STORY 149 -  As an Administrator, I want to detach a room from a house grid, so that the room’s power  and
    // energy  consumption  is  not  included  in  that  grid.  The  room’s characteristics are not changed.

    @DeleteMapping(value = "/grids/{energyGridId}")
    public ResponseEntity<String> detachRoomFromGrid(@RequestBody String roomID, @PathVariable("energyGridId") String
            gridID) {
        try {
            if (energyGridRepository.removeRoomFromGrid(roomID, gridID)) {
                return new ResponseEntity<>("The room was successfully detached from the grid.", HttpStatus.OK);
            }
            return new ResponseEntity<>("There is no room with that ID in this grid.", HttpStatus.NOT_FOUND);
        } catch (NoSuchElementException ok) {
            return new ResponseEntity<>(NO_GRID, HttpStatus.NOT_FOUND);
        }
    }
}