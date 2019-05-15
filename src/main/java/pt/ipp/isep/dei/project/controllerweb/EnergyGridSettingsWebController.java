package pt.ipp.isep.dei.project.controllerweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pt.ipp.isep.dei.project.model.energy.EnergyGrid;
import pt.ipp.isep.dei.project.repository.EnergyGridCrudeRepo;

import java.util.List;

@RestController
@RequestMapping("/gridSettings")
public class EnergyGridSettingsWebController {

    @Autowired
    private EnergyGridCrudeRepo gridRepo;

    @GetMapping(value = "/grids")
    public @ResponseBody
    List<EnergyGrid> getAllGrids() {
        return gridRepo.findAll();
    }
}
