package pt.ipp.isep.dei.project.controllerweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.ipp.isep.dei.project.dto.EnergyGridDTO;
import pt.ipp.isep.dei.project.dto.RoomDTO;
import pt.ipp.isep.dei.project.dto.RoomDTOWeb;
import pt.ipp.isep.dei.project.model.energy.EnergyGrid;
import pt.ipp.isep.dei.project.model.energy.EnergyGridRepository;
import pt.ipp.isep.dei.project.repository.EnergyGridCrudRepo;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/gridSettings")
public class EnergyGridSettingsWebController {

    @Autowired
    private EnergyGridCrudRepo gridRepo;

    @Autowired
    private EnergyGridRepository energyGridRepository;

    /* US 145 - As an Administrator, I want to have a list of existing rooms attached to a house grid, so that I can
     * attach/detach rooms from it.
     */
    @GetMapping(value = "/grids")
    public @ResponseBody
    List<EnergyGrid> getAllGrids() {
        return gridRepo.findAll();
    }

    /* US 145 - As an Administrator, I want to have a list of existing rooms attached to a house grid, so that I can
     * attach/detach rooms from it.
     */
    @GetMapping(value = "/grids/{energyGridId}")
    public @ResponseBody
    List<RoomDTOWeb> getRoomsWebDtoInGrid(@PathVariable("energyGridId") String gridId) {
        return energyGridRepository.getRoomsDtoWebInGrid(gridId);
    }

    /* US 147 - As an Administrator, I want to attach a room to a house grid, so that the room’s power and energy
     * consumption is included in that grid.
     */
    @PostMapping(value = "/grids/{energyGridId}")
    public ResponseEntity<String> attachRoomToGrid(@RequestBody RoomDTO roomDTO, @PathVariable("energyGridId") String gridId) {
        boolean attached = energyGridRepository.attachRoomToGrid(roomDTO, gridId);
        if (attached) {
            return new ResponseEntity<>("Room successfully added to the grid!",
                    HttpStatus.OK);
        }
        return new ResponseEntity<>("It wasn't possible to add the room. Please try again.", HttpStatus.CONFLICT);
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
            return new ResponseEntity<>("There is no grid with that ID.", HttpStatus.NOT_FOUND);
        }
    }
}

/*
US 147
{	"name": "B102",
	"description": "Reprographics Centre",
	"floor": "1",
	"width": 7,
	"length": 21,
	"height": 3.5
}*/
