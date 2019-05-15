package pt.ipp.isep.dei.project.controllerweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.ipp.isep.dei.project.dto.EnergyGridDTO;
import pt.ipp.isep.dei.project.model.energy.EnergyGrid;
import pt.ipp.isep.dei.project.model.energy.EnergyGridRepository;
import pt.ipp.isep.dei.project.repository.EnergyGridCrudeRepo;

import java.util.List;

@RestController
@RequestMapping("/gridSettings")
public class EnergyGridSettingsWebController {

    @Autowired
    private EnergyGridCrudeRepo gridRepo;

    @Autowired
    private EnergyGridRepository energyGridRepository;

    @GetMapping(value = "/grids")
    public @ResponseBody
    List<EnergyGrid> getAllGrids() {
        return gridRepo.findAll();
    }

    /*
     * USER STORY 130 - As an Administrator, I want to create a energy grid, so that I can define the rooms that are
     * attached to it and the contracted maximum power for that grid.
     */
    @PostMapping(value = "/grids")
    public ResponseEntity<String> createEnergyGrid(@RequestBody EnergyGridDTO energyGridDTO){
        energyGridRepository.createEnergyGridDTO(energyGridDTO);
        return new ResponseEntity<>(
                "Energy grid created and added to the house with success!",
                HttpStatus.CREATED);
    }
}
