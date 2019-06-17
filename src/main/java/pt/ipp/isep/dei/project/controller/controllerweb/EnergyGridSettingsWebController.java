package pt.ipp.isep.dei.project.controller.controllerweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.ipp.isep.dei.project.dto.EnergyGridDTO;
import pt.ipp.isep.dei.project.dto.RoomDTO;
import pt.ipp.isep.dei.project.dto.RoomDTOMinimal;
import pt.ipp.isep.dei.project.dto.mappers.EnergyGridMapper;
import pt.ipp.isep.dei.project.model.bridgeservices.EnergyGridRoomService;
import pt.ipp.isep.dei.project.model.energy.EnergyGrid;
import pt.ipp.isep.dei.project.model.energy.EnergyGridRepository;
import pt.ipp.isep.dei.project.model.room.RoomRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/gridSettings")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "http://localhost:3002"}, maxAge = 3600)
public class EnergyGridSettingsWebController {

    private static final String NO_GRID = "There is no grid with that ID.";

    @Autowired
    private EnergyGridRepository energyGridRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private EnergyGridRoomService energyGridRoomService;

    @GetMapping(value = "/grids")
    public @ResponseBody
    ResponseEntity<Object> getAllGrids() {
        List<EnergyGrid> list = energyGridRepository.getAllGrids();
        List<EnergyGridDTO> result = new ArrayList<>();
        for (EnergyGrid energyGrid : list) {
            EnergyGridDTO dto = EnergyGridMapper.objectToDTO(energyGrid);
            Link link = linkTo(methodOn(EnergyGridSettingsWebController.class).getRoomsWebDtoInGrid(dto.getName())).withRel("1. Get rooms in Grid.");
            dto.add(link);
            result.add(dto);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    /* US 145 - As an Administrator, I want to have a list of existing rooms attached to a house grid, so that I can
     * attach/detach rooms from it.
     */
    @GetMapping(value = "/grids/{energyGridId}")
    public ResponseEntity<Object> getRoomsWebDtoInGrid(@PathVariable("energyGridId") String gridId) {
        try {
            List<RoomDTOMinimal> minimalRoomDTOs = energyGridRoomService.getRoomsDtoWebInGrid(gridId);
            for (RoomDTOMinimal roomDTOMinimal : minimalRoomDTOs) {
                Link linkDelete = linkTo(methodOn(EnergyGridSettingsWebController.class).detachRoomFromGrid(roomDTOMinimal, gridId)).withRel("1. Detach the room from the grid.");
                roomDTOMinimal.add(linkDelete);
            }
            return new ResponseEntity<>(minimalRoomDTOs, HttpStatus.OK);
        } catch (NullPointerException ok) {
            return new ResponseEntity<>(NO_GRID, HttpStatus.NOT_FOUND);
        }
    }

    /* Get a list of the rooms NOT attached to the grid
     */
//    @GetMapping(value = "/grids/{energyGridId}/notAttached")
//    public ResponseEntity<Object> getRoomsWebDtoNotInGrid(@PathVariable("energyGridId") String gridId) {
//        try {
//            List<RoomDTOMinimal> minimalRoomDTOs = energyGridRoomService.getRoomsDtoWebNotInGrid(gridId);
//            for (RoomDTOMinimal roomDTOMinimal : minimalRoomDTOs) {
//                Link link = ControllerLinkBuilder.linkTo(HouseConfigurationWebController.class).slash(roomDTOMinimal.getName()).withRel("roomName");
//                roomDTOMinimal.add(link);
//            }
//            return new ResponseEntity<>(minimalRoomDTOs, HttpStatus.OK);
//        } catch (NullPointerException ok) {
//            return new ResponseEntity<>(NO_GRID, HttpStatus.NOT_FOUND);
//        }
//    }

    /* US 147 - As an Administrator, I want to attach a room to a house grid, so that the room’s power and energy
     * consumption is included in that grid.
     */
    @PostMapping(value = "/grids/{energyGridId}")
    public ResponseEntity<Object> attachRoomToGrid(@RequestBody RoomDTO roomDTO, @PathVariable("energyGridId") String gridId) {
        if (roomRepository.findRoomByID(roomDTO.getName()).isPresent()) {
            try {
                if (energyGridRoomService.attachRoomToGrid(roomDTO.getName(), gridId)) {
                    RoomDTOMinimal roomDTOMinimal = energyGridRoomService.getMinimalRoomDTOById(gridId, roomDTO.getName());
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
    public ResponseEntity<String> detachRoomFromGrid(@RequestBody RoomDTOMinimal roomID, @PathVariable("energyGridId") String gridID) {
        try {
            if (energyGridRoomService.removeRoomFromGrid(roomID.getName(), gridID)) {
                return new ResponseEntity<>("The room was successfully detached from the grid.", HttpStatus.OK);
            }
            return new ResponseEntity<>("There is no room with that ID in this grid.", HttpStatus.NOT_FOUND);
        } catch (NoSuchElementException ok) {
            return new ResponseEntity<>(NO_GRID, HttpStatus.NOT_FOUND);
        }
    }
}